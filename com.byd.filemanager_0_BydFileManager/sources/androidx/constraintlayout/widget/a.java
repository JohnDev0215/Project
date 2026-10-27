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
    private final ConstraintLayout f836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    androidx.constraintlayout.widget.b f837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f838c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f839d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<C0010a> f840e = new SparseArray<>();
    private SparseArray<androidx.constraintlayout.widget.b> f = new SparseArray<>();
    private c g;

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class C0010a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f842b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        androidx.constraintlayout.widget.b f844d;

        public C0010a(Context context, XmlPullParser xmlPullParser) {
            this.f843c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w7) {
                    this.f841a = typedArrayObtainStyledAttributes.getResourceId(index, this.f841a);
                } else if (index == e.x7) {
                    this.f843c = typedArrayObtainStyledAttributes.getResourceId(index, this.f843c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f843c);
                    context.getResources().getResourceName(this.f843c);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f844d = bVar;
                        bVar.h(context, this.f843c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f842b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i = 0; i < this.f842b.size(); i++) {
                if (this.f842b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f845a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f847c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f849e;
        androidx.constraintlayout.widget.b f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f845a = Float.NaN;
            this.f846b = Float.NaN;
            this.f847c = Float.NaN;
            this.f848d = Float.NaN;
            this.f849e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.e8) {
                    this.f849e = typedArrayObtainStyledAttributes.getResourceId(index, this.f849e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f849e);
                    context.getResources().getResourceName(this.f849e);
                    if ("layout".equals(resourceTypeName)) {
                        androidx.constraintlayout.widget.b bVar = new androidx.constraintlayout.widget.b();
                        this.f = bVar;
                        bVar.h(context, this.f849e);
                    }
                } else if (index == e.f8) {
                    this.f848d = typedArrayObtainStyledAttributes.getDimension(index, this.f848d);
                } else if (index == e.g8) {
                    this.f846b = typedArrayObtainStyledAttributes.getDimension(index, this.f846b);
                } else if (index == e.h8) {
                    this.f847c = typedArrayObtainStyledAttributes.getDimension(index, this.f847c);
                } else if (index == e.i8) {
                    this.f845a = typedArrayObtainStyledAttributes.getDimension(index, this.f845a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.f845a) && f < this.f845a) {
                return false;
            }
            if (!Float.isNaN(this.f846b) && f2 < this.f846b) {
                return false;
            }
            if (Float.isNaN(this.f847c) || f <= this.f847c) {
                return Float.isNaN(this.f848d) || f2 <= this.f848d;
            }
            return false;
        }
    }

    a(Context context, ConstraintLayout constraintLayout, int i) {
        this.f836a = constraintLayout;
        a(context, i);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        C0010a c0010a = null;
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
                            c0010a = new C0010a(context, xml);
                            this.f840e.put(c0010a.f841a, c0010a);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (c0010a != null) {
                                c0010a.a(bVar);
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
                this.f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i, float f, float f2) {
        int iB;
        int i2 = this.f838c;
        if (i2 == i) {
            C0010a c0010aValueAt = i == -1 ? this.f840e.valueAt(0) : this.f840e.get(i2);
            int i3 = this.f839d;
            if ((i3 == -1 || !c0010aValueAt.f842b.get(i3).a(f, f2)) && this.f839d != (iB = c0010aValueAt.b(f, f2))) {
                androidx.constraintlayout.widget.b bVar = iB == -1 ? this.f837b : c0010aValueAt.f842b.get(iB).f;
                if (iB != -1) {
                    int i4 = c0010aValueAt.f842b.get(iB).f849e;
                }
                if (bVar == null) {
                    return;
                }
                this.f839d = iB;
                if (this.g != null) {
                    throw null;
                }
                bVar.d(this.f836a);
                if (this.g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f838c = i;
        C0010a c0010a = this.f840e.get(i);
        int iB2 = c0010a.b(f, f2);
        androidx.constraintlayout.widget.b bVar2 = iB2 == -1 ? c0010a.f844d : c0010a.f842b.get(iB2).f;
        if (iB2 != -1) {
            int i5 = c0010a.f842b.get(iB2).f849e;
        }
        if (bVar2 != null) {
            this.f839d = iB2;
            if (this.g != null) {
                throw null;
            }
            bVar2.d(this.f836a);
            if (this.g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =" + f + ", " + f2);
    }
}
