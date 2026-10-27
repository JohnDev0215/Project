package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class RetargetFilter extends Filter {
    private MutableFrameFormat mOutputFormat;
    private int mTarget;

    @GenerateFinalPort(hasDefault = false, name = TypedValues.AttributesType.S_TARGET)
    private String mTargetString;

    public RetargetFilter(String str) {
        super(str);
        this.mTarget = -1;
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        this.mTarget = FrameFormat.readTargetString(this.mTargetString);
        addInputPort(TypedValues.AttributesType.S_FRAME);
        addOutputBasedOnInput(TypedValues.AttributesType.S_FRAME, TypedValues.AttributesType.S_FRAME);
    }

    @Override // android.filterfw.core.Filter
    public FrameFormat getOutputFormat(String str, FrameFormat frameFormat) {
        MutableFrameFormat mutableFrameFormatMutableCopy = frameFormat.mutableCopy();
        mutableFrameFormatMutableCopy.setTarget(this.mTarget);
        return mutableFrameFormatMutableCopy;
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Frame frameDuplicateFrameToTarget = filterContext.getFrameManager().duplicateFrameToTarget(pullInput(TypedValues.AttributesType.S_FRAME), this.mTarget);
        pushOutput(TypedValues.AttributesType.S_FRAME, frameDuplicateFrameToTarget);
        frameDuplicateFrameToTarget.release();
    }
}
