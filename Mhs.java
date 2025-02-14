package com.mycompany.modul8pbol;

public class Mhs {
   private String nim;
   private String nama;

   public Mhs() {
   }

   public Mhs(String nim, String nama) {
      this.nim = nim;
      this.nama = nama;
   }

   public String getNim() {
      return this.nim;
   }

   public void setNim(String nim) {
      this.nim = nim;
   }

   public String getNama() {
      return this.nama;
   }

   public void setNama(String nama) {
      this.nama = nama;
   }
}
