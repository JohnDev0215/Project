package com.amazon.music.signin;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class MAPAccountDeviceTypeLookup extends MAPAccountDataLookup {
    public MAPAccountDeviceTypeLookup(@NonNull Boolean bool) {
        super(bool);
    }

    public Observable<String> getAccountDeviceTypeObservable(@NonNull final Context context, @NonNull final String str) {
        return Observable.create(new Observable.OnSubscribe<String>() { // from class: com.amazon.music.signin.MAPAccountDeviceTypeLookup.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(MAPAccountDeviceTypeLookup.this.getAccountDeviceType(context, str));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAccountDeviceType(@NonNull Context context, @NonNull String str) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        return CustomerAttributeStore.getValueOrAttributeDefault(getValueFromMAP(CustomerAttributeStore.getInstance(context).getAttribute(str, CustomerAttributeKeys.getDeviceTypeKeyForPackage(context.getPackageName()), null)));
    }
}
