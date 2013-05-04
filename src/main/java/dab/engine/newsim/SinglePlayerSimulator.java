/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dab.engine.newsim;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import dab.engine.persistence.SaveGame;
import java.io.IOException;

/**
 *
 * @author eduard
 */
public class SinglePlayerSimulator extends AbstractSimulator {
    
    private String userName;
    private SinglePlayerFailureModel failureModel;
    
    public SinglePlayerSimulator() {
        super();
        this.failureModel = new SinglePlayerFailureModel(powerPlant);
    } 
    
    public void setUsername(String userName) {
        this.userName = userName;
    }
    
    /**
     *
     * Save game
     * @throws JsonProcessignException
     */
    public void saveGame() throws JsonProcessingException {
        SaveGame saveGame = new SaveGame(powerPlant, failureModel, userName);
        try {
            saveGame.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } 
    }
    /**
     *
     * Load a game
     * @param game Number
     */
    public void loadGame(int gameNumber) {
        try {
            SaveGame saveGame = SaveGame.load(listGames()[gameNumber]);
            this.powerPlant = saveGame.getPowerPlant();
            this.failureModel = new SinglePlayerFailureModel(powerPlant);
            this.userName = saveGame.getUserName();
        } catch (JsonParseException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    protected FailureModel getFailureModel() {
        return failureModel;
    }

}