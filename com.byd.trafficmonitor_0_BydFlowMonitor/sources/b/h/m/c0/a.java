package b.h.m.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f2606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2607d;

    public a(int i2, c cVar, int i3) {
        this.f2605b = i2;
        this.f2606c = cVar;
        this.f2607d = i3;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f2605b);
        this.f2606c.R(this.f2607d, bundle);
    }
}
