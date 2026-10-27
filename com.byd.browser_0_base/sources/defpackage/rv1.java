package defpackage;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: Palette.java */
/* JADX INFO: loaded from: classes.dex */
public final class rv1 {
    public static final a f = new a();
    public final List<d> a;
    public final List<q63> b;
    public final d e;
    public final SparseBooleanArray d = new SparseBooleanArray();
    public final wm2<q63, d> c = new wm2<>();

    /* JADX INFO: compiled from: Palette.java */
    public class a implements c {
        @Override // rv1.c
        public final boolean a(float[] fArr) {
            float f = fArr[2];
            if (!(f >= 0.95f)) {
                if (!(f <= 0.05f)) {
                    float f2 = fArr[0];
                    if (!(f2 >= 10.0f && f2 <= 37.0f && fArr[1] <= 0.82f)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: Palette.java */
    public static final class b {
        public final Bitmap a;
        public final ArrayList b;
        public int c;
        public final int d;
        public final int e;
        public final ArrayList f;

        public b(Bitmap bitmap) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            this.c = 16;
            this.d = 12544;
            this.e = -1;
            ArrayList arrayList2 = new ArrayList();
            this.f = arrayList2;
            if (bitmap.isRecycled()) {
                throw new IllegalArgumentException("Bitmap is not valid");
            }
            arrayList2.add(rv1.f);
            this.a = bitmap;
            arrayList.add(q63.e);
            arrayList.add(q63.f);
            arrayList.add(q63.g);
            arrayList.add(q63.h);
            arrayList.add(q63.i);
            arrayList.add(q63.j);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0114  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final defpackage.rv1 a() {
            /*
                Method dump skipped, instruction units count: 443
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rv1.b.a():rv1");
        }
    }

    /* JADX INFO: compiled from: Palette.java */
    public interface c {
        boolean a(float[] fArr);
    }

    /* JADX INFO: compiled from: Palette.java */
    public static final class d {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public boolean f;
        public int g;
        public int h;
        public float[] i;

        public d(int i, int i2) {
            this.a = Color.red(i);
            this.b = Color.green(i);
            this.c = Color.blue(i);
            this.d = i;
            this.e = i2;
        }

        public final void a() {
            if (this.f) {
                return;
            }
            int i = this.d;
            int iE = gv.e(-1, i, 4.5f);
            int iE2 = gv.e(-1, i, 3.0f);
            if (iE != -1 && iE2 != -1) {
                this.h = gv.g(-1, iE);
                this.g = gv.g(-1, iE2);
                this.f = true;
                return;
            }
            int iE3 = gv.e(-16777216, i, 4.5f);
            int iE4 = gv.e(-16777216, i, 3.0f);
            if (iE3 == -1 || iE4 == -1) {
                this.h = iE != -1 ? gv.g(-1, iE) : gv.g(-16777216, iE3);
                this.g = iE2 != -1 ? gv.g(-1, iE2) : gv.g(-16777216, iE4);
                this.f = true;
            } else {
                this.h = gv.g(-16777216, iE3);
                this.g = gv.g(-16777216, iE4);
                this.f = true;
            }
        }

        public final float[] b() {
            if (this.i == null) {
                this.i = new float[3];
            }
            gv.a(this.a, this.b, this.c, this.i);
            return this.i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || d.class != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            return this.e == dVar.e && this.d == dVar.d;
        }

        public final int hashCode() {
            return (this.d * 31) + this.e;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(d.class.getSimpleName());
            sb.append(" [RGB: #");
            sb.append(Integer.toHexString(this.d));
            sb.append("] [HSL: ");
            sb.append(Arrays.toString(b()));
            sb.append("] [Population: ");
            sb.append(this.e);
            sb.append("] [Title Text: #");
            a();
            sb.append(Integer.toHexString(this.g));
            sb.append("] [Body Text: #");
            a();
            sb.append(Integer.toHexString(this.h));
            sb.append(']');
            return sb.toString();
        }
    }

    public rv1(ArrayList arrayList, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = arrayList2;
        int size = arrayList.size();
        int i = Integer.MIN_VALUE;
        d dVar = null;
        for (int i2 = 0; i2 < size; i2++) {
            d dVar2 = (d) arrayList.get(i2);
            int i3 = dVar2.e;
            if (i3 > i) {
                dVar = dVar2;
                i = i3;
            }
        }
        this.e = dVar;
    }
}
