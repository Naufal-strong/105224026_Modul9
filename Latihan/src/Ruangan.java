public class Ruangan {
     String nomorRuangan;
    int kapasitas;

    public Ruangan(String nomorRuangan, int kapasitas) {
        this.nomorRuangan = nomorRuangan;
        this.kapasitas = kapasitas;
    }

    public void tampilkanRuangan() {

        System.out.println("Nomor Ruangan : " + nomorRuangan);
        System.out.println("Kapasitas     : " + kapasitas + " pasien");
    }
}
