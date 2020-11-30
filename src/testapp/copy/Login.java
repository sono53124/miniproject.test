package testapp.copy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login extends JFrame{
	JPanel panel_top;
	JPanel panel_center;
	JPanel panel;
	JPanel panel2;
	JLabel la_id;
	JLabel la_pass;
	JTextField t_id;
	JPasswordField pass;
	JButton bt_login;
	JButton bt_regist;
	DBConnection connection;
	File file;
	Image img;
	
	public Login() {
		
		connection = new DBConnection();
		panel_top=new JPanel() {
			public void paint(Graphics g) {
				try {
					file=new File("C:/Workspace/java_workspace/Seproject/res/image/bg.jpg");
					img=ImageIO.read(file);
					img=img.getScaledInstance(270, 160, Image.SCALE_SMOOTH);
					g.drawImage(img, 0, 0, this);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		panel_center= new JPanel();
		panel = new JPanel();
		panel2 = new JPanel();
		la_id = new JLabel("id");
		la_pass = new JLabel("pass");
		t_id = new JTextField(12);
		pass = new JPasswordField(12);
		bt_login = new JButton("로그인");
		bt_regist = new JButton("회원가입");
		
		panel_top.setPreferredSize(new Dimension(200,150));
		panel.setPreferredSize(new Dimension(200,60));
		la_id.setPreferredSize(new Dimension(52,20));
		la_pass.setPreferredSize(new Dimension(52,20));
		
		panel.add(la_id);
		panel.add(t_id);
		panel.add(la_pass);
		panel.add(pass);
		panel2.add(bt_regist);
		panel2.add(bt_login);
		
		panel_center.add(panel);
		panel_center.add(panel2,BorderLayout.SOUTH);	
		
		add(panel_top,BorderLayout.NORTH);
		add(panel_center);
		
		bt_login.addActionListener((e)->{
			boolean flag;
			flag=login();
			if(flag) {
				JOptionPane.showMessageDialog(this, "로그인 성공");
				new MainPage();
				setVisible(false);	
			}
			System.out.println(new String(pass.getPassword()));
		});
		bt_regist.addActionListener((e)->{
			new Regist();
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		setVisible(true);
		setSize(287,320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public boolean login() {
		boolean flag=false;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql="select * from gym_login where login_id=? and login_pass=?";
		
		try {
			pstmt=connection.connect().prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(pass.getPassword()));
			rs = pstmt.executeQuery();
			System.out.println(sql);
			if(rs.next()) {
				return flag=true;
			}else if(rs!=null){
				JOptionPane.showMessageDialog(this, "아이디,패드워드를 확인해주세요");
				return flag=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close(pstmt, rs);
		}
		return flag=false;
	}
	
	public static void main(String[] args) {
		new Login();
	}
	
}