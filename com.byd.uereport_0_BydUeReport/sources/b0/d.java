package b0;

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
    public int f2012a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2013b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2014c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<a> f2015d = new SparseArray<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<b0.b> f2016e = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2017a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b> f2018b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2019c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2020d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f2019c = -1;
            this.f2020d = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.State_android_id) {
                    this.f2017a = typedArrayObtainStyledAttributes.getResourceId(index, this.f2017a);
                } else if (index == R$styleable.State_constraints) {
                    this.f2019c = typedArrayObtainStyledAttributes.getResourceId(index, this.f2019c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2019c);
                    context.getResources().getResourceName(this.f2019c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2020d = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.f2018b.add(bVar);
        }

        public int b(float f8, float f9) {
            for (int i8 = 0; i8 < this.f2018b.size(); i8++) {
                if (this.f2018b.get(i8).a(f8, f9)) {
                    return i8;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2021a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2022b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2023c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2024d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2025e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2026f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f2021a = Float.NaN;
            this.f2022b = Float.NaN;
            this.f2023c = Float.NaN;
            this.f2024d = Float.NaN;
            this.f2025e = -1;
            this.f2026f = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.Variant_constraints) {
                    this.f2025e = typedArrayObtainStyledAttributes.getResourceId(index, this.f2025e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2025e);
                    context.getResources().getResourceName(this.f2025e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2026f = true;
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.f2024d = typedArrayObtainStyledAttributes.getDimension(index, this.f2024d);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.f2022b = typedArrayObtainStyledAttributes.getDimension(index, this.f2022b);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.f2023c = typedArrayObtainStyledAttributes.getDimension(index, this.f2023c);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.f2021a = typedArrayObtainStyledAttributes.getDimension(index, this.f2021a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f8, float f9) {
            if (!Float.isNaN(this.f2021a) && f8 < this.f2021a) {
                return false;
            }
            if (!Float.isNaN(this.f2022b) && f9 < this.f2022b) {
                return false;
            }
            if (Float.isNaN(this.f2023c) || f8 <= this.f2023c) {
                return Float.isNaN(this.f2024d) || f9 <= this.f2024d;
            }
            return false;
        }
    }

    public d(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    public int a(int i8, int i9, float f8, float f9) {
        a aVar = this.f2015d.get(i9);
        if (aVar == null) {
            return i9;
        }
        if (f8 == -1.0f || f9 == -1.0f) {
            if (aVar.f2019c == i8) {
                return i8;
            }
            Iterator<b> it = aVar.f2018b.iterator();
            while (it.hasNext()) {
                if (i8 == it.next().f2025e) {
                    return i8;
                }
            }
            return aVar.f2019c;
        }
        b bVar = null;
        for (b bVar2 : aVar.f2018b) {
            if (bVar2.a(f8, f9)) {
                if (i8 == bVar2.f2025e) {
                    return i8;
                }
                bVar = bVar2;
            }
        }
        return bVar != null ? bVar.f2025e : aVar.f2019c;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i8 = 0; i8 < indexCount; i8++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i8);
            if (index == R$styleable.StateSet_defaultState) {
                this.f2012a = typedArrayObtainStyledAttributes.getResourceId(index, this.f2012a);
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
                    byte b8 = -1;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                b8 = 2;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                b8 = 0;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b8 = 1;
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
                            aVar = new a(context, xmlPullParser);
                            this.f2015d.put(aVar.f2017a, aVar);
                        } else if (b8 != 3) {
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
        } catch (IOException e8) {
            e8.printStackTrace();
        } catch (XmlPullParserException e9) {
            e9.printStackTrace();
        }
    }

    public int c(int i8, int i9, int i10) {
        return d(-1, i8, i9, i10);
    }

    public int d(int i8, int i9, float f8, float f9) {
        int iB;
        if (i8 == i9) {
            a aVarValueAt = i9 == -1 ? this.f2015d.valueAt(0) : this.f2015d.get(this.f2013b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.f2014c == -1 || !aVarValueAt.f2018b.get(i8).a(f8, f9)) && i8 != (iB = aVarValueAt.b(f8, f9))) ? iB == -1 ? aVarValueAt.f2019c : aVarValueAt.f2018b.get(iB).f2025e : i8;
        }
        a aVar = this.f2015d.get(i9);
        if (aVar == null) {
            return -1;
        }
        int iB2 = aVar.b(f8, f9);
        return iB2 == -1 ? aVar.f2019c : aVar.f2018b.get(iB2).f2025e;
    }
}
