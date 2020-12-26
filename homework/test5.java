package homework;
import java.util.ArrayList;
import java.util.List;
public class test5 {
    public static void main(String[] args) {
        User zhangSan = new User("ZhangSan");
        User liSi = new User("LiSi");
        User wangWu = new User("WangWu");
        User zhaoLiu = new User("zhaoLiu");
        Channel douBiFanZhiJiDi = new Channel("douBiFanZhiJiDi");
        douBiFanZhiJiDi.add(liSi);
        douBiFanZhiJiDi.add(zhangSan);
        douBiFanZhiJiDi.add(wangWu);
        Channel yongYuanDe308 = new Channel("yongYuanDe308");
        yongYuanDe308.add(liSi);
        yongYuanDe308.add(wangWu);
        yongYuanDe308.add(zhaoLiu);
        zhaoLiu.send(yongYuanDe308, "What did you guys do during the holidays");
        wangWu.send(yongYuanDe308, "I went to eat barbecue, haha");
        zhaoLiu.send(yongYuanDe308, "It sounds interesting, we can barbecue together next time!");
        zhangSan.send(douBiFanZhiJiDi, "Please help me ask what Zhao Liu likes");
        wangWu.send(douBiFanZhiJiDi, "Zhang, I was Wang. Can't help you,bro. sorry");
        zhangSan.send(douBiFanZhiJiDi, "Lisi, would you mind...");
        liSi.send(douBiFanZhiJiDi, "You know I don’t like to be a matchmaker.");
        System.out.println("ZhangSan's messages list:");
        zhangSan.showMessages();
        System.out.println("\nLiSi's messages list:");
        liSi.showMessages();
        System.out.println("\nWangWu's messages list:");
        wangWu.showMessages();
        System.out.println("\nZhaoLiu's messages list:");
        zhaoLiu.showMessages();
    }    
}

interface Receiver{
    void receive(Sender sender,String message);
}

interface Sender{
    void send(Receiver receiver,String message);
    String getName();
}

class User implements Receiver,Sender{
    protected String name;
    protected List<String> messageList;
    public User(String _name){
        name = _name;
        messageList = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void send(Receiver receiver,String message){
        receiver.receive(this, message);
    }
    public void receive(Sender sender,String message){
        messageList.add(message);
    }
    public void showMessages(){
        for(int i = 0;i < messageList.size();i++){
            System.out.println(messageList.get(i));
        }
    }
}

class Channel implements Receiver{
    protected String name;
    protected List<User> userList;
    public Channel(String _name){
        name = _name;
        userList = new ArrayList<>();
    }
    public void receive(Sender sender,String message){
        for(int i = 0;i < userList.size();i++){
            if(userList.get(i) != sender){
                String tmp = message;
                message = "[From " + name + "] [From " + sender.getName() + "] " + message;
                userList.get(i).receive(sender, message);
                message = tmp;
            }
        }
    }
    public void add(User user){
        userList.add(user);
    }
    public void remove(User user){
        userList.remove(user);
    }
}