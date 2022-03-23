/**
 * Title: TP2 (Rapport)
 * Description: Fenêtre permettant d'afficher le rapport de tous les employés
 *              avec l'historique de leurs fonctions.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Rapport extends JFrame implements ActionListener
{
  private Employe temp;
  private DateFormat dateFormatter;

  private JTextArea affiche = new JTextArea();
  private JScrollPane scroll = new JScrollPane(affiche);
  private JButton btnOk = new JButton("Ok");

  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

  // Constructeur. Il recoit le Vector d'employés
  public Rapport(Vector empl)
  {
    //Format pour les dates
    dateFormatter = DateFormat.getDateInstance(DateFormat.LONG);

    affiche.setEditable(false);
    affiche.setText("Rapport des tous les employés :\n");
    affiche.append("===============================\n");

    // Va chercher et affiche les informations nécessaires.
    for(int i = 0; i < empl.size(); i++)
    {
      temp = (Employe)empl.get(i);
      affiche.append("\nEmployé #" + (i + 1) + " :\n");
      affiche.append(temp.getNas() + " : " + temp.getNom() + ", "
                     + temp.getPrenom() + "\n");
      for(int j = 0; j < temp.getNbFct(); j++)
      {
        affiche.append("\t"
                 + dateFormatter.format(temp.getFonction(j).getDate()) + " --> "
                 + temp.getFonction(j).getTitre() + "\n");
      }
    }

    btnOk.addActionListener(this);
    panBtn.add(btnOk);

    panMain.add(scroll, BorderLayout.CENTER);
    panMain.add(panBtn, BorderLayout.SOUTH);

    getContentPane().add(panMain);
    setSize(300, 250);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnOk)
      dispose();
  }
}