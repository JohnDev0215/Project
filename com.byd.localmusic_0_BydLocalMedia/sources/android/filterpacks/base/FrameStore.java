package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.GenerateFieldPort;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class FrameStore extends Filter {

    @GenerateFieldPort(name = "key")
    private String mKey;

    public FrameStore(String str) {
        super(str);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addInputPort(TypedValues.Attributes.S_FRAME);
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        filterContext.storeFrame(this.mKey, pullInput(TypedValues.Attributes.S_FRAME));
    }
}
