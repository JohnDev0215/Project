package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.helpers.RepeatingUiTimer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExclusiveCarouselView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/ExclusiveCarouselView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "markerDurationSeconds", "", "getMarkerDurationSeconds", "()I", "setMarkerDurationSeconds", "(I)V", NotificationCompat.CATEGORY_PROGRESS, "", "getProgress", "()F", "setProgress", "(F)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "timer", "Lcom/amazon/music/skyfire/ui/widgets/helpers/RepeatingUiTimer;", "flipPage", "", "startTimer", "stopTimer", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExclusiveCarouselView extends ConstraintLayout {
    private static final int TIMER_RESOLUTION_MS = 25;
    private int markerDurationSeconds;
    private float progress;

    @ViewFinder.AView
    public RecyclerView recyclerView;
    private final RepeatingUiTimer timer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExclusiveCarouselView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        RepeatingUiTimer repeatingUiTimer = new RepeatingUiTimer(25);
        this.timer = repeatingUiTimer;
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.exclusive_carousel_view), (Object) null, 1, (Object) null);
        repeatingUiTimer.setListener(new Function1<RepeatingUiTimer, Unit>() { // from class: com.amazon.music.skyfire.ui.widgets.views.ExclusiveCarouselView.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RepeatingUiTimer repeatingUiTimer2) {
                invoke2(repeatingUiTimer2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RepeatingUiTimer it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (ExclusiveCarouselView.this.getProgress() >= 1.0f) {
                    ExclusiveCarouselView.this.flipPage();
                    ExclusiveCarouselView.this.setProgress(0.0f);
                } else {
                    float markerDurationSeconds = ExclusiveCarouselView.this.getMarkerDurationSeconds() > 0 ? 1.0f / ((ExclusiveCarouselView.this.getMarkerDurationSeconds() * 1000) / 25) : 0.0f;
                    ExclusiveCarouselView exclusiveCarouselView = ExclusiveCarouselView.this;
                    exclusiveCarouselView.setProgress(exclusiveCarouselView.getProgress() + markerDurationSeconds);
                }
            }
        });
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        return null;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.recyclerView = recyclerView;
    }

    public final int getMarkerDurationSeconds() {
        return this.markerDurationSeconds;
    }

    public final void setMarkerDurationSeconds(int i) {
        this.markerDurationSeconds = i;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final void setProgress(float f) {
        this.progress = f;
    }

    public final void flipPage() {
        RecyclerView.Adapter adapter = getRecyclerView().getAdapter();
        if (adapter != null && adapter.getItemCount() >= 2) {
            RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            int iFindFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() + 1;
            if (iFindFirstVisibleItemPosition >= adapter.getItemCount()) {
                iFindFirstVisibleItemPosition = 0;
            }
            getRecyclerView().smoothScrollToPosition(iFindFirstVisibleItemPosition);
        }
    }

    public final void startTimer() {
        this.timer.start();
    }

    public final void stopTimer() {
        this.timer.stop();
    }
}
