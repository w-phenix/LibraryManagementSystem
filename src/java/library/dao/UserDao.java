package library.dao;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import library.model.User;
import library.util.StringUtil;

public class UserDao {
	/**
	 * 用户登录
	 * @param driver
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean login(Driver driver, User user) throws Exception{
		Transaction tx = driver.session().beginTransaction();
		try {
			StatementResult result = tx.run("MATCH (user:User{userName:{userName}, password:{password}}) "
					+ "RETURN user.userName AS userName",
					Values.parameters("userName", user.getUserName(), "password", user.getPassword()));
			if(result.next().get("userName").asString() != null) {
				return true;
			}
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return false;
	}

	public static int modifyPassword(Driver driver, User user) throws Exception{
		int res = 0;
		Transaction tx = driver.session().beginTransaction();
		try {
			tx.run("MATCH (u:User{userName:{userName}}) "
				 + "SET u.password = {password}", 
				 Values.parameters("userName", user.getUserName(), "password", user.getPassword()));
			res = 1;
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return res;
	}
}
