package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

//자동으로 item으로 된 Repository를 찾아서 여기에 주입을 함
//따라서 itemApiController에서 this.baseService 그런거 설정 안해줘도 돼
@Component 
public abstract class BaseService<Req, Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

}
