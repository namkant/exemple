/**
 * Title: TP2 (LimitedDoc)
 * Description: Document personnalis�. Permet de sp�cifier la longueur max d'un
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

    // Rejette tout caract�re qui d�passerait la limite du champs
    if ((getLength() + str.length()) <= maxCharacters)
    {
      // Si le champ accepte n'importe quel type de caract�res
      if(!nbSeul)
        super.insertString(offs, str, a);
      // S'il ne doit accepter que les chiffres
      else if(nbSeul && Character.isDigit(str.charAt(0)))
        super.insertString(offs, str, a);
      else // Beep si le caract�re n'est pas acceptable
        Toolkit.getDefaultToolkit().beep();
    }
    // Beep si la limite de caract�res est atteinte
    else
      Toolkit.getDefaultToolkit().beep();
  }
}
