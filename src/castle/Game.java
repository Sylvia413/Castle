package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String,Handler> handlers = new HashMap<String,Handler>(); 
        
    public Game() 
    {
//      handlers.put("go", new HandlerGo());
    	handlers.put("bye", new HandlerBye(this));
    	handlers.put("help", new HandlerHelp(this));
    	handlers.put("go", new HandlerGo(this));
    	createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom,balcony,secbedroom,secbalcony,thbedroom,kitchen,bathroom,garden;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        balcony = new Room("阳台");
        secbedroom = new Room("次卧");
        secbalcony = new Room("次卧阳台");
        thbedroom = new Room("客房");
        kitchen = new Room("厨房");
        bathroom = new Room("洗手间");
        garden = new Room("花园");
        
        
        //	初始化房间的出口
        outside.setExit("east",lobby);
        outside.setExit("south",study);
        outside.setExit("west",pub);
        lobby.setExit("west",outside);
        pub.setExit("east",outside);
        study.setExit("north",outside);
        study.setExit("east",bedroom);
        bedroom.setExit("west",study);
        pub.setExit("down", lobby);
        lobby.setExit("south", bedroom);
        bedroom.setExit("north", lobby);
        bedroom.setExit("south",balcony);
        balcony.setExit("north",bedroom);
        study.setExit("south",garden);
        garden.setExit("north",study);
        bedroom.setExit("east", secbedroom);
        secbedroom.setExit("west", bedroom);
        secbedroom.setExit("east", thbedroom);
        thbedroom.setExit("west", secbedroom);
        secbedroom.setExit("south", secbalcony);
        secbalcony.setExit("north", secbedroom);
        lobby.setExit("north", kitchen);
        kitchen.setExit("south", lobby);
        study.setExit("west", bathroom);
        bathroom.setExit("east", study);
        
        
        
        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("你可以输入go+方向去你想去的方向，如go west");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    // 以下为用户命令


    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            showPrompt();
        }
    }
    
	public void showPrompt() {
		System.out.println("你在" + currentRoom);
        System.out.print("出口有: ");
        System.out.print(currentRoom.getExitDesc());
        System.out.println();
	} 
	
	public void play() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler = handlers.get(words[0]);
    		String value = "";
    		if(words.length > 1)
    			value = words[1];
    		if(handler != null) {
    			handler.doCmd(value);
    			if(handler.isBye())
    				break;
    		}
//    		if ( words[0].equals("help") ) {
//    			printHelp();
//    		} else if (words[0].equals("go") ) {
//    			goRoom(words[1]);
//    		} else if ( words[0].equals("bye") ) {
//    			break;
//    		}
        }
		in.close();
	}
	public static void main(String[] args) {
		
		Game game = new Game();
		game.printWelcome();
		game.play();

        
        
        System.out.println("感谢您的光临。再见！");
	}

}
