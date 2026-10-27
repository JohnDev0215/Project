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
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f1116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.b f1117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1118c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1119d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<C0011a> f1120e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.b> f1121f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c f1122g;

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class C0011a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1124b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1125c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        androidx.constraintlayout.widget.b f1126d;

        public C0011a(Context context, XmlPullParser xmlPullParser) {
            this.f1125c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w7) {
                    this.f1123a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1123a);
                } else if (index == e.x7) {
                    this.f1125c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1125c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1125c);
                    context.getResources().getResourceName(this.f1125c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f1126d = bVar;
                        bVar.h(context, this.f1125c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1124b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i = 0; i < this.f1124b.size(); i++) {
                if (this.f1124b.get(i).a(f2, f3)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1127a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1128b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1129c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1130d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1131e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        androidx.constraintlayout.widget.b f1132f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1127a = Float.NaN;
            this.f1128b = Float.NaN;
            this.f1129c = Float.NaN;
            this.f1130d = Float.NaN;
            this.f1131e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.e8) {
                    this.f1131e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1131e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1131e);
                    context.getResources().getResourceName(this.f1131e);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f1132f = bVar;
                        bVar.h(context, this.f1131e);
                    }
                } else if (index == e.f8) {
                    this.f1130d = typedArrayObtainStyledAttributes.getDimension(index, this.f1130d);
                } else if (index == e.g8) {
                    this.f1128b = typedArrayObtainStyledAttributes.getDimension(index, this.f1128b);
                } else if (index == e.h8) {
                    this.f1129c = typedArrayObtainStyledAttributes.getDimension(index, this.f1129c);
                } else if (index == e.i8) {
                    this.f1127a = typedArrayObtainStyledAttributes.getDimension(index, this.f1127a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1127a) && f2 < this.f1127a) {
                return false;
            }
            if (!Float.isNaN(this.f1128b) && f3 < this.f1128b) {
                return false;
            }
            if (Float.isNaN(this.f1129c) || f2 <= this.f1129c) {
                return Float.isNaN(this.f1130d) || f3 <= this.f1130d;
            }
            return false;
        }
    }

    a(Context context, ConstraintLayout constraintLayout, int i) {
        this.f1116a = constraintLayout;
        a(context, i);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        C0011a c0011a = null;
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
                            c0011a = new C0011a(context, xml);
                            this.f1120e.put(c0011a.f1123a, c0011a);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (c0011a != null) {
                                c0011a.a(bVar);
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
        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("id".equals(xmlPullParser.getAttributeName(i))) {
                String attributeValue = xmlPullParser.getAttributeValue(i);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                bVar.v(context, xmlPullParser);
                this.f1121f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i, float f2, float f3) {
        int iB;
        int i2 = this.f1118c;
        if (i2 == i) {
            C0011a c0011aValueAt = i == -1 ? this.f1120e.valueAt(0) : this.f1120e.get(i2);
            int i3 = this.f1119d;
            if ((i3 == -1 || !c0011aValueAt.f1124b.get(i3).a(f2, f3)) && this.f1119d != (iB = c0011aValueAt.b(f2, f3))) {
                androidx.constraintlayout.widget.b bVar = iB == -1 ? this.f1117b : c0011aValueAt.f1124b.get(iB).f1132f;
                if (iB != -1) {
                    int i4 = c0011aValueAt.f1124b.get(iB).f1131e;
                }
                if (bVar == null) {
                    return;
                }
                this.f1119d = iB;
                if (this.f1122g != null) {
                    throw null;
                }
                bVar.d(this.f1116a);
                if (this.f1122g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f1118c = i;
        C0011a c0011a = this.f1120e.get(i);
        int iB2 = c0011a.b(f2, f3);
        androidx.constraintlayout.widget.b bVar2 = iB2 == -1 ? c0011a.f1126d : c0011a.f1124b.get(iB2).f1132f;
        if (iB2 != -1) {
            int i5 = c0011a.f1124b.get(iB2).f1131e;
        }
        if (bVar2 != null) {
            this.f1119d = iB2;
            if (this.f1122g != null) {
                throw null;
            }
            bVar2.d(this.f1116a);
            if (this.f1122g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =" + f2 + ", " + f3);
    }
}
