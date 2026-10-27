package g4;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: ControlAction.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f6526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f6527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f6528c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6529d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final View.OnClickListener f6530e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f6531f;

    a(Resources resources, int i7, int i8, String str, View.OnClickListener onClickListener, boolean z6) {
        this.f6527b = resources;
        this.f6528c = i7;
        this.f6531f = i8;
        this.f6529d = str;
        this.f6530e = onClickListener;
        this.f6526a = z6;
    }

    public Drawable a() {
        int i7 = this.f6528c;
        if (i7 <= 0) {
            return null;
        }
        return y.b.b(this.f6527b, i7, null);
    }

    public int b() {
        return this.f6528c;
    }

    public View.OnClickListener c() {
        return this.f6530e;
    }

    public String d() {
        return this.f6529d;
    }

    public int e() {
        return this.f6531f;
    }

    public boolean f() {
        return this.f6526a;
    }

    public void g(int i7) {
        this.f6531f = i7;
    }

    public void h(boolean z6) {
        this.f6526a = z6;
    }
}
