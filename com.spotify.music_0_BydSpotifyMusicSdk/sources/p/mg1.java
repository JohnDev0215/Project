package p;

import android.os.Bundle;
import android.os.Messenger;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat$Token;
import com.spotify.automotive.base.service.AAMMediaBrowserService;

/* JADX INFO: loaded from: classes.dex */
public abstract class mg1 extends MediaBrowserService {
    public final /* synthetic */ ng1 c;

    public mg1(ng1 ng1Var, bh1 bh1Var) {
        this.c = ng1Var;
        attachBaseContext(bh1Var);
    }

    @Override // android.service.media.MediaBrowserService
    public final MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        Bundle bundle2;
        hg1 hg1Var;
        bi1.a(bundle);
        Bundle bundle3 = bundle == null ? null : new Bundle(bundle);
        ng1 ng1Var = this.c;
        bh1 bh1Var = ng1Var.d;
        int i2 = -1;
        if (bundle3 == null || bundle3.getInt("extra_client_version", 0) == 0) {
            bundle2 = null;
        } else {
            bundle3.remove("extra_client_version");
            ng1Var.c = new Messenger(bh1Var.n);
            bundle2 = new Bundle();
            bundle2.putInt("extra_service_version", 2);
            uo.b(bundle2, "extra_messenger", ng1Var.c.getBinder());
            MediaSessionCompat$Token mediaSessionCompat$Token = bh1Var.o;
            if (mediaSessionCompat$Token != null) {
                android.support.v4.media.session.b bVarA = mediaSessionCompat$Token.a();
                uo.b(bundle2, "extra_session_binder", bVarA == null ? null : bVarA.asBinder());
            } else {
                ng1Var.a.add(bundle2);
            }
            i2 = bundle3.getInt("extra_calling_pid", -1);
            bundle3.remove("extra_calling_pid");
        }
        jg1 jg1Var = new jg1(ng1Var.d, str, i2, i, null);
        bh1Var.getClass();
        hg1 hg1VarA = bh1Var.a(str, i);
        if (hg1VarA == null) {
            hg1Var = null;
        } else {
            if (ng1Var.c != null) {
                bh1Var.l.add(jg1Var);
            }
            Object obj = hg1VarA.b;
            if (bundle2 == null) {
                bundle2 = (Bundle) obj;
            } else {
                Bundle bundle4 = (Bundle) obj;
                if (bundle4 != null) {
                    bundle2.putAll(bundle4);
                }
            }
            hg1Var = new hg1(bundle2, (String) hg1VarA.a);
        }
        if (hg1Var == null) {
            return null;
        }
        return new MediaBrowserService.BrowserRoot((String) hg1Var.a, (Bundle) hg1Var.b);
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadChildren(String str, MediaBrowserService.Result result) {
        wg1 wg1Var = new wg1(result);
        ng1 ng1Var = this.c;
        ng1Var.getClass();
        lg1 lg1Var = new lg1(str, wg1Var, 0);
        bh1 bh1Var = ng1Var.d;
        jg1 jg1Var = bh1Var.k;
        ((AAMMediaBrowserService) bh1Var).q.d(Bundle.EMPTY, lg1Var, str);
    }
}
