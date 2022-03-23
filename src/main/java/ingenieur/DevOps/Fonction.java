/**
 * Title: TP2 (Fonction)
 * Description: Objet Fonction.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Fonction implements Serializable
{
  private Date date;
  private String titre;
  private String noDept;
  private int niveau;

  // Constructeur
  public Fonction(Date laDate, String leTitre, String dept, int niv)
  {
    date = laDate;
    titre = leTitre;
    noDept = dept;
    niveau = niv;
  } // Fin du constructeur

  // Méthodes set
  public void setDate(Date laDate) { date = laDate; }
  public void setTitre(String leTitre) { titre = leTitre; }
  public void setNoDept(String dept) { noDept = dept; }
  public void setNiveau(int niv) { niveau = niv; }

  // Méthodes get
  public Date getDate() { return date; }
  public String getTitre() { return titre; }
  public String getNoDept() { return noDept; }
  public int getNiveau() { return niveau; }

} // Fin de la classe Fonction