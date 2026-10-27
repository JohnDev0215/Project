package com.amazon.mp3.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.ui.interaction.UiInteractionMappingUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.uiinteraction.UIInteractionController;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public class BootstrapSingletonTask {
    private static final String TAG = "BootstrapSingletonTask";
    private static BootstrapSingletonTask sBootstrapSingletonTask;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private static final long ACCOUNT_REFRESH_TIMEOUT = TimeUnit.SECONDS.toMillis(60);
    private static final Object mLock = new Object();
    private PublishSubject userSubject = PublishSubject.create();
    private boolean mUserInitDone = false;
    private AtomicBoolean mComponentsInitialized = new AtomicBoolean(false);
    private final AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.activity.BootstrapSingletonTask.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_HAS_CLOUD_PLAYER_ACCOUNT) || set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE) || set.contains(AccountStateField.USER_LIBRARY_HOME_MARKETPLACE) || set.contains(AccountStateField.IS_ACCOUNT_VALID)) {
                BootstrapSingletonTask.this.onAccountStateChangedOrTimedOut();
            }
        }
    };
    private final Runnable mRefreshEventTimeoutRunnable = new Runnable() { // from class: com.amazon.mp3.activity.BootstrapSingletonTask.2
        @Override // java.lang.Runnable
        public void run() {
            BootstrapSingletonTask.this.onAccountStateChangedOrTimedOut();
            BootstrapSingletonTask.this.mHandlerThread.quitSafely();
        }
    };

    private BootstrapSingletonTask(final Context context) {
        this.mContext = context;
    }

    public static synchronized void initialize(final Context context) {
        synchronized (mLock) {
            if (sBootstrapSingletonTask == null) {
                sBootstrapSingletonTask = new BootstrapSingletonTask(context.getApplicationContext());
            }
        }
    }

    public static synchronized BootstrapSingletonTask get() {
        return sBootstrapSingletonTask;
    }

    @Deprecated
    public static synchronized BootstrapSingletonTask get(final Context context) {
        return sBootstrapSingletonTask;
    }

    public void execute() {
        AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.activity.BootstrapSingletonTask.3
            @Override // java.lang.Runnable
            public void run() {
                BootstrapSingletonTask.this.initializeComponents();
                SecurityProvider.upgrade(BootstrapSingletonTask.this.mContext);
                try {
                    User user = AccountManagerSingleton.get().getUser();
                    if (!ConnectivityUtil.hasAnyInternetConnection(BootstrapSingletonTask.this.mContext) || user.getMusicTerritoryOfResidence() != null) {
                        BootstrapSingletonTask.this.onUserInitComplete();
                    } else {
                        BootstrapSingletonTask.this.setAccountRefreshListenerWithTimeoutRunnable();
                        BootstrapSingletonTask.this.updateUser();
                    }
                } catch (DataNotReadyException unused) {
                    Log.debug(BootstrapSingletonTask.TAG, "User not signed in yet, wait until user is signed in");
                    AccountManagerSingleton.get().registerListener(BootstrapSingletonTask.this.mAccountStateChangeListener);
                    if (AccountCredentialUtil.get().isSignedIn()) {
                        BootstrapSingletonTask.this.updateUser();
                        Log.error(BootstrapSingletonTask.TAG, "We're signed in but user data is missing, calling updateUser");
                    }
                } catch (MusicAccountNotCreatedException unused2) {
                    Log.info(BootstrapSingletonTask.TAG, "BootstrapSingletonTask detects User's Music Account Status is NOT_CREATED. If this is right after an app upgrade from pre 7.9.0 build, this is expected even if customer has a Music Account. Calling updateUser() to refresh user details. ");
                    AccountManagerSingleton.get().registerListener(BootstrapSingletonTask.this.mAccountStateChangeListener);
                    BootstrapSingletonTask.this.updateUser();
                }
            }
        });
    }

    public Observable<Void> registerUserObserver() {
        if (this.mUserInitDone) {
            return Observable.just(null).observeOn(AndroidSchedulers.mainThread());
        }
        return this.userSubject.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    @Deprecated
    public Observable<Void> registerFeatureFlagObserver() {
        return registerUserObserver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUser() {
        AccountManagerSingleton.get().updateUser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccountRefreshListenerWithTimeoutRunnable() {
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        HandlerThread handlerThread = new HandlerThread("BootstrapSingletonTaskThread", 10);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        this.mHandler = handler;
        handler.postDelayed(this.mRefreshEventTimeoutRunnable, ACCOUNT_REFRESH_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccountStateChangedOrTimedOut() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mRefreshEventTimeoutRunnable);
        }
        onUserInitComplete();
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeComponents() {
        synchronized (this.mComponentsInitialized) {
            initUIMetricsIfRequired();
            this.mComponentsInitialized.set(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onUserInitComplete() {
        broadcastUserInitComplete();
        handleEncryptionWeblab();
    }

    private void handleEncryptionWeblab() {
        try {
            PreferenceManager.getDefaultSharedPreferences(this.mContext).edit().putBoolean("use_gcm", true).apply();
        } catch (Exception e) {
            Log.error(TAG, "error when init encryption weblab", e);
        }
    }

    private void initUIMetricsIfRequired() {
        if (this.mComponentsInitialized.get()) {
            return;
        }
        try {
            UIInteractionController.getInstance().initialize(AmazonApplication.getObjectMapper(), UiInteractionMappingUtil.INSTANCE.mappingsAsInputStream(this.mContext.getAssets().open("ui_interaction_mapping_deprecated.json")));
        } catch (IOException e) {
            Log.error(TAG, "Failed to read the metrics mapping file from assets, no UI Interaction metrics will be recorded", e);
        }
    }

    private void broadcastUserInitComplete() {
        this.mUserInitDone = true;
        this.userSubject.onNext(null);
    }

    public void reset() {
        this.mUserInitDone = false;
        execute();
    }

    public Subscription subscribeToBootstrapTaskUserObjectObserver(Action1<Void> action) {
        return registerUserObserver().take(1).subscribe(action, new Action1() { // from class: com.amazon.mp3.activity.BootstrapSingletonTask$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BootstrapSingletonTask.lambda$subscribeToBootstrapTaskUserObjectObserver$0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$subscribeToBootstrapTaskUserObjectObserver$0(Throwable th) {
        Log.error(TAG, "Failed to register to feature flag observer with error: ", th);
    }
}
