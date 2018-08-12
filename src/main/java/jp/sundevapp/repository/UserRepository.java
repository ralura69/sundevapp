package jp.sundevapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.sundevapp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
