package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: compiled from: WebPaymentIntentHelperType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tr3 {
    public final String a;
    public final String b;
    public final nr3 c;
    public final boolean d;

    public tr3(String str, String str2, nr3 nr3Var, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = nr3Var;
        this.d = z;
    }

    public static Parcelable[] a(List<tr3> list) {
        Parcelable[] parcelableArr = new Parcelable[list.size()];
        int i = 0;
        for (tr3 tr3Var : list) {
            tr3Var.getClass();
            Bundle bundle = new Bundle();
            bundle.putString("id", tr3Var.a);
            bundle.putString("label", tr3Var.b);
            nr3 nr3Var = tr3Var.c;
            nr3Var.getClass();
            Bundle bundle2 = new Bundle();
            bundle2.putString("currency", nr3Var.a);
            bundle2.putString("value", nr3Var.b);
            bundle.putBundle("amount", bundle2);
            bundle.putBoolean("selected", tr3Var.d);
            parcelableArr[i] = bundle;
            i++;
        }
        return parcelableArr;
    }
}
