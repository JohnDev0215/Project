package com.android.car.ui.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.toolbar.TabLayout;
import java.util.function.Consumer;
import spi.g3;

/* JADX INFO: compiled from: DeprecatedTabWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final TabLayout.a a;
    public final Consumer<g> b;
    public String c = "";
    public Drawable d = null;

    public a(@NonNull Context context, @NonNull TabLayout.a aVar, @Nullable Runnable runnable, @Nullable Consumer<g> consumer) {
        this.a = aVar;
        this.b = consumer;
        TextViewListener textViewListener = new TextViewListener(context);
        textViewListener.setTextListener(new g3(this, runnable, 0));
        ImageViewListener imageViewListener = new ImageViewListener(context);
        imageViewListener.setImageDrawableListener(new g3(this, runnable, 1));
        imageViewListener.setImageDrawable(aVar.a);
        textViewListener.setText(aVar.b);
    }
}
