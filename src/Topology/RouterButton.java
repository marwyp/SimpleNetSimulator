package Topology;

import Devices.Devices.Router;
import Icons.Icons;

import javax.swing.*;

public class RouterButton extends JButton {
    /////////////////////////////////////////////////////////
    //                 variables and objects               //
    /////////////////////////////////////////////////////////

    // position on map
    private final Position position;

    // actual router
    private final Router router;

    /////////////////////////////////////////////////////////
    //                     functions                       //
    /////////////////////////////////////////////////////////

    public RouterButton(Position position, Router router) {
        super(router.get_name(), Icons.icon.test());
        this.position = position;
        this.router = router;
    }

    // position getter
    public Position get_position() {
        return position;
    }

    // router getter
    public Router get_router() {
        return router;
    }
}
