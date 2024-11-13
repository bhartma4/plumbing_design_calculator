import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class DFUCalculatorGUI {
    private static Map<String, Double> items;
    private static double totalQuantity = 0;
    private static Map<String, Integer> selectedItems = new HashMap<>();

    public static void main(String[] args) {
        // Initialize items and their DFU values in alphabetical order using TreeMap
        items = new TreeMap<>();
        items.put("Automatic clothes washers, commercial", 3.0);
        items.put("Automatic clothes washers, residential", 2.0);
        items.put("Bathroom group (1.6 gpf water closet)", 5.0);
        items.put("Bathroom group (water closet flushing > 1.6 gpf)", 6.0);
        items.put("Bathtub (with/without shower or whirlpool)", 2.0);
        items.put("Bidet", 1.0);
        items.put("Combination sink and tray", 2.0);
        items.put("Dental lavatory", 1.0);
        items.put("Dental unit or cuspidor", 1.0);
        items.put("Dishwashing machine, domestic", 2.0);
        items.put("Drinking fountain", 0.5);
        items.put("Emergency floor drain", 0.0);
        items.put("Floor drains", 2.0);
        items.put("Floor sinks", 0.0);
        items.put("Kitchen sink, domestic", 2.0);
        items.put("Kitchen sink, domestic (with food waste disposer or dishwasher)", 2.0);
        items.put("Laundry tray (1 or 2 compartments)", 2.0);
        items.put("Lavatory", 1.0);
        items.put("Shower (flow rate 5.7 gpm or less)", 2.0);
        items.put("Shower (flow rate > 5.7 gpm to 12.3 gpm)", 3.0);
        items.put("Shower (flow rate > 12.3 gpm to 25.8 gpm)", 5.0);
        items.put("Shower (flow rate > 25.8 gpm to 55.6 gpm)", 6.0);
        items.put("Service sink", 2.0);
        items.put("Sink", 2.0);
        items.put("Urinal", 4.0);
        items.put("Urinal, 1 gallon per flush or less", 2.0);
        items.put("Urinal, nonwater supplied", 0.5);
        items.put("Wash sink (circular or multiple), each set of faucets", 2.0);
        items.put("Water closet, flushometer tank, public or private", 4.0);
        items.put("Water closet, private (1.6 gpf)", 3.0);
        items.put("Water closet, private (flushing > 1.6 gpf)", 4.0);
        items.put("Water closet, public (1.6 gpf)", 4.0);
        // (Add more items as needed)

        // Create the frame
        JFrame frame = new JFrame("DFU Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Increased size to accommodate more components
        frame.setLayout(new BorderLayout());

        // Create top panel for selection and input
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        // Create components
        JComboBox<String> itemComboBox = new JComboBox<>(items.keySet().toArray(new String[0]));
        JTextField quantityField = new JTextField(5);
        JButton addButton = new JButton("Add Item");
        JLabel totalLabel = new JLabel("Total DFU: 0");

        // Add components to the top panel
        topPanel.add(new JLabel("Select Item:"));
        topPanel.add(itemComboBox);
        topPanel.add(new JLabel("Quantity:"));
        topPanel.add(quantityField);
        topPanel.add(addButton);
        topPanel.add(totalLabel);

        // Create center panel for displaying selected items
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(new JLabel("Selected Items:"), BorderLayout.NORTH);

        // Text area to display selected items
        JTextArea selectedItemsArea = new JTextArea();
        selectedItemsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(selectedItemsArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Create bottom panel for additional actions
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton printButton = new JButton("Print Selected Items");
        bottomPanel.add(printButton);

        // Add action listener to the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) itemComboBox.getSelectedItem();
                String quantityText = quantityField.getText().trim();

                try {
                    int quantity = Integer.parseInt(quantityText);
                    if (quantity < 1) throw new NumberFormatException();

                    double dfuValue = items.getOrDefault(selectedItem, 0.0);
                    double itemQuantity = dfuValue * quantity;
                    totalQuantity += itemQuantity;

                    // Update total label
                    totalLabel.setText("Total DFU: " + totalQuantity);

                    // Update selected items map
                    selectedItems.put(selectedItem, selectedItems.getOrDefault(selectedItem, 0) + quantity);

                    // Update selected items area
                    updateSelectedItemsArea(selectedItemsArea);

                    // Show confirmation message
                    JOptionPane.showMessageDialog(frame, "Added " + quantity + " " + selectedItem + "(s) - " + itemQuantity + " DFU");

                    // Clear quantity field
                    quantityField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.");
                }
            }
        });

        // Add action listener to the print button
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItems.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No items selected.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Selected Items:\n");
                    for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
                        sb.append(entry.getKey()).append(" - Quantity: ").append(entry.getValue()).append("\n");
                    }
                    sb.append("\nTotal DFU: ").append(totalQuantity);
                    JOptionPane.showMessageDialog(frame, sb.toString(), "Selected Items", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Arrange panels in the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Method to update the selected items area
    private static void updateSelectedItemsArea(JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
            sb.append(entry.getKey()).append(" - Quantity: ").append(entry.getValue()).append("\n");
        }
        textArea.setText(sb.toString());
    }
}