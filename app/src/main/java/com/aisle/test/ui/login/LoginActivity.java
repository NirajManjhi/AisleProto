package com.aisle.test.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.aisle.test.Base.BaseActivity;
import com.aisle.test.Base.ViewModelFactory;
import com.aisle.test.Constant;
import com.aisle.test.R;
import com.aisle.test.interfaces.LoginCallbacks;
import com.aisle.test.ui.home.MainActivity;
import com.aisle.test.viewmodels.LoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginCallbacks {

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private NavController navController;
    @Inject
    ViewModelFactory viewModelFactory;

    private LoginViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
        if (viewModel.getToken() != null && !viewModel.getToken().isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.containerLogin);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        viewModel.getLoading().observe(this, this::showHideProgress);
        viewModel.getErrorMessageResourceId().observe(this, this::setErrorMessage);
    }

    @Override
    public void onPhoneSubmitted(String phone) {
        viewModel.submitPhone(phone).observe(this, loginPhoneResponse -> {
            if (loginPhoneResponse != null && loginPhoneResponse.getStatus()) {
                Bundle args = new Bundle();
                args.putString(Constant.EXTRA_PHONE, phone);
                navController.navigate(R.id.action_loginPhoneFragment_to_loginOtpFragment, args);
            }
        });
    }

    @Override
    public void onOtpSubmitted(String phone, String otp) {
        viewModel.verifyOtp(phone, otp).observe(this, loginOtpResponse -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void showHideProgress(Boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            progressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    private void setErrorMessage(Integer resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
}