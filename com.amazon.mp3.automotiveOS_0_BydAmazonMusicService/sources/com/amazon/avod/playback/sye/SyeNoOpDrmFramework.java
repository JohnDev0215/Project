package com.amazon.avod.playback.sye;

import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyeNoOpDrmFramework.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J6\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\"\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0016\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001eH\u0016J\"\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010!\u001a\u00020\u0004H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016¨\u0006#"}, d2 = {"Lcom/amazon/avod/playback/sye/SyeNoOpDrmFramework;", "Lcom/amazon/avod/media/playback/support/DrmFramework;", "()V", "areDrmClientCallsEnabled", "", "disableDrmClientCalls", "", "enableDrmClientCalls", "generateLicenseChallenge", "", "headerAsBase64", "", "drmScheme", "Lcom/amazon/avod/playback/drm/DrmScheme;", "isImmediate", "getCurrentDrmScheme", "getDrmSecurityLevel", "Lcom/amazon/avod/playback/drm/DrmSecurityLevel;", "getRights", "", "", "uri", "offlineKeyId", "getSupportedDrmSchemes", "", "initialize", "processLicenseResponse", "response", "removeAllRights", "offlineKeyIdSet", "", "removeRights", "keyIdUri", "shutdownSession", "supportsChallengeReplay", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SyeNoOpDrmFramework implements DrmFramework {
    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean areDrmClientCallsEnabled() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void disableDrmClientCalls() {
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void enableDrmClientCalls() {
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public byte[] generateLicenseChallenge(String headerAsBase64, DrmScheme drmScheme, boolean isImmediate) {
        Intrinsics.checkNotNullParameter(headerAsBase64, "headerAsBase64");
        Intrinsics.checkNotNullParameter(drmScheme, "drmScheme");
        return new byte[0];
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework, com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public String processLicenseResponse(String response, DrmScheme drmScheme, boolean isImmediate) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(drmScheme, "drmScheme");
        return null;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeAllRights(Set<String> offlineKeyIdSet) {
        Intrinsics.checkNotNullParameter(offlineKeyIdSet, "offlineKeyIdSet");
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeRights(String keyIdUri, String offlineKeyId, DrmScheme drmScheme) {
        Intrinsics.checkNotNullParameter(keyIdUri, "keyIdUri");
        Intrinsics.checkNotNullParameter(drmScheme, "drmScheme");
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean shutdownSession() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean supportsChallengeReplay() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public Map<String, Object> getRights(String uri, String offlineKeyId, DrmScheme drmScheme, boolean isImmediate) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(drmScheme, "drmScheme");
        return new LinkedHashMap();
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public DrmScheme getCurrentDrmScheme() {
        return DrmScheme.NONE;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public DrmSecurityLevel getDrmSecurityLevel() {
        return DrmSecurityLevel.NOT_PROVISIONED;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public List<DrmScheme> getSupportedDrmSchemes() {
        return new ArrayList();
    }
}
