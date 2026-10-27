package com.amazon.mp3.view.stage.overlay;

import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.view.stage.overlay.OverlaySelectionType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayTypeStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\n¢\u0006\u0004\b&\u0010'J\u0012\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\t\u0010\u0006\u001a\u00020\u0002HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R*\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b!\u0010#\"\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "", "", "queueId", "", "reset", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "overlaySelectionType", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "getOverlaySelectionType", "()Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "setOverlaySelectionType", "(Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;)V", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "currentOverlay", "Landroidx/lifecycle/MutableLiveData;", "getCurrentOverlay", "()Landroidx/lifecycle/MutableLiveData;", "setCurrentOverlay", "(Landroidx/lifecycle/MutableLiveData;)V", "currentQueueId", "Ljava/lang/String;", "getCurrentQueueId", "()Ljava/lang/String;", "setCurrentQueueId", "(Ljava/lang/String;)V", "isLyricsSharingFromShareSheet", "Z", "()Z", "setLyricsSharingFromShareSheet", "(Z)V", "<init>", "(Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;Landroidx/lifecycle/MutableLiveData;Ljava/lang/String;Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class OverlayTypeStateProvider {
    private MutableLiveData<OverlayType> currentOverlay;
    private String currentQueueId;
    private boolean isLyricsSharingFromShareSheet;
    private OverlaySelectionType overlaySelectionType;

    public OverlayTypeStateProvider() {
        this(null, null, null, false, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OverlayTypeStateProvider)) {
            return false;
        }
        OverlayTypeStateProvider overlayTypeStateProvider = (OverlayTypeStateProvider) other;
        return Intrinsics.areEqual(this.overlaySelectionType, overlayTypeStateProvider.overlaySelectionType) && Intrinsics.areEqual(this.currentOverlay, overlayTypeStateProvider.currentOverlay) && Intrinsics.areEqual(this.currentQueueId, overlayTypeStateProvider.currentQueueId) && this.isLyricsSharingFromShareSheet == overlayTypeStateProvider.isLyricsSharingFromShareSheet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int hashCode() {
        int iHashCode = ((this.overlaySelectionType.hashCode() * 31) + this.currentOverlay.hashCode()) * 31;
        String str = this.currentQueueId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.isLyricsSharingFromShareSheet;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "OverlayTypeStateProvider(overlaySelectionType=" + this.overlaySelectionType + ", currentOverlay=" + this.currentOverlay + ", currentQueueId=" + ((Object) this.currentQueueId) + ", isLyricsSharingFromShareSheet=" + this.isLyricsSharingFromShareSheet + ')';
    }

    public OverlayTypeStateProvider(OverlaySelectionType overlaySelectionType, MutableLiveData<OverlayType> currentOverlay, String str, boolean z) {
        Intrinsics.checkNotNullParameter(overlaySelectionType, "overlaySelectionType");
        Intrinsics.checkNotNullParameter(currentOverlay, "currentOverlay");
        this.overlaySelectionType = overlaySelectionType;
        this.currentOverlay = currentOverlay;
        this.currentQueueId = str;
        this.isLyricsSharingFromShareSheet = z;
    }

    public /* synthetic */ OverlayTypeStateProvider(OverlaySelectionType overlaySelectionType, MutableLiveData mutableLiveData, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? OverlaySelectionType.Automatic.INSTANCE : overlaySelectionType, (i & 2) != 0 ? new MutableLiveData(OverlayType.ALBUM_ART) : mutableLiveData, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? true : z);
    }

    public final MutableLiveData<OverlayType> getCurrentOverlay() {
        return this.currentOverlay;
    }

    public final OverlaySelectionType getOverlaySelectionType() {
        return this.overlaySelectionType;
    }

    /* JADX INFO: renamed from: isLyricsSharingFromShareSheet, reason: from getter */
    public final boolean getIsLyricsSharingFromShareSheet() {
        return this.isLyricsSharingFromShareSheet;
    }

    public final void setLyricsSharingFromShareSheet(boolean z) {
        this.isLyricsSharingFromShareSheet = z;
    }

    public final void setOverlaySelectionType(OverlaySelectionType overlaySelectionType) {
        Intrinsics.checkNotNullParameter(overlaySelectionType, "<set-?>");
        this.overlaySelectionType = overlaySelectionType;
    }

    public final void reset(String queueId) {
        if (Intrinsics.areEqual(queueId, this.currentQueueId)) {
            return;
        }
        this.currentOverlay.postValue(null);
        this.currentQueueId = queueId;
        this.overlaySelectionType = OverlaySelectionType.Automatic.INSTANCE;
    }
}
