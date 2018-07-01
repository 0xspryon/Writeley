package com.springfield.y.writeley.presenter;


/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public interface SplashPresenterInterface extends BasePresenterInterface {
    /**
     * Verifies the authentication state of the application at that instant in time
     */
    boolean verifyAuthState();

    void onSuccessfulSignin();

    void onBackPressedInSignin();

    void exitApp();

    void showAuthErrorMessage(String errorMessage);
}
