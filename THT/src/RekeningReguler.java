public class RekeningReguler extends Rekening {
    private static final double BIAYA_ADMIN = 2500.0;
    public RekeningReguler(String nomorRekening, String namaPemilik,
                           double saldoAwal, String pin) {
        // super() HARUS menjadi baris pertama di constructor subclass.
        // Ini memastikan bagian parent (Rekening) diinisialisasi dulu,
        // termasuk pembuatan BukuMutasi secara otomatis (komposisi).
        super(nomorRekening, namaPemilik, saldoAwal, pin);
        System.out.println("  [SISTEM] Rekening Reguler [" + nomorRekening + "] berhasil dibuka.");
    }
    public void tarik(double jumlah) {
        // Hitung total yang harus dikurangi dari saldo (jumlah + biaya admin)
        double totalPotong = jumlah + BIAYA_ADMIN;

        // Validasi: pastikan saldo cukup untuk menutupi jumlah + biaya admin
        if (totalPotong > getSaldo()) {
            // getSaldo() digunakan karena saldo adalah private di parent,
            // tidak bisa diakses langsung dengan 'saldo'
            System.out.println("  [GAGAL] Saldo tidak cukup. Dibutuhkan Rp" + totalPotong
                    + " (termasuk admin Rp" + BIAYA_ADMIN + "), saldo Anda: Rp" + getSaldo());
            return;
        }

        // Kurangi saldo menggunakan setSaldo() protected dari parent
        // (tidak bisa langsung 'saldo -=' karena saldo private di Rekening)
        setSaldo(getSaldo() - totalPotong);

        // Catat log menggunakan getBukuMutasi() protected dari parent
        getBukuMutasi().catatLog("TARIK Rp" + jumlah + " + Admin Rp" + BIAYA_ADMIN
                + " | Saldo sekarang: Rp" + getSaldo());
        System.out.println("  [OK] Penarikan Rp" + jumlah + " berhasil (admin Rp"
                + BIAYA_ADMIN + "). Saldo: Rp" + getSaldo());
    }
}