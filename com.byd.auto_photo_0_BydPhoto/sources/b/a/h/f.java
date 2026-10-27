package b.a.h;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    l f485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f487c;
    int d;
    int e;
    int f;
    boolean g;
    float h;
    Bitmap.Config i;
    String j;
    int k;
    boolean l;
    int m;

    f(l lVar) {
        this.f485a = lVar;
    }

    void a() {
        String str;
        if (this.g) {
            str = this.f486b + this.f487c + this.d;
        } else {
            str = this.f486b;
        }
        this.j = str;
    }

    public void b(ImageView imageView) {
        this.f485a.v(imageView, this);
    }

    public f c(String str) {
        this.f486b = str;
        return this;
    }

    f d() {
        this.f486b = null;
        this.f487c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = 0.0f;
        this.i = null;
        this.j = null;
        this.k = 0;
        this.l = false;
        return this;
    }

    public f e(Bitmap.Config config) {
        this.i = config;
        return this;
    }

    public f f(boolean z) {
        this.l = z;
        return this;
    }

    public f g(int i) {
        this.e = i;
        return this;
    }

    public f h(int i) {
        this.m = i;
        return this;
    }

    public f i(int i) {
        this.f = i;
        return this;
    }

    public f j(int i, int i2) {
        this.f487c = i;
        this.d = i2;
        return this;
    }

    public f k(boolean z) {
        this.g = z;
        return this;
    }
}
