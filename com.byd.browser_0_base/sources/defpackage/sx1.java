package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.preference.Preference;
import java.util.LinkedHashMap;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.settings.MainSettings;
import org.chromium.chrome.browser.signin.SyncConsentActivity;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class sx1 implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ sx1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 1:
                od2 od2Var = (od2) obj;
                od2Var.getClass();
                qx1.b(preference.e, 10, od2Var.c, false);
                break;
            case 2:
                MainSettings mainSettings = (MainSettings) obj;
                int i2 = MainSettings.m0;
                mainSettings.getClass();
                mainSettings.F0(new Intent(mainSettings.D(), (Class<?>) SyncConsentActivity.class));
                break;
            default:
                TracingSettings tracingSettings = (TracingSettings) obj;
                LinkedHashMap linkedHashMap = TracingSettings.n0;
                tracingSettings.getClass();
                te3 te3VarA = te3.a();
                te3VarA.getClass();
                Intent intent = new Intent("android.intent.action.SEND");
                Uri uriB = ContentUriUtils.b(te3VarA.e);
                intent.setType("application/gzip");
                intent.putExtra("android.intent.extra.STREAM", uriB);
                intent.addFlags(1);
                Context context = xz.a;
                Intent intentCreateChooser = Intent.createChooser(intent, "Share trace");
                intentCreateChooser.addFlags(268435456);
                context.startActivity(intentCreateChooser);
                PostTask.b(rh3.a, new eu1(29, te3VarA), 3600000L);
                te3VarA.e = null;
                te3VarA.b(1);
                tracingSettings.P0();
                break;
        }
        return true;
    }
}
