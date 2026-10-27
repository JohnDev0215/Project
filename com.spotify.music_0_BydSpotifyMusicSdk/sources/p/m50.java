package p;

import android.app.Application;
import com.spotify.automotive.accountmanager.receiver.AccountRemovedBroadcastReceiver;
import com.spotify.automotive.accountmanager.service.SpotifyAccountAuthenticatorService;
import com.spotify.automotive.baseapp.mediaprovider.MediaProvider;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class m50 implements a7 {
    public final /* synthetic */ int a;
    public final h50 b;

    public /* synthetic */ m50(h50 h50Var, int i, int i2) {
        this.a = i;
        this.b = h50Var;
    }

    @Override // p.a7
    public final void a(Object obj) {
        int i = this.a;
        h50 h50Var = this.b;
        switch (i) {
            case 0:
                AccountRemovedBroadcastReceiver accountRemovedBroadcastReceiver = (AccountRemovedBroadcastReceiver) obj;
                accountRemovedBroadcastReceiver.a = (ef2) h50Var.W.get();
                new k(h50Var.a);
                accountRemovedBroadcastReceiver.b = new yn(26);
                break;
            case 1:
                m60 m60Var = (m60) h50Var.a0.get();
                Application application = h50Var.a;
                String string = application.getApplicationContext().getString(R.string.media_provider_authority);
                vr2.b(string);
                ((MediaProvider) obj).c = new v4(m60Var, new ki1(application, string));
                break;
            case 2:
                SettingsActivity settingsActivity = (SettingsActivity) obj;
                settingsActivity.G = h50Var.b();
                settingsActivity.U = (m60) h50Var.a0.get();
                settingsActivity.V = new zb0();
                break;
            default:
                ((SpotifyAccountAuthenticatorService) obj).c = (ff2) h50Var.d0.get();
                break;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m50(h50 h50Var, u4 u4Var) {
        this(h50Var, 3, 0);
        this.a = 3;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ m50(h50 h50Var) {
        int i = 0;
        this.a = 0;
        this(h50Var, i, i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m50(h50 h50Var, int i) {
        this(h50Var, 1, 0);
        this.a = 1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m50(h50 h50Var, Object obj) {
        this(h50Var, 2, 0);
        this.a = 2;
    }
}
