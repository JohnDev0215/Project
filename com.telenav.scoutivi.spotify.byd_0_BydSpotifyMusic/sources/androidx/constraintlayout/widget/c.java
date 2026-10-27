package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: ConstraintLayoutStates.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f1749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f1750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1751c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1752d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<a> f1753e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SparseArray<d> f1754f = new SparseArray<>();

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1755a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1756b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1757c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        d f1758d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1757c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.State_android_id) {
                    this.f1755a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1755a);
                } else if (index == i.State_constraints) {
                    this.f1757c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1757c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1757c);
                    context.getResources().getResourceName(this.f1757c);
                    if ("layout".equals(resourceTypeName)) {
                        d dVar = new d();
                        this.f1758d = dVar;
                        dVar.o(context, this.f1757c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1756b.add(bVar);
        }

        public int b(float f7, float f8) {
            for (int i7 = 0; i7 < this.f1756b.size(); i7++) {
                if (this.f1756b.get(i7).a(f7, f8)) {
                    return i7;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1759a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1760b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1761c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1762d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1763e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        d f1764f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1759a = Float.NaN;
            this.f1760b = Float.NaN;
            this.f1761c = Float.NaN;
            this.f1762d = Float.NaN;
            this.f1763e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.Variant_constraints) {
                    this.f1763e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1763e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1763e);
                    context.getResources().getResourceName(this.f1763e);
                    if ("layout".equals(resourceTypeName)) {
                        d dVar = new d();
                        this.f1764f = dVar;
                        dVar.o(context, this.f1763e);
                    }
                } else if (index == i.Variant_region_heightLessThan) {
                    this.f1762d = typedArrayObtainStyledAttributes.getDimension(index, this.f1762d);
                } else if (index == i.Variant_region_heightMoreThan) {
                    this.f1760b = typedArrayObtainStyledAttributes.getDimension(index, this.f1760b);
                } else if (index == i.Variant_region_widthLessThan) {
                    this.f1761c = typedArrayObtainStyledAttributes.getDimension(index, this.f1761c);
                } else if (index == i.Variant_region_widthMoreThan) {
                    this.f1759a = typedArrayObtainStyledAttributes.getDimension(index, this.f1759a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f7, float f8) {
            if (!Float.isNaN(this.f1759a) && f7 < this.f1759a) {
                return false;
            }
            if (!Float.isNaN(this.f1760b) && f8 < this.f1760b) {
                return false;
            }
            if (Float.isNaN(this.f1761c) || f7 <= this.f1761c) {
                return Float.isNaN(this.f1762d) || f8 <= this.f1762d;
            }
            return false;
        }
    }

    c(Context context, ConstraintLayout constraintLayout, int i7) {
        this.f1749a = constraintLayout;
        a(context, i7);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.content.Context r9, int r10) {
        /*
            r8 = this;
            android.content.res.Resources r0 = r9.getResources()
            android.content.res.XmlResourceParser r10 = r0.getXml(r10)
            r0 = 0
            int r1 = r10.getEventType()     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
        Ld:
            r2 = 1
            if (r1 == r2) goto L8c
            if (r1 == 0) goto L7b
            r3 = 2
            if (r1 == r3) goto L17
            goto L7e
        L17:
            java.lang.String r1 = r10.getName()     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r4 = -1
            int r5 = r1.hashCode()     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r6 = 4
            r7 = 3
            switch(r5) {
                case -1349929691: goto L4d;
                case 80204913: goto L43;
                case 1382829617: goto L3a;
                case 1657696882: goto L30;
                case 1901439077: goto L26;
                default: goto L25;
            }     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
        L25:
            goto L57
        L26:
            java.lang.String r2 = "Variant"
            boolean r1 = r1.equals(r2)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r1 == 0) goto L57
            r2 = r7
            goto L58
        L30:
            java.lang.String r2 = "layoutDescription"
            boolean r1 = r1.equals(r2)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r1 == 0) goto L57
            r2 = 0
            goto L58
        L3a:
            java.lang.String r5 = "StateSet"
            boolean r1 = r1.equals(r5)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r1 == 0) goto L57
            goto L58
        L43:
            java.lang.String r2 = "State"
            boolean r1 = r1.equals(r2)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r1 == 0) goto L57
            r2 = r3
            goto L58
        L4d:
            java.lang.String r2 = "ConstraintSet"
            boolean r1 = r1.equals(r2)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r1 == 0) goto L57
            r2 = r6
            goto L58
        L57:
            r2 = r4
        L58:
            if (r2 == r3) goto L6e
            if (r2 == r7) goto L63
            if (r2 == r6) goto L5f
            goto L7e
        L5f:
            r8.b(r9, r10)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            goto L7e
        L63:
            androidx.constraintlayout.widget.c$b r1 = new androidx.constraintlayout.widget.c$b     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r1.<init>(r9, r10)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r0 == 0) goto L7e
            r0.a(r1)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            goto L7e
        L6e:
            androidx.constraintlayout.widget.c$a r0 = new androidx.constraintlayout.widget.c$a     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r0.<init>(r9, r10)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            android.util.SparseArray<androidx.constraintlayout.widget.c$a> r1 = r8.f1753e     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            int r2 = r0.f1755a     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r1.put(r2, r0)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            goto L7e
        L7b:
            r10.getName()     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
        L7e:
            int r1 = r10.next()     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            goto Ld
        L83:
            r9 = move-exception
            r9.printStackTrace()
            goto L8c
        L88:
            r9 = move-exception
            r9.printStackTrace()
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.c.a(android.content.Context, int):void");
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        d dVar = new d();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i7 = 0; i7 < attributeCount; i7++) {
            String attributeName = xmlPullParser.getAttributeName(i7);
            String attributeValue = xmlPullParser.getAttributeValue(i7);
            if (attributeName != null && attributeValue != null && "id".equals(attributeName)) {
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                dVar.E(context, xmlPullParser);
                this.f1754f.put(identifier, dVar);
                return;
            }
        }
    }

    public void c(f fVar) {
    }

    public void d(int i7, float f7, float f8) {
        int iB;
        int i8 = this.f1751c;
        if (i8 == i7) {
            a aVarValueAt = i7 == -1 ? this.f1753e.valueAt(0) : this.f1753e.get(i8);
            int i9 = this.f1752d;
            if ((i9 == -1 || !aVarValueAt.f1756b.get(i9).a(f7, f8)) && this.f1752d != (iB = aVarValueAt.b(f7, f8))) {
                d dVar = iB == -1 ? this.f1750b : aVarValueAt.f1756b.get(iB).f1764f;
                if (iB != -1) {
                    int i10 = aVarValueAt.f1756b.get(iB).f1763e;
                }
                if (dVar == null) {
                    return;
                }
                this.f1752d = iB;
                dVar.i(this.f1749a);
                return;
            }
            return;
        }
        this.f1751c = i7;
        a aVar = this.f1753e.get(i7);
        int iB2 = aVar.b(f7, f8);
        d dVar2 = iB2 == -1 ? aVar.f1758d : aVar.f1756b.get(iB2).f1764f;
        if (iB2 != -1) {
            int i11 = aVar.f1756b.get(iB2).f1763e;
        }
        if (dVar2 != null) {
            this.f1752d = iB2;
            dVar2.i(this.f1749a);
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i7 + ", dim =" + f7 + ", " + f8);
    }
}
