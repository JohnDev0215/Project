package com.amazon.music.share.menu.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.share.R$id;
import com.amazon.music.share.R$layout;
import com.amazon.music.share.R$string;
import com.amazon.music.share.menu.data.LyricsShareCard;
import com.amazon.music.share.menu.ui.LyricsShareViewAdapter;
import com.amazon.music.share.menu.util.LyricsSharingSheetItemDecoration;
import com.amazon.music.share.menu.viewmodel.LyricsShareViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000U\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0011\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\"\u001a\u00020!\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b#\u0010$J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0006\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0007R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0018\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010 \u001a\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006%"}, d2 = {"Lcom/amazon/music/share/menu/ui/LyricsShareView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "initTitleTextView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initLyricsShareItems", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel;", "lyricsShareViewModel", "Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel;", "Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter;", "lyricsShareViewAdapter", "Lcom/amazon/music/share/menu/ui/LyricsShareViewAdapter;", "com/amazon/music/share/menu/ui/LyricsShareView$onLyricsShareCardClickListener$1", "onLyricsShareCardClickListener", "Lcom/amazon/music/share/menu/ui/LyricsShareView$onLyricsShareCardClickListener$1;", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getLyricsShareView", "()Landroid/view/View;", "lyricsShareView", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class LyricsShareView extends ConstraintLayout {
    private final LifecycleOwner lifecycleOwner;
    private LyricsShareViewAdapter lyricsShareViewAdapter;
    private final LyricsShareViewModel lyricsShareViewModel;
    private final LyricsShareView$onLyricsShareCardClickListener$1 onLyricsShareCardClickListener;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazon.music.share.menu.ui.LyricsShareView$onLyricsShareCardClickListener$1] */
    public LyricsShareView(Context context, StyleSheet styleSheet, LifecycleOwner lifecycleOwner, LyricsShareViewModel lyricsShareViewModel) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        this.lifecycleOwner = lifecycleOwner;
        this.lyricsShareViewModel = lyricsShareViewModel;
        this.onLyricsShareCardClickListener = new LyricsShareViewAdapter.OnItemClickListener() { // from class: com.amazon.music.share.menu.ui.LyricsShareView$onLyricsShareCardClickListener$1
            @Override // com.amazon.music.share.menu.ui.LyricsShareViewAdapter.OnItemClickListener
            public void onItemClicked(int cardPosition, LyricsShareCard lyricsShareCard) {
                Intrinsics.checkNotNullParameter(lyricsShareCard, "lyricsShareCard");
                LyricsShareViewModel lyricsShareViewModel2 = this.this$0.lyricsShareViewModel;
                if (lyricsShareViewModel2 == null) {
                    return;
                }
                lyricsShareViewModel2.onLyricsShareCardClicked(cardPosition, lyricsShareCard);
            }
        };
        View.inflate(context, R$layout.lyrics_share_sheet, this);
        getLyricsShareView().setVisibility(8);
        initTitleTextView();
        initLyricsShareItems(styleSheet);
    }

    private final View getLyricsShareView() {
        return View.inflate(getContext(), R$layout.lyrics_share_sheet, this);
    }

    private final TextView getTitleTextView() {
        View viewFindViewById = getLyricsShareView().findViewById(R$id.title_text_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "lyricsShareView.findViewById(R.id.title_text_view)");
        return (TextView) viewFindViewById;
    }

    private final RecyclerView getRecyclerView() {
        View viewFindViewById = getLyricsShareView().findViewById(R$id.recycler_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "lyricsShareView.findViewById(R.id.recycler_view)");
        return (RecyclerView) viewFindViewById;
    }

    private final void initTitleTextView() {
        FontStyle fontStyle;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) != null) {
            UIExtensionsKt.applyFontStyle(getTitleTextView(), fontStyle);
        }
        getTitleTextView().setText(getResources().getString(R$string.dmusic_context_share_with_lyrics));
    }

    private final void initLyricsShareItems(final StyleSheet styleSheet) {
        LyricsShareViewModel lyricsShareViewModel;
        getRecyclerView().addItemDecoration(new LyricsSharingSheetItemDecoration());
        getRecyclerView().setHasFixedSize(true);
        if (this.lifecycleOwner == null || (lyricsShareViewModel = this.lyricsShareViewModel) == null) {
            return;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        MutableLiveData<List<LyricsShareCard>> lyricsShareCards = lyricsShareViewModel.getLyricsShareCards(context);
        if (lyricsShareCards == null) {
            return;
        }
        lyricsShareCards.observe(this.lifecycleOwner, new Observer() { // from class: com.amazon.music.share.menu.ui.LyricsShareView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsShareView.m2189initLyricsShareItems$lambda2$lambda1(this.f$0, styleSheet, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLyricsShareItems$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2189initLyricsShareItems$lambda2$lambda1(LyricsShareView this$0, StyleSheet styleSheet, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsShareView$onLyricsShareCardClickListener$1 lyricsShareView$onLyricsShareCardClickListener$1 = this$0.onLyricsShareCardClickListener;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.lyricsShareViewAdapter = new LyricsShareViewAdapter(lyricsShareView$onLyricsShareCardClickListener$1, it, styleSheet);
        RecyclerView recyclerView = this$0.getRecyclerView();
        LyricsShareViewAdapter lyricsShareViewAdapter = this$0.lyricsShareViewAdapter;
        if (lyricsShareViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lyricsShareViewAdapter");
            lyricsShareViewAdapter = null;
        }
        recyclerView.setAdapter(lyricsShareViewAdapter);
        this$0.getLyricsShareView().setVisibility(0);
    }
}
