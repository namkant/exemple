/**
 * Title: TP2 (AjoutSalaire)
 * Description: Fenêtre permettant l'ajout d'un salaire à un employé.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class AjoutSalaire extends JFrame implements ActionListener
{
  // Parent
  private PanelAjoutEmploye parent;

  // Constantes
  private final String RAISON[] = { "Engagement", "Transfert",
                                    "Promotion", "Départ" };
  private final int CODE_RAISON[] = { 10, 20, 30, 50 };

  // JLabels
  private JLabel lblDateSeparateur1 = new JLabel("/");
  private JLabel lblDateSeparateur2 = new JLabel("/");
  private JLabel lblSalaire = new JLabel("Salaire :");
  private JLabel lblRaison = new JLabel("Raison :");

  // JTextField
  private JTextField txtDateJour = new JTextField(
                                                new LimitedDoc(2, true), "", 2);
  private JTextField txtDateMois = new JTextField(
                                                new LimitedDoc(2, true), "", 2);
  private JTextField txtDateAn = new JTextField(new LimitedDoc(4, true), "", 4);
  private JTextField txtSalaire = new JTextField(new LimitedDoc(10), "", 10);

  // JComboBox
  private JComboBox cboRaison = new JComboBox(RAISON);

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
  public AjoutSalaire(PanelAjoutEmploye leParent)
  {
    // Définition du parent
    parent = leParent;

    // Panneau pour la date
    panDate.setBorder(new TitledBorder("Date de début du salaire : "));
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
    panMain.add(lblSalaire, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(txtSalaire, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(lblRaison, constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.weightx = 1;
    constraints.gridwidth = 1;
    panMain.add(cboRaison, constraints);

    btnOk.addActionListener(this);
    panBtn.add(btnOk);
    btnCancel.addActionListener(this);
    panBtn.add(btnCancel);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.weightx = 1;
    constraints.gridwidth = 2;
    panMain.add(panBtn, constraints);

    getContentPane().add(panMain);
    setTitle("Ajout d'un salaire");
    setSize(250, 225);
    setVisible(true);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  // Méthode actionPerformed
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnOk)
    {
      ajouterSalaire();
      dispose();
    }
    else if(e.getSource() == btnCancel)
      dispose();
  } // Fin de la méthode actionPerformed

  private void ajouterSalaire()
  {
    Date laDate;
    double leSalaire;

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

    try
    {
      leSalaire = Double.parseDouble(txtSalaire.getText());
    }
    catch (NumberFormatException ex)
    {
      JOptionPane.showMessageDialog(this, "Le salaire est invalide.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
      // Initialise le salaire à 7.30
      leSalaire = 7.30;
    }

    parent.nouveauSalaire(new Salaire(laDate, leSalaire,
                          CODE_RAISON[cboRaison.getSelectedIndex()]));
  }
}