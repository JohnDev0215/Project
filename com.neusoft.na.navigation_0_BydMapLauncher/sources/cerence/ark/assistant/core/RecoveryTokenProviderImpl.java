package cerence.ark.assistant.core;

import cerence.ark.assistant.api.token.ITokenProvider;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.DmTokenProvider;
import cerence.ark.assistant.dm.data.DmTokenInfo;
import cerence.ark.assistant.framework.data.token.TokenConfig;
import cerence.ark.assistant.framework.data.token.TokenInfo;
import cerence.ark.assistant.framework.data.token.TokenProviderType;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.service.DmTokenProviderImpl;

/* JADX INFO: loaded from: classes2.dex */
public class RecoveryTokenProviderImpl implements DmTokenProvider {
    private static final String TAG = "RecoveryTokenProviderImpl";
    private final ITokenProvider mTokenProvider;

    public RecoveryTokenProviderImpl(ITokenProvider iTokenProvider) {
        this.mTokenProvider = iTokenProvider;
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public int onGetTokenProviderType() {
        TokenProviderType tokenProviderTypeOnGetTokenProviderType = this.mTokenProvider.onGetTokenProviderType();
        if (tokenProviderTypeOnGetTokenProviderType == null) {
            Logger.error(TAG, "onGetTokenProviderType: tokenProviderType is null");
            return TokenProviderType.OAUTH.ordinal();
        }
        Logger.info(TAG, "onGetTokenProviderType: " + tokenProviderTypeOnGetTokenProviderType);
        return tokenProviderTypeOnGetTokenProviderType.ordinal();
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public boolean onConfigure(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Utils.logMethodBegin(TAG, "onConfigure");
        boolean zOnConfigure = this.mTokenProvider.onConfigure(TokenConfig.newBuilder().setGrantType(str).setClientId(str2).setClientSecret(str3).setUrl(str4).setScope(str5).setSslCertificateBundle(str6).setExtraParams(str7).setAdapterParams(str8).build());
        Utils.logMethodEnd(TAG, "onConfigure");
        return zOnConfigure;
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public DmTokenInfo onAuthenticate() {
        Utils.logMethodBegin(TAG, "onAuthenticate");
        TokenInfo tokenInfoOnAuthenticate = this.mTokenProvider.onAuthenticate();
        Utils.logMethodEnd(TAG, "onAuthenticate");
        return DmTokenProviderImpl.convertToDmTokenInfo(tokenInfoOnAuthenticate);
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public DmTokenInfo onRefresh(String str) {
        Utils.logMethodBegin(TAG, "onRefresh");
        TokenInfo tokenInfoOnRefresh = this.mTokenProvider.onRefresh(str);
        Utils.logMethodEnd(TAG, "onRefresh");
        return DmTokenProviderImpl.convertToDmTokenInfo(tokenInfoOnRefresh);
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public void onAbort() {
        Utils.logMethodBegin(TAG, "onAbort");
        this.mTokenProvider.onAbort();
        Utils.logMethodEnd(TAG, "onAbort");
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public void onDestroy() {
        Utils.logMethodBegin(TAG, "onDestroy");
        this.mTokenProvider.onDestroy();
        Utils.logMethodEnd(TAG, "onDestroy");
    }
}
