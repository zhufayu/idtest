package com.dmall.distributedidtest.repository.second;

import com.dmall.distributedidtest.domain.second.SecondUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSecondRepository extends JpaRepository<SecondUser, Long> {
    @Query(value = "select * from f_user b where b.order_id=?1", nativeQuery = true)
    public List<SecondUser> getNativeSQL(Long orderId);
}
