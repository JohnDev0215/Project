package org.chromium.ui.widget;

import android.annotation.SuppressLint;
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

/* JADX INFO: loaded from: classes3.dex */
public class TextViewWithClickableSpans extends TextViewWithLeading implements View.OnLongClickListener {
    public AccessibilityManager e;
    public PopupMenu f;

    public class a implements MenuItem.OnMenuItemClickListener {
        public final /* synthetic */ ClickableSpan e;

        public a(ClickableSpan clickableSpan) {
            this.e = clickableSpan;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            this.e.onClick(TextViewWithClickableSpans.this);
            return true;
        }
    }

    public class b implements PopupMenu.OnDismissListener {
        public b() {
        }

        @Override // android.widget.PopupMenu.OnDismissListener
        public final void onDismiss(PopupMenu popupMenu) {
            TextViewWithClickableSpans.this.f = null;
        }
    }

    public TextViewWithClickableSpans(Context context) {
        super(context);
        setSaveEnabled(false);
        this.e = (AccessibilityManager) getContext().getSystemService("accessibility");
        a();
    }

    public final void a() {
        AccessibilityManager accessibilityManager = this.e;
        if (accessibilityManager == null) {
            return;
        }
        setOnLongClickListener(accessibilityManager.isTouchExplorationEnabled() ? this : null);
    }

    public final void b() {
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans == null || clickableSpans.length == 0 || this.f != null) {
            return;
        }
        SpannableString spannableString = (SpannableString) getText();
        PopupMenu popupMenu = new PopupMenu(getContext(), this);
        this.f = popupMenu;
        Menu menu = popupMenu.getMenu();
        for (ClickableSpan clickableSpan : clickableSpans) {
            menu.add(spannableString.subSequence(spannableString.getSpanStart(clickableSpan), spannableString.getSpanEnd(clickableSpan))).setOnMenuItemClickListener(new a(clickableSpan));
        }
        this.f.setOnDismissListener(new b());
        this.f.show();
    }

    public final boolean c(MotionEvent motionEvent) {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int totalPaddingLeft = x - getTotalPaddingLeft();
        int totalPaddingTop = y - getTotalPaddingTop();
        int scrollX = getScrollX() + totalPaddingLeft;
        int scrollY = getScrollY() + totalPaddingTop;
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

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        if (!this.e.isTouchExplorationEnabled()) {
            return false;
        }
        b();
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 || !this.e.isTouchExplorationEnabled() || c(motionEvent)) {
            return zOnTouchEvent;
        }
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans != null && clickableSpans.length != 0) {
            if (clickableSpans.length == 1) {
                clickableSpans[0].onClick(this);
            } else {
                b();
            }
        }
        return true;
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(i, bundle);
        }
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans != null && clickableSpans.length != 0) {
            if (clickableSpans.length == 1) {
                clickableSpans[0].onClick(this);
            } else {
                b();
            }
        }
        return true;
    }

    @Override // android.view.View
    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSaveEnabled(false);
        this.e = (AccessibilityManager) getContext().getSystemService("accessibility");
        a();
    }
}
