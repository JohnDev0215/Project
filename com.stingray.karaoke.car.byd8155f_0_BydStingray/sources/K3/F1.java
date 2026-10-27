package k3;

import a.AbstractC0904a;
import android.content.ComponentName;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class F1 implements D1 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f17763k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f17764m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f17765n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f17766p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f17767q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f17768r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f17769s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f17770t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17772b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f17773e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f17774f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ComponentName f17775g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final IBinder f17776h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Bundle f17777i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final MediaSession.Token f17778j;

    static {
        int i7 = AbstractC1343A.f15195a;
        f17763k = Integer.toString(0, 36);
        l = Integer.toString(1, 36);
        f17764m = Integer.toString(2, 36);
        f17765n = Integer.toString(3, 36);
        o = Integer.toString(4, 36);
        f17766p = Integer.toString(5, 36);
        f17767q = Integer.toString(6, 36);
        f17768r = Integer.toString(7, 36);
        f17769s = Integer.toString(8, 36);
        f17770t = Integer.toString(9, 36);
    }

    public F1(int i7, int i10, int i11, int i12, String str, String str2, ComponentName componentName, IBinder iBinder, Bundle bundle, MediaSession.Token token) {
        this.f17771a = i7;
        this.f17772b = i10;
        this.c = i11;
        this.d = i12;
        this.f17773e = str;
        this.f17774f = str2;
        this.f17775g = componentName;
        this.f17776h = iBinder;
        this.f17777i = bundle;
        this.f17778j = token;
    }

    @Override // k3.D1
    public final int a() {
        return this.f17771a;
    }

    @Override // k3.D1
    public final int b() {
        return this.f17772b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof F1)) {
            return false;
        }
        F1 f12 = (F1) obj;
        return this.f17771a == f12.f17771a && this.f17772b == f12.f17772b && this.c == f12.c && this.d == f12.d && TextUtils.equals(this.f17773e, f12.f17773e) && TextUtils.equals(this.f17774f, f12.f17774f) && AbstractC0904a.A(this.f17775g, f12.f17775g) && AbstractC0904a.A(this.f17776h, f12.f17776h) && AbstractC0904a.A(this.f17778j, f12.f17778j);
    }

    @Override // k3.D1
    public final String getPackageName() {
        return this.f17773e;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f17771a), Integer.valueOf(this.f17772b), Integer.valueOf(this.c), Integer.valueOf(this.d), this.f17773e, this.f17774f, this.f17775g, this.f17776h, this.f17778j});
    }

    @Override // k3.D1
    public final Bundle m() {
        return new Bundle(this.f17777i);
    }

    @Override // k3.D1
    public final boolean n() {
        return false;
    }

    @Override // k3.D1
    public final ComponentName o() {
        return this.f17775g;
    }

    @Override // k3.D1
    public final Object p() {
        return this.f17776h;
    }

    @Override // k3.D1
    public final String q() {
        return this.f17774f;
    }

    @Override // k3.D1
    public final int r() {
        return this.d;
    }

    @Override // k3.D1
    public final Bundle s() {
        Bundle bundle = new Bundle();
        bundle.putInt(f17763k, this.f17771a);
        bundle.putInt(l, this.f17772b);
        bundle.putInt(f17764m, this.c);
        bundle.putString(f17765n, this.f17773e);
        bundle.putString(o, this.f17774f);
        bundle.putBinder(f17767q, this.f17776h);
        bundle.putParcelable(f17766p, this.f17775g);
        bundle.putBundle(f17768r, this.f17777i);
        bundle.putInt(f17769s, this.d);
        MediaSession.Token token = this.f17778j;
        if (token != null) {
            bundle.putParcelable(f17770t, token);
        }
        return bundle;
    }

    @Override // k3.D1
    public final MediaSession.Token t() {
        return this.f17778j;
    }

    public final String toString() {
        return "SessionToken {pkg=" + this.f17773e + " type=" + this.f17772b + " libraryVersion=" + this.c + " interfaceVersion=" + this.d + " service=" + this.f17774f + " IMediaSession=" + this.f17776h + " extras=" + this.f17777i + "}";
    }
}
