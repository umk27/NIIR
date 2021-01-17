package NIIR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        createFrame("Введите данные");
    }


    static JFrame f;
    static JTextField tf;
    static JTextArea ta1, ta2;
    static JLabel l1;
    static JButton b1, b2, b3, b4;


    public static void createFrame(String title) {
        f = new JFrame(title);
        b1 = new JButton("Отправить в файл");
        b2 = new JButton("Отправить в базу данных");
        b3 = new JButton("Считать из файла");
        b4 = new JButton("Считать из базы данных");
        tf = new JTextField(40);
        ta1 = new JTextArea(5, 40);
        ta2 = new JTextArea(3, 40);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 20));
        //  JPanel p2 = new JPanel();
        //  JPanel p3 = new JPanel();

        p1.add(ta1);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(ta2);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = ta1.getText();
                char[] c = s.toCharArray();
                if (c.length > 50) {
                    JFrame f1 = new JFrame();
                    JTextArea t = new JTextArea("!!Длина текста не должна быть более 50 символов!");
                    f1.add(t);
                    f1.setBounds(600,400,400, 200);
                    f1.setVisible(true);
                    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                for (int i = 0; i < c.length; i++) {
                    if (Character.isDigit(c[i])) {
                        JFrame f1 = new JFrame();
                        JTextArea t = new JTextArea("!!При вводе не должно быть чисел!");
                        f1.add(t);
                        f1.setBounds(600,400,400, 200);
                        f1.setVisible(true);
                        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
                try {
                    FileWriter writer = new FileWriter("File1.txt");
                    writer.write(s);
                    writer.close();
                } catch (IOException ea) {
                    System.out.println(ea.getMessage());
                }

                ta2.setText("Текст сохранен в файл");

            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                try {
                    FileReader reader = new FileReader("File1.txt");
                    Scanner scanner = new Scanner(reader);
                    while (scanner.hasNextLine()) {
                        s = s + scanner.next();
                    }
                    reader.close();
                } catch (IOException ea) {

                }
                ta1.setText(s);
                ta2.setText("Текст считан из файла");
            }
        });

        f.add(p1);
        f.setBounds(600,400,500, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
