package G1;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e f2632m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2633n;

    public a(int i7, e eVar, int i10) {
        this.l = i7;
        this.f2632m = eVar;
        this.f2633n = i10;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.l);
        this.f2632m.f2646a.performAction(this.f2633n, bundle);
    }
}
