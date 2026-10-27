package com.amazon.podcast;

import android.view.ViewGroup;
import androidx.core.widget.TextViewCompat;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
public final class AutoSizeTextUtil {
    public static void continuousAutoSizeTextType(final int minTextSize, final int maxTextSize, final int minLineHeight, final int maxLineHeight, final EmberTextView textView) {
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(textView, minTextSize, maxTextSize, 1, 0);
        textView.post(new Runnable() { // from class: com.amazon.podcast.AutoSizeTextUtil.1
            @Override // java.lang.Runnable
            public void run() {
                float textSize = maxTextSize - textView.getTextSize();
                textView.setLineHeight((int) (maxLineHeight - (textSize * ((r1 - minLineHeight) / (maxTextSize - minTextSize)))));
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.height = -2;
                textView.setLayoutParams(layoutParams);
            }
        });
    }
}
