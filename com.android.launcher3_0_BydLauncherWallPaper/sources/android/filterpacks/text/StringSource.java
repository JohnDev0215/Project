package android.filterpacks.text;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ObjectFormat;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class StringSource extends Filter {
    private FrameFormat mOutputFormat;

    @GenerateFieldPort(name = "stringValue")
    private String mString;

    public StringSource(String str) {
        super(str);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        MutableFrameFormat mutableFrameFormatFromClass = ObjectFormat.fromClass(String.class, 1);
        this.mOutputFormat = mutableFrameFormatFromClass;
        addOutputPort(TypedValues.Custom.S_STRING, mutableFrameFormatFromClass);
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Frame frameNewFrame = filterContext.getFrameManager().newFrame(this.mOutputFormat);
        frameNewFrame.setObjectValue(this.mString);
        frameNewFrame.setTimestamp(-1L);
        pushOutput(TypedValues.Custom.S_STRING, frameNewFrame);
        closeOutputPort(TypedValues.Custom.S_STRING);
    }
}
