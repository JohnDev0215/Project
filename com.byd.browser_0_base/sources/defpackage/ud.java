package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask;

/* JADX INFO: compiled from: BaseScreenshotCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ud implements vi {
    public final Activity e;
    public final String f;
    public final rq g;
    public final ji h;
    public final cg0 i;
    public Bitmap j;

    public ud(Activity activity, String str, rq rqVar, ji jiVar) {
        EditorScreenshotTask editorScreenshotTask = new EditorScreenshotTask(activity, jiVar);
        this.e = activity;
        this.f = str;
        this.g = rqVar;
        this.h = jiVar;
        this.i = editorScreenshotTask;
    }

    @Override // defpackage.vi
    public final void d(int i, int i2) {
        if (i == 0) {
            this.h.e(this);
            f();
        }
    }

    public void f() {
        this.i.b(new eu1(13, this));
    }

    @Override // defpackage.vi
    public final void e() {
    }

    @Override // defpackage.vi
    public final void a(float f) {
    }

    @Override // defpackage.vi
    public final void b(ii iiVar) {
    }

    @Override // defpackage.vi
    public final void c(int i) {
    }
}
