package com.facebook.react.views.slider;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.SliderManagerDelegate;
import com.facebook.react.viewmanagers.SliderManagerInterface;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ReactSliderManager extends SimpleViewManager<ReactSlider> implements SliderManagerInterface<ReactSlider> {
    private static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER = new SeekBar.OnSeekBarChangeListener() { // from class: com.facebook.react.views.slider.ReactSliderManager.1
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) seekBar.getContext(), seekBar.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(new ReactSliderEvent(seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(i), z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) seekBar.getContext(), seekBar.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(new ReactSlidingCompleteEvent(UIManagerHelper.getSurfaceId(seekBar), seekBar.getId(), ((ReactSlider) seekBar).toRealProgress(seekBar.getProgress())));
            }
        }
    };
    public static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;
    private final ViewManagerDelegate<ReactSlider> mDelegate = new SliderManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(name = "disabled")
    public void setDisabled(ReactSlider reactSlider, boolean z) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "ImageSource", name = "maximumTrackImage")
    public void setMaximumTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "ImageSource", name = "minimumTrackImage")
    public void setMinimumTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "ImageSource", name = "thumbImage")
    public void setThumbImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "ImageSource", name = "trackImage")
    public void setTrackImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
    }

    static class ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
        private int mHeight;
        private boolean mMeasured;
        private int mWidth;

        private ReactSliderShadowNode() {
            initMeasureFunction();
        }

        private void initMeasureFunction() {
            setMeasureFunction(this);
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.mMeasured) {
                ReactSlider reactSlider = new ReactSlider(getThemedContext(), null, 16842875);
                reactSlider.disableStateListAnimatorIfNeeded();
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
                reactSlider.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                this.mWidth = reactSlider.getMeasuredWidth();
                this.mHeight = reactSlider.getMeasuredHeight();
                this.mMeasured = true;
            }
            return YogaMeasureOutput.make(this.mWidth, this.mHeight);
        }
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSliderShadowNode();
    }

    @Override // com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.ViewManager
    public Class getShadowNodeClass() {
        return ReactSliderShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactSlider createViewInstance(ThemedReactContext themedReactContext) {
        ReactSlider reactSlider = new ReactSlider(themedReactContext, null, 16842875);
        ReactAccessibilityDelegate.setDelegate(reactSlider, reactSlider.isFocusable(), reactSlider.getImportantForAccessibility());
        return reactSlider;
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider reactSlider, boolean z) {
        reactSlider.setEnabled(z);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = 0.0d, name = "value")
    public void setValue(ReactSlider reactSlider, double d) {
        reactSlider.setOnSeekBarChangeListener(null);
        reactSlider.setValue(d);
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = 0.0d, name = "minimumValue")
    public void setMinimumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMinValue(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider reactSlider, double d) {
        reactSlider.setMaxValue(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(defaultDouble = 0.0d, name = "step")
    public void setStep(ReactSlider reactSlider, double d) {
        reactSlider.setStep(d);
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSlider reactSlider, Integer num) {
        if (num == null) {
            reactSlider.getThumb().clearColorFilter();
        } else {
            reactSlider.getThumb().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(R.id.progress);
        if (num == null) {
            drawableFindDrawableByLayerId.clearColorFilter();
        } else {
            drawableFindDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(R.id.background);
        if (num == null) {
            drawableFindDrawableByLayerId.clearColorFilter();
        } else {
            drawableFindDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override // com.facebook.react.viewmanagers.SliderManagerInterface
    public void setTestID(ReactSlider reactSlider, @Nullable String str) {
        super.setTestId(reactSlider, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSlider reactSlider) {
        reactSlider.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.of("topSlidingComplete", MapBuilder.of("registrationName", "onSlidingComplete")));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        Map<String, Object> exportedCustomBubblingEventTypeConstants = super.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants == null) {
            exportedCustomBubblingEventTypeConstants = new HashMap<>();
        }
        exportedCustomBubblingEventTypeConstants.putAll(MapBuilder.builder().put("topValueChange", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onValueChange", "captured", "onValueChangeCapture"))).build());
        return exportedCustomBubblingEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        ReactSlider reactSlider = new ReactSlider(context, null, 16842875);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
        reactSlider.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        return YogaMeasureOutput.make(PixelUtil.toDIPFromPixel(reactSlider.getMeasuredWidth()), PixelUtil.toDIPFromPixel(reactSlider.getMeasuredHeight()));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ReactSlider> getDelegate() {
        return this.mDelegate;
    }
}
