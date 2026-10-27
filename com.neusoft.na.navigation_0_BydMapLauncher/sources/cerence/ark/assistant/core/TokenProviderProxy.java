package cerence.ark.assistant.core;

import cerence.ark.assistant.api.token.ITokenProvider;
import cerence.ark.assistant.framework.data.token.TokenConfig;
import cerence.ark.assistant.framework.data.token.TokenInfo;
import cerence.ark.assistant.framework.data.token.TokenProviderType;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.service.TokenProviderCallback;

/* JADX INFO: loaded from: classes2.dex */
public class TokenProviderProxy extends TokenProviderCallback.Stub {
    private static final String TAG = "TokenProviderProxy";
    private final ITokenProvider mTokenProvider;

    public TokenProviderProxy(ITokenProvider iTokenProvider) {
        this.mTokenProvider = iTokenProvider;
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public TokenProviderType onGetTokenProviderType() {
        return this.mTokenProvider.onGetTokenProviderType();
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public boolean onConfigure(TokenConfig tokenConfig) {
        String str = TAG;
        Utils.logMethodBegin(str, "onConfigure");
        boolean zOnConfigure = this.mTokenProvider.onConfigure(tokenConfig);
        Utils.logMethodEnd(str, "onConfigure");
        return zOnConfigure;
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public TokenInfo onAuthenticate() {
        String str = TAG;
        Utils.logMethodBegin(str, "onAuthenticate");
        TokenInfo tokenInfoOnAuthenticate = this.mTokenProvider.onAuthenticate();
        Utils.logMethodEnd(str, "onAuthenticate");
        return tokenInfoOnAuthenticate;
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public TokenInfo onRefresh(String str) {
        String str2 = TAG;
        Utils.logMethodBegin(str2, "onRefresh");
        TokenInfo tokenInfoOnRefresh = this.mTokenProvider.onRefresh(str);
        Utils.logMethodEnd(str2, "onRefresh");
        return tokenInfoOnRefresh;
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public void onAbort() {
        String str = TAG;
        Utils.logMethodBegin(str, "onAbort");
        this.mTokenProvider.onAbort();
        Utils.logMethodEnd(str, "onAbort");
    }

    @Override // cerence.ark.assistant.service.TokenProviderCallback
    public void onDestroy() {
        String str = TAG;
        Utils.logMethodBegin(str, "onDestroy");
        this.mTokenProvider.onDestroy();
        Utils.logMethodEnd(str, "onDestroy");
    }
}
