package plakolb;

public class Scoreboard {

    int player_id;
    int game_id;
    int points_reached;
    int play_date;

    public Scoreboard(int player_id, int game_id, int points_reached, int play_date) {
        this.player_id = player_id;
        this.game_id = game_id;
        this.points_reached = points_reached;
        this.play_date = play_date;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPoints_reached() {
        return points_reached;
    }

    public void setPoints_reached(int points_reached) {
        this.points_reached = points_reached;
    }

    public int getPlay_date() {
        return play_date;
    }

    public void setPlay_date(int play_date) {
        this.play_date = play_date;
    }

}
