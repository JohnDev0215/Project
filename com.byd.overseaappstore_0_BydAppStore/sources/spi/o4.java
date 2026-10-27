package spi;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class o4 {
    private o4() {
    }

    public static Application a(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        Context baseContext = context;
        while (baseContext instanceof ContextWrapper) {
            baseContext = ((ContextWrapper) baseContext).getBaseContext();
            if (baseContext instanceof Application) {
                return (Application) baseContext;
            }
        }
        throw new IllegalStateException("Could not find an Application in the given context: " + context);
    }
}
