package p;

import com.spotify.automotive.accountmanager.receiver.AccountRemovedBroadcastReceiver;
import com.spotify.automotive.accountmanager.service.SpotifyAccountAuthenticatorService;
import com.spotify.automotive.base.service.AutomotiveService;
import com.spotify.automotive.base.session.worker.LogoutWorker;
import com.spotify.automotive.baseapp.mediaprovider.MediaProvider;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.baseui.login.AAMLoginPinActivity;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.automotive.baseui.settings.SettingsActivity;

/* JADX INFO: loaded from: classes.dex */
public final class i50 implements z6 {
    public final /* synthetic */ int a;
    public final h50 b;

    public /* synthetic */ i50(h50 h50Var, int i) {
        this.a = i;
        this.b = h50Var;
    }

    public final a7 a(Object obj) {
        u4 u4Var = null;
        int i = this.a;
        h50 h50Var = this.b;
        switch (i) {
            case 0:
                ((AAMLoginPasswordActivity) obj).getClass();
                return new j50(h50Var);
            case 1:
                ((AAMLoginPinActivity) obj).getClass();
                return new k50(h50Var);
            case 2:
                ((AAMLoginScannableActivity) obj).getClass();
                return new l50(h50Var);
            case 3:
                ((AccountRemovedBroadcastReceiver) obj).getClass();
                return new m50(h50Var);
            case 4:
                AutomotiveService automotiveService = (AutomotiveService) obj;
                automotiveService.getClass();
                return new n50(this.b, new yn(24), new z90(23), new u11(), new z90(29), automotiveService);
            case 5:
                ((LogoutWorker) obj).getClass();
                return new o50(h50Var);
            case 6:
                ((MediaProvider) obj).getClass();
                return new m50(h50Var, 0);
            case 7:
                ((SettingsActivity) obj).getClass();
                return new m50(h50Var, (Object) u4Var);
            default:
                ((SpotifyAccountAuthenticatorService) obj).getClass();
                return new m50(h50Var, u4Var);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i50(h50 h50Var, int i, int i2) {
        this(h50Var, 0);
        this.a = i;
        switch (i) {
            case 1:
                this(h50Var, 1);
                break;
            case 2:
                this(h50Var, 2);
                break;
            case 3:
                this(h50Var, 3);
                break;
            case 4:
                this(h50Var, 4);
                break;
            case 5:
                this(h50Var, 5);
                break;
            case 6:
                this(h50Var, 6);
                break;
            case 7:
                this(h50Var, 7);
                break;
            case 8:
                this(h50Var, 8);
                break;
            default:
                break;
        }
    }
}
