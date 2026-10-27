package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintLayoutStates.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f1047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f1048b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1049c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1050d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<a> f1051e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SparseArray<c> f1052f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private d f1053g;

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1054a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<C0011b> f1055b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1056c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c f1057d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1056c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), f.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.State_android_id) {
                    this.f1054a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1054a);
                } else if (index == f.State_constraints) {
                    this.f1056c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1056c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1056c);
                    context.getResources().getResourceName(this.f1056c);
                    if ("layout".equals(resourceTypeName)) {
                        c cVar = new c();
                        this.f1057d = cVar;
                        cVar.h(context, this.f1056c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(C0011b c0011b) {
            this.f1055b.add(c0011b);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f1055b.size(); i2++) {
                if (this.f1055b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class C0011b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1058a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1059b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1060c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1061d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1062e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c f1063f;

        public C0011b(Context context, XmlPullParser xmlPullParser) {
            this.f1058a = Float.NaN;
            this.f1059b = Float.NaN;
            this.f1060c = Float.NaN;
            this.f1061d = Float.NaN;
            this.f1062e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), f.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.Variant_constraints) {
                    this.f1062e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1062e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1062e);
                    context.getResources().getResourceName(this.f1062e);
                    if ("layout".equals(resourceTypeName)) {
                        c cVar = new c();
                        this.f1063f = cVar;
                        cVar.h(context, this.f1062e);
                    }
                } else if (index == f.Variant_region_heightLessThan) {
                    this.f1061d = typedArrayObtainStyledAttributes.getDimension(index, this.f1061d);
                } else if (index == f.Variant_region_heightMoreThan) {
                    this.f1059b = typedArrayObtainStyledAttributes.getDimension(index, this.f1059b);
                } else if (index == f.Variant_region_widthLessThan) {
                    this.f1060c = typedArrayObtainStyledAttributes.getDimension(index, this.f1060c);
                } else if (index == f.Variant_region_widthMoreThan) {
                    this.f1058a = typedArrayObtainStyledAttributes.getDimension(index, this.f1058a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1058a) && f2 < this.f1058a) {
                return false;
            }
            if (!Float.isNaN(this.f1059b) && f3 < this.f1059b) {
                return false;
            }
            if (Float.isNaN(this.f1060c) || f2 <= this.f1060c) {
                return Float.isNaN(this.f1061d) || f3 <= this.f1061d;
            }
            return false;
        }
    }

    b(Context context, ConstraintLayout constraintLayout, int i2) {
        this.f1047a = constraintLayout;
        a(context, i2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        a aVar = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    byte b2 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                b2 = 4;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b2 = 2;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b2 = 1;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                b2 = 0;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                b2 = 3;
                            }
                            break;
                    }
                    if (b2 != 0 && b2 != 1) {
                        if (b2 == 2) {
                            aVar = new a(context, xml);
                            this.f1051e.put(aVar.f1054a, aVar);
                        } else if (b2 == 3) {
                            C0011b c0011b = new C0011b(context, xml);
                            if (aVar != null) {
                                aVar.a(c0011b);
                            }
                        } else if (b2 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        c cVar = new c();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            if ("id".equals(xmlPullParser.getAttributeName(i2))) {
                String attributeValue = xmlPullParser.getAttributeValue(i2);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                cVar.v(context, xmlPullParser);
                this.f1052f.put(identifier, cVar);
                return;
            }
        }
    }

    public void c(d dVar) {
    }

    public void d(int i2, float f2, float f3) {
        int iB;
        int i3 = this.f1049c;
        if (i3 == i2) {
            a aVarValueAt = i2 == -1 ? this.f1051e.valueAt(0) : this.f1051e.get(i3);
            int i4 = this.f1050d;
            if ((i4 == -1 || !aVarValueAt.f1055b.get(i4).a(f2, f3)) && this.f1050d != (iB = aVarValueAt.b(f2, f3))) {
                c cVar = iB == -1 ? this.f1048b : aVarValueAt.f1055b.get(iB).f1063f;
                if (iB != -1) {
                    int i5 = aVarValueAt.f1055b.get(iB).f1062e;
                }
                if (cVar == null) {
                    return;
                }
                this.f1050d = iB;
                if (this.f1053g != null) {
                    throw null;
                }
                cVar.d(this.f1047a);
                if (this.f1053g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f1049c = i2;
        a aVar = this.f1051e.get(i2);
        int iB2 = aVar.b(f2, f3);
        c cVar2 = iB2 == -1 ? aVar.f1057d : aVar.f1055b.get(iB2).f1063f;
        if (iB2 != -1) {
            int i6 = aVar.f1055b.get(iB2).f1062e;
        }
        if (cVar2 != null) {
            this.f1050d = iB2;
            if (this.f1053g != null) {
                throw null;
            }
            cVar2.d(this.f1047a);
            if (this.f1053g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f2 + ", " + f3);
    }
}
