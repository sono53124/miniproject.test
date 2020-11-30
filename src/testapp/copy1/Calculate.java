package testapp.copy1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

public class Calculate extends JPanel {

	JScrollPane scroll;
	JTable table;

	JPanel p_north, p_center, p_east, calcul, can, p_south, c_north, c_south, p_west;
	JLabel la_pay, la_m_name, la_m_regist, la_m_exp, la_h_pay, la_m_id, la_period1, la_period2, la_g_regist, la_g_dat,
			la_g_time, la_g_pay, la_total_pay;
	JTextField t_start, t_period1, t_period2, t_m_name, t_end, t_m_id, t_m_regist, t_m_exp, t_h_pay, t_period,
			t_g_regist, t_g_dat, t_g_time, t_g_pay, t_total_pay;
	JButton search;
	Image img;
	File file;
	MemberList meberList;
	Toolkit kit = Toolkit.getDefaultToolkit();
	MainPage mainPage;
	Connection con;

	private static final long serialVersionUID = 1L;

	public Calculate(MainPage mainPage) {
		this.mainPage = mainPage;
		con = mainPage.getCon();

		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1280, 700));
		p.setLayout(new BorderLayout());
		p_north = new JPanel();
		p_north.setLayout(new BorderLayout());
		p_north.setBackground(Color.LIGHT_GRAY);
		p_north.setPreferredSize(new Dimension(1200, 50));
		la_pay = new JLabel("회원 정보");
		la_pay.setHorizontalAlignment(JLabel.CENTER);// 라벨 가운데 정렬
		la_pay.setFont(new Font("SenSerif", Font.BOLD, 30));

		p_north.add(la_pay); // north영역생성

		p_west = new JPanel(); // 좌측공간에 붙일것
		p_west.setPreferredSize(new Dimension(400, 500));
//      p_west.setBackground(Color.LIGHT_GRAY);

		la_m_id = new JLabel("회원id");
		la_m_id.setFont(new Font("SenSerif", Font.BOLD, 20));
		la_m_id.setPreferredSize(new Dimension(100, 150));
		la_m_id.setHorizontalAlignment(JLabel.CENTER);
		t_m_id = new JTextField(15);
		t_m_id.setPreferredSize(new Dimension(30, 30));
		search = new JButton("검색");
		can = new JPanel() {
			public void paint(Graphics g) {
				// g.setColor(Color.RED);
				// g.fillRect(0, 0, 200, 100);
				g.drawImage(img, 0, 0, 240, 240, can);
			}
		};
		can.setPreferredSize(new Dimension(240, 240));
		can.setBackground(Color.black);
		p_west.add(la_m_id);
		p_west.add(t_m_id);
		p_west.add(search);
		p_west.add(can);

		// 가운데영역생성
		p_center = new JPanel();
//      p_center.setBackground(Color.);
		p_center.setPreferredSize(new Dimension(400, 500));

		// 가운데
		la_m_name = new JLabel("회원이름");
		la_m_name.setFont(new Font("돋움", Font.BOLD, 20));
		la_m_name.setPreferredSize(new Dimension(400, 35));
		la_m_name.setHorizontalAlignment(JLabel.CENTER);
		t_m_name = new JTextField(20);
		t_m_name.setPreferredSize(new Dimension(300, 30));

		la_m_regist = new JLabel("등록일");
		la_m_regist.setFont(new Font("돋움", Font.BOLD, 20));
		la_m_regist.setPreferredSize(new Dimension(400, 35));
		la_m_regist.setHorizontalAlignment(JLabel.CENTER);
		t_m_regist = new JTextField(20);
		t_m_regist.setPreferredSize(new Dimension(300, 30));

		la_m_exp = new JLabel("만기일");
		la_m_exp.setFont(new Font("돋움", Font.BOLD, 20));
		la_m_exp.setPreferredSize(new Dimension(400, 35));
		la_m_exp.setHorizontalAlignment(JLabel.CENTER);
		t_m_exp = new JTextField(20);
		t_m_exp.setPreferredSize(new Dimension(300, 30));
		// 가운데
		p_center.add(la_m_name);
		p_center.add(t_m_name);
		p_center.add(la_m_regist);
		p_center.add(t_m_regist);
		p_center.add(la_m_exp);
		p_center.add(t_m_exp);
		p_center.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 4), "<헬스이용>"));
		// 오른쪽
		p_east = new JPanel();
		// p_east.setBackground(Color.GREEN);
		p_east.setPreferredSize(new Dimension(400, 500));

		la_g_regist = new JLabel("GX이름");
		la_g_regist.setFont(new Font("돋움", Font.BOLD, 20));
		la_g_regist.setPreferredSize(new Dimension(400, 45));
		la_g_regist.setHorizontalAlignment(JLabel.CENTER);

		t_g_regist = new JTextField(30);
		t_g_regist.setPreferredSize(new Dimension(300, 30));

		la_g_dat = new JLabel("요일");
		la_g_dat.setFont(new Font("돋움", Font.BOLD, 20));
		la_g_dat.setPreferredSize(new Dimension(400, 45));
		la_g_dat.setHorizontalAlignment(JLabel.CENTER);
		t_g_dat = new JTextField(30);
		t_g_dat.setPreferredSize(new Dimension(300, 30));

		la_g_time = new JLabel("시간");
		la_g_time.setFont(new Font("돋움", Font.BOLD, 20));
		la_g_time.setPreferredSize(new Dimension(400, 45));
		la_g_time.setHorizontalAlignment(JLabel.CENTER);
		t_g_time = new JTextField(30);
		t_g_time.setPreferredSize(new Dimension(300, 30));

		la_h_pay = new JLabel("헬스이용금액");
		la_h_pay.setFont(new Font("돋움", Font.BOLD, 20));
		la_h_pay.setPreferredSize(new Dimension(400, 45));
		la_h_pay.setHorizontalAlignment(JLabel.CENTER);
		t_h_pay = new JTextField(30);
		t_h_pay.setPreferredSize(new Dimension(300, 30));

		la_g_pay = new JLabel("GX이용금액");
		la_g_pay.setFont(new Font("돋움", Font.BOLD, 20));
		la_h_pay.setPreferredSize(new Dimension(400, 45));
		la_h_pay.setHorizontalAlignment(JLabel.CENTER);
		t_g_pay = new JTextField(30);
		t_g_pay.setPreferredSize(new Dimension(300, 30));

		la_total_pay = new JLabel("총 이용금액");
		la_total_pay.setFont(new Font("돋움", Font.BOLD, 20));
		la_total_pay.setPreferredSize(new Dimension(400, 45));
		la_total_pay.setHorizontalAlignment(JLabel.CENTER);
		t_total_pay = new JTextField(30);
		t_total_pay.setPreferredSize(new Dimension(300, 30));

		p_east.add(la_g_regist);
		p_east.add(t_g_regist);
		p_east.add(la_g_dat);
		p_east.add(t_g_dat);
		p_east.add(la_g_time);
		p_east.add(t_g_time);
		p_east.add(la_h_pay);
		p_east.add(t_h_pay);
		p_east.add(la_g_pay);
		p_east.add(t_g_pay);
		p_east.add(la_total_pay);
		p_east.add(t_total_pay);
		p_east.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 4), "<GX이용>"));// 레이아웃주기

		// add(p_east);
		// 최하단
		p_south = new JPanel();
		p_south.setPreferredSize(new Dimension(1280, 80));
		p_south.setBackground(Color.LIGHT_GRAY);

		la_period1 = new JLabel("헬스이용 기간이  ");
		la_period1.setFont(new Font("돋움", Font.BOLD, 50));
		la_period1.setForeground(Color.RED);
		t_period = new JTextField(10);
		t_period.setPreferredSize(new Dimension(100, 50));
		t_period.setFont(new Font("돋움", Font.BOLD, 30));
		la_period2 = new JLabel("  일 남았습니다.");
		la_period2.setForeground(Color.RED);
		la_period2.setFont(new Font("돋움", Font.BOLD, 50));

		p_south.add(la_period1);
		p_south.add(t_period);
		p_south.add(la_period2);

		p.add(p_north, BorderLayout.NORTH);
		p.add(p_west, BorderLayout.WEST);
		p.add(p_center, BorderLayout.CENTER);
		p.add(p_east, BorderLayout.EAST);
		p.add(p_south, BorderLayout.SOUTH);
		add(p, BorderLayout.CENTER);

		// 여기까지가 디자인

		search.addActionListener((e) -> {
			// exitText();
			// findImage();
			HealthData();
			GXdata();
			int a = Integer.parseInt(t_g_pay.getText());
			int b = Integer.parseInt(t_h_pay.getText());
			int c = a + b;
			t_total_pay.setText(Integer.toString(c));
			Date();
		});
	}

	public void HealthData() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String a = "m_name";
		String b = "m_regdate";
		String c = "m_expdate";
		String d = "m_pay";

		int memberID = Integer.parseInt(t_m_id.getText());

		String sql = "select * from gym_member where m_id=" + memberID; // m_id가 ?인 회원의 이름
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				t_m_name.setText(rs.getString(a));
				t_m_regist.setText(rs.getString(b));
				t_m_exp.setText(rs.getString(c));
				t_h_pay.setText(rs.getString(d));

				String pic = rs.getString("m_file");
				System.out.println("이미지 url정보" + pic);
				img = kit.getImage(pic);
				img = img.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
			}
			can.repaint();
			can.updateUI();
			p_west.updateUI();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "실행중문제가 발생했습니다");
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
	}

	public void GXdata() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int memberID = Integer.parseInt(t_m_id.getText());
		String a = "pg_name";
		String b = "pg_day";
		String c = "pg_time";
		String d = "pg_price";

		String sql = "select * from gym_program where m_id=" + memberID;

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t_g_regist.setText(rs.getString(a));
				t_g_dat.setText(rs.getString(b));
				t_g_time.setText(rs.getString(c));
				t_g_pay.setText(rs.getString(d));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "실행중문제가 발생했습니다");
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
	}

	public void exitText() {
		t_m_name.setText("");
		t_m_regist.setText("");
		t_m_exp.setText("");
		t_g_regist.setText("");
		t_g_dat.setText("");
		t_g_time.setText("");
		t_h_pay.setText("");
		t_g_pay.setText("");
		t_total_pay.setText("");
	}

	public void Date() {
		String pattern = "yyyy-MM-dd";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat(pattern);
		String date = simpledate.format(cal.getTime());// 현재날짜를 스트링형으로 가져와서
		LocalDate d_day = LocalDate.parse(date);// 날짜형식으로 바꿈
		String ex_date = t_m_exp.getText();
		LocalDate h_d_day = LocalDate.parse(ex_date);// 만기일에서 얻은값을 날짜형식으로 바꿈
		long total = (ChronoUnit.DAYS.between(d_day, h_d_day));// 만기일 - 현재 날짜 일 수 계산
		String getDAY = Long.toString(total);
		t_period.setText(getDAY);
		System.out.println(getDAY);
	}

}
//이미지 얻어오기
//   public void getTargetImage(String getURL) {
//      img = kit.getImage(getURL);
//      System.out.println("현재찍히는url" + getURL);
//      img = img.getScaledInstance(260, 280, Image.SCALE_SMOOTH);
//      if (img != null) {
//         can.repaint();
//      }
//   }
//}