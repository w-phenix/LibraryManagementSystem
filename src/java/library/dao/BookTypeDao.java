package library.dao;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

public class BookTypeDao {
	public static StatementResult list(Driver driver) throws Exception{
		StatementResult results = null;
		Transaction tx = driver.session().beginTransaction();
		try {
			results = tx.run("MATCH (bt:BookType) RETURN bt.bookTypeName AS bookTypeName");
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return results;
	}
}
