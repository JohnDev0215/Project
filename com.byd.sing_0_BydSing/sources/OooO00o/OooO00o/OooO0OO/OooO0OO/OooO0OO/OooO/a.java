package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h;
import com.loostone.libserver.version2.bean.UpgradeData;
import com.loostone.libserver.version2.bean.UpgradeParams;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class a extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final UpgradeParams f105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f106c;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO.a$a, reason: collision with other inner class name */
    public static final class C0007a implements h<UpgradeData> {
        public C0007a() {
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            h.a.a(this, i, str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(UpgradeData upgradeData) {
            kotlin.jvm.internal.h.d(upgradeData, "data");
            a aVar = a.this;
            UpgradeData.UpgradeList data = upgradeData.getData();
            kotlin.jvm.internal.h.b(data);
            String fileUrl = data.getVersions().get(0).getFileUrl();
            kotlin.jvm.internal.h.b(fileUrl);
            aVar.k(fileUrl, a.this.o());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVarO = a.this.o();
            if (bVarO == null) {
                return;
            }
            bVarO.onError(a.this.p().getUpgradeType() + " config: " + ((Object) str));
        }
    }

    public a(UpgradeParams upgradeParams, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(upgradeParams, "params");
        this.f105b = upgradeParams;
        this.f106c = bVar;
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.k
    public void a() throws JSONException {
        C0007a c0007a = new C0007a();
        if (this.f105b.getPeriod() > 0) {
            n(this.f105b, c0007a);
        } else {
            j(this.f105b, c0007a);
        }
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b o() {
        return this.f106c;
    }

    public final UpgradeParams p() {
        return this.f105b;
    }
}
