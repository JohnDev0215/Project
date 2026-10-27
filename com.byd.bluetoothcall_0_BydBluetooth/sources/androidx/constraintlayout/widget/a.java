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
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f1056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.b f1057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1058c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1059d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<C0018a> f1060e = new SparseArray<>();
    private SparseArray<androidx.constraintlayout.widget.b> f = new SparseArray<>();
    private c g;

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class C0018a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1062b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1063c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        androidx.constraintlayout.widget.b f1064d;

        public C0018a(Context context, XmlPullParser xmlPullParser) {
            this.f1063c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Xa);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.Ya) {
                    this.f1061a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1061a);
                } else if (index == e.Za) {
                    this.f1063c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1063c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1063c);
                    context.getResources().getResourceName(this.f1063c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f1064d = bVar;
                        bVar.n(context, this.f1063c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1062b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i = 0; i < this.f1062b.size(); i++) {
                if (this.f1062b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1065a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1066b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1067c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1068d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1069e;
        androidx.constraintlayout.widget.b f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1065a = Float.NaN;
            this.f1066b = Float.NaN;
            this.f1067c = Float.NaN;
            this.f1068d = Float.NaN;
            this.f1069e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Hb);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.Ib) {
                    this.f1069e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1069e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1069e);
                    context.getResources().getResourceName(this.f1069e);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f = bVar;
                        bVar.n(context, this.f1069e);
                    }
                } else if (index == e.Jb) {
                    this.f1068d = typedArrayObtainStyledAttributes.getDimension(index, this.f1068d);
                } else if (index == e.Kb) {
                    this.f1066b = typedArrayObtainStyledAttributes.getDimension(index, this.f1066b);
                } else if (index == e.Lb) {
                    this.f1067c = typedArrayObtainStyledAttributes.getDimension(index, this.f1067c);
                } else if (index == e.Mb) {
                    this.f1065a = typedArrayObtainStyledAttributes.getDimension(index, this.f1065a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.f1065a) && f < this.f1065a) {
                return false;
            }
            if (!Float.isNaN(this.f1066b) && f2 < this.f1066b) {
                return false;
            }
            if (Float.isNaN(this.f1067c) || f <= this.f1067c) {
                return Float.isNaN(this.f1068d) || f2 <= this.f1068d;
            }
            return false;
        }
    }

    a(Context context, ConstraintLayout constraintLayout, int i) {
        this.f1056a = constraintLayout;
        a(context, i);
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
            androidx.constraintlayout.widget.a$b r1 = new androidx.constraintlayout.widget.a$b     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r1.<init>(r9, r10)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            if (r0 == 0) goto L7e
            r0.a(r1)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            goto L7e
        L6e:
            androidx.constraintlayout.widget.a$a r0 = new androidx.constraintlayout.widget.a$a     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            r0.<init>(r9, r10)     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            android.util.SparseArray<androidx.constraintlayout.widget.a$a> r1 = r8.f1060e     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
            int r2 = r0.f1061a     // Catch: java.io.IOException -> L83 org.xmlpull.v1.XmlPullParserException -> L88
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.a.a(android.content.Context, int):void");
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName != null && attributeValue != null && "id".equals(attributeName)) {
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                bVar.C(context, xmlPullParser);
                this.f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i, float f, float f2) {
        int iB;
        int i2 = this.f1058c;
        if (i2 == i) {
            C0018a c0018aValueAt = i == -1 ? this.f1060e.valueAt(0) : this.f1060e.get(i2);
            int i3 = this.f1059d;
            if ((i3 == -1 || !c0018aValueAt.f1062b.get(i3).a(f, f2)) && this.f1059d != (iB = c0018aValueAt.b(f, f2))) {
                androidx.constraintlayout.widget.b bVar = iB == -1 ? this.f1057b : c0018aValueAt.f1062b.get(iB).f;
                if (iB != -1) {
                    int i4 = c0018aValueAt.f1062b.get(iB).f1069e;
                }
                if (bVar == null) {
                    return;
                }
                this.f1059d = iB;
                if (this.g != null) {
                    throw null;
                }
                bVar.i(this.f1056a);
                if (this.g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f1058c = i;
        C0018a c0018a = this.f1060e.get(i);
        int iB2 = c0018a.b(f, f2);
        androidx.constraintlayout.widget.b bVar2 = iB2 == -1 ? c0018a.f1064d : c0018a.f1062b.get(iB2).f;
        if (iB2 != -1) {
            int i5 = c0018a.f1062b.get(iB2).f1069e;
        }
        if (bVar2 != null) {
            this.f1059d = iB2;
            if (this.g != null) {
                throw null;
            }
            bVar2.i(this.f1056a);
            if (this.g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =" + f + ", " + f2);
    }
}
