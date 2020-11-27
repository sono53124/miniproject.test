package testapp;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class TrainerList extends AbstractTableModel {
   //String[] column = { "No.","이름", "나이", "성별", "전화번호", "메일", "주소","이미지" };
  // String[][] data = {};
   
   ArrayList<TrainerVO> record = new ArrayList<TrainerVO>();

   // 컬럼정보를 위한 ArrayList 선언
   ArrayList<String> column = new ArrayList<String>();
   
   public TrainerList() {
      column.add("No.");
      column.add("이름");
      column.add("생년월일");
      column.add("전화번호");
      column.add("메일");
      column.add("주소");
      column.add("이미지");
      column.add("성별");
   }
   
   public int getRowCount() {
      return record.size();
   }

   public int getColumnCount() {
      return column.size();
   }

   public String getColumnName(int col) {
      return column.get(col);
   }

   public Object getValueAt(int row, int col) {
      TrainerVO vo = record.get(row);
      String obj = null;
      if (col == 0) {
         obj = Integer.toString(vo.getOrder_id());
      }else if(col ==1){
         obj = vo.getManager_name();
      }else if(col ==2){
         obj = Integer.toString(vo.getManager_age());
      }else if(col ==3){
         obj = vo.getManager_phone();
      }else if(col ==4){
         obj = vo.getManager_mail();
      }else if(col ==5){
         obj = vo.getManager_addr();
      }else if(col ==6){
         obj = vo.getManager_file();
      }else if(col ==7){
         obj = vo.getManager_gender();
      }
         
      return obj;
   }


}