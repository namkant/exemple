/**
 * Title: TP2 (Outils)
 * Description: Classe contenant des fonction utilisées par le projet.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.util.*;

public class Outils
{
  public static int SearchByNAS(Vector data, String nas)
  {
    Employe empl;

    for(int i = 0; i < data.size(); i++)
    {
      empl = (Employe)data.get(i);
      if(empl.getNas().equalsIgnoreCase(nas))
        return i;
    }
    return -1;
  } // Fin de la méthode SearchByNAS

  public static Vector SearchFct(Vector data, String fonction)
  {
    Vector result = new Vector();
    Employe empl;

    for(int i = 0; i < data.size(); i++)
    {
      empl = (Employe)data.get(i);
      // On s'assure que l'employé a bien eu au moins une fonction.
      if(empl.getNbFct() > 0)
        if(empl.getFonction(empl.getNbFct() - 1).getTitre().equalsIgnoreCase(
                                                                      fonction))
          result.addElement(empl);
    }
    return result;
  }

  public static void TriSalaire(Vector data)
  {
    Vector temp = new Vector();
    Salaire salaire1, salaire2;
    boolean done = false;

    while(!done)
    {
      done = true;
      for(int i = 0; i < data.size() - 1; i++)
      {
        salaire1 = (Salaire)data.get(i);
        salaire2 = (Salaire)data.get(i + 1);

        // Date 1 est plus grand que Date 2
        if(salaire1.getDate().compareTo(salaire2.getDate()) > 0)
        {
          done = false;
          data.setElementAt(salaire1, i + 1);
          data.setElementAt(salaire2, i);
        }
      }
    }
  }

  public static void TriFonction(Vector data)
  {
    Fonction fonction1, fonction2;
    boolean done = false;

    while(!done)
    {
      done = true;
      for(int i = 0; i < data.size() - 1; i++)
      {
        fonction1 = (Fonction)data.get(i);
        fonction2 = (Fonction)data.get(i + 1);

        // Date 1 est plus grand que Date 2
        if(fonction1.getDate().compareTo(fonction2.getDate()) > 0)
        {
          done = false;
          data.setElementAt(fonction1, i + 1);
          data.setElementAt(fonction2, i);
        }
      }
    }
  }
}