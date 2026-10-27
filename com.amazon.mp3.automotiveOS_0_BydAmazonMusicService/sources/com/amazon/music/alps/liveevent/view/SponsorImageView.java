package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.alps.R$dimen;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SponsorImageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u00020\n8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/SponsorImageView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "sponsorImageContainer", "Landroid/widget/FrameLayout;", "getSponsorImageContainer$annotations", "()V", "getSponsorImageContainer", "()Landroid/widget/FrameLayout;", "sponsorImageContainer$delegate", "Lkotlin/Lazy;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setSponsorImageAdapter", "", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "setSponsorImageContainerContentView", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SponsorImageView extends LinearLayout {
    private RecyclerView recyclerView;

    /* JADX INFO: renamed from: sponsorImageContainer$delegate, reason: from kotlin metadata */
    private final Lazy sponsorImageContainer;
    private final StyleSheet styleSheet;

    @VisibleForTesting
    public static /* synthetic */ void getSponsorImageContainer$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SponsorImageView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.sponsorImageContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.alps.liveevent.view.SponsorImageView$sponsorImageContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.sponsor_images);
            }
        });
        View.inflate(context, R$layout.live_event_sponsor_image_layout, this);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final FrameLayout getSponsorImageContainer() {
        Object value = this.sponsorImageContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sponsorImageContainer>(...)");
        return (FrameLayout) value;
    }

    public final void setSponsorImageAdapter(UniversalAdapter adapter) {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
        getSponsorImageContainer().setVisibility(adapter != null ? 0 : 8);
    }

    public final void setSponsorImageContainerContentView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getSponsorImageContainer().addView(recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration((int) getContext().getResources().getDimension(R$dimen.spacer_milli)));
    }
}
