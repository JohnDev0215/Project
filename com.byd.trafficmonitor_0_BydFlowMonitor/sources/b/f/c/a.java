package b.f.c;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintLayoutStates.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f2265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.f.c.b f2266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2267c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2268d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<C0028a> f2269e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray<b.f.c.b> f2270f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f2271g;

    /* JADX INFO: renamed from: b.f.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class C0028a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2272a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f2273b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2274c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.f.c.b f2275d;

        public C0028a(Context context, XmlPullParser xmlPullParser) {
            this.f2274c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.State_android_id) {
                    this.f2272a = typedArrayObtainStyledAttributes.getResourceId(index, this.f2272a);
                } else if (index == e.State_constraints) {
                    this.f2274c = typedArrayObtainStyledAttributes.getResourceId(index, this.f2274c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2274c);
                    context.getResources().getResourceName(this.f2274c);
                    if ("layout".equals(resourceTypeName)) {
                        b.f.c.b bVar = new b.f.c.b();
                        this.f2275d = bVar;
                        bVar.h(context, this.f2274c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f2273b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2273b.size(); i2++) {
                if (this.f2273b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2277b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2278c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2279d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2280e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.f.c.b f2281f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f2276a = Float.NaN;
            this.f2277b = Float.NaN;
            this.f2278c = Float.NaN;
            this.f2279d = Float.NaN;
            this.f2280e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.Variant_constraints) {
                    this.f2280e = typedArrayObtainStyledAttributes.getResourceId(index, this.f2280e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2280e);
                    context.getResources().getResourceName(this.f2280e);
                    if ("layout".equals(resourceTypeName)) {
                        b.f.c.b bVar = new b.f.c.b();
                        this.f2281f = bVar;
                        bVar.h(context, this.f2280e);
                    }
                } else if (index == e.Variant_region_heightLessThan) {
                    this.f2279d = typedArrayObtainStyledAttributes.getDimension(index, this.f2279d);
                } else if (index == e.Variant_region_heightMoreThan) {
                    this.f2277b = typedArrayObtainStyledAttributes.getDimension(index, this.f2277b);
                } else if (index == e.Variant_region_widthLessThan) {
                    this.f2278c = typedArrayObtainStyledAttributes.getDimension(index, this.f2278c);
                } else if (index == e.Variant_region_widthMoreThan) {
                    this.f2276a = typedArrayObtainStyledAttributes.getDimension(index, this.f2276a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f2276a) && f2 < this.f2276a) {
                return false;
            }
            if (!Float.isNaN(this.f2277b) && f3 < this.f2277b) {
                return false;
            }
            if (Float.isNaN(this.f2278c) || f2 <= this.f2278c) {
                return Float.isNaN(this.f2279d) || f3 <= this.f2279d;
            }
            return false;
        }
    }

    public a(Context context, ConstraintLayout constraintLayout, int i2) {
        this.f2265a = constraintLayout;
        a(context, i2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        C0028a c0028a = null;
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
                            c0028a = new C0028a(context, xml);
                            this.f2269e.put(c0028a.f2272a, c0028a);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (c0028a != null) {
                                c0028a.a(bVar);
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

    public final void b(Context context, XmlPullParser xmlPullParser) {
        b.f.c.b bVar = new b.f.c.b();
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
                bVar.v(context, xmlPullParser);
                this.f2270f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i2, float f2, float f3) {
        int iB;
        int i3 = this.f2267c;
        if (i3 == i2) {
            C0028a c0028aValueAt = i2 == -1 ? this.f2269e.valueAt(0) : this.f2269e.get(i3);
            int i4 = this.f2268d;
            if ((i4 == -1 || !c0028aValueAt.f2273b.get(i4).a(f2, f3)) && this.f2268d != (iB = c0028aValueAt.b(f2, f3))) {
                b.f.c.b bVar = iB == -1 ? this.f2266b : c0028aValueAt.f2273b.get(iB).f2281f;
                if (iB != -1) {
                    int i5 = c0028aValueAt.f2273b.get(iB).f2280e;
                }
                if (bVar == null) {
                    return;
                }
                this.f2268d = iB;
                if (this.f2271g != null) {
                    throw null;
                }
                bVar.d(this.f2265a);
                if (this.f2271g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f2267c = i2;
        C0028a c0028a = this.f2269e.get(i2);
        int iB2 = c0028a.b(f2, f3);
        b.f.c.b bVar2 = iB2 == -1 ? c0028a.f2275d : c0028a.f2273b.get(iB2).f2281f;
        if (iB2 != -1) {
            int i6 = c0028a.f2273b.get(iB2).f2280e;
        }
        if (bVar2 != null) {
            this.f2268d = iB2;
            if (this.f2271g != null) {
                throw null;
            }
            bVar2.d(this.f2265a);
            if (this.f2271g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f2 + ", " + f3);
    }
}
