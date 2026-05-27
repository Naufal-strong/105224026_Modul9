public abstract class Rekening implements Otorisasi {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;   // KRITIS: saldo TIDAK BOLEH diakses/diubah langsung dari luar
    private String pin;     // PIN disimpan private, tidak ada getter-nya untuk keamanan

    private BukuMutasi bukuMutasi;
    public Rekening(String nomorRekening, String namaPemilik, double saldoAwal, String pin) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik   = namaPemilik;
        this.saldo         = saldoAwal;
        this.pin           = pin;

        // Jika Rekening di-set null (dihancurkan), BukuMutasi ini
        // juga ikut musnah karena tidak ada referensi lain yang menunjuknya.
        this.bukuMutasi = new BukuMutasi(nomorRekening);

        // Langsung catat log pembukaan rekening sebagai histori pertama
        bukuMutasi.catatLog("Rekening dibuka. Saldo awal: Rp" + saldoAwal);
    }
    public void setor(double jumlah) {
        // Validasi: jumlah setoran harus positif
        if (jumlah <= 0) {
            System.out.println("  [ERROR] Jumlah setoran harus lebih dari Rp0.");
            return; // Hentikan eksekusi method jika tidak valid
        }
        saldo += jumlah; // Tambah saldo internal (hanya bisa dari sini)
        // Catat log otomatis setiap ada setoran (komposisi aktif)
        bukuMutasi.catatLog("SETOR Rp" + jumlah + " | Saldo sekarang: Rp" + saldo);
        System.out.println("  [OK] Setoran Rp" + jumlah + " berhasil. Saldo: Rp" + saldo);
    }
    public abstract void tarik(double jumlah);
    @Override 
    public void verifikasiPIN(String inputPin) {
        // Membandingkan pin yang tersimpan (private) dengan input user
        if (this.pin.equals(inputPin)) {
            System.out.println("  [AUTH] Verifikasi PIN BERHASIL untuk rekening " + nomorRekening + ".");
        } else {
            System.out.println("  [AUTH] PIN SALAH! Akses ditolak.");
        }
    }
    public String getNomorRekening() { 
        return nomorRekening; }
    public String getNamaPemilik(){ 
        return namaPemilik; }
    public double getSaldo(){ 
        return saldo; }

    protected void setSaldo(double saldo){ 
        this.saldo = saldo; }

    // getBukuMutasi() protected agar subclass bisa mencatat log saat tarik()
    protected BukuMutasi getBukuMutasi() { 
        return bukuMutasi; }
    public void infoRekening() {
        System.out.println("  +------------");
        System.out.println("   No. Rekening : " + nomorRekening);
        System.out.println("   Nama Pemilik : " + namaPemilik);
        System.out.println("   Saldo        : Rp" + saldo);
        System.out.println("   Tipe         : " + this.getClass().getSimpleName());
        // getClass().getSimpleName() mengembalikan nama kelas aktual saat runtime
        // (RekeningReguler atau RekeningPrioritas) - ini adalah polimorfisme runtime
        System.out.println("  +-------------");
    }
}
    

