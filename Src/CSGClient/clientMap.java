import java.io.*;
import javafx.stage.*;
import javafx.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.lang.Thread;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

public class clientMap extends Application{
  private class terr
  {
    public Circle rep;
    public Text nameRep;
    public Text countRep;
    public String name;
    public String color;
    public int count;
    public double x;
    public double y;
    public terr(String name, String color, int count, double x, double y)
    {
      this.name = name;
      this.color = color;
      this.count = count;
      this.x=x;
      this.y=y;
    }
    public String toString()
    {
      return "( terr: name=" + name + ", color=" + color + ", count=" + count + " )";
    }
  }

  private static terr[] terrs;
  public void populateMap(){
    terrs=new terr[43];
    //north america
    terrs[0]=new terr("ALA","GREY",0,20,30);
    terrs[1]=new terr("NWT","GREY",0,80,30);
    terrs[2]=new terr("ALB","GREY",0,80,90);
    terrs[3]=new terr("ONT","GREY",0,140,90);
    terrs[4]=new terr("QUE","GREY",0,200,90);
    terrs[5]=new terr("WUS","GREY",0,80,150);
    terrs[6]=new terr("EUS","GREY",0,140,150);
    terrs[7]=new terr("CAM","GREY",0,80,210);
    terrs[8]=new terr("GRE","GREY",0,260,30);

    //south america
    terrs[9]=new terr("VEN","GREY",0,140,320);
    terrs[10]=new terr("PER","GREY",0,140,380);
    terrs[11]=new terr("BRA","GREY",0,200,380);
    terrs[12]=new terr("ARG","GREY",0,140,440);

    //Africa
    terrs[20]=new terr("NAF","GREY",0,380,380);
    terrs[21]=new terr("CAF","GREY",0,380,440);
    terrs[22]=new terr("SAF","GREY",0,440,500);
    terrs[23]=new terr("EGY","GREY",0,440,380);
    terrs[24]=new terr("EAF","GREY",0,440,440);
    terrs[25]=new terr("MAD","GREY",0,500,500);

    //europe
    terrs[13]=new terr("ICE","GREY",0,380,30);
    terrs[14]=new terr("GBR","GREY",0,440,90);
    terrs[15]=new terr("SCA","GREY",0,500,30);
    terrs[16]=new terr("NEU","GREY",0,500,150);
    terrs[17]=new terr("WEU","GREY",0,440,210);
    terrs[18]=new terr("SEU","GREY",0,500,210);
    terrs[19]=new terr("UKR","GREY",0,560,150);

    //asia
    terrs[26]=new terr("URA","GREY",0,620,90);
    terrs[27]=new terr("MEA","GREY",0,560,270);
    terrs[28]=new terr("AFG","GREY",0,620,170);
    terrs[29]=new terr("SIB","GREY",0,680,90);
    terrs[30]=new terr("YAK","GREY",0,740,30);
    terrs[31]=new terr("IRK","GREY",0,740,90);
    terrs[32]=new terr("MON","GREY",0,740,150);
    terrs[33]=new terr("CHI","GREY",0,740,210);
    terrs[34]=new terr("IND","GREY",0,620,330);
    terrs[35]=new terr("SIA","GREY",0,740,210);
    terrs[36]=new terr("KAM","GREY",0,800,30);
    terrs[37]=new terr("JAP","GREY",0,800,210);

    //Australlia
    terrs[38]=new terr("INE","GREY",0,740,380);
    terrs[39]=new terr("NGU","GREY",0,800,380);
    terrs[40]=new terr("WAU","GREY",0,740,440);
    terrs[41]=new terr("EAU","GREY",0,800,440);
    terrs[42]=new terr("NZE","GREY",0,860,500);

  }
  public void start(Stage primaryStage) {
     Pane root = new Pane();
     String title = "Client UI";
     populateMap();
     for ( int i=0 ; i<terrs.length ; i ++ ) {
       terrs[i].rep=new Circle(terrs[i].x,terrs[i].y,25,Paint.valueOf(terrs[i].color));
       root.getChildren().add(terrs[i].rep);
       terrs[i].nameRep=new Text(terrs[i].x-10,terrs[i].y-10,terrs[i].name);
       root.getChildren().add(terrs[i].nameRep);
       terrs[i].countRep=new Text(terrs[i].x-3,terrs[i].y+10,terrs[i].count+"");
       root.getChildren().add(terrs[i].countRep);

     }
     primaryStage.setScene(new Scene(root));
     primaryStage.show();
     AnimationTimer timer = new AnimationTimer() {
       @Override
       public void handle(long l) {
         for ( int i=0 ; i<terrs.length ; i ++ ){
           terrs[i].rep.setFill(Paint.valueOf(terrs[i].color));
           terrs[i].countRep.setText(""+terrs[i].count);
         }

       }
     };
     timer.start();

  }
  public void mapUpdate(String name, String color, String count){
     for ( int i=0 ; i<terrs.length ; i ++ ){
       if(terrs[i].name.equals(name)){
         terrs[i].color=color;
         terrs[i].count=Integer.parseInt(count);
       }
     }
  }
  public static void showUI(){
    Application.launch();
  }
  public static void main(String[] args) {

    Application.launch();
  }

}
