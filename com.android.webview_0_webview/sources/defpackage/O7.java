package defpackage;

import android.graphics.Matrix;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class O7 extends P7 {
    public final Matrix a;
    public final ArrayList b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public final Matrix j;
    public int k;
    public int[] l;
    public String m;

    public O7(O7 o7, C0971j1 c0971j1) {
        Q7 m7;
        super(null);
        this.a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = new Matrix();
        this.m = null;
        this.c = o7.c;
        this.d = o7.d;
        this.e = o7.e;
        this.f = o7.f;
        this.g = o7.g;
        this.h = o7.h;
        this.i = o7.i;
        this.l = o7.l;
        String str = o7.m;
        this.m = str;
        this.k = o7.k;
        if (str != null) {
            c0971j1.put(str, this);
        }
        this.j.set(o7.j);
        ArrayList arrayList = o7.b;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof O7) {
                this.b.add(new O7((O7) obj, c0971j1));
            } else {
                if (obj instanceof N7) {
                    m7 = new N7((N7) obj);
                } else {
                    if (!(obj instanceof M7)) {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    m7 = new M7((M7) obj);
                }
                this.b.add(m7);
                Object obj2 = m7.b;
                if (obj2 != null) {
                    c0971j1.put(obj2, m7);
                }
            }
        }
    }

    @Override // defpackage.P7
    public boolean a() {
        for (int i = 0; i < this.b.size(); i++) {
            if (((P7) this.b.get(i)).a()) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.P7
    public boolean b(int[] iArr) {
        boolean zB = false;
        for (int i = 0; i < this.b.size(); i++) {
            zB |= ((P7) this.b.get(i)).b(iArr);
        }
        return zB;
    }

    public final void c() {
        this.j.reset();
        this.j.postTranslate(-this.d, -this.e);
        this.j.postScale(this.f, this.g);
        this.j.postRotate(this.c, 0.0f, 0.0f);
        this.j.postTranslate(this.h + this.d, this.i + this.e);
    }

    public String getGroupName() {
        return this.m;
    }

    public Matrix getLocalMatrix() {
        return this.j;
    }

    public float getPivotX() {
        return this.d;
    }

    public float getPivotY() {
        return this.e;
    }

    public float getRotation() {
        return this.c;
    }

    public float getScaleX() {
        return this.f;
    }

    public float getScaleY() {
        return this.g;
    }

    public float getTranslateX() {
        return this.h;
    }

    public float getTranslateY() {
        return this.i;
    }

    public void setPivotX(float f) {
        if (f != this.d) {
            this.d = f;
            c();
        }
    }

    public void setPivotY(float f) {
        if (f != this.e) {
            this.e = f;
            c();
        }
    }

    public void setRotation(float f) {
        if (f != this.c) {
            this.c = f;
            c();
        }
    }

    public void setScaleX(float f) {
        if (f != this.f) {
            this.f = f;
            c();
        }
    }

    public void setScaleY(float f) {
        if (f != this.g) {
            this.g = f;
            c();
        }
    }

    public void setTranslateX(float f) {
        if (f != this.h) {
            this.h = f;
            c();
        }
    }

    public void setTranslateY(float f) {
        if (f != this.i) {
            this.i = f;
            c();
        }
    }

    public O7() {
        super(null);
        this.a = new Matrix();
        this.b = new ArrayList();
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = new Matrix();
        this.m = null;
    }
}
