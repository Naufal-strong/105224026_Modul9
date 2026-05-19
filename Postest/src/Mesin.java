public class Mesin {
    private String nomorSeri;
    private int kapasitasCC;

    public Mesin(String nomorSeri, int kapasitasCC) {
        this.nomorSeri = nomorSeri;
        this.kapasitasCC = kapasitasCC;
    }

    public void tampilkanMesin() {
        System.out.println("Nomor Seri Mesin : " + nomorSeri);
        System.out.println("Kapasitas Mesin  : " + kapasitasCC + " CC");
    }
}
