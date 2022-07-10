package com.example.test01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Test01ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    public void getConntection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void contextLoads() {
    }

}
