package io.sentry.android.replay.viewhierarchy;

import A.C0022k;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16456b;
    public final float c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f16457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList f16458f;

    public d(int i7, int i10, float f10, d dVar, boolean z6, Rect rect) {
        this.f16455a = i7;
        this.f16456b = i10;
        this.c = f10;
        this.d = z6;
        this.f16457e = rect;
    }

    public final void a(C0022k c0022k) {
        ArrayList arrayList;
        if (!((Boolean) c0022k.b(this)).booleanValue() || (arrayList = this.f16458f) == null) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(c0022k);
        }
    }
}
