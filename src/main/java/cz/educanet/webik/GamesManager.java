package cz.educanet.webik;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class GamesManager {

    int id = 0;
    private ArrayList<GameDetail> gamesList = new ArrayList<>();
    public ArrayList<GameDetail> getGamesList(){
        return gamesList;
    }

    public boolean create(GameDetail game) {

        this.id++;
        int newId = this.id;

        game.setId(newId);
        gamesList.add(game);

        return true;
    }
    public GameDetail getGame (int id){
        return gamesList.stream()
                .filter(gameStream -> id == gameStream.getId())
                .findAny()
                .orElse(null);
    }

    public boolean gameCheck(int id) {
        for (int i = 0; i < gamesList.size(); i++){
            if (id == gamesList.get(i).id) {
                return false;
            }
        } return true;
    }



    public boolean removeGame(int id){
        return  gamesList.remove(getGame(id));
    }

    public boolean editGame(int id, GameDetail gameData) {
        for(GameDetail singleGame: gamesList) {
            if(singleGame.id == id) {
                singleGame.name = gameData.name;
                singleGame.price = gameData.price;
                singleGame.studio = gameData.studio;
                return true;
            }
        } return false;
    }

}
