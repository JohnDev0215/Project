package com.amazon.music.connect.firefly;

import android.net.Uri;
import android.util.Base64;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazonaws.http.HttpHeader;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: FireFlyService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/connect/firefly/FireFlyService;", "", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "serviceEndpoint", "", "apiKey", "(Lcom/amazon/music/connect/IdentityProvider;Ljava/lang/String;Ljava/lang/String;)V", "httpClient", "Lokhttp3/OkHttpClient;", "constructApiUrlString", "constructAuthorizationToken", "makeRequest", "Lorg/json/JSONObject;", SearchIntents.EXTRA_QUERY, "Companion", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FireFlyService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = FireFlyService.class.getSimpleName();
    private static FireFlyService instance;
    private final String apiKey;
    private final OkHttpClient httpClient;
    private final IdentityProvider identityProvider;
    private final String serviceEndpoint;

    public /* synthetic */ FireFlyService(IdentityProvider identityProvider, String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(identityProvider, str, str2);
    }

    private FireFlyService(IdentityProvider identityProvider, String str, String str2) {
        this.httpClient = UnifiedOkHttpClient.newClient();
        this.identityProvider = identityProvider;
        this.serviceEndpoint = str;
        this.apiKey = str2;
    }

    public final JSONObject query(String query) throws Exception {
        Intrinsics.checkNotNullParameter(query, "query");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(SearchIntents.EXTRA_QUERY, query);
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "queryJson.toString()");
        return makeRequest(string);
    }

    public final JSONObject makeRequest(String query) throws Exception {
        Intrinsics.checkNotNullParameter(query, "query");
        String strConstructApiUrlString = constructApiUrlString();
        ResponseBody responseBodyBody = FirebasePerfOkHttpClient.execute(this.httpClient.newCall(new Request.Builder().url(HttpUrl.INSTANCE.get(strConstructApiUrlString)).header(HttpHeader.AUTHORIZATION, constructAuthorizationToken()).header("x-api-key", this.apiKey).header(HttpHeader.CONTENT_TYPE, "application/json").post(RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/json"), query)).build())).body();
        return new JSONObject(responseBodyBody == null ? null : responseBodyBody.string());
    }

    private final String constructApiUrlString() {
        String string = Uri.parse(this.serviceEndpoint).buildUpon().build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "parse(serviceEndpoint)\n …              .toString()");
        return string;
    }

    private final String constructAuthorizationToken() throws Exception {
        String token = this.identityProvider.getToken();
        Intrinsics.checkNotNullExpressionValue(token, "identityProvider.getToken()");
        String deviceId = this.identityProvider.getDeviceId();
        Intrinsics.checkNotNullExpressionValue(deviceId, "identityProvider.getDeviceId()");
        String deviceType = this.identityProvider.getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "identityProvider.getDeviceType()");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access_token", token);
        jSONObject.put(Splash.PARAMS_DEVICE_ID, deviceId);
        jSONObject.put(Splash.PARAMS_DEVICE_TYPE, deviceType);
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "authValues.toString()");
        Charset charset = Charsets.UTF_8;
        byte[] bytes = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] bArrEncode = Base64.encode(bytes, 2);
        Intrinsics.checkNotNullExpressionValue(bArrEncode, "encode(authValues.toStri…eArray(), Base64.NO_WRAP)");
        return Intrinsics.stringPlus("AmznMusic ", new String(bArrEncode, charset));
    }

    /* JADX INFO: compiled from: FireFlyService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/connect/firefly/FireFlyService$Companion;", "", "()V", "ACCESS_TOKEN_KEY", "", "API_KEY_HEADER_KEY", "AUTHORIZATION_HEADER_KEY", "AUTH_TOKEN_PREFIX", "CONTENT_TYPE_HEADER_KEY", "CONTENT_TYPE_HEADER_VALUE", "DEVICE_ID_KEY", "DEVICE_TYPE_KEY", "QUERY_PROPERTY_KEY", "TAG", "kotlin.jvm.PlatformType", "instance", "Lcom/amazon/music/connect/firefly/FireFlyService;", "getInstance", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "serviceEndpoint", "apiKey", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FireFlyService getInstance(IdentityProvider identityProvider, String serviceEndpoint, String apiKey) {
            Intrinsics.checkNotNullParameter(identityProvider, "identityProvider");
            Intrinsics.checkNotNullParameter(serviceEndpoint, "serviceEndpoint");
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            if (FireFlyService.instance == null) {
                FireFlyService.instance = new FireFlyService(identityProvider, serviceEndpoint, apiKey, null);
            }
            return FireFlyService.instance;
        }
    }
}
