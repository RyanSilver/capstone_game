import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.*;

class rPanel extends JPanel
{
  public rPanel()
  {
  }
  private static CubbyHole rec = new CubbyHole();
  private static ArrayList<String> items = new ArrayList<String>();
  public static void itemAdd(String s)
  {
    items.add(s);
  }
  public ArrayList<String> messages = new ArrayList<String>();
  public void paint (Graphics g)
  {
    for (int i=0; i<items.size(); i++)
    {
      String sss = items.get(i);
      String [] tok = sss.split(" ");
      if (tok[1].equals("CIRCLE"))
      {
        int x = Integer.parseInt(tok[2]);
        int y = Integer.parseInt(tok[3]);
        Color c = Color.white;
        if (tok[4].equals("RED")) c = Color.red; 
        if (tok[4].equals("BLUE")) c = Color.blue; 
        if (tok[4].equals("WHITE")) c = Color.white; 
        if (tok[4].equals("YELLOW")) c = Color.yellow; 
        if (tok[4].equals("GREEN")) c = Color.green; 
        if (tok[4].equals("GRAY")) c = Color.gray; 
        if (tok[4].equals("CYAN")) c = Color.cyan; 
        if (tok[4].equals("DARKGRAY")) c = Color.darkGray; 
        g.setColor(c);
        g.fillOval(x,y,U.circleSize,U.circleSize); 
        g.setFont(new Font("TimesRoman", Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString(tok[5], x+5, y+20);
        g.drawString(tok[6], x+17, y+40);
        //System.out.println("MAP>>>" + sss);
      }
      else if (tok[1].equals("LINE"))
      {
        int x1 = Integer.parseInt(tok[2]);
        int y1 = Integer.parseInt(tok[3]);
        int x2 = Integer.parseInt(tok[4]);
        int y2 = Integer.parseInt(tok[5]);
        g.setColor(Color.gray);
        Graphics2D g2 = (Graphics2D) g;   
        Stroke oldStroke = g2.getStroke();   
        g2.setStroke(new BasicStroke(2)); 
        g.drawLine(x1, y1, x2, y2);
      }
    }
    g.setFont(new Font("TimesRoman", Font.BOLD, 16));
    if (messages.size() == 8)
      messages.remove(0);
    int start = 475;
    for (int j=0; j<messages.size(); j++)
    {
      g.setColor(Color.white);
      g.fillRect(10,start-17, 300, 23);
      g.setColor(Color.black);
      g.drawString(messages.get(j),10,start);
      start += 20;
    }
  }
} 

public class rMap implements ActionListener
{ 
  private javax.swing.Timer t; 
  private static CubbyHole rec = new CubbyHole();
  private static rPanel jj;  

  public rMap(CubbyHole a){
    rec = a;
    t = new javax.swing.Timer(10, this);   
    t.setRepeats(true); 
    t.start();   
    JFrame f = new JFrame(); 
    f.setVisible(true); 
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    f.setSize(1000, 650);
    f.setLocation(75,50);
    jj = new rPanel();
    f.getContentPane().add(jj);
  } 
  @Override 
  public void actionPerformed(ActionEvent arg0) 
  { 
    String xxx = rec.get();
    String [] tok = xxx.split(" ");
    if (tok[1].equals("MESSAGE"))
    {
      U.pr("rMap.actionPerformed>>>rec'd message for map\n");
      jj.messages.add(xxx.substring(9,xxx.length() -1));
    }
    else
      jj.itemAdd(xxx);
    jj.repaint();
  }
}
