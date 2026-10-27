package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p.a23;
import p.ay2;
import p.d23;
import p.d33;
import p.e23;
import p.f13;
import p.g13;
import p.g23;
import p.h13;
import p.qo;
import p.r13;
import p.s03;
import p.t03;
import p.tz2;
import p.x03;
import p.x13;
import p.xf1;
import p.y13;
import p.yf1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends t03 {
    public final j g;
    public final h h;
    public final f13 i;
    public final s03 j;
    public final g13 k;
    public final f13 l;
    public final f13 m;
    public final a23 n;
    public final Handler o;

    public b(Context context, j jVar, h hVar, f13 f13Var, g13 g13Var, s03 s03Var, f13 f13Var2, f13 f13Var3, a23 a23Var) {
        super(new tz2("AssetPackServiceListenerRegistry", 0), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.o = new Handler(Looper.getMainLooper());
        this.g = jVar;
        this.h = hVar;
        this.i = f13Var;
        this.k = g13Var;
        this.j = s03Var;
        this.l = f13Var2;
        this.m = f13Var3;
        this.n = a23Var;
    }

    @Override // p.t03
    public final void a(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        tz2 tz2Var = this.a;
        if (bundleExtra == null) {
            tz2Var.c("Empty bundle received from broadcast.", new Object[0]);
            return;
        }
        ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.size() != 1) {
            tz2Var.c("Corrupt bundle received from broadcast.", new Object[0]);
            return;
        }
        zzbn zzbnVarA = zzbn.a(bundleExtra, stringArrayList.get(0), this.k, this.n, qo.s);
        tz2Var.b("ListenerRegistryBroadcastReceiver.onReceive: %s", zzbnVarA);
        if (((PendingIntent) bundleExtra.getParcelable("confirmation_intent")) != null) {
            this.j.getClass();
        }
        ((Executor) ((h13) this.m).a()).execute(new xf1(this, bundleExtra, zzbnVarA));
        ((Executor) ((h13) this.l).a()).execute(new yf1(this, bundleExtra, 23));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c(Bundle bundle) {
        r13 r13VarA;
        j jVar = this.g;
        jVar.getClass();
        if (!((Boolean) jVar.c(new ay2(jVar, bundle))).booleanValue()) {
            return;
        }
        h hVar = this.h;
        f13 f13Var = hVar.h;
        tz2 tz2Var = h.k;
        tz2Var.b("Run extractor loop", new Object[0]);
        AtomicBoolean atomicBoolean = hVar.j;
        if (!atomicBoolean.compareAndSet(false, true)) {
            tz2Var.f("runLoop already looping; return", new Object[0]);
            return;
        }
        while (true) {
            try {
                r13VarA = hVar.i.a();
            } catch (zzck e) {
                tz2Var.c("Error while getting next extraction task: %s", e.getMessage());
                if (e.zza >= 0) {
                    ((d33) ((h13) f13Var).a()).a(e.zza);
                    hVar.a(e.zza, e);
                }
                r13VarA = null;
            }
            if (r13VarA == null) {
                atomicBoolean.set(false);
                return;
            }
            try {
                if (r13VarA instanceof x03) {
                    hVar.b.a((x03) r13VarA);
                } else if (r13VarA instanceof g23) {
                    hVar.c.a((g23) r13VarA);
                } else if (r13VarA instanceof x13) {
                    hVar.d.a((x13) r13VarA);
                } else if (r13VarA instanceof y13) {
                    hVar.e.a((y13) r13VarA);
                } else if (r13VarA instanceof d23) {
                    hVar.f.a((d23) r13VarA);
                } else if (r13VarA instanceof e23) {
                    hVar.g.a((e23) r13VarA);
                } else {
                    tz2Var.c("Unknown task type: %s", r13VarA.getClass().getName());
                }
            } catch (Exception e2) {
                tz2Var.c("Error during extraction task: %s", e2.getMessage());
                ((d33) ((h13) f13Var).a()).a(r13VarA.a);
                hVar.a(r13VarA.a, e2);
            }
        }
    }
}
