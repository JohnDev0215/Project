package android.service.quickaccesswallet;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.quickaccesswallet.IQuickAccessWalletService;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class QuickAccessWalletService extends Service {
    public static final String ACTION_VIEW_WALLET = "android.service.quickaccesswallet.action.VIEW_WALLET";
    public static final String ACTION_VIEW_WALLET_SETTINGS = "android.service.quickaccesswallet.action.VIEW_WALLET_SETTINGS";
    public static final String SERVICE_INTERFACE = "android.service.quickaccesswallet.QuickAccessWalletService";
    public static final String SERVICE_META_DATA = "android.quickaccesswallet";
    private static final String TAG = "QAWalletService";
    private IQuickAccessWalletServiceCallbacks mEventListener;
    private String mEventListenerId;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IQuickAccessWalletService mInterface = new AnonymousClass1();

    public abstract void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest);

    public abstract void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, GetWalletCardsCallback getWalletCardsCallback);

    public abstract void onWalletDismissed();

    /* JADX INFO: renamed from: android.service.quickaccesswallet.QuickAccessWalletService$1, reason: invalid class name */
    class AnonymousClass1 extends IQuickAccessWalletService.Stub {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onWalletCardsRequested$0$QuickAccessWalletService$1(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
            QuickAccessWalletService.this.onWalletCardsRequestedInternal(getWalletCardsRequest, iQuickAccessWalletServiceCallbacks);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardsRequested(final GetWalletCardsRequest getWalletCardsRequest, final IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletService$1$WboeXv6C-qmsF4Zem_GhSvPdPtg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWalletCardsRequested$0$QuickAccessWalletService$1(getWalletCardsRequest, iQuickAccessWalletServiceCallbacks);
                }
            });
        }

        public /* synthetic */ void lambda$onWalletCardSelected$1$QuickAccessWalletService$1(SelectWalletCardRequest selectWalletCardRequest) {
            QuickAccessWalletService.this.onWalletCardSelected(selectWalletCardRequest);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardSelected(final SelectWalletCardRequest selectWalletCardRequest) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletService$1$XpXHv7jyUsd_odyJGwQ94bwjn5c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onWalletCardSelected$1$QuickAccessWalletService$1(selectWalletCardRequest);
                }
            });
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletDismissed() {
            Handler handler = QuickAccessWalletService.this.mHandler;
            final QuickAccessWalletService quickAccessWalletService = QuickAccessWalletService.this;
            handler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$6Mz4YMJNrPs3R7pdUj1xmXdRy4s
                @Override // java.lang.Runnable
                public final void run() {
                    quickAccessWalletService.onWalletDismissed();
                }
            });
        }

        public /* synthetic */ void lambda$registerWalletServiceEventListener$2$QuickAccessWalletService$1(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
            QuickAccessWalletService.this.registerDismissWalletListenerInternal(walletServiceEventListenerRequest, iQuickAccessWalletServiceCallbacks);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void registerWalletServiceEventListener(final WalletServiceEventListenerRequest walletServiceEventListenerRequest, final IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletService$1$HCbM3aeYLja0xVbYVxKgAClrw-Y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$registerWalletServiceEventListener$2$QuickAccessWalletService$1(walletServiceEventListenerRequest, iQuickAccessWalletServiceCallbacks);
                }
            });
        }

        public /* synthetic */ void lambda$unregisterWalletServiceEventListener$3$QuickAccessWalletService$1(WalletServiceEventListenerRequest walletServiceEventListenerRequest) {
            QuickAccessWalletService.this.unregisterDismissWalletListenerInternal(walletServiceEventListenerRequest);
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void unregisterWalletServiceEventListener(final WalletServiceEventListenerRequest walletServiceEventListenerRequest) {
            QuickAccessWalletService.this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletService$1$NzXRT3EMIVwpLnK-Mk2mBS6pTdA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$unregisterWalletServiceEventListener$3$QuickAccessWalletService$1(walletServiceEventListenerRequest);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWalletCardsRequestedInternal(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
        onWalletCardsRequested(getWalletCardsRequest, new GetWalletCardsCallbackImpl(getWalletCardsRequest, iQuickAccessWalletServiceCallbacks, this.mHandler));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (Build.VERSION.SDK_INT < 30) {
            Log.w(TAG, "Warning: binding on pre-R device");
        }
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            Log.w(TAG, "Wrong action");
            return null;
        }
        return this.mInterface.asBinder();
    }

    public final void sendWalletServiceEvent(final WalletServiceEvent walletServiceEvent) {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$QuickAccessWalletService$M4FZfCN7BXYoHdfKcFysYGsRLTY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendWalletServiceEvent$0$QuickAccessWalletService(walletServiceEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendWalletServiceEventInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$sendWalletServiceEvent$0$QuickAccessWalletService(WalletServiceEvent walletServiceEvent) {
        IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks = this.mEventListener;
        if (iQuickAccessWalletServiceCallbacks == null) {
            Log.i(TAG, "No dismiss listener registered");
            return;
        }
        try {
            iQuickAccessWalletServiceCallbacks.onWalletServiceEvent(walletServiceEvent);
        } catch (RemoteException e) {
            Log.w(TAG, "onWalletServiceEvent error", e);
            this.mEventListenerId = null;
            this.mEventListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDismissWalletListenerInternal(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
        this.mEventListenerId = walletServiceEventListenerRequest.getListenerId();
        this.mEventListener = iQuickAccessWalletServiceCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterDismissWalletListenerInternal(WalletServiceEventListenerRequest walletServiceEventListenerRequest) {
        String str = this.mEventListenerId;
        if (str != null && str.equals(walletServiceEventListenerRequest.getListenerId())) {
            this.mEventListenerId = null;
            this.mEventListener = null;
        } else {
            Log.w(TAG, "dismiss listener missing or replaced");
        }
    }
}
