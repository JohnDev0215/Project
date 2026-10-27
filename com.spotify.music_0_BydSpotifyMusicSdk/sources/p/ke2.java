package p;

import android.os.Handler;
import android.os.Looper;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class ke2 implements ul0 {
    public final /* synthetic */ int a;

    public /* synthetic */ ke2(int i) {
        this.a = i;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        switch (i) {
            case 0:
                return new je2();
            case 1:
                return new sd1();
            case 2:
                return new t0();
            case 3:
                return b32.a;
            case 4:
                Optional optionalA = Optional.a();
                vr2.b(optionalA);
                return optionalA;
            case 5:
                return new k10(v03.C("audio/ad", "audio/episode", "audio/episode+track", "audio/interruption", "audio/local", "audio/track"));
            case 6:
                return n0.a;
            case 7:
                f70 f70Var = ha0.c;
                eh2 eh2Var = new eh2(null);
                f70Var.getClass();
                return q30.b(kotlin.coroutines.a.a(f70Var, eh2Var));
            case 8:
                return new Handler(Looper.getMainLooper());
            case 9:
                return new f61();
            case 10:
                return bt1.a;
            case 11:
                int i2 = Flowable.c;
                io.reactivex.rxjava3.internal.operators.flowable.l lVar = io.reactivex.rxjava3.internal.operators.flowable.l.e;
                v03.i(lVar, "empty()");
                return lVar;
            case 12:
                switch (i) {
                    case 12:
                        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.b;
                        vr2.b(scheduler);
                        return scheduler;
                    default:
                        return io.reactivex.rxjava3.android.schedulers.c.a();
                }
            case 13:
                switch (i) {
                    case 12:
                        Scheduler scheduler2 = io.reactivex.rxjava3.schedulers.e.b;
                        vr2.b(scheduler2);
                        return scheduler2;
                    default:
                        return io.reactivex.rxjava3.android.schedulers.c.a();
                }
            case 14:
                return c42.a;
            case 15:
                return new u6();
            case 16:
                return on1.a;
            case 17:
                return new qp2();
            case 18:
                return new yr2();
            default:
                return new aw2();
        }
    }
}
