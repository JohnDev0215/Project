package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IN extends QN {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f3J;

    public IN(Context context, boolean z) {
        super(context);
        Activity activityA = Is.a(context);
        if (activityA == null) {
            throw new IllegalArgumentException("Context is not and does not wrap an Activity");
        }
        this.f3J = z;
        if (z) {
            throw null;
        }
        AN.b = new HN(Q());
        this.C = new GN(Q());
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public WeakReference Q() {
        return new WeakReference(Is.a((Context) S().get()));
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int R() {
        if (!this.f3J) {
            return 6;
        }
        ApplicationStatus.b((Activity) Q().get());
        return 6;
    }

    @Override // defpackage.QN
    public final boolean a0(Intent intent, int i) {
        Activity activity = (Activity) Q().get();
        if (activity == null) {
            return false;
        }
        try {
            activity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }
}
