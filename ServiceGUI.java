import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JComboBox modelBox;
    private JLabel modelText;

    DefaultListModel<ServiceCall> serviceCallListModel;

    final String FORCED_AIR = "Forced Air";
    final String BOILER = "Boiler";
    final String OCTOPUS = "Octopus";

    boolean furanceIsChecked = false;
    boolean acIsChecked = false;

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

        addTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceAddress = addressText.getText();
                String problemDescription = descriptionText.getText();
            }
        });



        furnaceCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                typeBox.enable();
                typeText.enable();
                furanceIsChecked = true;

                if (acIsChecked == true){
                    JOptionPane.showInputDialog("You can only check one of the check boxes.");
                }
            }
        });

        acCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                modelBox.enable();
                modelText.enable();
                acIsChecked = true;

                if (furanceIsChecked == true){
                    JOptionPane.showInputDialog("You can only check one of the check boxes.");
                }
            }
        });
    }


}
