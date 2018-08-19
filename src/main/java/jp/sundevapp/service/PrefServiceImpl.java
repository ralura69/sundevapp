package jp.sundevapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jp.sundevapp.entity.PrefEntity;
import jp.sundevapp.repository.PrefRepository;

@Service
public class PrefServiceImpl implements PrefService {

	@Autowired
	private PrefRepository repository;

	@Cacheable("pref")
	@Override
	public List<PrefEntity> getPrefList() {
		return repository.findAll();
	}

}
