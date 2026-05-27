import java.util.Scanner; 

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerService cs = new CustomerService("CS-001", "Budi Santoso");
        Nasabah nasabah = null;
        Rekening[] bankDataRekening = new Rekening[3];
        int totalRekeningDibuat     = 0;

        // Flag login: melacak apakah user sudah login
        boolean sudahLogin         = false;
        int indexRekeningAktif     = -1;

        int pilihan = 0;

        // do-while memastikan menu tampil minimal sekali sebelum dicek kondisinya.
        do {
            System.out.println("      SELAMAT DATANG DI NEOBANK     ");
            System.out.println("+=====================================+");
            System.out.println("  1. Registrasi Profil Nasabah        ");
            System.out.println("  2. Buka Rekening Baru               ");
            System.out.println("  3. Login ke Rekening                ");
            System.out.println("  4. Setor Dana                       ");
            System.out.println("  5. Tarik Dana                       ");
            System.out.println("  6. Tampilkan Profil Nasabah         ");
            System.out.println("  7. Hubungi Customer Service         ");
            System.out.println("  8. Skenario Penutupan Akun (Paksa)  ");
            System.out.println("  0. Keluar                           ");
            System.out.print("Pilih menu: ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline sisa setelah nextInt()

            switch (pilihan) {

                case 1:
                    System.out.println("\n REGISTRASI PROFIL NASABAH ");
                    System.out.print("Masukkan ID Nasabah  : ");
                    String idNasabah = scanner.nextLine();
                    System.out.print("Masukkan Nama Lengkap: ");
                    String namaLengkap = scanner.nextLine();

                    // Membuat objek Nasabah. Array Rekening[] di dalamnya masih kosong.
                    // Rekening akan ditambahkan secara terpisah (agregasi).
                    nasabah = new Nasabah(idNasabah, namaLengkap);
                    break;
                case 2:
                    if (nasabah == null) {
                        System.out.println("[ERROR] Registrasi nasabah terlebih dahulu (Menu 1).");
                        break;
                    }
                    if (totalRekeningDibuat >= 3) {
                        System.out.println("[ERROR] Sistem hanya mendukung 3 rekening.");
                        break;
                    }

                    System.out.println("\n BUKA REKENING BARU ");
                    System.out.println("Pilih tipe rekening:");
                    System.out.println("  1. Rekening Reguler   (biaya admin Rp2.500/tarik)");
                    System.out.println("  2. Rekening Prioritas (bebas admin, min tarik Rp500.000)");
                    System.out.print("Pilihan: ");
                    int tipeRekening = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nomor Rekening (cth: NEO-001): ");
                    String noRek = scanner.nextLine();
                    System.out.print("Saldo Awal (Rp)              : ");
                    double saldoAwal = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Buat PIN (6 digit)           : ");
                    String pinBaru = scanner.nextLine();

                    Rekening rekeningBaru = null;
                    if (tipeRekening == 1) {
                        rekeningBaru = new RekeningReguler(noRek, nasabah.getNamaLengkap(), saldoAwal, pinBaru);
                    } else if (tipeRekening == 2) {
                        rekeningBaru = new RekeningPrioritas(noRek, nasabah.getNamaLengkap(), saldoAwal, pinBaru);
                    } else {
                        System.out.println("[ERROR] Pilihan tipe tidak valid.");
                        break;
                    }

                    // SIMPAN di bankDataRekening (sistem pusat) -> bukti AGREGASI.
                    // Rekening hidup di SINI, bukan di dalam Nasabah.
                    bankDataRekening[totalRekeningDibuat] = rekeningBaru;
                    totalRekeningDibuat++;

                    // Daftarkan REFERENSI rekening ke profil Nasabah (bukan pindah kepemilikan).
                    // Nasabah hanya menyimpan pointer ke objek yang sama di bankDataRekening.
                    nasabah.tambahRekening(rekeningBaru);
                    break;

                case 3:
                    if (nasabah == null || nasabah.getJumlahRekening() == 0) {
                        System.out.println("[ERROR] Belum ada nasabah atau rekening terdaftar.");
                        break;
                    }

                    System.out.println("\n LOGIN REKENING ");
                    for (int i = 0; i < nasabah.getJumlahRekening(); i++) {
                        System.out.println("  [" + i + "] " + nasabah.getRekening(i).getNomorRekening()
                                + " (" + nasabah.getRekening(i).getClass().getSimpleName() + ")");
                    }
                    System.out.print("Pilih index rekening: ");
                    int idxPilih = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan PIN        : ");
                    String pinInput = scanner.nextLine();

                    // verifikasiPIN() adalah metode dari interface Otorisasi.
                    // Dipanggil secara polimorfis lewat referensi Rekening.
                    Rekening rekTerpilih = nasabah.getRekening(idxPilih);
                    if (rekTerpilih != null) {
                        rekTerpilih.verifikasiPIN(pinInput);
                        sudahLogin = true;
                        indexRekeningAktif = idxPilih;
                        System.out.println("  [INFO] Rekening aktif: " + rekTerpilih.getNomorRekening());
                    }
                    break;

                case 4:
                    if (!sudahLogin || nasabah == null) {
                        System.out.println("[ERROR] Login terlebih dahulu (Menu 3).");
                        break;
                    }
                    System.out.println("\n SETOR DANA ");
                    System.out.print("Jumlah setoran (Rp): ");
                    double jumlahSetor = scanner.nextDouble();
                    scanner.nextLine();

                    // setor() memanggil getBukuMutasi().catatLog() secara internal.
                    // Ini membuktikan KOMPOSISI aktif: BukuMutasi melayani Rekening.
                    nasabah.getRekening(indexRekeningAktif).setor(jumlahSetor);
                    break;

                case 5:
                    if (!sudahLogin || nasabah == null) {
                        System.out.println("[ERROR] Login terlebih dahulu (Menu 3).");
                        break;
                    }
                    System.out.println("\n TARIK DANA ");
                    System.out.print("Jumlah penarikan (Rp): ");
                    double jumlahTarik = scanner.nextDouble();
                    scanner.nextLine();

                    // Jika rekening = RekeningReguler -> tarik() potong admin Rp2.500
                    // Jika rekening = RekeningPrioritas -> tarik() validasi minimum Rp500.000
                    nasabah.getRekening(indexRekeningAktif).tarik(jumlahTarik);
                    break;

                case 6:
                    if (nasabah == null) {
                        System.out.println("[ERROR] Belum ada nasabah terdaftar.");
                        break;
                    }
                    nasabah.tampilkanProfil();
                    break;

                case 7:
                    if (nasabah == null) {
                        System.out.println("[ERROR] Registrasi nasabah terlebih dahulu.");
                        break;
                    }
                    System.out.println("\n HUBUNGI CUSTOMER SERVICE ");
                    System.out.print("Tulis keluhan Anda: ");
                    String keluhan = scanner.nextLine();

                    // ASOSIASI: objek cs dikirim sebagai parameter.
                    // Tidak ada kepemilikan; interaksi hanya terjadi saat method berjalan.
                    nasabah.hubungiCustomerService(cs, keluhan);
                    break;

                case 8:
                    System.out.println("\n+=====================");
                    System.out.println("  SKENARIO PENUTUPAN AKUN PAKSA");
                    System.out.println("+========================");

                    if (nasabah == null) {
                        System.out.println("[INFO] Tidak ada nasabah aktif.");
                        break;
                    }

                    System.out.println("[SEBELUM] Profil nasabah: " + nasabah.getNamaLengkap());
                    System.out.println("[SEBELUM] Total rekening di bank data pusat: " + totalRekeningDibuat);

                    // Ini menghapus satu-satunya referensi ke objek Nasabah.
                    // Java Garbage Collector akan mengklaim memori objek Nasabah
                    // karena tidak ada lagi yang menunjuk ke sana.
                    nasabah = null;
                    sudahLogin = false;
                    System.out.println("\n[AKSI] nasabah = null -> profil dihancurkan dari memori program.");

                    System.out.println("\n--- ANALISIS KOMPOSISI: BukuMutasi ---");
                    // BukuMutasi adalah KOMPOSISI milik Rekening.
                    // BukuMutasi dibuat di DALAM constructor Rekening dan
                    // hanya direferensikan oleh field private Rekening.
                    // BukuMutasi tidak punya referensi dari luar -> nasibnya
                    // sepenuhnya bergantung pada Rekening yang memilikinya.
                    // Jika Rekening di-null, BukuMutasi otomatis musnah.
                    System.out.println("  -> BukuMutasi TIDAK PUNYA referensi dari luar Rekening.");
                    System.out.println("  -> BukuMutasi akan musnah otomatis jika Rekening-nya di-null.");
                    System.out.println("  -> Ini membuktikan KOMPOSISI: siklus hidup BukuMutasi = siklus Rekening.");

                    System.out.println("\n--- ANALISIS AGREGASI: Rekening ---");
                    // Rekening masih direferensikan oleh bankDataRekening[].
                    // Walaupun profil nasabah sudah null, Rekening TIDAK MUSNAH.
                    // Ini membuktikan loose-coupling: Rekening bisa hidup tanpa Nasabah.
                    System.out.println("  Meskipun nasabah = null, rekening-rekening MASIH HIDUP:");
                    boolean adaRekeningHidup = false;
                    for (int i = 0; i < totalRekeningDibuat; i++) {
                        if (bankDataRekening[i] != null) {
                            adaRekeningHidup = true;
                            // Rekening masih bisa diakses -> BUKTI AGREGASI berhasil
                            System.out.println("  [MASIH HIDUP] " + bankDataRekening[i].getNomorRekening()
                                    + "  Saldo: Rp" + bankDataRekening[i].getSaldo()
                                    + "  Tipe: " + bankDataRekening[i].getClass().getSimpleName());
                        }
                    }
                    if (!adaRekeningHidup) {
                        System.out.println("  INFO] Belum ada rekening yang dibuat sebelumnya.");
                    }

                    System.out.println("\n+ KESIMPULAN AKHIR +");
                    System.out.println(" Profil Nasabah : MUSNAH -> di-set null, GC akan bersihkan");
                    System.out.println(" Objek Rekening : HIDUP  -> referensi masih ada di bankDataRekening[]");
                    System.out.println(" BukuMutasi     : HIDUP  -> karena Rekening masih hidup");
                    System.out.println(" AGREGASI terbukti : Rekening tetap eksis tanpa Nasabah.");
                    System.out.println(" KOMPOSISI terbukti: BukuMutasi musnah jika Rekening di-null.");
                    break;

                case 0:
                    System.out.println("\nTerima kasih telah menggunakan NeoBank. Sampai jumpa!");
                    break;

                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (pilihan != 0); // Loop terus sampai user pilih 0

        // Tutup Scanner untuk membebaskan resource sistem I/O
        scanner.close();
    }
}