package com.sunbeam.dao;

import com.sunbeam.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackDao extends JpaRepository<Rack, Long> {
    Rack findByRackId(Long rackId);
}
