package defpackage;

import java.util.List;

/* JADX INFO: renamed from: j2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0972j2 {
    public final int[] a;
    public final float[] b;

    public C0972j2(List list, List list2) {
        int size = list.size();
        this.a = new int[size];
        this.b = new float[size];
        for (int i = 0; i < size; i++) {
            this.a[i] = ((Integer) list.get(i)).intValue();
            this.b[i] = ((Float) list2.get(i)).floatValue();
        }
    }

    public C0972j2(int i, int i2) {
        this.a = new int[]{i, i2};
        this.b = new float[]{0.0f, 1.0f};
    }

    public C0972j2(int i, int i2, int i3) {
        this.a = new int[]{i, i2, i3};
        this.b = new float[]{0.0f, 0.5f, 1.0f};
    }
}
