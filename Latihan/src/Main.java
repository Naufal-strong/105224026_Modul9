public class Main {

    public static void main(String[] args) {
        Dokter dokter1 =
                new Dokter("Dr. Boyke", "Penyakit Dalam");

        Dokter dokter2 =
                new Dokter("Dr. Tirta", "Umum");
        // MEMBUAT PASIEN (MANDIRI)

        Pasien pasien1 =
                new Pasien("Aldo", 45);

        Pasien pasien2 =
                new Pasien("Uwan", 21);

        // ASSOCIATION
        // Dokter memeriksa pasien
        dokter1.periksaPasien(pasien1);
        dokter2.periksaPasien(pasien2);

        // MEMBUAT RUMAH SAKIT
        RumahSakit rs =
                new RumahSakit("RS Sehat Selalu");
        // AGGREGATION
        // Dokter dimasukkan ke RS

        rs.tambahDokter(dokter1, 0);
        rs.tambahDokter(dokter2, 1);
        // MENAMPILKAN DATA

        rs.tampilkanRuangan();

        rs.tampilkanDokter();

        // PENGUJIAN LIFECYCLE

        System.out.println("\n PENGUJIAN ");

        // Rumah sakit dihapus
        rs = null;

        System.out.println("Objek Rumah Sakit dihapus.");

        // Dokter masih ada
        System.out.println("\nApakah dokter masih ada?");

        if (dokter1 != null) {
            System.out.println("Ya, " + dokter1.nama + " masih ada.");
        }

        if (dokter2 != null) {
            System.out.println("Ya, " + dokter2.nama + " masih ada.");
        }



        // Pasien masih ada
        System.out.println("\nApakah pasien masih ada?");

        if (pasien1 != null) {
            System.out.println("Ya, " + pasien1.nama + " masih ada.");
        }

        if (pasien2 != null) {
            System.out.println("Ya, " + pasien2.nama + " masih ada.");
        }

    }

}