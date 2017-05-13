package pl.jug.bydgoszcz.androidjugworkshop.user;

import pl.jug.bydgoszcz.androidjugworkshop.feed.model.UserModel;

interface UserDetailsView {
    void showUserDetails(UserModel user);

    void onUserShowError();

    void showLoading(boolean b);
}
