package spi;

import android.view.View;
import com.android.car.apps.common.ControlBar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class z2 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ ControlBar b;

    public /* synthetic */ z2(ControlBar controlBar, int i) {
        this.a = i;
        this.b = controlBar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                ControlBar controlBar = this.b;
                int i = ControlBar.q;
                controlBar.h();
                break;
            default:
                ControlBar controlBar2 = this.b;
                int i2 = ControlBar.q;
                controlBar2.h();
                break;
        }
    }
}
