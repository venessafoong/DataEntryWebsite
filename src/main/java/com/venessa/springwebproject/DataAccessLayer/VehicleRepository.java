package com.venessa.springwebproject.DataAccessLayer;

import com.venessa.springwebproject.Model.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    //    @Query("SELECT v FROM Vehicle v WHERE v.vehicleNumber LIKE %?1% OR v.model LIKE %?1% ")
//            "OR v.brand LIKE %?1% " +
//            "OR v.registrationDate LIKE %?1% " +
//            "OR v.coeExpiry LIKE %?1% " +
//            "OR v.roadTaxExpiry LIKE %?1% " +
//            "OR v.fuelType LIKE %?1% " +
//            "OR v.transmission LIKE %?1% " +
//            "OR v.soldDate LIKE %?1%) " +
//            "OR (v INSTANCE OF Van AND v.colour LIKE %?1%)")
//    @Query("SELECT v FROM Vehicle v WHERE CONCAT(v.type, ' ', v.vehicleNumber, ' ', v.model, ' ', v.brand, ' ', v.colour, v.body, ' ', v.tonnage, ' ', v.length, ' ', ' ', v.registrationDate, ' ', v.coeExpiry, ' ', v.roadTaxExpiry, ' ', v.fuelType, ' ', v.transmission) LIKE %?1%")
//
    @Query(value = "SELECT * FROM vehicle WHERE (vehicle_number LIKE %?1% " +
            "OR model LIKE %?1% " +
            "OR brand LIKE %?1% " +
            "OR registration_date LIKE %?1% " +
            "OR road_tax_expiry LIKE %?1% " +
            "OR coe_expiry LIKE %?1% " +
            "OR fuel_type LIKE %?1% " +
            "OR transmission LIKE %?1% " +
            "OR type LIKE %?1% " +
            "OR (type = 'Van' AND colour LIKE %?1%)" +
            "OR (type = 'Lorry' AND body LIKE %?1%)" +
            "OR (type = 'Lorry' AND tonnage LIKE %?1%)" +
            "OR (type = 'Lorry' AND length LIKE %?1%))",
            nativeQuery = true)
    public List<Vehicle> findAll(String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.soldDate = :soldDate WHERE v.vehicleId = :vehicleId")
    public int updateSoldDate(@Param("vehicleId") long vehicleId, @Param("soldDate") LocalDate soldDate);

    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}
