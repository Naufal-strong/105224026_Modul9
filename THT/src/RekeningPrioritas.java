public class RekeningPrioritas extends Rekening {
    private static final double MINIMUM_TARIK = 500000.0;

    // Minimum saldo yang harus tersisa setelah penarikan.
    // Rekening prioritas tidak boleh jatuh di bawah angka ini.
    private static final double MINIMUM_SALDO = 1000000.0;

    // Constructor memanggil super() untuk menginisialisasi parent Rekening,
    // termasuk otomatis membuat BukuMutasi (pola komposisi).
    public RekeningPrioritas(String nomorRekening, String namaPemilik,
                             double saldoAwal, String pin) {
        super(nomorRekening, namaPemilik, saldoAwal, pin);
        System.out.println("  [SISTEM] Rekening Prioritas [" + nomorRekening + "] berhasil dibuka.");
    }
    @Override
    public void tarik(double jumlah) {

        // Validasi 1: jumlah penarikan harus lebih besar dari MINIMUM_TARIK
        if (jumlah < MINIMUM_TARIK) {
            System.out.println("  [GAGAL] Rekening Prioritas mensyaratkan minimum penarikan Rp"
                    + MINIMUM_TARIK + ". Anda mencoba tarik Rp" + jumlah);
            return; // Hentikan proses jika tidak memenuhi syarat
        }

        // Validasi 2: saldo setelah penarikan tidak boleh < MINIMUM_SALDO
        if ((getSaldo() - jumlah) < MINIMUM_SALDO) {
            System.out.println("  [GAGAL] Saldo setelah penarikan akan kurang dari minimum saldo Rp"
                    + MINIMUM_SALDO + ". Saldo saat ini: Rp" + getSaldo());
            return;
        }

        // Jika semua validasi lolos: kurangi saldo TANPA biaya admin
        setSaldo(getSaldo() - jumlah);

        // Catat log dengan info "BEBAS ADMIN" sebagai pembeda
        getBukuMutasi().catatLog("TARIK Rp" + jumlah + " (BEBAS ADMIN) | Saldo sekarang: Rp" + getSaldo());
        System.out.println("  [OK] Penarikan Rp" + jumlah + " berhasil. BEBAS admin. Saldo: Rp" + getSaldo());
    }
}