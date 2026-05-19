public class RumahSakit {
     String namaRumahSakit;
    // COMPOSITION
    // Ruangan dibuat di dalam constructor

    private final Ruangan[] daftarRuangan;
    // AGGREGATION
    // Dokter dibuat dari luar


    Dokter[] daftarDokter;

    public RumahSakit(String namaRumahSakit) {

        this.namaRumahSakit = namaRumahSakit;

        // Maksimal 2 
        daftarRuangan = new Ruangan[2];

        // Composition
        daftarRuangan[0] = new Ruangan("R-01", 5);
        daftarRuangan[1] = new Ruangan("R-02", 10);

        // Array dokter
        daftarDokter = new Dokter[2];
    }
    // Method tambah dokter
    // Aggregation

    public void tambahDokter(Dokter dokter, int index) {

        if (index >= 0 && index < daftarDokter.length) {
            daftarDokter[index] = dokter;
        }
    }
    // Tampilkan ruangan

    public void tampilkanRuangan() {

        System.out.println("\n DAFTAR RUANGAN ");

        for (Ruangan r : daftarRuangan) {

            if (r != null) {

                System.out.println("----------------");
                r.tampilkanRuangan();
            }
        }
    }
    // Tampilkan dokter

    public void tampilkanDokter() {

        System.out.println("\n DAFTAR DOKTER ");

        for (Dokter d : daftarDokter) {

            if (d != null) {

                System.out.println("---------------");
                d.tampilkanDokter();
            }
        }
    }
}