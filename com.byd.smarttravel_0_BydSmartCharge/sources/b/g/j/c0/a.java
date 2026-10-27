package b.g.j.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1112d;

    public a(int i, c cVar, int i2) {
        this.f1110b = i;
        this.f1111c = cVar;
        this.f1112d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f1110b);
        this.f1111c.Q(this.f1112d, bundle);
    }
}
