package b.f.c;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: StateSet.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2325a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2326b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2327c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<a> f2328d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<b.f.c.b> f2329e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f2331b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2333d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f2332c = -1;
            this.f2333d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.State_android_id) {
                    this.f2330a = typedArrayObtainStyledAttributes.getResourceId(index, this.f2330a);
                } else if (index == e.State_constraints) {
                    this.f2332c = typedArrayObtainStyledAttributes.getResourceId(index, this.f2332c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2332c);
                    context.getResources().getResourceName(this.f2332c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2333d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f2331b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2331b.size(); i2++) {
                if (this.f2331b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2334a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2335b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2336c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2337d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2338e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2339f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f2334a = Float.NaN;
            this.f2335b = Float.NaN;
            this.f2336c = Float.NaN;
            this.f2337d = Float.NaN;
            this.f2338e = -1;
            this.f2339f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.Variant_constraints) {
                    this.f2338e = typedArrayObtainStyledAttributes.getResourceId(index, this.f2338e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2338e);
                    context.getResources().getResourceName(this.f2338e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2339f = true;
                    }
                } else if (index == e.Variant_region_heightLessThan) {
                    this.f2337d = typedArrayObtainStyledAttributes.getDimension(index, this.f2337d);
                } else if (index == e.Variant_region_heightMoreThan) {
                    this.f2335b = typedArrayObtainStyledAttributes.getDimension(index, this.f2335b);
                } else if (index == e.Variant_region_widthLessThan) {
                    this.f2336c = typedArrayObtainStyledAttributes.getDimension(index, this.f2336c);
                } else if (index == e.Variant_region_widthMoreThan) {
                    this.f2334a = typedArrayObtainStyledAttributes.getDimension(index, this.f2334a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f2334a) && f2 < this.f2334a) {
                return false;
            }
            if (!Float.isNaN(this.f2335b) && f3 < this.f2335b) {
                return false;
            }
            if (Float.isNaN(this.f2336c) || f2 <= this.f2336c) {
                return Float.isNaN(this.f2337d) || f3 <= this.f2337d;
            }
            return false;
        }
    }

    public f(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    public int a(int i2, int i3, float f2, float f3) {
        a aVar = this.f2328d.get(i3);
        if (aVar == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (aVar.f2332c == i2) {
                return i2;
            }
            Iterator<b> it = aVar.f2331b.iterator();
            while (it.hasNext()) {
                if (i2 == it.next().f2338e) {
                    return i2;
                }
            }
            return aVar.f2332c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f2331b) {
            if (bVar2.a(f2, f3)) {
                if (i2 == bVar2.f2338e) {
                    return i2;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f2338e : aVar.f2332c;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == e.StateSet_defaultState) {
                this.f2325a = typedArrayObtainStyledAttributes.getResourceId(index, this.f2325a);
            }
        }
        a aVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    byte b2 = -1;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                b2 = 2;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                b2 = 0;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b2 = 1;
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
                            aVar = new a(context, xmlPullParser);
                            this.f2328d.put(aVar.f2330a, aVar);
                        } else if (b2 != 3) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b bVar = new b(context, xmlPullParser);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public int c(int i2, int i3, int i4) {
        return d(-1, i2, i3, i4);
    }

    public int d(int i2, int i3, float f2, float f3) {
        int iB;
        if (i2 == i3) {
            a aVarValueAt = i3 == -1 ? this.f2328d.valueAt(0) : this.f2328d.get(this.f2326b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f2327c == -1 || !aVarValueAt.f2331b.get(i2).a(f2, f3)) && i2 != (iB = aVarValueAt.b(f2, f3))) ? iB == -1 ? aVarValueAt.f2332c : aVarValueAt.f2331b.get(iB).f2338e : i2;
        }
        a aVar = this.f2328d.get(i3);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f2, f3);
        return iB2 == -1 ? aVar.f2332c : aVar.f2331b.get(iB2).f2338e;
    }
}
