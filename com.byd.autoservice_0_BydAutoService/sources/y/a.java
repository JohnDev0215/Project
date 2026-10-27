package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintLayoutStates.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f5743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y.b f5744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5745c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5746d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<C0073a> f5747e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray<y.b> f5748f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f5749g;

    /* JADX INFO: renamed from: y.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class C0073a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5750a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f5751b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5752c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public y.b f5753d;

        public C0073a(Context context, XmlPullParser xmlPullParser) {
            this.f5752c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.State_android_id) {
                    this.f5750a = typedArrayObtainStyledAttributes.getResourceId(index, this.f5750a);
                } else if (index == R$styleable.State_constraints) {
                    this.f5752c = typedArrayObtainStyledAttributes.getResourceId(index, this.f5752c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f5752c);
                    context.getResources().getResourceName(this.f5752c);
                    if ("layout".equals(resourceTypeName)) {
                        y.b bVar = new y.b();
                        this.f5753d = bVar;
                        bVar.h(context, this.f5752c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f5751b.add(bVar);
        }

        public int b(float f3, float f4) {
            for (int i3 = 0; i3 < this.f5751b.size(); i3++) {
                if (this.f5751b.get(i3).a(f3, f4)) {
                    return i3;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f5754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f5755b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f5756c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f5757d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5758e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public y.b f5759f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f5754a = Float.NaN;
            this.f5755b = Float.NaN;
            this.f5756c = Float.NaN;
            this.f5757d = Float.NaN;
            this.f5758e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.Variant_constraints) {
                    this.f5758e = typedArrayObtainStyledAttributes.getResourceId(index, this.f5758e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f5758e);
                    context.getResources().getResourceName(this.f5758e);
                    if ("layout".equals(resourceTypeName)) {
                        y.b bVar = new y.b();
                        this.f5759f = bVar;
                        bVar.h(context, this.f5758e);
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.f5757d = typedArrayObtainStyledAttributes.getDimension(index, this.f5757d);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.f5755b = typedArrayObtainStyledAttributes.getDimension(index, this.f5755b);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.f5756c = typedArrayObtainStyledAttributes.getDimension(index, this.f5756c);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.f5754a = typedArrayObtainStyledAttributes.getDimension(index, this.f5754a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f3, float f4) {
            if (!Float.isNaN(this.f5754a) && f3 < this.f5754a) {
                return false;
            }
            if (!Float.isNaN(this.f5755b) && f4 < this.f5755b) {
                return false;
            }
            if (Float.isNaN(this.f5756c) || f3 <= this.f5756c) {
                return Float.isNaN(this.f5757d) || f4 <= this.f5757d;
            }
            return false;
        }
    }

    public a(Context context, ConstraintLayout constraintLayout, int i3) {
        this.f5743a = constraintLayout;
        a(context, i3);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, int i3) {
        XmlResourceParser xml = context.getResources().getXml(i3);
        C0073a c0073a = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    byte b3 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                b3 = 4;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b3 = 2;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b3 = 1;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                b3 = 0;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                b3 = 3;
                            }
                            break;
                    }
                    if (b3 != 0 && b3 != 1) {
                        if (b3 == 2) {
                            c0073a = new C0073a(context, xml);
                            this.f5747e.put(c0073a.f5750a, c0073a);
                        } else if (b3 == 3) {
                            b bVar = new b(context, xml);
                            if (c0073a != null) {
                                c0073a.a(bVar);
                            }
                        } else if (b3 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
        }
    }

    public final void b(Context context, XmlPullParser xmlPullParser) {
        y.b bVar = new y.b();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i3 = 0; i3 < attributeCount; i3++) {
            if ("id".equals(xmlPullParser.getAttributeName(i3))) {
                String attributeValue = xmlPullParser.getAttributeValue(i3);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                bVar.v(context, xmlPullParser);
                this.f5748f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i3, float f3, float f4) {
        int iB;
        int i4 = this.f5745c;
        if (i4 == i3) {
            C0073a c0073aValueAt = i3 == -1 ? this.f5747e.valueAt(0) : this.f5747e.get(i4);
            int i5 = this.f5746d;
            if ((i5 == -1 || !c0073aValueAt.f5751b.get(i5).a(f3, f4)) && this.f5746d != (iB = c0073aValueAt.b(f3, f4))) {
                y.b bVar = iB == -1 ? this.f5744b : c0073aValueAt.f5751b.get(iB).f5759f;
                if (iB != -1) {
                    int i6 = c0073aValueAt.f5751b.get(iB).f5758e;
                }
                if (bVar == null) {
                    return;
                }
                this.f5746d = iB;
                if (this.f5749g != null) {
                    throw null;
                }
                bVar.d(this.f5743a);
                if (this.f5749g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f5745c = i3;
        C0073a c0073a = this.f5747e.get(i3);
        int iB2 = c0073a.b(f3, f4);
        y.b bVar2 = iB2 == -1 ? c0073a.f5753d : c0073a.f5751b.get(iB2).f5759f;
        if (iB2 != -1) {
            int i7 = c0073a.f5751b.get(iB2).f5758e;
        }
        if (bVar2 != null) {
            this.f5746d = iB2;
            if (this.f5749g != null) {
                throw null;
            }
            bVar2.d(this.f5743a);
            if (this.f5749g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i3 + ", dim =" + f3 + ", " + f4);
    }
}
