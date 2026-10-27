package com.amazon.music.inappmessaging.internal;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.config.Configuration;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.DeviceInformationVendor;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.IAMCredentialsVendor;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandler;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.InAppMessageClientApi;
import com.amazon.music.inappmessaging.external.events.EventType;
import com.amazon.music.inappmessaging.external.events.IAMEventSender;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.inappmessaging.internal.service.InAppMessageJson;
import com.amazon.music.inappmessaging.internal.service.InAppMessageListJson;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApi;
import com.amazon.music.inappmessaging.internal.util.Timer;
import com.amazon.music.inappmessaging.internal.view.FullPageWebViewFragment;
import com.amazon.music.inappmessaging.internal.view.IAMLoadingSpinnerFragment;
import com.amazon.music.inappmessaging.internal.view.IamWebViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class InAppMessageClientApiImpl extends InAppMessageClientApi {
    private static final String IAM_LOADING_SPINNER_TAG = "iamLoadingSpinnerTag";
    private static final String IAM_SPLASH_DIALOG_TAG = "iamSplashDialogTag";
    private static final String JSON_RESPONSE_TYPE = "json";
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) InAppMessageClientApiImpl.class);
    private final List<String> cachedBatchJson;
    private final Map<IAMRequest, Observable<Splash>> cachedSplashObservables;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$loadInAppMessage$0(Observable observable) {
        return observable;
    }

    public InAppMessageClientApiImpl(Context context, InAppMessagesApi inAppMessagesApi, IAMCredentialsVendor iamCredentialsVendor, DeviceInformationVendor deviceInformationVendor, IAMWebViewDeeplinkHandler deeplinkHandler, Configuration configuration) {
        super(inAppMessagesApi, iamCredentialsVendor, deviceInformationVendor, deeplinkHandler, configuration);
        this.cachedSplashObservables = new HashMap();
        this.cachedBatchJson = new ArrayList();
        IamWebViewManager.get(context);
    }

    @Override // com.amazon.music.inappmessaging.external.InAppMessageClientApi
    public Observable<Boolean> displayInAppMessage(final FragmentManager fragmentManager, final Context context, final IAMRequest request, final Boolean isNightwingOnly, final Boolean isFTU) {
        final Timer timer = new Timer("latency");
        timer.start();
        Observable<Splash> observableLoadInAppMessage = this.cachedSplashObservables.get(request);
        if (observableLoadInAppMessage == null) {
            if (!request.shouldThrottle()) {
                displayLoadingSpinner(fragmentManager);
            }
            observableLoadInAppMessage = loadInAppMessage(request);
        }
        final boolean z = !request.shouldThrottle();
        observableLoadInAppMessage.subscribe(new Action1<Splash>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.1
            @Override // rx.functions.Action1
            public void call(final Splash splash) {
                IAMEventSender.withType(EventType.LATENCY_API).withLatency(Float.valueOf(timer.logInterval("experienceRetrieved").intervalTime)).withIamRef(splash == null ? null : splash.iamRef).send();
                InAppMessageClientApiImpl.this.dismissVisibleLoadingSpinner(fragmentManager);
                if (splash != null) {
                    IAMEventSender.withType(EventType.CAMPAIGN_RECEIVED).withIamRef(splash.iamRef).withDescription(request.toString()).send();
                    if (!InAppMessageClientApiImpl.JSON_RESPONSE_TYPE.equals(splash.responseType)) {
                        InAppMessageClientApiImpl.this.showFullPageSplashScreenDialog(splash, context.getApplicationContext(), fragmentManager, request, timer, isFTU);
                        return;
                    } else {
                        context.sendBroadcast(new Intent(InAppMessageBroadcasts.NON_FULLSCREEN_SPLASH));
                        return;
                    }
                }
                if (z) {
                    Toast.makeText(context, R.string.content_expired, 1).show();
                    IAMEventSender.withType(EventType.CAMPAIGN_EXPIRED).withDescription(request.toString()).send();
                } else {
                    IAMEventSender.withType(EventType.CAMPAIGN_INELIGIBLE).send();
                }
                InAppMessageClientApiImpl.this.cachedSplashObservables.remove(request);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.2
            @Override // rx.functions.Action1
            public void call(Throwable err) {
                InAppMessageClientApiImpl.this.dismissVisibleLoadingSpinner(fragmentManager);
                if (!request.shouldThrottle()) {
                    Toast.makeText(context, R.string.content_expired, 1).show();
                }
                IAMEventSender.withType(EventType.ERROR_EXCEPTION).withDescription(err.getMessage()).send();
                InAppMessageClientApiImpl.this.cachedSplashObservables.remove(request);
            }
        });
        return observableLoadInAppMessage.onErrorReturn(new Func1<Throwable, Splash>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.4
            @Override // rx.functions.Func1
            public Splash call(final Throwable throwable) {
                return null;
            }
        }).map(new Func1<Splash, Boolean>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.3
            @Override // rx.functions.Func1
            public Boolean call(final Splash splash) {
                return Boolean.valueOf(splash != null);
            }
        });
    }

    @Override // com.amazon.music.inappmessaging.external.InAppMessageClientApi
    public Observable<Splash> loadInAppMessage(final IAMRequest request) {
        IAMEventSender.withType(EventType.CLIENT_REQUEST).withDescription(request.toString()).send();
        Observable<IAMCredentials> iAMCredentials = getIAMCredentials();
        Observable<DeviceInformation> deviceInformation = getDeviceInformation();
        Observable<Splash> observableCache = Observable.zip(iAMCredentials, deviceInformation, Observable.zip(iAMCredentials, deviceInformation, new Func2<IAMCredentials, DeviceInformation, Observable<InAppMessageListJson>>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.5
            @Override // rx.functions.Func2
            public Observable<InAppMessageListJson> call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation2) {
                return ((InAppMessageClientApi) InAppMessageClientApiImpl.this).inAppMessagesApiClient.getInAppMessages(iamCredentials, deviceInformation2, request);
            }
        }).subscribeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return InAppMessageClientApiImpl.lambda$loadInAppMessage$0((Observable) obj);
            }
        }), new Func3<IAMCredentials, DeviceInformation, InAppMessageListJson, Splash>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.6
            @Override // rx.functions.Func3
            public Splash call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation2, final InAppMessageListJson listJson) {
                Splash splashInAppMessage = InAppMessageClientApiImpl.this.getSplashInAppMessage(listJson, iamCredentials);
                if (splashInAppMessage != null && InAppMessageClientApiImpl.JSON_RESPONSE_TYPE.equals(splashInAppMessage.responseType)) {
                    InAppMessageClientApiImpl.this.cachedBatchJson.add(((InAppMessageClientApi) InAppMessageClientApiImpl.this).inAppMessagesApiClient.getInAppBatchJson(iamCredentials, deviceInformation2, splashInAppMessage.generatedUrl));
                }
                return splashInAppMessage;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).cache();
        observableCache.onErrorReturn(new Func1<Throwable, Splash>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.7
            @Override // rx.functions.Func1
            public Splash call(final Throwable throwable) {
                return null;
            }
        }).subscribe();
        this.cachedSplashObservables.put(request, observableCache);
        return observableCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFullPageSplashScreenDialog(@NonNull final Splash splash, final Context context, final FragmentManager fragmentManager, final IAMRequest request, final Timer latencyTimer, final Boolean isFTU) {
        Timer.Interval intervalLogInterval = latencyTimer.logInterval("experienceRetrieved");
        long jLongValue = this.configuration.getTimeout().longValue();
        long j = intervalLogInterval.totalTime;
        if (j > jLongValue) {
            sendErrorTimeout(String.format("Api call took total %d >= max allowed %d. Not showing experience.", Long.valueOf(j), Long.valueOf(jLongValue)));
        } else {
            final boolean z = !request.shouldThrottle();
            IamWebViewManager.get(context).loadWebView(splash, this.deeplinkHandler, z).timeout(jLongValue - intervalLogInterval.totalTime, TimeUnit.MILLISECONDS).subscribe(new Action1<WebView>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.8
                @Override // rx.functions.Action1
                public void call(final WebView webView) {
                    InAppMessageClientApiImpl.LOG.info("onNext subscriber call received from IamWebViewManager.loadWebView()");
                    if (fragmentManager.isDestroyed() || webView.getParent() != null) {
                        return;
                    }
                    if (!z) {
                        Timer.Interval intervalLogInterval2 = latencyTimer.logInterval("webViewLoaded");
                        IAMEventSender.withType(EventType.LATENCY_RENDER).withLatency(Float.valueOf(intervalLogInterval2.intervalTime)).withIamRef(splash.iamRef).send();
                        IAMEventSender.withType(EventType.LATENCY_TOTAL).withLatency(Float.valueOf(intervalLogInterval2.totalTime)).withIamRef(splash.iamRef).send();
                    }
                    InAppMessageClientApiImpl.this.dismissVisibleLoadingSpinner(fragmentManager);
                    InAppMessageClientApiImpl.this.displayVisibleSplash(fragmentManager, splash);
                    latencyTimer.logInterval("webViewFragmentDisplayed");
                    IAMEventSender.withType(EventType.CAMPAIGN_DISPLAYED).withIamRef(splash.iamRef).withDescription(request.toString()).withFTU(isFTU).send();
                    context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISPLAYED));
                }
            }, new Action1<Throwable>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.9
                @Override // rx.functions.Action1
                public void call(final Throwable throwable) {
                    InAppMessageClientApiImpl.this.dismissVisibleLoadingSpinner(fragmentManager);
                    if (throwable instanceof TimeoutException) {
                        InAppMessageClientApiImpl.this.sendErrorTimeout(String.format("Render time took longer than max allowed %d. Not showing experience.", ((InAppMessageClientApi) InAppMessageClientApiImpl.this).configuration.getTimeout()));
                    } else {
                        InAppMessageClientApiImpl.LOG.info("An error happened, not showing IAM", throwable);
                        IAMEventSender.withType(EventType.ERROR_EXCEPTION).withDescription(throwable.getMessage()).send();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorTimeout(String message) {
        LOG.warn(message);
        IAMEventSender.withType(EventType.ERROR_TIMEOUT).withDescription(message).send();
    }

    Splash getSplashInAppMessage(final InAppMessageListJson listJson, final IAMCredentials iamCredentials) {
        InAppMessageJson inAppMessageJson = listJson.inAppMessages.isEmpty() ? null : listJson.inAppMessages.get(0);
        if (inAppMessageJson == null || !Splash.NAME.equals(inAppMessageJson.type) || inAppMessageJson.splash == null) {
            return null;
        }
        return Splash.fromInAppMessageJson(inAppMessageJson, iamCredentials);
    }

    Observable<IAMCredentials> getIAMCredentials() {
        return Observable.fromCallable(new Callable<IAMCredentials>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public IAMCredentials call() throws Exception {
                IAMCredentials iamCredentials = ((InAppMessageClientApi) InAppMessageClientApiImpl.this).credentialsVendor.getIamCredentials();
                List<String> listFindMissingIAMCredentials = iamCredentials.findMissingIAMCredentials();
                if (listFindMissingIAMCredentials.isEmpty()) {
                    return iamCredentials;
                }
                String str = "Missing IAM credentials: " + Arrays.toString(listFindMissingIAMCredentials.toArray());
                IAMEventSender.withType(EventType.ERROR_MISSING_CREDENTIALS).withDescription(str).send();
                throw new Exception(str);
            }
        }).cache().subscribeOn(Schedulers.io());
    }

    Observable<DeviceInformation> getDeviceInformation() {
        return Observable.fromCallable(new Callable<DeviceInformation>() { // from class: com.amazon.music.inappmessaging.internal.InAppMessageClientApiImpl.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public DeviceInformation call() {
                DeviceInformation deviceInformation = ((InAppMessageClientApi) InAppMessageClientApiImpl.this).deviceInformationVendor.getDeviceInformation();
                List<String> listFindMissingDeviceInfo = deviceInformation.findMissingDeviceInfo();
                if (!listFindMissingDeviceInfo.isEmpty()) {
                    InAppMessageClientApiImpl.LOG.debug("Not all device information is available. Missing fields are " + listFindMissingDeviceInfo);
                }
                return deviceInformation;
            }
        }).cache().subscribeOn(Schedulers.io());
    }

    public void displayVisibleSplash(final FragmentManager fragmentManager, final Splash splash) {
        FullPageWebViewFragment.create(splash).show(fragmentManager, IAM_SPLASH_DIALOG_TAG);
    }

    @Override // com.amazon.music.inappmessaging.external.InAppMessageClientApi
    public void dismissVisibleSplash(final FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(IAM_SPLASH_DIALOG_TAG);
        if (fragmentFindFragmentByTag != null && (fragmentFindFragmentByTag instanceof FullPageWebViewFragment)) {
            LOG.debug("Dismissing splash fragment");
            ((FullPageWebViewFragment) fragmentFindFragmentByTag).dismissView(true);
        } else {
            LOG.debug("No splash to dismiss");
        }
    }

    public void displayLoadingSpinner(final FragmentManager fragmentManager) {
        new IAMLoadingSpinnerFragment().show(fragmentManager, IAM_LOADING_SPINNER_TAG);
    }

    public void dismissVisibleLoadingSpinner(final FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(IAM_LOADING_SPINNER_TAG);
        if (fragmentFindFragmentByTag != null && (fragmentFindFragmentByTag instanceof IAMLoadingSpinnerFragment)) {
            LOG.debug("Dismissing loading spinner fragment");
            ((IAMLoadingSpinnerFragment) fragmentFindFragmentByTag).dismiss();
        } else {
            LOG.debug("No loading spinner to dismiss");
        }
    }
}
