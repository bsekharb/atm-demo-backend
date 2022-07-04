package com.org.repository;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.org.entity.BankCash;

@Repository
public interface BankCashRepository extends CrudRepository<BankCash, Integer> {

}
