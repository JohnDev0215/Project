package Q1;

import A7.m;
import A7.o;
import android.content.Context;
import android.content.SharedPreferences;
import h6.n;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6107m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Context f6108n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Context context, int i7) {
        super(0);
        this.f6107m = i7;
        this.f6108n = context;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f6107m) {
            case 0:
                SharedPreferences sharedPreferences = this.f6108n.getSharedPreferences("userSettings", 0);
                m.e(sharedPreferences, "context.getSharedPrefere…me, Context.MODE_PRIVATE)");
                return sharedPreferences;
            default:
                return n.b(this.f6108n);
        }
    }
}
