package defpackage;

import android.graphics.Rect;
import android.transition.Transition;

/* JADX INFO: renamed from: u4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1581u4 extends Transition.EpicenterCallback {
    public final /* synthetic */ Rect a;

    public C1581u4(C1801y4 c1801y4, Rect rect) {
        this.a = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public Rect onGetEpicenter(Transition transition) {
        return this.a;
    }
}
