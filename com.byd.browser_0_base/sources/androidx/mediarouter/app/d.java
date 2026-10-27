package androidx.mediarouter.app;

import android.view.View;
import androidx.mediarouter.app.MediaRouteDynamicChooserDialog;
import androidx.mediarouter.media.h;

/* JADX INFO: compiled from: MediaRouteDynamicChooserDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements View.OnClickListener {
    public final /* synthetic */ h.C0020h e;
    public final /* synthetic */ MediaRouteDynamicChooserDialog.d.c f;

    public d(MediaRouteDynamicChooserDialog.d.c cVar, h.C0020h c0020h) {
        this.f = cVar;
        this.e = c0020h;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MediaRouteDynamicChooserDialog.d.c cVar = this.f;
        MediaRouteDynamicChooserDialog mediaRouteDynamicChooserDialog = MediaRouteDynamicChooserDialog.this;
        h.C0020h c0020h = this.e;
        mediaRouteDynamicChooserDialog.r = c0020h;
        c0020h.n();
        cVar.z.setVisibility(4);
        cVar.A.setVisibility(0);
    }
}
