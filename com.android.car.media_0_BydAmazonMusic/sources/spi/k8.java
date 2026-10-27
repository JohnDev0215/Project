package spi;

import android.app.Activity;
import android.view.View;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.common.PlaybackControlsActionBar;
import com.android.car.ui.preference.CarUiTwoActionPreference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;
import spi.q0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k8 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ k8(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        Activity activityE;
        switch (this.a) {
            case 0:
                PlaybackControlsActionBar playbackControlsActionBar = ((PlaybackFragment) this.b).f;
                if (playbackControlsActionBar.h) {
                    playbackControlsActionBar.h();
                }
                break;
            case 1:
                q0 q0Var = (q0) this.b;
                q0Var.b = -1L;
                q0.b bVar = q0Var.e;
                if (bVar != null) {
                    ((q6) bVar).c();
                }
                break;
            case 2:
                ua uaVar = (ua) this.b;
                int i = ua.a;
                uaVar.dismiss();
                break;
            case 3:
                ((f7) this.b).c.b();
                break;
            case 4:
                jb jbVar = (jb) this.b;
                Objects.requireNonNull(jbVar);
                ArrayList<bb> arrayList = new ArrayList(jbVar.z);
                ArrayList arrayList2 = new ArrayList(jbVar.A);
                while (true) {
                    for (bb bbVar : arrayList) {
                        z = z || bbVar.a();
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        z = z || ((Boolean) ((Supplier) it.next()).get()).booleanValue();
                    }
                    if (!z && (activityE = b2.e(jbVar.u)) != null) {
                        activityE.onBackPressed();
                        break;
                    }
                }
                break;
            default:
                ((CarUiTwoActionPreference) this.b).performClickUnrestricted(view);
                break;
        }
    }
}
