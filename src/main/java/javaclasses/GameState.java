package javaclasses;

import servlets.CanvaHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameState {
    public static ArrayList<GameState> gamestatelist= new ArrayList<>();
    static{
        gamestatelist.add(new GameState("example"));
    }

     public String word;
     int drawingplayer=0;
     String gamename;
     HashMap<String,Integer> playerspoints;
     ArrayList<String> players;
    public CanvaHandler picture;
    ArrayList<Message> messagesList = new ArrayList<>();
    public AtomicInteger time=new AtomicInteger(10);
    public volatile boolean roundstarted=false;

     public GameState(String n){
         gamename=n;
         playerspoints= new HashMap<>();
         players=new ArrayList<>();
         word=getnextword();
         picture=new CanvaHandler();
         time.set(10);
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

     public void addplayer(String login){
         playerspoints.put(login,0);
         players.add(login);
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
         time.set(10);
     }

     public void addpoint(String n){
         Integer i=playerspoints.get(n);
         playerspoints.replace(n,i+1);
     }

    public  void addMessage (Message m){
        messagesList.add(m);
        for(String nick : players){
            DatabaseHandler.getUser(nick).newmessage=true;
        }
        //if(messagesList.size() > 8)
        //messagesList.remove(0);
    }

    public  List<Message> getMessagesList (){
        return messagesList;
    }


}
