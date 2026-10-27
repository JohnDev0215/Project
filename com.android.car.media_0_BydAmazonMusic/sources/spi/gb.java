package spi;

import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import com.android.car.ui.uxr.DrawableStateButton;
import com.android.car.ui.uxr.DrawableStateConstraintLayout;
import com.android.car.ui.uxr.DrawableStateFrameLayout;
import com.android.car.ui.uxr.DrawableStateImageView;
import com.android.car.ui.uxr.DrawableStateLinearLayout;
import com.android.car.ui.uxr.DrawableStateRelativeLayout;
import com.android.car.ui.uxr.DrawableStateSeekBar;
import com.android.car.ui.uxr.DrawableStateSwitch;
import com.android.car.ui.uxr.DrawableStateTextView;
import com.android.car.ui.uxr.DrawableStateToggleButton;
import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gb implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ gb(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return ((ToolbarControllerAdapterV1) this.b).lambda$setMenuItems$4((com.android.car.ui.toolbar.b) obj);
            case 1:
                return DrawableStateButton.a((DrawableStateButton) this.b, (Integer) obj);
            case 2:
                return DrawableStateConstraintLayout.e((DrawableStateConstraintLayout) this.b, (Integer) obj);
            case 3:
                return DrawableStateFrameLayout.a((DrawableStateFrameLayout) this.b, (Integer) obj);
            case 4:
                return DrawableStateImageView.a((DrawableStateImageView) this.b, (Integer) obj);
            case 5:
                return DrawableStateLinearLayout.a((DrawableStateLinearLayout) this.b, (Integer) obj);
            case 6:
                return DrawableStateRelativeLayout.a((DrawableStateRelativeLayout) this.b, (Integer) obj);
            case 7:
                return DrawableStateSeekBar.a((DrawableStateSeekBar) this.b, (Integer) obj);
            case 8:
                return DrawableStateSwitch.a((DrawableStateSwitch) this.b, (Integer) obj);
            case 9:
                return DrawableStateTextView.a((DrawableStateTextView) this.b, (Integer) obj);
            default:
                return DrawableStateToggleButton.a((DrawableStateToggleButton) this.b, (Integer) obj);
        }
    }
}
