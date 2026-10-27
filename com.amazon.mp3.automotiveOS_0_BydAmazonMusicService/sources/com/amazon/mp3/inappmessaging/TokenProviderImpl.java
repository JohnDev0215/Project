package com.amazon.mp3.inappmessaging;

import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.external.TokenProvider;
import java.util.concurrent.ExecutionException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TokenProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/inappmessaging/TokenProviderImpl;", "Lcom/amazon/music/inappmessaging/external/TokenProvider;", "()V", "token", "", "getToken", "()Ljava/lang/String;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TokenProviderImpl implements TokenProvider {
    private static final String TAG = TokenProviderImpl.class.getSimpleName();

    @Override // com.amazon.music.inappmessaging.external.TokenProvider
    public String getToken() throws Exception {
        try {
            String token = MAPCookiesManager.getCookiesManager().getToken();
            Intrinsics.checkNotNullExpressionValue(token, "{\n            MAPCookies…Manager().token\n        }");
            return token;
        } catch (Exception e) {
            if (e instanceof InterruptedException ? true : e instanceof ExecutionException ? true : e instanceof MAPCallbackErrorException) {
                Log.error(TAG, Intrinsics.stringPlus("Caught exception from MAPCookiesManager:\n ", ExceptionsKt__ExceptionsKt.stackTraceToString(e)));
                return "";
            }
            throw e;
        }
    }
}
