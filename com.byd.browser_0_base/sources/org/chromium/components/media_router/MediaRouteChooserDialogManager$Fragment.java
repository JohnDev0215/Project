package org.chromium.components.media_router;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import androidx.mediarouter.app.MediaRouteChooserDialog;
import androidx.mediarouter.media.h;
import defpackage.kd1;
import org.chromium.components.media_router.MediaRouteChooserDialogManager$Fragment;
import org.chromium.components.media_router.a;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteChooserDialogManager$Fragment extends kd1 {
    public static final /* synthetic */ int s0 = 0;
    public final a.C0132a r0;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            MediaRouteChooserDialogManager$Fragment.this.I0(false, false);
        }
    }

    public class b extends MediaRouteChooserDialog {
        public b(Context context, int i) {
            super(context, i);
        }

        @Override // androidx.mediarouter.app.MediaRouteChooserDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            ListView listView = (ListView) findViewById(R$id.mr_chooser_list);
            if (listView != null) {
                final MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment = MediaRouteChooserDialogManager$Fragment.this;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: ld1
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        int i2 = MediaRouteChooserDialogManager$Fragment.s0;
                        MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment2 = mediaRouteChooserDialogManager$Fragment;
                        mediaRouteChooserDialogManager$Fragment2.getClass();
                        h.C0020h c0020h = (h.C0020h) adapterView.getItemAtPosition(i);
                        if (c0020h == null || !c0020h.g) {
                            return;
                        }
                        mediaRouteChooserDialogManager$Fragment2.getClass();
                        throw null;
                    }
                });
                new Handler().postDelayed(new org.chromium.components.media_router.b(this), 3000L);
            }
        }
    }

    public MediaRouteChooserDialogManager$Fragment() {
        Handler handler = new Handler();
        this.r0 = new a.C0132a();
        handler.post(new a());
    }

    @Override // defpackage.kd1
    public final MediaRouteChooserDialog O0(Context context) {
        b bVar = new b(context, this.e0);
        bVar.setCanceledOnTouchOutside(true);
        return bVar;
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void o0() {
        FragmentActivity fragmentActivityD = D();
        a.C0132a c0132a = this.r0;
        c0132a.getClass();
        int systemUiVisibility = ((FrameLayout) fragmentActivityD.getWindow().getDecorView()).getSystemUiVisibility();
        c0132a.a = systemUiVisibility;
        c0132a.b = (systemUiVisibility & 1024) != 0;
        super.o0();
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        throw null;
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void p0() {
        super.p0();
        this.r0.a(D());
    }
}
