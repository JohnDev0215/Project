package org.chromium.components.media_router;

import android.widget.ListView;
import defpackage.oj2;
import org.chromium.components.media_router.MediaRouteChooserDialogManager$Fragment;

/* JADX INFO: compiled from: MediaRouteChooserDialogManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ MediaRouteChooserDialogManager$Fragment.b e;

    public b(MediaRouteChooserDialogManager$Fragment.b bVar) {
        this.e = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListView listView = (ListView) this.e.findViewById(R$id.mr_chooser_list);
        if (listView != null) {
            oj2.Q(listView.getCount(), "MediaRouter.Ui.Device.Count");
        }
    }
}
