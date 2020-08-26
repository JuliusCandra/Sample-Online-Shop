package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Tempt;
import com.dev_candra.onlineshopproject.databinding.FragmentRequestEmailBinding;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_request_email extends Fragment {

    private FrameLayout frameLayout;
    private FirebaseAuth firebaseAuth;
    private LinearLayout layout2;
    private FirebaseUser user;
    private ImageView backEmail;
    private MaterialButton buttonEmailReset;
    private TextInputLayout resetEmail;
    private ProgressBar prosess3;
    private MaterialTextView succesFullText,gagal;

    public fragment_request_email() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_email,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null){
            frameLayout = getActivity().findViewById(R.id.Reset_Melalui_Email);
        }
        firebaseAuth = FirebaseAuth.getInstance();
        layout2 = getActivity().findViewById(R.id.no_signal);
        user = firebaseAuth.getCurrentUser();
        backEmail = view.findViewById(R.id.back_email);
        buttonEmailReset = view.findViewById(R.id.button_email_reset);
        resetEmail = view.findViewById(R.id.reset_email);
        prosess3 = view.findViewById(R.id.prosess3);
        succesFullText = view.findViewById(R.id.succesFullText);
        gagal = view.findViewById(R.id.gagal);
        Aksi();
    }

    private void Aksi(){
        buttonEmailReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               chekKoneksiInternet();
            }
        });

        backEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Tempt.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }

    private void setNewFragment(Fragment fragment){
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }


    @SuppressLint("ResourceAsColor")
    private Boolean checkInputData(){
        boolean validation = true;
        String email1 = Objects.requireNonNull(resetEmail.getEditText()).getText().toString();
        if (TextUtils.isEmpty(email1)){
           resetEmail.setError("Email tidak boleh kosong");
           resetEmail.setErrorEnabled(true);
           resetEmail.setErrorTextColor(ColorStateList.valueOf(R.color.colorPrimaryDark));
           resetEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validation = false;
        }else {
           resetEmail.setError("Email terisi");
           resetEmail.setErrorEnabled(true);
           resetEmail.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
           resetEmail.setErrorTextColor(ColorStateList.valueOf(R.color.colorTeksReset));
        }

        String emailMatches = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        if (!email1.matches(emailMatches)){
           resetEmail.setError("Email tidak valid");
           resetEmail.setErrorEnabled(true);
           resetEmail.setErrorTextColor(ColorStateList.valueOf(R.color.colorPrimaryDark));
           resetEmail.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
            validation = false;
        }else{
           resetEmail.setError("Email sudah valid");
           resetEmail.setErrorEnabled(true);
           resetEmail.setErrorTextColor(ColorStateList.valueOf(R.color.colorTeksReset));
           resetEmail.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
        }
        return validation;
    }




    private void resetPasswordDenganEmail(){
        showProgeressBar(true);
        showTextSukses(false);
        showTeksGagal(false);
        if (resetEmail.getEditText() != null) {
            firebaseAuth.sendPasswordResetEmail(resetEmail.getEditText().getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: OnCompelete Sukses");
                                showProgeressBar(false);
                                showTextSukses(true);
                                showTeksGagal(false);
                            } else {
                                Log.d(TAG, "onComplete: OnComplete Gagal");
                                showProgeressBar(false);
                                showTextSukses(false);
                                showTeksGagal(true);
                            }
                        }
                    });
        }
    }

    private void showProgeressBar(boolean proses){
        if (proses){
            prosess3.setVisibility(View.VISIBLE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }else {
            prosess3.setVisibility(View.GONE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }
    }

    private void showTextSukses(boolean text){
        if (text){
            succesFullText.setVisibility(View.VISIBLE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }else{
            succesFullText.setVisibility(View.GONE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }
    }

    private void showTeksGagal(boolean text){
        if (text){
            gagal.setVisibility(View.VISIBLE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }else{
            gagal.setVisibility(View.GONE);
            buttonEmailReset.setVisibility(View.VISIBLE);
        }
    }



    // System belum di handle sepenuhnya
    private void showDialogErrorSignal(){
        showProgeressBar(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.no_signal,layout2);
        builder.setView(view);
        view.findViewById(R.id.close);
        view.findViewById(R.id.no_signal);
        view.findViewById(R.id.teksErrorSignal);
        view.findViewById(R.id.tombol_error_signal);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.tombol_error_signal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: tombol error signal");
                chekKoneksiInternet();
                showProgeressBar(false);
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgeressBar(false);
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }

    // chek koneksi internet
    private void chekKoneksiInternet(){
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()){
            validateData();
        }else{
            showDialogErrorSignal();
        }
    }

    // Membuat validasi data
    private void validateData(){
        if (!checkInputData()){
            return;
        }
        resetPasswordDenganEmail();
    }

}
