package defpackage;

import android.content.DialogInterface;
import android.widget.ListView;
import org.chromium.content.browser.input.d;

/* JADX INFO: compiled from: SelectPopupDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sh2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ListView e;
    public final /* synthetic */ d f;

    public sh2(d dVar, ListView listView) {
        this.f = dVar;
        this.e = listView;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f.d(d.c(this.e));
    }
}
