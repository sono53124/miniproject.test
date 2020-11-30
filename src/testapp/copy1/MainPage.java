package testapp.copy1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPage extends JFrame {
	private DBConnection dbConnection;
	private Connection con;

	// 상수
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 800;

	public static final int MEMBERREGIST = 0;
	public static final int TRAINER = 1;
	public static final int PROGRAM = 2;
	public static final int CALCULATE = 3;

	JPanel[] pages = new JPanel[4];

	JPanel main_north;// 큰 패널 북쪽
	JPanel main_center;// 큰 패널
	JButton bt_member, bt_trainer, bt_program, bt_regist, bt_logout, bt_calculate;

	private boolean loginStatus = false;

	public MainPage() {
		dbConnection = new DBConnection();
		con = dbConnection.connect();
		if (con == null) {
			JOptionPane.showMessageDialog(this, "데이터베이스에 접속할 수 없습니다");
			System.exit(0);
		} else {
			this.setTitle("Fitness management System");
		}

		main_north = new JPanel();
		main_center = new JPanel();
		bt_member = new JButton("회원 등록");
		bt_trainer = new JButton("트레이너");
		bt_program = new JButton("프로그램");
		bt_calculate = new JButton("회원정보");
		bt_logout = new JButton("로그아웃");

		// 페이지 생성
		pages[0] = new Member(this); // 처음보여지는 화면은 멤버페이지
		pages[1] = new Trainer(this);
		pages[2] = new Program(this);
		pages[3] = new Calculate(this);

		main_north.add(bt_member);
		main_north.add(bt_trainer);
		main_north.add(bt_program);
		main_north.add(bt_calculate);
		main_north.add(bt_logout);
		main_north.setPreferredSize(new Dimension(1200, 40));
		main_north.setBackground(Color.BLACK);
		main_center.setPreferredSize(new Dimension(WIDTH, HEIGHT - 30));

		main_center.add(pages[0]);
		main_center.add(pages[1]);
		main_center.add(pages[2]);
		main_center.add(pages[3]);
		add(main_north, BorderLayout.NORTH);
		add(main_center);

		setPage(MainPage.MEMBERREGIST);// 첫번째로 보여질 페이지

		// 회원등록버튼과 연결
		bt_member.addActionListener((e) -> {
			setPage(MEMBERREGIST);
		});

		// 트레이너 버튼과 연결
		bt_trainer.addActionListener((e) -> {

			setPage(TRAINER);

		});

		// 프로그램이랑 연결
		bt_program.addActionListener((e) -> {
			setPage(PROGRAM);
		});

		// 계산페이지랑 연결
		bt_calculate.addActionListener((e) -> {
			setPage(CALCULATE);
		});

		// 로그아웃이랑 연결
		bt_logout.addActionListener((e) -> {
			if (JOptionPane.showConfirmDialog(this, "로그아웃하시겠습니까?") == JOptionPane.OK_OPTION) {
				loginStatus = true;
				JOptionPane.showMessageDialog(this, "로그아웃되었습니다");
				setVisible(false);
				new Login();
			}
		});

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);

		// 프레임과 리스너 연결
		MainPage.this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbConnection.disconnect(con);
				System.exit(0);
			}
		});

	}

	public Connection getCon() {
		return con;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	// 화면 전환
	public void setPage(int index) {
		for (int i = 0; i < pages.length; i++) {
			if (index == i) {
				pages[i].setVisible(true);// 보여질 페이지
			} else {
				pages[i].setVisible(false);// 가려질 페이지
			}
		}
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

}