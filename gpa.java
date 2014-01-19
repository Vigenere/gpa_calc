import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Enumeration;

public class gpa extends JFrame{
	public Stack<Integer[]> grades = new Stack<Integer[]>(); //Entries should be of the form {grade, units}
	public double[] weights = new double[13];
	public JPanel all, top, bottom, right, right_top, right_bottom, left, left_top, left_middle, left_bottom; //for general structure
	public JPanel jp0, jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12; //for right column
	public JPanel grade_jp1, grade_jp2, grade_jp3, grade_jp4; //for radio buttons
	public JPanel units_jp, buttons_jp, graph_jp;
	public JButton add, undo, reset, update;
	public JRadioButton rb0, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12;
	public JLabel gpa_l, add_class, grade_l, units, grade_dis, weights_l;
	public JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, unit_entry;
	public ButtonGroup rb_bg;
	public GraphDrawer gradedis_graph;

	public gpa(int width, int height) {
		setDefaultWeights();
		addJPanels();
		addComponents();

		Container content = getContentPane();
		content.add(all);
		setContentPane(content);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setVisible(true);
	}

	public class UpdateWeightsHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateWeights();
		}
	}

	public class AddClassHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			addClass();
		}
	}

	public class UndoClassHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			undoClass();
		}
	}

	public class ResetClassHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			resetClass();
		}
	}

	public class GraphDrawer extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(50, 0, 100, 100);
		}
	}

	//----------------Structure----------------
	/*Sets default weights according to UC Berkeley's grading scale
	weights[0] = 0.0 (F)
	weights[1] = 0.7 (D-)
	...
	weights[10] = 3.7 (A-)
	weights[11] = 4.0 (A)
	weights[12] = 4.0 (A+)
	*/
	public void setDefaultWeights() {
		weights[0] = 0.0; //F
		weights[1] = 0.7; //D-
		weights[2] = 1.0; //D
		weights[3] = 1.3; //D+
		weights[4] = 1.7; //C-
		weights[5] = 2.0; //C
		weights[6] = 2.3; //C+
		weights[7] = 2.7; //B-
		weights[8] = 3.0; //B
		weights[9] = 3.3; //B+
		weights[10] = 3.7; //A-
		weights[11] = 4.0; //A
		weights[12] = 4.0; //A
	}

	private void addJPanels() {
		all = new JPanel();
		bottom = new JPanel();
		top = new JPanel();
		right = new JPanel();
		right_top = new JPanel();
		right_bottom = new JPanel();
		left = new JPanel();
		left_top = new JPanel();
		left_middle = new JPanel();
		left_bottom = new JPanel();

		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right_top.setLayout(new BoxLayout(right_top, BoxLayout.Y_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left_middle.setLayout(new BoxLayout(left_middle, BoxLayout.Y_AXIS));
		left_bottom.setLayout(new BoxLayout(left_bottom, BoxLayout.Y_AXIS));

		all.setPreferredSize(new Dimension(600, 800));
		top.setPreferredSize(new Dimension(600, 50));
		bottom.setPreferredSize(new Dimension(600, 750));
		right.setPreferredSize(new Dimension(200,750));
		left.setPreferredSize(new Dimension(400, 750));
		left_top.setPreferredSize(new Dimension(400,50));
		left_middle.setPreferredSize(new Dimension(400,400));
		left_bottom.setPreferredSize(new Dimension(400, 350));
		right_top.setPreferredSize(new Dimension(200, 700));
		right_bottom.setPreferredSize(new Dimension(200, 50));

		right.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		/*
		right_top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		right_bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left_top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left_middle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		*/left_bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		

		all.add(top);
		all.add(bottom);
		bottom.add(left);
		bottom.add(right);
		right.add(right_top);
		right.add(right_bottom);
		left.add(left_top);
		left.add(left_middle);
		left.add(left_bottom);
	}

	private void addComponents() {
		add = new JButton("Add");
		undo = new JButton("Undo");
		reset = new JButton("Reset");
		update = new JButton("Update");
		rb0 = new JRadioButton("F");
		rb1 = new JRadioButton("D-");
		rb2 = new JRadioButton("D");
		rb3 = new JRadioButton("D+");
		rb4 = new JRadioButton("C-");
		rb5 = new JRadioButton("C");
		rb6 = new JRadioButton("C+");
		rb7 = new JRadioButton("B-");
		rb8 = new JRadioButton("B");
		rb9 = new JRadioButton("B+");
		rb10 = new JRadioButton("A-");
		rb11 = new JRadioButton("A");
		rb12 = new JRadioButton("A+");
		gpa_l = new JLabel("GPA:");
		add_class = new JLabel("Add Class");
		grade_l = new JLabel("Grade");
		units = new JLabel("Units");
		grade_dis = new JLabel("Grade Distribution");
		weights_l = new JLabel("Weights");
		tf0 = new JTextField("0.0");
		tf1 = new JTextField("0.7");
		tf2 = new JTextField("1");
		tf3 = new JTextField("1.3");
		tf4 = new JTextField("1.7");
		tf5 = new JTextField("2");
		tf6 = new JTextField("2.3");
		tf7 = new JTextField("2.7");
		tf8 = new JTextField("3");
		tf9 = new JTextField("3.3");
		tf10 = new JTextField("3.7");
		tf11 = new JTextField("4");
		tf12 = new JTextField("4");
		unit_entry = new JTextField();

		add.addActionListener(new AddClassHandler());
		undo.addActionListener(new UndoClassHandler());
		reset.addActionListener(new ResetClassHandler());
		update.addActionListener(new UpdateWeightsHandler());

		top.add(gpa_l);

		right_top.add(weights_l);
		addRightComponents();
		addLeftComponents();
	}

	private void addRightComponents() {
		jp12 = new JPanel();
		jp12.setLayout(new BoxLayout(jp12, BoxLayout.X_AXIS));
		jp12.add(new JLabel("A+ "));
		jp12.add(tf12);
		right_top.add(jp12);
		jp11 = new JPanel();
		jp11.setLayout(new BoxLayout(jp11, BoxLayout.X_AXIS));
		jp11.add(new JLabel("A  "));
		jp11.add(tf11);
		right_top.add(jp11);
		jp10 = new JPanel();
		jp10.setLayout(new BoxLayout(jp10, BoxLayout.X_AXIS));
		jp10.add(new JLabel("A- "));
		jp10.add(tf10);
		right_top.add(jp10);
		jp9 = new JPanel();
		jp9.setLayout(new BoxLayout(jp9, BoxLayout.X_AXIS));
		jp9.add(new JLabel("B+ "));
		jp9.add(tf9);
		right_top.add(jp9);
		jp8 = new JPanel();
		jp8.setLayout(new BoxLayout(jp8, BoxLayout.X_AXIS));
		jp8.add(new JLabel("B  "));
		jp8.add(tf8);
		right_top.add(jp8);
		jp7 = new JPanel();
		jp7.setLayout(new BoxLayout(jp7, BoxLayout.X_AXIS));
		jp7.add(new JLabel("B- "));
		jp7.add(tf7);
		right_top.add(jp7);
		jp6 = new JPanel();
		jp6.setLayout(new BoxLayout(jp6, BoxLayout.X_AXIS));
		jp6.add(new JLabel("C+ "));
		jp6.add(tf6);
		right_top.add(jp6);
		jp5 = new JPanel();
		jp5.setLayout(new BoxLayout(jp5, BoxLayout.X_AXIS));
		jp5.add(new JLabel("C "));
		jp5.add(tf5);
		right_top.add(jp5);
		jp4 = new JPanel();
		jp4.setLayout(new BoxLayout(jp4, BoxLayout.X_AXIS));
		jp4.add(new JLabel("C- "));
		jp4.add(tf4);
		right_top.add(jp4);
		jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.X_AXIS));
		jp3.add(new JLabel("D+ "));
		jp3.add(tf3);
		right_top.add(jp3);
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
		jp2.add(new JLabel("D  "));
		jp2.add(tf2);
		right_top.add(jp2);
		jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		jp1.add(new JLabel("D- "));
		jp1.add(tf1);
		right_top.add(jp1);
		jp0 = new JPanel();
		jp0.setLayout(new BoxLayout(jp0, BoxLayout.X_AXIS));
		jp0.add(new JLabel("F  "));
		jp0.add(tf0);
		right_top.add(jp0);

		right_bottom.add(update);
	}

	private void addLeftComponents() {
		left_top.add(add_class);
		left_middle.add(grade_l);

		rb_bg = new ButtonGroup();
		rb_bg.add(rb12);
		rb_bg.add(rb11);
		rb_bg.add(rb10);
		rb_bg.add(rb9);
		rb_bg.add(rb8);
		rb_bg.add(rb7);
		rb_bg.add(rb6);
		rb_bg.add(rb5);
		rb_bg.add(rb4);
		rb_bg.add(rb3);
		rb_bg.add(rb2);
		rb_bg.add(rb1);
		rb_bg.add(rb0);

		grade_jp1 = new JPanel();
		grade_jp1.setLayout(new BoxLayout(grade_jp1, BoxLayout.X_AXIS));
		grade_jp2 = new JPanel();
		grade_jp2.setLayout(new BoxLayout(grade_jp2, BoxLayout.X_AXIS));
		grade_jp3 = new JPanel();
		grade_jp3.setLayout(new BoxLayout(grade_jp3, BoxLayout.X_AXIS));
		grade_jp4 = new JPanel();
		grade_jp4.setLayout(new BoxLayout(grade_jp4, BoxLayout.X_AXIS));
		grade_jp1.add(rb12);
		grade_jp1.add(rb11);
		grade_jp1.add(rb10);
		grade_jp2.add(rb9);
		grade_jp2.add(rb8);
		grade_jp2.add(rb7);
		grade_jp3.add(rb6);	
		grade_jp3.add(rb5);		
		grade_jp3.add(rb4);		
		grade_jp4.add(rb3);	
		grade_jp4.add(rb2);		
		grade_jp4.add(rb1);	
		grade_jp4.add(rb0);

		left_middle.add(grade_jp1);
		left_middle.add(grade_jp2);
		left_middle.add(grade_jp3);
		left_middle.add(grade_jp4);

		units_jp = new JPanel();
		units_jp.setLayout(new BoxLayout(units_jp, BoxLayout.X_AXIS));
		units_jp.add(units);
		units_jp.add(unit_entry);
		left_middle.add(units_jp);

		buttons_jp = new JPanel();
		buttons_jp.setLayout(new BoxLayout(buttons_jp, BoxLayout.X_AXIS));
		buttons_jp.add(add);
		buttons_jp.add(undo);
		buttons_jp.add(reset);
		left_middle.add(buttons_jp);

		gradedis_graph = new GraphDrawer();
		left_bottom.add(grade_dis);
		left_bottom.add(gradedis_graph);
	}

	//----------------Actions----------------

	private void addClass() {
		int grade = 0;
		int units = 0;
		boolean selected = false;
		Enumeration<AbstractButton> jrb_enum = rb_bg.getElements();
		while (jrb_enum.hasMoreElements()) {
			AbstractButton jrb = jrb_enum.nextElement();

			if (jrb.isSelected()) {
				switch (jrb.getText()) {
					case "A+":
						grade = 12;
						break;
					case "A":
						grade = 11;
						break;
					case "A-":
						grade = 10;
						break;
					case "B+":
						grade = 9;
						break;
					case "B":
						grade = 8;
						break;
					case "B-":
						grade = 7;
						break;
					case "C+":
						grade = 6;
						break;
					case "C":
						grade = 5;
						break;
					case "C-":
						grade = 4;
						break;
					case "D+":
						grade = 3;
						break;
					case "D":
						grade = 2;
						break;
					case "D-":
						grade = 1;
						break;
					case "F":
						grade = 0;
						break;
				}

				selected = true;
			}
		}
		if (!selected) {
			JOptionPane.showMessageDialog(null, "No grade selected", "Error", JOptionPane.ERROR_MESSAGE);
			return;				
		}
		try {
			units = Integer.parseInt(unit_entry.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Units must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Integer[] grade_unit = {grade, units};
		grades.push(grade_unit);
		updateGrades();
	}

	private void undoClass() {
		grades.pop();
		updateGrades();
	}

	private void resetClass() {
		grades = new Stack<Integer[]>();
		updateGrades();
	}

	private void reGraph() {

	}

	private void updateWeights() {
		try {
			weights[0] = Double.parseDouble(tf0.getText());
			weights[1] = Double.parseDouble(tf1.getText());
			weights[2] = Double.parseDouble(tf2.getText());
			weights[3] = Double.parseDouble(tf3.getText());
			weights[4] = Double.parseDouble(tf4.getText());
			weights[5] = Double.parseDouble(tf5.getText());
			weights[6] = Double.parseDouble(tf6.getText());
			weights[7] = Double.parseDouble(tf7.getText());
			weights[8] = Double.parseDouble(tf8.getText());
			weights[9] = Double.parseDouble(tf9.getText());
			weights[10] = Double.parseDouble(tf10.getText());
			weights[11] = Double.parseDouble(tf11.getText());
			weights[12] = Double.parseDouble(tf12.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateGrades() {
		double gpa_calc = computeGPA();
		gpa_l.setText("GPA: " + gpa_calc);
		reGraph();
	}

	/*
	@return	computed GPA from given grades and weights
	*/
	public double computeGPA() {
		double sum = 0;
		double units = 0;
		for (int n = 0; n < grades.size(); n++) {
			Integer[] grade = grades.elementAt(n);
			sum += weights[grade[0]] * grade[1];
			units += (double)grade[1];
		}
		return sum/units;
	}

	public static void main(String[] args) {
		gpa win = new gpa(600,800);
	}
}