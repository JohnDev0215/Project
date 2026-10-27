package defpackage;

import android.content.Context;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: qw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1408qw extends LinearLayout {
    public C1408qw(Context context, List list, InterfaceC1353pw interfaceC1353pw) {
        super(context);
        setOrientation(1);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            addView(new ViewOnClickListenerC1298ow(this, context, (InterfaceC1326pN) it.next(), interfaceC1353pw));
        }
    }
}
