package com.spotify.connectivity.platformconnectiontype;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.q;
import p.vx;

/* JADX INFO: loaded from: classes.dex */
public class MobileDataDisabledMonitor {
    private static final String SETTING_MOBILE_DATA = "mobile_data";
    private final Context mContext;
    private final Observable<Boolean> mObservable = Observable.defer(new q() { // from class: p.tk1
        @Override // io.reactivex.rxjava3.functions.q
        public final Object get() {
            return this.c.observeMobileData();
        }
    }).distinctUntilChanged().onErrorReturnItem(Boolean.FALSE);

    public MobileDataDisabledMonitor(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private static boolean getMobileDataSettingDisabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), SETTING_MOBILE_DATA, 1) != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeMobileData$1(Handler handler, ContentResolver contentResolver, Uri uri, final ObservableEmitter observableEmitter) {
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.spotify.connectivity.platformconnectiontype.MobileDataDisabledMonitor.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                observableEmitter.onNext(Boolean.valueOf(MobileDataDisabledMonitor.this.getMobileDataDisabled()));
            }
        };
        contentResolver.registerContentObserver(uri, false, contentObserver);
        observableEmitter.setCancellable(new vx(contentResolver, 2, contentObserver));
        observableEmitter.onNext(Boolean.valueOf(getMobileDataDisabled()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Boolean> observeMobileData() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final ContentResolver contentResolver = this.mContext.getContentResolver();
        final Uri uriFor = Settings.Global.getUriFor(SETTING_MOBILE_DATA);
        return Observable.create(new ObservableOnSubscribe() { // from class: p.uk1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                this.c.lambda$observeMobileData$1(handler, contentResolver, uriFor, observableEmitter);
            }
        });
    }

    public boolean getMobileDataDisabled() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        int phoneCount = telephonyManager.getPhoneCount();
        for (int i = 0; i < phoneCount; i++) {
            if (telephonyManager.getSimState(i) == 5) {
                return getMobileDataSettingDisabled(this.mContext);
            }
        }
        return false;
    }

    public Observable<Boolean> isMobileDataDisabled() {
        return this.mObservable;
    }
}
