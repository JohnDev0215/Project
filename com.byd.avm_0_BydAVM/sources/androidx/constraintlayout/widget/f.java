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
    int f781a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f782b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f783c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f784d = new SparseArray<>();
    private SparseArray<androidx.constraintlayout.widget.b> e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f786b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f787c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f788d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f787c = -1;
            this.f788d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w7) {
                    this.f785a = typedArrayObtainStyledAttributes.getResourceId(index, this.f785a);
                } else if (index == e.x7) {
                    this.f787c = typedArrayObtainStyledAttributes.getResourceId(index, this.f787c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f787c);
                    context.getResources().getResourceName(this.f787c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f788d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f786b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i = 0; i < this.f786b.size(); i++) {
                if (this.f786b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f789a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f790b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f791c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f792d;
        int e;
        boolean f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f789a = Float.NaN;
            this.f790b = Float.NaN;
            this.f791c = Float.NaN;
            this.f792d = Float.NaN;
            this.e = -1;
            this.f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.e8) {
                    this.e = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.e);
                    context.getResources().getResourceName(this.e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f = true;
                    }
                } else if (index == e.f8) {
                    this.f792d = typedArrayObtainStyledAttributes.getDimension(index, this.f792d);
                } else if (index == e.g8) {
                    this.f790b = typedArrayObtainStyledAttributes.getDimension(index, this.f790b);
                } else if (index == e.h8) {
                    this.f791c = typedArrayObtainStyledAttributes.getDimension(index, this.f791c);
                } else if (index == e.i8) {
                    this.f789a = typedArrayObtainStyledAttributes.getDimension(index, this.f789a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.f789a) && f < this.f789a) {
                return false;
            }
            if (!Float.isNaN(this.f790b) && f2 < this.f790b) {
                return false;
            }
            if (Float.isNaN(this.f791c) || f <= this.f791c) {
                return Float.isNaN(this.f792d) || f2 <= this.f792d;
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
                this.f781a = typedArrayObtainStyledAttributes.getResourceId(index, this.f781a);
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
                            this.f784d.put(aVar.f785a, aVar);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    public int a(int i, int i2, float f, float f2) {
        a aVar = this.f784d.get(i2);
        if (aVar == null) {
            return i2;
        }
        if (f == -1.0f || f2 == -1.0f) {
            if (aVar.f787c == i) {
                return i;
            }
            Iterator<b> it = aVar.f786b.iterator();
            while (it.hasNext()) {
                if (i == it.next().e) {
                    return i;
                }
            }
            return aVar.f787c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f786b) {
            if (bVar2.a(f, f2)) {
                if (i == bVar2.e) {
                    return i;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.e : aVar.f787c;
    }

    public int c(int i, int i2, int i3) {
        return d(-1, i, i2, i3);
    }

    public int d(int i, int i2, float f, float f2) {
        int iB;
        if (i == i2) {
            a aVarValueAt = i2 == -1 ? this.f784d.valueAt(0) : this.f784d.get(this.f782b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f783c == -1 || !aVarValueAt.f786b.get(i).a(f, f2)) && i != (iB = aVarValueAt.b(f, f2))) ? iB == -1 ? aVarValueAt.f787c : aVarValueAt.f786b.get(iB).e : i;
        }
        a aVar = this.f784d.get(i2);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f, f2);
        return iB2 == -1 ? aVar.f787c : aVar.f786b.get(iB2).e;
    }
}
