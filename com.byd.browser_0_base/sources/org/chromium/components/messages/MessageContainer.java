package org.chromium.components.messages;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import defpackage.fa1;
import defpackage.ig1;
import defpackage.ke0;
import defpackage.lg1;
import defpackage.om3;
import defpackage.uv;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes3.dex */
public class MessageContainer extends FrameLayout {
    public b e;
    public boolean f;
    public int g;

    public class a implements View.OnLayoutChangeListener {
        public final /* synthetic */ Runnable e;

        public a(Runnable runnable) {
            this.e = runnable;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (view.getHeight() == 0) {
                return;
            }
            this.e.run();
            view.removeOnLayoutChangeListener(this);
            MessageContainer.this.f = false;
        }
    }

    public interface b {
    }

    public class c extends View.AccessibilityDelegate {
        public c() {
        }

        public final void a(AccessibilityEvent accessibilityEvent) {
            MessageContainer messageContainer = MessageContainer.this;
            if (messageContainer.e == null) {
                return;
            }
            if (accessibilityEvent.getEventType() == 32768) {
                ((org.chromium.components.messages.b) messageContainer.e).a.d.a();
                return;
            }
            if (accessibilityEvent.getEventType() == 65536) {
                lg1 lg1Var = ((org.chromium.components.messages.b) messageContainer.e).a;
                long jLongValue = lg1Var.e.get().longValue();
                ig1 ig1Var = lg1Var.d;
                ig1Var.a = jLongValue;
                Runnable runnable = lg1Var.f;
                ig1Var.b = runnable;
                ig1Var.c.postDelayed(runnable, jLongValue);
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            a(accessibilityEvent);
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            a(accessibilityEvent);
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    public MessageContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        setAccessibilityDelegate(new c());
    }

    public final void a(MessageBannerView messageBannerView) {
        if (indexOfChild(messageBannerView) != -1) {
            throw new IllegalStateException("Should not contain the target view when adding.");
        }
        int i = 0;
        if (N.ML2UWg2V("MessagesForAndroidStackingAnimation")) {
            if (getChildCount() > 1) {
                throw new IllegalStateException("Should not contain more than 2 views when adding a new message.");
            }
            if (getChildCount() == 1 && getChildAt(0).getElevation() > messageBannerView.getElevation()) {
                i = 1;
            }
        } else if (getChildCount() == 1) {
            throw new IllegalStateException("Should not contain any view when adding a new message.");
        }
        super.addView(messageBannerView, i);
        b();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public final void addView(View view) {
        throw new uv("Use addMessage instead.");
    }

    public final void b() {
        om3.i(this, this.g);
        int i = 0;
        om3.f(this, 0);
        if (getChildCount() == 0) {
            return;
        }
        this.g = om3.a(this, getResources().getString(getChildCount() == 1 ? R$string.dismiss : R$string.message_dismiss_and_show_next), new ke0(i, this));
    }

    public final void c(MessageBannerView messageBannerView) {
        if (indexOfChild(messageBannerView) == -1) {
            throw new IllegalStateException("The given view is not being shown.");
        }
        super.removeView(messageBannerView);
        if (getChildCount() == 0) {
            this.e = null;
        }
        b();
    }

    public final void d(Runnable runnable) {
        View childAt = getChildAt(0);
        if (childAt.getHeight() > 0) {
            this.f = false;
            runnable.run();
        } else {
            this.f = true;
            childAt.addOnLayoutChangeListener(new a(runnable));
        }
    }

    public int getA11yDismissActionIdForTesting() {
        return this.g;
    }

    public int getMessageBannerHeight() {
        if (getChildAt(0) == null) {
            fa1.k("MessageContainer", "Null child in message container; child count %s", Integer.valueOf(getChildCount()));
        }
        return getChildAt(0).getHeight();
    }

    public int getMessageShadowTopMargin() {
        return getResources().getDimensionPixelOffset(R$dimen.message_shadow_top_margin);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    @Deprecated
    public final void removeView(View view) {
        throw new uv("Use removeMessage instead.");
    }

    public void setA11yDelegate(b bVar) {
        this.e = bVar;
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        TraceEvent traceEventH0 = TraceEvent.H0("MessageContainer.setLayoutParams", null);
        try {
            super.setLayoutParams(layoutParams);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
