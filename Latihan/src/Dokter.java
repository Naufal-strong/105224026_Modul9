public class Dokter {
    String nama;
    String spesialisasi;

    public Dokter(String nama, String spesialisasi) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }

    // Association
    public void periksaPasien(Pasien pasien) {

        System.out.println("==================================");
        System.out.println("Dokter        : " + nama);
        System.out.println("Spesialisasi  : " + spesialisasi);
        System.out.println("Memeriksa     : " + pasien.nama);
        System.out.println("Umur Pasien   : " + pasien.umur + " tahun");
        System.out.println("==================================");
    }

    public void tampilkanDokter() {
        System.out.println("Nama Dokter   : " + nama);
        System.out.println("Spesialisasi  : " + spesialisasi);
    }
}