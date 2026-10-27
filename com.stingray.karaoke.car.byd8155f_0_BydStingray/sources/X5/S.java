package X5;

import V8.InterfaceC0818g;
import androidx.media3.exoplayer.ExoPlayer;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.AudioResponse;
import com.stingray.kca.model.VideoBackground;
import com.stingray.kca.model.VideoBackgroundResource;
import java.util.Collections;
import java.util.List;
import n2.C1923x;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public final class S implements InterfaceC0818g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10198m;

    public /* synthetic */ S(D0 d02, int i7) {
        this.l = i7;
        this.f10198m = d02;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(com.stingray.karaoke.car.player.PlayerStatus r10, q7.InterfaceC2189c r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X5.S.a(com.stingray.karaoke.car.player.PlayerStatus, q7.c):java.lang.Object");
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        VideoBackground videoBackground;
        List list;
        VideoBackgroundResource videoBackgroundResource;
        switch (this.l) {
            case 0:
                return a((PlayerStatus) obj, interfaceC2189c);
            case 1:
                AudioResponse audioResponse = (AudioResponse) obj;
                Y5.d dVar = this.f10198m.f10154v;
                String str = (audioResponse == null || (videoBackground = audioResponse.c) == null || (list = videoBackground.f13233b) == null || (videoBackgroundResource = (VideoBackgroundResource) AbstractC1998m.a0(list)) == null) ? null : videoBackgroundResource.f13236a;
                dVar.f10789g.l(str);
                A7.m.f("updateVideoBackground: " + str, "text");
                V8.f0 f0Var = dVar.f10787e;
                ((C1923x) ((ExoPlayer) f0Var.getValue())).stop();
                if (str != null) {
                    e2.F fC = e2.F.c(str);
                    E0.X x8 = dVar.f10786b;
                    if (x8 == null) {
                        A7.m.j("cacheDataSourceFactory");
                        throw null;
                    }
                    u2.W wC = new u2.V(x8).c(fC);
                    C1923x c1923x = (C1923x) ((ExoPlayer) f0Var.getValue());
                    c1923x.A1();
                    List listSingletonList = Collections.singletonList(wC);
                    c1923x.A1();
                    c1923x.A1();
                    c1923x.s1(listSingletonList, -1, -9223372036854775807L, true);
                    ((C1923x) ((ExoPlayer) f0Var.getValue())).c();
                }
                return m7.q.f19267a;
            case 2:
                ((Boolean) obj).booleanValue();
                this.f10198m.f10160y.a();
                return m7.q.f19267a;
            default:
                this.f10198m.f10103C.c = new Long(((Number) obj).longValue());
                return m7.q.f19267a;
        }
    }
}
