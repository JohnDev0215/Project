package defpackage;

import android.content.Context;
import android.view.ViewStub;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: VideoIPHCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rl3 {
    public final Context a;
    public final PropertyModel b;
    public final ax0 c;
    public final Callback<Tutorial> d;
    public final Callback<Tutorial> e;

    public rl3(ViewStub viewStub, ax0 ax0Var, wm1 wm1Var, so soVar) {
        this.a = viewStub.getContext();
        this.c = ax0Var;
        this.d = wm1Var;
        this.e = soVar;
        PropertyModel propertyModel = new PropertyModel(sl3.h);
        this.b = propertyModel;
        j.b(propertyModel, new tl3(viewStub), new ee3(7));
    }
}
