package com.amazon.music.alps.liveevent.ktx;

import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a&\u0010\u0007\u001a\u00020\u0001*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¨\u0006\r"}, d2 = {"applyFontStyle", "", "Landroid/widget/TextView;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "setImageUrl", "Landroid/widget/ImageView;", ImagesContract.URL, "", "onSuccess", "Lkotlin/Function0;", "DMMALPS_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewKt {
    public static final void applyFontStyle(TextView textView, StyleSheet styleSheet, FontStyleKey styleKey) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(styleKey, "styleKey");
        FontUtil fontUtil = FontUtil.INSTANCE;
        FontStyle fontStyle = styleSheet.getFontStyle(styleKey);
        Intrinsics.checkNotNull(fontStyle);
        fontUtil.applyFontStyle(textView, fontStyle);
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
            Picasso.get().load(str).into(imageView, function0 == null ? null : new Callback() { // from class: com.amazon.music.alps.liveevent.ktx.ViewKt$setImageUrl$callback$1$1
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
}
