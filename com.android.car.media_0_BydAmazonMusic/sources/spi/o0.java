package spi;

import android.app.Activity;
import android.app.Application;
import android.car.IXCarApplicationStub;
import android.car.XCarRoute;
import android.car.media.IXCarMediaSource;
import android.car.utils.Logs;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.main.R$string;
import java.util.Objects;

/* JADX INFO: compiled from: BydCarApplicationStub.java */
/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_APPLICATION_STUB)
public final class o0 implements IXCarApplicationStub {
    public q0 a;
    public String b;
    public IXCarMediaSource c;
    public int d;
    public int e;
    public boolean f = false;
    public long g = -1;
    public final a h = new a();

    /* JADX INFO: compiled from: BydCarApplicationStub.java */
    public class a implements Application.ActivityLifecycleCallbacks {
        public a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            TextView textView;
            if (o0.a(o0.this, activity) && i2.a(activity)) {
                o0 o0Var = o0.this;
                o0Var.b();
                q0 q0Var = new q0(activity);
                o0Var.a = q0Var;
                String string = activity.getString(R$string.dialog_message);
                if (!TextUtils.isEmpty(string) && (textView = q0Var.d) != null) {
                    textView.setText(string);
                    q0Var.d.setVisibility(0);
                }
                q0Var.e = new q6(o0Var);
                long j = o0Var.g;
                if (!o0Var.f) {
                    j = -1;
                }
                q0Var.b = j;
                q0Var.show();
                o0Var.f = false;
                o0Var.g = -1L;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            if (o0.a(o0.this, activity)) {
                o0 o0Var = o0.this;
                q0 q0Var = o0Var.a;
                if (q0Var != null) {
                    o0Var.g = q0Var.b;
                } else {
                    o0Var.g = -1L;
                }
                o0Var.b();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPostResumed(Activity activity) {
            if (o0.a(o0.this, activity) && i2.a(activity)) {
                o0.this.a.b();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            q0 q0Var;
            if (o0.a(o0.this, activity) && i2.a(activity) && (q0Var = o0.this.a) != null) {
                q0Var.c();
            }
        }
    }

    public static boolean a(o0 o0Var, Activity activity) {
        Objects.requireNonNull(o0Var);
        return activity != null && activity.getClass().getName().equals("com.android.car.media.MediaActivity");
    }

    public final void b() {
        q0 q0Var = this.a;
        if (q0Var != null) {
            q0Var.dismiss();
            this.a = null;
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
    }

    @Override // android.car.IXCarApplicationStub
    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.uiMode & 48;
        int i2 = configuration.orientation;
        if (this.d != i) {
            this.d = i;
            this.f = true;
        }
        if (this.e != i2) {
            this.e = i2;
            this.f = true;
        }
        StringBuilder sbK = c.k("onConfigurationChanged: ");
        sbK.append(this.d);
        sbK.append(" ");
        sbK.append(this.e);
        sbK.append(" ");
        sbK.append(this.f);
        Logs.w("BydCarApplicationStub", sbK.toString());
        String displayLanguage = configuration.locale.getDisplayLanguage();
        String str = this.b;
        if (str == null || str.equals(displayLanguage)) {
            return;
        }
        StringBuilder sbK2 = c.k("onConfigurationChanged: ");
        sbK2.append(this.b);
        sbK2.append(" -> ");
        sbK2.append(displayLanguage);
        Logs.d("BydCarApplicationStub", sbK2.toString());
        this.b = displayLanguage;
        IXCarMediaSource iXCarMediaSource = this.c;
        if (iXCarMediaSource == null && iXCarMediaSource == null) {
            this.c = (IXCarMediaSource) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_SOURCE_SERVICE).navigation();
        }
        IXCarMediaSource iXCarMediaSource2 = this.c;
        if (iXCarMediaSource2 != null) {
            iXCarMediaSource2.reConnectMediaSource();
        }
    }

    @Override // android.car.IXCarApplicationStub
    public final void onCreate(Application application) {
        this.b = application.getResources().getConfiguration().locale.getDisplayLanguage();
        application.registerActivityLifecycleCallbacks(this.h);
        this.d = application.getResources().getConfiguration().uiMode & 48;
        this.e = application.getResources().getConfiguration().orientation;
        StringBuilder sbK = c.k("onCreate: ");
        sbK.append(this.b);
        sbK.append(" ");
        sbK.append(this.d);
        sbK.append(" ");
        sbK.append(this.e);
        Logs.w("BydCarApplicationStub", sbK.toString());
    }
}
