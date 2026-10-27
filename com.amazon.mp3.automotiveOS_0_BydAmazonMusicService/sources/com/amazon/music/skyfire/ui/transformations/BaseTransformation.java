package com.amazon.music.skyfire.ui.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.amazon.music.find.utils.PageUriUtils;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/transformations/BaseTransformation;", "Lcom/squareup/picasso/Transformation;", "()V", "argbClone", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseTransformation implements Transformation {
    @Override // com.squareup.picasso.Transformation
    public abstract /* synthetic */ String key();

    @Override // com.squareup.picasso.Transformation
    public abstract /* synthetic */ Bitmap transform(Bitmap bitmap);

    public final Bitmap argbClone(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Bitmap copy = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(copy).drawBitmap(source, 0.0f, 0.0f, (Paint) null);
        Intrinsics.checkNotNullExpressionValue(copy, "copy");
        return copy;
    }
}
