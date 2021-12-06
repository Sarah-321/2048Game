
import javax.swing.JFrame;
public class Driver2048
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("2048");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GameBoard2048());
      frame.setVisible(true);
   }
}