package spi;

import android.car.XCarRoute;
import android.car.media.IXCarMediaControl;
import android.car.utils.Logs;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.byd.BydBaseCarMediaReceiver;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Timer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m0 implements Runnable {
    public static final /* synthetic */ m0 b = new m0(0);
    public static final /* synthetic */ m0 c = new m0(1);
    public static final /* synthetic */ m0 d = new m0(2);
    public static final /* synthetic */ m0 e = new m0(3);
    public static final /* synthetic */ m0 f = new m0(4);
    public static final /* synthetic */ m0 g = new m0(5);
    public final /* synthetic */ int a;

    public /* synthetic */ m0(int i) {
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                int i = BydBaseCarMediaReceiver.b;
                IXCarMediaControl iXCarMediaControl = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
                if (iXCarMediaControl != null) {
                    iXCarMediaControl.play();
                    return;
                }
                return;
            case 1:
                int i2 = BydBaseCarMediaReceiver.b;
                IXCarMediaControl iXCarMediaControl2 = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
                if (iXCarMediaControl2 != null) {
                    iXCarMediaControl2.previous();
                    return;
                }
                return;
            case 2:
                int i3 = BydBaseCarMediaReceiver.b;
                IXCarMediaControl iXCarMediaControl3 = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
                if (iXCarMediaControl3 != null) {
                    iXCarMediaControl3.next();
                    return;
                }
                return;
            case 3:
                p7.b.countDown();
                return;
            case 4:
                p7.b.countDown();
                return;
            default:
                y7 y7Var = z7.a;
                synchronized (z7.class) {
                    if (ThreadUtils.isMainThread()) {
                        if (z7.d) {
                            return;
                        }
                        z7.d = true;
                        z7.b(false);
                        Logs.i("NetworkUtils", " NetworkUtils:startDetectNetwork:");
                        z7.a = new y7();
                        Timer timer = new Timer();
                        z7.b = timer;
                        y7 y7Var2 = z7.a;
                        if (y7Var2 != null) {
                            try {
                                timer.schedule(y7Var2, 0L, 12000L);
                            } catch (Throwable unused) {
                                Logs.e("NetworkUtils", " NetworkUtils:startDetectNetwork:error:");
                            }
                            break;
                        }
                        return;
                    }
                    return;
                }
        }
    }
}
