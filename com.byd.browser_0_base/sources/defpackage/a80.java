package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.b;
import androidx.fragment.app.j;
import defpackage.am;
import java.util.Collection;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemShareInfo;
import org.chromium.components.offline_items_collection.ShareCallback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a80 implements am.a, ShareCallback {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ a80(Object obj, Object obj2, Object obj3, Object obj4) {
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.h = obj4;
    }

    @Override // org.chromium.components.offline_items_collection.ShareCallback
    public final void a(OfflineItemShareInfo offlineItemShareInfo) {
        Intent intentA;
        m60 m60Var = (m60) this.e;
        Collection collection = (Collection) this.f;
        OfflineItem offlineItem = (OfflineItem) this.g;
        Collection collection2 = (Collection) this.h;
        m60Var.getClass();
        collection.add(new qv1(offlineItem, offlineItemShareInfo));
        if (collection.size() != collection2.size() || (intentA = hl2.a(collection)) == null) {
            return;
        }
        g60 g60Var = (g60) ((gx) m60Var.h).f;
        g60Var.getClass();
        try {
            Context context = g60Var.a;
            context.startActivity(Intent.createChooser(intentA, context.getString(R$string.share_link_chooser_title)));
        } catch (ActivityNotFoundException unused) {
            Log.e("cr_DownloadHome", "Cannot find activity for sharing");
        } catch (Exception e) {
            Log.e("cr_DownloadHome", "Cannot start activity for sharing, exception: " + e);
        }
    }

    @Override // am.a
    public final void onCancel() {
        View view = (View) this.e;
        b bVar = (b) this.f;
        b.a aVar = (b.a) this.g;
        j.b bVar2 = (j.b) this.h;
        i21.e(bVar, "this$0");
        i21.e(aVar, "$animationInfo");
        i21.e(bVar2, "$operation");
        view.clearAnimation();
        bVar.a.endViewTransition(view);
        aVar.a();
    }
}
