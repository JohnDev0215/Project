package com.amazon.mp3.view.stage.overlay;

import androidx.fragment.app.Fragment;
import com.amazon.mp3.view.stage.overlay.OverlayViewType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayContainer;", "", "overlayViewType", "Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;", "(Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;)V", "<set-?>", "Landroidx/fragment/app/Fragment;", "overlayFragment", "getOverlayFragment", "()Landroidx/fragment/app/Fragment;", "getOverlayViewType", "()Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OverlayContainer {
    private Fragment overlayFragment;
    private final OverlayViewType overlayViewType;

    public OverlayContainer(OverlayViewType overlayViewType) {
        Fragment fragment;
        Intrinsics.checkNotNullParameter(overlayViewType, "overlayViewType");
        this.overlayViewType = overlayViewType;
        if (overlayViewType instanceof OverlayViewType.ViewOverlay) {
            OverlayFragment overlayFragment = new OverlayFragment();
            overlayFragment.initWith(((OverlayViewType.ViewOverlay) getOverlayViewType()).getView(), ((OverlayViewType.ViewOverlay) getOverlayViewType()).getOverlayPosition());
            fragment = overlayFragment;
        } else if (overlayViewType instanceof OverlayViewType.FragmentOverlay) {
            fragment = ((OverlayViewType.FragmentOverlay) overlayViewType).getFragment();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        this.overlayFragment = fragment;
    }

    public final OverlayViewType getOverlayViewType() {
        return this.overlayViewType;
    }

    public final Fragment getOverlayFragment() {
        return this.overlayFragment;
    }
}
