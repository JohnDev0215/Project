package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class ShareCompat$IntentBuilder {
    public final Intent a;

    public ShareCompat$IntentBuilder(Context context) {
        Activity activity;
        context.getClass();
        Intent action = new Intent().setAction("android.intent.action.SEND");
        this.a = action;
        action.putExtra("androidx.core.app.EXTRA_CALLING_PACKAGE", context.getPackageName());
        action.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", context.getPackageName());
        action.addFlags(524288);
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            } else {
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (activity != null) {
            ComponentName componentName = activity.getComponentName();
            this.a.putExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY", componentName);
            this.a.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", componentName);
        }
    }
}
