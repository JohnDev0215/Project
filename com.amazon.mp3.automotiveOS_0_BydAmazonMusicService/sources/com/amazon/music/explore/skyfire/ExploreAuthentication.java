package com.amazon.music.explore.skyfire;

import ClientAuthenticationInterface.v1_0.ImmutableClientTokenElement;
import com.amazon.music.explore.Mappers;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.CustomerMetadata;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.skyfire.core.network.Authentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreAuthentication implements Authentication {
    private AuthenticationProvider authenticationProvider;
    private CustomerMetadataProvider customerMetadataProvider;
    private final Logger logger = LoggerFactory.getLogger("ExploreAuthentication");

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String getVideoAuthenticationHeader() {
        return null;
    }

    public ExploreAuthentication(AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider) {
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
    }

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String playbackAuthentication() {
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authenticationProvider.getOAuthInfoBlocking();
        if (!(oAuthInfoBlocking instanceof Outcome.Success)) {
            return "";
        }
        String accessToken = ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        try {
            return Mappers.mapper().writeValueAsString(ImmutableClientTokenElement.builder().accessToken(accessToken).build());
        } catch (JsonProcessingException e) {
            this.logger.error("Error building client token", (Throwable) e);
            return "";
        }
    }

    @Override // com.amazon.music.skyfire.core.network.Authentication
    public String getSessionId() {
        CustomerMetadata customerMetadata = this.customerMetadataProvider.getCustomerMetadata();
        if (customerMetadata != null) {
            return customerMetadata.getSessionId();
        }
        return null;
    }
}
