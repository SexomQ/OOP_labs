package model.testing;

import model.GameModel;

interface Test extends GameModel {
    public String status = "in development";

    public String getStatus();
}
