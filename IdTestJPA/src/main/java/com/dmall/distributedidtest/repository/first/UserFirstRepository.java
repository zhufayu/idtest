package com.dmall.distributedidtest.repository.first;

import com.dmall.distributedidtest.domain.first.FirstUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserFirstRepository extends JpaRepository<FirstUser, Long> {

    @Query(value = "select * from f_user b where b.order_id=?1", nativeQuery = true)
    public List<FirstUser> getNativeSQL(Long orderId);

}
