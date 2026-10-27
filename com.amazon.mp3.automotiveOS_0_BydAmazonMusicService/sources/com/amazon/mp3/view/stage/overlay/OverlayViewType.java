package com.amazon.mp3.view.stage.overlay;

import android.view.View;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayViewType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;", "", "overlayType", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "(Lcom/amazon/mp3/view/stage/overlay/OverlayType;)V", "getOverlayType", "()Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "FragmentOverlay", "ViewOverlay", "Lcom/amazon/mp3/view/stage/overlay/OverlayViewType$ViewOverlay;", "Lcom/amazon/mp3/view/stage/overlay/OverlayViewType$FragmentOverlay;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class OverlayViewType {
    private final OverlayType overlayType;

    public /* synthetic */ OverlayViewType(OverlayType overlayType, DefaultConstructorMarker defaultConstructorMarker) {
        this(overlayType);
    }

    private OverlayViewType(OverlayType overlayType) {
        this.overlayType = overlayType;
    }

    public final OverlayType getOverlayType() {
        return this.overlayType;
    }

    /* JADX INFO: compiled from: OverlayViewType.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayViewType$ViewOverlay;", "Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;", "view", "Landroid/view/View;", "overlayPosition", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "overlayType", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "(Landroid/view/View;Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;Lcom/amazon/mp3/view/stage/overlay/OverlayType;)V", "getOverlayPosition", "()Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "getView", "()Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewOverlay extends OverlayViewType {
        private final OverlayPosition overlayPosition;
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewOverlay(View view, OverlayPosition overlayPosition, OverlayType overlayType) {
            super(overlayType, null);
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(overlayPosition, "overlayPosition");
            Intrinsics.checkNotNullParameter(overlayType, "overlayType");
            this.view = view;
            this.overlayPosition = overlayPosition;
        }

        public final OverlayPosition getOverlayPosition() {
            return this.overlayPosition;
        }

        public final View getView() {
            return this.view;
        }
    }

    /* JADX INFO: compiled from: OverlayViewType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayViewType$FragmentOverlay;", "Lcom/amazon/mp3/view/stage/overlay/OverlayViewType;", "fragment", "Landroidx/fragment/app/Fragment;", "overlayType", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/mp3/view/stage/overlay/OverlayType;)V", "getFragment", "()Landroidx/fragment/app/Fragment;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class FragmentOverlay extends OverlayViewType {
        private final Fragment fragment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FragmentOverlay(Fragment fragment, OverlayType overlayType) {
            super(overlayType, null);
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(overlayType, "overlayType");
            this.fragment = fragment;
        }

        public final Fragment getFragment() {
            return this.fragment;
        }
    }
}
