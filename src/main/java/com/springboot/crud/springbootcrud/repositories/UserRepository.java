package com.springboot.crud.springbootcrud.repositories;

import com.springboot.crud.springbootcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user join fetch user.roles where user.email = :email")
    User findByEmail(@Param("email") String email);

    @Override
    List<User> findAll();

    @Override
    User getOne(Long aLong);

    @Override
    <S extends User> S save(S s);

    @Override
    void delete(User user);

    @Override
    void deleteById(Long aLong);

    @Override
    Optional<User> findById(Long aLong);
}
