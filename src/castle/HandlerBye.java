package castle;

public class HandlerBye extends Handler {
	public HandlerBye(Game game)
	{
		super(game);
	}
	public boolean isBye() {
		return true;
	}
}
