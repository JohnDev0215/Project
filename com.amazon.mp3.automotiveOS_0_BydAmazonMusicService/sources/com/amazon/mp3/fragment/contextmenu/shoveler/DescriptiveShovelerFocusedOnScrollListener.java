package com.amazon.mp3.fragment.contextmenu.shoveler;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerSnapHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveShovelerFocusedOnScrollListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedOnScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "", "onSnapPositionChange", "", "dx", "dy", "onScrolled", "Landroidx/recyclerview/widget/SnapHelper;", "snapHelper", "Landroidx/recyclerview/widget/SnapHelper;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;", "onSnapPositionChangeListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;", "getOnSnapPositionChangeListener", "()Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;", "setOnSnapPositionChangeListener", "(Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;)V", "snapPosition", "I", "<init>", "(Landroidx/recyclerview/widget/SnapHelper;Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveShovelerFocusedOnScrollListener extends RecyclerView.OnScrollListener {
    private DescriptiveShovelerSnapHelper.OnSnapPositionChangeListener onSnapPositionChangeListener;
    private final SnapHelper snapHelper;
    private int snapPosition;

    public DescriptiveShovelerFocusedOnScrollListener(SnapHelper snapHelper, DescriptiveShovelerSnapHelper.OnSnapPositionChangeListener onSnapPositionChangeListener) {
        Intrinsics.checkNotNullParameter(snapHelper, "snapHelper");
        this.snapHelper = snapHelper;
        this.onSnapPositionChangeListener = onSnapPositionChangeListener;
        this.snapPosition = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        onSnapPositionChange(recyclerView);
    }

    private final void onSnapPositionChange(RecyclerView recyclerView) {
        int snapPosition = ShovelerExtensionFunctionsKt.getSnapPosition(this.snapHelper, recyclerView);
        int i = this.snapPosition;
        if (i != snapPosition) {
            DescriptiveShovelerSnapHelper.OnSnapPositionChangeListener onSnapPositionChangeListener = this.onSnapPositionChangeListener;
            if (onSnapPositionChangeListener != null) {
                onSnapPositionChangeListener.onSnapPositionChange(i, snapPosition);
            }
            this.snapPosition = snapPosition;
        }
    }
}
