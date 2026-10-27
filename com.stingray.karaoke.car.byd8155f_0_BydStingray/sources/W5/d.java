package W5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import n3.C1930E;
import n3.InterfaceC1949n;
import n3.y;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC1949n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f9816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ StingrayKaraokeTabScreen f9817b;
    public final /* synthetic */ boolean c;

    public d(e eVar, StingrayKaraokeTabScreen stingrayKaraokeTabScreen, boolean z6) {
        this.f9816a = eVar;
        this.f9817b = stingrayKaraokeTabScreen;
        this.c = z6;
    }

    @Override // n3.InterfaceC1949n
    public final void a(C1930E c1930e, y yVar, Bundle bundle) {
        A7.m.f(c1930e, "controller");
        A7.m.f(yVar, "destination");
        final e eVar = this.f9816a;
        C1930E c1930e2 = (C1930E) eVar.d.get(NavigationType.MAIN);
        if (c1930e2 != null) {
            c1930e2.f19812q.remove(this);
        }
        Handler handler = new Handler(Looper.getMainLooper());
        final StingrayKaraokeTabScreen stingrayKaraokeTabScreen = this.f9817b;
        final boolean z6 = this.c;
        handler.postDelayed(new Runnable() { // from class: W5.c
            @Override // java.lang.Runnable
            public final void run() {
                eVar.h(stingrayKaraokeTabScreen, z6);
            }
        }, 100L);
    }
}
