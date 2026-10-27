package a.f.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f636b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f637c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<a> f638d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<d> f639e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f640f = null;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f641a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f642b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f643c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f644d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f643c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.State_android_id) {
                    this.f641a = typedArrayObtainStyledAttributes.getResourceId(index, this.f641a);
                } else if (index == i.State_constraints) {
                    this.f643c = typedArrayObtainStyledAttributes.getResourceId(index, this.f643c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f643c);
                    context.getResources().getResourceName(this.f643c);
                    if ("layout".equals(resourceTypeName)) {
                        d dVar = new d();
                        this.f644d = dVar;
                        dVar.c((ConstraintLayout) LayoutInflater.from(context).inflate(this.f643c, (ViewGroup) null));
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f645a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f646b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f647c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f648d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f649e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public d f650f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f645a = Float.NaN;
            this.f646b = Float.NaN;
            this.f647c = Float.NaN;
            this.f648d = Float.NaN;
            this.f649e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.Variant_constraints) {
                    this.f649e = typedArrayObtainStyledAttributes.getResourceId(index, this.f649e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f649e);
                    context.getResources().getResourceName(this.f649e);
                    if ("layout".equals(resourceTypeName)) {
                        d dVar = new d();
                        this.f650f = dVar;
                        dVar.c((ConstraintLayout) LayoutInflater.from(context).inflate(this.f649e, (ViewGroup) null));
                    }
                } else if (index == i.Variant_region_heightLessThan) {
                    this.f648d = typedArrayObtainStyledAttributes.getDimension(index, this.f648d);
                } else if (index == i.Variant_region_heightMoreThan) {
                    this.f646b = typedArrayObtainStyledAttributes.getDimension(index, this.f646b);
                } else if (index == i.Variant_region_widthLessThan) {
                    this.f647c = typedArrayObtainStyledAttributes.getDimension(index, this.f647c);
                } else if (index == i.Variant_region_widthMoreThan) {
                    this.f645a = typedArrayObtainStyledAttributes.getDimension(index, this.f645a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(android.content.Context r8, androidx.constraintlayout.widget.ConstraintLayout r9, int r10) {
        /*
            r7 = this;
            r7.<init>()
            r0 = -1
            r7.f636b = r0
            r7.f637c = r0
            android.util.SparseArray r1 = new android.util.SparseArray
            r1.<init>()
            r7.f638d = r1
            android.util.SparseArray r1 = new android.util.SparseArray
            r1.<init>()
            r7.f639e = r1
            r1 = 0
            r7.f640f = r1
            r7.f635a = r9
            android.content.res.Resources r9 = r8.getResources()
            android.content.res.XmlResourceParser r9 = r9.getXml(r10)
            int r10 = r9.getEventType()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L27:
            r2 = 1
            if (r10 == r2) goto La8
            if (r10 == 0) goto L97
            r3 = 2
            if (r10 == r3) goto L31
            goto L9a
        L31:
            java.lang.String r10 = r9.getName()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            int r4 = r10.hashCode()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r5 = 4
            r6 = 3
            switch(r4) {
                case -1349929691: goto L66;
                case 80204913: goto L5c;
                case 1382829617: goto L53;
                case 1657696882: goto L49;
                case 1901439077: goto L3f;
                default: goto L3e;
            }     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L3e:
            goto L70
        L3f:
            java.lang.String r2 = "Variant"
            boolean r10 = r10.equals(r2)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r10 == 0) goto L70
            r2 = r6
            goto L71
        L49:
            java.lang.String r2 = "layoutDescription"
            boolean r10 = r10.equals(r2)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r10 == 0) goto L70
            r2 = 0
            goto L71
        L53:
            java.lang.String r4 = "StateSet"
            boolean r10 = r10.equals(r4)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r10 == 0) goto L70
            goto L71
        L5c:
            java.lang.String r2 = "State"
            boolean r10 = r10.equals(r2)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r10 == 0) goto L70
            r2 = r3
            goto L71
        L66:
            java.lang.String r2 = "ConstraintSet"
            boolean r10 = r10.equals(r2)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r10 == 0) goto L70
            r2 = r5
            goto L71
        L70:
            r2 = r0
        L71:
            if (r2 == r3) goto L89
            if (r2 == r6) goto L7c
            if (r2 == r5) goto L78
            goto L9a
        L78:
            r7.a(r8, r9)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L9a
        L7c:
            a.f.b.c$b r10 = new a.f.b.c$b     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r10.<init>(r8, r9)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            if (r1 == 0) goto L9a
            java.util.ArrayList<a.f.b.c$b> r2 = r1.f642b     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r2.add(r10)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L9a
        L89:
            a.f.b.c$a r10 = new a.f.b.c$a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r10.<init>(r8, r9)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            android.util.SparseArray<a.f.b.c$a> r1 = r7.f638d     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            int r2 = r10.f641a     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r1.put(r2, r10)     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            r1 = r10
            goto L9a
        L97:
            r9.getName()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
        L9a:
            int r10 = r9.next()     // Catch: java.io.IOException -> L9f org.xmlpull.v1.XmlPullParserException -> La4
            goto L27
        L9f:
            r7 = move-exception
            r7.printStackTrace()
            goto La8
        La4:
            r7 = move-exception
            r7.printStackTrace()
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.b.c.<init>(android.content.Context, androidx.constraintlayout.widget.ConstraintLayout, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x022b, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r12, org.xmlpull.v1.XmlPullParser r13) {
        /*
            Method dump skipped, instruction units count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.b.c.a(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }
}
