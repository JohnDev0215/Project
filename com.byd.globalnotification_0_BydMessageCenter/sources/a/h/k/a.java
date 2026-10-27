package a.h.k;

import a.h.k.x.b;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final View.AccessibilityDelegate f806c = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View.AccessibilityDelegate f807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View.AccessibilityDelegate f808b;

    /* JADX INFO: renamed from: a.h.k.a$a, reason: collision with other inner class name */
    public static final class C0015a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f809a;

        public C0015a(a aVar) {
            this.f809a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f809a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            a.h.k.x.c cVarB = this.f809a.b(view);
            if (cVarB != null) {
                return (AccessibilityNodeProvider) cVarB.f867a;
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f809a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            a.h.k.x.b bVar = new a.h.k.x.b(accessibilityNodeInfo);
            bVar.f856a.setScreenReaderFocusable(q.F(view));
            Boolean boolD = new p(a.h.a.tag_accessibility_heading, Boolean.class, 28).d(view);
            bVar.f856a.setHeading(boolD == null ? false : boolD.booleanValue());
            bVar.f856a.setPaneTitle(q.g(view));
            CharSequence charSequenceD = new o(a.h.a.tag_state_description, CharSequence.class, 64, 30).d(view);
            boolean z = Build.VERSION.SDK_INT >= 30;
            AccessibilityNodeInfo accessibilityNodeInfo2 = bVar.f856a;
            if (z) {
                accessibilityNodeInfo2.setStateDescription(charSequenceD);
            } else {
                accessibilityNodeInfo2.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequenceD);
            }
            this.f809a.d(view, bVar);
            accessibilityNodeInfo.getText();
            List listEmptyList = (List) view.getTag(a.h.a.tag_accessibility_actions);
            if (listEmptyList == null) {
                listEmptyList = Collections.emptyList();
            }
            for (int i = 0; i < listEmptyList.size(); i++) {
                bVar.a((b.a) listEmptyList.get(i));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f809a.e(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f809a.f(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f809a.g(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.f809a.h(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f809a.i(view, accessibilityEvent);
        }
    }

    public a() {
        this.f807a = f806c;
        this.f808b = new C0015a(this);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f807a = accessibilityDelegate;
        this.f808b = new C0015a(this);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f807a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public a.h.k.x.c b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.f807a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new a.h.k.x.c(accessibilityNodeProvider);
        }
        return null;
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
    }

    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f807a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean g(android.view.View r9, int r10, android.os.Bundle r11) {
        /*
            r8 = this;
            int r0 = a.h.a.tag_accessibility_actions
            java.lang.Object r0 = r9.getTag(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto Le
            java.util.List r0 = java.util.Collections.emptyList()
        Le:
            r1 = 0
            r2 = r1
        L10:
            int r3 = r0.size()
            if (r2 >= r3) goto L6f
            java.lang.Object r3 = r0.get(r2)
            a.h.k.x.b$a r3 = (a.h.k.x.b.a) r3
            int r4 = r3.a()
            if (r4 != r10) goto L6c
            a.h.k.x.d r0 = r3.f864d
            if (r0 == 0) goto L6f
            java.lang.Class<? extends a.h.k.x.d$a> r0 = r3.f863c
            r2 = 0
            if (r0 == 0) goto L65
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L3f
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r4)     // Catch: java.lang.Exception -> L3f
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L3f
            java.lang.Object r0 = r0.newInstance(r4)     // Catch: java.lang.Exception -> L3f
            a.h.k.x.d$a r0 = (a.h.k.x.d.a) r0     // Catch: java.lang.Exception -> L3f
            if (r0 == 0) goto L3c
            goto L64
        L3c:
            throw r2     // Catch: java.lang.Exception -> L3d
        L3d:
            r2 = move-exception
            goto L43
        L3f:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
        L43:
            java.lang.Class<? extends a.h.k.x.d$a> r4 = r3.f863c
            if (r4 != 0) goto L4a
            java.lang.String r4 = "null"
            goto L4e
        L4a:
            java.lang.String r4 = r4.getName()
        L4e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed to execute command with argument class ViewCommandArgument: "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "A11yActionCompat"
            android.util.Log.e(r5, r4, r2)
        L64:
            r2 = r0
        L65:
            a.h.k.x.d r0 = r3.f864d
            boolean r0 = r0.a(r9, r2)
            goto L70
        L6c:
            int r2 = r2 + 1
            goto L10
        L6f:
            r0 = r1
        L70:
            if (r0 != 0) goto L78
            android.view.View$AccessibilityDelegate r8 = r8.f807a
            boolean r0 = r8.performAccessibilityAction(r9, r10, r11)
        L78:
            if (r0 != 0) goto Lc7
            int r8 = a.h.a.accessibility_action_clickable_span
            if (r10 != r8) goto Lc7
            r8 = -1
            java.lang.String r10 = "ACCESSIBILITY_CLICKABLE_SPAN_ID"
            int r8 = r11.getInt(r10, r8)
            int r10 = a.h.a.tag_accessibility_clickable_spans
            java.lang.Object r10 = r9.getTag(r10)
            android.util.SparseArray r10 = (android.util.SparseArray) r10
            r11 = 1
            if (r10 == 0) goto Lc6
            java.lang.Object r8 = r10.get(r8)
            java.lang.ref.WeakReference r8 = (java.lang.ref.WeakReference) r8
            if (r8 == 0) goto Lc6
            java.lang.Object r8 = r8.get()
            android.text.style.ClickableSpan r8 = (android.text.style.ClickableSpan) r8
            if (r8 == 0) goto Lbf
            android.view.accessibility.AccessibilityNodeInfo r10 = r9.createAccessibilityNodeInfo()
            java.lang.CharSequence r10 = r10.getText()
            android.text.style.ClickableSpan[] r10 = a.h.k.x.b.d(r10)
            r0 = r1
        Lad:
            if (r10 == 0) goto Lbf
            int r2 = r10.length
            if (r0 >= r2) goto Lbf
            r2 = r10[r0]
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto Lbc
            r10 = r11
            goto Lc0
        Lbc:
            int r0 = r0 + 1
            goto Lad
        Lbf:
            r10 = r1
        Lc0:
            if (r10 == 0) goto Lc6
            r8.onClick(r9)
            r1 = r11
        Lc6:
            r0 = r1
        Lc7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.k.a.g(android.view.View, int, android.os.Bundle):boolean");
    }

    public void h(View view, int i) {
        this.f807a.sendAccessibilityEvent(view, i);
    }

    public void i(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
