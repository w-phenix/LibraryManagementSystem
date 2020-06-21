package library.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import library.dao.BookDao;
import library.dao.BookTypeDao;
import library.model.Book;
import library.util.DbUtil;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTable bookTable;
	
	private JComboBox bookTypeJcb;
	private BookModifyFrm bookModifyFrm = null;
	private ModifyPasswordFrm modifyPasswordFrm = null;
	private BookAddFrm bookAddFrm = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrm frame = new MainFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFrm() {
		setTitle("图书管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 555);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("用户管理");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyPasswordActionPerformed(e);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/modify.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出系统");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/exit.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("图书管理");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("添加图书");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddActionPerformed(e);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_2 = new JButton("删除记录");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton_3 = new JButton("修改记录");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		btnNewButton_3.setFont(new Font("黑体", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_3)
							.addGap(18)
							.addComponent(btnNewButton_2)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		bookTable = new JTable();
		bookTable.setFont(new Font("黑体", Font.PLAIN, 15));
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(51);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(142);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(213);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(111);
		scrollPane.setViewportView(bookTable);
		
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
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("查询");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		this.setLocationRelativeTo(null);
		this.fillBookTable(new Book());
		this.fillBookTypeJcb();
	}
	
	/**
	 * 添加图书
	 * @param e
	 */
	protected void BookAddActionPerformed(ActionEvent e) {
		bookAddFrm = new BookAddFrm();
		bookAddFrm.setVisible(true);
	}

	private void modifyPasswordActionPerformed(ActionEvent e) {
		modifyPasswordFrm = new ModifyPasswordFrm();
		modifyPasswordFrm.setVisible(true);
	}

	/**
	 * 退出系统事件处理
	 */
	private void exitActionPerformed(ActionEvent e) {
		this.dispose();
		if(bookModifyFrm != null) {
			bookModifyFrm.dispose();
		}
		if(modifyPasswordFrm != null) {
			modifyPasswordFrm.dispose();
		}
		if(bookAddFrm != null) {
			bookAddFrm.dispose();
		}
	}

	/**
	 * 修改图书记录
	 * @param evt
	 */
	private void modifyActionPerformed(ActionEvent evt) {
		int row = bookTable.getSelectedRow();
		if(row < 0) {
			JOptionPane.showMessageDialog(null, "请选择要修改的图书记录！");
			return;
		}
		String bookName = (String) bookTable.getValueAt(row, 1);
		String author = (String) bookTable.getValueAt(row, 2);
		String bookDesc = (String) bookTable.getValueAt(row, 3);
		String bookType = (String) bookTable.getValueAt(row, 4);
		Book book = new Book(bookName, bookDesc, author, bookType);
		bookModifyFrm = new BookModifyFrm(book);
		bookModifyFrm.setVisible(true);
	}

	/**
	 * 删除图书记录
	 * @param evt
	 */
	private void deleteActionPerformed(ActionEvent evt) {
		int row = bookTable.getSelectedRow();
		if(row < 0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的图书记录！");
			return;
		}
		int deleteNum = JOptionPane.showConfirmDialog(null, "确定删除该条图书记录吗？");
		if(deleteNum == 0) {
			String bookName = (String) bookTable.getValueAt(row, 1);
			String author = (String) bookTable.getValueAt(row, 2);
			String bookType = (String) bookTable.getValueAt(row, 4);
			Book book = new Book(bookName, "", author, bookType);
			Driver driver = null;
			try {
				driver = DbUtil.getDriver();
				int re = BookDao.delete(driver, book);
				if(re == 1) {
					JOptionPane.showMessageDialog(null, "图书删除成功！");
					this.fillBookTable(new Book());
				}
				else if(re == 0) {
					JOptionPane.showMessageDialog(null, "图书删除失败！");
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
	}

	/**
	 * 刷新事件处理
	 * @param e
	 */
	private void refreshActionPerformed(ActionEvent e) {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.bookTypeJcb.setSelectedIndex(0);
		this.fillBookTable(new Book());
	}

	/**
	 * 搜索事件处理
	 * @param evt
	 */
	private void searchActionPerformed(ActionEvent evt) {
		String bookName = bookNameTxt.getText();
		String author = authorTxt.getText();
		String bookType = (String)bookTypeJcb.getSelectedItem();
		Book book = new Book(bookName, "", author, bookType);
		this.fillBookTable(book);
	}

	/**
	 * 初始化表格数据
	 * @param book 查询条件
	 */
	public void fillBookTable(Book book) {
		int i = 0;
		Driver driver = null;
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0); // 清空表格
		try {
			driver = DbUtil.getDriver();
			StatementResult res = BookDao.list(driver, book);
			while(res.hasNext()) {
				Vector v = new Vector();
				Record record = res.next();
				v.add(++i);
				v.add(record.get("bookName").asString());
				v.add(record.get("author").asString());
				v.add(record.get("bookDesc").asString());
				v.add(record.get("bookType").asString());
				dtm.addRow(v); // 添加行
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.closeDriver(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化下拉框
	 */
	public void fillBookTypeJcb() {
		Driver driver = null;
		try {
			driver = DbUtil.getDriver();
			StatementResult res = BookTypeDao.list(driver);
			bookTypeJcb.addItem(new String("请选择..."));
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
}
