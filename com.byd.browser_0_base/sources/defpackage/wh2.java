package defpackage;

import android.view.View;
import android.widget.AdapterView;
import org.chromium.content.browser.input.e;

/* JADX INFO: compiled from: SelectPopupDropdown.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wh2 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ e e;

    public wh2(e eVar) {
        this.e = eVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        e eVar = this.e;
        eVar.c(new int[]{i});
        eVar.b(false);
    }
}
