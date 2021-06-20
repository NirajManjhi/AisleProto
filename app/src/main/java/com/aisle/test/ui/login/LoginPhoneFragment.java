package com.aisle.test.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.view.View;

import com.aisle.test.Base.BaseFragment;
import com.aisle.test.R;
import com.aisle.test.interfaces.LoginCallbacks;
import com.aisle.test.ui.login.LoginActivity;

import butterknife.BindView;

public class LoginPhoneFragment extends BaseFragment {

    @BindView(R.id.edtPhone)
    AppCompatEditText edtPhone;
    @BindView(R.id.btnContinue)
    AppCompatButton btnContinue;

    public LoginPhoneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_login_phone;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginCallbacks callbacks = (LoginActivity) getActivity();
        btnContinue.setOnClickListener(v -> {
            if (callbacks != null) {
                callbacks.onPhoneSubmitted(edtPhone.getText().toString().trim());
            }
        });
    }
}