package a.h.k.x;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f853a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f854b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f855c;

    public a(int i, b bVar, int i2) {
        this.f853a = i;
        this.f854b = bVar;
        this.f855c = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f853a);
        b bVar = this.f854b;
        bVar.f856a.performAction(this.f855c, bundle);
    }
}
