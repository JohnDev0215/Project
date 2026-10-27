package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO.OooO0OO;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO0oo.OooOO0;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.k;
import com.loostone.libserver.version2.bean.DongleParams;
import java.util.ArrayList;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b {

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e.a$a, reason: collision with other inner class name */
    public static final class C0011a implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ DongleParams f145a;

        public C0011a(DongleParams dongleParams) {
            this.f145a = dongleParams;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            if (str == null) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("DongleNetProxy", "当前麦克风信息有误!");
                OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b activateListener = this.f145a.getActivateListener();
                if (activateListener == null) {
                    return;
                }
                activateListener.onError("mic channel error");
            }
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
        }
    }

    public a(DongleParams dongleParams) {
        h.d(dongleParams, "params");
        e(new OooOO0());
        d(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.d.b());
        c(new C0011a(dongleParams));
        OooO00o dongle = dongleParams.getDongle();
        if (dongle == null) {
            return;
        }
        ArrayList<k> arrayListH = h();
        arrayListH.add(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO.b(dongle, dongleParams.getConfigListener()));
        arrayListH.add(new OooO0OO(dongle, dongleParams.getDownloadListener()));
    }
}
