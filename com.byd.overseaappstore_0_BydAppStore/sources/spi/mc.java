package spi;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class mc implements SavedStateRegistry.SavedStateProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ mc(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public final Bundle saveState() {
        switch (this.a) {
            case 0:
                return ((FragmentActivity) this.b).lambda$init$0();
            default:
                return ((FragmentManager) this.b).lambda$attachController$4();
        }
    }
}
