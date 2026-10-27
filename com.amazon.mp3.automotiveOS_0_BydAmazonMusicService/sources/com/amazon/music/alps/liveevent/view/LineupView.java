package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineupView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010J\u0010\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eR!\u0010\u0007\u001a\u00020\b8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0011\u001a\u00020\b8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u000e\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\fR\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcom/amazon/music/alps/liveevent/view/LineupView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "lineupItemDetailContainer", "Landroid/widget/FrameLayout;", "getLineupItemDetailContainer$annotations", "()V", "getLineupItemDetailContainer", "()Landroid/widget/FrameLayout;", "lineupItemDetailContainer$delegate", "Lkotlin/Lazy;", "lineupItemDetailRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "lineupItemListContainer", "getLineupItemListContainer$annotations", "getLineupItemListContainer", "lineupItemListContainer$delegate", "lineupItemsRecyclerView", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setItemDetailContentView", "", "recyclerView", "setItemListContentView", "setLineupDetailListAdapter", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "setLineupItemsAdapter", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LineupView extends LinearLayout {

    /* JADX INFO: renamed from: lineupItemDetailContainer$delegate, reason: from kotlin metadata */
    private final Lazy lineupItemDetailContainer;
    private RecyclerView lineupItemDetailRecyclerView;

    /* JADX INFO: renamed from: lineupItemListContainer$delegate, reason: from kotlin metadata */
    private final Lazy lineupItemListContainer;
    private RecyclerView lineupItemsRecyclerView;
    private final StyleSheet styleSheet;

    @VisibleForTesting
    public static /* synthetic */ void getLineupItemDetailContainer$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getLineupItemListContainer$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineupView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.lineupItemListContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.alps.liveevent.view.LineupView$lineupItemListContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.lineup_items);
            }
        });
        this.lineupItemDetailContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.alps.liveevent.view.LineupView$lineupItemDetailContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.lineup_item_detail);
            }
        });
        View.inflate(context, R$layout.live_event_lineup_layout, this);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final FrameLayout getLineupItemListContainer() {
        Object value = this.lineupItemListContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-lineupItemListContainer>(...)");
        return (FrameLayout) value;
    }

    public final FrameLayout getLineupItemDetailContainer() {
        Object value = this.lineupItemDetailContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-lineupItemDetailContainer>(...)");
        return (FrameLayout) value;
    }

    public final void setLineupItemsAdapter(UniversalAdapter adapter) {
        RecyclerView recyclerView = this.lineupItemsRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
        getLineupItemListContainer().setVisibility(adapter != null ? 0 : 8);
    }

    public final void setLineupDetailListAdapter(UniversalAdapter adapter) {
        RecyclerView recyclerView = this.lineupItemDetailRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
        getLineupItemDetailContainer().setVisibility(adapter != null ? 0 : 8);
    }

    public final void setItemListContentView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getLineupItemListContainer().addView(recyclerView);
        this.lineupItemsRecyclerView = recyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(20));
    }

    public final void setItemDetailContentView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getLineupItemDetailContainer().addView(recyclerView);
        this.lineupItemDetailRecyclerView = recyclerView;
    }
}
