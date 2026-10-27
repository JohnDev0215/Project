package l3;

import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.service.media.MediaBrowserService;
import java.util.ArrayList;

/* JADX INFO: renamed from: l3.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1758n extends MediaBrowserService {
    public final /* synthetic */ C1759o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1759o f18587m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1759o f18588n;

    public C1758n(C1759o c1759o, Context context) {
        this.f18588n = c1759o;
        this.f18587m = c1759o;
        this.l = c1759o;
        attachBaseContext(context);
    }

    @Override // android.service.media.MediaBrowserService
    public final MediaBrowserService.BrowserRoot onGetRoot(String str, int i7, Bundle bundle) {
        Bundle bundle2;
        C1755k c1755k;
        P.o(bundle);
        Bundle bundle3 = bundle == null ? null : new Bundle(bundle);
        C1759o c1759o = this.l;
        AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1759o.d;
        int i10 = -1;
        if (bundle3 == null || bundle3.getInt("extra_client_version", 0) == 0) {
            bundle2 = null;
        } else {
            bundle3.remove("extra_client_version");
            c1759o.c = new Messenger(abstractServiceC1765v.f18614r);
            bundle2 = new Bundle();
            bundle2.putInt("extra_service_version", 2);
            bundle2.putBinder("extra_messenger", ((Messenger) c1759o.c).getBinder());
            O o = abstractServiceC1765v.f18615s;
            if (o != null) {
                InterfaceC1750f interfaceC1750fA = o.a();
                bundle2.putBinder("extra_session_binder", interfaceC1750fA == null ? null : interfaceC1750fA.asBinder());
            } else {
                ((ArrayList) c1759o.f18585a).add(bundle2);
            }
            i10 = bundle3.getInt("extra_calling_pid", -1);
            bundle3.remove("extra_calling_pid");
        }
        C1756l c1756l = new C1756l((AbstractServiceC1765v) c1759o.d, str, i10, i7, null);
        abstractServiceC1765v.f18613q = c1756l;
        C1755k c1755kA = abstractServiceC1765v.a(bundle3);
        abstractServiceC1765v.f18613q = null;
        if (c1755kA == null) {
            c1755k = null;
        } else {
            if (((Messenger) c1759o.c) != null) {
                abstractServiceC1765v.o.add(c1756l);
            }
            Bundle bundle4 = c1755kA.f18579a;
            if (bundle2 == null) {
                bundle2 = bundle4;
            } else if (bundle4 != null) {
                bundle2.putAll(bundle4);
            }
            c1755k = new C1755k(bundle2);
        }
        if (c1755k == null) {
            return null;
        }
        return new MediaBrowserService.BrowserRoot("androidx.media3.session.MediaLibraryService", c1755k.f18579a);
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadChildren(String str, MediaBrowserService.Result result) {
        e7.f fVar = new e7.f(result, 29);
        C1759o c1759o = this.l;
        c1759o.getClass();
        AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1759o.d;
        abstractServiceC1765v.f18613q = abstractServiceC1765v.f18611n;
        fVar.n(null);
        abstractServiceC1765v.f18613q = null;
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadItem(String str, MediaBrowserService.Result result) {
        e7.f fVar = new e7.f(result, 29);
        AbstractServiceC1765v abstractServiceC1765v = this.f18587m.f18589e;
        abstractServiceC1765v.f18613q = abstractServiceC1765v.f18611n;
        fVar.n(null);
        abstractServiceC1765v.f18613q = null;
    }

    @Override // android.service.media.MediaBrowserService
    public final void onLoadChildren(String str, MediaBrowserService.Result result, Bundle bundle) {
        P.o(bundle);
        C1759o c1759o = this.f18588n;
        AbstractServiceC1765v abstractServiceC1765v = c1759o.f18590f;
        C1756l c1756l = abstractServiceC1765v.f18611n;
        e7.f fVar = new e7.f(result, 29);
        abstractServiceC1765v.f18613q = c1756l;
        fVar.n(null);
        abstractServiceC1765v.f18613q = null;
        c1759o.f18590f.f18613q = null;
    }
}
