package com.android.car.ui.toolbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
@TargetApi(28)
public final class ImageViewListener extends ImageView {
    public Consumer<Drawable> a;

    public ImageViewListener(Context context) {
        super(context);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        Consumer<Drawable> consumer = this.a;
        if (consumer != null) {
            consumer.accept(drawable);
        }
    }

    public void setImageDrawableListener(Consumer<Drawable> consumer) {
        this.a = consumer;
    }
}
