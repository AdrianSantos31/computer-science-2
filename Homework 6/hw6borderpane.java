/* This program will utilize Java FX Programming. The instructions are noted as below
  Written by: Adrian Santos
              N00798593
  Date: 10/08/2016

Write a single program that produces Figure 14.52 (part b)  and
Figure 14.48  (part a)  and Figure 14.45 (part b).  All three should be visible at
the same time in a single window.
In addition, the clock (figure 14.52 part b)should have your name inside it, displayed
horizontally
between the 9 and the 3.  Note that the time displayed is based upon random numbers and
should vary with each run.
 A single program should only produce a single display of all three figures. Resizing of the
window should be
possible and should preserve the figures as much as possible.
*/

//Library Stage and Scene Imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.GridPane;



//Clock Library Imports
//Fan Library Imports
//Hangman Library Imports

//Displays Three Figures
public class hw6borderpane extends Application{
    final static int RADIUS = 75;
    final static int GAP = 10;

  //Main method, since jGrasp will be used to check the program
  public static void main(String[] args){
     Application.launch(args);
    }

  @Override
  public void start(Stage primaryStage) throws Exception{


    int hour = (int)(Math.random()* 10);
    int minute = (int)(Math.random()* 31);
    int second = (int)(Math.random()* 93);

    ClockPane clock = new ClockPane(hour, minute, second);
    String timeString = clock.getHour() + ":" + clock.getMinute() + ":"
    + clock.getSecond();
    //Create a label
    Label lblCurrentTime = new Label(timeString);


    //BorderPane
    BorderPane borderPane = new BorderPane();//GridPane

    Pane fanPane = new Pane(); //fanPane
    Pane hangmanPane = new Pane(); //hangmanPane

    borderPane.setLeft(fanPane);
    borderPane.setCenter(clock);
    //borderPane.setRight(hangmanPane);

    //Create a scene and place it in the Stage
    Scene scene = new Scene(borderPane,720,330);
    primaryStage.setTitle("Adrian Santos - N00798593"); //Title of the document
    primaryStage.setScene(scene); // Put scene on the stage
    primaryStage.show(); // Display the stage

    borderPane.widthProperty().addListener(ov ->
    clock.setW(borderPane.getWidth()));
    borderPane.heightProperty().addListener(ov ->
    clock.setH(borderPane.getHeight()));

    /*borderPane.widthProperty().addListener(ov ->
    fanPane.setW(borderPane.getWidth()));
    borderPane.heightProperty().addListener(ov ->
    fanPane.setH(borderPane.getHeight()));
    */

    //Fan
    int x = RADIUS + GAP;
    int y = RADIUS + GAP;

    for (int i=0; i < 2; i++){
      for (int j = 0; j < 2; j++){
        //Create Scene 1
        Circle fan = new Circle();
        fan.setCenterX(x);
        fan.setCenterY(y);
        fan.setRadius(RADIUS);
        fan.setStroke(Color.BLACK);
        fan.setFill(Color.GREEN);
        fanPane.getChildren().add(fan);

        for (int k =30; k < 360; k+= 90){
          Arc arc = new Arc(x, y, RADIUS - 15, RADIUS - 15, k, 30);
          arc.setStroke(Color.BLACK);
          arc.setFill(Color.BLACK);
          arc.setType(ArcType.ROUND);
          fanPane.getChildren().add(arc);
        }
       x += RADIUS * 2 + GAP;
      }
      //Reset the center
      x = RADIUS + GAP;
      y += RADIUS * 2 + GAP;
    }

    //Hangman
    Circle circle = new Circle(20);
    circle.setCenterX(80.0f);
    circle.setCenterY(70.0f);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);

    Line line1 = new Line();//Correct - Long V Pole
    line1.setStartX(20.0f);
    line1.setStartY(220.0f);
    line1.setEndX(20.0f);
    line1.setEndY(40.0f);

    Line line2 = new Line();//Correct - H pole
    line2.setStartX(20.0f);
    line2.setStartY(40.0f);
    line2.setEndX(80.0f);
    line2.setEndY(40.0f);

    Line line3 = new Line();//Correct - Short V Pole
    line3.setStartX(80.0f);
    line3.setStartY(50.0f);
    line3.setEndX(80.0f);
    line3.setEndY(40.0f);

    Line line4 = new Line();//Correct - Body
    line4.setStartX(80.0f);
    line4.setStartY(90.0f);
    line4.setEndX(80.0f);
    line4.setEndY(170.0f);

    Line line5 = new Line();//Correct R Leg
    line5.setStartX(80.0f);
    line5.setStartY(170.0f);
    line5.setEndX(50.0f);
    line5.setEndY(200.0f);

    Line line6 = new Line();//Correct L Leg
    line6.setStartX(80.0f);
    line6.setStartY(170.0f);
    line6.setEndX(110.0f);
    line6.setEndY(200.0f);

    Line line7 = new Line();//Incorrect - R Arm
    line7.setStartX(63.0f);
    line7.setStartY(80.0f);
    line7.setEndX(43.0f);
    line7.setEndY(100.0f);

    Line line8 = new Line();//Incorrect - L Arm
    line8.setStartX(97.0f);
    line8.setStartY(80.0f);
    line8.setEndX(117.0f);
    line8.setEndY(100.0f);

    Arc arc = new Arc(110,297,180,90, 90,25);
    arc.setType(ArcType.OPEN);


    hangmanPane.getChildren().addAll(circle, line1, line2, line3, line4, line5, line6, line7, line8, arc);



  }
}


class ClockPane extends Pane{
  private int hour;
  private int minute;
  private int second;

  private double w = 300, h = 300;

  public ClockPane(){
    setCurrentTime();
  }

  public ClockPane(int hour, int minute, int second){
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    paintClock();
  }

  public int getHour(){
    return hour;
  }

  public void setHour(int hour){
    this.hour = hour;
    paintClock();
  }

  public int getMinute(){
    return minute;
  }

  public void setMinute(int minute){
    this.minute = minute;
    paintClock();
  }

  public int getSecond(){
    return second;
  }

  public void setSecond(int second){
    this.second = second;
    paintClock();
  }

  public double getW(){
    return w;
  }

  public void setW(double w){
    this.w = w;
    paintClock();
  }

  public double getH(){
    return h;
  }

  public void setH(double h){
    this.h = h;
    paintClock();
  }

  public void setCurrentTime (){

    Calendar calendar = new GregorianCalendar();

    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);

    paintClock();

  }

  protected void paintClock(){
    double clockRadius = Math.min(w,h) * 0.8 * 0.5;
    double centerX = w / 2;
    double centerY = h / 2;//

    //Create Circle
    Circle circle = new Circle(centerX, centerY, clockRadius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    Text t1 = new Text (centerX - 5, centerY - clockRadius + 12, "12");
    Text t2 = new Text (centerX - clockRadius + 3, centerY + 3, "9");
    Text t3 = new Text (centerX + clockRadius - 10, centerY + 3, "3");
    Text t4 = new Text (centerX - 3, centerY + clockRadius - 12, "6");
    Text name = new Text (centerX - 40, centerY+2, "Adrian Santos");

    //Draw second hand
    double sLength = clockRadius * 0.8;
    double secondX = centerX + sLength *
    Math.sin(second *(2 * Math.PI / 60));
    double secondY = centerY - sLength *
    Math.cos(second * (2 * Math.PI / 60));
    Line sLine =  new Line (centerX, centerY, secondX, secondY);
    sLine.setStroke(Color.RED);

    //Draw minute hand
    double mLength = clockRadius * 0.65;
    double minuteX = centerX + mLength *
    Math.sin(minute *(2 * Math.PI / 60));
    double minuteY = centerY - mLength *
    Math.cos(minute * (2 * Math.PI / 60));
    Line mLine =  new Line (centerX, centerY, minuteX, minuteY);
    mLine.setStroke(Color.BLUE);

    //Draw hour hand
    double hLength = clockRadius * 0.5;
    double hourX = centerX + hLength *
    Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = centerY - hLength *
    Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine =  new Line (centerX, centerY, hourX, hourY);
    hLine.setStroke(Color.GREEN);

    getChildren().clear();
    getChildren().addAll(circle, t1, t2,t3,t4, name, sLine, mLine, hLine);
  }

}
