package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.util.SparseArray;
import java.util.Objects;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class UO {
    public final int a;
    public final TO b;

    public UO(int i, TO to) {
        this.a = i;
        this.b = to;
    }

    public abstract void a(int i);

    public void b(int i, QO qo) {
        Bitmap bitmapC;
        TO to = this.b;
        if (to != null) {
            int i2 = this.a;
            ResourceManager resourceManager = (ResourceManager) to;
            Objects.requireNonNull(resourceManager);
            if (qo == null || (bitmapC = qo.c()) == null) {
                return;
            }
            SparseArray sparseArray = (SparseArray) resourceManager.b.get(i2);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                resourceManager.b.put(i2, sparseArray);
            }
            sparseArray.put(i, new PO(resourceManager.c, qo));
            long j = resourceManager.d;
            if (j == 0) {
                return;
            }
            N.MM7E4tBk(j, resourceManager, i2, i, bitmapC, qo.a().width(), qo.a().height(), qo.d());
        }
    }

    public abstract void c(int i);
}
