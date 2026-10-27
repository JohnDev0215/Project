package defpackage;

import J.N;
import android.view.View;
import android.widget.AdapterView;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;
import org.chromium.components.dom_distiller.core.DistilledPagePrefs;

/* JADX INFO: compiled from: DistilledPagePrefsView.java */
/* JADX INFO: loaded from: classes.dex */
public final class ib0 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ DistilledPagePrefsView e;

    public ib0(DistilledPagePrefsView distilledPagePrefsView) {
        this.e = distilledPagePrefsView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= 0 && i <= 2) {
            DistilledPagePrefs distilledPagePrefs = this.e.g;
            distilledPagePrefs.getClass();
            z7.n0(i);
            N.MfkxLC88(distilledPagePrefs.a, distilledPagePrefs, i);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
