public class Main {
    public static void main(String[] args) {

        // 1. MEMBUAT 4
        Ban ban1 = new Ban("Bridgestone", 17);
        Ban ban2 = new Ban("Bridgestone", 17);
        Ban ban3 = new Ban("Bridgestone", 17);
        Ban ban4 = new Ban("Bridgestone", 17);

        Ban[] setBan = {ban1, ban2, ban3, ban4};

        // MEMBUAT MOBIL
        Mobil mobil1 = new Mobil("Toyota Supra", "Merah");

        // MEMASANG BAN
        mobil1.pasangSetBan(setBan);

        // MENAMPILKAN SPESIFIKASI
        mobil1.tampilkanSpesifikasi();

        // MEMBUAT MONTIR & QUALITY CONTROL (ASSOCIATION)
        Montir montir1 = new Montir("Aldo");
        montir1.lakukanQualityControl(mobil1);

        // SIMULASI GAGAL QC / MOBIL HANCUR
        System.out.println("\n= SIMULASI =");
        mobil1 = null;
        System.out.println("Mobil dihancurkan.");

        System.out.println("\nApakah ban masih ada?");
        if (ban1 != null) System.out.println("Ban 1 masih ada.");
        if (ban2 != null) System.out.println("Ban 2 masih ada.");
    }
}