package com.springfield.y.writeley.presenter.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.springfield.y.writeley.presenter.SplashPresenterInterface;
import com.springfield.y.writeley.view.SplashActivityView;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public class SplashPresenter implements SplashPresenterInterface {
    SplashActivityView view;

    public SplashPresenter(SplashActivityView view) {
        this.view = view;
    }

    /**
     * Verifies the authentication state of the application at that instant in time
     */
    @Override
    public boolean verifyAuthState() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    @Override
    public void onSuccessfulSignin() {
        view.goToJournalActivity();
    }

    @Override
    public void exitApp() {
        view.exitApp();
    }

    @Override
    public void showAuthErrorMessage(String errorMessage) {
        view.showAuthErrorMessage(errorMessage);
    }

    @Override
    public void onBackPressedInSignin() {
        exitApp();
    }

    @Override
    public void takeOverExecution() {
        if (verifyAuthState()) {
            // user signed in
            view.goToJournalActivity();
        } else {
            // user not signed in
            view.executeAuthFlow();
        }
    }
}
