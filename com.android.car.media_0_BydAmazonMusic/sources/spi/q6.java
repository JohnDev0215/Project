package spi;

import android.app.AlertDialog;
import android.car.XCarRoute;
import android.car.drivingstate.XCarUxRestrictions;
import android.car.drivingstate.XCarUxRestrictionsManager;
import android.car.media.IXCarMediaControl;
import android.car.media.XCarMediaManager;
import android.car.utils.Logs;
import android.content.ComponentName;
import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.browse.BrowseAdapter;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.media.common.source.a;
import com.android.car.ui.toolbar.b;
import com.blankj.utilcode.util.ActivityUtils;
import java.util.Iterator;
import java.util.Objects;
import spi.e2;
import spi.q0;
import spi.uc;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class q6 implements PlaybackFragment.b, uc.d, BrowseAdapter.d, q0.b, PlaybackViewModel.b, a.d, XCarMediaManager.MediaSourceChangedListener, b.e, XCarUxRestrictionsManager.OnUxRestrictionsChangedListener {
    public final /* synthetic */ Object a;

    public /* synthetic */ q6(Object obj) {
        this.a = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashMap, java.util.Map<android.view.View, com.android.car.media.a>] */
    public final void a(View view) {
        com.android.car.media.a aVar = (com.android.car.media.a) ((com.android.car.media.d) this.a).p.remove(view);
        if (aVar != null) {
            aVar.b();
        }
    }

    @Override // com.android.car.ui.toolbar.b.e
    public final void b(com.android.car.ui.toolbar.b bVar) {
        AlertDialog alertDialog = ((jb) this.a).Q;
        if (alertDialog != null) {
            alertDialog.show();
        } else if (Log.isLoggable("CarUiToolbarController", 6)) {
            Log.e("CarUiToolbarController", "Overflow dialog was null when trying to show it!");
        }
    }

    public final void c() {
        o0 o0Var = (o0) this.a;
        Objects.requireNonNull(o0Var);
        Logs.w("BydCarApplicationStub", "cancel errorDialog.");
        BaseMediaApplication.b.a();
        o0Var.b();
        IXCarMediaControl iXCarMediaControl = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
        if (iXCarMediaControl != null) {
            iXCarMediaControl.stop();
        } else {
            Logs.e("BydCarApplicationStub", " iXCarMediaControl is null.");
        }
        ActivityUtils.finishAllActivities();
    }

    @Override // android.car.media.XCarMediaManager.MediaSourceChangedListener
    public final void onMediaSourceChanged(ComponentName componentName) {
        MediaSourceViewModel mediaSourceViewModel = (MediaSourceViewModel) this.a;
        mediaSourceViewModel.e.post(new g6(mediaSourceViewModel, componentName, 2));
    }

    @Override // android.car.drivingstate.XCarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public final void onUxRestrictionsChanged(XCarUxRestrictions xCarUxRestrictions) {
        e2 e2Var = (e2) this.a;
        Objects.requireNonNull(e2Var);
        if (xCarUxRestrictions == null) {
            e2Var.a = new XCarUxRestrictions.Builder(true, 511, 0L).build();
        } else {
            e2Var.a = xCarUxRestrictions;
        }
        Iterator<e2.a> it = e2Var.b.iterator();
        while (it.hasNext()) {
            it.next().a(e2Var.a);
        }
    }
}
