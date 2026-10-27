package com.google.android.setupdesign.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.google.android.setupdesign.accessibility.LinkAccessibilityHelper;
import com.google.android.setupdesign.span.LinkSpan;
import com.google.android.setupdesign.span.SpanHelper;
import com.google.android.setupdesign.view.TouchableMovementMethod;

/* JADX INFO: loaded from: classes.dex */
public class RichTextView extends AppCompatTextView implements LinkSpan.OnLinkClickListener {
    private LinkAccessibilityHelper accessibilityHelper;
    private LinkSpan.OnLinkClickListener onLinkClickListener;

    public static CharSequence getRichText(Context context, CharSequence charSequence) {
        if (!(charSequence instanceof Spanned)) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        for (Annotation annotation : (Annotation[]) spannableString.getSpans(0, spannableString.length(), Annotation.class)) {
            String key = annotation.getKey();
            if ("textAppearance".equals(key)) {
                int identifier = context.getResources().getIdentifier(annotation.getValue(), "style", context.getPackageName());
                if (identifier == 0) {
                    Log.w("RichTextView", "Cannot find resource: " + identifier);
                }
                SpanHelper.replaceSpan(spannableString, annotation, new TextAppearanceSpan(context, identifier));
            } else if ("link".equals(key)) {
                SpanHelper.replaceSpan(spannableString, annotation, new LinkSpan(annotation.getValue()), new TypefaceSpan("sans-serif-medium"));
            }
        }
        return spannableString;
    }

    public RichTextView(Context context) {
        super(context);
        init();
    }

    public RichTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LinkAccessibilityHelper linkAccessibilityHelper = new LinkAccessibilityHelper(this);
        this.accessibilityHelper = linkAccessibilityHelper;
        ViewCompat.setAccessibilityDelegate(this, linkAccessibilityHelper);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        CharSequence richText = getRichText(getContext(), charSequence);
        super.setText(richText, bufferType);
        boolean zHasLinks = hasLinks(richText);
        if (zHasLinks) {
            setMovementMethod(TouchableMovementMethod.TouchableLinkMovementMethod.getInstance());
        } else {
            setMovementMethod(null);
        }
        setFocusable(zHasLinks);
        if (Build.VERSION.SDK_INT >= 25) {
            setRevealOnFocusHint(false);
            setFocusableInTouchMode(zHasLinks);
        }
    }

    private boolean hasLinks(CharSequence charSequence) {
        return (charSequence instanceof Spanned) && ((ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class)).length > 0;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        MovementMethod movementMethod = getMovementMethod();
        if (movementMethod instanceof TouchableMovementMethod) {
            TouchableMovementMethod touchableMovementMethod = (TouchableMovementMethod) movementMethod;
            if (touchableMovementMethod.getLastTouchEvent() == motionEvent) {
                return touchableMovementMethod.isLastTouchEventHandled();
            }
        }
        return zOnTouchEvent;
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        LinkAccessibilityHelper linkAccessibilityHelper = this.accessibilityHelper;
        if (linkAccessibilityHelper == null || !linkAccessibilityHelper.dispatchHoverEvent(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (Build.VERSION.SDK_INT >= 17) {
            int[] drawableState = getDrawableState();
            for (Drawable drawable : getCompoundDrawablesRelative()) {
                if (drawable != null && drawable.setState(drawableState)) {
                    invalidateDrawable(drawable);
                }
            }
        }
    }

    public void setOnLinkClickListener(LinkSpan.OnLinkClickListener onLinkClickListener) {
        this.onLinkClickListener = onLinkClickListener;
    }

    public LinkSpan.OnLinkClickListener getOnLinkClickListener() {
        return this.onLinkClickListener;
    }

    @Override // com.google.android.setupdesign.span.LinkSpan.OnLinkClickListener
    public boolean onLinkClick(LinkSpan linkSpan) {
        LinkSpan.OnLinkClickListener onLinkClickListener = this.onLinkClickListener;
        if (onLinkClickListener != null) {
            return onLinkClickListener.onLinkClick(linkSpan);
        }
        return false;
    }
}
