package com.springfield.y.writeley.view;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public interface SplashActivityView extends BaseView {

    /**
     * starts the flow of authentication
     */
    void executeAuthFlow();

    void goToJournalActivity();

    void exitApp();

    void showAuthErrorMessage(String errorMessage);
}
