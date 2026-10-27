package spi;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class qc implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ qc(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((FragmentManager) this.b).lambda$new$0((Configuration) obj);
                break;
            case 1:
                ((FragmentManager) this.b).lambda$new$3((PictureInPictureModeChangedInfo) obj);
                break;
            default:
                ((FragmentActivity) this.b).lambda$init$2((Intent) obj);
                break;
        }
    }
}
