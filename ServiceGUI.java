import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private JList <ServiceCall> serviceCallList;
    private JButton addTicketButton;
    private JButton clearFieldsButton;
    private JButton resolveTicketButton;
    private JButton deleteTicketButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JComboBox modelBox;
    private JLabel modelText;

    DefaultListModel<ServiceCall> serviceCallListModel;

    final String FORCED_AIR = "Forced Air";  //furance type 1
    final String BOILER = "Boiler";  //furance type 2
    final String OCTOPUS = "Octopus"; //furance type 3

    final String INFINITY = "Infinity";
    final String PERFORMANCE = "Performance";
    final String COMFORT = "Comfort";

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

        modelBox.addItem(INFINITY);
        modelBox.addItem(PERFORMANCE);
        modelBox.addItem(COMFORT);



        addTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceAddress = addressText.getText();
                String problemDescription = descriptionText.getText();
                String date = dateText.getText();
                Date reportedDate = new Date(1901, 01, 01);

                int furanceType = 0;
                String model = "model";

                if (furanceIsChecked == true && acIsChecked == false){  //add a furnace ticket only if AC check box isn't marked
                    if (typeBox.getSelectedItem().equals(FORCED_AIR)){
                        furanceType = 1;
                    } else if (typeBox.getSelectedItem().equals(BOILER)){
                        furanceType = 2;
                    } else {
                        furanceType = 3;
                    }

                    try {
                        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                        reportedDate = format.parse(date);
                    } catch (ParseException pe){
                        pe.printStackTrace();
                    }

                    Furnace furnaceTicket = new Furnace(serviceAddress, problemDescription, reportedDate, furanceType);
                    ServiceGUI.this.serviceCallListModel.addElement(furnaceTicket);

                }

                if (acIsChecked == true && furanceIsChecked == false){  //add a CentralAC ticket only if the furance check box isn't marked
                    if (modelBox.getSelectedItem().equals(INFINITY)){
                        model = "Infinity";
                    } else if (modelBox.getSelectedItem().equals(PERFORMANCE)){
                        model = "Performance";
                    } else {
                        model = "Comfort";
                    }

                    CentralAC acTicket = new CentralAC(serviceAddress, problemDescription, reportedDate, model);
                    ServiceGUI.this.serviceCallListModel.addElement(acTicket);
                }
            }
        });

        clearFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addressText.setText("");
                descriptionText.setText("");
                dateText.setText("");

                furnaceCheck.setSelected(false);  //uncheck the checkboxes
                acCheck.setSelected(false);
                acIsChecked = false;   //change the check box identifiers
                furanceIsChecked = false;

                modelBox.setEnabled(false);  //hide the model drop list
                modelText.setEnabled(false);

                typeBox.setEnabled(false);  //hide the furnance type drop list
                typeText.setEnabled(false);


            }
        });

        resolveTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceCall resolvedCall = ServiceGUI.this.serviceCallList.getSelectedValue();
                String resolution = JOptionPane.showInputDialog("How was the issue resolved?");
                Date resolvedDate = new Date();
                String resolvedDateText = JOptionPane.showInputDialog("On what date was the issue resolved?");

                try {
                    DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                     resolvedDate = format.parse(resolvedDateText);
                } catch (ParseException pe){
                    pe.printStackTrace();
                }

                resolvedCall.setResolution(resolution);
                resolvedCall.setResolvedDate(resolvedDate);

                ServiceGUI.this.serviceCallListModel.removeElement(resolvedCall);

            }
        });

        deleteTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceCall toDelete = ServiceGUI.this.serviceCallList.getSelectedValue();


                ServiceGUI.this.serviceCallListModel.removeElement(toDelete);
            }
        });



        furnaceCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                typeBox.setEnabled(true);  //display the furance type drop list
                typeText.setEnabled(true);
                furanceIsChecked = true;

                if (acIsChecked == true){
                    JOptionPane.showMessageDialog(null, "You can only check one of the check" +
                            "boxes.", "Checkbox Warning", JOptionPane.ERROR_MESSAGE);
                    furnaceCheck.setSelected(false);
                    acCheck.setSelected(false);
                    acIsChecked = false;
                    furanceIsChecked = false;
                }
            }
        });

        acCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                modelBox.setEnabled(true);  //display the model type drop list
                modelText.setEnabled(true);

                acIsChecked = true;

                if (furanceIsChecked == true){
                    JOptionPane.showMessageDialog(null, "You can only check one of the check" +
                            "boxes.", "Checkbox Warning", JOptionPane.ERROR_MESSAGE);
                    furnaceCheck.setSelected(false);
                    acCheck.setSelected(false);
                    acIsChecked = false;
                    furanceIsChecked = false;
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
