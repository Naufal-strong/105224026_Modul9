public class BukuMutasi {
    private String nomorRekening;
    public BukuMutasi(String nomorRekening) {
        this.nomorRekening = nomorRekening;
        // Pesan ini membuktikan BukuMutasi lahir tepat saat Rekening dibuat
        System.out.println("  [SISTEM] Buku Mutasi untuk rekening ["
                + nomorRekening + "] telah diinisialisasi secara otomatis.");
    }
    public void catatLog(String aktivitas) {
        System.out.println("  [MUTASI | " + nomorRekening + "] >> " + aktivitas);
    }
}