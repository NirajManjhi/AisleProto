package com.aisle.test.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.aisle.test.Base.BaseFragment;
import com.aisle.test.Constant;
import com.aisle.test.CountDownTimer;
import com.aisle.test.R;
import com.aisle.test.interfaces.LoginCallbacks;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class LoginOtpFragment extends BaseFragment {

    @BindView(R.id.txvNumber)
    AppCompatTextView txvNumber;
    @BindView(R.id.txvTimer)
    AppCompatTextView txvTimer;
    @BindView(R.id.btnContinue)
    AppCompatButton btnContinue;
    @BindView(R.id.edtOtp)
    AppCompatEditText edtOtp;

    private CountDownTimer countDownTimer;

    public LoginOtpFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_login_otp;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String phone = getArguments().getString(Constant.EXTRA_PHONE);
        txvNumber.setText(phone);
        LoginCallbacks callbacks = (LoginActivity) getActivity();
        btnContinue.setOnClickListener(v -> {
            if (callbacks != null) {
                callbacks.onOtpSubmitted(getArguments().getString(Constant.EXTRA_PHONE), edtOtp.getText().toString().trim());
            }
        });

        countDownTimer = new CountDownTimer(TimeUnit.SECONDS, 120) {
            @Override
            public void onTick(int tickValue) {
                if (!isDetached() && !isRemoving()) {
                    txvTimer.setText(String.valueOf(tickValue));
                }
            }

            @Override
            public void onFinish() {
                if (!isDetached() && !isRemoving()) {
                    txvTimer.setVisibility(View.GONE);
                }
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        countDownTimer.cancel();
    }
}