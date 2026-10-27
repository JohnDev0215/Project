package com.android.car.ui.toolbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.widget.TextView;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
@TargetApi(28)
public final class TextViewListener extends TextView {
    public Consumer<CharSequence> a;

    public TextViewListener(Context context) {
        super(context);
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        Consumer<CharSequence> consumer = this.a;
        if (consumer != null) {
            consumer.accept(charSequence);
        }
    }

    public void setTextListener(Consumer<CharSequence> consumer) {
        this.a = consumer;
    }
}
