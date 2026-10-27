package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.ExperienceEligibility;
import com.amazon.music.platform.data.eligibility.ExperienceEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowableKt;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amazon.music.platform.providers.LoggingProviderKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlayableEntityCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J8\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H$J\u001c\u0010\u001a\u001a\u00020\u00182\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013H\u0004J\u001c\u0010\u001b\u001a\u00020\u00182\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013H\u0004J6\u0010\u001c\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "T", "", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "getBuildInfoProvider", "()Lcom/amazon/music/platform/providers/BuildInfoProvider;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "getLoggingProvider", "()Lcom/amazon/music/platform/providers/LoggingProvider;", "canPerform", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "shouldHandleError", "", "canPerformInternal", "isContentUnavailable", "shouldSupportInterludes", "validate", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class PlayableEntityCapability<T> {
    protected abstract Outcome<T> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem);

    public abstract String getActionName();

    private final LoggingProvider getLoggingProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LoggingProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof LoggingProvider)) {
            interfaceProvider = null;
        }
        return (LoggingProvider) interfaceProvider;
    }

    private final BuildInfoProvider getBuildInfoProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(BuildInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof BuildInfoProvider)) {
            interfaceProvider = null;
        }
        return (BuildInfoProvider) interfaceProvider;
    }

    private final Outcome<T> validate(Map<String, ? extends Eligibility> eligibilities, boolean shouldHandleError, EntityItem entityItem) {
        Outcome.Failure failureDoValidation = CapabilityExtensionsKt.doValidation(this, eligibilities, shouldHandleError, entityItem);
        if (failureDoValidation != null) {
            return failureDoValidation;
        }
        return null;
    }

    public static /* synthetic */ Outcome canPerform$default(PlayableEntityCapability playableEntityCapability, Map map, EntityItem entityItem, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: canPerform");
        }
        if ((i & 2) != 0) {
            entityItem = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return playableEntityCapability.canPerform(map, entityItem, z);
    }

    @JvmOverloads
    public final Outcome<T> canPerform(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem, boolean shouldHandleError) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        BuildInfoProvider buildInfoProvider = getBuildInfoProvider();
        if (buildInfoProvider != null && buildInfoProvider.getIsDebug()) {
            String tag = LoggingProviderKt.getTag(this);
            LoggingProvider loggingProvider = getLoggingProvider();
            if (loggingProvider != null) {
                loggingProvider.logDebug(tag, "ContentEligibility = " + eligibilities.get("CONTENT"));
                loggingProvider.logDebug(tag, "PlaymodeEligibility = " + eligibilities.get("PLAYMODES"));
                loggingProvider.logDebug(tag, "AppStateEligibility = " + eligibilities.get("APPLICATION_STATE"));
                loggingProvider.logDebug(tag, "ExperienceEligibility = " + eligibilities.get("EXPERIENCE"));
            }
        }
        Outcome<T> outcomeValidate = validate(eligibilities, shouldHandleError, entityItem);
        if (outcomeValidate == null) {
            outcomeValidate = canPerformInternal(eligibilities, entityItem);
        }
        if (shouldHandleError) {
            Outcome.Failure failure = outcomeValidate instanceof Outcome.Failure ? (Outcome.Failure) outcomeValidate : null;
            Throwable cause = failure != null ? failure.getCause() : null;
            EligibilityThrowable eligibilityThrowable = cause instanceof EligibilityThrowable ? (EligibilityThrowable) cause : null;
            if (eligibilityThrowable != null) {
                EligibilityThrowableKt.handleError(eligibilityThrowable, entityItem);
            }
        }
        return outcomeValidate;
    }

    protected final boolean isContentUnavailable(Map<String, ? extends Eligibility> eligibilities) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("PLAYMODES");
        PlaymodeEligibility playmodeEligibility = eligibility instanceof PlaymodeEligibility ? (PlaymodeEligibility) eligibility : null;
        Eligibility eligibility2 = eligibilities.get("CONTENT");
        ContentEligibility contentEligibility = eligibility2 instanceof ContentEligibility ? (ContentEligibility) eligibility2 : null;
        if (playmodeEligibility == null) {
            if (contentEligibility != null && contentEligibility.has(ContentEligibilityQualifier.IsUnavailable.INSTANCE)) {
                return true;
            }
        }
        return false;
    }

    protected final boolean shouldSupportInterludes(Map<String, ? extends Eligibility> eligibilities) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("EXPERIENCE");
        ExperienceEligibility experienceEligibility = eligibility instanceof ExperienceEligibility ? (ExperienceEligibility) eligibility : null;
        return experienceEligibility != null && experienceEligibility.has(ExperienceEligibilityQualifier.IsInterludeSupportRequired.INSTANCE);
    }
}
