package p;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fg1 extends vg1 {
    public final /* synthetic */ jg1 e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Bundle g;
    public final /* synthetic */ Bundle h;
    public final /* synthetic */ bh1 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fg1(bh1 bh1Var, Object obj, jg1 jg1Var, String str, Bundle bundle, Bundle bundle2) {
        super(obj);
        this.i = bh1Var;
        this.e = jg1Var;
        this.f = str;
        this.g = bundle;
        this.h = bundle2;
    }

    @Override // p.vg1
    public final void e(Object obj) {
        List list = (List) obj;
        nb nbVar = this.i.m;
        jg1 jg1Var = this.e;
        if (nbVar.get(jg1Var.d.a()) != jg1Var) {
            int i = bh1.f107p;
        } else {
            try {
                jg1Var.d.d(this.f, list, this.g, this.h);
            } catch (RemoteException unused) {
            }
        }
    }
}
