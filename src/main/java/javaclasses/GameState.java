package javaclasses;

import servlets.CanvaHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class GameState {
    public static ArrayList<GameState> gamestatelist= new ArrayList<>();
    static{
        gamestatelist.add(new GameState("example"));
    }

    final Integer ROUND_LEN=20;

     public String word;
     public int drawingplayer=0;
     String gamename;
    public ArrayList<String> players;
    public CanvaHandler picture;
    ArrayList<Message> messagesList = new ArrayList<>();
    public AtomicInteger time=new AtomicInteger(10);
    public volatile boolean roundstarted=false;

     public GameState(String n){
         gamename=n;
         players=new ArrayList<>();
         word=getnextword();
         picture=new CanvaHandler();
         time.set(ROUND_LEN);
         thread.start();
     }


    public String getGamenamewith() {
        return "'"+gamename+"'";
    }

    public String getGamename() {
        return gamename;
    }

    public String getDrawing(){
         return players.get(drawingplayer);
    }

    private String getnextword(){
         return "kot";
     }

     public void addplayer(UserInfo ui){
         players.add(ui.login);
         ui.scorenow=0;

     }

    Thread thread = new Thread(){

        public void run() {
            while (true){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
                if(roundstarted) {
                    time.decrementAndGet();
                    if (time.get() == 0) {
                        nextround();
                    }
                }
            }

        }
    };

     public void nextround(){
         System.out.println("nowa runda");
         roundstarted=false;
         drawingplayer++;
         drawingplayer %= players.size();
         word=getnextword();
         for(String nick : players){
             DatabaseHandler.getUser(nick).reload=true;
         }
         time.set(ROUND_LEN);
     }

     public void addpoint(String n){
         UserInfo ui=DatabaseHandler.getUser(n);
         ui.scorenow++;
         if(ui.bestscore < ui.scorenow)
            ui.bestscore=ui.scorenow;
     }

    public  void addMessage (Message m){
        messagesList.add(m);
        for(String nick : players){
            DatabaseHandler.getUser(nick).newmessage=true;
        }
    }

    public  List<Message> getMessagesList (){
        return messagesList;
    }


}
