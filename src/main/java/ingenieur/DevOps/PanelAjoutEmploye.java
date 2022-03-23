/**
 * Title: TP2 (PanelAjoutEmploye)
 * Description: Fenêtre permettant l'ajout d'employés au système.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import java.text.*;

public class PanelAjoutEmploye extends JFrame implements ActionListener
{
  // Constantes
  private final String SEXE[] = { "Masculin", "Féminin" };

  // Formats
  private DateFormat dateFormatter;
  private DecimalFormat formatArgent;

  // Variables
  private Vector fonctions = new Vector(5);
  private Vector salaires = new Vector(10);
  private Vector tabEmpl;

  // Éléments graphiques
  // JLabels
  private JLabel lblNom = new JLabel("Nom :");
  private JLabel lblPrenom = new JLabel("Prenom :");
  private JLabel lblDateSeparateur1 = new JLabel("/");
  private JLabel lblDateSeparateur2 = new JLabel("/");
  private JLabel lblSexe = new JLabel("Sexe :");
  private JLabel lblNas = new JLabel("# ass. sociale :");
  private JLabel lblNoCiv = new JLabel("# civique :");
  private JLabel lblRue = new JLabel("Rue :");
  private JLabel lblVille = new JLabel("Ville :");
  private JLabel lblCodePostal = new JLabel("Code Postal :");
  private JLabel lblFonctions = new JLabel("Historique des fonctions :");
  private JLabel lblSalaires = new JLabel("Historique des salaires :");

  // JTextFields
  private JTextField txtNom = new JTextField(new LimitedDoc(20), "", 20);
  private JTextField txtPrenom = new JTextField(new LimitedDoc(20), "", 20);
  private JTextField txtDateJour = new JTextField(new LimitedDoc(2, true), "",
                                                                             2);
  private JTextField txtDateMois = new JTextField(new LimitedDoc(2, true), "",
                                                                             2);
  private JTextField txtDateAn = new JTextField(new LimitedDoc(4, true), "", 4);
  private JTextField txtNas = new JTextField(new LimitedDoc(6, true), "", 6);
  private JTextField txtNoCiv = new JTextField(new LimitedDoc(10), "", 10);
  private JTextField txtRue = new JTextField(new LimitedDoc(20), "", 20);
  private JTextField txtVille = new JTextField(new LimitedDoc(12), "", 12);
  private JTextField txtCodePostal = new JTextField(new LimitedDoc(6), "", 6);

  // JComboBox
  private JComboBox cboSexe = new JComboBox(SEXE);

  // JButton
  private JButton btnOk = new JButton("Accepter");
  private JButton btnCancel = new JButton("Annuler");
  private JButton btnAddFonction = new JButton("Ajouter une fonction");
  private JButton btnAddSalaire = new JButton("Ajouter un salaire");

  // JTable
  private JTable tableFonctions = new JTable(new FonctionTableModel(5));
  private JTable tableSalaires = new JTable(new SalaireTableModel(10));

  // JScrollPane
  private JScrollPane tabFct = new JScrollPane(tableFonctions,
                                       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  private JScrollPane tabSal = new JScrollPane(tableSalaires,
                                       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

  // JPanel
  private JPanel panMain = new JPanel();
  private JPanel panInfos = new JPanel();
  private JPanel panDate = new JPanel();
  private JPanel panAdresse = new JPanel();
  private JPanel panFct = new JPanel();
  private JPanel panSal = new JPanel();
  private JPanel panBtn = new JPanel();

  // Layout managers
  private GridBagConstraints constraints = new GridBagConstraints();

  // Constructeur
  public PanelAjoutEmploye(Vector empl)
  {
    tabEmpl = empl;
    // Ajout des composantes
    // Panneau pour les informations personnelles
    panInfos.setBorder(new TitledBorder("Informations personelles : "));
    panInfos.setLayout(new GridBagLayout());

    constraints.anchor = constraints.WEST;
    constraints.insets = new Insets(2, 2, 2, 2);
    constraints.fill = constraints.HORIZONTAL;

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 0;
    panInfos.add(lblNom, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.weightx = 1;
    panInfos.add(txtNom, constraints);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.weightx = 0;
    panInfos.add(lblPrenom, constraints);

    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.weightx = 1;
    panInfos.add(txtPrenom, constraints);

    // Panneau pour la date
    panDate.setBorder(new TitledBorder("Date de naissance (jj/mm/aaaa) : "));
    panDate.setLayout(new FlowLayout(FlowLayout.CENTER));
    panDate.add(txtDateJour);
    panDate.add(lblDateSeparateur1);
    panDate.add(txtDateMois);
    panDate.add(lblDateSeparateur2);
    panDate.add(txtDateAn);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    panInfos.add(panDate, constraints);
    // Fin du panneau pour la date

    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.weightx = 0;
    panInfos.add(lblSexe, constraints);

    constraints.gridx = 3;
    constraints.gridy = 1;
    constraints.weightx = 1;
    panInfos.add(cboSexe, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.weightx = 0;
    panInfos.add(lblNas, constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.weightx = 1;
    //constraints.gridwidth = 2;
    panInfos.add(txtNas, constraints);

    // Panneau pour l'adresse
    panAdresse.setLayout(new GridBagLayout());
    panAdresse.setBorder(new TitledBorder("Adresse : "));
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.weightx = 0;
    panAdresse.add(lblNoCiv, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.weightx = 1;
    panAdresse.add(txtNoCiv, constraints);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.weightx = 0;
    panAdresse.add(lblRue, constraints);

    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.weightx = 1;
    panAdresse.add(txtRue, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.weightx = 0;
    panAdresse.add(lblVille, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    constraints.weightx = 1;
    panAdresse.add(txtVille, constraints);

    constraints.gridx = 3;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.weightx = 0;
    panAdresse.add(lblCodePostal, constraints);

    constraints.gridx = 4;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.weightx = 1;
    panAdresse.add(txtCodePostal, constraints);
    // Fin du panneau pour l'adresse

    // Panneau pour l'historique des fonctions
    panFct.setLayout(new BorderLayout());
    panFct.setBorder(new TitledBorder("Historique des fonctions :"));
    tabFct.setViewportView(tableFonctions);
    panFct.add(tabFct, BorderLayout.CENTER);
    btnAddFonction.addActionListener(this);
    panFct.add(btnAddFonction, BorderLayout.SOUTH);

    // Panneau pour l'historique des salaires
    panSal.setLayout(new BorderLayout());
    panSal.setBorder(new TitledBorder("Historique des salaires :"));
    tabSal.setViewportView(tableSalaires);
    panSal.add(tabSal, BorderLayout.CENTER);
    btnAddSalaire.addActionListener(this);
    panSal.add(btnAddSalaire, BorderLayout.SOUTH);

    // Panneau de boutons
    panBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
    btnOk.addActionListener(this);
    panBtn.add(btnOk);
    btnCancel.addActionListener(this);
    panBtn.add(btnCancel);

    // Afficher la fenetre
    panMain.setLayout(new GridBagLayout());
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = 0;
    constraints.gridy = 0;
    panMain.add(panInfos, constraints);
    constraints.gridx = 0;
    constraints.gridy = 1;
    panMain.add(panAdresse, constraints);
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.weighty = 0.5;
    constraints.fill = GridBagConstraints.BOTH;
    panMain.add(panFct, constraints);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.weighty = 1;
    panMain.add(panSal, constraints);
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.weighty = 0;
    panMain.add(panBtn, constraints);

    getContentPane().add(panMain);
    setTitle("Ajout d'un employé");
    setSize(500, 650);
    setVisible(true);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  } // Fin du constructeur

  // Méthode actionPerformed
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnCancel)
      dispose();
    else if(e.getSource() == btnOk)
    {
      // On s'assure que tous les champs ont des données (on évite ainsi
      // plusieurs erreurs). Si un champs est vide, on permet à l'usager d'y
      // inscrire une valeur.
      if(txtNom.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier un nom.", "Données manquantes",
            JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtNom.setText(result);
      }

      else if(txtPrenom.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier un prénom.", "Données manquantes",
            JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtPrenom.setText(result);
      }

      else if(txtDateJour.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une date de naissance (Jour \"JJ\").",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtDateJour.setText(result);
      }

      else if(txtDateMois.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une date de naissance (Mois \"MM\").",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtDateMois.setText(result);
      }

      else if(txtDateAn.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une date de naissance (Année \"AAAA\").",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtDateAn.setText(result);
      }

      else if(txtNas.getText().length() != 6)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier un # d'ass. social de 6 chiffres.",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtNas.setText(result);
      }

      else if(txtNoCiv.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une adresse (# civique).",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtNoCiv.setText(result);
      }

      else if(txtRue.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une adresse (Rue).", "Données manquantes",
            JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtRue.setText(result);
      }

      else if(txtVille.getText().length() == 0)
      {
        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une adresse (Ville).", "Données manquantes",
            JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtVille.setText(result);
      }

      else if(txtCodePostal.getText().length() == 0)
      {

        String result = JOptionPane.showInputDialog(this,
            "Vous devez spécifier une adresse (Code Postal).",
            "Données manquantes", JOptionPane.INFORMATION_MESSAGE);
        if(result != null)
          txtCodePostal.setText(result);
      }
      else if(fonctions.size() == 0)
      {
        JOptionPane.showMessageDialog(this,
                                  "Vous devez spécifier au moins une fonction.",
                                  "Données manquantes",
                                  JOptionPane.INFORMATION_MESSAGE);
        new AjoutFonction(this);
      }
      else if(salaires.size() == 0)
      {
        JOptionPane.showMessageDialog(this,
                                    "Vous devez spécifier au moins un salaire.",
                                    "Données manquantes",
                                    JOptionPane.INFORMATION_MESSAGE);
        new AjoutSalaire(this);
      }
      else // Si tout est beau, on ajoute l'employé et on ferme cette fenêtre.
      {
        ajouterEmploye();
        dispose();
      }

    }
    else if(e.getSource() == btnAddFonction)
      new AjoutFonction(this);
    else if(e.getSource() == btnAddSalaire)
      new AjoutSalaire(this);
  } // Fin de la méthode actionPerformed

  private void ajouterEmploye()
  {
    Employe temp;

    temp = new Employe(txtNom.getText(), txtPrenom.getText(),
                       cboSexe.getSelectedIndex() + 1,
                       new Date(Integer.parseInt(txtDateAn.getText()) - 1900,
                       Integer.parseInt(txtDateMois.getText()) - 1,
                       Integer.parseInt(txtDateJour.getText())),
                       txtNas.getText(), new Adresse(txtNoCiv.getText(),
                       txtRue.getText(), txtVille.getText(),
                       txtCodePostal.getText()));
    Outils.TriFonction(fonctions);
    temp.addFonction(fonctions);
    Outils.TriSalaire(salaires);
    temp.addSalaire(salaires);

    // Si le NAS existe deja (il doit etre unique...) l'employé ne sera pas
    // ajouté.
    if(Outils.SearchByNAS(tabEmpl, temp.getNas()) != -1)
      JOptionPane.showMessageDialog(null,
                                    "Ce # d'ass. social existe déja. "
                                     + "L'employé ne sera pas ajouté.",
                                     "Mauvais NAS",
                                     JOptionPane.INFORMATION_MESSAGE);
    else
      tabEmpl.addElement(temp);
  }

  public void nouveauSalaire(Salaire newSalaire)
  {
    // Setting des formats
    dateFormatter = DateFormat.getDateInstance(DateFormat.LONG);
    formatArgent = new DecimalFormat("0.00 $");

    if(salaires.size() < 10)
    {
      salaires.addElement(newSalaire);
      tableSalaires.setValueAt(dateFormatter.format(newSalaire.getDate()),
                               salaires.size() - 1, 0);
      tableSalaires.setValueAt(formatArgent.format(newSalaire.getSalaire()),
                               salaires.size() - 1, 1);
      tableSalaires.setValueAt(newSalaire.getRaison(), salaires.size() - 1, 2);
    }
    else
      JOptionPane.showMessageDialog(null, "L'historique de salaires est plein",
                                    "Historique plein",
                                    JOptionPane.ERROR_MESSAGE);
  } // Fin de la méthode nouveauSalaire

  public void nouvelleFonction(Fonction newFct)
  {
    // Setting du format de date
    dateFormatter = DateFormat.getDateInstance(DateFormat.LONG);

    if(fonctions.size() < 5)
    {
      fonctions.addElement(newFct);
      tableFonctions.setValueAt(dateFormatter.format(newFct.getDate()),
                                fonctions.size() - 1, 0);
      tableFonctions.setValueAt(newFct.getTitre(), fonctions.size() - 1, 1);
      tableFonctions.setValueAt(newFct.getNoDept(), fonctions.size() - 1, 2);
      tableFonctions.setValueAt(String.valueOf(newFct.getNiveau()),
                                fonctions.size() - 1, 3);
    }
    else
      JOptionPane.showMessageDialog(null, "L'historique de fonctions est plein",
                                    "Historique plein",
                                    JOptionPane.ERROR_MESSAGE);
  } // Fin de la méthode NouvelleFonction

} // Fin de la classe PanelAjoutEmploye