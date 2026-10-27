package com.amazon.commsnetworking.auth;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.CookieProvider;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.util.TextUtils;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import rx.Observable;

/* JADX INFO: loaded from: classes2.dex */
public class MAPAuthenticationProvider implements AuthenticationProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger("NComms", MAPAuthenticationProvider.class);
    private AlexaCommsCoreIdentityService commsCoreIdentityService;
    private CommsCoreIdentity commsIdentity;
    private AlexaCommsCoreRemoteConfigurationService configurationService;
    private IdentityService identityService;
    private AlexaCommsCoreMetricsService metricsService;
    private String source;

    public MAPAuthenticationProvider(@NonNull String str, @NonNull AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, @NonNull AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.source = str;
        this.commsCoreIdentityService = alexaCommsCoreIdentityService;
        this.configurationService = alexaCommsCoreRemoteConfigurationService;
        this.metricsService = alexaCommsCoreMetricsService;
    }

    private String getCookie(String str) {
        Observable<String[]> cookies;
        String[] strArr = new String[0];
        String string = this.configurationService.getRemoteConfiguration("ACMS.Auth.Host", ".amazon.com").toString();
        IdentityService identityService = this.identityService;
        if (identityService != null) {
            cookies = identityService.getCookiesFromDirectedId(str, string);
        } else {
            AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.commsCoreIdentityService;
            cookies = (alexaCommsCoreIdentityService == null || !(alexaCommsCoreIdentityService instanceof CookieProvider)) ? null : ((CookieProvider) alexaCommsCoreIdentityService).getCookies(str, string);
        }
        if (cookies != null) {
            try {
                strArr = cookies.toBlocking().toFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                LOG.e("[" + this.source + "] Failed to get cookie", e);
                HashMap map = new HashMap();
                map.put("errorSource", e);
                this.metricsService.recordOccurrence("comms.net.auth.map.fail", this.source, true, map);
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            String[] strArrSplit = TextUtils.split(str2, ";");
            if (strArrSplit.length >= 1 && !TextUtils.isEmpty(strArrSplit[0])) {
                sb.append(strArrSplit[0]);
                sb.append(";");
            } else {
                LOG.w("[" + this.source + "] malformed cookie: " + str2);
            }
        }
        String string2 = sb.toString();
        if (validateCookie(string2)) {
            this.metricsService.recordOccurrence("comms.net.auth.map.success", this.source, true, new HashMap());
        } else {
            CommsLogger commsLogger = LOG;
            commsLogger.e("[" + this.source + "] invalid cookie: " + commsLogger.sensitive(string2));
            HashMap map2 = new HashMap();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("invalid cookie: ");
            sb2.append(commsLogger.sensitive(string2));
            map2.put("errorSource", sb2.toString());
            this.metricsService.recordOccurrence("comms.net.auth.map.fail", this.source, true, map2);
        }
        return string2;
    }

    private boolean validateCookie(@NonNull String str) {
        boolean z = false;
        boolean z2 = false;
        for (String str2 : str.split(";")) {
            if (str2.startsWith("ubid-main") || str2.startsWith("ubid-acb") || str2.startsWith("ubid-tacb")) {
                z = true;
            }
            if (str2.startsWith("at-main") || str2.startsWith("at-acb") || str2.startsWith("at-tacb")) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private String getCsrfCookieValue(String str) {
        for (String str2 : str.split(";")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length >= 2) {
                String strTrim = strArrSplit[0].trim();
                String strTrim2 = strArrSplit[1].trim();
                if ("csrf".equals(strTrim)) {
                    return strTrim2;
                }
            }
        }
        return null;
    }

    @Override // com.amazon.commsnetworking.auth.AuthenticationProvider
    public void authenticateRequest(@NonNull INetworkRequest iNetworkRequest) {
        AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.commsCoreIdentityService;
        if (alexaCommsCoreIdentityService != null) {
            this.commsIdentity = alexaCommsCoreIdentityService.getIdentity();
        }
        String cookie = getCookie(this.commsIdentity.getDirectedId());
        String csrfCookieValue = getCsrfCookieValue(cookie);
        if (csrfCookieValue == null) {
            LOG.d("[" + this.source + "] csrf token not found, regenerating token");
            csrfCookieValue = Integer.toString(new SecureRandom().nextInt());
            cookie = cookie + "csrf=" + csrfCookieValue;
        }
        LOG.d("[" + this.source + "] cookie = " + cookie + ", csrf = " + csrfCookieValue);
        iNetworkRequest.withHeader("csrf", csrfCookieValue).withHeader("Cookie", cookie);
    }
}
