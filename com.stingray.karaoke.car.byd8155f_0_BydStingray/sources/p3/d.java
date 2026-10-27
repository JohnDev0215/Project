package p3;

import android.graphics.Color;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20879b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f20880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f20881f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20882g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20883h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f20884i;

    public d(int i7, int i10) {
        this.f20878a = Color.red(i7);
        this.f20879b = Color.green(i7);
        this.c = Color.blue(i7);
        this.d = i7;
        this.f20880e = i10;
    }

    public final void a() {
        if (this.f20881f) {
            return;
        }
        int i7 = this.d;
        int iE = y1.a.e(-1, i7, 4.5f);
        int iE2 = y1.a.e(-1, i7, 3.0f);
        if (iE != -1 && iE2 != -1) {
            this.f20883h = y1.a.h(-1, iE);
            this.f20882g = y1.a.h(-1, iE2);
            this.f20881f = true;
            return;
        }
        int iE3 = y1.a.e(-16777216, i7, 4.5f);
        int iE4 = y1.a.e(-16777216, i7, 3.0f);
        if (iE3 == -1 || iE4 == -1) {
            this.f20883h = iE != -1 ? y1.a.h(-1, iE) : y1.a.h(-16777216, iE3);
            this.f20882g = iE2 != -1 ? y1.a.h(-1, iE2) : y1.a.h(-16777216, iE4);
            this.f20881f = true;
        } else {
            this.f20883h = y1.a.h(-16777216, iE3);
            this.f20882g = y1.a.h(-16777216, iE4);
            this.f20881f = true;
        }
    }

    public final float[] b() {
        if (this.f20884i == null) {
            this.f20884i = new float[3];
        }
        y1.a.a(this.f20878a, this.f20879b, this.c, this.f20884i);
        return this.f20884i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        return this.f20880e == dVar.f20880e && this.d == dVar.d;
    }

    public final int hashCode() {
        return (this.d * 31) + this.f20880e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(d.class.getSimpleName());
        sb.append(" [RGB: #");
        sb.append(Integer.toHexString(this.d));
        sb.append("] [HSL: ");
        sb.append(Arrays.toString(b()));
        sb.append("] [Population: ");
        sb.append(this.f20880e);
        sb.append("] [Title Text: #");
        a();
        sb.append(Integer.toHexString(this.f20882g));
        sb.append("] [Body Text: #");
        a();
        sb.append(Integer.toHexString(this.f20883h));
        sb.append(']');
        return sb.toString();
    }
}
