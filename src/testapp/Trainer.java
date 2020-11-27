package testapp;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

public class Trainer extends JPanel {
	JPanel input, p_west, p_center, c_center, c_north, can, bt_north;
	JTextField t_name, t_age, t_phone, t_mail, t_address, n_t_name;
	JLabel la_name, la_age, la_phone, la_mail, la_address, n_la_name;
	JButton img_regist, regist, edit, del, search, list;
//   JRadioButton man, woman;
	ButtonGroup group;
	JRadioButton[] gender = new JRadioButton[2];

	JScrollPane scroll;
	JTable table;

//   String human = null;
	Image img;
	File file;
	String filename;// 상세보기 눌렀을때 파일경로 담을곳
	TrainerList trainerList;
	JFileChooser chooser = new JFileChooser("D:/workspace/java/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();

	MainPage mainPage;
	TrainerVO trainerVO;
	String order_id = null;

	public Trainer(MainPage mainPage) {
		this.mainPage = mainPage;

		// 서쪽영역 생성

		p_west = new JPanel();
		la_name = new JLabel("이름");
		t_name = new JTextField(13);
//      man = new JRadioButton();
//      man.setText("man");
//      woman = new JRadioButton();
//      woman.setText("woman");
		group = new ButtonGroup();
		gender[0] = new JRadioButton("man", true);
		gender[1] = new JRadioButton("woman", true);

		la_age = new JLabel("생년월일");
		t_age = new JTextField(20);
		la_phone = new JLabel(" 전화번호");
		t_phone = new JTextField(20);
		la_mail = new JLabel("메일");
		t_mail = new JTextField(22);
		la_address = new JLabel("주소");
		t_address = new JTextField(22);
		can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		img_regist = new JButton("이미지 파일 등록");

		regist = new JButton("등록");
		edit = new JButton("수정");
		del = new JButton("삭제");
		// 서쪽조립
		p_west.add(la_name);
		p_west.add(t_name);
//      p_west.add(man);
//      p_west.add(woman);
		group.add(gender[0]);
		group.add(gender[1]);
		p_west.add(gender[0]);
		p_west.add(gender[1]);
		p_west.add(la_age);
		p_west.add(t_age);
		p_west.add(la_phone);
		p_west.add(t_phone);
		p_west.add(la_mail);
		p_west.add(t_mail);
		p_west.add(la_address);
		p_west.add(t_address);
		p_west.add(can);
		p_west.add(img_regist);

		p_west.add(regist);
		p_west.add(edit);
		p_west.add(del);
		// 서쪽 스타일적용
		p_west.setPreferredSize(new Dimension(330, 700));
		la_name.setPreferredSize(new Dimension(30, 60));
		t_name.setPreferredSize(new Dimension(50, 30));
		la_age.setPreferredSize(new Dimension(60, 60));
		t_age.setPreferredSize(new Dimension(50, 30));
		la_phone.setPreferredSize(new Dimension(60, 60));
		t_phone.setPreferredSize(new Dimension(50, 30));
		la_mail.setPreferredSize(new Dimension(40, 60));
		t_mail.setPreferredSize(new Dimension(50, 30));
		la_address.setPreferredSize(new Dimension(40, 60));
		t_address.setPreferredSize(new Dimension(50, 30));
		can.setPreferredSize(new Dimension(280, 240));
		img_regist.setPreferredSize(new Dimension(280, 30));
		regist.setPreferredSize(new Dimension(90, 30));
		edit.setPreferredSize(new Dimension(90, 30));
		del.setPreferredSize(new Dimension(90, 30));
		p_west.setBackground(Color.lightGray);
		p_center = new JPanel();
		p_west.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "<트레이너>"));// 레이아웃주기

		// 가운데 영역 전체패널에 두 패널부착
		add(p_west, BorderLayout.WEST);

		// 가운데 위쪽영역영역생성
		c_north = new JPanel();
		c_center = new JPanel();
		n_la_name = new JLabel("이름");
		n_t_name = new JTextField(10);
		search = new JButton("검색");
		list = new JButton("전체목록");
		table = new JTable(trainerList = new TrainerList());
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
		p_center.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "<트레이너 리스트>"));// 레이아웃주기

		// 북쪽스타일 적용
		n_la_name.setPreferredSize(new Dimension(60, 60));
		n_t_name.setPreferredSize(new Dimension(50, 30));

		// 회원이미지찾기버튼과 리스너연결
		img_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();// 쇼핑몰에 사용할 상품이미지 선택!!
				preview();
			}
		});

		regist.addActionListener((e) -> {
			boolean flag =check();
			if(flag) {				
				Regist(selectedGender());
				getTrainerList(null);
				exitText();
			}

		});

		// 수정버튼
		edit.addActionListener((e) -> {
			boolean flag =check();
			if(flag) {	
				Edit(selectedGender());
				getTrainerList(null);
				exitText();
			}
		});

		// 삭제버튼
		del.addActionListener((e) -> {
			if (JOptionPane.showConfirmDialog(Trainer.this, "삭제하시겠습니까?") == JOptionPane.OK_OPTION) {
				Del();
				can.removeAll();
				exitText();
			}
		});

		// 강사검색 버튼
		search.addActionListener((e) -> {
			getTrainerList(n_t_name.getText());
		});

		// 전체검색 버튼
		list.addActionListener((e) -> {
			getTrainerList(null);
		});

		// 테이블과 마우스 리스너연결
		table.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow(); // 선택한 행
				int col = table.getSelectedColumn();// 선택한 열
				TableModel data = table.getModel();

				String value = (String) table.getValueAt(row, col);
				System.out.println(value);

				selectTable(row);
				getDetail(row);

				filename = (String) table.getValueAt(row, 6);
				getTargetImage(filename);// 이미지 그리기
				can.repaint();
				order_id = (String) table.getValueAt(row, 0);
				System.out.println("파일경로" + filename);
				// 성별 선택
				String s = (String) table.getValueAt(row, 7);
				if (s.equals(gender[0].getText())) {
					gender[0].setSelected(true);
				} else if (s.equals(gender[1].getText())) {
					gender[1].setSelected(true);
				}

			}

		});
		getTrainerList(null);

	}

	public String selectedGender() {
		String human = null;
		if (gender[0].isSelected() == true) {
			human = gender[0].getText();
		} else if (gender[1].isSelected() == true) {
			human = gender[1].getText();
		}
//      } else {
//         JOptionPane.showMessageDialog(this, "성별을 선택하세요");
//      }
		return human;
	}

	public int getTrainerNumber() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;

		String sql = "select order_id from gym_manager where manager_name=?";
		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			pstmt.setString(1, t_name.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("order_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
		return id;
	}

	public String getTrainerName(int id) {
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

	// 등록하기
	public void Regist(String human) {
		PreparedStatement pstmt = null;
		String sql = "";

		sql = "INSERT INTO gym_manager(order_id ,manager_name ,manager_age ";
		sql += " ,manager_phone ,manager_mail, manager_addr, manager_file,manager_gender )";
		sql += " values(seq_gym_manager.nextval,?,?,?,?,?,?,?)";

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);

			pstmt.setString(1, t_name.getText()); // 이름
			pstmt.setInt(2, Integer.parseInt(t_age.getText()));// 나이
			pstmt.setString(3, t_phone.getText()); // 번호
			pstmt.setString(4, t_mail.getText()); // 메일
			pstmt.setString(5, t_address.getText()); // 주소
			pstmt.setString(6, file.getAbsolutePath()); // 파일
			pstmt.setString(7, human); // 성별

			int result = pstmt.executeUpdate();

			if (result == 0) {
				JOptionPane.showMessageDialog(this, "등록실패");
			} else {
				JOptionPane.showMessageDialog(this, "등록성공");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}

	}

	// 수정하기
	public void Edit(String human) {
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;

		sql = "update gym_manager set manager_name=?,manager_age=?";
		sql += ", manager_phone=?, manager_mail=?,manager_addr=?";
		sql += ", manager_file=?,manager_gender=? ";
		sql += "where order_id=" + order_id;

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);

			pstmt.setString(1, t_name.getText()); // 이름
			pstmt.setInt(2, Integer.parseInt(t_age.getText()));// 나이
			pstmt.setString(3, t_phone.getText()); // 번호
			pstmt.setString(4, t_mail.getText()); // 메일
			pstmt.setString(5, t_address.getText()); // 주소
			pstmt.setString(6, file.getAbsolutePath()); // 파일
			pstmt.setString(7, human); // 성별

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

	// 삭제하기
	public void Del() {
		PreparedStatement pstmt = null;

		String sql = "delete gym_manager where order_id=" + order_id;

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);
			int result = pstmt.executeUpdate();

			if (result == 0) {
				JOptionPane.showMessageDialog(this, "삭제 실패");
			} else {
				JOptionPane.showMessageDialog(this, "삭제 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt);
		}

	}

	// 리스트 가져오기
	public void getTrainerList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		if (name == null) {
			sql = "SELECT * FROM gym_manager ORDER BY order_id desc";
		} else {
			sql = "SELECT * FROM gym_manager WHERE manager_name='" + name + "'";
		}

		try {
			pstmt = mainPage.getCon().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			ResultSetMetaData meta = rs.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String colName = meta.getColumnName(i); // 컬럼명 추출
				columnNames.add(colName);
			}
			ArrayList<TrainerVO> TrainerVOList = new ArrayList<TrainerVO>();
			while (rs.next()) {
				TrainerVO vo = new TrainerVO();
				vo.setOrder_id(rs.getInt("order_id"));
				vo.setManager_name(rs.getString("manager_name"));
				vo.setManager_age(rs.getInt("manager_age"));
				vo.setManager_phone(rs.getString("manager_phone"));
				vo.setManager_mail(rs.getString("manager_mail"));
				vo.setManager_addr(rs.getString("manager_addr"));
				vo.setManager_file(rs.getString("manager_file"));
				file = new File(rs.getString("manager_file"));
				vo.setManager_gender(rs.getString("manager_gender"));

				TrainerVOList.add(vo); // 방금 생성하고 하나의 레코드가 채워진 vo를 ArrayList에 추가하자
			}
			trainerList = new TrainerList();
//         trainerList.column = columnNames; // 컬럼 정보 대입
			trainerList.record = TrainerVOList;

			table.setModel(trainerList);
			table.updateUI();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mainPage.getDbConnection().close(pstmt, rs);
		}
	}

	public void selectTable(int row) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		order_id = (String) table.getValueAt(row, 0);
		String sql = "select * from gym_manager where order_id=" + order_id;

		System.out.println(sql);

		try {
			pstmt = mainPage.getCon().prepareStatement(sql);// 쿼리문 준비
			rs = pstmt.executeQuery(); // 쿼리문 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void getDetail(int row) {

		t_name.setText((String) table.getValueAt(row, 1));
		t_age.setText((String) table.getValueAt(row, 2));
		t_phone.setText((String) table.getValueAt(row, 3));
		t_mail.setText((String) table.getValueAt(row, 4));
		t_address.setText((String) table.getValueAt(row, 5));

	}

	// 입력후 텍스트 필드값 삭제
	public void exitText() {
		t_name.setText("");
		t_age.setText("");
		t_phone.setText("");
		t_mail.setText("");
		t_address.setText("");
		group.clearSelection();// 성별체크

		getTrainerList(null);
	}

	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH);

	}

	public void findImage() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// 파일정보를 구한다.
			file = chooser.getSelectedFile();
			System.out.println("선택한 파일 정보 " + file.getAbsolutePath());
			getTargetImage(file.getAbsolutePath());
		}
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
		} else if (t_address.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "주소을 입력하세요");
			t_address.requestFocus();
			return flag;
		} else if (gender[0].isSelected() == false && gender[1].isSelected() == false) {
			JOptionPane.showMessageDialog(this, "성별을 선택해주세요");
			return flag;
		}
		return flag=true;

	}

	public void preview() {
		// paint로 그림 처리~~
		can.repaint();
	}

}