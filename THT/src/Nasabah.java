public class Nasabah {

    private String idNasabah;
    private String namaLengkap;
    private Rekening[] daftarRekening;

    // Melacak berapa rekening yang sudah ditambahkan
    private int jumlahRekening;

    // Constructor Nasabah: tidak membuat Rekening di sini 
    // Rekening akan ditambahkan kemudian via tambahRekening().
    public Nasabah(String idNasabah, String namaLengkap) {
        this.idNasabah     = idNasabah;
        this.namaLengkap   = namaLengkap;
        // Inisialisasi array dengan kapasitas 3 (sesuai spesifikasi)
        this.daftarRekening = new Rekening[3];
        this.jumlahRekening = 0;
        System.out.println("[NASABAH] Profil nasabah '" + namaLengkap + "' (ID: " + idNasabah + ") berhasil didaftarkan.");
    }
    public void tambahRekening(Rekening rekening) {
        if (jumlahRekening >= 3) {
            // Validasi batas maksimal 3 rekening per nasabah
            System.out.println("  [ERROR] Nasabah " + namaLengkap + " sudah memiliki 3 rekening (batas maksimal).");
            return;
        }
        // Simpan referensi rekening ke slot array yang tersedia
        daftarRekening[jumlahRekening] = rekening;
        jumlahRekening++;
        System.out.println("  [OK] Rekening [" + rekening.getNomorRekening()
                + "] berhasil ditambahkan ke profil nasabah " + namaLengkap + ".");
    }

    // Mengambil rekening berdasarkan index (untuk operasi transaksi)
    public Rekening getRekening(int index) {
        // Validasi index agar tidak ArrayIndexOutOfBoundsException
        if (index < 0 || index >= jumlahRekening) {
            System.out.println("  [ERROR] Index rekening tidak valid.");
            return null; // Kembalikan null jika tidak ada
        }
        return daftarRekening[index];
    }
    public void hubungiCustomerService(CustomerService cs, String keluhan) {
        System.out.println("[ASOSIASI] Nasabah " + namaLengkap + " menghubungi Customer Service...");
        // Meneruskan keluhan ke objek CustomerService untuk ditangani
        cs.terimaKeluhan(this, keluhan);
    }
    public String getIdNasabah(){ 
        return idNasabah; }
    public String getNamaLengkap(){ 
        return namaLengkap; }
    public int getJumlahRekening(){ 
        return jumlahRekening; }

    // Menampilkan profil lengkap nasabah beserta semua rekeningnya
    public void tampilkanProfil() {
        System.out.println("  PROFIL NASABAH");
        System.out.println("  ID       : " + idNasabah);
        System.out.println("  Nama     : " + namaLengkap);
        System.out.println("  Rekening : " + jumlahRekening + " buah");
        // Iterasi array rekening untuk menampilkan detail tiap rekening
        for (int i = 0; i < jumlahRekening; i++) {
            System.out.println("\n   Rekening ke-" + (i + 1) + " --");
            daftarRekening[i].infoRekening(); 
        }
    }
}