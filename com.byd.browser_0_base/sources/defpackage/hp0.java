package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.fragment.app.FragmentActivity;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class hp0<E> extends vd1 {
    public final Activity k;
    public final Context l;
    public final Handler m;
    public final lp0 n;

    public hp0(FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.n = new lp0();
        this.k = fragmentActivity;
        if (fragmentActivity == null) {
            throw new NullPointerException("context == null");
        }
        this.l = fragmentActivity;
        this.m = handler;
    }

    public abstract void J(PrintWriter printWriter, String[] strArr);

    public abstract FragmentActivity K();

    public abstract LayoutInflater L();

    public abstract void M();
}
