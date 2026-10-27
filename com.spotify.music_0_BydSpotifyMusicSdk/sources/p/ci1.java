package p;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.components.DependencyCycleException;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.player.esperanto.proto.EsCommandOptions$CommandOptions;
import com.spotify.player.model.command.options.CommandOptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public abstract class ci1 implements y4 {
    public static bi1 c = null;
    public static final u e = new u();
    public static final vk0 k = new vk0(1);
    public static final ke2 l = new ke2(16);
    public static final ke2 m = new ke2(7);
    public static final az2 n = new az2();
    public static boolean o = true;

    public static void A(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MAX_VALUE) {
                return;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.b(new IllegalStateException(u4.g("More produced than requested: ", j3)));
                j3 = 0;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
    }

    public static long B(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.b(new IllegalStateException(u4.g("More produced than requested: ", j3)));
                j3 = 0;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j3;
    }

    public static final void C(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("null not expected: ".concat(str));
        }
    }

    public static void D(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
        } else if (o) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                o = false;
            }
        }
    }

    public static int E(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Animation.Activity, new int[]{i});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static void F(a81 a81Var, com.apollographql.apollo3.api.c cVar, l92 l92Var) {
        v03.j(a81Var, "writer");
        v03.j(cVar, "customScalarAdapters");
        v03.j(l92Var, "value");
        a81Var.l0("query");
        c5.a.b(a81Var, cVar, l92Var.a);
        a81Var.l0("limit");
        w1 w1Var = c5.b;
        w1Var.b(a81Var, cVar, Integer.valueOf(l92Var.b));
        a81Var.l0("topResultsLimit");
        w1Var.b(a81Var, cVar, Integer.valueOf(l92Var.c));
        a81Var.l0("includeTracks");
        c5.c.b(a81Var, cVar, Boolean.valueOf(l92Var.d));
    }

    public static final dg1 G(xu1 xu1Var, Referrer referrer, String str, int i, boolean z, ki1 ki1Var, lh1 lh1Var, Resources resources, w40 w40Var) {
        String str2;
        yu1 yu1Var;
        String str3;
        String string;
        v03.j(referrer, "referrer");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(resources, "resources");
        v03.j(w40Var, "customBrowseActionHandler");
        uu1 uu1Var = (uu1) ou.d0(xu1Var.b.a);
        Uri uriB = wb1.b(he1.r(uu1Var != null ? uu1Var.b : null), ki1Var, MediaUriUtil$Transformation.k, ki1Var.a(com.spotify.music.R.drawable.playlist_placeholder));
        String str4 = xu1Var.a;
        if (str4 == null || ng2.c0(str4) || z) {
            ru1 ru1Var = xu1Var.d.a;
            if (ru1Var == null || (yu1Var = ru1Var.c) == null || (str3 = yu1Var.a) == null) {
                str2 = null;
                return y(referrer.a(), Integer.valueOf(i), xu1Var.e, uriB, xu1Var.c, str2, str, lh1Var, w40Var);
            }
            string = resources.getString(com.spotify.music.R.string.content_playlist_subtitle, str3);
        } else {
            string = d21.a(str4, 63).toString();
        }
        str2 = string;
        return y(referrer.a(), Integer.valueOf(i), xu1Var.e, uriB, xu1Var.c, str2, str, lh1Var, w40Var);
    }

    public static final ArrayList H(List list) {
        v03.j(list, "<this>");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                v03.P();
                throw null;
            }
            Iterator it = ((ls2) obj).a().iterator();
            while (it.hasNext()) {
                arrayList.add("[" + i + "] " + ((String) it.next()));
            }
            i = i2;
        }
        return arrayList;
    }

    public static final String I(List list) {
        Object next;
        v03.j(list, "<this>");
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (ng2.b0(((g21) next).a, "Content-Type", true)) {
                break;
            }
        }
        g21 g21Var = (g21) next;
        if (g21Var != null) {
            return g21Var.b;
        }
        return null;
    }

    public static b33 J(List list) {
        if (list == null || list.isEmpty()) {
            return t(null);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((aj2) it.next()) == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        b33 b33Var = new b33();
        sz2 sz2Var = new sz2(list.size(), b33Var);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            aj2 aj2Var = (aj2) it2.next();
            wa waVar = dj2.b;
            b33 b33Var2 = (b33) aj2Var;
            b33Var2.getClass();
            b33Var2.b.b(new i23(waVar, (eq1) sz2Var));
            b33Var2.j();
            i23 i23Var = new i23(waVar, (xp1) sz2Var);
            s23 s23Var = b33Var2.b;
            s23Var.b(i23Var);
            b33Var2.j();
            s23Var.b(new i23(waVar, (pp1) sz2Var));
            b33Var2.j();
        }
        return b33Var;
    }

    public static void K(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static Object L(b33 b33Var) throws ExecutionException {
        if (b33Var.d()) {
            return b33Var.c();
        }
        if (b33Var.d) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(b33Var.b());
    }

    public static void M(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    public static Object b(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static final AudioFocusRequest c(pv0 pv0Var, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(pv0Var.b);
        Boolean bool = (Boolean) pv0Var.c;
        if (bool != null) {
            builder.setAcceptsDelayedFocusGain(bool.booleanValue());
        }
        yc ycVar = (yc) pv0Var.d;
        if (ycVar != null) {
            AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
            Integer num = ycVar.a;
            if (num != null) {
                builder2.setContentType(num.intValue());
            }
            Integer num2 = ycVar.b;
            if (num2 != null) {
                builder2.setUsage(num2.intValue());
            }
            builder.setAudioAttributes(builder2.build());
        }
        Handler handler = (Handler) pv0Var.f;
        if (!(handler instanceof Handler)) {
            handler = null;
        }
        if (handler != null) {
            builder.setOnAudioFocusChangeListener(onAudioFocusChangeListener, handler);
        } else {
            builder.setOnAudioFocusChangeListener(onAudioFocusChangeListener);
        }
        Boolean bool2 = (Boolean) pv0Var.g;
        if (bool2 != null) {
            builder.setWillPauseWhenDucked(bool2.booleanValue());
        }
        AudioFocusRequest audioFocusRequestBuild = builder.build();
        v03.i(audioFocusRequestBuild, "builder.build()");
        return audioFocusRequestBuild;
    }

    public static long d(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, e(j2, j)));
        return j2;
    }

    public static long e(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x06a8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void f(p.uy r38, p.ra1 r39, java.util.ArrayList r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1823
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ci1.f(p.uy, p.ra1, java.util.ArrayList, int):void");
    }

    public static Object g(b33 b33Var) throws InterruptedException {
        boolean z;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException("Must not be called on the main application thread");
        }
        synchronized (b33Var.a) {
            z = b33Var.c;
        }
        if (z) {
            return L(b33Var);
        }
        p01 p01Var = new p01();
        wa waVar = dj2.b;
        b33Var.b.b(new i23(waVar, (eq1) p01Var));
        b33Var.j();
        i23 i23Var = new i23(waVar, (xp1) p01Var);
        s23 s23Var = b33Var.b;
        s23Var.b(i23Var);
        b33Var.j();
        s23Var.b(new i23(waVar, (pp1) p01Var));
        b33Var.j();
        ((CountDownLatch) p01Var.e).await();
        return L(b33Var);
    }

    public static String h(String str, int i, int i2) {
        if (i < 0) {
            return v03.z("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return v03.z("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IllegalArgumentException(u4.e("negative size: ", i2));
    }

    public static b33 i(Executor executor, Callable callable) {
        if (executor == null) {
            throw new NullPointerException("Executor must not be null");
        }
        b33 b33Var = new b33();
        executor.execute(new yf1(b33Var, callable, 18));
        return b33Var;
    }

    public static void j(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void k(int i, int i2) {
        String strZ;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                strZ = v03.z("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else {
                if (i2 < 0) {
                    throw new IllegalArgumentException(u4.e("negative size: ", i2));
                }
                strZ = v03.z("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(strZ);
        }
    }

    public static void l(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(h("index", i, i2));
        }
    }

    public static void m(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException((i < 0 || i > i3) ? h("start index", i, i3) : (i2 < 0 || i2 > i3) ? h("end index", i2, i3) : v03.z("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    public static final EsCommandOptions$CommandOptions n(CommandOptions commandOptions) {
        mf0 mf0VarI = EsCommandOptions$CommandOptions.i();
        if (commandOptions.d().d()) {
            Object objC = commandOptions.d().c();
            v03.i(objC, "commandOptions.overrideRestrictions().get()");
            mf0VarI.f(((Boolean) objC).booleanValue());
        }
        if (commandOptions.b().d()) {
            Object objC2 = commandOptions.b().c();
            v03.i(objC2, "commandOptions.onlyForLocalDevice().get()");
            mf0VarI.d(((Boolean) objC2).booleanValue());
        }
        if (commandOptions.e().d()) {
            Object objC3 = commandOptions.e().c();
            v03.i(objC3, "commandOptions.systemInitiated().get()");
            mf0VarI.g(((Boolean) objC3).booleanValue());
        }
        if (commandOptions.c().d()) {
            Object objC4 = commandOptions.c().c();
            v03.i(objC4, "commandOptions.onlyForPlaybackId().get()");
            mf0VarI.e(vr2.q((String) objC4));
        }
        com.google.protobuf.i iVarBuild = mf0VarI.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …)\n        }\n    }.build()");
        return (EsCommandOptions$CommandOptions) iVarBuild;
    }

    public static int o(k22 k22Var, dr1 dr1Var, View view, View view2, y12 y12Var, boolean z) {
        if (y12Var.v() == 0 || k22Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(y12.D(view) - y12.D(view2)) + 1;
        }
        return Math.min(dr1Var.g(), dr1Var.b(view2) - dr1Var.d(view));
    }

    public static int p(k22 k22Var, dr1 dr1Var, View view, View view2, y12 y12Var, boolean z, boolean z2) {
        if (y12Var.v() == 0 || k22Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (k22Var.b() - Math.max(y12.D(view), y12.D(view2))) - 1) : Math.max(0, Math.min(y12.D(view), y12.D(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(dr1Var.b(view2) - dr1Var.d(view)) / (Math.abs(y12.D(view) - y12.D(view2)) + 1))) + (dr1Var.f() - dr1Var.d(view)));
        }
        return iMax;
    }

    public static int q(k22 k22Var, dr1 dr1Var, View view, View view2, y12 y12Var, boolean z) {
        if (y12Var.v() == 0 || k22Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return k22Var.b();
        }
        return (int) (((dr1Var.b(view2) - dr1Var.d(view)) / (Math.abs(y12.D(view) - y12.D(view2)) + 1)) * k22Var.b());
    }

    public static void r(ArrayList arrayList) {
        HashMap map = new HashMap(arrayList.size());
        Iterator it = arrayList.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                Iterator it2 = map.values().iterator();
                while (it2.hasNext()) {
                    for (d50 d50Var : (Set) it2.next()) {
                        for (d90 d90Var : d50Var.a.c) {
                            if (d90Var.c == 0) {
                                Set<d50> set = (Set) map.get(new e50(d90Var.a, d90Var.b == 2));
                                if (set != null) {
                                    for (d50 d50Var2 : set) {
                                        d50Var.b.add(d50Var2);
                                        d50Var2.c.add(d50Var);
                                    }
                                }
                            }
                        }
                    }
                }
                HashSet<d50> hashSet = new HashSet();
                Iterator it3 = map.values().iterator();
                while (it3.hasNext()) {
                    hashSet.addAll((Set) it3.next());
                }
                HashSet hashSet2 = new HashSet();
                for (d50 d50Var3 : hashSet) {
                    if (d50Var3.c.isEmpty()) {
                        hashSet2.add(d50Var3);
                    }
                }
                while (!hashSet2.isEmpty()) {
                    d50 d50Var4 = (d50) hashSet2.iterator().next();
                    hashSet2.remove(d50Var4);
                    i++;
                    for (d50 d50Var5 : d50Var4.b) {
                        d50Var5.c.remove(d50Var4);
                        if (d50Var5.c.isEmpty()) {
                            hashSet2.add(d50Var5);
                        }
                    }
                }
                if (i == arrayList.size()) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                for (d50 d50Var6 : hashSet) {
                    if (!d50Var6.c.isEmpty() && !d50Var6.b.isEmpty()) {
                        arrayList2.add(d50Var6.a);
                    }
                }
                throw new DependencyCycleException(arrayList2);
            }
            mv mvVar = (mv) it.next();
            d50 d50Var7 = new d50(mvVar);
            for (dz1 dz1Var : mvVar.b) {
                boolean z = !(mvVar.e == 0);
                e50 e50Var = new e50(dz1Var, z);
                if (!map.containsKey(e50Var)) {
                    map.put(e50Var, new HashSet());
                }
                Set set2 = (Set) map.get(e50Var);
                if (!set2.isEmpty() && !z) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", dz1Var));
                }
                set2.add(d50Var7);
            }
        }
    }

    public static boolean s(String str, String str2) {
        if (str != str2) {
            return str != null && str.equals(str2);
        }
        return true;
    }

    public static b33 t(Object obj) {
        b33 b33Var = new b33();
        b33Var.g(obj);
        return b33Var;
    }

    public static final rx2 u(ky2 ky2Var) {
        v03.j(ky2Var, "<this>");
        return new rx2(ky2Var.a, ky2Var.t);
    }

    public static ColorStateList v(Context context, TypedArray typedArray, int i) {
        int resourceId;
        ColorStateList colorStateListD;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListD = l13.d(context, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListD;
    }

    public static ColorStateList w(Context context, v4 v4Var, int i) {
        int iY;
        ColorStateList colorStateListD;
        return (!v4Var.G(i) || (iY = v4Var.y(i, 0)) == 0 || (colorStateListD = l13.d(context, iY)) == null) ? v4Var.q(i) : colorStateListD;
    }

    public static Drawable x(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable drawableF;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawableF = l13.f(context, resourceId)) == null) ? typedArray.getDrawable(i) : drawableF;
    }

    public static final dg1 y(String str, Integer num, String str2, Uri uri, String str3, String str4, String str5, lh1 lh1Var, w40 w40Var) {
        kh1 kh1VarS = yn.s(str2);
        kh1VarS.c = str;
        kh1VarS.d = num;
        String strA = kh1VarS.a();
        ArrayList arrayListA = w40Var.a(strA);
        p01 p01Var = new p01(25);
        p01Var.E(1);
        p01Var.H(str5);
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.HEART);
        p01Var.F(arrayListA);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = strA;
        jh1Var.g = Uri.parse(str2);
        jh1Var.f = uri;
        jh1Var.b = str3;
        jh1Var.c = str4;
        jh1Var.h = bundle;
        return new dg1(jh1Var, lh1Var.a(str2));
    }

    public static boolean z(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }
}
