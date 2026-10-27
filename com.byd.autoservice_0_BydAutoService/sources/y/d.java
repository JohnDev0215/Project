package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.R$styleable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: StateSet.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5836a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5837b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5838c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<a> f5839d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<y.b> f5840e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f5842b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5844d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f5843c = -1;
            this.f5844d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.State_android_id) {
                    this.f5841a = typedArrayObtainStyledAttributes.getResourceId(index, this.f5841a);
                } else if (index == R$styleable.State_constraints) {
                    this.f5843c = typedArrayObtainStyledAttributes.getResourceId(index, this.f5843c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f5843c);
                    context.getResources().getResourceName(this.f5843c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f5844d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f5842b.add(bVar);
        }

        public int b(float f3, float f4) {
            for (int i3 = 0; i3 < this.f5842b.size(); i3++) {
                if (this.f5842b.get(i3).a(f3, f4)) {
                    return i3;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f5845a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f5846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f5847c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f5848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5850f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f5845a = Float.NaN;
            this.f5846b = Float.NaN;
            this.f5847c = Float.NaN;
            this.f5848d = Float.NaN;
            this.f5849e = -1;
            this.f5850f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.Variant_constraints) {
                    this.f5849e = typedArrayObtainStyledAttributes.getResourceId(index, this.f5849e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f5849e);
                    context.getResources().getResourceName(this.f5849e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f5850f = true;
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.f5848d = typedArrayObtainStyledAttributes.getDimension(index, this.f5848d);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.f5846b = typedArrayObtainStyledAttributes.getDimension(index, this.f5846b);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.f5847c = typedArrayObtainStyledAttributes.getDimension(index, this.f5847c);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.f5845a = typedArrayObtainStyledAttributes.getDimension(index, this.f5845a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f3, float f4) {
            if (!Float.isNaN(this.f5845a) && f3 < this.f5845a) {
                return false;
            }
            if (!Float.isNaN(this.f5846b) && f4 < this.f5846b) {
                return false;
            }
            if (Float.isNaN(this.f5847c) || f3 <= this.f5847c) {
                return Float.isNaN(this.f5848d) || f4 <= this.f5848d;
            }
            return false;
        }
    }

    public d(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    public int a(int i3, int i4, float f3, float f4) {
        a aVar = this.f5839d.get(i4);
        if (aVar == null) {
            return i4;
        }
        if (f3 == -1.0f || f4 == -1.0f) {
            if (aVar.f5843c == i3) {
                return i3;
            }
            Iterator<b> it = aVar.f5842b.iterator();
            while (it.hasNext()) {
                if (i3 == it.next().f5849e) {
                    return i3;
                }
            }
            return aVar.f5843c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f5842b) {
            if (bVar2.a(f3, f4)) {
                if (i3 == bVar2.f5849e) {
                    return i3;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f5849e : aVar.f5843c;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i3);
            if (index == R$styleable.StateSet_defaultState) {
                this.f5836a = typedArrayObtainStyledAttributes.getResourceId(index, this.f5836a);
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
                    byte b3 = -1;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                b3 = 2;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                b3 = 0;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b3 = 1;
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
                            aVar = new a(context, xmlPullParser);
                            this.f5839d.put(aVar.f5841a, aVar);
                        } else if (b3 != 3) {
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
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
        }
    }

    public int c(int i3, int i4, int i5) {
        return d(-1, i3, i4, i5);
    }

    public int d(int i3, int i4, float f3, float f4) {
        int iB;
        if (i3 == i4) {
            a aVarValueAt = i4 == -1 ? this.f5839d.valueAt(0) : this.f5839d.get(this.f5837b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f5838c == -1 || !aVarValueAt.f5842b.get(i3).a(f3, f4)) && i3 != (iB = aVarValueAt.b(f3, f4))) ? iB == -1 ? aVarValueAt.f5843c : aVarValueAt.f5842b.get(iB).f5849e : i3;
        }
        a aVar = this.f5839d.get(i4);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f3, f4);
        return iB2 == -1 ? aVar.f5843c : aVar.f5842b.get(iB2).f5849e;
    }
}
