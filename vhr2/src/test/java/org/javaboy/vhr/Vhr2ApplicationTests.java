package org.javaboy.vhr;

import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.model.Hr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Vhr2ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private HrMapper hrMapper;
    @Test
    public void TestMApper(){
        Hr hr = hrMapper.selectByPrimaryKey(3);
        System.out.println(hr);

    }

    @Test
    public void testMap() {

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.get("1");
    }

    @Test
    public void datasourceTest() throws SQLException {

            // 获取数据源类型

        System.out.println("默认数据源为：" + dataSource.getClass());

            // 获取数据库连接对象

        Connection connection = dataSource.getConnection();

        // 判断连接对象是否为空

        System.out.println(connection != null);

        connection.close();

    }

}
