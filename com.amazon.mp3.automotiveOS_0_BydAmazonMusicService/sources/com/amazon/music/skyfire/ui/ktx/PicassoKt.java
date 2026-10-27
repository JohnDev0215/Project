package com.amazon.music.skyfire.ui.ktx;

import android.content.Context;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Picasso.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"createImageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "Lcom/squareup/picasso/Picasso;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class PicassoKt {
    public static final ImageBinder createImageBinder(Picasso picasso, Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(picasso, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        PicassoImageLoader picassoImageLoaderBuild = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        Intrinsics.checkNotNullExpressionValue(picassoImageLoaderBuild, "builder(context, Picasso…\n                .build()");
        return new ImageBinder(context, styleSheet, picassoImageLoaderBuild);
    }
}
