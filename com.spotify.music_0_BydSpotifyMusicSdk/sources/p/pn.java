package p;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class pn extends io.reactivex.rxjava3.android.a {
    public final Observer e;
    public final Context k;
    public final o8 l;

    public pn(Observer observer, Context context, IntentFilter intentFilter, Boolean bool) {
        v03.j(observer, "observer");
        v03.j(context, "context");
        v03.j(intentFilter, "intentFilter");
        this.e = observer;
        this.k = context;
        o8 o8Var = new o8(3, this);
        this.l = o8Var;
        if (Build.VERSION.SDK_INT <= 33) {
            context.registerReceiver(o8Var, intentFilter);
        } else if (bool == null) {
            context.registerReceiver(o8Var, intentFilter);
        } else {
            context.registerReceiver(o8Var, intentFilter, bool.booleanValue() ? 2 : 4);
        }
    }
}
