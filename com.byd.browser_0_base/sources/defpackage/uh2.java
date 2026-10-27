package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.chromium.content.browser.input.d;

/* JADX INFO: compiled from: SelectPopupDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uh2 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ ListView e;
    public final /* synthetic */ d f;

    public uh2(d dVar, ListView listView) {
        this.f = dVar;
        this.e = listView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int[] iArrC = d.c(this.e);
        d dVar = this.f;
        dVar.d(iArrC);
        dVar.a.dismiss();
    }
}
