
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;

class MyPanel extends JPanel {
	private double flag = -1, x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3, y3, x4, y4;
	private String imgdz = "1.png";
	public String getImgdz() {
		return imgdz;
	}
	public void setImgdz(String imgdz) {
		this.imgdz = imgdz;
	}
	public double getFlag() {
		return flag;
	}
	public void setFlag(double flag) {
		this.flag = flag;
	}
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	public double getX3() {
		return x3;
	}

	public void setX3(double x3) {
		this.x3 = x3;
	}

	public double getY3() {
		return y3;
	}

	public void setY3(double y3) {
		this.y3 = y3;
	}
	public double getX4() {
		return x4;
	}

	public void setX4(double x4) {
		this.x4 = x4;
	}

	public double getY4() {
		return y4;
	}

	public void setY4(double y4) {
		this.y4 = y4;
	}

	public void paint(Graphics g1) {

		Toolkit tool = Toolkit.getDefaultToolkit();

		Graphics2D g = (Graphics2D) g1;
		super.paint(g);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(1));
		Image img = tool.getImage(this.getClass().getResource("image/"+imgdz));
		ImageIcon imageIcon = new ImageIcon(img);
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		bufferedImage = g2.getDeviceConfiguration().createCompatibleImage(imageIcon.getIconWidth(),
				imageIcon.getIconHeight(), Transparency.TRANSLUCENT);
		g2.dispose();
		g2 = bufferedImage.createGraphics();
		g.drawImage(imageIcon.getImage(), 35, 35, 280, 280, this);
		


		if (flag == 1) {
			g.setColor(Color.PINK);
			g.setStroke(new BasicStroke(7));
			g.setColor(Color.gray);
			g.draw(new Line2D.Double(x1, y1, x4, y4));
			g.setStroke(new BasicStroke(4));
			g.setColor(Color.yellow);
			g.draw(new Line2D.Double(x1, y1, x3, y3));
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.red);
			g.draw(new Line2D.Double(x1, y1, x2, y2));
			g.setColor(Color.green);
			g.setStroke(new BasicStroke(5));
			g.fillOval(170, 170, 10, 10);

		}
	}
}

public class Clock {

	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clock window = new Clock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Clock() {
		initialize();
	}
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		int frameh = 350, framew = 480, framey = (int) (scr.getHeight() / 2 - frameh / 2),
				framex = (int) (scr.getWidth() / 2 - framew / 2);

		frame = new JFrame("精美钟表");
		frame.setType(Type.UTILITY);
		frame.setBounds(framex, framey, frameh, framew);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel beijing1 = new JPanel();
		beijing1.setLayout(null);
		ImageIcon bj = new ImageIcon(this.getClass().getResource("/bj.png"));
		JLabel bjt = new JLabel();
		bjt.setBounds(0, 0, 350, 500);
		beijing1.add(bjt);
		bjt.setIcon(bj);
		beijing1.setBounds(0, 0, framew, frameh+100);

		JLayeredPane fenceng = new JLayeredPane();

		MyPanel panel = new MyPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, framew, frameh);
		panel.setOpaque(false);

		fenceng.add(panel, new Integer(2));
		fenceng.add(beijing1, new Integer(1));

		frame.getContentPane().add(fenceng);

		JLabel label = new JLabel();
		label.setBounds(80, 270, 300, 200);
		panel.add(label);
		panel.setBackground(Color.white);
		Font f1 = new Font("幼圆", 1, 25);
		label.setFont(f1);

		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);

		JMenu shezhi = new JMenu("设置");
		bar.add(shezhi);
		JMenuItem beijing = new JMenuItem("选择表盘");
		shezhi.add(beijing);
		beijing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "复古", "卡通", "朴素" };
				int response = JOptionPane.showOptionDialog(frame, "选择需要的表盘", "表盘选择", JOptionPane.YES_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if (response == 0) {
					panel.setImgdz("1.png");
				} else if (response == 1) {
					panel.setImgdz("2.png");
				} else if (response == 2) {
					panel.setImgdz("3.png");
				}
			}
		});

		JMenu bangzhu = new JMenu("帮助");
		bar.add(bangzhu);

		JMenuItem guanyu = new JMenuItem("关于");
		bangzhu.add(guanyu);
		guanyu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "软件名：精美时钟\n制作时间：2017年4月28日\n制作人：muyangren907", "版权信息",
						JOptionPane.PLAIN_MESSAGE);
			}
		});

		Timer timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar Cld = Calendar.getInstance();

				panel.setFlag(1);
				panel.setX1(175);
				panel.setY1(175);
				int s = Cld.get(Calendar.SECOND), ms = Cld.get(Calendar.MILLISECOND);

				double x2, y2;
				x2 = (175 + 80 * Math.sin((s + ms / 1000.0) * 2 * Math.PI / 60.0));
				y2 = (175 - 80 * Math.cos((s + ms / 1000.0) * 2 * Math.PI / 60.0));
				panel.setX2(x2);
				panel.setY2(y2);

				double x3, y3;
				int m = Cld.get(Calendar.MINUTE);
				x3 = (175 + 60 * Math.sin((m + s / 60.0 + ms / 60000.0) * 2 * Math.PI / 60.0));
				y3 = (175 - 60 * Math.cos((m + s / 60.0 + ms / 60000.0) * 2 * Math.PI / 60.0));
				panel.setX3(x3);
				panel.setY3(y3);

				double x4, y4;
				int h = Cld.get(Calendar.HOUR);
				x4 = (175 + 40 * Math.sin((h + m / 60.0 + s / 3600.0 + ms / 60 / 60000.0) * 2 * Math.PI / 12.0));
				y4 = (175 - 40 * Math.cos((h + m / 60.0 + s / 3600.0 + ms / 60 / 60000.0) * 2 * Math.PI / 12.0));
				panel.setX4(x4);
				panel.setY4(y4);

				int y = Cld.get(Calendar.YEAR), mo = Cld.get(Calendar.MONTH) + 1, d = Cld.get(Calendar.DAY_OF_MONTH),
						week = Cld.get(Calendar.DAY_OF_WEEK);

				panel.repaint();

			}
		});
		timer.start();

	}

}
