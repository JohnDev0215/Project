package com.amazon.music.authentication.mapr5;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.AuthenticationException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthInfoMAPR5 implements AuthInfo {
    private static final Logger LOG = LoggerFactory.getLogger(AuthInfoMAPR5.class.getSimpleName());
    private final Context applicationContext;
    private final AuthCookiesProviderMAPR5 authCookiesProviderMAPR5;
    private final MAPAccountManager mapAccountManager;

    public AuthInfoMAPR5(Context context, String str, boolean z) {
        this(context, new MAPAccountManager(context), new AuthCookiesProviderMAPR5(new MAPAccountManager(context), new TokenManagement(context), str, z));
    }

    public AuthInfoMAPR5(Context context, MAPAccountManager mAPAccountManager, AuthCookiesProviderMAPR5 authCookiesProviderMAPR5) {
        this.applicationContext = context.getApplicationContext();
        this.mapAccountManager = mAPAccountManager;
        this.authCookiesProviderMAPR5 = authCookiesProviderMAPR5;
    }

    @Override // com.amazon.music.authentication.AuthInfo
    public String find(@NonNull AuthDataType authDataType) throws AuthenticationException {
        Validate.notNull(authDataType, "AuthDataType cannnot be null", new Object[0]);
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$authentication$AuthDataType[authDataType.ordinal()];
        if (i == 1) {
            return getDirectedId();
        }
        if (i != 2) {
            return null;
        }
        if (this.authCookiesProviderMAPR5 != null) {
            if (getDirectedId() == null) {
                LOG.warn("User not signed in, returning null session Id");
                return null;
            }
            return getSessionId();
        }
        LOG.error("authCookiesProviderMAPR5 not initialized, returning null session Id");
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.music.authentication.mapr5.AuthInfoMAPR5$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$authentication$AuthDataType;

        static {
            int[] iArr = new int[AuthDataType.values().length];
            $SwitchMap$com$amazon$music$authentication$AuthDataType = iArr;
            try {
                iArr[AuthDataType.DIRECTED_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$authentication$AuthDataType[AuthDataType.SESSION_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private synchronized String getDirectedId() {
        return this.mapAccountManager.getAccount();
    }

    private synchronized String getSessionId() throws AuthenticationException {
        String string;
        string = this.applicationContext.getSharedPreferences("com.amazon.music.authentication.mapr5_AuthInfoMAPR5", 0).getString("session-id", "");
        if (string.isEmpty()) {
            Logger logger = LOG;
            logger.debug("No cached session ID. Returning session ID from MAP.");
            String strExtractSessionIdFromMAPCookies = extractSessionIdFromMAPCookies();
            if (strExtractSessionIdFromMAPCookies == null) {
                logger.error("Returned null session ID from MAP");
            } else {
                writeSessionIdToSharedPreferences(strExtractSessionIdFromMAPCookies);
            }
            string = strExtractSessionIdFromMAPCookies;
        }
        return string;
    }

    private String extractSessionIdFromMAPCookies() throws AuthenticationException {
        String[] cookies = this.authCookiesProviderMAPR5.getCookies();
        String str = null;
        if (cookies == null) {
            return null;
        }
        for (String str2 : cookies) {
            if (str2 != null && str2.startsWith("session-id")) {
                String[] strArrSplit = str2.split("\\s*;\\s*");
                int length = strArrSplit.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        String[] strArrSplit2 = strArrSplit[i].split("\\s*=\\s*");
                        if (strArrSplit2.length > 1 && "session-id".equalsIgnoreCase(strArrSplit2[0])) {
                            str = strArrSplit2[1];
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return str;
    }

    @Override // com.amazon.music.authentication.AuthInfo
    public synchronized void resetSessionId() {
        writeSessionIdToSharedPreferences("");
    }

    private void writeSessionIdToSharedPreferences(String str) {
        SharedPreferences.Editor editorEdit = this.applicationContext.getSharedPreferences("com.amazon.music.authentication.mapr5_AuthInfoMAPR5", 0).edit();
        editorEdit.putString("session-id", str);
        editorEdit.apply();
    }
}
