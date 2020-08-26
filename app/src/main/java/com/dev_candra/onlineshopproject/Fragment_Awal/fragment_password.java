package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Tempt;
import com.dev_candra.onlineshopproject.databinding.FragmentPasswordBinding;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_password extends Fragment {

    private Button buttonKonfirmasi;
    private TextInputLayout passowrdReset,confirmPasswordReset;
    private ProgressBar progressBar2;
    private LinearLayout fragmentPassword;

    public fragment_password() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_password,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonKonfirmasi = view.findViewById(R.id.button_konfirmasi);
        passowrdReset = view.findViewById(R.id.passowrd_reset);
        confirmPasswordReset = view.findViewById(R.id.confirm_password_reset);
        progressBar2 = view.findViewById(R.id.progressBar2);
        fragmentPassword = view.findViewById(R.id.fragment_password);
        chekInputData();
        Aksi();
    }

    private void Aksi(){
        buttonKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekKoneksiInternet();
            }
        });

    }


    private void chekInputData(){
        Log.d(TAG, "chekInputData: ActiveOnChekInputData");
        Objects.requireNonNull(passowrdReset.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if  (TextUtils.isEmpty(s)){
                    passowrdReset.setError("Password tidak boleh kosong");
                    passowrdReset.setErrorEnabled(true);
                    passowrdReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                }else if (s.length() > 10){
                    passowrdReset.setErrorEnabled(true);
                    passowrdReset.setError("Password melebihi 10 karakter");
                    passowrdReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                }else {
                    passowrdReset.setErrorEnabled(false);
                    passowrdReset.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(confirmPasswordReset.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                   confirmPasswordReset.setError("Confirm Password tidak boleh kosong");
                   confirmPasswordReset.setErrorEnabled(true);
                   confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                }else if (s.length() > 10){
                   confirmPasswordReset.setError("Confrim Password melebihi 10 karakter");
                   confirmPasswordReset.setErrorEnabled(true);
                   confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
                }else{
                   confirmPasswordReset.setError(null);
                   confirmPasswordReset.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validateDataPassword(){
        Log.d(TAG, "validateDataPassword: ActiveValidatePassword");
        String passwordAnda = Objects.requireNonNull(passowrdReset.getEditText()).getText().toString().toLowerCase().trim();
        if  (!chekInputDataBenar()){
            return;
        }
        updatePassword(passwordAnda);
    }

    private void updatePassword(String passwordAnda){
        String confirmPaswword = Objects.requireNonNull(confirmPasswordReset.getEditText()).getText().toString().toLowerCase().trim();
        String password = Objects.requireNonNull(passowrdReset.getEditText()).getText().toString().toLowerCase().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        showProgerssBar(true);
        if (user != null) {
            Log.d(TAG, "updatePassword: ActiveUpdatePassword");
            if (confirmPaswword.equals(password)) {
                user.updatePassword(passwordAnda).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        showProgerssBar(false);
                        showDialogSucces();
                    }
                });
            }else{
                showProgerssBar(false);
                confirmPasswordReset.setError("Confirm Password tidak sama");
                confirmPasswordReset.setErrorEnabled(true);
                confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            }
        }
    }

    private Boolean chekInputDataBenar(){
        String resetPassword = Objects.requireNonNull(passowrdReset.getEditText()).getText().toString().toLowerCase().trim();
        String confirmPassword = Objects.requireNonNull(Objects.requireNonNull(confirmPasswordReset.getEditText()).getText().toString().toLowerCase().trim());
        String passwordMatches = ".{4,}";
        boolean validasi = true;
        if (resetPassword.isEmpty()){
            passowrdReset.setError("Passoword tidak boleh kosong");
            passowrdReset.setErrorEnabled(true);
            passowrdReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            passowrdReset.setErrorEnabled(true);
            validasi = false;
        }else if (confirmPassword.isEmpty()){
            confirmPasswordReset.setError("Confirm Password tidak boleh kosong");
            confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            confirmPasswordReset.setErrorEnabled(true);
            validasi = false;
        }else if (!confirmPassword.matches(passwordMatches)){
            confirmPasswordReset.setError("Password setidaknya 4 karakter");
            confirmPasswordReset.setErrorEnabled(true);
            confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validasi = false;
        }else if (resetPassword.length() > 10){
            passowrdReset.setError("Password melebihi 10 karakter");
            passowrdReset.setErrorEnabled(true);
            passowrdReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }
        else if (!resetPassword.matches(passwordMatches)){
            passowrdReset.setError("Password setidaknya 4 karakter");
            passowrdReset.setErrorEnabled(true);
            passowrdReset.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validasi = false;
        }else{
            confirmPasswordReset.setErrorEnabled(true);
            confirmPasswordReset.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            confirmPasswordReset.setError("Confirm Password sudah valid");
            passowrdReset.setError("Password sudah valid");
            passowrdReset.setErrorEnabled(true);
            passowrdReset.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
        }
        return validasi;
    }

    private void showDialogSucces() {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "Password berhasil di ubah", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Tempt.class));
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    private void showProgerssBar(boolean show){
        if (show){
           progressBar2.setVisibility(View.VISIBLE);
           buttonKonfirmasi.setVisibility(View.GONE);
        }else{
           progressBar2.setVisibility(View.GONE);
           buttonKonfirmasi.setVisibility(View.VISIBLE);
        }
    }

    private void chekKoneksiInternet(){
        showProgerssBar(true);
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()){
            showProgerssBar(false);
            validateDataPassword();
        }else{
            showProgerssBar(false);
            Snackbar snackbar = Snackbar.make(fragmentPassword,"Anda tidak memiliki koneksi internet \n periksa jaringan anda",Snackbar.LENGTH_SHORT)
                    .setAction("Coba lagi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showProgerssBar(true);
                            chekKoneksiInternet();
                        }
                    });
            snackbar.show();
        }
    }

}
