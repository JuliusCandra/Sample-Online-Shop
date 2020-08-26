package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.MainActivity;
import com.dev_candra.onlineshopproject.session.session_manager;
import com.dev_candra.onlineshopproject.method.databaseFirebase;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.rpc.context.AttributeContext;

import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.dev_candra.onlineshopproject.activity.Tempt.onRegisterFragment;

/**
 * A simple {@link Fragment} subclass.
 */



public class Login_Up extends Fragment{

    private FrameLayout frameLayout;
    private TextView daftar,forgotPaswword,atau1;
    private ImageView imageQuestion;
    private Button login;
    private FirebaseAuth mAuth;
    private TextInputLayout email,password;
    private FirebaseUser firebaseUser;
    private ProgressBar progressBar;
    private LinearLayout loginUp;
    private LinearLayout layout_verification_or_no;
    private databaseFirebase firebaseDatabase;
    private FirebaseDatabase realtimeDatabase;
    private DatabaseReference referenceDatabase;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount account;
    private GoogleSignInOptions optionsGoogle;
    private static final int RC_SIGN_IN = 123;
    public Login_Up() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login__up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daftar = view.findViewById(R.id.daftarLogin);
        forgotPaswword = view.findViewById(R.id.textForgot);
        imageQuestion = view.findViewById(R.id.imageQuestion2);
        login = view.findViewById(R.id.btn_login1);
        if (getActivity() != null) {
            frameLayout = getActivity().findViewById(R.id.tempt);
        }
        mAuth = FirebaseAuth.getInstance();
        email = view.findViewById(R.id.email1);
        password = view.findViewById(R.id.passowrdAnda);
        progressBar = view.findViewById(R.id.proses2);
        loginUp = view.findViewById(R.id.login_up_fragment);
        realtimeDatabase = FirebaseDatabase.getInstance();
        referenceDatabase = realtimeDatabase.getReference("User Google");
        layout_verification_or_no = view.findViewById(R.id.Linear_verification_or_email);
        firebaseDatabase = new databaseFirebase(getActivity());
        Aksi();
        showProgressBar(false);
        showButton(false);
    }

    // Menginisiasi aksi pada semua interface pada layout
    private void Aksi(){
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterFragment = true;
                setNewFragment(new Daftar_Up());
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masukMelaluiEmail();
            }
        });

        forgotPaswword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterFragment = true;
                setNewFragment(new Forgot_Password());
            }
        });

        requestIdToken();
    }


    private void requestIdToken() {
        if (getActivity() != null) {
            optionsGoogle = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            googleSignInClient = GoogleSignIn.getClient(getActivity(), optionsGoogle);
        }
    }

    private void SignInWithGoogle(){
        Intent enterToMainUtama = googleSignInClient.getSignInIntent();
        startActivityForResult(enterToMainUtama,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();;
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        if (getActivity() != null) {
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                UpdateUi(user);
                            }
                        }
                    });
        }
    }

    private void UpdateUi(FirebaseUser user) {
        if (getActivity() != null) {
            if (user != null) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        }
    }

    // Menginisasi fragment
    private void setNewFragment(Fragment fragment) {
        if (getActivity() != null){
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }



    // Mengecek apakah user udah masuk diluan ke main utama atau tidak


    // Membuat validasi user
    @SuppressLint("ResourceAsColor")
    private Boolean validationMasuk(){
        boolean validasi = true;
        String matchesUsername = ".{4,}";
        String Username = Objects.requireNonNull(email.getEditText()).getText().toString();
        if (Username.isEmpty()){
            email.setError("Email tidak boleh kosong");
            email.setErrorEnabled(true);
            email.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.requestFocus();
            validasi = false;
        }else if (!Username.matches(matchesUsername)){
            email.setError("Username minimal 4 karakter");
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            email.requestFocus();
        }
        else if (Username.length() > 10){
            email.setError("Username melebihi 10 karakter");
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            email.requestFocus();
            validasi = false;
        }else{
            email.setError("Username valid");
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            email.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            email.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            email.requestFocus();
        }

        String password1 = Objects.requireNonNull(password.getEditText()).getText().toString().toLowerCase().trim();
        String matches1 = ".{4,}";
        if (password1.isEmpty()){
            password.setError("Password tidak boleh kosong");
            password.setErrorEnabled(true);
            password.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            password.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            validasi = false;
        }else if (!password1.matches(matches1)){
            password.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            password.setError("Password setidaknya 4 karakter");
            password.setErrorEnabled(true);
            password.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validasi = false;
        }else if (password1.length() > 10 ){
            password.setError("Password melebihi 10 karakter");
            password.setErrorEnabled(true);
            password.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            password.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validasi = false;
        }
        else{
            password.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            password.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            password.setError("Password sudah valid");
            password.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            password.setErrorEnabled(true);
        }

        return validasi;
    }

    // Membuat validasasi login
    private void masukMelaluiEmail(){
        // Jika Koneksi Internet tidak ada
        // maka kembalikan nilai
        if (!checkKoneksiInternet()){
            return;
        }
        // Jika koneksi internet ada makan kembalikan
        // nilai
        if (!validationMasuk()) {
            return;
        }
        inToMainUtama();
    }

   // Membuat progressbar muncul
    private void showProgressBar(boolean proses){
        if (proses){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    // Membuat tombol button login dan login google hilang
    private void showButton(boolean buttonLogin){
        if (buttonLogin){
            login.setVisibility(View.GONE);
        }else{
            login.setVisibility(View.VISIBLE);
        }
    }


    // Membuat snack bar login dengan email yang menyatakan bahwasan
    // anda gagal login dikarenakan koneksi internet tidak ada
    private void showSnackBarLoginWithEmail(){
        showProgressBar(false);
        showButton(false);
        Log.d(TAG, "showSnackBarLoginWithEmail: Active");
        final Snackbar snackbar = Snackbar.make(loginUp,"Anda gagal login, Periksa koneksi internet anda",Snackbar.LENGTH_SHORT)
                .setAction("Coba lagi", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgressBar(false); // progress bar nampak
                        showButton(false); // button login menghilang
                        masukMelaluiEmail();
                    }
                });
        snackbar.show();
    }

    // Mengkonfigurasi masuk ke main utama dengan email
    private void signOnEmail(FirebaseUser user){
        Log.d(TAG, "signOnEmail: Active");
        if (user != null) {
            if (getActivity() != null) {
                // handle enter to main utama
                enterToMainContent();
            }
        }
    }


    // Mengecek apakah email telah diverifikasi user atau tidak
    private void emailVerifikasi(final String fullname, final String email, final String username, final String password, final String nmrTelepon){
        Log.d(TAG, "emailVerifikasi: Active");
        firebaseUser = mAuth.getCurrentUser();
            if (firebaseUser != null && firebaseUser.isEmailVerified()){
                    session_manager manager = new session_manager(Objects.requireNonNull(getActivity()));
                    manager.createLoginSessions(fullname,username,email,password,nmrTelepon);
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    getActivity().finish();
            }else{
                Toast.makeText(getActivity(), "Bermasalah", Toast.LENGTH_SHORT).show();
            }
        }

    // Dialog yang memunculkan bahwasannya email user belum diverifiaksi
    // system akan menyuruh user untuk mengecek verifikasi email yang dikirim oleh system
    private void showDialogVerifikasiEmail(){
        showProgressBar(false);
        showButton(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.verification_or_no,layout_verification_or_no);
        builder.setView(view);
        view.findViewById(R.id.image_close);
        view.findViewById(R.id.image_error);
        view.findViewById(R.id.text_error_email);
        view.findViewById(R.id.button_error_email);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.image_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(false);
                showButton(false);
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.button_error_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);
                showButton(true);
                verifiasiEmailSekarang();
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();

    }

    // Jika user menekan tombol verfikasi maka
    // User verifikasi email akan terkirim ke email user
    private void verifiasiEmailSekarang(){
        firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    showProgressBar(false);
                    showButton(false);
                    Toast.makeText(getActivity(), "Verifkasi Email Telah Dikirim, Chek Email kamu Sekarang", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Belum membuat UI untuk chek koneksi Internet
    // Membuat method chek koneksi Internet
    private Boolean checkKoneksiInternet(){
        boolean validate = true;
        showProgressBar(true);
        showButton(true);
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()){
            showButton(false);
            showProgressBar(false);
            Log.d(TAG, "checkKoneksiInternet: ActiveCheckInternet");
        }else{
            showSnackBarLoginWithEmail();
            validate = false;
        }
        return validate;
    }




    /// Masuk ke main utama dengan menggunakan shared preference
    // Mengecek apakah user sudah login atau tidak
    // jika user sudah login maka akun akan lengket dan jika user mau membuka aplikasi
    // akan masuk ke main utama



    private void enterToMainContent() {
        final String emailUser = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        final String passwordUser = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
            final Query checkUser = databaseReference.orderByChild("email").equalTo(emailUser);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot snapshot) {
                    Log.d(TAG, "onDataChange: login");
                    showProgressBar(true); // progress bar nampak
                    showButton(true); // button dan teks serta button login gone
                    if (snapshot.exists()) {
                        String passowordDatabase = snapshot.child("password").getValue(String.class);
                        if (passowordDatabase != null && passowordDatabase.equals(passwordUser)) {
                                password.setError(null);
                                password.requestFocus();
                                password.setErrorEnabled(false);
                                email.setError(null);
                                email.requestFocus();
                                email.setErrorEnabled(false);
                                final String email12 = snapshot.child(emailUser).child("email").getValue(String.class);
                                final String password12 = snapshot.child(emailUser).child("password").getValue(String.class);
                                final String username1 = snapshot.child(emailUser).child("usename").getValue(String.class);
                                final String nmrTelepone = snapshot.child(emailUser).child("nmrTelepn").getValue(String.class);
                                final String fullname = snapshot.child(emailUser).child("fullname").getValue(String.class);

                            } else {
                                showProgressBar(false);
                                showButton(false);
                                password.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                                password.setErrorEnabled(true);
                                password.setError("Password anda salah");
                                password.requestFocus();
                                password.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                            }
                        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }


    private void inToMainUtama(){
        final String usernameUser = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        final String passwordUser = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("User");
        Query chekUser = databaseReference.orderByChild("username").equalTo(usernameUser);
        chekUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String password = snapshot.child(usernameUser).child("password").getValue(String.class);
                    if (password != null && password.equals(passwordUser)){
                        startActivity(new Intent(getActivity(),MainActivity.class));
                        Objects.requireNonNull(getActivity()).finish();
                    }else{
                        Toast.makeText(getActivity(), "Password salah", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void emailYangMasuk(){
        String matchesUser = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        String emailAnda = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        String passwordAnda = Objects.requireNonNull(password.getEditText()).getText().toString().trim().toLowerCase();
        if (emailAnda.matches(matchesUser) && passwordAnda.length() > 10){
            mAuth.createUserWithEmailAndPassword(emailAnda,passwordAnda).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                    }else{

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

}
