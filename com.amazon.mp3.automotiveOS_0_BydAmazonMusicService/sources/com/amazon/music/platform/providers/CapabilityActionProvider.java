package com.amazon.music.platform.providers;

import com.amazon.music.platform.Outcome;
import kotlin.Metadata;

/* JADX INFO: compiled from: CapabilityActionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H&J6\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "T", "", "capabilityActionType", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "eligibilitiesProvider", "Lcom/amazon/music/platform/Outcome;", "canPerform", "", "shouldHandleError", "useParentEligibility", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface CapabilityActionProvider extends InterfaceProvider {
    <T> Outcome<T> canPerform(String capabilityActionType, EntityEligibilitiesProvider eligibilitiesProvider);

    <T> Outcome<T> canPerform(String capabilityActionType, EntityEligibilitiesProvider eligibilitiesProvider, boolean shouldHandleError, boolean useParentEligibility);

    /* JADX INFO: compiled from: CapabilityActionProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Outcome canPerform$default(CapabilityActionProvider capabilityActionProvider, String str, EntityEligibilitiesProvider entityEligibilitiesProvider, boolean z, boolean z2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: canPerform");
            }
            if ((i & 8) != 0) {
                z2 = true;
            }
            return capabilityActionProvider.canPerform(str, entityEligibilitiesProvider, z, z2);
        }
    }
}
