package androidx.activity.result;

import android.annotation.SuppressLint;
import android.os.Bundle;
import defpackage.i21;
import defpackage.s2;
import defpackage.t2;
import defpackage.w2;
import defpackage.x82;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: compiled from: ActivityResultRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public ArrayList<String> d = new ArrayList<>();
    public final transient HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final Bundle g = new Bundle();

    /* JADX INFO: renamed from: androidx.activity.result.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityResultRegistry.java */
    public static class C0001a<O> {
        public final s2<O> a;
        public final t2<?, O> b;

        public C0001a(s2<O> s2Var, t2<?, O> t2Var) {
            this.a = s2Var;
            this.b = t2Var;
        }
    }

    /* JADX INFO: compiled from: ActivityResultRegistry.java */
    public static class b {
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
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r5v2 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    public final boolean a(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            java.util.HashMap r0 = r3.a
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r4 = r0.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L10
            r3 = 0
            return r3
        L10:
            java.util.HashMap r0 = r3.e
            java.lang.Object r0 = r0.get(r4)
            androidx.activity.result.a$a r0 = (androidx.activity.result.a.C0001a) r0
            if (r0 == 0) goto L35
            s2<O> r1 = r0.a
            if (r1 == 0) goto L35
            java.util.ArrayList<java.lang.String> r2 = r3.d
            boolean r2 = r2.contains(r4)
            if (r2 == 0) goto L35
            t2<?, O> r0 = r0.b
            java.lang.Object r5 = r0.c(r6, r5)
            r1.a(r5)
            java.util.ArrayList<java.lang.String> r3 = r3.d
            r3.remove(r4)
            goto L44
        L35:
            java.util.HashMap r0 = r3.f
            r0.remove(r4)
            androidx.activity.result.ActivityResult r0 = new androidx.activity.result.ActivityResult
            r0.<init>(r6, r5)
            android.os.Bundle r3 = r3.g
            r3.putParcelable(r4, r0)
        L44:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.a.a(int, int, android.content.Intent):boolean");
    }

    public abstract void b(int i, t2 t2Var, @SuppressLint({"UnknownNullness"}) Object obj);

    public final w2 c(String str, t2 t2Var, s2 s2Var) {
        int i;
        HashMap map;
        HashMap map2 = this.b;
        if (((Integer) map2.get(str)) == null) {
            x82.e.getClass();
            Random random = x82.f.g.get();
            i21.d(random, "implStorage.get()");
            int iNextInt = random.nextInt(2147418112);
            while (true) {
                i = iNextInt + 65536;
                map = this.a;
                if (!map.containsKey(Integer.valueOf(i))) {
                    break;
                }
                x82.e.getClass();
                Random random2 = x82.f.g.get();
                i21.d(random2, "implStorage.get()");
                iNextInt = random2.nextInt(2147418112);
            }
            map.put(Integer.valueOf(i), str);
            map2.put(str, Integer.valueOf(i));
        }
        this.e.put(str, new C0001a(s2Var, t2Var));
        HashMap map3 = this.f;
        if (map3.containsKey(str)) {
            Object obj = map3.get(str);
            map3.remove(str);
            s2Var.a(obj);
        }
        Bundle bundle = this.g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            s2Var.a(t2Var.c(activityResult.f, activityResult.e));
        }
        return new w2(this, str, t2Var);
    }
}
