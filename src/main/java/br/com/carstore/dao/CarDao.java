package br.com.carstore.dao;

import br.com.carstore.model.CarDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbc;

    public CarDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<CarDTO> rowMapper = new RowMapper<>() {

        @Override
        public CarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarDTO dto = new CarDTO();
            dto.setId(rs.getLong("id"));
            dto.setName(rs.getString("name"));
            dto.setColor(rs.getString("color"));
            try {
                dto.setImage(rs.getString("image"));
                dto.setPrice(rs.getBigDecimal("price"));
                dto.setDescription(rs.getString("description"));
            } catch (SQLException e) {
                // Campos podem não existir ainda
            }
            return dto;
        }

    };

    // SELECT * FROM car -> List<CarDTO>
    public List<CarDTO> findAll() {

        String sql = "SELECT id, name, color, image, price, description FROM car";

        return jdbc.query(sql, rowMapper);

    }

    // INSERT INTO car (name, color, image, price, description)
    public void save(CarDTO carDTO) {

        String sql = "INSERT INTO car (name, color, image, price, description) VALUES (?, ?, ?, ?, ?)";

        jdbc.update(sql, carDTO.getName(), carDTO.getColor(), carDTO.getImage(), carDTO.getPrice(), carDTO.getDescription());

    }

    // DELETE FROM car WHERE id = ?
    public void deleteById(String id) {

        String sql = "DELETE FROM car WHERE id = ?";

        jdbc.update(sql, Long.valueOf(id));

    }

    // UPDATE car SET name = ?, color = ?, image = ?, price = ?, description = ? WHERE id = ?
    public void update(String id, CarDTO carDTO) {

        String sql = "UPDATE car SET name = ?, color = ?, image = ?, price = ?, description = ? WHERE id = ?";

        jdbc.update(sql, carDTO.getName(), carDTO.getColor(), carDTO.getImage(), carDTO.getPrice(), carDTO.getDescription(), Long.valueOf(id));

    }

}