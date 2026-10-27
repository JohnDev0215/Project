package defpackage;

import androidx.lifecycle.l;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: InitializerViewModelFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nInitializerViewModelFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InitializerViewModelFactory.kt\nandroidx/lifecycle/viewmodel/InitializerViewModelFactory\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,115:1\n13579#2,2:116\n*S KotlinDebug\n*F\n+ 1 InitializerViewModelFactory.kt\nandroidx/lifecycle/viewmodel/InitializerViewModelFactory\n*L\n105#1:116,2\n*E\n"})
public final class b01 implements l.a {
    public final zm3<?>[] a;

    public b01(zm3<?>... zm3VarArr) {
        i21.e(zm3VarArr, "initializers");
        this.a = zm3VarArr;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // androidx.lifecycle.l.a
    public final ym3 b(Class cls, xj1 xj1Var) {
        ym3 ym3Var = null;
        for (zm3<?> zm3Var : this.a) {
            if (i21.a(zm3Var.a, cls)) {
                Object objC = zm3Var.b.c(xj1Var);
                ym3Var = objC instanceof ym3 ? (ym3) objC : null;
            }
        }
        if (ym3Var != null) {
            return ym3Var;
        }
        throw new IllegalArgumentException("No initializer set for given class ".concat(cls.getName()));
    }
}
