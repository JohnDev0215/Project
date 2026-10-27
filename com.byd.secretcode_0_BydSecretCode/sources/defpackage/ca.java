package defpackage;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ca extends ClickableSpan {
    public final int b;
    public final ea c;
    public final int d;

    public ca(int i, ea eaVar, int i2) {
        this.b = i;
        this.c = eaVar;
        this.d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.b);
        this.c.Q(this.d, bundle);
    }
}
