package pl.jug.bydgoszcz.androidjugworkshop.user;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import pl.jug.bydgoszcz.androidjugworkshop.R;
import pl.jug.bydgoszcz.androidjugworkshop.common.BaseActivity;
import pl.jug.bydgoszcz.androidjugworkshop.common.Constants;
import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityUserDetailsBinding;
import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;
import pl.jug.bydgoszcz.androidjugworkshop.user.di.DaggerUserDetailsComponent;
import pl.jug.bydgoszcz.androidjugworkshop.user.di.UserDetailsComponent;

public class UserDetailsActivity extends BaseActivity implements UserDetailsView {

    private static final String EXTRA_USER_ID = "extra_user_id";

    @Inject
    protected UserDetailsPresenter userDetailsPresenter;

    private ActivityUserDetailsBinding binding;

    public static Intent forUserId(Activity callingActivity, long userId) {
        final Intent result = new Intent(callingActivity, UserDetailsActivity.class);
        result.putExtra(EXTRA_USER_ID, userId);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
    }

    private void injectDependencies() {
        DaggerUserDetailsComponent.builder()
                .userDetailsModule(new UserDetailsComponent.UserDetailsModule())
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userDetailsPresenter.attachView(this);

        loadUserDataIfNeeded();
    }

    @Override
    protected void onStop() {
        userDetailsPresenter.detachView();
        super.onStop();
    }

    @Override
    public void showUserDetails(UserModel user) {
        binding.setModel(user);
    }

    @Override
    public void onUserShowError() {
        Toast.makeText(this, "Can't load user details.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            showProgressDialog("Loading user data...");
        } else {
            hideProgressDialog();
        }
    }

    private void loadUserDataIfNeeded() {
        final Intent startingIntent = getIntent();
        if (startingIntent != null && startingIntent.hasExtra(EXTRA_USER_ID)) {
            binding.getRoot().post(() -> userDetailsPresenter.loadUserDetails(
                    startingIntent.getLongExtra(EXTRA_USER_ID, Constants.NO_VALUE)));
        } else {
            Toast.makeText(this, "Can't load user details. Please start activity with user id.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
