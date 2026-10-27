package a.g.k.c0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f392c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f393d;

    public a(int i, c cVar, int i2) {
        this.f391b = i;
        this.f392c = cVar;
        this.f393d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f391b);
        this.f392c.N(this.f393d, bundle);
    }
}
