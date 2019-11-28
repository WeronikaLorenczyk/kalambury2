package javaclasses;

public class UserInfo {
    String login;
    String password;
    Integer age;
    public Integer bestscore;
    public Integer scorenow;
    public boolean reload=false;
    public boolean newmessage=false;

    public UserInfo( String l, String p) {
        login=l;
        password=p;
        bestscore=0;
        scorenow=0;
    }

    public UserInfo() {
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setLogin(String value){
        login=value;
    }

    public String getLogin(){
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
