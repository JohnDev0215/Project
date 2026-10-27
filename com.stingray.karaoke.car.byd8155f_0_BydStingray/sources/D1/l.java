package d1;

import T.C0757o;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import androidx.compose.ui.node.Owner;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class l extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f13322m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f13323n;
    public final /* synthetic */ C0757o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ d0.k f13324p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f13325q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ View f13326r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Context context, InterfaceC2782k interfaceC2782k, C0757o c0757o, d0.k kVar, int i7, View view) {
        super(0);
        this.f13322m = context;
        this.f13323n = interfaceC2782k;
        this.o = c0757o;
        this.f13324p = kVar;
        this.f13325q = i7;
        this.f13326r = view;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        KeyEvent.Callback callback = this.f13326r;
        A7.m.d(callback, "null cannot be cast to non-null type androidx.compose.ui.node.Owner");
        Owner owner = (Owner) callback;
        return new o(this.f13322m, this.f13323n, this.o, this.f13324p, this.f13325q, owner).getLayoutNode();
    }
}
