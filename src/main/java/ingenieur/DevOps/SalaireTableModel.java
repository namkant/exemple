/**
 * Title: TP2 (SalaireTableModel)
 * Description: AbstractTableModel personnalisé. Permet d'afficher le tableau
 *              des salaires.
 * Copyright: Copyright (c) 2003
 * @author Carl Fauteux
 * @version 1.0
 */

import javax.swing.table.*;

public class SalaireTableModel extends AbstractTableModel
{
  // Variables d'instances
  private String columnNames[] = { "Date", "Salaire", "Raison" };

  private Object data[][];

  // Constructeur
  public SalaireTableModel(int nbRows)
  {
    data = new Object[nbRows][columnNames.length];
    for(int i = 0; i < data.length; i++)
    {
      for(int j = 0; j < data[i].length; j++)
        data[i][j] = new String("");
    }
  }

  // Redéfinition des méthodes abstracts de AbstractTableModel
  public int getColumnCount() { return columnNames.length; }
  public int getRowCount() { return data.length; }
  public String getColumnName(int col) { return columnNames[col];}
  public Object getValueAt(int row, int col) { return data[row][col]; }
  public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }

  public void setValueAt(Object value, int row, int col)
  {
    data[row][col] = value;
    fireTableCellUpdated(row, col);
  }
}
