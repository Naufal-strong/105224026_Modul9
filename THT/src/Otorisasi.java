
public interface Otorisasi {
    // Keyword 'interface' digunakan karena kita ingin membuat
    // "kontrak" murni tanpa implementasi. Setiap kelas yang
    // implements Otorisasi WAJIB mengisi body method ini.
    // Ini memastikan standarisasi keamanan di seluruh sistem.
    void verifikasiPIN(String pin);
    // Method ini tidak punya body {} karena di interface semua
    // method secara default adalah abstract & public.
    // Parameter String pin: PIN yang diinput user untuk diverifikasi.
}
   
