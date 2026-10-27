package m0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3795b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f3796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3797d;

    public a(int i3, c cVar, int i4) {
        this.f3795b = i3;
        this.f3796c = cVar;
        this.f3797d = i4;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f3795b);
        this.f3796c.Q(this.f3797d, bundle);
    }
}
