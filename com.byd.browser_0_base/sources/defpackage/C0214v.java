package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R$id;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.om3;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: v, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class C0214v {
    public static final View.AccessibilityDelegate c = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate a;
    public final a b;

    /* JADX INFO: renamed from: v$a */
    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    public static final class a extends View.AccessibilityDelegate {
        public final C0214v a;

        public a(C0214v c0214v) {
            this.a = c0214v;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            C0180d0 c0180d0B = this.a.b(view);
            if (c0180d0B != null) {
                return (AccessibilityNodeProvider) c0180d0B.e;
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            Object tag;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            Boolean boolValueOf = Boolean.valueOf(om3.m.c(view));
            accessibilityNodeInfo.setScreenReaderFocusable(boolValueOf != null && boolValueOf.booleanValue());
            Boolean boolValueOf2 = Boolean.valueOf(om3.m.b(view));
            accessibilityNodeInfo.setHeading(boolValueOf2 != null && boolValueOf2.booleanValue());
            accessibilityNodeInfo.setPaneTitle(om3.m.a(view));
            int i = R$id.tag_state_description;
            if (Build.VERSION.SDK_INT >= 30) {
                tag = om3.o.a(view);
            } else {
                tag = view.getTag(i);
                if (!CharSequence.class.isInstance(tag)) {
                    tag = null;
                }
            }
            CharSequence charSequence = (CharSequence) tag;
            int i2 = yj.a;
            if (Build.VERSION.SDK_INT >= 30) {
                accessibilityNodeInfo.setStateDescription(charSequence);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
            }
            this.a.d(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfo.getText();
            List listEmptyList = (List) view.getTag(R$id.tag_accessibility_actions);
            if (listEmptyList == null) {
                listEmptyList = Collections.emptyList();
            }
            for (int i3 = 0; i3 < listEmptyList.size(); i3++) {
                accessibilityNodeInfoCompat.b((AccessibilityNodeInfoCompat.a) listEmptyList.get(i3));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.a.e(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.a.f(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.a.g(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void sendAccessibilityEvent(View view, int i) {
            this.a.h(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.a.i(view, accessibilityEvent);
        }
    }

    public C0214v() {
        this(c);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public C0180d0 b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new C0180d0(accessibilityNodeProvider);
        }
        return null;
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
    }

    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean g(android.view.View r6, int r7, android.os.Bundle r8) {
        /*
            r5 = this;
            int r0 = androidx.core.R$id.tag_accessibility_actions
            java.lang.Object r0 = r6.getTag(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto Le
            java.util.List r0 = java.util.Collections.emptyList()
        Le:
            r1 = 0
            r2 = r1
        L10:
            int r3 = r0.size()
            if (r2 >= r3) goto L54
            java.lang.Object r3 = r0.get(r2)
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$a r3 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat.a) r3
            int r4 = r3.a()
            if (r4 != r7) goto L51
            androidx.core.view.accessibility.a r0 = r3.d
            if (r0 == 0) goto L54
            java.lang.Class<? extends androidx.core.view.accessibility.a$a> r2 = r3.c
            if (r2 == 0) goto L4c
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L3c
            java.lang.reflect.Constructor r3 = r2.getDeclaredConstructor(r3)     // Catch: java.lang.Exception -> L3c
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L3c
            java.lang.Object r3 = r3.newInstance(r4)     // Catch: java.lang.Exception -> L3c
            androidx.core.view.accessibility.a$a r3 = (androidx.core.view.accessibility.a.AbstractC0007a) r3     // Catch: java.lang.Exception -> L3c
            r3.getClass()     // Catch: java.lang.Exception -> L3c
            goto L4c
        L3c:
            r3 = move-exception
            java.lang.String r2 = r2.getName()
            java.lang.String r4 = "Failed to execute command with argument class ViewCommandArgument: "
            java.lang.String r2 = r4.concat(r2)
            java.lang.String r4 = "A11yActionCompat"
            android.util.Log.e(r4, r2, r3)
        L4c:
            boolean r0 = r0.f(r6)
            goto L55
        L51:
            int r2 = r2 + 1
            goto L10
        L54:
            r0 = r1
        L55:
            if (r0 != 0) goto L5d
            android.view.View$AccessibilityDelegate r5 = r5.a
            boolean r0 = r5.performAccessibilityAction(r6, r7, r8)
        L5d:
            if (r0 != 0) goto Lbf
            int r5 = androidx.core.R$id.accessibility_action_clickable_span
            if (r7 != r5) goto Lbf
            if (r8 == 0) goto Lbf
            java.lang.String r5 = "ACCESSIBILITY_CLICKABLE_SPAN_ID"
            r7 = -1
            int r5 = r8.getInt(r5, r7)
            int r7 = androidx.core.R$id.tag_accessibility_clickable_spans
            java.lang.Object r7 = r6.getTag(r7)
            android.util.SparseArray r7 = (android.util.SparseArray) r7
            if (r7 == 0) goto Lbe
            java.lang.Object r5 = r7.get(r5)
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
            if (r5 == 0) goto Lbe
            java.lang.Object r5 = r5.get()
            android.text.style.ClickableSpan r5 = (android.text.style.ClickableSpan) r5
            r7 = 1
            if (r5 == 0) goto Lb7
            android.view.accessibility.AccessibilityNodeInfo r8 = r6.createAccessibilityNodeInfo()
            java.lang.CharSequence r8 = r8.getText()
            boolean r0 = r8 instanceof android.text.Spanned
            if (r0 == 0) goto La3
            r0 = r8
            android.text.Spanned r0 = (android.text.Spanned) r0
            int r8 = r8.length()
            java.lang.Class<android.text.style.ClickableSpan> r2 = android.text.style.ClickableSpan.class
            java.lang.Object[] r8 = r0.getSpans(r1, r8, r2)
            android.text.style.ClickableSpan[] r8 = (android.text.style.ClickableSpan[]) r8
            goto La4
        La3:
            r8 = 0
        La4:
            r0 = r1
        La5:
            if (r8 == 0) goto Lb7
            int r2 = r8.length
            if (r0 >= r2) goto Lb7
            r2 = r8[r0]
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto Lb4
            r8 = r7
            goto Lb8
        Lb4:
            int r0 = r0 + 1
            goto La5
        Lb7:
            r8 = r1
        Lb8:
            if (r8 == 0) goto Lbe
            r5.onClick(r6)
            r1 = r7
        Lbe:
            r0 = r1
        Lbf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0214v.g(android.view.View, int, android.os.Bundle):boolean");
    }

    public void h(View view, int i) {
        this.a.sendAccessibilityEvent(view, i);
    }

    public void i(View view, AccessibilityEvent accessibilityEvent) {
        this.a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public C0214v(View.AccessibilityDelegate accessibilityDelegate) {
        this.a = accessibilityDelegate;
        this.b = new a(this);
    }
}
