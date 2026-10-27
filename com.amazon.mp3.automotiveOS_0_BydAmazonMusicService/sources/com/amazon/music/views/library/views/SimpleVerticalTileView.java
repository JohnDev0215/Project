package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleVerticalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 )2\u00020\u0001:\u0001)B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u000eH\u0016JE\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00162\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lcom/amazon/music/views/library/views/SimpleVerticalTileView;", "Lcom/amazon/music/views/library/views/ScopedConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "artworkFrameView", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "getArtworkFrameView", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView$delegate", "Lkotlin/Lazy;", "isContentEnabled", "", "nanoSpace", "", "Ljava/lang/Integer;", "smallSpace", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "titleTextView", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView$delegate", "initViewMargins", "", "initViewStyling", Constants.ENABLE_DISABLE, "setEnabled", "setTextMargins", "textView", "left", "top", "right", "bottom", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setTitle", "title", "", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SimpleVerticalTileView extends ScopedConstraintLayout {

    /* JADX INFO: renamed from: artworkFrameView$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameView;
    private final boolean isContentEnabled;
    private final Integer nanoSpace;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleVerticalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameView = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.SimpleVerticalTileView$artworkFrameView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.artwork_frame);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SimpleVerticalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.isContentEnabled = true;
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        View.inflate(context, R$layout.dmmviewlibrary_simple_vertical_tile_layout, this);
        initViewStyling();
        initViewMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameView() {
        Object value = this.artworkFrameView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameView>(...)");
        return (ArtworkFrameView) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY_T2_BIG_SHARPGROTESK);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
    }

    private final void initViewMargins() {
        setTextMargins(getTitleTextView(), 0, this.smallSpace, 0, this.nanoSpace);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.3f);
            getArtworkFrameView().setPlayState(null);
        }
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.isContentEnabled;
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
