package javaclasses;

import servlets.CanvaHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

     public GameState(String n){
         gamename=n;
         playerspoints= new HashMap<>();
         players=new ArrayList<>();
         word=getnextword();
         picture=new CanvaHandler();
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

     public void nextround(){
         drawingplayer++;
         drawingplayer %= players.size();
         word=getnextword();
         for(String nick : players){
             DatabaseHandler.getUser(nick).reload=true;
         }
     }

     public void addpoint(String n){
         Integer i=playerspoints.get(n);
         playerspoints.replace(n,i+1);
     }

    public  void addMessage (Message m){
        messagesList.add(m);
    }

    public  List<Message> getMessagesList (){
        return messagesList;
    }


}
