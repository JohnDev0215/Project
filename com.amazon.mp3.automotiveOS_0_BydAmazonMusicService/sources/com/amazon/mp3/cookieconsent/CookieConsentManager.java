package com.amazon.mp3.cookieconsent;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.net.stratus.preferences.PrivacyCompliancePreferencesAPIProvider;
import com.amazon.mp3.net.stratus.preferences.UpdatePrivacyCompliancePreferencesAPIProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CookieConsentManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 ,2\u00020\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b*\u0010+J\u0012\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007J\u0012\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007J\u0006\u0010\u0007\u001a\u00020\u0002J\u0006\u0010\t\u001a\u00020\bJ\u001c\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fJ.\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0007J$\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fJ6\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0007R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0018\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u001c\u0010%\u001a\n $*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010\u0018R\u0016\u0010&\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lcom/amazon/mp3/cookieconsent/CookieConsentManager;", "", "", "isFTUFlow", "isCookieConsentEnabled", "shouldStartCookieConsentFlow", "isCookieConsentFlowSupported", "shouldRequestUserForConsent", "", "markCookieConsentScreenAsShownToUser", "Lio/reactivex/rxjava3/functions/Action;", "successAction", "Lio/reactivex/rxjava3/functions/Consumer;", "", "errorAction", "Lio/reactivex/rxjava3/disposables/Disposable;", "cookieConsentSubscription", "Lio/reactivex/rxjava3/core/Scheduler;", "obsScheduler", "subScheduler", "", "consent", "cookieConsentUpdate", "cookieConsentString", "Ljava/lang/String;", "cookieConsentLegaleseUrl", "getCookieConsentLegaleseUrl", "()Ljava/lang/String;", "setCookieConsentLegaleseUrl", "(Ljava/lang/String;)V", "Lcom/amazon/mp3/net/stratus/preferences/PrivacyCompliancePreferencesAPIProvider;", "privacyCompliancePreferencesAPIProvider", "Lcom/amazon/mp3/net/stratus/preferences/PrivacyCompliancePreferencesAPIProvider;", "Lcom/amazon/mp3/net/stratus/preferences/UpdatePrivacyCompliancePreferencesAPIProvider;", "updatePrivacyCompliancePreferencesAPIProvider", "Lcom/amazon/mp3/net/stratus/preferences/UpdatePrivacyCompliancePreferencesAPIProvider;", "kotlin.jvm.PlatformType", "TAG", "isCookieConsentShownToUser", "Z", "isCookieConsentStatusAvailable", "()Z", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CookieConsentManager {

    @JvmField
    public static final CookieConsentManager instance = new CookieConsentManager();
    private String cookieConsentLegaleseUrl;
    private String cookieConsentString;
    private boolean isCookieConsentShownToUser;
    private final PrivacyCompliancePreferencesAPIProvider privacyCompliancePreferencesAPIProvider = new PrivacyCompliancePreferencesAPIProvider();
    private final UpdatePrivacyCompliancePreferencesAPIProvider updatePrivacyCompliancePreferencesAPIProvider = new UpdatePrivacyCompliancePreferencesAPIProvider();
    private final String TAG = CookieConsentManager.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cookieConsentSubscription$lambda-1, reason: not valid java name */
    public static final void m738cookieConsentSubscription$lambda1(Unit unit) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cookieConsentUpdate$lambda-3, reason: not valid java name */
    public static final void m740cookieConsentUpdate$lambda3(Unit unit) {
    }

    private CookieConsentManager() {
    }

    public final String getCookieConsentLegaleseUrl() {
        return this.cookieConsentLegaleseUrl;
    }

    public final boolean isCookieConsentStatusAvailable() {
        return this.cookieConsentString != null;
    }

    @JvmOverloads
    public final boolean shouldStartCookieConsentFlow(boolean isFTUFlow) {
        if (!isCookieConsentFlowSupported(isFTUFlow)) {
            return false;
        }
        try {
            if (!this.isCookieConsentShownToUser) {
                if (!isCookieConsentStatusAvailable()) {
                    return true;
                }
                if (shouldRequestUserForConsent()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            Log.error(this.TAG, "Failure in shouldRequestUserForConsent method");
            return true;
        }
    }

    @JvmOverloads
    public final boolean isCookieConsentFlowSupported(boolean isFTUFlow) {
        return !PlatformUtil.isFireOS() && isCookieConsentEnabled(isFTUFlow);
    }

    private final boolean isCookieConsentEnabled(boolean isFTUFlow) {
        if (isFTUFlow) {
            return Feature.cookie_consent_ftu_eu_workflow.isEnabled();
        }
        return Feature.cookie_consent_eu_workflow.isEnabled();
    }

    public final boolean shouldRequestUserForConsent() throws Exception {
        if (!isCookieConsentStatusAvailable()) {
            throw new Exception("This method should not be called without having cookieConsentString");
        }
        return Intrinsics.areEqual("UNKNOWN", this.cookieConsentString);
    }

    public final void markCookieConsentScreenAsShownToUser() {
        this.isCookieConsentShownToUser = true;
    }

    public final Disposable cookieConsentSubscription(Action successAction, Consumer<Throwable> errorAction) {
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Scheduler schedulerMainThread = AndroidSchedulers.mainThread();
        Intrinsics.checkNotNullExpressionValue(schedulerMainThread, "mainThread()");
        Scheduler schedulerIo = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(schedulerIo, "io()");
        return cookieConsentSubscription(successAction, errorAction, schedulerMainThread, schedulerIo);
    }

    public final Disposable cookieConsentSubscription(Action successAction, Consumer<Throwable> errorAction, Scheduler obsScheduler, Scheduler subScheduler) {
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Intrinsics.checkNotNullParameter(obsScheduler, "obsScheduler");
        Intrinsics.checkNotNullParameter(subScheduler, "subScheduler");
        Disposable disposableSubscribe = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cookieconsent.CookieConsentManager$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                CookieConsentManager.m737cookieConsentSubscription$lambda0(this.f$0, observableEmitter);
            }
        }).subscribeOn(subScheduler).observeOn(obsScheduler).subscribe(new Consumer() { // from class: com.amazon.mp3.cookieconsent.CookieConsentManager$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CookieConsentManager.m738cookieConsentSubscription$lambda1((Unit) obj);
            }
        }, errorAction, successAction);
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "observable.subscribe({ }…rorAction, successAction)");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cookieConsentSubscription$lambda-0, reason: not valid java name */
    public static final void m737cookieConsentSubscription$lambda0(CookieConsentManager this$0, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Pair<String, String> cookieConsentStatusAndLegaleseUrl = this$0.privacyCompliancePreferencesAPIProvider.getCookieConsentStatusAndLegaleseUrl();
            String strComponent1 = cookieConsentStatusAndLegaleseUrl.component1();
            String strComponent2 = cookieConsentStatusAndLegaleseUrl.component2();
            this$0.cookieConsentString = strComponent1;
            this$0.cookieConsentLegaleseUrl = strComponent2;
            observableEmitter.onComplete();
        } catch (Exception e) {
            observableEmitter.onError(new Exception("Cookie consent fetch failed", e));
        }
    }

    public final Disposable cookieConsentUpdate(String consent, Action successAction, Consumer<Throwable> errorAction) {
        Intrinsics.checkNotNullParameter(consent, "consent");
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Scheduler schedulerMainThread = AndroidSchedulers.mainThread();
        Intrinsics.checkNotNullExpressionValue(schedulerMainThread, "mainThread()");
        Scheduler schedulerIo = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(schedulerIo, "io()");
        return cookieConsentUpdate(consent, successAction, errorAction, schedulerMainThread, schedulerIo);
    }

    public final Disposable cookieConsentUpdate(final String consent, Action successAction, Consumer<Throwable> errorAction, Scheduler obsScheduler, Scheduler subScheduler) {
        Intrinsics.checkNotNullParameter(consent, "consent");
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Intrinsics.checkNotNullParameter(obsScheduler, "obsScheduler");
        Intrinsics.checkNotNullParameter(subScheduler, "subScheduler");
        Disposable disposableSubscribe = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cookieconsent.CookieConsentManager$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                CookieConsentManager.m739cookieConsentUpdate$lambda2(this.f$0, consent, observableEmitter);
            }
        }).subscribeOn(subScheduler).observeOn(obsScheduler).subscribe(new Consumer() { // from class: com.amazon.mp3.cookieconsent.CookieConsentManager$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CookieConsentManager.m740cookieConsentUpdate$lambda3((Unit) obj);
            }
        }, errorAction, successAction);
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "observable.subscribe({ }…rorAction, successAction)");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cookieConsentUpdate$lambda-2, reason: not valid java name */
    public static final void m739cookieConsentUpdate$lambda2(CookieConsentManager this$0, String consent, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(consent, "$consent");
        try {
            Log.info(this$0.TAG, Intrinsics.stringPlus("Cookie Consent Preferences updated for the user with response: ", this$0.updatePrivacyCompliancePreferencesAPIProvider.updateCookieConsentPreferences(consent)));
            observableEmitter.onComplete();
        } catch (Exception e) {
            observableEmitter.onError(new Exception("Cookie consent update failed", e));
        }
    }
}
