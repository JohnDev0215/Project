package androidx.mediarouter.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.h;

/* JADX INFO: compiled from: MediaRouteProviderService.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends h.c {
    public final /* synthetic */ Messenger a;
    public final /* synthetic */ int b;
    public final /* synthetic */ MediaRouteProviderService.c c;

    public f(MediaRouteProviderService.c cVar, MediaRouteProviderService.c.b bVar, int i, Intent intent, Messenger messenger, int i2) {
        this.c = cVar;
        this.a = messenger;
        this.b = i2;
    }

    @Override // androidx.mediarouter.media.h.c
    public final void a(String str, Bundle bundle) {
        int i = MediaRouteProviderService.j;
        if (this.c.d(this.a) >= 0) {
            if (str == null) {
                MediaRouteProviderService.e(this.a, 4, this.b, 0, bundle, null);
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("error", str);
            MediaRouteProviderService.e(this.a, 4, this.b, 0, bundle, bundle2);
        }
    }

    @Override // androidx.mediarouter.media.h.c
    public final void b(Bundle bundle) {
        int i = MediaRouteProviderService.j;
        if (this.c.d(this.a) >= 0) {
            MediaRouteProviderService.e(this.a, 3, this.b, 0, bundle, null);
        }
    }
}
