/**
 * Title: TP2 (LimitedDoc)
 * Description: Document personnalisé. Permet de spécifier la longueur max d'un
 *              champ et si celui-ci ne doit prendre que des chiffres.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Toolkit;

public class LimitedDoc extends PlainDocument
{
  int maxCharacters;
  boolean nbSeul = false;

  public LimitedDoc(int maxChars)
  {
    maxCharacters = maxChars;
  }

  public LimitedDoc(int maxChars, boolean nb)
  {
    maxCharacters = maxChars;
    nbSeul = nb;
  }

  public void insertString(int offs, String str, AttributeSet a)
      throws BadLocationException
  {
    // Quitte la fonction si le champs est vide.
    // Evite ainsi une erreur lors de l'initialisation du champ.
    if(getLength() + str.length() == 0)
      return;

    // Rejette tout caractère qui dépasserait la limite du champs
    if ((getLength() + str.length()) <= maxCharacters)
    {
      // Si le champ accepte n'importe quel type de caractères
      if(!nbSeul)
        super.insertString(offs, str, a);
      // S'il ne doit accepter que les chiffres
      else if(nbSeul && Character.isDigit(str.charAt(0)))
        super.insertString(offs, str, a);
      else // Beep si le caractère n'est pas acceptable
        Toolkit.getDefaultToolkit().beep();
    }
    // Beep si la limite de caractères est atteinte
    else
      Toolkit.getDefaultToolkit().beep();
  }
}
