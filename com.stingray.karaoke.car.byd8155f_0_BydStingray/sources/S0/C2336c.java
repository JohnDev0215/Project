package s0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: s0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2336c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f22004b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22005e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22006f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f22007g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22008h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f22009i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f22010j;

    public C2336c(String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List list, int i7) {
        str = (i7 & 1) != 0 ? "" : str;
        f10 = (i7 & 2) != 0 ? 0.0f : f10;
        f11 = (i7 & 4) != 0 ? 0.0f : f11;
        f12 = (i7 & 8) != 0 ? 0.0f : f12;
        f13 = (i7 & 16) != 0 ? 1.0f : f13;
        f14 = (i7 & 32) != 0 ? 1.0f : f14;
        f15 = (i7 & 64) != 0 ? 0.0f : f15;
        f16 = (i7 & 128) != 0 ? 0.0f : f16;
        if ((i7 & 256) != 0) {
            int i10 = AbstractC2331E.f21974a;
            list = n7.u.l;
        }
        ArrayList arrayList = new ArrayList();
        this.f22003a = str;
        this.f22004b = f10;
        this.c = f11;
        this.d = f12;
        this.f22005e = f13;
        this.f22006f = f14;
        this.f22007g = f15;
        this.f22008h = f16;
        this.f22009i = list;
        this.f22010j = arrayList;
    }
}
