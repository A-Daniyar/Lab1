import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JFrame frame;
    private JTextField amountField;
    private JTextArea resultArea;
    private JPanel imagePanel;

    // Coin values and images
    private static final int[] MONEY_VALUES = {10000,5000,2000,1000,500,100,25, 10, 5, 1};
    // hundred-note, fifty-note, twenty-note, ten-note, five-note, two-note, one-note, quarters, dimes, nickels, pennies
    private static final String[] COIN_NAMES = {"Hundred-Dollar Note", "Fifty-Dollar Note", "Twenty-Dollar Note", "Ten-Dollar Note","Five-Dollar Note", "One-Dollar Note",
    "Quarter", "Dime", "Nickel", "Penny"};
    private static final String[] COIN_IMAGE_PATHS = {
            "LabOne/hundred_note.png", "fifty_note.png", "twenty_note.png", "ten_note.png", "five_note.png",
            "one_note.png", "quarter.png", "dime.png", "nickel.png", "penny.png"};

    private ImageIcon[] coinImages;

    public Main() {
        loadCoinImages(); // Load images at initialization

        frame = new JFrame("Change Maker");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Enter amount:");
        amountField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setMargin(new Insets(5, 5, 5, 5));

        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateChange();
            }
        });

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(calculateButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        frame.add(imagePanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadCoinImages() {
        coinImages = new ImageIcon[COIN_IMAGE_PATHS.length];
        for (int i = 0; i < COIN_IMAGE_PATHS.length; i++) {
            coinImages[i] = new ImageIcon(COIN_IMAGE_PATHS[i]);
        }
    }

    private void calculateChange() {
        String amountText = amountField.getText();
            double amount = Double.parseDouble(amountText);
            int cents = (int) Math.round(amount * 100);

            StringBuilder result = new StringBuilder();
            imagePanel.removeAll(); // Clear previous images

            for (int i = 0; i < MONEY_VALUES.length; i++) {
                int count = cents / MONEY_VALUES[i];
                if (count > 0) {
                    result.append(count).append(" ").append(COIN_NAMES[i])
                            .append(count > 1 ? "s" : "").append("\n");

                    // Display coin images
                    JLabel coinLabel = new JLabel(coinImages[i]);
                    coinLabel.setText(count + " " + COIN_NAMES[i] + (count > 1 ? "s" : ""));
                    coinLabel.setHorizontalTextPosition(JLabel.CENTER);
                    coinLabel.setVerticalTextPosition(JLabel.BOTTOM);
                    imagePanel.add(coinLabel);
                }
                cents %= MONEY_VALUES[i];
            }

            resultArea.setText(result.toString());
            imagePanel.revalidate(); // Refresh the image panel

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
