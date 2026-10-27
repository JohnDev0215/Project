package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.internal.Excluder;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import p.lo2;
import p.s22;
import p.t71;
import p.u22;
import p.x42;
import p.xo1;
import p.yt0;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class ReflectiveTypeAdapterFactory implements lo2 {
    public final x42 c;
    public final yt0 e;
    public final Excluder k;
    public final JsonAdapterAnnotationTypeAdapterFactory l;
    public final s22 m = s22.a;

    public static final class Adapter<T> extends b {
        public final xo1 a;
        public final Map b;

        public Adapter(xo1 xo1Var, LinkedHashMap linkedHashMap) {
            this.a = xo1Var;
            this.b = linkedHashMap;
        }

        @Override // com.google.gson.b
        public final Object b(t71 t71Var) throws IOException {
            if (t71Var.K0() == JsonToken.q) {
                t71Var.z0();
                return null;
            }
            Object objK = this.a.k();
            try {
                t71Var.c();
                while (t71Var.hasNext()) {
                    u22 u22Var = (u22) this.b.get(t71Var.r0());
                    if (u22Var == null || !u22Var.c) {
                        t71Var.D();
                    } else {
                        Object objB = u22Var.f.b(t71Var);
                        if (objB != null || !u22Var.i) {
                            u22Var.d.set(objK, objB);
                        }
                    }
                }
                t71Var.J();
                return objK;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.b
        public final void c(z71 z71Var, Object obj) throws IOException {
            if (obj == null) {
                z71Var.E0();
                return;
            }
            z71Var.i();
            try {
                for (u22 u22Var : this.b.values()) {
                    boolean z = u22Var.b;
                    Field field = u22Var.d;
                    if (z && field.get(obj) != obj) {
                        z71Var.X(u22Var.a);
                        Object obj2 = field.get(obj);
                        boolean z2 = u22Var.e;
                        b typeAdapterRuntimeTypeWrapper = u22Var.f;
                        if (!z2) {
                            typeAdapterRuntimeTypeWrapper = new TypeAdapterRuntimeTypeWrapper(u22Var.g, typeAdapterRuntimeTypeWrapper, u22Var.h.b);
                        }
                        typeAdapterRuntimeTypeWrapper.c(z71Var, obj2);
                    }
                }
                z71Var.J();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public ReflectiveTypeAdapterFactory(x42 x42Var, yt0 yt0Var, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.c = x42Var;
        this.e = yt0Var;
        this.k = excluder;
        this.l = jsonAdapterAnnotationTypeAdapterFactory;
    }

    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:43)
        */
    @Override // p.lo2
    public final com.google.gson.b a(
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r34v0 ??
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

    /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(java.lang.reflect.Field r8, boolean r9) {
        /*
            r7 = this;
            java.lang.Class r0 = r8.getType()
            com.google.gson.internal.Excluder r1 = r7.k
            boolean r0 = r1.d(r0)
            if (r0 != 0) goto L9a
            r1.e(r9)
            int r0 = r8.getModifiers()
            int r2 = r1.e
            r0 = r0 & r2
            if (r0 == 0) goto L1a
            goto L9a
        L1a:
            double r2 = r1.c
            r4 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L4a
            java.lang.Class<p.md2> r0 = p.md2.class
            java.lang.annotation.Annotation r0 = r8.getAnnotation(r0)
            p.md2 r0 = (p.md2) r0
            java.lang.Class<p.er2> r2 = p.er2.class
            java.lang.annotation.Annotation r2 = r8.getAnnotation(r2)
            p.er2 r2 = (p.er2) r2
            double r3 = r1.c
            if (r0 == 0) goto L3f
            double r5 = r0.value()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 <= 0) goto L3f
            goto L9a
        L3f:
            if (r2 == 0) goto L4a
            double r5 = r2.value()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 > 0) goto L4a
            goto L9a
        L4a:
            boolean r0 = r8.isSynthetic()
            if (r0 == 0) goto L51
            goto L9a
        L51:
            boolean r0 = r1.k
            if (r0 != 0) goto L67
            java.lang.Class r0 = r8.getType()
            boolean r2 = r0.isMemberClass()
            if (r2 == 0) goto L67
            int r0 = r0.getModifiers()
            r0 = r0 & 8
            if (r0 == 0) goto L9a
        L67:
            java.lang.Class r0 = r8.getType()
            boolean r0 = com.google.gson.internal.Excluder.f(r0)
            if (r0 == 0) goto L72
            goto L9a
        L72:
            if (r9 == 0) goto L77
            java.util.List r9 = r1.l
            goto L79
        L77:
            java.util.List r9 = r1.m
        L79:
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L98
            p.hk0 r0 = new p.hk0
            r0.<init>(r8)
            java.util.Iterator r8 = r9.iterator()
            boolean r9 = r8.hasNext()
            if (r9 != 0) goto L8f
            goto L98
        L8f:
            java.lang.Object r8 = r8.next()
            p.u4.z(r8)
            r8 = 0
            throw r8
        L98:
            r8 = 1
            goto L9b
        L9a:
            r8 = 0
        L9b:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b(java.lang.reflect.Field, boolean):boolean");
    }
}
