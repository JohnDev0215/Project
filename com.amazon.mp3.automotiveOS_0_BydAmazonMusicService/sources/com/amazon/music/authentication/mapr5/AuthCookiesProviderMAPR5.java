package com.amazon.music.authentication.mapr5;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AuthenticationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthCookiesProviderMAPR5 {
    private static final Logger LOG = LoggerFactory.getLogger(AuthCookiesProviderMAPR5.class.getSimpleName());
    private final MAPAccountManager accountManager;
    private final String domain;
    private final boolean forceRefresh;
    private final TokenManagement tokenManagement;

    public AuthCookiesProviderMAPR5(Context context, String str, boolean z) {
        this(new MAPAccountManager(context), new TokenManagement(context), str, z);
    }

    public AuthCookiesProviderMAPR5(MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, String str, boolean z) {
        Validate.notNull(mAPAccountManager, "accountManager can't be null", new Object[0]);
        Validate.notNull(tokenManagement, "tokenManagement can't be null", new Object[0]);
        Validate.notNull(str, "domain can't be null", new Object[0]);
        this.accountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
        this.domain = str;
        this.forceRefresh = z;
    }

    public String[] getCookies() throws AuthenticationException {
        String account = this.accountManager.getAccount();
        if (account == null) {
            LOG.error("User hasn't signed in. Account ID null.");
            return new String[0];
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, this.forceRefresh);
        try {
            return this.tokenManagement.getCookies(account, this.domain, bundle, null).get(90L, TimeUnit.SECONDS).getStringArray(CookieKeys.KEY_COOKIES);
        } catch (MAPCallbackErrorException e) {
            throw new AuthenticationException(e);
        } catch (InterruptedException unused) {
            throw new AuthenticationException("getAtMainInfo was interrupted internally.");
        } catch (ExecutionException e2) {
            throw new AuthenticationException(e2.getCause());
        } catch (TimeoutException e3) {
            throw new AuthenticationException(e3);
        }
    }
}
