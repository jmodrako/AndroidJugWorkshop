package pl.jug.bydgoszcz.androidjugworkshop.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import pl.jug.bydgoszcz.androidjugworkshop.R;
import pl.jug.bydgoszcz.androidjugworkshop.common.BaseActivity;
import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityLoginBinding;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginView {

    private CompositeDisposable viewsDisposables = new CompositeDisposable();
    private ActivityLoginBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setupViews();
        setupPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        viewsDisposables.dispose();
        presenter.detachView();
        super.onStop();
    }

    @Override
    public void showLoading() {
        showProgressDialog("Logging in...");
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void onLoginSucceed(LoginResponse loginResponse) {
        // startActivity(new Intent(this, FeedActivity.class));
        Toast.makeText(this, "Login succeed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disableUi() {
        enableUi(false);
    }

    @Override
    public void enableUi() {
        enableUi(true);
    }

    private void enableUi(boolean enabled) {
        binding.mainButtonLogin.setEnabled(enabled);
        binding.mainUsernameInput.setEnabled(enabled);
        binding.mainPasswordInput.setEnabled(enabled);
    }

    private void setupPresenter() {
        presenter = new LoginPresenter();
    }

    private void setupViews() {
        // username input
        viewsDisposables.add(
                RxTextView.afterTextChangeEvents(binding.mainUsernameInput)
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .subscribe(afterTextChangeEvent ->
                                Timber.d("On username changes: %s", afterTextChangeEvent.view().getText()))
        );

        // login button
        viewsDisposables.add(
                RxView.clicks(binding.mainButtonLogin)
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> presenter.handleLoginClick("login", "pass"))
        );
    }
}
