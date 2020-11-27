package testapp;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Action;
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

public class Regist extends JFrame {
	String[] value = { "아이디", "이름", "휴대폰번호", "이메일" };
	JLabel[] la = new JLabel[value.length];
	JTextField[] text = new JTextField[value.length];
	JLabel la_pass;
	JPasswordField pass;

	JPanel panel;
	JCheckBox all, years, service, privacy;
	JTextArea area_service;
	JScrollPane scroll_service;
	JTextArea area_Privacy;
	JScrollPane scroll_Privacy;
	JButton bt_regist;
	JButton bt_login;

	boolean flag;
	DBConnection dbConnection;

	public Regist() {
		dbConnection = new DBConnection();
		panel = new JPanel();

		for (int i = 0; i < text.length; i++) {
			text[i] = new JTextField();
			la[i] = new JLabel(value[i]);
			text[i].setPreferredSize(new Dimension(300, 40));
			la[i].setPreferredSize(new Dimension(85, 40));
			int index = i;
			text[i].addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					text[index].setText("");
				}
			});
		}
		la_pass = new JLabel("비밀번호");
		pass = new JPasswordField();

		all = new JCheckBox("전체 동의");
		years = new JCheckBox("만 14세 이상 여부 체크 (필수)");
		service = new JCheckBox("서비스 약관 안내 (필수)");
		privacy = new JCheckBox("개인정보 수집 및 이용에 대한 안내 (필수)");
		area_service = new JTextArea(
				"1.총칙\r\n" + "제1조 (목적)\r\n" + "이 약관은 회원이 브로제이(이하 “회사”라 합니다)에서 제공하는 유무선 인터넷 헬스케어서비스(웹, 모바일 웹•앱 " + "\r\n"
						+ "제2조 (약관의 효력 및 변경)\r\n" + "① 이 약관은 대한민국 내에서 서비스를 이용하고자 하는 모든 회원에 대하여 그 효력을 발생합니다. "
						+ "② 이 약관의 내용은 서비스 화면에 게시하거나 기타의 방법으로 회원에게 공시하고, 이에 동의한 회원이 서비스"
						+ "③ 회사는 필요하다고 인정하는 경우, “약관의규제에관한법률”, “정보통신망이용"
						+ " 회원에게 불리한 약관의 변경의 경우 개정 내용을 회원이 알기 쉽게 표시하여 그 적용일자 30일 전부터 공지하며, 이메일 주소, \r\n");
		scroll_service = new JScrollPane(area_service);
		area_Privacy = new JTextArea("서비스 이용자의 개인정보를 이용하는 목적은 다음과 같습니다.\r\n" + "수집이용 목적	필수 수집항목	선택 수집항목	보존기간\r\n"
				+ "서비스 회원가입	아이디, 이름, 이메일, 생년월일, 성별, 휴대폰번호, 비밀번호	-	회원탈퇴시\r\n"
				+ "지점 및 계정관리	트레이너 경력/이력 관리	경력정보(입사일자, 직무, 회사명)), 학교정보(입학일자, 졸	퇴사일자\r\n"
				+ "재화 또는  서비스의 제공	신규 트레이너 추가	아이디, 비밀번호, 이름, 생년월일, 성별, 휴대폰번호, 이메일, 주소	-\r\n" + "트레이너 초대하기	아이디	-\r\n"
				+ "상품결제	신용카드	카드사, 카드번호(일부)	-\r\n" + "무통장입금	은행, 계좌번호, 예금주	-\r\n"
				+ "서비스 품질향상 및 상품 개발	이름, 아이디, 서비스 이용기록	-\r\n" + "이용자 민원 및 고충 처리	센터명, 이메일, 핸드폰번호, 문의내용	-\r\n"
				+ "- 동의를 거부할 권리가 있으며, 동의 거부 시 회원가입을 통한 기본서");
		scroll_Privacy = new JScrollPane(area_Privacy);
		bt_regist = new JButton("회원가입");
		bt_login = new JButton("로그인");

		pass.setPreferredSize(new Dimension(300, 40));
		la_pass.setPreferredSize(new Dimension(85, 40));

		all.setPreferredSize(new Dimension(380, 20));
		service.setPreferredSize(new Dimension(380, 20));
		years.setPreferredSize(new Dimension(380, 20));
		privacy.setPreferredSize(new Dimension(380, 20));

		scroll_service.setPreferredSize(new Dimension(380, 80));
		scroll_Privacy.setPreferredSize(new Dimension(380, 80));

		panel.add(la[0]);
		panel.add(text[0]);
		panel.add(la[1]);
		panel.add(text[1]);
		panel.add(la_pass);
		panel.add(pass);
		panel.add(la[2]);
		panel.add(text[2]);
		panel.add(la[3]);
		panel.add(text[3]);

		panel.add(all);
		panel.add(years);
		panel.add(scroll_service);
		panel.add(service);
		panel.add(scroll_Privacy);
		panel.add(privacy);
		panel.add(bt_regist);
		panel.add(bt_login);

		add(panel);

		all.addActionListener((e) -> {
			if (!flag) {
				service.setSelected(true);
				years.setSelected(true);
				privacy.setSelected(true);
				flag = true;
			} else {
				service.setSelected(false);
				years.setSelected(false);
				privacy.setSelected(false);
				flag = false;
			}
		});

		bt_regist.addActionListener((e) -> {
			boolean flag;
			flag = check();
			if (flag) {
				join();
				setVisible(false);
			}
		});
		bt_login.addActionListener((e) -> {
			setVisible(false);
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		setVisible(true);
		setSize(450, 580);
		setLocationRelativeTo(null);
	}

	public boolean check() {
		boolean flag = false;
		if (text[0].getText().length() < 1) {
			JOptionPane.showMessageDialog(this, value[0] + "를 입력해주세요");
			return flag = false;
		} else if (text[1].getText().length() < 1) {
			JOptionPane.showMessageDialog(this, value[1] + "을 입력해주세요");
			return flag = false;
		} else if (text[2].getText().length() < 1) {
			JOptionPane.showMessageDialog(this, value[2] + "비밀번호를 입력해주세요");
			return flag = false;
		} else if (text[3].getText().length() < 1) {
			JOptionPane.showMessageDialog(this, value[3] + "을 입력해주세요");
			return flag = false;
		} else if (pass.getPassword().toString().length() < 1) {
			JOptionPane.showMessageDialog(this, value[4] + "를 입력해주세요");
			return flag = false;
		} else if (years.getSelectedObjects() == null || service.getSelectedObjects() == null
				|| privacy.getSelectedObjects() == null) {
			System.out.println(years.getSelectedObjects());
			JOptionPane.showMessageDialog(this, "약관동의를 확인해주세요");
			return flag = false;
		}
		return flag = true;
	}

	public void join() {
		PreparedStatement pstmt = null;

		String sql = "insert into gym_login(id ";
		sql += ",login_id,login_name,login_pass,login_phone,login_mail)";
		sql += " values(seq_gym_login.nextval,?,?,?,?,?)";

		try {
			pstmt = dbConnection.connect().prepareStatement(sql);
			pstmt.setString(1, text[0].getText());
			pstmt.setString(2, text[1].getText());
			pstmt.setString(3, new String(pass.getPassword()));
			pstmt.setString(4, text[2].getText());
			pstmt.setString(5, text[3].getText());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				JOptionPane.showMessageDialog(this, "회원가입 완료");
			} else {
				JOptionPane.showMessageDialog(this, "회원가입 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.close(pstmt);
		}

	}

}
