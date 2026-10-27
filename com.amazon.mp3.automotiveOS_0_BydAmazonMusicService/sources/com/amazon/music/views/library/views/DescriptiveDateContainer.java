package com.amazon.music.views.library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.utils.DurationStringUtil;
import com.amazon.music.views.library.views.TableRowItemView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveDateContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b%\u0010&J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0006J\u0017\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004R\u001b\u0010\u0015\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u001a\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0019R*\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006'"}, d2 = {"Lcom/amazon/music/views/library/views/DescriptiveDateContainer;", "Landroid/widget/FrameLayout;", "", "enabled", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "setEnabled", "cleanViews", "", "popularity", "showPopularityBar", "(Ljava/lang/Double;)V", "", "date", "showDate", "Landroid/widget/TextView;", "dateTextView$delegate", "Lkotlin/Lazy;", "getDateTextView", "()Landroid/widget/TextView;", "dateTextView", "Lcom/amazon/music/views/library/views/TrackPopularityBar;", "popularityBar$delegate", "getPopularityBar", "()Lcom/amazon/music/views/library/views/TrackPopularityBar;", "popularityBar", "value", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveDateContainer extends FrameLayout {

    /* JADX INFO: renamed from: dateTextView$delegate, reason: from kotlin metadata */
    private final Lazy dateTextView;
    private boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: popularityBar$delegate, reason: from kotlin metadata */
    private final Lazy popularityBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptiveDateContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.dateTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptiveDateContainer$dateTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.date);
            }
        });
        this.popularityBar = LazyKt__LazyJVMKt.lazy(new Function0<TrackPopularityBar>() { // from class: com.amazon.music.views.library.views.DescriptiveDateContainer$popularityBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TrackPopularityBar invoke() {
                return (TrackPopularityBar) this.this$0.findViewById(R$id.popularity_bar);
            }
        });
        View.inflate(getContext(), R$layout.dmmviewlibrary_descriptive_date_container, this);
    }

    private final TextView getDateTextView() {
        Object value = this.dateTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-dateTextView>(...)");
        return (TextView) value;
    }

    private final TrackPopularityBar getPopularityBar() {
        Object value = this.popularityBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-popularityBar>(...)");
        return (TrackPopularityBar) value;
    }

    public final void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
        getPopularityBar().setSonicRushEnabled(z);
    }

    public final void setEnabled(boolean enabled, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        super.setEnabled(enabled);
        getDateTextView().setEnabled(enabled);
        if (!enabled) {
            Float alpha = styleSheet.getAlpha(AlphaKey.GLASS_3);
            if (alpha == null) {
                return;
            }
            getPopularityBar().setAlpha(alpha.floatValue());
            return;
        }
        getPopularityBar().setAlpha(1.0f);
    }

    public final void cleanViews() {
        getDateTextView().setVisibility(8);
        getPopularityBar().setVisibility(8);
    }

    public final void showPopularityBar(Double popularity) {
        if (popularity == null) {
            return;
        }
        getDateTextView().setVisibility(8);
        if (getPopularityBar().setPopularity(popularity.doubleValue()).show()) {
            getPopularityBar().setVisibility(0);
        }
    }

    public final void showDate(String date, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        getPopularityBar().setVisibility(8);
        getDateTextView().setVisibility(0);
        getDateTextView().setText(DurationStringUtil.INSTANCE.getFormattedDate(date));
        TableRowItemView.Companion.applyFontStyle$default(TableRowItemView.INSTANCE, styleSheet, getDateTextView(), FontStyleKey.SECONDARY, null, 8, null);
    }
}
