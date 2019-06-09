package com.js.sas.repository;

import com.js.sas.entity.SettlementSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISettlementSummaryRepository extends JpaRepository<SettlementSummaryEntity, Integer> {

}
