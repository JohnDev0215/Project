package org.chromium.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupMenu;
import android.widget.TextView;

/* JADX INFO: loaded from: classes4.dex */
public class TextViewWithClickableSpans extends TextView {
    private AccessibilityManager mAccessibilityManager;

    public TextViewWithClickableSpans(Context context) {
        super(context);
        init();
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setSaveEnabled(false);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setOnLongClickListener(new View.OnLongClickListener() { // from class: org.chromium.ui.widget.TextViewWithClickableSpans.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (!TextViewWithClickableSpans.this.mAccessibilityManager.isTouchExplorationEnabled()) {
                    return false;
                }
                TextViewWithClickableSpans.this.openDisambiguationMenu();
                return true;
            }
        });
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i == 16) {
            handleAccessibilityClick();
            return true;
        }
        return super.performAccessibilityAction(i, bundle);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 || !this.mAccessibilityManager.isTouchExplorationEnabled() || touchIntersectsAnyClickableSpans(motionEvent)) {
            return zOnTouchEvent;
        }
        handleAccessibilityClick();
        return true;
    }

    private boolean touchIntersectsAnyClickableSpans(MotionEvent motionEvent) {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int totalPaddingLeft = x - getTotalPaddingLeft();
        int totalPaddingTop = y - getTotalPaddingTop();
        int scrollX = totalPaddingLeft + getScrollX();
        int scrollY = totalPaddingTop + getScrollY();
        Layout layout = getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
        return ((ClickableSpan[]) ((SpannableString) text).getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class)).length > 0;
    }

    public ClickableSpan[] getClickableSpans() {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return null;
        }
        SpannableString spannableString = (SpannableString) text;
        return (ClickableSpan[]) spannableString.getSpans(0, spannableString.length(), ClickableSpan.class);
    }

    private void handleAccessibilityClick() {
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans == null || clickableSpans.length == 0) {
            return;
        }
        if (clickableSpans.length == 1) {
            clickableSpans[0].onClick(this);
        } else {
            openDisambiguationMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openDisambiguationMenu() {
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans == null || clickableSpans.length == 0) {
            return;
        }
        SpannableString spannableString = (SpannableString) getText();
        PopupMenu popupMenu = new PopupMenu(getContext(), this);
        Menu menu = popupMenu.getMenu();
        for (final ClickableSpan clickableSpan : clickableSpans) {
            menu.add(spannableString.subSequence(spannableString.getSpanStart(clickableSpan), spannableString.getSpanEnd(clickableSpan))).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.chromium.ui.widget.TextViewWithClickableSpans.2
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem) {
                    clickableSpan.onClick(TextViewWithClickableSpans.this);
                    return true;
                }
            });
        }
        popupMenu.show();
    }
}
