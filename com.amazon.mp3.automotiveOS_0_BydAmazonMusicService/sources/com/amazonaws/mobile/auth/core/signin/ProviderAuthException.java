package com.amazonaws.mobile.auth.core.signin;

import com.amazonaws.mobile.auth.core.IdentityProvider;

/* JADX INFO: loaded from: classes5.dex */
public class ProviderAuthException extends AuthException {
    public ProviderAuthException(IdentityProvider identityProvider, Exception exc) {
        super(identityProvider, exc);
    }
}
