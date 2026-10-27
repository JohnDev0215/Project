package defpackage;

import android.graphics.Rect;
import android.transition.Transition;

/* JADX INFO: renamed from: x4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1746x4 extends Transition.EpicenterCallback {
    public final /* synthetic */ Rect a;

    public C1746x4(C1801y4 c1801y4, Rect rect) {
        this.a = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public Rect onGetEpicenter(Transition transition) {
        Rect rect = this.a;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return this.a;
    }
}
