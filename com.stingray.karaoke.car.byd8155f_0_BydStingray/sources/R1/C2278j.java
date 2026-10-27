package r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import j1.AbstractC1537a;

/* JADX INFO: renamed from: r1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2278j {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final SparseIntArray f21738j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f21739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21740b;
    public int c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f21741e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f21742f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f21743g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f21744h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21745i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f21738j = sparseIntArray;
        sparseIntArray.append(3, 1);
        sparseIntArray.append(5, 2);
        sparseIntArray.append(9, 3);
        sparseIntArray.append(2, 4);
        sparseIntArray.append(1, 5);
        sparseIntArray.append(0, 6);
        sparseIntArray.append(4, 7);
        sparseIntArray.append(8, 8);
        sparseIntArray.append(7, 9);
        sparseIntArray.append(6, 10);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.f21766f);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            switch (f21738j.get(index)) {
                case 1:
                    this.f21741e = typedArrayObtainStyledAttributes.getFloat(index, this.f21741e);
                    break;
                case 2:
                    this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                    break;
                case 3:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        String str = AbstractC1537a.f16999a[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                    }
                    break;
                case 4:
                    typedArrayObtainStyledAttributes.getInt(index, 0);
                    break;
                case 5:
                    this.f21739a = m.f(typedArrayObtainStyledAttributes, index, this.f21739a);
                    break;
                case 6:
                    this.f21740b = typedArrayObtainStyledAttributes.getInteger(index, this.f21740b);
                    break;
                case 7:
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                    break;
                case 8:
                    this.f21743g = typedArrayObtainStyledAttributes.getInteger(index, this.f21743g);
                    break;
                case 9:
                    this.f21742f = typedArrayObtainStyledAttributes.getFloat(index, this.f21742f);
                    break;
                case 10:
                    int i10 = typedArrayObtainStyledAttributes.peekValue(index).type;
                    if (i10 == 1) {
                        this.f21745i = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    } else if (i10 == 3) {
                        String string = typedArrayObtainStyledAttributes.getString(index);
                        this.f21744h = string;
                        if (string.indexOf("/") > 0) {
                            this.f21745i = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        }
                    } else {
                        typedArrayObtainStyledAttributes.getInteger(index, this.f21745i);
                    }
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
