package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Email_Activity;
import com.dev_candra.onlineshopproject.activity.Phone_Actiity;
import com.dev_candra.onlineshopproject.activity.Tempt;

import java.util.Objects;

import static com.dev_candra.onlineshopproject.activity.Email_Activity.kembaliKeResetPassword;
import static com.dev_candra.onlineshopproject.activity.Phone_Actiity.phone;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forgot_Password extends Fragment {
    private CardView emailReset,handphoneReset;
    private ImageView backLogin;


    public Forgot_Password() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot__password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailReset = view.findViewById(R.id.resetPassword_Email);
        handphoneReset = view.findViewById(R.id.resetPassword_handphone);
        backLogin = view.findViewById(R.id.kembali_ke_login);
        Aksi();
    }


    private void Aksi(){
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    startActivity(new Intent(getActivity(), Tempt.class));
                    getActivity().finish();
                }
            }
        });

        emailReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kembaliKeResetPassword = true;
                startActivity(new Intent(getActivity(),Email_Activity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        handphoneReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = true;
                if (getActivity() != null) {
                    startActivity(new Intent(getActivity(), Phone_Actiity.class));
                    getActivity().finish();
                }
            }
        });
    }


}
