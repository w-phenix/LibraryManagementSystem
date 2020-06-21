package library.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.neo4j.driver.v1.Driver;

import library.dao.UserDao;
import library.model.User;
import library.util.DbUtil;
import library.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyPasswordFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt1;
	private JPasswordField passwordTxt2;

	private User currentUser = null;

	/**
	 * Create the frame.
	 */
	public ModifyPasswordFrm() {
		setTitle("修改用户密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setIcon(new ImageIcon(ModifyPasswordFrm.class.getResource("/images/userName.png")));
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		
		userNameTxt = new JTextField();
		userNameTxt.setEditable(false);
		userNameTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		userNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("新密码：");
		lblNewLabel_1.setIcon(new ImageIcon(ModifyPasswordFrm.class.getResource("/images/password.png")));
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		
		passwordTxt1 = new JPasswordField();
		passwordTxt1.setFont(new Font("黑体", Font.PLAIN, 14));
		passwordTxt1.setEchoChar('*');
		
		JLabel lblNewLabel_2 = new JLabel("重复密码：");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 14));
		
		passwordTxt2 = new JPasswordField();
		passwordTxt2.setEchoChar('*');
		passwordTxt2.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ModifyPasswordFrm.class.getResource("/images/modify.png")));
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ModifyPasswordFrm.class.getResource("/images/reset.png")));
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(userNameTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordTxt2)
								.addComponent(passwordTxt1, 115, 115, Short.MAX_VALUE))))
					.addGap(163))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(100, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(88)
					.addComponent(btnNewButton)
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordTxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		currentUser = LoginOn.getCurrentUser();
		this.userNameTxt.setText(currentUser.getUserName());
	}

	/**
	 * 确认修改密码事件处理
	 * @param e
	 */
	private void confirmActionPerformed(ActionEvent evt) {
		String password1 = new String(passwordTxt1.getPassword()); // 这里类型不一致不能用.toString()转
		String password2 = new String(passwordTxt2.getPassword());
		if(StringUtil.isEmpty(password1)) {
			JOptionPane.showMessageDialog(null, "请输入新密码！");
			return;
		}
		if(StringUtil.isEmpty(password2)) {
			JOptionPane.showMessageDialog(null, "请重复新密码！");
			return;
		}
		if(!password1.equals(password2)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一样，请重新输入！");
			return;
		}
		if(password1.equals(currentUser.getPassword())) {
			JOptionPane.showMessageDialog(null, "新密码不能和旧密码一样，请重新输入！");
			return;
		}
		User newUser = new User(currentUser.getUserName(), password1);
		Driver driver = null;
		try {
			driver = DbUtil.getDriver();
			int re = UserDao.modifyPassword(driver, newUser);
			if(re == 1) {
				JOptionPane.showMessageDialog(null, "密码修改成功，下次登录时生效！");
			}
			else {
				JOptionPane.showMessageDialog(null, "密码修改失败！");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "密码修改失败！");
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
	private void resetActionPerformed(ActionEvent e) {
		this.passwordTxt1.setText("");
		this.passwordTxt2.setText("");
	}
}
