/**
 * Title: TP2 (RechercheEmploye)
 * Description: Fenêtre permettant de rechercher un employé et de l'effacer.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RechercheEmploye extends JFrame implements ActionListener
{
  private Vector lesEmpl;
  private Employe result;
  private boolean suppr;

  // Composantes graphiques
  private JLabel lblNAS = new JLabel("# d'assurance sociale");
  private JTextField txtNAS = new JTextField(new LimitedDoc(6, true), "", 6);
  private JButton btnSearch = new JButton("Rechercher");
  private JTextArea resultats = new JTextArea(20, 20);
  private JScrollPane scroll = new JScrollPane(resultats);
  private JButton btnSupprimer = new JButton("Supprimer");
  private JButton btnOk = new JButton("Terminé");

  private JPanel panInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panMain = new JPanel(new BorderLayout(10, 10));

  // Constructeur (si delete == true, la fenetre permet aussi d'effacer un
  // employé.
  public RechercheEmploye(Vector empl, boolean delete)
  {
    lesEmpl = empl;
    suppr = delete;

    panInfos.add(lblNAS);
    txtNAS.addActionListener(this);
    panInfos.add(txtNAS);
    btnSearch.addActionListener(this);
    panInfos.add(btnSearch);

    btnSupprimer.addActionListener(this);
    btnSupprimer.setEnabled(false);
    if(suppr)
      panBtn.add(btnSupprimer);
    btnOk.addActionListener(this);
    panBtn.add(btnOk);

    resultats.setEditable(false);

    panMain.add(panInfos, BorderLayout.NORTH);
    panMain.add(scroll, BorderLayout.CENTER);
    panMain.add(panBtn, BorderLayout.SOUTH);

    getContentPane().add(panMain);
    setTitle(suppr ? "Suppression" : "Recherche");
    setSize(350, 250);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  // Méthode actionPerformed
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == txtNAS || e.getSource() == btnSearch)
    {
      resultats.setText("Recherche de l'employé #" + txtNAS.getText() + "...");
      resultats.update(resultats.getGraphics());
      result = rechercher(txtNAS.getText());
      if(result != null)
      {
        afficherResult(result);
        btnSupprimer.setEnabled(true);
      }
      else
      {
        resultats.append("\nAucun employé ne correspond à ce #...");
        resultats.update(resultats.getGraphics());
        btnSupprimer.setEnabled(false);
      }
    }
    else if(e.getSource() == btnSupprimer) // Confirmation avant de supprimer
    {
      int confirm;
      confirm = JOptionPane.showConfirmDialog(this,
          "Voulez-vous vraiment effacer cet employé ?", "Suppression",
          JOptionPane.YES_NO_OPTION);
      if(confirm == JOptionPane.YES_OPTION)
      {
        lesEmpl.removeElement(result);
        resultats.append("\n=====SUPPRIMÉ!=====");
        btnSupprimer.setEnabled(false);
      }
    }
    else if(e.getSource() == btnOk)
    {
      dispose();
    }
  } // Fin de la méthode actionPerformed

  // Méthode rechercher (effectue la recherche)
  private Employe rechercher(String nas)
  {
    int posi;

    posi = Outils.SearchByNAS(lesEmpl, nas);
    if(posi != -1)
      return (Employe)lesEmpl.get(posi);
    else
      return null;
  }

  // Méthode afficherResult (affiche les resultat de la recherche)
  private void afficherResult(Employe empl)
  {
    String reponse;
    reponse = "\n" + empl.getNas() + "\n" + empl.getNom() + ", "
            + empl.getPrenom();
    resultats.append(reponse);
    resultats.update(resultats.getGraphics());
  }

} // Fin de la classe RechercheEmploye