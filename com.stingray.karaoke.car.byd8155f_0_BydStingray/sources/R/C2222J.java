package r;

import android.content.Context;
import android.graphics.RectF;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: r.J, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2222J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f21434a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f21435b = -1.0f;
    public float c = -1.0f;
    public float d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f21436e = new int[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f21437f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextView f21438g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Context f21439h;

    static {
        new RectF();
        new ConcurrentHashMap();
    }

    public C2222J(TextView textView) {
        this.f21438g = textView;
        this.f21439h = textView.getContext();
        new C2220H();
    }

    public static int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i7 : iArr) {
            if (i7 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i7)) < 0) {
                arrayList.add(Integer.valueOf(i7));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr2[i10] = ((Integer) arrayList.get(i10)).intValue();
        }
        return iArr2;
    }

    public final boolean b() {
        return !(this.f21438g instanceof AbstractC2260s);
    }
}
