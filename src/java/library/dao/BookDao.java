package library.dao;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import library.model.Book;
import library.util.StringUtil;

public class BookDao {
	/**
	 * 查询图书，返回结果集
	 * @param driver
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static StatementResult list(Driver driver, Book book) throws Exception{
		StatementResult results = null;
		StringBuffer sb = new StringBuffer("MATCH (book:Book)");
		Transaction tx = driver.session().beginTransaction();
		if(StringUtil.isNotEmpty(book.getBookName())) {
			String bookName = "\"" + book.getBookName() + "\"";
			sb.append(" WHERE book.bookName CONTAINS " + bookName);
		}
		sb.append(" MATCH (auth:Author)");
		if(StringUtil.isNotEmpty(book.getAuthor())) {
			String author = "\"" + book.getAuthor() + "\"";
			sb.append(" WHERE auth.author CONTAINS " + author);
		}
		sb.append(" MATCH (bt:BookType)");
		if(StringUtil.isNotEmpty(book.getBookType()) && !"请选择...".equals(book.getBookType())) {
			String bookTypeName = "\"" + book.getBookType() + "\"";
			sb.append(" WHERE bt.bookTypeName CONTAINS " + bookTypeName);
		}
		sb.append(" MATCH (auth)-[:WRITE]->(book)-[BELONG_TO]->(bt)");
		sb.append(" RETURN book.bookName AS bookName, book.bookDesc AS bookDesc, auth.author AS author, bt.bookTypeName AS bookType");
		try {
			results = tx.run(sb.toString());
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return results;
	}

	/**
	 * 删除图书及与之关联的关系，删除后如果作者节点和图书类别节点没有任何关系了，一并删除
	 * @param driver
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int delete(Driver driver, Book book) throws Exception{
		int res = 0;
		Transaction tx = driver.session().beginTransaction();
		try {
			tx.run("MATCH (b:Book{bookName:{bookName}}) DETACH DELETE b", Values.parameters("bookName", book.getBookName()));
			tx.run("MATCH (a:Author) WHERE NOT (a)--() DELETE a");
			tx.run("MATCH (bt:BookType) WHERE NOT (bt)--() DELETE bt");
			res = 1;
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return res;
	}

	/**
	 * 修改图书信息，如果要修改的信息是作者和图书类别，则先删除旧关系，再建立新关系
	 * @param driver
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int modify(Driver driver, Book book) throws Exception{
		int res = 0;
		Transaction tx = driver.session().beginTransaction();
		try {
			tx.run("MATCH (b:Book{bookName:{bookName}}), (b)-[r1:BELONG_TO]->(), (b)<-[r2:WRITE]-() "
				 + "DELETE r1, r2 "
				 + "MERGE (a:Author{author:{author}}) "
				 + "MERGE (bt:BookType{bookTypeName:{bookTypeName}}) "
				 + "SET b.bookDesc = {bookDesc} "
				 + "MERGE (b)-[:BELONG_TO]->(bt) "
				 + "MERGE (a)-[:WRITE]->(b)", 
				 Values.parameters("bookName", book.getBookName(), "author", book.getAuthor(), "bookTypeName", book.getBookType(), "bookDesc", book.getBookDesc()));
			res = 1;
			tx.success();
		}catch (Exception e) {
			tx.failure();
		}finally {
			tx.close();
		}
		return res;
	}

	/**
	 * 添加图书
	 * @param driver
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int add(Driver driver, Book book) throws Exception{
		int res = 0;
		Transaction tx = driver.session().beginTransaction();
		try {
			tx.run("MERGE (b:Book{bookName:{bookName}, bookDesc:{bookDesc}}) "
				 + "MERGE (a:Author{author:{author}}) "
				 + "MERGE (bt:BookType{bookTypeName:{bookTypeName}}) "
				 + "MERGE (b)-[:BELONG_TO]->(bt) "
				 + "MERGE (b)<-[:WRITE]-(a)", 
				 Values.parameters("bookName", book.getBookName(), "bookDesc", book.getBookDesc(), "author", book.getAuthor(), "bookTypeName", book.getBookType()));
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
