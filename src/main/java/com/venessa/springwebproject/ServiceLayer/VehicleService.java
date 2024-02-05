package com.venessa.springwebproject.ServiceLayer;

import com.venessa.springwebproject.DataAccessLayer.VehicleRepository;
import com.venessa.springwebproject.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService
{
    @Autowired
    private VehicleRepository vehicleRepo;

    public List<Vehicle> getAll(String keyword)
    {
        if (keyword != null)
        {
            return vehicleRepo.findAll(keyword);
        }
        return vehicleRepo.findAll();
    }

    public boolean updateSoldDate(long vehicleId, LocalDate soldDate)
    {
        if (vehicleRepo.updateSoldDate(vehicleId, soldDate) == 1)
        {
            return true;
        }
        return false;
    }

    public boolean addVehicle(Vehicle vehicle)
    {
        Optional<Vehicle> vehicleOptional = vehicleRepo.findByVehicleNumber(vehicle.getVehicleNumber());
        if(vehicleOptional.isEmpty())
        {
            vehicleRepo.save(vehicle);
            return true;
        }
        return false;
    }
}
