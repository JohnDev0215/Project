package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import java.util.Date;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l2 {
    public static final String c = "com.amazon.identity.auth.device.l2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f416a;
    public final PandaServiceAccessor b;

    public l2(Context context, PandaServiceAccessor pandaServiceAccessor) {
        this.f416a = context;
        this.b = pandaServiceAccessor;
    }

    public static boolean a(Context context) {
        return new Date(new f8(context, "authority.signature.expiry.store", 0).f313a.getLong("authority.signature.expiry.key", 0L)).before(new Date());
    }

    public final void a(Callback callback, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        callback.onError(bundle);
    }
}
