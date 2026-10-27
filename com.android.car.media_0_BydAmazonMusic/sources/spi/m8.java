package spi;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.android.car.apps.common.BackgroundImageView;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.common.MinimizedPlaybackControlBar;
import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m8 implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ m8(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((PlaybackFragment) this.b).d.setBackgroundDrawable((Drawable) obj);
                break;
            case 1:
                int i = MinimizedPlaybackControlBar.n;
                ((BackgroundImageView) this.b).setBackgroundDrawable((Drawable) obj);
                break;
            case 2:
                ((com.android.car.ui.toolbar.f) this.b).g((TextView) obj);
                break;
            default:
                ((ToolbarControllerAdapterV1) this.b).lambda$new$1((String) obj);
                break;
        }
    }
}
