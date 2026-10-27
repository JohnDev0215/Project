package j0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f7107e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c f7108g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f7109h;

    public a(int i7, c cVar, int i8) {
        this.f7107e = i7;
        this.f7108g = cVar;
        this.f7109h = i8;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f7107e);
        this.f7108g.H(this.f7109h, bundle);
    }
}
