package defpackage;

import android.view.View;
import androidx.customview.poolingcontainer.R$id;
import java.util.ArrayList;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: PoolingContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPoolingContainer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PoolingContainer.kt\nandroidx/customview/poolingcontainer/PoolingContainer\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,152:1\n1295#2,2:153\n1295#2,2:155\n1295#2,2:157\n*S KotlinDebug\n*F\n+ 1 PoolingContainer.kt\nandroidx/customview/poolingcontainer/PoolingContainer\n*L\n89#1:153,2\n104#1:155,2\n116#1:157,2\n*E\n"})
public final class q22 {
    public static final int a = R$id.pooling_container_listener_holder_tag;
    public static final int b = R$id.is_pooling_container_tag;

    public static final void a(View view) {
        i21.e(view, "<this>");
        xm3 xm3Var = new xm3(view, null);
        mi2 mi2Var = new mi2();
        mi2Var.h = oj2.g(mi2Var, mi2Var, xm3Var);
        while (mi2Var.hasNext()) {
            View view2 = (View) mi2Var.next();
            int i = a;
            s22 s22Var = (s22) view2.getTag(i);
            if (s22Var == null) {
                s22Var = new s22();
                view2.setTag(i, s22Var);
            }
            ArrayList<r22> arrayList = s22Var.a;
            for (int iW = z7.w(arrayList); -1 < iW; iW--) {
                arrayList.get(iW).a();
            }
        }
    }
}
