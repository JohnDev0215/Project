package org.chromium.chrome.browser.ntp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import defpackage.im;
import defpackage.z7;
import java.util.ArrayList;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: MyAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ g f;

    public f(g gVar, int i) {
        this.f = gVar;
        this.e = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NewTabPageLayout newTabPageLayout = ((i) this.f.j).a;
        ArrayList arrayList = newTabPageLayout.E;
        int i = this.e;
        String str = ((im) arrayList.get(i)).d;
        int i2 = R$string.card_add;
        Context context = newTabPageLayout.f;
        if (str.equals(context.getString(i2))) {
            if (newTabPageLayout.E.size() >= 12) {
                Toast.makeText(newTabPageLayout.n, context.getString(R$string.card_full_msg), 0).show();
                return;
            }
            Intent intent = new Intent(context, (Class<?>) CardEditActivity.class);
            intent.putExtra("CardType", 0);
            intent.putExtra("CARD_POS", i);
            newTabPageLayout.n.startActivity(intent);
            return;
        }
        Activity activity = ApplicationStatus.c;
        if (activity == null) {
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(((im) newTabPageLayout.E.get(i)).c));
        intent2.putExtra("com.android.browser.application_id", activity.getApplicationContext().getPackageName());
        intent2.setPackage(activity.getApplicationContext().getPackageName());
        intent2.setFlags(268435456);
        z7.g0(intent2, activity.getComponentName());
        org.chromium.chrome.browser.b.A(null, intent2, null);
    }
}
