package com.amazon.mp3.amplifyqueue.api.login;

import com.amazon.digitalmusicxp.CustomerInfoProvider;
import com.amazon.digitalmusicxp.callbacks.ChangeStateResult;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amplifyframework.api.aws.sigv4.FunctionAuthProvider;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FunctionAuthProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0002\u0010\u000bJ\n\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/api/login/FunctionAuthProviderImpl;", "Lcom/amplifyframework/api/aws/sigv4/FunctionAuthProvider;", "customerInfoProvider", "Lcom/amazon/digitalmusicxp/CustomerInfoProvider;", Splash.PARAMS_DEVICE_ID, "", Splash.PARAMS_DEVICE_TYPE, "accessTokenProvider", "Lkotlin/Function0;", "callback", "Lcom/amazon/digitalmusicxp/callbacks/ChangeStateResult;", "(Lcom/amazon/digitalmusicxp/CustomerInfoProvider;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lcom/amazon/digitalmusicxp/callbacks/ChangeStateResult;)V", "lastRetrievalToken", "getAccessTokenWithRetry", "getLatestAuthToken", "updateLastRetrievalToken", "", "accessToken", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FunctionAuthProviderImpl implements FunctionAuthProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private static final Logger logger;
    private final Function0<String> accessTokenProvider;
    private final ChangeStateResult<String> callback;
    private final CustomerInfoProvider customerInfoProvider;
    private final String deviceId;
    private final String deviceType;
    private String lastRetrievalToken;

    public FunctionAuthProviderImpl(CustomerInfoProvider customerInfoProvider, String deviceId, String deviceType, Function0<String> accessTokenProvider, ChangeStateResult<String> callback) {
        Intrinsics.checkNotNullParameter(customerInfoProvider, "customerInfoProvider");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(accessTokenProvider, "accessTokenProvider");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.customerInfoProvider = customerInfoProvider;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.accessTokenProvider = accessTokenProvider;
        this.callback = callback;
    }

    /* JADX INFO: compiled from: FunctionAuthProviderImpl.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/api/login/FunctionAuthProviderImpl$Companion;", "", "()V", "MAX_RETRY_GET_AUTH_TOKEN_COUNT", "", "TAG", "", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "tokenTemplate", "isTokenValid", "", "accessToken", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTokenValid(String accessToken) {
            return !(accessToken == null || accessToken.length() == 0);
        }
    }

    static {
        String simpleName = FunctionAuthProviderImpl.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    @Override // com.amplifyframework.api.aws.sigv4.AuthProvider
    public String getLatestAuthToken() {
        String str;
        String accessTokenWithRetry = getAccessTokenWithRetry();
        if (accessTokenWithRetry != null) {
            if (updateLastRetrievalToken(accessTokenWithRetry) && (str = this.lastRetrievalToken) != null) {
                this.callback.onChangedSuccess(str);
            }
        } else {
            this.callback.onChangeFailed(new Exception("Failed to fetch access token"));
        }
        String str2 = this.lastRetrievalToken;
        String customerId = this.customerInfoProvider.getCustomerId();
        if (str2 != null && customerId != null) {
            String str3 = String.format("{\"accessToken\":\"%s\",\"deviceId\":\"%s\",\"deviceType\":\"%s\",\"customerId\":\"%s\"}", Arrays.copyOf(new Object[]{str2, this.deviceId, this.deviceType, customerId}, 4));
            Intrinsics.checkNotNullExpressionValue(str3, "format(this, *args)");
            return str3;
        }
        logger.error("getLatestAuthToken failed, return empty str: lastRetrievalToken=" + ((Object) str2) + ", customerId=" + ((Object) customerId));
        return "";
    }

    private final synchronized boolean updateLastRetrievalToken(String accessToken) {
        if (!INSTANCE.isTokenValid(accessToken) || Intrinsics.areEqual(this.lastRetrievalToken, accessToken)) {
            return false;
        }
        this.lastRetrievalToken = accessToken;
        return true;
    }

    private final String getAccessTokenWithRetry() {
        int i = 0;
        while (i < 3) {
            int i2 = i + 1;
            if (i != 0) {
                logger.warn(Intrinsics.stringPlus("getAccessTokenWithRetry: get access token attempt#", Integer.valueOf(i)));
            }
            String strInvoke = this.accessTokenProvider.invoke();
            if (INSTANCE.isTokenValid(strInvoke)) {
                return strInvoke;
            }
            i = i2;
        }
        logger.error("getAccessTokenWithRetry: get access token null");
        return null;
    }
}
