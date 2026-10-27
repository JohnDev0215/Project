package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.provider.Telephony;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class FrameSource extends Filter {

    @GenerateFinalPort(name = Telephony.CellBroadcasts.MESSAGE_FORMAT)
    private FrameFormat mFormat;

    @GenerateFieldPort(hasDefault = true, name = TypedValues.AttributesType.S_FRAME)
    private Frame mFrame;

    @GenerateFieldPort(hasDefault = true, name = "repeatFrame")
    private boolean mRepeatFrame;

    public FrameSource(String str) {
        super(str);
        this.mFrame = null;
        this.mRepeatFrame = false;
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addOutputPort(TypedValues.AttributesType.S_FRAME, this.mFormat);
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Frame frame = this.mFrame;
        if (frame != null) {
            pushOutput(TypedValues.AttributesType.S_FRAME, frame);
        }
        if (this.mRepeatFrame) {
            return;
        }
        closeOutputPort(TypedValues.AttributesType.S_FRAME);
    }
}
