package pl.jug.bydgoszcz.androidjugworkshop;

import android.app.Application;

import pl.jug.bydgoszcz.androidjugworkshop.common.di.ApplicationComponent;
import pl.jug.bydgoszcz.androidjugworkshop.common.di.DaggerApplicationComponent;
import timber.log.Timber;

public class JugApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupApplicationComponent();
        setupTimberLogging();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void setupApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationComponent.ApplicationModule(this))
                .build();
    }

    private void setupTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
