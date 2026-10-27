package android.filterpacks.performance;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.format.ObjectFormat;
import android.os.SystemClock;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class ThroughputFilter extends Filter {
    private long mLastTime;
    private FrameFormat mOutputFormat;

    @GenerateFieldPort(hasDefault = true, name = TypedValues.CycleType.S_WAVE_PERIOD)
    private int mPeriod;
    private int mPeriodFrameCount;
    private int mTotalFrameCount;

    @Override // android.filterfw.core.Filter
    public FrameFormat getOutputFormat(String str, FrameFormat frameFormat) {
        return frameFormat;
    }

    public ThroughputFilter(String str) {
        super(str);
        this.mPeriod = 5;
        this.mLastTime = 0L;
        this.mTotalFrameCount = 0;
        this.mPeriodFrameCount = 0;
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addInputPort(TypedValues.AttributesType.S_FRAME);
        this.mOutputFormat = ObjectFormat.fromClass(Throughput.class, 1);
        addOutputBasedOnInput(TypedValues.AttributesType.S_FRAME, TypedValues.AttributesType.S_FRAME);
        addOutputPort("throughput", this.mOutputFormat);
    }

    @Override // android.filterfw.core.Filter
    public void open(FilterContext filterContext) {
        this.mTotalFrameCount = 0;
        this.mPeriodFrameCount = 0;
        this.mLastTime = 0L;
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Frame framePullInput = pullInput(TypedValues.AttributesType.S_FRAME);
        pushOutput(TypedValues.AttributesType.S_FRAME, framePullInput);
        this.mTotalFrameCount++;
        this.mPeriodFrameCount++;
        if (this.mLastTime == 0) {
            this.mLastTime = SystemClock.elapsedRealtime();
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.mLastTime >= this.mPeriod * 1000) {
            FrameFormat format = framePullInput.getFormat();
            Throughput throughput = new Throughput(this.mTotalFrameCount, this.mPeriodFrameCount, this.mPeriod, format.getWidth() * format.getHeight());
            Frame frameNewFrame = filterContext.getFrameManager().newFrame(this.mOutputFormat);
            frameNewFrame.setObjectValue(throughput);
            pushOutput("throughput", frameNewFrame);
            this.mLastTime = jElapsedRealtime;
            this.mPeriodFrameCount = 0;
        }
    }
}
