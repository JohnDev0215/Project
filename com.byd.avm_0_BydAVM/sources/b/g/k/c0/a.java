package b.g.k.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1727b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f1728c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1729d;

    public a(int i, c cVar, int i2) {
        this.f1727b = i;
        this.f1728c = cVar;
        this.f1729d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f1727b);
        this.f1728c.Q(this.f1729d, bundle);
    }
}
