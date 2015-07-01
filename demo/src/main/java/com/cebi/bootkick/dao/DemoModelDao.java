package com.cebi.bootkick.dao;

import com.cebi.bootkick.model.DemoModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DemoModelDao extends CrudRepository<DemoModel, Long> {
    
    public List<DemoModel> findByName(String name);

}
