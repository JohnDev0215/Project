package android.service.quickaccesswallet;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
final class GetWalletCardsCallbackImpl implements GetWalletCardsCallback {
    private static final String TAG = "QAWalletCallback";
    private final IQuickAccessWalletServiceCallbacks mCallback;
    private boolean mCalled;
    private final Handler mHandler;
    private final GetWalletCardsRequest mRequest;

    GetWalletCardsCallbackImpl(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks, Handler handler) {
        this.mRequest = getWalletCardsRequest;
        this.mCallback = iQuickAccessWalletServiceCallbacks;
        this.mHandler = handler;
    }

    @Override // android.service.quickaccesswallet.GetWalletCardsCallback
    public void onSuccess(final GetWalletCardsResponse getWalletCardsResponse) {
        if (isValidResponse(getWalletCardsResponse)) {
            this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$GetWalletCardsCallbackImpl$QC3ZFKzSt3o3HZXPv79AXm_y5CU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$GetWalletCardsCallbackImpl(getWalletCardsResponse);
                }
            });
        } else {
            Log.w(TAG, "Invalid GetWalletCards response");
            this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$GetWalletCardsCallbackImpl$7xNzWesJhU8w8EsyP1THfKUBQJI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$1$GetWalletCardsCallbackImpl();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onSuccess$1$GetWalletCardsCallbackImpl() {
        lambda$onFailure$2$GetWalletCardsCallbackImpl(new GetWalletCardsError(null, null));
    }

    @Override // android.service.quickaccesswallet.GetWalletCardsCallback
    public void onFailure(final GetWalletCardsError getWalletCardsError) {
        this.mHandler.post(new Runnable() { // from class: android.service.quickaccesswallet.-$$Lambda$GetWalletCardsCallbackImpl$2AlMCVx8bzhL7Zj_xhmXCyi8dRM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFailure$2$GetWalletCardsCallbackImpl(getWalletCardsError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSuccessInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$onSuccess$0$GetWalletCardsCallbackImpl(GetWalletCardsResponse getWalletCardsResponse) {
        if (this.mCalled) {
            Log.w(TAG, "already called");
            return;
        }
        this.mCalled = true;
        try {
            this.mCallback.onGetWalletCardsSuccess(getWalletCardsResponse);
        } catch (RemoteException e) {
            Log.w(TAG, "Error returning wallet cards", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onFailureInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$onFailure$2$GetWalletCardsCallbackImpl(GetWalletCardsError getWalletCardsError) {
        if (this.mCalled) {
            Log.w(TAG, "already called");
            return;
        }
        this.mCalled = true;
        try {
            this.mCallback.onGetWalletCardsFailure(getWalletCardsError);
        } catch (RemoteException e) {
            Log.e(TAG, "Error returning failure message", e);
        }
    }

    private boolean isValidResponse(GetWalletCardsResponse getWalletCardsResponse) {
        if (getWalletCardsResponse == null) {
            Log.w(TAG, "Invalid response: response is null");
            return false;
        }
        if (getWalletCardsResponse.getWalletCards() == null) {
            Log.w(TAG, "Invalid response: walletCards is null");
            return false;
        }
        if (getWalletCardsResponse.getSelectedIndex() < 0) {
            Log.w(TAG, "Invalid response: selectedIndex is negative");
            return false;
        }
        if (!getWalletCardsResponse.getWalletCards().isEmpty() && getWalletCardsResponse.getSelectedIndex() >= getWalletCardsResponse.getWalletCards().size()) {
            Log.w(TAG, "Invalid response: selectedIndex out of bounds");
            return false;
        }
        if (getWalletCardsResponse.getWalletCards().size() > this.mRequest.getMaxCards()) {
            Log.w(TAG, "Invalid response: too many cards");
            return false;
        }
        for (WalletCard walletCard : getWalletCardsResponse.getWalletCards()) {
            if (walletCard == null) {
                Log.w(TAG, "Invalid response: card is null");
                return false;
            }
            if (walletCard.getCardId() == null) {
                Log.w(TAG, "Invalid response: cardId is null");
                return false;
            }
            Icon cardImage = walletCard.getCardImage();
            if (cardImage == null) {
                Log.w(TAG, "Invalid response: cardImage is null");
                return false;
            }
            if (cardImage.getType() == 1 && cardImage.getBitmap().getConfig() != Bitmap.Config.HARDWARE) {
                Log.w(TAG, "Invalid response: cardImage bitmaps must be hardware bitmaps");
                return false;
            }
            if (TextUtils.isEmpty(walletCard.getContentDescription())) {
                Log.w(TAG, "Invalid response: contentDescription is null");
                return false;
            }
            if (walletCard.getPendingIntent() == null) {
                Log.w(TAG, "Invalid response: pendingIntent is null");
                return false;
            }
        }
        return true;
    }
}
