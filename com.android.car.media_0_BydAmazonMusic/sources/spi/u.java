package spi;

import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import com.android.car.media.browse.BrowseAdapter;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class u implements Consumer {
    public static final /* synthetic */ u b = new u(0);
    public static final /* synthetic */ u c = new u(1);
    public final /* synthetic */ int a;

    public /* synthetic */ u(int i) {
        this.a = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((IServiceConnectionListener) obj).onServiceConnected(true);
                break;
            case 1:
                ((IServiceConnectionListener) obj).onServiceDisconnected(false);
                break;
            default:
                Objects.requireNonNull((BrowseAdapter.c) obj);
                break;
        }
    }
}
