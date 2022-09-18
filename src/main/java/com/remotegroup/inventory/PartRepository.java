package com.remotegroup.inventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long>{
    List<Part> findByProductId(Long id);
}