package android.widget;

import android.animation.ValueAnimator;
import android.app.AppGlobals;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.UndoManager;
import android.content.UndoOperation;
import android.content.UndoOwner;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ParcelableParcel;
import android.os.SystemClock;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.ParcelableSpan;
import android.text.Selection;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.KeyListener;
import android.text.method.MetaKeyKeyListener;
import android.text.method.MovementMethod;
import android.text.method.WordIterator;
import android.text.style.EasyEditSpan;
import android.text.style.SuggestionRangeSpan;
import android.text.style.SuggestionSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationManager;
import android.widget.AdapterView;
import android.widget.Magnifier;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.transition.EpicenterTranslateClipReveal;
import com.android.internal.util.GrowingArrayUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.view.FloatingActionMode;
import com.android.internal.widget.EditableInputConnection;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class Editor {
    static final int BLINK = 500;
    private static final boolean DEBUG_UNDO = false;
    private static final int DELAY_BEFORE_HANDLE_FADES_OUT = 4000;
    private static final int DRAG_SHADOW_MAX_TEXT_LENGTH = 20;
    static final int EXTRACT_NOTHING = -2;
    static final int EXTRACT_UNKNOWN = -1;
    private static final boolean FLAG_USE_MAGNIFIER = true;
    public static final int HANDLE_TYPE_SELECTION_END = 1;
    public static final int HANDLE_TYPE_SELECTION_START = 0;
    private static final int LINE_CHANGE_SLOP_MAX_DP = 45;
    private static final int LINE_CHANGE_SLOP_MIN_DP = 12;
    private static final float LINE_SLOP_MULTIPLIER_FOR_HANDLEVIEWS = 0.5f;
    private static final int MAX_LINE_HEIGHT_FOR_MAGNIFIER = 32;
    private static final int MENU_ITEM_ORDER_ASSIST = 0;
    private static final int MENU_ITEM_ORDER_AUTOFILL = 10;
    private static final int MENU_ITEM_ORDER_COPY = 5;
    private static final int MENU_ITEM_ORDER_CUT = 4;
    private static final int MENU_ITEM_ORDER_PASTE = 6;
    private static final int MENU_ITEM_ORDER_PASTE_AS_PLAIN_TEXT = 11;
    private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
    private static final int MENU_ITEM_ORDER_REDO = 3;
    private static final int MENU_ITEM_ORDER_REPLACE = 9;
    private static final int MENU_ITEM_ORDER_SECONDARY_ASSIST_ACTIONS_START = 50;
    private static final int MENU_ITEM_ORDER_SELECT_ALL = 8;
    private static final int MENU_ITEM_ORDER_SHARE = 7;
    private static final int MENU_ITEM_ORDER_UNDO = 2;
    private static final int MIN_LINE_HEIGHT_FOR_MAGNIFIER = 20;
    private static final int RECENT_CUT_COPY_DURATION_MS = 15000;
    private static final String TAG = "Editor";
    private static final String UNDO_OWNER_TAG = "Editor";
    private static final int UNSET_LINE = -1;
    private static final int UNSET_X_VALUE = -1;
    boolean mAllowUndo;
    private Blink mBlink;
    private float mContextMenuAnchorX;
    private float mContextMenuAnchorY;
    private CorrectionHighlighter mCorrectionHighlighter;
    boolean mCreatedWithASelection;
    private final CursorAnchorInfoNotifier mCursorAnchorInfoNotifier;
    private float mCursorDragDirectionMinXYRatio;
    boolean mCursorVisible;
    ActionMode.Callback mCustomInsertionActionModeCallback;
    ActionMode.Callback mCustomSelectionActionModeCallback;
    boolean mDiscardNextActionUp;
    Drawable mDrawableForCursor;
    CharSequence mError;
    private ErrorPopup mErrorPopup;
    boolean mErrorWasChanged;
    private boolean mFlagCursorDragFromAnywhereEnabled;
    private boolean mFlagInsertionHandleGesturesEnabled;
    boolean mFrozenWithFocus;
    private final boolean mHapticTextHandleEnabled;
    boolean mIgnoreActionUpEvent;
    boolean mInBatchEditControllers;
    private float mInitialZoom;
    InputContentType mInputContentType;
    InputMethodState mInputMethodState;
    int mInputType;
    private Runnable mInsertionActionModeRunnable;
    private boolean mInsertionControllerEnabled;
    InsertionPointCursorController mInsertionPointCursorController;
    boolean mIsBeingLongClicked;
    boolean mIsBeingLongClickedByAccessibility;
    boolean mIsInsertionActionModeStartPending;
    KeyListener mKeyListener;
    private int mLastButtonState;
    private int mLineChangeSlopMax;
    private int mLineChangeSlopMin;
    private MagnifierMotionAnimator mMagnifierAnimator;
    private final ViewTreeObserver.OnDrawListener mMagnifierOnDrawListener;
    private int mMaxLineHeightForMagnifier;
    private final MetricsLogger mMetricsLogger;
    private int mMinLineHeightForMagnifier;
    private final boolean mNewMagnifierEnabled;
    private final MenuItem.OnMenuItemClickListener mOnContextMenuItemClickListener;
    private PositionListener mPositionListener;
    private boolean mPreserveSelection;
    final ProcessTextIntentActionsHandler mProcessTextIntentActionsHandler;
    private boolean mRenderCursorRegardlessTiming;
    private boolean mRequestingLinkActionMode;
    private boolean mRestartActionModeOnNextRefresh;
    boolean mSelectAllOnFocus;
    Drawable mSelectHandleCenter;
    Drawable mSelectHandleLeft;
    Drawable mSelectHandleRight;
    private SelectionActionModeHelper mSelectionActionModeHelper;
    private boolean mSelectionControllerEnabled;
    SelectionModifierCursorController mSelectionModifierCursorController;
    boolean mSelectionMoved;
    private long mShowCursor;
    private boolean mShowErrorAfterAttach;
    private final Runnable mShowFloatingToolbar;
    boolean mShowSoftInputOnFocus;
    private Runnable mShowSuggestionRunnable;
    private SpanController mSpanController;
    SpellChecker mSpellChecker;
    private final SuggestionHelper mSuggestionHelper;
    SuggestionRangeSpan mSuggestionRangeSpan;
    private SuggestionsPopupWindow mSuggestionsPopupWindow;
    private Rect mTempRect;
    private ActionMode mTextActionMode;
    boolean mTextIsSelectable;
    private TextRenderNode[] mTextRenderNodes;
    private final TextView mTextView;
    boolean mTouchFocusSelected;
    private final EditorTouchState mTouchState;
    final UndoInputFilter mUndoInputFilter;
    private final UndoManager mUndoManager;
    private UndoOwner mUndoOwner;
    private final Runnable mUpdateMagnifierRunnable;
    private boolean mUpdateWordIteratorText;
    private WordIterator mWordIterator;
    private WordIterator mWordIteratorWithText;

    private interface CursorController extends ViewTreeObserver.OnTouchModeChangeListener {
        void hide();

        boolean isActive();

        boolean isCursorBeingModified();

        void onDetached();

        void show();
    }

    private interface EasyEditDeleteListener {
        void onDeleteClick(EasyEditSpan easyEditSpan);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HandleType {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface MagnifierHandleTrigger {
        public static final int INSERTION = 0;
        public static final int SELECTION_END = 2;
        public static final int SELECTION_START = 1;
    }

    @interface TextActionMode {
        public static final int INSERTION = 1;
        public static final int SELECTION = 0;
        public static final int TEXT_LINK = 2;
    }

    private interface TextViewPositionListener {
        void updatePosition(int i, int i2, boolean z, boolean z2);
    }

    private static class TextRenderNode {
        boolean isDirty = true;
        boolean needsToBeShifted = true;
        RenderNode renderNode;

        public TextRenderNode(String str) {
            this.renderNode = RenderNode.create(str, null);
        }

        boolean needsRecord() {
            return this.isDirty || !this.renderNode.hasDisplayList();
        }
    }

    Editor(TextView textView) {
        UndoManager undoManager = new UndoManager();
        this.mUndoManager = undoManager;
        this.mUndoOwner = undoManager.getOwner("Editor", this);
        this.mUndoInputFilter = new UndoInputFilter(this);
        this.mAllowUndo = true;
        this.mMetricsLogger = new MetricsLogger();
        this.mUpdateMagnifierRunnable = new Runnable() { // from class: android.widget.Editor.1
            @Override // java.lang.Runnable
            public void run() {
                Editor.this.mMagnifierAnimator.update();
            }
        };
        this.mMagnifierOnDrawListener = new ViewTreeObserver.OnDrawListener() { // from class: android.widget.Editor.2
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public void onDraw() {
                if (Editor.this.mMagnifierAnimator != null) {
                    Editor.this.mTextView.post(Editor.this.mUpdateMagnifierRunnable);
                }
            }
        };
        this.mInputType = 0;
        this.mCursorVisible = true;
        this.mShowSoftInputOnFocus = true;
        this.mDrawableForCursor = null;
        this.mTouchState = new EditorTouchState();
        this.mCursorAnchorInfoNotifier = new CursorAnchorInfoNotifier();
        this.mShowFloatingToolbar = new Runnable() { // from class: android.widget.Editor.3
            @Override // java.lang.Runnable
            public void run() {
                if (Editor.this.mTextActionMode != null) {
                    Editor.this.mTextActionMode.hide(0L);
                }
            }
        };
        this.mIsInsertionActionModeStartPending = false;
        this.mSuggestionHelper = new SuggestionHelper();
        this.mInitialZoom = 1.0f;
        this.mOnContextMenuItemClickListener = new MenuItem.OnMenuItemClickListener() { // from class: android.widget.Editor.5
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (Editor.this.mProcessTextIntentActionsHandler.performMenuItemAction(menuItem)) {
                    return true;
                }
                return Editor.this.mTextView.onTextContextMenuItem(menuItem.getItemId());
            }
        };
        this.mTextView = textView;
        textView.setFilters(textView.getFilters());
        this.mProcessTextIntentActionsHandler = new ProcessTextIntentActionsHandler();
        this.mHapticTextHandleEnabled = this.mTextView.getContext().getResources().getBoolean(R.bool.config_enableHapticTextHandle);
        this.mFlagCursorDragFromAnywhereEnabled = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_ENABLE_CURSOR_DRAG_FROM_ANYWHERE, 1) != 0;
        this.mCursorDragDirectionMinXYRatio = EditorTouchState.getXYRatio(AppGlobals.getIntCoreSetting(WidgetFlags.KEY_CURSOR_DRAG_MIN_ANGLE_FROM_VERTICAL, 45));
        this.mFlagInsertionHandleGesturesEnabled = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_ENABLE_INSERTION_HANDLE_GESTURES, 0) != 0;
        this.mNewMagnifierEnabled = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_ENABLE_NEW_MAGNIFIER, 0) != 0;
        this.mLineChangeSlopMax = (int) TypedValue.applyDimension(1, 45.0f, this.mTextView.getContext().getResources().getDisplayMetrics());
        this.mLineChangeSlopMin = (int) TypedValue.applyDimension(1, 12.0f, this.mTextView.getContext().getResources().getDisplayMetrics());
    }

    public boolean getFlagCursorDragFromAnywhereEnabled() {
        return this.mFlagCursorDragFromAnywhereEnabled;
    }

    public void setFlagCursorDragFromAnywhereEnabled(boolean z) {
        this.mFlagCursorDragFromAnywhereEnabled = z;
    }

    public void setCursorDragMinAngleFromVertical(int i) {
        this.mCursorDragDirectionMinXYRatio = EditorTouchState.getXYRatio(i);
    }

    public boolean getFlagInsertionHandleGesturesEnabled() {
        return this.mFlagInsertionHandleGesturesEnabled;
    }

    public void setFlagInsertionHandleGesturesEnabled(boolean z) {
        this.mFlagInsertionHandleGesturesEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MagnifierMotionAnimator getMagnifierAnimator() {
        Magnifier.Builder builderCreateBuilderWithOldMagnifierDefaults;
        if (this.mMagnifierAnimator == null) {
            if (this.mNewMagnifierEnabled) {
                builderCreateBuilderWithOldMagnifierDefaults = createBuilderWithInlineMagnifierDefaults();
            } else {
                builderCreateBuilderWithOldMagnifierDefaults = Magnifier.createBuilderWithOldMagnifierDefaults(this.mTextView);
            }
            this.mMagnifierAnimator = new MagnifierMotionAnimator(builderCreateBuilderWithOldMagnifierDefaults.build());
        }
        return this.mMagnifierAnimator;
    }

    private Magnifier.Builder createBuilderWithInlineMagnifierDefaults() {
        Magnifier.Builder builder = new Magnifier.Builder(this.mTextView);
        float f = 1.5f;
        float floatCoreSetting = AppGlobals.getFloatCoreSetting(WidgetFlags.KEY_MAGNIFIER_ZOOM_FACTOR, 1.5f);
        float f2 = 5.5f;
        float floatCoreSetting2 = AppGlobals.getFloatCoreSetting(WidgetFlags.KEY_MAGNIFIER_ASPECT_RATIO, 5.5f);
        if (floatCoreSetting >= 1.2f && floatCoreSetting <= 1.8f) {
            f = floatCoreSetting;
        }
        if (floatCoreSetting2 >= 3.0f && floatCoreSetting2 <= 8.0f) {
            f2 = floatCoreSetting2;
        }
        this.mInitialZoom = f;
        this.mMinLineHeightForMagnifier = (int) TypedValue.applyDimension(1, 20.0f, this.mTextView.getContext().getResources().getDisplayMetrics());
        this.mMaxLineHeightForMagnifier = (int) TypedValue.applyDimension(1, 32.0f, this.mTextView.getContext().getResources().getDisplayMetrics());
        Layout layout = this.mTextView.getLayout();
        int lineForOffset = layout.getLineForOffset(this.mTextView.getSelectionStart());
        int lineBottomWithoutSpacing = layout.getLineBottomWithoutSpacing(lineForOffset) - layout.getLineTop(lineForOffset);
        int iMax = (int) (f2 * Math.max(lineBottomWithoutSpacing, this.mMinLineHeightForMagnifier));
        builder.setFishEyeStyle().setSize(iMax, (int) (lineBottomWithoutSpacing * f)).setSourceSize(iMax, lineBottomWithoutSpacing).setElevation(0.0f).setInitialZoom(f).setClippingEnabled(false);
        TypedArray typedArrayObtainStyledAttributes = this.mTextView.getContext().obtainStyledAttributes(null, R.styleable.Magnifier, R.attr.magnifierStyle, 0);
        builder.setDefaultSourceToMagnifierOffset(typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 0), typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0));
        typedArrayObtainStyledAttributes.recycle();
        return builder.setSourceBounds(1, 0, 1, 0);
    }

    ParcelableParcel saveInstanceState() {
        ParcelableParcel parcelableParcel = new ParcelableParcel(getClass().getClassLoader());
        Parcel parcel = parcelableParcel.getParcel();
        this.mUndoManager.saveInstanceState(parcel);
        this.mUndoInputFilter.saveInstanceState(parcel);
        return parcelableParcel;
    }

    void restoreInstanceState(ParcelableParcel parcelableParcel) {
        Parcel parcel = parcelableParcel.getParcel();
        this.mUndoManager.restoreInstanceState(parcel, parcelableParcel.getClassLoader());
        this.mUndoInputFilter.restoreInstanceState(parcel);
        this.mUndoOwner = this.mUndoManager.getOwner("Editor", this);
    }

    void forgetUndoRedo() {
        UndoOwner[] undoOwnerArr = {this.mUndoOwner};
        this.mUndoManager.forgetUndos(undoOwnerArr, -1);
        this.mUndoManager.forgetRedos(undoOwnerArr, -1);
    }

    boolean canUndo() {
        return this.mAllowUndo && this.mUndoManager.countUndos(new UndoOwner[]{this.mUndoOwner}) > 0;
    }

    boolean canRedo() {
        return this.mAllowUndo && this.mUndoManager.countRedos(new UndoOwner[]{this.mUndoOwner}) > 0;
    }

    void undo() {
        if (this.mAllowUndo) {
            this.mUndoManager.undo(new UndoOwner[]{this.mUndoOwner}, 1);
        }
    }

    void redo() {
        if (this.mAllowUndo) {
            this.mUndoManager.redo(new UndoOwner[]{this.mUndoOwner}, 1);
        }
    }

    void replace() {
        if (this.mSuggestionsPopupWindow == null) {
            this.mSuggestionsPopupWindow = new SuggestionsPopupWindow();
        }
        hideCursorAndSpanControllers();
        this.mSuggestionsPopupWindow.show();
        Selection.setSelection((Spannable) this.mTextView.getText(), (this.mTextView.getSelectionStart() + this.mTextView.getSelectionEnd()) / 2);
    }

    void onAttachedToWindow() {
        if (this.mShowErrorAfterAttach) {
            showError();
            this.mShowErrorAfterAttach = false;
        }
        ViewTreeObserver viewTreeObserver = this.mTextView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            InsertionPointCursorController insertionPointCursorController = this.mInsertionPointCursorController;
            if (insertionPointCursorController != null) {
                viewTreeObserver.addOnTouchModeChangeListener(insertionPointCursorController);
            }
            SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
            if (selectionModifierCursorController != null) {
                selectionModifierCursorController.resetTouchOffsets();
                viewTreeObserver.addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
            }
            viewTreeObserver.addOnDrawListener(this.mMagnifierOnDrawListener);
        }
        updateSpellCheckSpans(0, this.mTextView.getText().length(), true);
        if (this.mTextView.hasSelection()) {
            refreshTextActionMode();
        }
        getPositionListener().addSubscriber(this.mCursorAnchorInfoNotifier, true);
        resumeBlink();
    }

    void onDetachedFromWindow() {
        getPositionListener().removeSubscriber(this.mCursorAnchorInfoNotifier);
        if (this.mError != null) {
            hideError();
        }
        suspendBlink();
        InsertionPointCursorController insertionPointCursorController = this.mInsertionPointCursorController;
        if (insertionPointCursorController != null) {
            insertionPointCursorController.onDetached();
        }
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController != null) {
            selectionModifierCursorController.onDetached();
        }
        Runnable runnable = this.mShowSuggestionRunnable;
        if (runnable != null) {
            this.mTextView.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.mInsertionActionModeRunnable;
        if (runnable2 != null) {
            this.mTextView.removeCallbacks(runnable2);
        }
        this.mTextView.removeCallbacks(this.mShowFloatingToolbar);
        discardTextDisplayLists();
        SpellChecker spellChecker = this.mSpellChecker;
        if (spellChecker != null) {
            spellChecker.closeSession();
            this.mSpellChecker = null;
        }
        ViewTreeObserver viewTreeObserver = this.mTextView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnDrawListener(this.mMagnifierOnDrawListener);
        }
        hideCursorAndSpanControllers();
        stopTextActionModeWithPreservingSelection();
    }

    private void discardTextDisplayLists() {
        if (this.mTextRenderNodes == null) {
            return;
        }
        int i = 0;
        while (true) {
            TextRenderNode[] textRenderNodeArr = this.mTextRenderNodes;
            if (i >= textRenderNodeArr.length) {
                return;
            }
            RenderNode renderNode = textRenderNodeArr[i] != null ? textRenderNodeArr[i].renderNode : null;
            if (renderNode != null && renderNode.hasDisplayList()) {
                renderNode.discardDisplayList();
            }
            i++;
        }
    }

    private void showError() {
        if (this.mTextView.getWindowToken() == null) {
            this.mShowErrorAfterAttach = true;
            return;
        }
        if (this.mErrorPopup == null) {
            TextView textView = (TextView) LayoutInflater.from(this.mTextView.getContext()).inflate(R.layout.textview_hint, (ViewGroup) null);
            float f = this.mTextView.getResources().getDisplayMetrics().density;
            ErrorPopup errorPopup = new ErrorPopup(textView, (int) ((200.0f * f) + 0.5f), (int) ((f * 50.0f) + 0.5f));
            this.mErrorPopup = errorPopup;
            errorPopup.setFocusable(false);
            this.mErrorPopup.setInputMethodMode(1);
        }
        TextView textView2 = (TextView) this.mErrorPopup.getContentView();
        chooseSize(this.mErrorPopup, this.mError, textView2);
        textView2.setText(this.mError);
        this.mErrorPopup.showAsDropDown(this.mTextView, getErrorX(), getErrorY(), 51);
        ErrorPopup errorPopup2 = this.mErrorPopup;
        errorPopup2.fixDirection(errorPopup2.isAboveAnchor());
    }

    public void setError(CharSequence charSequence, Drawable drawable) {
        CharSequence charSequenceStringOrSpannedString = TextUtils.stringOrSpannedString(charSequence);
        this.mError = charSequenceStringOrSpannedString;
        this.mErrorWasChanged = true;
        if (charSequenceStringOrSpannedString == null) {
            setErrorIcon(null);
            ErrorPopup errorPopup = this.mErrorPopup;
            if (errorPopup != null) {
                if (errorPopup.isShowing()) {
                    this.mErrorPopup.dismiss();
                }
                this.mErrorPopup = null;
            }
            this.mShowErrorAfterAttach = false;
            return;
        }
        setErrorIcon(drawable);
        if (this.mTextView.isFocused()) {
            showError();
        }
    }

    private void setErrorIcon(Drawable drawable) {
        TextView.Drawables drawables = this.mTextView.mDrawables;
        if (drawables == null) {
            TextView textView = this.mTextView;
            TextView.Drawables drawables2 = new TextView.Drawables(this.mTextView.getContext());
            textView.mDrawables = drawables2;
            drawables = drawables2;
        }
        drawables.setErrorDrawable(drawable, this.mTextView);
        this.mTextView.resetResolvedDrawables();
        this.mTextView.invalidate();
        this.mTextView.requestLayout();
    }

    private void hideError() {
        ErrorPopup errorPopup = this.mErrorPopup;
        if (errorPopup != null && errorPopup.isShowing()) {
            this.mErrorPopup.dismiss();
        }
        this.mShowErrorAfterAttach = false;
    }

    private int getErrorX() {
        float f = this.mTextView.getResources().getDisplayMetrics().density;
        TextView.Drawables drawables = this.mTextView.mDrawables;
        if (this.mTextView.getLayoutDirection() != 1) {
            return ((this.mTextView.getWidth() - this.mErrorPopup.getWidth()) - this.mTextView.getPaddingRight()) + ((-(drawables != null ? drawables.mDrawableSizeRight : 0)) / 2) + ((int) ((f * 25.0f) + 0.5f));
        }
        return this.mTextView.getPaddingLeft() + (((drawables != null ? drawables.mDrawableSizeLeft : 0) / 2) - ((int) ((f * 25.0f) + 0.5f)));
    }

    private int getErrorY() {
        int compoundPaddingTop = this.mTextView.getCompoundPaddingTop();
        int bottom = ((this.mTextView.getBottom() - this.mTextView.getTop()) - this.mTextView.getCompoundPaddingBottom()) - compoundPaddingTop;
        TextView.Drawables drawables = this.mTextView.mDrawables;
        int i = 0;
        if (this.mTextView.getLayoutDirection() != 1) {
            if (drawables != null) {
                i = drawables.mDrawableHeightRight;
            }
        } else if (drawables != null) {
            i = drawables.mDrawableHeightLeft;
        }
        return (((compoundPaddingTop + ((bottom - i) / 2)) + i) - this.mTextView.getHeight()) - ((int) ((this.mTextView.getResources().getDisplayMetrics().density * 2.0f) + 0.5f));
    }

    void createInputContentTypeIfNeeded() {
        if (this.mInputContentType == null) {
            this.mInputContentType = new InputContentType();
        }
    }

    void createInputMethodStateIfNeeded() {
        if (this.mInputMethodState == null) {
            this.mInputMethodState = new InputMethodState();
        }
    }

    private boolean isCursorVisible() {
        return this.mCursorVisible && this.mTextView.isTextEditable();
    }

    boolean shouldRenderCursor() {
        if (isCursorVisible()) {
            return this.mRenderCursorRegardlessTiming || (SystemClock.uptimeMillis() - this.mShowCursor) % 1000 < 500;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void prepareCursorControllers() {
        /*
            r5 = this;
            android.widget.TextView r0 = r5.mTextView
            android.view.View r0 = r0.getRootView()
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            boolean r1 = r0 instanceof android.view.WindowManager.LayoutParams
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L20
            android.view.WindowManager$LayoutParams r0 = (android.view.WindowManager.LayoutParams) r0
            int r1 = r0.type
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r1 < r4) goto L1e
            int r0 = r0.type
            r1 = 1999(0x7cf, float:2.801E-42)
            if (r0 <= r1) goto L20
        L1e:
            r0 = r2
            goto L21
        L20:
            r0 = r3
        L21:
            if (r0 == 0) goto L2d
            android.widget.TextView r0 = r5.mTextView
            android.text.Layout r0 = r0.getLayout()
            if (r0 == 0) goto L2d
            r0 = r2
            goto L2e
        L2d:
            r0 = r3
        L2e:
            if (r0 == 0) goto L38
            boolean r1 = r5.isCursorVisible()
            if (r1 == 0) goto L38
            r1 = r2
            goto L39
        L38:
            r1 = r3
        L39:
            r5.mInsertionControllerEnabled = r1
            if (r0 == 0) goto L46
            android.widget.TextView r0 = r5.mTextView
            boolean r0 = r0.textCanBeSelected()
            if (r0 == 0) goto L46
            goto L47
        L46:
            r2 = r3
        L47:
            r5.mSelectionControllerEnabled = r2
            boolean r0 = r5.mInsertionControllerEnabled
            r1 = 0
            if (r0 != 0) goto L5a
            r5.hideInsertionPointCursorController()
            android.widget.Editor$InsertionPointCursorController r0 = r5.mInsertionPointCursorController
            if (r0 == 0) goto L5a
            r0.onDetached()
            r5.mInsertionPointCursorController = r1
        L5a:
            boolean r0 = r5.mSelectionControllerEnabled
            if (r0 != 0) goto L6a
            r5.lambda$startActionModeInternal$0$Editor()
            android.widget.Editor$SelectionModifierCursorController r0 = r5.mSelectionModifierCursorController
            if (r0 == 0) goto L6a
            r0.onDetached()
            r5.mSelectionModifierCursorController = r1
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.prepareCursorControllers():void");
    }

    void hideInsertionPointCursorController() {
        InsertionPointCursorController insertionPointCursorController = this.mInsertionPointCursorController;
        if (insertionPointCursorController != null) {
            insertionPointCursorController.hide();
        }
    }

    void hideCursorAndSpanControllers() {
        hideCursorControllers();
        hideSpanControllers();
    }

    private void hideSpanControllers() {
        SpanController spanController = this.mSpanController;
        if (spanController != null) {
            spanController.hide();
        }
    }

    private void hideCursorControllers() {
        if (this.mSuggestionsPopupWindow != null && (this.mTextView.isInExtractedMode() || !this.mSuggestionsPopupWindow.isShowingUp())) {
            this.mSuggestionsPopupWindow.hide();
        }
        hideInsertionPointCursorController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSpellCheckSpans(int i, int i2, boolean z) {
        this.mTextView.removeAdjacentSuggestionSpans(i);
        this.mTextView.removeAdjacentSuggestionSpans(i2);
        if (this.mTextView.isTextEditable() && this.mTextView.isSuggestionsEnabled() && !this.mTextView.isInExtractedMode()) {
            if (this.mSpellChecker == null && z) {
                this.mSpellChecker = new SpellChecker(this.mTextView);
            }
            SpellChecker spellChecker = this.mSpellChecker;
            if (spellChecker != null) {
                spellChecker.spellCheck(i, i2);
            }
        }
    }

    void onScreenStateChanged(int i) {
        if (i == 0) {
            suspendBlink();
        } else {
            if (i != 1) {
                return;
            }
            resumeBlink();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void suspendBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.uncancel();
            makeBlink();
        }
    }

    void adjustInputType(boolean z, boolean z2, boolean z3, boolean z4) {
        int i = this.mInputType;
        if ((i & 15) != 1) {
            if ((i & 15) == 2 && z4) {
                this.mInputType = (i & (-4081)) | 16;
                return;
            }
            return;
        }
        if (z || z2) {
            this.mInputType = (this.mInputType & (-4081)) | 128;
        }
        if (z3) {
            this.mInputType = (this.mInputType & (-4081)) | 224;
        }
    }

    private void chooseSize(PopupWindow popupWindow, CharSequence charSequence, TextView textView) {
        int paddingLeft = textView.getPaddingLeft() + textView.getPaddingRight();
        int paddingTop = textView.getPaddingTop() + textView.getPaddingBottom();
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textView.getPaint(), this.mTextView.getResources().getDimensionPixelSize(R.dimen.textview_error_popup_default_width)).setUseLineSpacingFromFallbacks(textView.mUseFallbackLineSpacing).build();
        float fMax = 0.0f;
        for (int i = 0; i < staticLayoutBuild.getLineCount(); i++) {
            fMax = Math.max(fMax, staticLayoutBuild.getLineWidth(i));
        }
        popupWindow.setWidth(paddingLeft + ((int) Math.ceil(fMax)));
        popupWindow.setHeight(paddingTop + staticLayoutBuild.getHeight());
    }

    void setFrame() {
        ErrorPopup errorPopup = this.mErrorPopup;
        if (errorPopup != null) {
            chooseSize(this.mErrorPopup, this.mError, (TextView) errorPopup.getContentView());
            this.mErrorPopup.update(this.mTextView, getErrorX(), getErrorY(), this.mErrorPopup.getWidth(), this.mErrorPopup.getHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getWordStart(int i) {
        int prevWordBeginningOnTwoWordsBoundary;
        if (getWordIteratorWithText().isOnPunctuation(getWordIteratorWithText().prevBoundary(i))) {
            prevWordBeginningOnTwoWordsBoundary = getWordIteratorWithText().getPunctuationBeginning(i);
        } else {
            prevWordBeginningOnTwoWordsBoundary = getWordIteratorWithText().getPrevWordBeginningOnTwoWordsBoundary(i);
        }
        return prevWordBeginningOnTwoWordsBoundary == -1 ? i : prevWordBeginningOnTwoWordsBoundary;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getWordEnd(int i) {
        int nextWordEndOnTwoWordBoundary;
        if (getWordIteratorWithText().isAfterPunctuation(getWordIteratorWithText().nextBoundary(i))) {
            nextWordEndOnTwoWordBoundary = getWordIteratorWithText().getPunctuationEnd(i);
        } else {
            nextWordEndOnTwoWordBoundary = getWordIteratorWithText().getNextWordEndOnTwoWordBoundary(i);
        }
        return nextWordEndOnTwoWordBoundary == -1 ? i : nextWordEndOnTwoWordBoundary;
    }

    private boolean needsToSelectAllToSelectWordOrParagraph() {
        if (this.mTextView.hasPasswordTransformationMethod()) {
            return true;
        }
        int inputType = this.mTextView.getInputType();
        int i = inputType & 15;
        int i2 = inputType & 4080;
        return i == 2 || i == 3 || i == 4 || i2 == 16 || i2 == 32 || i2 == 208 || i2 == 176;
    }

    boolean selectCurrentWord() {
        int spanStart;
        int spanEnd;
        if (!this.mTextView.canSelectText()) {
            return false;
        }
        if (needsToSelectAllToSelectWordOrParagraph()) {
            return this.mTextView.selectAllText();
        }
        long lastTouchOffsets = getLastTouchOffsets();
        int iUnpackRangeStartFromLong = TextUtils.unpackRangeStartFromLong(lastTouchOffsets);
        int iUnpackRangeEndFromLong = TextUtils.unpackRangeEndFromLong(lastTouchOffsets);
        if (iUnpackRangeStartFromLong < 0 || iUnpackRangeStartFromLong > this.mTextView.getText().length() || iUnpackRangeEndFromLong < 0 || iUnpackRangeEndFromLong > this.mTextView.getText().length()) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) ((Spanned) this.mTextView.getText()).getSpans(iUnpackRangeStartFromLong, iUnpackRangeEndFromLong, URLSpan.class);
        if (uRLSpanArr.length >= 1) {
            URLSpan uRLSpan = uRLSpanArr[0];
            spanStart = ((Spanned) this.mTextView.getText()).getSpanStart(uRLSpan);
            spanEnd = ((Spanned) this.mTextView.getText()).getSpanEnd(uRLSpan);
        } else {
            WordIterator wordIterator = getWordIterator();
            wordIterator.setCharSequence(this.mTextView.getText(), iUnpackRangeStartFromLong, iUnpackRangeEndFromLong);
            int beginning = wordIterator.getBeginning(iUnpackRangeStartFromLong);
            int end = wordIterator.getEnd(iUnpackRangeEndFromLong);
            if (beginning == -1 || end == -1 || beginning == end) {
                long charClusterRange = getCharClusterRange(iUnpackRangeStartFromLong);
                int iUnpackRangeStartFromLong2 = TextUtils.unpackRangeStartFromLong(charClusterRange);
                int iUnpackRangeEndFromLong2 = TextUtils.unpackRangeEndFromLong(charClusterRange);
                spanStart = iUnpackRangeStartFromLong2;
                spanEnd = iUnpackRangeEndFromLong2;
            } else {
                spanEnd = end;
                spanStart = beginning;
            }
        }
        Selection.setSelection((Spannable) this.mTextView.getText(), spanStart, spanEnd);
        return spanEnd > spanStart;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean selectCurrentParagraph() {
        if (!this.mTextView.canSelectText()) {
            return false;
        }
        if (needsToSelectAllToSelectWordOrParagraph()) {
            return this.mTextView.selectAllText();
        }
        long lastTouchOffsets = getLastTouchOffsets();
        long paragraphsRange = getParagraphsRange(TextUtils.unpackRangeStartFromLong(lastTouchOffsets), TextUtils.unpackRangeEndFromLong(lastTouchOffsets));
        int iUnpackRangeStartFromLong = TextUtils.unpackRangeStartFromLong(paragraphsRange);
        int iUnpackRangeEndFromLong = TextUtils.unpackRangeEndFromLong(paragraphsRange);
        if (iUnpackRangeStartFromLong >= iUnpackRangeEndFromLong) {
            return false;
        }
        Selection.setSelection((Spannable) this.mTextView.getText(), iUnpackRangeStartFromLong, iUnpackRangeEndFromLong);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getParagraphsRange(int i, int i2) {
        Layout layout = this.mTextView.getLayout();
        if (layout == null) {
            return TextUtils.packRangeInLong(-1, -1);
        }
        CharSequence text = this.mTextView.getText();
        int lineForOffset = layout.getLineForOffset(i);
        while (lineForOffset > 0 && text.charAt(layout.getLineEnd(lineForOffset - 1) - 1) != '\n') {
            lineForOffset--;
        }
        int lineForOffset2 = layout.getLineForOffset(i2);
        while (lineForOffset2 < layout.getLineCount() - 1 && text.charAt(layout.getLineEnd(lineForOffset2) - 1) != '\n') {
            lineForOffset2++;
        }
        return TextUtils.packRangeInLong(layout.getLineStart(lineForOffset), layout.getLineEnd(lineForOffset2));
    }

    void onLocaleChanged() {
        this.mWordIterator = null;
        this.mWordIteratorWithText = null;
    }

    public WordIterator getWordIterator() {
        if (this.mWordIterator == null) {
            this.mWordIterator = new WordIterator(this.mTextView.getTextServicesLocale());
        }
        return this.mWordIterator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WordIterator getWordIteratorWithText() {
        if (this.mWordIteratorWithText == null) {
            this.mWordIteratorWithText = new WordIterator(this.mTextView.getTextServicesLocale());
            this.mUpdateWordIteratorText = true;
        }
        if (this.mUpdateWordIteratorText) {
            CharSequence text = this.mTextView.getText();
            this.mWordIteratorWithText.setCharSequence(text, 0, text.length());
            this.mUpdateWordIteratorText = false;
        }
        return this.mWordIteratorWithText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNextCursorOffset(int i, boolean z) {
        Layout layout = this.mTextView.getLayout();
        return layout == null ? i : z == layout.isRtlCharAt(i) ? layout.getOffsetToLeftOf(i) : layout.getOffsetToRightOf(i);
    }

    private long getCharClusterRange(int i) {
        if (i < this.mTextView.getText().length()) {
            int nextCursorOffset = getNextCursorOffset(i, true);
            return TextUtils.packRangeInLong(getNextCursorOffset(nextCursorOffset, false), nextCursorOffset);
        }
        if (i - 1 >= 0) {
            int nextCursorOffset2 = getNextCursorOffset(i, false);
            return TextUtils.packRangeInLong(nextCursorOffset2, getNextCursorOffset(nextCursorOffset2, true));
        }
        return TextUtils.packRangeInLong(i, i);
    }

    private boolean touchPositionIsInSelection() {
        int selectionStart = this.mTextView.getSelectionStart();
        int selectionEnd = this.mTextView.getSelectionEnd();
        if (selectionStart == selectionEnd) {
            return false;
        }
        if (selectionStart > selectionEnd) {
            Selection.setSelection((Spannable) this.mTextView.getText(), selectionEnd, selectionStart);
            selectionEnd = selectionStart;
            selectionStart = selectionEnd;
        }
        SelectionModifierCursorController selectionController = getSelectionController();
        return selectionController.getMinTouchOffset() >= selectionStart && selectionController.getMaxTouchOffset() < selectionEnd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PositionListener getPositionListener() {
        if (this.mPositionListener == null) {
            this.mPositionListener = new PositionListener();
        }
        return this.mPositionListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOffsetVisible(int i) {
        Layout layout = this.mTextView.getLayout();
        if (layout == null) {
            return false;
        }
        int lineBottom = layout.getLineBottom(layout.getLineForOffset(i));
        int primaryHorizontal = (int) layout.getPrimaryHorizontal(i);
        return this.mTextView.isPositionVisible(primaryHorizontal + r0.viewportToContentHorizontalOffset(), lineBottom + this.mTextView.viewportToContentVerticalOffset());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPositionOnText(float f, float f2) {
        Layout layout = this.mTextView.getLayout();
        if (layout == null) {
            return false;
        }
        int lineAtCoordinate = this.mTextView.getLineAtCoordinate(f2);
        float fConvertToLocalHorizontalCoordinate = this.mTextView.convertToLocalHorizontalCoordinate(f);
        return fConvertToLocalHorizontalCoordinate >= layout.getLineLeft(lineAtCoordinate) && fConvertToLocalHorizontalCoordinate <= layout.getLineRight(lineAtCoordinate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDragAndDrop() {
        getSelectionActionModeHelper().onSelectionDrag();
        if (this.mTextView.isInExtractedMode()) {
            return;
        }
        int selectionStart = this.mTextView.getSelectionStart();
        int selectionEnd = this.mTextView.getSelectionEnd();
        this.mTextView.startDragAndDrop(ClipData.newPlainText(null, this.mTextView.getTransformedText(selectionStart, selectionEnd)), getTextThumbnailBuilder(selectionStart, selectionEnd), new DragLocalState(this.mTextView, selectionStart, selectionEnd), 256);
        lambda$startActionModeInternal$0$Editor();
        if (hasSelectionController()) {
            getSelectionController().resetTouchOffsets();
        }
    }

    public boolean performLongClick(boolean z) {
        if (this.mIsBeingLongClickedByAccessibility) {
            if (!z) {
                toggleInsertionActionMode();
            }
            return true;
        }
        if (!z && !isPositionOnText(this.mTouchState.getLastDownX(), this.mTouchState.getLastDownY()) && !this.mTouchState.isOnHandle() && this.mInsertionControllerEnabled) {
            Selection.setSelection((Spannable) this.mTextView.getText(), this.mTextView.getOffsetForPosition(this.mTouchState.getLastDownX(), this.mTouchState.getLastDownY()));
            getInsertionController().show();
            this.mIsInsertionActionModeStartPending = true;
            MetricsLogger.action(this.mTextView.getContext(), 629, 0);
            z = true;
        }
        if (!z && this.mTextActionMode != null) {
            if (touchPositionIsInSelection()) {
                startDragAndDrop();
                MetricsLogger.action(this.mTextView.getContext(), 629, 2);
            } else {
                lambda$startActionModeInternal$0$Editor();
                selectCurrentWordAndStartDrag();
                MetricsLogger.action(this.mTextView.getContext(), 629, 1);
            }
            z = true;
        }
        if (!z && (z = selectCurrentWordAndStartDrag())) {
            MetricsLogger.action(this.mTextView.getContext(), 629, 1);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleInsertionActionMode() {
        if (this.mTextActionMode != null) {
            lambda$startActionModeInternal$0$Editor();
        } else {
            startInsertionActionMode();
        }
    }

    float getLastUpPositionX() {
        return this.mTouchState.getLastUpX();
    }

    float getLastUpPositionY() {
        return this.mTouchState.getLastUpY();
    }

    private long getLastTouchOffsets() {
        SelectionModifierCursorController selectionController = getSelectionController();
        return TextUtils.packRangeInLong(selectionController.getMinTouchOffset(), selectionController.getMaxTouchOffset());
    }

    void onFocusChanged(boolean z, int i) {
        this.mShowCursor = SystemClock.uptimeMillis();
        ensureEndedBatchEdit();
        if (z) {
            int selectionStart = this.mTextView.getSelectionStart();
            int selectionEnd = this.mTextView.getSelectionEnd();
            this.mCreatedWithASelection = this.mFrozenWithFocus && this.mTextView.hasSelection() && !(this.mSelectAllOnFocus && selectionStart == 0 && selectionEnd == this.mTextView.getText().length());
            if (!this.mFrozenWithFocus || selectionStart < 0 || selectionEnd < 0) {
                int lastTapPosition = getLastTapPosition();
                if (lastTapPosition >= 0) {
                    Selection.setSelection((Spannable) this.mTextView.getText(), lastTapPosition);
                }
                MovementMethod movementMethod = this.mTextView.getMovementMethod();
                if (movementMethod != null) {
                    TextView textView = this.mTextView;
                    movementMethod.onTakeFocus(textView, (Spannable) textView.getText(), i);
                }
                if ((this.mTextView.isInExtractedMode() || this.mSelectionMoved) && selectionStart >= 0 && selectionEnd >= 0) {
                    Selection.setSelection((Spannable) this.mTextView.getText(), selectionStart, selectionEnd);
                }
                if (this.mSelectAllOnFocus) {
                    this.mTextView.selectAllText();
                }
                this.mTouchFocusSelected = true;
            }
            this.mFrozenWithFocus = false;
            this.mSelectionMoved = false;
            if (this.mError != null) {
                showError();
            }
            makeBlink();
            return;
        }
        if (this.mError != null) {
            hideError();
        }
        this.mTextView.onEndBatchEdit();
        if (this.mTextView.isInExtractedMode()) {
            hideCursorAndSpanControllers();
            stopTextActionModeWithPreservingSelection();
        } else {
            hideCursorAndSpanControllers();
            if (this.mTextView.isTemporarilyDetached()) {
                stopTextActionModeWithPreservingSelection();
            } else {
                lambda$startActionModeInternal$0$Editor();
            }
            downgradeEasyCorrectionSpans();
        }
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController != null) {
            selectionModifierCursorController.resetTouchOffsets();
        }
        ensureNoSelectionIfNonSelectable();
    }

    private void ensureNoSelectionIfNonSelectable() {
        if (this.mTextView.textCanBeSelected() || !this.mTextView.hasSelection()) {
            return;
        }
        Selection.setSelection((Spannable) this.mTextView.getText(), this.mTextView.length(), this.mTextView.length());
    }

    private void downgradeEasyCorrectionSpans() {
        CharSequence text = this.mTextView.getText();
        if (text instanceof Spannable) {
            Spannable spannable = (Spannable) text;
            SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spannable.getSpans(0, spannable.length(), SuggestionSpan.class);
            for (int i = 0; i < suggestionSpanArr.length; i++) {
                int flags = suggestionSpanArr[i].getFlags();
                if ((flags & 1) != 0 && (flags & 2) == 0) {
                    suggestionSpanArr[i].setFlags(flags & (-2));
                }
            }
        }
    }

    void sendOnTextChanged(int i, int i2, int i3) {
        getSelectionActionModeHelper().onTextChanged(i, i2 + i);
        updateSpellCheckSpans(i, i3 + i, false);
        this.mUpdateWordIteratorText = true;
        hideCursorControllers();
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController != null) {
            selectionModifierCursorController.resetTouchOffsets();
        }
        lambda$startActionModeInternal$0$Editor();
    }

    private int getLastTapPosition() {
        int minTouchOffset;
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController == null || (minTouchOffset = selectionModifierCursorController.getMinTouchOffset()) < 0) {
            return -1;
        }
        return minTouchOffset > this.mTextView.getText().length() ? this.mTextView.getText().length() : minTouchOffset;
    }

    void onWindowFocusChanged(boolean z) {
        if (z) {
            Blink blink = this.mBlink;
            if (blink != null) {
                blink.uncancel();
                makeBlink();
            }
            if (!this.mTextView.hasSelection() || extractedTextModeWillBeStarted()) {
                return;
            }
            refreshTextActionMode();
            return;
        }
        Blink blink2 = this.mBlink;
        if (blink2 != null) {
            blink2.cancel();
        }
        InputContentType inputContentType = this.mInputContentType;
        if (inputContentType != null) {
            inputContentType.enterDown = false;
        }
        hideCursorAndSpanControllers();
        stopTextActionModeWithPreservingSelection();
        SuggestionsPopupWindow suggestionsPopupWindow = this.mSuggestionsPopupWindow;
        if (suggestionsPopupWindow != null) {
            suggestionsPopupWindow.onParentLostFocus();
        }
        ensureEndedBatchEdit();
        ensureNoSelectionIfNonSelectable();
    }

    private boolean shouldFilterOutTouchEvent(MotionEvent motionEvent) {
        if (!motionEvent.isFromSource(8194)) {
            return false;
        }
        boolean z = ((this.mLastButtonState ^ motionEvent.getButtonState()) & 1) != 0;
        int actionMasked = motionEvent.getActionMasked();
        if ((actionMasked == 0 || actionMasked == 1) && !z) {
            return true;
        }
        return actionMasked == 2 && !motionEvent.isButtonPressed(1);
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        boolean zShouldFilterOutTouchEvent = shouldFilterOutTouchEvent(motionEvent);
        this.mLastButtonState = motionEvent.getButtonState();
        if (zShouldFilterOutTouchEvent) {
            if (motionEvent.getActionMasked() == 1) {
                this.mDiscardNextActionUp = true;
                return;
            }
            return;
        }
        this.mTouchState.update(motionEvent, ViewConfiguration.get(this.mTextView.getContext()));
        updateFloatingToolbarVisibility(motionEvent);
        if (hasInsertionController()) {
            getInsertionController().onTouchEvent(motionEvent);
        }
        if (hasSelectionController()) {
            getSelectionController().onTouchEvent(motionEvent);
        }
        Runnable runnable = this.mShowSuggestionRunnable;
        if (runnable != null) {
            this.mTextView.removeCallbacks(runnable);
            this.mShowSuggestionRunnable = null;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.mTouchFocusSelected = false;
            this.mIgnoreActionUpEvent = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatingToolbarVisibility(MotionEvent motionEvent) {
        if (this.mTextActionMode != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    hideFloatingToolbar(-1);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            showFloatingToolbar();
        }
    }

    void hideFloatingToolbar(int i) {
        if (this.mTextActionMode != null) {
            this.mTextView.removeCallbacks(this.mShowFloatingToolbar);
            this.mTextActionMode.hide(i);
        }
    }

    private void showFloatingToolbar() {
        if (this.mTextActionMode != null) {
            this.mTextView.postDelayed(this.mShowFloatingToolbar, ViewConfiguration.getDoubleTapTimeout());
            invalidateActionModeAsync();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputMethodManager getInputMethodManager() {
        return (InputMethodManager) this.mTextView.getContext().getSystemService(InputMethodManager.class);
    }

    public void beginBatchEdit() {
        this.mInBatchEditControllers = true;
        InputMethodState inputMethodState = this.mInputMethodState;
        if (inputMethodState != null) {
            int i = inputMethodState.mBatchEditNesting + 1;
            inputMethodState.mBatchEditNesting = i;
            if (i == 1) {
                inputMethodState.mCursorChanged = false;
                inputMethodState.mChangedDelta = 0;
                if (inputMethodState.mContentChanged) {
                    inputMethodState.mChangedStart = 0;
                    inputMethodState.mChangedEnd = this.mTextView.getText().length();
                } else {
                    inputMethodState.mChangedStart = -1;
                    inputMethodState.mChangedEnd = -1;
                    inputMethodState.mContentChanged = false;
                }
                this.mUndoInputFilter.beginBatchEdit();
                this.mTextView.onBeginBatchEdit();
            }
        }
    }

    public void endBatchEdit() {
        this.mInBatchEditControllers = false;
        InputMethodState inputMethodState = this.mInputMethodState;
        if (inputMethodState != null) {
            int i = inputMethodState.mBatchEditNesting - 1;
            inputMethodState.mBatchEditNesting = i;
            if (i == 0) {
                finishBatchEdit(inputMethodState);
            }
        }
    }

    void ensureEndedBatchEdit() {
        InputMethodState inputMethodState = this.mInputMethodState;
        if (inputMethodState == null || inputMethodState.mBatchEditNesting == 0) {
            return;
        }
        inputMethodState.mBatchEditNesting = 0;
        finishBatchEdit(inputMethodState);
    }

    void finishBatchEdit(InputMethodState inputMethodState) {
        this.mTextView.onEndBatchEdit();
        this.mUndoInputFilter.endBatchEdit();
        if (inputMethodState.mContentChanged || inputMethodState.mSelectionModeChanged) {
            this.mTextView.updateAfterEdit();
            reportExtractedText();
        } else if (inputMethodState.mCursorChanged) {
            this.mTextView.invalidateCursor();
        }
        sendUpdateSelection();
        if (this.mTextActionMode != null) {
            CursorController selectionController = this.mTextView.hasSelection() ? getSelectionController() : getInsertionController();
            if (selectionController == null || selectionController.isActive() || selectionController.isCursorBeingModified()) {
                return;
            }
            selectionController.show();
        }
    }

    boolean extractText(ExtractedTextRequest extractedTextRequest, ExtractedText extractedText) {
        return extractTextInternal(extractedTextRequest, -1, -1, -1, extractedText);
    }

    private boolean extractTextInternal(ExtractedTextRequest extractedTextRequest, int i, int i2, int i3, ExtractedText extractedText) {
        CharSequence text;
        if (extractedTextRequest == null || extractedText == null || (text = this.mTextView.getText()) == null) {
            return false;
        }
        if (i != -2) {
            int length = text.length();
            if (i < 0) {
                extractedText.partialEndOffset = -1;
                extractedText.partialStartOffset = -1;
                i = 0;
            } else {
                int i4 = i2 + i3;
                if (text instanceof Spanned) {
                    Spanned spanned = (Spanned) text;
                    Object[] spans = spanned.getSpans(i, i4, ParcelableSpan.class);
                    int length2 = spans.length;
                    while (length2 > 0) {
                        length2--;
                        int spanStart = spanned.getSpanStart(spans[length2]);
                        if (spanStart < i) {
                            i = spanStart;
                        }
                        int spanEnd = spanned.getSpanEnd(spans[length2]);
                        if (spanEnd > i4) {
                            i4 = spanEnd;
                        }
                    }
                }
                extractedText.partialStartOffset = i;
                extractedText.partialEndOffset = i4 - i3;
                if (i > length) {
                    i = length;
                } else if (i < 0) {
                    i = 0;
                }
                if (i4 <= length) {
                    length = i4 < 0 ? 0 : i4;
                }
            }
            if ((extractedTextRequest.flags & 1) != 0) {
                extractedText.text = text.subSequence(i, length);
            } else {
                extractedText.text = TextUtils.substring(text, i, length);
            }
        } else {
            extractedText.partialStartOffset = 0;
            extractedText.partialEndOffset = 0;
            extractedText.text = "";
        }
        extractedText.flags = 0;
        if (MetaKeyKeyListener.getMetaState(text, 2048) != 0) {
            extractedText.flags |= 2;
        }
        if (this.mTextView.isSingleLine()) {
            extractedText.flags |= 1;
        }
        extractedText.startOffset = 0;
        extractedText.selectionStart = this.mTextView.getSelectionStart();
        extractedText.selectionEnd = this.mTextView.getSelectionEnd();
        extractedText.hint = this.mTextView.getHint();
        return true;
    }

    boolean reportExtractedText() {
        InputMethodManager inputMethodManager;
        InputMethodState inputMethodState = this.mInputMethodState;
        if (inputMethodState == null) {
            return false;
        }
        boolean z = inputMethodState.mContentChanged;
        if (!z && !inputMethodState.mSelectionModeChanged) {
            return false;
        }
        inputMethodState.mContentChanged = false;
        inputMethodState.mSelectionModeChanged = false;
        ExtractedTextRequest extractedTextRequest = inputMethodState.mExtractedTextRequest;
        if (extractedTextRequest == null || (inputMethodManager = getInputMethodManager()) == null) {
            return false;
        }
        if (inputMethodState.mChangedStart < 0 && !z) {
            inputMethodState.mChangedStart = -2;
        }
        if (!extractTextInternal(extractedTextRequest, inputMethodState.mChangedStart, inputMethodState.mChangedEnd, inputMethodState.mChangedDelta, inputMethodState.mExtractedText)) {
            return false;
        }
        inputMethodManager.updateExtractedText(this.mTextView, extractedTextRequest.token, inputMethodState.mExtractedText);
        inputMethodState.mChangedStart = -1;
        inputMethodState.mChangedEnd = -1;
        inputMethodState.mChangedDelta = 0;
        inputMethodState.mContentChanged = false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdateSelection() {
        InputMethodManager inputMethodManager;
        int i;
        int composingSpanEnd;
        InputMethodState inputMethodState = this.mInputMethodState;
        if (inputMethodState == null || inputMethodState.mBatchEditNesting > 0 || (inputMethodManager = getInputMethodManager()) == null) {
            return;
        }
        int selectionStart = this.mTextView.getSelectionStart();
        int selectionEnd = this.mTextView.getSelectionEnd();
        if (this.mTextView.getText() instanceof Spannable) {
            Spannable spannable = (Spannable) this.mTextView.getText();
            int composingSpanStart = EditableInputConnection.getComposingSpanStart(spannable);
            composingSpanEnd = EditableInputConnection.getComposingSpanEnd(spannable);
            i = composingSpanStart;
        } else {
            i = -1;
            composingSpanEnd = -1;
        }
        inputMethodManager.updateSelection(this.mTextView, selectionStart, selectionEnd, i, composingSpanEnd);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void onDraw(android.graphics.Canvas r10, android.text.Layout r11, android.graphics.Path r12, android.graphics.Paint r13, int r14) {
        /*
            r9 = this;
            android.widget.TextView r0 = r9.mTextView
            int r0 = r0.getSelectionStart()
            android.widget.TextView r1 = r9.mTextView
            int r1 = r1.getSelectionEnd()
            android.widget.Editor$InputMethodState r2 = r9.mInputMethodState
            if (r2 == 0) goto L2d
            int r3 = r2.mBatchEditNesting
            if (r3 != 0) goto L2d
            android.view.inputmethod.InputMethodManager r3 = r9.getInputMethodManager()
            if (r3 == 0) goto L2d
            android.widget.TextView r4 = r9.mTextView
            boolean r3 = r3.isActive(r4)
            if (r3 == 0) goto L2d
            boolean r3 = r2.mContentChanged
            if (r3 != 0) goto L2a
            boolean r2 = r2.mSelectionModeChanged
            if (r2 == 0) goto L2d
        L2a:
            r9.reportExtractedText()
        L2d:
            android.widget.Editor$CorrectionHighlighter r2 = r9.mCorrectionHighlighter
            if (r2 == 0) goto L34
            r2.draw(r10, r14)
        L34:
            r2 = 0
            if (r12 == 0) goto L41
            if (r0 != r1) goto L41
            android.graphics.drawable.Drawable r0 = r9.mDrawableForCursor
            if (r0 == 0) goto L41
            r9.drawCursor(r10, r14)
            r12 = r2
        L41:
            android.widget.SelectionActionModeHelper r0 = r9.mSelectionActionModeHelper
            if (r0 == 0) goto L52
            r0.onDraw(r10)
            android.widget.SelectionActionModeHelper r0 = r9.mSelectionActionModeHelper
            boolean r0 = r0.isDrawingHighlight()
            if (r0 == 0) goto L52
            r6 = r2
            goto L53
        L52:
            r6 = r12
        L53:
            android.widget.TextView r12 = r9.mTextView
            boolean r12 = r12.canHaveDisplayList()
            if (r12 == 0) goto L6a
            boolean r12 = r10.isHardwareAccelerated()
            if (r12 == 0) goto L6a
            r3 = r9
            r4 = r10
            r5 = r11
            r7 = r13
            r8 = r14
            r3.drawHardwareAccelerated(r4, r5, r6, r7, r8)
            goto L6d
        L6a:
            r11.draw(r10, r6, r13, r14)
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.onDraw(android.graphics.Canvas, android.text.Layout, android.graphics.Path, android.graphics.Paint, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x012d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawHardwareAccelerated(android.graphics.Canvas r25, android.text.Layout r26, android.graphics.Path r27, android.graphics.Paint r28, int r29) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.drawHardwareAccelerated(android.graphics.Canvas, android.text.Layout, android.graphics.Path, android.graphics.Paint, int):void");
    }

    private int drawHardwareAcceleratedInner(Canvas canvas, Layout layout, Path path, Paint paint, int i, int[] iArr, int[] iArr2, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = iArr[i2];
        int availableDisplayListIndex = iArr2[i2];
        if (availableDisplayListIndex == -1) {
            availableDisplayListIndex = getAvailableDisplayListIndex(iArr2, i3, i4);
            iArr2[i2] = availableDisplayListIndex;
            TextRenderNode[] textRenderNodeArr = this.mTextRenderNodes;
            if (textRenderNodeArr[availableDisplayListIndex] != null) {
                textRenderNodeArr[availableDisplayListIndex].isDirty = true;
            }
            i5 = availableDisplayListIndex + 1;
        } else {
            i5 = i4;
        }
        TextRenderNode[] textRenderNodeArr2 = this.mTextRenderNodes;
        if (textRenderNodeArr2[availableDisplayListIndex] == null) {
            textRenderNodeArr2[availableDisplayListIndex] = new TextRenderNode("Text " + availableDisplayListIndex);
        }
        boolean zNeedsRecord = this.mTextRenderNodes[availableDisplayListIndex].needsRecord();
        RenderNode renderNode = this.mTextRenderNodes[availableDisplayListIndex].renderNode;
        if (this.mTextRenderNodes[availableDisplayListIndex].needsToBeShifted || zNeedsRecord) {
            int i8 = i2 == 0 ? 0 : iArr[i2 - 1] + 1;
            int lineTop = layout.getLineTop(i8);
            int lineBottom = layout.getLineBottom(i7);
            int width = this.mTextView.getWidth();
            if (this.mTextView.getHorizontallyScrolling()) {
                float fMin = Float.MAX_VALUE;
                float fMax = Float.MIN_VALUE;
                for (int i9 = i8; i9 <= i7; i9++) {
                    fMin = Math.min(fMin, layout.getLineLeft(i9));
                    fMax = Math.max(fMax, layout.getLineRight(i9));
                }
                int i10 = (int) (fMax + 0.5f);
                i6 = (int) fMin;
                width = i10;
            } else {
                i6 = 0;
            }
            if (zNeedsRecord) {
                RecordingCanvas recordingCanvasBeginRecording = renderNode.beginRecording(width - i6, lineBottom - lineTop);
                try {
                    recordingCanvasBeginRecording.translate(-i6, -lineTop);
                    layout.drawText(recordingCanvasBeginRecording, i8, i7);
                    this.mTextRenderNodes[availableDisplayListIndex].isDirty = false;
                } finally {
                    renderNode.endRecording();
                    renderNode.setClipToBounds(false);
                }
            }
            renderNode.setLeftTopRightBottom(i6, lineTop, width, lineBottom);
            this.mTextRenderNodes[availableDisplayListIndex].needsToBeShifted = false;
        }
        ((RecordingCanvas) canvas).drawRenderNode(renderNode);
        return i5;
    }

    private int getAvailableDisplayListIndex(int[] iArr, int i, int i2) {
        int length = this.mTextRenderNodes.length;
        while (i2 < length) {
            boolean z = false;
            int i3 = 0;
            while (true) {
                if (i3 >= i) {
                    break;
                }
                if (iArr[i3] == i2) {
                    z = true;
                    break;
                }
                i3++;
            }
            if (!z) {
                return i2;
            }
            i2++;
        }
        this.mTextRenderNodes = (TextRenderNode[]) GrowingArrayUtils.append(this.mTextRenderNodes, length, (Object) null);
        return length;
    }

    private void drawCursor(Canvas canvas, int i) {
        boolean z = i != 0;
        if (z) {
            canvas.translate(0.0f, i);
        }
        Drawable drawable = this.mDrawableForCursor;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        if (z) {
            canvas.translate(0.0f, -i);
        }
    }

    void invalidateHandlesAndActionMode() {
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController != null) {
            selectionModifierCursorController.invalidateHandles();
        }
        InsertionPointCursorController insertionPointCursorController = this.mInsertionPointCursorController;
        if (insertionPointCursorController != null) {
            insertionPointCursorController.invalidateHandle();
        }
        if (this.mTextActionMode != null) {
            invalidateActionMode();
        }
    }

    void invalidateTextDisplayList(Layout layout, int i, int i2) {
        if (this.mTextRenderNodes == null || !(layout instanceof DynamicLayout)) {
            return;
        }
        int lineForOffset = layout.getLineForOffset(i);
        int lineForOffset2 = layout.getLineForOffset(i2);
        DynamicLayout dynamicLayout = (DynamicLayout) layout;
        int[] blockEndLines = dynamicLayout.getBlockEndLines();
        int[] blockIndices = dynamicLayout.getBlockIndices();
        int numberOfBlocks = dynamicLayout.getNumberOfBlocks();
        int i3 = 0;
        while (i3 < numberOfBlocks && blockEndLines[i3] < lineForOffset) {
            i3++;
        }
        while (i3 < numberOfBlocks) {
            int i4 = blockIndices[i3];
            if (i4 != -1) {
                this.mTextRenderNodes[i4].isDirty = true;
            }
            if (blockEndLines[i3] >= lineForOffset2) {
                return;
            } else {
                i3++;
            }
        }
    }

    void invalidateTextDisplayList() {
        if (this.mTextRenderNodes == null) {
            return;
        }
        int i = 0;
        while (true) {
            TextRenderNode[] textRenderNodeArr = this.mTextRenderNodes;
            if (i >= textRenderNodeArr.length) {
                return;
            }
            if (textRenderNodeArr[i] != null) {
                textRenderNodeArr[i].isDirty = true;
            }
            i++;
        }
    }

    void updateCursorPosition() {
        loadCursorDrawable();
        if (this.mDrawableForCursor == null) {
            return;
        }
        Layout layout = this.mTextView.getLayout();
        int selectionStart = this.mTextView.getSelectionStart();
        int lineForOffset = layout.getLineForOffset(selectionStart);
        updateCursorPosition(layout.getLineTop(lineForOffset), layout.getLineBottomWithoutSpacing(lineForOffset), layout.getPrimaryHorizontal(selectionStart, layout.shouldClampCursor(lineForOffset)));
    }

    void refreshTextActionMode() {
        if (extractedTextModeWillBeStarted()) {
            this.mRestartActionModeOnNextRefresh = false;
            return;
        }
        boolean zHasSelection = this.mTextView.hasSelection();
        SelectionModifierCursorController selectionController = getSelectionController();
        InsertionPointCursorController insertionController = getInsertionController();
        if ((selectionController != null && selectionController.isCursorBeingModified()) || (insertionController != null && insertionController.isCursorBeingModified())) {
            this.mRestartActionModeOnNextRefresh = false;
            return;
        }
        if (zHasSelection) {
            hideInsertionPointCursorController();
            if (this.mTextActionMode == null) {
                if (this.mRestartActionModeOnNextRefresh) {
                    startSelectionActionModeAsync(false);
                }
            } else if (selectionController == null || !selectionController.isActive()) {
                stopTextActionModeWithPreservingSelection();
                startSelectionActionModeAsync(false);
            } else {
                this.mTextActionMode.invalidateContentRect();
            }
        } else if (insertionController == null || !insertionController.isActive()) {
            lambda$startActionModeInternal$0$Editor();
        } else {
            ActionMode actionMode = this.mTextActionMode;
            if (actionMode != null) {
                actionMode.invalidateContentRect();
            }
        }
        this.mRestartActionModeOnNextRefresh = false;
    }

    void startInsertionActionMode() {
        Runnable runnable = this.mInsertionActionModeRunnable;
        if (runnable != null) {
            this.mTextView.removeCallbacks(runnable);
        }
        if (extractedTextModeWillBeStarted()) {
            return;
        }
        lambda$startActionModeInternal$0$Editor();
        ActionMode actionModeStartActionMode = this.mTextView.startActionMode(new TextActionModeCallback(1), 1);
        this.mTextActionMode = actionModeStartActionMode;
        if (actionModeStartActionMode == null || getInsertionController() == null) {
            return;
        }
        getInsertionController().show();
    }

    TextView getTextView() {
        return this.mTextView;
    }

    ActionMode getTextActionMode() {
        return this.mTextActionMode;
    }

    void setRestartActionModeOnNextRefresh(boolean z) {
        this.mRestartActionModeOnNextRefresh = z;
    }

    void startSelectionActionModeAsync(boolean z) {
        getSelectionActionModeHelper().startSelectionActionModeAsync(z);
    }

    void startLinkActionModeAsync(int i, int i2) {
        if (this.mTextView.getText() instanceof Spannable) {
            lambda$startActionModeInternal$0$Editor();
            this.mRequestingLinkActionMode = true;
            getSelectionActionModeHelper().startLinkActionModeAsync(i, i2);
        }
    }

    void invalidateActionModeAsync() {
        getSelectionActionModeHelper().invalidateActionModeAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateActionMode() {
        ActionMode actionMode = this.mTextActionMode;
        if (actionMode != null) {
            actionMode.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SelectionActionModeHelper getSelectionActionModeHelper() {
        if (this.mSelectionActionModeHelper == null) {
            this.mSelectionActionModeHelper = new SelectionActionModeHelper(this);
        }
        return this.mSelectionActionModeHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean selectCurrentWordAndStartDrag() {
        Runnable runnable = this.mInsertionActionModeRunnable;
        if (runnable != null) {
            this.mTextView.removeCallbacks(runnable);
        }
        if (extractedTextModeWillBeStarted() || !checkField()) {
            return false;
        }
        if (!this.mTextView.hasSelection() && !selectCurrentWord()) {
            return false;
        }
        stopTextActionModeWithPreservingSelection();
        getSelectionController().enterDrag(2);
        return true;
    }

    boolean checkField() {
        if (this.mTextView.canSelectText() && this.mTextView.requestFocus()) {
            return true;
        }
        Log.w("TextView", "TextView does not support text selection. Selection cancelled.");
        return false;
    }

    boolean startActionModeInternal(int i) {
        InputMethodManager inputMethodManager;
        if (extractedTextModeWillBeStarted()) {
            return false;
        }
        if (this.mTextActionMode != null) {
            invalidateActionMode();
            return false;
        }
        if (i != 2 && (!checkField() || !this.mTextView.hasSelection())) {
            return false;
        }
        this.mTextActionMode = this.mTextView.startActionMode(new TextActionModeCallback(i), 1);
        boolean z = this.mTextView.isTextEditable() || this.mTextView.isTextSelectable();
        if (i == 2 && !z) {
            ActionMode actionMode = this.mTextActionMode;
            if (actionMode instanceof FloatingActionMode) {
                ((FloatingActionMode) actionMode).setOutsideTouchable(true, new PopupWindow.OnDismissListener() { // from class: android.widget.-$$Lambda$Editor$TdqUlJ6RRep0wXYHaRH51nTa08I
                    @Override // android.widget.PopupWindow.OnDismissListener
                    public final void onDismiss() {
                        this.f$0.lambda$startActionModeInternal$0$Editor();
                    }
                });
            }
        }
        boolean z2 = this.mTextActionMode != null;
        if (z2 && this.mTextView.isTextEditable() && !this.mTextView.isTextSelectable() && this.mShowSoftInputOnFocus && (inputMethodManager = getInputMethodManager()) != null) {
            inputMethodManager.showSoftInput(this.mTextView, 0, null);
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean extractedTextModeWillBeStarted() {
        InputMethodManager inputMethodManager;
        return (this.mTextView.isInExtractedMode() || (inputMethodManager = getInputMethodManager()) == null || !inputMethodManager.isFullscreenMode()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldOfferToShowSuggestions() {
        CharSequence text = this.mTextView.getText();
        if (!(text instanceof Spannable)) {
            return false;
        }
        Spannable spannable = (Spannable) text;
        int selectionStart = this.mTextView.getSelectionStart();
        int selectionEnd = this.mTextView.getSelectionEnd();
        SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spannable.getSpans(selectionStart, selectionEnd, SuggestionSpan.class);
        if (suggestionSpanArr.length == 0) {
            return false;
        }
        if (selectionStart == selectionEnd) {
            for (SuggestionSpan suggestionSpan : suggestionSpanArr) {
                if (suggestionSpan.getSuggestions().length > 0) {
                    return true;
                }
            }
            return false;
        }
        int length = this.mTextView.getText().length();
        int length2 = this.mTextView.getText().length();
        boolean z = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i = 0; i < suggestionSpanArr.length; i++) {
            int spanStart = spannable.getSpanStart(suggestionSpanArr[i]);
            int spanEnd = spannable.getSpanEnd(suggestionSpanArr[i]);
            length = Math.min(length, spanStart);
            iMax = Math.max(iMax, spanEnd);
            if (selectionStart >= spanStart && selectionStart <= spanEnd) {
                z = z || suggestionSpanArr[i].getSuggestions().length > 0;
                length2 = Math.min(length2, spanStart);
                iMax2 = Math.max(iMax2, spanEnd);
            }
        }
        return z && length2 < iMax2 && length >= length2 && iMax <= iMax2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCursorInsideEasyCorrectionSpan() {
        for (SuggestionSpan suggestionSpan : (SuggestionSpan[]) ((Spannable) this.mTextView.getText()).getSpans(this.mTextView.getSelectionStart(), this.mTextView.getSelectionEnd(), SuggestionSpan.class)) {
            if ((suggestionSpan.getFlags() & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    void onTouchUpEvent(MotionEvent motionEvent) {
        if (getSelectionActionModeHelper().resetSelection(getTextView().getOffsetForPosition(motionEvent.getX(), motionEvent.getY()))) {
            return;
        }
        boolean z = this.mSelectAllOnFocus && this.mTextView.didTouchFocusSelect();
        hideCursorAndSpanControllers();
        lambda$startActionModeInternal$0$Editor();
        CharSequence text = this.mTextView.getText();
        if (z || text.length() <= 0) {
            return;
        }
        int offsetForPosition = this.mTextView.getOffsetForPosition(motionEvent.getX(), motionEvent.getY());
        boolean z2 = !this.mRequestingLinkActionMode;
        if (z2) {
            Selection.setSelection((Spannable) text, offsetForPosition);
            SpellChecker spellChecker = this.mSpellChecker;
            if (spellChecker != null) {
                spellChecker.onSelectionChanged();
            }
        }
        if (extractedTextModeWillBeStarted()) {
            return;
        }
        if (isCursorInsideEasyCorrectionSpan()) {
            Runnable runnable = this.mInsertionActionModeRunnable;
            if (runnable != null) {
                this.mTextView.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: android.widget.-$$Lambda$DZXn7FbDDFyBvNjI-iG9_hfa7kw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.replace();
                }
            };
            this.mShowSuggestionRunnable = runnable2;
            this.mTextView.postDelayed(runnable2, ViewConfiguration.getDoubleTapTimeout());
            return;
        }
        if (hasInsertionController()) {
            if (z2) {
                getInsertionController().show();
            } else {
                getInsertionController().hide();
            }
        }
    }

    final void onTextOperationUserChanged() {
        SpellChecker spellChecker = this.mSpellChecker;
        if (spellChecker != null) {
            spellChecker.resetSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: stopTextActionMode, reason: merged with bridge method [inline-methods] */
    public void lambda$startActionModeInternal$0$Editor() {
        ActionMode actionMode = this.mTextActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    private void stopTextActionModeWithPreservingSelection() {
        if (this.mTextActionMode != null) {
            this.mRestartActionModeOnNextRefresh = true;
        }
        this.mPreserveSelection = true;
        lambda$startActionModeInternal$0$Editor();
        this.mPreserveSelection = false;
    }

    boolean hasInsertionController() {
        return this.mInsertionControllerEnabled;
    }

    boolean hasSelectionController() {
        return this.mSelectionControllerEnabled;
    }

    public InsertionPointCursorController getInsertionController() {
        if (!this.mInsertionControllerEnabled) {
            return null;
        }
        if (this.mInsertionPointCursorController == null) {
            this.mInsertionPointCursorController = new InsertionPointCursorController();
            this.mTextView.getViewTreeObserver().addOnTouchModeChangeListener(this.mInsertionPointCursorController);
        }
        return this.mInsertionPointCursorController;
    }

    public SelectionModifierCursorController getSelectionController() {
        if (!this.mSelectionControllerEnabled) {
            return null;
        }
        if (this.mSelectionModifierCursorController == null) {
            this.mSelectionModifierCursorController = new SelectionModifierCursorController();
            this.mTextView.getViewTreeObserver().addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
        }
        return this.mSelectionModifierCursorController;
    }

    public Drawable getCursorDrawable() {
        return this.mDrawableForCursor;
    }

    private void updateCursorPosition(int i, int i2, float f) {
        loadCursorDrawable();
        int iClampHorizontalPosition = clampHorizontalPosition(this.mDrawableForCursor, f);
        this.mDrawableForCursor.setBounds(iClampHorizontalPosition, i - this.mTempRect.top, this.mDrawableForCursor.getIntrinsicWidth() + iClampHorizontalPosition, i2 + this.mTempRect.bottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int clampHorizontalPosition(Drawable drawable, float f) {
        int i;
        float fMax = Math.max(0.5f, f - 0.5f);
        if (this.mTempRect == null) {
            this.mTempRect = new Rect();
        }
        int intrinsicWidth = 0;
        if (drawable != null) {
            drawable.getPadding(this.mTempRect);
            intrinsicWidth = drawable.getIntrinsicWidth();
        } else {
            this.mTempRect.setEmpty();
        }
        int scrollX = this.mTextView.getScrollX();
        float f2 = fMax - scrollX;
        int width = (this.mTextView.getWidth() - this.mTextView.getCompoundPaddingLeft()) - this.mTextView.getCompoundPaddingRight();
        float f3 = width;
        if (f2 >= f3 - 1.0f) {
            return (width + scrollX) - (intrinsicWidth - this.mTempRect.right);
        }
        if (Math.abs(f2) <= 1.0f || (TextUtils.isEmpty(this.mTextView.getText()) && 1048576 - scrollX <= f3 + 1.0f && fMax <= 1.0f)) {
            i = this.mTempRect.left;
        } else {
            scrollX = (int) fMax;
            i = this.mTempRect.left;
        }
        return scrollX - i;
    }

    public void onCommitCorrection(CorrectionInfo correctionInfo) {
        CorrectionHighlighter correctionHighlighter = this.mCorrectionHighlighter;
        if (correctionHighlighter == null) {
            this.mCorrectionHighlighter = new CorrectionHighlighter();
        } else {
            correctionHighlighter.invalidate(false);
        }
        this.mCorrectionHighlighter.highlight(correctionInfo);
        this.mUndoInputFilter.freezeLastEdit();
    }

    void onScrollChanged() {
        PositionListener positionListener = this.mPositionListener;
        if (positionListener != null) {
            positionListener.onScrollChanged();
        }
        ActionMode actionMode = this.mTextActionMode;
        if (actionMode != null) {
            actionMode.invalidateContentRect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBlink() {
        int selectionStart;
        int selectionEnd;
        return isCursorVisible() && this.mTextView.isFocused() && (selectionStart = this.mTextView.getSelectionStart()) >= 0 && (selectionEnd = this.mTextView.getSelectionEnd()) >= 0 && selectionStart == selectionEnd;
    }

    void makeBlink() {
        if (shouldBlink()) {
            this.mShowCursor = SystemClock.uptimeMillis();
            if (this.mBlink == null) {
                this.mBlink = new Blink();
            }
            this.mTextView.removeCallbacks(this.mBlink);
            this.mTextView.postDelayed(this.mBlink, 500L);
            return;
        }
        Blink blink = this.mBlink;
        if (blink != null) {
            this.mTextView.removeCallbacks(blink);
        }
    }

    private class Blink implements Runnable {
        private boolean mCancelled;

        private Blink() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mCancelled) {
                return;
            }
            Editor.this.mTextView.removeCallbacks(this);
            if (Editor.this.shouldBlink()) {
                if (Editor.this.mTextView.getLayout() != null) {
                    Editor.this.mTextView.invalidateCursorPath();
                }
                Editor.this.mTextView.postDelayed(this, 500L);
            }
        }

        void cancel() {
            if (this.mCancelled) {
                return;
            }
            Editor.this.mTextView.removeCallbacks(this);
            this.mCancelled = true;
        }

        void uncancel() {
            this.mCancelled = false;
        }
    }

    private View.DragShadowBuilder getTextThumbnailBuilder(int i, int i2) {
        TextView textView = (TextView) View.inflate(this.mTextView.getContext(), R.layout.text_drag_thumbnail, null);
        if (textView == null) {
            throw new IllegalArgumentException("Unable to inflate text drag thumbnail");
        }
        if (i2 - i > 20) {
            i2 = TextUtils.unpackRangeEndFromLong(getCharClusterRange(i + 20));
        }
        textView.setText(this.mTextView.getTransformedText(i, i2));
        textView.setTextColor(this.mTextView.getTextColors());
        textView.setTextAppearance(16);
        textView.setGravity(17);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        textView.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        textView.invalidate();
        return new View.DragShadowBuilder(textView);
    }

    private static class DragLocalState {
        public int end;
        public TextView sourceTextView;
        public int start;

        public DragLocalState(TextView textView, int i, int i2) {
            this.sourceTextView = textView;
            this.start = i;
            this.end = i2;
        }
    }

    void onDrop(DragEvent dragEvent) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        DragAndDropPermissions dragAndDropPermissionsObtain = DragAndDropPermissions.obtain(dragEvent);
        if (dragAndDropPermissionsObtain != null) {
            dragAndDropPermissionsObtain.takeTransient();
        }
        try {
            ClipData clipData = dragEvent.getClipData();
            int itemCount = clipData.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                spannableStringBuilder.append(clipData.getItemAt(i).coerceToStyledText(this.mTextView.getContext()));
            }
            this.mTextView.beginBatchEdit();
            this.mUndoInputFilter.freezeLastEdit();
            try {
                int offsetForPosition = this.mTextView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
                Object localState = dragEvent.getLocalState();
                DragLocalState dragLocalState = localState instanceof DragLocalState ? (DragLocalState) localState : null;
                boolean z = dragLocalState != null && dragLocalState.sourceTextView == this.mTextView;
                if (!z || offsetForPosition < dragLocalState.start || offsetForPosition >= dragLocalState.end) {
                    int length = this.mTextView.getText().length();
                    Selection.setSelection((Spannable) this.mTextView.getText(), offsetForPosition);
                    this.mTextView.replaceText_internal(offsetForPosition, offsetForPosition, spannableStringBuilder);
                    if (z) {
                        int i2 = dragLocalState.start;
                        int i3 = dragLocalState.end;
                        if (offsetForPosition <= i2) {
                            int length2 = this.mTextView.getText().length() - length;
                            i2 += length2;
                            i3 += length2;
                        }
                        this.mTextView.deleteText_internal(i2, i3);
                        int iMax = Math.max(0, i2 - 1);
                        int iMin = Math.min(this.mTextView.getText().length(), i2 + 1);
                        int i4 = iMax + 1;
                        if (iMin > i4) {
                            CharSequence transformedText = this.mTextView.getTransformedText(iMax, iMin);
                            if (Character.isSpaceChar(transformedText.charAt(0)) && Character.isSpaceChar(transformedText.charAt(1))) {
                                this.mTextView.deleteText_internal(iMax, i4);
                            }
                        }
                    }
                }
            } finally {
                this.mTextView.endBatchEdit();
                this.mUndoInputFilter.freezeLastEdit();
            }
        } finally {
            if (dragAndDropPermissionsObtain != null) {
                dragAndDropPermissionsObtain.release();
            }
        }
    }

    public void addSpanWatchers(Spannable spannable) {
        int length = spannable.length();
        KeyListener keyListener = this.mKeyListener;
        if (keyListener != null) {
            spannable.setSpan(keyListener, 0, length, 18);
        }
        if (this.mSpanController == null) {
            this.mSpanController = new SpanController();
        }
        spannable.setSpan(this.mSpanController, 0, length, 18);
    }

    void setContextMenuAnchor(float f, float f2) {
        this.mContextMenuAnchorX = f;
        this.mContextMenuAnchorY = f2;
    }

    void onCreateContextMenu(ContextMenu contextMenu) {
        int offsetForPosition;
        if (this.mIsBeingLongClicked || Float.isNaN(this.mContextMenuAnchorX) || Float.isNaN(this.mContextMenuAnchorY) || (offsetForPosition = this.mTextView.getOffsetForPosition(this.mContextMenuAnchorX, this.mContextMenuAnchorY)) == -1) {
            return;
        }
        stopTextActionModeWithPreservingSelection();
        if (this.mTextView.canSelectText()) {
            if (!(this.mTextView.hasSelection() && offsetForPosition >= this.mTextView.getSelectionStart() && offsetForPosition <= this.mTextView.getSelectionEnd())) {
                Selection.setSelection((Spannable) this.mTextView.getText(), offsetForPosition);
                lambda$startActionModeInternal$0$Editor();
            }
        }
        if (shouldOfferToShowSuggestions()) {
            SuggestionInfo[] suggestionInfoArr = new SuggestionInfo[5];
            int i = 0;
            while (true) {
                if (i >= 5) {
                    break;
                }
                suggestionInfoArr[i] = new SuggestionInfo();
                i++;
            }
            SubMenu subMenuAddSubMenu = contextMenu.addSubMenu(0, 0, 9, R.string.replace);
            int suggestionInfo = this.mSuggestionHelper.getSuggestionInfo(suggestionInfoArr, null);
            for (int i2 = 0; i2 < suggestionInfo; i2++) {
                final SuggestionInfo suggestionInfo2 = suggestionInfoArr[i2];
                subMenuAddSubMenu.add(0, 0, i2, suggestionInfo2.mText).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: android.widget.Editor.4
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Editor.this.replaceWithSuggestion(suggestionInfo2);
                        return true;
                    }
                });
            }
        }
        contextMenu.add(0, 16908338, 2, R.string.undo).setAlphabeticShortcut(DateFormat.TIME_ZONE).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener).setEnabled(this.mTextView.canUndo());
        contextMenu.add(0, 16908339, 3, R.string.redo).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener).setEnabled(this.mTextView.canRedo());
        contextMenu.add(0, 16908320, 4, 17039363).setAlphabeticShortcut(EpicenterTranslateClipReveal.StateProperty.TARGET_X).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener).setEnabled(this.mTextView.canCut());
        contextMenu.add(0, 16908321, 5, 17039361).setAlphabeticShortcut('c').setOnMenuItemClickListener(this.mOnContextMenuItemClickListener).setEnabled(this.mTextView.canCopy());
        contextMenu.add(0, 16908322, 6, 17039371).setAlphabeticShortcut('v').setEnabled(this.mTextView.canPaste()).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener);
        contextMenu.add(0, 16908337, 11, 17039385).setEnabled(this.mTextView.canPasteAsPlainText()).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener);
        contextMenu.add(0, 16908341, 7, R.string.share).setEnabled(this.mTextView.canShare()).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener);
        contextMenu.add(0, 16908319, 8, 17039373).setAlphabeticShortcut(DateFormat.AM_PM).setEnabled(this.mTextView.canSelectAllText()).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener);
        contextMenu.add(0, 16908355, 10, 17039386).setEnabled(this.mTextView.canRequestAutofill()).setOnMenuItemClickListener(this.mOnContextMenuItemClickListener);
        this.mPreserveSelection = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SuggestionSpan findEquivalentSuggestionSpan(SuggestionSpanInfo suggestionSpanInfo) {
        Editable editable = (Editable) this.mTextView.getText();
        if (editable.getSpanStart(suggestionSpanInfo.mSuggestionSpan) >= 0) {
            return suggestionSpanInfo.mSuggestionSpan;
        }
        for (SuggestionSpan suggestionSpan : (SuggestionSpan[]) editable.getSpans(suggestionSpanInfo.mSpanStart, suggestionSpanInfo.mSpanEnd, SuggestionSpan.class)) {
            if (editable.getSpanStart(suggestionSpan) == suggestionSpanInfo.mSpanStart && editable.getSpanEnd(suggestionSpan) == suggestionSpanInfo.mSpanEnd && suggestionSpan.equals(suggestionSpanInfo.mSuggestionSpan)) {
                return suggestionSpan;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceWithSuggestion(SuggestionInfo suggestionInfo) {
        SuggestionSpan suggestionSpanFindEquivalentSuggestionSpan = findEquivalentSuggestionSpan(suggestionInfo.mSuggestionSpanInfo);
        if (suggestionSpanFindEquivalentSuggestionSpan == null) {
            return;
        }
        Editable editable = (Editable) this.mTextView.getText();
        int spanStart = editable.getSpanStart(suggestionSpanFindEquivalentSuggestionSpan);
        int spanEnd = editable.getSpanEnd(suggestionSpanFindEquivalentSuggestionSpan);
        if (spanStart < 0 || spanEnd <= spanStart) {
            return;
        }
        String strSubstring = TextUtils.substring(editable, spanStart, spanEnd);
        SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) editable.getSpans(spanStart, spanEnd, SuggestionSpan.class);
        int length = suggestionSpanArr.length;
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        int[] iArr3 = new int[length];
        for (int i = 0; i < length; i++) {
            SuggestionSpan suggestionSpan = suggestionSpanArr[i];
            iArr[i] = editable.getSpanStart(suggestionSpan);
            iArr2[i] = editable.getSpanEnd(suggestionSpan);
            iArr3[i] = editable.getSpanFlags(suggestionSpan);
            int flags = suggestionSpan.getFlags();
            if ((flags & 2) != 0) {
                suggestionSpan.setFlags(flags & (-3) & (-2));
            }
        }
        String string = suggestionInfo.mText.subSequence(suggestionInfo.mSuggestionStart, suggestionInfo.mSuggestionEnd).toString();
        this.mTextView.replaceText_internal(spanStart, spanEnd, string);
        suggestionSpanFindEquivalentSuggestionSpan.getSuggestions()[suggestionInfo.mSuggestionIndex] = strSubstring;
        int length2 = string.length() - (spanEnd - spanStart);
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] <= spanStart && iArr2[i2] >= spanEnd) {
                this.mTextView.setSpan_internal(suggestionSpanArr[i2], iArr[i2], iArr2[i2] + length2, iArr3[i2]);
            }
        }
        int i3 = spanEnd + length2;
        this.mTextView.setCursorPosition_internal(i3, i3);
    }

    private class SpanController implements SpanWatcher {
        private static final int DISPLAY_TIMEOUT_MS = 3000;
        private Runnable mHidePopup;
        private EasyEditPopupWindow mPopupWindow;

        private SpanController() {
        }

        private boolean isNonIntermediateSelectionSpan(Spannable spannable, Object obj) {
            return (Selection.SELECTION_START == obj || Selection.SELECTION_END == obj) && (spannable.getSpanFlags(obj) & 512) == 0;
        }

        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable, Object obj, int i, int i2) {
            if (isNonIntermediateSelectionSpan(spannable, obj)) {
                Editor.this.sendUpdateSelection();
                return;
            }
            if (obj instanceof EasyEditSpan) {
                if (this.mPopupWindow == null) {
                    this.mPopupWindow = new EasyEditPopupWindow();
                    this.mHidePopup = new Runnable() { // from class: android.widget.Editor.SpanController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SpanController.this.hide();
                        }
                    };
                }
                if (this.mPopupWindow.mEasyEditSpan != null) {
                    this.mPopupWindow.mEasyEditSpan.setDeleteEnabled(false);
                }
                this.mPopupWindow.setEasyEditSpan((EasyEditSpan) obj);
                this.mPopupWindow.setOnDeleteListener(new EasyEditDeleteListener() { // from class: android.widget.Editor.SpanController.2
                    @Override // android.widget.Editor.EasyEditDeleteListener
                    public void onDeleteClick(EasyEditSpan easyEditSpan) {
                        Editable editable = (Editable) Editor.this.mTextView.getText();
                        int spanStart = editable.getSpanStart(easyEditSpan);
                        int spanEnd = editable.getSpanEnd(easyEditSpan);
                        if (spanStart >= 0 && spanEnd >= 0) {
                            SpanController.this.sendEasySpanNotification(1, easyEditSpan);
                            Editor.this.mTextView.deleteText_internal(spanStart, spanEnd);
                        }
                        editable.removeSpan(easyEditSpan);
                    }
                });
                if (Editor.this.mTextView.getWindowVisibility() != 0 || Editor.this.mTextView.getLayout() == null || Editor.this.extractedTextModeWillBeStarted()) {
                    return;
                }
                this.mPopupWindow.show();
                Editor.this.mTextView.removeCallbacks(this.mHidePopup);
                Editor.this.mTextView.postDelayed(this.mHidePopup, TelecomManager.VERY_SHORT_CALL_TIME_MS);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable, Object obj, int i, int i2) {
            if (isNonIntermediateSelectionSpan(spannable, obj)) {
                Editor.this.sendUpdateSelection();
                return;
            }
            EasyEditPopupWindow easyEditPopupWindow = this.mPopupWindow;
            if (easyEditPopupWindow == null || obj != easyEditPopupWindow.mEasyEditSpan) {
                return;
            }
            hide();
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable, Object obj, int i, int i2, int i3, int i4) {
            if (isNonIntermediateSelectionSpan(spannable, obj)) {
                Editor.this.sendUpdateSelection();
            } else {
                if (this.mPopupWindow == null || !(obj instanceof EasyEditSpan)) {
                    return;
                }
                EasyEditSpan easyEditSpan = (EasyEditSpan) obj;
                sendEasySpanNotification(2, easyEditSpan);
                spannable.removeSpan(easyEditSpan);
            }
        }

        public void hide() {
            EasyEditPopupWindow easyEditPopupWindow = this.mPopupWindow;
            if (easyEditPopupWindow != null) {
                easyEditPopupWindow.hide();
                Editor.this.mTextView.removeCallbacks(this.mHidePopup);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendEasySpanNotification(int i, EasyEditSpan easyEditSpan) {
            try {
                PendingIntent pendingIntent = easyEditSpan.getPendingIntent();
                if (pendingIntent != null) {
                    Intent intent = new Intent();
                    intent.putExtra(EasyEditSpan.EXTRA_TEXT_CHANGED_TYPE, i);
                    pendingIntent.send(Editor.this.mTextView.getContext(), 0, intent);
                }
            } catch (PendingIntent.CanceledException e) {
                Log.w("Editor", "PendingIntent for notification cannot be sent", e);
            }
        }
    }

    private class EasyEditPopupWindow extends PinnedPopupWindow implements View.OnClickListener {
        private static final int POPUP_TEXT_LAYOUT = 17367324;
        private TextView mDeleteTextView;
        private EasyEditSpan mEasyEditSpan;
        private EasyEditDeleteListener mOnDeleteListener;

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int clipVertically(int i) {
            return i;
        }

        private EasyEditPopupWindow() {
            super();
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void createPopupWindow() {
            this.mPopupWindow = new PopupWindow(Editor.this.mTextView.getContext(), (AttributeSet) null, 16843464);
            this.mPopupWindow.setInputMethodMode(2);
            this.mPopupWindow.setClippingEnabled(true);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void initContentView() {
            LinearLayout linearLayout = new LinearLayout(Editor.this.mTextView.getContext());
            linearLayout.setOrientation(0);
            this.mContentView = linearLayout;
            this.mContentView.setBackgroundResource(R.drawable.text_edit_side_paste_window);
            LayoutInflater layoutInflater = (LayoutInflater) Editor.this.mTextView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            TextView textView = (TextView) layoutInflater.inflate(17367324, (ViewGroup) null);
            this.mDeleteTextView = textView;
            textView.setLayoutParams(layoutParams);
            this.mDeleteTextView.setText(R.string.delete);
            this.mDeleteTextView.setOnClickListener(this);
            this.mContentView.addView(this.mDeleteTextView);
        }

        public void setEasyEditSpan(EasyEditSpan easyEditSpan) {
            this.mEasyEditSpan = easyEditSpan;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnDeleteListener(EasyEditDeleteListener easyEditDeleteListener) {
            this.mOnDeleteListener = easyEditDeleteListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EasyEditSpan easyEditSpan;
            EasyEditDeleteListener easyEditDeleteListener;
            if (view != this.mDeleteTextView || (easyEditSpan = this.mEasyEditSpan) == null || !easyEditSpan.isDeleteEnabled() || (easyEditDeleteListener = this.mOnDeleteListener) == null) {
                return;
            }
            easyEditDeleteListener.onDeleteClick(this.mEasyEditSpan);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        public void hide() {
            EasyEditSpan easyEditSpan = this.mEasyEditSpan;
            if (easyEditSpan != null) {
                easyEditSpan.setDeleteEnabled(false);
            }
            this.mOnDeleteListener = null;
            super.hide();
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int getTextOffset() {
            return ((Editable) Editor.this.mTextView.getText()).getSpanEnd(this.mEasyEditSpan);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int getVerticalLocalPosition(int i) {
            return Editor.this.mTextView.getLayout().getLineBottomWithoutSpacing(i);
        }
    }

    private class PositionListener implements ViewTreeObserver.OnPreDrawListener {
        private static final int MAXIMUM_NUMBER_OF_LISTENERS = 7;
        private boolean[] mCanMove;
        private int mNumberOfListeners;
        private boolean mPositionHasChanged;
        private TextViewPositionListener[] mPositionListeners;
        private int mPositionX;
        private int mPositionXOnScreen;
        private int mPositionY;
        private int mPositionYOnScreen;
        private boolean mScrollHasChanged;
        final int[] mTempCoords;

        private PositionListener() {
            this.mPositionListeners = new TextViewPositionListener[7];
            this.mCanMove = new boolean[7];
            this.mPositionHasChanged = true;
            this.mTempCoords = new int[2];
        }

        public void addSubscriber(TextViewPositionListener textViewPositionListener, boolean z) {
            if (this.mNumberOfListeners == 0) {
                updatePosition();
                Editor.this.mTextView.getViewTreeObserver().addOnPreDrawListener(this);
            }
            int i = -1;
            for (int i2 = 0; i2 < 7; i2++) {
                TextViewPositionListener textViewPositionListener2 = this.mPositionListeners[i2];
                if (textViewPositionListener2 == textViewPositionListener) {
                    return;
                }
                if (i < 0 && textViewPositionListener2 == null) {
                    i = i2;
                }
            }
            this.mPositionListeners[i] = textViewPositionListener;
            this.mCanMove[i] = z;
            this.mNumberOfListeners++;
        }

        public void removeSubscriber(TextViewPositionListener textViewPositionListener) {
            int i = 0;
            while (true) {
                if (i >= 7) {
                    break;
                }
                TextViewPositionListener[] textViewPositionListenerArr = this.mPositionListeners;
                if (textViewPositionListenerArr[i] == textViewPositionListener) {
                    textViewPositionListenerArr[i] = null;
                    this.mNumberOfListeners--;
                    break;
                }
                i++;
            }
            if (this.mNumberOfListeners == 0) {
                Editor.this.mTextView.getViewTreeObserver().removeOnPreDrawListener(this);
            }
        }

        public int getPositionX() {
            return this.mPositionX;
        }

        public int getPositionY() {
            return this.mPositionY;
        }

        public int getPositionXOnScreen() {
            return this.mPositionXOnScreen;
        }

        public int getPositionYOnScreen() {
            return this.mPositionYOnScreen;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            TextViewPositionListener textViewPositionListener;
            updatePosition();
            for (int i = 0; i < 7; i++) {
                if ((this.mPositionHasChanged || this.mScrollHasChanged || this.mCanMove[i]) && (textViewPositionListener = this.mPositionListeners[i]) != null) {
                    textViewPositionListener.updatePosition(this.mPositionX, this.mPositionY, this.mPositionHasChanged, this.mScrollHasChanged);
                }
            }
            this.mScrollHasChanged = false;
            return true;
        }

        private void updatePosition() {
            Editor.this.mTextView.getLocationInWindow(this.mTempCoords);
            int[] iArr = this.mTempCoords;
            this.mPositionHasChanged = (iArr[0] == this.mPositionX && iArr[1] == this.mPositionY) ? false : true;
            int[] iArr2 = this.mTempCoords;
            this.mPositionX = iArr2[0];
            this.mPositionY = iArr2[1];
            Editor.this.mTextView.getLocationOnScreen(this.mTempCoords);
            int[] iArr3 = this.mTempCoords;
            this.mPositionXOnScreen = iArr3[0];
            this.mPositionYOnScreen = iArr3[1];
        }

        public void onScrollChanged() {
            this.mScrollHasChanged = true;
        }
    }

    private abstract class PinnedPopupWindow implements TextViewPositionListener {
        int mClippingLimitLeft;
        int mClippingLimitRight;
        protected ViewGroup mContentView;
        protected PopupWindow mPopupWindow;
        int mPositionX;
        int mPositionY;

        protected abstract int clipVertically(int i);

        protected abstract void createPopupWindow();

        protected abstract int getTextOffset();

        protected abstract int getVerticalLocalPosition(int i);

        protected abstract void initContentView();

        protected void setUp() {
        }

        public PinnedPopupWindow() {
            setUp();
            createPopupWindow();
            this.mPopupWindow.setWindowLayoutType(1005);
            this.mPopupWindow.setWidth(-2);
            this.mPopupWindow.setHeight(-2);
            initContentView();
            this.mContentView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            this.mPopupWindow.setContentView(this.mContentView);
        }

        public void show() {
            Editor.this.getPositionListener().addSubscriber(this, false);
            computeLocalPosition();
            PositionListener positionListener = Editor.this.getPositionListener();
            updatePosition(positionListener.getPositionX(), positionListener.getPositionY());
        }

        protected void measureContent() {
            DisplayMetrics displayMetrics = Editor.this.mTextView.getResources().getDisplayMetrics();
            this.mContentView.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE));
        }

        private void computeLocalPosition() {
            measureContent();
            int measuredWidth = this.mContentView.getMeasuredWidth();
            int textOffset = getTextOffset();
            int primaryHorizontal = (int) (Editor.this.mTextView.getLayout().getPrimaryHorizontal(textOffset) - (measuredWidth / 2.0f));
            this.mPositionX = primaryHorizontal;
            this.mPositionX = primaryHorizontal + Editor.this.mTextView.viewportToContentHorizontalOffset();
            int verticalLocalPosition = getVerticalLocalPosition(Editor.this.mTextView.getLayout().getLineForOffset(textOffset));
            this.mPositionY = verticalLocalPosition;
            this.mPositionY = verticalLocalPosition + Editor.this.mTextView.viewportToContentVerticalOffset();
        }

        private void updatePosition(int i, int i2) {
            int i3 = i + this.mPositionX;
            int iClipVertically = clipVertically(i2 + this.mPositionY);
            DisplayMetrics displayMetrics = Editor.this.mTextView.getResources().getDisplayMetrics();
            int iMax = Math.max(-this.mClippingLimitLeft, Math.min((displayMetrics.widthPixels - this.mContentView.getMeasuredWidth()) + this.mClippingLimitRight, i3));
            if (!isShowing()) {
                this.mPopupWindow.showAtLocation(Editor.this.mTextView, 0, iMax, iClipVertically);
            } else {
                this.mPopupWindow.update(iMax, iClipVertically, -1, -1);
            }
        }

        public void hide() {
            if (isShowing()) {
                this.mPopupWindow.dismiss();
                Editor.this.getPositionListener().removeSubscriber(this);
            }
        }

        @Override // android.widget.Editor.TextViewPositionListener
        public void updatePosition(int i, int i2, boolean z, boolean z2) {
            if (isShowing() && Editor.this.isOffsetVisible(getTextOffset())) {
                if (z2) {
                    computeLocalPosition();
                }
                updatePosition(i, i2);
                return;
            }
            hide();
        }

        public boolean isShowing() {
            return this.mPopupWindow.isShowing();
        }
    }

    private static final class SuggestionInfo {
        int mSuggestionEnd;
        int mSuggestionIndex;
        final SuggestionSpanInfo mSuggestionSpanInfo;
        int mSuggestionStart;
        final SpannableStringBuilder mText;

        private SuggestionInfo() {
            this.mSuggestionSpanInfo = new SuggestionSpanInfo();
            this.mText = new SpannableStringBuilder();
        }

        void clear() {
            this.mSuggestionSpanInfo.clear();
            this.mText.clear();
        }

        void setSpanInfo(SuggestionSpan suggestionSpan, int i, int i2) {
            this.mSuggestionSpanInfo.mSuggestionSpan = suggestionSpan;
            this.mSuggestionSpanInfo.mSpanStart = i;
            this.mSuggestionSpanInfo.mSpanEnd = i2;
        }
    }

    private static final class SuggestionSpanInfo {
        int mSpanEnd;
        int mSpanStart;
        SuggestionSpan mSuggestionSpan;

        private SuggestionSpanInfo() {
        }

        void clear() {
            this.mSuggestionSpan = null;
        }
    }

    private class SuggestionHelper {
        private final HashMap<SuggestionSpan, Integer> mSpansLengths;
        private final Comparator<SuggestionSpan> mSuggestionSpanComparator;

        private SuggestionHelper() {
            this.mSuggestionSpanComparator = new SuggestionSpanComparator();
            this.mSpansLengths = new HashMap<>();
        }

        private class SuggestionSpanComparator implements Comparator<SuggestionSpan> {
            private SuggestionSpanComparator() {
            }

            @Override // java.util.Comparator
            public int compare(SuggestionSpan suggestionSpan, SuggestionSpan suggestionSpan2) {
                int flags = suggestionSpan.getFlags();
                int flags2 = suggestionSpan2.getFlags();
                if (flags != flags2) {
                    boolean z = (flags & 1) != 0;
                    boolean z2 = (flags2 & 1) != 0;
                    boolean z3 = (flags & 2) != 0;
                    boolean z4 = (flags2 & 2) != 0;
                    if (z && !z3) {
                        return -1;
                    }
                    if (z2 && !z4) {
                        return 1;
                    }
                    if (z3) {
                        return -1;
                    }
                    if (z4) {
                        return 1;
                    }
                }
                return ((Integer) SuggestionHelper.this.mSpansLengths.get(suggestionSpan)).intValue() - ((Integer) SuggestionHelper.this.mSpansLengths.get(suggestionSpan2)).intValue();
            }
        }

        private SuggestionSpan[] getSortedSuggestionSpans() {
            int selectionStart = Editor.this.mTextView.getSelectionStart();
            Spannable spannable = (Spannable) Editor.this.mTextView.getText();
            SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spannable.getSpans(selectionStart, selectionStart, SuggestionSpan.class);
            this.mSpansLengths.clear();
            for (SuggestionSpan suggestionSpan : suggestionSpanArr) {
                this.mSpansLengths.put(suggestionSpan, Integer.valueOf(spannable.getSpanEnd(suggestionSpan) - spannable.getSpanStart(suggestionSpan)));
            }
            Arrays.sort(suggestionSpanArr, this.mSuggestionSpanComparator);
            this.mSpansLengths.clear();
            return suggestionSpanArr;
        }

        public int getSuggestionInfo(SuggestionInfo[] suggestionInfoArr, SuggestionSpanInfo suggestionSpanInfo) {
            int i;
            SuggestionSpanInfo suggestionSpanInfo2 = suggestionSpanInfo;
            Spannable spannable = (Spannable) Editor.this.mTextView.getText();
            SuggestionSpan[] sortedSuggestionSpans = getSortedSuggestionSpans();
            int i2 = 0;
            if (sortedSuggestionSpans.length == 0) {
                return 0;
            }
            int length = sortedSuggestionSpans.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                SuggestionSpan suggestionSpan = sortedSuggestionSpans[i3];
                int spanStart = spannable.getSpanStart(suggestionSpan);
                int spanEnd = spannable.getSpanEnd(suggestionSpan);
                if (suggestionSpanInfo2 != null && (suggestionSpan.getFlags() & 2) != 0) {
                    suggestionSpanInfo2.mSuggestionSpan = suggestionSpan;
                    suggestionSpanInfo2.mSpanStart = spanStart;
                    suggestionSpanInfo2.mSpanEnd = spanEnd;
                }
                String[] suggestions = suggestionSpan.getSuggestions();
                int length2 = suggestions.length;
                int i5 = i2;
                while (i5 < length2) {
                    String str = suggestions[i5];
                    while (true) {
                        if (i2 < i4) {
                            SuggestionInfo suggestionInfo = suggestionInfoArr[i2];
                            if (suggestionInfo.mText.toString().equals(str)) {
                                int i6 = suggestionInfo.mSuggestionSpanInfo.mSpanStart;
                                int i7 = suggestionInfo.mSuggestionSpanInfo.mSpanEnd;
                                if (spanStart == i6 && spanEnd == i7) {
                                    i = 0;
                                    break;
                                }
                            }
                            i2++;
                        } else {
                            SuggestionInfo suggestionInfo2 = suggestionInfoArr[i4];
                            suggestionInfo2.setSpanInfo(suggestionSpan, spanStart, spanEnd);
                            suggestionInfo2.mSuggestionIndex = i5;
                            i = 0;
                            suggestionInfo2.mSuggestionStart = 0;
                            suggestionInfo2.mSuggestionEnd = str.length();
                            suggestionInfo2.mText.replace(0, suggestionInfo2.mText.length(), (CharSequence) str);
                            i4++;
                            if (i4 >= suggestionInfoArr.length) {
                                return i4;
                            }
                        }
                    }
                    i5++;
                    i2 = i;
                }
                i3++;
                suggestionSpanInfo2 = suggestionSpanInfo;
            }
            return i4;
        }
    }

    private final class SuggestionsPopupWindow extends PinnedPopupWindow implements AdapterView.OnItemClickListener {
        private static final int MAX_NUMBER_SUGGESTIONS = 5;
        private static final String USER_DICTIONARY_EXTRA_LOCALE = "locale";
        private static final String USER_DICTIONARY_EXTRA_WORD = "word";
        private TextView mAddToDictionaryButton;
        private int mContainerMarginTop;
        private int mContainerMarginWidth;
        private LinearLayout mContainerView;
        private Context mContext;
        private boolean mCursorWasVisibleBeforeSuggestions;
        private TextView mDeleteButton;
        private TextAppearanceSpan mHighlightSpan;
        private boolean mIsShowingUp;
        private final SuggestionSpanInfo mMisspelledSpanInfo;
        private int mNumberOfSuggestions;
        private SuggestionInfo[] mSuggestionInfos;
        private ListView mSuggestionListView;
        private SuggestionAdapter mSuggestionsAdapter;

        private class CustomPopupWindow extends PopupWindow {
            private CustomPopupWindow() {
            }

            @Override // android.widget.PopupWindow
            public void dismiss() {
                if (isShowing()) {
                    super.dismiss();
                    Editor.this.getPositionListener().removeSubscriber(SuggestionsPopupWindow.this);
                    ((Spannable) Editor.this.mTextView.getText()).removeSpan(Editor.this.mSuggestionRangeSpan);
                    Editor.this.mTextView.setCursorVisible(SuggestionsPopupWindow.this.mCursorWasVisibleBeforeSuggestions);
                    if (!Editor.this.hasInsertionController() || Editor.this.extractedTextModeWillBeStarted()) {
                        return;
                    }
                    Editor.this.getInsertionController().show();
                }
            }
        }

        public SuggestionsPopupWindow() {
            super();
            this.mIsShowingUp = false;
            this.mMisspelledSpanInfo = new SuggestionSpanInfo();
            this.mCursorWasVisibleBeforeSuggestions = Editor.this.mCursorVisible;
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void setUp() {
            this.mContext = applyDefaultTheme(Editor.this.mTextView.getContext());
            this.mHighlightSpan = new TextAppearanceSpan(this.mContext, Editor.this.mTextView.mTextEditSuggestionHighlightStyle);
        }

        private Context applyDefaultTheme(Context context) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{16844176});
            int i = typedArrayObtainStyledAttributes.getBoolean(0, true) ? 16974410 : 16974411;
            typedArrayObtainStyledAttributes.recycle();
            return new ContextThemeWrapper(context, i);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void createPopupWindow() {
            this.mPopupWindow = new CustomPopupWindow();
            this.mPopupWindow.setInputMethodMode(2);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setClippingEnabled(false);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void initContentView() {
            this.mContentView = (ViewGroup) ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(Editor.this.mTextView.mTextEditSuggestionContainerLayout, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) this.mContentView.findViewById(R.id.suggestionWindowContainer);
            this.mContainerView = linearLayout;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
            this.mContainerMarginWidth = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            this.mContainerMarginTop = marginLayoutParams.topMargin;
            this.mClippingLimitLeft = marginLayoutParams.leftMargin;
            this.mClippingLimitRight = marginLayoutParams.rightMargin;
            this.mSuggestionListView = (ListView) this.mContentView.findViewById(R.id.suggestionContainer);
            SuggestionAdapter suggestionAdapter = new SuggestionAdapter();
            this.mSuggestionsAdapter = suggestionAdapter;
            this.mSuggestionListView.setAdapter((ListAdapter) suggestionAdapter);
            this.mSuggestionListView.setOnItemClickListener(this);
            this.mSuggestionInfos = new SuggestionInfo[5];
            int i = 0;
            while (true) {
                SuggestionInfo[] suggestionInfoArr = this.mSuggestionInfos;
                if (i < suggestionInfoArr.length) {
                    suggestionInfoArr[i] = new SuggestionInfo();
                    i++;
                } else {
                    TextView textView = (TextView) this.mContentView.findViewById(R.id.addToDictionaryButton);
                    this.mAddToDictionaryButton = textView;
                    textView.setOnClickListener(new View.OnClickListener() { // from class: android.widget.Editor.SuggestionsPopupWindow.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            SuggestionSpan suggestionSpanFindEquivalentSuggestionSpan = Editor.this.findEquivalentSuggestionSpan(SuggestionsPopupWindow.this.mMisspelledSpanInfo);
                            if (suggestionSpanFindEquivalentSuggestionSpan == null) {
                                return;
                            }
                            Editable editable = (Editable) Editor.this.mTextView.getText();
                            int spanStart = editable.getSpanStart(suggestionSpanFindEquivalentSuggestionSpan);
                            int spanEnd = editable.getSpanEnd(suggestionSpanFindEquivalentSuggestionSpan);
                            if (spanStart < 0 || spanEnd <= spanStart) {
                                return;
                            }
                            String strSubstring = TextUtils.substring(editable, spanStart, spanEnd);
                            Intent intent = new Intent(Settings.ACTION_USER_DICTIONARY_INSERT);
                            intent.putExtra("word", strSubstring);
                            intent.putExtra("locale", Editor.this.mTextView.getTextServicesLocale().toString());
                            intent.setFlags(intent.getFlags() | 268435456);
                            editable.removeSpan(SuggestionsPopupWindow.this.mMisspelledSpanInfo.mSuggestionSpan);
                            Selection.setSelection(editable, spanEnd);
                            Editor.this.updateSpellCheckSpans(spanStart, spanEnd, false);
                            SuggestionsPopupWindow.this.hideWithCleanUp();
                        }
                    });
                    this.mAddToDictionaryButton.setVisibility(8);
                    TextView textView2 = (TextView) this.mContentView.findViewById(R.id.deleteButton);
                    this.mDeleteButton = textView2;
                    textView2.setOnClickListener(new View.OnClickListener() { // from class: android.widget.Editor.SuggestionsPopupWindow.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            Editable editable = (Editable) Editor.this.mTextView.getText();
                            int spanStart = editable.getSpanStart(Editor.this.mSuggestionRangeSpan);
                            int spanEnd = editable.getSpanEnd(Editor.this.mSuggestionRangeSpan);
                            if (spanStart >= 0 && spanEnd > spanStart) {
                                if (spanEnd < editable.length() && Character.isSpaceChar(editable.charAt(spanEnd)) && (spanStart == 0 || Character.isSpaceChar(editable.charAt(spanStart - 1)))) {
                                    spanEnd++;
                                }
                                Editor.this.mTextView.deleteText_internal(spanStart, spanEnd);
                            }
                            SuggestionsPopupWindow.this.hideWithCleanUp();
                        }
                    });
                    return;
                }
            }
        }

        public boolean isShowingUp() {
            return this.mIsShowingUp;
        }

        public void onParentLostFocus() {
            this.mIsShowingUp = false;
        }

        private class SuggestionAdapter extends BaseAdapter {
            private LayoutInflater mInflater;

            @Override // android.widget.Adapter
            public long getItemId(int i) {
                return i;
            }

            private SuggestionAdapter() {
                this.mInflater = (LayoutInflater) SuggestionsPopupWindow.this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            @Override // android.widget.Adapter
            public int getCount() {
                return SuggestionsPopupWindow.this.mNumberOfSuggestions;
            }

            @Override // android.widget.Adapter
            public Object getItem(int i) {
                return SuggestionsPopupWindow.this.mSuggestionInfos[i];
            }

            @Override // android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) view;
                if (textView == null) {
                    textView = (TextView) this.mInflater.inflate(Editor.this.mTextView.mTextEditSuggestionItemLayout, viewGroup, false);
                }
                textView.setText(SuggestionsPopupWindow.this.mSuggestionInfos[i].mText);
                return textView;
            }
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        public void show() {
            if ((Editor.this.mTextView.getText() instanceof Editable) && !Editor.this.extractedTextModeWillBeStarted()) {
                if (updateSuggestions()) {
                    this.mCursorWasVisibleBeforeSuggestions = Editor.this.mCursorVisible;
                    Editor.this.mTextView.setCursorVisible(false);
                    this.mIsShowingUp = true;
                    super.show();
                }
                this.mSuggestionListView.setVisibility(this.mNumberOfSuggestions == 0 ? 8 : 0);
            }
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected void measureContent() {
            DisplayMetrics displayMetrics = Editor.this.mTextView.getResources().getDisplayMetrics();
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE);
            View view = null;
            int iMax = 0;
            for (int i = 0; i < this.mNumberOfSuggestions; i++) {
                view = this.mSuggestionsAdapter.getView(i, view, this.mContentView);
                view.getLayoutParams().width = -2;
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.mDeleteButton.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int iMax2 = Math.max(iMax, this.mDeleteButton.getMeasuredWidth()) + this.mContainerView.getPaddingLeft() + this.mContainerView.getPaddingRight() + this.mContainerMarginWidth;
            this.mContentView.measure(View.MeasureSpec.makeMeasureSpec(iMax2, 1073741824), iMakeMeasureSpec2);
            Drawable background = this.mPopupWindow.getBackground();
            if (background != null) {
                if (Editor.this.mTempRect == null) {
                    Editor.this.mTempRect = new Rect();
                }
                background.getPadding(Editor.this.mTempRect);
                iMax2 += Editor.this.mTempRect.left + Editor.this.mTempRect.right;
            }
            this.mPopupWindow.setWidth(iMax2);
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int getTextOffset() {
            return (Editor.this.mTextView.getSelectionStart() + Editor.this.mTextView.getSelectionStart()) / 2;
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int getVerticalLocalPosition(int i) {
            return Editor.this.mTextView.getLayout().getLineBottomWithoutSpacing(i) - this.mContainerMarginTop;
        }

        @Override // android.widget.Editor.PinnedPopupWindow
        protected int clipVertically(int i) {
            return Math.min(i, Editor.this.mTextView.getResources().getDisplayMetrics().heightPixels - this.mContentView.getMeasuredHeight());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void hideWithCleanUp() {
            for (SuggestionInfo suggestionInfo : this.mSuggestionInfos) {
                suggestionInfo.clear();
            }
            this.mMisspelledSpanInfo.clear();
            hide();
        }

        private boolean updateSuggestions() {
            int underlineColor;
            Spannable spannable = (Spannable) Editor.this.mTextView.getText();
            int suggestionInfo = Editor.this.mSuggestionHelper.getSuggestionInfo(this.mSuggestionInfos, this.mMisspelledSpanInfo);
            this.mNumberOfSuggestions = suggestionInfo;
            if (suggestionInfo == 0 && this.mMisspelledSpanInfo.mSuggestionSpan == null) {
                return false;
            }
            int length = Editor.this.mTextView.getText().length();
            int iMax = 0;
            for (int i = 0; i < this.mNumberOfSuggestions; i++) {
                SuggestionSpanInfo suggestionSpanInfo = this.mSuggestionInfos[i].mSuggestionSpanInfo;
                length = Math.min(length, suggestionSpanInfo.mSpanStart);
                iMax = Math.max(iMax, suggestionSpanInfo.mSpanEnd);
            }
            if (this.mMisspelledSpanInfo.mSuggestionSpan != null) {
                length = Math.min(length, this.mMisspelledSpanInfo.mSpanStart);
                iMax = Math.max(iMax, this.mMisspelledSpanInfo.mSpanEnd);
            }
            for (int i2 = 0; i2 < this.mNumberOfSuggestions; i2++) {
                highlightTextDifferences(this.mSuggestionInfos[i2], length, iMax);
            }
            this.mAddToDictionaryButton.setVisibility(8);
            if (Editor.this.mSuggestionRangeSpan == null) {
                Editor.this.mSuggestionRangeSpan = new SuggestionRangeSpan();
            }
            if (this.mNumberOfSuggestions != 0) {
                underlineColor = this.mSuggestionInfos[0].mSuggestionSpanInfo.mSuggestionSpan.getUnderlineColor();
            } else {
                underlineColor = this.mMisspelledSpanInfo.mSuggestionSpan.getUnderlineColor();
            }
            if (underlineColor == 0) {
                Editor.this.mSuggestionRangeSpan.setBackgroundColor(Editor.this.mTextView.mHighlightColor);
            } else {
                Editor.this.mSuggestionRangeSpan.setBackgroundColor((underlineColor & 16777215) + (((int) (Color.alpha(underlineColor) * 0.4f)) << 24));
            }
            spannable.setSpan(Editor.this.mSuggestionRangeSpan, length, iMax, 33);
            this.mSuggestionsAdapter.notifyDataSetChanged();
            return true;
        }

        private void highlightTextDifferences(SuggestionInfo suggestionInfo, int i, int i2) {
            Spannable spannable = (Spannable) Editor.this.mTextView.getText();
            int i3 = suggestionInfo.mSuggestionSpanInfo.mSpanStart;
            int i4 = suggestionInfo.mSuggestionSpanInfo.mSpanEnd;
            suggestionInfo.mSuggestionStart = i3 - i;
            suggestionInfo.mSuggestionEnd = suggestionInfo.mSuggestionStart + suggestionInfo.mText.length();
            suggestionInfo.mText.setSpan(this.mHighlightSpan, 0, suggestionInfo.mText.length(), 33);
            String string = spannable.toString();
            suggestionInfo.mText.insert(0, (CharSequence) string.substring(i, i3));
            suggestionInfo.mText.append((CharSequence) string.substring(i4, i2));
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Editor.this.replaceWithSuggestion(this.mSuggestionInfos[i]);
            hideWithCleanUp();
        }
    }

    private class TextActionModeCallback extends ActionMode.Callback2 {
        private final int mHandleHeight;
        private final boolean mHasSelection;
        private final Path mSelectionPath = new Path();
        private final RectF mSelectionBounds = new RectF();
        private final Map<MenuItem, View.OnClickListener> mAssistClickHandlers = new HashMap();

        TextActionModeCallback(int i) {
            boolean z = i == 0 || (Editor.this.mTextIsSelectable && i == 2);
            this.mHasSelection = z;
            if (z) {
                SelectionModifierCursorController selectionController = Editor.this.getSelectionController();
                if (selectionController.mStartHandle == null) {
                    Editor.this.loadHandleDrawables(false);
                    selectionController.initHandles();
                    selectionController.hide();
                }
                this.mHandleHeight = Math.max(Editor.this.mSelectHandleLeft.getMinimumHeight(), Editor.this.mSelectHandleRight.getMinimumHeight());
                return;
            }
            InsertionPointCursorController insertionController = Editor.this.getInsertionController();
            if (insertionController != null) {
                insertionController.getHandle();
                this.mHandleHeight = Editor.this.mSelectHandleCenter.getMinimumHeight();
            } else {
                this.mHandleHeight = 0;
            }
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mAssistClickHandlers.clear();
            actionMode.setTitle((CharSequence) null);
            actionMode.setSubtitle((CharSequence) null);
            actionMode.setTitleOptionalHint(true);
            populateMenuWithItems(menu);
            ActionMode.Callback customCallback = getCustomCallback();
            if (customCallback == null || customCallback.onCreateActionMode(actionMode, menu)) {
                if (Editor.this.mTextView.canProcessText()) {
                    Editor.this.mProcessTextIntentActionsHandler.onInitializeMenu(menu);
                }
                if (this.mHasSelection && !Editor.this.mTextView.hasTransientState()) {
                    Editor.this.mTextView.setHasTransientState(true);
                }
                return true;
            }
            Selection.setSelection((Spannable) Editor.this.mTextView.getText(), Editor.this.mTextView.getSelectionEnd());
            return false;
        }

        private ActionMode.Callback getCustomCallback() {
            if (this.mHasSelection) {
                return Editor.this.mCustomSelectionActionModeCallback;
            }
            return Editor.this.mCustomInsertionActionModeCallback;
        }

        private void populateMenuWithItems(Menu menu) {
            String selectedText;
            if (Editor.this.mTextView.canCut()) {
                menu.add(0, 16908320, 4, 17039363).setAlphabeticShortcut(EpicenterTranslateClipReveal.StateProperty.TARGET_X).setShowAsAction(2);
            }
            if (Editor.this.mTextView.canCopy()) {
                menu.add(0, 16908321, 5, 17039361).setAlphabeticShortcut('c').setShowAsAction(2);
            }
            if (Editor.this.mTextView.canPaste()) {
                menu.add(0, 16908322, 6, 17039371).setAlphabeticShortcut('v').setShowAsAction(2);
            }
            if (Editor.this.mTextView.canShare()) {
                menu.add(0, 16908341, 7, R.string.share).setShowAsAction(1);
            }
            if (Editor.this.mTextView.canRequestAutofill() && ((selectedText = Editor.this.mTextView.getSelectedText()) == null || selectedText.isEmpty())) {
                menu.add(0, 16908355, 10, 17039386).setShowAsAction(0);
            }
            if (Editor.this.mTextView.canPasteAsPlainText()) {
                menu.add(0, 16908337, 11, 17039385).setShowAsAction(1);
            }
            updateSelectAllItem(menu);
            updateReplaceItem(menu);
            updateAssistMenuItems(menu);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            updateSelectAllItem(menu);
            updateReplaceItem(menu);
            updateAssistMenuItems(menu);
            ActionMode.Callback customCallback = getCustomCallback();
            if (customCallback != null) {
                return customCallback.onPrepareActionMode(actionMode, menu);
            }
            return true;
        }

        private void updateSelectAllItem(Menu menu) {
            boolean zCanSelectAllText = Editor.this.mTextView.canSelectAllText();
            boolean z = menu.findItem(16908319) != null;
            if (zCanSelectAllText && !z) {
                menu.add(0, 16908319, 8, 17039373).setShowAsAction(1);
            } else {
                if (zCanSelectAllText || !z) {
                    return;
                }
                menu.removeItem(16908319);
            }
        }

        private void updateReplaceItem(Menu menu) {
            boolean z = Editor.this.mTextView.isSuggestionsEnabled() && Editor.this.shouldOfferToShowSuggestions();
            boolean z2 = menu.findItem(16908340) != null;
            if (z && !z2) {
                menu.add(0, 16908340, 9, R.string.replace).setShowAsAction(1);
            } else {
                if (z || !z2) {
                    return;
                }
                menu.removeItem(16908340);
            }
        }

        private void updateAssistMenuItems(Menu menu) {
            TextClassification textClassification;
            clearAssistMenuItems(menu);
            if (shouldEnableAssistMenuItems() && (textClassification = Editor.this.getSelectionActionModeHelper().getTextClassification()) != null) {
                if (!textClassification.getActions().isEmpty()) {
                    addAssistMenuItem(menu, textClassification.getActions().get(0), 16908353, 0, 2).setIntent(textClassification.getIntent());
                } else if (hasLegacyAssistItem(textClassification)) {
                    MenuItem intent = menu.add(16908353, 16908353, 0, textClassification.getLabel()).setIcon(textClassification.getIcon()).setIntent(textClassification.getIntent());
                    intent.setShowAsAction(2);
                    this.mAssistClickHandlers.put(intent, TextClassification.createIntentOnClickListener(TextClassification.createPendingIntent(Editor.this.mTextView.getContext(), textClassification.getIntent(), createAssistMenuItemPendingIntentRequestCode())));
                }
                int size = textClassification.getActions().size();
                for (int i = 1; i < size; i++) {
                    addAssistMenuItem(menu, textClassification.getActions().get(i), 0, (i + 50) - 1, 0);
                }
            }
        }

        private MenuItem addAssistMenuItem(Menu menu, RemoteAction remoteAction, int i, int i2, int i3) {
            MenuItem contentDescription = menu.add(16908353, i, i2, remoteAction.getTitle()).setContentDescription(remoteAction.getContentDescription());
            if (remoteAction.shouldShowIcon()) {
                contentDescription.setIcon(remoteAction.getIcon().loadDrawable(Editor.this.mTextView.getContext()));
            }
            contentDescription.setShowAsAction(i3);
            this.mAssistClickHandlers.put(contentDescription, TextClassification.createIntentOnClickListener(remoteAction.getActionIntent()));
            return contentDescription;
        }

        private void clearAssistMenuItems(Menu menu) {
            int i = 0;
            while (i < menu.size()) {
                MenuItem item = menu.getItem(i);
                if (item.getGroupId() == 16908353) {
                    menu.removeItem(item.getItemId());
                } else {
                    i++;
                }
            }
        }

        private boolean hasLegacyAssistItem(TextClassification textClassification) {
            return ((textClassification.getIcon() == null && TextUtils.isEmpty(textClassification.getLabel())) || (textClassification.getIntent() == null && textClassification.getOnClickListener() == null)) ? false : true;
        }

        private boolean onAssistMenuItemClicked(MenuItem menuItem) {
            Intent intent;
            Preconditions.checkArgument(menuItem.getGroupId() == 16908353);
            TextClassification textClassification = Editor.this.getSelectionActionModeHelper().getTextClassification();
            if (shouldEnableAssistMenuItems() && textClassification != null) {
                View.OnClickListener onClickListenerCreateIntentOnClickListener = this.mAssistClickHandlers.get(menuItem);
                if (onClickListenerCreateIntentOnClickListener == null && (intent = menuItem.getIntent()) != null) {
                    onClickListenerCreateIntentOnClickListener = TextClassification.createIntentOnClickListener(TextClassification.createPendingIntent(Editor.this.mTextView.getContext(), intent, createAssistMenuItemPendingIntentRequestCode()));
                }
                if (onClickListenerCreateIntentOnClickListener != null) {
                    onClickListenerCreateIntentOnClickListener.onClick(Editor.this.mTextView);
                    Editor.this.lambda$startActionModeInternal$0$Editor();
                }
            }
            return true;
        }

        private int createAssistMenuItemPendingIntentRequestCode() {
            if (Editor.this.mTextView.hasSelection()) {
                return Editor.this.mTextView.getText().subSequence(Editor.this.mTextView.getSelectionStart(), Editor.this.mTextView.getSelectionEnd()).hashCode();
            }
            return 0;
        }

        private boolean shouldEnableAssistMenuItems() {
            return Editor.this.mTextView.isDeviceProvisioned() && TextClassificationManager.getSettings(Editor.this.mTextView.getContext()).isSmartTextShareEnabled();
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Editor.this.getSelectionActionModeHelper().onSelectionAction(menuItem.getItemId(), menuItem.getTitle().toString());
            if (Editor.this.mProcessTextIntentActionsHandler.performMenuItemAction(menuItem)) {
                return true;
            }
            ActionMode.Callback customCallback = getCustomCallback();
            if (customCallback != null && customCallback.onActionItemClicked(actionMode, menuItem)) {
                return true;
            }
            if (menuItem.getGroupId() == 16908353 && onAssistMenuItemClicked(menuItem)) {
                return true;
            }
            return Editor.this.mTextView.onTextContextMenuItem(menuItem.getItemId());
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            Editor.this.getSelectionActionModeHelper().onDestroyActionMode();
            Editor.this.mTextActionMode = null;
            ActionMode.Callback customCallback = getCustomCallback();
            if (customCallback != null) {
                customCallback.onDestroyActionMode(actionMode);
            }
            if (!Editor.this.mPreserveSelection) {
                Selection.setSelection((Spannable) Editor.this.mTextView.getText(), Editor.this.mTextView.getSelectionEnd());
            }
            if (Editor.this.mSelectionModifierCursorController != null) {
                Editor.this.mSelectionModifierCursorController.hide();
            }
            this.mAssistClickHandlers.clear();
            Editor.this.mRequestingLinkActionMode = false;
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            if (view.equals(Editor.this.mTextView) && Editor.this.mTextView.getLayout() != null) {
                if (Editor.this.mTextView.getSelectionStart() == Editor.this.mTextView.getSelectionEnd()) {
                    Layout layout = Editor.this.mTextView.getLayout();
                    int lineForOffset = layout.getLineForOffset(Editor.this.mTextView.getSelectionStart());
                    Editor editor = Editor.this;
                    float fClampHorizontalPosition = editor.clampHorizontalPosition(null, layout.getPrimaryHorizontal(editor.mTextView.getSelectionStart()));
                    this.mSelectionBounds.set(fClampHorizontalPosition, layout.getLineTop(lineForOffset), fClampHorizontalPosition, layout.getLineBottom(lineForOffset) + this.mHandleHeight);
                } else {
                    this.mSelectionPath.reset();
                    Editor.this.mTextView.getLayout().getSelectionPath(Editor.this.mTextView.getSelectionStart(), Editor.this.mTextView.getSelectionEnd(), this.mSelectionPath);
                    this.mSelectionPath.computeBounds(this.mSelectionBounds, true);
                    this.mSelectionBounds.bottom += this.mHandleHeight;
                }
                float fViewportToContentHorizontalOffset = Editor.this.mTextView.viewportToContentHorizontalOffset();
                float fViewportToContentVerticalOffset = Editor.this.mTextView.viewportToContentVerticalOffset();
                rect.set((int) Math.floor(this.mSelectionBounds.left + fViewportToContentHorizontalOffset), (int) Math.floor(this.mSelectionBounds.top + fViewportToContentVerticalOffset), (int) Math.ceil(this.mSelectionBounds.right + fViewportToContentHorizontalOffset), (int) Math.ceil(this.mSelectionBounds.bottom + fViewportToContentVerticalOffset));
                return;
            }
            super.onGetContentRect(actionMode, view, rect);
        }
    }

    private final class CursorAnchorInfoNotifier implements TextViewPositionListener {
        final CursorAnchorInfo.Builder mSelectionInfoBuilder;
        final int[] mTmpIntOffset;
        final Matrix mViewToScreenMatrix;

        private CursorAnchorInfoNotifier() {
            this.mSelectionInfoBuilder = new CursorAnchorInfo.Builder();
            this.mTmpIntOffset = new int[2];
            this.mViewToScreenMatrix = new Matrix();
        }

        @Override // android.widget.Editor.TextViewPositionListener
        public void updatePosition(int i, int i2, boolean z, boolean z2) {
            InputMethodManager inputMethodManager;
            Layout layout;
            int i3;
            InputMethodState inputMethodState = Editor.this.mInputMethodState;
            if (inputMethodState == null || inputMethodState.mBatchEditNesting > 0 || (inputMethodManager = Editor.this.getInputMethodManager()) == null || !inputMethodManager.isActive(Editor.this.mTextView) || !inputMethodManager.isCursorAnchorInfoEnabled() || (layout = Editor.this.mTextView.getLayout()) == null) {
                return;
            }
            CursorAnchorInfo.Builder builder = this.mSelectionInfoBuilder;
            builder.reset();
            int selectionStart = Editor.this.mTextView.getSelectionStart();
            builder.setSelectionRange(selectionStart, Editor.this.mTextView.getSelectionEnd());
            this.mViewToScreenMatrix.set(Editor.this.mTextView.getMatrix());
            Editor.this.mTextView.getLocationOnScreen(this.mTmpIntOffset);
            Matrix matrix = this.mViewToScreenMatrix;
            int[] iArr = this.mTmpIntOffset;
            matrix.postTranslate(iArr[0], iArr[1]);
            builder.setMatrix(this.mViewToScreenMatrix);
            float fViewportToContentHorizontalOffset = Editor.this.mTextView.viewportToContentHorizontalOffset();
            float fViewportToContentVerticalOffset = Editor.this.mTextView.viewportToContentVerticalOffset();
            CharSequence text = Editor.this.mTextView.getText();
            if (text instanceof Spannable) {
                Spannable spannable = (Spannable) text;
                int composingSpanStart = EditableInputConnection.getComposingSpanStart(spannable);
                int composingSpanEnd = EditableInputConnection.getComposingSpanEnd(spannable);
                if (composingSpanEnd < composingSpanStart) {
                    i3 = composingSpanStart;
                    composingSpanStart = composingSpanEnd;
                } else {
                    i3 = composingSpanEnd;
                }
                if (composingSpanStart >= 0 && composingSpanStart < i3) {
                    builder.setComposingText(composingSpanStart, text.subSequence(composingSpanStart, i3));
                    Editor.this.mTextView.populateCharacterBounds(builder, composingSpanStart, i3, fViewportToContentHorizontalOffset, fViewportToContentVerticalOffset);
                }
            }
            if (selectionStart >= 0) {
                int lineForOffset = layout.getLineForOffset(selectionStart);
                float primaryHorizontal = layout.getPrimaryHorizontal(selectionStart) + fViewportToContentHorizontalOffset;
                float lineTop = layout.getLineTop(lineForOffset) + fViewportToContentVerticalOffset;
                float lineBaseline = layout.getLineBaseline(lineForOffset) + fViewportToContentVerticalOffset;
                float lineBottomWithoutSpacing = layout.getLineBottomWithoutSpacing(lineForOffset) + fViewportToContentVerticalOffset;
                boolean zIsPositionVisible = Editor.this.mTextView.isPositionVisible(primaryHorizontal, lineTop);
                boolean zIsPositionVisible2 = Editor.this.mTextView.isPositionVisible(primaryHorizontal, lineBottomWithoutSpacing);
                int i4 = (zIsPositionVisible || zIsPositionVisible2) ? 1 : 0;
                if (!zIsPositionVisible || !zIsPositionVisible2) {
                    i4 |= 2;
                }
                builder.setInsertionMarkerLocation(primaryHorizontal, lineTop, lineBaseline, lineBottomWithoutSpacing, layout.isRtlCharAt(selectionStart) ? i4 | 4 : i4);
            }
            inputMethodManager.updateCursorAnchorInfo(Editor.this.mTextView, builder.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class MagnifierMotionAnimator {
        private static final long DURATION = 100;
        private float mAnimationCurrentX;
        private float mAnimationCurrentY;
        private float mAnimationStartX;
        private float mAnimationStartY;
        private final ValueAnimator mAnimator;
        private float mLastX;
        private float mLastY;
        private final Magnifier mMagnifier;
        private boolean mMagnifierIsShowing;

        private MagnifierMotionAnimator(Magnifier magnifier) {
            this.mMagnifier = magnifier;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(DURATION);
            this.mAnimator.setInterpolator(new LinearInterpolator());
            this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.widget.-$$Lambda$Editor$MagnifierMotionAnimator$E-RaelOMgCHAzvKgSSZE-hDYeIg
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$new$0$Editor$MagnifierMotionAnimator(valueAnimator);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$Editor$MagnifierMotionAnimator(ValueAnimator valueAnimator) {
            float f = this.mAnimationStartX;
            this.mAnimationCurrentX = f + ((this.mLastX - f) * valueAnimator.getAnimatedFraction());
            float f2 = this.mAnimationStartY;
            float animatedFraction = f2 + ((this.mLastY - f2) * valueAnimator.getAnimatedFraction());
            this.mAnimationCurrentY = animatedFraction;
            this.mMagnifier.show(this.mAnimationCurrentX, animatedFraction);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void show(float f, float f2) {
            if (this.mMagnifierIsShowing && f2 != this.mLastY) {
                if (this.mAnimator.isRunning()) {
                    this.mAnimator.cancel();
                    this.mAnimationStartX = this.mAnimationCurrentX;
                    this.mAnimationStartY = this.mAnimationCurrentY;
                } else {
                    this.mAnimationStartX = this.mLastX;
                    this.mAnimationStartY = this.mLastY;
                }
                this.mAnimator.start();
            } else if (!this.mAnimator.isRunning()) {
                this.mMagnifier.show(f, f2);
            }
            this.mLastX = f;
            this.mLastY = f2;
            this.mMagnifierIsShowing = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void update() {
            this.mMagnifier.update();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dismiss() {
            this.mMagnifier.dismiss();
            this.mAnimator.cancel();
            this.mMagnifierIsShowing = false;
        }
    }

    public abstract class HandleView extends View implements TextViewPositionListener {
        private static final int HISTORY_SIZE = 5;
        private static final int TOUCH_UP_FILTER_DELAY_AFTER = 150;
        private static final int TOUCH_UP_FILTER_DELAY_BEFORE = 350;
        private final PopupWindow mContainer;
        private float mCurrentDragInitialTouchRawX;
        protected Drawable mDrawable;
        protected Drawable mDrawableLtr;
        protected Drawable mDrawableRtl;
        protected int mHorizontalGravity;
        protected int mHotspotX;
        private final int mIdealFingerToCursorOffset;
        private final float mIdealVerticalOffset;
        private boolean mIsDragging;
        private int mLastParentX;
        private int mLastParentXOnScreen;
        private int mLastParentY;
        private int mLastParentYOnScreen;
        private int mMinSize;
        private int mNumberPreviousOffsets;
        private boolean mPositionHasChanged;
        private int mPositionX;
        private int mPositionY;
        protected int mPrevLine;
        protected int mPreviousLineTouched;
        protected int mPreviousOffset;
        private int mPreviousOffsetIndex;
        private final int[] mPreviousOffsets;
        private final long[] mPreviousOffsetsTimes;
        private float mTextViewScaleX;
        private float mTextViewScaleY;
        private float mTouchOffsetY;
        private float mTouchToWindowOffsetX;
        private float mTouchToWindowOffsetY;

        public abstract int getCurrentCursorOffset();

        protected int getCursorOffset() {
            return 0;
        }

        protected abstract int getHorizontalGravity(boolean z);

        protected abstract int getHotspotX(Drawable drawable, boolean z);

        protected abstract int getMagnifierHandleTrigger();

        public void onDetached() {
        }

        void onHandleMoved() {
        }

        protected abstract void updatePosition(float f, float f2, boolean z);

        protected abstract void updateSelection(int i);

        private HandleView(Drawable drawable, Drawable drawable2, int i) {
            super(Editor.this.mTextView.getContext());
            this.mPreviousOffset = -1;
            this.mPositionHasChanged = true;
            this.mPrevLine = -1;
            this.mPreviousLineTouched = -1;
            this.mCurrentDragInitialTouchRawX = -1.0f;
            this.mPreviousOffsetsTimes = new long[5];
            this.mPreviousOffsets = new int[5];
            this.mPreviousOffsetIndex = 0;
            this.mNumberPreviousOffsets = 0;
            setId(i);
            PopupWindow popupWindow = new PopupWindow(Editor.this.mTextView.getContext(), (AttributeSet) null, 16843464);
            this.mContainer = popupWindow;
            popupWindow.setSplitTouchEnabled(true);
            this.mContainer.setClippingEnabled(false);
            this.mContainer.setWindowLayoutType(1002);
            this.mContainer.setWidth(-2);
            this.mContainer.setHeight(-2);
            this.mContainer.setContentView(this);
            setDrawables(drawable, drawable2);
            this.mMinSize = Editor.this.mTextView.getContext().getResources().getDimensionPixelSize(R.dimen.text_handle_min_size);
            float preferredHeight = getPreferredHeight();
            this.mTouchOffsetY = (-0.3f) * preferredHeight;
            int intCoreSetting = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_FINGER_TO_CURSOR_DISTANCE, -1);
            if (intCoreSetting < 0 || intCoreSetting > 100) {
                float f = preferredHeight * 0.7f;
                this.mIdealVerticalOffset = f;
                this.mIdealFingerToCursorOffset = (int) (f - this.mTouchOffsetY);
            } else {
                int iApplyDimension = (int) TypedValue.applyDimension(1, intCoreSetting, Editor.this.mTextView.getContext().getResources().getDisplayMetrics());
                this.mIdealFingerToCursorOffset = iApplyDimension;
                this.mIdealVerticalOffset = iApplyDimension + this.mTouchOffsetY;
            }
        }

        public float getIdealVerticalOffset() {
            return this.mIdealVerticalOffset;
        }

        final int getIdealFingerToCursorOffset() {
            return this.mIdealFingerToCursorOffset;
        }

        void setDrawables(Drawable drawable, Drawable drawable2) {
            this.mDrawableLtr = drawable;
            this.mDrawableRtl = drawable2;
            updateDrawable(true);
        }

        protected void updateDrawable(boolean z) {
            Layout layout;
            if ((z || !this.mIsDragging) && (layout = Editor.this.mTextView.getLayout()) != null) {
                int currentCursorOffset = getCurrentCursorOffset();
                boolean zIsAtRtlRun = isAtRtlRun(layout, currentCursorOffset);
                Drawable drawable = this.mDrawable;
                Drawable drawable2 = zIsAtRtlRun ? this.mDrawableRtl : this.mDrawableLtr;
                this.mDrawable = drawable2;
                this.mHotspotX = getHotspotX(drawable2, zIsAtRtlRun);
                this.mHorizontalGravity = getHorizontalGravity(zIsAtRtlRun);
                if (drawable == this.mDrawable || !isShowing()) {
                    return;
                }
                int cursorHorizontalPosition = ((getCursorHorizontalPosition(layout, currentCursorOffset) - this.mHotspotX) - getHorizontalOffset()) + getCursorOffset();
                this.mPositionX = cursorHorizontalPosition;
                this.mPositionX = cursorHorizontalPosition + Editor.this.mTextView.viewportToContentHorizontalOffset();
                this.mPositionHasChanged = true;
                updatePosition(this.mLastParentX, this.mLastParentY, false, false);
                postInvalidate();
            }
        }

        private void startTouchUpFilter(int i) {
            this.mNumberPreviousOffsets = 0;
            addPositionToTouchUpFilter(i);
        }

        private void addPositionToTouchUpFilter(int i) {
            int i2 = (this.mPreviousOffsetIndex + 1) % 5;
            this.mPreviousOffsetIndex = i2;
            this.mPreviousOffsets[i2] = i;
            this.mPreviousOffsetsTimes[i2] = SystemClock.uptimeMillis();
            this.mNumberPreviousOffsets++;
        }

        private void filterOnTouchUp(boolean z) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            int i = this.mPreviousOffsetIndex;
            int iMin = Math.min(this.mNumberPreviousOffsets, 5);
            int i2 = 0;
            while (i2 < iMin && jUptimeMillis - this.mPreviousOffsetsTimes[i] < 150) {
                i2++;
                i = ((this.mPreviousOffsetIndex - i2) + 5) % 5;
            }
            if (i2 <= 0 || i2 >= iMin || jUptimeMillis - this.mPreviousOffsetsTimes[i] <= 350) {
                return;
            }
            positionAtCursorOffset(this.mPreviousOffsets[i], false, z);
        }

        public boolean offsetHasBeenChanged() {
            return this.mNumberPreviousOffsets > 1;
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            setMeasuredDimension(getPreferredWidth(), getPreferredHeight());
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (isShowing()) {
                positionAtCursorOffset(getCurrentCursorOffset(), true, false);
            }
        }

        protected final int getPreferredWidth() {
            return Math.max(this.mDrawable.getIntrinsicWidth(), this.mMinSize);
        }

        protected final int getPreferredHeight() {
            return Math.max(this.mDrawable.getIntrinsicHeight(), this.mMinSize);
        }

        public void show() {
            if (isShowing()) {
                return;
            }
            Editor.this.getPositionListener().addSubscriber(this, true);
            this.mPreviousOffset = -1;
            positionAtCursorOffset(getCurrentCursorOffset(), false, false);
        }

        protected void dismiss() {
            this.mIsDragging = false;
            this.mContainer.dismiss();
            onDetached();
        }

        public void hide() {
            dismiss();
            Editor.this.getPositionListener().removeSubscriber(this);
        }

        public boolean isShowing() {
            return this.mContainer.isShowing();
        }

        private boolean shouldShow() {
            if (this.mIsDragging) {
                return true;
            }
            if (Editor.this.mTextView.isInBatchEditMode()) {
                return false;
            }
            return Editor.this.mTextView.isPositionVisible(this.mPositionX + this.mHotspotX + getHorizontalOffset(), this.mPositionY);
        }

        private void setVisible(boolean z) {
            this.mContainer.getContentView().setVisibility(z ? 0 : 4);
        }

        protected boolean isAtRtlRun(Layout layout, int i) {
            return layout.isRtlCharAt(i);
        }

        public float getHorizontal(Layout layout, int i) {
            return layout.getPrimaryHorizontal(i);
        }

        protected int getOffsetAtCoordinate(Layout layout, int i, float f) {
            return Editor.this.mTextView.getOffsetAtCoordinate(i, f);
        }

        protected void positionAtCursorOffset(int i, boolean z, boolean z2) {
            if (Editor.this.mTextView.getLayout() != null) {
                Layout layout = Editor.this.mTextView.getLayout();
                boolean z3 = i != this.mPreviousOffset;
                if (z3 || z) {
                    if (z3) {
                        updateSelection(i);
                        if (z2 && Editor.this.mHapticTextHandleEnabled) {
                            Editor.this.mTextView.performHapticFeedback(9);
                        }
                        addPositionToTouchUpFilter(i);
                    }
                    int lineForOffset = layout.getLineForOffset(i);
                    this.mPrevLine = lineForOffset;
                    this.mPositionX = ((getCursorHorizontalPosition(layout, i) - this.mHotspotX) - getHorizontalOffset()) + getCursorOffset();
                    this.mPositionY = layout.getLineBottomWithoutSpacing(lineForOffset);
                    this.mPositionX += Editor.this.mTextView.viewportToContentHorizontalOffset();
                    this.mPositionY += Editor.this.mTextView.viewportToContentVerticalOffset();
                    this.mPreviousOffset = i;
                    this.mPositionHasChanged = true;
                    return;
                }
                return;
            }
            Editor.this.prepareCursorControllers();
        }

        int getCursorHorizontalPosition(Layout layout, int i) {
            return (int) (getHorizontal(layout, i) - 0.5f);
        }

        @Override // android.widget.Editor.TextViewPositionListener
        public void updatePosition(int i, int i2, boolean z, boolean z2) {
            positionAtCursorOffset(getCurrentCursorOffset(), z2, false);
            if (z || this.mPositionHasChanged) {
                if (this.mIsDragging) {
                    if (i != this.mLastParentX || i2 != this.mLastParentY) {
                        this.mTouchToWindowOffsetX += i - this.mLastParentX;
                        this.mTouchToWindowOffsetY += i2 - this.mLastParentY;
                        this.mLastParentX = i;
                        this.mLastParentY = i2;
                    }
                    onHandleMoved();
                }
                if (shouldShow()) {
                    int[] iArr = {this.mPositionX + this.mHotspotX + getHorizontalOffset(), this.mPositionY};
                    Editor.this.mTextView.transformFromViewToWindowSpace(iArr);
                    iArr[0] = iArr[0] - (this.mHotspotX + getHorizontalOffset());
                    if (!isShowing()) {
                        this.mContainer.showAtLocation(Editor.this.mTextView, 0, iArr[0], iArr[1]);
                    } else {
                        this.mContainer.update(iArr[0], iArr[1], -1, -1);
                    }
                } else if (isShowing()) {
                    dismiss();
                }
                this.mPositionHasChanged = false;
            }
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            int intrinsicWidth = this.mDrawable.getIntrinsicWidth();
            int horizontalOffset = getHorizontalOffset();
            Drawable drawable = this.mDrawable;
            drawable.setBounds(horizontalOffset, 0, intrinsicWidth + horizontalOffset, drawable.getIntrinsicHeight());
            this.mDrawable.draw(canvas);
        }

        private int getHorizontalOffset() {
            int preferredWidth = getPreferredWidth();
            int intrinsicWidth = this.mDrawable.getIntrinsicWidth();
            int i = this.mHorizontalGravity;
            if (i != 3) {
                return i != 5 ? (preferredWidth - intrinsicWidth) / 2 : preferredWidth - intrinsicWidth;
            }
            return 0;
        }

        private boolean tooLargeTextForMagnifier() {
            if (Editor.this.mNewMagnifierEnabled) {
                Layout layout = Editor.this.mTextView.getLayout();
                int lineForOffset = layout.getLineForOffset(getCurrentCursorOffset());
                return layout.getLineBottomWithoutSpacing(lineForOffset) - layout.getLineTop(lineForOffset) >= Editor.this.mMaxLineHeightForMagnifier;
            }
            float fRound = Math.round(Editor.this.mMagnifierAnimator.mMagnifier.getHeight() / Editor.this.mMagnifierAnimator.mMagnifier.getZoom());
            Paint.FontMetrics fontMetrics = Editor.this.mTextView.getPaint().getFontMetrics();
            return (fontMetrics.descent - fontMetrics.ascent) * this.mTextViewScaleY > fRound;
        }

        private boolean checkForTransforms() {
            if (Editor.this.mMagnifierAnimator.mMagnifierIsShowing) {
                return true;
            }
            if (Editor.this.mTextView.getRotation() != 0.0f || Editor.this.mTextView.getRotationX() != 0.0f || Editor.this.mTextView.getRotationY() != 0.0f) {
                return false;
            }
            this.mTextViewScaleX = Editor.this.mTextView.getScaleX();
            this.mTextViewScaleY = Editor.this.mTextView.getScaleY();
            for (ViewParent parent = Editor.this.mTextView.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof View) {
                    View view = (View) parent;
                    if (view.getRotation() != 0.0f || view.getRotationX() != 0.0f || view.getRotationY() != 0.0f) {
                        return false;
                    }
                    this.mTextViewScaleX *= view.getScaleX();
                    this.mTextViewScaleY *= view.getScaleY();
                }
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0100  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0128  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean obtainMagnifierShowCoordinates(android.view.MotionEvent r14, android.graphics.PointF r15) {
            /*
                Method dump skipped, instruction units count: 462
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.HandleView.obtainMagnifierShowCoordinates(android.view.MotionEvent, android.graphics.PointF):boolean");
        }

        private boolean handleOverlapsMagnifier(HandleView handleView, Rect rect) {
            PopupWindow popupWindow = handleView.mContainer;
            if (popupWindow.hasDecorView()) {
                return Rect.intersects(new Rect(popupWindow.getDecorViewLayoutParams().x, popupWindow.getDecorViewLayoutParams().y, popupWindow.getDecorViewLayoutParams().x + popupWindow.getContentView().getWidth(), popupWindow.getDecorViewLayoutParams().y + popupWindow.getContentView().getHeight()), rect);
            }
            return false;
        }

        private HandleView getOtherSelectionHandle() {
            SelectionModifierCursorController selectionController = Editor.this.getSelectionController();
            if (selectionController == null || !selectionController.isActive()) {
                return null;
            }
            return selectionController.mStartHandle != this ? selectionController.mStartHandle : selectionController.mEndHandle;
        }

        private void updateHandlesVisibility() {
            Point position = Editor.this.mMagnifierAnimator.mMagnifier.getPosition();
            if (position == null) {
                return;
            }
            Rect rect = new Rect(position.x, position.y, position.x + Editor.this.mMagnifierAnimator.mMagnifier.getWidth(), position.y + Editor.this.mMagnifierAnimator.mMagnifier.getHeight());
            setVisible(!handleOverlapsMagnifier(this, rect));
            HandleView otherSelectionHandle = getOtherSelectionHandle();
            if (otherSelectionHandle != null) {
                otherSelectionHandle.setVisible(!handleOverlapsMagnifier(otherSelectionHandle, rect));
            }
        }

        protected final void updateMagnifier(MotionEvent motionEvent) {
            if (Editor.this.getMagnifierAnimator() == null) {
                return;
            }
            PointF pointF = new PointF();
            if (checkForTransforms() && !tooLargeTextForMagnifier() && obtainMagnifierShowCoordinates(motionEvent, pointF)) {
                Editor.this.mRenderCursorRegardlessTiming = true;
                Editor.this.mTextView.invalidateCursorPath();
                Editor.this.suspendBlink();
                if (Editor.this.mNewMagnifierEnabled) {
                    Layout layout = Editor.this.mTextView.getLayout();
                    int lineForOffset = layout.getLineForOffset(getCurrentCursorOffset());
                    Editor.this.mMagnifierAnimator.mMagnifier.setSourceHorizontalBounds(((int) layout.getLineLeft(lineForOffset)) + (Editor.this.mTextView.getTotalPaddingLeft() - Editor.this.mTextView.getScrollX()), ((int) layout.getLineRight(lineForOffset)) + (Editor.this.mTextView.getTotalPaddingLeft() - Editor.this.mTextView.getScrollX()));
                    int lineBottomWithoutSpacing = layout.getLineBottomWithoutSpacing(lineForOffset) - layout.getLineTop(lineForOffset);
                    float f = Editor.this.mInitialZoom;
                    if (lineBottomWithoutSpacing < Editor.this.mMinLineHeightForMagnifier) {
                        f = (f * Editor.this.mMinLineHeightForMagnifier) / lineBottomWithoutSpacing;
                    }
                    Editor.this.mMagnifierAnimator.mMagnifier.updateSourceFactors(lineBottomWithoutSpacing, f);
                    Editor.this.mMagnifierAnimator.mMagnifier.show(pointF.x, pointF.y);
                } else {
                    Editor.this.mMagnifierAnimator.show(pointF.x, pointF.y);
                }
                updateHandlesVisibility();
                return;
            }
            dismissMagnifier();
        }

        protected final void dismissMagnifier() {
            if (Editor.this.mMagnifierAnimator != null) {
                Editor.this.mMagnifierAnimator.dismiss();
                Editor.this.mRenderCursorRegardlessTiming = false;
                Editor.this.resumeBlink();
                setVisible(true);
                HandleView otherSelectionHandle = getOtherSelectionHandle();
                if (otherSelectionHandle != null) {
                    otherSelectionHandle.setVisible(true);
                }
            }
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            float fMin;
            Editor.this.updateFloatingToolbarVisibility(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                startTouchUpFilter(getCurrentCursorOffset());
                PositionListener positionListener = Editor.this.getPositionListener();
                this.mLastParentX = positionListener.getPositionX();
                this.mLastParentY = positionListener.getPositionY();
                this.mLastParentXOnScreen = positionListener.getPositionXOnScreen();
                this.mLastParentYOnScreen = positionListener.getPositionYOnScreen();
                float rawX = (motionEvent.getRawX() - this.mLastParentXOnScreen) + this.mLastParentX;
                float rawY = (motionEvent.getRawY() - this.mLastParentYOnScreen) + this.mLastParentY;
                this.mTouchToWindowOffsetX = rawX - this.mPositionX;
                this.mTouchToWindowOffsetY = rawY - this.mPositionY;
                this.mIsDragging = true;
                this.mPreviousLineTouched = -1;
            } else {
                if (actionMasked == 1) {
                    filterOnTouchUp(motionEvent.isFromSource(4098));
                } else if (actionMasked == 2) {
                    float rawX2 = (motionEvent.getRawX() - this.mLastParentXOnScreen) + this.mLastParentX;
                    float rawY2 = motionEvent.getRawY() - this.mLastParentYOnScreen;
                    int i = this.mLastParentY;
                    float f = rawY2 + i;
                    float f2 = this.mTouchToWindowOffsetY - i;
                    float f3 = (f - this.mPositionY) - i;
                    float f4 = this.mIdealVerticalOffset;
                    if (f2 < f4) {
                        fMin = Math.max(Math.min(f3, f4), f2);
                    } else {
                        fMin = Math.min(Math.max(f3, f4), f2);
                    }
                    this.mTouchToWindowOffsetY = fMin + this.mLastParentY;
                    updatePosition((rawX2 - this.mTouchToWindowOffsetX) + this.mHotspotX + getHorizontalOffset(), (f - this.mTouchToWindowOffsetY) + this.mTouchOffsetY, motionEvent.isFromSource(4098));
                } else if (actionMasked == 3) {
                }
                this.mIsDragging = false;
                updateDrawable(false);
            }
            return true;
        }

        public boolean isDragging() {
            return this.mIsDragging;
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            setSystemGestureExclusionRects(Collections.singletonList(new Rect(0, 0, i, i2)));
        }
    }

    private class InsertionHandleView extends HandleView {
        private final int mDeltaHeight;
        private final int mDrawableOpacity;
        private Runnable mHider;
        private boolean mIsInActionMode;
        private boolean mIsTouchDown;
        private float mLastDownRawX;
        private float mLastDownRawY;
        private long mLastUpTime;
        private boolean mOffsetChanged;
        private int mOffsetDown;
        private boolean mPendingDismissOnUp;
        private float mTouchDownX;
        private float mTouchDownY;

        @Override // android.widget.Editor.HandleView
        protected int getHorizontalGravity(boolean z) {
            return 1;
        }

        @Override // android.widget.Editor.HandleView
        protected int getMagnifierHandleTrigger() {
            return 0;
        }

        InsertionHandleView(Drawable drawable) {
            super(drawable, drawable, R.id.insertion_handle);
            int i = 0;
            this.mIsTouchDown = false;
            this.mPendingDismissOnUp = false;
            int i2 = 255;
            if (Editor.this.mFlagInsertionHandleGesturesEnabled) {
                i = 25;
                int intCoreSetting = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_INSERTION_HANDLE_DELTA_HEIGHT, 25);
                int i3 = 50;
                int intCoreSetting2 = AppGlobals.getIntCoreSetting(WidgetFlags.KEY_INSERTION_HANDLE_OPACITY, 50);
                if (intCoreSetting >= -25 && intCoreSetting <= 50) {
                    i = intCoreSetting;
                }
                if (intCoreSetting2 >= 10 && intCoreSetting2 <= 100) {
                    i3 = intCoreSetting2;
                }
                i2 = (i3 * 255) / 100;
            }
            this.mDeltaHeight = i;
            this.mDrawableOpacity = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void hideAfterDelay() {
            if (this.mHider == null) {
                this.mHider = new Runnable() { // from class: android.widget.Editor.InsertionHandleView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        InsertionHandleView.this.hide();
                    }
                };
            } else {
                removeHiderCallback();
            }
            Editor.this.mTextView.postDelayed(this.mHider, 4000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeHiderCallback() {
            if (this.mHider != null) {
                Editor.this.mTextView.removeCallbacks(this.mHider);
            }
        }

        @Override // android.widget.Editor.HandleView
        protected int getHotspotX(Drawable drawable, boolean z) {
            return drawable.getIntrinsicWidth() / 2;
        }

        @Override // android.widget.Editor.HandleView
        protected int getCursorOffset() {
            int cursorOffset = super.getCursorOffset();
            if (Editor.this.mDrawableForCursor == null) {
                return cursorOffset;
            }
            Editor.this.mDrawableForCursor.getPadding(Editor.this.mTempRect);
            return cursorOffset + (((Editor.this.mDrawableForCursor.getIntrinsicWidth() - Editor.this.mTempRect.left) - Editor.this.mTempRect.right) / 2);
        }

        @Override // android.widget.Editor.HandleView
        int getCursorHorizontalPosition(Layout layout, int i) {
            if (Editor.this.mDrawableForCursor != null) {
                float horizontal = getHorizontal(layout, i);
                Editor editor = Editor.this;
                return editor.clampHorizontalPosition(editor.mDrawableForCursor, horizontal) + Editor.this.mTempRect.left;
            }
            return super.getCursorHorizontalPosition(layout, i);
        }

        @Override // android.widget.Editor.HandleView, android.view.View
        protected void onMeasure(int i, int i2) {
            if (Editor.this.mFlagInsertionHandleGesturesEnabled) {
                setMeasuredDimension(getPreferredWidth(), Math.max(getPreferredHeight() + this.mDeltaHeight, this.mDrawable.getIntrinsicHeight()));
            } else {
                super.onMeasure(i, i2);
            }
        }

        @Override // android.widget.Editor.HandleView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!Editor.this.mTextView.isFromPrimePointer(motionEvent, true)) {
                return true;
            }
            if (Editor.this.mFlagInsertionHandleGesturesEnabled && Editor.this.mFlagCursorDragFromAnywhereEnabled) {
                return touchThrough(motionEvent);
            }
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mLastDownRawX = motionEvent.getRawX();
                this.mLastDownRawY = motionEvent.getRawY();
                updateMagnifier(motionEvent);
            } else {
                if (actionMasked != 1) {
                    if (actionMasked == 2) {
                        updateMagnifier(motionEvent);
                    } else if (actionMasked == 3) {
                    }
                } else if (!offsetHasBeenChanged()) {
                    if (EditorTouchState.isDistanceWithin(this.mLastDownRawX, this.mLastDownRawY, motionEvent.getRawX(), motionEvent.getRawY(), ViewConfiguration.get(Editor.this.mTextView.getContext()).getScaledTouchSlop())) {
                        Editor.this.toggleInsertionActionMode();
                    }
                } else if (Editor.this.mTextActionMode != null) {
                    Editor.this.mTextActionMode.invalidateContentRect();
                }
                hideAfterDelay();
                dismissMagnifier();
            }
            return zOnTouchEvent;
        }

        private boolean touchThrough(MotionEvent motionEvent) {
            int selectionStart;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mIsTouchDown = true;
                this.mOffsetChanged = false;
                this.mOffsetDown = Editor.this.mTextView.getSelectionStart();
                this.mTouchDownX = motionEvent.getX();
                this.mTouchDownY = motionEvent.getY();
                this.mIsInActionMode = Editor.this.mTextActionMode != null;
                if (motionEvent.getEventTime() - this.mLastUpTime < ViewConfiguration.getDoubleTapTimeout()) {
                    Editor.this.lambda$startActionModeInternal$0$Editor();
                }
                Editor.this.mTouchState.setIsOnHandle(true);
            } else if (actionMasked == 1) {
                this.mLastUpTime = motionEvent.getEventTime();
            }
            boolean zOnTouchEvent = Editor.this.mTextView.onTouchEvent(transformEventForTouchThrough(motionEvent));
            if (actionMasked == 1 || actionMasked == 3) {
                this.mIsTouchDown = false;
                if (this.mPendingDismissOnUp) {
                    dismiss();
                }
                Editor.this.mTouchState.setIsOnHandle(false);
            }
            if (!this.mOffsetChanged && ((selectionStart = Editor.this.mTextView.getSelectionStart()) != Editor.this.mTextView.getSelectionEnd() || this.mOffsetDown != selectionStart)) {
                this.mOffsetChanged = true;
            }
            if (!this.mOffsetChanged && actionMasked == 1) {
                if (this.mIsInActionMode) {
                    Editor.this.lambda$startActionModeInternal$0$Editor();
                } else {
                    Editor.this.startInsertionActionMode();
                }
            }
            return zOnTouchEvent;
        }

        private MotionEvent transformEventForTouchThrough(MotionEvent motionEvent) {
            Layout layout = Editor.this.mTextView.getLayout();
            int lineForOffset = layout.getLineForOffset(getCurrentCursorOffset());
            int lineBottomWithoutSpacing = layout.getLineBottomWithoutSpacing(lineForOffset) - layout.getLineTop(lineForOffset);
            Matrix matrix = new Matrix();
            matrix.setTranslate(((motionEvent.getRawX() - motionEvent.getX()) + (getMeasuredWidth() >> 1)) - this.mTouchDownX, ((motionEvent.getRawY() - motionEvent.getY()) - (lineBottomWithoutSpacing >> 1)) - this.mTouchDownY);
            motionEvent.transform(matrix);
            Editor.this.mTextView.toLocalMotionEvent(motionEvent);
            return motionEvent;
        }

        @Override // android.widget.Editor.HandleView
        public boolean isShowing() {
            if (this.mPendingDismissOnUp) {
                return false;
            }
            return super.isShowing();
        }

        @Override // android.widget.Editor.HandleView
        public void show() {
            super.show();
            this.mPendingDismissOnUp = false;
            this.mDrawable.setAlpha(this.mDrawableOpacity);
        }

        @Override // android.widget.Editor.HandleView
        public void dismiss() {
            if (this.mIsTouchDown) {
                this.mPendingDismissOnUp = true;
                this.mDrawable.setAlpha(0);
            } else {
                super.dismiss();
                this.mPendingDismissOnUp = false;
            }
        }

        @Override // android.widget.Editor.HandleView
        protected void updateDrawable(boolean z) {
            super.updateDrawable(z);
            this.mDrawable.setAlpha(this.mDrawableOpacity);
        }

        @Override // android.widget.Editor.HandleView
        public int getCurrentCursorOffset() {
            return Editor.this.mTextView.getSelectionStart();
        }

        @Override // android.widget.Editor.HandleView
        public void updateSelection(int i) {
            Selection.setSelection((Spannable) Editor.this.mTextView.getText(), i);
        }

        @Override // android.widget.Editor.HandleView
        protected void updatePosition(float f, float f2, boolean z) {
            Layout layout = Editor.this.mTextView.getLayout();
            int offsetAtCoordinate = -1;
            if (layout != null) {
                if (this.mPreviousLineTouched == -1) {
                    this.mPreviousLineTouched = Editor.this.mTextView.getLineAtCoordinate(f2);
                }
                int currentLineAdjustedForSlop = Editor.this.getCurrentLineAdjustedForSlop(layout, this.mPreviousLineTouched, f2);
                offsetAtCoordinate = getOffsetAtCoordinate(layout, currentLineAdjustedForSlop, f);
                this.mPreviousLineTouched = currentLineAdjustedForSlop;
            }
            positionAtCursorOffset(offsetAtCoordinate, false, z);
            if (Editor.this.mTextActionMode != null) {
                Editor.this.invalidateActionMode();
            }
        }

        @Override // android.widget.Editor.HandleView
        void onHandleMoved() {
            super.onHandleMoved();
            removeHiderCallback();
        }

        @Override // android.widget.Editor.HandleView
        public void onDetached() {
            super.onDetached();
            removeHiderCallback();
        }
    }

    public final class SelectionHandleView extends HandleView {
        private final int mHandleType;
        private boolean mInWord;
        private boolean mLanguageDirectionChanged;
        private float mPrevX;
        private final float mTextViewEdgeSlop;
        private final int[] mTextViewLocation;
        private float mTouchWordDelta;

        public SelectionHandleView(Drawable drawable, Drawable drawable2, int i, int i2) {
            super(drawable, drawable2, i);
            this.mInWord = false;
            this.mLanguageDirectionChanged = false;
            this.mTextViewLocation = new int[2];
            this.mHandleType = i2;
            this.mTextViewEdgeSlop = ViewConfiguration.get(Editor.this.mTextView.getContext()).getScaledTouchSlop() * 4;
        }

        private boolean isStartHandle() {
            return this.mHandleType == 0;
        }

        @Override // android.widget.Editor.HandleView
        protected int getHotspotX(Drawable drawable, boolean z) {
            if (z == isStartHandle()) {
                return drawable.getIntrinsicWidth() / 4;
            }
            return (drawable.getIntrinsicWidth() * 3) / 4;
        }

        @Override // android.widget.Editor.HandleView
        protected int getHorizontalGravity(boolean z) {
            return z == isStartHandle() ? 3 : 5;
        }

        @Override // android.widget.Editor.HandleView
        public int getCurrentCursorOffset() {
            return isStartHandle() ? Editor.this.mTextView.getSelectionStart() : Editor.this.mTextView.getSelectionEnd();
        }

        @Override // android.widget.Editor.HandleView
        protected void updateSelection(int i) {
            if (isStartHandle()) {
                Selection.setSelection((Spannable) Editor.this.mTextView.getText(), i, Editor.this.mTextView.getSelectionEnd());
            } else {
                Selection.setSelection((Spannable) Editor.this.mTextView.getText(), Editor.this.mTextView.getSelectionStart(), i);
            }
            updateDrawable(false);
            if (Editor.this.mTextActionMode != null) {
                Editor.this.invalidateActionMode();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:174:0x025b  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x011a  */
        @Override // android.widget.Editor.HandleView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void updatePosition(float r17, float r18, boolean r19) {
            /*
                Method dump skipped, instruction units count: 619
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.SelectionHandleView.updatePosition(float, float, boolean):void");
        }

        @Override // android.widget.Editor.HandleView
        protected void positionAtCursorOffset(int i, boolean z, boolean z2) {
            super.positionAtCursorOffset(i, z, z2);
            this.mInWord = (i == -1 || Editor.this.getWordIteratorWithText().isBoundary(i)) ? false : true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
        @Override // android.widget.Editor.HandleView, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouchEvent(android.view.MotionEvent r4) {
            /*
                r3 = this;
                android.widget.Editor r0 = android.widget.Editor.this
                android.widget.TextView r0 = android.widget.Editor.access$300(r0)
                r1 = 1
                boolean r0 = r0.isFromPrimePointer(r4, r1)
                if (r0 != 0) goto Le
                return r1
            Le:
                boolean r0 = super.onTouchEvent(r4)
                int r2 = r4.getActionMasked()
                if (r2 == 0) goto L29
                if (r2 == r1) goto L25
                r1 = 2
                if (r2 == r1) goto L21
                r4 = 3
                if (r2 == r4) goto L25
                goto L33
            L21:
                r3.updateMagnifier(r4)
                goto L33
            L25:
                r3.dismissMagnifier()
                goto L33
            L29:
                r1 = 0
                r3.mTouchWordDelta = r1
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                r3.mPrevX = r1
                r3.updateMagnifier(r4)
            L33:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.SelectionHandleView.onTouchEvent(android.view.MotionEvent):boolean");
        }

        private void positionAndAdjustForCrossingHandles(int i, boolean z) {
            int iUnpackRangeEndFromLong;
            int selectionEnd = isStartHandle() ? Editor.this.mTextView.getSelectionEnd() : Editor.this.mTextView.getSelectionStart();
            if ((isStartHandle() && i >= selectionEnd) || (!isStartHandle() && i <= selectionEnd)) {
                this.mTouchWordDelta = 0.0f;
                Layout layout = Editor.this.mTextView.getLayout();
                if (layout != null && i != selectionEnd) {
                    float horizontal = getHorizontal(layout, i);
                    float horizontal2 = getHorizontal(layout, selectionEnd, !isStartHandle());
                    float horizontal3 = getHorizontal(layout, this.mPreviousOffset);
                    if ((horizontal3 < horizontal2 && horizontal < horizontal2) || (horizontal3 > horizontal2 && horizontal > horizontal2)) {
                        int currentCursorOffset = getCurrentCursorOffset();
                        if (!isStartHandle()) {
                            currentCursorOffset = Math.max(currentCursorOffset - 1, 0);
                        }
                        long runRange = layout.getRunRange(currentCursorOffset);
                        if (isStartHandle()) {
                            iUnpackRangeEndFromLong = TextUtils.unpackRangeStartFromLong(runRange);
                        } else {
                            iUnpackRangeEndFromLong = TextUtils.unpackRangeEndFromLong(runRange);
                        }
                        positionAtCursorOffset(iUnpackRangeEndFromLong, false, z);
                        return;
                    }
                }
                i = Editor.this.getNextCursorOffset(selectionEnd, !isStartHandle());
            }
            positionAtCursorOffset(i, false, z);
        }

        private boolean positionNearEdgeOfScrollingView(float f, boolean z) {
            Editor.this.mTextView.getLocationOnScreen(this.mTextViewLocation);
            if (z == isStartHandle()) {
                if (f > ((this.mTextViewLocation[0] + Editor.this.mTextView.getWidth()) - Editor.this.mTextView.getPaddingRight()) - this.mTextViewEdgeSlop) {
                    return true;
                }
            } else if (f < this.mTextViewLocation[0] + Editor.this.mTextView.getPaddingLeft() + this.mTextViewEdgeSlop) {
                return true;
            }
            return false;
        }

        @Override // android.widget.Editor.HandleView
        protected boolean isAtRtlRun(Layout layout, int i) {
            if (!isStartHandle()) {
                i = Math.max(i - 1, 0);
            }
            return layout.isRtlCharAt(i);
        }

        @Override // android.widget.Editor.HandleView
        public float getHorizontal(Layout layout, int i) {
            return getHorizontal(layout, i, isStartHandle());
        }

        private float getHorizontal(Layout layout, int i, boolean z) {
            return layout.isRtlCharAt(z ? i : Math.max(i + (-1), 0)) == (layout.getParagraphDirection(layout.getLineForOffset(i)) == -1) ? layout.getPrimaryHorizontal(i) : layout.getSecondaryHorizontal(i);
        }

        @Override // android.widget.Editor.HandleView
        protected int getOffsetAtCoordinate(Layout layout, int i, float f) {
            float fConvertToLocalHorizontalCoordinate = Editor.this.mTextView.convertToLocalHorizontalCoordinate(f);
            int offsetForHorizontal = layout.getOffsetForHorizontal(i, fConvertToLocalHorizontalCoordinate, true);
            if (!layout.isLevelBoundary(offsetForHorizontal)) {
                return offsetForHorizontal;
            }
            int offsetForHorizontal2 = layout.getOffsetForHorizontal(i, fConvertToLocalHorizontalCoordinate, false);
            int currentCursorOffset = getCurrentCursorOffset();
            int iAbs = Math.abs(offsetForHorizontal - currentCursorOffset);
            int iAbs2 = Math.abs(offsetForHorizontal2 - currentCursorOffset);
            if (iAbs < iAbs2) {
                return offsetForHorizontal;
            }
            if (iAbs > iAbs2) {
                return offsetForHorizontal2;
            }
            if (!isStartHandle()) {
                currentCursorOffset = Math.max(currentCursorOffset - 1, 0);
            }
            return layout.isRtlCharAt(currentCursorOffset) == (layout.getParagraphDirection(i) == -1) ? offsetForHorizontal : offsetForHorizontal2;
        }

        @Override // android.widget.Editor.HandleView
        protected int getMagnifierHandleTrigger() {
            return isStartHandle() ? 1 : 2;
        }
    }

    public void setLineChangeSlopMinMaxForTesting(int i, int i2) {
        this.mLineChangeSlopMin = i;
        this.mLineChangeSlopMax = i2;
    }

    public int getCurrentLineAdjustedForSlop(Layout layout, int i, float f) {
        int lineAtCoordinate = this.mTextView.getLineAtCoordinate(f);
        if (layout == null || i > layout.getLineCount() || layout.getLineCount() <= 0 || i < 0 || Math.abs(lineAtCoordinate - i) >= 2) {
            return lineAtCoordinate;
        }
        int lineHeight = (int) (this.mTextView.getLineHeight() * 0.5f);
        float fViewportToContentVerticalOffset = this.mTextView.viewportToContentVerticalOffset();
        return (lineAtCoordinate <= i || f < ((float) (layout.getLineBottom(i) + lineHeight)) + fViewportToContentVerticalOffset) ? (lineAtCoordinate >= i || f > ((float) (layout.getLineTop(i) - lineHeight)) + fViewportToContentVerticalOffset) ? i : lineAtCoordinate : lineAtCoordinate;
    }

    void loadCursorDrawable() {
        if (this.mDrawableForCursor == null) {
            this.mDrawableForCursor = this.mTextView.getTextCursorDrawable();
        }
    }

    public class InsertionPointCursorController implements CursorController {
        private InsertionHandleView mHandle;
        private boolean mIsDraggingCursor;
        private boolean mIsTouchSnappedToHandleDuringDrag;
        private int mPrevLineDuringDrag;

        public InsertionPointCursorController() {
        }

        public void onTouchEvent(MotionEvent motionEvent) {
            if (Editor.this.hasSelectionController() && Editor.this.getSelectionController().isCursorBeingModified()) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (motionEvent.isFromSource(8194)) {
                        return;
                    }
                    if (!this.mIsDraggingCursor) {
                        if (Editor.this.mFlagCursorDragFromAnywhereEnabled && Editor.this.mTextView.getLayout() != null && Editor.this.mTextView.isFocused() && Editor.this.mTouchState.isMovedEnoughForDrag()) {
                            if (Editor.this.mTouchState.getInitialDragDirectionXYRatio() > Editor.this.mCursorDragDirectionMinXYRatio || Editor.this.mTouchState.isOnHandle()) {
                                startCursorDrag(motionEvent);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    performCursorDrag(motionEvent);
                    return;
                }
                if (actionMasked != 3) {
                    return;
                }
            }
            if (this.mIsDraggingCursor) {
                endCursorDrag(motionEvent);
            }
        }

        private void positionCursorDuringDrag(MotionEvent motionEvent) {
            this.mPrevLineDuringDrag = getLineDuringDrag(motionEvent);
            int offsetAtCoordinate = Editor.this.mTextView.getOffsetAtCoordinate(this.mPrevLineDuringDrag, motionEvent.getX());
            int selectionStart = Editor.this.mTextView.getSelectionStart();
            int selectionEnd = Editor.this.mTextView.getSelectionEnd();
            if (offsetAtCoordinate == selectionStart && offsetAtCoordinate == selectionEnd) {
                return;
            }
            Selection.setSelection((Spannable) Editor.this.mTextView.getText(), offsetAtCoordinate);
            Editor.this.updateCursorPosition();
            if (Editor.this.mHapticTextHandleEnabled) {
                Editor.this.mTextView.performHapticFeedback(9);
            }
        }

        private int getLineDuringDrag(MotionEvent motionEvent) {
            float y;
            Layout layout = Editor.this.mTextView.getLayout();
            int i = this.mPrevLineDuringDrag;
            if (i != -1) {
                if (Editor.this.mTouchState.isOnHandle()) {
                    y = motionEvent.getRawY() - Editor.this.mTextView.getLocationOnScreen()[1];
                } else {
                    y = motionEvent.getY();
                }
                int currentLineAdjustedForSlop = Editor.this.getCurrentLineAdjustedForSlop(layout, this.mPrevLineDuringDrag, y - getHandle().getIdealFingerToCursorOffset());
                if (this.mIsTouchSnappedToHandleDuringDrag) {
                    return currentLineAdjustedForSlop;
                }
                int i2 = this.mPrevLineDuringDrag;
                if (currentLineAdjustedForSlop < i2) {
                    return Math.min(i2, Editor.this.getCurrentLineAdjustedForSlop(layout, i2, y));
                }
                this.mIsTouchSnappedToHandleDuringDrag = true;
                return currentLineAdjustedForSlop;
            }
            return Editor.this.getCurrentLineAdjustedForSlop(layout, i, motionEvent.getY());
        }

        private void startCursorDrag(MotionEvent motionEvent) {
            this.mIsDraggingCursor = true;
            this.mIsTouchSnappedToHandleDuringDrag = false;
            this.mPrevLineDuringDrag = -1;
            Editor.this.mTextView.getParent().requestDisallowInterceptTouchEvent(true);
            Editor.this.mTextView.cancelLongPress();
            positionCursorDuringDrag(motionEvent);
            show();
            getHandle().removeHiderCallback();
            getHandle().updateMagnifier(motionEvent);
        }

        private void performCursorDrag(MotionEvent motionEvent) {
            positionCursorDuringDrag(motionEvent);
            getHandle().updateMagnifier(motionEvent);
        }

        private void endCursorDrag(MotionEvent motionEvent) {
            this.mIsDraggingCursor = false;
            this.mIsTouchSnappedToHandleDuringDrag = false;
            this.mPrevLineDuringDrag = -1;
            getHandle().dismissMagnifier();
            getHandle().hideAfterDelay();
            Editor.this.mTextView.getParent().requestDisallowInterceptTouchEvent(false);
        }

        @Override // android.widget.Editor.CursorController
        public void show() {
            getHandle().show();
            long jUptimeMillis = SystemClock.uptimeMillis() - TextView.sLastCutCopyOrTextChangedTime;
            if (Editor.this.mInsertionActionModeRunnable != null && (this.mIsDraggingCursor || Editor.this.mTouchState.isMultiTap() || Editor.this.isCursorInsideEasyCorrectionSpan())) {
                Editor.this.mTextView.removeCallbacks(Editor.this.mInsertionActionModeRunnable);
            }
            if (!this.mIsDraggingCursor && !Editor.this.mTouchState.isMultiTap() && !Editor.this.isCursorInsideEasyCorrectionSpan() && jUptimeMillis < 15000 && Editor.this.mTextActionMode == null) {
                if (Editor.this.mInsertionActionModeRunnable == null) {
                    Editor.this.mInsertionActionModeRunnable = new Runnable() { // from class: android.widget.Editor.InsertionPointCursorController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Editor.this.startInsertionActionMode();
                        }
                    };
                }
                Editor.this.mTextView.postDelayed(Editor.this.mInsertionActionModeRunnable, ViewConfiguration.getDoubleTapTimeout() + 1);
            }
            if (!this.mIsDraggingCursor) {
                getHandle().hideAfterDelay();
            }
            if (Editor.this.mSelectionModifierCursorController != null) {
                Editor.this.mSelectionModifierCursorController.hide();
            }
        }

        @Override // android.widget.Editor.CursorController
        public void hide() {
            InsertionHandleView insertionHandleView = this.mHandle;
            if (insertionHandleView != null) {
                insertionHandleView.hide();
            }
        }

        @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
        public void onTouchModeChanged(boolean z) {
            if (z) {
                return;
            }
            hide();
        }

        public InsertionHandleView getHandle() {
            if (this.mHandle == null) {
                Editor.this.loadHandleDrawables(false);
                Editor editor = Editor.this;
                this.mHandle = editor.new InsertionHandleView(editor.mSelectHandleCenter);
            }
            return this.mHandle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reloadHandleDrawable() {
            InsertionHandleView insertionHandleView = this.mHandle;
            if (insertionHandleView == null) {
                return;
            }
            insertionHandleView.setDrawables(Editor.this.mSelectHandleCenter, Editor.this.mSelectHandleCenter);
        }

        @Override // android.widget.Editor.CursorController
        public void onDetached() {
            Editor.this.mTextView.getViewTreeObserver().removeOnTouchModeChangeListener(this);
            InsertionHandleView insertionHandleView = this.mHandle;
            if (insertionHandleView != null) {
                insertionHandleView.onDetached();
            }
        }

        @Override // android.widget.Editor.CursorController
        public boolean isCursorBeingModified() {
            InsertionHandleView insertionHandleView;
            return this.mIsDraggingCursor || ((insertionHandleView = this.mHandle) != null && insertionHandleView.isDragging());
        }

        @Override // android.widget.Editor.CursorController
        public boolean isActive() {
            InsertionHandleView insertionHandleView = this.mHandle;
            return insertionHandleView != null && insertionHandleView.isShowing();
        }

        public void invalidateHandle() {
            InsertionHandleView insertionHandleView = this.mHandle;
            if (insertionHandleView != null) {
                insertionHandleView.invalidate();
            }
        }
    }

    public class SelectionModifierCursorController implements CursorController {
        private static final int DRAG_ACCELERATOR_MODE_CHARACTER = 1;
        private static final int DRAG_ACCELERATOR_MODE_INACTIVE = 0;
        private static final int DRAG_ACCELERATOR_MODE_PARAGRAPH = 3;
        private static final int DRAG_ACCELERATOR_MODE_WORD = 2;
        private SelectionHandleView mEndHandle;
        private boolean mGestureStayedInTapRegion;
        private boolean mHaventMovedEnoughToStartDrag;
        private int mMaxTouchOffset;
        private int mMinTouchOffset;
        private SelectionHandleView mStartHandle;
        private int mStartOffset = -1;
        private int mLineSelectionIsOn = -1;
        private boolean mSwitchedLines = false;
        private int mDragAcceleratorMode = 0;

        SelectionModifierCursorController() {
            resetTouchOffsets();
        }

        @Override // android.widget.Editor.CursorController
        public void show() {
            if (Editor.this.mTextView.isInBatchEditMode()) {
                return;
            }
            Editor.this.loadHandleDrawables(false);
            initHandles();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initHandles() {
            if (this.mStartHandle == null) {
                Editor editor = Editor.this;
                this.mStartHandle = editor.new SelectionHandleView(editor.mSelectHandleLeft, Editor.this.mSelectHandleRight, R.id.selection_start_handle, 0);
            }
            if (this.mEndHandle == null) {
                Editor editor2 = Editor.this;
                this.mEndHandle = editor2.new SelectionHandleView(editor2.mSelectHandleRight, Editor.this.mSelectHandleLeft, R.id.selection_end_handle, 1);
            }
            this.mStartHandle.show();
            this.mEndHandle.show();
            Editor.this.hideInsertionPointCursorController();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reloadHandleDrawables() {
            SelectionHandleView selectionHandleView = this.mStartHandle;
            if (selectionHandleView == null) {
                return;
            }
            selectionHandleView.setDrawables(Editor.this.mSelectHandleLeft, Editor.this.mSelectHandleRight);
            this.mEndHandle.setDrawables(Editor.this.mSelectHandleRight, Editor.this.mSelectHandleLeft);
        }

        @Override // android.widget.Editor.CursorController
        public void hide() {
            SelectionHandleView selectionHandleView = this.mStartHandle;
            if (selectionHandleView != null) {
                selectionHandleView.hide();
            }
            SelectionHandleView selectionHandleView2 = this.mEndHandle;
            if (selectionHandleView2 != null) {
                selectionHandleView2.hide();
            }
        }

        public void enterDrag(int i) {
            show();
            this.mDragAcceleratorMode = i;
            this.mStartOffset = Editor.this.mTextView.getOffsetForPosition(Editor.this.mTouchState.getLastDownX(), Editor.this.mTouchState.getLastDownY());
            this.mLineSelectionIsOn = Editor.this.mTextView.getLineAtCoordinate(Editor.this.mTouchState.getLastDownY());
            hide();
            Editor.this.mTextView.getParent().requestDisallowInterceptTouchEvent(true);
            Editor.this.mTextView.cancelLongPress();
        }

        public void onTouchEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            boolean zIsFromSource = motionEvent.isFromSource(8194);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (!Editor.this.extractedTextModeWillBeStarted()) {
                    int offsetForPosition = Editor.this.mTextView.getOffsetForPosition(x, y);
                    this.mMaxTouchOffset = offsetForPosition;
                    this.mMinTouchOffset = offsetForPosition;
                    if (this.mGestureStayedInTapRegion && Editor.this.mTouchState.isMultiTapInSameArea() && (zIsFromSource || Editor.this.isPositionOnText(x, y) || Editor.this.mTouchState.isOnHandle())) {
                        if (Editor.this.mTouchState.isDoubleTap()) {
                            Editor.this.selectCurrentWordAndStartDrag();
                        } else if (Editor.this.mTouchState.isTripleClick()) {
                            selectCurrentParagraphAndStartDrag();
                        }
                        Editor.this.mDiscardNextActionUp = true;
                    }
                    this.mGestureStayedInTapRegion = true;
                    this.mHaventMovedEnoughToStartDrag = true;
                    return;
                }
                hide();
                return;
            }
            if (actionMasked == 1) {
                if (isDragAcceleratorActive()) {
                    updateSelection(motionEvent);
                    Editor.this.mTextView.getParent().requestDisallowInterceptTouchEvent(false);
                    resetDragAcceleratorState();
                    if (Editor.this.mTextView.hasSelection()) {
                        Editor.this.startSelectionActionModeAsync(this.mHaventMovedEnoughToStartDrag);
                        return;
                    }
                    return;
                }
                return;
            }
            if (actionMasked != 2) {
                if ((actionMasked == 5 || actionMasked == 6) && Editor.this.mTextView.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT)) {
                    updateMinAndMaxOffsets(motionEvent);
                    return;
                }
                return;
            }
            if (this.mGestureStayedInTapRegion) {
                this.mGestureStayedInTapRegion = EditorTouchState.isDistanceWithin(Editor.this.mTouchState.getLastDownX(), Editor.this.mTouchState.getLastDownY(), x, y, ViewConfiguration.get(Editor.this.mTextView.getContext()).getScaledDoubleTapTouchSlop());
            }
            if (this.mHaventMovedEnoughToStartDrag) {
                this.mHaventMovedEnoughToStartDrag = !Editor.this.mTouchState.isMovedEnoughForDrag();
            }
            if (zIsFromSource && !isDragAcceleratorActive()) {
                int offsetForPosition2 = Editor.this.mTextView.getOffsetForPosition(x, y);
                if (Editor.this.mTextView.hasSelection() && ((!this.mHaventMovedEnoughToStartDrag || this.mStartOffset != offsetForPosition2) && offsetForPosition2 >= Editor.this.mTextView.getSelectionStart() && offsetForPosition2 <= Editor.this.mTextView.getSelectionEnd())) {
                    Editor.this.startDragAndDrop();
                    return;
                } else if (this.mStartOffset != offsetForPosition2) {
                    Editor.this.lambda$startActionModeInternal$0$Editor();
                    enterDrag(1);
                    Editor.this.mDiscardNextActionUp = true;
                    this.mHaventMovedEnoughToStartDrag = false;
                }
            }
            SelectionHandleView selectionHandleView = this.mStartHandle;
            if (selectionHandleView == null || !selectionHandleView.isShowing()) {
                updateSelection(motionEvent);
            }
        }

        private void updateSelection(MotionEvent motionEvent) {
            if (Editor.this.mTextView.getLayout() != null) {
                int i = this.mDragAcceleratorMode;
                if (i == 1) {
                    updateCharacterBasedSelection(motionEvent);
                } else if (i == 2) {
                    updateWordBasedSelection(motionEvent);
                } else {
                    if (i != 3) {
                        return;
                    }
                    updateParagraphBasedSelection(motionEvent);
                }
            }
        }

        private boolean selectCurrentParagraphAndStartDrag() {
            if (Editor.this.mInsertionActionModeRunnable != null) {
                Editor.this.mTextView.removeCallbacks(Editor.this.mInsertionActionModeRunnable);
            }
            Editor.this.lambda$startActionModeInternal$0$Editor();
            if (!Editor.this.selectCurrentParagraph()) {
                return false;
            }
            enterDrag(3);
            return true;
        }

        private void updateCharacterBasedSelection(MotionEvent motionEvent) {
            updateSelectionInternal(this.mStartOffset, Editor.this.mTextView.getOffsetForPosition(motionEvent.getX(), motionEvent.getY()), motionEvent.isFromSource(4098));
        }

        private void updateWordBasedSelection(MotionEvent motionEvent) {
            int currentLineAdjustedForSlop;
            int wordStart;
            int wordEnd;
            if (this.mHaventMovedEnoughToStartDrag) {
                return;
            }
            boolean zIsFromSource = motionEvent.isFromSource(8194);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(Editor.this.mTextView.getContext());
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (zIsFromSource) {
                currentLineAdjustedForSlop = Editor.this.mTextView.getLineAtCoordinate(y);
            } else {
                if (this.mSwitchedLines) {
                    int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                    SelectionHandleView selectionHandleView = this.mStartHandle;
                    y -= selectionHandleView != null ? selectionHandleView.getIdealVerticalOffset() : scaledTouchSlop;
                }
                Editor editor = Editor.this;
                currentLineAdjustedForSlop = editor.getCurrentLineAdjustedForSlop(editor.mTextView.getLayout(), this.mLineSelectionIsOn, y);
                if (!this.mSwitchedLines && currentLineAdjustedForSlop != this.mLineSelectionIsOn) {
                    this.mSwitchedLines = true;
                    return;
                }
            }
            int offsetAtCoordinate = Editor.this.mTextView.getOffsetAtCoordinate(currentLineAdjustedForSlop, x);
            if (this.mStartOffset < offsetAtCoordinate) {
                wordStart = Editor.this.getWordEnd(offsetAtCoordinate);
                wordEnd = Editor.this.getWordStart(this.mStartOffset);
            } else {
                wordStart = Editor.this.getWordStart(offsetAtCoordinate);
                wordEnd = Editor.this.getWordEnd(this.mStartOffset);
                if (wordEnd == wordStart) {
                    wordStart = Editor.this.getNextCursorOffset(wordStart, false);
                }
            }
            this.mLineSelectionIsOn = currentLineAdjustedForSlop;
            updateSelectionInternal(wordEnd, wordStart, motionEvent.isFromSource(4098));
        }

        private void updateParagraphBasedSelection(MotionEvent motionEvent) {
            int offsetForPosition = Editor.this.mTextView.getOffsetForPosition(motionEvent.getX(), motionEvent.getY());
            long paragraphsRange = Editor.this.getParagraphsRange(Math.min(offsetForPosition, this.mStartOffset), Math.max(offsetForPosition, this.mStartOffset));
            updateSelectionInternal(TextUtils.unpackRangeStartFromLong(paragraphsRange), TextUtils.unpackRangeEndFromLong(paragraphsRange), motionEvent.isFromSource(4098));
        }

        private void updateSelectionInternal(int i, int i2, boolean z) {
            boolean z2 = z && Editor.this.mHapticTextHandleEnabled && !(Editor.this.mTextView.getSelectionStart() == i && Editor.this.mTextView.getSelectionEnd() == i2);
            Selection.setSelection((Spannable) Editor.this.mTextView.getText(), i, i2);
            if (z2) {
                Editor.this.mTextView.performHapticFeedback(9);
            }
        }

        private void updateMinAndMaxOffsets(MotionEvent motionEvent) {
            int pointerCount = motionEvent.getPointerCount();
            for (int i = 0; i < pointerCount; i++) {
                int offsetForPosition = Editor.this.mTextView.getOffsetForPosition(motionEvent.getX(i), motionEvent.getY(i));
                if (offsetForPosition < this.mMinTouchOffset) {
                    this.mMinTouchOffset = offsetForPosition;
                }
                if (offsetForPosition > this.mMaxTouchOffset) {
                    this.mMaxTouchOffset = offsetForPosition;
                }
            }
        }

        public int getMinTouchOffset() {
            return this.mMinTouchOffset;
        }

        public int getMaxTouchOffset() {
            return this.mMaxTouchOffset;
        }

        public void resetTouchOffsets() {
            this.mMaxTouchOffset = -1;
            this.mMinTouchOffset = -1;
            resetDragAcceleratorState();
        }

        private void resetDragAcceleratorState() {
            this.mStartOffset = -1;
            this.mDragAcceleratorMode = 0;
            this.mSwitchedLines = false;
            int selectionStart = Editor.this.mTextView.getSelectionStart();
            int selectionEnd = Editor.this.mTextView.getSelectionEnd();
            if (selectionStart < 0 || selectionEnd < 0) {
                Selection.removeSelection((Spannable) Editor.this.mTextView.getText());
            } else if (selectionStart > selectionEnd) {
                Selection.setSelection((Spannable) Editor.this.mTextView.getText(), selectionEnd, selectionStart);
            }
        }

        public boolean isSelectionStartDragged() {
            SelectionHandleView selectionHandleView = this.mStartHandle;
            return selectionHandleView != null && selectionHandleView.isDragging();
        }

        @Override // android.widget.Editor.CursorController
        public boolean isCursorBeingModified() {
            SelectionHandleView selectionHandleView;
            return isDragAcceleratorActive() || isSelectionStartDragged() || ((selectionHandleView = this.mEndHandle) != null && selectionHandleView.isDragging());
        }

        public boolean isDragAcceleratorActive() {
            return this.mDragAcceleratorMode != 0;
        }

        @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
        public void onTouchModeChanged(boolean z) {
            if (z) {
                return;
            }
            hide();
        }

        @Override // android.widget.Editor.CursorController
        public void onDetached() {
            Editor.this.mTextView.getViewTreeObserver().removeOnTouchModeChangeListener(this);
            SelectionHandleView selectionHandleView = this.mStartHandle;
            if (selectionHandleView != null) {
                selectionHandleView.onDetached();
            }
            SelectionHandleView selectionHandleView2 = this.mEndHandle;
            if (selectionHandleView2 != null) {
                selectionHandleView2.onDetached();
            }
        }

        @Override // android.widget.Editor.CursorController
        public boolean isActive() {
            SelectionHandleView selectionHandleView = this.mStartHandle;
            return selectionHandleView != null && selectionHandleView.isShowing();
        }

        public void invalidateHandles() {
            SelectionHandleView selectionHandleView = this.mStartHandle;
            if (selectionHandleView != null) {
                selectionHandleView.invalidate();
            }
            SelectionHandleView selectionHandleView2 = this.mEndHandle;
            if (selectionHandleView2 != null) {
                selectionHandleView2.invalidate();
            }
        }
    }

    void loadHandleDrawables(boolean z) {
        if (this.mSelectHandleCenter == null || z) {
            this.mSelectHandleCenter = this.mTextView.getTextSelectHandle();
            if (hasInsertionController()) {
                getInsertionController().reloadHandleDrawable();
            }
        }
        if (this.mSelectHandleLeft == null || this.mSelectHandleRight == null || z) {
            this.mSelectHandleLeft = this.mTextView.getTextSelectHandleLeft();
            this.mSelectHandleRight = this.mTextView.getTextSelectHandleRight();
            if (hasSelectionController()) {
                getSelectionController().reloadHandleDrawables();
            }
        }
    }

    private class CorrectionHighlighter {
        private static final int FADE_OUT_DURATION = 400;
        private int mEnd;
        private long mFadingStartTime;
        private final Paint mPaint;
        private final Path mPath = new Path();
        private int mStart;
        private RectF mTempRectF;

        public CorrectionHighlighter() {
            Paint paint = new Paint(1);
            this.mPaint = paint;
            paint.setCompatibilityScaling(Editor.this.mTextView.getResources().getCompatibilityInfo().applicationScale);
            this.mPaint.setStyle(Paint.Style.FILL);
        }

        public void highlight(CorrectionInfo correctionInfo) {
            int offset = correctionInfo.getOffset();
            this.mStart = offset;
            this.mEnd = offset + correctionInfo.getNewText().length();
            this.mFadingStartTime = SystemClock.uptimeMillis();
            if (this.mStart < 0 || this.mEnd < 0) {
                stopAnimation();
            }
        }

        public void draw(Canvas canvas, int i) {
            if (updatePath() && updatePaint()) {
                if (i != 0) {
                    canvas.translate(0.0f, i);
                }
                canvas.drawPath(this.mPath, this.mPaint);
                if (i != 0) {
                    canvas.translate(0.0f, -i);
                }
                invalidate(true);
                return;
            }
            stopAnimation();
            invalidate(false);
        }

        private boolean updatePaint() {
            long jUptimeMillis = SystemClock.uptimeMillis() - this.mFadingStartTime;
            if (jUptimeMillis > 400) {
                return false;
            }
            this.mPaint.setColor((Editor.this.mTextView.mHighlightColor & 16777215) + (((int) (Color.alpha(Editor.this.mTextView.mHighlightColor) * (1.0f - (jUptimeMillis / 400.0f)))) << 24));
            return true;
        }

        private boolean updatePath() {
            Layout layout = Editor.this.mTextView.getLayout();
            if (layout == null) {
                return false;
            }
            int length = Editor.this.mTextView.getText().length();
            int iMin = Math.min(length, this.mStart);
            int iMin2 = Math.min(length, this.mEnd);
            this.mPath.reset();
            layout.getSelectionPath(iMin, iMin2, this.mPath);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidate(boolean z) {
            if (Editor.this.mTextView.getLayout() == null) {
                return;
            }
            if (this.mTempRectF == null) {
                this.mTempRectF = new RectF();
            }
            this.mPath.computeBounds(this.mTempRectF, false);
            int compoundPaddingLeft = Editor.this.mTextView.getCompoundPaddingLeft();
            int extendedPaddingTop = Editor.this.mTextView.getExtendedPaddingTop() + Editor.this.mTextView.getVerticalOffset(true);
            if (z) {
                Editor.this.mTextView.postInvalidateOnAnimation(((int) this.mTempRectF.left) + compoundPaddingLeft, ((int) this.mTempRectF.top) + extendedPaddingTop, compoundPaddingLeft + ((int) this.mTempRectF.right), extendedPaddingTop + ((int) this.mTempRectF.bottom));
            } else {
                Editor.this.mTextView.postInvalidate((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            }
        }

        private void stopAnimation() {
            Editor.this.mCorrectionHighlighter = null;
        }
    }

    private static class ErrorPopup extends PopupWindow {
        private boolean mAbove;
        private int mPopupInlineErrorAboveBackgroundId;
        private int mPopupInlineErrorBackgroundId;
        private final TextView mView;

        ErrorPopup(TextView textView, int i, int i2) {
            super(textView, i, i2);
            this.mAbove = false;
            this.mPopupInlineErrorBackgroundId = 0;
            this.mPopupInlineErrorAboveBackgroundId = 0;
            this.mView = textView;
            int resourceId = getResourceId(0, 297);
            this.mPopupInlineErrorBackgroundId = resourceId;
            this.mView.setBackgroundResource(resourceId);
        }

        void fixDirection(boolean z) {
            this.mAbove = z;
            if (z) {
                this.mPopupInlineErrorAboveBackgroundId = getResourceId(this.mPopupInlineErrorAboveBackgroundId, 296);
            } else {
                this.mPopupInlineErrorBackgroundId = getResourceId(this.mPopupInlineErrorBackgroundId, 297);
            }
            this.mView.setBackgroundResource(z ? this.mPopupInlineErrorAboveBackgroundId : this.mPopupInlineErrorBackgroundId);
        }

        private int getResourceId(int i, int i2) {
            if (i != 0) {
                return i;
            }
            TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(android.R.styleable.Theme);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
            typedArrayObtainStyledAttributes.recycle();
            return resourceId;
        }

        @Override // android.widget.PopupWindow
        public void update(int i, int i2, int i3, int i4, boolean z) {
            super.update(i, i2, i3, i4, z);
            boolean zIsAboveAnchor = isAboveAnchor();
            if (zIsAboveAnchor != this.mAbove) {
                fixDirection(zIsAboveAnchor);
            }
        }
    }

    static class InputContentType {
        boolean enterDown;
        Bundle extras;
        int imeActionId;
        CharSequence imeActionLabel;
        LocaleList imeHintLocales;
        int imeOptions = 0;
        TextView.OnEditorActionListener onEditorActionListener;
        String privateImeOptions;

        InputContentType() {
        }
    }

    static class InputMethodState {
        int mBatchEditNesting;
        int mChangedDelta;
        int mChangedEnd;
        int mChangedStart;
        boolean mContentChanged;
        boolean mCursorChanged;
        final ExtractedText mExtractedText = new ExtractedText();
        ExtractedTextRequest mExtractedTextRequest;
        boolean mSelectionModeChanged;

        InputMethodState() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidRange(CharSequence charSequence, int i, int i2) {
        return i >= 0 && i <= i2 && i2 <= charSequence.length();
    }

    public static class UndoInputFilter implements InputFilter {
        private static final int MERGE_EDIT_MODE_FORCE_MERGE = 0;
        private static final int MERGE_EDIT_MODE_NEVER_MERGE = 1;
        private static final int MERGE_EDIT_MODE_NORMAL = 2;
        private final Editor mEditor;
        private boolean mExpanding;
        private boolean mHasComposition;
        private boolean mIsUserEdit;
        private boolean mPreviousOperationWasInSameBatchEdit;

        @Retention(RetentionPolicy.SOURCE)
        private @interface MergeMode {
        }

        public UndoInputFilter(Editor editor) {
            this.mEditor = editor;
        }

        public void saveInstanceState(Parcel parcel) {
            parcel.writeInt(this.mIsUserEdit ? 1 : 0);
            parcel.writeInt(this.mHasComposition ? 1 : 0);
            parcel.writeInt(this.mExpanding ? 1 : 0);
            parcel.writeInt(this.mPreviousOperationWasInSameBatchEdit ? 1 : 0);
        }

        public void restoreInstanceState(Parcel parcel) {
            this.mIsUserEdit = parcel.readInt() != 0;
            this.mHasComposition = parcel.readInt() != 0;
            this.mExpanding = parcel.readInt() != 0;
            this.mPreviousOperationWasInSameBatchEdit = parcel.readInt() != 0;
        }

        public void beginBatchEdit() {
            this.mIsUserEdit = true;
        }

        public void endBatchEdit() {
            this.mIsUserEdit = false;
            this.mPreviousOperationWasInSameBatchEdit = false;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0028  */
        @Override // android.text.InputFilter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.CharSequence filter(java.lang.CharSequence r11, int r12, int r13, android.text.Spanned r14, int r15, int r16) {
            /*
                r10 = this;
                r8 = r10
                boolean r0 = r10.canUndoEdit(r11, r12, r13, r14, r15, r16)
                r9 = 0
                if (r0 != 0) goto L9
                return r9
            L9:
                boolean r0 = r8.mHasComposition
                boolean r1 = isComposition(r11)
                r8.mHasComposition = r1
                boolean r1 = r8.mExpanding
                int r2 = r13 - r12
                int r3 = r16 - r15
                r4 = 1
                r5 = 0
                if (r2 == r3) goto L28
                if (r2 <= r3) goto L1f
                r2 = r4
                goto L20
            L1f:
                r2 = r5
            L20:
                r8.mExpanding = r2
                if (r0 == 0) goto L28
                if (r2 == r1) goto L28
                r7 = r4
                goto L29
            L28:
                r7 = r5
            L29:
                r0 = r10
                r1 = r11
                r2 = r12
                r3 = r13
                r4 = r14
                r5 = r15
                r6 = r16
                r0.handleEdit(r1, r2, r3, r4, r5, r6, r7)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: android.widget.Editor.UndoInputFilter.filter(java.lang.CharSequence, int, int, android.text.Spanned, int, int):java.lang.CharSequence");
        }

        void freezeLastEdit() {
            this.mEditor.mUndoManager.beginUpdate("Edit text");
            EditOperation lastEdit = getLastEdit();
            if (lastEdit != null) {
                lastEdit.mFrozen = true;
            }
            this.mEditor.mUndoManager.endUpdate();
        }

        private void handleEdit(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4, boolean z) {
            int i5 = (isInTextWatcher() || this.mPreviousOperationWasInSameBatchEdit) ? 0 : z ? 1 : 2;
            EditOperation editOperation = new EditOperation(this.mEditor, TextUtils.substring(spanned, i3, i4), i3, TextUtils.substring(charSequence, i, i2), this.mHasComposition);
            if (this.mHasComposition && TextUtils.equals(editOperation.mNewText, editOperation.mOldText)) {
                return;
            }
            recordEdit(editOperation, i5);
        }

        private EditOperation getLastEdit() {
            return (EditOperation) this.mEditor.mUndoManager.getLastOperation(EditOperation.class, this.mEditor.mUndoOwner, 1);
        }

        private void recordEdit(EditOperation editOperation, int i) {
            UndoManager undoManager = this.mEditor.mUndoManager;
            undoManager.beginUpdate("Edit text");
            EditOperation lastEdit = getLastEdit();
            if (lastEdit == null) {
                undoManager.addOperation(editOperation, 0);
            } else if (i == 0) {
                lastEdit.forceMergeWith(editOperation);
            } else if (!this.mIsUserEdit || i != 2 || !lastEdit.mergeWith(editOperation)) {
                undoManager.commitState(this.mEditor.mUndoOwner);
                undoManager.addOperation(editOperation, 0);
            }
            this.mPreviousOperationWasInSameBatchEdit = this.mIsUserEdit;
            undoManager.endUpdate();
        }

        private boolean canUndoEdit(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (this.mEditor.mAllowUndo && !this.mEditor.mUndoManager.isInUndo() && Editor.isValidRange(charSequence, i, i2) && Editor.isValidRange(spanned, i3, i4)) {
                return (i == i2 && i3 == i4) ? false : true;
            }
            return false;
        }

        private static boolean isComposition(CharSequence charSequence) {
            if (!(charSequence instanceof Spannable)) {
                return false;
            }
            Spannable spannable = (Spannable) charSequence;
            return EditableInputConnection.getComposingSpanStart(spannable) < EditableInputConnection.getComposingSpanEnd(spannable);
        }

        private boolean isInTextWatcher() {
            CharSequence text = this.mEditor.mTextView.getText();
            return (text instanceof SpannableStringBuilder) && ((SpannableStringBuilder) text).getTextWatcherDepth() > 0;
        }
    }

    public static class EditOperation extends UndoOperation<Editor> {
        public static final Parcelable.ClassLoaderCreator<EditOperation> CREATOR = new Parcelable.ClassLoaderCreator<EditOperation>() { // from class: android.widget.Editor.EditOperation.1
            @Override // android.os.Parcelable.Creator
            public EditOperation createFromParcel(Parcel parcel) {
                return new EditOperation(parcel, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public EditOperation createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new EditOperation(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public EditOperation[] newArray(int i) {
                return new EditOperation[i];
            }
        };
        private static final int TYPE_DELETE = 1;
        private static final int TYPE_INSERT = 0;
        private static final int TYPE_REPLACE = 2;
        private boolean mFrozen;
        private boolean mIsComposition;
        private int mNewCursorPos;
        private String mNewText;
        private int mOldCursorPos;
        private String mOldText;
        private int mStart;
        private int mType;

        @Override // android.content.UndoOperation
        public void commit() {
        }

        public EditOperation(Editor editor, String str, int i, String str2, boolean z) {
            super(editor.mUndoOwner);
            this.mOldText = str;
            this.mNewText = str2;
            if (str2.length() > 0 && this.mOldText.length() == 0) {
                this.mType = 0;
            } else if (this.mNewText.length() == 0 && this.mOldText.length() > 0) {
                this.mType = 1;
            } else {
                this.mType = 2;
            }
            this.mStart = i;
            this.mOldCursorPos = editor.mTextView.getSelectionStart();
            this.mNewCursorPos = i + this.mNewText.length();
            this.mIsComposition = z;
        }

        public EditOperation(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mType = parcel.readInt();
            this.mOldText = parcel.readString();
            this.mNewText = parcel.readString();
            this.mStart = parcel.readInt();
            this.mOldCursorPos = parcel.readInt();
            this.mNewCursorPos = parcel.readInt();
            this.mFrozen = parcel.readInt() == 1;
            this.mIsComposition = parcel.readInt() == 1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mType);
            parcel.writeString(this.mOldText);
            parcel.writeString(this.mNewText);
            parcel.writeInt(this.mStart);
            parcel.writeInt(this.mOldCursorPos);
            parcel.writeInt(this.mNewCursorPos);
            parcel.writeInt(this.mFrozen ? 1 : 0);
            parcel.writeInt(this.mIsComposition ? 1 : 0);
        }

        private int getNewTextEnd() {
            return this.mStart + this.mNewText.length();
        }

        private int getOldTextEnd() {
            return this.mStart + this.mOldText.length();
        }

        @Override // android.content.UndoOperation
        public void undo() {
            modifyText((Editable) getOwnerData().mTextView.getText(), this.mStart, getNewTextEnd(), this.mOldText, this.mStart, this.mOldCursorPos);
        }

        @Override // android.content.UndoOperation
        public void redo() {
            modifyText((Editable) getOwnerData().mTextView.getText(), this.mStart, getOldTextEnd(), this.mNewText, this.mStart, this.mNewCursorPos);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean mergeWith(EditOperation editOperation) {
            if (this.mFrozen) {
                return false;
            }
            int i = this.mType;
            if (i == 0) {
                return mergeInsertWith(editOperation);
            }
            if (i == 1) {
                return mergeDeleteWith(editOperation);
            }
            if (i != 2) {
                return false;
            }
            return mergeReplaceWith(editOperation);
        }

        private boolean mergeInsertWith(EditOperation editOperation) {
            int i = editOperation.mType;
            if (i == 0) {
                if (getNewTextEnd() != editOperation.mStart) {
                    return false;
                }
                this.mNewText += editOperation.mNewText;
                this.mNewCursorPos = editOperation.mNewCursorPos;
                this.mFrozen = editOperation.mFrozen;
                this.mIsComposition = editOperation.mIsComposition;
                return true;
            }
            if (!this.mIsComposition || i != 2 || this.mStart > editOperation.mStart || getNewTextEnd() < editOperation.getOldTextEnd()) {
                return false;
            }
            this.mNewText = this.mNewText.substring(0, editOperation.mStart - this.mStart) + editOperation.mNewText + this.mNewText.substring(editOperation.getOldTextEnd() - this.mStart, this.mNewText.length());
            this.mNewCursorPos = editOperation.mNewCursorPos;
            this.mIsComposition = editOperation.mIsComposition;
            return true;
        }

        private boolean mergeDeleteWith(EditOperation editOperation) {
            if (editOperation.mType != 1 || this.mStart != editOperation.getOldTextEnd()) {
                return false;
            }
            this.mStart = editOperation.mStart;
            this.mOldText = editOperation.mOldText + this.mOldText;
            this.mNewCursorPos = editOperation.mNewCursorPos;
            this.mIsComposition = editOperation.mIsComposition;
            return true;
        }

        private boolean mergeReplaceWith(EditOperation editOperation) {
            if (editOperation.mType == 0 && getNewTextEnd() == editOperation.mStart) {
                this.mNewText += editOperation.mNewText;
                this.mNewCursorPos = editOperation.mNewCursorPos;
                return true;
            }
            if (!this.mIsComposition) {
                return false;
            }
            if (editOperation.mType == 1 && this.mStart <= editOperation.mStart && getNewTextEnd() >= editOperation.getOldTextEnd()) {
                String str = this.mNewText.substring(0, editOperation.mStart - this.mStart) + this.mNewText.substring(editOperation.getOldTextEnd() - this.mStart, this.mNewText.length());
                this.mNewText = str;
                if (str.isEmpty()) {
                    this.mType = 1;
                }
                this.mNewCursorPos = editOperation.mNewCursorPos;
                this.mIsComposition = editOperation.mIsComposition;
                return true;
            }
            if (editOperation.mType != 2 || this.mStart != editOperation.mStart || !TextUtils.equals(this.mNewText, editOperation.mOldText)) {
                return false;
            }
            this.mNewText = editOperation.mNewText;
            this.mNewCursorPos = editOperation.mNewCursorPos;
            this.mIsComposition = editOperation.mIsComposition;
            return true;
        }

        public void forceMergeWith(EditOperation editOperation) {
            if (mergeWith(editOperation)) {
                return;
            }
            Editable editable = (Editable) getOwnerData().mTextView.getText();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(editable.toString());
            modifyText(spannableStringBuilder, this.mStart, getNewTextEnd(), this.mOldText, this.mStart, this.mOldCursorPos);
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(editable.toString());
            modifyText(spannableStringBuilder2, editOperation.mStart, editOperation.getOldTextEnd(), editOperation.mNewText, editOperation.mStart, editOperation.mNewCursorPos);
            this.mType = 2;
            this.mNewText = spannableStringBuilder2.toString();
            this.mOldText = spannableStringBuilder.toString();
            this.mStart = 0;
            this.mNewCursorPos = editOperation.mNewCursorPos;
            this.mIsComposition = editOperation.mIsComposition;
        }

        private static void modifyText(Editable editable, int i, int i2, CharSequence charSequence, int i3, int i4) {
            if (Editor.isValidRange(editable, i, i2) && i3 <= editable.length() - (i2 - i)) {
                if (i != i2) {
                    editable.delete(i, i2);
                }
                if (charSequence.length() != 0) {
                    editable.insert(i3, charSequence);
                }
            }
            if (i4 < 0 || i4 > editable.length()) {
                return;
            }
            Selection.setSelection(editable, i4);
        }

        private String getTypeString() {
            int i = this.mType;
            return i != 0 ? i != 1 ? i != 2 ? "" : "replace" : "delete" : "insert";
        }

        public String toString() {
            return "[mType=" + getTypeString() + ", mOldText=" + this.mOldText + ", mNewText=" + this.mNewText + ", mStart=" + this.mStart + ", mOldCursorPos=" + this.mOldCursorPos + ", mNewCursorPos=" + this.mNewCursorPos + ", mFrozen=" + this.mFrozen + ", mIsComposition=" + this.mIsComposition + "]";
        }
    }

    static final class ProcessTextIntentActionsHandler {
        private final SparseArray<AccessibilityNodeInfo.AccessibilityAction> mAccessibilityActions;
        private final SparseArray<Intent> mAccessibilityIntents;
        private final Context mContext;
        private final Editor mEditor;
        private final PackageManager mPackageManager;
        private final String mPackageName;
        private final List<ResolveInfo> mSupportedActivities;
        private final TextView mTextView;

        private ProcessTextIntentActionsHandler(Editor editor) {
            this.mAccessibilityIntents = new SparseArray<>();
            this.mAccessibilityActions = new SparseArray<>();
            this.mSupportedActivities = new ArrayList();
            Editor editor2 = (Editor) Objects.requireNonNull(editor);
            this.mEditor = editor2;
            TextView textView = (TextView) Objects.requireNonNull(editor2.mTextView);
            this.mTextView = textView;
            Context context = (Context) Objects.requireNonNull(textView.getContext());
            this.mContext = context;
            this.mPackageManager = (PackageManager) Objects.requireNonNull(context.getPackageManager());
            this.mPackageName = (String) Objects.requireNonNull(this.mContext.getPackageName());
        }

        public void onInitializeMenu(Menu menu) {
            loadSupportedActivities();
            int size = this.mSupportedActivities.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = this.mSupportedActivities.get(i);
                menu.add(0, 0, i + 100, getLabel(resolveInfo)).setIntent(createProcessTextIntentForResolveInfo(resolveInfo)).setShowAsAction(0);
            }
        }

        public boolean performMenuItemAction(MenuItem menuItem) {
            return fireIntent(menuItem.getIntent());
        }

        public void initializeAccessibilityActions() {
            this.mAccessibilityIntents.clear();
            this.mAccessibilityActions.clear();
            loadSupportedActivities();
            int i = 0;
            for (ResolveInfo resolveInfo : this.mSupportedActivities) {
                int i2 = i + 1;
                int i3 = i + 268435712;
                this.mAccessibilityActions.put(i3, new AccessibilityNodeInfo.AccessibilityAction(i3, getLabel(resolveInfo)));
                this.mAccessibilityIntents.put(i3, createProcessTextIntentForResolveInfo(resolveInfo));
                i = i2;
            }
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            for (int i = 0; i < this.mAccessibilityActions.size(); i++) {
                accessibilityNodeInfo.addAction(this.mAccessibilityActions.valueAt(i));
            }
        }

        public boolean performAccessibilityAction(int i) {
            return fireIntent(this.mAccessibilityIntents.get(i));
        }

        private boolean fireIntent(Intent intent) {
            if (intent == null || !Intent.ACTION_PROCESS_TEXT.equals(intent.getAction())) {
                return false;
            }
            intent.putExtra(Intent.EXTRA_PROCESS_TEXT, (String) TextUtils.trimToParcelableSize(this.mTextView.getSelectedText()));
            this.mEditor.mPreserveSelection = true;
            this.mTextView.startActivityForResult(intent, 100);
            return true;
        }

        private void loadSupportedActivities() {
            this.mSupportedActivities.clear();
            if (this.mContext.canStartActivityForResult()) {
                for (ResolveInfo resolveInfo : this.mTextView.getContext().getPackageManager().queryIntentActivities(createProcessTextIntent(), 0)) {
                    if (isSupportedActivity(resolveInfo)) {
                        this.mSupportedActivities.add(resolveInfo);
                    }
                }
            }
        }

        private boolean isSupportedActivity(ResolveInfo resolveInfo) {
            return this.mPackageName.equals(resolveInfo.activityInfo.packageName) || (resolveInfo.activityInfo.exported && (resolveInfo.activityInfo.permission == null || this.mContext.checkSelfPermission(resolveInfo.activityInfo.permission) == 0));
        }

        private Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo) {
            return createProcessTextIntent().putExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, !this.mTextView.isTextEditable()).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }

        private Intent createProcessTextIntent() {
            return new Intent().setAction(Intent.ACTION_PROCESS_TEXT).setType("text/plain");
        }

        private CharSequence getLabel(ResolveInfo resolveInfo) {
            return resolveInfo.loadLabel(this.mPackageManager);
        }
    }

    static void logCursor(String str, String str2, Object... objArr) {
        if (str2 == null) {
            Log.d("Editor", str);
            return;
        }
        Log.d("Editor", str + ": " + String.format(str2, objArr));
    }
}
