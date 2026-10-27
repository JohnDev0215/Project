package com.amazon.mp3.inappmessaging;

import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.net.UserAgent;
import com.amazon.mp3.store.html5.map.CookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.IAMCredentialsVendor;

/* JADX INFO: loaded from: classes3.dex */
public class IAMCredentialsVendorImpl implements IAMCredentialsVendor {
    private String TAG = IAMCredentialsVendorImpl.class.getSimpleName();
    private final AccountCredentialStorage mAccountCredentialStorage;
    private final AccountDetailUtil mAccountDetailUtil;
    private final ApplicationAttributes mApplicationAttributes;
    private final AuthInfo mAuthInfoMAPR5;
    private final CookiesManager mCookiesManager;

    public IAMCredentialsVendorImpl(final AccountCredentialStorage accountCredentialStorage, final AuthInfo authInfoMAPR5, final CookiesManager cookiesManager, final AccountDetailUtil accountDetailUtil, final ApplicationAttributes applicationAttributes) {
        this.mAccountCredentialStorage = accountCredentialStorage;
        this.mAuthInfoMAPR5 = authInfoMAPR5;
        this.mCookiesManager = cookiesManager;
        this.mAccountDetailUtil = accountDetailUtil;
        this.mApplicationAttributes = applicationAttributes;
    }

    @Override // com.amazon.music.inappmessaging.external.IAMCredentialsVendor
    public IAMCredentials getIamCredentials() {
        return new IAMCredentials(this.mAccountCredentialStorage.getDeviceId(), this.mAccountCredentialStorage.getDeviceType(), getDirectedId(), getSessionId(), AccountDetailUtil.getMusicTerritoryOfResidence(), this.mApplicationAttributes.getVersion(), new TokenProviderImpl(), UserAgent.getUserAgent());
    }

    private String getDirectedId() {
        try {
            return this.mAuthInfoMAPR5.find(AuthDataType.DIRECTED_ID);
        } catch (AuthenticationException e) {
            Log.error(this.TAG, "Unable to fetch directed ID from AuthInfo", e);
            return "";
        }
    }

    private String getSessionId() {
        try {
            return this.mAuthInfoMAPR5.find(AuthDataType.SESSION_ID);
        } catch (AuthenticationException e) {
            Log.error(this.TAG, "Unable to fetch directed ID from AuthInfo", e);
            return "";
        }
    }
}
