package pl.jug.bydgoszcz.androidjugworkshop;

import android.app.Application;

import timber.log.Timber;

public class JugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimberLogging();
    }

    private void setupTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
