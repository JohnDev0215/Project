package com.amazon.music.share.menu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.amazon.music.share.R$dimen;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$id;
import com.amazon.music.share.R$layout;
import com.amazon.music.share.menu.data.LyricsShareCard;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareCardView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010)\u001a\u00020(\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b*\u0010+J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J'\u0010\u000b\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u000e\u001a\u00020\rH\u0002J)\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0014\u0010\u0016\u001a\u00020\u0004*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0002J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006,"}, d2 = {"Lcom/amazon/music/share/menu/ui/LyricsShareCardView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/share/menu/data/LyricsShareCard;", "card", "", "bindBackground", "", "", "lyricsLines", "", "fontColor", "bindLyrics", "(Ljava/util/List;Ljava/lang/Integer;)V", "Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;", "getMainViewLayoutParams", "Landroid/widget/TextView;", "lyricsLineView", "lyricsLine", "bindLyricsLineView", "(Landroid/widget/TextView;Ljava/lang/String;Ljava/lang/Integer;)V", "Landroid/view/View;", "margin", "applyTopMargin", "bindData", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "mainView", "Landroid/view/View;", "lyricsLine1", "Landroid/widget/TextView;", "lyricsLine2", "Landroid/widget/ImageView;", "cardBackground", "Landroid/widget/ImageView;", "Landroidx/cardview/widget/CardView;", "cardView", "Landroidx/cardview/widget/CardView;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class LyricsShareCardView extends ConstraintLayout {
    private ImageView cardBackground;
    private CardView cardView;
    private TextView lyricsLine1;
    private TextView lyricsLine2;
    private View mainView;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsShareCardView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        View viewInflate = View.inflate(context, R$layout.lyrics_share_sheet_share_card, this);
        viewInflate.setLayoutParams(getMainViewLayoutParams());
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, R.layou…wLayoutParams()\n        }");
        this.mainView = viewInflate;
        View viewFindViewById = viewInflate.findViewById(R$id.lyrics_line1);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "mainView.findViewById(R.id.lyrics_line1)");
        this.lyricsLine1 = (TextView) viewFindViewById;
        View viewFindViewById2 = this.mainView.findViewById(R$id.lyrics_line2);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "mainView.findViewById(R.id.lyrics_line2)");
        this.lyricsLine2 = (TextView) viewFindViewById2;
        View viewFindViewById3 = this.mainView.findViewById(R$id.card_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "mainView.findViewById(R.id.card_background)");
        this.cardBackground = (ImageView) viewFindViewById3;
        View viewFindViewById4 = this.mainView.findViewById(R$id.card_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "mainView.findViewById(R.id.card_view)");
        this.cardView = (CardView) viewFindViewById4;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final void bindData(LyricsShareCard card) {
        Intrinsics.checkNotNullParameter(card, "card");
        bindBackground(card);
        bindLyrics(card.getLyrics(), card.getFontColor());
    }

    private final void bindBackground(LyricsShareCard card) {
        if (card.getLyricsShareCardBackground().getBackgroundBitmap() != null) {
            Bitmap backgroundBitmap = card.getLyricsShareCardBackground().getBackgroundBitmap();
            if (backgroundBitmap != null) {
                this.cardBackground.setImageBitmap(backgroundBitmap);
            }
            if (card.getLyricsShareCardBackground().getIsBlurAlbumArtBackground()) {
                this.cardBackground.setForeground(ResourcesCompat.getDrawable(getResources(), R$drawable.lyrics_share_card_blur_album_art_background, null));
                return;
            }
            return;
        }
        GradientStyle gradientStyle = card.getLyricsShareCardBackground().getGradientStyle();
        if (gradientStyle == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, CollectionsKt___CollectionsKt.toIntArray(gradientStyle.getColors()));
        gradientDrawable.setCornerRadius(getResources().getDimension(R$dimen.lyrics_share_card_corner_radius));
        this.cardBackground.setImageDrawable(gradientDrawable);
    }

    private final void bindLyrics(List<String> lyricsLines, Integer fontColor) {
        bindLyricsLineView(this.lyricsLine1, lyricsLines.get(0), fontColor);
        String str = (String) CollectionsKt___CollectionsKt.getOrNull(lyricsLines, 1);
        if (str == null) {
            return;
        }
        applyTopMargin(this.lyricsLine2, (int) getResources().getDimension(R$dimen.lyrics_card_lyrics_margin));
        bindLyricsLineView(this.lyricsLine2, str, fontColor);
    }

    private final ConstraintLayout.LayoutParams getMainViewLayoutParams() {
        float f = ResourcesCompat.getFloat(getContext().getResources(), R$dimen.lyrics_share_card_dimen_ratio);
        int dimension = (int) getContext().getResources().getDimension(R$dimen.lyrics_share_card_width);
        return new ConstraintLayout.LayoutParams(dimension, (int) (dimension * f));
    }

    private final void bindLyricsLineView(TextView lyricsLineView, String lyricsLine, Integer fontColor) {
        FontSize fontSize;
        FontStyle fontStyle;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.LYRICS_XRAY)) != null) {
            FontUtil.INSTANCE.applyFontStyle(lyricsLineView, fontStyle);
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 != null && (fontSize = styleSheet2.getFontSize(FontSizeKey.BODY_3)) != null) {
            FontUtil.INSTANCE.applyFontSize(lyricsLineView, fontSize);
        }
        lyricsLineView.setText(lyricsLine);
        lyricsLineView.setTextAlignment(4);
        lyricsLineView.setMaxLines(Integer.MAX_VALUE);
        lyricsLineView.setEllipsize(TextUtils.TruncateAt.END);
        lyricsLineView.setSingleLine(false);
        if (fontColor != null) {
            lyricsLineView.setTextColor(fontColor.intValue());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            lyricsLineView.setBreakStrategy(1);
        }
    }

    private final void applyTopMargin(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.topMargin = i;
        view.setLayoutParams(marginLayoutParams);
    }
}
