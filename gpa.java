import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Stack;

public class gpa extends JFrame{
	public static Stack<Integer[]> grades = new Stack<Integer[]>(); //Entries should be of the form {grade, units}
	public static double[] weights = new double[13];
	public static JPanel all, top, bottom, right, right_top, right_bottom, left, left_top, left_middle, left_bottom;
	public static JButton add, undo, reset, update;
	public static JRadioButton rb0, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12;
	public static JLabel gpa, add_class, grade, units, grade_dis;
	public static JTextField tf0, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, unit_entry;

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

	public class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
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

	public static void main(String[] args) {
		gpa win = new gpa(600,800);
	}

	/*Sets default weights according to UC Berkeley's grading scale
	weights[0] = 0.0 (F)
	weights[1] = 0.7 (D-)
	...
	weights[10] = 3.7 (A-)
	weights[11] = 4.0 (A)
	weights[12] = 4.0 (A+)
	*/
	public static void setDefaultWeights() {
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

	public static void addJPanels() {
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
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

		all.setPreferredSize(new Dimension(600, 800));
		top.setPreferredSize(new Dimension(600, 50));
		bottom.setPreferredSize(new Dimension(600, 750));
		right.setPreferredSize(new Dimension(200,750));
		left.setPreferredSize(new Dimension(400, 750));
		left_top.setPreferredSize(new Dimension(400,100));
		left_middle.setPreferredSize(new Dimension(400,400));
		left_bottom.setPreferredSize(new Dimension(400, 250));
		right_top.setPreferredSize(new Dimension(200, 700));
		right_bottom.setPreferredSize(new Dimension(200, 50));

		right.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		/*
		right_top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		right_bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left_top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left_middle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		left_bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		*/

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

	public static void addComponents() {
		add = new JButton("Add");
		undo = new JButton("Undo");
		reset = new JButton("Reset");
		update = new JButton("Update");
		rb0 = new JRadioButton();
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		rb4 = new JRadioButton();
		rb5 = new JRadioButton();
		rb6 = new JRadioButton();
		rb7 = new JRadioButton();
		rb8 = new JRadioButton();
		rb9 = new JRadioButton();
		rb10 = new JRadioButton();
		rb11 = new JRadioButton();
		rb12 = new JRadioButton();
		gpa = new JLabel("GPA");
		add_class = new JLabel("Add Class");
		grade = new JLabel("Grade");
		units = new JLabel("Units");
		grade_dis = new JLabel("Grade Distribution");
		tf0 = new JTextField();
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		tf7 = new JTextField();
		tf8 = new JTextField();
		tf9 = new JTextField();
		tf10 = new JTextField();
		tf11 = new JTextField();
		tf12 = new JTextField();
		unit_entry = new JTextField();
	}

	private void addClass() {

	}

	private void undoClass() {

	}

	private void resetClass() {

	}

	private void reGraph() {

	}

	private void updateWeights() {

	}

	/*
	@return	computed GPA from given grades and weights
	*/
	public double computeGPA() {
		double sum = 0;
		double units = 0;
		for (int n = 0; n < grades.size(); n++) {
			Integer[] grade = grades.elementAt(n);
			sum += weights[grade[0]];
			units += (double)grade[1];
		}
		return sum/units;
	}
}