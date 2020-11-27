package testapp;

//내가만든코드 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

//import com.mysql.fabric.xmlrpc.base.Data;

//import common.image.ImageUtil;

public class Member extends JPanel {
	JPanel input, p_west, p_center, c_center, c_north, can, bt_north;
	JTextField t_name, t_age, t_phone, t_mail, t_addr, n_t_name, t_locker;
	JLabel la_name, la_age, la_phone, la_mail, la_addr, n_la_name, la_locker;
	JButton img_regist, regist, edit, del, search, list, bt_mail;
	JRadioButton man, woman;

	// JRadioButton man, woman;
	ButtonGroup group;
	JRadioButton[] gender = new JRadioButton[2];
	JScrollPane scroll;
	JTable table;
	
	String m_id = null;
	JLabel la_start, la_end;
	JTextField t_start, t_end;

	Image img;
	File file;
	JFileChooser chooser = new JFileChooser("D:/workspace/java/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();

	String days[] = { "1일", "1주일", "30일", "60일", "90일", "365일" };
	JComboBox<String> combo_endDay;
	MainPage mainPage;

	MemberList memberList;
	MemberVO memberVO;

	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl m_start;
	
	String mail;
	public Member(MainPage mainPage) {
		this.mainPage = mainPage;

		// datePicker 생성
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		m_start = new JDatePickerImpl(datePanel);

		// 서쪽영역 생성
		p_west = new JPanel();
		la_name = new JLabel("이름");
		t_name = new JTextField(13);
		
		// 젠더셀렉
		group = new ButtonGroup();
		gender[0] = new JRadioButton("man", true);
		gender[1] = new JRadioButton("woman", true);

		la_age = new JLabel("  생년월일");
		t_age = new JTextField();
		la_phone = new JLabel("전화번호");
		t_phone = new JTextField(22);
		la_mail = new JLabel("메일");
		t_mail = new JTextField(22);
		la_addr = new JLabel("주소");
		t_addr = new JTextField(22);
//      la_key = new JLabel("회원번호");
//      t_key = new JTextField(10);
		la_locker = new JLabel(" 락카키");
		t_locker = new JTextField();
		can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		img_regist = new JButton("이미지 파일 등록");
		la_start = new JLabel("시작일");
		la_end = new JLabel("만기일");
		combo_endDay = new JComboBox<String>(days);
		regist = new JButton("등록");
		edit = new JButton("수정");
		del = new JButton("삭제");
		bt_mail = new JButton("메일 전송");
		
		// 서쪽 스타일적용
		p_west.setPreferredSize(new Dimension(330, 700));
		la_name.setPreferredSize(new Dimension(30, 20));
		t_name.setPreferredSize(new Dimension(45, 30));
		la_age.setPreferredSize(new Dimension(60, 40));
		t_age.setPreferredSize(new Dimension(100, 30));
		la_phone.setPreferredSize(new Dimension(53, 40));
		t_phone.setPreferredSize(new Dimension(50, 30));
		la_mail.setPreferredSize(new Dimension(53, 40));
		t_mail.setPreferredSize(new Dimension(50, 30));
		la_addr.setPreferredSize(new Dimension(53, 60));
		t_addr.setPreferredSize(new Dimension(60, 30));
		la_start.setPreferredSize(new Dimension(39, 50));
		m_start.setPreferredSize(new Dimension(110, 26));
		combo_endDay.setPreferredSize(new Dimension(80, 30));

		la_locker.setPreferredSize(new Dimension(53, 40));
		t_locker.setPreferredSize(new Dimension(70, 30));

		can.setPreferredSize(new Dimension(240, 240));

		img_regist.setPreferredSize(new Dimension(280, 30));
		bt_mail.setPreferredSize(new Dimension(280, 30));
		regist.setPreferredSize(new Dimension(90, 30));
		edit.setPreferredSize(new Dimension(90, 30));
		del.setPreferredSize(new Dimension(90, 30));
		p_west.setBackground(Color.lightGray);
		p_center = new JPanel();
		p_west.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "<회원등록>"));// 레이아웃주기

		// 서쪽조립
		p_west.add(la_name);
		p_west.add(t_name);
		group.add(gender[0]);
		group.add(gender[1]);
		p_west.add(gender[0]);
		p_west.add(gender[1]);
		p_west.add(la_locker);
		p_west.add(t_locker);
		p_west.add(la_age);
		p_west.add(t_age);
		p_west.add(la_phone);
		p_west.add(t_phone);
		p_west.add(la_mail);
		p_west.add(t_mail);
		p_west.add(la_addr);
		p_west.add(t_addr);

		p_west.add(la_start);
		p_west.add(m_start);
		p_west.add(la_end);
		p_west.add(combo_endDay);

		p_west.add(can);
		p_west.add(img_regist);

		p_west.add(regist);
		p_west.add(edit);
		p_west.add(del);
		p_west.add(bt_mail);

		// 가운데 영역 전체패널에 두 패널부착
		add(p_west, BorderLayout.WEST);

		// 가운데 위쪽영역영역생성
		c_north = new JPanel();
		c_center = new JPanel();
		n_la_name = new JLabel("이름");
		n_t_name = new JTextField(13);
		search = new JButton("검색");
		list = new JButton("전체목록");
		table = new JTable(memberList = new MemberList());
		scroll = new JScrollPane(table);

		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);

		p_center = new JPanel();// 전체패널에 가운데
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north, BorderLayout.NORTH);
		p_center.add(c_center);
		p_center.setPreferredSize(new Dimension(870, 700));
		add(p_center);

		// 북쪽 부착
		c_north.add(n_la_name);
		c_north.add(n_t_name);
		c_north.add(search);
		c_north.add(list);
		p_center.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "<등록회원 리스트>"));// 레이아웃주기

		// 북쪽스타일 적용
		n_la_name.setPreferredSize(new Dimension(60, 60));
		n_t_name.setPreferredSize(new Dimension(50, 30));

		// 회원이미지찾기버튼과 리스너연결
		img_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();// 쇼핑몰에 사용할 상품이미지 선택!!
				// preview();
			}
		});

		// 등록 버튼
		regist.addActionListener((e) -> {
			boolean flag = check();
			if (flag) {
				boolean locker = lockerCheck();
				if (locker) {
					regist(getMemberNumber(), selectedGender());
					getMemberList(null);
					exitText();
				}
			}
		});
		
		// 수정버튼
		edit.addActionListener((e) -> {
//			boolean flag = check();
//			if (flag) {
//				edit(getMemberNumber(), selectedGender());
//				getMemberList(null);
//			}
		});

		// 삭제버튼
		del.addActionListener((e) -> {
			if (JOptionPane.showConfirmDialog(Member.this, "삭제하시겠습니까?") == JOptionPane.OK_OPTION) {
				delete();
				can.removeAll();
				exitText();
			}
		});

		// 오른쪽 상단 회원검색
		search.addActionListener((e) -> {
			getMemberList(n_t_name.getText());
		});

		// 전체목록
		list.addActionListener((e) -> {
			getMemberList(null);
		});

		// 테이블과 마우스 리스너연결
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow(); // 선택한 행
				TableModel data = table.getModel();

				String value = (String) data.getValueAt(row, 0);
				m_id = value;

				// 상세보기 가져오기
				getDetail(data, row);

				// 성별 선택
				String s = (String) table.getValueAt(row, 2);
				if (s.equals(gender[0].getText())) {
					gender[0].setSelected(true);
				} else if (s.equals(gender[1].getText())) {
					gender[1].setSelected(true);
				}
				System.out.println("id" + value);
			}
		});
		getMemberList(null);
	}

	// 성별선택
	public String selectedGender() {
		String human = null;
		if (gender[0].isSelected() == true) {
			human = gender[0].getText();
		} else if (gender[1].isSelected() == true) {
			human = gender[1].getText();
		}
		return human;
	}

	// 이름으로 번호 찾는 메서드
	public int getMemberNumber() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;

		String sql = "select m_id from gym_member where m_name=?";
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setNString(1, t_name.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("m_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
		return id;
	}

//트레이너 번호로 트레이너 이름 가져오는 메서드
	public String getMemberName(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;

		String sql = "select m_name from gym_member where m_id=?";
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("m_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
		return name;
	}

	// 등록 메서드
	public void regist(int id, String human) {
		PreparedStatement pstmt = null;

		String sql = "insert into gym_member(m_id,m_name, m_gender ";
		sql += ",m_locker ,m_age ,m_phone ,m_mail ,m_addr,m_regdate ,m_expdate,m_file,m_pay)";
		sql += " values( seq_gym_member.nextval,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			System.out.println(pstmt);
			System.out.println(sql);
			int m = 1 + model.getMonth();
			int d = model.getDay();
			String mm = null;
			String dd = null;
			if (model.getMonth() < 10 || model.getDay() < 10) {
				if (m < 10) {
					mm = "0" + m;
				} else {
					mm = "" + m;
				}

				if (d <= 10) {
					dd = "0" + d;
				} else {
					dd = "" + d;
				}
			} else {
				mm = "" + m;
				dd = "" + d;
			}
			String getText = model.getYear() + "-" + mm + "-" + dd;
			String selectday = combo_endDay.getSelectedItem().toString();

			// 테이블에서 값 얻어서 넣어주기
			pstmt.setString(1, t_name.getText());
			pstmt.setString(2, human);
			// pstmt.setInt(3, Integer.parseInt(t_number.getText()));//

			pstmt.setInt(3, Integer.parseInt(t_locker.getText()));

			pstmt.setInt(4, Integer.parseInt(t_age.getText()));
			pstmt.setString(5, t_phone.getText());
			pstmt.setString(6, t_mail.getText());
			pstmt.setString(7, t_addr.getText());
			pstmt.setString(8, getText);

			LocalDate writedate = LocalDate.parse(getText);
			String w_day1 = (writedate.plusDays(1)).toString();
			String w_day7 = (writedate.plusDays(7)).toString();
			String w_day30 = (writedate.plusDays(30)).toString();
			String w_day60 = (writedate.plusDays(60)).toString();
			String w_day90 = (writedate.plusDays(90)).toString();
			String w_day365 = (writedate.plusDays(365)).toString();
			if (selectday == "1일") {
				pstmt.setString(9, w_day1);
				pstmt.setInt(11, 5000);
			} else if (selectday == "1주일") {
				pstmt.setString(9, w_day7);
				pstmt.setInt(11, 30000);
			} else if (selectday == "30일") {
				pstmt.setString(9, w_day30);
				pstmt.setInt(11, 50000);
			} else if (selectday == "60일") {
				pstmt.setString(9, w_day60);
				pstmt.setInt(11, 80000);
			} else if (selectday == "90일") {
				pstmt.setString(9, w_day90);
				pstmt.setInt(11, 120000);
			} else if (selectday == "365일") {
				pstmt.setString(9, w_day365);
				pstmt.setInt(11, 360000);
			}

			pstmt.setString(10, file.getAbsolutePath());
			System.out.println("현재파일경로:" + file.getAbsolutePath());

			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패");
			} else {
				JOptionPane.showMessageDialog(this, "등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

	// 수정 메서드
	public void edit(int id, String human) {
		PreparedStatement pstmt = null;

		String sql = "update gym_member SET  m_name=?, m_gender=? ";
		sql += ",m_locker=? ,m_age=? ,m_phone=? ,m_mail=? ,m_addr=?,m_regdate=? ,m_expdate=?,m_file=?,m_pay=?";
		sql += " where m_id=" + m_id;
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			System.out.println(pstmt);
			System.out.println(sql);
			int m = 1 + model.getMonth();
			int d = model.getDay();
			String mm = null;
			String dd = null;
			if (model.getMonth() < 10 || model.getDay() < 10) {
				if (m < 10) {
					mm = "0" + m;
				} else {
					mm = "" + m;
				}

				if (d <= 10) {
					dd = "0" + d;
				} else {
					dd = "" + d;
				}
			} else {
				mm = "" + m;
				dd = "" + d;
			}
			String getText = model.getYear() + "-" + mm + "-" + dd;
			String selectday = combo_endDay.getSelectedItem().toString();

			// 테이블에서 값 얻어서 넣어주기
			pstmt.setString(1, t_name.getText());
			pstmt.setString(2, human);
			// pstmt.setInt(3, Integer.parseInt(t_number.getText()));//

			pstmt.setInt(3, Integer.parseInt(t_locker.getText()));

			pstmt.setInt(4, Integer.parseInt(t_age.getText()));
			pstmt.setString(5, t_phone.getText());
			pstmt.setString(6, t_mail.getText());
			pstmt.setString(7, t_addr.getText());
			pstmt.setString(8, getText);

			LocalDate writedate = LocalDate.parse(getText);
			String w_day1 = (writedate.plusDays(1)).toString();
			String w_day7 = (writedate.plusDays(7)).toString();
			String w_day30 = (writedate.plusDays(30)).toString();
			String w_day60 = (writedate.plusDays(60)).toString();
			String w_day90 = (writedate.plusDays(90)).toString();
			String w_day365 = (writedate.plusDays(365)).toString();
			if (selectday == "1일") {
				pstmt.setString(9, w_day1);
				pstmt.setInt(11, 5000);
			} else if (selectday == "1주일") {
				pstmt.setString(9, w_day7);
				pstmt.setInt(11, 30000);
			} else if (selectday == "30일") {
				pstmt.setString(9, w_day30);
				pstmt.setInt(11, 50000);
			} else if (selectday == "60일") {
				pstmt.setString(9, w_day60);
				pstmt.setInt(11, 80000);
			} else if (selectday == "90일") {
				pstmt.setString(9, w_day90);
				pstmt.setInt(11, 120000);
			} else if (selectday == "365일") {
				pstmt.setString(9, w_day365);
				pstmt.setInt(11, 360000);
			}

			pstmt.setString(10, file.getAbsolutePath());
			System.out.println("현재파일경로:" + file.getAbsolutePath());

			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "수정 실패");
			} else {
				JOptionPane.showMessageDialog(this, "수정 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

	// 삭제 메서드
	public void delete() {
		PreparedStatement pstmt = null;

		String sql = "delete gym_member where m_id=" + m_id;
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "삭제 실패");
			} else {
				JOptionPane.showMessageDialog(this, "삭제 성공");
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

	public void getDetail(TableModel data, int row) {
		t_name.setText((String) data.getValueAt(row, 1));
		t_locker.setText((String) data.getValueAt(row, 3));
		
		t_age.setText((String) data.getValueAt(row, 4));
		t_phone.setText((String) data.getValueAt(row, 5));
		mail=(String) data.getValueAt(row, 6);
		t_mail.setText((String) data.getValueAt(row, 6));
		t_addr.setText((String) data.getValueAt(row, 7));
		getTargetImage((String) data.getValueAt(row, 10));

		file = new File((String) data.getValueAt(row, 10));

		String start = (String) table.getValueAt(row, 8);
		int start_yy = Integer.parseInt((String) start.subSequence(0, 4)) - 1900;
		int start_mm = Integer.parseInt((String) start.subSequence(5, 7)) - 1;
		int start_dd = Integer.parseInt((String) start.subSequence(8, start.length()));
		Date date = new Date(start_yy, start_mm, start_dd);
		model.setValue(date);

	}

	// 리스트 가져오기
	public void getMemberList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		if (name == null) {
			// 회원번호", "이름", "나이", "성별", "전화번호", "메일", "주소", "등록일", "만기일
			sql = "select * from gym_member Order By m_id desc"; // m_id기준으로 내림차순
		} else {
			sql = "select * from gym_member where m_name='" + name + "'";
		}

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			ArrayList<MemberVO> MemberVOList = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setM_id(rs.getInt("m_id"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_gender(rs.getString("m_gender"));
				vo.setM_locker(rs.getInt("m_locker"));
				vo.setM_age(rs.getInt("m_age"));
				vo.setM_phone(rs.getString("m_phone"));
				vo.setM_mail(rs.getString("m_mail"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_regdate(rs.getNString("m_regdate"));
				vo.setM_expdate(rs.getString("m_expdate"));
				vo.setM_file(rs.getString("m_file"));
				vo.setM_pay(rs.getInt("m_pay"));

				MemberVOList.add(vo);
			}
			memberList = new MemberList();
//         memberList.column = columnNames; // 컬럼 정보 대입
			memberList.record = MemberVOList;

			table.setModel(memberList);
			table.updateUI();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
	}

	// 입력후 텍스트 필드값 삭제
	public void exitText() {
		t_name.setText("");
		t_age.setText("");
		t_phone.setText("");
		t_mail.setText("");
		t_addr.setText("");
		t_locker.setText("");
		group.clearSelection();// 성별체크
		getMemberList(null);
		model.setValue(null);
	}

	// 타겟이미지얻어오기!
	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = img.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
		if (img != null) {
			can.repaint();
		}
	}

	public void findImage() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// 파일정보를 구한다.
			file = chooser.getSelectedFile();
			System.out.println("선택한 파일 정보 " + file.getAbsolutePath());
			getTargetImage(file.getAbsolutePath());
			can.repaint();
		}
	}

	public boolean lockerCheck() {
		boolean flag = false;
		PreparedStatement pstmt = null;
		int rs = 0;

		String sql = "select * from gym_member where m_locker=" + t_locker.getText();

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			rs = pstmt.executeUpdate();
			if (rs != 0) {
				JOptionPane.showMessageDialog(this, "사용중인 락커입니다.");
				return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
		return flag = true;
	}

	public boolean check() {
		boolean flag = false;

		if (t_name.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요");
			t_name.requestFocus();
			return flag;
		} else if (t_age.getText() == null || t_age.getText().length() < 6 || t_age.getText().length() > 6) {
			JOptionPane.showMessageDialog(this, "생년월일을 바르게 입력하세요");
			t_age.requestFocus();
			return flag;
		} else if (t_phone.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력하세요");
			t_phone.requestFocus();
			return flag;
		} else if (t_mail.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "이메일을 입력하세요");
			t_mail.requestFocus();
			return flag;
		} else if (t_addr.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "주소을 입력하세요");
			t_addr.requestFocus();
			return flag;
		}
		return flag = true;
	}

	public void lockerCheck(String m_locker) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from gym_member where m_locker= " + m_locker;
	}

}