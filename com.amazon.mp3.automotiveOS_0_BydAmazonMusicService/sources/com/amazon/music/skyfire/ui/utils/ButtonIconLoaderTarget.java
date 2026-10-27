package com.amazon.music.skyfire.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ButtonIconLoaderTarget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/ButtonIconLoaderTarget;", "Lcom/squareup/picasso/Target;", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", "getButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "onBitmapFailed", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "drawable", "Landroid/graphics/drawable/Drawable;", "onBitmapLoaded", "bitmap", "Landroid/graphics/Bitmap;", "loadedFrom", "Lcom/squareup/picasso/Picasso$LoadedFrom;", "onPrepareLoad", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ButtonIconLoaderTarget implements Target {
    private static final Logger logger;
    private final BaseButton button;

    @Override // com.squareup.picasso.Target
    public void onPrepareLoad(Drawable drawable) {
    }

    public ButtonIconLoaderTarget(BaseButton button) {
        Intrinsics.checkNotNullParameter(button, "button");
        this.button = button;
    }

    public final BaseButton getButton() {
        return this.button;
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapFailed(Exception e, Drawable drawable) {
        Intrinsics.checkNotNullParameter(e, "e");
        logger.warn("Error loading bitmap", (Throwable) e);
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        final Context context = this.button.getContext();
        if (context == null) {
            return;
        }
        this.button.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.utils.ButtonIconLoaderTarget$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ButtonIconLoaderTarget.m2203onBitmapLoaded$lambda0(this.f$0, context, bitmap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-0, reason: not valid java name */
    public static final void m2203onBitmapLoaded$lambda0(ButtonIconLoaderTarget this$0, Context context, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        this$0.button.setIcon(new BitmapDrawable(context.getResources(), bitmap));
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(ButtonIconLoaderTarget.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(ButtonIconLoad…t::class.java.simpleName)");
        logger = logger2;
    }
}
