package android.service.quickaccesswallet;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.quickaccesswallet.IQuickAccessWalletService;
import android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.widget.LockPatternUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public class QuickAccessWalletClientImpl implements QuickAccessWalletClient, ServiceConnection {
    private static final int MSG_TIMEOUT_SERVICE = 5;
    private static final long SERVICE_CONNECTION_TIMEOUT_MS = 60000;
    private static final String TAG = "QAWalletSClient";
    private final Context mContext;
    private boolean mIsConnected;
    private IQuickAccessWalletService mService;
    private final QuickAccessWalletServiceInfo mServiceInfo;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Queue<ApiCaller> mRequestQueue = new LinkedList();
    private final Map<QuickAccessWalletClient.WalletServiceEventListener, String> mEventListeners = new HashMap(1);

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    QuickAccessWalletClientImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mServiceInfo = QuickAccessWalletServiceInfo.tryCreate(context);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletServiceAvailable() {
        return this.mServiceInfo != null;
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletFeatureAvailable() {
        int currentUser = ActivityManager.getCurrentUser();
        return currentUser == 0 && checkUserSetupComplete() && checkSecureSetting(Settings.Secure.GLOBAL_ACTIONS_PANEL_ENABLED) && !new LockPatternUtils(this.mContext).isUserInLockdown(currentUser);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public boolean isWalletFeatureAvailableWhenDeviceLocked() {
        return checkSecureSetting(Settings.Secure.POWER_MENU_LOCKED_SHOW_CONTENT);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void getWalletCards(GetWalletCardsRequest getWalletCardsRequest, QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback) {
        getWalletCards(this.mContext.getMainExecutor(), getWalletCardsRequest, onWalletCardsRetrievedCallback);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void getWalletCards(Executor executor, final GetWalletCardsRequest getWalletCardsRequest, final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback) {
        if (!isWalletServiceAvailable()) {
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$9fpShU4lpa_oyQrvYBJriO589Zk
                @Override // java.lang.Runnable
                public final void run() {
                    onWalletCardsRetrievedCallback.onWalletCardRetrievalError(new GetWalletCardsError(null, null));
                }
            });
        } else {
            final AnonymousClass1 anonymousClass1 = new AnonymousClass1(executor, onWalletCardsRetrievedCallback);
            executeApiCall(new ApiCaller("onWalletCardsRequested") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.2
                {
                    AnonymousClass1 anonymousClass12 = null;
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException {
                    iQuickAccessWalletService.onWalletCardsRequested(getWalletCardsRequest, anonymousClass1);
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void onApiError() {
                    anonymousClass1.onGetWalletCardsFailure(new GetWalletCardsError(null, null));
                }
            });
        }
    }

    /* JADX INFO: renamed from: android.service.quickaccesswallet.QuickAccessWalletClientImpl$1, reason: invalid class name */
    class AnonymousClass1 extends BaseCallbacks {
        final /* synthetic */ QuickAccessWalletClient.OnWalletCardsRetrievedCallback val$callback;
        final /* synthetic */ Executor val$executor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Executor executor, QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback) {
            super(null);
            this.val$executor = executor;
            this.val$callback = onWalletCardsRetrievedCallback;
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsSuccess(final GetWalletCardsResponse getWalletCardsResponse) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$1$-EeC__v-8yl5HNu2DkD_XNGiBZA
                @Override // java.lang.Runnable
                public final void run() {
                    onWalletCardsRetrievedCallback.onWalletCardsRetrieved(getWalletCardsResponse);
                }
            });
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsFailure(final GetWalletCardsError getWalletCardsError) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$1$AHcYfBfW_N76VwnZJ0yQ24agjjU
                @Override // java.lang.Runnable
                public final void run() {
                    onWalletCardsRetrievedCallback.onWalletCardRetrievalError(getWalletCardsError);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void selectWalletCard(final SelectWalletCardRequest selectWalletCardRequest) {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("onWalletCardSelected") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.3
                {
                    AnonymousClass1 anonymousClass1 = null;
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException {
                    iQuickAccessWalletService.onWalletCardSelected(selectWalletCardRequest);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void notifyWalletDismissed() {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("onWalletDismissed") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.4
                {
                    AnonymousClass1 anonymousClass1 = null;
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException {
                    iQuickAccessWalletService.onWalletDismissed();
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void addWalletServiceEventListener(QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener) {
        addWalletServiceEventListener(this.mContext.getMainExecutor(), walletServiceEventListener);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void addWalletServiceEventListener(Executor executor, final QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener) {
        if (isWalletServiceAvailable()) {
            final AnonymousClass5 anonymousClass5 = new AnonymousClass5(executor, walletServiceEventListener);
            executeApiCall(new ApiCaller("registerListener") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.6
                {
                    AnonymousClass1 anonymousClass1 = null;
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException {
                    String string = UUID.randomUUID().toString();
                    WalletServiceEventListenerRequest walletServiceEventListenerRequest = new WalletServiceEventListenerRequest(string);
                    QuickAccessWalletClientImpl.this.mEventListeners.put(walletServiceEventListener, string);
                    iQuickAccessWalletService.registerWalletServiceEventListener(walletServiceEventListenerRequest, anonymousClass5);
                }
            });
        }
    }

    /* JADX INFO: renamed from: android.service.quickaccesswallet.QuickAccessWalletClientImpl$5, reason: invalid class name */
    class AnonymousClass5 extends BaseCallbacks {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ QuickAccessWalletClient.WalletServiceEventListener val$listener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Executor executor, QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener) {
            super(null);
            this.val$executor = executor;
            this.val$listener = walletServiceEventListener;
        }

        @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.BaseCallbacks, android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onWalletServiceEvent(final WalletServiceEvent walletServiceEvent) {
            Executor executor = this.val$executor;
            final QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener = this.val$listener;
            executor.execute(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$5$oT1HQ_9xe39U91Aafdr92t5Sl_E
                @Override // java.lang.Runnable
                public final void run() {
                    walletServiceEventListener.onWalletServiceEvent(walletServiceEvent);
                }
            });
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void removeWalletServiceEventListener(final QuickAccessWalletClient.WalletServiceEventListener walletServiceEventListener) {
        if (isWalletServiceAvailable()) {
            executeApiCall(new ApiCaller("unregisterListener") { // from class: android.service.quickaccesswallet.QuickAccessWalletClientImpl.7
                {
                    AnonymousClass1 anonymousClass1 = null;
                }

                @Override // android.service.quickaccesswallet.QuickAccessWalletClientImpl.ApiCaller
                public void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException {
                    String str = (String) QuickAccessWalletClientImpl.this.mEventListeners.remove(walletServiceEventListener);
                    if (str == null) {
                        return;
                    }
                    iQuickAccessWalletService.unregisterWalletServiceEventListener(new WalletServiceEventListenerRequest(str));
                }
            });
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        disconnect();
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public void disconnect() {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$3ZnK4dvNiN6XRJzwXm9wuEL9CQI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$disconnect$1$QuickAccessWalletClientImpl();
            }
        });
    }

    public /* synthetic */ void lambda$disconnect$1$QuickAccessWalletClientImpl() {
        disconnectInternal(true);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Intent createWalletIntent() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return createIntent(this.mServiceInfo.getWalletActivity(), quickAccessWalletServiceInfo.getComponentName().getPackageName(), QuickAccessWalletService.ACTION_VIEW_WALLET);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Intent createWalletSettingsIntent() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return createIntent(this.mServiceInfo.getSettingsActivity(), quickAccessWalletServiceInfo.getComponentName().getPackageName(), QuickAccessWalletService.ACTION_VIEW_WALLET_SETTINGS);
    }

    private Intent createIntent(String str, String str2, String str3) {
        PackageManager packageManager = this.mContext.getPackageManager();
        if (TextUtils.isEmpty(str)) {
            str = queryActivityForAction(packageManager, str2, str3);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ComponentName componentName = new ComponentName(str2, str);
        if (isActivityEnabled(packageManager, componentName)) {
            return new Intent(str3).setComponent(componentName);
        }
        return null;
    }

    private static String queryActivityForAction(PackageManager packageManager, String str, String str2) {
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(new Intent(str2).setPackage(str), 0);
        if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null || !resolveInfoResolveActivity.activityInfo.exported) {
            return null;
        }
        return resolveInfoResolveActivity.activityInfo.name;
    }

    private static boolean isActivityEnabled(PackageManager packageManager, ComponentName componentName) {
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting == 1) {
            return true;
        }
        if (componentEnabledSetting != 0) {
            return false;
        }
        try {
            return packageManager.getActivityInfo(componentName, 0).isEnabled();
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public Drawable getLogo() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getWalletLogo(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getServiceLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getServiceLabel(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getShortcutShortLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getShortcutShortLabel(this.mContext);
    }

    @Override // android.service.quickaccesswallet.QuickAccessWalletClient
    public CharSequence getShortcutLongLabel() {
        QuickAccessWalletServiceInfo quickAccessWalletServiceInfo = this.mServiceInfo;
        if (quickAccessWalletServiceInfo == null) {
            return null;
        }
        return quickAccessWalletServiceInfo.getShortcutLongLabel(this.mContext);
    }

    private void connect() {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$ZVs3IwfL14SOwWl64RtuB_VVX0M
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.connectInternal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectInternal() {
        if (this.mServiceInfo == null) {
            Log.w(TAG, "Wallet service unavailable");
            return;
        }
        if (this.mIsConnected) {
            return;
        }
        this.mIsConnected = true;
        Intent intent = new Intent(QuickAccessWalletService.SERVICE_INTERFACE);
        intent.setComponent(this.mServiceInfo.getComponentName());
        this.mContext.bindService(intent, this, 33);
        resetServiceConnectionTimeout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConnectedInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$onServiceConnected$5$QuickAccessWalletClientImpl(IQuickAccessWalletService iQuickAccessWalletService) {
        if (!this.mIsConnected) {
            Log.w(TAG, "onConnectInternal but connection closed");
            this.mService = null;
            return;
        }
        this.mService = iQuickAccessWalletService;
        for (ApiCaller apiCaller : new ArrayList(this.mRequestQueue)) {
            performApiCallInternal(apiCaller, this.mService);
            this.mRequestQueue.remove(apiCaller);
        }
    }

    private void resetServiceConnectionTimeout() {
        this.mHandler.removeMessages(5);
        this.mHandler.postDelayed(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$PlDmJOnS-X3k4hKAHEgOqI5tOVE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$resetServiceConnectionTimeout$2$QuickAccessWalletClientImpl();
            }
        }, 5, 60000L);
    }

    public /* synthetic */ void lambda$resetServiceConnectionTimeout$2$QuickAccessWalletClientImpl() {
        disconnectInternal(true);
    }

    private void disconnectInternal(boolean z) {
        if (!this.mIsConnected) {
            Log.w(TAG, "already disconnected");
            return;
        }
        if (z && !this.mEventListeners.isEmpty()) {
            Iterator<QuickAccessWalletClient.WalletServiceEventListener> it = this.mEventListeners.keySet().iterator();
            while (it.hasNext()) {
                removeWalletServiceEventListener(it.next());
            }
            this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$hr-_9xHBy5onBUPkRUDlbnft4eg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$disconnectInternal$3$QuickAccessWalletClientImpl();
                }
            });
            return;
        }
        this.mIsConnected = false;
        this.mContext.unbindService(this);
        this.mService = null;
        this.mEventListeners.clear();
        this.mRequestQueue.clear();
    }

    public /* synthetic */ void lambda$disconnectInternal$3$QuickAccessWalletClientImpl() {
        disconnectInternal(false);
    }

    private void executeApiCall(final ApiCaller apiCaller) {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$8M9cggJn98aTR9ZosP0vvNXuWro
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$executeApiCall$4$QuickAccessWalletClientImpl(apiCaller);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: executeInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$executeApiCall$4$QuickAccessWalletClientImpl(ApiCaller apiCaller) {
        IQuickAccessWalletService iQuickAccessWalletService;
        if (this.mIsConnected && (iQuickAccessWalletService = this.mService) != null) {
            performApiCallInternal(apiCaller, iQuickAccessWalletService);
        } else {
            this.mRequestQueue.add(apiCaller);
            connect();
        }
    }

    private void performApiCallInternal(ApiCaller apiCaller, IQuickAccessWalletService iQuickAccessWalletService) {
        if (iQuickAccessWalletService == null) {
            apiCaller.onApiError();
            return;
        }
        try {
            apiCaller.performApiCall(iQuickAccessWalletService);
            resetServiceConnectionTimeout();
        } catch (RemoteException e) {
            Log.w(TAG, "executeInternal error: " + apiCaller.mDesc, e);
            apiCaller.onApiError();
            disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class ApiCaller {
        private final String mDesc;

        abstract void performApiCall(IQuickAccessWalletService iQuickAccessWalletService) throws RemoteException;

        /* synthetic */ ApiCaller(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private ApiCaller(String str) {
            this.mDesc = str;
        }

        void onApiError() {
            Log.w(QuickAccessWalletClientImpl.TAG, "api error: " + this.mDesc);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        final IQuickAccessWalletService iQuickAccessWalletServiceAsInterface = IQuickAccessWalletService.Stub.asInterface(iBinder);
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletClientImpl$qmxjjNXawkds5ZLMf104m8DetWI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onServiceConnected$5$QuickAccessWalletClientImpl(iQuickAccessWalletServiceAsInterface);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        disconnect();
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        disconnect();
    }

    private boolean checkSecureSetting(String str) {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), str, 0) == 1;
    }

    private boolean checkUserSetupComplete() {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), Settings.Secure.USER_SETUP_COMPLETE, 0, -2) == 1;
    }

    private static class BaseCallbacks extends IQuickAccessWalletServiceCallbacks.Stub {
        private BaseCallbacks() {
        }

        /* synthetic */ BaseCallbacks(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onGetWalletCardsSuccess(GetWalletCardsResponse getWalletCardsResponse) {
            throw new IllegalStateException();
        }

        public void onGetWalletCardsFailure(GetWalletCardsError getWalletCardsError) {
            throw new IllegalStateException();
        }

        public void onWalletServiceEvent(WalletServiceEvent walletServiceEvent) {
            throw new IllegalStateException();
        }
    }
}
