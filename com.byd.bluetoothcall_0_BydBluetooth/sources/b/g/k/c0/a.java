package b.g.k.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f2320c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2321d;

    public a(int i, c cVar, int i2) {
        this.f2319b = i;
        this.f2320c = cVar;
        this.f2321d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f2319b);
        this.f2320c.R(this.f2321d, bundle);
    }
}
