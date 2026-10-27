package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.format.ImageFormat;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class GLTextureTarget extends Filter {

    @GenerateFieldPort(name = "texId")
    private int mTexId;

    public GLTextureTarget(String str) {
        super(str);
    }

    @Override // android.filterfw.core.Filter
    public void setupPorts() {
        addMaskedInputPort(TypedValues.Attributes.S_FRAME, ImageFormat.create(3));
    }

    @Override // android.filterfw.core.Filter
    public void process(FilterContext filterContext) {
        Frame framePullInput = pullInput(TypedValues.Attributes.S_FRAME);
        Frame frameNewBoundFrame = filterContext.getFrameManager().newBoundFrame(ImageFormat.create(framePullInput.getFormat().getWidth(), framePullInput.getFormat().getHeight(), 3, 3), 100, this.mTexId);
        frameNewBoundFrame.setDataFromFrame(framePullInput);
        frameNewBoundFrame.release();
    }
}
