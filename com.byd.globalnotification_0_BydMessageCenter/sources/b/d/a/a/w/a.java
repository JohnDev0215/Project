package b.d.a.a.w;

import a.b.k.l;
import android.content.Context;
import b.d.a.a.b;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f2132d;

    public a(Context context) {
        this.f2129a = l.i.i0(context, b.elevationOverlayEnabled, false);
        this.f2130b = l.i.w(context, b.elevationOverlayColor, 0);
        this.f2131c = l.i.w(context, b.colorSurface, 0);
        this.f2132d = context.getResources().getDisplayMetrics().density;
    }
}
