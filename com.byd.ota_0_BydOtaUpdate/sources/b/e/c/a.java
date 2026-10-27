package b.e.c;

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
    public final ConstraintLayout f1453a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.e.c.b f1454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1455c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1456d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<C0017a> f1457e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray<b.e.c.b> f1458f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f1459g;

    /* JADX INFO: renamed from: b.e.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class C0017a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f1461b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1462c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.e.c.b f1463d;

        public C0017a(Context context, XmlPullParser xmlPullParser) {
            this.f1462c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.v7);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.w7) {
                    this.f1460a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1460a);
                } else if (index == e.x7) {
                    this.f1462c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1462c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1462c);
                    context.getResources().getResourceName(this.f1462c);
                    if ("layout".equals(resourceTypeName)) {
                        b.e.c.b bVar = new b.e.c.b();
                        this.f1463d = bVar;
                        bVar.h(context, this.f1462c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f1461b.add(bVar);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f1461b.size(); i2++) {
                if (this.f1461b.get(i2).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1464a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1465b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1466c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1467d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1468e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.e.c.b f1469f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1464a = Float.NaN;
            this.f1465b = Float.NaN;
            this.f1466c = Float.NaN;
            this.f1467d = Float.NaN;
            this.f1468e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), e.d8);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.e8) {
                    this.f1468e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1468e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1468e);
                    context.getResources().getResourceName(this.f1468e);
                    if ("layout".equals(resourceTypeName)) {
                        b.e.c.b bVar = new b.e.c.b();
                        this.f1469f = bVar;
                        bVar.h(context, this.f1468e);
                    }
                } else if (index == e.f8) {
                    this.f1467d = typedArrayObtainStyledAttributes.getDimension(index, this.f1467d);
                } else if (index == e.g8) {
                    this.f1465b = typedArrayObtainStyledAttributes.getDimension(index, this.f1465b);
                } else if (index == e.h8) {
                    this.f1466c = typedArrayObtainStyledAttributes.getDimension(index, this.f1466c);
                } else if (index == e.i8) {
                    this.f1464a = typedArrayObtainStyledAttributes.getDimension(index, this.f1464a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f1464a) && f2 < this.f1464a) {
                return false;
            }
            if (!Float.isNaN(this.f1465b) && f3 < this.f1465b) {
                return false;
            }
            if (Float.isNaN(this.f1466c) || f2 <= this.f1466c) {
                return Float.isNaN(this.f1467d) || f3 <= this.f1467d;
            }
            return false;
        }
    }

    public a(Context context, ConstraintLayout constraintLayout, int i2) {
        this.f1453a = constraintLayout;
        a(context, i2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        C0017a c0017a = null;
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
                            c0017a = new C0017a(context, xml);
                            this.f1457e.put(c0017a.f1460a, c0017a);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (c0017a != null) {
                                c0017a.a(bVar);
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
        b.e.c.b bVar = new b.e.c.b();
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
                this.f1458f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i2, float f2, float f3) {
        int iB;
        int i3 = this.f1455c;
        if (i3 == i2) {
            C0017a c0017aValueAt = i2 == -1 ? this.f1457e.valueAt(0) : this.f1457e.get(i3);
            int i4 = this.f1456d;
            if ((i4 == -1 || !c0017aValueAt.f1461b.get(i4).a(f2, f3)) && this.f1456d != (iB = c0017aValueAt.b(f2, f3))) {
                b.e.c.b bVar = iB == -1 ? this.f1454b : c0017aValueAt.f1461b.get(iB).f1469f;
                if (iB != -1) {
                    int i5 = c0017aValueAt.f1461b.get(iB).f1468e;
                }
                if (bVar == null) {
                    return;
                }
                this.f1456d = iB;
                if (this.f1459g != null) {
                    throw null;
                }
                bVar.d(this.f1453a);
                if (this.f1459g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f1455c = i2;
        C0017a c0017a = this.f1457e.get(i2);
        int iB2 = c0017a.b(f2, f3);
        b.e.c.b bVar2 = iB2 == -1 ? c0017a.f1463d : c0017a.f1461b.get(iB2).f1469f;
        if (iB2 != -1) {
            int i6 = c0017a.f1461b.get(iB2).f1468e;
        }
        if (bVar2 != null) {
            this.f1456d = iB2;
            if (this.f1459g != null) {
                throw null;
            }
            bVar2.d(this.f1453a);
            if (this.f1459g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f2 + ", " + f3);
    }
}
