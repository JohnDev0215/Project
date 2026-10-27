package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: WebFeedFollowIntroView.java */
/* JADX INFO: loaded from: classes.dex */
public final class ar3 {
    public final Activity a;
    public final h7 b;
    public final Handler c = new Handler();
    public final View d;
    public final xe3 e;
    public final Runnable f;
    public bu g;
    public final int h;

    public ar3(AppCompatActivity appCompatActivity, i7 i7Var, ImageButton imageButton, xe3 xe3Var, Runnable runnable) {
        oj2.k(Profile.d());
        this.a = appCompatActivity;
        this.b = i7Var;
        this.d = imageButton;
        this.e = xe3Var;
        this.f = runnable;
        sk skVar = bq.a;
        this.h = N.M37SqSAy("WebFeed", "intro-show-timeout-millis", 8000);
    }

    public final nn3 a() {
        nn3 nn3Var = new nn3(this.d);
        nn3Var.e(new Rect(0, 0, 0, this.a.getResources().getDimensionPixelOffset(R$dimen.web_feed_intro_y_inset)));
        return nn3Var;
    }

    public final void b() {
        bu buVar = this.g;
        if (buVar != null) {
            buVar.b();
            this.g.r.b();
            this.g = null;
        }
    }

    public final void c(View.OnTouchListener onTouchListener, Runnable runnable, Runnable runnable2) {
        xe3 xe3Var = this.e;
        if (xe3Var != null && !xe3Var.shouldTriggerHelpUI("IPH_WebFeedFollow")) {
            runnable2.run();
            return;
        }
        Activity activity = this.a;
        View view = this.d;
        int i = R$string.menu_follow;
        bu buVar = new bu(activity, view, i, i, a(), R$drawable.ic_add, qo.f().c(), onTouchListener);
        this.g = buVar;
        buVar.addOnDismissListener(new PopupWindow.OnDismissListener() { // from class: zq3
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                ar3 ar3Var = this.e;
                ar3Var.c.postDelayed(new ql0(3, ar3Var), 200L);
                ar3Var.f.run();
            }
        });
        this.g.d(this.h);
        ((i7) this.b).c(Integer.valueOf(R$id.follow_chip_view));
        this.g.f();
        runnable.run();
    }

    public final void d() {
        Activity activity = this.a;
        View view = this.d;
        int i = R$string.menu_following;
        bu buVar = new bu(activity, view, i, i, a(), R$drawable.ic_done_blue, qo.f().c(), null);
        buVar.e(true);
        buVar.f();
    }
}
