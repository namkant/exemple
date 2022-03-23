/**
 * Title: TP2 (GestionEmploye)
 * Description: Classe principale. Elle contient le main du programme.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class GestionEmploye extends JFrame implements ActionListener
{
  private Vector tabEmpl = new Vector();
  private File fileSave;
  private File fileLoad;

  private JMenuBar menubar = new JMenuBar();

  private JMenu fichier = new JMenu("Fichier");
  private JMenuItem charger = new JMenuItem("Ouvrir");
  private JMenuItem save = new JMenuItem("Enregistrer");
  private JMenuItem quit = new JMenuItem("Quitter");

  private JMenu gestion = new JMenu("Gestion");
  private JMenuItem ajout = new JMenuItem("Ajouter un employé");
  private JMenuItem suppression = new JMenuItem("Supprimer un employé");

  private JMenu rapport = new JMenu("Rapports");
  private JMenuItem liste = new JMenuItem("Tous les employés");
  private JMenuItem fonctions = new JMenuItem("Salaires par fonction");

  private JMenu recherche = new JMenu("Rechercher par...");
  private JMenuItem parNAS = new JMenuItem("# d'ass. social");
  private JMenuItem parFct = new JMenuItem("fonctions");

  private JMenu aide = new JMenu("Aide");
  private JMenuItem about = new JMenuItem("À propos...");

  private JLabel image = new JLabel(new ImageIcon("banniere.jpg"));

  private JFileChooser fileChooser = new JFileChooser();

  public GestionEmploye()
  {
    fileChooser.setCurrentDirectory(new File("."));
    fileChooser.addChoosableFileFilter(new FiltreExtension("emp",
                                                           "Fichier employe"));
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    charger.addActionListener(this);
    fichier.add(charger);
    save.addActionListener(this);
    fichier.add(save);
    fichier.addSeparator();
    quit.addActionListener(this);
    fichier.add(quit);

    ajout.addActionListener(this);
    gestion.add(ajout);
    suppression.addActionListener(this);
    gestion.add(suppression);

    liste.addActionListener(this);
    rapport.add(liste);
    fonctions.addActionListener(this);
    rapport.add(fonctions);

    parNAS.addActionListener(this);
    recherche.add(parNAS);
    parFct.addActionListener(this);
    recherche.add(parFct);

    about.addActionListener(this);
    aide.add(about);

    menubar.add(fichier);
    menubar.add(gestion);
    menubar.add(rapport);
    menubar.add(recherche);
    menubar.add(aide);

    setJMenuBar(menubar);
    getContentPane().setLayout(new BorderLayout(10, 10));
    getContentPane().add(image, BorderLayout.CENTER);
    setTitle("Gestion des employés");
    //setSize(500, 250);
    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  } // Fin du constructeur

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == charger)
      loadEmployes();
    else if(e.getSource() == save)
      saveEmployes();
    else if(e.getSource() == quit)
      System.exit(0);
    else if(e.getSource() == ajout)
      new PanelAjoutEmploye(tabEmpl);
    else if(e.getSource() == suppression)
      new RechercheEmploye(tabEmpl, true);
    else if(e.getSource() == liste)
      new Rapport(tabEmpl);
    else if(e.getSource() == fonctions)
      new RechercheFonction(tabEmpl, true);
    else if(e.getSource() == parNAS)
      new RechercheEmploye(tabEmpl, false);
    else if(e.getSource() == parFct)
      new RechercheFonction(tabEmpl, false);
    else if(e.getSource() == about)
      JOptionPane.showMessageDialog(this,
                                    new JLabel(new ImageIcon("objectifs.jpg")),
                                    "À propos...",
                                    JOptionPane.INFORMATION_MESSAGE);
  } // Fin de la méthode actionPerformed

  // Méthode pour enregistrer
  private void saveEmployes()
  {
    // Si l'usager enregistre
    if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
    {
      // On obtient le fichier
      fileSave = fileChooser.getSelectedFile();

      // Si le fichier existe, on demande une confirmation
      if(fileSave.exists())
      {
        int result = JOptionPane.showConfirmDialog(this,
                          "Désirez-vous écraser ce fichier?", "Confirmation",
                          JOptionPane.OK_CANCEL_OPTION);

        // Si l'usager ne veux pas écraser le fichier, on quitte cette fonction
        // et réaffiche la boîte de dialogue save.
        if(result == JOptionPane.CANCEL_OPTION)
        {
          actionPerformed(new ActionEvent(save, ActionEvent.ACTION_PERFORMED,
                                                                           ""));
          return;
        }
      }

      // Si tout est beau, on essaye de sauver
      try
      {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                                                                     fileSave));
        oos.writeObject(tabEmpl);
        oos.flush();
        oos.close(); // On ferme le flux
        // On s'assure que le fichier porte l'extension .emp
        if(!fileSave.getAbsolutePath().endsWith(".emp"))
          fileSave.renameTo(new File(fileSave.getAbsolutePath() + ".emp"));
      }
      catch (IOException ex) // Si une erreur se produit lors de l'écriture
      {
        JOptionPane.showMessageDialog(this, "Une erreur s'est produite. Le "
                                      + "fichier n'a pu être enregistré.",
                                      "Erreur", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // Méthode pour charger.
  private void loadEmployes()
  {
    // Si l'usager confirme
    if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    {
      // On obtient le fichier
      fileLoad = fileChooser.getSelectedFile();

      // On essaye de le lire
      try
      {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                                                                     fileLoad));
        tabEmpl = (Vector)ois.readObject(); // Conversion
        ois.close(); // On ferme le flux
      }
      catch (IOException ex) // Si une erreur se produit lors de la lecture
      {
        JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de "
                                      + "la lecture du fichier.", "Erreur",
                                      JOptionPane.ERROR_MESSAGE);
      }catch (ClassNotFoundException ex) // Erreur lors de la conversion
      {
        JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de "
                                      + "la conversion des données.", "Erreur",
                                      JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // Main
  public static void main(String args[])
  {
    new GestionEmploye();
  }
}