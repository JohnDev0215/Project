package b.f.m.b0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1755c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f1756d;
    public final int s;

    public a(int i2, c cVar, int i3) {
        this.f1755c = i2;
        this.f1756d = cVar;
        this.s = i3;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f1755c);
        this.f1756d.K(this.s, bundle);
    }
}
