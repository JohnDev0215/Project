package H5;

import B5.RunnableC0079c;
import N1.S;
import b7.RunnableC1004e;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.scoring.MicrophoneCommand;
import io.sentry.E;
import io.sentry.android.core.L;
import io.sentry.transport.m;
import java.util.TimerTask;
import k7.C1707a;
import l3.C1764u;
import n6.AbstractC1961B;
import n6.C1960A;
import n6.C1963D;
import n6.y;

/* JADX INFO: loaded from: classes.dex */
public final class f extends TimerTask {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2837m;

    public /* synthetic */ f(Object obj, int i7) {
        this.l = i7;
        this.f2837m = obj;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ((CompanionManager) this.f2837m).receive("status");
                break;
            case 1:
                C1707a.a((RunnableC1004e) this.f2837m);
                break;
            case 2:
                C1707a.a(new RunnableC0079c(this, 9));
                break;
            case 3:
                L l = (L) this.f2837m;
                boolean z6 = l.f16068s;
                E e10 = l.f16067r;
                if (z6) {
                    e10.o();
                }
                e10.q().getReplayController().stop();
                break;
            case 4:
                ((m) this.f2837m).i();
                break;
            default:
                C1963D c1963d = (C1963D) this.f2837m;
                AbstractC1961B abstractC1961B = (AbstractC1961B) c1963d.f19991b.a();
                if (!A7.m.a(c1963d.f19993f, abstractC1961B)) {
                    c1963d.f19993f = abstractC1961B;
                    boolean z10 = abstractC1961B instanceof y;
                    C1764u c1764u = c1963d.f19990a;
                    if (!z10) {
                        if (abstractC1961B instanceof C1960A) {
                            if (c1963d.f19994g) {
                                c1764u.U(MicrophoneCommand.StopPassthroughLiveVoice);
                                c1963d.f19994g = false;
                            }
                            int i7 = ((C1960A) abstractC1961B).c;
                            ((S) c1764u.f18608m).getClass();
                        }
                    } else if (!c1963d.f19994g) {
                        c1963d.f19994g = true;
                        c1764u.U(MicrophoneCommand.StartPassthroughLiveVoice);
                    }
                }
                break;
        }
    }
}
