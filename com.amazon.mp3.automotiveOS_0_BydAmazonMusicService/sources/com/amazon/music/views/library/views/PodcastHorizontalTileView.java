package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.squareup.picasso.Picasso;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastHorizontalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0002J\u000e\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+J\u0015\u0010,\u001a\u00020'2\b\u0010-\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020'2\u0006\u00100\u001a\u000201J\u0010\u00102\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u000101JE\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u00112\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010:J\u0010\u0010;\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u000101J\u000e\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u00020?J\u000e\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020\u000eR\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0016\u0010\nR\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001c\u0010\u0013R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\f\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b$\u0010\u0013¨\u0006B"}, d2 = {"Lcom/amazon/music/views/library/views/PodcastHorizontalTileView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "artworkFrameView", "Landroid/widget/ImageView;", "getArtworkFrameView", "()Landroid/widget/ImageView;", "artworkFrameView$delegate", "Lkotlin/Lazy;", "baseSpace", "", "Ljava/lang/Integer;", "numberTextView", "Landroid/widget/TextView;", "getNumberTextView", "()Landroid/widget/TextView;", "numberTextView$delegate", "playButton", "getPlayButton", "playButton$delegate", "smallSpace", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "subtitleTextView", "getSubtitleTextView", "subtitleTextView$delegate", "textContainer", "Landroid/widget/LinearLayout;", "getTextContainer", "()Landroid/widget/LinearLayout;", "textContainer$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "initViewMargins", "", "initViewStyling", "setImageOnClickListener", "clickListener", "Landroid/view/View$OnClickListener;", "setNumber", "number", "(Ljava/lang/Integer;)V", "setPodcastImage", "imageUrl", "", "setSubTitle", "subTitle", "setTextMargins", "textView", "left", "top", "right", "bottom", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setTitle", "title", "showPlayOverlay", "show", "", "updateViewWidth", "width", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastHorizontalTileView extends ConstraintLayout {

    /* JADX INFO: renamed from: artworkFrameView$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameView;
    private final Integer baseSpace;

    /* JADX INFO: renamed from: numberTextView$delegate, reason: from kotlin metadata */
    private final Lazy numberTextView;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PodcastHorizontalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$artworkFrameView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.podcast_row_item_image);
            }
        });
        this.numberTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$numberTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.podcast_row_item_index);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.podcast_row_item_text);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.podcast_row_item_primary_text);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.podcast_row_item_secondary_text);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastHorizontalTileView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.podcast_row_item_play_button);
            }
        });
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        View.inflate(context, R$layout.dmmviewlibrary_podcast_horizontal_tile_layout, this);
        initViewStyling();
        initViewMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final ImageView getArtworkFrameView() {
        Object value = this.artworkFrameView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameView>(...)");
        return (ImageView) value;
    }

    private final TextView getNumberTextView() {
        Object value = this.numberTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-numberTextView>(...)");
        return (TextView) value;
    }

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final ImageView getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (ImageView) value;
    }

    public final void setNumber(Integer number) {
        Unit unit;
        if (number == null) {
            unit = null;
        } else {
            int iIntValue = number.intValue();
            getNumberTextView().setText(String.valueOf(iIntValue));
            getNumberTextView().setVisibility(iIntValue > 0 ? 0 : 8);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getNumberTextView().setVisibility(8);
        }
    }

    public final void setTitle(String title) {
        Unit unit;
        if (title == null) {
            unit = null;
        } else {
            getTitleTextView().setText(title);
            getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getTitleTextView().setVisibility(8);
        }
    }

    public final void setSubTitle(String subTitle) {
        Unit unit;
        if (subTitle == null) {
            unit = null;
        } else {
            getSubtitleTextView().setText(subTitle);
            getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subTitle) ? 0 : 8);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getSubtitleTextView().setVisibility(8);
        }
    }

    public final void showPlayOverlay(boolean show) {
        getPlayButton().setVisibility(show ? 0 : 8);
    }

    public final void setPodcastImage(String imageUrl) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        int dimension = (int) getContext().getResources().getDimension(R$dimen.artwork_default_dimen);
        Picasso.get().load(imageUrl).resize(dimension, dimension).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.spacer_6))).centerCrop().into(getArtworkFrameView());
    }

    public final void setImageOnClickListener(View.OnClickListener clickListener) {
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        getArtworkFrameView().setOnClickListener(clickListener);
    }

    public final void updateViewWidth(int width) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, width, -2, Boolean.TRUE, 0, 0, 0, 0);
        Integer num = this.smallSpace;
        if (num == null) {
            return;
        }
        int iIntValue = num.intValue();
        setPadding(0, iIntValue, 0, iIntValue);
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.INDEX);
        if (fontStyle3 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getNumberTextView(), fontStyle3);
    }

    private final void initViewMargins() {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTextContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : this.baseSpace, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : this.smallSpace, (240 & 128) != 0 ? null : 0);
        setTextMargins(getNumberTextView(), 0, 0, this.baseSpace, 0);
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
