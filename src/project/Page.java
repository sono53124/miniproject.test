package project;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Page extends JPanel{
	Image img;
	Toolkit kit;
	JFileChooser chooser = new JFileChooser("D:/workspace/java/SeProject/res/travel2");
	File file;
	URL url;
	public Page() {
		
	}
	public Image getTargetImage(URL url) {
		img = kit.getImage(url);
		img=img.getScaledInstance(330,280, Image.SCALE_SMOOTH);
		// img = ImageUtil.getCustomSize(img, 135, 115);
		
		return img;
	}

	public void findImage() {
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// 파일정보를 구한다.
			file = chooser.getSelectedFile();
			try {
				url = new URL(file.toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			String path=file.getAbsolutePath();
			System.out.println("선택한 파일 정보 : " + file.getAbsolutePath());
			getTargetImage(url);
		}
	}

	public void preview(JPanel can) {
		// paint로 그림 처리~~
		if(img !=null) {
			can.repaint();
		}
	}
	
}
