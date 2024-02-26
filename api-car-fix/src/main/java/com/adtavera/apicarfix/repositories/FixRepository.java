package com.adtavera.apicarfix.repositories;

import com.adtavera.apicarfix.models.Fix;
import org.springframework.data.repository.CrudRepository;

public interface FixRepository extends CrudRepository<Fix, Long> {
}
