package javaclasses;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public static ArrayList<GameState> gamestatelist= new ArrayList<>();
    static{
        gamestatelist.add(new GameState("example"));
    }

     String word;
     int drawingplayer=0;
     String gamename;
     HashMap<String,Integer> playerspoints;
     ArrayList<String> players;

     public GameState(String n){
         gamename=n;
         playerspoints= new HashMap<>();
         players=new ArrayList<>();
         word=getnextword();
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
     }

}
