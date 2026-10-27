package spi;

import android.car.XCarRoute;
import android.car.media.IXCarMediaService;
import android.car.media.IXCarMediaStateListener;
import android.car.utils.Logs;
import android.content.Context;
import androidx.lifecycle.Observer;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.blankj.utilcode.util.Utils;
import java.util.Objects;

/* JADX INFO: compiled from: XCarMediaStateListener.java */
/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_MEDIA_STATE_LISTENER)
public final class dd implements IXCarMediaStateListener {
    public transient IXCarMediaService a;
    public final transient Observer<u8> b;
    public final transient Observer<PlaybackViewModel.e> c;
    public transient PlaybackViewModel d;

    public dd() {
        final int i = 0;
        this.b = new Observer(this) { // from class: spi.cd
            public final /* synthetic */ dd b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i) {
                    case 0:
                        u8 u8Var = (u8) obj;
                        IXCarMediaService iXCarMediaService = this.b.a;
                        if (iXCarMediaService != null) {
                            iXCarMediaService.onProgressChanged(u8Var == null ? 0L : u8Var.a, u8Var != null ? u8Var.b : 0L);
                        }
                        break;
                    default:
                        dd ddVar = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        Objects.requireNonNull(ddVar);
                        Logs.d("XCarMediaStateListener", "onMediaPlayStateChanged(" + eVar + ")," + ddVar.a);
                        IXCarMediaService iXCarMediaService2 = ddVar.a;
                        if (iXCarMediaService2 != null) {
                            if (eVar == null) {
                                iXCarMediaService2.onPlayStateChanged(null, null);
                            } else {
                                try {
                                    iXCarMediaService2.onPlayStateChanged(eVar.b, eVar.c);
                                } catch (Exception unused) {
                                    Logs.e("XCarMediaStateListener", "error: onMediaPlayStateChanged");
                                    return;
                                }
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        this.c = new Observer(this) { // from class: spi.cd
            public final /* synthetic */ dd b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i2) {
                    case 0:
                        u8 u8Var = (u8) obj;
                        IXCarMediaService iXCarMediaService = this.b.a;
                        if (iXCarMediaService != null) {
                            iXCarMediaService.onProgressChanged(u8Var == null ? 0L : u8Var.a, u8Var != null ? u8Var.b : 0L);
                        }
                        break;
                    default:
                        dd ddVar = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        Objects.requireNonNull(ddVar);
                        Logs.d("XCarMediaStateListener", "onMediaPlayStateChanged(" + eVar + ")," + ddVar.a);
                        IXCarMediaService iXCarMediaService2 = ddVar.a;
                        if (iXCarMediaService2 != null) {
                            if (eVar == null) {
                                iXCarMediaService2.onPlayStateChanged(null, null);
                            } else {
                                try {
                                    iXCarMediaService2.onPlayStateChanged(eVar.b, eVar.c);
                                } catch (Exception unused) {
                                    Logs.e("XCarMediaStateListener", "error: onMediaPlayStateChanged");
                                    return;
                                }
                            }
                            break;
                        }
                        break;
                }
            }
        };
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
        Logs.d("XCarMediaStateListener", "init");
        PlaybackViewModel playbackViewModelA = PlaybackViewModel.a(Utils.getApp(), 1);
        this.d = playbackViewModelA;
        if (playbackViewModelA != null) {
            playbackViewModelA.j.observeForever(this.c);
            this.d.l.observeForever(this.b);
        }
    }

    @Override // android.car.media.IXCarMediaStateListener
    public final void onCarDisconnected() {
        Logs.d("XCarMediaStateListener", "onCarDisconnected");
        PlaybackViewModel playbackViewModel = this.d;
        if (playbackViewModel != null) {
            playbackViewModel.j.removeObserver(this.c);
            this.d.l.removeObserver(this.b);
        }
    }

    @Override // android.car.media.IXCarMediaStateListener
    public final void setCarMediaService(IXCarMediaService iXCarMediaService) {
        this.a = iXCarMediaService;
    }
}
