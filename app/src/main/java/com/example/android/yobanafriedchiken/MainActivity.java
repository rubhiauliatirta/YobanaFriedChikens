package com.example.android.yobanafriedchiken;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
// jadi enaknya OOP ini adalah kita bisa membagi barisan2 statment kita kedalam program2 kecil yang dalam hal ini kita buat
//dalam bentuk method sebenarnya bisa juga semuanya itu kita buat dalam onCreate, cuma bakalan penuh dan kalau ada error jadi susah
//lihat dimana errornya

public class MainActivity extends AppCompatActivity {

    //mendeklarasikan variable global (agar bisa diakses di dalam method kita) untuk penampung views yang kita buat di layout
    Button tombolMinusPaha;
    Button tombolPlusPaha;
    Button tombolMinusDada;
    Button tombolPlusDada;
    Button tombolOrder;
    
    TextView teksURL;
    TextView teksJumlahPaha;
    TextView teksJumlahDada;
    TextView teksTotalBayar;
    TextView teksOrderSummary;
    
    CheckBox extraKremesh;
    
    // variabel global (agar bisa diakses di dalam method kita) utk penampung jumlah kuantitas paha dan dada saat ini
    int kuantitasPaha=0;
    int kuantitasDada=0;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { // didalam ini dari buka kurang hingga tutup kurung X adalah apa yang dilakukan aplikasi kita ketika aplikasi kita ini jalan / onCreate.
        //jadi ceritanya kita mengoveride/mengimplementasikan kan method onCreate ini (baca lebihlanjut mengenai lifecycle android)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// menentukan layout mana yang digunakan activity ini

        // mengisi variable kita diatas tadi yang masih kosong sesuai dengan view yang terdapat pada layout xml yang sudah kita buat
        tombolMinusPaha = (Button) findViewById(R.id.minusPaha);
        tombolPlusPaha = (Button) findViewById(R.id.plusPaha);
        tombolMinusDada = (Button) findViewById(R.id.minusDada);
        tombolPlusDada = (Button) findViewById(R.id.plusDada);
        tombolOrder = (Button) findViewById(R.id.btnOrder);
        teksURL = (TextView) findViewById(R.id.tv_url);
        teksJumlahPaha = (TextView) findViewById(R.id.jumlahPaha);
        teksJumlahDada = (TextView) findViewById(R.id.jumlahDada);
        teksTotalBayar = (TextView) findViewById(R.id.totalBayar);
        teksOrderSummary = (TextView) findViewById(R.id.ordersummary);
        extraKremesh = (CheckBox) findViewById(R.id.extra);
        
    } //<-- ini tutup kurung X

    //method untuk menjalankan statment2 untuk mengurangi jumlah kuantitas paha
    public void kurangPaha(View v){
        //karena kuantitas tidak boleh minus maka kita harus buat kondsional
        if(kuantitasPaha!=0) //jika nilai kuantitas kita tidak sama dengan nol, maka ( notasi tidak sama dengan adalah !=)
        {
            kuantitasPaha = kuantitasPaha - 1; // kuantitasPaha yang baru adalah kuantitasPaha saat ini dikurangi 1
            teksJumlahPaha.setText(kuantitasPaha+""); // mengupdate text pada textview yang menampilkan jumlah paha (id jumlahPaha)
            //kuantitasPaha--; artinya sama dengan diatas
        }
    }
    //method untuk menjalankan statment2 untuk menambah jumlah kuantitas paha
    public void tambahPaha(View v)
    {
        kuantitasPaha++; //ini sama aja denan kuantitasPaha = kuantitasPaha + 1
        teksJumlahPaha.setText(kuantitasPaha+"");// mengupdate text pada textview yang menampilkan jumlah paha (id jumlahPaha)
    }

    // sama aja kaya yang method kurangPaha dan tambahPaha
    public void kurangDada(View v){
        if(kuantitasDada!=0) {
            kuantitasDada = kuantitasDada - 1;
            teksJumlahDada.setText(kuantitasDada+"");
            //kuantitasDada--; artinya sama dengan diatas
        }
    }
    public void tambahDada(View v)
    {
        kuantitasDada++;
        teksJumlahDada.setText(kuantitasDada+"");
    }
    /*
    *perhatikan ada yang menerima parameter View v (atas) ada yang tidak (bawah) (parameter adalah syarat penggunaan method, kita harus menyediakan parameter sesuai tipe datanya (dalam hal ini View))
    * diatas harus kita buat menggunakan parameter karena dia method ini akan dipanggil dari xml sehingga dia menerima parameter View
    * sedangkan yang dibawah  (hitungTotalHarga) di design tidak menerima parameter jika method ini ingin dipakai.
     */
    private int hitungTotalHarga(){//contoh membuat method yang punya nilai kembali/output (dalam hal ini integer (int)) tidak hanya statment2

        int total; //kita buat variable lokal --> karena dia tidak akan diakses dari luar method ini
        total = (kuantitasDada*8500) + (kuantitasPaha*7000); // hitungan biasa, untuk cari harga total
        if(extraKremesh.isChecked())// method isChecked() adalah method untuk mengecek apakah si view checkbox yang kita buat tadi di ceklik atau nggak, nilai kembalinya udah langsung boolean (true/false)
        {
            total = total + 2000;// jika di cek berarti harga total ditambah 2000
        }
        return total;// karena kita membuat method yang punya nilai kembali, jadi harus di return sesuai tipe data nilai kembalinya
    }

    //method yang disiapkan ketika user menekan order
    public void tekanOrder(View v)
    {
        // nilai total di xml kita tadi akan di update dengan method setText ini dengan text dari method hitungTotalHarga
        teksTotalBayar.setText(hitungTotalHarga()+"");
        // snamun kita harus ngecek juga karena kita mau nampilkan order summary nya kan.
        // chek apakah total harganya sama dengan 0 yang artinya user tidak order apapun
        if(hitungTotalHarga() == 0)
        {
            //tampilkan Toast dengan teks "silahkan order", informasi kecil yang muncul sesaat
            Toast.makeText(getApplicationContext(),"silahkan order",Toast.LENGTH_SHORT).show();
            // textview oredersummary nya kita buat tidak tampak/invisible
            teksOrderSummary.setVisibility(View.INVISIBLE);
        }
        else
        {
            //disini adalah kondisi jika pengondisian diatas (hitungTotalHarga()==0) bernilai salah
            //artinya kan totalnya tidak 0, berarti user memesan sesuatu
            //tampilkan Toast dengan teks "Terimakasih", informasi kecil yang muncul sesaat
            Toast.makeText(getApplicationContext(),"Terima Kasih!",Toast.LENGTH_SHORT).show();
            teksOrderSummary.setText(getSummary());
            teksOrderSummary.setVisibility(View.VISIBLE);
        }
    }

    //method yang kita buat untuk mendapatkan summary order
    private String getSummary()
    {
        String teks = ""; // deklarasikan dulu text sementara kita nilainya kosonng

        if(kuantitasPaha!=0) // jika kuantitas tidak sama dengan 0, artinya ada pesanan paha
        {
            //misal kuantitasPaha = 4
            teks = teks + "Paha : " + kuantitasPaha + " pcs\n";
            // / dengan ini nanti keluar :
            // Paha : 4 pcs (newline/enter --> karena kita pake karakter \n)
        }
        if(kuantitasDada!=0) // jika kuantitas tidak sama dengan 0, artinya ada pesanan Dada
        {
            teks = teks + "Dada : " + kuantitasDada + " pcs\n";
        }
        if(extraKremesh.isChecked()) // jika diceklis, berarti user minta tambahan kremesh
        {
            teks = teks + " + kremesh";
        }
        return teks;
    }

    //method yang statment2nya akan membuat intent implicit untuk membuka sebuah url, dalm hal ini www.google.com
    public void tekanURL(View v)
    {
        Uri webpage = Uri.parse("http://www.google.com/");// buat object Uri yang web nya google
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage); //parameter constructor Intent ini (String Action, Uri uri) disini kita membuat objek intentnya
        if (intent.resolveActivity(getPackageManager()) != null) //jika dalam aplikasi kita ada aplikasi untuk membuka web
        {
            startActivity(intent); // jika benar maka dia akan start activitynya
        }
    }
}
