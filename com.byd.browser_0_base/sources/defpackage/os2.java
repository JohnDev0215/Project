package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.R$styleable;
import java.util.ArrayList;

/* JADX INFO: compiled from: StateSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class os2 {
    public int a;
    public final SparseArray<a> b = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {
        public final int a;
        public final ArrayList<b> b = new ArrayList<>();
        public final int c;

        public a(Context context, XmlResourceParser xmlResourceParser) {
            this.c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.State_android_id) {
                    this.a = typedArrayObtainStyledAttributes.getResourceId(index, this.a);
                } else if (index == R$styleable.State_constraints) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
                    this.c = resourceId;
                    String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                    context.getResources().getResourceName(resourceId);
                    "layout".equals(resourceTypeName);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {
        public final float a;
        public final float b;
        public final float c;
        public final float d;
        public final int e;

        public b(Context context, XmlResourceParser xmlResourceParser) {
            this.a = Float.NaN;
            this.b = Float.NaN;
            this.c = Float.NaN;
            this.d = Float.NaN;
            this.e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.Variant_constraints) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                    this.e = resourceId;
                    String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                    context.getResources().getResourceName(resourceId);
                    "layout".equals(resourceTypeName);
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.d = typedArrayObtainStyledAttributes.getDimension(index, this.d);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.b = typedArrayObtainStyledAttributes.getDimension(index, this.b);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.c = typedArrayObtainStyledAttributes.getDimension(index, this.c);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.a = typedArrayObtainStyledAttributes.getDimension(index, this.a);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public final boolean a(float f, float f2) {
            float f3 = this.a;
            if (!Float.isNaN(f3) && f < f3) {
                return false;
            }
            float f4 = this.b;
            if (!Float.isNaN(f4) && f2 < f4) {
                return false;
            }
            float f5 = this.c;
            if (!Float.isNaN(f5) && f > f5) {
                return false;
            }
            float f6 = this.d;
            return Float.isNaN(f6) || f2 <= f6;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public os2(android.content.Context r12, android.content.res.XmlResourceParser r13) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.os2.<init>(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    public final int a(int i) {
        ArrayList<b> arrayList;
        int i2;
        ArrayList<b> arrayList2;
        float f = -1;
        SparseArray<a> sparseArray = this.b;
        int i3 = 0;
        if (-1 == i) {
            a aVarValueAt = i == -1 ? sparseArray.valueAt(0) : sparseArray.get(-1);
            if (aVarValueAt == null) {
                return -1;
            }
            while (true) {
                arrayList2 = aVarValueAt.b;
                if (i3 >= arrayList2.size()) {
                    i3 = -1;
                    break;
                }
                if (arrayList2.get(i3).a(f, f)) {
                    break;
                }
                i3++;
            }
            if (-1 == i3) {
                return -1;
            }
            i2 = i3 == -1 ? aVarValueAt.c : arrayList2.get(i3).e;
        } else {
            a aVar = sparseArray.get(i);
            if (aVar == null) {
                return -1;
            }
            while (true) {
                arrayList = aVar.b;
                if (i3 >= arrayList.size()) {
                    i3 = -1;
                    break;
                }
                if (arrayList.get(i3).a(f, f)) {
                    break;
                }
                i3++;
            }
            i2 = i3 == -1 ? aVar.c : arrayList.get(i3).e;
        }
        return i2;
    }
}
