package com.dev_candra.onlineshopproject.Fragment_Awal;

import android.os.Build;
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

import com.chaos.view.PinView;
import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.databinding.FragmentOtpBinding;
import com.dev_candra.onlineshopproject.method.koneksi_internet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.nio.BufferUnderflowException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_otp extends Fragment {

    private FirebaseAuth mAuth;
    private String codeSystem;
    private FrameLayout frameLayout;
    private PhoneAuthProvider.ForceResendingToken mToken;
    private MaterialTextView mTeksTelepone;
    private ImageView backOtp;
    private Button verificationKode;
    private PinView pinView;
    private ProgressBar proses4;
    private LinearLayout layoutFragmentOtp;

    public fragment_otp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTeksTelepone = view.findViewById(R.id.text_telephone);
        backOtp = view.findViewById(R.id.back_otp);
        verificationKode = view.findViewById(R.id.verification_kode);
        pinView = view.findViewById(R.id.pin_view);
        proses4 = view.findViewById(R.id.proses4);
        layoutFragmentOtp = view.findViewById(R.id.layout_fragment_otp);

        Bundle bundle = getArguments();
        if (bundle != null){
            String phone = bundle.getString("nomor_telephone");
            chekKoneksiInternet();
            mTeksTelepone.setText(phone);

        }
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("id");
        mAuth.useAppLanguage();
        Aksi();
        frameLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.activity_phone);
    }

    private void Aksi() {
        backOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(new Forgot_Password());
            }
        });
        verificationKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                if (bundle != null) {
                    String phoneNumber = bundle.getString("nomor_telephone");
//                    codeVerfication(phoneNumber);
                    mTeksTelepone.setText(phoneNumber);
                    UpdateUi(new fragment_password());
                }
            }
        });
    }

    private void codeVerfication(String phoneNumber) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,
                    60,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    mCallback);
        }
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    Log.d(TAG, "onCodeSent: ActiveOnCodeSent");
                    showProgressBar(true);
                    codeSystem = s;
                    mToken = forceResendingToken;
                    onCodeAutoRetrievalTimeOut(s);
                }

                @Override
                public void onVerificationCompleted(@NonNull final PhoneAuthCredential phoneAuthCredential) {
                    final String code = phoneAuthCredential.getSmsCode();
                    Log.d(TAG, "onVerificationCompleted: ActiveOnVerficationCompleted");
                    // method verifkasi sebenarnya
                    if (code != null) {
                        pinView.setText(code);
                        showProgressBar(false);
                        verificationKode.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showProgressBar(false);
                                verify(code);
                            }
                        });
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Log.d(TAG, "onVerificationFailed: ActiveOnVerifcationFaield");
                    showProgressBar(false);
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getActivity(), "Invalid Request", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onVerificationFailed: Invalid Request");
                        // Invalid request
                        // ...
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                        Log.d(TAG, "onVerificationFailed: SMS Excedeed");
                        Toast.makeText(getActivity(), "SMS quota for the project has been exceede", Toast.LENGTH_SHORT).show();
                    }
                }
            };

    private void verify(String code) {
        mAuth = FirebaseAuth.getInstance();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSystem, code);
        signWithPhoneAuthCredential(credential);

    }

    private void signWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "signWithPhoneAuthCredential: ActiveOnSignWithPhoneAuthCredential");
        showProgressBar(true);
            mAuth.signInWithCredential(credential).addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showProgressBar(false);
                        Log.d(TAG, "onComplete: ActiveOnCompleted");
                        UpdateUi(new fragment_password());
                    } else {
                        Toast.makeText(getActivity(), "Anda gagal mereset Password Anda", Toast.LENGTH_SHORT).show();
                        showProgressBar(false);
                    }
                }
            });
    }

    private void UpdateUi(Fragment fragment){
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
            fragmentTransaction.commit();
    }

    private void showProgressBar(boolean saya){
        if (saya){
            proses4.setVisibility(View.VISIBLE);
            verificationKode.setVisibility(View.GONE);
        }else{
            proses4.setVisibility(View.GONE);
            verificationKode.setVisibility(View.VISIBLE);
        }
    }

    private void setNewFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void chekKoneksiInternet(){
        if (koneksi_internet.getInstance(Objects.requireNonNull(getActivity())).isOnline()){
            Bundle bundle = getArguments();
            if (bundle != null) {
                String phone = bundle.getString("nomor_telephone");
                codeVerfication(phone);
            };
        }else{
            Snackbar snackbar = Snackbar.make(layoutFragmentOtp,"Anda tidak memiliki koneksi internet \n periksa jaringan anda",Snackbar.LENGTH_SHORT)
                    .setAction("Coba lagi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = getArguments();
                            if (bundle != null) {
                                String phone = bundle.getString("nomor_telephone");
                                codeVerfication(phone);
                            }
                        }
                    });
            snackbar.show();
        }
    }
}