public class CustomerService {
    private String idCS;
    private String namaCS;
    public CustomerService(String idCS, String namaCS) {
        this.idCS   = idCS;
        this.namaCS = namaCS;
        System.out.println("[CS] Customer Service '" + namaCS + "' siap melayani.");
    }
    public void terimaKeluhan(Nasabah nasabah, String keluhan) {
        System.out.println("  CS - " + namaCS + " Menerima keluhan dari nasabah: " + nasabah.getNamaLengkap());
        System.out.println("  CS - " + namaCS + " Isi keluhan: \"" + keluhan + "\"");
        System.out.println("  CS - " + namaCS + " Keluhan dicatat. Tim kami akan menindaklanjuti dalam 1x24 jam.");
    }

    public String getNamaCS() { return namaCS; }
}