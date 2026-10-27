package defpackage;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import defpackage.tp3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: WebApkServiceConnectionManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tp3 {
    public final String a;
    public final String b;
    public final d73 c;
    public oi2 d;
    public int e;
    public final HashMap<String, a> f = new HashMap<>();

    /* JADX INFO: compiled from: WebApkServiceConnectionManager.java */
    public static class a implements ServiceConnection {
        public final tp3 e;
        public final ArrayList<b> f = new ArrayList<>();
        public IBinder g;

        public a(tp3 tp3Var) {
            this.e = tp3Var;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.g = iBinder;
            ArrayList<b> arrayList = this.f;
            Iterator<b> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a(this.g);
            }
            arrayList.clear();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            this.g = null;
            String packageName = componentName.getPackageName();
            tp3 tp3Var = this.e;
            HashMap<String, a> map = tp3Var.f;
            map.remove(packageName);
            if (map.isEmpty() && tp3Var.e == 0 && tp3Var.d != null) {
                tp3Var.d = null;
            }
        }
    }

    /* JADX INFO: compiled from: WebApkServiceConnectionManager.java */
    public interface b {
        void a(IBinder iBinder);
    }

    public tp3(d73 d73Var, String str, String str2) {
        this.c = d73Var;
        this.a = str;
        this.b = str2;
    }

    @SuppressLint({"StaticFieldLeak"})
    public final void a(final Context context, final String str, b bVar) {
        HashMap<String, a> map = this.f;
        a aVar = map.get(str);
        if (aVar == null) {
            final a aVar2 = new a(this);
            map.put(str, aVar2);
            aVar2.f.add(bVar);
            c(new Callable() { // from class: sp3
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Context context2 = context;
                    tp3.a aVar3 = aVar2;
                    tp3 tp3Var = this.a;
                    tp3Var.getClass();
                    Intent intent = new Intent();
                    String str2 = tp3Var.a;
                    if (str2 != null) {
                        intent.addCategory(str2);
                    }
                    String str3 = tp3Var.b;
                    if (str3 != null) {
                        intent.setAction(str3);
                    }
                    intent.setPackage(str);
                    try {
                    } catch (SecurityException e) {
                        Log.w("WebApkService", "Security exception binding.", e);
                    }
                    if (context2.bindService(intent, aVar3, 1)) {
                        return Boolean.TRUE;
                    }
                    context2.unbindService(aVar3);
                    return Boolean.FALSE;
                }
            }, new ur3(5, aVar2));
            return;
        }
        IBinder iBinder = aVar.g;
        if (iBinder != null) {
            bVar.a(iBinder);
        } else {
            aVar.f.add(bVar);
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public final void b(Context context) {
        HashMap<String, a> map = this.f;
        if (map.isEmpty()) {
            return;
        }
        a[] aVarArr = (a[]) map.values().toArray(new a[map.size()]);
        map.clear();
        for (a aVar : aVarArr) {
            aVar.onServiceConnected(null, null);
        }
        c(new z40(aVarArr, 1, context), new ur3(6, this));
    }

    public final void c(Callable callable, ur3 ur3Var) {
        this.e++;
        if (this.d == null) {
            this.d = PostTask.a(d73.g);
        }
        this.d.b(new b80(this, 9, callable, ur3Var));
    }
}
