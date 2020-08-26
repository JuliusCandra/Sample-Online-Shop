package com.dev_candra.onlineshopproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.category_fragment.Cart_Fragment;
import com.dev_candra.onlineshopproject.category_fragment.History_Fragment;
import com.dev_candra.onlineshopproject.category_fragment.Home_Fragment;
import com.dev_candra.onlineshopproject.category_fragment.Order_Fragment;
import com.dev_candra.onlineshopproject.category_fragment.Wishlist_Fragment;
import com.dev_candra.onlineshopproject.method.gmail;
import com.dev_candra.onlineshopproject.method.installed;
import com.dev_candra.onlineshopproject.method.proses_dialog;
import com.dev_candra.onlineshopproject.session.session_manager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;


import java.util.HashMap;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Fragment";
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private proses_dialog dialog;
    private installed install;
    private FrameLayout frameLayout;
    private MaterialTextView namaLogo;
    private gmail developerGmail;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static int CURRENT_FRAGMENT = -1;
    private static final int ORDER_FRAGMENT = 2;
    private static final int FAVORITE_FRAGMENT = 3;
    private static final int HISTORY_FRAGMENT = 4;
    private Window window;
    private Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Membuat inisiasi semua komponen
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_main);
        frameLayout = findViewById(R.id.layout_activity_main);
        namaLogo = findViewById(R.id.nama_logo);
        setSupportActionBar(toolbar);
        // Menginisasi GoogleAuth
        mAuth = FirebaseAuth.getInstance();
        dialog = new proses_dialog(this);
        install = new installed(this);
        developerGmail = new gmail(this);
       Aksi();

       // Contoh hide or show menu items
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_account).setVisible(false);
//        menu.findItem(R.id.nav_account).setVisible(false);
//        menu.findItem(R.id.nav_account).setVisible(false);
    }


    // Melakukan aksi pada komponen yang dibuat
    private void Aksi(){
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.bringToFront();
        setDefaultFragment(new Home_Fragment());
        invalidateOptionsMenu(); // mengecek apakah menu tervalidasasi
        setHeaderNavigation();

    }

    // Membuat aksi navigation pada main utama
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int item1 = item.getItemId();
        if  (item1 == R.id.nav_home){
            // handle nav_home
            setHomeFragment();
        }else if (item1 == R.id.nav_cart){
            // handle nav_cart
            gotoFragment("Cart",new Cart_Fragment(),CART_FRAGMENT);
        }else if (item1 == R.id.nav_favorite){
            // handle nav_favorite
            gotoFragment("Favorite",new Wishlist_Fragment(),FAVORITE_FRAGMENT);
        }else if (item1 == R.id.nav_history){
            gotoFragment("History",new History_Fragment(),HISTORY_FRAGMENT);
            // handle nav_history
        }else if (item1 == R.id.nav_myorder){
            gotoFragment("Order",new Order_Fragment(),ORDER_FRAGMENT);
            // handle nav_my_order
        }else if (item1 == R.id.nav_account){
            // handle nav_my_account
        } else if (item1 == R.id.nav_share) {
            // handle nav_share
        }else if (item1 == R.id.nav_logout){
            // handle nav_logut
            Logout();
        }else if (item1 == R.id.nav_send){
            // handle nav_send
        }else if(item1 == R.id.nav_facebook){
            // handle nav_facebook
            openFacebook();
        }else if (item1 == R.id.nav_instagram){
            // handle nav_instagram
            openInstagram();
        }else if (item1 == R.id.nav_whatssApp){
            // handle nav whatsapp
            openWhatsApp();
        }else if (item1 == R.id.nav_developer){
            // handle nav developer
            developer();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Membuat menu pada layout main utama
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (CURRENT_FRAGMENT == HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.menu_bar, menu);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        }
        return true;
    }

    // Membuat fitur aksi pada fitur menu bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
            if (id == R.id.search) {
                // Codingan untuk search
                return true;
            } else if (id == R.id.cart) {
                // codingan untuk cart
                // Membuat method untuk cart
                gotoFragment("Cart", new Cart_Fragment(), CART_FRAGMENT);
                return true;
            } else if (id != R.id.notfication) {
                // codingan untuk notification
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

    private void setNewFragment(Fragment fragment, int fragmentNo){
        Log.d(TAG, "setNewFragment: Fragment");
        if (CURRENT_FRAGMENT != fragmentNo) {
            if  (fragmentNo == HISTORY_FRAGMENT){
                window.setStatusBarColor(Color.parseColor("#5B04B1"));
                toolbar1.setBackgroundColor(Color.parseColor("#5B04B1"));
            }else{
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            CURRENT_FRAGMENT = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }


    /// START Handle Navigation Menu

    // Membuat function untuk membuka Whatssapp
    private void openWhatsApp(){
        final String helloUser = "Hallo developer saya ingin bertanya tentang aplikasi ini";
        final String url = "https://wa.me/6282311558341/?text=" + helloUser;
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean AppInstalled = install.chekInstalled("com.whatsapp");
                if (AppInstalled){
                    dialog.endDialog();
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
                }else{
                    dialog.endDialog();
                    Toast.makeText(MainActivity.this, "Anda belum menginstall aplikasi atau belum aplikasi belum diUpdate", LENGTH_SHORT).show();
                }
            }
        },5000);


    }

    // membuat function untuk membuka instagram
    private void openInstagram(){
        dialog.startLoading();
        final boolean Installed = install.chekInstalled("com.instagram.android");
        final String url = "http://instagram.com/_u/candrajuliussinaga";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Installed){
                    dialog.endDialog();
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
                }else {
                    dialog.endDialog();
                    Toast.makeText(MainActivity.this, "Anda belum menginstall aplikasi atau belum aplikasi belum diUpdate", LENGTH_SHORT).show();
                }
            }
        },5000);

    }

    // membuat function untuk membuka facebook
    private void openFacebook(){
        dialog.startLoading();
        final String url = "https://facebook.com/candra.julius.52";
        final boolean Installed = install.chekInstalled("com.facebook.katana");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Installed){
                    dialog.endDialog();
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
                }else{
                    dialog.endDialog();
                    Toast.makeText(MainActivity.this, "Anda belum menginstall aplikasi atau belum aplikasi belum diUpdate", LENGTH_SHORT).show();
                }
            }
        },5000);

    }

    // membuat akun untuk logout
    private void Logout(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.endDialog();
                signOut();
            }
        },5000);
    }

    // membuat icon hamburger
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    // Mengisiasi Login
    private void LogoutActivity(){
        startActivity(new Intent(MainActivity.this,Tempt.class));
        finish();
    }

    // Membuat Google Sign Out
    private void signOut(){
        mAuth.signOut(); // Melogoutkan user
        // Melakukan aksi untuk Sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG,"Sign Out");
                Toast.makeText(MainActivity.this, "Anda berhasil keluar", LENGTH_SHORT).show();
                LogoutActivity();
            }
        });
    }

    // Membuat function atau method untuk membuka gmail
    private void developer(){
       final String penerima = "candrajulius1@gmail.com";
       final String body = "Beri Masukkan";
       final String tittle = "Judul Masukkan";
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.endDialog();
                developerGmail.sendGmail(penerima,tittle,body);
            }
        },5000);


    }
    /// END Handle Navigation Menu

    // mengambil data dari login utama dan memsukkan nya keheader main utama
    // atau main navigasi
    private void setHeaderNavigation(){
        // Menginisiasi session manager
        session_manager manager = new session_manager(this);
        // mengambil semua data dari session_manager
        HashMap<String,String> sessionManager = manager.getUserDetailFromSessions();

        // Menginisasi dalam sebuah variabel String
        String fullname = sessionManager.get(session_manager.Fullname);
        String email = sessionManager.get(session_manager.Email);

        // Menginisasi header navigation view
        View view = navigationView.getHeaderView(0);
        MaterialTextView textViewUser = view.findViewById(R.id.nama_anda);
        MaterialTextView textViewEmail = view.findViewById(R.id.email_anda);
        textViewUser.setText(fullname);
        textViewEmail.setText(email);

    }

    // Membuat method yang digunakan untuk masuk kentar fragment yang akan menjadi
    // navigasi dari menu utama
    private void gotoFragment(final String tittle, final Fragment fragment, final int noFragment){
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.endDialog();
                namaLogo.setVisibility(View.GONE);
                if (getSupportActionBar() != null){
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setTitle(tittle);
                }
                invalidateOptionsMenu();
                setNewFragment(fragment,noFragment);
                // menengecek apakah variable no fragment sama dengan cartfragment
                // jika sama maka akan masuk ke menu yang ke 3
                if (noFragment == CART_FRAGMENT) {
                    navigationView.getMenu().getItem(1).setChecked(true); // mengambil data dari menu navigasi
                }
            }
        },5000);
    }


    // membuat navigasi home fragment
    private void setHomeFragment(){
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.endDialog();
                namaLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setNewFragment(new Home_Fragment(),HOME_FRAGMENT);
            }
        },5000);
    }


    // Kesalahan suatu algoritma pada program
//    if (CURRENT_FRAGMENT == HOME_FRAGMENT) {
//            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//            MenuInflater menuInflater = getMenuInflater();
//            invalidateOptionsMenu();
//            menuInflater.inflate(R.menu.menu_bar, menu);
//        }


    // Membuat default home utama pada aplikasi
    private void setDefaultFragment(Fragment fragment){
        if (CURRENT_FRAGMENT != MainActivity.HOME_FRAGMENT) {
            CURRENT_FRAGMENT = MainActivity.HOME_FRAGMENT;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }
}
