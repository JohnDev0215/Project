package com.amazon.music.curate.skyfire;

import CoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.NetworkProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.StyleSheetProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.core.MethodEngine;
import com.amazon.music.curate.skyfire.local.LocalSkillService;
import com.amazon.music.curate.skyfire.local.SyncEngine;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.music.curate.skyfire.network.ClientInformationCache;
import com.amazon.music.curate.skyfire.network.NetworkEngine;
import com.amazon.music.curate.skyfire.views.TemplateContainer;
import com.amazon.music.curate.skyfire.views.TemplateEngine;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public final class Curate {
    private static final String TAG = "Curate";
    private static AppDatabase appDatabase;
    private static LocalSkillService localSkillService;
    private static final Logger logger = LoggerFactory.getLogger(Curate.class.getSimpleName());
    private static final MethodEngine methodEngine;
    private static NetworkEngine networkEngine;
    private static NetworkProvider networkStaticProvider;
    private static PlaybackProvider playbackStaticProvider;
    private static SharedPreferences sharedPreferences;
    private static StyleSheetProvider styleSheetStaticProvider;
    private static SubscriptionProvider subscriptionStaticProvider;
    private static SyncEngine syncEngine;
    private static TemplateEngine templateEngine;
    private final Context context;
    private CurateBootstrapProviders curateProviders;
    private boolean loadingLibrary;
    private TemplateContainer templateContainer;

    static {
        MethodEngine methodEngine2 = new MethodEngine();
        methodEngine = methodEngine2;
        templateEngine = new TemplateEngine(methodEngine2);
        methodEngine2.registerOwner(Curate.class.getSimpleName());
    }

    public static Curate create(final CurateBootstrapProviders bootstrap) {
        return new Curate(bootstrap);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void onStart(final Context context) {
        CurateMethods.onStart(context);
    }

    public static void clearAppState(final Context context) {
        CurateMethods.clearAppState(context);
    }

    public static void requestLibrarySync() {
        SyncScheduler.resetContextMappingTableLastSync();
    }

    public static PlaybackProvider getStaticPlaybackProvider() {
        return playbackStaticProvider;
    }

    public static void setPlaybackStaticProvider(PlaybackProvider playbackStaticProvider2) {
        playbackStaticProvider = playbackStaticProvider2;
    }

    public static StyleSheetProvider getStaticStyleSheetProvider() {
        return styleSheetStaticProvider;
    }

    public static void setStyleSheetStaticProvider(StyleSheetProvider styleSheetStaticProvider2) {
        styleSheetStaticProvider = styleSheetStaticProvider2;
    }

    public static SubscriptionProvider getSubscriptionStaticProvider() {
        return subscriptionStaticProvider;
    }

    public static void setSubscriptionStaticProvider(SubscriptionProvider subscriptionStaticProvider2) {
        subscriptionStaticProvider = subscriptionStaticProvider2;
    }

    public static NetworkProvider getNetworkStaticProvider() {
        return networkStaticProvider;
    }

    public static void setNetworkStaticProvider(NetworkProvider networkStaticProvider2) {
        networkStaticProvider = networkStaticProvider2;
    }

    public void library() {
        this.loadingLibrary = true;
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.skyfire.Curate$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$library$0((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Method>>() { // from class: com.amazon.music.curate.skyfire.Curate.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                Curate.logger.error("failed to open library", throwable);
            }

            @Override // rx.Observer
            public void onNext(final List<Method> methods) {
                if (Curate.this.loadingLibrary) {
                    Curate.methodEngine.handleMethods(Curate.this.templateContainer.getOwnerId(), methods);
                } else {
                    Curate.logger.debug("Library loading has been stopped, resetting");
                    Curate.this.reset();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$library$0(Subscriber subscriber) {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.loadingLibrary) {
                arrayList.addAll(CurateMethods.onLibraryFrontPageOpened(this.context, appDatabase, this.curateProviders));
            }
            subscriber.onNext(arrayList);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    public void stopLoadingLibrary() {
        logger.debug("Stopped loading library");
        this.loadingLibrary = false;
    }

    public void recommendationsWidget(final String playlistId) {
        methodEngine.handleMethods(this.templateContainer.getOwnerId(), CurateMethods.fetchPlaylistRecommendations(this.context, this.curateProviders, playlistId));
    }

    private Curate(final CurateBootstrapProviders curateBootstrapProviders) {
        Context context = curateBootstrapProviders.getContext();
        this.context = context;
        this.curateProviders = curateBootstrapProviders;
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        AppDatabase database = internalProvider.getDatabase();
        appDatabase = database == null ? internalProvider.provideDatabase(context) : database;
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(TAG, 4);
        }
        if (networkEngine == null) {
            networkEngine = new NetworkEngine(methodEngine, ClientInformationCache.INSTANCE, this.curateProviders.getAppDatabase(), this.curateProviders.getBuildInfoProvider(), this.curateProviders.getDeviceInfoProvider(), this.curateProviders.getFeatureFlagProvider(), this.curateProviders.getAuthProvider(), this.curateProviders.getCustomerMetadataProvider());
        }
        if (syncEngine == null) {
            syncEngine = new SyncEngine(methodEngine, appDatabase);
        }
        this.templateContainer = templateEngine.createTemplateContainer(curateBootstrapProviders);
        if (localSkillService == null) {
            localSkillService = new LocalSkillService(methodEngine);
        }
        setStaticProviders();
    }

    private void setStaticProviders() {
        CurateBootstrapProviders curateBootstrapProviders = this.curateProviders;
        if (curateBootstrapProviders != null) {
            styleSheetStaticProvider = curateBootstrapProviders.getStyleSheetProvider();
            playbackStaticProvider = this.curateProviders.getPlaybackProvider();
            subscriptionStaticProvider = this.curateProviders.getSubscriptionProvider();
            networkStaticProvider = this.curateProviders.getNetworkProvider();
        }
    }

    public static void releaseStaticProviders() {
        logger.debug("Release static CurateBootstrapProviders");
        styleSheetStaticProvider = null;
        playbackStaticProvider = null;
        subscriptionStaticProvider = null;
        networkStaticProvider = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.templateContainer = null;
        CurateBootstrapProviders curateBootstrapProviders = this.curateProviders;
        if (curateBootstrapProviders != null) {
            curateBootstrapProviders.reset();
            this.curateProviders = null;
        }
        Logger logger2 = logger;
        logger2.debug("TemplateContainer was reset");
        logger2.debug("CurateProviders were reset");
    }
}
