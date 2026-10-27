package t4;

import A4.q;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.l;
import n0.C1886o;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Handler.Callback {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m6.b f22459m = new m6.b();
    public volatile l l;

    public static Activity a(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public final l b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        char[] cArr = q.f432a;
        if (Looper.myLooper() == Looper.getMainLooper() && !(context instanceof Application) && (context instanceof ContextWrapper)) {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                return b(contextWrapper.getBaseContext());
            }
        }
        if (this.l == null) {
            synchronized (this) {
                try {
                    if (this.l == null) {
                        this.l = new l(com.bumptech.glide.b.a(context.getApplicationContext()), new C1886o(10), new C1886o(11), context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return this.l;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        return false;
    }
}
