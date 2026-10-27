package defpackage;

import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: OnBackPressedCallback.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nOnBackPressedCallback.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnBackPressedCallback.kt\nandroidx/activity/OnBackPressedCallback\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,85:1\n1855#2,2:86\n*S KotlinDebug\n*F\n+ 1 OnBackPressedCallback.kt\nandroidx/activity/OnBackPressedCallback\n*L\n67#1:86,2\n*E\n"})
public abstract class xr1 {
    public boolean a;
    public final CopyOnWriteArrayList<yl> b = new CopyOnWriteArrayList<>();
    public yq0<vi3> c;

    public xr1(boolean z) {
        this.a = z;
    }

    public abstract void a();

    public final void b(boolean z) {
        this.a = z;
        yq0<vi3> yq0Var = this.c;
        if (yq0Var != null) {
            yq0Var.a();
        }
    }
}
