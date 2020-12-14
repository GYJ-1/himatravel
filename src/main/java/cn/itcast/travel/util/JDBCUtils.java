package cn.itcast.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
	1. 声明静态数据源成员变量
	2. 创建连接池对象
	3. 定义公有的得到数据源的方法
	4. 定义得到连接对象的方法
	5. 定义关闭资源的方法
 */
public class JDBCUtils {
	// 1.	声明静态数据源成员变量
		public static DataSource ds;
		static {
			try {
				Properties pro = new Properties();
				InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
				pro.load(is);
				ds = DruidDataSourceFactory.createDataSource(pro);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static DataSource getDataSource(){
			return ds;
		}

		public static Connection getConnection()throws SQLException{
			return ds.getConnection();
		}

}
