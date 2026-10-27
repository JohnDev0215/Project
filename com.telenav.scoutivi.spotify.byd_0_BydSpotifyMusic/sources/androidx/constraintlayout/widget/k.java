package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: StateSet.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1883a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1884b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1885c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f1886d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<d> f1887e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1888a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1889b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1890c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1891d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1890c = -1;
            this.f1891d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.State_android_id) {
                    this.f1888a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1888a);
                } else if (index == i.State_constraints) {
                    this.f1890c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1890c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1890c);
                    context.getResources().getResourceName(this.f1890c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1891d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1889b.add(bVar);
        }

        public int b(float f7, float f8) {
            for (int i7 = 0; i7 < this.f1889b.size(); i7++) {
                if (this.f1889b.get(i7).a(f7, f8)) {
                    return i7;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1892a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1894c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1895d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1896e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f1897f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1892a = Float.NaN;
            this.f1893b = Float.NaN;
            this.f1894c = Float.NaN;
            this.f1895d = Float.NaN;
            this.f1896e = -1;
            this.f1897f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.Variant_constraints) {
                    this.f1896e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1896e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1896e);
                    context.getResources().getResourceName(this.f1896e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1897f = true;
                    }
                } else if (index == i.Variant_region_heightLessThan) {
                    this.f1895d = typedArrayObtainStyledAttributes.getDimension(index, this.f1895d);
                } else if (index == i.Variant_region_heightMoreThan) {
                    this.f1893b = typedArrayObtainStyledAttributes.getDimension(index, this.f1893b);
                } else if (index == i.Variant_region_widthLessThan) {
                    this.f1894c = typedArrayObtainStyledAttributes.getDimension(index, this.f1894c);
                } else if (index == i.Variant_region_widthMoreThan) {
                    this.f1892a = typedArrayObtainStyledAttributes.getDimension(index, this.f1892a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f7, float f8) {
            if (!Float.isNaN(this.f1892a) && f7 < this.f1892a) {
                return false;
            }
            if (!Float.isNaN(this.f1893b) && f8 < this.f1893b) {
                return false;
            }
            if (Float.isNaN(this.f1894c) || f7 <= this.f1894c) {
                return Float.isNaN(this.f1895d) || f8 <= this.f1895d;
            }
            return false;
        }
    }

    public k(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            r9 = this;
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)
            int[] r1 = androidx.constraintlayout.widget.i.StateSet
            android.content.res.TypedArray r0 = r10.obtainStyledAttributes(r0, r1)
            int r1 = r0.getIndexCount()
            r2 = 0
            r3 = r2
        L10:
            if (r3 >= r1) goto L25
            int r4 = r0.getIndex(r3)
            int r5 = androidx.constraintlayout.widget.i.StateSet_defaultState
            if (r4 != r5) goto L22
            int r5 = r9.f1883a
            int r4 = r0.getResourceId(r4, r5)
            r9.f1883a = r4
        L22:
            int r3 = r3 + 1
            goto L10
        L25:
            r0.recycle()
            r0 = 0
            int r1 = r11.getEventType()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L2d:
            r3 = 1
            if (r1 == r3) goto La8
            if (r1 == 0) goto L97
            java.lang.String r4 = "StateSet"
            r5 = 3
            r6 = 2
            if (r1 == r6) goto L47
            if (r1 == r5) goto L3c
            goto L9a
        L3c:
            java.lang.String r1 = r11.getName()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            boolean r1 = r4.equals(r1)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L9a
            return
        L47:
            java.lang.String r1 = r11.getName()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r7 = -1
            int r8 = r1.hashCode()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            switch(r8) {
                case 80204913: goto L6f;
                case 1301459538: goto L65;
                case 1382829617: goto L5e;
                case 1901439077: goto L54;
                default: goto L53;
            }     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L53:
            goto L79
        L54:
            java.lang.String r3 = "Variant"
            boolean r1 = r1.equals(r3)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L79
            r3 = r5
            goto L7a
        L5e:
            boolean r1 = r1.equals(r4)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L79
            goto L7a
        L65:
            java.lang.String r3 = "LayoutDescription"
            boolean r1 = r1.equals(r3)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L79
            r3 = r2
            goto L7a
        L6f:
            java.lang.String r3 = "State"
            boolean r1 = r1.equals(r3)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L79
            r3 = r6
            goto L7a
        L79:
            r3 = r7
        L7a:
            if (r3 == r6) goto L8a
            if (r3 == r5) goto L7f
            goto L9a
        L7f:
            androidx.constraintlayout.widget.k$b r1 = new androidx.constraintlayout.widget.k$b     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r1.<init>(r10, r11)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r0 == 0) goto L9a
            r0.a(r1)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L9a
        L8a:
            androidx.constraintlayout.widget.k$a r0 = new androidx.constraintlayout.widget.k$a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r0.<init>(r10, r11)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            android.util.SparseArray<androidx.constraintlayout.widget.k$a> r1 = r9.f1886d     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            int r3 = r0.f1888a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r1.put(r3, r0)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L9a
        L97:
            r11.getName()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L9a:
            int r1 = r11.next()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L2d
        L9f:
            r10 = move-exception
            r10.printStackTrace()
            goto La8
        La4:
            r10 = move-exception
            r10.printStackTrace()
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.k.b(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public int a(int i7, int i8, float f7, float f8) {
        a aVar = this.f1886d.get(i8);
        if (aVar == null) {
            return i8;
        }
        if (f7 == -1.0f || f8 == -1.0f) {
            if (aVar.f1890c == i7) {
                return i7;
            }
            Iterator<b> it = aVar.f1889b.iterator();
            while (it.hasNext()) {
                if (i7 == it.next().f1896e) {
                    return i7;
                }
            }
            return aVar.f1890c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f1889b) {
            if (bVar2.a(f7, f8)) {
                if (i7 == bVar2.f1896e) {
                    return i7;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f1896e : aVar.f1890c;
    }

    public int c(int i7, int i8, int i9) {
        return d(-1, i7, i8, i9);
    }

    public int d(int i7, int i8, float f7, float f8) {
        int iB;
        if (i7 == i8) {
            a aVarValueAt = i8 == -1 ? this.f1886d.valueAt(0) : this.f1886d.get(this.f1884b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f1885c == -1 || !aVarValueAt.f1889b.get(i7).a(f7, f8)) && i7 != (iB = aVarValueAt.b(f7, f8))) ? iB == -1 ? aVarValueAt.f1890c : aVarValueAt.f1889b.get(iB).f1896e : i7;
        }
        a aVar = this.f1886d.get(i8);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f7, f8);
        return iB2 == -1 ? aVar.f1890c : aVar.f1889b.get(iB2).f1896e;
    }
}
