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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookAddFrm extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextArea bookDescTxt;
	private JTextField bookTypeTxt;

	/**
	 * Create the frame.
	 */
	public BookAddFrm() {
		setTitle("添加图书");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("图书名称：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("图书作者：");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("图书类别：");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("图书描述：");
		lblNewLabel_3.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		bookDescTxt.setLineWrap(true);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookAddFrm.class.getResource("/images/add.png")));
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookAddFrm.class.getResource("/images/reset.png")));
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 14));
		
		bookTypeTxt = new JTextField();
		bookTypeTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		bookTypeTxt.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(authorTxt))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(177, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(35)
					.addComponent(btnNewButton)
					.addGap(50))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * 添加图书事件处理
	 * @param e
	 */
	private void addActionPerformed(ActionEvent evt) {
		String bookName = bookNameTxt.getText();
		String author = authorTxt.getText();
		String bookType = bookTypeTxt.getText();
		String bookDesc = bookDescTxt.getText();
		Book book = new Book(bookName, bookDesc, author, bookType);
		Driver driver = null;
		try {
			driver = DbUtil.getDriver();
			int re = BookDao.add(driver, book);
			if(re == 1) {
				JOptionPane.showMessageDialog(null, "图书添加成功！");
				this.resetValue();
			}
			else if(re == 0) {
				JOptionPane.showMessageDialog(null, "图书添加失败！");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "图书添加失败！");
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
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}

	/**
	 * 重置
	 */
	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.bookDescTxt.setText("");
		this.bookTypeTxt.setText("");
	}
}
