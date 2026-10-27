package com.amazon.music.media.auto.provider.network;

import ClientAuthenticationInterface.v1_0.ImmutableClientTokenElement;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.find.skyfire.providers.FindNetworkInterceptorProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.Mappers;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* JADX INFO: compiled from: DefaultMESKRequestProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/media/auto/provider/network/DefaultMESKRequestProvider;", "Lcom/amazon/music/media/auto/provider/network/MESKRequestProvider;", "()V", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildBody", "Lokhttp3/RequestBody;", "meskRequestBody", "Lcom/amazon/music/media/auto/provider/network/MESKRequestBody;", "buildHeaders", "Lokhttp3/Headers;", "getOAuthInfoBlocking", "", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultMESKRequestProvider implements MESKRequestProvider {
    private static final String TAG = DefaultMESKRequestProvider.class.getSimpleName();
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;

    public DefaultMESKRequestProvider() {
        Providers providers = Providers.INSTANCE;
        this.authenticationProvider = (AuthenticationProvider) providers.get(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class));
        this.buildInfoProvider = (BuildInfoProvider) providers.get(Reflection.getOrCreateKotlinClass(BuildInfoProvider.class));
        this.customerMetadataProvider = (CustomerMetadataProvider) providers.get(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class));
        this.deviceInfoProvider = (DeviceInfoProvider) providers.get(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class));
    }

    @Override // com.amazon.music.media.auto.provider.network.MESKRequestProvider
    public Headers buildHeaders() throws Exception {
        try {
            if (this.buildInfoProvider == null) {
                throw new Exception("BuildInfoProvider is null");
            }
            if (this.customerMetadataProvider == null) {
                throw new Exception("CustomerMetadataProvider is null");
            }
            if (this.deviceInfoProvider == null) {
                throw new Exception("DeviceInfoProvider is null");
            }
            Headers.Builder builderAdd = new Headers.Builder().add("x-amzn-device-id", this.deviceInfoProvider.getDeviceIdentity().getDeviceId()).add("x-amzn-device-type-id", this.deviceInfoProvider.getDeviceIdentity().getDeviceTypeId()).add("x-amzn-device-family", "MobileAndroid").add("x-amzn-device-manufacturer", this.deviceInfoProvider.getManufacturer()).add("x-amzn-device-language", this.deviceInfoProvider.getLocales().get(0)).add("x-amzn-device-height", String.valueOf(this.deviceInfoProvider.getScreenHeight())).add("x-amzn-device-width", String.valueOf(this.deviceInfoProvider.getScreenWidth())).add("x-amzn-application-version", this.buildInfoProvider.getAppVersion()).add("x-amzn-os-version", this.deviceInfoProvider.getOsVersion()).add("x-amzn-device-time-zone", this.deviceInfoProvider.getTimeZone()).add("x-amzn-timestamp", String.valueOf(System.currentTimeMillis())).add("x-amzn-user-agent", this.deviceInfoProvider.getUserAgent());
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            builderAdd.add("x-amzn-request-id", string);
            builderAdd.add(FindNetworkInterceptorProvider.AUTHENTICATION_HEADER, getOAuthInfoBlocking());
            builderAdd.add("x-amzn-session-id", String.valueOf(this.customerMetadataProvider.getCustomerMetadata().getSessionId()));
            return builderAdd.build();
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Failed to build headers", e);
            throw e;
        }
    }

    @Override // com.amazon.music.media.auto.provider.network.MESKRequestProvider
    public RequestBody buildBody(MESKRequestBody meskRequestBody) throws Exception {
        Intrinsics.checkNotNullParameter(meskRequestBody, "meskRequestBody");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", meskRequestBody.getId());
            MediaType mediaType = MediaType.INSTANCE.get("application/json; charset=utf-8");
            RequestBody.Companion companion = RequestBody.INSTANCE;
            String string = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "bodyObject.toString()");
            return companion.create(string, mediaType);
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Failed to build request body", e);
            throw e;
        }
    }

    private final String getOAuthInfoBlocking() throws Exception {
        AuthenticationProvider authenticationProvider = this.authenticationProvider;
        if (authenticationProvider != null) {
            Outcome<OAuthInfo> oAuthInfoBlocking = authenticationProvider.getOAuthInfoBlocking();
            if (oAuthInfoBlocking instanceof Outcome.Success) {
                String accessToken = ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
                if (StringUtils.isEmpty(accessToken)) {
                    return accessToken;
                }
                ImmutableClientTokenElement immutableClientTokenElementBuild = ImmutableClientTokenElement.builder().accessToken(accessToken).build();
                Intrinsics.checkNotNullExpressionValue(immutableClientTokenElementBuild, "builder()\n              …                 .build()");
                try {
                    String strWriteValueAsString = Mappers.INSTANCE.mapper().writeValueAsString(immutableClientTokenElementBuild);
                    Intrinsics.checkNotNullExpressionValue(strWriteValueAsString, "Mappers.mapper().writeValueAsString(clientToken)");
                    return strWriteValueAsString;
                } catch (Exception e) {
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.error(TAG2, "Error building client token", e);
                    throw new AuthenticationException(e);
                }
            }
            throw new AuthenticationException("Failed to call getOAuthInfoBlocking", null, 2, null);
        }
        throw new AuthenticationException("AuthenticationProvider is null", null, 2, null);
    }
}
