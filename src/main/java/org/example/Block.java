package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Block extends JFrame implements ActionListener {

    JTextField nombretxt;
    JTextArea textArea;
    JButton button_guardar;
    JButton button_leer;
    JButton button_eliminar;
    JButton button_cambiar_color;
    JButton button_eliminar_color;

    public Block() {
        this.setTitle("Block");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());//Flujo
        this.nombretxt = new JTextField(20);
        textArea = new JTextArea(6,25);
        button_guardar= new JButton("Guardar");
        button_eliminar= new JButton("Borrar");
        button_cambiar_color= new JButton("Cambiar color");
        button_eliminar_color= new JButton("Eliminar color");
        button_leer= new JButton("Cargar");

        button_guardar.addActionListener(this);
        button_eliminar.addActionListener(this);
        button_cambiar_color.addActionListener(this);
        button_eliminar_color.addActionListener(this);
        button_leer.addActionListener(this);


        this.add(nombretxt);
        this.add(textArea);
        this.add(button_guardar);
        this.add(button_eliminar);
        this.add(button_cambiar_color);
        this.add(button_eliminar_color);
        this.add(button_leer);

        //this.pack();
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_guardar && !this.nombretxt.getText().isEmpty()) {
            try {
            File archivo = new File("/Users/misael.perilla/Desktop/"+this.nombretxt.getText()+".txt");
            FileWriter fw = new FileWriter(archivo);
            fw.append(textArea.getText());
            fw.close();
            JOptionPane.showMessageDialog(null, "El archivo se guardado correctamente");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == button_eliminar) {
            textArea.setText("");
        }
        if (e.getSource() == button_cambiar_color) {
            this.textArea.setForeground(new Color(220,5,45));
        }

        if (e.getSource() == button_eliminar_color) {
            this.textArea.setForeground(Color.BLACK);
        }

        if (e.getSource() == button_leer) {
            try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "solo TXT",  "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
            }

                FileReader fr = new FileReader(chooser.getSelectedFile());
                BufferedReader bf = new BufferedReader(fr);
                String sCadena;
                String textoTotal="";

                while ((sCadena = bf.readLine())!=null) {
                    System.out.println(sCadena);
                    textoTotal=textoTotal+sCadena;
                }
                this.textArea.setText(textoTotal);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


    }
}
