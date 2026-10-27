package r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f21746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21747b;
    public float c;
    public float d;

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.f21767g);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == 1) {
                this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
            } else if (index == 0) {
                int i10 = typedArrayObtainStyledAttributes.getInt(index, this.f21746a);
                this.f21746a = i10;
                this.f21746a = m.d[i10];
            } else if (index == 4) {
                this.f21747b = typedArrayObtainStyledAttributes.getInt(index, this.f21747b);
            } else if (index == 3) {
                this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
