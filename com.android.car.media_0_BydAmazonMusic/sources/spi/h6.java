package spi;

import android.car.utils.Logs;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.os.RemoteException;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.byd.amazon.music.param.PlayState;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class h6 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ h6(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        switch (this.a) {
            case 0:
                ((LocalListenerHelper) this.b).lambda$asyncNotifyListener$1((Consumer) this.c, (LocalListenerHelper.ErrorPolicy) this.d);
                break;
            case 1:
                p7 p7Var = (p7) this.b;
                AtomicInteger atomicInteger = (AtomicInteger) this.c;
                StringBuilder sb = (StringBuilder) this.d;
                StringBuilder sb2 = new StringBuilder("getWorkingState: ");
                PlaybackViewModel.e value = p7Var.c().j.getValue();
                if (value == null) {
                    sb2.append(" playbackStateWrapper is NULL");
                    i = -1;
                } else if (value.c() == 7 && value.a() == 3) {
                    sb2.append(" RESULT_ERROR_NOT_LOGE_IN");
                    i = 1001;
                } else {
                    i = 0;
                }
                sb2.append(" state= ");
                sb2.append(i);
                int i2 = i == 1001 ? i : 0;
                sb2.append(" tName= ");
                sb2.append(Thread.currentThread().getName());
                Logs.d("MusicVoiceControlImpl", sb2.toString());
                atomicInteger.set(i2);
                p7.b.countDown();
                sb.append(" state=");
                sb.append(atomicInteger.get());
                break;
            default:
                p7 p7Var2 = (p7) this.b;
                String str = (String) this.c;
                r4 r4Var = (r4) this.d;
                Objects.requireNonNull(p7Var2);
                try {
                    PlaybackViewModel.d value2 = p7Var2.c().i.getValue();
                    if (value2 != null) {
                        if (value2.a != null) {
                            Logs.d("PlaybackViewModel", "invoke  playItemId = " + str);
                            value2.a.getTransportControls().playFromMediaId(str, null);
                        }
                        if (r4Var != null) {
                            Logs.d("MusicVoiceControlImpl", "playById() callback");
                            r4Var.c(PlayState.STATE_PLAYING);
                        }
                    }
                } catch (RemoteException e) {
                    StringBuilder sbO = c.o("playById ", str, " onResult: error: ");
                    sbO.append(e.getMessage());
                    Logs.e("MusicVoiceControlImpl", sbO.toString());
                    return;
                }
                break;
        }
    }
}
