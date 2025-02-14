package com.mycompany.modul8pbol;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelModelMhs extends AbstractTableModel {
   private List<Mhs> daftarMahasiswa = new ArrayList();

   public TabelModelMhs(List<Mhs> mhs) {
      this.daftarMahasiswa = mhs;
   }

   public int getRowCount() {
      return this.daftarMahasiswa.size();
   }

   public int getColumnCount() {
      return 2;
   }

   public Object getValueAt(int rowIndex, int columnIndex) {
      Mhs mhs = (Mhs)this.daftarMahasiswa.get(rowIndex);
      switch(columnIndex) {
      case 0:
         return mhs.getNim();
      case 1:
         return mhs.getNama();
      default:
         return "";
      }
   }

   public String getColumnName(int column) {
      switch(column) {
      case 0:
         return "NIM";
      case 1:
         return "Nama";
      default:
         return "";
      }
   }
}
