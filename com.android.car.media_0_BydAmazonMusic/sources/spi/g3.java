package spi;

import android.graphics.drawable.Drawable;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class g3 implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ g3(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                com.android.car.ui.toolbar.a aVar = (com.android.car.ui.toolbar.a) this.b;
                Runnable runnable = (Runnable) this.c;
                CharSequence charSequence = (CharSequence) obj;
                Objects.requireNonNull(aVar);
                aVar.c = charSequence == null ? "" : charSequence.toString();
                if (runnable != null) {
                    runnable.run();
                }
                break;
            case 1:
                com.android.car.ui.toolbar.a aVar2 = (com.android.car.ui.toolbar.a) this.b;
                Runnable runnable2 = (Runnable) this.c;
                aVar2.d = (Drawable) obj;
                if (runnable2 != null) {
                    runnable2.run();
                }
                break;
            default:
                ((ToolbarControllerAdapterV1) this.b).lambda$addTab$3((TabLayout.a) this.c, (com.android.car.ui.toolbar.g) obj);
                break;
        }
    }
}
