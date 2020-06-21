package library.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import library.dao.BookDao;
import library.dao.BookTypeDao;
import library.model.Book;
import library.util.DbUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookModifyFrm extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JComboBox bookTypeJcb;
	private JTextArea bookDescTxt;

	private Book modifyBook = new Book();
	
	/**
	 * Create the frame.
	 */
	public BookModifyFrm(Book book) {
		setTitle("图书信息修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("图书名称：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setEditable(false);
		bookNameTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("图书作者：");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("图书类别：");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("图书描述：");
		lblNewLabel_3.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setLineWrap(true);
		bookDescTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		authorTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(BookModifyFrm.class.getResource("/images/modify.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton_1.setIcon(new ImageIcon(BookModifyFrm.class.getResource("/images/reset.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton_1)
								.addGap(18)
								.addComponent(btnNewButton))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))))
					.addGap(81))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.resetValue(book);
		modifyBook = book;
	}

	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue(modifyBook);
	}

	private void modifyActionPerformed(ActionEvent evt) {
		String author = authorTxt.getText();
		String bookType = (String) bookTypeJcb.getSelectedItem();
		String bookDesc = bookDescTxt.getText();
		Book newBook = new Book(modifyBook.getBookName(), bookDesc, author, bookType);
		Driver driver = null;
		try {
			driver = DbUtil.getDriver();
			int re = BookDao.modify(driver, newBook);
			if(re == 1) {
				JOptionPane.showMessageDialog(null, "图书信息修改成功！");
			}
			else if(re == 0){
				JOptionPane.showMessageDialog(null, "图书信息修改失败！");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.closeDriver(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void fillBookTypeJcb() {
		Driver driver = null;
		try {
			driver = DbUtil.getDriver();
			StatementResult res = BookTypeDao.list(driver);
			while(res.hasNext()) {
				Record record = res.next();
				bookTypeJcb.addItem(new String(record.get("bookTypeName").asString()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.closeDriver(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置图书信息
	 * @param book
	 */
	public void resetValue(Book book) {
		this.bookNameTxt.setText(book.getBookName());
		this.authorTxt.setText(book.getAuthor());
		
		this.fillBookTypeJcb();
		int cnt = bookTypeJcb.getItemCount();
		for(int i = 0;i < cnt;++i) {
			if(book.getBookType().equals((String)bookTypeJcb.getItemAt(i))){
				this.bookTypeJcb.setSelectedIndex(i);
			}
		}
		this.bookDescTxt.setText(book.getBookDesc());
	}

}
