package com.amazon.avod.media.playback.support;

import android.content.Context;
import com.amazon.avod.media.framework.MediaSystemSharedPrefs;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RendererSchemeController {
    private AutoEvalCanaryPolicy mCanaryPolicy;
    private boolean mIsAutoSchemeResolutionEnabled;
    private MediaSystemSharedPrefs mMediaSystemSharedPrefs;
    private ImmutableList<RendererScheme> mOrderedRendererSchemes;
    private final Object mMutex = new Object();
    private Set<RendererSchemeType> mHardwareAcceleratedRendererSchemes = new HashSet();
    private Set<RendererSchemeType> mBlockedRendererSchemes = new HashSet();

    public void initialize(@Nonnull ImmutableList<RendererScheme> immutableList, @Nonnull MediaSystemSharedPrefs mediaSystemSharedPrefs, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig, @Nonnull AutoEvalCanaryPolicy autoEvalCanaryPolicy, @Nonnull Context context) {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mOrderedRendererSchemes == null, "Initialize() should only be called once.");
            this.mOrderedRendererSchemes = (ImmutableList) Preconditions.checkNotNull(immutableList, "rendererSchemeList");
            Preconditions.checkState(immutableList.size() >= 1, "rendererSchemeList must contain one or more schemes");
            this.mMediaSystemSharedPrefs = (MediaSystemSharedPrefs) Preconditions.checkNotNull(mediaSystemSharedPrefs, "mediaSystemSharedPrefs");
            this.mIsAutoSchemeResolutionEnabled = ((RendererSchemeResolverConfig) Preconditions.checkNotNull(rendererSchemeResolverConfig, "rendererSchemeResolverConfig")).isAutoSchemeResolutionEnabled();
            this.mCanaryPolicy = (AutoEvalCanaryPolicy) Preconditions.checkNotNull(autoEvalCanaryPolicy, "autoEvalCanaryPolicy");
            UnmodifiableIterator<RendererScheme> it = this.mOrderedRendererSchemes.iterator();
            while (it.hasNext()) {
                RendererSchemeType schemeType = it.next().getSchemeType();
                if (schemeType.isHardwareAccelerated()) {
                    this.mHardwareAcceleratedRendererSchemes.add(schemeType);
                }
                if ("FAIL".equals(this.mMediaSystemSharedPrefs.readFromPrefs(schemeType.name()))) {
                    DLog.warnf("%s renderer scheme is blocked", schemeType);
                    this.mBlockedRendererSchemes.add(schemeType);
                }
            }
            checkAndApplyProfileOverride();
        }
    }

    @Nonnull
    public RendererScheme getRendererScheme(@Nullable RendererSchemeType rendererSchemeType, @Nullable DrmScheme drmScheme) {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mOrderedRendererSchemes != null, "Cannot get getRendererScheme() until initialize() completes.");
            if (this.mOrderedRendererSchemes.size() == 1) {
                return this.mOrderedRendererSchemes.get(0);
            }
            if (rendererSchemeType == null) {
                UnmodifiableIterator<RendererScheme> it = this.mOrderedRendererSchemes.iterator();
                while (it.hasNext()) {
                    RendererScheme next = it.next();
                    if (!this.mBlockedRendererSchemes.contains(next.getSchemeType())) {
                        return next;
                    }
                }
            }
            UnmodifiableIterator<RendererScheme> it2 = this.mOrderedRendererSchemes.iterator();
            while (it2.hasNext()) {
                RendererScheme next2 = it2.next();
                if (next2.getSchemeType() == rendererSchemeType && (drmScheme == null || next2.getDrmFramework().getSupportedDrmSchemes().contains(drmScheme))) {
                    return next2;
                }
            }
            if (drmScheme != null) {
                UnmodifiableIterator<RendererScheme> it3 = this.mOrderedRendererSchemes.iterator();
                while (it3.hasNext()) {
                    RendererScheme next3 = it3.next();
                    if (next3.getDrmFramework().getSupportedDrmSchemes().contains(drmScheme)) {
                        return next3;
                    }
                }
            }
            DLog.warnf("No renderer scheme match found for rendererScheme: %s drmScheme: %s, using %s", rendererSchemeType, drmScheme, this.mOrderedRendererSchemes.get(0).getSchemeType());
            return this.mOrderedRendererSchemes.get(0);
        }
    }

    @Nonnull
    public ImmutableList<RendererScheme> getOrderedRendererSchemes() {
        Preconditions.checkState(this.mOrderedRendererSchemes != null, "Cannot call getOrderedRendererSchemes() until initialize() completes.");
        return this.mOrderedRendererSchemes;
    }

    @Nonnull
    public Set<RendererSchemeType> getHardwareAcceleratedRendererSchemes() {
        Preconditions.checkState(this.mOrderedRendererSchemes != null, "Cannot call getOrderedRendererSchemes() until initialize() completes.");
        return this.mHardwareAcceleratedRendererSchemes;
    }

    public boolean changeRendererSchemeStatus(@Nullable MediaException mediaException, @Nonnull RendererSchemeType rendererSchemeType, boolean z) {
        synchronized (this.mMutex) {
            boolean z2 = true;
            Preconditions.checkState(this.mOrderedRendererSchemes != null, "Cannot call changeRendererSchemeStatus() until initialize() completes.");
            Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
            if (rendererSchemeType == RendererSchemeType.VISUAL_ON) {
                return false;
            }
            if (!(z != this.mBlockedRendererSchemes.contains(rendererSchemeType))) {
                return false;
            }
            String str = z ? "FAIL" : "PASS";
            Object[] objArr = new Object[3];
            objArr[0] = rendererSchemeType;
            objArr[1] = str;
            objArr[2] = mediaException != null ? mediaException : "USER_TOGGLE";
            DLog.warnf("Renderer scheme status changed %s -> %s, trigger: %s", objArr);
            this.mMediaSystemSharedPrefs.writeToPrefs(rendererSchemeType.name(), str);
            if (z) {
                this.mBlockedRendererSchemes.add(rendererSchemeType);
            } else {
                this.mBlockedRendererSchemes.remove(rendererSchemeType);
            }
            checkAndApplyProfileOverride();
            if (rendererSchemeType != RendererSchemeType.MEDIACODEC_MEDIADRM && rendererSchemeType != RendererSchemeType.OMXIL) {
                z2 = false;
            }
            return z2;
        }
    }

    public boolean isHardwareAcceleratedSchemeAvailable(boolean z) {
        synchronized (this.mMutex) {
            for (RendererSchemeType rendererSchemeType : this.mHardwareAcceleratedRendererSchemes) {
                if (z || !this.mBlockedRendererSchemes.contains(rendererSchemeType)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void checkAndApplyProfileOverride() {
        if (this.mIsAutoSchemeResolutionEnabled) {
            UnmodifiableIterator<RendererScheme> it = this.mOrderedRendererSchemes.iterator();
            while (it.hasNext()) {
                RendererScheme next = it.next();
                ThirdPartyProfileName supportedProfile = next.getAvailabilityStatus().getSupportedProfile();
                if (!this.mBlockedRendererSchemes.contains(next.getSchemeType()) && supportedProfile != null) {
                    DLog.logf("Applying automatically resolved profile %s from %s renderer scheme,", supportedProfile, next.getSchemeType());
                    ThirdPartyConfigurationProfile.getInstance().overrideDeviceProfile(supportedProfile);
                    return;
                }
            }
        }
    }

    public boolean notifyAutoEvalCanaryPlaybackFailureEvent(@Nonnull MediaException mediaException) {
        return notifyAutoEvalCanaryPlaybackEventInternal((MediaException) Preconditions.checkNotNull(mediaException, "failureCause"));
    }

    public boolean notifyAutoEvalCanaryPlaybackSuccessEvent() {
        return notifyAutoEvalCanaryPlaybackEventInternal(null);
    }

    private boolean notifyAutoEvalCanaryPlaybackEventInternal(@Nullable MediaException mediaException) {
        if (!shouldHandleCanaryEvent(mediaException)) {
            return false;
        }
        this.mCanaryPolicy.recordEvent(mediaException);
        if (!this.mCanaryPolicy.shouldExecuteAction(mediaException)) {
            return false;
        }
        boolean z = mediaException == null;
        Object[] objArr = new Object[3];
        objArr[0] = z ? "PASS" : "FAIL";
        objArr[1] = mediaException;
        objArr[2] = this.mCanaryPolicy.getPolicyNote();
        DLog.warnf("AutoEvalCanary: %s, event: %s, policy: %s", objArr);
        setAutoEvalCanaryResult(z);
        return true;
    }

    private boolean shouldHandleCanaryEvent(@Nullable MediaException mediaException) {
        return this.mIsAutoSchemeResolutionEnabled && isHardwareAcceleratedSchemeAvailable(false) && this.mCanaryPolicy.shouldHandle(mediaException) && !hasAutoEvalCanaryPhaseElapsed();
    }

    private void setAutoEvalCanaryResult(boolean z) {
        this.mMediaSystemSharedPrefs.writeToPrefs("AutoEvalCanaryEvaluation", z ? "PASS" : "FAIL");
    }

    private boolean hasAutoEvalCanaryPhaseElapsed() {
        String fromPrefs = this.mMediaSystemSharedPrefs.readFromPrefs("AutoEvalCanaryEvaluation");
        return "PASS".equals(fromPrefs) || "FAIL".equals(fromPrefs);
    }

    public boolean isAutoSchemeResolutionEnabled() {
        Preconditions.checkState(this.mOrderedRendererSchemes != null, "Cannot call isAutoSchemeResolutionEnabled() until initialize() completes.");
        return this.mIsAutoSchemeResolutionEnabled;
    }
}
