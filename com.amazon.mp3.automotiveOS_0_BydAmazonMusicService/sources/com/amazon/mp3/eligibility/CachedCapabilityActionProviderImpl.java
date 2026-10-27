package com.amazon.mp3.eligibility;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityId;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CachedCapabilityActionProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\fH\u0014J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00100\n\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "Lcom/amazon/music/platform/providers/impl/CapabilityActionProviderImpl;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationEligibility", "Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "capabilityMappings", "", "", "Lcom/amazon/music/platform/Outcome;", "addAppStateIfEmpty", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "eligibilities", "canPerform", "T", "capabilityActionType", "eligibilitiesProvider", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "resetCacheForNewRequest", "", "setCapabilityResult", "identifier", "capabilityAction", "result", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CachedCapabilityActionProviderImpl extends CapabilityActionProviderImpl {
    private AppStateEligibility applicationEligibility;
    private final Map<String, Map<String, Outcome<?>>> capabilityMappings;
    private final Context context;

    public CachedCapabilityActionProviderImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CachedCapabilityActionProviderImpl(Context context) {
        super(null, 1, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.capabilityMappings = new LinkedHashMap();
        this.applicationEligibility = new ApplicationEligiblityProvider(context, 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0).getEligibility();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CachedCapabilityActionProviderImpl(Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            context = AmazonApplication.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        }
        this(context);
    }

    @Override // com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl, com.amazon.music.platform.providers.CapabilityActionProvider
    public <T> Outcome<T> canPerform(String capabilityActionType, EntityEligibilitiesProvider eligibilitiesProvider) {
        EntityId id;
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        EntityItemIdentifier entityItemIdentifier = eligibilitiesProvider.getEntityItem().getEntityItemIdentifier();
        String identifier = (entityItemIdentifier == null || (id = entityItemIdentifier.getId()) == null) ? null : id.getIdentifier();
        if (identifier != null) {
            Map<String, Outcome<?>> map = this.capabilityMappings.get(identifier);
            Outcome<T> outcome = map != null ? (Outcome) map.get(capabilityActionType) : null;
            if (outcome != null) {
                return outcome;
            }
        }
        Outcome<T> outcomeCanPerform = super.canPerform(capabilityActionType, eligibilitiesProvider);
        if (identifier != null) {
            setCapabilityResult(identifier, capabilityActionType, outcomeCanPerform);
        }
        return outcomeCanPerform;
    }

    @Override // com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl
    protected Map<String, Eligibility> addAppStateIfEmpty(Map<String, ? extends Eligibility> eligibilities) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Map<String, Eligibility> mutableMap = MapsKt__MapsKt.toMutableMap(eligibilities);
        mutableMap.put("APPLICATION_STATE", this.applicationEligibility);
        return mutableMap;
    }

    public final void resetCacheForNewRequest() {
        this.applicationEligibility = new ApplicationEligiblityProvider(this.context, null, 2, 0 == true ? 1 : 0).getEligibility();
        this.capabilityMappings.clear();
    }

    private final void setCapabilityResult(String identifier, String capabilityAction, Outcome<?> result) {
        Map<String, Outcome<?>> linkedHashMap = this.capabilityMappings.get(identifier);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
        }
        linkedHashMap.put(capabilityAction, result);
        this.capabilityMappings.put(identifier, linkedHashMap);
    }
}
