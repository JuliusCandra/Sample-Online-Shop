package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.MainActivity;
import com.dev_candra.onlineshopproject.method.databaseFirebase;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */






public class Daftar_Up extends Fragment {

    private static final String TAG = "Daftar UP";
    private MaterialTextView sudahMemilikiAkun, textError;
    private FirebaseAuth mAuth;
    private FrameLayout frameLayout;
    private ImageView imagequestion, backStack;
    private Button daftar;
    private TextInputLayout textFullname, textEmail, textPassword, textConfirmPassword, textPhone, Username;
    private ProgressBar progressBar;
    private FirebaseFirestore firebaseFirestore;
    private LinearLayout layout, layout1, layout2, layout_fragment_daftar_up, layout_error_signal, layout_email;
    private databaseFirebase firebaseDatabase;

    public Daftar_Up() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar__up, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daftar = view.findViewById(R.id.btn_daftar);
        if (getActivity() != null) {
            frameLayout = getActivity().findViewById(R.id.tempt);
        }
        imagequestion = view.findViewById(R.id.imageQuestion2);
        backStack = view.findViewById(R.id.backStack);
        sudahMemilikiAkun = view.findViewById(R.id.btn_login);
        textFullname = view.findViewById(R.id.textFullname);
        textEmail = view.findViewById(R.id.emailSatu);
        textPassword = view.findViewById(R.id.password1);
        textConfirmPassword = view.findViewById(R.id.password2);
        textPhone = view.findViewById(R.id.textPhone);
        Username = view.findViewById(R.id.userName);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressBar = view.findViewById(R.id.proses1);
        layout = getActivity().findViewById(R.id.layout_succes);
        layout1 = getActivity().findViewById(R.id.layout_email_verification);
        layout2 = getActivity().findViewById(R.id.layout_okay_verification);
        layout_fragment_daftar_up = view.findViewById(R.id.fragment_daftar_up);
        layout_error_signal = view.findViewById(R.id.layout_error_signal);
        layout_email = view.findViewById(R.id.layout_email_sudah_digunakan);
        firebaseDatabase = new databaseFirebase(getActivity());
        Aksi1();
        getDataFromLogin_UP();
    }


    // Menginisiasi aksi pada semua interface pada layout
    private void Aksi1() {
        sudahMemilikiAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(new Login_Up());
            }
        });

        imagequestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKoneksiInternet();
            }
        });

        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(new Login_Up());
            }
        });
    }


    // mengambil data jika user masuk melalui google
    private void getDataFromLogin_UP() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String emailUser = bundle.getString("email");
            String fullnameUser = bundle.getString("fullname");
            Objects.requireNonNull(textEmail.getEditText()).setText(emailUser);
            Objects.requireNonNull(textFullname.getEditText()).setText(fullnameUser);
        }
    }

    // Menginisasi fragment
    private void setNewFragment(Fragment fragment) {
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }


    // Membuat validasasi formulir pendfataran
    @SuppressLint("ResourceAsColor")
    private Boolean ValidatePendaftaran() {
        boolean validation = true;
        String email = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
        String emailMatches = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        if (email.isEmpty()) {
            textEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textEmail.setError("Email tidak boleh kosong");
            textEmail.setErrorEnabled(true);
            textEmail.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            validation = false;
        } else if (!email.matches(emailMatches)) {
            textEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textEmail.setError("Email tidak valid");
            textEmail.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textEmail.setErrorEnabled(true);
            validation = false;
        } else {
            textEmail.setError("Email sudah valid");
            textEmail.setErrorEnabled(true);
            textEmail.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            textEmail.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            textEmail.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
        }


        String textFullName1 = Objects.requireNonNull(textFullname.getEditText()).getText().toString().toLowerCase().trim();
        if (textFullName1.isEmpty()) {
            textFullname.setError("Fullname tidak boleh kosong");
            textFullname.setErrorEnabled(true);
            textFullname.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textFullname.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            validation = false;
        } else if (textFullName1.length() > 20) {
            textFullname.setError("Fullname tidak boleh melebihi 20 karakter");
            textFullname.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textFullname.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textFullname.setErrorEnabled(true);
            validation = false;
        } else {
            textFullname.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            textFullname.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            textFullname.setError("Fullname Anda sudah valid");
            textFullname.setErrorEnabled(true);
            textFullname.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
        }

        String Username1 = Objects.requireNonNull(Username.getEditText()).getText().toString().toLowerCase().trim();
        String matchesUsername = ".{4,}";
        if (Username1.isEmpty()) {
            Username.setError("Username tidak boleh kosong");
            Username.setErrorEnabled(true);
            Username.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            Username.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            validation = false;
        } else if (Username1.length() > 15) {
            Username.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            Username.setErrorEnabled(true);
            Username.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            Username.setError("Username tidak boleh melebihi 15 karakter");
            validation = false;
        } else if (!Username1.matches(matchesUsername)) {
            Username.setError("Username setidaknya 4 karakter");
            Username.setErrorEnabled(true);
            Username.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            Username.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            validation = false;
        } else {
            Username.setError("Username sudah valid");
            Username.setErrorEnabled(false);
            Username.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            Username.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            Username.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
        }

        String Password1 = Objects.requireNonNull(textPassword.getEditText()).getText().toString().toLowerCase().trim();
        String passowrdMatches = ".{4,}";

        if (Password1.isEmpty()) {
            textPassword.setError("Password tidak boleh kosong");
            textPassword.setErrorEnabled(true);
            textPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validation = false;
        } else if (!Password1.matches(passowrdMatches)) {
            textPassword.setError("Password setidaknya 4 karakter");
            textPassword.setErrorEnabled(true);
            textPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validation = false;
        } else if (Password1.length() > 10) {
            textPassword.setError("Password melebih 10 karakter");
            textPassword.setErrorEnabled(true);
            textPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validation = false;
        } else {
            textPassword.setError("Password sudah valid");
            textPassword.setErrorEnabled(false);
            textPassword.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            textPassword.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            textPassword.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
        }

        String ConfirmPassoword = Objects.requireNonNull(textConfirmPassword.getEditText()).getText().toString().toLowerCase().trim();
        String matchesConfirmPassword = ".{4,}";
        if (ConfirmPassoword.isEmpty()) {
            textConfirmPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textConfirmPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textConfirmPassword.setError("Confirm Password tidak boleh kosong");
            textConfirmPassword.setErrorEnabled(true);
            validation = false;
        } else if (ConfirmPassoword.length() > 10) {
            textConfirmPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textConfirmPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textConfirmPassword.setError("Confirm Password melebihi 10 karakter");
            textConfirmPassword.setErrorEnabled(true);
            validation = false;
        } else if (!ConfirmPassoword.matches(matchesConfirmPassword)) {
            textConfirmPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textConfirmPassword.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textConfirmPassword.setErrorEnabled(true);
            textConfirmPassword.setError("Confirm Password setidaknya 4 karakter");
            validation = false;
        } else {
            textConfirmPassword.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            textConfirmPassword.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            textConfirmPassword.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            textConfirmPassword.setError("Confirm Password sudah valid");
            textConfirmPassword.setErrorEnabled(false);
        }

        String Phone = Objects.requireNonNull(textPhone.getEditText()).getText().toString();
        if (Phone.isEmpty()) {
            textPhone.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textPhone.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPhone.setError("Nomor Handphone tidak boleh kosong");
            textPhone.setErrorEnabled(true);
            validation = false;
        }else if (Phone.length() < 12){
            textPhone.setError("Nomor Handphone harus 12 karakter");
            textPhone.setErrorEnabled(true);
            textPhone.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPhone.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }
        else if (Phone.length() > 12) {
            textPhone.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textPhone.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            textPhone.setError("Nomor Handphone melebihi 12 karakter");
            textPhone.setErrorEnabled(true);
            validation = false;
        } else {
            textPhone.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            textPhone.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
            textPhone.setErrorTextColor(ColorStateList.valueOf(Color.GREEN));
            textPhone.setError("Nomor Handphone sudah Valid");
            textPhone.setErrorEnabled(false);
        }

        return validation;
    }

    // Method atau function yang digunakan untuk
    // jika user melengkapi formulir pendaftaran
    // maka akun user akan dibuat
    private void PendaftaranUser() {
        if (!ValidatePendaftaran()) {
            return;
        }
        chekDataUser();
    }

    // Mengecek apakah user sudah masuk ke main utama lebih dahulu
    // Jika user sudah login diluan maka akan muncul main utama
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Mengecek apakah user telah masuk ke main utama dengan kata sandi dan email
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Log.d(TAG, "onStart: Active");
//        UpdateUI(currentUser);
//    }

    // Mengkonfigurasi Main Utama
    // Ketka akun user dibuat maka akan masuk main utama
    private void UpdateUI(FirebaseUser user) {
        if (user != null) {
            if (getActivity() != null) {
                String newUser = "Pengguna Baru";
                Toast.makeText(getActivity(), "Selamat datang " + newUser, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        } else {
            Toast.makeText(getActivity(), "Silahkan Daftar", Toast.LENGTH_SHORT).show();
        }
    }

    // Method yang digunakan untuk membuat akun user
    // dan Memasukkan Akun User ke Firebase Firestore
    @SuppressLint("ResourceAsColor")
    private void emailAndPassoword() {
        String emailMatches = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        final String email1 = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
        final String Password1 = Objects.requireNonNull(textPassword.getEditText()).getText().toString().toLowerCase().trim();
        final String PassowrdConfirm = Objects.requireNonNull(textConfirmPassword.getEditText()).getText().toString().toLowerCase().trim();
        final String fullname = Objects.requireNonNull(textFullname.getEditText()).getText().toString().toLowerCase().trim();
        final String phone = Objects.requireNonNull(textPhone.getEditText()).getText().toString();
        if (email1.matches(emailMatches)) {
            if (Password1.equals(PassowrdConfirm)) {
                showProgress(true);
                hideButton(true);
                mAuth.createUserWithEmailAndPassword(email1, Password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Map<Object, String> userData = new HashMap<>();
                            userData.put("fullname", fullname);
                            userData.put("password", Password1);
                            userData.put("email", email1);
                            userData.put("phone", phone);
                            firebaseFirestore.collection("USERS")
                                    .add(userData)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                                if (user != null) {
                                                    Log.d(TAG, "Success emailAndPassword");
                                                    storeDataToFirebase();
                                                    showDialogSuccesFull();
                                                }
                                            } else {
                                                showProgress(false);
                                                hideButton(false);
                                                showDialogNoSignal();
                                                Log.d(TAG, "Failed emailAndPassword");
                                                UpdateUI(null);
                                            }
                                        }
                                    });
                        }
                    }
                });
            } else {
                textConfirmPassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                textConfirmPassword.setError("Password tidak sama");
                textConfirmPassword.setErrorEnabled(true);
            }
        } else {
            textEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            textEmail.setError("Email tidak valid");
            textEmail.setErrorEnabled(true);
        }
    }

    // Memunculkan Progressbar
    private void showProgress(boolean proses) {
        if (proses) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);

        }
    }

    // Menyembunyikan button
    private void hideButton(boolean button) {
        if (button) {
            daftar.setVisibility(View.GONE);
        } else {
            daftar.setVisibility(View.VISIBLE);
        }
    }

    // Logic
    // Ketika User menekan daftar maka akan tampil AlertDialog
    // lalu ketika user menekan yes maka user akan diarahkan ke fragment login dan user akan memasukkan
    // Email dan password yang tadi didaftar
    private void showDialogSuccesFull() {
        showProgress(false);
        hideButton(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.succesfull, layout);
        builder.setView(view);
        view.findViewById(R.id.betul);
        view.findViewById(R.id.texkSukses);
        view.findViewById(R.id.tombol_ya);
        view.findViewById(R.id.tombol_tidak);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.tombol_ya).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                hideButton(true);
                showDialogVerificationEmail();
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.tombol_tidak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(false);
                hideButton(false);
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();

    }

    // Membuat dilaog yang menyatakan apakah user mau memeverfikasi emailnya
    // Jika user menekan ok maka verifikasi email akan dikirim ke email user yang
    // telah didaftar oleh user
    // Jika tidak maka verfikasi email tidak dikirim
    private void showDialogVerificationEmail() {
        showProgress(false);
        hideButton(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.verification_email, layout1);
        builder.setView(view);
        view.findViewById(R.id.murung);
        view.findViewById(R.id.texkEmailVerifikasi);
        view.findViewById(R.id.tombol_ya_verifaksi_email);
        view.findViewById(R.id.tombol_tidak_verifikasi_email);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.tombol_ya_verifaksi_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                hideButton(true);
                verifikasiEmail();
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.tombol_tidak_verifikasi_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(false);
                hideButton(false);
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }

    // Method yang akan memberitahukan user bahwasannya verifikasi email berhasil dikirim
    // ke Email User
    private void showDialogOkay() {
        showProgress(false);
        hideButton(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.verifikasi_okay, layout2);
        builder.setView(view);
        view.findViewById(R.id.senyum);
        view.findViewById(R.id.teksSenyum);
        view.findViewById(R.id.tombol_oke_verifaksi_email);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.tombol_oke_verifaksi_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                hideButton(true);
                FirebaseUser user = mAuth.getCurrentUser();
                setNewFragment(new Login_Up());
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }

    // Method yang digunakan untuk memeverifikasi email
    private void verifikasiEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showDialogOkay();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar.make(layout_fragment_daftar_up, "Maaf anda gagal mengirim verifikasi email,Periksa koneksi internet anda", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Coba lagi", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showProgress(true);
                                    showDialogVerificationEmail();
                                }
                            });
                }
            });
        }
    }


    // Membuat dialog yang menyatakan bahwasannya
    // user tidak memiliki internet atau koneksi internet
    private void showDialogNoSignal() {
        showProgress(false);
        hideButton(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.no_signal, layout_error_signal);
        builder.setView(view);
        view.findViewById(R.id.close);
        view.findViewById(R.id.no_signal);
        view.findViewById(R.id.teksErrorSignal);
        view.findViewById(R.id.tombol_error_signal);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.tombol_error_signal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(false);
                hideButton(false);
                checkKoneksiInternet();
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }

    }

    // Untuk mengecek apakah email sudan digunakan atau tidak
//    private void checkEmailSudahDigunakan() {
//        showProgress(true);
//        hideButton(true);
//        String email1 = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
//        mAuth.fetchSignInMethodsForEmail(email1)
//                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//                    @SuppressLint("ResourceAsColor")
//                    @Override
//                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                        boolean check = !Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getSignInMethods()).isEmpty();
//                        if (!check) {
//                            showProgress(false);
//                            hideButton(false);
//                            emailAndPassoword();
//                            textEmail.setErrorEnabled(true);
//                            textEmail.setError("Email dapat digunakan");
//                            textEmail.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
//                            textEmail.setErrorTextColor(ColorStateList.valueOf(R.color.colorTeksReset));
//                            storeDataToFirebase();
//                        } else {
//                            showDialogEmailAlreadyExist();
//                        }
//                    }
//                });
//    }

//    private void meneruskanTindakanEmail(){
//        String url = "https://online-shop-579b9.firebaseapp.com/__/auth/action";
//        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
//                .setUrl(url)
//                .setAndroidPackageName("com.dev_candra.onlineshopproject",false,null)
//                .build();
//        verifikasiEmail(actionCodeSettings);
//
//    }

    // Dialog yang memunculkan bahwasannya email telah digunakan
//    private void showDialogEmailAlreadyExist() {
//        showProgress(false);
//        hideButton(false);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.email_sudah_digunakan, layout_email);
//        builder.setView(view);
//        view.findViewById(R.id.error_email);
//        view.findViewById(R.id.error_teks_email);
//        view.findViewById(R.id.tombol_error_email);
//        final AlertDialog alertDialog = builder.create();
//
//        view.findViewById(R.id.tombol_error_email).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showProgress(false);
//                hideButton(false);
//                alertDialog.dismiss();
//            }
//        });
//
//        if (alertDialog.getWindow() != null) {
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
//        }
//        alertDialog.show();
//    }


    // Start logic
    // Logika untuk menyelesaikan yang method email ditemukan atau tidak
    // kita masukkan ke chek internet apakah user online atau tidak
    // Jika user online masukkan method email ditemukan atau tidak
    // Jika offline kita masukkan user offline dan jangan masukkan email ditemukan atau tidak ditemukan
    // End Logic

    private void checkKoneksiInternet() {
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()) {
            PendaftaranUser();
        } else {
            showProgress(true);
            hideButton(true);
            showDialogNoSignal();
        }
    }

    // Menyimpan data user ke firebase
    private void storeDataToFirebase() {
        String fullnamae = Objects.requireNonNull(textFullname.getEditText()).getText().toString().toLowerCase().trim();
        String username = Objects.requireNonNull(Username.getEditText()).getText().toString().toLowerCase().trim();
        String email = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
        String password = Objects.requireNonNull(textPassword.getEditText()).getText().toString().toLowerCase().trim();
        String nmrHp = Objects.requireNonNull(textPhone.getEditText()).getText().toString().toLowerCase().trim();
        firebaseDatabase.menyimpanData(fullnamae, username, email, nmrHp, password);
    }

    // Mnegecek data user apakah ada di firebase atau tidak
    private void chekDataUser() {
        final String nomorTelepon = Objects.requireNonNull(textPhone.getEditText()).getText().toString();
        final String fullname1 = Objects.requireNonNull(textFullname.getEditText()).getText().toString().toLowerCase().trim();
        final String username1 = Objects.requireNonNull(Username.getEditText()).getText().toString().toLowerCase().trim();
        final String emailUser1 = Objects.requireNonNull(textEmail.getEditText()).getText().toString();
        // Membaca data dan mengecek data dari firebase database
        FirebaseDatabase databaseFirebase = FirebaseDatabase.getInstance(); // Menginisiasi firebasedatabse dari firebase
        DatabaseReference databseReference = databaseFirebase.getReference("User"); // membaca data dari firebase dengan refrencenya user
        Query user = databseReference.orderByChild("nmrTelepn").equalTo(nomorTelepon); // Mencari data dari databse reference dengan orderan dari nmrtelepon dan sana dengan nmr telepon
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showProgress(true);
                hideButton(true);
                if (snapshot.exists()) {
                    showProgress(false);
                    hideButton(false);
                    String username = snapshot.child(nomorTelepon).child("username").getValue(String.class); // Mengambil nilai username
                    String nmrTelepon1 = snapshot.child(nomorTelepon).child("nmrTelepn").getValue(String.class);
                    String fullname = snapshot.child(nomorTelepon).child("fullname").getValue(String.class);
                    String emailUser = snapshot.child(nomorTelepon).child("email").getValue(String.class);
                    if (username != null && username.equals(username1)) {
                        Username.setError("username telah digunakan");
                        Username.requestFocus();
                        Username.setErrorEnabled(true);
                        Username.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                    } else if (fullname != null && fullname.equals(fullname1)) {
                        textFullname.setError("Fullname telah digunakan");
                        textFullname.requestFocus();
                        textFullname.setErrorEnabled(true);
                        textFullname.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                    } else if (nmrTelepon1 != null && nmrTelepon1.equals(nomorTelepon)) {
                        textPhone.setError("nmr telepon telah digunakan");
                        textPhone.requestFocus();
                        textPhone.setErrorEnabled(true);
                        textPhone.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                    }else if (emailUser != null && emailUser.equals(emailUser1)){
                        textEmail.setError("Email telah digunakan");
                        textEmail.requestFocus();
                        textEmail.setErrorEnabled(true);
                        textEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                    }
                    else {
                        textFullname.setError(null);
                        textFullname.setErrorEnabled(false);
                        textEmail.setError(null);
                        textEmail.setErrorEnabled(false);
                        textPhone.setError(null);
                        textPhone.setErrorEnabled(false);
                        Username.setError(null);
                        Username.setErrorEnabled(false);
                    }

                }else{
                    showProgress(false);
                    hideButton(false);
                    emailAndPassoword();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Anda mengagalkan data", Toast.LENGTH_SHORT).show();
                hideButton(false);
                showProgress(false);
            }
        });


    }


//     Mengecek data user melalui Google Sign In
//    private void checkDataUserGoogle() {
//        final String emailUser = Objects.requireNonNull(textEmail.getEditText()).getText().toString().trim();
//        final String fullName1 = Objects.requireNonNull(textFullname.getEditText()).getText().toString().trim().toLowerCase();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Google");
//        final Query chekUser = reference.orderByChild("email").equalTo(emailUser);
//        chekUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    String userName = snapshot.child(emailUser).child("fullname").getValue(String.class);
//                    String emailUser1 = snapshot.child(emailUser).child("email").getValue(String.class);
//                    if ( userName != null && userName.equals(fullName1)){
//                        textFullname.setError("Fullname telah digunakan");
//                        textFullname.requestFocus();
//                        textFullname.setErrorEnabled(true);
//                        textFullname.setErrorTextColor(ColorStateList.valueOf(Color.RED));
//                        textFullname.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
//                    }else if (emailUser1 != null && emailUser1.equals(emailUser)){
//                        textEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
//                        textEmail.setError("email telah digunakan");
//                        textEmail.setErrorTextColor(ColorStateList.valueOf(Color.RED));
//                        textEmail.requestFocus();
//                    }else{
//                        emailAndPassoword();
//                        textEmail.setErrorTextColor(null);
//                        textEmail.setErrorEnabled(false);
//                        textEmail.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
//                        textEmail.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
//                        textFullname.setErrorTextColor(null);
//                        textFullname.setErrorEnabled(false);
//                        textFullname.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
//                        textFullname.setErrorIconTintList(ColorStateList.valueOf(Color.GREEN));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), "Anda mengagalkan data", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}


