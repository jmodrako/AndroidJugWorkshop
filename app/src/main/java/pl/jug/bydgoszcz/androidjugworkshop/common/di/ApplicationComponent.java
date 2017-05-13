package pl.jug.bydgoszcz.androidjugworkshop.common.di;

import android.content.Context;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import pl.jug.bydgoszcz.androidjugworkshop.data.DataRepository;

@PerApplication
@Component(modules = {ApplicationComponent.ApplicationModule.class})
public interface ApplicationComponent {

    Context exposeAppContext();

    DataRepository exposeDataRepository();

    @Module
    class ApplicationModule {

        private final Context appContext;

        public ApplicationModule(Context appContext) {
            this.appContext = appContext;
        }

        @Provides
        @PerApplication
        public Context providesAppContext() {
            return appContext;
        }

        @Provides
        @PerApplication
        public DataRepository providesDataRepository() {
            return new DataRepository();
        }
    }
}
