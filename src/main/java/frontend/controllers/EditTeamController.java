package frontend.controllers;

import backend.GameEngine;
import backend.Season;
import backend.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;

public class EditTeamController {
    @FXML private Label budget;
    @FXML private Label firstDriver;
    @FXML private Label secondDriver;
    @FXML private Label strategist;
    @FXML private Label aerodynamicist;
    @FXML private Label mechanic;
    @FXML private Label engine;

    private Season season;

    /**
     * Sets the budget for the team to the right value.
     */
    @FXML
    public void initialize() {
        season = GameEngine.getInstance().getSeason();
        Team playerTeam = season.getPlayerControlledTeam();

        budget.setText(playerTeam.getBudgetString());
        engine.setText(playerTeam.getEngine().getName());
        firstDriver.setText(playerTeam.getFirstDriver().getName());
        secondDriver.setText(playerTeam.getSecondDriver().getName());
        strategist.setText(playerTeam.getStrategist().getName());
        aerodynamicist.setText(playerTeam.getAerodynamicist().getName());
        mechanic.setText(playerTeam.getMechanic().getName());
    }

    /**
     * Cancels all the changes by loading the json file from save.
     * Returns to the home screen after clicking.
     *
     * @param event not using it
     * @throws IOException throws if the home screen fxml doesn't exist
     */
    @FXML
    public void cancel(ActionEvent event) throws IOException {
        // TODO generalize the save-game name by adding an attribute to Season

        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        Stage stage = (Stage) budget.getScene().getWindow();

        GameEngine.getInstance().setSeason(Season.load("save1.json"));
        season = GameEngine.getInstance().getSeason();

        stage.getScene().setRoot(root);
    }

    /**
     * Saves the changes to the json file and return to the home screen.
     *
     * @param event not using it
     * @throws IOException throws if the home screen fxml doesn't exist
     */
    @FXML
    public void save(ActionEvent event) throws IOException {
        // TODO generalize the save-game name by adding an attribute to Season

        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        Stage stage = (Stage) budget.getScene().getWindow();

        season.save("save1.json");

        stage.getScene().setRoot(root);
    }

    /**
     * Button handler goes to the select team member.
     *
     * @param event don't use it
     * @throws IOException throws if the fxml file can not be found
     */
    @FXML
    public void editDriver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/select-team-member.fxml"));
        Stage stage = (Stage) budget.getScene().getWindow();
        stage.getScene().setRoot(root);
    }


}