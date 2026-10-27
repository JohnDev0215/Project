package com.google.android.setupdesign.span;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class LinkSpan extends ClickableSpan {
    private final String id;

    @Deprecated
    public interface OnClickListener {
        void onClick(LinkSpan linkSpan);
    }

    public interface OnLinkClickListener {
        boolean onLinkClick(LinkSpan linkSpan);
    }

    public LinkSpan(String str) {
        this.id = str;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        if (dispatchClick(view)) {
            if (Build.VERSION.SDK_INT >= 19) {
                view.cancelPendingInputEvents();
            }
        } else {
            Log.w("LinkSpan", "Dropping click event. No listener attached.");
        }
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text instanceof Spannable) {
                Selection.setSelection((Spannable) text, 0);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean dispatchClick(View view) {
        OnClickListener legacyListenerFromContext;
        boolean zOnLinkClick = view instanceof OnLinkClickListener ? ((OnLinkClickListener) view).onLinkClick(this) : false;
        if (zOnLinkClick || (legacyListenerFromContext = getLegacyListenerFromContext(view.getContext())) == null) {
            return zOnLinkClick;
        }
        legacyListenerFromContext.onClick(this);
        return true;
    }

    @Deprecated
    private OnClickListener getLegacyListenerFromContext(Context context) {
        Object baseContext = context;
        while (!(baseContext instanceof OnClickListener)) {
            if (!(baseContext instanceof ContextWrapper)) {
                return null;
            }
            baseContext = ((ContextWrapper) baseContext).getBaseContext();
        }
        return (OnClickListener) baseContext;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
    }

    public String getId() {
        return this.id;
    }
}
