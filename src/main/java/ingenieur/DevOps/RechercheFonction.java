/**
 * Title: TP2 (RechercheFonction)
 * Description: Fenêtre permettant de rechercher les employés par fonction et
 *              d'afficher leur salaire.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class RechercheFonction extends JFrame implements ActionListener
{
  private final String FONCTION[] = { "Cadre", "Bureau", "Professionnel" };

  private Vector lesEmpl;
  private boolean avecSalaires;

  // Composantes graphiques
  private JLabel lblFct = new JLabel("Fonction :");
  private JComboBox cboFonction = new JComboBox(FONCTION);
  private JButton btnSearch = new JButton("Rechercher");
  private JTextArea resultats = new JTextArea();
  private JScrollPane scroll = new JScrollPane(resultats);
  private JButton btnOk = new JButton("Terminé");

  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

  // Constructeur (si salaire == true, leurs salaires seront aussi affichés)
  public RechercheFonction(Vector empl, boolean salaires)
  {
    lesEmpl = empl;
    avecSalaires = salaires;

    panInfos.add(lblFct);
    panInfos.add(cboFonction);
    btnSearch.addActionListener(this);
    panInfos.add(btnSearch);

    btnOk.addActionListener(this);
    panBtn.add(btnOk);

    resultats.setEditable(false);

    panMain.add(panInfos, BorderLayout.NORTH);
    panMain.add(scroll, BorderLayout.CENTER);
    panMain.add(panBtn, BorderLayout.SOUTH);

    getContentPane().add(panMain);
    setTitle(avecSalaires ? "Salaires par fonction" : "Recherche par fonction");
    setSize(300, 250);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  // Méthode actionPerformed
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnSearch)
    {
      Vector result;
      resultats.setText("Recherche des employés dont la fonction actuelle\nest"
                        + " \"" + FONCTION[cboFonction.getSelectedIndex()]
                        + "\"...\n");
      result = Outils.SearchFct(lesEmpl,
                                FONCTION[cboFonction.getSelectedIndex()]);
      if(result.size() != 0)
        afficherResult(result);
      else
      {
        resultats.append("\nAucun employé n'occupe cette fonction.");
      }
    }
    else if(e.getSource() == btnOk)
    {
      dispose();
    }
  } // Fin de la méthode actionPerformed

  // Méthode afficherResult (affiche les resultats en tenant compte si elle
  // doit afficher aussi leur salaire actuel
  private void afficherResult(Vector empl)
  {
    DecimalFormat formatArgent = new DecimalFormat("0.00 $");
    Employe temp;

    for(int i = 0; i < empl.size(); i++)
    {
      temp = (Employe)empl.get(i);
      resultats.append("\n" + temp.getNas() + "\n" + temp.getNom() + ", "
                       + temp.getPrenom() + "\n");
      if(avecSalaires)
        resultats.append(formatArgent.format(temp.getSalaire(
            temp.getNbSalaires() - 1).getSalaire()) + "\n");
    }
  } // Fin de la méthode afficherResult

} // Fin de la classe RechercheFonction