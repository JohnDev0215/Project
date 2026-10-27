package com.byd.auto_photo;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialogSupActivity extends Activity {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f998c;
    private ReceiverForVoice f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f996a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f997b = -1;
    private int d = -1;
    private Point e = new Point();

    private void d(int i) {
        if (this.f996a == i) {
            return;
        }
        this.f996a = i;
        if (i != 0) {
            setTheme(i);
        }
        h();
    }

    protected int e() {
        return getResources().getDimensionPixelSize(R.dimen.dialog_min_height);
    }

    protected int f() {
        return getResources().getDimensionPixelSize(R.dimen.dialog_min_width);
    }

    protected int g(boolean z) {
        if (a.f1178a.intValue() >= 35) {
            return z ? R.style.CustomDialogNight : R.style.CustomDialogDay;
        }
        return 0;
    }

    protected abstract void h();

    protected boolean i(Configuration configuration) {
        return (this.f998c == null || configuration.locale.toLanguageTag().equals(this.f998c)) ? false : true;
    }

    protected void j(Configuration configuration) {
        k(configuration);
    }

    protected abstract void k(Configuration configuration);

    protected void l(boolean z) {
        d(g(z));
    }

    protected void m() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = e();
        attributes.width = f();
        window.setAttributes(attributes);
    }

    protected boolean n(Configuration configuration) {
        int i = this.f997b;
        return (i == -1 || i == configuration.uiMode) ? false : true;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        w.c("ThemeActivity onConfigurationChanged :" + this.f997b + " " + configuration.uiMode);
        if (n(configuration)) {
            l((configuration.uiMode & 48) == 32);
            this.f997b = configuration.uiMode;
        }
        if (i(configuration)) {
            this.f998c = configuration.locale.toLanguageTag();
            j(configuration);
        }
        int i = this.d;
        if (i != -1) {
            int i2 = configuration.orientation;
            if (i == i2) {
                Point point = this.e;
                if (point.x == configuration.screenWidthDp && point.y == configuration.screenHeightDp) {
                    return;
                }
            }
            this.d = i2;
            this.e.set(configuration.screenWidthDp, configuration.screenHeightDp);
            k(configuration);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ReceiverForVoice receiverForVoice = new ReceiverForVoice(this);
        this.f = receiverForVoice;
        receiverForVoice.a();
        Configuration configuration = getResources().getConfiguration();
        this.f997b = configuration.uiMode;
        w.c("BaseDialog uiMode:" + this.f997b);
        this.f998c = configuration.locale.toLanguageTag();
        this.d = configuration.orientation;
        this.e.set(configuration.screenWidthDp, configuration.screenHeightDp);
        d(g((this.f997b & 48) == 32));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        ReceiverForVoice receiverForVoice = this.f;
        if (receiverForVoice != null) {
            receiverForVoice.b();
            this.f = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        m();
    }
}
