import java.awt.*;
import java.awt.event.*;

public class MultiplicationTableAWT extends Frame implements ActionListener {

    Label lblEnter, lblResult;
    TextField txtNumber;
    TextArea txtTable;
    Button btnDisplay, btnClear;

    public MultiplicationTableAWT() {
        // Frame properties
        setTitle("Multiplication Table");
        setSize(400, 400);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);

        // Components
        lblEnter = new Label("Enter a number:");
        txtNumber = new TextField(10);

        btnDisplay = new Button("Display");
        btnClear = new Button("Clear");

        lblResult = new Label("Multiplication Table:");
        txtTable = new TextArea(10, 30);
        txtTable.setEditable(false);

        // Add components to frame
        add(lblEnter);
        add(txtNumber);
        add(btnDisplay);
        add(btnClear);
        add(lblResult);
        add(txtTable);

        // Register event listeners
        btnDisplay.addActionListener(this);
        btnClear.addActionListener(this);

        // Handle window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDisplay) {
            try {
                int n = Integer.parseInt(txtNumber.getText());
                StringBuilder table = new StringBuilder();

                for (int i = 1; i <= 10; i++) {
                    table.append(n + " x " + i + " = " + (n * i) + "\n");
                }

                txtTable.setText(table.toString());
            } catch (NumberFormatException ex) {
                txtTable.setText("Please enter a valid number!");
            }
        } else if (e.getSource() == btnClear) {
            txtNumber.setText("");
            txtTable.setText("");
        }
    }

    public static void main(String[] args) {
        new MultiplicationTableAWT();
    }
}
