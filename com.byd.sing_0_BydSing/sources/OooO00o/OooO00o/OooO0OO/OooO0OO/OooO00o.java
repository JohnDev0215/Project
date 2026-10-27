package OooO00o.OooO00o.OooO0Oo.OooO0OO;

import android.content.Context;
import com.loostone.libserver.version1.entity.response.ResponseData;
import com.loostone.libserver.version1.entity.response.config.MicLimitInfo;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o implements OooO00o.OooO00o.OooO0Oo.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f69b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f70c;

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.b.a.a> {
        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.a.a invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.a.a(OooO00o.this.f68a, "APP_UPDATE_TAG");
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.b.g.a> {
        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.g.a invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.g.a(OooO00o.this.f68a, "AUDIO_EFFECT_TAG");
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d.a> {
        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d.a invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d.a(OooO00o.this.f68a, "MIC_VERIFICATION_TAG");
        }
    }

    public static final class OooOO0 extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.b.c.a> {
        public OooOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.c.a invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.c.a(OooO00o.this.f68a, "REFRESH_DATA_TAG");
        }
    }

    public static final class OooOO0O extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f.a> {
        public OooOO0O() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f.a invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f.a(OooO00o.this.f68a, "UPLOAD_MIC_INFO_TAG");
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a<MicLimitInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.a.b<Boolean> f76a;

        public b(OooO00o.OooO00o.OooO0Oo.a.b<Boolean> bVar) {
            this.f76a = bVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a
        public void a(ResponseData<MicLimitInfo> responseData) {
            if (responseData == null || !responseData.isSuccess()) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("NetworkVersion1", "micVerification() onSuccess but no data");
                this.f76a.onError();
            } else {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("NetworkVersion1", "micVerification() onSuccess");
                this.f76a.onSuccess(Boolean.valueOf(responseData.getData().getState() == 1));
            }
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a
        public void onError(String str) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("NetworkVersion1", h.i("micVerification() onError ------> ", str));
            this.f76a.onError();
        }
    }

    static {
        new a(null);
    }

    public OooO00o(Context context) {
        h.d(context, "context");
        this.f68a = context;
        d.b(new OooO0O0());
        this.f69b = d.b(new OooO0o());
        this.f70c = d.b(new OooOO0O());
        d.b(new OooOO0());
        d.b(new OooO0OO());
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.a
    public void a(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
        h.d(oooO00o, "cachedDongleDevice");
        e().j(oooO00o.w(), this.f68a.getPackageName(), null);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.a
    public void b(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o, OooO00o.OooO00o.OooO0Oo.a.b<Boolean> bVar) {
        h.d(oooO00o, "cachedDongleDevice");
        h.d(bVar, "listener");
        c().j(1, oooO00o, new b(bVar));
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d.a c() {
        return (OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d.a) this.f69b.getValue();
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f.a e() {
        return (OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f.a) this.f70c.getValue();
    }
}
