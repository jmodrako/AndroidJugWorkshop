package pl.jug.bydgoszcz.androidjugworkshop.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {
    public static class Transformers {
        public static <T> ObservableTransformer<T, T> schedulersIoAndroidObservable() {
            return upstream -> upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        public static <T> SingleTransformer<T, T> schedulersIoAndroidSingle() {
            return upstream -> upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
