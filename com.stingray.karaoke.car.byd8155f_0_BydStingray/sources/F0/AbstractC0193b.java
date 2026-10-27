package F0;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import java.io.Serializable;

/* JADX INFO: renamed from: F0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0193b implements k4.r {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Serializable f2176m;

    /* JADX WARN: Type inference failed for: r0v1, types: [int[], java.io.Serializable] */
    public AbstractC0193b() {
        this.f2176m = new int[2];
    }

    @Override // k4.r
    public k4.q A(k4.w wVar) {
        Class cls = (Class) this.f2176m;
        return new l4.d((Context) this.l, wVar.b(File.class, cls), wVar.b(Uri.class, cls), cls);
    }

    public abstract int[] a(int i7);

    public int[] b(int i7, int i10) {
        if (i7 < 0 || i10 < 0 || i7 == i10) {
            return null;
        }
        int[] iArr = (int[]) this.f2176m;
        iArr[0] = i7;
        iArr[1] = i10;
        return iArr;
    }

    public String c() {
        String str = (String) this.l;
        if (str != null) {
            return str;
        }
        A7.m.j("text");
        throw null;
    }

    public abstract int[] d(int i7);

    public AbstractC0193b(Context context, Class cls) {
        this.l = context;
        this.f2176m = cls;
    }
}
