package com.amazon.mp3.prime.upsell.nightwing.viewmodel;

import androidx.lifecycle.ViewModel;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.AccountManagerKt;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: AdsPreferenceSettingsViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u000bH\u0014J\"\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/prime/upsell/nightwing/viewmodel/AdsPreferenceSettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "subscriptions", "Lrx/subscriptions/CompositeSubscription;", "ifTargetedAdsEnabled", "", "onCleared", "", "toggleAdsPrefSettings", "flipToggleButton", "Lkotlin/Function0;", "showErrorMsg", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AdsPreferenceSettingsViewModel extends ViewModel {
    private final String TAG = AdsPreferenceSettingsViewModel.class.getSimpleName();
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        this.subscriptions.unsubscribe();
    }

    public final boolean ifTargetedAdsEnabled() {
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = AccountManagerKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        return user != null && user.isTargetedAdsEnabled();
    }

    public final void toggleAdsPrefSettings(final Function0<Unit> flipToggleButton, final Function0<Unit> showErrorMsg) {
        Intrinsics.checkNotNullParameter(flipToggleButton, "flipToggleButton");
        Intrinsics.checkNotNullParameter(showErrorMsg, "showErrorMsg");
        this.subscriptions.add(Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.prime.upsell.nightwing.viewmodel.AdsPreferenceSettingsViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AdsPreferenceSettingsViewModel.m1596toggleAdsPrefSettings$lambda0(this.f$0, (Subscriber) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.prime.upsell.nightwing.viewmodel.AdsPreferenceSettingsViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AdsPreferenceSettingsViewModel.m1597toggleAdsPrefSettings$lambda2(flipToggleButton, showErrorMsg, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.prime.upsell.nightwing.viewmodel.AdsPreferenceSettingsViewModel$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AdsPreferenceSettingsViewModel.m1598toggleAdsPrefSettings$lambda3(this.f$0, (Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toggleAdsPrefSettings$lambda-0, reason: not valid java name */
    public static final void m1596toggleAdsPrefSettings$lambda0(AdsPreferenceSettingsViewModel this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(Boolean.valueOf(AccountManagerSingleton.get().updateTargetedAdsPreference(!this$0.ifTargetedAdsEnabled())));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toggleAdsPrefSettings$lambda-2, reason: not valid java name */
    public static final void m1597toggleAdsPrefSettings$lambda2(Function0 flipToggleButton, Function0 showErrorMsg, Boolean bool) {
        Intrinsics.checkNotNullParameter(flipToggleButton, "$flipToggleButton");
        Intrinsics.checkNotNullParameter(showErrorMsg, "$showErrorMsg");
        if (bool == null) {
            return;
        }
        bool.booleanValue();
        if (bool.booleanValue()) {
            flipToggleButton.invoke();
        } else {
            showErrorMsg.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toggleAdsPrefSettings$lambda-3, reason: not valid java name */
    public static final void m1598toggleAdsPrefSettings$lambda3(AdsPreferenceSettingsViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, th.toString());
    }
}
