package test;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class DatePicker extends JFrame {
	JPanel p;
	JButton bt;

	public DatePicker() {
		p = new JPanel();
		bt = new JButton("버튼");

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

		p.add(datePicker);
		p.add(datePicker2);
		p.add(bt);
		add(p);

		// String date=model.getYear() + "-" + (model.getMonth() + 1) + "-" +
		// model.getDay();

		bt.addActionListener((e) -> {
			Date date = new Date(2020);
			System.out.println(date);
			model2.setValue(date);
		});

		setBounds(300, 300, 400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new DatePicker();
	}
}
