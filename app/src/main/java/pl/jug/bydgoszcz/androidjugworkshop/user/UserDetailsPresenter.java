package pl.jug.bydgoszcz.androidjugworkshop.user;

import pl.jug.bydgoszcz.androidjugworkshop.BasePresenter;
import pl.jug.bydgoszcz.androidjugworkshop.common.Constants;
import pl.jug.bydgoszcz.androidjugworkshop.data.DataRepository;
import pl.jug.bydgoszcz.androidjugworkshop.util.RxUtil;

public class UserDetailsPresenter extends BasePresenter<UserDetailsView> {

    private final DataRepository dataRepository;

    public UserDetailsPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    protected Class viewClass() {
        return UserDetailsView.class;
    }

    void loadUserDetails(long userId) {
        if (userId == Constants.NO_VALUE) {
            view().onUserShowError();
        } else {
            dataRepository.user(userId)
                    .compose(RxUtil.Transformers.schedulersIoAndroidSingle())
                    .doOnSubscribe(disposable -> view().showLoading(true))
                    .doOnSuccess(userModel -> view().showLoading(false))
                    .subscribe(user -> view().showUserDetails(user),
                            error -> view().onUserShowError());
        }
    }
}
