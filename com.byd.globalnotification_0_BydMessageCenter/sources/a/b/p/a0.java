package a.b.p;

import android.content.Context;
import android.graphics.RectF;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class a0 {
    public static final RectF k = new RectF();
    public static ConcurrentHashMap<String, Method> l = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Field> m = new ConcurrentHashMap<>();
    public final TextView h;
    public final Context i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f228a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f229b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f230c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f231d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f232e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f233f = new int[0];
    public boolean g = false;
    public final c j = new b();

    public static class a extends c {
    }

    public static class b extends a {
    }

    public static class c {
    }

    public a0(TextView textView) {
        this.h = textView;
        this.i = textView.getContext();
    }

    public final int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public final boolean b() {
        if (d() && this.f228a == 1) {
            if (!this.g || this.f233f.length == 0) {
                int iFloor = ((int) Math.floor((this.f232e - this.f231d) / this.f230c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i = 0; i < iFloor; i++) {
                    iArr[i] = Math.round((i * this.f230c) + this.f231d);
                }
                this.f233f = a(iArr);
            }
            this.f229b = true;
        } else {
            this.f229b = false;
        }
        return this.f229b;
    }

    public final boolean c() {
        boolean z = this.f233f.length > 0;
        this.g = z;
        if (z) {
            this.f228a = 1;
            int[] iArr = this.f233f;
            this.f231d = iArr[0];
            this.f232e = iArr[r0 - 1];
            this.f230c = -1.0f;
        }
        return this.g;
    }

    public final boolean d() {
        return !(this.h instanceof k);
    }

    public final void e(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
        this.f228a = 1;
        this.f231d = f2;
        this.f232e = f3;
        this.f230c = f4;
        this.g = false;
    }
}
