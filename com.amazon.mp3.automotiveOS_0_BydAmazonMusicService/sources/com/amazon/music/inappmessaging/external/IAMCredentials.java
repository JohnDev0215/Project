package com.amazon.music.inappmessaging.external;

import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class IAMCredentials {
    private static final String PARAMS_USER_AGENT = "userAgent";
    private final String appVersion;
    private final String deviceId;
    private final String deviceType;
    private final String directedId;
    private final String musicTerritory;
    private final String sessionId;
    private final TokenProvider tokenProvider;
    private final String userAgent;

    public IAMCredentials(final String deviceId, final String deviceType, final String directedId, final String sessionId, final String musicTerritory, final String appVersion, final TokenProvider tokenProvider, final String userAgent) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.directedId = directedId;
        this.sessionId = sessionId;
        this.musicTerritory = musicTerritory;
        this.appVersion = appVersion;
        this.tokenProvider = tokenProvider;
        this.userAgent = userAgent;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public TokenProvider getTokenProvider() {
        return this.tokenProvider;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String toString() {
        TokenProvider tokenProvider = this.tokenProvider;
        return "IAMCredentials{deviceId='" + this.deviceId + "', deviceType='" + this.deviceType + "', directedId='" + this.directedId + "', sessionId='" + this.sessionId + "', musicTerritory='" + this.musicTerritory + "', appVersion='" + this.appVersion + "', token='" + (tokenProvider == null ? "" : tokenProvider.getToken()) + "', userAgent='" + this.userAgent + "'}";
    }

    public List<String> findMissingIAMCredentials() {
        ArrayList arrayList = new ArrayList();
        if (StringUtils.isBlank(getTokenProvider() == null ? "" : getTokenProvider().getToken())) {
            arrayList.add(Splash.AUTH_TOKEN);
        }
        if (StringUtils.isBlank(getDeviceId())) {
            arrayList.add(Splash.PARAMS_DEVICE_ID);
        }
        if (StringUtils.isBlank(getSessionId())) {
            arrayList.add(Splash.PARAMS_SESSION_ID);
        }
        if (StringUtils.isBlank(getMusicTerritory())) {
            arrayList.add(Splash.PARAMS_MUSIC_TERRITORY);
        }
        if (StringUtils.isBlank(getDeviceType())) {
            arrayList.add(Splash.PARAMS_DEVICE_TYPE);
        }
        if (StringUtils.isBlank(getDirectedId())) {
            arrayList.add("directedId");
        }
        if (StringUtils.isBlank(getAppVersion())) {
            arrayList.add(Splash.PARAMS_APP_VERSION);
        }
        if (StringUtils.isBlank(getUserAgent())) {
            arrayList.add(PARAMS_USER_AGENT);
        }
        return arrayList;
    }
}
