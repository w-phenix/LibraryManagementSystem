package library.util;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class DbUtil {
	
	private static String dbUrl = "bolt://localhost:7687";
	private static String dbUserName = "neo4j";
	private static String dbPassword = "neo4j";
	
	/**
	 * 连接数据库
	 * @return
	 * @throws Exception
	 */
	public static Driver getDriver() throws Exception{
		Driver driver = GraphDatabase.driver(dbUrl, AuthTokens.basic(dbUserName, dbPassword));
		return driver;
	}
	
	/**
	 * 关闭数据库驱动
	 * @param driver
	 * @throws Exception
	 */
	public static void closeDriver(Driver driver) throws Exception{
		if(driver != null) {
			driver.close();
		}
	}
}
