package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$integer;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.PodcastTileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.firebase.perf.util.Constants;
import com.squareup.picasso.Picasso;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 >2\u00020\u0001:\u0001>B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200J\u0010\u00101\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u00102\u001a\u00020*H\u0002J\b\u00103\u001a\u00020*H\u0002J\u0010\u00104\u001a\u00020*2\u0006\u00105\u001a\u000206H\u0016JE\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u000e2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010=R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\f\u001a\u0004\b#\u0010\u0010R\u001b\u0010%\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\f\u001a\u0004\b&\u0010\u0010R\u000e\u0010(\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/amazon/music/views/library/views/PodcastTileView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "artworkFrameView", "Landroid/widget/ImageView;", "getArtworkFrameView", "()Landroid/widget/ImageView;", "artworkFrameView$delegate", "Lkotlin/Lazy;", "labelTextView", "Landroid/widget/TextView;", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView$delegate", "nanoSpace", "", "Ljava/lang/Integer;", "playButton", "Landroid/widget/ImageButton;", "getPlayButton", "()Landroid/widget/ImageButton;", "playButton$delegate", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "progressBar$delegate", "smallSpace", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "subtitleTextView", "getSubtitleTextView", "subtitleTextView$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "visibleVerticalItemCount", "bind", "", "model", "Lcom/amazon/music/views/library/models/PodcastTileModel;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getVerticalItemDimension", "initViewMargins", "initViewStyling", "setEnabled", Constants.ENABLE_DISABLE, "", "setTextMargins", "textView", "left", "top", "right", "bottom", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastTileView extends RelativeLayout {

    /* JADX INFO: renamed from: artworkFrameView$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private final Integer nanoSpace;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;

    /* JADX INFO: renamed from: progressBar$delegate, reason: from kotlin metadata */
    private final Lazy progressBar;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;
    private int visibleVerticalItemCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PodcastTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.artworkFrameView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastTileView$artworkFrameView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.vertical_item_image);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastTileView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.vertical_item_label);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.vertical_item_primary_text);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.vertical_item_secondary_text);
            }
        });
        this.progressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.music.views.library.views.PodcastTileView$progressBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R$id.vertical_item_horizontal_progress_bar);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageButton>() { // from class: com.amazon.music.views.library.views.PodcastTileView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageButton invoke() {
                return (ImageButton) this.this$0.findViewById(R$id.vertical_item_play_button);
            }
        });
        this.visibleVerticalItemCount = context.getResources().getInteger(R$integer.podcast_vertical_item_count);
        View.inflate(context, R$layout.dmmviewlibrary_podcast_tile_layout, this);
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

    private final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
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

    private final ProgressBar getProgressBar() {
        Object value = this.progressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-progressBar>(...)");
        return (ProgressBar) value;
    }

    private final ImageButton getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (ImageButton) value;
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle3);
    }

    private final void initViewMargins() {
        setTextMargins(getTitleTextView(), 0, this.smallSpace, 0, this.nanoSpace);
        setTextMargins(getSubtitleTextView(), 0, 0, 0, this.nanoSpace);
    }

    public final void bind(PodcastTileModel model, LifecycleOwner lifecycleOwner, ClickListenerFactory clickListenerFactory) {
        Unit unit;
        Unit unit2;
        Unit unit3;
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        View.OnClickListener onClickListenerCreate = clickListenerFactory.create(model.getMetadata());
        setOnClickListener(onClickListenerCreate);
        String label = model.getLabel();
        Unit unit4 = null;
        if (label == null) {
            unit = null;
        } else {
            getLabelTextView().setText(label);
            getLabelTextView().setVisibility(!TextUtils.isEmpty(label) ? 0 : 8);
            setTextMargins(getLabelTextView(), 0, this.smallSpace, 0, this.nanoSpace);
            setTextMargins(getTitleTextView(), 0, 0, 0, this.nanoSpace);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getLabelTextView().setVisibility(8);
        }
        String title = model.getTitle();
        if (title == null) {
            unit2 = null;
        } else {
            getTitleTextView().setText(title);
            getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
            unit2 = Unit.INSTANCE;
        }
        if (unit2 == null) {
            getTitleTextView().setVisibility(8);
        }
        if (model.getSubTitle() == null) {
            unit3 = null;
        } else {
            String subTitle = model.getSubTitle();
            getSubtitleTextView().setText(subTitle);
            getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subTitle) ? 0 : 8);
            unit3 = Unit.INSTANCE;
        }
        if (unit3 == null) {
            String remainingDurationText = model.getRemainingDurationText();
            if (remainingDurationText != null) {
                getSubtitleTextView().setText(remainingDurationText);
                getSubtitleTextView().setVisibility(!TextUtils.isEmpty(remainingDurationText) ? 0 : 8);
                unit4 = Unit.INSTANCE;
            }
            if (unit4 == null) {
                getSubtitleTextView().setVisibility(8);
            }
        }
        if (model.getShowPlayOverlay()) {
            getPlayButton().setVisibility(0);
            getPlayButton().setOnClickListener(onClickListenerCreate);
        } else {
            getPlayButton().setVisibility(8);
        }
        if (model.getProgress() != null && model.getTotalDurationMilliseconds() != null) {
            getProgressBar().setMax(model.getTotalDurationMilliseconds().intValue());
            getProgressBar().setProgress(model.getProgress().intValue());
            getProgressBar().setVisibility(0);
        } else {
            getProgressBar().setVisibility(8);
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        int verticalItemDimension = getVerticalItemDimension(context);
        ViewGroup.LayoutParams layoutParams = getArtworkFrameView().getLayoutParams();
        layoutParams.height = verticalItemDimension;
        layoutParams.width = verticalItemDimension;
        getProgressBar().getLayoutParams().width = verticalItemDimension;
        getTitleTextView().getLayoutParams().width = verticalItemDimension;
        getSubtitleTextView().getLayoutParams().width = verticalItemDimension;
        Picasso.get().load(model.getImageUrl()).resize(verticalItemDimension, verticalItemDimension).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.spacer_8))).centerCrop().into(getArtworkFrameView());
    }

    private final int getVerticalItemDimension(Context context) {
        Resources resources = context.getResources();
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.spacer_36) * 3;
        int i2 = this.visibleVerticalItemCount;
        int i3 = R$dimen.podcast_gutter;
        int dimensionPixelSize2 = i2 * resources.getDimensionPixelSize(i3);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.podcast_margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i3);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.shoveler_width);
        int i4 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / this.visibleVerticalItemCount;
        return i4 < dimensionPixelSize5 ? dimensionPixelSize5 : i4;
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        setAlpha(isEnabled ? 1.0f : 0.3f);
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
