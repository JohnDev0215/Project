package spi;

import android.content.ComponentName;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.ui.preference.ListPreferenceFragment;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class g6 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ g6(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((LocalListenerHelper) this.b).lambda$asyncNotifyListener$0((Consumer) this.c);
                break;
            case 1:
                ((ViewTransition) this.b).lambda$applyTransition$0((View[]) this.c);
                break;
            case 2:
                MediaSourceViewModel mediaSourceViewModel = (MediaSourceViewModel) this.b;
                ComponentName componentName = (ComponentName) this.c;
                MediaSourceViewModel.a aVar = (MediaSourceViewModel.a) mediaSourceViewModel.h;
                Objects.requireNonNull(aVar);
                mediaSourceViewModel.b(componentName == null ? null : b7.a(aVar.a, componentName));
                break;
            default:
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = ((CarUiRecyclerView) this.c).findViewHolderForAdapterPosition(((ListPreferenceFragment) this.b).c);
                if (viewHolderFindViewHolderForAdapterPosition != null) {
                    viewHolderFindViewHolderForAdapterPosition.itemView.requestFocus();
                }
                break;
        }
    }
}
