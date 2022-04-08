package com.dmall.distributedidtest.service;

import com.dmall.distributedidtest.domain.first.FirstUser;
import com.dmall.distributedidtest.domain.second.SecondUser;
import com.dmall.distributedidtest.repository.first.UserFirstRepository;
import com.dmall.distributedidtest.repository.second.UserSecondRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserFirstRepository firstRepository;
    @Autowired
    UserSecondRepository secondRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSourceSecond")
    private DataSource dataSource;

    @PersistenceContext
    @Qualifier("firstEntityManager")
    EntityManager entityManager;


    public void saveUser() {
        FirstUser user = new FirstUser();
        user.setName("test");
        user.setAge(25);
        FirstUser result = firstRepository.save(user);
        System.out.println(result.getId());

        SecondUser secondUser = new SecondUser();
        secondUser.setName("test");
        secondUser.setAge(12);
        SecondUser re = secondRepository.save(secondUser);
        System.out.println(re.getId());
    }

    public void saveUserUpdate() {
        FirstUser user = new FirstUser();
        user.setName("test");
        user.setAge(25);
        FirstUser result = firstRepository.save(user);
        System.out.println(result.getId());
        result.setAge(28);
        firstRepository.save(result);
    }

    public void saveUserDelete() {
        FirstUser user = new FirstUser();
        user.setName("test");
        user.setAge(25);
        FirstUser result = firstRepository.save(user);
        System.out.println(result.getId());
        firstRepository.deleteById(result.getId());
    }


    @Transactional
    public List<FirstUser> insertListBatch(List<FirstUser> users) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO f_user(name, age, order_id) VALUES ");
        for(FirstUser u : users) {
            sb.append("('" + u.getName() + "'," +  u.getAge() + "," + u.getOrderId()+ "),");
        }
        String sql = sb.toString().substring(0, sb.length() - 1);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            int i =0;
            if (rs.next()) {
                Long id = rs.getLong(1);
                FirstUser tmpU  = users.get(i);
                tmpU.setId(id);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn = null;
        }
        return users;
    }

    public List<FirstUser> selectByOrderId(Long orderId){
        return firstRepository.getNativeSQL(orderId);
    }

    public void saveUserList() {
        ArrayList<FirstUser> inputList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FirstUser user = new FirstUser();
            user.setName("test value");
            user.setAge(25);
            inputList.add(user);
        }
        FirstUser user = new FirstUser();
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i< 100; i++){
            buffer.append("invalid value-----------------------");
        }
        //user.setName(buffer.toString());
        user.setAge(25);
        inputList.add(user);
        firstRepository.saveAll(inputList);
    }
    public void entityManagerInsert() {
        String sql = "INSERT INTO f_user (name) VALUES ( 'aaa')";
        javax.persistence.Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    public long jdbcTemp() {
        String sql = "INSERT INTO f_user (name) VALUES ( 'aaa')";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("insertOneLine        " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbcTemplate.execute(sql);
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM f_user")
                .get(0).get("CNT");
    }

    public List<FirstUser> findFirstAll() {
        return firstRepository.findAll();
    }

    public List<SecondUser> findSecondAll() {
        return secondRepository.findAll();
    }
}
