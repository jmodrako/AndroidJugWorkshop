package pl.jug.bydgoszcz.androidjugworkshop.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import pl.jug.bydgoszcz.androidjugworkshop.BasePresenter;
import pl.jug.bydgoszcz.androidjugworkshop.util.RxUtil;

class LoginPresenter extends BasePresenter<LoginView> {

    @Override
    protected Class viewClass() {
        return LoginView.class;
    }

    void handleLoginClick(String userName, String password) {
        final Disposable loginDisposable = callServer(userName, password)
                .doOnSubscribe(disposable -> {
                    final LoginView loginView = view();
                    loginView.disableUi();
                    loginView.showLoading();
                })
                .subscribe(loginResponse -> {
                    final LoginView loginView = view();
                    loginView.enableUi();
                    loginView.hideLoading();
                    loginView.onLoginSucceed(loginResponse);
                }, throwable -> {
                    final LoginView loginView = view();
                    loginView.enableUi();
                    loginView.onLoginFailed();
                });

        disposables.add(loginDisposable);
    }

    private Single<LoginResponse> callServer(String userName, String password) {
        // simulate server call
        final boolean isSucceed = new Random(System.currentTimeMillis()).nextBoolean();
        return Single.just(new LoginResponse(isSucceed))
                .delay(3, TimeUnit.SECONDS)
                .compose(RxUtil.Transformers.schedulersIoAndroidSingle());
    }
}
