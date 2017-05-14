package pl.jug.bydgoszcz.androidjugworkshop.login;

interface LoginView {
    void showLoading();

    void hideLoading();

    void onLoginSucceed(LoginResponse loginResponse);

    void onLoginFailed();

    void disableUi();

    void enableUi();

    void onCredentialsNeeded();
}
