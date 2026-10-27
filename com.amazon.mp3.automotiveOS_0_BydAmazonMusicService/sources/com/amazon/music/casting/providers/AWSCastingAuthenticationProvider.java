package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.LoginResponse;
import com.amazonaws.auth.AWSAbstractCognitoDeveloperIdentityProvider;
import com.amazonaws.regions.Regions;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AWSCastingAuthenticationProvider extends AWSAbstractCognitoDeveloperIdentityProvider {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) AWSCastingAuthenticationProvider.class);
    private final CastingService service;

    @Override // com.amazonaws.auth.AWSAbstractCognitoDeveloperIdentityProvider, com.amazonaws.auth.AWSAbstractCognitoIdentityProvider
    public String getProviderName() {
        return "amazon.music.casting";
    }

    public AWSCastingAuthenticationProvider(CastingService castingService, String str, String str2, Regions regions) {
        super(str, str2, regions);
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    @Override // com.amazonaws.auth.AWSAbstractCognitoIdentityProvider, com.amazonaws.auth.AWSIdentityProvider
    public String refresh() {
        setToken(null);
        try {
            LoginResponse loginResponseLogin = new LoginProvider(this.service).login();
            update(loginResponseLogin.getIdentityId(), loginResponseLogin.getToken());
        } catch (CastingException unused) {
            LOG.error("Failed login API call");
        }
        return this.token;
    }
}
