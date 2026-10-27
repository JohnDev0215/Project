package spi;

import android.content.res.Configuration;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class lc implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ lc(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((FragmentActivity) this.b).lambda$init$1((Configuration) obj);
                break;
            default:
                ((FragmentManager) this.b).lambda$new$2((MultiWindowModeChangedInfo) obj);
                break;
        }
    }
}
