import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Game extends JFrame {

    private int number1, number2, answer;
    private JTextField textField;
    private JLabel number1Display, number2Display;
    Font thaiFont = loadThaiFont("/fonts/kanit/Kanit-Regular.ttf");

    public Game() {
        setTitle("บวกเลขเกม");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2));

        JLabel number1Label = new JLabel("numbers 1: ");
        topPanel.add(number1Label);

        number1 = (int)(Math.random() * 100);
        number1Display = new JLabel(String.valueOf(number1));
        topPanel.add(number1Display);

        JLabel number2Label = new JLabel("numbers 2: ");
        topPanel.add(number2Label);

        number2 = (int)(Math.random() * 100);
        number2Display = new JLabel(String.valueOf(number2));
        topPanel.add(number2Display);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());

        JLabel answerLabel = new JLabel("answer: ");
        centerPanel.add(answerLabel);

        textField = new JTextField(10);
        centerPanel.add(textField);

        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton submitButton = new JButton("examine");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    answer = Integer.parseInt(textField.getText());
                    checkAnswer();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bottomPanel.add(submitButton);

        JButton plusButton = new JButton("Hard Modes");
        plusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setNumber();
            }
        });
        bottomPanel.add(plusButton);

        JButton easyButton = new JButton("Easy Modes");
        plusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backNumber();
            }
        });
        bottomPanel.add(easyButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    private void checkAnswer() {
        int correctAnswer = number1 + number2;
        if (answer == correctAnswer) {
            JOptionPane.showMessageDialog(null, "The answer is correct! The answer is " + correctAnswer, "result", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else {
            JOptionPane.showMessageDialog(null, "The answer is incorrect. The answer is " + correctAnswer, "result", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Font loadThaiFont(String fontFilePath) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath));
            return font.deriveFont(Font.PLAIN, 14);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 14);
        }
    }

    private void resetGame() {
        number1 = (int)(Math.random() * 100);
        number2 = (int)(Math.random() * 100);

        number1Display.setText(String.valueOf(number1));
        number2Display.setText(String.valueOf(number2));

        textField.setText("");
    }

    private void setNumber(){
        number1 = (int)(Math.random() * 1000);
        number2 = (int)(Math.random() * 1000);
        number1Display.setText(String.valueOf(number1));
        number2Display.setText(String.valueOf(number2));
    }

    private void backNumber(){
        number1 = (int)(Math.random() * 10);
        number2 = (int)(Math.random() * 10);
        number1Display.setText(String.valueOf(number1));
        number2Display.setText(String.valueOf(number2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }
}
