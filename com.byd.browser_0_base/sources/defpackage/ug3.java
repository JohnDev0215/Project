package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: compiled from: TutorialListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ug3 {
    public final Context a;
    public final ya1 b;
    public final em3 c;
    public final ax0 d;
    public final Callback<Tutorial> e;

    public ug3(ya1 ya1Var, Context context, em3 em3Var, ax0 ax0Var, c3 c3Var) {
        this.b = ya1Var;
        this.a = context;
        this.c = em3Var;
        this.d = ax0Var;
        this.e = c3Var;
        em3Var.d(new se3(10, this));
    }
}
