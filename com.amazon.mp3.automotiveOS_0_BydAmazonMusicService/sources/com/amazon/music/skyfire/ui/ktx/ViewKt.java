package com.amazon.music.skyfire.ui.ktx;

import Touch.WidgetsInterface.v1_0.ImageResources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\f\u001a\u00020\r*\u00020\b\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0002\u001a\u0014\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u001a&\u0010\u0015\u001a\u00020\u0001*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0001*\u00020\u001b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0011¨\u0006\u001d"}, d2 = {"applyFontStyle", "", "Landroid/widget/TextView;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "collapseIf", "Landroid/view/View;", "flag", "", "collapseIfEmpty", "getOrCreateUniqueTag", "", "hideIf", "hideIfEmpty", "setActiveIcon", "Lcom/amazon/ui/foundations/views/BaseButton;", "resource", "LTouch/WidgetsInterface/v1_0/ImageResources$Image;", "setIcon", "setImageUrl", "Landroid/widget/ImageView;", ImagesContract.URL, "onSuccess", "Lkotlin/Function0;", "setPlayIcon", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "toggle", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewKt {
    public static final String getOrCreateUniqueTag(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.getTag() == null) {
            view.setTag(UUID.randomUUID().toString());
        }
        Object tag = view.getTag();
        if (tag != null) {
            return (String) tag;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public static final void applyFontStyle(TextView textView, StyleSheet styleSheet, FontStyleKey styleKey) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(styleKey, "styleKey");
        FontUtil fontUtil = FontUtil.INSTANCE;
        FontStyle fontStyle = styleSheet.getFontStyle(styleKey);
        Intrinsics.checkNotNull(fontStyle);
        fontUtil.applyFontStyle(textView, fontStyle);
    }

    public static final void collapseIf(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public static final void hideIf(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 4 : 0);
    }

    public static final void collapseIfEmpty(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        CharSequence text = textView.getText();
        textView.setVisibility(text == null || text.length() == 0 ? 8 : 0);
    }

    public static final void hideIfEmpty(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        CharSequence text = textView.getText();
        textView.setVisibility(text == null || text.length() == 0 ? 4 : 0);
    }

    public static /* synthetic */ void setImageUrl$default(ImageView imageView, String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        setImageUrl(imageView, str, function0);
    }

    public static final void setImageUrl(ImageView imageView, String str, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        if (str != null) {
            Picasso.get().load(str).into(imageView, function0 == null ? null : new Callback() { // from class: com.amazon.music.skyfire.ui.ktx.ViewKt$setImageUrl$callback$1$1
                @Override // com.squareup.picasso.Callback
                public void onError(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // com.squareup.picasso.Callback
                public void onSuccess() {
                    function0.invoke();
                }
            });
        }
    }

    public static final void setActiveIcon(BaseButton baseButton, ImageResources.Image image) {
        Intrinsics.checkNotNullParameter(baseButton, "<this>");
        Integer num = Constants.INSTANCE.getResMap().get(image);
        if (num == null) {
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(baseButton.getContext(), num.intValue());
        if (drawable == null) {
            return;
        }
        baseButton.setActiveIcon(drawable);
    }

    public static final void setIcon(BaseButton baseButton, ImageResources.Image image) {
        Intrinsics.checkNotNullParameter(baseButton, "<this>");
        Integer num = Constants.INSTANCE.getResMap().get(image);
        if (num == null) {
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(baseButton.getContext(), num.intValue());
        if (drawable == null) {
            return;
        }
        baseButton.setIcon(drawable);
    }

    public static final void toggle(BaseButton baseButton) {
        Intrinsics.checkNotNullParameter(baseButton, "<this>");
        baseButton.setActive(!baseButton.getIsActive());
    }

    public static final void setPlayIcon(ArtworkFrameView artworkFrameView, ImageResources.Image image) {
        Intrinsics.checkNotNullParameter(artworkFrameView, "<this>");
        Integer num = Constants.INSTANCE.getResMap().get(image);
        if (num == null) {
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(artworkFrameView.getContext(), num.intValue());
        if (drawable == null) {
            return;
        }
        artworkFrameView.setPlayIcon(drawable);
    }
}
