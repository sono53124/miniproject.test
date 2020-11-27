package testapp;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Page extends JPanel {
	Image img;
	Toolkit kit;
	JFileChooser chooser = new JFileChooser("D:/workspace/java/SeProject/res/travel2");
	File file;
	URL url;
	JPanel can;
	
	public Page() {
		
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
// paint로 그림 처리~~
		if (img != null) {
			can.repaint();
		}
	}

}
