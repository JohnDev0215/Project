package defpackage;

import android.content.res.Resources;
import android.util.StateSet;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class I extends G {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int[][] f2J;

    public I(I i, J j, Resources resources) {
        super(i, j, resources);
        if (i != null) {
            this.f2J = i.f2J;
        } else {
            this.f2J = new int[this.g.length][];
        }
    }

    public int g(int[] iArr) {
        int[][] iArr2 = this.f2J;
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                return i2;
            }
        }
        return -1;
    }
}
