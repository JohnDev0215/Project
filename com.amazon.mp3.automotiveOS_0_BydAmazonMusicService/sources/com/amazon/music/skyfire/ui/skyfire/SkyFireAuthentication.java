package com.amazon.music.skyfire.ui.skyfire;

import ClientAuthenticationInterface.v1_0.ImmutableClientTokenElement;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.skyfire.core.network.Authentication;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkyFireAuthentication.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/SkyFireAuthentication;", "Lcom/amazon/music/skyfire/core/network/Authentication;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "(Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/CustomerMetadataProvider;)V", "getSessionId", "", "getVideoAuthenticationHeader", "playbackAuthentication", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkyFireAuthentication implements Authentication {
    private final AuthenticationProvider authenticationProvider;
    private final CustomerMetadataProvider customerMetadataProvider;

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String getVideoAuthenticationHeader() {
        return null;
    }

    public SkyFireAuthentication(AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider) {
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(customerMetadataProvider, "customerMetadataProvider");
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
    }

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String playbackAuthentication() {
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authenticationProvider.getOAuthInfoBlocking();
        Outcome.Success success = oAuthInfoBlocking instanceof Outcome.Success ? (Outcome.Success) oAuthInfoBlocking : null;
        if (success == null) {
            return "";
        }
        String accessToken = ((OAuthInfo) success.getValue()).getAccessToken();
        if (accessToken.length() == 0) {
            return accessToken;
        }
        ImmutableClientTokenElement clientToken = ImmutableClientTokenElement.builder().accessToken(accessToken).build();
        Mapper mapper = Mapper.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(clientToken, "clientToken");
        String strObjectToString = mapper.objectToString(clientToken);
        return strObjectToString == null ? "" : strObjectToString;
    }

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String getSessionId() {
        return this.customerMetadataProvider.getCustomerMetadata().getSessionId();
    }
}
