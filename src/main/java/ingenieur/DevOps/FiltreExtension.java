/**
 * Title: TP2 (FiltreExtension)
 * Description: FileFilter personnalisé.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import java.io.*;

public class FiltreExtension extends javax.swing.filechooser.FileFilter
{
  String extension,
         description;

  public FiltreExtension(String ext, String desc)
  {
    if(ext.indexOf(".") == -1)
      ext = "." + ext;

    extension = ext;
    description = desc;
  }

  public boolean accept(File fichier)
  {
    if(fichier.getName().endsWith(extension))
      return true;
    else if(fichier.isDirectory())
      return true;
    return false;
  }

  public String getDescription()
  {
    return description + "(*" + extension + ")";
  }
}