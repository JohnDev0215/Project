package spi;

import android.graphics.drawable.Drawable;
import android.util.Size;
import com.android.car.apps.common.CrossfadeImageView;
import com.android.car.media.common.PlaybackFragment;
import com.android.car.ui.pluginsupport.PluginFactoryAdapterV1;
import com.android.car.ui.pluginsupport.PluginFactoryAdapterV2;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class r8 implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ r8(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                Size size = (Size) this.b;
                CrossfadeImageView crossfadeImageView = (CrossfadeImageView) this.c;
                Drawable drawable = (Drawable) obj;
                int i = PlaybackFragment.j;
                crossfadeImageView.b(drawable != null ? x.b(drawable, size) : null, true);
                break;
            case 1:
                qa qaVar = (qa) this.b;
                Consumer consumer = (Consumer) this.c;
                Objects.requireNonNull(qaVar);
                consumer.accept(qaVar);
                break;
            case 2:
                ((PluginFactoryAdapterV1) this.b).lambda$installBaseLayoutAround$2((z4) this.c, (a5) obj);
                break;
            default:
                ((PluginFactoryAdapterV2) this.b).lambda$installBaseLayoutAround$2((z4) this.c, (a5) obj);
                break;
        }
    }
}
