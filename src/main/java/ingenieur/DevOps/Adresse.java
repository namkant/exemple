/**
 * Title: TP2 (Adresse)
 * Description: Objet Adresse
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.io.*;

public class Adresse implements Serializable
{
  private String noCiv,
                 rue,
                 ville,
                 postal;

  // Constructeur
  public Adresse(String no, String laRue, String laVille, String code)
  {
    noCiv = no;
    rue = laRue;
    ville = laVille;
    postal = code;
  } // Fin du constructeur

  // Méthodes set
  public void setNoCiv(String no) { noCiv = no; }
  public void setRue(String laRue) { rue = laRue; }
  public void setVille(String laVille) { ville = laVille; }
  public void setPostal(String code) { postal = code; }

  // Méthodes get
  public String getNoCiv() { return noCiv; }
  public String getRue() { return rue; }
  public String getVille() { return ville; }
  public String getPostal() { return (postal.substring(0, 3) + " "
                                      + postal.substring(3)); }

  // Méthode toString
  public String toString()
  {
    return (noCiv + ", " + rue + "\n" + ville + ", " + postal);
  }
} // Fin de la classe Adresse