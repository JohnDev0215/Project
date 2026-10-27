package com.amazon.music.authentication.mapr5;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.OAuthInfo;
import com.amazon.music.authentication.TokenKeyProvider;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ActorOAuthProviderMAPR5 {
    private final String accountId;
    private final String actorId;
    private final Context context;
    private final String packageName;
    private final TokenKeyProvider tokenKeyProvider;
    private final TokenManagement tokenManagement;

    public ActorOAuthProviderMAPR5(Context context, String str) {
        this(context, new MAPAccountManager(context), new TokenManagement(context), new TokenKeyProvider(), str);
    }

    public ActorOAuthProviderMAPR5(Context context, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, TokenKeyProvider tokenKeyProvider, String str) {
        Validate.notNull(context, "context can't be null", new Object[0]);
        Validate.notEmpty(context.getPackageName(), "packageName can't be empty", new Object[0]);
        Validate.notNull(mAPAccountManager, "accountManager can't be null", new Object[0]);
        Validate.notNull(tokenManagement, "tokenManagement can't be null", new Object[0]);
        Validate.notNull(tokenKeyProvider, "tokenKeyProvider can't be null", new Object[0]);
        Validate.notNull(str, "actorId can't be null", new Object[0]);
        this.packageName = context.getPackageName();
        this.context = context;
        String account = mAPAccountManager.getAccount();
        this.accountId = account;
        this.actorId = str;
        Validate.notNull(account, "user hasn't signed in", new Object[0]);
        this.tokenManagement = tokenManagement;
        this.tokenKeyProvider = tokenKeyProvider;
    }

    public OAuthInfo getOAuthInfo() throws AuthenticationException {
        return new OAuthInfo(fetchValue(this.tokenKeyProvider.getActorAccessTokenKeyForPackage(this.packageName)));
    }

    private String fetchValue(String str) throws AuthenticationException {
        try {
            String string = this.tokenManagement.getTokenForActor(this.context, this.accountId, this.actorId, str, null, null, null).get(90L, TimeUnit.SECONDS).getString("value_key");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            throw new AuthenticationException("failed to fetch " + str);
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
