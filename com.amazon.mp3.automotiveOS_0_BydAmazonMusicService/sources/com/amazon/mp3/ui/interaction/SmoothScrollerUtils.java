package com.amazon.mp3.ui.interaction;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.views.HotSpotPulse;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmoothScrollerUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ,\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/ui/interaction/SmoothScrollerUtils;", "", "Landroid/content/Context;", "context", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotspot", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "tooltip", "Landroidx/recyclerview/widget/LinearSmoothScroller;", "createHotspotSmoothScroller", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SmoothScrollerUtils {
    public static final SmoothScrollerUtils INSTANCE = new SmoothScrollerUtils();

    private SmoothScrollerUtils() {
    }

    public final LinearSmoothScroller createHotspotSmoothScroller(final Context context, final RecyclerView recyclerView, final HotSpotPulse hotspot, final HotSpotToolTipDialog tooltip) {
        Intrinsics.checkNotNullParameter(hotspot, "hotspot");
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        if (context == null || recyclerView == null) {
            return null;
        }
        return new LinearSmoothScroller(context, tooltip, hotspot, recyclerView) { // from class: com.amazon.mp3.ui.interaction.SmoothScrollerUtils$createHotspotSmoothScroller$smoothScroller$1
            final /* synthetic */ Context $context;
            final /* synthetic */ HotSpotPulse $hotspot;
            final /* synthetic */ RecyclerView $recyclerView;
            final /* synthetic */ HotSpotToolTipDialog $tooltip;
            private RecyclerView.OnScrollListener scrollListener;

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return -1;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
                this.$tooltip = tooltip;
                this.$hotspot = hotspot;
                this.$recyclerView = recyclerView;
                this.scrollListener = new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.ui.interaction.SmoothScrollerUtils$createHotspotSmoothScroller$smoothScroller$1$scrollListener$1
                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                        Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                        super.onScrollStateChanged(recyclerView2, newState);
                        if (isRunning()) {
                            return;
                        }
                        if (newState == 0) {
                            tooltip.setAnchorView(hotspot);
                            HotSpotToolTipDialog.toggleShowTextView$default(tooltip, true, false, 2, null);
                        }
                        recyclerView2.removeOnScrollListener(this);
                    }
                };
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
            protected void onStart() {
                super.onStart();
                this.$recyclerView.addOnScrollListener(this.scrollListener);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
            protected void onStop() {
                super.onStop();
                if (this.$recyclerView.getScrollState() == 0) {
                    this.$tooltip.setAnchorView(this.$hotspot);
                    HotSpotToolTipDialog.toggleShowTextView$default(this.$tooltip, true, false, 2, null);
                    this.$recyclerView.removeOnScrollListener(this.scrollListener);
                }
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                Intrinsics.checkNotNullParameter(displayMetrics, "displayMetrics");
                return 150.0f / displayMetrics.densityDpi;
            }
        };
    }
}
