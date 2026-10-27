package org.extra.tools;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class b implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b f3992a = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<FragmentManager, c> f3994c = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f3993b = new Handler(Looper.getMainLooper(), this);

    private b() {
    }

    public static b a() {
        return f3992a;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        FragmentManager fragmentManager = (FragmentManager) message.obj;
        c cVar = (c) fragmentManager.findFragmentByTag("io.pag.manager");
        if (fragmentManager.isDestroyed()) {
            Log.w("Lifecycle", "Parent was destroyed before our Fragment could be added.");
        } else if (cVar != this.f3994c.get(fragmentManager)) {
            Log.w("Lifecycle", "adding Fragment failed.");
        }
        this.f3994c.remove(fragmentManager);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(View view) {
        if ((view.getContext() instanceof Activity) && (view instanceof d)) {
            Activity activity = (Activity) view.getContext();
            if (activity.isDestroyed()) {
                return;
            }
            FragmentManager fragmentManager = activity.getFragmentManager();
            c cVar = this.f3994c.get(fragmentManager);
            if (cVar == null) {
                c cVar2 = (c) fragmentManager.findFragmentByTag("io.pag.manager");
                if (cVar2 == null) {
                    cVar2 = new c();
                    this.f3994c.put(fragmentManager, cVar2);
                    fragmentManager.beginTransaction().add(cVar2, "io.pag.manager").commitAllowingStateLoss();
                    this.f3993b.obtainMessage(1, fragmentManager).sendToTarget();
                }
                cVar = cVar2;
            }
            cVar.a((d) view);
        }
    }
}
