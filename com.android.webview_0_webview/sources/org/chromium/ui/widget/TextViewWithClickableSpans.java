package org.chromium.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupMenu;
import defpackage.C1492sP;
import defpackage.MenuItemOnMenuItemClickListenerC1437rP;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TextViewWithClickableSpans extends TextViewWithLeading implements View.OnLongClickListener {
    public AccessibilityManager s;
    public PopupMenu t;

    public TextViewWithClickableSpans(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSaveEnabled(false);
        this.s = (AccessibilityManager) getContext().getSystemService("accessibility");
        a();
    }

    public final void a() {
        AccessibilityManager accessibilityManager = this.s;
        if (accessibilityManager == null) {
            return;
        }
        setOnLongClickListener(accessibilityManager.isTouchExplorationEnabled() ? this : null);
    }

    public ClickableSpan[] b() {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return null;
        }
        SpannableString spannableString = (SpannableString) text;
        return (ClickableSpan[]) spannableString.getSpans(0, spannableString.length(), ClickableSpan.class);
    }

    public final void c() {
        ClickableSpan[] clickableSpanArrB = b();
        if (clickableSpanArrB == null || clickableSpanArrB.length == 0) {
            return;
        }
        if (clickableSpanArrB.length == 1) {
            clickableSpanArrB[0].onClick(this);
        } else {
            d();
        }
    }

    public final void d() {
        ClickableSpan[] clickableSpanArrB = b();
        if (clickableSpanArrB == null || clickableSpanArrB.length == 0 || this.t != null) {
            return;
        }
        SpannableString spannableString = (SpannableString) getText();
        PopupMenu popupMenu = new PopupMenu(getContext(), this);
        this.t = popupMenu;
        Menu menu = popupMenu.getMenu();
        for (ClickableSpan clickableSpan : clickableSpanArrB) {
            menu.add(spannableString.subSequence(spannableString.getSpanStart(clickableSpan), spannableString.getSpanEnd(clickableSpan))).setOnMenuItemClickListener(new MenuItemOnMenuItemClickListenerC1437rP(this, clickableSpan));
        }
        this.t.setOnDismissListener(new C1492sP(this));
        this.t.show();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (!this.s.isTouchExplorationEnabled()) {
            return false;
        }
        d();
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (motionEvent.getAction() != 1 && this.s.isTouchExplorationEnabled()) {
            CharSequence text = getText();
            boolean z = false;
            if (text instanceof SpannableString) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int totalPaddingLeft = x - getTotalPaddingLeft();
                int totalPaddingTop = y - getTotalPaddingTop();
                int scrollX = getScrollX() + totalPaddingLeft;
                int scrollY = getScrollY() + totalPaddingTop;
                Layout layout = getLayout();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
                if (((ClickableSpan[]) ((SpannableString) text).getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class)).length > 0) {
                    z = true;
                }
            }
            if (!z) {
                c();
                return true;
            }
        }
        return zOnTouchEvent;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(i, bundle);
        }
        c();
        return true;
    }

    @Override // android.view.View
    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
    }
}
