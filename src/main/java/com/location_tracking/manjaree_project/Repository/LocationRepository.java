package com.location_tracking.manjaree_project.Repository;

import com.location_tracking.manjaree_project.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>
{
    @Query(value = "Select * from location where user_id=:u_id order by created_at",nativeQuery = true)
    List<Location> getLocationHistoryUser(Long u_id);

    @Query(value = "SELECT * FROM location WHERE user_id = :userId AND created_at >= :startDate AND created_at <= :endDate ORDER BY created_at", nativeQuery = true)
    List<Location> getLocationDateWise(Long userId, Date startDate, Date endDate);
}
