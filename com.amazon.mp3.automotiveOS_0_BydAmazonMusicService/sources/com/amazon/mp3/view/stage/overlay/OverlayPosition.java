package com.amazon.mp3.view.stage.overlay;

import androidx.constraintlayout.widget.ConstraintSet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: OverlayPosition.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "", "()V", "Custom", "Top", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition$Top;", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition$Custom;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class OverlayPosition {
    public /* synthetic */ OverlayPosition(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: OverlayPosition.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayPosition$Top;", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Top extends OverlayPosition {
        public static final Top INSTANCE = new Top();

        private Top() {
            super(null);
        }
    }

    private OverlayPosition() {
    }

    /* JADX INFO: compiled from: OverlayPosition.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayPosition$Custom;", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "Landroidx/constraintlayout/widget/ConstraintSet;", "constraintSet", "Landroidx/constraintlayout/widget/ConstraintSet;", "getConstraintSet", "()Landroidx/constraintlayout/widget/ConstraintSet;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Custom extends OverlayPosition {
        private final ConstraintSet constraintSet;

        public final ConstraintSet getConstraintSet() {
            return this.constraintSet;
        }
    }
}
