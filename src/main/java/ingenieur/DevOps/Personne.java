/**
 * Title: TP2 (Personne)
 * Description: Objet Personne.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Personne implements Serializable
{
  // Constantes
  public static int MALE = 1;
  public static int FEMALE = 2;

  private String nom,
                 prenom;
  private int sexe;
  private Date birth;

  // Constructeur
  public Personne(String leNom, String lePrenom, int leSexe, Date naissance)
  {
    nom = leNom;
    prenom = lePrenom;
    if(leSexe == MALE || leSexe == FEMALE)
      sexe = leSexe;
    else
      sexe = MALE;
    birth = naissance;
  } // Fin du constructeur

  // Méthodes "set"
  public void setNom(String leNom) { nom = leNom; }
  public void setPrenom(String lePrenom) { prenom = lePrenom; }
  public void setSexe(int leSexe) { sexe = leSexe; }
  public void setBirth(Date naissance) { birth = naissance; }

  // Méthodes "get"
  public String getNom() { return nom; }
  public String getPrenom() { return prenom; }
  public int getSexe() { return sexe; }
  public Date getBirth() { return birth; }

  // Méthode toString
  public String toString()
  {
    String leSexe;

    if(sexe == MALE)
      leSexe = "Masculin";
    else
      leSexe = "Féminin";
    return (nom + ", " + prenom + " " + leSexe + " " + birth);
  }
} // Fin de la classe Personne