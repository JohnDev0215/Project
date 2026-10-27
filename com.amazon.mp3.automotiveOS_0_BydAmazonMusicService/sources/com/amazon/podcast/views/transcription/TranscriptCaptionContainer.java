package com.amazon.podcast.views.transcription;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class TranscriptCaptionContainer extends RelativeLayout {
    private Float maskAlpha;
    private Float viewAlpha;

    public TranscriptCaptionContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.maskAlpha = Float.valueOf(0.0f);
        this.viewAlpha = Float.valueOf(1.0f);
    }

    public void setMaskAlpha(Float alpha) {
        if (alpha.floatValue() < 0.0f || alpha.floatValue() > 1.0f) {
            return;
        }
        this.maskAlpha = alpha;
        updateAlpha();
    }

    public void setViewAlpha(Float alpha) {
        if (alpha.floatValue() < 0.0f || alpha.floatValue() > 1.0f) {
            return;
        }
        this.viewAlpha = alpha;
        updateAlpha();
    }

    private void updateAlpha() {
        getChildAt(0).setAlpha(this.viewAlpha.floatValue() * this.maskAlpha.floatValue());
    }
}
