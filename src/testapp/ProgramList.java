package testapp;

import javax.swing.table.AbstractTableModel;

public class ProgramList extends AbstractTableModel {
   String[][] data = {};
   String[] column = { "프로그램번호","트레이너","프로그램명", "요일", "시작일", "종료일","시간", "금액","최대인원" ,"이미지"};

   @Override
   public int getColumnCount() {
      return column.length;
   }

   public int getRowCount() {
      return data.length;
   }

   public String getColumnName(int col) {
      return column[col];
   }

   public Object getValueAt(int row, int col) {
      return data[row][col];
   }

}