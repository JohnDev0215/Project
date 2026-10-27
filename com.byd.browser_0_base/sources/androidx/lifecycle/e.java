package androidx.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import androidx.lifecycle.ReportFragment;
import defpackage.i21;
import defpackage.ug0;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: LifecycleDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static final AtomicBoolean a = new AtomicBoolean(false);

    /* JADX INFO: compiled from: LifecycleDispatcher.kt */
    public static final class a extends ug0 {
        @Override // defpackage.ug0, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            ReportFragment.b.b(activity);
        }
    }
}
