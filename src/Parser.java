import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Parser {
	String regex = "";
	String line = "";

	public static class ParserFrame {
		JTextField regex = new JTextField();
		JTextField text = new JTextField();
		JButton tnmb = new JButton("Try and Match");
		JLabel result = new JLabel();
		Parser parser;

		public ParserFrame(Parser p) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
			// initializing values
			this.parser = p;
			regex.setText(p.regex);
			text.setText(p.line);

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame f = new JFrame("Java Regex Matcher Frame");

			// setting it to center
			Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
			int windowWidth = 400;
			int windowHeight = 150;
			f.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth, windowHeight);

			Container content = f.getContentPane();
			content.setLayout(new BorderLayout());

			JPanel p1 = new JPanel();
			p1.setLayout(new GridLayout(3, 2));
			p1.add(new JLabel("Regex : "));
			p1.add(regex);
			p1.add(new JLabel("input text : "));
			p1.add(text);
			p1.add(tnmb);
			p1.add(result);

			content.add(new JLabel("Reg EX evaluation window"), BorderLayout.NORTH);
			content.add(p1, BorderLayout.CENTER);

			tnmb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parser.regex = regex.getText();
					parser.line = text.getText();
					try {
						result.setText(parser.doesMatch());
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

			f.setVisible(true);
		}
	}

	public String doesMatch() throws UnsupportedEncodingException {
		Pattern refererpattern = Pattern.compile(regex);
		Matcher referermatcher = refererpattern.matcher(line);
		String val = "";
		if (!referermatcher.matches()) {
			// System.out.println("Failed Matching");
			val = "does not match";
		} else {
			// match successful try group method to find group
			val = referermatcher.group();
			for(int i=0; i<=referermatcher.groupCount(); i++)
			System.out.println(URLDecoder.decode(referermatcher.group(i), "UTF-8"));
			
		}
		return val;
	}

	public static void main(String a[]) {
		Parser p = new Parser();
		try {
			// creating a new parser frame
			new ParserFrame(p);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
