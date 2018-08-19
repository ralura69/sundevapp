package jp.sundevapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.sundevapp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserId(String userId);
	List<UserEntity> findByNameLike(String name);
	UserEntity findFirstByUserIdIsNotNullOrderByUserIdDesc();
	void deleteByUserId(String id);

}
