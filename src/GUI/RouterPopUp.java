package GUI;

import Devices.Devices.Router;
import Devices.Link;
import Devices.Routing.Route;
import Devices.Routing.RouteCode;
import Protocols.Packets.IPv4;
import Protocols.Packets.IPv4MessageTypes;
import Topology.Topology;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RouterPopUp extends JDialog {
    /////////////////////////////////////////////////////////
    //                 variables and objects               //
    /////////////////////////////////////////////////////////

    // swing
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private javax.swing.JPanel JPanel;
    private JTextField header_name;
    // ip address
    private JTextField int0_ip_address;
    private JTextField int1_ip_address;
    private JTextField int2_ip_address;
    private JTextField int3_ip_address;
    private JTextField int4_ip_address;
    private JTextField int5_ip_address;
    private JTextField int6_ip_address;
    private JTextField int7_ip_address;
    // mask
    private JTextField int0_mask;
    private JTextField int1_mask;
    private JTextField int2_mask;
    private JTextField int3_mask;
    private JTextField int4_mask;
    private JTextField int5_mask;
    private JTextField int6_mask;
    private JTextField int7_mask;
    // link state
    private JTextField int0_link_state;
    private JTextField int1_link_state;
    private JTextField int2_link_state;
    private JTextField int3_link_state;
    private JTextField int4_link_state;
    private JTextField int5_link_state;
    private JTextField int6_link_state;
    private JTextField int7_link_state;

    private JComboBox port_combo_box;
    private JTextField ip_address_config;
    private JTextField mask_config;
    private JButton ip_address_apply;
    private JButton ip_address_delete;
    private JComboBox delete_route_combo_box;
    private JButton deleteButton;
    private JTextArea textArea1;
    private JButton showButton;
    private JButton sendButton;
    // int state
    private JButton int0_state;
    private JButton int1_state;
    private JButton int2_state;
    private JButton int3_state;
    private JButton int4_state;
    private JButton int5_state;
    private JButton int6_state;
    private JButton int7_state;
    // int panels
    private JPanel int0_panel;
    private JPanel int1_panel;
    private JPanel int2_panel;
    private JPanel int3_panel;
    private JPanel int4_panel;
    private JPanel int5_panel;
    private JPanel int6_panel;
    private JPanel int7_panel;
    private JButton route_add;
    private JTextField ip_address_route_add;
    private JTextField mask_route_add;
    private JComboBox add_route_combo_box;

    // int state set
    JButton[] int_state = new JButton[]{int0_state, int1_state, int2_state, int3_state,
            int4_state, int5_state, int6_state, int7_state};

    // vars and objects
    private Router router;

    // topology
    Topology topology = Topology.get_topology();

    /////////////////////////////////////////////////////////
    //                     functions                       //
    /////////////////////////////////////////////////////////

    public RouterPopUp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // appearance settings
        setLocation(200, 100);
        setResizable(false);
        header_name.setBorder(BorderFactory.createEmptyBorder());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // up/down interface 0
        int0_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(0);
            }
        });

        // up/down interface 1
        int1_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(1);
            }
        });

        // up/down interface 2
        int2_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(2);
            }
        });

        // up/down interface 3
        int3_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(3);
            }
        });

        // up/down interface 4
        int4_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(4);
            }
        });

        // up/down interface 5
        int5_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(5);
            }
        });

        // up/down interface 6
        int6_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(6);
            }
        });

        // up/down interface 7
        int7_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up_down_interface(7);
            }
        });

        // ip address apply
        ip_address_apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get interface
                String int_number_string = port_combo_box.getSelectedItem().toString();
                int int_number = Integer.parseInt(int_number_string);

                // get ip and mask
                String ip_address = ip_address_config.getText();
                String mask = mask_config.getText();

                // valid ip and mask
                IPv4MessageTypes ip_message = IPv4.is_ip_valid(ip_address);
                IPv4MessageTypes mask_message = IPv4.is_mask_valid(mask);

                // if valid apply
                if (ip_message == IPv4MessageTypes.is_valid && mask_message == IPv4MessageTypes.is_valid){
                    System.out.println("true");
                    router.set_interface_ip(int_number, IPv4.parse_to_long(ip_address),
                            IPv4.parse_mask_to_long(mask));
                    refresh();
                }else{

                }
            }
        });

        // delete ip
        ip_address_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get interface
                String int_number_string = port_combo_box.getSelectedItem().toString();
                int int_number = Integer.parseInt(int_number_string);
                router.set_interface_ip(int_number, -1, -1);
                refresh();
            }
        });

        // route add
        route_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get interface
                String int_number_string = add_route_combo_box.getSelectedItem().toString();
                int int_number = Integer.parseInt(int_number_string);

                // get ip and mask
                String ip_address = ip_address_route_add.getText();
                String mask = mask_route_add.getText();

                // valid ip and mask
                IPv4MessageTypes ip_message = IPv4.is_ip_valid(ip_address);
                IPv4MessageTypes mask_message = IPv4.is_mask_valid(mask);

                if (ip_message == IPv4MessageTypes.is_valid && mask_message == IPv4MessageTypes.is_valid){
                    System.out.println("true");
                    router.add_static_route(IPv4.parse_to_long(ip_address),IPv4.parse_mask_to_long(mask), int_number);
                    refresh();
                }else{

                }
            }
        });
    }

    private void up_down_interface(int int_number){
        if (router.get_interface(int_number).is_up()){
            router.down_interface(int_number);
        }else{
            router.up_interface(int_number);
        }
        refresh();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void refresh(){
        // int number
        int int_number = router.get_int_number();

        // set window size
        int width = 450;
        int height = 450 + 25 * (int_number - 1);
        setSize(width, height);

        // ip address set
        JTextField[] ip_address = new JTextField[]{int0_ip_address, int1_ip_address, int2_ip_address,
                int3_ip_address, int4_ip_address, int5_ip_address, int6_ip_address, int7_ip_address};

        // mask set
        JTextField[] mask = new JTextField[]{int0_mask, int1_mask, int2_mask, int3_mask, int4_mask,
        int5_mask, int6_mask, int7_mask};

        // link state set
        JTextField[] link_state = new JTextField[]{int0_link_state, int1_link_state, int2_link_state,
        int3_link_state, int4_link_state, int5_link_state, int6_link_state, int7_link_state};

        // int panel set
        JPanel[] panel = new JPanel[]{int0_panel, int1_panel, int2_panel, int3_panel,
        int4_panel, int5_panel, int6_panel, int7_panel};

        // set header
        header_name.setText(router.get_name());

        // set ports combo box
        port_combo_box.removeAllItems();
        for (int i = 0; i < int_number; i++){
            port_combo_box.addItem(i);
            add_route_combo_box.addItem(i);
        }

        // set routes delete route combo box
        String item;
        delete_route_combo_box.removeAllItems();
        for (int i = 0; i < router.get_routing_table_size(); i++){
            Route route = router.get_route(i);
            if (route.code() == RouteCode.S){
                item = route.to_string();
                delete_route_combo_box.addItem(item);
            }
        }

        // set ip int states
        for (int i = 0; i < int_number; i++){
            if(router.get_interface(i).is_up()){
                int_state[i].setBackground(Color.green);
            }else{
                int_state[i].setBackground(Color.red);
            }
        }

        // set ip addresses and masks
        for (int i = 0; i < int_number; i++){
            String ip_string = "";
            String mask_string = "";
            if (router.get_interface(i).get_ip_address().get("address") != -1){
                ip_string = IPv4.parse_to_string(router.get_interface(i).get_ip_address().get("address"));
                mask_string = IPv4.parse_mask_to_string_short(router.get_interface(i).get_ip_address().get("mask"));
            }
            ip_address[i].setText(ip_string);
            mask[i].setText(mask_string);
        }

        // link state
        for (int i = 0; i < int_number; i++){
            link_state[i].setText("unconnected");
            for (Link link: topology.get_links()){
                if (link.get_end1() == router.get_interface(i)){
                    link_state[i].setText("connected");
                    break;
                }else if (link.get_end2() == router.get_interface(i)){
                    link_state[i].setText("connected");
                    break;
                }
            }
        }

        // delete ports
        for (int i = 0; i < topology.get_max_int_number(); i++){
            panel[i].setVisible(i < int_number);
        }
    }

    public void set_router(Router router){
        this.router = router;
    }
}
