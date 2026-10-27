package com.amazon.music.share.menu.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.share.menu.data.LyricsShareCard;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareViewAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0016\u0017B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter$ViewHolder;", "onItemClickListener", "Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter$OnItemClickListener;", "lyricsShareCards", "", "Lcom/amazon/music/share/menu/data/LyricsShareCard;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter$OnItemClickListener;Ljava/util/List;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "OnItemClickListener", "ViewHolder", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsShareViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<LyricsShareCard> lyricsShareCards;
    private final OnItemClickListener onItemClickListener;
    private StyleSheet styleSheet;

    /* JADX INFO: compiled from: LyricsShareViewAdapter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter$OnItemClickListener;", "", "onItemClicked", "", "cardPosition", "", "lyricsShareCard", "Lcom/amazon/music/share/menu/data/LyricsShareCard;", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnItemClickListener {
        void onItemClicked(int cardPosition, LyricsShareCard lyricsShareCard);
    }

    public LyricsShareViewAdapter(OnItemClickListener onItemClickListener, List<LyricsShareCard> lyricsShareCards, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        Intrinsics.checkNotNullParameter(lyricsShareCards, "lyricsShareCards");
        ArrayList arrayList = new ArrayList();
        this.lyricsShareCards = arrayList;
        this.onItemClickListener = onItemClickListener;
        arrayList.addAll(lyricsShareCards);
        this.styleSheet = styleSheet;
    }

    /* JADX INFO: compiled from: LyricsShareViewAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "lyricsShareCardView", "Lcom/amazon/music/share/menu/ui/LyricsShareCardView;", "(Lcom/amazon/music/share/menu/ui/LyricsShareCardView;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(LyricsShareCardView lyricsShareCardView) {
            super(lyricsShareCardView);
            Intrinsics.checkNotNullParameter(lyricsShareCardView, "lyricsShareCardView");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return new ViewHolder(new LyricsShareCardView(context, this.styleSheet));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        LyricsShareCardView lyricsShareCardView = (LyricsShareCardView) holder.itemView;
        if (lyricsShareCardView != null) {
            lyricsShareCardView.bindData(this.lyricsShareCards.get(position));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.share.menu.ui.LyricsShareViewAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsShareViewAdapter.m2191onBindViewHolder$lambda0(this.f$0, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m2191onBindViewHolder$lambda0(LyricsShareViewAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onItemClickListener.onItemClicked(i, this$0.lyricsShareCards.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lyricsShareCards.size();
    }
}
