package com.amazon.music.authentication.mapr5;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AdpInfo;
import com.amazon.music.authentication.AdpProvider;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.TokenKeyProvider;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class AdpProviderMAPR5 implements AdpProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AdpProviderMAPR5.class.getSimpleName());
    private final MAPAccountManager mapAccountManager;
    private final String packageName;
    private final TokenKeyProvider tokenKeyProvider;
    private final TokenManagement tokenManagement;

    public AdpProviderMAPR5(Context context) {
        this(((Context) Validate.notNull(context)).getPackageName(), new MAPAccountManager(context), new TokenManagement(context), new TokenKeyProvider());
    }

    public AdpProviderMAPR5(String str, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, TokenKeyProvider tokenKeyProvider) {
        Validate.notEmpty(str, "packageName can't be empty", new Object[0]);
        Validate.notNull(mAPAccountManager, "accountManager can't be null", new Object[0]);
        Validate.notNull(tokenManagement, "tokenManagement can't be null", new Object[0]);
        Validate.notNull(tokenKeyProvider, "tokenKeyProvider can't be null", new Object[0]);
        this.packageName = str;
        this.mapAccountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
        this.tokenKeyProvider = tokenKeyProvider;
    }

    @Override // com.amazon.music.authentication.AdpProvider
    public AdpInfo getAdpInfo() throws AuthenticationException {
        return new AdpInfo(fetchValue(this.tokenKeyProvider.getAdpTokenKeyForPackage(this.packageName)), fetchValue(this.tokenKeyProvider.getPrivateKeyKeyForPackage(this.packageName)));
    }

    private String fetchValue(String str) throws AuthenticationException {
        String account = this.mapAccountManager.getAccount();
        if (account == null) {
            throw new AuthenticationException("MAP returned NULL account id");
        }
        try {
            Bundle bundle = this.tokenManagement.getToken(account, str, null, null).get(90L, TimeUnit.SECONDS);
            String string = bundle.getString("value_key");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            LOG.error(String.format("DMMAuthenticationMAPR5: Authentication Error: Missing MAP authentication value. Fetching MAP value resulted in missing value for keyName: %s. Throwing MapEmptyValueException", str));
            throw new AuthenticationException(new MapEmptyValueException(bundle, str));
        } catch (MAPCallbackErrorException e) {
            throw new AuthenticationException(e);
        } catch (InterruptedException unused) {
            throw new AuthenticationException("getAdpInfo was interrupted internally.");
        } catch (ExecutionException e2) {
            throw new AuthenticationException(e2.getCause());
        } catch (TimeoutException e3) {
            throw new AuthenticationException(e3);
        }
    }
}
