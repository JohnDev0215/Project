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
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1114a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1115b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1116c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f1117d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.b> f1118e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1119a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1120b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1121c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1122d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1121c = -1;
            this.f1122d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Xa);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.Ya) {
                    this.f1119a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1119a);
                } else if (index == e.Za) {
                    this.f1121c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1121c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1121c);
                    context.getResources().getResourceName(this.f1121c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1122d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1120b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i = 0; i < this.f1120b.size(); i++) {
                if (this.f1120b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1124b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1125c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1126d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1127e;
        boolean f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1123a = Float.NaN;
            this.f1124b = Float.NaN;
            this.f1125c = Float.NaN;
            this.f1126d = Float.NaN;
            this.f1127e = -1;
            this.f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Hb);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.Ib) {
                    this.f1127e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1127e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1127e);
                    context.getResources().getResourceName(this.f1127e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f = true;
                    }
                } else if (index == e.Jb) {
                    this.f1126d = typedArrayObtainStyledAttributes.getDimension(index, this.f1126d);
                } else if (index == e.Kb) {
                    this.f1124b = typedArrayObtainStyledAttributes.getDimension(index, this.f1124b);
                } else if (index == e.Lb) {
                    this.f1125c = typedArrayObtainStyledAttributes.getDimension(index, this.f1125c);
                } else if (index == e.Mb) {
                    this.f1123a = typedArrayObtainStyledAttributes.getDimension(index, this.f1123a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.f1123a) && f < this.f1123a) {
                return false;
            }
            if (!Float.isNaN(this.f1124b) && f2 < this.f1124b) {
                return false;
            }
            if (Float.isNaN(this.f1125c) || f <= this.f1125c) {
                return Float.isNaN(this.f1126d) || f2 <= this.f1126d;
            }
            return false;
        }
    }

    public g(Context context, XmlPullParser xmlPullParser) {
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
            int[] r1 = androidx.constraintlayout.widget.e.cb
            android.content.res.TypedArray r0 = r10.obtainStyledAttributes(r0, r1)
            int r1 = r0.getIndexCount()
            r2 = 0
            r3 = r2
        L10:
            if (r3 >= r1) goto L25
            int r4 = r0.getIndex(r3)
            int r5 = androidx.constraintlayout.widget.e.db
            if (r4 != r5) goto L22
            int r5 = r9.f1114a
            int r4 = r0.getResourceId(r4, r5)
            r9.f1114a = r4
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
            androidx.constraintlayout.widget.g$b r1 = new androidx.constraintlayout.widget.g$b     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r1.<init>(r10, r11)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r0 == 0) goto L9a
            r0.a(r1)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L9a
        L8a:
            androidx.constraintlayout.widget.g$a r0 = new androidx.constraintlayout.widget.g$a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r0.<init>(r10, r11)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            android.util.SparseArray<androidx.constraintlayout.widget.g$a> r1 = r9.f1117d     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            int r3 = r0.f1119a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.g.b(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public int a(int i, int i2, float f, float f2) {
        a aVar = this.f1117d.get(i2);
        if (aVar == null) {
            return i2;
        }
        if (f == -1.0f || f2 == -1.0f) {
            if (aVar.f1121c == i) {
                return i;
            }
            Iterator<b> it = aVar.f1120b.iterator();
            while (it.hasNext()) {
                if (i == it.next().f1127e) {
                    return i;
                }
            }
            return aVar.f1121c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f1120b) {
            if (bVar2.a(f, f2)) {
                if (i == bVar2.f1127e) {
                    return i;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f1127e : aVar.f1121c;
    }

    public int c(int i, int i2, int i3) {
        return d(-1, i, i2, i3);
    }

    public int d(int i, int i2, float f, float f2) {
        int iB;
        if (i == i2) {
            a aVarValueAt = i2 == -1 ? this.f1117d.valueAt(0) : this.f1117d.get(this.f1115b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f1116c == -1 || !aVarValueAt.f1120b.get(i).a(f, f2)) && i != (iB = aVarValueAt.b(f, f2))) ? iB == -1 ? aVarValueAt.f1121c : aVarValueAt.f1120b.get(iB).f1127e : i;
        }
        a aVar = this.f1117d.get(i2);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f, f2);
        return iB2 == -1 ? aVar.f1121c : aVar.f1120b.get(iB2).f1127e;
    }
}
