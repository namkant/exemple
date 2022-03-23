/**
 * Title: TP2 (AjoutFonction)
 * Description: Fenêtre permettant l'ajout d'une fonction à un employé.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class AjoutFonction extends JFrame implements ActionListener
{
  // Parent
  private PanelAjoutEmploye parent;

  // Constantes
  private final String FONCTION[] = { "Cadre", "Bureau", "Professionnel" };
  private final String NIVEAU[] = { "1", "2", "3", "4", "5", "6", "7", "8" };

  // JLabels
  private JLabel lblDateSeparateur1 = new JLabel("/");
  private JLabel lblDateSeparateur2 = new JLabel("/");
  private JLabel lblTitre = new JLabel("Titre :");
  private JLabel lblDept = new JLabel("Département :");
  private JLabel lblNiveau = new JLabel("Niveau :");

  // JTextField
  private JTextField txtDateJour = new JTextField(new LimitedDoc(2, true), "",
                                                                             2);
  private JTextField txtDateMois = new JTextField(new LimitedDoc(2, true), "",
                                                                             2);
  private JTextField txtDateAn = new JTextField(new LimitedDoc(4, true), "", 4);
  private JTextField txtDept = new JTextField(new LimitedDoc(10), "", 10);

  // JComboBox
  private JComboBox cboFonction = new JComboBox(FONCTION);
  private JComboBox cboNiveau = new JComboBox(NIVEAU);

  // JButton
  private JButton btnOk = new JButton("Accepter");
  private JButton btnCancel = new JButton("Annuler");

  // JPanel
  private JPanel panDate = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private JPanel panMain = new JPanel(new GridBagLayout());

  // Contraintes
  private GridBagConstraints constraints = new GridBagConstraints();

  // Constructeur
  public AjoutFonction(PanelAjoutEmploye leParent)
  {
    // Définition du parent
    parent = leParent;

    // Panneau pour la date
    panDate.setBorder(new TitledBorder("Date de début de la fonction : "));
    panDate.add(txtDateJour);
    panDate.add(lblDateSeparateur1);
    panDate.add(txtDateMois);
    panDate.add(lblDateSeparateur2);
    panDate.add(txtDateAn);

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.gridwidth = 2;
    panMain.add(panDate, constraints);
    // Fin du panneau pour la date

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(lblTitre, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(cboFonction, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(lblDept, constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(txtDept, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(lblNiveau, constraints);

    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(cboNiveau, constraints);

    btnOk.addActionListener(this);
    panBtn.add(btnOk);
    btnCancel.addActionListener(this);
    panBtn.add(btnCancel);

    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.weightx = 1;
    constraints.gridwidth = 2;
    panMain.add(panBtn, constraints);

    getContentPane().add(panMain);
    setTitle("Ajout d'une fonction");
    setSize(250, 250);
    setVisible(true);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  // Méthode actionPerformed
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnOk)
    {
      ajouterFonction();
      dispose();
    }
    else if(e.getSource() == btnCancel)
      dispose();
  } // Fin de la méthode actionPerformed

  private void ajouterFonction()
  {
    Date laDate;

    // gestion des erreurs possibles
    try
    {
      laDate = new Date(Integer.parseInt(txtDateAn.getText()) - 1900,
                        Integer.parseInt(txtDateMois.getText()) - 1,
                        Integer.parseInt(txtDateJour.getText()));
    }
    catch (NumberFormatException ex)
    {
      JOptionPane.showMessageDialog(this, "La date est invalide.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
      // Initialise la date à la date actuelle
      laDate = new Date();
    }

    parent.nouvelleFonction(new Fonction(laDate,
        FONCTION[cboFonction.getSelectedIndex()], txtDept.getText(),
        Integer.parseInt(NIVEAU[cboNiveau.getSelectedIndex()])));
  }
}