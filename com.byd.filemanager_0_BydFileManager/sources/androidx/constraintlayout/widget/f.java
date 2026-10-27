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
    int f886a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f887b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f888c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f889d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SparseArray<androidx.constraintlayout.widget.b> f890e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f891a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f892b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f893c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f894d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f893c = -1;
            this.f894d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w7) {
                    this.f891a = typedArrayObtainStyledAttributes.getResourceId(index, this.f891a);
                } else if (index == e.x7) {
                    this.f893c = typedArrayObtainStyledAttributes.getResourceId(index, this.f893c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f893c);
                    context.getResources().getResourceName(this.f893c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f894d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f892b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i = 0; i < this.f892b.size(); i++) {
                if (this.f892b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f895a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f896b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f897c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f898d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f899e;
        boolean f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f895a = Float.NaN;
            this.f896b = Float.NaN;
            this.f897c = Float.NaN;
            this.f898d = Float.NaN;
            this.f899e = -1;
            this.f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.e8) {
                    this.f899e = typedArrayObtainStyledAttributes.getResourceId(index, this.f899e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f899e);
                    context.getResources().getResourceName(this.f899e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f = true;
                    }
                } else if (index == e.f8) {
                    this.f898d = typedArrayObtainStyledAttributes.getDimension(index, this.f898d);
                } else if (index == e.g8) {
                    this.f896b = typedArrayObtainStyledAttributes.getDimension(index, this.f896b);
                } else if (index == e.h8) {
                    this.f897c = typedArrayObtainStyledAttributes.getDimension(index, this.f897c);
                } else if (index == e.i8) {
                    this.f895a = typedArrayObtainStyledAttributes.getDimension(index, this.f895a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.f895a) && f < this.f895a) {
                return false;
            }
            if (!Float.isNaN(this.f896b) && f2 < this.f896b) {
                return false;
            }
            if (Float.isNaN(this.f897c) || f <= this.f897c) {
                return Float.isNaN(this.f898d) || f2 <= this.f898d;
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
                this.f886a = typedArrayObtainStyledAttributes.getResourceId(index, this.f886a);
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
                            this.f889d.put(aVar.f891a, aVar);
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

    public int a(int i, int i2, float f, float f2) {
        a aVar = this.f889d.get(i2);
        if (aVar == null) {
            return i2;
        }
        if (f == -1.0f || f2 == -1.0f) {
            if (aVar.f893c == i) {
                return i;
            }
            Iterator<b> it = aVar.f892b.iterator();
            while (it.hasNext()) {
                if (i == it.next().f899e) {
                    return i;
                }
            }
            return aVar.f893c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f892b) {
            if (bVar2.a(f, f2)) {
                if (i == bVar2.f899e) {
                    return i;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f899e : aVar.f893c;
    }

    public int c(int i, int i2, int i3) {
        return d(-1, i, i2, i3);
    }

    public int d(int i, int i2, float f, float f2) {
        int iB;
        if (i == i2) {
            a aVarValueAt = i2 == -1 ? this.f889d.valueAt(0) : this.f889d.get(this.f887b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f888c == -1 || !aVarValueAt.f892b.get(i).a(f, f2)) && i != (iB = aVarValueAt.b(f, f2))) ? iB == -1 ? aVarValueAt.f893c : aVarValueAt.f892b.get(iB).f899e : i;
        }
        a aVar = this.f889d.get(i2);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f, f2);
        return iB2 == -1 ? aVar.f893c : aVar.f892b.get(iB2).f899e;
    }
}
