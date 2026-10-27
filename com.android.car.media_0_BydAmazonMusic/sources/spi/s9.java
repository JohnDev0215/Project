package spi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s9 implements ob {
    public final o2 a;
    public final x3 b;
    public final s3 c;
    public final f5 d;
    public final q9 e = q9.a;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class a<T> extends nb<T> {
        public final b8<T> a;
        public final Map<String, b> b;

        public a(b8<T> b8Var, Map<String, b> map) {
            this.a = b8Var;
            this.b = map;
        }

        @Override // spi.nb
        public final T a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            T tA = this.a.a();
            try {
                o5Var.b();
                while (o5Var.i()) {
                    b bVar = this.b.get(o5Var.p());
                    if (bVar == null || !bVar.c) {
                        o5Var.A();
                    } else {
                        bVar.a(o5Var, tA);
                    }
                }
                o5Var.f();
                return tA;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new r5(e2);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, T t) throws IOException {
            if (t == null) {
                v5Var.i();
                return;
            }
            v5Var.c();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.c(t)) {
                        v5Var.g(bVar.a);
                        bVar.b(v5Var, t);
                    }
                }
                v5Var.f();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class b {
        public final String a;
        public final boolean b;
        public final boolean c;

        public b(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }

        public abstract void a(o5 o5Var, Object obj);

        public abstract void b(v5 v5Var, Object obj);

        public abstract boolean c(Object obj);
    }

    public s9(o2 o2Var, x3 x3Var, s3 s3Var, f5 f5Var) {
        this.a = o2Var;
        this.b = x3Var;
        this.c = s3Var;
        this.d = f5Var;
    }

    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:43)
        */
    @Override // spi.ob
    public final <T> spi.nb<T> a(
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r35v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */
    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        */

    public final boolean b(Field field, boolean z) {
        boolean z2;
        s3 s3Var = this.c;
        Class<?> type = field.getType();
        if (s3Var.b(type) || s3Var.c(type, z)) {
            return false;
        }
        if ((s3Var.b & field.getModifiers()) != 0 || ((s3Var.a != -1.0d && !s3Var.f((ea) field.getAnnotation(ea.class), (cc) field.getAnnotation(cc.class))) || field.isSynthetic() || ((!s3Var.c && s3Var.e(field.getType())) || s3Var.d(field.getType())))) {
            z2 = true;
            break;
        }
        List<t3> list = z ? s3Var.d : s3Var.e;
        if (!list.isEmpty()) {
            new v3(field);
            Iterator<t3> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().b()) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        return !z2;
    }
}
