package b0;

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
    public final ConstraintLayout f1919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b0.b f1920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1921c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1922d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<C0005a> f1923e = new SparseArray<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray<b0.b> f1924f = new SparseArray<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f1925g;

    /* JADX INFO: renamed from: b0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class C0005a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1926a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f1927b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1928c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b0.b f1929d;

        public C0005a(Context context, XmlPullParser xmlPullParser) {
            this.f1928c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.State_android_id) {
                    this.f1926a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1926a);
                } else if (index == R$styleable.State_constraints) {
                    this.f1928c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1928c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1928c);
                    context.getResources().getResourceName(this.f1928c);
                    if ("layout".equals(resourceTypeName)) {
                        b0.b bVar = new b0.b();
                        this.f1929d = bVar;
                        bVar.h(context, this.f1928c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f1927b.add(bVar);
        }

        public int b(float f8, float f9) {
            for (int i8 = 0; i8 < this.f1927b.size(); i8++) {
                if (this.f1927b.get(i8).a(f8, f9)) {
                    return i8;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1930a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1931b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1932c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1933d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1934e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b0.b f1935f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1930a = Float.NaN;
            this.f1931b = Float.NaN;
            this.f1932c = Float.NaN;
            this.f1933d = Float.NaN;
            this.f1934e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.Variant_constraints) {
                    this.f1934e = typedArrayObtainStyledAttributes.getResourceId(index, this.f1934e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1934e);
                    context.getResources().getResourceName(this.f1934e);
                    if ("layout".equals(resourceTypeName)) {
                        b0.b bVar = new b0.b();
                        this.f1935f = bVar;
                        bVar.h(context, this.f1934e);
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.f1933d = typedArrayObtainStyledAttributes.getDimension(index, this.f1933d);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.f1931b = typedArrayObtainStyledAttributes.getDimension(index, this.f1931b);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.f1932c = typedArrayObtainStyledAttributes.getDimension(index, this.f1932c);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.f1930a = typedArrayObtainStyledAttributes.getDimension(index, this.f1930a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f8, float f9) {
            if (!Float.isNaN(this.f1930a) && f8 < this.f1930a) {
                return false;
            }
            if (!Float.isNaN(this.f1931b) && f9 < this.f1931b) {
                return false;
            }
            if (Float.isNaN(this.f1932c) || f8 <= this.f1932c) {
                return Float.isNaN(this.f1933d) || f9 <= this.f1933d;
            }
            return false;
        }
    }

    public a(Context context, ConstraintLayout constraintLayout, int i8) {
        this.f1919a = constraintLayout;
        a(context, i8);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, int i8) {
        XmlResourceParser xml = context.getResources().getXml(i8);
        C0005a c0005a = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    byte b8 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                b8 = 4;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b8 = 2;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b8 = 1;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                b8 = 0;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                b8 = 3;
                            }
                            break;
                    }
                    if (b8 != 0 && b8 != 1) {
                        if (b8 == 2) {
                            c0005a = new C0005a(context, xml);
                            this.f1923e.put(c0005a.f1926a, c0005a);
                        } else if (b8 == 3) {
                            b bVar = new b(context, xml);
                            if (c0005a != null) {
                                c0005a.a(bVar);
                            }
                        } else if (b8 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        } catch (XmlPullParserException e9) {
            e9.printStackTrace();
        }
    }

    public final void b(Context context, XmlPullParser xmlPullParser) {
        b0.b bVar = new b0.b();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i8 = 0; i8 < attributeCount; i8++) {
            if ("id".equals(xmlPullParser.getAttributeName(i8))) {
                String attributeValue = xmlPullParser.getAttributeValue(i8);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                bVar.v(context, xmlPullParser);
                this.f1924f.put(identifier, bVar);
                return;
            }
        }
    }

    public void c(c cVar) {
    }

    public void d(int i8, float f8, float f9) {
        int iB;
        int i9 = this.f1921c;
        if (i9 == i8) {
            C0005a c0005aValueAt = i8 == -1 ? this.f1923e.valueAt(0) : this.f1923e.get(i9);
            int i10 = this.f1922d;
            if ((i10 == -1 || !c0005aValueAt.f1927b.get(i10).a(f8, f9)) && this.f1922d != (iB = c0005aValueAt.b(f8, f9))) {
                b0.b bVar = iB == -1 ? this.f1920b : c0005aValueAt.f1927b.get(iB).f1935f;
                if (iB != -1) {
                    int i11 = c0005aValueAt.f1927b.get(iB).f1934e;
                }
                if (bVar == null) {
                    return;
                }
                this.f1922d = iB;
                if (this.f1925g != null) {
                    throw null;
                }
                bVar.d(this.f1919a);
                if (this.f1925g != null) {
                    throw null;
                }
                return;
            }
            return;
        }
        this.f1921c = i8;
        C0005a c0005a = this.f1923e.get(i8);
        int iB2 = c0005a.b(f8, f9);
        b0.b bVar2 = iB2 == -1 ? c0005a.f1929d : c0005a.f1927b.get(iB2).f1935f;
        if (iB2 != -1) {
            int i12 = c0005a.f1927b.get(iB2).f1934e;
        }
        if (bVar2 != null) {
            this.f1922d = iB2;
            if (this.f1925g != null) {
                throw null;
            }
            bVar2.d(this.f1919a);
            if (this.f1925g != null) {
                throw null;
            }
            return;
        }
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i8 + ", dim =" + f8 + ", " + f9);
    }
}
