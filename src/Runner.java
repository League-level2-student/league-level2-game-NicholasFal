import javax.swing.JFrame;

public class Runner {
JFrame frame;
	
	GamePanel panel = new GamePanel();
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	Runner() {
		this.frame = new JFrame();
		this.panel = new GamePanel();
		frame.addKeyListener(panel);
	}
	
	public static void main(String[] args) {
		Runner window = new Runner();
		window.setup();
		
	}
	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
