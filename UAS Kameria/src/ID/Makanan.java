package ID;

import java.time.LocalDate;

public class Makanan {
    private String namaMakanan;
    private String hargaMakanan;
    private String deskripsiMakanan;
    private int jumblah;
    private int id;
    private String tanggal;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private String nama;

    public String getLocalDate() {
        return this.tanggal;
    }

    public void setLocalDate(String aturTanggal) {
        this.tanggal = aturTanggal;
    }

    public Makanan(){
        this.jumblah++;

    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(String hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public String getDeskripsiMakanan() {
        return deskripsiMakanan;
    }

    public void setDeskripsiMakanan(String deskripsiMakanan) {
        this.deskripsiMakanan = deskripsiMakanan;
    }
    public void setJumblah(int inputJumblah){
        this.jumblah = inputJumblah;
    }
    public int getJumblah(){
        return this.jumblah;
    }
    public int getId(){
        return id;
    }
    public void setId(int setid){
        this.id = setid;
    }
}
