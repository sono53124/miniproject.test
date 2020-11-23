package project;

//내가만든코드 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

//import com.mysql.fabric.xmlrpc.base.Data;

//import common.image.ImageUtil;

public class Member extends JPanel {
	JPanel input, p_west, p_center, c_center, c_north, can, bt_north;
	JTextField t_name, t_age, t_phone, t_mail, t_addr, n_t_name, t_key, t_locker;
	JLabel la_name, la_age, la_phone, la_mail, la_addr, n_la_name, la_key, la_locker, empty;
	JButton img_regist, regist, edit, del, search, list;
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
	MemberList meberList;
	JFileChooser chooser = new JFileChooser("D:/workspace/java/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();

	String days[] = { "1일", "1주일", "30일", "60일", "90일", "365일" };
	JComboBox<String> combo_endDay;
	MainPage mainPage;

	MemberList memberList;
	MemberVO memberVO;

	public Member(MainPage mainPage) {

		this.mainPage = mainPage;

		// 서쪽영역 생성
		p_west = new JPanel();
		la_name = new JLabel("이름");
		t_name = new JTextField(13);

		// 젠더셀렉
		group = new ButtonGroup();
		gender[0] = new JRadioButton("man", true);
		gender[1] = new JRadioButton("woman", true);

		la_age = new JLabel("생년월일");
		t_age = new JTextField(8);
		la_phone = new JLabel(" 전화번호");
		t_phone = new JTextField(8);
		la_mail = new JLabel("메일");
		t_mail = new JTextField(22);
		la_addr = new JLabel("주소");
		t_addr = new JTextField(22);
		la_key = new JLabel("회원번호");
		t_key = new JTextField(10);
		la_locker = new JLabel(" 락카키");
		t_locker = new JTextField(5);
		can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		img_regist = new JButton("이미지 파일 등록");
		la_start = new JLabel("시작일");
		la_end = new JLabel("만기일");
		t_start = new JTextField(11);
		combo_endDay = new JComboBox<String>(days);
		empty = new JLabel("");
		regist = new JButton("등록");
		edit = new JButton("수정");
		del = new JButton("삭제");
		// 서쪽조립
		p_west.add(la_name);
		p_west.add(t_name);
		group.add(gender[0]);
		group.add(gender[1]);
		p_west.add(gender[0]);
		p_west.add(gender[1]);
		p_west.add(la_key);
		p_west.add(t_key);
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
		p_west.add(t_start);
		p_west.add(la_end);
		p_west.add(combo_endDay);

		p_west.add(can);
		p_west.add(img_regist);
		p_west.add(empty);

		p_west.add(regist);
		p_west.add(edit);
		p_west.add(del);

		// 서쪽 스타일적용
		p_west.setPreferredSize(new Dimension(330, 700));
		la_name.setPreferredSize(new Dimension(40, 40));
		t_name.setPreferredSize(new Dimension(50, 30));
		la_age.setPreferredSize(new Dimension(60, 40));
		t_age.setPreferredSize(new Dimension(50, 30));
		la_phone.setPreferredSize(new Dimension(60, 40));
		t_phone.setPreferredSize(new Dimension(50, 30));
		la_mail.setPreferredSize(new Dimension(40, 40));
		t_mail.setPreferredSize(new Dimension(50, 30));
		la_addr.setPreferredSize(new Dimension(40, 60));
		t_addr.setPreferredSize(new Dimension(60, 30));
		la_start.setPreferredSize(new Dimension(40, 50));
		t_start.setPreferredSize(new Dimension(50, 30));
		combo_endDay.setPreferredSize(new Dimension(80, 30));
		empty.setPreferredSize(new Dimension(340, 20));

		la_key.setPreferredSize(new Dimension(60, 40));
		t_key.setPreferredSize(new Dimension(50, 30));
		la_locker.setPreferredSize(new Dimension(44, 40));
		t_locker.setPreferredSize(new Dimension(50, 30));

		can.setPreferredSize(new Dimension(240, 240));

		img_regist.setPreferredSize(new Dimension(280, 30));
		regist.setPreferredSize(new Dimension(90, 30));
		edit.setPreferredSize(new Dimension(90, 30));
		del.setPreferredSize(new Dimension(90, 30));
		p_west.setBackground(Color.lightGray);
		p_center = new JPanel();
		p_west.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "<회원등록>"));// 레이아웃주기

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
			String action = "regist";
			Update(getMemberNumber(), action, null, selectedGender());
			getMemberList(null);
			exitText();
		});

		// 수정버튼
		edit.addActionListener((e) -> {
			String action = "edit";
			Update(getMemberNumber(), action, null, selectedGender());
			getMemberList(null);
			exitText();
		});

		// 삭제버튼
		del.addActionListener((e) -> {
			String action = "del";
			if (JOptionPane.showConfirmDialog(Member.this, "삭제하시겠습니까?") == JOptionPane.OK_OPTION) {
				Update(0, action, null, selectedGender());
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
				int col = table.getSelectedColumn();// 선택한 열
				TableModel data = table.getModel();

				String value = (String) data.getValueAt(row, 0);
				m_id = value;

				// 성별 선택
				String s = (String) table.getValueAt(row, 2);
				if (s.equals(gender[0].getText())) {
					gender[0].setSelected(true);
				} else if (s.equals(gender[1].getText())) {
					gender[1].setSelected(true);
				}

				Update(0, null, value, selectedGender());
				System.out.println(value);
			}
		});

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

//등록,수정,삭제,테이블 상세보기
	public void Update(int id, String action, String value, String human) {
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		int result = 0;

		if (value == null) {
			if (action == "regist") {
				sql = "insert into gym_member(m_id,m_name, m_gender ";
				sql += ",m_locker ,m_age ,m_phone ,m_mail ,m_addr,m_regdate ,m_expdate,m_file)";
				sql += " values( seq_gym_member.nextval,?,?,?,?,?,?,?,?,?,?)";// ? 10개
			} else if (action == "edit") {
				sql = "update gym_member SET  m_name=?, m_gender=? ";
				sql += ",m_locker=? ,m_age=? ,m_phone=? ,m_mail=? ,m_addr=?,m_regdate=? ,m_expdate=?,m_file=?";
				sql += " where m_id=" + m_id;
			} else if (action == "del") {
				sql += "delete gym_member where m_id=" + m_id;
				System.out.println("삭제할 m_id : " + m_id);
				System.out.println("sql문 : " + sql);
			}
		} else {
			sql = "select * from gym_member where m_id=?";
			System.out.println("선택한 m_id : " + value);
		}

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			String getText = t_start.getText();
			String selectday = combo_endDay.getSelectedItem().toString();
			if (value == null) {
				if (action == "del") {// 삭제버튼 누를때
					result = pstmt.executeUpdate();
					System.out.println(result);
					if (result == 0) {
						JOptionPane.showMessageDialog(this, "삭제 실패");
					} else {
						JOptionPane.showMessageDialog(this, "삭제 성공");
					}
				} else {
					// 테이블에서 값 가져오기
					pstmt.setString(1, t_name.getText());
					pstmt.setString(2, human);
					// pstmt.setInt(3, Integer.parseInt(t_number.getText()));//
					pstmt.setInt(3, Integer.parseInt(t_locker.getText()));
					pstmt.setInt(4, Integer.parseInt(t_age.getText()));
					pstmt.setString(5, t_phone.getText());
					pstmt.setString(6, t_mail.getText());
					pstmt.setString(7, t_addr.getText());
					pstmt.setString(8, getText);
					if (getText != null) {
						LocalDate writedate = LocalDate.parse(getText);
						String w_day1 = (writedate.plusDays(1)).toString();
						String w_day7 = (writedate.plusDays(7)).toString();
						String w_day30 = (writedate.plusDays(30)).toString();
						String w_day60 = (writedate.plusDays(60)).toString();
						String w_day90 = (writedate.plusDays(90)).toString();
						String w_day365 = (writedate.plusDays(365)).toString();
						if (selectday == "1일") {
							pstmt.setString(9, w_day1);
						} else if (selectday == "1주일") {
							pstmt.setString(9, w_day7);
						} else if (selectday == "30일") {
							pstmt.setString(9, w_day30);
						} else if (selectday == "60일") {
							pstmt.setString(9, w_day60);
						} else if (selectday == "90일") {
							pstmt.setString(9, w_day90);
						} else if (selectday == "365일") {
							pstmt.setString(9, w_day365);
						}
					}
					pstmt.setString(10, file.getAbsolutePath());
					System.out.println("현재파일경로:" + file.getAbsolutePath());

					if (action == "regist") {// 등록
						result = pstmt.executeUpdate();
						if (result == 0) {
							JOptionPane.showMessageDialog(this, "등록 실패");
						} else {
							JOptionPane.showMessageDialog(this, "등록 성공");
						}
					} else if (action == "edit") {// 수정
						result = pstmt.executeUpdate();
						if (result == 0) {
							JOptionPane.showMessageDialog(this, "수정 실패");
						} else {
							JOptionPane.showMessageDialog(this, "수정 완료");
						}
					}
				}
			} else {
				pstmt.setInt(1, Integer.parseInt(m_id));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					t_name.setText(rs.getString("m_name"));
					t_key.setText(rs.getString("m_id"));
					t_locker.setText(rs.getString("m_locker"));
					t_age.setText(rs.getString("m_age"));
					t_phone.setText(rs.getString("m_phone"));
					t_mail.setText(rs.getString("m_mail"));
					t_addr.setText(rs.getString("m_addr"));
					t_start.setText(rs.getString("m_regdate"));
					// String selectday = combo_endDay.getSelectedItem().toString();
					// selectday.setText(rs.getString("m_expdate"));
					file = new File(rs.getString("m_file"));
					getTargetImage(rs.getString("m_file"));
					// getTargetImage(file);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

//리스트 가져오기
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
		t_key.setText("");
		t_age.setText("");
		t_phone.setText("");
		t_mail.setText("");
		t_addr.setText("");
		t_key.setText("");
		t_start.setText("");
		t_locker.setText("");
		group.clearSelection();// 성별체크
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

	public void preview() {
		// paint로 그림 처리~~
		can.repaint();
	}

}