public class Montir {
    private String nama;

    public Montir(String nama) {
        this.nama = nama;
    }

    // Association
    public void lakukanQualityControl(Mobil m) {
        System.out.println("\n===== QUALITY CONTROL =====");
        System.out.println("Montir : " + nama);
        
        // Menggunakan Getter untuk mengambil nama merk mobil
        System.out.println("Sedang memeriksa mobil " + m.getMerkMobil());
    }
}
