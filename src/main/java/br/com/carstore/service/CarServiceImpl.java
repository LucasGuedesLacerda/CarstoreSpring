package br.com.carstore.service;

import br.com.carstore.dao.CarJpaDao;
import br.com.carstore.model.CarDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarServiceImpl implements CarService  {
    private final CarJpaDao carDao;

    public CarServiceImpl(CarJpaDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public CarDTO save(CarDTO carDTO) {
        carDao.save(carDTO);
        return carDTO;
    }

    @Override
    public List<CarDTO> findAll() {
        return carDao.findAll();
    }

    @Override
    public CarDTO update(Long id, CarDTO carDTO) {
        carDao.update(String.valueOf(id), carDTO);
        return carDTO;
    }

    @Override
    public void deleteById(Long id) {
        carDao.deleteById(String.valueOf(id));
    }
}