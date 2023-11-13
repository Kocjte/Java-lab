package Prova;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VehicleGUI extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField searchField;
    private JButton btnSearch;
    private JButton btnAddVehicle;
    private JButton btnRemoveVehicle;

    private Connection connection;
    private Statement statement;
    
    private JTextField txtVehicleName;
    private JTextField txtBrandName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VehicleGUI frame = new VehicleGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VehicleGUI() {
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 70, 764, 480);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        searchField = new JTextField();
        searchField.setBounds(10, 40, 200, 20);
        contentPane.add(searchField);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDatabase(searchField.getText());
            }
        });
        btnSearch.setBounds(220, 39, 89, 23);
        contentPane.add(btnSearch);

        btnAddVehicle = new JButton("Add Vehicle");
        btnAddVehicle.setBounds(319, 39, 120, 23);
        contentPane.add(btnAddVehicle);
        btnAddVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the method to add a vehicle to the database
                showAddVehicleDialog();
            }
        });
        
        txtVehicleName = new JTextField();
        txtVehicleName.setBounds(10, 100, 200, 20);
        contentPane.add(txtVehicleName);

        txtBrandName = new JTextField();
        txtBrandName.setBounds(10, 130, 200, 20);
        contentPane.add(txtBrandName);

        btnRemoveVehicle = new JButton("Remove Vehicle");
        btnRemoveVehicle.setBounds(449, 39, 140, 23);
        contentPane.add(btnRemoveVehicle);
        btnRemoveVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the method to show the remove vehicle input dialog
                showRemoveVehicleInputDialog();
            }
        });

        JLabel lblSearchBy = new JLabel("Search by name:");
        lblSearchBy.setBounds(10, 15, 110, 14);
        contentPane.add(lblSearchBy);

        initializeDatabase();
        loadTableData();
    }

    // Initialize the database connection
    private void initializeDatabase() {
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "newuser", "password");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search the database for vehicles with a given name
    private void searchDatabase(String name) {
        try {
            // Your SQL query to search for the given name
            String query = "SELECT * FROM Vehicles WHERE name LIKE '%" + name + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata about the ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get the number of columns in the ResultSet
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data for the JTable
            DefaultTableModel tableModel = new DefaultTableModel();

            // Add column names to the table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
            }

            // Set the table model to the JTable
            table.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load data from the database and display it in the JTable
    private void loadTableData() {
        try {
            // Your SQL query to retrieve all data from the table
            String query = "SELECT * FROM Vehicles";
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata about the ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get the number of columns in the ResultSet
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data for the JTable
            DefaultTableModel tableModel = new DefaultTableModel();

            // Add column names to the table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
            }

            // Set the table model to the JTable
            table.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void showAddVehicleDialog() {
        JDialog addVehicleDialog = new JDialog(this, "Add Vehicle", true);
        addVehicleDialog.setSize(300, 150);
        addVehicleDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblVehicleName = new JLabel("Vehicle Name:");
        JLabel lblBrandName = new JLabel("Brand Name:");

        txtVehicleName = new JTextField();
        txtBrandName = new JTextField();

        JButton btnAdd = new JButton("Add");
        JButton btnCancel = new JButton("Cancel");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to add a vehicle to the database
                addVehicletoDataFromDialog();
                addVehicleDialog.dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicleDialog.dispose();
            }
        });

        panel.add(lblVehicleName);
        panel.add(txtVehicleName);
        panel.add(lblBrandName);
        panel.add(txtBrandName);
        panel.add(btnAdd);
        panel.add(btnCancel);

        addVehicleDialog.add(panel);
        addVehicleDialog.setVisible(true);
    }

    // Method to add a vehicle to the database from the dialog
    private void addVehicletoDataFromDialog() {
        try {
            // Get the values from the dialog input fields
            String vehicleName = txtVehicleName.getText();
            String brandName = txtBrandName.getText();

            // Your SQL query to insert data into the Vehicles table
            String query = "INSERT INTO Vehicles (name, brand_name) VALUES (?, ?)";

            // Create a PreparedStatement to safely execute the query
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the values for the parameters in the query
                preparedStatement.setString(1, vehicleName);
                preparedStatement.setString(2, brandName);

                // Execute the query
                preparedStatement.executeUpdate();
            }

            // Refresh the JTable with the updated data
            loadTableData();

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private void showRemoveVehicleInputDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JTextField txtName = new JTextField();
        JTextField txtBrand = new JTextField();
        JTextField txtId = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Brand:"));
        panel.add(txtBrand);
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        int result = JOptionPane.showConfirmDialog(null, panel, "Remove Vehicle", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = txtName.getText();
            String brand = txtBrand.getText();
            int id = 0;

            try {
                id = Integer.parseInt(txtId.getText());
            } catch (NumberFormatException e) {
                // Handle the exception (show an error message, log, etc.)
            }

            // Call the method to remove a vehicle from the database
            removeVehiclefromDataWithInput(name, brand, id);
        }
    }

    // Method to remove a vehicle from the database with input values
    private void removeVehiclefromDataWithInput(String name, String brand, int id) {
        try {
            // Your SQL query to delete the vehicle from the Vehicles table based on name, brand, or id
            String query = "DELETE FROM Vehicles WHERE name = ? OR brand_name = ? OR id = ?";

            // Create a PreparedStatement to safely execute the query
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the values for the parameters in the query
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, brand);
                preparedStatement.setInt(3, id);

                // Execute the query
                preparedStatement.executeUpdate();
            }

            // Refresh the JTable with the updated data
            loadTableData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}