package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommentaryIndicatorView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u0002R\u001b\u0010\u0011\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0016\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019¨\u0006!"}, d2 = {"Lcom/amazon/music/views/library/views/CommentaryIndicatorView;", "Landroid/widget/LinearLayout;", "", "isToggleActive", "", "setToggleActive", "", "activeDrawable", "inactiveDrawable", "configureIcons", "visible", "setSpeakerImageVisible", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "speakerImageView$delegate", "Lkotlin/Lazy;", "getSpeakerImageView", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "speakerImageView", "Landroid/widget/ImageView;", "iconView$delegate", "getIconView", "()Landroid/widget/ImageView;", "iconView", "Landroid/graphics/drawable/Drawable;", "activeIcon", "Landroid/graphics/drawable/Drawable;", "inactiveIcon", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CommentaryIndicatorView extends LinearLayout {
    private Drawable activeIcon;

    /* JADX INFO: renamed from: iconView$delegate, reason: from kotlin metadata */
    private final Lazy iconView;
    private Drawable inactiveIcon;

    /* JADX INFO: renamed from: speakerImageView$delegate, reason: from kotlin metadata */
    private final Lazy speakerImageView;

    public final ArtworkFrameView getSpeakerImageView() {
        Object value = this.speakerImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-speakerImageView>(...)");
        return (ArtworkFrameView) value;
    }

    private final ImageView getIconView() {
        Object value = this.iconView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-iconView>(...)");
        return (ImageView) value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommentaryIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.speakerImageView = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.CommentaryIndicatorView$speakerImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.speaker_image);
            }
        });
        this.iconView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.CommentaryIndicatorView$iconView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.commentary_icon);
            }
        });
        View.inflate(getContext(), R$layout.dmmviewlibrary_commentary_indicator_view_layout, this);
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        configureIcons(R$drawable.ic_other_commentaryon, R$drawable.ic_other_commentaryoff);
        setToggleActive(false);
        setSpeakerImageVisible(false);
    }

    public final void setToggleActive(boolean isToggleActive) {
        if (isToggleActive) {
            getIconView().setImageDrawable(this.activeIcon);
        } else {
            getIconView().setImageDrawable(this.inactiveIcon);
        }
    }

    public final void configureIcons(int activeDrawable, int inactiveDrawable) {
        this.activeIcon = AppCompatResources.getDrawable(getContext(), activeDrawable);
        this.inactiveIcon = AppCompatResources.getDrawable(getContext(), inactiveDrawable);
    }

    public final void setSpeakerImageVisible(boolean visible) {
        getSpeakerImageView().setVisibility(visible ? 0 : 8);
    }
}
