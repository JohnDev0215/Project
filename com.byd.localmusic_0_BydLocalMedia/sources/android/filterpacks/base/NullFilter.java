package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class NullFilter extends Filter {
    public NullFilter(String str) {
        super(str);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addInputPort(TypedValues.Attributes.S_FRAME);
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        pullInput(TypedValues.Attributes.S_FRAME);
    }
}
