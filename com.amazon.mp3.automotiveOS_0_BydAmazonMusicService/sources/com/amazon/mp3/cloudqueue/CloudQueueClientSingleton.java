package com.amazon.mp3.cloudqueue;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.digitalmusicxp.CustomerInfoProvider;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.amplifyqueue.AmplifyAppClient;
import com.amazon.mp3.amplifyqueue.AmplifyClient;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.RetryWithDelay;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueClientSingleton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueClientSingleton;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueClientSingleton {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static CloudQueueClient sCloudQueueClient = new NoOpCloudQueueClient();
    private static CloudQueueAppClient sCloudQueueAppClient = new NoOpCloudQueueAppClient();
    private static final String TAG = CloudQueueClientSingleton.class.getSimpleName();
    private static final long RETRY_DELAY = TimeUnit.SECONDS.toMillis(1);
    private static final Lazy<String> serviceTier$delegate = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$serviceTier$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            ServiceTierEnum serviceTier = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
            if (serviceTier == null) {
                return null;
            }
            return serviceTier.name();
        }
    });

    /* JADX INFO: compiled from: CloudQueueClientSingleton.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0003J\b\u0010\u0014\u001a\u00020\rH\u0003J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0003J\b\u0010\u001c\u001a\u00020\u001bH\u0007J\u0006\u0010\u001d\u001a\u00020\u001bJ\"\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\b2\u0010\b\u0002\u0010 \u001a\n\u0018\u00010!j\u0004\u0018\u0001`\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u0004\u0018\u00010\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueClientSingleton$Companion;", "", "()V", "MAX_RETRIES", "", "RETRY_DELAY", "", "TAG", "", "kotlin.jvm.PlatformType", "sCloudQueueAppClient", "Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;", "sCloudQueueClient", "Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", "serviceTier", "getServiceTier", "()Ljava/lang/String;", "serviceTier$delegate", "Lkotlin/Lazy;", "createCloudQueueAppClient", "createCloudQueueClient", "getCloudQueueAppClientObservable", "Lio/reactivex/Observable;", "getCloudQueueClientObservable", "getRateTrackManager", "Lcom/amazon/mp3/cloudqueue/RateTrackManager;", "initializeAppClient", "", "initializeClients", "reset", "trackFallbackEvent", "method", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeAppClient$lambda-9, reason: not valid java name */
        public static final void m681initializeAppClient$lambda9(CloudQueueAppClient cloudQueueAppClient) {
        }

        private Companion() {
        }

        private final String getServiceTier() {
            return (String) CloudQueueClientSingleton.serviceTier$delegate.getValue();
        }

        public final RateTrackManager getRateTrackManager() {
            CloudQueueFactory cloudQueueFactory = CloudQueueFactory.getInstance(AmazonApplication.getApplication());
            Intrinsics.checkNotNullExpressionValue(cloudQueueFactory, "getInstance(AmazonApplication.getApplication())");
            return new RateTrackManager(cloudQueueFactory.getCloudQueueService());
        }

        public final Observable<CloudQueueClient> getCloudQueueClientObservable() {
            if (!AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
                Observable<CloudQueueClient> observableSubscribeOn = Observable.just(CloudQueueClientSingleton.sCloudQueueClient).subscribeOn(Schedulers.computation());
                Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "just(sCloudQueueClient).…Schedulers.computation())");
                return observableSubscribeOn;
            }
            Observable<CloudQueueClient> observableSubscribeOn2 = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda4
                @Override // io.reactivex.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    CloudQueueClientSingleton.Companion.m678getCloudQueueClientObservable$lambda0(observableEmitter);
                }
            }).subscribeOn(Schedulers.computation());
            Intrinsics.checkNotNullExpressionValue(observableSubscribeOn2, "create<CloudQueueClient>…Schedulers.computation())");
            return observableSubscribeOn2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCloudQueueClientObservable$lambda-0, reason: not valid java name */
        public static final void m678getCloudQueueClientObservable$lambda0(ObservableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            if (CloudQueueClientSingleton.sCloudQueueClient == null || (CloudQueueClientSingleton.sCloudQueueClient instanceof NoOpCloudQueueClient)) {
                CloudQueueClientSingleton.sCloudQueueClient = CloudQueueClientSingleton.INSTANCE.createCloudQueueClient();
            }
            emitter.onNext(CloudQueueClientSingleton.sCloudQueueClient);
            emitter.onComplete();
        }

        public final Observable<CloudQueueAppClient> getCloudQueueAppClientObservable() {
            if (!AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
                Observable<CloudQueueAppClient> observableSubscribeOn = Observable.just(CloudQueueClientSingleton.sCloudQueueAppClient).subscribeOn(Schedulers.computation());
                Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "just(sCloudQueueAppClien…Schedulers.computation())");
                return observableSubscribeOn;
            }
            Observable<CloudQueueAppClient> observableSubscribeOn2 = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda3
                @Override // io.reactivex.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    CloudQueueClientSingleton.Companion.m677getCloudQueueAppClientObservable$lambda1(observableEmitter);
                }
            }).subscribeOn(Schedulers.computation());
            Intrinsics.checkNotNullExpressionValue(observableSubscribeOn2, "create<CloudQueueAppClie…Schedulers.computation())");
            return observableSubscribeOn2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCloudQueueAppClientObservable$lambda-1, reason: not valid java name */
        public static final void m677getCloudQueueAppClientObservable$lambda1(ObservableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            if (CloudQueueClientSingleton.sCloudQueueAppClient == null || (CloudQueueClientSingleton.sCloudQueueAppClient instanceof NoOpCloudQueueAppClient)) {
                CloudQueueClientSingleton.sCloudQueueAppClient = CloudQueueClientSingleton.INSTANCE.createCloudQueueAppClient();
            }
            emitter.onNext(CloudQueueClientSingleton.sCloudQueueAppClient);
            emitter.onComplete();
        }

        @VisibleForTesting
        private final CloudQueueClient createCloudQueueClient() {
            CloudQueueClient noOpCloudQueueClient;
            Object objInvoke;
            synchronized (CloudQueueClientSingleton.class) {
                final AmazonApplication application = AmazonApplication.getApplication();
                if (AccountCredentialUtil.get(application).isSignedOut()) {
                    return new NoOpCloudQueueClient();
                }
                try {
                    AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(application);
                    CustomerInfoProvider customerInfoProvider = new CustomerInfoProvider() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$createCloudQueueClient$1$customerInfoProvider$1
                        @Override // com.amazon.digitalmusicxp.CustomerInfoProvider
                        public String getCustomerId() {
                            return AccountDetailUtil.get(application).getCustomerId();
                        }
                    };
                    AmplifyClient.Companion companion = AmplifyClient.INSTANCE;
                    objInvoke = AmplifyClient.class.getMethod("getInstance", Context.class, CustomerInfoProvider.class, String.class, String.class, String.class).invoke(null, application, customerInfoProvider, accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), EndpointsProvider.get().getCloudQueueAuthApiEndpoint());
                } catch (Exception e) {
                    Log.error(CloudQueueClientSingleton.TAG, "failed to create cloud queue client", e);
                    CloudQueueClientSingleton.INSTANCE.trackFallbackEvent("createCloudQueueClient", e);
                    noOpCloudQueueClient = new NoOpCloudQueueClient();
                }
                if (objInvoke == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.digitalmusicxp.clients.CloudQueueClient");
                }
                noOpCloudQueueClient = (CloudQueueClient) objInvoke;
                return noOpCloudQueueClient;
            }
        }

        @VisibleForTesting
        private final CloudQueueAppClient createCloudQueueAppClient() {
            CloudQueueAppClient noOpCloudQueueAppClient;
            Object objNewInstance;
            synchronized (CloudQueueClientSingleton.class) {
                final AmazonApplication application = AmazonApplication.getApplication();
                if (AccountCredentialUtil.get(application).isSignedOut()) {
                    return new NoOpCloudQueueAppClient();
                }
                try {
                    AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(application);
                    CustomerInfoProvider customerInfoProvider = new CustomerInfoProvider() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$createCloudQueueAppClient$1$customerInfoProvider$1
                        @Override // com.amazon.digitalmusicxp.CustomerInfoProvider
                        public String getCustomerId() {
                            return AccountDetailUtil.get(application).getCustomerId();
                        }
                    };
                    AmplifyAppClient.Companion companion = AmplifyAppClient.INSTANCE;
                    objNewInstance = AmplifyAppClient.class.getConstructor(Context.class, CustomerInfoProvider.class, String.class, String.class, String.class).newInstance(application, customerInfoProvider, accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), EndpointsProvider.get().getCloudQueueAuthApiEndpoint());
                } catch (Exception e) {
                    Log.error(CloudQueueClientSingleton.TAG, "failed to create cloud app queue client", e);
                    CloudQueueClientSingleton.INSTANCE.trackFallbackEvent("createCloudQueueAppClient", e);
                    noOpCloudQueueAppClient = new NoOpCloudQueueAppClient();
                }
                if (objNewInstance == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.digitalmusicxp.clients.CloudQueueAppClient");
                }
                noOpCloudQueueAppClient = (CloudQueueAppClient) objNewInstance;
                return noOpCloudQueueAppClient;
            }
        }

        public final void reset() {
            synchronized (CloudQueueClientSingleton.class) {
                Companion companion = CloudQueueClientSingleton.INSTANCE;
                CloudQueueClientSingleton.sCloudQueueAppClient = new NoOpCloudQueueAppClient();
                CloudQueueClientSingleton.sCloudQueueClient = new NoOpCloudQueueClient();
                Unit unit = Unit.INSTANCE;
            }
        }

        private final void trackFallbackEvent(String method, Exception exception) {
            Throwable cause;
            CloudQueueFlexMetricsManager cloudQueueFlexMetricsManager = CloudQueueFlexMetricsManager.INSTANCE;
            String message = null;
            FlexEvent.Builder builderWithFlexStr2 = FlexEvent.builder(CloudQueueFlexMetricsManager.Event.FALLBACK.getValue()).withFlexStr1(method).withFlexStr2(exception == null ? null : exception.getMessage());
            if (exception != null && (cause = exception.getCause()) != null) {
                message = cause.getMessage();
            }
            FlexEvent flexEventBuild = builderWithFlexStr2.withFlexStr3(message).withFlexStr4(getServiceTier()).withFlexNum1(Float.valueOf(exception == null ? 1.0f : 0.0f)).build();
            Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(CloudQueueFlexMe…                 .build()");
            cloudQueueFlexMetricsManager.trackEvent(flexEventBuild);
        }

        @SuppressLint({"CheckResult"})
        public final void initializeClients() {
            if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
                Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda0
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter observableEmitter) {
                        CloudQueueClientSingleton.Companion.m682initializeClients$lambda5(observableEmitter);
                    }
                }).subscribeOn(Schedulers.computation()).retryWhen(new RetryWithDelay(3, CloudQueueClientSingleton.RETRY_DELAY)).subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        CloudQueueClientSingleton.Companion.m683initializeClients$lambda6((CloudQueueClient) obj);
                    }
                }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        CloudQueueClientSingleton.Companion.m684initializeClients$lambda7((Throwable) obj);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeClients$lambda-5, reason: not valid java name */
        public static final void m682initializeClients$lambda5(ObservableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            CloudQueueClientSingleton.sCloudQueueClient = CloudQueueClientSingleton.INSTANCE.createCloudQueueClient();
            if (!(CloudQueueClientSingleton.sCloudQueueClient instanceof NoOpCloudQueueClient)) {
                emitter.onNext(CloudQueueClientSingleton.sCloudQueueClient);
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable("NoOpCloudQueueClient is created during initialization"));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeClients$lambda-6, reason: not valid java name */
        public static final void m683initializeClients$lambda6(CloudQueueClient cloudQueueClient) {
            if (cloudQueueClient instanceof NoOpCloudQueueClient) {
                return;
            }
            CloudQueueClientSingleton.INSTANCE.initializeAppClient();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeClients$lambda-7, reason: not valid java name */
        public static final void m684initializeClients$lambda7(Throwable th) {
            Log.error(CloudQueueClientSingleton.TAG, "CloudQueueClient failed to initialize after 3 times", th);
        }

        @SuppressLint({"CheckResult"})
        private final void initializeAppClient() {
            if (ConnectivityUtil.hasAnyInternetConnection()) {
                Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda5
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter observableEmitter) {
                        CloudQueueClientSingleton.Companion.m680initializeAppClient$lambda8(observableEmitter);
                    }
                }).retryWhen(new RetryWithDelay(3, CloudQueueClientSingleton.RETRY_DELAY)).subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda6
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        CloudQueueClientSingleton.Companion.m681initializeAppClient$lambda9((CloudQueueAppClient) obj);
                    }
                }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueueClientSingleton$Companion$$ExternalSyntheticLambda7
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        CloudQueueClientSingleton.Companion.m679initializeAppClient$lambda10((Throwable) obj);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeAppClient$lambda-8, reason: not valid java name */
        public static final void m680initializeAppClient$lambda8(ObservableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            CloudQueueClientSingleton.sCloudQueueAppClient = CloudQueueClientSingleton.INSTANCE.createCloudQueueAppClient();
            if (!(CloudQueueClientSingleton.sCloudQueueAppClient instanceof NoOpCloudQueueAppClient)) {
                emitter.onNext(CloudQueueClientSingleton.sCloudQueueAppClient);
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable("NoOpCloudQueueAppClient is created during initialization"));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: initializeAppClient$lambda-10, reason: not valid java name */
        public static final void m679initializeAppClient$lambda10(Throwable th) {
            Log.error(CloudQueueClientSingleton.TAG, "CloudQueueAppClient failed to initialize after 3 times", th);
        }
    }
}
