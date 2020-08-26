package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Tempt;
import com.dev_candra.onlineshopproject.databinding.FragmentResetPhoneBinding;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_reset_phone extends Fragment {

    private FrameLayout frameLayout;
    private String code;
    private FirebaseAuth auth;
    private TextInputLayout layout;
    private MaterialButton btn_reset_phone;
    private CountryCodePicker codePicker;
    private ImageView backPhone;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    public fragment_reset_phone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_phone,container,false);
//        if (getActivity() != null){
//           frameLayout = getActivity().findViewById(R.id.activity_phone);
//        }
//        binding.codePicker.registerCarrierNumberEditText(binding.resetPhone.getEditText());
//        Aksi();
//        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_reset_phone = view.findViewById(R.id.button_phone_reset);
        if (getActivity() != null){
           frameLayout = getActivity().findViewById(R.id.activity_phone);
        }
        codePicker = view.findViewById(R.id.codePicker);
        layout = view.findViewById(R.id.reset_phone);
        codePicker.registerCarrierNumberEditText(layout.getEditText());
        backPhone = view.findViewById(R.id.back_phone);
        progressBar = view.findViewById(R.id.prosesBar1);
        linearLayout = view.findViewById(R.id.layout_fragment_reset_phone);
    }

    // Melakukan aksi pada interface
    private void Aksi(){
        btn_reset_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKoneksiInternet();
            }
        });

       backPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Tempt.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }

    private void resetPhone(){
        String hanphone = Objects.requireNonNull(layout.getEditText()).getText().toString().trim();
        if (hanphone.isEmpty()){
            layout.setError("Nomor Handphone tidak boleh kosong");
            layout.setErrorEnabled(true);
            layout.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }else if (hanphone.length() > 13 ){
            layout.setError("Nomor Handphone tidak boleh melebihi 13 karakter");
            layout.setErrorEnabled(true);
            layout.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }else if (hanphone.length() < 13){
            layout.setError("Nomor Handphone harus tiga belas karakter");
            layout.setErrorEnabled(true);
            layout.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }else if (hanphone.matches("0")){
            layout.setError("Anda memasukkan angka 0 ");
            layout.setErrorEnabled(true);
            layout.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }else if (hanphone.charAt(0) == '0'){
            layout.setErrorEnabled(true);
            layout.setError("Tidak boleh menggunakan angka 0");
            layout.setErrorIconDrawable(R.drawable.ic_error_black_24dp);
        }
        else {
            String nomorTelepon1 = codePicker.getFullNumberWithPlus().trim();
            Bundle bundle = new Bundle();
            bundle.putString("nomor_telephone", nomorTelepon1);
            fragment_otp mfragment_otp = new fragment_otp();
            mfragment_otp.setArguments(bundle);
            setNewFragment(mfragment_otp);
            layout.setError("Nomor Handphone sudah valid");
            layout.setErrorEnabled(true);
            layout.setErrorIconDrawable(R.drawable.ic_check_black_24dp);
            btn_reset_phone.setEnabled(true);
        }
    }

    // Membuat fragment
    private void setNewFragment(Fragment fragment){
        if (getActivity() != null){
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

    // Memuncul progress bar
    private void showProgressBar(boolean show){
        if (show){
            progressBar.setVisibility(View.VISIBLE);
            btn_reset_phone.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            btn_reset_phone.setVisibility(View.VISIBLE);
        }
    }

    private void checkKoneksiInternet(){
        showProgressBar(true);
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()){
            showProgressBar(false);
            resetPhone();
        }else{
            showProgressBar(false);
            Snackbar snackbar = Snackbar.make(linearLayout,"Anda tidak memiliki koneksi internet \n Periksa jaringan ada",Snackbar.LENGTH_SHORT)
                    .setAction("Coba lagi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showProgressBar(true);
                            resetPhone();
                        }
                    });
            snackbar.show();
        }
    }

}
