package p0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6961d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f6962e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6963f;

    public a(int i8, c cVar, int i9) {
        this.f6961d = i8;
        this.f6962e = cVar;
        this.f6963f = i9;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f6961d);
        this.f6962e.R(this.f6963f, bundle);
    }
}
