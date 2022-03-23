/**
 * Title: TP2 (Employe)
 * Description: Objet Employe. Extends Personne et contient une Adresse.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Employe extends Personne implements Serializable
{
  private String nas;
  private Adresse adresse;
  private Vector fonctions = new Vector(5);
  private Vector salaires = new Vector(10);

  // Constructeur
  public Employe(String leNom, String lePrenom, int leSexe, Date naissance,
                 String leNas, Adresse adr)
  {
    super(leNom, lePrenom, leSexe, naissance);
    nas = leNas;
    adresse = adr;
  } // Fin du constructeur

  // Méthodes set
  public void setNas(String leNas) { nas = leNas; }
  public void adresse(Adresse adr) { adresse = adr; }

  // Méthodes get
  public String getNas() { return nas; }
  public Adresse getAdresse() { return adresse; }
  public int getNbFct() { return fonctions.size(); }
  public int getNbSalaires() { return salaires.size(); }
  public Fonction getFonction(int index)
  {
    return (Fonction)fonctions.elementAt(index);
  }
  public Salaire getSalaire(int index)
  {
    return (Salaire)salaires.elementAt(index);
  }

  // Méthode toString
  public String toString()
  {
    return (super.toString() + "\n" + nas + "\n" + adresse);
  }

  // Méthodes pour ajouter des éléments dans les Vectors fonctions et salaires
  // Méthode pour ajouter une seule fonction
  public void addFonction(Fonction nlleFonction)
  {
    // Ajout de la fonction au début du tableau
    fonctions.add(0, nlleFonction);

    if(fonctions.size() > 5)
    {
      // Redimensionnement du Vector (max 5 fonctions)
    fonctions.setSize(5);   // Le reste est perdu...
    fonctions.trimToSize(); // On s'assure de ne pas gaspiller d'espace memoire.
    }
  } // Fin de addFonction

  // Méthode pour copier un Vector contenant des fonctions
  public void addFonction(Vector nlleFonctions)
  {
    fonctions = nlleFonctions;

    if(fonctions.size() > 5)
    {
      // Redimensionnement du Vector (max 5 fonctions)
    fonctions.setSize(5);   // Le reste est perdu...
    fonctions.trimToSize(); // On s'assure de ne pas gaspiller d'espace memoire.
    }
  }

  // Méthode pour ajouter un seul salaire
  public void addSalaire(Salaire newSalaire)
  {
    // Ajout de la fonction au début du tableau
    salaires.add(0, newSalaire);

    if(salaires.size() > 10)
    {
      // Redimensionnement du Vector (max 10 salaires)
    salaires.setSize(10);   // Le reste est perdu...
    salaires.trimToSize();  // On s'assure de ne pas gaspiller d'espace memoire.
    }
  } // Fin de addSalaire

  // Méthode pour copier un Vector contenant des salaires
  public void addSalaire(Vector newSalaires)
  {
    salaires = newSalaires;

    if(salaires.size() > 10)
    {
      // Redimensionnement du Vector (max 10 salaires)
    salaires.setSize(10);   // Le reste est perdu...
    salaires.trimToSize();  // On s'assure de ne pas gaspiller d'espace memoire.
    }
  }

} // Fin de la classe