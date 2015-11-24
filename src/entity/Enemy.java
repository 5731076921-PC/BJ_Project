package entity;

public abstract class Enemy  extends CollidableEntity {
		
	public void hitWithPlayer(int decScore){
		Player.setScore(Player.getScore() - decScore);
		destroyed = true;
	}
	

	

}
