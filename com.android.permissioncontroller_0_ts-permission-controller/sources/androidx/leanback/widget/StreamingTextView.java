package androidx.leanback.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import androidx.core.widget.TextViewCompat;
import androidx.leanback.R;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
class StreamingTextView extends EditText {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.leanback.widget.StreamingTextView";
    static final boolean ANIMATE_DOTS_FOR_PENDING = true;
    private static final boolean DEBUG = false;
    private static final boolean DOTS_FOR_PENDING = true;
    private static final boolean DOTS_FOR_STABLE = false;
    private static final Pattern SPLIT_PATTERN = Pattern.compile("\\S+");
    private static final Property<StreamingTextView, Integer> STREAM_POSITION_PROPERTY = new Property<StreamingTextView, Integer>(Integer.class, "streamPosition") { // from class: androidx.leanback.widget.StreamingTextView.1
        @Override // android.util.Property
        public Integer get(StreamingTextView view) {
            return Integer.valueOf(view.getStreamPosition());
        }

        @Override // android.util.Property
        public void set(StreamingTextView view, Integer value) {
            view.setStreamPosition(value.intValue());
        }
    };
    private static final long STREAM_UPDATE_DELAY_MILLIS = 50;
    private static final String TAG = "StreamingTextView";
    private static final float TEXT_DOT_SCALE = 1.3f;
    Bitmap mOneDot;
    final Random mRandom;
    int mStreamPosition;
    private ObjectAnimator mStreamingAnimation;
    Bitmap mTwoDot;

    public StreamingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRandom = new Random();
    }

    public StreamingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRandom = new Random();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mOneDot = getScaledBitmap(R.drawable.lb_text_dot_one, TEXT_DOT_SCALE);
        this.mTwoDot = getScaledBitmap(R.drawable.lb_text_dot_two, TEXT_DOT_SCALE);
        reset();
    }

    private Bitmap getScaledBitmap(int resourceId, float scaled) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scaled), (int) (bitmap.getHeight() * scaled), false);
    }

    public void reset() {
        this.mStreamPosition = -1;
        cancelStreamAnimation();
        setText("");
    }

    public void updateRecognizedText(String stableText, String pendingText) {
        if (stableText == null) {
            stableText = "";
        }
        SpannableStringBuilder displayText = new SpannableStringBuilder(stableText);
        if (pendingText != null) {
            int pendingTextStart = displayText.length();
            displayText.append((CharSequence) pendingText);
            addDottySpans(displayText, pendingText, pendingTextStart);
        }
        int pendingTextStart2 = stableText.length();
        this.mStreamPosition = Math.max(pendingTextStart2, this.mStreamPosition);
        updateText(new SpannedString(displayText));
        startStreamAnimation();
    }

    int getStreamPosition() {
        return this.mStreamPosition;
    }

    void setStreamPosition(int streamPosition) {
        this.mStreamPosition = streamPosition;
        invalidate();
    }

    private void startStreamAnimation() {
        cancelStreamAnimation();
        int pos = getStreamPosition();
        int totalLen = length();
        int animLen = totalLen - pos;
        if (animLen > 0) {
            if (this.mStreamingAnimation == null) {
                ObjectAnimator objectAnimator = new ObjectAnimator();
                this.mStreamingAnimation = objectAnimator;
                objectAnimator.setTarget(this);
                this.mStreamingAnimation.setProperty(STREAM_POSITION_PROPERTY);
            }
            this.mStreamingAnimation.setIntValues(pos, totalLen);
            this.mStreamingAnimation.setDuration(((long) animLen) * STREAM_UPDATE_DELAY_MILLIS);
            this.mStreamingAnimation.start();
        }
    }

    private void cancelStreamAnimation() {
        ObjectAnimator objectAnimator = this.mStreamingAnimation;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private void addDottySpans(SpannableStringBuilder displayText, String text, int textStart) {
        Matcher m = SPLIT_PATTERN.matcher(text);
        while (m.find()) {
            int wordStart = m.start() + textStart;
            int wordEnd = m.end() + textStart;
            DottySpan span = new DottySpan(text.charAt(m.start()), wordStart);
            displayText.setSpan(span, wordStart, wordEnd, 33);
        }
    }

    private void addColorSpan(SpannableStringBuilder displayText, int color, String text, int textStart) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        int end = text.length() + textStart;
        displayText.setSpan(span, textStart, end, 33);
    }

    public void setFinalRecognizedText(CharSequence finalText) {
        updateText(finalText);
    }

    private void updateText(CharSequence displayText) {
        setText(displayText);
        bringPointIntoView(length());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    private class DottySpan extends ReplacementSpan {
        private final int mPosition;
        private final int mSeed;

        public DottySpan(int seed, int pos) {
            this.mSeed = seed;
            this.mPosition = pos;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            int width = (int) paint.measureText(text, start, end);
            int dotWidth = StreamingTextView.this.mOneDot.getWidth();
            int sliceWidth = dotWidth * 2;
            int sliceCount = width / sliceWidth;
            int excess = width % sliceWidth;
            int prop = excess / 2;
            boolean rtl = StreamingTextView.isLayoutRtl(StreamingTextView.this);
            StreamingTextView.this.mRandom.setSeed(this.mSeed);
            int oldAlpha = paint.getAlpha();
            for (int i = 0; i < sliceCount && this.mPosition + i < StreamingTextView.this.mStreamPosition; i++) {
                float left = (i * sliceWidth) + prop + (dotWidth / 2);
                float dotLeft = rtl ? ((x + width) - left) - dotWidth : x + left;
                paint.setAlpha((StreamingTextView.this.mRandom.nextInt(4) + 1) * 63);
                if (StreamingTextView.this.mRandom.nextBoolean()) {
                    canvas.drawBitmap(StreamingTextView.this.mTwoDot, dotLeft, y - StreamingTextView.this.mTwoDot.getHeight(), paint);
                } else {
                    canvas.drawBitmap(StreamingTextView.this.mOneDot, dotLeft, y - StreamingTextView.this.mOneDot.getHeight(), paint);
                }
            }
            paint.setAlpha(oldAlpha);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
            return (int) paint.measureText(text, start, end);
        }
    }

    public static boolean isLayoutRtl(View view) {
        return Build.VERSION.SDK_INT >= 17 && 1 == view.getLayoutDirection();
    }

    public void updateRecognizedText(String stableText, List<Float> rmsValues) {
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionModeCallback));
    }
}
