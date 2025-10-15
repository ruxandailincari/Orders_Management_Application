package org.example.Presentation;

import org.example.Model.Bill;
import org.example.Model.Client;
import org.example.Model.Order;
import org.example.Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Class used to define the gui of the application
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class View extends JFrame {
    private JPanel contentPane1;
    private JPanel contentPane2;
    private JPanel contentPane3;
    private JPanel contentPane4;
    private JPanel contentPane5;
    private JPanel contentPane6;
    private JPanel clientPanel2;
    private JPanel productPanel2;
    private JPanel orderPanel1;
    private JTextField clientName;
    private JTextField productName;
    private JTextField clientMail;
    private JTextField productPrice;
    private JTextField productStock;
    private JTextField newProductId;
    private JTextField newProductName;
    private JTextField newProductPrice;
    private JTextField newProductStock;
    private JTextField updatedClientId;
    private JTextField updatedClientName;
    private JTextField updatedClientMail;
    private JTextField productQuantity;
    private JTable clientTable;
    private JTable productTable;
    private JPopupMenu clientMenu;
    private JPopupMenu productMenu;
    private JComboBox<Client> clientJComboBox;
    private JComboBox<Product> productJComboBox;

    private List<Client> currentClients;
    private List<Product> currentProducts;
    private Controller controller;

    /**
     * Constructor used to initialize the view and instantiate the controller
     * @param name of the window
     */
    public View(String name){
        super(name);
        this.prepareGUI();
        controller = new Controller(this);
    }

    /**
     * Method used to prepare the main menu of the application
     */
    public void prepareGUI(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(700, 700);
        this.setResizable(true);
        this.setLocation(350, 0);
        this.contentPane1 = new JPanel(new GridBagLayout());
        this.prepareMenuPanel();
        this.setContentPane(this.contentPane1);
    }

    /**
     * Method used to set constraints for a GridBagConstraints object
     * @param gridx the column position in the grid
     * @param gridy the row position in the grid
     * @param gridWidth  the number of columns the component should span
     * @param weightx how to distribute extra horizontal space
     * @param weighy how to distribute extra vertical space
     * @return an object with specified constraints
     */
    public GridBagConstraints setConstraints(int gridx, int gridy, int gridWidth, double weightx, double weighy) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridWidth;
        c.weightx = weightx;
        c.weighty = weighy;
        c.insets = new Insets(5, 5, 5, 5);
        return c;
    }

    /**
     * Method used to put the buttons in the main menu and set an action listener for them
     */
    public void prepareMenuPanel(){
        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0, 1.0);
        GridBagConstraints c2 = setConstraints(0,1,1,1.0, 1.0);
        GridBagConstraints c3 = setConstraints(0,2,1,1.0, 1.0);
        GridBagConstraints c4 = setConstraints(0,3,1,1.0, 1.0);
        GridBagConstraints c5 = setConstraints(0,4,1,1.0,1.0);
        menuPanel.add(new JLabel("Main menu"), c1);
        JButton clientButton = new JButton("Client Operations");
        clientButton.addActionListener(e -> openClientWindow());
        menuPanel.add(clientButton, c2);
        JButton productButton = new JButton("Product Operations");
        productButton.addActionListener(e -> openProductWindow());
        menuPanel.add(productButton, c3);
        JButton orderButton = new JButton("Order operations");
        orderButton.addActionListener(e -> openOrderWindow());
        menuPanel.add(orderButton, c4);
        JButton showBills = new JButton("Show all bills");
        showBills.addActionListener(e -> openBillsWindow());
        menuPanel.add(showBills, c5);
        this.contentPane1.add(menuPanel, c0);
    }

    /**
     * Method used to open the first client window
     */
    public void openClientWindow(){
        JFrame clientFrame = new JFrame();
        createFrame(clientFrame);
        this.contentPane2 = new JPanel(new GridBagLayout());
        prepareClientPanel1();
        prepareClientPanel2();
        prepareClientMenu();
        clientFrame.setContentPane(this.contentPane2);
    }

    /**
     * Method used to prepare any frame with certain attributes
     * @param frame we want to prepare
     */
    public void createFrame(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(500, 500);
        frame.setResizable(true);
        frame.setTitle("Orders management");
        frame.setLocation(350, 0);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Method used to prepare the first client panel
     */
    public void prepareClientPanel1(){
        JPanel clientPanel1 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0, 1.0);
        GridBagConstraints c2 = setConstraints(0,1,1,1.0, 1.0);
        GridBagConstraints c3 = setConstraints(1,0,1,1.0,1.0);
        GridBagConstraints c4 = setConstraints(1,1,1,1.0,1.0);
        GridBagConstraints c5 = setConstraints(0,2,2,1.0,1.0);
        clientPanel1.add(new JLabel("Client name:"), c1);
        this.clientName = new JTextField();
        clientPanel1.add(this.clientName, c2);
        clientPanel1.add(new JLabel("Client mail:"), c3);
        this.clientMail = new JTextField();
        clientPanel1.add(this.clientMail, c4);
        JButton addClientButton = new JButton("Add client");
        addClientButton.setActionCommand("ADD CLIENT");
        addClientButton.addActionListener(this.controller);
        clientPanel1.add(addClientButton, c5);
        this.contentPane2.add(clientPanel1, c0);
    }

    /**
     * Method used to prepare the second client panel
     */
    public void prepareClientPanel2(){
        this.clientPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,1,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0,1.0);
        this.currentClients = controller.getClientList();
        this.clientTable = buildTableFromObjects(this.currentClients);
        JScrollPane scrollPane = new JScrollPane(this.clientTable);
        this.clientPanel2.add(scrollPane, c1);
        this.contentPane2.add(this.clientPanel2, c0);
    }

    /**
     * Method used to prepare the popup menu for the client JTable
     */
    public void prepareClientMenu(){
        this.clientMenu = new JPopupMenu();
        JMenuItem deleteClient = new JMenuItem("Delete client");
        deleteClient.addActionListener(e -> {
            this.controller.deleteClient(getSelectedClient().getId());
        });
        JMenuItem updateClient = new JMenuItem("Update client");
        updateClient.addActionListener(e -> openClientFrame2());
        clientMenu.add(deleteClient);
        clientMenu.add(updateClient);
        addClientMouseListener();
    }

    /**
     * Method used to retrieve the client name inserted in the gui
     * @return client name
     */
    public JTextField getClientName(){
        return this.clientName;
    }

    /**
     * Method used to retrieve the client mail inserted in the gui
     * @return client mail
     */
    public JTextField getClientMail(){
        return this.clientMail;
    }

    /**
     * Method used to generate the header of a table using reflection and then populates the table with
     * objects from a list
     * @param objects to populate the table
     * @return an empty or populated JTable
     */
    public JTable buildTableFromObjects(List<?> objects) {
        if (objects == null || objects.isEmpty()) {
            return new JTable();
        }
        Object o = objects.getFirst();
        Field[] fields = o.getClass().getDeclaredFields();
        String[] columnNames = new String[fields.length];
        for(int i =0; i < fields.length; i++){
            fields[i].setAccessible(true);
            columnNames[i] = fields[i].getName();
        }
        String[][] rowData = new String[objects.size()][fields.length];
        for(int i=0; i<objects.size(); i++){
            Object obj = objects.get(i);
            for(int j=0; j<fields.length; j++){
                try {
                    Object value = fields[j].get(obj);
                    rowData[i][j] = value.toString();
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new JTable(rowData, columnNames);
    }

    /**
     * Method used to refresh the client table after a performed operation
     */
    public void refreshClientTable(){
        GridBagConstraints c1 = setConstraints(0,0,1,1.0,1.0);
        this.currentClients = controller.getClientList();
        this.clientTable = buildTableFromObjects(this.currentClients);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        this.clientPanel2.removeAll();
        this.clientPanel2.add(scrollPane, c1);
        addClientMouseListener();
        this.clientPanel2.revalidate();
        this.clientPanel2.repaint();
    }

    /**
     * Method used to add a mouse listener for a right click on the client table
     */
    public void addClientMouseListener(){
        clientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    clientMenu.show(clientTable, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Method used to open the second client frame
     */
    public void openClientFrame2(){
        JFrame clientFrame2 = new JFrame();
        createFrame(clientFrame2);
        this.contentPane3 = new JPanel(new GridBagLayout());
        prepareClientPanel3();
        clientFrame2.setContentPane(this.contentPane3);
    }

    /**
     * Method used to prepare the client panel for the update
     */
    public void prepareClientPanel3(){
        JPanel clientPanel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0, 1.0);
        GridBagConstraints c2 = setConstraints(0,1,1,1.0, 1.0);
        GridBagConstraints c3 = setConstraints(1,0,1,1.0,1.0);
        GridBagConstraints c4 = setConstraints(1,1,1,1.0,1.0);
        GridBagConstraints c5 = setConstraints(2,0,1,1.0,1.0);
        GridBagConstraints c6 = setConstraints(2,1,1,1.0,1.0);
        GridBagConstraints c7 = setConstraints(0,2,3,1.0,1.0);
        clientPanel3.add(new JLabel("New client id:"), c1);
        clientPanel3.add(new JLabel("New client name:"), c3);
        clientPanel3.add(new JLabel("New client mail:"), c5);
        this.updatedClientId = new JTextField(Integer.toString(getSelectedClient().getId()));
        this.updatedClientName = new JTextField(getSelectedClient().getName());
        this.updatedClientMail = new JTextField(getSelectedClient().getMail());
        clientPanel3.add(this.updatedClientId, c2);
        clientPanel3.add(this.updatedClientName, c4);
        clientPanel3.add(this.updatedClientMail, c6);
        JButton updateClientButton = new JButton("Update client");
        updateClientButton.setActionCommand("UPDATE CLIENT");
        updateClientButton.addActionListener(this.controller);
        clientPanel3.add(updateClientButton, c7);
        this.contentPane3.add(clientPanel3, c0);
    }

    /**
     * Method used to retrieve the selected client from the JTable
     * @return the selected client
     */
    public Client getSelectedClient(){
        int selectedClient = this.clientTable.getSelectedRow();
        return currentClients.get(selectedClient);
    }

    /**
     * Method used to retrieve the updated client id inserted in the gui
     * @return updated client id
     */
    public JTextField getUpdatedClientId(){
        return this.updatedClientId;
    }

    /**
     * Method used to retrieve the updated client name inserted in the gui
     * @return updated client name
     */
    public JTextField getUpdatedClientName(){
        return this.updatedClientName;
    }

    /**
     * Method used to retrieve the updated client mail inserted in the gui
     * @return updated client mail
     */
    public JTextField getUpdatedClientMail(){
        return this.updatedClientMail;
    }

    /**
     * Method used to open the first product window
     */
    public void openProductWindow(){
        JFrame productFrame1 = new JFrame();
        createFrame(productFrame1);
        this.contentPane4 = new JPanel(new GridBagLayout());
        prepareProductPanel1();
        prepareProductPanel2();
        prepareProductMenu();
        productFrame1.setContentPane(this.contentPane4);
    }

    /**
     * Method used to prepare the first product panel
     */
    public void prepareProductPanel1(){
        JPanel productPanel1 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0, 1.0);
        GridBagConstraints c2 = setConstraints(0,1,1,1.0, 1.0);
        GridBagConstraints c3 = setConstraints(1,0,1,1.0,1.0);
        GridBagConstraints c4 = setConstraints(1,1,1,1.0,1.0);
        GridBagConstraints c5 = setConstraints(2,0,1,1.0,1.0);
        GridBagConstraints c6 = setConstraints(2,1,1,1.0,1.0);
        GridBagConstraints c9 = setConstraints(0,2,4,1.0,1.0);
        productPanel1.add(new JLabel("Product name:"), c1);
        productPanel1.add(new JLabel("Product price:"), c3);
        productPanel1.add(new JLabel("Product stock:"), c5);
        this.productName = new JTextField();
        this.productPrice = new JTextField();
        this.productStock = new JTextField();
        productPanel1.add(this.productName, c2);
        productPanel1.add(this.productPrice, c4);
        productPanel1.add(this.productStock, c6);
        JButton addProductButton = new JButton("Add product");
        addProductButton.setActionCommand("ADD PRODUCT");
        addProductButton.addActionListener(this.controller);
        productPanel1.add(addProductButton, c9);
        this.contentPane4.add(productPanel1, c0);
    }

    /**
     * Method used to prepare the second product panel
     */
    public void prepareProductPanel2(){
        this.productPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,1,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0,1.0);
        this.currentProducts = controller.getProductList();
        this.productTable = buildTableFromObjects(this.currentProducts);
        JScrollPane scrollPane = new JScrollPane(this.productTable);
        this.productPanel2.add(scrollPane, c1);
        this.contentPane4.add(this.productPanel2, c0);
    }

    /**
     * Method used to retrieve the product name inserted in the gui
     * @return product name
     */
    public JTextField getProductName(){
        return this.productName;
    }

    /**
     * Method used to retrieve the product price inserted in the gui
     * @return product price
     */
    public JTextField getProductPrice(){
        return this.productPrice;
    }

    /**
     * Method used to retrieve the product stock inserted in the gui
     * @return product stock
     */
    public JTextField getProductStock(){
        return this.productStock;
    }

    /**
     * Method used to refresh the product table after a performed action
     */
    public void refreshProductTable(){
        GridBagConstraints c1 = setConstraints(0,0,1,1.0,1.0);
        this.currentProducts = controller.getProductList();
        this.productTable = buildTableFromObjects(this.currentProducts);
        JScrollPane scrollPane = new JScrollPane(productTable);
        this.productPanel2.removeAll();
        this.productPanel2.add(scrollPane, c1);
        addProductMouseListener();
        this.productPanel2.revalidate();
        this.productPanel2.repaint();
    }

    /**
     * Method used to add a mouse listener for a right click on the product table
     */
    public void addProductMouseListener(){
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    productMenu.show(productTable, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Method used to prepare the popup menu for the product table
     */
    public void prepareProductMenu(){
        this.productMenu = new JPopupMenu();
        JMenuItem deleteProduct = new JMenuItem("Delete product");
        deleteProduct.addActionListener(e -> {
            this.controller.deleteProduct(getSelectedProduct().getId());
        });
        JMenuItem updateProduct = new JMenuItem("Update product");
        updateProduct.addActionListener(e -> openProductFrame2());
        productMenu.add(deleteProduct);
        productMenu.add(updateProduct);
        addProductMouseListener();
    }

    /**
     * Method used to retrieve the selected product from the JTable
     * @return the selected product
     */
    public Product getSelectedProduct(){
        int selectedProduct = this.productTable.getSelectedRow();
        return currentProducts.get(selectedProduct);
    }

    /**
     * Method used to prepare the second product frame
     */
    public void openProductFrame2(){
        JFrame productFrame2 = new JFrame();
        createFrame(productFrame2);
        this.contentPane5 = new JPanel(new GridBagLayout());
        prepareProductPanel3();
        productFrame2.setContentPane(this.contentPane5);
    }

    /**
     * Method used to prepare the third product panel
     */
    public void prepareProductPanel3(){
        JPanel productPanel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0, 1.0);
        GridBagConstraints c2 = setConstraints(0,1,1,1.0, 1.0);
        GridBagConstraints c3 = setConstraints(1,0,1,1.0,1.0);
        GridBagConstraints c4 = setConstraints(1,1,1,1.0,1.0);
        GridBagConstraints c5 = setConstraints(2,0,1,1.0,1.0);
        GridBagConstraints c6 = setConstraints(2,1,1,1.0,1.0);
        GridBagConstraints c7 = setConstraints(3,0,1,1.0,1.0);
        GridBagConstraints c8 = setConstraints(3,1,1,1.0,1.0);
        GridBagConstraints c9 = setConstraints(0,2,4,1.0,1.0);
        productPanel3.add(new JLabel("New product id:"), c1);
        productPanel3.add(new JLabel("New product name:"), c3);
        productPanel3.add(new JLabel("New product price:"), c5);
        productPanel3.add(new JLabel("New product stock:"), c7);
        this.newProductId = new JTextField(Integer.toString(getSelectedProduct().getId()));
        this.newProductName = new JTextField(getSelectedProduct().getName());
        this.newProductPrice = new JTextField(Double.toString(getSelectedProduct().getPrice()));
        this.newProductStock = new JTextField(Integer.toString(getSelectedProduct().getStock()));
        productPanel3.add(this.newProductId, c2);
        productPanel3.add(this.newProductName, c4);
        productPanel3.add(this.newProductPrice, c6);
        productPanel3.add(this.newProductStock, c8);
        JButton updateProductButton = new JButton("Update product");
        updateProductButton.setActionCommand("UPDATE PRODUCT");
        updateProductButton.addActionListener(this.controller);
        productPanel3.add(updateProductButton, c9);
        this.contentPane5.add(productPanel3, c0);
    }

    /**
     * Method used to retrieve the updated product id inserted in the gui
     * @return updated product id
     */
    public JTextField getNewProductId(){
        return this.newProductId;
    }

    /**
     * Method used to retrieve the updated product name inserted in the gui
     * @return updated product name
     */
    public JTextField getNewProductName(){
        return this.newProductName;
    }

    /**
     * Method used to retrieve the updated product price inserted in the gui
     * @return updated product price
     */
    public JTextField getNewProductPrice(){
        return this.newProductPrice;
    }

    /**
     * Method used to retrieve the updated product stock inserted in the gui
     * @return updated product stock
     */
    public JTextField getNewProductStock(){
        return this.newProductStock;
    }

    /**
     * Method used to prepare the first order window
     */
    public void openOrderWindow(){
        JFrame orderFrame1 = new JFrame();
        createFrame(orderFrame1);
        this.contentPane6 = new JPanel(new GridBagLayout());
        prepareOrderPanel1();
        prepareOrderPanel2();
        orderFrame1.setContentPane(this.contentPane6);
    }

    /**
     * Method used to prepare the first order panel
     */
    public void prepareOrderPanel1(){
        this.orderPanel1 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,2,1.0,1.0);
        GridBagConstraints c2 = setConstraints(0,1,2,1.0,1.0);
        this.clientJComboBox = new JComboBox<>();
        this.currentClients = controller.getClientList();
        this.currentProducts = controller.getProductList();
        currentClients.forEach(clientJComboBox::addItem);
        this.productJComboBox = new JComboBox<>();
        currentProducts.forEach(productJComboBox::addItem);
        this.orderPanel1.add(this.clientJComboBox, c1);
        this.orderPanel1.add(this.productJComboBox, c2);
        this.contentPane6.add(this.orderPanel1, c0);
    }

    /**
     * Method used to prepare the second order panel
     */
    public void prepareOrderPanel2(){
        JPanel orderPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints c0 = setConstraints(0,1,1,1.0,1.0);
        GridBagConstraints c1 = setConstraints(0,0,1,1.0,1.0);
        GridBagConstraints c2 = setConstraints(1,0,1,1.0,1.0);
        GridBagConstraints c3 = setConstraints(1,1,1,1.0,1.0);
        GridBagConstraints c4 = setConstraints(0,1,1,1.0,1.0);
        orderPanel2.add(new JLabel("Desired quantity:"), c1);
        this.productQuantity = new JTextField();
        orderPanel2.add(this.productQuantity, c2);
        JButton finalizeOrderButton = new JButton("Finalize order");
        finalizeOrderButton.addActionListener(this.controller);
        finalizeOrderButton.setActionCommand("MAKE ORDER");
        orderPanel2.add(finalizeOrderButton, c3);
        JButton showOrdersButton = new JButton("Show all orders");
        showOrdersButton.addActionListener(e -> openOrderWindow2());
        orderPanel2.add(showOrdersButton, c4);
        this.contentPane6.add(orderPanel2, c0);
    }

    /**
     * Method used to prepare the second order frame
     */
    public  void openOrderWindow2(){
        JFrame orderFrame2 = new JFrame();
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        createFrame(orderFrame2);
        JPanel contentPane8 = new JPanel(new GridBagLayout());
        List<Order> currentOrders = controller.getOrderList();
        JTable orderTable = buildTableFromObjects(currentOrders);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        contentPane8.add(scrollPane, c0);
        orderFrame2.setContentPane(contentPane8);
    }

    /**
     * Method used to retrieve the selected client from the client combo box
     * @return the selected client
     */
    public JComboBox<Client> getClientJComboBox(){
        return this.clientJComboBox;
    }

    /**
     * Method used to retrieve the selected product from the client combo box
     * @return the selected product
     */
    public JComboBox<Product> getProductJComboBox(){
        return this.productJComboBox;
    }

    /**
     * Method used to insert the desired product quantity
     * @return the inserted quantity
     */
    public JTextField getProductQuantity(){
        return this.productQuantity;
    }

    /**
     * Method used to refresh the product combo box after an order is placed
     */
    public void refreshJComboBoxes(){
        GridBagConstraints c2 = setConstraints(0,1,2,1.0,1.0);
        this.currentProducts = controller.getProductList();
        this.orderPanel1.remove(this.productJComboBox);
        this.productJComboBox = new JComboBox<>();
        currentProducts.forEach(productJComboBox::addItem);
        this.orderPanel1.add(productJComboBox, c2);
        this.orderPanel1.revalidate();
        this.orderPanel1.repaint();

    }

    /**
     * Method used to open the bills window
     */
    public void openBillsWindow(){
        GridBagConstraints c0 = setConstraints(0,0,1,1.0,1.0);
        JFrame billsFrame1 = new JFrame();
        createFrame(billsFrame1);
        JPanel contentPane7 = new JPanel(new GridBagLayout());
        List<Bill> bills = controller.getBillList();
        JTable billsTable = buildTableFromObjects(bills);
        JScrollPane scrollPane = new JScrollPane(billsTable);
        contentPane7.add(scrollPane, c0);
        billsFrame1.setContentPane(contentPane7);
    }
}
