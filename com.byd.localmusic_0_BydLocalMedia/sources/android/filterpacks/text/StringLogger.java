package android.filterpacks.text;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.format.ObjectFormat;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class StringLogger extends Filter {
    public StringLogger(String str) {
        super(str);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addMaskedInputPort(TypedValues.Custom.S_STRING, ObjectFormat.fromClass(Object.class, 1));
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Log.i("StringLogger", pullInput(TypedValues.Custom.S_STRING).getObjectValue().toString());
    }
}
