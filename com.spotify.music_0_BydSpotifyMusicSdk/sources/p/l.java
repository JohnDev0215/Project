package p;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l implements ph1 {
    public final Resources a;
    public final k0 b;

    public l(Resources resources, k0 k0Var) {
        this.a = resources;
        this.b = k0Var;
    }

    @Override // p.ph1
    public final void a(bi1 bi1Var) {
        Bundle bundle = new Bundle();
        Resources resources = this.a;
        bundle.putString("android.media.extras.ERROR_RESOLUTION_ACTION_LABEL", resources.getString(R.string.client_deprecated_error_title));
        l0 l0Var = (l0) this.b;
        l0Var.getClass();
        StringBuilder sb = new StringBuilder("market://details?id=");
        Context context = l0Var.a;
        sb.append(context.getPackageName());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268468224);
        bundle.putParcelable("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT", PendingIntent.getActivity(context, 0, intent, 67108864));
        bi1Var.e(new PlaybackStateCompat(7, -1L, 0L, 0.0f, 0L, 1, resources.getString(R.string.client_deprecated_error_text), SystemClock.elapsedRealtime(), new ArrayList(), -1L, bundle));
        bi1Var.d(null);
    }
}
