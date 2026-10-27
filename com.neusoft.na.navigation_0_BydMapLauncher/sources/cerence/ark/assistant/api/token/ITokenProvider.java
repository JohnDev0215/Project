package cerence.ark.assistant.api.token;

import cerence.ark.assistant.framework.data.token.TokenConfig;
import cerence.ark.assistant.framework.data.token.TokenInfo;
import cerence.ark.assistant.framework.data.token.TokenProviderType;

/* JADX INFO: loaded from: classes2.dex */
public interface ITokenProvider {
    void onAbort();

    TokenInfo onAuthenticate();

    boolean onConfigure(TokenConfig tokenConfig);

    void onDestroy();

    TokenProviderType onGetTokenProviderType();

    TokenInfo onRefresh(String str);
}
