package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WaysToWatchView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/WaysToWatchView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "watchOnAmazonMusic", "Lcom/amazon/music/alps/liveevent/view/VerticalIconLinkItemView;", "getWatchOnAmazonMusic", "()Lcom/amazon/music/alps/liveevent/view/VerticalIconLinkItemView;", "watchOnAmazonMusic$delegate", "Lkotlin/Lazy;", "watchOnPrimeVideos", "getWatchOnPrimeVideos", "watchOnPrimeVideos$delegate", "waysToWatchTitle", "Landroid/widget/TextView;", "getWaysToWatchTitle", "()Landroid/widget/TextView;", "waysToWatchTitle$delegate", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WaysToWatchView extends ConstraintLayout {

    /* JADX INFO: renamed from: watchOnAmazonMusic$delegate, reason: from kotlin metadata */
    private final Lazy watchOnAmazonMusic;

    /* JADX INFO: renamed from: watchOnPrimeVideos$delegate, reason: from kotlin metadata */
    private final Lazy watchOnPrimeVideos;

    /* JADX INFO: renamed from: waysToWatchTitle$delegate, reason: from kotlin metadata */
    private final Lazy waysToWatchTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaysToWatchView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.watchOnPrimeVideos = LazyKt__LazyJVMKt.lazy(new Function0<VerticalIconLinkItemView>() { // from class: com.amazon.music.alps.liveevent.view.WaysToWatchView$watchOnPrimeVideos$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VerticalIconLinkItemView invoke() {
                return (VerticalIconLinkItemView) this.this$0.findViewById(R$id.watch_on_prime_video);
            }
        });
        this.watchOnAmazonMusic = LazyKt__LazyJVMKt.lazy(new Function0<VerticalIconLinkItemView>() { // from class: com.amazon.music.alps.liveevent.view.WaysToWatchView$watchOnAmazonMusic$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VerticalIconLinkItemView invoke() {
                return (VerticalIconLinkItemView) this.this$0.findViewById(R$id.watch_on_amazon_music);
            }
        });
        this.waysToWatchTitle = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.WaysToWatchView$waysToWatchTitle$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.ways_to_watch_title);
            }
        });
        View.inflate(context, R$layout.ways_to_watch_view, this);
    }

    public final VerticalIconLinkItemView getWatchOnPrimeVideos() {
        Object value = this.watchOnPrimeVideos.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-watchOnPrimeVideos>(...)");
        return (VerticalIconLinkItemView) value;
    }

    public final VerticalIconLinkItemView getWatchOnAmazonMusic() {
        Object value = this.watchOnAmazonMusic.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-watchOnAmazonMusic>(...)");
        return (VerticalIconLinkItemView) value;
    }

    public final TextView getWaysToWatchTitle() {
        Object value = this.waysToWatchTitle.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-waysToWatchTitle>(...)");
        return (TextView) value;
    }
}
