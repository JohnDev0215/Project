package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: WebPaymentIntentHelperType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sr3 {
    public final nr3 a;
    public final List<tr3> b;
    public final String c;
    public final String d;
    public final Bundle e;

    public sr3(nr3 nr3Var, ArrayList arrayList, String str, String str2, Bundle bundle) {
        this.a = nr3Var;
        this.b = arrayList;
        this.c = str;
        this.d = str2;
        this.e = bundle;
    }

    public final Bundle a() {
        Bundle bundle = new Bundle();
        nr3 nr3Var = this.a;
        if (nr3Var != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("currency", nr3Var.a);
            bundle2.putString("value", nr3Var.b);
            bundle.putBundle("total", bundle2);
        }
        List<tr3> list = this.b;
        if (list != null && !list.isEmpty()) {
            bundle.putParcelableArray("shippingOptions", tr3.a(list));
        }
        String str = this.c;
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("error", str);
        }
        String str2 = this.d;
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("stringifiedPaymentMethodErrors", str2);
        }
        Bundle bundle3 = this.e;
        if (bundle3 != null) {
            bundle.putBundle("addressErrors", bundle3);
        }
        return bundle;
    }
}
