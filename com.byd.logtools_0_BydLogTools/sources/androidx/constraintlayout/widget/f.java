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
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1183a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1184b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1185c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f1186d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.b> f1187e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1188a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1189b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1190c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1191d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1190c = -1;
            this.f1191d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w7) {
                    this.f1188a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1188a);
                } else if (index == e.x7) {
                    this.f1190c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1190c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1190c);
                    context.getResources().getResourceName(this.f1190c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1191d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1189b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i = 0; i < this.f1189b.size(); i++) {
                if (this.f1189b.get(i).a(f2, f3)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1192a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1193b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1194c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1195d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1196e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f1197f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1192a = Float.NaN;
            this.f1193b = Float.NaN;
            this.f1194c = Float.NaN;
            this.f1195d = Float.NaN;
            this.f1196e = -1;
            this.f1197f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.e8) {
                    this.f1196e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1196e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1196e);
                    context.getResources().getResourceName(this.f1196e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f1197f = true;
                    }
                } else if (index == e.f8) {
                    this.f1195d = typedArrayObtainStyledAttributes.getDimension(index, this.f1195d);
                } else if (index == e.g8) {
                    this.f1193b = typedArrayObtainStyledAttributes.getDimension(index, this.f1193b);
                } else if (index == e.h8) {
                    this.f1194c = typedArrayObtainStyledAttributes.getDimension(index, this.f1194c);
                } else if (index == e.i8) {
                    this.f1192a = typedArrayObtainStyledAttributes.getDimension(index, this.f1192a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1192a) && f2 < this.f1192a) {
                return false;
            }
            if (!Float.isNaN(this.f1193b) && f3 < this.f1193b) {
                return false;
            }
            if (Float.isNaN(this.f1194c) || f2 <= this.f1194c) {
                return Float.isNaN(this.f1195d) || f3 <= this.f1195d;
            }
            return false;
        }
    }

    public f(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.A7);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == e.B7) {
                this.f1183a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1183a);
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
                            this.f1186d.put(aVar.f1188a, aVar);
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

    public int a(int i, int i2, float f2, float f3) {
        a aVar = this.f1186d.get(i2);
        if (aVar == null) {
            return i2;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (aVar.f1190c == i) {
                return i;
            }
            Iterator<b> it = aVar.f1189b.iterator();
            while (it.hasNext()) {
                if (i == it.next().f1196e) {
                    return i;
                }
            }
            return aVar.f1190c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f1189b) {
            if (bVar2.a(f2, f3)) {
                if (i == bVar2.f1196e) {
                    return i;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f1196e : aVar.f1190c;
    }

    public int c(int i, int i2, int i3) {
        return d(-1, i, i2, i3);
    }

    public int d(int i, int i2, float f2, float f3) {
        int iB;
        if (i == i2) {
            a aVarValueAt = i2 == -1 ? this.f1186d.valueAt(0) : this.f1186d.get(this.f1184b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f1185c == -1 || !aVarValueAt.f1189b.get(i).a(f2, f3)) && i != (iB = aVarValueAt.b(f2, f3))) ? iB == -1 ? aVarValueAt.f1190c : aVarValueAt.f1189b.get(iB).f1196e : i;
        }
        a aVar = this.f1186d.get(i2);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f2, f3);
        return iB2 == -1 ? aVar.f1190c : aVar.f1189b.get(iB2).f1196e;
    }
}
