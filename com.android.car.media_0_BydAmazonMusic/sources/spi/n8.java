package spi;

import com.android.car.media.PlaybackFragment;
import com.android.car.ui.toolbar.b;
import spi.uc;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class n8 implements uc.c, b.e {
    public final /* synthetic */ PlaybackFragment a;

    public /* synthetic */ n8(PlaybackFragment playbackFragment) {
        this.a = playbackFragment;
    }

    @Override // com.android.car.ui.toolbar.b.e
    public final void b(com.android.car.ui.toolbar.b bVar) {
        PlaybackFragment playbackFragment = this.a;
        int i = PlaybackFragment.J;
        playbackFragment.g(false);
    }
}
