package android.widget;

import android.animation.ObjectAnimator;
import android.app.slice.Slice;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.MathUtils;
import android.util.Pools;
import android.view.RemotableViewMethod;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewHierarchyEncoder;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.RemoteViews;
import com.android.internal.R;
import java.text.NumberFormat;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class ProgressBar extends View {
    private static final int MAX_LEVEL = 10000;
    private static final int PROGRESS_ANIM_DURATION = 80;
    private static final DecelerateInterpolator PROGRESS_ANIM_INTERPOLATOR = new DecelerateInterpolator();
    private final FloatProperty<ProgressBar> VISUAL_PROGRESS;
    private boolean mAggregatedIsVisible;
    private AlphaAnimation mAnimation;
    private boolean mAttached;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private CharSequence mCustomStateDescription;
    private int mDuration;
    private boolean mHasAnimation;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private Interpolator mInterpolator;
    private int mMax;
    int mMaxHeight;
    private boolean mMaxInitialized;
    int mMaxWidth;
    private int mMin;
    int mMinHeight;
    private boolean mMinInitialized;
    int mMinWidth;
    boolean mMirrorForRtl;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private int mProgress;
    private Drawable mProgressDrawable;
    private ProgressTintInfo mProgressTintInfo;
    private final ArrayList<RefreshData> mRefreshData;
    private boolean mRefreshIsPosted;
    private RefreshProgressRunnable mRefreshProgressRunnable;
    int mSampleWidth;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;
    private float mVisualProgress;

    void onVisualProgressChanged(int i, float f) {
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<ProgressBar> {
        private int mIndeterminateDrawableId;
        private int mIndeterminateId;
        private int mIndeterminateTintBlendModeId;
        private int mIndeterminateTintId;
        private int mIndeterminateTintModeId;
        private int mInterpolatorId;
        private int mMaxId;
        private int mMinId;
        private int mMirrorForRtlId;
        private int mProgressBackgroundTintBlendModeId;
        private int mProgressBackgroundTintId;
        private int mProgressBackgroundTintModeId;
        private int mProgressDrawableId;
        private int mProgressId;
        private int mProgressTintBlendModeId;
        private int mProgressTintId;
        private int mProgressTintModeId;
        private boolean mPropertiesMapped = false;
        private int mSecondaryProgressId;
        private int mSecondaryProgressTintBlendModeId;
        private int mSecondaryProgressTintId;
        private int mSecondaryProgressTintModeId;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.mIndeterminateId = propertyMapper.mapBoolean("indeterminate", 16843065);
            this.mIndeterminateDrawableId = propertyMapper.mapObject("indeterminateDrawable", 16843067);
            this.mIndeterminateTintId = propertyMapper.mapObject("indeterminateTint", 16843881);
            this.mIndeterminateTintBlendModeId = propertyMapper.mapObject("indeterminateTintBlendMode", 23);
            this.mIndeterminateTintModeId = propertyMapper.mapObject("indeterminateTintMode", 16843882);
            this.mInterpolatorId = propertyMapper.mapObject("interpolator", 16843073);
            this.mMaxId = propertyMapper.mapInt(Slice.SUBTYPE_MAX, 16843062);
            this.mMinId = propertyMapper.mapInt("min", 16844089);
            this.mMirrorForRtlId = propertyMapper.mapBoolean("mirrorForRtl", 16843726);
            this.mProgressId = propertyMapper.mapInt("progress", 16843063);
            this.mProgressBackgroundTintId = propertyMapper.mapObject("progressBackgroundTint", 16843877);
            this.mProgressBackgroundTintBlendModeId = propertyMapper.mapObject("progressBackgroundTintBlendMode", 19);
            this.mProgressBackgroundTintModeId = propertyMapper.mapObject("progressBackgroundTintMode", 16843878);
            this.mProgressDrawableId = propertyMapper.mapObject("progressDrawable", 16843068);
            this.mProgressTintId = propertyMapper.mapObject("progressTint", 16843875);
            this.mProgressTintBlendModeId = propertyMapper.mapObject("progressTintBlendMode", 17);
            this.mProgressTintModeId = propertyMapper.mapObject("progressTintMode", 16843876);
            this.mSecondaryProgressId = propertyMapper.mapInt("secondaryProgress", 16843064);
            this.mSecondaryProgressTintId = propertyMapper.mapObject("secondaryProgressTint", 16843879);
            this.mSecondaryProgressTintBlendModeId = propertyMapper.mapObject("secondaryProgressTintBlendMode", 21);
            this.mSecondaryProgressTintModeId = propertyMapper.mapObject("secondaryProgressTintMode", 16843880);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(ProgressBar progressBar, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readBoolean(this.mIndeterminateId, progressBar.isIndeterminate());
            propertyReader.readObject(this.mIndeterminateDrawableId, progressBar.getIndeterminateDrawable());
            propertyReader.readObject(this.mIndeterminateTintId, progressBar.getIndeterminateTintList());
            propertyReader.readObject(this.mIndeterminateTintBlendModeId, progressBar.getIndeterminateTintBlendMode());
            propertyReader.readObject(this.mIndeterminateTintModeId, progressBar.getIndeterminateTintMode());
            propertyReader.readObject(this.mInterpolatorId, progressBar.getInterpolator());
            propertyReader.readInt(this.mMaxId, progressBar.getMax());
            propertyReader.readInt(this.mMinId, progressBar.getMin());
            propertyReader.readBoolean(this.mMirrorForRtlId, progressBar.getMirrorForRtl());
            propertyReader.readInt(this.mProgressId, progressBar.getProgress());
            propertyReader.readObject(this.mProgressBackgroundTintId, progressBar.getProgressBackgroundTintList());
            propertyReader.readObject(this.mProgressBackgroundTintBlendModeId, progressBar.getProgressBackgroundTintBlendMode());
            propertyReader.readObject(this.mProgressBackgroundTintModeId, progressBar.getProgressBackgroundTintMode());
            propertyReader.readObject(this.mProgressDrawableId, progressBar.getProgressDrawable());
            propertyReader.readObject(this.mProgressTintId, progressBar.getProgressTintList());
            propertyReader.readObject(this.mProgressTintBlendModeId, progressBar.getProgressTintBlendMode());
            propertyReader.readObject(this.mProgressTintModeId, progressBar.getProgressTintMode());
            propertyReader.readInt(this.mSecondaryProgressId, progressBar.getSecondaryProgress());
            propertyReader.readObject(this.mSecondaryProgressTintId, progressBar.getSecondaryProgressTintList());
            propertyReader.readObject(this.mSecondaryProgressTintBlendModeId, progressBar.getSecondaryProgressTintBlendMode());
            propertyReader.readObject(this.mSecondaryProgressTintModeId, progressBar.getSecondaryProgressTintMode());
        }
    }

    public ProgressBar(Context context) {
        this(context, null);
    }

    public ProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842871);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSampleWidth = 0;
        this.mMirrorForRtl = false;
        this.mCustomStateDescription = null;
        this.mRefreshData = new ArrayList<>();
        this.VISUAL_PROGRESS = new FloatProperty<ProgressBar>("visual_progress") { // from class: android.widget.ProgressBar.1
            @Override // android.util.FloatProperty
            public void setValue(ProgressBar progressBar, float f) {
                progressBar.setVisualProgress(16908301, f);
                progressBar.mVisualProgress = f;
            }

            @Override // android.util.Property
            public Float get(ProgressBar progressBar) {
                return Float.valueOf(progressBar.mVisualProgress);
            }
        };
        this.mUiThreadId = Thread.currentThread().getId();
        initProgressBar();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressBar, i, i2);
        saveAttributeDataForStyleable(context, R.styleable.ProgressBar, attributeSet, typedArrayObtainStyledAttributes, i, i2);
        this.mNoInvalidate = true;
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(8);
        if (drawable != null) {
            if (needsTileify(drawable)) {
                setProgressDrawableTiled(drawable);
            } else {
                setProgressDrawable(drawable);
            }
        }
        this.mDuration = typedArrayObtainStyledAttributes.getInt(9, this.mDuration);
        this.mMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mMinWidth);
        this.mMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.mMaxWidth);
        this.mMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, this.mMinHeight);
        this.mMaxHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mMaxHeight);
        this.mBehavior = typedArrayObtainStyledAttributes.getInt(10, this.mBehavior);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(13, 17432587);
        if (resourceId > 0) {
            setInterpolator(context, resourceId);
        }
        setMin(typedArrayObtainStyledAttributes.getInt(26, this.mMin));
        setMax(typedArrayObtainStyledAttributes.getInt(2, this.mMax));
        setProgress(typedArrayObtainStyledAttributes.getInt(3, this.mProgress));
        setSecondaryProgress(typedArrayObtainStyledAttributes.getInt(4, this.mSecondaryProgress));
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(7);
        if (drawable2 != null) {
            if (needsTileify(drawable2)) {
                setIndeterminateDrawableTiled(drawable2);
            } else {
                setIndeterminateDrawable(drawable2);
            }
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(6, this.mOnlyIndeterminate);
        this.mOnlyIndeterminate = z;
        this.mNoInvalidate = false;
        setIndeterminate(z || typedArrayObtainStyledAttributes.getBoolean(5, this.mIndeterminate));
        this.mMirrorForRtl = typedArrayObtainStyledAttributes.getBoolean(15, this.mMirrorForRtl);
        if (typedArrayObtainStyledAttributes.hasValue(17)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mProgressBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(17, -1), null);
            this.mProgressTintInfo.mHasProgressTintMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(16)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mProgressTintList = typedArrayObtainStyledAttributes.getColorStateList(16);
            this.mProgressTintInfo.mHasProgressTint = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(19)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mProgressBackgroundBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(19, -1), null);
            this.mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(18)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mProgressBackgroundTintList = typedArrayObtainStyledAttributes.getColorStateList(18);
            this.mProgressTintInfo.mHasProgressBackgroundTint = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(21)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mSecondaryProgressBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(21, -1), null);
            this.mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(20)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mSecondaryProgressTintList = typedArrayObtainStyledAttributes.getColorStateList(20);
            this.mProgressTintInfo.mHasSecondaryProgressTint = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(23)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mIndeterminateBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(23, -1), null);
            this.mProgressTintInfo.mHasIndeterminateTintMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(22)) {
            if (this.mProgressTintInfo == null) {
                this.mProgressTintInfo = new ProgressTintInfo();
            }
            this.mProgressTintInfo.mIndeterminateTintList = typedArrayObtainStyledAttributes.getColorStateList(22);
            this.mProgressTintInfo.mHasIndeterminateTint = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        applyProgressTints();
        applyIndeterminateTint();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public void setMinWidth(int i) {
        this.mMinWidth = i;
        requestLayout();
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public void setMinHeight(int i) {
        this.mMinHeight = i;
        requestLayout();
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
        requestLayout();
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private static boolean needsTileify(Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                if (needsTileify(layerDrawable.getDrawable(i))) {
                    return true;
                }
            }
            return false;
        }
        if (!(drawable instanceof StateListDrawable)) {
            return drawable instanceof BitmapDrawable;
        }
        StateListDrawable stateListDrawable = (StateListDrawable) drawable;
        int stateCount = stateListDrawable.getStateCount();
        for (int i2 = 0; i2 < stateCount; i2++) {
            if (needsTileify(stateListDrawable.getStateDrawable(i2))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable] */
    private Drawable tileify(Drawable drawable, boolean z) {
        int i = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                int id = layerDrawable.getId(i2);
                drawableArr[i2] = tileify(layerDrawable.getDrawable(i2), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                layerDrawable2.setId(i, layerDrawable.getId(i));
                layerDrawable2.setLayerGravity(i, layerDrawable.getLayerGravity(i));
                layerDrawable2.setLayerWidth(i, layerDrawable.getLayerWidth(i));
                layerDrawable2.setLayerHeight(i, layerDrawable.getLayerHeight(i));
                layerDrawable2.setLayerInsetLeft(i, layerDrawable.getLayerInsetLeft(i));
                layerDrawable2.setLayerInsetRight(i, layerDrawable.getLayerInsetRight(i));
                layerDrawable2.setLayerInsetTop(i, layerDrawable.getLayerInsetTop(i));
                layerDrawable2.setLayerInsetBottom(i, layerDrawable.getLayerInsetBottom(i));
                layerDrawable2.setLayerInsetStart(i, layerDrawable.getLayerInsetStart(i));
                layerDrawable2.setLayerInsetEnd(i, layerDrawable.getLayerInsetEnd(i));
                i++;
            }
            return layerDrawable2;
        }
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            StateListDrawable stateListDrawable2 = new StateListDrawable();
            int stateCount = stateListDrawable.getStateCount();
            while (i < stateCount) {
                stateListDrawable2.addState(stateListDrawable.getStateSet(i), tileify(stateListDrawable.getStateDrawable(i), z));
                i++;
            }
            return stateListDrawable2;
        }
        if (drawable instanceof BitmapDrawable) {
            drawable = (BitmapDrawable) drawable.getConstantState().newDrawable(getResources());
            drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            if (this.mSampleWidth <= 0) {
                this.mSampleWidth = drawable.getIntrinsicWidth();
            }
            if (z) {
                return new ClipDrawable((Drawable) drawable, 3, 1);
            }
        }
        return drawable;
    }

    Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable drawableTileify = tileify(animationDrawable.getFrame(i), true);
            drawableTileify.setLevel(10000);
            animationDrawable2.addFrame(drawableTileify, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }

    private void initProgressBar() {
        this.mMin = 0;
        this.mMax = 100;
        this.mProgress = 0;
        this.mSecondaryProgress = 0;
        this.mIndeterminate = false;
        this.mOnlyIndeterminate = false;
        this.mDuration = 4000;
        this.mBehavior = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized boolean isIndeterminate() {
        return this.mIndeterminate;
    }

    @RemotableViewMethod
    public synchronized void setIndeterminate(boolean z) {
        if ((!this.mOnlyIndeterminate || !this.mIndeterminate) && z != this.mIndeterminate) {
            this.mIndeterminate = z;
            if (z) {
                swapCurrentDrawable(this.mIndeterminateDrawable);
                startAnimation();
            } else {
                swapCurrentDrawable(this.mProgressDrawable);
                stopAnimation();
            }
        }
    }

    private void swapCurrentDrawable(Drawable drawable) {
        Drawable drawable2 = this.mCurrentDrawable;
        this.mCurrentDrawable = drawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setVisible(false, false);
            }
            Drawable drawable3 = this.mCurrentDrawable;
            if (drawable3 != null) {
                drawable3.setVisible(getWindowVisibility() == 0 && isShown(), false);
            }
        }
    }

    public Drawable getIndeterminateDrawable() {
        return this.mIndeterminateDrawable;
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.mIndeterminateDrawable);
            }
            this.mIndeterminateDrawable = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                applyIndeterminateTint();
            }
            if (this.mIndeterminate) {
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
        }
    }

    @RemotableViewMethod
    public void setIndeterminateTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mIndeterminateTintList = colorStateList;
        this.mProgressTintInfo.mHasIndeterminateTint = true;
        applyIndeterminateTint();
    }

    public ColorStateList getIndeterminateTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateTintList;
        }
        return null;
    }

    public void setIndeterminateTintMode(PorterDuff.Mode mode) {
        setIndeterminateTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setIndeterminateTintBlendMode(BlendMode blendMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mIndeterminateBlendMode = blendMode;
        this.mProgressTintInfo.mHasIndeterminateTintMode = true;
        applyIndeterminateTint();
    }

    public PorterDuff.Mode getIndeterminateTintMode() {
        BlendMode indeterminateTintBlendMode = getIndeterminateTintBlendMode();
        if (indeterminateTintBlendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(indeterminateTintBlendMode);
        }
        return null;
    }

    public BlendMode getIndeterminateTintBlendMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateBlendMode;
        }
        return null;
    }

    private void applyIndeterminateTint() {
        ProgressTintInfo progressTintInfo;
        if (this.mIndeterminateDrawable == null || (progressTintInfo = this.mProgressTintInfo) == null) {
            return;
        }
        if (progressTintInfo.mHasIndeterminateTint || progressTintInfo.mHasIndeterminateTintMode) {
            this.mIndeterminateDrawable = this.mIndeterminateDrawable.mutate();
            if (progressTintInfo.mHasIndeterminateTint) {
                this.mIndeterminateDrawable.setTintList(progressTintInfo.mIndeterminateTintList);
            }
            if (progressTintInfo.mHasIndeterminateTintMode) {
                this.mIndeterminateDrawable.setTintBlendMode(progressTintInfo.mIndeterminateBlendMode);
            }
            if (this.mIndeterminateDrawable.isStateful()) {
                this.mIndeterminateDrawable.setState(getDrawableState());
            }
        }
    }

    public void setIndeterminateDrawableTiled(Drawable drawable) {
        if (drawable != null) {
            drawable = tileifyIndeterminate(drawable);
        }
        setIndeterminateDrawable(drawable);
    }

    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public void setProgressDrawable(Drawable drawable) {
        Drawable drawable2 = this.mProgressDrawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.mProgressDrawable);
            }
            this.mProgressDrawable = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (this.mMaxHeight < minimumHeight) {
                    this.mMaxHeight = minimumHeight;
                    requestLayout();
                }
                applyProgressTints();
            }
            if (!this.mIndeterminate) {
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(16908301, this.mProgress, false, false, false);
            doRefreshProgress(16908303, this.mSecondaryProgress, false, false, false);
        }
    }

    public boolean getMirrorForRtl() {
        return this.mMirrorForRtl;
    }

    private void applyProgressTints() {
        if (this.mProgressDrawable == null || this.mProgressTintInfo == null) {
            return;
        }
        applyPrimaryProgressTint();
        applyProgressBackgroundTint();
        applySecondaryProgressTint();
    }

    private void applyPrimaryProgressTint() {
        Drawable tintTarget;
        if ((this.mProgressTintInfo.mHasProgressTint || this.mProgressTintInfo.mHasProgressTintMode) && (tintTarget = getTintTarget(16908301, true)) != null) {
            if (this.mProgressTintInfo.mHasProgressTint) {
                tintTarget.setTintList(this.mProgressTintInfo.mProgressTintList);
            }
            if (this.mProgressTintInfo.mHasProgressTintMode) {
                tintTarget.setTintBlendMode(this.mProgressTintInfo.mProgressBlendMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    private void applyProgressBackgroundTint() {
        Drawable tintTarget;
        if ((this.mProgressTintInfo.mHasProgressBackgroundTint || this.mProgressTintInfo.mHasProgressBackgroundTintMode) && (tintTarget = getTintTarget(16908288, false)) != null) {
            if (this.mProgressTintInfo.mHasProgressBackgroundTint) {
                tintTarget.setTintList(this.mProgressTintInfo.mProgressBackgroundTintList);
            }
            if (this.mProgressTintInfo.mHasProgressBackgroundTintMode) {
                tintTarget.setTintBlendMode(this.mProgressTintInfo.mProgressBackgroundBlendMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    private void applySecondaryProgressTint() {
        Drawable tintTarget;
        if ((this.mProgressTintInfo.mHasSecondaryProgressTint || this.mProgressTintInfo.mHasSecondaryProgressTintMode) && (tintTarget = getTintTarget(16908303, false)) != null) {
            if (this.mProgressTintInfo.mHasSecondaryProgressTint) {
                tintTarget.setTintList(this.mProgressTintInfo.mSecondaryProgressTintList);
            }
            if (this.mProgressTintInfo.mHasSecondaryProgressTintMode) {
                tintTarget.setTintBlendMode(this.mProgressTintInfo.mSecondaryProgressBlendMode);
            }
            if (tintTarget.isStateful()) {
                tintTarget.setState(getDrawableState());
            }
        }
    }

    @RemotableViewMethod
    public void setProgressTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mProgressTintList = colorStateList;
        this.mProgressTintInfo.mHasProgressTint = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    public ColorStateList getProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressTintList;
        }
        return null;
    }

    public void setProgressTintMode(PorterDuff.Mode mode) {
        setProgressTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setProgressTintBlendMode(BlendMode blendMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mProgressBlendMode = blendMode;
        this.mProgressTintInfo.mHasProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    public PorterDuff.Mode getProgressTintMode() {
        BlendMode progressTintBlendMode = getProgressTintBlendMode();
        if (progressTintBlendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(progressTintBlendMode);
        }
        return null;
    }

    public BlendMode getProgressTintBlendMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBlendMode;
        }
        return null;
    }

    @RemotableViewMethod
    public void setProgressBackgroundTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mProgressBackgroundTintList = colorStateList;
        this.mProgressTintInfo.mHasProgressBackgroundTint = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public ColorStateList getProgressBackgroundTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundTintList;
        }
        return null;
    }

    public void setProgressBackgroundTintMode(PorterDuff.Mode mode) {
        setProgressBackgroundTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setProgressBackgroundTintBlendMode(BlendMode blendMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mProgressBackgroundBlendMode = blendMode;
        this.mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public PorterDuff.Mode getProgressBackgroundTintMode() {
        BlendMode progressBackgroundTintBlendMode = getProgressBackgroundTintBlendMode();
        if (progressBackgroundTintBlendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(progressBackgroundTintBlendMode);
        }
        return null;
    }

    public BlendMode getProgressBackgroundTintBlendMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundBlendMode;
        }
        return null;
    }

    public void setSecondaryProgressTintList(ColorStateList colorStateList) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mSecondaryProgressTintList = colorStateList;
        this.mProgressTintInfo.mHasSecondaryProgressTint = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    public ColorStateList getSecondaryProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressTintList;
        }
        return null;
    }

    public void setSecondaryProgressTintMode(PorterDuff.Mode mode) {
        setSecondaryProgressTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setSecondaryProgressTintBlendMode(BlendMode blendMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        this.mProgressTintInfo.mSecondaryProgressBlendMode = blendMode;
        this.mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    public PorterDuff.Mode getSecondaryProgressTintMode() {
        BlendMode secondaryProgressTintBlendMode = getSecondaryProgressTintBlendMode();
        if (secondaryProgressTintBlendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(secondaryProgressTintBlendMode);
        }
        return null;
    }

    public BlendMode getSecondaryProgressTintBlendMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressBlendMode;
        }
        return null;
    }

    private Drawable getTintTarget(int i, boolean z) {
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            this.mProgressDrawable = drawable.mutate();
            drawableFindDrawableByLayerId = drawable instanceof LayerDrawable ? ((LayerDrawable) drawable).findDrawableByLayerId(i) : null;
            if (z && drawableFindDrawableByLayerId == null) {
                return drawable;
            }
        }
        return drawableFindDrawableByLayerId;
    }

    public void setProgressDrawableTiled(Drawable drawable) {
        if (drawable != null) {
            drawable = tileify(drawable, false);
        }
        setProgressDrawable(drawable);
    }

    public Drawable getCurrentDrawable() {
        return this.mCurrentDrawable;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mProgressDrawable || drawable == this.mIndeterminateDrawable || super.verifyDrawable(drawable);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onResolveDrawables(int i) {
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != null) {
            drawable.setLayoutDirection(i);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.setLayoutDirection(i);
        }
        Drawable drawable3 = this.mProgressDrawable;
        if (drawable3 != null) {
            drawable3.setLayoutDirection(i);
        }
    }

    @Override // android.view.View
    public void postInvalidate() {
        if (this.mNoInvalidate) {
            return;
        }
        super.postInvalidate();
    }

    private class RefreshProgressRunnable implements Runnable {
        private RefreshProgressRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ProgressBar.this) {
                int size = ProgressBar.this.mRefreshData.size();
                for (int i = 0; i < size; i++) {
                    RefreshData refreshData = (RefreshData) ProgressBar.this.mRefreshData.get(i);
                    ProgressBar.this.doRefreshProgress(refreshData.id, refreshData.progress, refreshData.fromUser, true, refreshData.animate);
                    refreshData.recycle();
                }
                ProgressBar.this.mRefreshData.clear();
                ProgressBar.this.mRefreshIsPosted = false;
            }
        }
    }

    private static class RefreshData {
        private static final int POOL_MAX = 24;
        private static final Pools.SynchronizedPool<RefreshData> sPool = new Pools.SynchronizedPool<>(24);
        public boolean animate;
        public boolean fromUser;
        public int id;
        public int progress;

        private RefreshData() {
        }

        public static RefreshData obtain(int i, int i2, boolean z, boolean z2) {
            RefreshData refreshDataAcquire = sPool.acquire();
            if (refreshDataAcquire == null) {
                refreshDataAcquire = new RefreshData();
            }
            refreshDataAcquire.id = i;
            refreshDataAcquire.progress = i2;
            refreshDataAcquire.fromUser = z;
            refreshDataAcquire.animate = z2;
            return refreshDataAcquire;
        }

        public void recycle() {
            sPool.release(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void doRefreshProgress(int i, int i2, boolean z, boolean z2, boolean z3) {
        int i3 = this.mMax - this.mMin;
        float f = i3 > 0 ? (i2 - this.mMin) / i3 : 0.0f;
        boolean z4 = i == 16908301;
        if (z4 && z3) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.VISUAL_PROGRESS, f);
            objectAnimatorOfFloat.setAutoCancel(true);
            objectAnimatorOfFloat.setDuration(80L);
            objectAnimatorOfFloat.setInterpolator(PROGRESS_ANIM_INTERPOLATOR);
            objectAnimatorOfFloat.start();
        } else {
            setVisualProgress(i, f);
        }
        if (z4 && z2) {
            onProgressRefresh(f, z, i2);
        }
    }

    private float getPercent(int i) {
        float max = getMax();
        float min = getMin();
        float f = i;
        float f2 = max - min;
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.max(0.0f, Math.min(1.0f, (f - min) / f2));
    }

    private CharSequence formatStateDescription(int i) {
        return NumberFormat.getPercentInstance(this.mContext.getResources().getConfiguration().locale).format(getPercent(i));
    }

    @Override // android.view.View
    public void setStateDescription(CharSequence charSequence) {
        this.mCustomStateDescription = charSequence;
        if (charSequence == null) {
            super.setStateDescription(formatStateDescription(this.mProgress));
        } else {
            super.setStateDescription(charSequence);
        }
    }

    void onProgressRefresh(float f, boolean z, int i) {
        if (AccessibilityManager.getInstance(this.mContext).isEnabled() && this.mCustomStateDescription == null) {
            super.setStateDescription(formatStateDescription(this.mProgress));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisualProgress(int i, float f) {
        this.mVisualProgress = f;
        Drawable drawableFindDrawableByLayerId = this.mCurrentDrawable;
        if ((drawableFindDrawableByLayerId instanceof LayerDrawable) && (drawableFindDrawableByLayerId = ((LayerDrawable) drawableFindDrawableByLayerId).findDrawableByLayerId(i)) == null) {
            drawableFindDrawableByLayerId = this.mCurrentDrawable;
        }
        if (drawableFindDrawableByLayerId != null) {
            drawableFindDrawableByLayerId.setLevel((int) (10000.0f * f));
        } else {
            invalidate();
        }
        onVisualProgressChanged(i, f);
    }

    private synchronized void refreshProgress(int i, int i2, boolean z, boolean z2) {
        if (this.mUiThreadId == Thread.currentThread().getId()) {
            doRefreshProgress(i, i2, z, true, z2);
        } else {
            if (this.mRefreshProgressRunnable == null) {
                this.mRefreshProgressRunnable = new RefreshProgressRunnable();
            }
            this.mRefreshData.add(RefreshData.obtain(i, i2, z, z2));
            if (this.mAttached && !this.mRefreshIsPosted) {
                post(this.mRefreshProgressRunnable);
                this.mRefreshIsPosted = true;
            }
        }
    }

    @RemotableViewMethod
    public synchronized void setProgress(int i) {
        setProgressInternal(i, false, false);
    }

    public void setProgress(int i, boolean z) {
        setProgressInternal(i, false, z);
    }

    @RemotableViewMethod
    synchronized boolean setProgressInternal(int i, boolean z, boolean z2) {
        if (this.mIndeterminate) {
            return false;
        }
        int iConstrain = MathUtils.constrain(i, this.mMin, this.mMax);
        if (iConstrain == this.mProgress) {
            return false;
        }
        this.mProgress = iConstrain;
        refreshProgress(16908301, iConstrain, z, z2);
        return true;
    }

    @RemotableViewMethod
    public synchronized void setSecondaryProgress(int i) {
        if (this.mIndeterminate) {
            return;
        }
        if (i < this.mMin) {
            i = this.mMin;
        }
        if (i > this.mMax) {
            i = this.mMax;
        }
        if (i != this.mSecondaryProgress) {
            this.mSecondaryProgress = i;
            refreshProgress(16908303, i, false, false);
        }
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getProgress() {
        return this.mIndeterminate ? 0 : this.mProgress;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getSecondaryProgress() {
        return this.mIndeterminate ? 0 : this.mSecondaryProgress;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMin() {
        return this.mMin;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMax() {
        return this.mMax;
    }

    @RemotableViewMethod
    public synchronized void setMin(int i) {
        if (this.mMaxInitialized && i > this.mMax) {
            i = this.mMax;
        }
        this.mMinInitialized = true;
        if (this.mMaxInitialized && i != this.mMin) {
            this.mMin = i;
            postInvalidate();
            if (this.mProgress < i) {
                this.mProgress = i;
            }
            refreshProgress(16908301, this.mProgress, false, false);
        } else {
            this.mMin = i;
        }
    }

    @RemotableViewMethod
    public synchronized void setMax(int i) {
        if (this.mMinInitialized && i < this.mMin) {
            i = this.mMin;
        }
        this.mMaxInitialized = true;
        if (this.mMinInitialized && i != this.mMax) {
            this.mMax = i;
            postInvalidate();
            if (this.mProgress > i) {
                this.mProgress = i;
            }
            refreshProgress(16908301, this.mProgress, false, false);
        } else {
            this.mMax = i;
        }
    }

    public final synchronized void incrementProgressBy(int i) {
        setProgress(this.mProgress + i);
    }

    public final synchronized void incrementSecondaryProgressBy(int i) {
        setSecondaryProgress(this.mSecondaryProgress + i);
    }

    void startAnimation() {
        if (getVisibility() == 0 && getWindowVisibility() == 0) {
            if (this.mIndeterminateDrawable instanceof Animatable) {
                this.mShouldStartAnimationDrawable = true;
                this.mHasAnimation = false;
            } else {
                this.mHasAnimation = true;
                if (this.mInterpolator == null) {
                    this.mInterpolator = new LinearInterpolator();
                }
                Transformation transformation = this.mTransformation;
                if (transformation == null) {
                    this.mTransformation = new Transformation();
                } else {
                    transformation.clear();
                }
                AlphaAnimation alphaAnimation = this.mAnimation;
                if (alphaAnimation == null) {
                    this.mAnimation = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    alphaAnimation.reset();
                }
                this.mAnimation.setRepeatMode(this.mBehavior);
                this.mAnimation.setRepeatCount(-1);
                this.mAnimation.setDuration(this.mDuration);
                this.mAnimation.setInterpolator(this.mInterpolator);
                this.mAnimation.setStartTime(-1L);
            }
            postInvalidate();
        }
    }

    void stopAnimation() {
        this.mHasAnimation = false;
        Object obj = this.mIndeterminateDrawable;
        if (obj instanceof Animatable) {
            ((Animatable) obj).stop();
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    public void setInterpolator(Context context, int i) {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (z != this.mAggregatedIsVisible) {
            this.mAggregatedIsVisible = z;
            if (this.mIndeterminate) {
                if (z) {
                    startAnimation();
                } else {
                    stopAnimation();
                }
            }
            Drawable drawable = this.mCurrentDrawable;
            if (drawable != null) {
                drawable.setVisible(z, false);
            }
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mInDrawing) {
            return;
        }
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int i = this.mScrollX + this.mPaddingLeft;
            int i2 = this.mScrollY + this.mPaddingTop;
            invalidate(bounds.left + i, bounds.top + i2, bounds.right + i, bounds.bottom + i2);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        updateDrawableBounds(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateDrawableBounds(int r8, int r9) {
        /*
            r7 = this;
            int r0 = r7.mPaddingRight
            int r1 = r7.mPaddingLeft
            int r0 = r0 + r1
            int r8 = r8 - r0
            int r0 = r7.mPaddingTop
            int r1 = r7.mPaddingBottom
            int r0 = r0 + r1
            int r9 = r9 - r0
            android.graphics.drawable.Drawable r0 = r7.mIndeterminateDrawable
            r1 = 0
            if (r0 == 0) goto L64
            boolean r2 = r7.mOnlyIndeterminate
            if (r2 == 0) goto L4c
            boolean r2 = r0 instanceof android.graphics.drawable.AnimationDrawable
            if (r2 != 0) goto L4c
            int r0 = r0.getIntrinsicWidth()
            android.graphics.drawable.Drawable r2 = r7.mIndeterminateDrawable
            int r2 = r2.getIntrinsicHeight()
            float r0 = (float) r0
            float r2 = (float) r2
            float r0 = r0 / r2
            float r2 = (float) r8
            float r3 = (float) r9
            float r4 = r2 / r3
            int r5 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r5 == 0) goto L4c
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 <= 0) goto L3d
            float r3 = r3 * r0
            int r0 = (int) r3
            int r2 = r8 - r0
            int r2 = r2 / 2
            int r0 = r0 + r2
            r3 = r2
            r2 = r0
            r0 = r1
            goto L4f
        L3d:
            r3 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 / r0
            float r2 = r2 * r3
            int r0 = (int) r2
            int r9 = r9 - r0
            int r9 = r9 / 2
            int r0 = r0 + r9
            r2 = r8
            r3 = r1
            r6 = r0
            r0 = r9
            r9 = r6
            goto L4f
        L4c:
            r2 = r8
            r0 = r1
            r3 = r0
        L4f:
            boolean r4 = r7.isLayoutRtl()
            if (r4 == 0) goto L5e
            boolean r4 = r7.mMirrorForRtl
            if (r4 == 0) goto L5e
            int r2 = r8 - r2
            int r8 = r8 - r3
            r3 = r2
            goto L5f
        L5e:
            r8 = r2
        L5f:
            android.graphics.drawable.Drawable r2 = r7.mIndeterminateDrawable
            r2.setBounds(r3, r0, r8, r9)
        L64:
            android.graphics.drawable.Drawable r0 = r7.mProgressDrawable
            if (r0 == 0) goto L6b
            r0.setBounds(r1, r1, r8, r9)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.ProgressBar.updateDrawableBounds(int, int):void");
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    void drawTrack(Canvas canvas) {
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != 0) {
            int iSave = canvas.save();
            if (isLayoutRtl() && this.mMirrorForRtl) {
                canvas.translate(getWidth() - this.mPaddingRight, this.mPaddingTop);
                canvas.scale(-1.0f, 1.0f);
            } else {
                canvas.translate(this.mPaddingLeft, this.mPaddingTop);
            }
            long drawingTime = getDrawingTime();
            if (this.mHasAnimation) {
                this.mAnimation.getTransformation(drawingTime, this.mTransformation);
                float alpha = this.mTransformation.getAlpha();
                try {
                    this.mInDrawing = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.mInDrawing = false;
                    postInvalidateOnAnimation();
                } catch (Throwable th) {
                    this.mInDrawing = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restoreToCount(iSave);
            if (this.mShouldStartAnimationDrawable && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    @Override // android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        int iMax;
        int iMax2;
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != null) {
            iMax2 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, drawable.getIntrinsicWidth()));
            iMax = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, drawable.getIntrinsicHeight()));
        } else {
            iMax = 0;
            iMax2 = 0;
        }
        updateDrawableState();
        setMeasuredDimension(resolveSizeAndState(iMax2 + this.mPaddingLeft + this.mPaddingRight, i, 0), resolveSizeAndState(iMax + this.mPaddingTop + this.mPaddingBottom, i2, 0));
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    private void updateDrawableState() {
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mProgressDrawable;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.setHotspot(f, f2);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.widget.ProgressBar.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int progress;
        int secondaryProgress;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.secondaryProgress = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.secondaryProgress);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.progress = this.mProgress;
        savedState.secondaryProgress = this.mSecondaryProgress;
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.progress);
        setSecondaryProgress(savedState.secondaryProgress);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIndeterminate) {
            startAnimation();
        }
        if (this.mRefreshData != null) {
            synchronized (this) {
                int size = this.mRefreshData.size();
                for (int i = 0; i < size; i++) {
                    RefreshData refreshData = this.mRefreshData.get(i);
                    doRefreshProgress(refreshData.id, refreshData.progress, refreshData.fromUser, true, refreshData.animate);
                    refreshData.recycle();
                }
                this.mRefreshData.clear();
            }
        }
        this.mAttached = true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.mIndeterminate) {
            stopAnimation();
        }
        RefreshProgressRunnable refreshProgressRunnable = this.mRefreshProgressRunnable;
        if (refreshProgressRunnable != null) {
            removeCallbacks(refreshProgressRunnable);
            this.mRefreshIsPosted = false;
        }
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEventInternal(accessibilityEvent);
        accessibilityEvent.setItemCount(this.mMax - this.mMin);
        accessibilityEvent.setCurrentItemIndex(this.mProgress);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (isIndeterminate()) {
            return;
        }
        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, getMin(), getMax(), getProgress()));
        accessibilityNodeInfo.setStateDescription(formatStateDescription(this.mProgress));
    }

    @Override // android.view.View
    protected void encodeProperties(ViewHierarchyEncoder viewHierarchyEncoder) {
        super.encodeProperties(viewHierarchyEncoder);
        viewHierarchyEncoder.addProperty("progress:max", getMax());
        viewHierarchyEncoder.addProperty("progress:progress", getProgress());
        viewHierarchyEncoder.addProperty("progress:secondaryProgress", getSecondaryProgress());
        viewHierarchyEncoder.addProperty("progress:indeterminate", isIndeterminate());
    }

    public boolean isAnimating() {
        return isIndeterminate() && getWindowVisibility() == 0 && isShown();
    }

    private static class ProgressTintInfo {
        boolean mHasIndeterminateTint;
        boolean mHasIndeterminateTintMode;
        boolean mHasProgressBackgroundTint;
        boolean mHasProgressBackgroundTintMode;
        boolean mHasProgressTint;
        boolean mHasProgressTintMode;
        boolean mHasSecondaryProgressTint;
        boolean mHasSecondaryProgressTintMode;
        BlendMode mIndeterminateBlendMode;
        ColorStateList mIndeterminateTintList;
        BlendMode mProgressBackgroundBlendMode;
        ColorStateList mProgressBackgroundTintList;
        BlendMode mProgressBlendMode;
        ColorStateList mProgressTintList;
        BlendMode mSecondaryProgressBlendMode;
        ColorStateList mSecondaryProgressTintList;

        private ProgressTintInfo() {
        }
    }
}
