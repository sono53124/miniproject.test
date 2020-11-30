package testapp.copy1;

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
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Program extends JPanel {
	JPanel input, p_west, p_center, c_center, c_north, can, bt_north;
	JTextField n_t_name, t_member, t_manager;
	JLabel la_name, la_day, la_member, la_start, la_end, n_la_name, la_manager, la_price, la_time, empty;
	JButton img_regist, regist, edit, del, search, list;
	JScrollPane scroll;
	JTable table;

	Image img;
	File file;
	ProgramList programList;
	JFileChooser chooser = new JFileChooser("D:/workspace/js_workspace/images/project_gym");
	Toolkit kit = Toolkit.getDefaultToolkit();
	String days[] = { "월,수,금", "화,목" };
	String program_name[] = { "스피닝", "뮤직복싱", "줌바", "필라테스", "요가" };
	String program_time[] = { "10시~11시", "11시~12시", "19시~20시", "20시~21시", "21시~22시" };
	String program_pay[] = { "50000", "80000" };
	JComboBox<String> combo_day;
	JComboBox<String> combo_program;
	JComboBox<String> combo_pay;
	JComboBox<String> combo_time;

// 프로그램 날짜 선택하게 해줄 datePicker
	UtilDateModel model, model2;
	JDatePanelImpl datePanel, datePanel2;
	JDatePickerImpl pg_start, pg_end;

	MainPage mainPage;
	String pg_id;

	Connection con;

	public Program(MainPage mainPage) {
		this.mainPage = mainPage;
		con = mainPage.getCon();
// 서쪽영역 생성
		p_west = new JPanel();

// datePicker 생성
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		pg_start = new JDatePickerImpl(datePanel);

		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2);
		pg_end = new JDatePickerImpl(datePanel2);

		la_name = new JLabel("프로그램명");
		combo_program = new JComboBox<String>(program_name);
		la_day = new JLabel("요일");
		combo_day = new JComboBox<String>(days);
		la_start = new JLabel("시작");
		la_end = new JLabel("종료");
		la_member = new JLabel("회원번호");
		t_member = new JTextField(5);
		la_manager = new JLabel("트레이너");
		t_manager = new JTextField(8);
		la_price = new JLabel("금액");
		combo_pay = new JComboBox<String>(program_pay);
		la_time = new JLabel("시간선택");
		combo_time = new JComboBox<String>(program_time);
		can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};

		img_regist = new JButton("이미지 파일 등록");
		empty = new JLabel("");
		regist = new JButton("등록");
		edit = new JButton("수정");
		del = new JButton("삭제");
// 서쪽조립
		p_west.add(la_name);
		p_west.add(combo_program);
		p_west.add(la_day);
		p_west.add(combo_day);
		p_west.add(la_start);
		p_west.add(pg_start);
		p_west.add(la_end);
		p_west.add(pg_end);
		p_west.add(la_member);
		p_west.add(t_member);
		p_west.add(la_manager);
		p_west.add(t_manager);
		p_west.add(la_price);
		p_west.add(combo_pay);
		p_west.add(la_time);
		p_west.add(combo_time);
		p_west.add(can);
		p_west.add(img_regist);
		p_west.add(empty);
		p_west.add(regist);
		p_west.add(edit);
		p_west.add(del);

// 서쪽 스타일적용
		p_west.setPreferredSize(new Dimension(330, 700));

		la_name.setPreferredSize(new Dimension(70, 60));
		combo_program.setPreferredSize(new Dimension(80, 30));
		la_day.setPreferredSize(new Dimension(40, 60));
		combo_day.setPreferredSize(new Dimension(80, 30));

		la_start.setPreferredSize(new Dimension(35, 60));
		pg_start.setPreferredSize(new Dimension(110, 26));
		la_end.setPreferredSize(new Dimension(35, 60));
		pg_end.setPreferredSize(new Dimension(110, 26));

		la_manager.setPreferredSize(new Dimension(60, 60));
		t_manager.setPreferredSize(new Dimension(60, 30));

		la_member.setPreferredSize(new Dimension(60, 60));
		t_member.setPreferredSize(new Dimension(60, 30));

		la_price.setPreferredSize(new Dimension(40, 60));
		combo_pay.setPreferredSize(new Dimension(80, 30));

		la_time.setPreferredSize(new Dimension(60, 60));
		combo_time.setPreferredSize(new Dimension(100, 30));

		can.setPreferredSize(new Dimension(240, 240));
		img_regist.setPreferredSize(new Dimension(280, 30));
		empty.setPreferredSize(new Dimension(340, 20));
		regist.setPreferredSize(new Dimension(90, 30));
		edit.setPreferredSize(new Dimension(90, 30));
		del.setPreferredSize(new Dimension(90, 30));

		p_west.setBackground(Color.lightGray);
		p_center = new JPanel();
		p_west.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "<프로그램 정보입력>"));// 레이아웃주기

// 가운데 영역 전체패널에 두 패널부착
		add(p_west, BorderLayout.WEST);

// 가운데 위쪽영역영역생성
		c_north = new JPanel();
		c_center = new JPanel();

		n_la_name = new JLabel("프로그램명");
		n_t_name = new JTextField(10);
		search = new JButton("검색");
		list = new JButton("전체목록");
		table = new JTable(programList = new ProgramList());
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
		p_center.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "<프로그램 리스트>"));// 레이아웃주기

// 북쪽스타일 적용
		n_la_name.setPreferredSize(new Dimension(80, 60));
		n_t_name.setPreferredSize(new Dimension(50, 30));

// 회원이미지찾기버튼과 리스너연결
		img_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();// 쇼핑몰에 사용할 상품이미지 선택!!
			}
		});

// 등록 버튼
		regist.addActionListener((e) -> {
			boolean flag = check();
			if (flag) {
				regist(getManagerNumber());
				getProgramList(null);
			}
		});

// 수정버튼
		edit.addActionListener((e) -> {
			boolean flag = check();
			if (flag) {
				edit(getManagerNumber());
				getProgramList(null);
			}
		});

// 삭제버튼
		del.addActionListener((e) -> {
			if (JOptionPane.showConfirmDialog(Program.this, "삭제하시겠습니까?") == JOptionPane.OK_OPTION) {
				delete();
				reset();
			}
			can.removeAll();
			can.remove(Program.this);
		});

// 검색버튼
		search.addActionListener((e) -> {
			getProgramList(n_t_name.getText());
		});

// 전체목록 버튼
		list.addActionListener((e) -> {
			getProgramList(null);
		});

// 테이블에서 선택시 상세목록 출력
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				int row = table.getSelectedRow();
				file = new File((String) table.getValueAt(row, 9));
				String program_name = (String) table.getValueAt(row, 2);// 프로그램명
				String program_day = (String) table.getValueAt(row, 3);// 프로그램 요일
				String program_pay = (String) table.getValueAt(row, 7);// 프로그램 금액
				String program_time = (String) table.getValueAt(row, 6);// 프로그램 시간

				combo_program.setSelectedItem(program_name);
				combo_day.setSelectedItem(program_day);
				combo_pay.setSelectedItem(program_pay);
				combo_time.setSelectedItem(program_time);
				t_member.setText((String) table.getValueAt(row, 8));
				t_manager.setText((String) table.getValueAt(row, 1));

				getTargetImage((String) table.getValueAt(row, 9));
				date(model, row, 4);
				date(model2, row, 5);
				pg_id = (String) table.getValueAt(row, 0);
				System.out.println("pg_id : " + pg_id);
			}
		});

		getProgramList(null);
	}

// 리스트 가져오기
	public void getProgramList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		if (name == null) {
			sql = "select * from gym_program order by pg_id desc";
		} else {
			sql = "select * from gym_program where pg_name='" + name + "'";
		}

		try {
			pstmt = mainPage.getCon().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			String[][] lists = new String[total][10];

			rs.beforeFirst();
			int index = 0;
			while (rs.next()) {
				String[] arr = new String[10];
				arr[0] = rs.getString("pg_id");
				arr[1] = getManagerName(rs.getInt("order_id"));
				arr[2] = rs.getString("pg_name");
				arr[3] = rs.getString("pg_day");
				arr[4] = rs.getString("pg_start_day");
				arr[5] = rs.getString("pg_end_day");
				arr[6] = rs.getString("pg_time");
				arr[7] = rs.getString("pg_price");
				arr[8] = rs.getString("m_id");
				arr[9] = rs.getString("pg_file");
				lists[index++] = arr;
			}
			programList.data = lists;
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
	}

// 트레이너 이름으로 트레이너 번호 찾는 메서드
	public int getManagerNumber() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;

		String sql = "select order_id from gym_manager where manager_name=?";
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setNString(1, t_manager.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("order_id"); // 쿼리문에서 트레이너 id(int값)dmf id를 넣은것
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
		return id; // 아이디를 얻어온이유? 트레이너 이름을 텍스트필드에 넣었을때, order_id-->트레이너마다 붙는 고유의 숫자값얻어올 수 있다.
					// 이것을 활용해서 여러가지 쿼리문을 가져오려고!
	}

// 트레이너 번호로 트레이너 이름 가져오는 메서드
	public String getManagerName(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;

		String sql = "select manager_name from gym_manager where order_id=?";
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("manager_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
		return name;
	}

//등록 메서드
	public void regist(int id) {
		PreparedStatement pstmt = null;

		String sql = "insert into gym_program(pg_id,order_id ,pg_name ,pg_day ,pg_start_day ,";
		sql += "pg_end_day, pg_time, pg_price ,m_id ,pg_file) values(seq_gym_program.nextval,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setInt(1, id);// 트레이너 번호
			pstmt.setString(2, combo_program.getSelectedItem().toString()); // 프로그램이름
			pstmt.setString(3, combo_day.getSelectedItem().toString()); // 날짜
			pstmt.setString(4, model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());// 등록일
			pstmt.setString(5, model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay());// 마감일
			pstmt.setString(6, combo_time.getSelectedItem().toString());// 시간
			pstmt.setString(7, combo_pay.getSelectedItem().toString());// 돈
			pstmt.setInt(8, Integer.parseInt(t_member.getText())); // 회원번호
			if (img == null) {
				JOptionPane.showMessageDialog(this, "이미지를 등록해주세요");
				return;
			}
			pstmt.setString(9, file.getAbsolutePath());
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패");
			} else {
				JOptionPane.showMessageDialog(this, "등록 성공");
			}
		} catch (SQLException e) {
//         if (img != null) {
//            JOptionPane.showMessageDialog(this, "등록실패 트레이너 이름을 확인해주세요");
//            return;
//         }
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

//수정 메서드
	public void edit(int id) {
		PreparedStatement pstmt = null;

		String sql = "update gym_program set order_id=?,pg_name =?,pg_day=?,pg_start_day=?,";
		sql += "pg_end_day=?,pg_time=?,pg_price=?,m_id=?,pg_file=?";
		sql += " where pg_id=" + pg_id;

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, combo_program.getSelectedItem().toString());
			pstmt.setString(3, combo_day.getSelectedItem().toString());
			pstmt.setString(4, model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
			pstmt.setString(5, model2.getYear() + "-" + (model2.getMonth() + 1) + "-" + model2.getDay());
			pstmt.setString(6, combo_time.getSelectedItem().toString());
			pstmt.setString(7, combo_pay.getSelectedItem().toString());
			pstmt.setString(8, t_member.getText());
			if (img == null) {
				JOptionPane.showMessageDialog(this, "이미지를 등록해주세요");
				return;
			}
			pstmt.setString(9, file.getAbsolutePath());
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "수정 실패");
			} else {
				JOptionPane.showMessageDialog(this, "수정 성공");
			}
		} catch (SQLException e) {
//         if (img != null) {
			JOptionPane.showMessageDialog(this, "수정중 문제가 발생했습니다");
//            return;
//         }
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}
	}

//삭제 메서드
	public void delete() {
		PreparedStatement pstmt = null;

		String sql = "delete gym_program where pg_id=" + pg_id;
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

	public void reset() {
		combo_program.setSelectedIndex(0);
		combo_day.setSelectedIndex(0);
		combo_pay.setSelectedIndex(0);
		combo_time.setSelectedIndex(0);
		model.setValue(null);
		model2.setValue(null);
		t_member.setText("");
		t_manager.setText("");
		getProgramList(null);
	}

	public void date(UtilDateModel model, int row, int col) {
		String start = (String) table.getValueAt(row, col);
		int mm = 0;
		int dd = 0;

		int yy = Integer.parseInt((String) start.subSequence(0, 4)) - 1900;
		if (start.charAt(6) == '-') {
			mm = Integer.parseInt((String) start.subSequence(5, 6)) - 1;
		} else {
			mm = Integer.parseInt((String) start.subSequence(5, 7)) - 1;
		}
		if (start.length() == 8) {
			dd = Integer.parseInt((String) start.subSequence(7, start.length()));
		} else {
			dd = Integer.parseInt((String) start.subSequence(8, start.length()));
		}
		Date date = new Date(yy, mm, dd);
		model.setValue(date);
	}

	public boolean check() {
		boolean flag = false;
		if (t_member.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "회원번호를 입력하세요");
			t_member.requestFocus();
			return flag;
		} else if (t_manager.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "담당 트레이너를 입력하세요");
			return flag;
		}
		return flag = true;
	}

	public void findImage() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
// 파일정보를 구한다.
			file = chooser.getSelectedFile();
			System.out.println("선택한 파일 정보 : " + file.getAbsolutePath());
			getTargetImage(file.getAbsolutePath());
		}
	}

	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
		if (img != null) {
			can.repaint();
		}
	}

}