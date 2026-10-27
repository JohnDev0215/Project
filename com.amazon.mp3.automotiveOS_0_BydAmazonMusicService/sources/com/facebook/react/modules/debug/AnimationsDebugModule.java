package com.facebook.react.modules.debug;

import android.widget.Toast;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeAnimationsDebugModuleSpec;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = AnimationsDebugModule.NAME)
public class AnimationsDebugModule extends NativeAnimationsDebugModuleSpec {
    protected static final String NAME = "AnimationsDebugModule";

    @Nullable
    private final DeveloperSettings mCatalystSettings;

    @Nullable
    private FpsDebugFrameCallback mFrameCallback;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public AnimationsDebugModule(ReactApplicationContext reactApplicationContext, DeveloperSettings developerSettings) {
        super(reactApplicationContext);
        this.mCatalystSettings = developerSettings;
    }

    @Override // com.facebook.fbreact.specs.NativeAnimationsDebugModuleSpec
    public void startRecordingFps() {
        DeveloperSettings developerSettings = this.mCatalystSettings;
        if (developerSettings == null || !developerSettings.isAnimationFpsDebugEnabled()) {
            return;
        }
        if (this.mFrameCallback != null) {
            throw new JSApplicationCausedNativeException("Already recording FPS!");
        }
        FpsDebugFrameCallback fpsDebugFrameCallback = new FpsDebugFrameCallback(getReactApplicationContext());
        this.mFrameCallback = fpsDebugFrameCallback;
        fpsDebugFrameCallback.startAndRecordFpsAtEachFrame();
    }

    @Override // com.facebook.fbreact.specs.NativeAnimationsDebugModuleSpec
    public void stopRecordingFps(double d) {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback == null) {
            return;
        }
        fpsDebugFrameCallback.stop();
        FpsDebugFrameCallback.FpsInfo fpsInfo = this.mFrameCallback.getFpsInfo((long) d);
        if (fpsInfo == null) {
            Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
        } else {
            Locale locale = Locale.US;
            String str = String.format(locale, "FPS: %.2f, %d frames (%d expected)", Double.valueOf(fpsInfo.fps), Integer.valueOf(fpsInfo.totalFrames), Integer.valueOf(fpsInfo.totalExpectedFrames)) + "\n" + String.format(locale, "JS FPS: %.2f, %d frames (%d expected)", Double.valueOf(fpsInfo.jsFps), Integer.valueOf(fpsInfo.totalJsFrames), Integer.valueOf(fpsInfo.totalExpectedFrames)) + "\nTotal Time MS: " + String.format(locale, "%d", Integer.valueOf(fpsInfo.totalTimeMs));
            FLog.d("ReactNative", str);
            Toast.makeText(getReactApplicationContext(), str, 1).show();
        }
        this.mFrameCallback = null;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback != null) {
            fpsDebugFrameCallback.stop();
            this.mFrameCallback = null;
        }
    }
}
