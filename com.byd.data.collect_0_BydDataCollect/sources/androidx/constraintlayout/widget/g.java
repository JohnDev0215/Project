package androidx.constraintlayout.widget;

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
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1107a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1108b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1109c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f1110d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<c> f1111e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1112a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1113b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1114c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1115d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1114c = -1;
            this.f1115d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), f.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.State_android_id) {
                    this.f1112a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1112a);
                } else if (index == f.State_constraints) {
                    this.f1114c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1114c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1114c);
                    context.getResources().getResourceName(this.f1114c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1115d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1113b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f1113b.size(); i2++) {
                if (this.f1113b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1118c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1119d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1120e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f1121f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1116a = Float.NaN;
            this.f1117b = Float.NaN;
            this.f1118c = Float.NaN;
            this.f1119d = Float.NaN;
            this.f1120e = -1;
            this.f1121f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), f.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.Variant_constraints) {
                    this.f1120e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1120e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1120e);
                    context.getResources().getResourceName(this.f1120e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1121f = true;
                    }
                } else if (index == f.Variant_region_heightLessThan) {
                    this.f1119d = typedArrayObtainStyledAttributes.getDimension(index, this.f1119d);
                } else if (index == f.Variant_region_heightMoreThan) {
                    this.f1117b = typedArrayObtainStyledAttributes.getDimension(index, this.f1117b);
                } else if (index == f.Variant_region_widthLessThan) {
                    this.f1118c = typedArrayObtainStyledAttributes.getDimension(index, this.f1118c);
                } else if (index == f.Variant_region_widthMoreThan) {
                    this.f1116a = typedArrayObtainStyledAttributes.getDimension(index, this.f1116a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1116a) && f2 < this.f1116a) {
                return false;
            }
            if (!Float.isNaN(this.f1117b) && f3 < this.f1117b) {
                return false;
            }
            if (Float.isNaN(this.f1118c) || f2 <= this.f1118c) {
                return Float.isNaN(this.f1119d) || f3 <= this.f1119d;
            }
            return false;
        }
    }

    public g(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), f.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == f.StateSet_defaultState) {
                this.f1107a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1107a);
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
                            this.f1110d.put(aVar.f1112a, aVar);
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

    public int a(int i2, int i3, float f2, float f3) {
        a aVar = this.f1110d.get(i3);
        if (aVar == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (aVar.f1114c == i2) {
                return i2;
            }
            Iterator<b> it = aVar.f1113b.iterator();
            while (it.hasNext()) {
                if (i2 == it.next().f1120e) {
                    return i2;
                }
            }
            return aVar.f1114c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f1113b) {
            if (bVar2.a(f2, f3)) {
                if (i2 == bVar2.f1120e) {
                    return i2;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f1120e : aVar.f1114c;
    }

    public int c(int i2, int i3, int i4) {
        return d(-1, i2, i3, i4);
    }

    public int d(int i2, int i3, float f2, float f3) {
        int iB;
        if (i2 == i3) {
            a aVarValueAt = i3 == -1 ? this.f1110d.valueAt(0) : this.f1110d.get(this.f1108b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f1109c == -1 || !aVarValueAt.f1113b.get(i2).a(f2, f3)) && i2 != (iB = aVarValueAt.b(f2, f3))) ? iB == -1 ? aVarValueAt.f1114c : aVarValueAt.f1113b.get(iB).f1120e : i2;
        }
        a aVar = this.f1110d.get(i3);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f2, f3);
        return iB2 == -1 ? aVar.f1114c : aVar.f1113b.get(iB2).f1120e;
    }
}
