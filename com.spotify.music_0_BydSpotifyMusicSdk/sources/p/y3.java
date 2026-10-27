package p;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class y3 implements ic0 {
    public Context a;

    public /* synthetic */ y3(Context context) {
        this.a = context;
    }

    public static String c(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            return bundle.getString("local_testing_dir");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // p.ic0
    public void a(vr2 vr2Var) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new kw("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new kc0(this, vr2Var, threadPoolExecutor, 0));
    }

    public u50 b() {
        Context context = this.a;
        if (context == null) {
            throw new IllegalStateException(Context.class.getCanonicalName() + " must be set");
        }
        u50 u50Var = new u50();
        u50Var.c = va0.a(vr2.e);
        c51 c51Var = new c51(context);
        u50Var.e = c51Var;
        vk0 vk0Var = l13.e;
        vk0 vk0Var2 = zu.h;
        int i = 0;
        u50Var.k = va0.a(new ck1(c51Var, new o40(c51Var, vk0Var, vk0Var2, i)));
        c51 c51Var2 = u50Var.e;
        int i2 = 1;
        u50Var.l = new o40(c51Var2, ci1.k, ln1.b, i2);
        xy1 xy1VarA = va0.a(new io2(vk0Var, vk0Var2, p02.b, u50Var.l, va0.a(new i72(c51Var2, i2)), 2));
        u50Var.m = xy1VarA;
        i72 i72Var = new i72(vk0Var, i);
        c51 c51Var3 = u50Var.e;
        j72 j72Var = new j72(c51Var3, xy1VarA, i72Var, vk0Var2, 0);
        xy1 xy1Var = u50Var.c;
        xy1 xy1Var2 = u50Var.k;
        u50Var.n = va0.a(new io2(vk0Var, vk0Var2, new io2(xy1Var, xy1Var2, j72Var, xy1VarA, xy1VarA, 1), new kr2(c51Var3, xy1Var2, xy1VarA, j72Var, xy1Var, xy1VarA, xy1VarA), new j72(xy1Var, xy1VarA, j72Var, xy1VarA, 1), 0));
        return u50Var;
    }

    public /* synthetic */ y3(Context context, int i) {
        if (i != 1) {
            this.a = context.getApplicationContext();
        } else {
            this.a = context;
        }
    }
}
