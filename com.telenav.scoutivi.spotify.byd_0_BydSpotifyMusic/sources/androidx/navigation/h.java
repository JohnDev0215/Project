package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: compiled from: NavDeepLinkBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2695a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Intent f2696b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private k f2697c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2698d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bundle f2699e;

    public h(Context context) {
        this.f2695a = context;
        if (context instanceof Activity) {
            this.f2696b = new Intent(context, context.getClass());
        } else {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            this.f2696b = launchIntentForPackage == null ? new Intent() : launchIntentForPackage;
        }
        this.f2696b.addFlags(268468224);
    }

    private void b() {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(this.f2697c);
        j jVar = null;
        while (!arrayDeque.isEmpty() && jVar == null) {
            j jVar2 = (j) arrayDeque.poll();
            if (jVar2.k() == this.f2698d) {
                jVar = jVar2;
            } else if (jVar2 instanceof k) {
                Iterator<j> it = ((k) jVar2).iterator();
                while (it.hasNext()) {
                    arrayDeque.add(it.next());
                }
            }
        }
        if (jVar != null) {
            this.f2696b.putExtra("android-support-nav:controller:deepLinkIds", jVar.f());
            return;
        }
        throw new IllegalArgumentException("Navigation destination " + j.j(this.f2695a, this.f2698d) + " cannot be found in the navigation graph " + this.f2697c);
    }

    public w.i a() {
        if (this.f2696b.getIntArrayExtra("android-support-nav:controller:deepLinkIds") == null) {
            if (this.f2697c == null) {
                throw new IllegalStateException("You must call setGraph() before constructing the deep link");
            }
            throw new IllegalStateException("You must call setDestination() before constructing the deep link");
        }
        w.i iVarD = w.i.g(this.f2695a).d(new Intent(this.f2696b));
        for (int i7 = 0; i7 < iVarD.i(); i7++) {
            iVarD.h(i7).putExtra("android-support-nav:controller:deepLinkIntent", this.f2696b);
        }
        return iVarD;
    }

    public h c(Bundle bundle) {
        this.f2699e = bundle;
        this.f2696b.putExtra("android-support-nav:controller:deepLinkExtras", bundle);
        return this;
    }

    public h d(int i7) {
        this.f2698d = i7;
        if (this.f2697c != null) {
            b();
        }
        return this;
    }

    h(NavController navController) {
        this(navController.e());
        this.f2697c = navController.i();
    }
}
