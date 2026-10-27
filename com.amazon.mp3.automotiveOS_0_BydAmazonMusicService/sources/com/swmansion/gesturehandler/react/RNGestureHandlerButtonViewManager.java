package com.swmansion.gesturehandler.react;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewGroupKt;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerDelegate;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: RNGestureHandlerButtonViewManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = RNGestureHandlerButtonViewManager.REACT_CLASS)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0002$%B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006H\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0017J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0015H\u0017J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0015H\u0017J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0015H\u0017J\u001f\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0017¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u001eH\u0017J\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u0015H\u0017R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "Lcom/facebook/react/viewmanagers/RNGestureHandlerButtonManagerInterface;", "()V", "mDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "createViewInstance", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getDelegate", "getName", "", "onAfterUpdateTransaction", "", "view", "setBorderRadius", "borderRadius", "", "setBorderless", "useBorderlessDrawable", "", "setEnabled", "enabled", "setExclusive", "exclusive", "setForeground", "useDrawableOnForeground", "setRippleColor", "rippleColor", "", "(Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;Ljava/lang/Integer;)V", "setRippleRadius", "rippleRadius", "setTouchSoundDisabled", "touchSoundDisabled", "ButtonViewGroup", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> implements RNGestureHandlerButtonManagerInterface<ButtonViewGroup> {
    public static final String REACT_CLASS = "RNGestureHandlerButton";
    private final ViewManagerDelegate<ButtonViewGroup> mDelegate = new RNGestureHandlerButtonManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ButtonViewGroup createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ButtonViewGroup(context);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "foreground")
    @TargetApi(23)
    public void setForeground(ButtonViewGroup view, boolean useDrawableOnForeground) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setUseDrawableOnForeground(useDrawableOnForeground);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup view, boolean useBorderlessDrawable) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setUseBorderlessDrawable(useBorderlessDrawable);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup view, boolean enabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setEnabled(enabled);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ButtonViewGroup view, float borderRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderRadius(borderRadius);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup view, Integer rippleColor) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setRippleColor(rippleColor);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "rippleRadius")
    public void setRippleRadius(ButtonViewGroup view, int rippleRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setRippleRadius(Integer.valueOf(rippleRadius));
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "exclusive")
    public void setExclusive(ButtonViewGroup view, boolean exclusive) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setExclusive(exclusive);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "touchSoundDisabled")
    public void setTouchSoundDisabled(ButtonViewGroup view, boolean touchSoundDisabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSoundEffectsEnabled(!touchSoundDisabled);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ButtonViewGroup view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.updateBackground();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ButtonViewGroup> getDelegate() {
        return this.mDelegate;
    }

    /* JADX INFO: compiled from: RNGestureHandlerButtonViewManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b)\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 P2\u00020\u00012\u00020\u0002:\u0001PB\u0011\u0012\b\u0010M\u001a\u0004\u0018\u00010L¢\u0006\u0004\bN\u0010OJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\u0018\u0010\f\u001a\u00020\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0010H\u0017J\u0006\u0010\u0015\u001a\u00020\u0005J0\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0014J\u0018\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010 \u001a\u00020\u0007H\u0016J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\"\u001a\u00020\u0007H\u0016J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0007H\u0016J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016R.\u0010&\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R.\u0010-\u001a\u0004\u0018\u00010\r2\b\u0010,\u001a\u0004\u0018\u00010\r8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010'\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R*\u00101\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00078\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00107\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00102\u001a\u0004\b8\u00104\"\u0004\b9\u00106R*\u0010:\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u001c8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010@\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b@\u00102\u001a\u0004\bA\u00104\"\u0004\bB\u00106R\u0016\u0010C\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u00102R\u0016\u0010G\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010DR\"\u0010J\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bJ\u00102\u001a\u0004\bJ\u00104\"\u0004\bK\u00106¨\u0006Q"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "Landroid/view/ViewGroup;", "Lcom/swmansion/gesturehandler/core/NativeViewGestureHandler$NativeViewGestureHandlerHook;", "Landroid/graphics/drawable/Drawable;", "createSelectableDrawable", "", "tryFreeingResponder", "", "tryGrabbingResponder", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "children", "isChildTouched", "", "color", "setBackgroundColor", "Landroid/view/MotionEvent;", "ev", "onInterceptTouchEvent", NotificationCompat.CATEGORY_EVENT, "onTouchEvent", "updateBackground", "changed", "l", "t", "r", "b", "onLayout", "", "x", "y", "drawableHotspotChanged", "canBegin", "afterGestureEnd", "performClick", "pressed", "setPressed", "dispatchDrawableHotspotChanged", "rippleColor", "Ljava/lang/Integer;", "getRippleColor", "()Ljava/lang/Integer;", "setRippleColor", "(Ljava/lang/Integer;)V", "radius", "rippleRadius", "getRippleRadius", "setRippleRadius", "useForeground", "useDrawableOnForeground", "Z", "getUseDrawableOnForeground", "()Z", "setUseDrawableOnForeground", "(Z)V", "useBorderlessDrawable", "getUseBorderlessDrawable", "setUseBorderlessDrawable", "borderRadius", "F", "getBorderRadius", "()F", "setBorderRadius", "(F)V", "exclusive", "getExclusive", "setExclusive", "_backgroundColor", "I", "needBackgroundUpdate", "", "lastEventTime", "J", "lastAction", "isTouched", "setTouched", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0})
    public static final class ButtonViewGroup extends ViewGroup implements NativeViewGestureHandler.NativeViewGestureHandlerHook {
        private static ButtonViewGroup soundResponder;
        private static ButtonViewGroup touchResponder;
        private int _backgroundColor;
        private float borderRadius;
        private boolean exclusive;
        private boolean isTouched;
        private int lastAction;
        private long lastEventTime;
        private boolean needBackgroundUpdate;
        private Integer rippleColor;
        private Integer rippleRadius;
        private boolean useBorderlessDrawable;
        private boolean useDrawableOnForeground;
        private static TypedValue resolveOutValue = new TypedValue();
        private static View.OnClickListener dummyClickListener = new View.OnClickListener() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RNGestureHandlerButtonViewManager.ButtonViewGroup.m2755dummyClickListener$lambda5(view);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: dummyClickListener$lambda-5, reason: not valid java name */
        public static final void m2755dummyClickListener$lambda5(View view) {
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDrawableHotspotChanged(float x, float y) {
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean shouldRecognizeSimultaneously(GestureHandler<?> gestureHandler) {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }

        public ButtonViewGroup(Context context) {
            super(context);
            this.exclusive = true;
            this.lastEventTime = -1L;
            this.lastAction = -1;
            setOnClickListener(dummyClickListener);
            setClickable(true);
            setFocusable(true);
            this.needBackgroundUpdate = true;
        }

        public final Integer getRippleColor() {
            return this.rippleColor;
        }

        public final void setRippleColor(Integer num) {
            this.rippleColor = num;
            this.needBackgroundUpdate = true;
        }

        public final Integer getRippleRadius() {
            return this.rippleRadius;
        }

        public final void setRippleRadius(Integer num) {
            this.rippleRadius = num;
            this.needBackgroundUpdate = true;
        }

        public final boolean getUseDrawableOnForeground() {
            return this.useDrawableOnForeground;
        }

        public final void setUseDrawableOnForeground(boolean z) {
            this.useDrawableOnForeground = z;
            this.needBackgroundUpdate = true;
        }

        public final boolean getUseBorderlessDrawable() {
            return this.useBorderlessDrawable;
        }

        public final void setUseBorderlessDrawable(boolean z) {
            this.useBorderlessDrawable = z;
        }

        public final float getBorderRadius() {
            return this.borderRadius;
        }

        public final void setBorderRadius(float f) {
            this.borderRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final boolean getExclusive() {
            return this.exclusive;
        }

        public final void setExclusive(boolean z) {
            this.exclusive = z;
        }

        public final void setTouched(boolean z) {
            this.isTouched = z;
        }

        @Override // android.view.View
        public void setBackgroundColor(int color) {
            this._backgroundColor = color;
            this.needBackgroundUpdate = true;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Intrinsics.checkNotNullParameter(ev, "ev");
            if (super.onInterceptTouchEvent(ev)) {
                return true;
            }
            onTouchEvent(ev);
            return isPressed();
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            if (event.getAction() == 3) {
                tryFreeingResponder();
            }
            long eventTime = event.getEventTime();
            int action = event.getAction();
            if (this.lastEventTime == eventTime && this.lastAction == action) {
                return false;
            }
            this.lastEventTime = eventTime;
            this.lastAction = action;
            return super.onTouchEvent(event);
        }

        public final void updateBackground() {
            if (this.needBackgroundUpdate) {
                this.needBackgroundUpdate = false;
                if (this._backgroundColor == 0) {
                    setBackground(null);
                }
                setForeground(null);
                Drawable drawableCreateSelectableDrawable = createSelectableDrawable();
                if (!(this.borderRadius == 0.0f) && (drawableCreateSelectableDrawable instanceof RippleDrawable)) {
                    PaintDrawable paintDrawable = new PaintDrawable(-1);
                    paintDrawable.setCornerRadius(this.borderRadius);
                    ((RippleDrawable) drawableCreateSelectableDrawable).setDrawableByLayerId(R.id.mask, paintDrawable);
                }
                if (this.useDrawableOnForeground) {
                    setForeground(drawableCreateSelectableDrawable);
                    int i = this._backgroundColor;
                    if (i != 0) {
                        setBackgroundColor(i);
                        return;
                    }
                    return;
                }
                if (this._backgroundColor == 0 && this.rippleColor == null) {
                    setBackground(drawableCreateSelectableDrawable);
                    return;
                }
                PaintDrawable paintDrawable2 = new PaintDrawable(this._backgroundColor);
                float f = this.borderRadius;
                if (!(f == 0.0f)) {
                    paintDrawable2.setCornerRadius(f);
                }
                setBackground(new LayerDrawable(new Drawable[]{paintDrawable2, drawableCreateSelectableDrawable}));
            }
        }

        private final Drawable createSelectableDrawable() {
            ColorStateList colorStateList;
            int[][] iArr = {new int[]{R.attr.state_enabled}};
            Integer num = this.rippleRadius;
            Integer num2 = this.rippleColor;
            if (num2 != null) {
                Intrinsics.checkNotNull(num2);
                colorStateList = new ColorStateList(iArr, new int[]{num2.intValue()});
            } else {
                getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, resolveOutValue, true);
                colorStateList = new ColorStateList(iArr, new int[]{resolveOutValue.data});
            }
            RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, null, this.useBorderlessDrawable ? null : new ShapeDrawable(new RectShape()));
            if (num != null) {
                rippleDrawable.setRadius((int) PixelUtil.toPixelFromDIP(num.intValue()));
            }
            return rippleDrawable;
        }

        @Override // android.view.View
        public void drawableHotspotChanged(float x, float y) {
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(x, y);
            }
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean canBegin() {
            boolean zTryGrabbingResponder = tryGrabbingResponder();
            if (zTryGrabbingResponder) {
                this.isTouched = true;
            }
            return zTryGrabbingResponder;
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public void afterGestureEnd(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            tryFreeingResponder();
            this.isTouched = false;
        }

        private final void tryFreeingResponder() {
            if (touchResponder == this) {
                touchResponder = null;
                soundResponder = this;
            }
        }

        private final boolean tryGrabbingResponder() {
            if (isChildTouched$default(this, null, 1, null)) {
                return false;
            }
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null) {
                touchResponder = this;
                return true;
            }
            if (!this.exclusive) {
                if (!(buttonViewGroup == null ? false : buttonViewGroup.exclusive)) {
                    return true;
                }
            } else if (buttonViewGroup == this) {
                return true;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* synthetic */ boolean isChildTouched$default(ButtonViewGroup buttonViewGroup, Sequence sequence, int i, Object obj) {
            if ((i & 1) != 0) {
                sequence = ViewGroupKt.getChildren(buttonViewGroup);
            }
            return buttonViewGroup.isChildTouched(sequence);
        }

        private final boolean isChildTouched(Sequence<? extends View> children) {
            for (View view : children) {
                if (view instanceof ButtonViewGroup) {
                    ButtonViewGroup buttonViewGroup = (ButtonViewGroup) view;
                    if (buttonViewGroup.isTouched || buttonViewGroup.isPressed()) {
                        return true;
                    }
                }
                if ((view instanceof ViewGroup) && isChildTouched(ViewGroupKt.getChildren((ViewGroup) view))) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (isChildTouched$default(this, null, 1, null) || !Intrinsics.areEqual(soundResponder, this)) {
                return false;
            }
            tryFreeingResponder();
            soundResponder = null;
            return super.performClick();
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0025  */
        @Override // android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void setPressed(boolean r4) {
            /*
                r3 = this;
                if (r4 == 0) goto La
                boolean r0 = r3.tryGrabbingResponder()
                if (r0 == 0) goto La
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.soundResponder = r3
            La:
                boolean r0 = r3.exclusive
                r1 = 1
                r2 = 0
                if (r0 != 0) goto L25
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                if (r0 != 0) goto L16
            L14:
                r0 = r2
                goto L1b
            L16:
                boolean r0 = r0.exclusive
                if (r0 != r1) goto L14
                r0 = r1
            L1b:
                if (r0 != 0) goto L25
                r0 = 0
                boolean r0 = isChildTouched$default(r3, r0, r1, r0)
                if (r0 != 0) goto L25
                goto L26
            L25:
                r1 = r2
            L26:
                if (r4 == 0) goto L2e
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                if (r0 == r3) goto L2e
                if (r1 == 0) goto L33
            L2e:
                r3.isTouched = r4
                super.setPressed(r4)
            L33:
                if (r4 != 0) goto L3b
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r4 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                if (r4 != r3) goto L3b
                r3.isTouched = r2
            L3b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.setPressed(boolean):void");
        }
    }
}
