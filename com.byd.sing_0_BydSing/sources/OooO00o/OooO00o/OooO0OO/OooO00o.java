package OooO00o.OooO00o.OooO0Oo;

import OooO00o.OooO00o.OooO0O0.b;
import android.app.Application;
import com.blankj.utilcode.util.q;
import com.loostone.libserver.version1.entity.NetUrlConfig;
import com.loostone.libserver.version1.util.JniCall;
import com.loostone.libtuning.R;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f63a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f64b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f65c;

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.OooO00o> {
        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.OooO00o invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.OooO00o(OooO00o.this.f63a);
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0> {
        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0 invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0(OooO00o.this.f63a);
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public OooO00o(Application application) {
        h.d(application, "context");
        this.f63a = application;
        OooO00o.OooO00o.OooO0O0.a.f39a.b(application);
        JniCall.f3810OooO00o.initNetConfig();
        String strD = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.d(application);
        b.f41a.e("NetworkMgr", h.i("channel: ", strD));
        NetUrlConfig.init((o.l(strD, "CAR_", false, 2, null) || h.a(strD, "BIYADI")) ? q.b("address_car.json") : q.b("address.json"));
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.b bVar = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.b.f136a;
        String string = application.getResources().getString(R.string.ota_address_formal);
        h.c(string, "context.resources.getString(R.string.ota_address_formal)");
        bVar.b(string);
        this.f64b = d.b(new OooO0O0());
        this.f65c = d.b(new OooO0OO());
    }

    public final OooO00o.OooO00o.OooO0Oo.a.a a() {
        return (OooO00o.OooO00o.OooO0Oo.a.a) this.f64b.getValue();
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0 c() {
        return (OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0) this.f65c.getValue();
    }
}
