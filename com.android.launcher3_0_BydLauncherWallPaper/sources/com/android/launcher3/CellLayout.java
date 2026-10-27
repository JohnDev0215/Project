package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.android.launcher3.DropTarget;
import com.android.launcher3.accessibility.DragAndDropAccessibilityDelegate;
import com.android.launcher3.accessibility.FolderAccessibilityHelper;
import com.android.launcher3.accessibility.WorkspaceAccessibilityHelper;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertyListBuilder;
import com.android.launcher3.folder.PreviewBackground;
import com.android.launcher3.graphics.DragPreviewProvider;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.util.CellAndSpan;
import com.android.launcher3.util.GridOccupancy;
import com.android.launcher3.util.ParcelableSparseArray;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.ActivityContext;
import com.android.launcher3.views.Transposable;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public class CellLayout extends ViewGroup implements Transposable {
    private static final boolean DEBUG_VISUALIZE_OCCUPIED = false;
    private static final boolean DESTRUCTIVE_REORDER = false;
    public static final int FOLDER = 2;
    public static final int FOLDER_ACCESSIBILITY_DRAG = 1;
    public static final int HOTSEAT = 1;
    private static final int INVALID_DIRECTION = -100;
    private static final boolean LOGD = false;
    public static final int MODE_ACCEPT_DROP = 4;
    public static final int MODE_DRAG_OVER = 1;
    public static final int MODE_ON_DROP = 2;
    public static final int MODE_ON_DROP_EXTERNAL = 3;
    public static final int MODE_SHOW_REORDER_HINT = 0;
    private static final int REORDER_ANIMATION_DURATION = 150;
    private static final float REORDER_PREVIEW_MAGNITUDE = 0.12f;
    private static final String TAG = "CellLayout";
    public static final int WORKSPACE = 0;
    public static final int WORKSPACE_ACCESSIBILITY_DRAG = 2;
    protected final ActivityContext mActivity;
    private final Drawable mBackground;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    int mCellHeight;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    int mCellWidth;
    private final float mChildScale;
    private final int mContainerType;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mCountX;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mCountY;
    private final int[] mDirectionVector;
    private final int[] mDragCell;
    final float[] mDragOutlineAlphas;
    private final InterruptibleInOutAnimator[] mDragOutlineAnims;
    private int mDragOutlineCurrent;
    private final Paint mDragOutlinePaint;
    final Rect[] mDragOutlines;
    private boolean mDragging;
    private boolean mDropPending;
    private final TimeInterpolator mEaseOutInterpolator;
    private int mFixedCellHeight;
    private int mFixedCellWidth;
    private int mFixedHeight;
    private int mFixedWidth;
    private final ArrayList<PreviewBackground> mFolderBackgrounds;
    final PreviewBackground mFolderLeaveBehind;
    private View.OnTouchListener mInterceptTouchListener;
    private final ArrayList<View> mIntersectingViews;
    private boolean mIsDragOverlapping;
    private boolean mItemPlacementDirty;
    private GridOccupancy mOccupied;
    private final Rect mOccupiedRect;
    final int[] mPreviousReorderDirection;
    final ArrayMap<LayoutParams, Animator> mReorderAnimators;
    final float mReorderPreviewAnimationMagnitude;
    private RotationMode mRotationMode;
    final ArrayMap<View, ReorderPreviewAnimation> mShakeAnimators;
    private final ShortcutAndWidgetContainer mShortcutsAndWidgets;
    final int[] mTempLocation;
    private final Rect mTempRect;
    private final Stack<Rect> mTempRectStack;
    private GridOccupancy mTmpOccupied;
    final int[] mTmpPoint;
    private DragAndDropAccessibilityDelegate mTouchHelper;
    private boolean mUseTouchHelper;
    private static final int[] BACKGROUND_STATE_ACTIVE = {16842914};
    private static final int[] BACKGROUND_STATE_DEFAULT = EMPTY_STATE_SET;
    private static final Paint sPaint = new Paint();
    private static final Property<ReorderPreviewAnimation, Float> ANIMATION_PROGRESS = new Property<ReorderPreviewAnimation, Float>(Float.TYPE, "animationProgress") { // from class: com.android.launcher3.CellLayout.5
        @Override // android.util.Property
        public Float get(ReorderPreviewAnimation anim) {
            return Float.valueOf(anim.animationProgress);
        }

        @Override // android.util.Property
        public void set(ReorderPreviewAnimation anim, Float progress) {
            anim.setAnimationProgress(progress.floatValue());
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContainerType {
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public CellLayout(Context context) {
        this(context, null);
    }

    public CellLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CellLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDropPending = false;
        this.mTmpPoint = new int[2];
        this.mTempLocation = new int[2];
        this.mFolderBackgrounds = new ArrayList<>();
        PreviewBackground previewBackground = new PreviewBackground();
        this.mFolderLeaveBehind = previewBackground;
        this.mFixedWidth = -1;
        this.mFixedHeight = -1;
        this.mIsDragOverlapping = false;
        Rect[] rectArr = new Rect[4];
        this.mDragOutlines = rectArr;
        this.mDragOutlineAlphas = new float[rectArr.length];
        this.mDragOutlineAnims = new InterruptibleInOutAnimator[rectArr.length];
        this.mDragOutlineCurrent = 0;
        this.mDragOutlinePaint = new Paint();
        this.mReorderAnimators = new ArrayMap<>();
        this.mShakeAnimators = new ArrayMap<>();
        this.mItemPlacementDirty = false;
        this.mDragCell = new int[]{-1, -1};
        this.mDragging = false;
        this.mChildScale = 1.0f;
        this.mIntersectingViews = new ArrayList<>();
        this.mOccupiedRect = new Rect();
        this.mDirectionVector = new int[2];
        this.mPreviousReorderDirection = new int[]{-100, -100};
        this.mTempRect = new Rect();
        this.mUseTouchHelper = false;
        this.mRotationMode = RotationMode.NORMAL;
        this.mTempRectStack = new Stack<>();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CellLayout, defStyle, 0);
        this.mContainerType = typedArrayObtainStyledAttributes.getInteger(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        setWillNotDraw(false);
        setClipToPadding(false);
        ActivityContext activityContextLookupContext = ActivityContext.lookupContext(context);
        this.mActivity = activityContextLookupContext;
        DeviceProfile wallpaperDeviceProfile = activityContextLookupContext.getWallpaperDeviceProfile();
        this.mCellHeight = -1;
        this.mCellWidth = -1;
        this.mFixedCellHeight = -1;
        this.mFixedCellWidth = -1;
        this.mCountX = wallpaperDeviceProfile.inv.numColumns;
        this.mCountY = wallpaperDeviceProfile.inv.numRows;
        this.mOccupied = new GridOccupancy(this.mCountX, this.mCountY);
        this.mTmpOccupied = new GridOccupancy(this.mCountX, this.mCountY);
        previewBackground.delegateCellX = -1;
        previewBackground.delegateCellY = -1;
        setAlwaysDrawnWithCacheEnabled(false);
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(R.drawable.bg_celllayout);
        this.mBackground = drawable;
        drawable.setCallback(this);
        drawable.setAlpha(0);
        this.mReorderPreviewAnimationMagnitude = wallpaperDeviceProfile.iconSizePx * REORDER_PREVIEW_MAGNITUDE;
        this.mEaseOutInterpolator = Interpolators.DEACCEL_2_5;
        int i = 0;
        while (true) {
            Rect[] rectArr2 = this.mDragOutlines;
            if (i >= rectArr2.length) {
                break;
            }
            rectArr2[i] = new Rect(-1, -1, -1, -1);
            i++;
        }
        this.mDragOutlinePaint.setColor(Themes.getAttrColor(context, R.attr.workspaceTextColor));
        int integer = resources.getInteger(R.integer.config_dragOutlineFadeTime);
        float integer2 = resources.getInteger(R.integer.config_dragOutlineMaxAlpha);
        Arrays.fill(this.mDragOutlineAlphas, 0.0f);
        for (final int i2 = 0; i2 < this.mDragOutlineAnims.length; i2++) {
            final InterruptibleInOutAnimator interruptibleInOutAnimator = new InterruptibleInOutAnimator(integer, 0.0f, integer2);
            interruptibleInOutAnimator.getAnimator().setInterpolator(this.mEaseOutInterpolator);
            interruptibleInOutAnimator.getAnimator().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.CellLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (((Bitmap) interruptibleInOutAnimator.getTag()) == null) {
                        animation.cancel();
                        return;
                    }
                    CellLayout.this.mDragOutlineAlphas[i2] = ((Float) animation.getAnimatedValue()).floatValue();
                    CellLayout cellLayout = CellLayout.this;
                    cellLayout.invalidate(cellLayout.mDragOutlines[i2]);
                }
            });
            interruptibleInOutAnimator.getAnimator().addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.CellLayout.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    if (((Float) ((ValueAnimator) animation).getAnimatedValue()).floatValue() == 0.0f) {
                        interruptibleInOutAnimator.setTag(null);
                    }
                }
            });
            this.mDragOutlineAnims[i2] = interruptibleInOutAnimator;
        }
        ShortcutAndWidgetContainer shortcutAndWidgetContainer = new ShortcutAndWidgetContainer(context, this.mContainerType);
        this.mShortcutsAndWidgets = shortcutAndWidgetContainer;
        shortcutAndWidgetContainer.setCellDimensions(this.mCellWidth, this.mCellHeight, this.mCountX, this.mCountY);
        addView(shortcutAndWidgetContainer);
    }

    public void enableAccessibleDrag(boolean enable, int dragType) {
        this.mUseTouchHelper = enable;
        if (!enable) {
            ViewCompat.setAccessibilityDelegate(this, null);
            setImportantForAccessibility(2);
            getShortcutsAndWidgets().setImportantForAccessibility(2);
            setOnClickListener(null);
        } else {
            if (dragType == 2 && !(this.mTouchHelper instanceof WorkspaceAccessibilityHelper)) {
                this.mTouchHelper = new WorkspaceAccessibilityHelper(this);
            } else if (dragType == 1 && !(this.mTouchHelper instanceof FolderAccessibilityHelper)) {
                this.mTouchHelper = new FolderAccessibilityHelper(this);
            }
            ViewCompat.setAccessibilityDelegate(this, this.mTouchHelper);
            setImportantForAccessibility(1);
            getShortcutsAndWidgets().setImportantForAccessibility(1);
            setOnClickListener(this.mTouchHelper);
        }
        if (getParent() != null) {
            getParent().notifySubtreeAccessibilityStateChanged(this, this, 1);
        }
    }

    public void setRotationMode(RotationMode mode) {
        if (this.mRotationMode != mode) {
            this.mRotationMode = mode;
            requestLayout();
        }
    }

    @Override // com.android.launcher3.views.Transposable
    public RotationMode getRotationMode() {
        return this.mRotationMode;
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        this.mRotationMode.mapRect(left, top, right, bottom, this.mTempRect);
        super.setPadding(this.mTempRect.left, this.mTempRect.top, this.mTempRect.right, this.mTempRect.bottom);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        if (this.mUseTouchHelper && this.mTouchHelper.dispatchHoverEvent(event)) {
            return true;
        }
        return super.dispatchHoverEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.mUseTouchHelper) {
            return true;
        }
        View.OnTouchListener onTouchListener = this.mInterceptTouchListener;
        return onTouchListener != null && onTouchListener.onTouch(this, ev);
    }

    public void enableHardwareLayer(boolean hasLayer) {
        this.mShortcutsAndWidgets.setLayerType(hasLayer ? 2 : 0, sPaint);
    }

    public boolean isHardwareLayerEnabled() {
        return this.mShortcutsAndWidgets.getLayerType() == 2;
    }

    public void setCellDimensions(int width, int height) {
        this.mCellWidth = width;
        this.mFixedCellWidth = width;
        this.mCellHeight = height;
        this.mFixedCellHeight = height;
        this.mShortcutsAndWidgets.setCellDimensions(width, height, this.mCountX, this.mCountY);
    }

    public void setGridSize(int x, int y) {
        this.mCountX = x;
        this.mCountY = y;
        this.mOccupied = new GridOccupancy(this.mCountX, this.mCountY);
        this.mTmpOccupied = new GridOccupancy(this.mCountX, this.mCountY);
        this.mTempRectStack.clear();
        this.mShortcutsAndWidgets.setCellDimensions(this.mCellWidth, this.mCellHeight, this.mCountX, this.mCountY);
        requestLayout();
    }

    public void setInvertIfRtl(boolean invert) {
        this.mShortcutsAndWidgets.setInvertIfRtl(invert);
    }

    public void setDropPending(boolean pending) {
        this.mDropPending = pending;
    }

    public boolean isDropPending() {
        return this.mDropPending;
    }

    void setIsDragOverlapping(boolean isDragOverlapping) {
        if (this.mIsDragOverlapping != isDragOverlapping) {
            this.mIsDragOverlapping = isDragOverlapping;
            this.mBackground.setState(isDragOverlapping ? BACKGROUND_STATE_ACTIVE : BACKGROUND_STATE_DEFAULT);
            invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        ParcelableSparseArray jailedArray = getJailedArray(container);
        super.dispatchSaveInstanceState(jailedArray);
        container.put(R.id.cell_layout_jail_id, jailedArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchRestoreInstanceState(getJailedArray(container));
    }

    private ParcelableSparseArray getJailedArray(SparseArray<Parcelable> container) {
        Parcelable parcelable = container.get(R.id.cell_layout_jail_id);
        return parcelable instanceof ParcelableSparseArray ? (ParcelableSparseArray) parcelable : new ParcelableSparseArray();
    }

    public boolean getIsDragOverlapping() {
        return this.mIsDragOverlapping;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBackground.getAlpha() > 0) {
            this.mBackground.draw(canvas);
        }
        Paint paint = this.mDragOutlinePaint;
        for (int i = 0; i < this.mDragOutlines.length; i++) {
            float f = this.mDragOutlineAlphas[i];
            if (f > 0.0f) {
                Bitmap bitmap = (Bitmap) this.mDragOutlineAnims[i].getTag();
                paint.setAlpha((int) (f + 0.5f));
                canvas.drawBitmap(bitmap, (Rect) null, this.mDragOutlines[i], paint);
            }
        }
        for (int i2 = 0; i2 < this.mFolderBackgrounds.size(); i2++) {
            PreviewBackground previewBackground = this.mFolderBackgrounds.get(i2);
            cellToPoint(previewBackground.delegateCellX, previewBackground.delegateCellY, this.mTempLocation);
            canvas.save();
            int[] iArr = this.mTempLocation;
            canvas.translate(iArr[0], iArr[1]);
            previewBackground.drawBackground(canvas);
            if (!previewBackground.isClipping) {
                previewBackground.drawBackgroundStroke(canvas);
            }
            canvas.restore();
        }
        if (this.mFolderLeaveBehind.delegateCellX < 0 || this.mFolderLeaveBehind.delegateCellY < 0) {
            return;
        }
        cellToPoint(this.mFolderLeaveBehind.delegateCellX, this.mFolderLeaveBehind.delegateCellY, this.mTempLocation);
        canvas.save();
        int[] iArr2 = this.mTempLocation;
        canvas.translate(iArr2[0], iArr2[1]);
        this.mFolderLeaveBehind.drawLeaveBehind(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (int i = 0; i < this.mFolderBackgrounds.size(); i++) {
            PreviewBackground previewBackground = this.mFolderBackgrounds.get(i);
            if (previewBackground.isClipping) {
                cellToPoint(previewBackground.delegateCellX, previewBackground.delegateCellY, this.mTempLocation);
                canvas.save();
                int[] iArr = this.mTempLocation;
                canvas.translate(iArr[0], iArr[1]);
                previewBackground.drawBackgroundStroke(canvas);
                canvas.restore();
            }
        }
    }

    public void addFolderBackground(PreviewBackground bg) {
        this.mFolderBackgrounds.add(bg);
    }

    public void removeFolderBackground(PreviewBackground bg) {
        this.mFolderBackgrounds.remove(bg);
    }

    public void setFolderLeaveBehindCell(int x, int y) {
        View childAt = getChildAt(x, y);
        this.mFolderLeaveBehind.setup(getContext(), this.mActivity, null, childAt.getMeasuredWidth(), childAt.getPaddingTop());
        this.mFolderLeaveBehind.delegateCellX = x;
        this.mFolderLeaveBehind.delegateCellY = y;
        invalidate();
    }

    public void clearFolderLeaveBehind() {
        this.mFolderLeaveBehind.delegateCellX = -1;
        this.mFolderLeaveBehind.delegateCellY = -1;
        invalidate();
    }

    public void restoreInstanceState(SparseArray<Parcelable> states) {
        try {
            dispatchRestoreInstanceState(states);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Ignoring an error while restoring a view instance state", e);
        }
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).cancelLongPress();
        }
    }

    public void setOnInterceptTouchListener(View.OnTouchListener listener) {
        this.mInterceptTouchListener = listener;
    }

    public int getCountX() {
        return this.mCountX;
    }

    public int getCountY() {
        return this.mCountY;
    }

    public boolean acceptsWidget() {
        return this.mContainerType == 0;
    }

    public boolean addViewToCellLayout(View child, int index, int childId, LayoutParams params, boolean markCells) {
        if (child instanceof BubbleTextView) {
            ((BubbleTextView) child).setTextVisibility(this.mContainerType != 1);
        }
        child.setScaleX(1.0f);
        child.setScaleY(1.0f);
        if (params.cellX < 0 || params.cellX > this.mCountX - 1 || params.cellY < 0 || params.cellY > this.mCountY - 1) {
            return false;
        }
        if (params.cellHSpan < 0) {
            params.cellHSpan = this.mCountX;
        }
        if (params.cellVSpan < 0) {
            params.cellVSpan = this.mCountY;
        }
        child.setId(childId);
        this.mShortcutsAndWidgets.addView(child, index, params);
        if (markCells) {
            markCellsAsOccupiedForView(child);
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        this.mOccupied.clear();
        this.mShortcutsAndWidgets.removeAllViews();
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        if (this.mShortcutsAndWidgets.getChildCount() > 0) {
            this.mOccupied.clear();
            this.mShortcutsAndWidgets.removeAllViewsInLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        markCellsAsUnoccupiedForView(view);
        this.mShortcutsAndWidgets.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int index) {
        markCellsAsUnoccupiedForView(this.mShortcutsAndWidgets.getChildAt(index));
        this.mShortcutsAndWidgets.removeViewAt(index);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        markCellsAsUnoccupiedForView(view);
        this.mShortcutsAndWidgets.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int start, int count) {
        for (int i = start; i < start + count; i++) {
            markCellsAsUnoccupiedForView(this.mShortcutsAndWidgets.getChildAt(i));
        }
        this.mShortcutsAndWidgets.removeViews(start, count);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int start, int count) {
        for (int i = start; i < start + count; i++) {
            markCellsAsUnoccupiedForView(this.mShortcutsAndWidgets.getChildAt(i));
        }
        this.mShortcutsAndWidgets.removeViewsInLayout(start, count);
    }

    public void pointToCellExact(int x, int y, int[] result) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        result[0] = (x - paddingLeft) / this.mCellWidth;
        result[1] = (y - paddingTop) / this.mCellHeight;
        int i = this.mCountX;
        int i2 = this.mCountY;
        if (result[0] < 0) {
            result[0] = 0;
        }
        if (result[0] >= i) {
            result[0] = i - 1;
        }
        if (result[1] < 0) {
            result[1] = 0;
        }
        if (result[1] >= i2) {
            result[1] = i2 - 1;
        }
    }

    void pointToCellRounded(int x, int y, int[] result) {
        pointToCellExact(x + (this.mCellWidth / 2), y + (this.mCellHeight / 2), result);
    }

    void cellToPoint(int cellX, int cellY, int[] result) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        result[0] = paddingLeft + (cellX * this.mCellWidth);
        result[1] = paddingTop + (cellY * this.mCellHeight);
    }

    void cellToCenterPoint(int cellX, int cellY, int[] result) {
        regionToCenterPoint(cellX, cellY, 1, 1, result);
    }

    void regionToCenterPoint(int cellX, int cellY, int spanX, int spanY, int[] result) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i = this.mCellWidth;
        result[0] = paddingLeft + (cellX * i) + ((spanX * i) / 2);
        int i2 = this.mCellHeight;
        result[1] = paddingTop + (cellY * i2) + ((spanY * i2) / 2);
    }

    void regionToRect(int cellX, int cellY, int spanX, int spanY, Rect result) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i = this.mCellWidth;
        int i2 = paddingLeft + (cellX * i);
        int i3 = this.mCellHeight;
        int i4 = paddingTop + (cellY * i3);
        result.set(i2, i4, (spanX * i) + i2, (spanY * i3) + i4);
    }

    public float getDistanceFromCell(float x, float y, int[] cell) {
        cellToCenterPoint(cell[0], cell[1], this.mTmpPoint);
        int[] iArr = this.mTmpPoint;
        return (float) Math.hypot(x - iArr[0], y - iArr[1]);
    }

    public int getCellWidth() {
        return this.mCellWidth;
    }

    public int getCellHeight() {
        return this.mCellHeight;
    }

    public void setFixedSize(int width, int height) {
        this.mFixedWidth = width;
        this.mFixedHeight = height;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = size - (getPaddingLeft() + getPaddingRight());
        int paddingTop = size2 - (getPaddingTop() + getPaddingBottom());
        this.mShortcutsAndWidgets.setRotation(this.mRotationMode.surfaceRotation);
        if (this.mRotationMode.isTransposed) {
            paddingTop = paddingLeft;
            paddingLeft = paddingTop;
        }
        if (this.mFixedCellWidth < 0 || this.mFixedCellHeight < 0) {
            int iCalculateCellWidth = DeviceProfile.calculateCellWidth(paddingLeft, this.mCountX);
            int iCalculateCellHeight = DeviceProfile.calculateCellHeight(paddingTop, this.mCountY);
            if (iCalculateCellWidth != this.mCellWidth || iCalculateCellHeight != this.mCellHeight) {
                this.mCellWidth = iCalculateCellWidth;
                this.mCellHeight = iCalculateCellHeight;
                this.mShortcutsAndWidgets.setCellDimensions(iCalculateCellWidth, iCalculateCellHeight, this.mCountX, this.mCountY);
            }
        }
        int i2 = this.mFixedWidth;
        if (i2 > 0 && (i = this.mFixedHeight) > 0) {
            paddingLeft = i2;
            paddingTop = i;
        } else if (mode == 0 || mode2 == 0) {
            throw new RuntimeException("CellLayout cannot have UNSPECIFIED dimensions");
        }
        this.mShortcutsAndWidgets.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824), View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824));
        int measuredWidth = this.mShortcutsAndWidgets.getMeasuredWidth();
        int measuredHeight = this.mShortcutsAndWidgets.getMeasuredHeight();
        if (this.mFixedWidth > 0 && this.mFixedHeight > 0) {
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else {
            setMeasuredDimension(size, size2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft() + ((int) Math.ceil(getUnusedHorizontalSpace() / 2.0f));
        int paddingRight = ((r - l) - getPaddingRight()) - ((int) Math.ceil(getUnusedHorizontalSpace() / 2.0f));
        int paddingTop = getPaddingTop();
        int paddingBottom = (b - t) - getPaddingBottom();
        this.mBackground.getPadding(this.mTempRect);
        this.mBackground.setBounds((paddingLeft - this.mTempRect.left) - getPaddingLeft(), (paddingTop - this.mTempRect.top) - getPaddingTop(), this.mTempRect.right + paddingRight + getPaddingRight(), this.mTempRect.bottom + paddingBottom + getPaddingBottom());
        if (this.mRotationMode.isTransposed) {
            int measuredWidth = this.mShortcutsAndWidgets.getMeasuredWidth() / 2;
            int measuredHeight = this.mShortcutsAndWidgets.getMeasuredHeight() / 2;
            int i = (paddingLeft + paddingRight) / 2;
            int i2 = (paddingTop + paddingBottom) / 2;
            this.mShortcutsAndWidgets.layout(i - measuredWidth, i2 - measuredHeight, i + measuredWidth, i2 + measuredHeight);
            return;
        }
        this.mShortcutsAndWidgets.layout(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public int getUnusedHorizontalSpace() {
        return (((this.mRotationMode.isTransposed ? getMeasuredHeight() : getMeasuredWidth()) - getPaddingLeft()) - getPaddingRight()) - (this.mCountX * this.mCellWidth);
    }

    public Drawable getScrimBackground() {
        return this.mBackground;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mBackground;
    }

    public ShortcutAndWidgetContainer getShortcutsAndWidgets() {
        return this.mShortcutsAndWidgets;
    }

    public View getChildAt(int x, int y) {
        return this.mShortcutsAndWidgets.getChildAt(x, y);
    }

    public boolean animateChildToPosition(final View child, int cellX, int cellY, int duration, int delay, boolean permanent, boolean adjustOccupied) {
        ShortcutAndWidgetContainer shortcutsAndWidgets = getShortcutsAndWidgets();
        if (shortcutsAndWidgets.indexOfChild(child) == -1) {
            return false;
        }
        final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        ItemInfo itemInfo = (ItemInfo) child.getTag();
        if (this.mReorderAnimators.containsKey(layoutParams)) {
            this.mReorderAnimators.get(layoutParams).cancel();
            this.mReorderAnimators.remove(layoutParams);
        }
        final int i = layoutParams.x;
        final int i2 = layoutParams.y;
        if (adjustOccupied) {
            GridOccupancy gridOccupancy = permanent ? this.mOccupied : this.mTmpOccupied;
            gridOccupancy.markCells(layoutParams.cellX, layoutParams.cellY, layoutParams.cellHSpan, layoutParams.cellVSpan, false);
            gridOccupancy.markCells(cellX, cellY, layoutParams.cellHSpan, layoutParams.cellVSpan, true);
        }
        layoutParams.isLockedToGrid = true;
        if (permanent) {
            itemInfo.cellX = cellX;
            layoutParams.cellX = cellX;
            itemInfo.cellY = cellY;
            layoutParams.cellY = cellY;
        } else {
            layoutParams.tmpCellX = cellX;
            layoutParams.tmpCellY = cellY;
        }
        shortcutsAndWidgets.setupLp(child);
        layoutParams.isLockedToGrid = false;
        final int i3 = layoutParams.x;
        final int i4 = layoutParams.y;
        layoutParams.x = i;
        layoutParams.y = i2;
        if (i == i3 && i2 == i4) {
            layoutParams.isLockedToGrid = true;
            return true;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(duration);
        this.mReorderAnimators.put(layoutParams, valueAnimatorOfFloat);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.CellLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                float f = 1.0f - fFloatValue;
                layoutParams.x = (int) ((i * f) + (i3 * fFloatValue));
                layoutParams.y = (int) ((f * i2) + (fFloatValue * i4));
                child.requestLayout();
            }
        });
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.CellLayout.4
            boolean cancelled = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (!this.cancelled) {
                    layoutParams.isLockedToGrid = true;
                    child.requestLayout();
                }
                if (CellLayout.this.mReorderAnimators.containsKey(layoutParams)) {
                    CellLayout.this.mReorderAnimators.remove(layoutParams);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                this.cancelled = true;
            }
        });
        valueAnimatorOfFloat.setStartDelay(delay);
        valueAnimatorOfFloat.start();
        return true;
    }

    void visualizeDropLocation(View v, DragPreviewProvider outlineProvider, int cellX, int cellY, int spanX, int spanY, boolean resize, DropTarget.DragObject dragObject) {
        int width;
        int height;
        int height2;
        int[] iArr = this.mDragCell;
        int i = iArr[0];
        int i2 = iArr[1];
        if (outlineProvider == null || outlineProvider.generatedDragOutline == null) {
            return;
        }
        Bitmap bitmap = outlineProvider.generatedDragOutline;
        if (cellX == i && cellY == i2) {
            return;
        }
        Point dragVisualizeOffset = dragObject.dragView.getDragVisualizeOffset();
        Rect dragRegion = dragObject.dragView.getDragRegion();
        int[] iArr2 = this.mDragCell;
        iArr2[0] = cellX;
        iArr2[1] = cellY;
        int i3 = this.mDragOutlineCurrent;
        this.mDragOutlineAnims[i3].animateOut();
        Rect[] rectArr = this.mDragOutlines;
        int length = (i3 + 1) % rectArr.length;
        this.mDragOutlineCurrent = length;
        Rect rect = rectArr[length];
        if (resize) {
            cellToRect(cellX, cellY, spanX, spanY, rect);
            if (v instanceof LauncherAppWidgetHostView) {
                DeviceProfile wallpaperDeviceProfile = this.mActivity.getWallpaperDeviceProfile();
                Utilities.shrinkRect(rect, wallpaperDeviceProfile.appWidgetScale.x, wallpaperDeviceProfile.appWidgetScale.y);
            }
        } else {
            int[] iArr3 = this.mTmpPoint;
            cellToPoint(cellX, cellY, iArr3);
            int i4 = iArr3[0];
            int i5 = iArr3[1];
            if (v == null || dragVisualizeOffset != null) {
                if (dragVisualizeOffset != null && dragRegion != null) {
                    width = i4 + dragVisualizeOffset.x + (((this.mCellWidth * spanX) - dragRegion.width()) / 2);
                    height = dragVisualizeOffset.y + ((int) Math.max(0.0f, (this.mCellHeight - getShortcutsAndWidgets().getCellContentHeight()) / 2.0f));
                } else {
                    width = i4 + (((this.mCellWidth * spanX) - bitmap.getWidth()) / 2);
                    height = ((this.mCellHeight * spanY) - bitmap.getHeight()) / 2;
                }
                height2 = i5 + height;
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                int i6 = i4 + marginLayoutParams.leftMargin;
                height2 = i5 + marginLayoutParams.topMargin + (((this.mCellHeight * spanY) - bitmap.getHeight()) / 2);
                width = i6 + (((this.mCellWidth * spanX) - bitmap.getWidth()) / 2);
            }
            rect.set(width, height2, bitmap.getWidth() + width, bitmap.getHeight() + height2);
        }
        Utilities.scaleRectAboutCenter(rect, 1.0f);
        this.mDragOutlineAnims[this.mDragOutlineCurrent].setTag(bitmap);
        this.mDragOutlineAnims[this.mDragOutlineCurrent].animateIn();
        if (dragObject.stateAnnouncer != null) {
            dragObject.stateAnnouncer.announce(getItemMoveDescription(cellX, cellY));
        }
    }

    public String getItemMoveDescription(int cellX, int cellY) {
        if (this.mContainerType == 1) {
            return getContext().getString(R.string.move_to_hotseat_position, Integer.valueOf(Math.max(cellX, cellY) + 1));
        }
        return getContext().getString(R.string.move_to_empty_cell, Integer.valueOf(cellY + 1), Integer.valueOf(cellX + 1));
    }

    public void clearDragOutlines() {
        this.mDragOutlineAnims[this.mDragOutlineCurrent].animateOut();
        int[] iArr = this.mDragCell;
        iArr[1] = -1;
        iArr[0] = -1;
    }

    int[] findNearestVacantArea(int pixelX, int pixelY, int minSpanX, int minSpanY, int spanX, int spanY, int[] result, int[] resultSpan) {
        return findNearestArea(pixelX, pixelY, minSpanX, minSpanY, spanX, spanY, true, result, resultSpan);
    }

    private void lazyInitTempRectStack() {
        if (this.mTempRectStack.isEmpty()) {
            for (int i = 0; i < this.mCountX * this.mCountY; i++) {
                this.mTempRectStack.push(new Rect());
            }
        }
    }

    private void recycleTempRects(Stack<Rect> used) {
        while (!used.isEmpty()) {
            this.mTempRectStack.push(used.pop());
        }
    }

    private int[] findNearestArea(int pixelX, int pixelY, int minSpanX, int minSpanY, int spanX, int spanY, boolean ignoreOccupied, int[] result, int[] resultSpan) {
        int i;
        int i2;
        int[] iArr;
        Rect rect;
        boolean z;
        Rect rect2;
        int i3;
        int i4;
        Rect rect3;
        int i5 = minSpanX;
        int i6 = minSpanY;
        int i7 = spanX;
        int i8 = spanY;
        lazyInitTempRectStack();
        int i9 = (int) (pixelX - ((this.mCellWidth * (i7 - 1)) / 2.0f));
        int i10 = (int) (pixelY - ((this.mCellHeight * (i8 - 1)) / 2.0f));
        int[] iArr2 = result != null ? result : new int[2];
        Rect rect4 = new Rect(-1, -1, -1, -1);
        Stack<Rect> stack = new Stack<>();
        int i11 = this.mCountX;
        int i12 = this.mCountY;
        if (i5 <= 0 || i6 <= 0 || i7 <= 0 || i8 <= 0 || i7 < i5 || i8 < i6) {
            return iArr2;
        }
        int i13 = 0;
        double d = Double.MAX_VALUE;
        while (i13 < i12 - (i6 - 1)) {
            int i14 = 0;
            while (i14 < i11 - (i5 - 1)) {
                if (ignoreOccupied) {
                    for (int i15 = 0; i15 < i5; i15++) {
                        int i16 = 0;
                        while (i16 < i6) {
                            iArr = iArr2;
                            if (this.mOccupied.cells[i14 + i15][i13 + i16]) {
                                i = i9;
                                i2 = i10;
                                rect2 = rect4;
                                break;
                            }
                            i16++;
                            iArr2 = iArr;
                        }
                    }
                    iArr = iArr2;
                    boolean z2 = i5 >= i7;
                    boolean z3 = i6 >= i8;
                    boolean z4 = z2;
                    boolean z5 = true;
                    while (true) {
                        if (z4 && z3) {
                            break;
                        }
                        if (!z5 || z4) {
                            i3 = i9;
                            i4 = i10;
                            rect3 = rect4;
                            if (!z3) {
                                for (int i17 = 0; i17 < i5; i17++) {
                                    int i18 = i13 + i6;
                                    if (i18 > i12 - 1 || this.mOccupied.cells[i14 + i17][i18]) {
                                        z3 = true;
                                    }
                                }
                                if (!z3) {
                                    i6++;
                                }
                            }
                        } else {
                            rect3 = rect4;
                            int i19 = 0;
                            while (i19 < i6) {
                                int i20 = i10;
                                int i21 = i14 + i5;
                                int i22 = i9;
                                if (i21 > i11 - 1 || this.mOccupied.cells[i21][i13 + i19]) {
                                    z4 = true;
                                }
                                i19++;
                                i10 = i20;
                                i9 = i22;
                            }
                            i3 = i9;
                            i4 = i10;
                            if (!z4) {
                                i5++;
                            }
                        }
                        z4 |= i5 >= i7;
                        z3 |= i6 >= i8;
                        z5 = !z5;
                        rect4 = rect3;
                        i10 = i4;
                        i9 = i3;
                    }
                    i = i9;
                    i2 = i10;
                    rect = rect4;
                } else {
                    i = i9;
                    i2 = i10;
                    iArr = iArr2;
                    rect = rect4;
                    i5 = -1;
                    i6 = -1;
                }
                cellToCenterPoint(i14, i13, this.mTmpPoint);
                Rect rectPop = this.mTempRectStack.pop();
                rectPop.set(i14, i13, i14 + i5, i13 + i6);
                Iterator<Rect> it = stack.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (it.next().contains(rectPop)) {
                        z = true;
                        break;
                    }
                }
                stack.push(rectPop);
                double dHypot = Math.hypot(r5[0] - i, r5[1] - i2);
                if (dHypot > d || z) {
                    rect2 = rect;
                    if (rectPop.contains(rect2)) {
                    }
                    i14++;
                    i5 = minSpanX;
                    i6 = minSpanY;
                    i7 = spanX;
                    i8 = spanY;
                    rect4 = rect2;
                    iArr2 = iArr;
                    i10 = i2;
                    i9 = i;
                } else {
                    rect2 = rect;
                }
                iArr[0] = i14;
                iArr[1] = i13;
                if (resultSpan != null) {
                    resultSpan[0] = i5;
                    resultSpan[1] = i6;
                }
                rect2.set(rectPop);
                d = dHypot;
                i14++;
                i5 = minSpanX;
                i6 = minSpanY;
                i7 = spanX;
                i8 = spanY;
                rect4 = rect2;
                iArr2 = iArr;
                i10 = i2;
                i9 = i;
            }
            i13++;
            i5 = minSpanX;
            i6 = minSpanY;
            i7 = spanX;
            i8 = spanY;
            i9 = i9;
        }
        int[] iArr3 = iArr2;
        if (d == Double.MAX_VALUE) {
            iArr3[0] = -1;
            iArr3[1] = -1;
        }
        recycleTempRects(stack);
        return iArr3;
    }

    private int[] findNearestArea(int cellX, int cellY, int spanX, int spanY, int[] direction, boolean[][] occupied, boolean[][] blockOccupied, int[] result) {
        int i;
        int[] iArr = result != null ? result : new int[2];
        int i2 = Integer.MIN_VALUE;
        int i3 = this.mCountX;
        int i4 = this.mCountY;
        int i5 = 0;
        float f = Float.MAX_VALUE;
        while (i5 < i4 - (spanY - 1)) {
            int i6 = 0;
            while (i6 < i3 - (spanX - 1)) {
                for (int i7 = 0; i7 < spanX; i7++) {
                    for (int i8 = 0; i8 < spanY; i8++) {
                        if (occupied[i6 + i7][i5 + i8] && (blockOccupied == null || blockOccupied[i7][i8])) {
                            i = i5;
                            break;
                        }
                    }
                }
                int i9 = i6 - cellX;
                int i10 = i5 - cellY;
                i = i5;
                float fHypot = (float) Math.hypot(i9, i10);
                int[] iArr2 = this.mTmpPoint;
                computeDirectionVector(i9, i10, iArr2);
                int i11 = (direction[0] * iArr2[0]) + (direction[1] * iArr2[1]);
                if (Float.compare(fHypot, f) < 0 || (Float.compare(fHypot, f) == 0 && i11 > i2)) {
                    iArr[0] = i6;
                    iArr[1] = i;
                    f = fHypot;
                    i2 = i11;
                }
                i6++;
                i5 = i;
            }
            i5++;
        }
        if (f == Float.MAX_VALUE) {
            iArr[0] = -1;
            iArr[1] = -1;
        }
        return iArr;
    }

    private boolean addViewToTempLocation(View v, Rect rectOccupiedByPotentialDrop, int[] direction, ItemConfiguration currentState) {
        CellAndSpan cellAndSpan = currentState.map.get(v);
        boolean z = false;
        this.mTmpOccupied.markCells(cellAndSpan, false);
        this.mTmpOccupied.markCells(rectOccupiedByPotentialDrop, true);
        findNearestArea(cellAndSpan.cellX, cellAndSpan.cellY, cellAndSpan.spanX, cellAndSpan.spanY, direction, this.mTmpOccupied.cells, null, this.mTempLocation);
        int[] iArr = this.mTempLocation;
        if (iArr[0] >= 0 && iArr[1] >= 0) {
            cellAndSpan.cellX = iArr[0];
            cellAndSpan.cellY = this.mTempLocation[1];
            z = true;
        }
        this.mTmpOccupied.markCells(cellAndSpan, true);
        return z;
    }

    private class ViewCluster {
        static final int BOTTOM = 8;
        static final int LEFT = 1;
        static final int RIGHT = 4;
        static final int TOP = 2;
        final int[] bottomEdge;
        boolean boundingRectDirty;
        final ItemConfiguration config;
        int dirtyEdges;
        final int[] leftEdge;
        final int[] rightEdge;
        final int[] topEdge;
        final ArrayList<View> views;
        final Rect boundingRect = new Rect();
        final PositionComparator comparator = new PositionComparator();

        public ViewCluster(ArrayList<View> views, ItemConfiguration config) {
            this.leftEdge = new int[CellLayout.this.mCountY];
            this.rightEdge = new int[CellLayout.this.mCountY];
            this.topEdge = new int[CellLayout.this.mCountX];
            this.bottomEdge = new int[CellLayout.this.mCountX];
            this.views = (ArrayList) views.clone();
            this.config = config;
            resetEdges();
        }

        void resetEdges() {
            for (int i = 0; i < CellLayout.this.mCountX; i++) {
                this.topEdge[i] = -1;
                this.bottomEdge[i] = -1;
            }
            for (int i2 = 0; i2 < CellLayout.this.mCountY; i2++) {
                this.leftEdge[i2] = -1;
                this.rightEdge[i2] = -1;
            }
            this.dirtyEdges = 15;
            this.boundingRectDirty = true;
        }

        void computeEdge(int which) {
            int size = this.views.size();
            for (int i = 0; i < size; i++) {
                CellAndSpan cellAndSpan = this.config.map.get(this.views.get(i));
                if (which == 1) {
                    int i2 = cellAndSpan.cellX;
                    for (int i3 = cellAndSpan.cellY; i3 < cellAndSpan.cellY + cellAndSpan.spanY; i3++) {
                        int[] iArr = this.leftEdge;
                        if (i2 < iArr[i3] || iArr[i3] < 0) {
                            iArr[i3] = i2;
                        }
                    }
                } else if (which == 2) {
                    int i4 = cellAndSpan.cellY;
                    for (int i5 = cellAndSpan.cellX; i5 < cellAndSpan.cellX + cellAndSpan.spanX; i5++) {
                        int[] iArr2 = this.topEdge;
                        if (i4 < iArr2[i5] || iArr2[i5] < 0) {
                            iArr2[i5] = i4;
                        }
                    }
                } else if (which == 4) {
                    int i6 = cellAndSpan.cellX + cellAndSpan.spanX;
                    for (int i7 = cellAndSpan.cellY; i7 < cellAndSpan.cellY + cellAndSpan.spanY; i7++) {
                        int[] iArr3 = this.rightEdge;
                        if (i6 > iArr3[i7]) {
                            iArr3[i7] = i6;
                        }
                    }
                } else if (which == 8) {
                    int i8 = cellAndSpan.cellY + cellAndSpan.spanY;
                    for (int i9 = cellAndSpan.cellX; i9 < cellAndSpan.cellX + cellAndSpan.spanX; i9++) {
                        int[] iArr4 = this.bottomEdge;
                        if (i8 > iArr4[i9]) {
                            iArr4[i9] = i8;
                        }
                    }
                }
            }
        }

        boolean isViewTouchingEdge(View v, int whichEdge) {
            CellAndSpan cellAndSpan = this.config.map.get(v);
            if ((this.dirtyEdges & whichEdge) == whichEdge) {
                computeEdge(whichEdge);
                this.dirtyEdges &= ~whichEdge;
            }
            if (whichEdge == 1) {
                for (int i = cellAndSpan.cellY; i < cellAndSpan.cellY + cellAndSpan.spanY; i++) {
                    if (this.leftEdge[i] == cellAndSpan.cellX + cellAndSpan.spanX) {
                        return true;
                    }
                }
                return false;
            }
            if (whichEdge == 2) {
                for (int i2 = cellAndSpan.cellX; i2 < cellAndSpan.cellX + cellAndSpan.spanX; i2++) {
                    if (this.topEdge[i2] == cellAndSpan.cellY + cellAndSpan.spanY) {
                        return true;
                    }
                }
                return false;
            }
            if (whichEdge == 4) {
                for (int i3 = cellAndSpan.cellY; i3 < cellAndSpan.cellY + cellAndSpan.spanY; i3++) {
                    if (this.rightEdge[i3] == cellAndSpan.cellX) {
                        return true;
                    }
                }
                return false;
            }
            if (whichEdge != 8) {
                return false;
            }
            for (int i4 = cellAndSpan.cellX; i4 < cellAndSpan.cellX + cellAndSpan.spanX; i4++) {
                if (this.bottomEdge[i4] == cellAndSpan.cellY) {
                    return true;
                }
            }
            return false;
        }

        void shift(int whichEdge, int delta) {
            Iterator<View> it = this.views.iterator();
            while (it.hasNext()) {
                CellAndSpan cellAndSpan = this.config.map.get(it.next());
                if (whichEdge == 1) {
                    cellAndSpan.cellX -= delta;
                } else if (whichEdge == 2) {
                    cellAndSpan.cellY -= delta;
                } else if (whichEdge == 4) {
                    cellAndSpan.cellX += delta;
                } else {
                    cellAndSpan.cellY += delta;
                }
            }
            resetEdges();
        }

        public void addView(View v) {
            this.views.add(v);
            resetEdges();
        }

        public Rect getBoundingRect() {
            if (this.boundingRectDirty) {
                this.config.getBoundingRectForViews(this.views, this.boundingRect);
            }
            return this.boundingRect;
        }

        class PositionComparator implements Comparator<View> {
            int whichEdge = 0;

            PositionComparator() {
            }

            @Override // java.util.Comparator
            public int compare(View left, View right) {
                CellAndSpan cellAndSpan = ViewCluster.this.config.map.get(left);
                CellAndSpan cellAndSpan2 = ViewCluster.this.config.map.get(right);
                int i = this.whichEdge;
                if (i == 1) {
                    return (cellAndSpan2.cellX + cellAndSpan2.spanX) - (cellAndSpan.cellX + cellAndSpan.spanX);
                }
                if (i == 2) {
                    return (cellAndSpan2.cellY + cellAndSpan2.spanY) - (cellAndSpan.cellY + cellAndSpan.spanY);
                }
                if (i == 4) {
                    return cellAndSpan.cellX - cellAndSpan2.cellX;
                }
                return cellAndSpan.cellY - cellAndSpan2.cellY;
            }
        }

        public void sortConfigurationForEdgePush(int edge) {
            this.comparator.whichEdge = edge;
            Collections.sort(this.config.sortedViews, this.comparator);
        }
    }

    private boolean pushViewsToTempLocation(ArrayList<View> views, Rect rectOccupiedByPotentialDrop, int[] direction, View dragView, ItemConfiguration currentState) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        ViewCluster viewCluster = new ViewCluster(views, currentState);
        Rect boundingRect = viewCluster.getBoundingRect();
        boolean z = false;
        if (direction[0] < 0) {
            i5 = boundingRect.right - rectOccupiedByPotentialDrop.left;
            i6 = 1;
        } else {
            if (direction[0] > 0) {
                i = 4;
                i2 = rectOccupiedByPotentialDrop.right;
                i3 = boundingRect.left;
            } else if (direction[1] < 0) {
                i = 2;
                i4 = boundingRect.bottom - rectOccupiedByPotentialDrop.top;
                int i7 = i;
                i5 = i4;
                i6 = i7;
            } else {
                i = 8;
                i2 = rectOccupiedByPotentialDrop.bottom;
                i3 = boundingRect.top;
            }
            i4 = i2 - i3;
            int i72 = i;
            i5 = i4;
            i6 = i72;
        }
        if (i5 <= 0) {
            return false;
        }
        Iterator<View> it = views.iterator();
        while (it.hasNext()) {
            this.mTmpOccupied.markCells(currentState.map.get(it.next()), false);
        }
        currentState.save();
        viewCluster.sortConfigurationForEdgePush(i6);
        boolean z2 = false;
        while (i5 > 0 && !z2) {
            Iterator<View> it2 = currentState.sortedViews.iterator();
            while (true) {
                if (it2.hasNext()) {
                    View next = it2.next();
                    if (!viewCluster.views.contains(next) && next != dragView && viewCluster.isViewTouchingEdge(next, i6)) {
                        if (!((LayoutParams) next.getLayoutParams()).canReorder) {
                            z2 = true;
                            break;
                        }
                        viewCluster.addView(next);
                        this.mTmpOccupied.markCells(currentState.map.get(next), false);
                    }
                }
            }
            i5--;
            viewCluster.shift(i6, 1);
        }
        Rect boundingRect2 = viewCluster.getBoundingRect();
        if (z2 || boundingRect2.left < 0 || boundingRect2.right > this.mCountX || boundingRect2.top < 0 || boundingRect2.bottom > this.mCountY) {
            currentState.restore();
        } else {
            z = true;
        }
        Iterator<View> it3 = viewCluster.views.iterator();
        while (it3.hasNext()) {
            this.mTmpOccupied.markCells(currentState.map.get(it3.next()), true);
        }
        return z;
    }

    private boolean addViewsToTempLocation(ArrayList<View> views, Rect rectOccupiedByPotentialDrop, int[] direction, View dragView, ItemConfiguration currentState) {
        boolean z;
        if (views.size() == 0) {
            return true;
        }
        Rect rect = new Rect();
        currentState.getBoundingRectForViews(views, rect);
        Iterator<View> it = views.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            this.mTmpOccupied.markCells(currentState.map.get(it.next()), false);
        }
        GridOccupancy gridOccupancy = new GridOccupancy(rect.width(), rect.height());
        int i = rect.top;
        int i2 = rect.left;
        Iterator<View> it2 = views.iterator();
        while (it2.hasNext()) {
            CellAndSpan cellAndSpan = currentState.map.get(it2.next());
            gridOccupancy.markCells(cellAndSpan.cellX - i2, cellAndSpan.cellY - i, cellAndSpan.spanX, cellAndSpan.spanY, true);
        }
        this.mTmpOccupied.markCells(rectOccupiedByPotentialDrop, true);
        findNearestArea(rect.left, rect.top, rect.width(), rect.height(), direction, this.mTmpOccupied.cells, gridOccupancy.cells, this.mTempLocation);
        int[] iArr = this.mTempLocation;
        if (iArr[0] >= 0 && iArr[1] >= 0) {
            int i3 = iArr[0] - rect.left;
            int i4 = this.mTempLocation[1] - rect.top;
            Iterator<View> it3 = views.iterator();
            while (it3.hasNext()) {
                CellAndSpan cellAndSpan2 = currentState.map.get(it3.next());
                cellAndSpan2.cellX += i3;
                cellAndSpan2.cellY += i4;
            }
            z = true;
        }
        Iterator<View> it4 = views.iterator();
        while (it4.hasNext()) {
            this.mTmpOccupied.markCells(currentState.map.get(it4.next()), true);
        }
        return z;
    }

    private boolean attemptPushInDirection(ArrayList<View> intersectingViews, Rect occupied, int[] direction, View ignoreView, ItemConfiguration solution) {
        if (Math.abs(direction[0]) + Math.abs(direction[1]) > 1) {
            int i = direction[1];
            direction[1] = 0;
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[1] = i;
            int i2 = direction[0];
            direction[0] = 0;
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = i2;
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
            int i3 = direction[1];
            direction[1] = 0;
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[1] = i3;
            int i4 = direction[0];
            direction[0] = 0;
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = i4;
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
        } else {
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
            int i5 = direction[1];
            direction[1] = direction[0];
            direction[0] = i5;
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
            if (pushViewsToTempLocation(intersectingViews, occupied, direction, ignoreView, solution)) {
                return true;
            }
            direction[0] = direction[0] * (-1);
            direction[1] = direction[1] * (-1);
            int i6 = direction[1];
            direction[1] = direction[0];
            direction[0] = i6;
        }
        return false;
    }

    private boolean rearrangementExists(int cellX, int cellY, int spanX, int spanY, int[] direction, View ignoreView, ItemConfiguration solution) {
        CellAndSpan cellAndSpan;
        if (cellX < 0 || cellY < 0) {
            return false;
        }
        this.mIntersectingViews.clear();
        int i = spanX + cellX;
        int i2 = spanY + cellY;
        this.mOccupiedRect.set(cellX, cellY, i, i2);
        if (ignoreView != null && (cellAndSpan = solution.map.get(ignoreView)) != null) {
            cellAndSpan.cellX = cellX;
            cellAndSpan.cellY = cellY;
        }
        Rect rect = new Rect(cellX, cellY, i, i2);
        Rect rect2 = new Rect();
        for (View view : solution.map.keySet()) {
            if (view != ignoreView) {
                CellAndSpan cellAndSpan2 = solution.map.get(view);
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                rect2.set(cellAndSpan2.cellX, cellAndSpan2.cellY, cellAndSpan2.cellX + cellAndSpan2.spanX, cellAndSpan2.cellY + cellAndSpan2.spanY);
                if (!Rect.intersects(rect, rect2)) {
                    continue;
                } else {
                    if (!layoutParams.canReorder) {
                        return false;
                    }
                    this.mIntersectingViews.add(view);
                }
            }
        }
        solution.intersectingViews = new ArrayList<>(this.mIntersectingViews);
        if (attemptPushInDirection(this.mIntersectingViews, this.mOccupiedRect, direction, ignoreView, solution) || addViewsToTempLocation(this.mIntersectingViews, this.mOccupiedRect, direction, ignoreView, solution)) {
            return true;
        }
        Iterator<View> it = this.mIntersectingViews.iterator();
        while (it.hasNext()) {
            if (!addViewToTempLocation(it.next(), this.mOccupiedRect, direction, solution)) {
                return false;
            }
        }
        return true;
    }

    private void computeDirectionVector(float deltaX, float deltaY, int[] result) {
        double dAtan = Math.atan(deltaY / deltaX);
        result[0] = 0;
        result[1] = 0;
        if (Math.abs(Math.cos(dAtan)) > 0.5d) {
            result[0] = (int) Math.signum(deltaX);
        }
        if (Math.abs(Math.sin(dAtan)) > 0.5d) {
            result[1] = (int) Math.signum(deltaY);
        }
    }

    private ItemConfiguration findReorderSolution(int pixelX, int pixelY, int minSpanX, int minSpanY, int spanX, int spanY, int[] direction, View dragView, boolean decX, ItemConfiguration solution) {
        copyCurrentStateToSolution(solution, false);
        this.mOccupied.copyTo(this.mTmpOccupied);
        int[] iArrFindNearestArea = findNearestArea(pixelX, pixelY, spanX, spanY, new int[2]);
        if (rearrangementExists(iArrFindNearestArea[0], iArrFindNearestArea[1], spanX, spanY, direction, dragView, solution)) {
            solution.isSolution = true;
            solution.cellX = iArrFindNearestArea[0];
            solution.cellY = iArrFindNearestArea[1];
            solution.spanX = spanX;
            solution.spanY = spanY;
        } else {
            if (spanX > minSpanX && (minSpanY == spanY || decX)) {
                return findReorderSolution(pixelX, pixelY, minSpanX, minSpanY, spanX - 1, spanY, direction, dragView, false, solution);
            }
            if (spanY > minSpanY) {
                return findReorderSolution(pixelX, pixelY, minSpanX, minSpanY, spanX, spanY - 1, direction, dragView, true, solution);
            }
            solution.isSolution = false;
        }
        return solution;
    }

    private void copyCurrentStateToSolution(ItemConfiguration solution, boolean temp) {
        CellAndSpan cellAndSpan;
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (temp) {
                cellAndSpan = new CellAndSpan(layoutParams.tmpCellX, layoutParams.tmpCellY, layoutParams.cellHSpan, layoutParams.cellVSpan);
            } else {
                cellAndSpan = new CellAndSpan(layoutParams.cellX, layoutParams.cellY, layoutParams.cellHSpan, layoutParams.cellVSpan);
            }
            solution.add(childAt, cellAndSpan);
        }
    }

    private void copySolutionToTempState(ItemConfiguration solution, View dragView) {
        this.mTmpOccupied.clear();
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i);
            if (childAt != dragView) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                CellAndSpan cellAndSpan = solution.map.get(childAt);
                if (cellAndSpan != null) {
                    layoutParams.tmpCellX = cellAndSpan.cellX;
                    layoutParams.tmpCellY = cellAndSpan.cellY;
                    layoutParams.cellHSpan = cellAndSpan.spanX;
                    layoutParams.cellVSpan = cellAndSpan.spanY;
                    this.mTmpOccupied.markCells(cellAndSpan, true);
                }
            }
        }
        this.mTmpOccupied.markCells((CellAndSpan) solution, true);
    }

    private void animateItemsToSolution(ItemConfiguration solution, View dragView, boolean commitDragView) {
        CellAndSpan cellAndSpan;
        GridOccupancy gridOccupancy = this.mTmpOccupied;
        gridOccupancy.clear();
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i);
            if (childAt != dragView && (cellAndSpan = solution.map.get(childAt)) != null) {
                animateChildToPosition(childAt, cellAndSpan.cellX, cellAndSpan.cellY, 150, 0, false, false);
                gridOccupancy.markCells(cellAndSpan, true);
            }
        }
        if (commitDragView) {
            gridOccupancy.markCells((CellAndSpan) solution, true);
        }
    }

    private void beginOrAdjustReorderPreviewAnimations(ItemConfiguration solution, View dragView, int delay, int mode) {
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i);
            if (childAt != dragView) {
                CellAndSpan cellAndSpan = solution.map.get(childAt);
                boolean z = (mode != 0 || solution.intersectingViews == null || solution.intersectingViews.contains(childAt)) ? false : true;
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (cellAndSpan != null && !z) {
                    new ReorderPreviewAnimation(childAt, mode, layoutParams.cellX, layoutParams.cellY, cellAndSpan.cellX, cellAndSpan.cellY, cellAndSpan.spanX, cellAndSpan.spanY).animate();
                }
            }
        }
    }

    class ReorderPreviewAnimation {
        private static final float CHILD_DIVIDEND = 4.0f;
        private static final int HINT_DURATION = 650;
        public static final int MODE_HINT = 0;
        public static final int MODE_PREVIEW = 1;
        private static final int PREVIEW_DURATION = 300;
        ValueAnimator a;
        final View child;
        float finalDeltaX;
        float finalDeltaY;
        final float finalScale;
        float initDeltaX;
        float initDeltaY;
        float initScale;
        final int mode;
        boolean repeating = false;
        float animationProgress = 0.0f;

        public ReorderPreviewAnimation(View child, int mode, int cellX0, int cellY0, int cellX1, int cellY1, int spanX, int spanY) {
            CellLayout.this.regionToCenterPoint(cellX0, cellY0, spanX, spanY, CellLayout.this.mTmpPoint);
            int i = CellLayout.this.mTmpPoint[0];
            int i2 = CellLayout.this.mTmpPoint[1];
            CellLayout.this.regionToCenterPoint(cellX1, cellY1, spanX, spanY, CellLayout.this.mTmpPoint);
            int i3 = CellLayout.this.mTmpPoint[0] - i;
            int i4 = CellLayout.this.mTmpPoint[1] - i2;
            this.child = child;
            this.mode = mode;
            setInitialAnimationValues(false);
            this.finalScale = (1.0f - (CHILD_DIVIDEND / child.getWidth())) * this.initScale;
            float f = this.initDeltaX;
            this.finalDeltaX = f;
            float f2 = this.initDeltaY;
            this.finalDeltaY = f2;
            int i5 = mode == 0 ? -1 : 1;
            if (i3 == i4 && i3 == 0) {
                return;
            }
            if (i4 == 0) {
                this.finalDeltaX = f + ((-i5) * Math.signum(i3) * CellLayout.this.mReorderPreviewAnimationMagnitude);
                return;
            }
            if (i3 == 0) {
                this.finalDeltaY = f2 + ((-i5) * Math.signum(i4) * CellLayout.this.mReorderPreviewAnimationMagnitude);
                return;
            }
            float f3 = i4;
            float f4 = i3;
            double dAtan = Math.atan(f3 / f4);
            float f5 = -i5;
            this.finalDeltaX += (int) (((double) (Math.signum(f4) * f5)) * Math.abs(Math.cos(dAtan) * ((double) CellLayout.this.mReorderPreviewAnimationMagnitude)));
            this.finalDeltaY += (int) (((double) (f5 * Math.signum(f3))) * Math.abs(Math.sin(dAtan) * ((double) CellLayout.this.mReorderPreviewAnimationMagnitude)));
        }

        void setInitialAnimationValues(boolean restoreOriginalValues) {
            if (restoreOriginalValues) {
                View view = this.child;
                if (view instanceof LauncherAppWidgetHostView) {
                    LauncherAppWidgetHostView launcherAppWidgetHostView = (LauncherAppWidgetHostView) view;
                    this.initScale = launcherAppWidgetHostView.getScaleToFit();
                    this.initDeltaX = launcherAppWidgetHostView.getTranslationForCentering().x;
                    this.initDeltaY = launcherAppWidgetHostView.getTranslationForCentering().y;
                    return;
                }
                this.initScale = 1.0f;
                this.initDeltaX = 0.0f;
                this.initDeltaY = 0.0f;
                return;
            }
            this.initScale = this.child.getScaleX();
            this.initDeltaX = this.child.getTranslationX();
            this.initDeltaY = this.child.getTranslationY();
        }

        void animate() {
            boolean z = this.finalDeltaX == this.initDeltaX && this.finalDeltaY == this.initDeltaY;
            if (CellLayout.this.mShakeAnimators.containsKey(this.child)) {
                CellLayout.this.mShakeAnimators.get(this.child).cancel();
                CellLayout.this.mShakeAnimators.remove(this.child);
                if (z) {
                    completeAnimationImmediately();
                    return;
                }
            }
            if (z) {
                return;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<ReorderPreviewAnimation, Float>) CellLayout.ANIMATION_PROGRESS, 0.0f, 1.0f);
            this.a = objectAnimatorOfFloat;
            if (Utilities.areAnimationsEnabled(CellLayout.this.getContext())) {
                objectAnimatorOfFloat.setRepeatMode(2);
                objectAnimatorOfFloat.setRepeatCount(-1);
            }
            objectAnimatorOfFloat.setDuration(this.mode == 0 ? 650L : 300L);
            objectAnimatorOfFloat.setStartDelay((int) (Math.random() * 60.0d));
            objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.CellLayout.ReorderPreviewAnimation.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    ReorderPreviewAnimation.this.setInitialAnimationValues(true);
                    ReorderPreviewAnimation.this.repeating = true;
                }
            });
            CellLayout.this.mShakeAnimators.put(this.child, this);
            objectAnimatorOfFloat.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAnimationProgress(float progress) {
            this.animationProgress = progress;
            if (this.mode == 0 && this.repeating) {
                progress = 1.0f;
            }
            float f = 1.0f - progress;
            float f2 = (this.finalDeltaX * progress) + (this.initDeltaX * f);
            float f3 = (progress * this.finalDeltaY) + (f * this.initDeltaY);
            this.child.setTranslationX(f2);
            this.child.setTranslationY(f3);
            float f4 = this.animationProgress;
            float f5 = (this.finalScale * f4) + ((1.0f - f4) * this.initScale);
            this.child.setScaleX(f5);
            this.child.setScaleY(f5);
        }

        private void cancel() {
            ValueAnimator valueAnimator = this.a;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }

        void completeAnimationImmediately() {
            ValueAnimator valueAnimator = this.a;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            setInitialAnimationValues(true);
            this.a = new PropertyListBuilder().scale(this.initScale).translationX(this.initDeltaX).translationY(this.initDeltaY).build(this.child).setDuration(150L);
            Launcher.cast(CellLayout.this.mActivity).getDragController().addFirstFrameAnimationHelper(this.a);
            this.a.setInterpolator(Interpolators.DEACCEL_1_5);
            this.a.start();
        }
    }

    private void completeAndClearReorderPreviewAnimations() {
        Iterator<ReorderPreviewAnimation> it = this.mShakeAnimators.values().iterator();
        while (it.hasNext()) {
            it.next().completeAnimationImmediately();
        }
        this.mShakeAnimators.clear();
    }

    private void commitTempPlacement() {
        int i;
        this.mTmpOccupied.copyTo(this.mOccupied);
        int idForScreen = Launcher.cast(this.mActivity).getWorkspace().getIdForScreen(this);
        if (this.mContainerType == 1) {
            idForScreen = -1;
            i = -101;
        } else {
            i = -100;
        }
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ItemInfo itemInfo = (ItemInfo) childAt.getTag();
            if (itemInfo != null) {
                boolean z = (itemInfo.cellX == layoutParams.tmpCellX && itemInfo.cellY == layoutParams.tmpCellY && itemInfo.spanX == layoutParams.cellHSpan && itemInfo.spanY == layoutParams.cellVSpan) ? false : true;
                int i3 = layoutParams.tmpCellX;
                layoutParams.cellX = i3;
                itemInfo.cellX = i3;
                int i4 = layoutParams.tmpCellY;
                layoutParams.cellY = i4;
                itemInfo.cellY = i4;
                itemInfo.spanX = layoutParams.cellHSpan;
                itemInfo.spanY = layoutParams.cellVSpan;
                if (z) {
                    Launcher.cast(this.mActivity).getModelWriter().modifyItemInDatabase(itemInfo, i, idForScreen, itemInfo.cellX, itemInfo.cellY, itemInfo.spanX, itemInfo.spanY);
                }
            }
        }
    }

    private void setUseTempCoords(boolean useTempCoords) {
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((LayoutParams) this.mShortcutsAndWidgets.getChildAt(i).getLayoutParams()).useTmpCoords = useTempCoords;
        }
    }

    private ItemConfiguration findConfigurationNoShuffle(int pixelX, int pixelY, int minSpanX, int minSpanY, int spanX, int spanY, View dragView, ItemConfiguration solution) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        findNearestVacantArea(pixelX, pixelY, minSpanX, minSpanY, spanX, spanY, iArr, iArr2);
        if (iArr[0] >= 0 && iArr[1] >= 0) {
            copyCurrentStateToSolution(solution, false);
            solution.cellX = iArr[0];
            solution.cellY = iArr[1];
            solution.spanX = iArr2[0];
            solution.spanY = iArr2[1];
            solution.isSolution = true;
        } else {
            solution.isSolution = false;
        }
        return solution;
    }

    private void getDirectionVectorForDrop(int dragViewCenterX, int dragViewCenterY, int spanX, int spanY, View dragView, int[] resultDirection) {
        int[] iArr = new int[2];
        findNearestArea(dragViewCenterX, dragViewCenterY, spanX, spanY, iArr);
        Rect rect = new Rect();
        regionToRect(iArr[0], iArr[1], spanX, spanY, rect);
        rect.offset(dragViewCenterX - rect.centerX(), dragViewCenterY - rect.centerY());
        Rect rect2 = new Rect();
        getViewsIntersectingRegion(iArr[0], iArr[1], spanX, spanY, dragView, rect2, this.mIntersectingViews);
        int iWidth = rect2.width();
        int iHeight = rect2.height();
        regionToRect(rect2.left, rect2.top, rect2.width(), rect2.height(), rect2);
        int iCenterX = (rect2.centerX() - dragViewCenterX) / spanX;
        int iCenterY = (rect2.centerY() - dragViewCenterY) / spanY;
        int i = this.mCountX;
        if (iWidth == i || spanX == i) {
            iCenterX = 0;
        }
        int i2 = this.mCountY;
        if (iHeight == i2 || spanY == i2) {
            iCenterY = 0;
        }
        if (iCenterX == 0 && iCenterY == 0) {
            resultDirection[0] = 1;
            resultDirection[1] = 0;
        } else {
            computeDirectionVector(iCenterX, iCenterY, resultDirection);
        }
    }

    private void getViewsIntersectingRegion(int cellX, int cellY, int spanX, int spanY, View dragView, Rect boundingRect, ArrayList<View> intersectingViews) {
        if (boundingRect != null) {
            boundingRect.set(cellX, cellY, cellX + spanX, cellY + spanY);
        }
        intersectingViews.clear();
        Rect rect = new Rect(cellX, cellY, spanX + cellX, spanY + cellY);
        Rect rect2 = new Rect();
        int childCount = this.mShortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mShortcutsAndWidgets.getChildAt(i);
            if (childAt != dragView) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                rect2.set(layoutParams.cellX, layoutParams.cellY, layoutParams.cellX + layoutParams.cellHSpan, layoutParams.cellY + layoutParams.cellVSpan);
                if (Rect.intersects(rect, rect2)) {
                    this.mIntersectingViews.add(childAt);
                    if (boundingRect != null) {
                        boundingRect.union(rect2);
                    }
                }
            }
        }
    }

    boolean isNearestDropLocationOccupied(int pixelX, int pixelY, int spanX, int spanY, View dragView, int[] result) {
        int[] iArrFindNearestArea = findNearestArea(pixelX, pixelY, spanX, spanY, result);
        getViewsIntersectingRegion(iArrFindNearestArea[0], iArrFindNearestArea[1], spanX, spanY, dragView, null, this.mIntersectingViews);
        return !this.mIntersectingViews.isEmpty();
    }

    void revertTempState() {
        completeAndClearReorderPreviewAnimations();
        if (isItemPlacementDirty()) {
            int childCount = this.mShortcutsAndWidgets.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.mShortcutsAndWidgets.getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.tmpCellX != layoutParams.cellX || layoutParams.tmpCellY != layoutParams.cellY) {
                    layoutParams.tmpCellX = layoutParams.cellX;
                    layoutParams.tmpCellY = layoutParams.cellY;
                    animateChildToPosition(childAt, layoutParams.cellX, layoutParams.cellY, 150, 0, false, false);
                }
            }
            setItemPlacementDirty(false);
        }
    }

    boolean createAreaForResize(int cellX, int cellY, int spanX, int spanY, View dragView, int[] direction, boolean commit) {
        int[] iArr = new int[2];
        regionToCenterPoint(cellX, cellY, spanX, spanY, iArr);
        ItemConfiguration itemConfigurationFindReorderSolution = findReorderSolution(iArr[0], iArr[1], spanX, spanY, spanX, spanY, direction, dragView, true, new ItemConfiguration());
        setUseTempCoords(true);
        if (itemConfigurationFindReorderSolution != null && itemConfigurationFindReorderSolution.isSolution) {
            copySolutionToTempState(itemConfigurationFindReorderSolution, dragView);
            setItemPlacementDirty(true);
            animateItemsToSolution(itemConfigurationFindReorderSolution, dragView, commit);
            if (commit) {
                commitTempPlacement();
                completeAndClearReorderPreviewAnimations();
                setItemPlacementDirty(false);
            } else {
                beginOrAdjustReorderPreviewAnimations(itemConfigurationFindReorderSolution, dragView, 150, 1);
            }
            this.mShortcutsAndWidgets.requestLayout();
        }
        return itemConfigurationFindReorderSolution.isSolution;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int[] performReorder(int r20, int r21, int r22, int r23, int r24, int r25, android.view.View r26, int[] r27, int[] r28, int r29) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.CellLayout.performReorder(int, int, int, int, int, int, android.view.View, int[], int[], int):int[]");
    }

    void setItemPlacementDirty(boolean dirty) {
        this.mItemPlacementDirty = dirty;
    }

    boolean isItemPlacementDirty() {
        return this.mItemPlacementDirty;
    }

    private static class ItemConfiguration extends CellAndSpan {
        ArrayList<View> intersectingViews;
        boolean isSolution;
        final ArrayMap<View, CellAndSpan> map;
        private final ArrayMap<View, CellAndSpan> savedMap;
        final ArrayList<View> sortedViews;

        private ItemConfiguration() {
            this.map = new ArrayMap<>();
            this.savedMap = new ArrayMap<>();
            this.sortedViews = new ArrayList<>();
            this.isSolution = false;
        }

        void save() {
            for (View view : this.map.keySet()) {
                this.savedMap.get(view).copyFrom(this.map.get(view));
            }
        }

        void restore() {
            for (View view : this.savedMap.keySet()) {
                this.map.get(view).copyFrom(this.savedMap.get(view));
            }
        }

        void add(View v, CellAndSpan cs) {
            this.map.put(v, cs);
            this.savedMap.put(v, new CellAndSpan());
            this.sortedViews.add(v);
        }

        int area() {
            return this.spanX * this.spanY;
        }

        void getBoundingRectForViews(ArrayList<View> views, Rect outRect) {
            Iterator<View> it = views.iterator();
            boolean z = true;
            while (it.hasNext()) {
                CellAndSpan cellAndSpan = this.map.get(it.next());
                if (z) {
                    outRect.set(cellAndSpan.cellX, cellAndSpan.cellY, cellAndSpan.cellX + cellAndSpan.spanX, cellAndSpan.cellY + cellAndSpan.spanY);
                    z = false;
                } else {
                    outRect.union(cellAndSpan.cellX, cellAndSpan.cellY, cellAndSpan.cellX + cellAndSpan.spanX, cellAndSpan.cellY + cellAndSpan.spanY);
                }
            }
        }
    }

    public int[] findNearestArea(int pixelX, int pixelY, int spanX, int spanY, int[] result) {
        return findNearestArea(pixelX, pixelY, spanX, spanY, spanX, spanY, false, result, null);
    }

    boolean existsEmptyCell() {
        return findCellForSpan(null, 1, 1);
    }

    public boolean findCellForSpan(int[] cellXY, int spanX, int spanY) {
        if (cellXY == null) {
            cellXY = new int[2];
        }
        return this.mOccupied.findVacantCell(cellXY, spanX, spanY);
    }

    void onDragEnter() {
        this.mDragging = true;
    }

    void onDragExit() {
        if (this.mDragging) {
            this.mDragging = false;
        }
        int[] iArr = this.mDragCell;
        iArr[1] = -1;
        iArr[0] = -1;
        this.mDragOutlineAnims[this.mDragOutlineCurrent].animateOut();
        this.mDragOutlineCurrent = (this.mDragOutlineCurrent + 1) % this.mDragOutlineAnims.length;
        revertTempState();
        setIsDragOverlapping(false);
    }

    void onDropChild(View child) {
        if (child != null) {
            ((LayoutParams) child.getLayoutParams()).dropped = true;
            child.requestLayout();
            markCellsAsOccupiedForView(child);
        }
    }

    public void cellToRect(int cellX, int cellY, int cellHSpan, int cellVSpan, Rect resultRect) {
        int i = this.mCellWidth;
        int i2 = this.mCellHeight;
        int paddingLeft = getPaddingLeft() + (cellX * i);
        int paddingTop = getPaddingTop() + (cellY * i2);
        resultRect.set(paddingLeft, paddingTop, (cellHSpan * i) + paddingLeft, (cellVSpan * i2) + paddingTop);
    }

    public void markCellsAsOccupiedForView(View view) {
        if (view == null || view.getParent() != this.mShortcutsAndWidgets) {
            return;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        this.mOccupied.markCells(layoutParams.cellX, layoutParams.cellY, layoutParams.cellHSpan, layoutParams.cellVSpan, true);
    }

    public void markCellsAsUnoccupiedForView(View view) {
        if (view == null || view.getParent() != this.mShortcutsAndWidgets) {
            return;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        this.mOccupied.markCells(layoutParams.cellX, layoutParams.cellY, layoutParams.cellHSpan, layoutParams.cellVSpan, false);
    }

    public int getDesiredWidth() {
        return getPaddingLeft() + getPaddingRight() + (this.mCountX * this.mCellWidth);
    }

    public int getDesiredHeight() {
        return getPaddingTop() + getPaddingBottom() + (this.mCountY * this.mCellHeight);
    }

    public boolean isOccupied(int x, int y) {
        if (x < this.mCountX && y < this.mCountY) {
            return this.mOccupied.cells[x][y];
        }
        throw new RuntimeException("Position exceeds the bound of this CellLayout");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public boolean canReorder;

        @ViewDebug.ExportedProperty
        public int cellHSpan;

        @ViewDebug.ExportedProperty
        public int cellVSpan;

        @ViewDebug.ExportedProperty
        public int cellX;

        @ViewDebug.ExportedProperty
        public int cellY;
        boolean dropped;
        public boolean isLockedToGrid;
        public int tmpCellX;
        public int tmpCellY;
        public boolean useTmpCoords;

        @ViewDebug.ExportedProperty
        public int x;

        @ViewDebug.ExportedProperty
        public int y;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.isLockedToGrid = true;
            this.canReorder = true;
            this.cellHSpan = 1;
            this.cellVSpan = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.isLockedToGrid = true;
            this.canReorder = true;
            this.cellHSpan = 1;
            this.cellVSpan = 1;
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.isLockedToGrid = true;
            this.canReorder = true;
            this.cellX = source.cellX;
            this.cellY = source.cellY;
            this.cellHSpan = source.cellHSpan;
            this.cellVSpan = source.cellVSpan;
        }

        public LayoutParams(int cellX, int cellY, int cellHSpan, int cellVSpan) {
            super(-1, -1);
            this.isLockedToGrid = true;
            this.canReorder = true;
            this.cellX = cellX;
            this.cellY = cellY;
            this.cellHSpan = cellHSpan;
            this.cellVSpan = cellVSpan;
        }

        public void setup(int cellWidth, int cellHeight, boolean invertHorizontally, int colCount) {
            setup(cellWidth, cellHeight, invertHorizontally, colCount, 1.0f, 1.0f);
        }

        public void setup(int cellWidth, int cellHeight, boolean invertHorizontally, int colCount, float cellScaleX, float cellScaleY) {
            if (this.isLockedToGrid) {
                int i = this.cellHSpan;
                int i2 = this.cellVSpan;
                boolean z = this.useTmpCoords;
                int i3 = z ? this.tmpCellX : this.cellX;
                int i4 = z ? this.tmpCellY : this.cellY;
                if (invertHorizontally) {
                    i3 = (colCount - i3) - i;
                }
                this.width = (int) ((((i * cellWidth) / cellScaleX) - this.leftMargin) - this.rightMargin);
                this.height = (int) ((((i2 * cellHeight) / cellScaleY) - this.topMargin) - this.bottomMargin);
                this.x = (i3 * cellWidth) + this.leftMargin;
                this.y = (i4 * cellHeight) + this.topMargin;
            }
        }

        public String toString() {
            return "(" + this.cellX + ", " + this.cellY + ")";
        }
    }

    public static final class CellInfo extends CellAndSpan {
        public final View cell;
        final int container;
        final int screenId;

        public CellInfo(View v, ItemInfo info) {
            this.cellX = info.cellX;
            this.cellY = info.cellY;
            this.spanX = info.spanX;
            this.spanY = info.spanY;
            this.cell = v;
            this.screenId = info.screenId;
            this.container = info.container;
        }

        @Override // com.android.launcher3.util.CellAndSpan
        public String toString() {
            StringBuilder sbAppend = new StringBuilder().append("Cell[view=");
            View view = this.cell;
            return sbAppend.append(view == null ? "null" : view.getClass()).append(", x=").append(this.cellX).append(", y=").append(this.cellY).append("]").toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean hasReorderSolution(ItemInfo itemInfo) {
        int[] iArr = new int[2];
        int i = 0;
        int i2 = 0;
        while (i2 < getCountX()) {
            int i3 = i;
            while (i3 < getCountY()) {
                cellToPoint(i2, i3, iArr);
                int i4 = i3;
                if (findReorderSolution(iArr[i], iArr[1], itemInfo.minSpanX, itemInfo.minSpanY, itemInfo.spanX, itemInfo.spanY, this.mDirectionVector, null, true, new ItemConfiguration()).isSolution) {
                    return true;
                }
                i3 = i4 + 1;
                i = 0;
            }
            i2++;
            i = 0;
        }
        return i;
    }

    public boolean isRegionVacant(int x, int y, int spanX, int spanY) {
        return this.mOccupied.isRegionVacant(x, y, spanX, spanY);
    }
}
