package defpackage;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: u, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0212u extends ClickableSpan {
    public final int e;
    public final AccessibilityNodeInfoCompat f;
    public final int g;

    public C0212u(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2) {
        this.e = i;
        this.f = accessibilityNodeInfoCompat;
        this.g = i2;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.e);
        this.f.a.performAction(this.g, bundle);
    }
}
