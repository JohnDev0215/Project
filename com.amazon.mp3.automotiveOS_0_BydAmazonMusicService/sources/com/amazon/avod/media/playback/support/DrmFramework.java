package com.amazon.avod.media.playback.support;

import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DrmFramework extends MediaComponent {
    boolean areDrmClientCallsEnabled();

    void disableDrmClientCalls() throws DrmLicensingException;

    void enableDrmClientCalls();

    @Nonnull
    byte[] generateLicenseChallenge(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException;

    @Nonnull
    DrmScheme getCurrentDrmScheme();

    @Nonnull
    DrmSecurityLevel getDrmSecurityLevel();

    @Nullable
    Map<String, Object> getRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException;

    @Nonnull
    List<DrmScheme> getSupportedDrmSchemes();

    @Override // com.amazon.avod.media.framework.MediaComponent
    /* synthetic */ void initialize();

    default boolean isMultiSessionDrmSupported() {
        return false;
    }

    @Nullable
    String processLicenseResponse(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException;

    void removeAllRights(@Nonnull Set<String> set) throws DrmLicensingException;

    void removeRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme) throws DrmLicensingException;

    boolean shutdownSession();

    boolean supportsChallengeReplay();
}
