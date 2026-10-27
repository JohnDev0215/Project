package r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final SparseIntArray f21748n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f21749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f21750b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f21751e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f21752f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f21753g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f21754h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f21755i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f21756j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f21757k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f21758m;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f21748n = sparseIntArray;
        sparseIntArray.append(6, 1);
        sparseIntArray.append(7, 2);
        sparseIntArray.append(8, 3);
        sparseIntArray.append(4, 4);
        sparseIntArray.append(5, 5);
        sparseIntArray.append(0, 6);
        sparseIntArray.append(1, 7);
        sparseIntArray.append(2, 8);
        sparseIntArray.append(3, 9);
        sparseIntArray.append(9, 10);
        sparseIntArray.append(10, 11);
        sparseIntArray.append(11, 12);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.f21769i);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            switch (f21748n.get(index)) {
                case 1:
                    this.f21749a = typedArrayObtainStyledAttributes.getFloat(index, this.f21749a);
                    break;
                case 2:
                    this.f21750b = typedArrayObtainStyledAttributes.getFloat(index, this.f21750b);
                    break;
                case 3:
                    this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                    break;
                case 4:
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                    break;
                case 5:
                    this.f21751e = typedArrayObtainStyledAttributes.getFloat(index, this.f21751e);
                    break;
                case 6:
                    this.f21752f = typedArrayObtainStyledAttributes.getDimension(index, this.f21752f);
                    break;
                case 7:
                    this.f21753g = typedArrayObtainStyledAttributes.getDimension(index, this.f21753g);
                    break;
                case 8:
                    this.f21755i = typedArrayObtainStyledAttributes.getDimension(index, this.f21755i);
                    break;
                case 9:
                    this.f21756j = typedArrayObtainStyledAttributes.getDimension(index, this.f21756j);
                    break;
                case 10:
                    this.f21757k = typedArrayObtainStyledAttributes.getDimension(index, this.f21757k);
                    break;
                case 11:
                    this.l = true;
                    this.f21758m = typedArrayObtainStyledAttributes.getDimension(index, this.f21758m);
                    break;
                case 12:
                    this.f21754h = m.f(typedArrayObtainStyledAttributes, index, this.f21754h);
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
