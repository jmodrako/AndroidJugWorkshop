package pl.jug.bydgoszcz.androidjugworkshop.common;

import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import pl.jug.bydgoszcz.androidjugworkshop.JugApplication;
import pl.jug.bydgoszcz.androidjugworkshop.common.di.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    protected void showProgressDialog(@StringRes int stringResId) {
        showProgressDialog(getString(stringResId));
    }

    protected void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        }

        progressDialog.setMessage(message);

        if (!progressDialog.isShowing()) progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        final JugApplication application = (JugApplication) getApplication();
        return application.getApplicationComponent();
    }
}
