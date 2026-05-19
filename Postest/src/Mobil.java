public class Mobil {
    private String merkMobil;
    private String warna;

    // COMPOSITION: Mesin dibuat di dalam Mobil
    private Mesin mesin;

    // AGGREGATION: Ban dipasang dari luar
    private Ban[] daftarBan;

    public Mobil(String merkMobil, String warna) {
        this.merkMobil = merkMobil;
        this.warna = warna;
        this.mesin = new Mesin("MSN-001", 1500);
        this.daftarBan = new Ban[4];
    }

    // Getter untuk mendapatkan merk mobil (dibutuhkan oleh Montir)
    public String getMerkMobil() {
        return merkMobil;
    }
    // Pasang Ban
    public void pasangSetBan(Ban[] setBan) {
        // Validasi agar jumlah ban sesuai
        if (setBan != null && setBan.length == 4) {
            for (int i = 0; i < daftarBan.length; i++) {
                this.daftarBan[i] = setBan[i];
            }
        } else {
            System.out.println("Gagal! Jumlah set ban harus 4.");
        }
    }
    // Tampilkan spesifikasi
    public void tampilkanSpesifikasi() {
        System.out.println("\n SPESIFIKASI MOBIL ");
        System.out.println("Merk Mobil : " + merkMobil);
        System.out.println("Warna      : " + warna);

        System.out.println("\n- Detail Mesin - ");
        mesin.tampilkanMesin();

        System.out.println("\n- Daftar Ban -");
        for (int i = 0; i < daftarBan.length; i++) {
            System.out.println("\nBan ke-" + (i + 1));
            // Mencegah NullPointerException jika ban belum terpasang
            if (daftarBan[i] != null) {
                daftarBan[i].tampilkanBan();
            } else {
                System.out.println("Ban belum dipasang.");
            }
        }
    }
}
