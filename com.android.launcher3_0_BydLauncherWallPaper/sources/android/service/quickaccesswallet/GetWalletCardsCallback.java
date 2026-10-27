package android.service.quickaccesswallet;

/* JADX INFO: loaded from: classes3.dex */
public interface GetWalletCardsCallback {
    void onFailure(GetWalletCardsError getWalletCardsError);

    void onSuccess(GetWalletCardsResponse getWalletCardsResponse);
}
