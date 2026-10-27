package defpackage;

import J.N;
import android.text.TextUtils;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.chromium.android_webview.AwTracingController;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class K8 {
    public final C0070Dq a;
    public final AwTracingController b;

    public K8(C0070Dq c0070Dq, AwTracingController awTracingController) {
        this.a = c0070Dq;
        this.b = awTracingController;
    }

    public static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean b() {
        return !ThreadUtils.j();
    }

    public boolean c() {
        if (!b()) {
            return this.b.a();
        }
        C0070Dq c0070Dq = this.a;
        final AwTracingController awTracingController = this.b;
        awTracingController.getClass();
        return ((Boolean) c0070Dq.d(new Callable(awTracingController) { // from class: E8
            public final AwTracingController a;

            {
                this.a = awTracingController;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return Boolean.valueOf(this.a.a());
            }
        })).booleanValue();
    }

    public void d(final int i, final Collection collection, final int i2) {
        int iIntValue = b() ? ((Integer) this.a.d(new Callable(this, i, collection, i2) { // from class: F8
            public final K8 a;
            public final int b;
            public final Collection c;
            public final int d;

            {
                this.a = this;
                this.b = i;
                this.c = collection;
                this.d = i2;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return Integer.valueOf(this.a.e(this.b, this.c, this.d));
            }
        })).intValue() : e(i, collection, i2);
        if (iIntValue != 0) {
            if (iIntValue == 1) {
                throw new IllegalStateException("cannot start tracing: tracing is already enabled");
            }
            if (iIntValue == 2) {
                throw new IllegalArgumentException("category patterns starting with '-' or containing ',' are not allowed");
            }
            if (iIntValue == 3) {
                throw new IllegalArgumentException("invalid tracing mode");
            }
        }
    }

    public final int e(int i, Collection collection, int i2) {
        boolean z;
        AwTracingController awTracingController = this.b;
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        if (a(i, 1)) {
            arrayList.add(0);
        }
        if (a(i, 2)) {
            arrayList.add(1);
        }
        if (a(i, 4)) {
            arrayList.add(2);
        }
        if (a(i, 8)) {
            arrayList.add(3);
        }
        if (a(i, 16)) {
            arrayList.add(4);
        }
        if (a(i, 32)) {
            arrayList.add(5);
        }
        if (a(i, 64)) {
            arrayList.add(6);
        }
        int i3 = i2 != 0 ? 1 : 0;
        if (awTracingController.a()) {
            return 1;
        }
        Iterator it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            String str = (String) it.next();
            if (!((str.startsWith("-") || str.contains(",")) ? false : true)) {
                z = false;
                break;
            }
        }
        if (!z) {
            return 2;
        }
        if (i3 != 0 && i3 != 1) {
            z2 = false;
        }
        if (!z2) {
            return 3;
        }
        HashSet hashSet = new HashSet();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            hashSet.addAll((Collection) AwTracingController.j.get(((Integer) it2.next()).intValue()));
        }
        hashSet.addAll(collection);
        if (hashSet.isEmpty()) {
            hashSet.add("-*");
        }
        N.MDhATp5G(awTracingController.b, awTracingController, TextUtils.join(",", hashSet), i3);
        return 0;
    }

    public boolean f(final OutputStream outputStream, final Executor executor) {
        return b() ? ((Boolean) this.a.d(new Callable(this, outputStream, executor) { // from class: G8
            public final K8 a;
            public final OutputStream b;
            public final Executor c;

            {
                this.a = this;
                this.b = outputStream;
                this.c = executor;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return Boolean.valueOf(this.a.g(this.b, this.c));
            }
        })).booleanValue() : g(outputStream, executor);
    }

    public final boolean g(OutputStream outputStream, Executor executor) {
        if (outputStream == null) {
            AwTracingController awTracingController = this.b;
            if (!awTracingController.a()) {
                return false;
            }
            awTracingController.a = null;
            N.MVnr_0Jw(awTracingController.b, awTracingController);
            return true;
        }
        AwTracingController awTracingController2 = this.b;
        J8 j8 = new J8(this, executor, outputStream);
        if (!awTracingController2.a()) {
            return false;
        }
        awTracingController2.a = j8;
        N.MVnr_0Jw(awTracingController2.b, awTracingController2);
        return true;
    }
}
