package b.a.k;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Context f592a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Uri f593b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String[] f594c;

    public r(Context context) {
        this.f592a = context;
    }

    protected abstract String[] a();

    protected abstract Uri b();

    public void c(s sVar) {
        if (this.f593b == null) {
            this.f593b = b();
        }
        if (this.f594c == null) {
            this.f594c = a();
        }
    }
}
