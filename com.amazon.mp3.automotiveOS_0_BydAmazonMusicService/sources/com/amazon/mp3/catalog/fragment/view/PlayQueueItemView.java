package com.amazon.mp3.catalog.fragment.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.recyclerview.DraggableItemView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.TextRowItemView;
import com.amazon.music.views.library.views.VisualRowItemView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueItemView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0018\u0010\u0015\u001a\u00020\u00132\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u0013J\u0006\u0010\u001c\u001a\u00020\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/view/PlayQueueItemView;", "Landroid/widget/FrameLayout;", "Lcom/amazon/music/views/library/recyclerview/DraggableItemView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "headerRowItemView", "Lcom/amazon/music/views/library/views/TextRowItemView;", "getHeaderRowItemView", "()Lcom/amazon/music/views/library/views/TextRowItemView;", "visualRowItemView", "Lcom/amazon/music/views/library/views/VisualRowItemView;", "getVisualRowItemView", "()Lcom/amazon/music/views/library/views/VisualRowItemView;", "isDraggingEnabled", "", "setEnabled", "", "enabled", "setOnDraggedListener", "onDragged", "Lkotlin/Function0;", "setupChildItemView", "childView", "Landroid/view/View;", "showTextRowItemView", "showVisualRowItemView", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueItemView extends FrameLayout implements DraggableItemView {
    private final TextRowItemView headerRowItemView;
    private final VisualRowItemView visualRowItemView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayQueueItemView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        TextRowItemView textRowItemView = new TextRowItemView(context, styleSheet);
        this.headerRowItemView = textRowItemView;
        VisualRowItemView visualRowItemView = new VisualRowItemView(context, styleSheet);
        this.visualRowItemView = visualRowItemView;
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        setupChildItemView(textRowItemView);
        setupChildItemView(visualRowItemView);
    }

    public final TextRowItemView getHeaderRowItemView() {
        return this.headerRowItemView;
    }

    public final VisualRowItemView getVisualRowItemView() {
        return this.visualRowItemView;
    }

    private final void setupChildItemView(View childView) {
        childView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        childView.setVisibility(8);
        addView(childView);
    }

    public final void showTextRowItemView() {
        this.headerRowItemView.setVisibility(0);
        this.visualRowItemView.setVisibility(8);
    }

    public final void showVisualRowItemView() {
        this.visualRowItemView.setVisibility(0);
        this.headerRowItemView.setVisibility(8);
    }

    @Override // com.amazon.music.views.library.recyclerview.DraggableItemView
    public boolean isDraggingEnabled() {
        return this.visualRowItemView.getVisibility() == 0 && this.visualRowItemView.isDraggingEnabled();
    }

    @Override // com.amazon.music.views.library.recyclerview.DraggableItemView
    public void setOnDraggedListener(Function0<Unit> onDragged) {
        this.visualRowItemView.setOnDraggedListener(onDragged);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        this.visualRowItemView.setEnabled(enabled);
        this.headerRowItemView.setEnabled(enabled);
        super.setEnabled(enabled);
    }
}
