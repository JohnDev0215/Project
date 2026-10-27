package a.b.k;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertController;

/* JADX INFO: loaded from: classes.dex */
public class g implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AlertController f13a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AlertController.b f14b;

    public g(AlertController.b bVar, AlertController alertController) {
        this.f14b = bVar;
        this.f13a = alertController;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f14b.m.onClick(this.f13a.f1387b, i);
        if (this.f14b.o) {
            return;
        }
        this.f13a.f1387b.dismiss();
    }
}
