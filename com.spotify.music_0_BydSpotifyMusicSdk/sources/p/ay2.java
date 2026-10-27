package p;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonIOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class ay2 implements kp1, q13, ah2, xo1 {
    public Object c;
    public Object e;

    public ay2(int i) {
        if (i != 5) {
            return;
        }
        this.c = "";
        this.e = new ArrayList();
    }

    @Override // p.q13
    public final Object a() {
        Iterator it;
        String str;
        boolean z;
        com.google.android.play.core.assetpacks.j jVar = (com.google.android.play.core.assetpacks.j) this.c;
        Bundle bundle = (Bundle) this.e;
        jVar.getClass();
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return Boolean.FALSE;
        }
        HashMap map = jVar.d;
        Integer numValueOf = Integer.valueOf(i);
        String str2 = "chunk_intents";
        if (map.containsKey(numValueOf)) {
            n13 n13Var = jVar.b(i).c;
            int i2 = bundle.getInt(l13.u("status", n13Var.a));
            int i3 = n13Var.d;
            boolean zG0 = he1.g0(i3, i2);
            String str3 = n13Var.a;
            if (zG0) {
                com.google.android.play.core.assetpacks.j.f.b("Found stale update for session %s with status %d.", numValueOf, Integer.valueOf(i3));
                int i4 = n13Var.d;
                f13 f13Var = jVar.b;
                if (i4 == 4) {
                    ((d33) ((h13) f13Var).a()).d(i, str3);
                } else if (i4 == 5) {
                    ((d33) ((h13) f13Var).a()).a(i);
                } else if (i4 == 6) {
                    ((d33) ((h13) f13Var).a()).f(Arrays.asList(str3));
                }
            } else {
                n13Var.d = i2;
                if (i2 == 5 || i2 == 6 || i2 == 4) {
                    jVar.c(new com.google.android.play.core.assetpacks.i(jVar, i));
                    jVar.c.a(str3);
                } else {
                    for (p13 p13Var : n13Var.f) {
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(l13.v("chunk_intents", str3, p13Var.a));
                        if (parcelableArrayList != null) {
                            for (int i5 = 0; i5 < parcelableArrayList.size(); i5++) {
                                if (parcelableArrayList.get(i5) != null && ((Intent) parcelableArrayList.get(i5)).getData() != null) {
                                    ((k13) p13Var.d.get(i5)).a = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String strD = com.google.android.play.core.assetpacks.j.d(bundle);
            long j = bundle.getLong(l13.u("pack_version", strD));
            String string = bundle.getString(l13.u("pack_version_tag", strD), "");
            int i6 = bundle.getInt(l13.u("status", strD));
            long j2 = bundle.getLong(l13.u("total_bytes_to_download", strD));
            List stringArrayList = bundle.getStringArrayList(l13.u("slice_ids", strD));
            ArrayList arrayList = new ArrayList();
            if (stringArrayList == null) {
                stringArrayList = Collections.emptyList();
            }
            Iterator it2 = stringArrayList.iterator();
            while (it2.hasNext()) {
                String str4 = (String) it2.next();
                List parcelableArrayList2 = bundle.getParcelableArrayList(l13.v(str2, strD, str4));
                ArrayList arrayList2 = new ArrayList();
                if (parcelableArrayList2 == null) {
                    parcelableArrayList2 = Collections.emptyList();
                }
                Iterator it3 = parcelableArrayList2.iterator();
                while (it3.hasNext()) {
                    if (((Intent) it3.next()) != null) {
                        it = it2;
                        str = str2;
                        z = true;
                    } else {
                        it = it2;
                        str = str2;
                        z = false;
                    }
                    k13 k13Var = new k13();
                    k13Var.a = z;
                    arrayList2.add(k13Var);
                    it2 = it;
                    str2 = str;
                }
                Iterator it4 = it2;
                String str5 = str2;
                String string2 = bundle.getString(l13.v("uncompressed_hash_sha256", strD, str4));
                long j3 = bundle.getLong(l13.v("uncompressed_size", strD, str4));
                int i7 = bundle.getInt(l13.v("patch_format", strD, str4), 0);
                arrayList.add(i7 != 0 ? new p13(str4, string2, j3, arrayList2, 0, i7) : new p13(str4, string2, j3, arrayList2, bundle.getInt(l13.v("compression_format", strD, str4), 0), 0));
                it2 = it4;
                str2 = str5;
            }
            map.put(Integer.valueOf(i), new o13(i, bundle.getInt("app_version_code"), new n13(strD, j, i6, j2, arrayList, string)));
        }
        return Boolean.TRUE;
    }

    public final fh b() {
        String strConcat = ((String) this.c) == null ? " key" : "";
        if (((String) this.e) == null) {
            strConcat = strConcat.concat(" value");
        }
        if (strConcat.isEmpty()) {
            return new fh((String) this.c, (String) this.e);
        }
        throw new IllegalStateException("Missing required properties:".concat(strConcat));
    }

    public final wt0 c() {
        return new wt0((String) this.c, ((Map) this.e) == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap((Map) this.e)));
    }

    public final int d() {
        try {
            return Settings.Global.getInt(((Context) this.c).getContentResolver(), "boot_count");
        } catch (Exception e) {
            ((u11) ((wc1) this.e)).y("Error in getting boot count.", e);
            return 0;
        }
    }

    public final void e(vc vcVar) {
        if (((Map) this.e) == null) {
            this.e = new HashMap();
        }
        ((Map) this.e).put(sy1.class, vcVar);
    }

    @Override // p.kp1
    public final fx2 i(View view, fx2 fx2Var) {
        k52 k52Var = (k52) this.c;
        u12 u12Var = (u12) this.e;
        int i = u12Var.a;
        int i2 = u12Var.c;
        int i3 = u12Var.d;
        BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) k52Var.b;
        bottomSheetBehavior.s = fx2Var.d();
        WeakHashMap weakHashMap = zt2.a;
        boolean z = jt2.d(view) == 1;
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        if (bottomSheetBehavior.n) {
            int iA = fx2Var.a();
            bottomSheetBehavior.r = iA;
            paddingBottom = iA + i3;
        }
        if (bottomSheetBehavior.o) {
            paddingLeft = fx2Var.b() + (z ? i2 : i);
        }
        if (bottomSheetBehavior.f29p) {
            if (!z) {
                i = i2;
            }
            paddingRight = fx2Var.c() + i;
        }
        view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
        boolean z2 = k52Var.a;
        if (z2) {
            bottomSheetBehavior.l = fx2Var.a.g().d;
        }
        if (bottomSheetBehavior.n || z2) {
            bottomSheetBehavior.I();
        }
        return fx2Var;
    }

    @Override // p.ah2
    public final b33 j(Object obj) {
        if (((cc2) obj) == null) {
            return ci1.t(null);
        }
        com.google.firebase.crashlytics.internal.common.a.b((com.google.firebase.crashlytics.internal.common.a) ((x42) ((a30) this.e).k).k);
        ((com.google.firebase.crashlytics.internal.common.a) ((x42) ((a30) this.e).k).k).l.e((Executor) this.c, null);
        ((com.google.firebase.crashlytics.internal.common.a) ((x42) ((a30) this.e).k).k).f38p.b(null);
        return ci1.t(null);
    }

    @Override // p.xo1
    public final Object k() {
        Object obj = this.c;
        if (!(((Type) obj) instanceof ParameterizedType)) {
            throw new JsonIOException("Invalid EnumSet type: " + ((Type) this.c).toString());
        }
        Type type = ((ParameterizedType) ((Type) obj)).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return EnumSet.noneOf((Class) type);
        }
        throw new JsonIOException("Invalid EnumSet type: " + ((Type) this.c).toString());
    }

    public /* synthetic */ ay2(Object obj, Object obj2) {
        this.c = obj;
        this.e = obj2;
    }

    public /* synthetic */ ay2(Object obj, Object obj2, int i) {
        this.e = obj;
        this.c = obj2;
    }

    public ay2(i52 i52Var, int i) {
        if (i != 2) {
            this.c = i52Var;
            this.e = new f90(this, i52Var, 3);
            return;
        }
        v03.j(i52Var, "database");
        this.c = i52Var;
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        v03.i(setNewSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.e = setNewSetFromMap;
    }
}
