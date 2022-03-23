/**
 * Title: TP2 (Salaire)
 * Description: Objet Salaire.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Salaire implements Serializable
{
  // Constantes de raison
  static final int ENGAGEMENT = 10;
  static final int TRANSFERT = 20;
  static final int PROMOTION = 30;
  static final int DEPART = 50;

  private Date date;
  private double salaire;
  private int raison;

  // Constructeur
  public Salaire(Date laDate, double leSalaire, int laRaison)
  {
    date = laDate;
    salaire = leSalaire;
    raison = laRaison;
  } // Fin du constructeur

  // Méthodes set
  public void setDate(Date laDate) { date = laDate; }
  public void setSalaire(double leSalaire) { salaire = leSalaire; }
  public void setRaison(int laRaison) { raison = laRaison; }

  // Méthodes get
  public Date getDate() { return date; }
  public double getSalaire() { return salaire; }
  public String getRaison()
  {
    String laRaison;
    switch(raison)
    {
      case 10: laRaison = "Engagement"; break;
      case 20: laRaison = "Transfert";  break;
      case 30: laRaison = "Promotion";  break;
      case 50: laRaison = "Départ";     break;
      default: laRaison = "N/A";
    }
    return laRaison;
  }

} // Fin de la classe Salaire