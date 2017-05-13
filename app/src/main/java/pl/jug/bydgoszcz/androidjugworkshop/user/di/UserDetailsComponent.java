package pl.jug.bydgoszcz.androidjugworkshop.user.di;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import pl.jug.bydgoszcz.androidjugworkshop.common.di.ApplicationComponent;
import pl.jug.bydgoszcz.androidjugworkshop.common.di.PerActivity;
import pl.jug.bydgoszcz.androidjugworkshop.data.DataRepository;
import pl.jug.bydgoszcz.androidjugworkshop.user.UserDetailsPresenter;

@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {UserDetailsComponent.UserDetailsModule.class})
public interface UserDetailsComponent {

    // void inject(UserDetailsActivity userDetailsActivity);

    @Module
    class UserDetailsModule {

        @Provides
        public UserDetailsPresenter provideUserDetailsPresenter(DataRepository dataRepository) {
            return new UserDetailsPresenter(dataRepository);
        }
    }
}
