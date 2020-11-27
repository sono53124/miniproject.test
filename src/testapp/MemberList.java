package testapp;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberList extends AbstractTableModel {
	// String[][] data = {};
	// String[] columnName = { "회원번호", "이름", "성별","락커", "나이", "전화번호", "메일", "주소",
	// "등록일", "만기일","파일" };

	ArrayList<MemberVO> record = new ArrayList<MemberVO>();
	ArrayList<String> column = new ArrayList<String>();

	public MemberList() {
		column.add("No.");
		column.add("이름");
		column.add("성별");
		column.add("락커");
		column.add("생년월일");
		column.add("전화번호");
		column.add("메일");
		column.add("주소");
		column.add("등록일");
		column.add("만기일");
		column.add("파일");
		column.add("금액");
	}

	@Override
	public int getColumnCount() {
		return column.size();
	}

	public int getRowCount() {
		return record.size();
	}

	public String getColumnName(int col) {
		return column.get(col);
	}

	public ArrayList<MemberVO> getRecord() {
		return record;
	}

	// "회원번호", "이름", "나이", "성별","락커 "전화번호", "메일", "주소", "등록일", "만기일"
	public Object getValueAt(int row, int col) {
		MemberVO vo = record.get(row);
		String obj = null;
		if (col == 0) {
			obj = Integer.toString(vo.getM_id());
		} else if (col == 1) {//
			obj = vo.getM_name();
		} else if (col == 2) {// locker
			obj = vo.getM_gender();
		} else if (col == 3) {// 나이
			obj = Integer.toString(vo.getM_locker());
		} else if (col == 4) {// 성별
			obj = Integer.toString(vo.getM_age());
		} else if (col == 5) {// 전화번호
			obj = vo.getM_phone();
		} else if (col == 6) {// 이메일
			obj = vo.getM_mail();
		} else if (col == 7) {// 주소
			obj = vo.getM_addr();
		} else if (col == 8) {// 등록일
			obj = vo.getM_regdate();
		} else if (col == 9) {// 만기일
			obj = vo.getM_expdate();
		} else if (col == 10) {
			obj = vo.getM_file();
		} else if (col == 11) {
			obj = Integer.toString(vo.getM_pay());
		}
		return obj;
	}
}