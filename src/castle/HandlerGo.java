package castle;

public class HandlerGo extends Handler {

	public HandlerGo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void doCmd(String word) {
		// TODO Auto-generated method stub
		game.goRoom(word);
	}

}
