package android.view;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class InputEventCompatProcessor {
    protected Context mContext;
    private List<InputEvent> mProcessedEvents = new ArrayList();
    protected int mTargetSdkVersion;

    public InputEvent processInputEventBeforeFinish(InputEvent inputEvent) {
        return inputEvent;
    }

    public InputEventCompatProcessor(Context context) {
        this.mContext = context;
        this.mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
    }

    public List<InputEvent> processInputEventForCompatibility(InputEvent inputEvent) {
        if (this.mTargetSdkVersion >= 23 || !(inputEvent instanceof MotionEvent)) {
            return null;
        }
        this.mProcessedEvents.clear();
        MotionEvent motionEvent = (MotionEvent) inputEvent;
        int buttonState = motionEvent.getButtonState();
        int i = (buttonState & 96) >> 4;
        if (i != 0) {
            motionEvent.setButtonState(buttonState | i);
        }
        this.mProcessedEvents.add(motionEvent);
        return this.mProcessedEvents;
    }
}
