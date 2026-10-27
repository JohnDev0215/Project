package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0017\u0012\u0006\u0010<\u001a\u00020;\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b=\u0010>J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002JG\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000eJ\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0006\u0010\u0018\u001a\u00020\u0007R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010#\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001b\u0010'\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010 \u001a\u0004\b%\u0010&R\u001b\u0010*\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010 \u001a\u0004\b)\u0010&R\u001b\u0010-\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010 \u001a\u0004\b,\u0010&R\u001d\u00102\u001a\u0004\u0018\u00010.8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u0010 \u001a\u0004\b0\u00101R\u0014\u00103\u001a\u00020\u00158\u0002X\u0082D¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00106R\u0016\u00108\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00106R\u0016\u00109\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00106R\u0016\u0010:\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00106¨\u0006@"}, d2 = {"Lcom/amazon/music/views/library/views/VideoStoryTileView;", "Lcom/amazon/music/views/library/views/ScopedConstraintLayout;", "", "initViewMargins", "initViewStyling", "Landroid/widget/TextView;", "textView", "", "left", "top", "right", "bottom", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "", ContextMappingConstants.LABEL, "setLabel", "title", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "", Constants.ENABLE_DISABLE, "setEnabled", "getTileWidth", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView$delegate", "Lkotlin/Lazy;", "getArtworkFrameView", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView", "labelTextView$delegate", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView", "titleTextView$delegate", "getTitleTextView", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "Landroid/view/TextureView;", "videoStoryTextureView$delegate", "getVideoStoryTextureView", "()Landroid/view/TextureView;", "videoStoryTextureView", "mIsContentEnabled", "Z", "nanoSpace", "Ljava/lang/Integer;", "microSpace", "smallSpace", "hugeSpace", "miniSpace", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class VideoStoryTileView extends ScopedConstraintLayout {

    /* JADX INFO: renamed from: artworkFrameView$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameView;
    private final Integer hugeSpace;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private final boolean mIsContentEnabled;
    private final Integer microSpace;
    private final Integer miniSpace;
    private final Integer nanoSpace;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: renamed from: videoStoryTextureView$delegate, reason: from kotlin metadata */
    private final Lazy videoStoryTextureView;
    private static final float ENABLED_ALPHA_VALUE = 1.0f;
    private static final float DISABLED_ALPHA_VALUE = 0.3f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStoryTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameView = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.VideoStoryTileView$artworkFrameView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.artwork_frame);
                if (viewFindViewById != null) {
                    return (ArtworkFrameView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.ArtworkFrameView");
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VideoStoryTileView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VideoStoryTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VideoStoryTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.videoStoryTextureView = LazyKt__LazyJVMKt.lazy(new Function0<TextureView>() { // from class: com.amazon.music.views.library.views.VideoStoryTileView$videoStoryTextureView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextureView invoke() {
                return (TextureView) this.this$0.findViewById(R$id.video_story_texture_view);
            }
        });
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.hugeSpace = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        this.miniSpace = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        View.inflate(context, R$layout.dmmviewlibrary_video_story_tile_view_layout, this);
        initViewStyling();
        initViewMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameView() {
        return (ArtworkFrameView) this.artworkFrameView.getValue();
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

    public final TextureView getVideoStoryTextureView() {
        return (TextureView) this.videoStoryTextureView.getValue();
    }

    private final void initViewMargins() {
        TextView titleTextView = getTitleTextView();
        Integer num = this.miniSpace;
        Integer num2 = this.smallSpace;
        setTextMargins(titleTextView, num, num2, num, num2);
        setTextMargins(getSubtitleTextView(), this.smallSpace, 0, 0, this.nanoSpace);
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

    public final void setLabel(String label) {
        getLabelTextView().setText(label);
        if (label == null || label.length() == 0) {
            getLabelTextView().setVisibility(8);
            return;
        }
        setTextMargins(getLabelTextView(), 0, this.smallSpace, 0, this.nanoSpace);
        TextView titleTextView = getTitleTextView();
        Integer num = this.miniSpace;
        Integer num2 = this.smallSpace;
        setTextMargins(titleTextView, num, num2, num, num2);
        getLabelTextView().setVisibility(0);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            setAlpha(ENABLED_ALPHA_VALUE);
        } else {
            setAlpha(DISABLED_ALPHA_VALUE);
            getArtworkFrameView().setPlayState(null);
        }
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.mIsContentEnabled;
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final int getTileWidth() {
        Drawable drawable = getContext().getDrawable(R$drawable.placeholder_stories);
        if (drawable == null) {
            return 0;
        }
        return drawable.getIntrinsicWidth();
    }
}
