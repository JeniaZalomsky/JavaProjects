import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import com.sun.javafx.scene.paint.GradientUtils.Point;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
public class pongGame extends JComponent implements ActionListener,MouseMotionListener{
	
	//ball position
	private int ballX=30;
	private int ballY=330;
	
	private int paddleX=0;
	//ball direction+speed
	private int ballYmove=4;
	private int ballXmove=5;
	 JLabel labelGameOver=new JLabel();

	public static void main(String args[]) {
		pongGame game=new pongGame();  
		JFrame pong1=new JFrame("pong game by jenia");  
		pong1.setPreferredSize(getPrefferedSize());
		pong1.add(game);
		//pong1.add(labelGameOver,BorderLayout.NORTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		pong1.setLocation(dim.width/2-pong1.getSize().width/2, dim.height/2-pong1.getSize().height/2);
		pong1.setVisible(true);
		pong1.addMouseMotionListener(game);
		pong1.setDefaultCloseOperation(pong1.EXIT_ON_CLOSE);
		pong1.pack();
		Timer t=new Timer(15, game);
		t.start();	
	}
	
	public static Dimension getPrefferedSize(){
		return new Dimension(800, 600);
	}

	
	//the paing component every 10 millisec paint all the comp
	@Override
	protected void paintComponent(Graphics g){
		g.setColor(new Color(178,223,224));
		g.fillRect(0, 0, 800, 600);
	
		//paddle
		g.setColor(new Color(32, 107, 47));
		g.fillRect(paddleX-150/2, 510, 150, 15);  //data: x.pos   y.pos   width   length   
	//	the paddle moving on x axis according parameter paddleX
		
		//ball
		g.setColor(new Color(239, 197, 69));
		g.fillOval(ballX, ballY, 25, 25);//data: x.pos   y.pos   width   length   
//		the ball moving on x  y axis according to ballX ballY
		
		//the timer is set (every 10 millisec turning on game, game turning on actionPerformed and according to timer moving ballX and BallY,with spesific orders (if ball comes to wall...)
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ballX=ballX+ballXmove ;
		ballY=ballY+ballYmove;
		if(ballY>=485&&ballX<(paddleX+150/2)&&ballX>paddleX-150/2)
			ballYmove=-4;

		if(ballX>=800-30)
			ballXmove=-5;
		
		if(ballY<=0)
			ballYmove=4;
		
		if(ballX<=0)
			ballXmove=5;
		
		if(ballY>=540){
		
			labelGameOver.setText("Game over");
		}
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) { 	
		paddleX=e.getX();
		repaint();
	}

}
