package testapp;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MailApp extends JFrame{
	final String user = "sono53124@gmail.com"; // 발신자의 이메일 아이디를 입력
	final String password = "qlql5857"; // 발신자 이메일의 패스워드를 입력
	Session session;
	Properties prop;
	MimeMessage message;
	
	JPanel panel;
	JTextField title;
	JLabel la_title;
	JTextField to;
	JLabel la_to;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_send,bt;
	
	public MailApp(String m) {
		panel=new JPanel();
		la_title = new JLabel("메일 제목");
		title = new JTextField("제목을 입력하세요");
		la_to = new JLabel("받는 사람");
		to = new JTextField(m);
		area =new JTextArea();
		scroll = new JScrollPane(area);
		bt_send = new JButton("전송");
		bt = new JButton("취소");
		
		panel.setPreferredSize(new Dimension(300,400));
		la_title.setPreferredSize(new Dimension(80,30));
		title.setPreferredSize(new Dimension(180,30));
		la_to.setPreferredSize(new Dimension(80,30));
		to.setPreferredSize(new Dimension(180,30));
		area.setPreferredSize(new Dimension(270,270));
		
		
		panel.add(la_title);
		panel.add(title);
		panel.add(la_to);
		panel.add(to);
		panel.add(scroll);
		panel.add(bt_send);
		panel.add(bt);
		add(panel);
		
		
		title.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				title.setText("");
			}
		});
		
		bt_send.addActionListener((e)->{
			send();
			setVisible(false);
		});
		bt.addActionListener((e)->{
			setVisible(false);
		});
		
		setVisible(true);
		setSize(300,430);
		setLocationRelativeTo(null);
		
	}
	
	public void send() {
		prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.getText()));

			// Subject
			message.setSubject(title.getText()); // 메일 제목을 입력

			// Text
			message.setText(area.getText()); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
			JOptionPane.showMessageDialog(this, "전송 완료");
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(this, "전송실패");
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(this, "전송실패");
			e.printStackTrace();
		}
	}
}