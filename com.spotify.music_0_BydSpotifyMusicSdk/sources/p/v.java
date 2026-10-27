package p;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class v implements ph1 {
    public final Resources a;
    public final k0 b;
    public final up2 c;

    public v(Resources resources, k0 k0Var, up2 up2Var) {
        v03.j(resources, "mResources");
        v03.j(k0Var, "navigationController");
        v03.j(up2Var, "ubiLogger");
        this.a = resources;
        this.b = k0Var;
        this.c = up2Var;
    }

    @Override // p.ph1
    public final void a(bi1 bi1Var) {
        v03.j(bi1Var, "mediaSessionCompat");
        Bundle bundle = new Bundle();
        Resources resources = this.a;
        bundle.putString("android.media.extras.ERROR_RESOLUTION_ACTION_LABEL", resources.getString(R.string.logged_out_error_title));
        l0 l0Var = (l0) this.b;
        l0Var.getClass();
        Context context = l0Var.a;
        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
        intent.setFlags(268435456);
        bundle.putParcelable("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT", PendingIntent.getActivity(context, 0, intent, 67108864));
        String string = resources.getString(R.string.logged_out_error_text);
        v03.i(string, "mResources.getString(R.s…ng.logged_out_error_text)");
        bi1Var.e(new PlaybackStateCompat(7, -1L, 0L, 0.0f, 0L, 3, string, SystemClock.elapsedRealtime(), new ArrayList(), -1L, bundle));
        bi1Var.d(null);
        up2 up2Var = this.c;
        up2Var.getClass();
        cp2 cp2Var = cp2.b;
        v03.j(cp2Var, "parentAbsoluteLocation");
        ip2 ip2Var = ip2.i;
        hp2 hp2VarM = qo.m();
        hp2VarM.h = "automotive";
        hp2VarM.a = "automotive-pre-login";
        hp2VarM.f = "1.0.0";
        hp2VarM.g = "15.3.0";
        ip2 ip2VarA = hp2VarM.a();
        lp2 lp2Var = new lp2();
        lp2Var.a = ip2VarA;
        lp2Var.b = cp2Var;
        ((tp2) up2Var.a).c((mp2) lp2Var.a());
    }
}
