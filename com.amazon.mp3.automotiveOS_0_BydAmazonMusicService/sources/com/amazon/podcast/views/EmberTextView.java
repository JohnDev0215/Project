package com.amazon.podcast.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.podcast.R$styleable;

/* JADX INFO: loaded from: classes5.dex */
public class EmberTextView extends AppCompatTextView {
    public EmberTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs, 0);
    }

    private void applyCustomFont(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        if (context == null || attrs == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R$styleable.TextView, defStyleAttr, 0);
        try {
            setTypeface(TypeFaceCache.INSTANCE.getTypeFace(typedArrayObtainStyledAttributes.getString(R$styleable.TextView_fontPath), context));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
