package defpackage;

import android.content.DialogInterface;
import android.widget.ListView;

/* JADX INFO: renamed from: eB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnClickListenerC0702eB implements DialogInterface.OnClickListener {
    public final /* synthetic */ ListView s;
    public final /* synthetic */ C0926iB t;

    public DialogInterfaceOnClickListenerC0702eB(C0926iB c0926iB, ListView listView) {
        this.t = c0926iB;
        this.s = listView;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.t.d(C0926iB.c(this.s));
    }
}
