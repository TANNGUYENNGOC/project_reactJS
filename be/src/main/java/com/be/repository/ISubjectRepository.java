package com.be.repository;

import com.be.model.product.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject,Integer> {
}
