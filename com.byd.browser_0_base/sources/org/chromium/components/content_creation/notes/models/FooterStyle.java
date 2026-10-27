package org.chromium.components.content_creation.notes.models;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public final class FooterStyle {
    public final int a;
    public final int b;

    public FooterStyle(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final void a(TextView textView, TextView textView2, ImageView imageView) {
        int i = this.a;
        textView.setTextColor(i);
        textView2.setTextColor(i);
        imageView.setColorFilter(new BlendModeColorFilter(this.b, BlendMode.SRC_IN));
    }
}
