package cerence.ark.assistant.service;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.DmTokenProvider;
import cerence.ark.assistant.dm.data.DmTokenInfo;
import cerence.ark.assistant.framework.data.token.TokenConfig;
import cerence.ark.assistant.framework.data.token.TokenInfo;
import cerence.ark.assistant.framework.data.token.TokenProviderType;

/* JADX INFO: loaded from: classes2.dex */
public class DmTokenProviderImpl implements DmTokenProvider {
    private static final String TAG = "DmTokenProviderImpl";
    private final TokenProviderCallback mTokenProviderCallback;

    public DmTokenProviderImpl(TokenProviderCallback tokenProviderCallback) {
        this.mTokenProviderCallback = tokenProviderCallback;
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public int onGetTokenProviderType() {
        try {
            TokenProviderType tokenProviderTypeOnGetTokenProviderType = this.mTokenProviderCallback.onGetTokenProviderType();
            if (tokenProviderTypeOnGetTokenProviderType == null) {
                Logger.error(TAG, "onGetTokenProviderType: tokenProviderType is null");
                return TokenProviderType.OAUTH.ordinal();
            }
            Logger.info(TAG, "onGetTokenProviderType: " + tokenProviderTypeOnGetTokenProviderType);
            return tokenProviderTypeOnGetTokenProviderType.ordinal();
        } catch (Exception e) {
            Logger.error(TAG, "onGetTokenProviderType exception", e);
            return TokenProviderType.OAUTH.ordinal();
        }
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public boolean onConfigure(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            return this.mTokenProviderCallback.onConfigure(TokenConfig.newBuilder().setGrantType(str).setClientId(str2).setClientSecret(str3).setUrl(str4).setScope(str5).setSslCertificateBundle(str6).setExtraParams(str7).setAdapterParams(str8).build());
        } catch (Exception e) {
            Logger.error(TAG, "onConfigure exception", e);
            return false;
        }
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public DmTokenInfo onAuthenticate() {
        try {
            return convertToDmTokenInfo(this.mTokenProviderCallback.onAuthenticate());
        } catch (Exception e) {
            Logger.error(TAG, "onAuthenticate exception", e);
            return null;
        }
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public DmTokenInfo onRefresh(String str) {
        try {
            return convertToDmTokenInfo(this.mTokenProviderCallback.onRefresh(str));
        } catch (Exception e) {
            Logger.error(TAG, "onRefresh exception", e);
            return null;
        }
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public void onAbort() {
        try {
            this.mTokenProviderCallback.onAbort();
        } catch (Exception e) {
            Logger.error(TAG, "onAbort exception", e);
        }
    }

    @Override // cerence.ark.assistant.dm.DmTokenProvider
    public void onDestroy() {
        try {
            this.mTokenProviderCallback.onDestroy();
        } catch (Exception e) {
            Logger.error(TAG, "onDestroy exception", e);
        }
    }

    public static DmTokenInfo convertToDmTokenInfo(TokenInfo tokenInfo) {
        if (tokenInfo == null) {
            Logger.error(TAG, "convertDmTokeInfo: tokenInfo is null");
            return null;
        }
        DmTokenInfo dmTokenInfo = new DmTokenInfo();
        dmTokenInfo.setTokenType(tokenInfo.getTokenType()).setAccessToken(tokenInfo.getAccessToken()).setRefreshToken(tokenInfo.getRefreshToken()).setExpireIn(tokenInfo.getExpireIn());
        return dmTokenInfo;
    }
}
