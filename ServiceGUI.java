import javax.swing.*;
import java.awt.*;

/**
 * Created by Marty on 3/29/2015.
 */
public class ServiceGUI extends JFrame {
    private JCheckBox furnaceCheck;
    private JCheckBox acCheck;
    private JLabel addressLabel;
    private JTextField addressText;
    private JLabel descriptionLabel;
    private JTextField descriptionText;
    private JLabel reportedLabel;
    private JTextField dateText;
    private JLabel feeLabel;
    private JTextField feeText;
    private JLabel typeText;
    private JComboBox typeBox;
    private JList serviceCallList;
    private JButton addTicketButton;
    private JButton clearFieldsButton;
    private JButton resolveTicketButton;
    private JButton deleteTicketButton;
    private JButton quitButton;
    private JPanel rootPanel;

    DefaultListModel<ServiceCall> serviceCallListModel;

    final String FORCED_AIR = "Forced Air";
    final String BOILER = "Boiler";
    final String OCTOPUS = "Octopus";

    public ServiceGUI(){
        super("Service Call Manager");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(300,500));

        serviceCallListModel = new DefaultListModel<ServiceCall>();
        serviceCallList.setModel(serviceCallListModel);

        serviceCallList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        typeBox.addItem(FORCED_AIR);
        typeBox.addItem(BOILER);
        typeBox.addItem(OCTOPUS);
    }


}
