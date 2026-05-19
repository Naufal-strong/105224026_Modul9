public class Ban{
private String merk;
    private int ukuranRing;

    public Ban(String merk, int ukuranRing) {
        this.merk = merk;
        this.ukuranRing = ukuranRing;
    }

    // Getter opsional jika butuh mengambil data dari luar
    public String getMerk() {
        return merk;
    }

    public int getUkuranRing() {
        return ukuranRing;
    }

    public void tampilkanBan() {
        System.out.println("Merk Ban    : " + merk);
        System.out.println("Ukuran Ring : R" + ukuranRing);
    }
}