package com.amazon.podcast.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$string;
import com.amazon.podcast.R$styleable;
import com.amazonaws.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class ExpandableTextView extends EmberTextView {
    private TextView.BufferType bufferType;
    private int colorClickableText;
    private final ExpandClickableSpan expandSpan;
    private boolean isCollapsed;
    private int lineEndIndex;
    private final Logger logger;
    private CharSequence text;
    private CharSequence trimCollapsedText;
    private CharSequence trimExpandedText;
    private int trimLines;

    public ExpandableTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.logger = LoggerFactory.getLogger("ExpandableTextView");
        this.isCollapsed = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R$styleable.ExpandableTextView);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ExpandableTextView_trimCollapsedText, R$string.podcast_read_more);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ExpandableTextView_trimExpandedText, R$string.podcast_read_less);
        this.trimCollapsedText = getResources().getString(resourceId);
        this.trimExpandedText = getResources().getString(resourceId2);
        this.trimLines = typedArrayObtainStyledAttributes.getInt(R$styleable.ExpandableTextView_trimLines, 6);
        this.colorClickableText = typedArrayObtainStyledAttributes.getColor(R$styleable.ExpandableTextView_colorClickableText, ContextCompat.getColor(context, R$color.primary_glass_4));
        this.isCollapsed = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExpandableTextView_isCollapsed, true);
        typedArrayObtainStyledAttributes.recycle();
        this.expandSpan = new ExpandClickableSpan();
        onGlobalLayoutLineEndIndex();
        setText();
    }

    @Override // android.widget.TextView
    public void setText(final CharSequence text, final TextView.BufferType type) {
        this.text = text;
        this.bufferType = type;
        setText();
    }

    private void onGlobalLayoutLineEndIndex() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.views.ExpandableTextView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (StringUtils.isBlank(ExpandableTextView.this.text)) {
                    return;
                }
                ExpandableTextView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ExpandableTextView.this.refreshLineEndIndex();
                ExpandableTextView.this.setText();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLineEndIndex() {
        try {
            int i = this.trimLines;
            if (i == 0) {
                this.lineEndIndex = getLayout().getLineEnd(0);
            } else if (i > 0 && getLineCount() > this.trimLines) {
                this.lineEndIndex = getLayout().getLineEnd(this.trimLines - 1);
            } else {
                this.lineEndIndex = -1;
            }
        } catch (Exception e) {
            this.logger.error("Error about Expandable TextView", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setText() {
        super.setText(getDisplayableText(), this.bufferType);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(0);
    }

    private CharSequence getDisplayableText() {
        return getTrimmedText(this.text);
    }

    private CharSequence getTrimmedText(final CharSequence text) {
        if (text == null || this.lineEndIndex <= 0) {
            return text;
        }
        if (this.isCollapsed) {
            return getLayout().getLineCount() > this.trimLines ? updateCollapsedText(text) : text;
        }
        return updateExpandedText(text);
    }

    private CharSequence updateCollapsedText(final CharSequence text) {
        int iMin = Math.min(this.lineEndIndex - ((4 + this.trimCollapsedText.length()) + 1), text.length());
        return addClickableSpan(new SpannableStringBuilder(text, 0, Math.max(0, iMin)).append((CharSequence) "... ").append(this.trimCollapsedText), this.trimCollapsedText, Math.max(0, iMin));
    }

    private CharSequence updateExpandedText(final CharSequence text) {
        return addClickableSpan(new SpannableStringBuilder(text, 0, text.length()).append((CharSequence) "... ").append(this.trimExpandedText), this.trimExpandedText, text.length());
    }

    private CharSequence addClickableSpan(final SpannableStringBuilder s, final CharSequence trimText, final int endIndex) {
        s.setSpan(this.expandSpan, 0, s.length(), 33);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R$color.primary)), endIndex, endIndex + 4 + trimText.length(), 33);
        for (URLSpan uRLSpan : (URLSpan[]) s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new URLSpanNoUnderline(uRLSpan.getURL(), getResources()), s.getSpanStart(uRLSpan), s.getSpanEnd(uRLSpan), 33);
        }
        return s;
    }

    public void setColorClickableText(final int colorClickableText) {
        this.colorClickableText = colorClickableText;
    }

    public void setTrimCollapsedText(final CharSequence trimCollapsedText) {
        this.trimCollapsedText = trimCollapsedText;
    }

    public void setTrimExpandedText(final CharSequence trimExpandedText) {
        this.trimExpandedText = trimExpandedText;
    }

    public void setTrimLines(final int trimLines) {
        this.trimLines = trimLines;
    }

    private class ExpandClickableSpan extends ClickableSpan {
        private ExpandClickableSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(final View widget) {
            ExpandableTextView.this.isCollapsed = !r2.isCollapsed;
            ExpandableTextView.this.setText();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(final TextPaint ds) {
            ds.setColor(ExpandableTextView.this.colorClickableText);
        }
    }
}
