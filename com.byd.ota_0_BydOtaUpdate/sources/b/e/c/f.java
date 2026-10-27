package b.e.c;

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
    public int f1523a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1524b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1525c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<a> f1526d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<b.e.c.b> f1527e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1528a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f1529b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1530c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1531d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1530c = -1;
            this.f1531d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.w7) {
                    this.f1528a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1528a);
                } else if (index == e.x7) {
                    this.f1530c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1530c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1530c);
                    context.getResources().getResourceName(this.f1530c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1531d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f1529b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f1529b.size(); i2++) {
                if (this.f1529b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1532a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1533b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1534c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1535d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1536e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1537f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1532a = Float.NaN;
            this.f1533b = Float.NaN;
            this.f1534c = Float.NaN;
            this.f1535d = Float.NaN;
            this.f1536e = -1;
            this.f1537f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.e8) {
                    this.f1536e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1536e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1536e);
                    context.getResources().getResourceName(this.f1536e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1537f = true;
                    }
                } else if (index == e.f8) {
                    this.f1535d = typedArrayObtainStyledAttributes.getDimension(index, this.f1535d);
                } else if (index == e.g8) {
                    this.f1533b = typedArrayObtainStyledAttributes.getDimension(index, this.f1533b);
                } else if (index == e.h8) {
                    this.f1534c = typedArrayObtainStyledAttributes.getDimension(index, this.f1534c);
                } else if (index == e.i8) {
                    this.f1532a = typedArrayObtainStyledAttributes.getDimension(index, this.f1532a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1532a) && f2 < this.f1532a) {
                return false;
            }
            if (!Float.isNaN(this.f1533b) && f3 < this.f1533b) {
                return false;
            }
            if (Float.isNaN(this.f1534c) || f2 <= this.f1534c) {
                return Float.isNaN(this.f1535d) || f3 <= this.f1535d;
            }
            return false;
        }
    }

    public f(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    public int a(int i2, int i3, float f2, float f3) {
        a aVar = this.f1526d.get(i3);
        if (aVar == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (aVar.f1530c == i2) {
                return i2;
            }
            Iterator<b> it = aVar.f1529b.iterator();
            while (it.hasNext()) {
                if (i2 == it.next().f1536e) {
                    return i2;
                }
            }
            return aVar.f1530c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f1529b) {
            if (bVar2.a(f2, f3)) {
                if (i2 == bVar2.f1536e) {
                    return i2;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f1536e : aVar.f1530c;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.A7);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == e.B7) {
                this.f1523a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1523a);
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
                            this.f1526d.put(aVar.f1528a, aVar);
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
            a aVarValueAt = i3 == -1 ? this.f1526d.valueAt(0) : this.f1526d.get(this.f1524b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f1525c == -1 || !aVarValueAt.f1529b.get(i2).a(f2, f3)) && i2 != (iB = aVarValueAt.b(f2, f3))) ? iB == -1 ? aVarValueAt.f1530c : aVarValueAt.f1529b.get(iB).f1536e : i2;
        }
        a aVar = this.f1526d.get(i3);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f2, f3);
        return iB2 == -1 ? aVar.f1530c : aVar.f1529b.get(iB2).f1536e;
    }
}
