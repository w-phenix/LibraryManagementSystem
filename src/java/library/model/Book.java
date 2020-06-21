package library.model;

public class Book {
	private String bookName; // 书名
	private String bookDesc; // 图书描述
	private String author; // 作者
	private String bookType; // 图书类别
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(String bookName, String bookDesc, String author, String bookType) {
		super();
		this.bookName = bookName;
		this.bookDesc = bookDesc;
		this.author = author;
		this.bookType = bookType;
	}

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
}
