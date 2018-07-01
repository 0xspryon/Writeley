package com.springfield.y.writeley.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.springfield.y.writeley.R;
import com.springfield.y.writeley.presenter.impl.SplashPresenter;
import com.springfield.y.writeley.view.SplashActivityView;

import java.util.Arrays;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {

    private static final int RC_SIGN_IN = 123;
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        presenter = new SplashPresenter(SplashActivity.this);
        presenter.takeOverExecution();
    }

    /**
     * starts the flow of authentication
     */
    @Override
    public void executeAuthFlow() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build()
                                )
                        )
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                presenter.onSuccessfulSignin();
                finish();
            } else {
                if (response == null) {
                    // User pressed back button
                    presenter.onBackPressedInSignin();
                    return;
                }

                showAuthErrorMessage(getResources().getString(R.string.unknown_error));
                presenter.exitApp();
            }
        }


    }

    @Override
    public void goToJournalActivity() {
        Intent intent = new Intent(SplashActivity.this, JournalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void exitApp() {
        finish();
    }

    @Override
    public void showAuthErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
