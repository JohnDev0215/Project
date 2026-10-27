package android.widget;

import android.Manifest;
import android.R;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.UndoManager;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.BaseCanvas;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ParcelableParcel;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.BoringLayout;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.GetChars;
import android.text.GraphicsOperations;
import android.text.InputFilter;
import android.text.Layout;
import android.text.ParcelableSpan;
import android.text.PrecomputedText;
import android.text.Selection;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.AllCapsTransformationMethod;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.DateKeyListener;
import android.text.method.DateTimeKeyListener;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.LinkMovementMethod;
import android.text.method.MetaKeyKeyListener;
import android.text.method.MovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TextKeyListener;
import android.text.method.TimeKeyListener;
import android.text.method.TransformationMethod;
import android.text.method.TransformationMethod2;
import android.text.method.WordIterator;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ParagraphStyle;
import android.text.style.SpellCheckSpan;
import android.text.style.SuggestionSpan;
import android.text.style.URLSpan;
import android.text.style.UpdateAppearance;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.IntArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.AccessibilityIterators;
import android.view.ActionMode;
import android.view.Choreographer;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.RemotableViewMethod;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewHierarchyEncoder;
import android.view.ViewParent;
import android.view.ViewRootImpl;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.autofill.Helper;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.ContentCaptureSession;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.IntFlagMapping;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextLinks;
import android.view.textservice.SpellCheckerSubtype;
import android.view.textservice.TextServicesManager;
import android.widget.AccessibilityIterators;
import android.widget.Editor;
import android.widget.RemoteViews;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.FastMath;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.EditableInputConnection;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class TextView extends View implements ViewTreeObserver.OnPreDrawListener {
    static final int ACCESSIBILITY_ACTION_PROCESS_TEXT_START_ID = 268435712;
    private static final int ACCESSIBILITY_ACTION_SHARE = 268435456;
    private static final int ANIMATED_SCROLL_GAP = 250;
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    private static final int CHANGE_WATCHER_PRIORITY = 100;
    static final boolean DEBUG_CURSOR = false;
    static final boolean DEBUG_EXTRACT = false;
    private static final int DECIMAL = 4;
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final int DEFAULT_TYPEFACE = -1;
    private static final int DEVICE_PROVISIONED_NO = 1;
    private static final int DEVICE_PROVISIONED_UNKNOWN = 0;
    private static final int DEVICE_PROVISIONED_YES = 2;
    private static final int ELLIPSIZE_END = 3;
    private static final int ELLIPSIZE_MARQUEE = 4;
    private static final int ELLIPSIZE_MIDDLE = 2;
    private static final int ELLIPSIZE_NONE = 0;
    private static final int ELLIPSIZE_NOT_SET = -1;
    private static final int ELLIPSIZE_START = 1;
    private static final int EMS = 1;
    private static final int FLOATING_TOOLBAR_SELECT_ALL_REFRESH_DELAY = 500;
    static final int ID_ASSIST = 16908353;
    static final int ID_AUTOFILL = 16908355;
    static final int ID_COPY = 16908321;
    static final int ID_CUT = 16908320;
    static final int ID_PASTE = 16908322;
    static final int ID_PASTE_AS_PLAIN_TEXT = 16908337;
    static final int ID_REDO = 16908339;
    static final int ID_REPLACE = 16908340;
    static final int ID_SELECT_ALL = 16908319;
    static final int ID_SHARE = 16908341;
    static final int ID_UNDO = 16908338;
    private static final int KEY_DOWN_HANDLED_BY_KEY_LISTENER = 1;
    private static final int KEY_DOWN_HANDLED_BY_MOVEMENT_METHOD = 2;
    private static final int KEY_EVENT_HANDLED = -1;
    private static final int KEY_EVENT_NOT_HANDLED = 0;
    private static final int LINES = 1;
    static final String LOG_TAG = "TextView";
    private static final int MARQUEE_FADE_NORMAL = 0;
    private static final int MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS = 1;
    private static final int MARQUEE_FADE_SWITCH_SHOW_FADE = 2;
    private static final int MAX_LENGTH_FOR_SINGLE_LINE_EDIT_TEXT = 5000;
    private static final int MONOSPACE = 3;
    private static final int NO_POINTER_ID = -1;
    private static final int PIXELS = 2;
    static final int PROCESS_TEXT_REQUEST_CODE = 100;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int SIGNED = 2;
    public static final BoringLayout.Metrics UNKNOWN_BORING;
    private static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0f;
    static final int VERY_WIDE = 1048576;
    private static final SparseIntArray sAppearanceValues;
    static long sLastCutCopyOrTextChangedTime;
    private boolean mAllowTransformationLengthChange;
    private int mAutoLinkMask;
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int[] mAutoSizeTextSizesInPx;
    private int mAutoSizeTextType;
    private BoringLayout.Metrics mBoring;
    private int mBreakStrategy;
    private BufferType mBufferType;
    private ChangeWatcher mChangeWatcher;
    private CharWrapper mCharWrapper;
    private int mCurHintTextColor;

    @ViewDebug.ExportedProperty(category = "text")
    private int mCurTextColor;
    private volatile Locale mCurrentSpellCheckerLocaleCache;
    private Drawable mCursorDrawable;
    int mCursorDrawableRes;
    private int mDeferScroll;
    private int mDesiredHeightAtMeasure;
    private int mDeviceProvisionedState;
    Drawables mDrawables;
    private Editable.Factory mEditableFactory;
    private Editor mEditor;
    private TextUtils.TruncateAt mEllipsize;
    private InputFilter[] mFilters;
    private boolean mFreezesText;

    @ViewDebug.ExportedProperty(category = "text")
    private int mGravity;
    private boolean mHasPresetAutoSizeValues;
    int mHighlightColor;
    private final Paint mHighlightPaint;
    private Path mHighlightPath;
    private boolean mHighlightPathBogus;
    private CharSequence mHint;
    private BoringLayout.Metrics mHintBoring;
    private int mHintId;
    private Layout mHintLayout;
    private ColorStateList mHintTextColor;
    private boolean mHorizontallyScrolling;
    private int mHyphenationFrequency;
    private boolean mIncludePad;
    private boolean mIsPrimePointerFromHandleView;
    private int mJustificationMode;
    private int mLastLayoutDirection;
    private long mLastScroll;
    private Layout mLayout;
    private ColorStateList mLinkTextColor;
    private boolean mLinksClickable;
    private boolean mListenerChanged;
    private ArrayList<TextWatcher> mListeners;
    private boolean mLocalesChanged;
    private Marquee mMarquee;
    private int mMarqueeFadeMode;
    private int mMarqueeRepeatLimit;
    private int mMaxMode;
    private int mMaxWidth;
    private int mMaxWidthMode;
    private int mMaximum;
    private int mMinMode;
    private int mMinWidth;
    private int mMinWidthMode;
    private int mMinimum;
    private MovementMethod mMovement;
    private boolean mNeedsAutoSizeText;
    private int mOldMaxMode;
    private int mOldMaximum;
    private boolean mPreDrawListenerDetached;
    private boolean mPreDrawRegistered;
    private PrecomputedText mPrecomputed;
    private boolean mPreventDefaultMovement;
    private int mPrimePointerId;
    private boolean mRestartMarquee;
    private BoringLayout mSavedHintLayout;
    private BoringLayout mSavedLayout;
    private Layout mSavedMarqueeModeLayout;
    private Scroller mScroller;
    private int mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private boolean mSingleLine;
    private InputFilter.LengthFilter mSingleLineLengthFilter;
    private float mSpacingAdd;
    private float mSpacingMult;
    private Spannable mSpannable;
    private Spannable.Factory mSpannableFactory;
    private Rect mTempRect;
    private TextPaint mTempTextPaint;

    @ViewDebug.ExportedProperty(category = "text")
    private CharSequence mText;
    private TextClassificationContext mTextClassificationContext;
    private TextClassifier mTextClassificationSession;
    private TextClassifier mTextClassifier;
    private ColorStateList mTextColor;
    private TextDirectionHeuristic mTextDir;
    int mTextEditSuggestionContainerLayout;
    int mTextEditSuggestionHighlightStyle;
    int mTextEditSuggestionItemLayout;
    private int mTextId;
    private UserHandle mTextOperationUser;
    private final TextPaint mTextPaint;
    private Drawable mTextSelectHandle;
    private Drawable mTextSelectHandleLeft;
    int mTextSelectHandleLeftRes;
    int mTextSelectHandleRes;
    private Drawable mTextSelectHandleRight;
    int mTextSelectHandleRightRes;
    private boolean mTextSetFromXmlOrResourceId;
    private int mTextSizeUnit;
    private TransformationMethod mTransformation;
    private CharSequence mTransformed;
    boolean mUseFallbackLineSpacing;
    private final boolean mUseInternationalizedInput;
    private boolean mUserSetTextScaleX;
    private static final float[] TEMP_POSITION = new float[2];
    private static final RectF TEMP_RECTF = new RectF();
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final Spanned EMPTY_SPANNED = new SpannedString("");
    private static final int[] MULTILINE_STATE_SET = {16843597};

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoSizeTextType {
    }

    public enum BufferType {
        NORMAL,
        SPANNABLE,
        EDITABLE
    }

    public interface OnEditorActionListener {
        boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface XMLTypefaceAttr {
    }

    private boolean isDirectionalNavigationKey(int i) {
        switch (i) {
            case 19:
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    private static boolean isMultilineInputType(int i) {
        return (i & 131087) == 131073;
    }

    static boolean isPasswordInputType(int i) {
        int i2 = i & 4095;
        return i2 == 129 || i2 == 225 || i2 == 18;
    }

    private static boolean isVisiblePasswordInputType(int i) {
        return (i & 4095) == 145;
    }

    protected boolean getDefaultEditable() {
        return false;
    }

    protected MovementMethod getDefaultMovementMethod() {
        return null;
    }

    public int getHorizontalOffsetForDrawables() {
        return 0;
    }

    @Override // android.view.View
    public boolean isAccessibilitySelectionExtendable() {
        return true;
    }

    public boolean isInExtractedMode() {
        return false;
    }

    public void onBeginBatchEdit() {
    }

    public void onCommitCompletion(CompletionInfo completionInfo) {
    }

    public void onEndBatchEdit() {
    }

    public boolean onPrivateIMECommand(String str, Bundle bundle) {
        return false;
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected boolean supportsAutoSizeText() {
        return true;
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<TextView> {
        private int mAutoLinkId;
        private int mAutoSizeMaxTextSizeId;
        private int mAutoSizeMinTextSizeId;
        private int mAutoSizeStepGranularityId;
        private int mAutoSizeTextTypeId;
        private int mBreakStrategyId;
        private int mCursorVisibleId;
        private int mDrawableBlendModeId;
        private int mDrawablePaddingId;
        private int mDrawableTintId;
        private int mDrawableTintModeId;
        private int mElegantTextHeightId;
        private int mEllipsizeId;
        private int mFallbackLineSpacingId;
        private int mFirstBaselineToTopHeightId;
        private int mFontFeatureSettingsId;
        private int mFreezesTextId;
        private int mGravityId;
        private int mHintId;
        private int mHyphenationFrequencyId;
        private int mImeActionIdId;
        private int mImeActionLabelId;
        private int mImeOptionsId;
        private int mIncludeFontPaddingId;
        private int mInputTypeId;
        private int mJustificationModeId;
        private int mLastBaselineToBottomHeightId;
        private int mLetterSpacingId;
        private int mLineHeightId;
        private int mLineSpacingExtraId;
        private int mLineSpacingMultiplierId;
        private int mLinksClickableId;
        private int mMarqueeRepeatLimitId;
        private int mMaxEmsId;
        private int mMaxHeightId;
        private int mMaxLinesId;
        private int mMaxWidthId;
        private int mMinEmsId;
        private int mMinLinesId;
        private int mMinWidthId;
        private int mPrivateImeOptionsId;
        private boolean mPropertiesMapped = false;
        private int mScrollHorizontallyId;
        private int mShadowColorId;
        private int mShadowDxId;
        private int mShadowDyId;
        private int mShadowRadiusId;
        private int mSingleLineId;
        private int mTextAllCapsId;
        private int mTextColorHighlightId;
        private int mTextColorHintId;
        private int mTextColorId;
        private int mTextColorLinkId;
        private int mTextId;
        private int mTextIsSelectableId;
        private int mTextScaleXId;
        private int mTextSizeId;
        private int mTypefaceId;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            final IntFlagMapping intFlagMapping = new IntFlagMapping();
            intFlagMapping.add(2, 2, "email");
            intFlagMapping.add(8, 8, "map");
            intFlagMapping.add(4, 4, "phone");
            intFlagMapping.add(1, 1, "web");
            Objects.requireNonNull(intFlagMapping);
            this.mAutoLinkId = propertyMapper.mapIntFlag("autoLink", 16842928, new IntFunction() { // from class: android.widget.-$$Lambda$TBEFIMWmYQi3asJAI2bd-emdKpc
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return intFlagMapping.get(i);
                }
            });
            this.mAutoSizeMaxTextSizeId = propertyMapper.mapInt("autoSizeMaxTextSize", 16844102);
            this.mAutoSizeMinTextSizeId = propertyMapper.mapInt("autoSizeMinTextSize", 16844088);
            this.mAutoSizeStepGranularityId = propertyMapper.mapInt("autoSizeStepGranularity", 16844086);
            SparseArray sparseArray = new SparseArray();
            sparseArray.put(0, "none");
            sparseArray.put(1, "uniform");
            Objects.requireNonNull(sparseArray);
            this.mAutoSizeTextTypeId = propertyMapper.mapIntEnum("autoSizeTextType", 16844085, new $$Lambda$Mj9GuqQ_pbMJNINps4vUOSIj7Q(sparseArray));
            SparseArray sparseArray2 = new SparseArray();
            sparseArray2.put(0, "simple");
            sparseArray2.put(1, "high_quality");
            sparseArray2.put(2, "balanced");
            Objects.requireNonNull(sparseArray2);
            this.mBreakStrategyId = propertyMapper.mapIntEnum("breakStrategy", 16843997, new $$Lambda$Mj9GuqQ_pbMJNINps4vUOSIj7Q(sparseArray2));
            this.mCursorVisibleId = propertyMapper.mapBoolean("cursorVisible", 16843090);
            this.mDrawableBlendModeId = propertyMapper.mapObject("drawableBlendMode", 80);
            this.mDrawablePaddingId = propertyMapper.mapInt("drawablePadding", 16843121);
            this.mDrawableTintId = propertyMapper.mapObject("drawableTint", 16843990);
            this.mDrawableTintModeId = propertyMapper.mapObject("drawableTintMode", 16843991);
            this.mElegantTextHeightId = propertyMapper.mapBoolean("elegantTextHeight", 16843869);
            this.mEllipsizeId = propertyMapper.mapObject("ellipsize", 16842923);
            this.mFallbackLineSpacingId = propertyMapper.mapBoolean("fallbackLineSpacing", 16844155);
            this.mFirstBaselineToTopHeightId = propertyMapper.mapInt("firstBaselineToTopHeight", 16844157);
            this.mFontFeatureSettingsId = propertyMapper.mapObject("fontFeatureSettings", 16843959);
            this.mFreezesTextId = propertyMapper.mapBoolean("freezesText", 16843116);
            this.mGravityId = propertyMapper.mapGravity("gravity", 16842927);
            this.mHintId = propertyMapper.mapObject("hint", 16843088);
            SparseArray sparseArray3 = new SparseArray();
            sparseArray3.put(0, "none");
            sparseArray3.put(1, "normal");
            sparseArray3.put(2, "full");
            Objects.requireNonNull(sparseArray3);
            this.mHyphenationFrequencyId = propertyMapper.mapIntEnum("hyphenationFrequency", 16843998, new $$Lambda$Mj9GuqQ_pbMJNINps4vUOSIj7Q(sparseArray3));
            this.mImeActionIdId = propertyMapper.mapInt("imeActionId", 16843366);
            this.mImeActionLabelId = propertyMapper.mapObject("imeActionLabel", 16843365);
            final IntFlagMapping intFlagMapping2 = new IntFlagMapping();
            intFlagMapping2.add(255, 6, "actionDone");
            intFlagMapping2.add(255, 2, "actionGo");
            intFlagMapping2.add(255, 5, "actionNext");
            intFlagMapping2.add(255, 1, "actionNone");
            intFlagMapping2.add(255, 7, "actionPrevious");
            intFlagMapping2.add(255, 3, "actionSearch");
            intFlagMapping2.add(255, 4, "actionSend");
            intFlagMapping2.add(255, 0, "actionUnspecified");
            intFlagMapping2.add(Integer.MIN_VALUE, Integer.MIN_VALUE, "flagForceAscii");
            intFlagMapping2.add(134217728, 134217728, "flagNavigateNext");
            intFlagMapping2.add(67108864, 67108864, "flagNavigatePrevious");
            intFlagMapping2.add(536870912, 536870912, "flagNoAccessoryAction");
            intFlagMapping2.add(1073741824, 1073741824, "flagNoEnterAction");
            intFlagMapping2.add(268435456, 268435456, "flagNoExtractUi");
            intFlagMapping2.add(33554432, 33554432, "flagNoFullscreen");
            intFlagMapping2.add(16777216, 16777216, "flagNoPersonalizedLearning");
            intFlagMapping2.add(-1, 0, "normal");
            Objects.requireNonNull(intFlagMapping2);
            this.mImeOptionsId = propertyMapper.mapIntFlag("imeOptions", 16843364, new IntFunction() { // from class: android.widget.-$$Lambda$TBEFIMWmYQi3asJAI2bd-emdKpc
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return intFlagMapping2.get(i);
                }
            });
            this.mIncludeFontPaddingId = propertyMapper.mapBoolean("includeFontPadding", 16843103);
            final IntFlagMapping intFlagMapping3 = new IntFlagMapping();
            intFlagMapping3.add(4095, 20, "date");
            intFlagMapping3.add(4095, 4, TextClassifier.TYPE_DATE_TIME);
            intFlagMapping3.add(-1, 0, "none");
            intFlagMapping3.add(4095, 2, "number");
            intFlagMapping3.add(16773135, 8194, "numberDecimal");
            intFlagMapping3.add(4095, 18, "numberPassword");
            intFlagMapping3.add(16773135, 4098, "numberSigned");
            intFlagMapping3.add(4095, 3, "phone");
            intFlagMapping3.add(4095, 1, "text");
            intFlagMapping3.add(16773135, 65537, "textAutoComplete");
            intFlagMapping3.add(16773135, 32769, "textAutoCorrect");
            intFlagMapping3.add(16773135, 4097, "textCapCharacters");
            intFlagMapping3.add(16773135, 16385, "textCapSentences");
            intFlagMapping3.add(16773135, 8193, "textCapWords");
            intFlagMapping3.add(4095, 33, "textEmailAddress");
            intFlagMapping3.add(4095, 49, "textEmailSubject");
            intFlagMapping3.add(4095, 177, "textFilter");
            intFlagMapping3.add(16773135, 262145, "textImeMultiLine");
            intFlagMapping3.add(4095, 81, "textLongMessage");
            intFlagMapping3.add(16773135, 131073, "textMultiLine");
            intFlagMapping3.add(16773135, ConnectivityManager.CALLBACK_PRECHECK, "textNoSuggestions");
            intFlagMapping3.add(4095, 129, "textPassword");
            intFlagMapping3.add(4095, 97, "textPersonName");
            intFlagMapping3.add(4095, 193, "textPhonetic");
            intFlagMapping3.add(4095, 113, "textPostalAddress");
            intFlagMapping3.add(4095, 65, "textShortMessage");
            intFlagMapping3.add(4095, 17, "textUri");
            intFlagMapping3.add(4095, 145, "textVisiblePassword");
            intFlagMapping3.add(4095, 161, "textWebEditText");
            intFlagMapping3.add(4095, 209, "textWebEmailAddress");
            intFlagMapping3.add(4095, 225, "textWebPassword");
            intFlagMapping3.add(4095, 36, DropBoxManager.EXTRA_TIME);
            Objects.requireNonNull(intFlagMapping3);
            this.mInputTypeId = propertyMapper.mapIntFlag("inputType", 16843296, new IntFunction() { // from class: android.widget.-$$Lambda$TBEFIMWmYQi3asJAI2bd-emdKpc
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return intFlagMapping3.get(i);
                }
            });
            SparseArray sparseArray4 = new SparseArray();
            sparseArray4.put(0, "none");
            sparseArray4.put(1, "inter_word");
            Objects.requireNonNull(sparseArray4);
            this.mJustificationModeId = propertyMapper.mapIntEnum("justificationMode", 16844135, new $$Lambda$Mj9GuqQ_pbMJNINps4vUOSIj7Q(sparseArray4));
            this.mLastBaselineToBottomHeightId = propertyMapper.mapInt("lastBaselineToBottomHeight", 16844158);
            this.mLetterSpacingId = propertyMapper.mapFloat("letterSpacing", 16843958);
            this.mLineHeightId = propertyMapper.mapInt("lineHeight", 16844159);
            this.mLineSpacingExtraId = propertyMapper.mapFloat("lineSpacingExtra", 16843287);
            this.mLineSpacingMultiplierId = propertyMapper.mapFloat("lineSpacingMultiplier", 16843288);
            this.mLinksClickableId = propertyMapper.mapBoolean("linksClickable", 16842929);
            this.mMarqueeRepeatLimitId = propertyMapper.mapInt("marqueeRepeatLimit", 16843293);
            this.mMaxEmsId = propertyMapper.mapInt("maxEms", 16843095);
            this.mMaxHeightId = propertyMapper.mapInt("maxHeight", 16843040);
            this.mMaxLinesId = propertyMapper.mapInt("maxLines", 16843091);
            this.mMaxWidthId = propertyMapper.mapInt("maxWidth", 16843039);
            this.mMinEmsId = propertyMapper.mapInt("minEms", 16843098);
            this.mMinLinesId = propertyMapper.mapInt("minLines", 16843094);
            this.mMinWidthId = propertyMapper.mapInt("minWidth", 16843071);
            this.mPrivateImeOptionsId = propertyMapper.mapObject("privateImeOptions", 16843299);
            this.mScrollHorizontallyId = propertyMapper.mapBoolean("scrollHorizontally", 16843099);
            this.mShadowColorId = propertyMapper.mapColor("shadowColor", 16843105);
            this.mShadowDxId = propertyMapper.mapFloat("shadowDx", 16843106);
            this.mShadowDyId = propertyMapper.mapFloat("shadowDy", 16843107);
            this.mShadowRadiusId = propertyMapper.mapFloat("shadowRadius", 16843108);
            this.mSingleLineId = propertyMapper.mapBoolean("singleLine", 16843101);
            this.mTextId = propertyMapper.mapObject("text", 16843087);
            this.mTextAllCapsId = propertyMapper.mapBoolean("textAllCaps", 16843660);
            this.mTextColorId = propertyMapper.mapObject("textColor", 16842904);
            this.mTextColorHighlightId = propertyMapper.mapColor("textColorHighlight", 16842905);
            this.mTextColorHintId = propertyMapper.mapObject("textColorHint", 16842906);
            this.mTextColorLinkId = propertyMapper.mapObject("textColorLink", 16842907);
            this.mTextIsSelectableId = propertyMapper.mapBoolean("textIsSelectable", 16843542);
            this.mTextScaleXId = propertyMapper.mapFloat("textScaleX", 16843089);
            this.mTextSizeId = propertyMapper.mapFloat("textSize", 16842901);
            this.mTypefaceId = propertyMapper.mapObject("typeface", 16842902);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(TextView textView, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readIntFlag(this.mAutoLinkId, textView.getAutoLinkMask());
            propertyReader.readInt(this.mAutoSizeMaxTextSizeId, textView.getAutoSizeMaxTextSize());
            propertyReader.readInt(this.mAutoSizeMinTextSizeId, textView.getAutoSizeMinTextSize());
            propertyReader.readInt(this.mAutoSizeStepGranularityId, textView.getAutoSizeStepGranularity());
            propertyReader.readIntEnum(this.mAutoSizeTextTypeId, textView.getAutoSizeTextType());
            propertyReader.readIntEnum(this.mBreakStrategyId, textView.getBreakStrategy());
            propertyReader.readBoolean(this.mCursorVisibleId, textView.isCursorVisible());
            propertyReader.readObject(this.mDrawableBlendModeId, textView.getCompoundDrawableTintBlendMode());
            propertyReader.readInt(this.mDrawablePaddingId, textView.getCompoundDrawablePadding());
            propertyReader.readObject(this.mDrawableTintId, textView.getCompoundDrawableTintList());
            propertyReader.readObject(this.mDrawableTintModeId, textView.getCompoundDrawableTintMode());
            propertyReader.readBoolean(this.mElegantTextHeightId, textView.isElegantTextHeight());
            propertyReader.readObject(this.mEllipsizeId, textView.getEllipsize());
            propertyReader.readBoolean(this.mFallbackLineSpacingId, textView.isFallbackLineSpacing());
            propertyReader.readInt(this.mFirstBaselineToTopHeightId, textView.getFirstBaselineToTopHeight());
            propertyReader.readObject(this.mFontFeatureSettingsId, textView.getFontFeatureSettings());
            propertyReader.readBoolean(this.mFreezesTextId, textView.getFreezesText());
            propertyReader.readGravity(this.mGravityId, textView.getGravity());
            propertyReader.readObject(this.mHintId, textView.getHint());
            propertyReader.readIntEnum(this.mHyphenationFrequencyId, textView.getHyphenationFrequency());
            propertyReader.readInt(this.mImeActionIdId, textView.getImeActionId());
            propertyReader.readObject(this.mImeActionLabelId, textView.getImeActionLabel());
            propertyReader.readIntFlag(this.mImeOptionsId, textView.getImeOptions());
            propertyReader.readBoolean(this.mIncludeFontPaddingId, textView.getIncludeFontPadding());
            propertyReader.readIntFlag(this.mInputTypeId, textView.getInputType());
            propertyReader.readIntEnum(this.mJustificationModeId, textView.getJustificationMode());
            propertyReader.readInt(this.mLastBaselineToBottomHeightId, textView.getLastBaselineToBottomHeight());
            propertyReader.readFloat(this.mLetterSpacingId, textView.getLetterSpacing());
            propertyReader.readInt(this.mLineHeightId, textView.getLineHeight());
            propertyReader.readFloat(this.mLineSpacingExtraId, textView.getLineSpacingExtra());
            propertyReader.readFloat(this.mLineSpacingMultiplierId, textView.getLineSpacingMultiplier());
            propertyReader.readBoolean(this.mLinksClickableId, textView.getLinksClickable());
            propertyReader.readInt(this.mMarqueeRepeatLimitId, textView.getMarqueeRepeatLimit());
            propertyReader.readInt(this.mMaxEmsId, textView.getMaxEms());
            propertyReader.readInt(this.mMaxHeightId, textView.getMaxHeight());
            propertyReader.readInt(this.mMaxLinesId, textView.getMaxLines());
            propertyReader.readInt(this.mMaxWidthId, textView.getMaxWidth());
            propertyReader.readInt(this.mMinEmsId, textView.getMinEms());
            propertyReader.readInt(this.mMinLinesId, textView.getMinLines());
            propertyReader.readInt(this.mMinWidthId, textView.getMinWidth());
            propertyReader.readObject(this.mPrivateImeOptionsId, textView.getPrivateImeOptions());
            propertyReader.readBoolean(this.mScrollHorizontallyId, textView.isHorizontallyScrollable());
            propertyReader.readColor(this.mShadowColorId, textView.getShadowColor());
            propertyReader.readFloat(this.mShadowDxId, textView.getShadowDx());
            propertyReader.readFloat(this.mShadowDyId, textView.getShadowDy());
            propertyReader.readFloat(this.mShadowRadiusId, textView.getShadowRadius());
            propertyReader.readBoolean(this.mSingleLineId, textView.isSingleLine());
            propertyReader.readObject(this.mTextId, textView.getText());
            propertyReader.readBoolean(this.mTextAllCapsId, textView.isAllCaps());
            propertyReader.readObject(this.mTextColorId, textView.getTextColors());
            propertyReader.readColor(this.mTextColorHighlightId, textView.getHighlightColor());
            propertyReader.readObject(this.mTextColorHintId, textView.getHintTextColors());
            propertyReader.readObject(this.mTextColorLinkId, textView.getLinkTextColors());
            propertyReader.readBoolean(this.mTextIsSelectableId, textView.isTextSelectable());
            propertyReader.readFloat(this.mTextScaleXId, textView.getTextScaleX());
            propertyReader.readFloat(this.mTextSizeId, textView.getTextSize());
            propertyReader.readObject(this.mTypefaceId, textView.getTypeface());
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sAppearanceValues = sparseIntArray;
        sparseIntArray.put(6, 4);
        sparseIntArray.put(5, 3);
        sparseIntArray.put(7, 5);
        sparseIntArray.put(8, 6);
        sparseIntArray.put(2, 0);
        sparseIntArray.put(96, 19);
        sparseIntArray.put(3, 1);
        sparseIntArray.put(75, 12);
        sparseIntArray.put(4, 2);
        sparseIntArray.put(95, 18);
        sparseIntArray.put(72, 11);
        sparseIntArray.put(36, 7);
        sparseIntArray.put(37, 8);
        sparseIntArray.put(38, 9);
        sparseIntArray.put(39, 10);
        sparseIntArray.put(76, 13);
        sparseIntArray.put(91, 17);
        sparseIntArray.put(77, 14);
        sparseIntArray.put(78, 15);
        sparseIntArray.put(90, 16);
        UNKNOWN_BORING = new BoringLayout.Metrics();
    }

    static class Drawables {
        static final int BOTTOM = 3;
        static final int DRAWABLE_LEFT = 1;
        static final int DRAWABLE_NONE = -1;
        static final int DRAWABLE_RIGHT = 0;
        static final int LEFT = 0;
        static final int RIGHT = 2;
        static final int TOP = 1;
        BlendMode mBlendMode;
        Drawable mDrawableEnd;
        Drawable mDrawableError;
        int mDrawableHeightEnd;
        int mDrawableHeightError;
        int mDrawableHeightLeft;
        int mDrawableHeightRight;
        int mDrawableHeightStart;
        int mDrawableHeightTemp;
        Drawable mDrawableLeftInitial;
        int mDrawablePadding;
        Drawable mDrawableRightInitial;
        int mDrawableSizeBottom;
        int mDrawableSizeEnd;
        int mDrawableSizeError;
        int mDrawableSizeLeft;
        int mDrawableSizeRight;
        int mDrawableSizeStart;
        int mDrawableSizeTemp;
        int mDrawableSizeTop;
        Drawable mDrawableStart;
        Drawable mDrawableTemp;
        int mDrawableWidthBottom;
        int mDrawableWidthTop;
        boolean mHasTint;
        boolean mHasTintMode;
        boolean mIsRtlCompatibilityMode;
        boolean mOverride;
        ColorStateList mTintList;
        final Rect mCompoundRect = new Rect();
        final Drawable[] mShowing = new Drawable[4];
        int mDrawableSaved = -1;

        public Drawables(Context context) {
            this.mIsRtlCompatibilityMode = context.getApplicationInfo().targetSdkVersion < 17 || !context.getApplicationInfo().hasRtlSupport();
            this.mOverride = false;
        }

        public boolean hasMetadata() {
            return this.mDrawablePadding != 0 || this.mHasTintMode || this.mHasTint;
        }

        public boolean resolveWithLayoutDirection(int i) {
            Drawable[] drawableArr = this.mShowing;
            Drawable drawable = drawableArr[0];
            Drawable drawable2 = drawableArr[2];
            drawableArr[0] = this.mDrawableLeftInitial;
            drawableArr[2] = this.mDrawableRightInitial;
            if (this.mIsRtlCompatibilityMode) {
                Drawable drawable3 = this.mDrawableStart;
                if (drawable3 != null && drawableArr[0] == null) {
                    drawableArr[0] = drawable3;
                    this.mDrawableSizeLeft = this.mDrawableSizeStart;
                    this.mDrawableHeightLeft = this.mDrawableHeightStart;
                }
                Drawable drawable4 = this.mDrawableEnd;
                if (drawable4 != null && drawableArr[2] == null) {
                    drawableArr[2] = drawable4;
                    this.mDrawableSizeRight = this.mDrawableSizeEnd;
                    this.mDrawableHeightRight = this.mDrawableHeightEnd;
                }
            } else if (i == 1) {
                if (this.mOverride) {
                    drawableArr[2] = this.mDrawableStart;
                    this.mDrawableSizeRight = this.mDrawableSizeStart;
                    this.mDrawableHeightRight = this.mDrawableHeightStart;
                    drawableArr[0] = this.mDrawableEnd;
                    this.mDrawableSizeLeft = this.mDrawableSizeEnd;
                    this.mDrawableHeightLeft = this.mDrawableHeightEnd;
                }
            } else if (this.mOverride) {
                drawableArr[0] = this.mDrawableStart;
                this.mDrawableSizeLeft = this.mDrawableSizeStart;
                this.mDrawableHeightLeft = this.mDrawableHeightStart;
                drawableArr[2] = this.mDrawableEnd;
                this.mDrawableSizeRight = this.mDrawableSizeEnd;
                this.mDrawableHeightRight = this.mDrawableHeightEnd;
            }
            applyErrorDrawableIfNeeded(i);
            Drawable[] drawableArr2 = this.mShowing;
            return (drawableArr2[0] == drawable && drawableArr2[2] == drawable2) ? false : true;
        }

        public void setErrorDrawable(Drawable drawable, TextView textView) {
            Drawable drawable2 = this.mDrawableError;
            if (drawable2 != drawable && drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.mDrawableError = drawable;
            if (drawable != null) {
                Rect rect = this.mCompoundRect;
                this.mDrawableError.setState(textView.getDrawableState());
                this.mDrawableError.copyBounds(rect);
                this.mDrawableError.setCallback(textView);
                this.mDrawableSizeError = rect.width();
                this.mDrawableHeightError = rect.height();
                return;
            }
            this.mDrawableHeightError = 0;
            this.mDrawableSizeError = 0;
        }

        private void applyErrorDrawableIfNeeded(int i) {
            int i2 = this.mDrawableSaved;
            if (i2 == 0) {
                this.mShowing[2] = this.mDrawableTemp;
                this.mDrawableSizeRight = this.mDrawableSizeTemp;
                this.mDrawableHeightRight = this.mDrawableHeightTemp;
            } else if (i2 == 1) {
                this.mShowing[0] = this.mDrawableTemp;
                this.mDrawableSizeLeft = this.mDrawableSizeTemp;
                this.mDrawableHeightLeft = this.mDrawableHeightTemp;
            }
            Drawable drawable = this.mDrawableError;
            if (drawable != null) {
                if (i == 1) {
                    this.mDrawableSaved = 1;
                    Drawable[] drawableArr = this.mShowing;
                    this.mDrawableTemp = drawableArr[0];
                    this.mDrawableSizeTemp = this.mDrawableSizeLeft;
                    this.mDrawableHeightTemp = this.mDrawableHeightLeft;
                    drawableArr[0] = drawable;
                    this.mDrawableSizeLeft = this.mDrawableSizeError;
                    this.mDrawableHeightLeft = this.mDrawableHeightError;
                    return;
                }
                this.mDrawableSaved = 0;
                Drawable[] drawableArr2 = this.mShowing;
                this.mDrawableTemp = drawableArr2[2];
                this.mDrawableSizeTemp = this.mDrawableSizeRight;
                this.mDrawableHeightTemp = this.mDrawableHeightRight;
                drawableArr2[2] = drawable;
                this.mDrawableSizeRight = this.mDrawableSizeError;
                this.mDrawableHeightRight = this.mDrawableHeightError;
            }
        }
    }

    public static void preloadFontCache() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT);
        paint.measureText("H");
    }

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public TextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:35:0x01cc. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0765  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x077c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x078a  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0798  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x07dd  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x080d  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0814 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0820  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x082f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0834  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0846 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0857  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0889  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x08ad  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x08b6  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x08c5  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x08db  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x092c  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0936  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x093f  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x097b  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0982  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0989  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0990  */
    /* JADX WARN: Removed duplicated region for block: B:444:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextView(android.content.Context r46, android.util.AttributeSet r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 2600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    private void setTextInternal(CharSequence charSequence) {
        this.mText = charSequence;
        this.mSpannable = charSequence instanceof Spannable ? (Spannable) charSequence : null;
        this.mPrecomputed = charSequence instanceof PrecomputedText ? (PrecomputedText) charSequence : null;
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (supportsAutoSizeText()) {
            if (i == 0) {
                clearAutoSizeConfiguration();
                return;
            }
            if (i == 1) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                if (setupAutoSizeText()) {
                    autoSizeText();
                    invalidate();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Unknown auto-size text type: " + i);
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (supportsAutoSizeText()) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (setupAutoSizeText()) {
                autoSizeText();
                invalidate();
            }
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (supportsAutoSizeText()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(iArrCopyOf);
                if (!setupAutoSizeUniformPresetSizesConfiguration()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.mHasPresetAutoSizeValues = false;
            }
            if (setupAutoSizeText()) {
                autoSizeText();
                invalidate();
            }
        }
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    public int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    public int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    public int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(iArr);
            setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        boolean z = this.mAutoSizeTextSizesInPx.length > 0;
        this.mHasPresetAutoSizeValues = z;
        if (z) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = r0[0];
            this.mAutoSizeMaxTextSizeInPx = r0[r1 - 1];
            this.mAutoSizeStepGranularityInPx = -1.0f;
        }
        return z;
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        }
        if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        }
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
        this.mAutoSizeTextType = 1;
        this.mAutoSizeMinTextSizeInPx = f;
        this.mAutoSizeMaxTextSizeInPx = f2;
        this.mAutoSizeStepGranularityInPx = f3;
        this.mHasPresetAutoSizeValues = false;
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = EmptyArray.INT;
        this.mNeedsAutoSizeText = false;
    }

    private int[] cleanupAutoSizePresetSizes(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        IntArray intArray = new IntArray();
        for (int i : iArr) {
            if (i > 0 && intArray.binarySearch(i) < 0) {
                intArray.add(i);
            }
        }
        return length == intArray.size() ? iArr : intArray.toArray();
    }

    private boolean setupAutoSizeText() {
        if (supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                int iFloor = ((int) Math.floor((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx)) + 1;
                int[] iArr = new int[iFloor];
                for (int i = 0; i < iFloor; i++) {
                    iArr[i] = Math.round(this.mAutoSizeMinTextSizeInPx + (i * this.mAutoSizeStepGranularityInPx));
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(iArr);
            }
            this.mNeedsAutoSizeText = true;
        } else {
            this.mNeedsAutoSizeText = false;
        }
        return this.mNeedsAutoSizeText;
    }

    private int[] parseDimensionArray(TypedArray typedArray) {
        if (typedArray == null) {
            return null;
        }
        int length = typedArray.length();
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = typedArray.getDimensionPixelSize(i, 0);
        }
        return iArr;
    }

    @Override // android.view.View
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            if (i2 == -1 && intent != null) {
                CharSequence charSequenceExtra = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
                if (charSequenceExtra != null) {
                    if (isTextEditable()) {
                        replaceSelectionWithText(charSequenceExtra);
                        Editor editor = this.mEditor;
                        if (editor != null) {
                            editor.refreshTextActionMode();
                            return;
                        }
                        return;
                    }
                    if (charSequenceExtra.length() > 0) {
                        Toast.makeText(getContext(), String.valueOf(charSequenceExtra), 1).show();
                        return;
                    }
                    return;
                }
                return;
            }
            Spannable spannable = this.mSpannable;
            if (spannable != null) {
                Selection.setSelection(spannable, getSelectionEnd());
            }
        }
    }

    private void setTypefaceFromAttrs(Typeface typeface, String str, int i, int i2, int i3) {
        if (typeface == null && str != null) {
            resolveStyleAndSetTypeface(Typeface.create(str, 0), i2, i3);
            return;
        }
        if (typeface != null) {
            resolveStyleAndSetTypeface(typeface, i2, i3);
            return;
        }
        if (i == 1) {
            resolveStyleAndSetTypeface(Typeface.SANS_SERIF, i2, i3);
            return;
        }
        if (i == 2) {
            resolveStyleAndSetTypeface(Typeface.SERIF, i2, i3);
        } else if (i == 3) {
            resolveStyleAndSetTypeface(Typeface.MONOSPACE, i2, i3);
        } else {
            resolveStyleAndSetTypeface(null, i2, i3);
        }
    }

    private void resolveStyleAndSetTypeface(Typeface typeface, int i, int i2) {
        if (i2 >= 0) {
            setTypeface(Typeface.create(typeface, Math.min(1000, i2), (i & 2) != 0));
        } else {
            setTypeface(typeface, i);
        }
    }

    private void setRelativeDrawablesIfNeeded(Drawable drawable, Drawable drawable2) {
        if ((drawable == null && drawable2 == null) ? false : true) {
            Drawables drawables = this.mDrawables;
            if (drawables == null) {
                drawables = new Drawables(getContext());
                this.mDrawables = drawables;
            }
            this.mDrawables.mOverride = true;
            Rect rect = drawables.mCompoundRect;
            int[] drawableState = getDrawableState();
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.setState(drawableState);
                drawable.copyBounds(rect);
                drawable.setCallback(this);
                drawables.mDrawableStart = drawable;
                drawables.mDrawableSizeStart = rect.width();
                drawables.mDrawableHeightStart = rect.height();
            } else {
                drawables.mDrawableHeightStart = 0;
                drawables.mDrawableSizeStart = 0;
            }
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                drawable2.setState(drawableState);
                drawable2.copyBounds(rect);
                drawable2.setCallback(this);
                drawables.mDrawableEnd = drawable2;
                drawables.mDrawableSizeEnd = rect.width();
                drawables.mDrawableHeightEnd = rect.height();
            } else {
                drawables.mDrawableHeightEnd = 0;
                drawables.mDrawableSizeEnd = 0;
            }
            resetResolvedDrawables();
            resolveDrawables();
            applyCompoundDrawableTint();
        }
    }

    @Override // android.view.View
    @RemotableViewMethod
    public void setEnabled(boolean z) {
        InputMethodManager inputMethodManager;
        InputMethodManager inputMethodManager2;
        if (z == isEnabled()) {
            return;
        }
        if (!z && (inputMethodManager2 = getInputMethodManager()) != null && inputMethodManager2.isActive(this)) {
            inputMethodManager2.hideSoftInputFromWindow(getWindowToken(), 0);
        }
        super.setEnabled(z);
        if (z && (inputMethodManager = getInputMethodManager()) != null) {
            inputMethodManager.restartInput(this);
        }
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.invalidateTextDisplayList();
            this.mEditor.prepareCursorControllers();
            this.mEditor.makeBlink();
        }
    }

    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (i > 0) {
            if (typeface == null) {
                typefaceCreate = Typeface.defaultFromStyle(i);
            } else {
                typefaceCreate = Typeface.create(typeface, i);
            }
            setTypeface(typefaceCreate);
            int i2 = (~(typefaceCreate != null ? typefaceCreate.getStyle() : 0)) & i;
            this.mTextPaint.setFakeBoldText((i2 & 1) != 0);
            this.mTextPaint.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setTypeface(typeface);
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getText() {
        return this.mText;
    }

    public int length() {
        return this.mText.length();
    }

    public Editable getEditableText() {
        CharSequence charSequence = this.mText;
        if (charSequence instanceof Editable) {
            return (Editable) charSequence;
        }
        return null;
    }

    public CharSequence getTransformed() {
        return this.mTransformed;
    }

    public int getLineHeight() {
        return FastMath.round((this.mTextPaint.getFontMetricsInt(null) * this.mSpacingMult) + this.mSpacingAdd);
    }

    public final Layout getLayout() {
        return this.mLayout;
    }

    final Layout getHintLayout() {
        return this.mHintLayout;
    }

    public final UndoManager getUndoManager() {
        throw new UnsupportedOperationException("not implemented");
    }

    public final Editor getEditorForTesting() {
        return this.mEditor;
    }

    public final void setUndoManager(UndoManager undoManager, String str) {
        throw new UnsupportedOperationException("not implemented");
    }

    public final KeyListener getKeyListener() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return null;
        }
        return editor.mKeyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.mListenerChanged = true;
        setKeyListenerOnly(keyListener);
        fixFocusableAndClickableSettings();
        if (keyListener != null) {
            createEditorIfNeeded();
            setInputTypeFromEditor();
        } else {
            Editor editor = this.mEditor;
            if (editor != null) {
                editor.mInputType = 0;
            }
        }
        InputMethodManager inputMethodManager = getInputMethodManager();
        if (inputMethodManager != null) {
            inputMethodManager.restartInput(this);
        }
    }

    private void setInputTypeFromEditor() {
        try {
            Editor editor = this.mEditor;
            editor.mInputType = editor.mKeyListener.getInputType();
        } catch (IncompatibleClassChangeError unused) {
            this.mEditor.mInputType = 1;
        }
        setInputTypeSingleLine(this.mSingleLine);
    }

    private void setKeyListenerOnly(KeyListener keyListener) {
        if (this.mEditor == null && keyListener == null) {
            return;
        }
        createEditorIfNeeded();
        if (this.mEditor.mKeyListener != keyListener) {
            this.mEditor.mKeyListener = keyListener;
            if (keyListener != null) {
                CharSequence charSequence = this.mText;
                if (!(charSequence instanceof Editable)) {
                    setText(charSequence);
                }
            }
            setFilters((Editable) this.mText, this.mFilters);
        }
    }

    public final MovementMethod getMovementMethod() {
        return this.mMovement;
    }

    public final void setMovementMethod(MovementMethod movementMethod) {
        if (this.mMovement != movementMethod) {
            this.mMovement = movementMethod;
            if (movementMethod != null && this.mSpannable == null) {
                setText(this.mText);
            }
            fixFocusableAndClickableSettings();
            Editor editor = this.mEditor;
            if (editor != null) {
                editor.prepareCursorControllers();
            }
        }
    }

    private void fixFocusableAndClickableSettings() {
        Editor editor;
        if (this.mMovement != null || ((editor = this.mEditor) != null && editor.mKeyListener != null)) {
            setFocusable(1);
            setClickable(true);
            setLongClickable(true);
        } else {
            setFocusable(16);
            setClickable(false);
            setLongClickable(false);
        }
    }

    public final TransformationMethod getTransformationMethod() {
        return this.mTransformation;
    }

    public final void setTransformationMethod(TransformationMethod transformationMethod) {
        Spannable spannable;
        TransformationMethod transformationMethod2 = this.mTransformation;
        if (transformationMethod == transformationMethod2) {
            return;
        }
        if (transformationMethod2 != null && (spannable = this.mSpannable) != null) {
            spannable.removeSpan(transformationMethod2);
        }
        this.mTransformation = transformationMethod;
        if (transformationMethod instanceof TransformationMethod2) {
            TransformationMethod2 transformationMethod22 = (TransformationMethod2) transformationMethod;
            boolean z = (isTextSelectable() || (this.mText instanceof Editable)) ? false : true;
            this.mAllowTransformationLengthChange = z;
            transformationMethod22.setLengthChangesAllowed(z);
        } else {
            this.mAllowTransformationLengthChange = false;
        }
        setText(this.mText);
        if (hasPasswordTransformationMethod()) {
            notifyViewAccessibilityStateChangedIfNeeded(0);
        }
        this.mTextDir = getTextDirectionHeuristic();
    }

    public int getCompoundPaddingTop() {
        Drawables drawables = this.mDrawables;
        if (drawables == null || drawables.mShowing[1] == null) {
            return this.mPaddingTop;
        }
        return this.mPaddingTop + drawables.mDrawablePadding + drawables.mDrawableSizeTop;
    }

    public int getCompoundPaddingBottom() {
        Drawables drawables = this.mDrawables;
        if (drawables == null || drawables.mShowing[3] == null) {
            return this.mPaddingBottom;
        }
        return this.mPaddingBottom + drawables.mDrawablePadding + drawables.mDrawableSizeBottom;
    }

    public int getCompoundPaddingLeft() {
        Drawables drawables = this.mDrawables;
        if (drawables == null || drawables.mShowing[0] == null) {
            return this.mPaddingLeft;
        }
        return this.mPaddingLeft + drawables.mDrawablePadding + drawables.mDrawableSizeLeft;
    }

    public int getCompoundPaddingRight() {
        Drawables drawables = this.mDrawables;
        if (drawables == null || drawables.mShowing[2] == null) {
            return this.mPaddingRight;
        }
        return this.mPaddingRight + drawables.mDrawablePadding + drawables.mDrawableSizeRight;
    }

    public int getCompoundPaddingStart() {
        resolveDrawables();
        if (getLayoutDirection() != 1) {
            return getCompoundPaddingLeft();
        }
        return getCompoundPaddingRight();
    }

    public int getCompoundPaddingEnd() {
        resolveDrawables();
        if (getLayoutDirection() != 1) {
            return getCompoundPaddingRight();
        }
        return getCompoundPaddingLeft();
    }

    public int getExtendedPaddingTop() {
        int i;
        if (this.mMaxMode != 1) {
            return getCompoundPaddingTop();
        }
        if (this.mLayout == null) {
            assumeLayout();
        }
        if (this.mLayout.getLineCount() <= this.mMaximum) {
            return getCompoundPaddingTop();
        }
        int compoundPaddingTop = getCompoundPaddingTop();
        int height = (getHeight() - compoundPaddingTop) - getCompoundPaddingBottom();
        int lineTop = this.mLayout.getLineTop(this.mMaximum);
        return (lineTop < height && (i = this.mGravity & 112) != 48) ? i == 80 ? (compoundPaddingTop + height) - lineTop : compoundPaddingTop + ((height - lineTop) / 2) : compoundPaddingTop;
    }

    public int getExtendedPaddingBottom() {
        if (this.mMaxMode != 1) {
            return getCompoundPaddingBottom();
        }
        if (this.mLayout == null) {
            assumeLayout();
        }
        if (this.mLayout.getLineCount() <= this.mMaximum) {
            return getCompoundPaddingBottom();
        }
        int compoundPaddingTop = getCompoundPaddingTop();
        int compoundPaddingBottom = getCompoundPaddingBottom();
        int height = (getHeight() - compoundPaddingTop) - compoundPaddingBottom;
        int lineTop = this.mLayout.getLineTop(this.mMaximum);
        if (lineTop >= height) {
            return compoundPaddingBottom;
        }
        int i = this.mGravity & 112;
        return i == 48 ? (compoundPaddingBottom + height) - lineTop : i == 80 ? compoundPaddingBottom : compoundPaddingBottom + ((height - lineTop) / 2);
    }

    public int getTotalPaddingLeft() {
        return getCompoundPaddingLeft();
    }

    public int getTotalPaddingRight() {
        return getCompoundPaddingRight();
    }

    public int getTotalPaddingStart() {
        return getCompoundPaddingStart();
    }

    public int getTotalPaddingEnd() {
        return getCompoundPaddingEnd();
    }

    public int getTotalPaddingTop() {
        return getExtendedPaddingTop() + getVerticalOffset(true);
    }

    public int getTotalPaddingBottom() {
        return getExtendedPaddingBottom() + getBottomVerticalOffset(true);
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            if (drawables.mDrawableStart != null) {
                drawables.mDrawableStart.setCallback(null);
            }
            drawables.mDrawableStart = null;
            if (drawables.mDrawableEnd != null) {
                drawables.mDrawableEnd.setCallback(null);
            }
            drawables.mDrawableEnd = null;
            drawables.mDrawableHeightStart = 0;
            drawables.mDrawableSizeStart = 0;
            drawables.mDrawableHeightEnd = 0;
            drawables.mDrawableSizeEnd = 0;
        }
        if ((drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) ? false : true) {
            if (drawables == null) {
                drawables = new Drawables(getContext());
                this.mDrawables = drawables;
            }
            this.mDrawables.mOverride = false;
            if (drawables.mShowing[0] != drawable && drawables.mShowing[0] != null) {
                drawables.mShowing[0].setCallback(null);
            }
            drawables.mShowing[0] = drawable;
            if (drawables.mShowing[1] != drawable2 && drawables.mShowing[1] != null) {
                drawables.mShowing[1].setCallback(null);
            }
            drawables.mShowing[1] = drawable2;
            if (drawables.mShowing[2] != drawable3 && drawables.mShowing[2] != null) {
                drawables.mShowing[2].setCallback(null);
            }
            drawables.mShowing[2] = drawable3;
            if (drawables.mShowing[3] != drawable4 && drawables.mShowing[3] != null) {
                drawables.mShowing[3].setCallback(null);
            }
            drawables.mShowing[3] = drawable4;
            Rect rect = drawables.mCompoundRect;
            int[] drawableState = getDrawableState();
            if (drawable != null) {
                drawable.setState(drawableState);
                drawable.copyBounds(rect);
                drawable.setCallback(this);
                drawables.mDrawableSizeLeft = rect.width();
                drawables.mDrawableHeightLeft = rect.height();
            } else {
                drawables.mDrawableHeightLeft = 0;
                drawables.mDrawableSizeLeft = 0;
            }
            if (drawable3 != null) {
                drawable3.setState(drawableState);
                drawable3.copyBounds(rect);
                drawable3.setCallback(this);
                drawables.mDrawableSizeRight = rect.width();
                drawables.mDrawableHeightRight = rect.height();
            } else {
                drawables.mDrawableHeightRight = 0;
                drawables.mDrawableSizeRight = 0;
            }
            if (drawable2 != null) {
                drawable2.setState(drawableState);
                drawable2.copyBounds(rect);
                drawable2.setCallback(this);
                drawables.mDrawableSizeTop = rect.height();
                drawables.mDrawableWidthTop = rect.width();
            } else {
                drawables.mDrawableWidthTop = 0;
                drawables.mDrawableSizeTop = 0;
            }
            if (drawable4 != null) {
                drawable4.setState(drawableState);
                drawable4.copyBounds(rect);
                drawable4.setCallback(this);
                drawables.mDrawableSizeBottom = rect.height();
                drawables.mDrawableWidthBottom = rect.width();
            } else {
                drawables.mDrawableWidthBottom = 0;
                drawables.mDrawableSizeBottom = 0;
            }
        } else if (drawables != null) {
            if (!drawables.hasMetadata()) {
                this.mDrawables = null;
            } else {
                for (int length = drawables.mShowing.length - 1; length >= 0; length--) {
                    if (drawables.mShowing[length] != null) {
                        drawables.mShowing[length].setCallback(null);
                    }
                    drawables.mShowing[length] = null;
                }
                drawables.mDrawableHeightLeft = 0;
                drawables.mDrawableSizeLeft = 0;
                drawables.mDrawableHeightRight = 0;
                drawables.mDrawableSizeRight = 0;
                drawables.mDrawableWidthTop = 0;
                drawables.mDrawableSizeTop = 0;
                drawables.mDrawableWidthBottom = 0;
                drawables.mDrawableSizeBottom = 0;
            }
        }
        if (drawables != null) {
            drawables.mDrawableLeftInitial = drawable;
            drawables.mDrawableRightInitial = drawable3;
        }
        resetResolvedDrawables();
        resolveDrawables();
        applyCompoundDrawableTint();
        invalidate();
        requestLayout();
    }

    @RemotableViewMethod
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? context.getDrawable(i) : null, i2 != 0 ? context.getDrawable(i2) : null, i3 != 0 ? context.getDrawable(i3) : null, i4 != 0 ? context.getDrawable(i4) : null);
    }

    @RemotableViewMethod
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight());
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        }
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, drawable4.getIntrinsicWidth(), drawable4.getIntrinsicHeight());
        }
        setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @RemotableViewMethod
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            if (drawables.mShowing[0] != null) {
                drawables.mShowing[0].setCallback(null);
            }
            Drawable[] drawableArr = drawables.mShowing;
            drawables.mDrawableLeftInitial = null;
            drawableArr[0] = null;
            if (drawables.mShowing[2] != null) {
                drawables.mShowing[2].setCallback(null);
            }
            Drawable[] drawableArr2 = drawables.mShowing;
            drawables.mDrawableRightInitial = null;
            drawableArr2[2] = null;
            drawables.mDrawableHeightLeft = 0;
            drawables.mDrawableSizeLeft = 0;
            drawables.mDrawableHeightRight = 0;
            drawables.mDrawableSizeRight = 0;
        }
        if ((drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) ? false : true) {
            if (drawables == null) {
                drawables = new Drawables(getContext());
                this.mDrawables = drawables;
            }
            this.mDrawables.mOverride = true;
            if (drawables.mDrawableStart != drawable && drawables.mDrawableStart != null) {
                drawables.mDrawableStart.setCallback(null);
            }
            drawables.mDrawableStart = drawable;
            if (drawables.mShowing[1] != drawable2 && drawables.mShowing[1] != null) {
                drawables.mShowing[1].setCallback(null);
            }
            drawables.mShowing[1] = drawable2;
            if (drawables.mDrawableEnd != drawable3 && drawables.mDrawableEnd != null) {
                drawables.mDrawableEnd.setCallback(null);
            }
            drawables.mDrawableEnd = drawable3;
            if (drawables.mShowing[3] != drawable4 && drawables.mShowing[3] != null) {
                drawables.mShowing[3].setCallback(null);
            }
            drawables.mShowing[3] = drawable4;
            Rect rect = drawables.mCompoundRect;
            int[] drawableState = getDrawableState();
            if (drawable != null) {
                drawable.setState(drawableState);
                drawable.copyBounds(rect);
                drawable.setCallback(this);
                drawables.mDrawableSizeStart = rect.width();
                drawables.mDrawableHeightStart = rect.height();
            } else {
                drawables.mDrawableHeightStart = 0;
                drawables.mDrawableSizeStart = 0;
            }
            if (drawable3 != null) {
                drawable3.setState(drawableState);
                drawable3.copyBounds(rect);
                drawable3.setCallback(this);
                drawables.mDrawableSizeEnd = rect.width();
                drawables.mDrawableHeightEnd = rect.height();
            } else {
                drawables.mDrawableHeightEnd = 0;
                drawables.mDrawableSizeEnd = 0;
            }
            if (drawable2 != null) {
                drawable2.setState(drawableState);
                drawable2.copyBounds(rect);
                drawable2.setCallback(this);
                drawables.mDrawableSizeTop = rect.height();
                drawables.mDrawableWidthTop = rect.width();
            } else {
                drawables.mDrawableWidthTop = 0;
                drawables.mDrawableSizeTop = 0;
            }
            if (drawable4 != null) {
                drawable4.setState(drawableState);
                drawable4.copyBounds(rect);
                drawable4.setCallback(this);
                drawables.mDrawableSizeBottom = rect.height();
                drawables.mDrawableWidthBottom = rect.width();
            } else {
                drawables.mDrawableWidthBottom = 0;
                drawables.mDrawableSizeBottom = 0;
            }
        } else if (drawables != null) {
            if (!drawables.hasMetadata()) {
                this.mDrawables = null;
            } else {
                if (drawables.mDrawableStart != null) {
                    drawables.mDrawableStart.setCallback(null);
                }
                drawables.mDrawableStart = null;
                if (drawables.mShowing[1] != null) {
                    drawables.mShowing[1].setCallback(null);
                }
                drawables.mShowing[1] = null;
                if (drawables.mDrawableEnd != null) {
                    drawables.mDrawableEnd.setCallback(null);
                }
                drawables.mDrawableEnd = null;
                if (drawables.mShowing[3] != null) {
                    drawables.mShowing[3].setCallback(null);
                }
                drawables.mShowing[3] = null;
                drawables.mDrawableHeightStart = 0;
                drawables.mDrawableSizeStart = 0;
                drawables.mDrawableHeightEnd = 0;
                drawables.mDrawableSizeEnd = 0;
                drawables.mDrawableWidthTop = 0;
                drawables.mDrawableSizeTop = 0;
                drawables.mDrawableWidthBottom = 0;
                drawables.mDrawableSizeBottom = 0;
            }
        }
        resetResolvedDrawables();
        resolveDrawables();
        invalidate();
        requestLayout();
    }

    @RemotableViewMethod
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? context.getDrawable(i) : null, i2 != 0 ? context.getDrawable(i2) : null, i3 != 0 ? context.getDrawable(i3) : null, i4 != 0 ? context.getDrawable(i4) : null);
    }

    @RemotableViewMethod
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight());
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        }
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, drawable4.getIntrinsicWidth(), drawable4.getIntrinsicHeight());
        }
        setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    public Drawable[] getCompoundDrawables() {
        Drawables drawables = this.mDrawables;
        return drawables != null ? (Drawable[]) drawables.mShowing.clone() : new Drawable[]{null, null, null, null};
    }

    public Drawable[] getCompoundDrawablesRelative() {
        Drawables drawables = this.mDrawables;
        return drawables != null ? new Drawable[]{drawables.mDrawableStart, drawables.mShowing[1], drawables.mDrawableEnd, drawables.mShowing[3]} : new Drawable[]{null, null, null, null};
    }

    @RemotableViewMethod
    public void setCompoundDrawablePadding(int i) {
        Drawables drawables = this.mDrawables;
        if (i != 0) {
            if (drawables == null) {
                drawables = new Drawables(getContext());
                this.mDrawables = drawables;
            }
            drawables.mDrawablePadding = i;
        } else if (drawables != null) {
            drawables.mDrawablePadding = i;
        }
        invalidate();
        requestLayout();
    }

    public int getCompoundDrawablePadding() {
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            return drawables.mDrawablePadding;
        }
        return 0;
    }

    public void setCompoundDrawableTintList(ColorStateList colorStateList) {
        if (this.mDrawables == null) {
            this.mDrawables = new Drawables(getContext());
        }
        this.mDrawables.mTintList = colorStateList;
        this.mDrawables.mHasTint = true;
        applyCompoundDrawableTint();
    }

    public ColorStateList getCompoundDrawableTintList() {
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            return drawables.mTintList;
        }
        return null;
    }

    public void setCompoundDrawableTintMode(PorterDuff.Mode mode) {
        setCompoundDrawableTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setCompoundDrawableTintBlendMode(BlendMode blendMode) {
        if (this.mDrawables == null) {
            this.mDrawables = new Drawables(getContext());
        }
        this.mDrawables.mBlendMode = blendMode;
        this.mDrawables.mHasTintMode = true;
        applyCompoundDrawableTint();
    }

    public PorterDuff.Mode getCompoundDrawableTintMode() {
        BlendMode compoundDrawableTintBlendMode = getCompoundDrawableTintBlendMode();
        if (compoundDrawableTintBlendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(compoundDrawableTintBlendMode);
        }
        return null;
    }

    public BlendMode getCompoundDrawableTintBlendMode() {
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            return drawables.mBlendMode;
        }
        return null;
    }

    private void applyCompoundDrawableTint() {
        Drawables drawables = this.mDrawables;
        if (drawables == null) {
            return;
        }
        if (drawables.mHasTint || this.mDrawables.mHasTintMode) {
            ColorStateList colorStateList = this.mDrawables.mTintList;
            BlendMode blendMode = this.mDrawables.mBlendMode;
            boolean z = this.mDrawables.mHasTint;
            boolean z2 = this.mDrawables.mHasTintMode;
            int[] drawableState = getDrawableState();
            for (Drawable drawable : this.mDrawables.mShowing) {
                if (drawable != null && drawable != this.mDrawables.mDrawableError) {
                    drawable.mutate();
                    if (z) {
                        drawable.setTintList(colorStateList);
                    }
                    if (z2) {
                        drawable.setTintBlendMode(blendMode);
                    }
                    if (drawable.isStateful()) {
                        drawable.setState(drawableState);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        if (i != this.mPaddingLeft || i3 != this.mPaddingRight || i2 != this.mPaddingTop || i4 != this.mPaddingBottom) {
            nullLayouts();
        }
        super.setPadding(i, i2, i3, i4);
        invalidate();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        if (i != getPaddingStart() || i3 != getPaddingEnd() || i2 != this.mPaddingTop || i4 != this.mPaddingBottom) {
            nullLayouts();
        }
        super.setPaddingRelative(i, i2, i3, i4);
        invalidate();
    }

    public void setFirstBaselineToTopHeight(int i) {
        int i2;
        Preconditions.checkArgumentNonnegative(i);
        Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
        if (getIncludeFontPadding()) {
            i2 = fontMetricsInt.top;
        } else {
            i2 = fontMetricsInt.ascent;
        }
        if (i > Math.abs(i2)) {
            setPadding(getPaddingLeft(), i - (-i2), getPaddingRight(), getPaddingBottom());
        }
    }

    public void setLastBaselineToBottomHeight(int i) {
        int i2;
        Preconditions.checkArgumentNonnegative(i);
        Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
        if (getIncludeFontPadding()) {
            i2 = fontMetricsInt.bottom;
        } else {
            i2 = fontMetricsInt.descent;
        }
        if (i > Math.abs(i2)) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), i - i2);
        }
    }

    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public final int getAutoLinkMask() {
        return this.mAutoLinkMask;
    }

    @RemotableViewMethod
    public void setTextSelectHandle(Drawable drawable) {
        Preconditions.checkNotNull(drawable, "The text select handle should not be null.");
        this.mTextSelectHandle = drawable;
        this.mTextSelectHandleRes = 0;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.loadHandleDrawables(true);
        }
    }

    @RemotableViewMethod
    public void setTextSelectHandle(int i) {
        Preconditions.checkArgument(i != 0, "The text select handle should be a valid drawable resource id.");
        setTextSelectHandle(this.mContext.getDrawable(i));
    }

    public Drawable getTextSelectHandle() {
        if (this.mTextSelectHandle == null && this.mTextSelectHandleRes != 0) {
            this.mTextSelectHandle = this.mContext.getDrawable(this.mTextSelectHandleRes);
        }
        return this.mTextSelectHandle;
    }

    @RemotableViewMethod
    public void setTextSelectHandleLeft(Drawable drawable) {
        Preconditions.checkNotNull(drawable, "The left text select handle should not be null.");
        this.mTextSelectHandleLeft = drawable;
        this.mTextSelectHandleLeftRes = 0;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.loadHandleDrawables(true);
        }
    }

    @RemotableViewMethod
    public void setTextSelectHandleLeft(int i) {
        Preconditions.checkArgument(i != 0, "The text select left handle should be a valid drawable resource id.");
        setTextSelectHandleLeft(this.mContext.getDrawable(i));
    }

    public Drawable getTextSelectHandleLeft() {
        if (this.mTextSelectHandleLeft == null && this.mTextSelectHandleLeftRes != 0) {
            this.mTextSelectHandleLeft = this.mContext.getDrawable(this.mTextSelectHandleLeftRes);
        }
        return this.mTextSelectHandleLeft;
    }

    @RemotableViewMethod
    public void setTextSelectHandleRight(Drawable drawable) {
        Preconditions.checkNotNull(drawable, "The right text select handle should not be null.");
        this.mTextSelectHandleRight = drawable;
        this.mTextSelectHandleRightRes = 0;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.loadHandleDrawables(true);
        }
    }

    @RemotableViewMethod
    public void setTextSelectHandleRight(int i) {
        Preconditions.checkArgument(i != 0, "The text select right handle should be a valid drawable resource id.");
        setTextSelectHandleRight(this.mContext.getDrawable(i));
    }

    public Drawable getTextSelectHandleRight() {
        if (this.mTextSelectHandleRight == null && this.mTextSelectHandleRightRes != 0) {
            this.mTextSelectHandleRight = this.mContext.getDrawable(this.mTextSelectHandleRightRes);
        }
        return this.mTextSelectHandleRight;
    }

    public void setTextCursorDrawable(Drawable drawable) {
        this.mCursorDrawable = drawable;
        this.mCursorDrawableRes = 0;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.loadCursorDrawable();
        }
    }

    public void setTextCursorDrawable(int i) {
        setTextCursorDrawable(i != 0 ? this.mContext.getDrawable(i) : null);
    }

    public Drawable getTextCursorDrawable() {
        if (this.mCursorDrawable == null && this.mCursorDrawableRes != 0) {
            this.mCursorDrawable = this.mContext.getDrawable(this.mCursorDrawableRes);
        }
        return this.mCursorDrawable;
    }

    public void setTextAppearance(int i) {
        setTextAppearance(this.mContext, i);
    }

    @Deprecated
    public void setTextAppearance(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.TextAppearance);
        TextAppearanceAttributes textAppearanceAttributes = new TextAppearanceAttributes();
        readTextAppearance(context, typedArrayObtainStyledAttributes, textAppearanceAttributes, false);
        typedArrayObtainStyledAttributes.recycle();
        applyTextAppearance(textAppearanceAttributes);
    }

    private static class TextAppearanceAttributes {
        boolean mAllCaps;
        boolean mElegant;
        boolean mFallbackLineSpacing;
        String mFontFamily;
        boolean mFontFamilyExplicit;
        String mFontFeatureSettings;
        Typeface mFontTypeface;
        String mFontVariationSettings;
        int mFontWeight;
        boolean mHasElegant;
        boolean mHasFallbackLineSpacing;
        boolean mHasLetterSpacing;
        float mLetterSpacing;
        int mShadowColor;
        float mShadowDx;
        float mShadowDy;
        float mShadowRadius;
        ColorStateList mTextColor;
        int mTextColorHighlight;
        ColorStateList mTextColorHint;
        ColorStateList mTextColorLink;
        LocaleList mTextLocales;
        int mTextSize;
        int mTextSizeUnit;
        int mTextStyle;
        int mTypefaceIndex;

        private TextAppearanceAttributes() {
            this.mTextColorHighlight = 0;
            this.mTextColor = null;
            this.mTextColorHint = null;
            this.mTextColorLink = null;
            this.mTextSize = -1;
            this.mTextSizeUnit = -1;
            this.mTextLocales = null;
            this.mFontFamily = null;
            this.mFontTypeface = null;
            this.mFontFamilyExplicit = false;
            this.mTypefaceIndex = -1;
            this.mTextStyle = 0;
            this.mFontWeight = -1;
            this.mAllCaps = false;
            this.mShadowColor = 0;
            this.mShadowDx = 0.0f;
            this.mShadowDy = 0.0f;
            this.mShadowRadius = 0.0f;
            this.mHasElegant = false;
            this.mElegant = false;
            this.mHasFallbackLineSpacing = false;
            this.mFallbackLineSpacing = false;
            this.mHasLetterSpacing = false;
            this.mLetterSpacing = 0.0f;
            this.mFontFeatureSettings = null;
            this.mFontVariationSettings = null;
        }

        public String toString() {
            return "TextAppearanceAttributes {\n    mTextColorHighlight:" + this.mTextColorHighlight + "\n    mTextColor:" + this.mTextColor + "\n    mTextColorHint:" + this.mTextColorHint + "\n    mTextColorLink:" + this.mTextColorLink + "\n    mTextSize:" + this.mTextSize + "\n    mTextSizeUnit:" + this.mTextSizeUnit + "\n    mTextLocales:" + this.mTextLocales + "\n    mFontFamily:" + this.mFontFamily + "\n    mFontTypeface:" + this.mFontTypeface + "\n    mFontFamilyExplicit:" + this.mFontFamilyExplicit + "\n    mTypefaceIndex:" + this.mTypefaceIndex + "\n    mTextStyle:" + this.mTextStyle + "\n    mFontWeight:" + this.mFontWeight + "\n    mAllCaps:" + this.mAllCaps + "\n    mShadowColor:" + this.mShadowColor + "\n    mShadowDx:" + this.mShadowDx + "\n    mShadowDy:" + this.mShadowDy + "\n    mShadowRadius:" + this.mShadowRadius + "\n    mHasElegant:" + this.mHasElegant + "\n    mElegant:" + this.mElegant + "\n    mHasFallbackLineSpacing:" + this.mHasFallbackLineSpacing + "\n    mFallbackLineSpacing:" + this.mFallbackLineSpacing + "\n    mHasLetterSpacing:" + this.mHasLetterSpacing + "\n    mLetterSpacing:" + this.mLetterSpacing + "\n    mFontFeatureSettings:" + this.mFontFeatureSettings + "\n    mFontVariationSettings:" + this.mFontVariationSettings + "\n}";
        }
    }

    private void readTextAppearance(Context context, TypedArray typedArray, TextAppearanceAttributes textAppearanceAttributes, boolean z) {
        int i;
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (z) {
                i = sAppearanceValues.get(index, -1);
                if (i == -1) {
                }
            } else {
                i = index;
            }
            switch (i) {
                case 0:
                    textAppearanceAttributes.mTextSize = typedArray.getDimensionPixelSize(index, textAppearanceAttributes.mTextSize);
                    textAppearanceAttributes.mTextSizeUnit = typedArray.peekValue(index).getComplexUnit();
                    break;
                case 1:
                    textAppearanceAttributes.mTypefaceIndex = typedArray.getInt(index, textAppearanceAttributes.mTypefaceIndex);
                    if (textAppearanceAttributes.mTypefaceIndex != -1 && !textAppearanceAttributes.mFontFamilyExplicit) {
                        textAppearanceAttributes.mFontFamily = null;
                    }
                    break;
                case 2:
                    textAppearanceAttributes.mTextStyle = typedArray.getInt(index, textAppearanceAttributes.mTextStyle);
                    break;
                case 3:
                    textAppearanceAttributes.mTextColor = typedArray.getColorStateList(index);
                    break;
                case 4:
                    textAppearanceAttributes.mTextColorHighlight = typedArray.getColor(index, textAppearanceAttributes.mTextColorHighlight);
                    break;
                case 5:
                    textAppearanceAttributes.mTextColorHint = typedArray.getColorStateList(index);
                    break;
                case 6:
                    textAppearanceAttributes.mTextColorLink = typedArray.getColorStateList(index);
                    break;
                case 7:
                    textAppearanceAttributes.mShadowColor = typedArray.getInt(index, textAppearanceAttributes.mShadowColor);
                    break;
                case 8:
                    textAppearanceAttributes.mShadowDx = typedArray.getFloat(index, textAppearanceAttributes.mShadowDx);
                    break;
                case 9:
                    textAppearanceAttributes.mShadowDy = typedArray.getFloat(index, textAppearanceAttributes.mShadowDy);
                    break;
                case 10:
                    textAppearanceAttributes.mShadowRadius = typedArray.getFloat(index, textAppearanceAttributes.mShadowRadius);
                    break;
                case 11:
                    textAppearanceAttributes.mAllCaps = typedArray.getBoolean(index, textAppearanceAttributes.mAllCaps);
                    break;
                case 12:
                    if (!context.isRestricted() && context.canLoadUnsafeResources()) {
                        try {
                            textAppearanceAttributes.mFontTypeface = typedArray.getFont(index);
                            break;
                        } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                        }
                    }
                    if (textAppearanceAttributes.mFontTypeface == null) {
                        textAppearanceAttributes.mFontFamily = typedArray.getString(index);
                    }
                    textAppearanceAttributes.mFontFamilyExplicit = true;
                    break;
                case 13:
                    textAppearanceAttributes.mHasElegant = true;
                    textAppearanceAttributes.mElegant = typedArray.getBoolean(index, textAppearanceAttributes.mElegant);
                    break;
                case 14:
                    textAppearanceAttributes.mHasLetterSpacing = true;
                    textAppearanceAttributes.mLetterSpacing = typedArray.getFloat(index, textAppearanceAttributes.mLetterSpacing);
                    break;
                case 15:
                    textAppearanceAttributes.mFontFeatureSettings = typedArray.getString(index);
                    break;
                case 16:
                    textAppearanceAttributes.mFontVariationSettings = typedArray.getString(index);
                    break;
                case 17:
                    textAppearanceAttributes.mHasFallbackLineSpacing = true;
                    textAppearanceAttributes.mFallbackLineSpacing = typedArray.getBoolean(index, textAppearanceAttributes.mFallbackLineSpacing);
                    break;
                case 18:
                    textAppearanceAttributes.mFontWeight = typedArray.getInt(index, textAppearanceAttributes.mFontWeight);
                    break;
                case 19:
                    String string = typedArray.getString(index);
                    if (string != null) {
                        LocaleList localeListForLanguageTags = LocaleList.forLanguageTags(string);
                        if (!localeListForLanguageTags.isEmpty()) {
                            textAppearanceAttributes.mTextLocales = localeListForLanguageTags;
                        }
                    }
                    break;
            }
        }
    }

    private void applyTextAppearance(TextAppearanceAttributes textAppearanceAttributes) {
        if (textAppearanceAttributes.mTextColor != null) {
            setTextColor(textAppearanceAttributes.mTextColor);
        }
        if (textAppearanceAttributes.mTextColorHint != null) {
            setHintTextColor(textAppearanceAttributes.mTextColorHint);
        }
        if (textAppearanceAttributes.mTextColorLink != null) {
            setLinkTextColor(textAppearanceAttributes.mTextColorLink);
        }
        if (textAppearanceAttributes.mTextColorHighlight != 0) {
            setHighlightColor(textAppearanceAttributes.mTextColorHighlight);
        }
        if (textAppearanceAttributes.mTextSize != -1) {
            this.mTextSizeUnit = textAppearanceAttributes.mTextSizeUnit;
            setRawTextSize(textAppearanceAttributes.mTextSize, true);
        }
        if (textAppearanceAttributes.mTextLocales != null) {
            setTextLocales(textAppearanceAttributes.mTextLocales);
        }
        if (textAppearanceAttributes.mTypefaceIndex != -1 && !textAppearanceAttributes.mFontFamilyExplicit) {
            textAppearanceAttributes.mFontFamily = null;
        }
        setTypefaceFromAttrs(textAppearanceAttributes.mFontTypeface, textAppearanceAttributes.mFontFamily, textAppearanceAttributes.mTypefaceIndex, textAppearanceAttributes.mTextStyle, textAppearanceAttributes.mFontWeight);
        if (textAppearanceAttributes.mShadowColor != 0) {
            setShadowLayer(textAppearanceAttributes.mShadowRadius, textAppearanceAttributes.mShadowDx, textAppearanceAttributes.mShadowDy, textAppearanceAttributes.mShadowColor);
        }
        if (textAppearanceAttributes.mAllCaps) {
            setTransformationMethod(new AllCapsTransformationMethod(getContext()));
        }
        if (textAppearanceAttributes.mHasElegant) {
            setElegantTextHeight(textAppearanceAttributes.mElegant);
        }
        if (textAppearanceAttributes.mHasFallbackLineSpacing) {
            setFallbackLineSpacing(textAppearanceAttributes.mFallbackLineSpacing);
        }
        if (textAppearanceAttributes.mHasLetterSpacing) {
            setLetterSpacing(textAppearanceAttributes.mLetterSpacing);
        }
        if (textAppearanceAttributes.mFontFeatureSettings != null) {
            setFontFeatureSettings(textAppearanceAttributes.mFontFeatureSettings);
        }
        if (textAppearanceAttributes.mFontVariationSettings != null) {
            setFontVariationSettings(textAppearanceAttributes.mFontVariationSettings);
        }
    }

    public Locale getTextLocale() {
        return this.mTextPaint.getTextLocale();
    }

    public LocaleList getTextLocales() {
        return this.mTextPaint.getTextLocales();
    }

    private void changeListenerLocaleTo(Locale locale) {
        Editor editor;
        KeyListener dateTimeKeyListener;
        if (this.mListenerChanged || (editor = this.mEditor) == null) {
            return;
        }
        KeyListener keyListener = editor.mKeyListener;
        if (keyListener instanceof DigitsKeyListener) {
            dateTimeKeyListener = DigitsKeyListener.getInstance(locale, (DigitsKeyListener) keyListener);
        } else if (keyListener instanceof DateKeyListener) {
            dateTimeKeyListener = DateKeyListener.getInstance(locale);
        } else if (keyListener instanceof TimeKeyListener) {
            dateTimeKeyListener = TimeKeyListener.getInstance(locale);
        } else if (!(keyListener instanceof DateTimeKeyListener)) {
            return;
        } else {
            dateTimeKeyListener = DateTimeKeyListener.getInstance(locale);
        }
        boolean zIsPasswordInputType = isPasswordInputType(this.mEditor.mInputType);
        setKeyListenerOnly(dateTimeKeyListener);
        setInputTypeFromEditor();
        if (zIsPasswordInputType) {
            int i = this.mEditor.mInputType & 15;
            if (i == 1) {
                this.mEditor.mInputType |= 128;
            } else if (i == 2) {
                this.mEditor.mInputType |= 16;
            }
        }
    }

    public void setTextLocale(Locale locale) {
        this.mLocalesChanged = true;
        this.mTextPaint.setTextLocale(locale);
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setTextLocales(LocaleList localeList) {
        this.mLocalesChanged = true;
        this.mTextPaint.setTextLocales(localeList);
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mLocalesChanged) {
            return;
        }
        this.mTextPaint.setTextLocales(LocaleList.getDefault());
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    @ViewDebug.ExportedProperty(category = "text")
    public float getTextSize() {
        return this.mTextPaint.getTextSize();
    }

    @ViewDebug.ExportedProperty(category = "text")
    public float getScaledTextSize() {
        return this.mTextPaint.getTextSize() / this.mTextPaint.density;
    }

    @ViewDebug.ExportedProperty(category = "text", mapping = {@ViewDebug.IntToString(from = 0, to = "NORMAL"), @ViewDebug.IntToString(from = 1, to = "BOLD"), @ViewDebug.IntToString(from = 2, to = "ITALIC"), @ViewDebug.IntToString(from = 3, to = "BOLD_ITALIC")})
    public int getTypefaceStyle() {
        Typeface typeface = this.mTextPaint.getTypeface();
        if (typeface != null) {
            return typeface.getStyle();
        }
        return 0;
    }

    @RemotableViewMethod
    public void setTextSize(float f) {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f) {
        if (isAutoSizeEnabled()) {
            return;
        }
        setTextSizeInternal(i, f, true);
    }

    private void setTextSizeInternal(int i, float f, boolean z) {
        Resources resources;
        Context context = getContext();
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        this.mTextSizeUnit = i;
        setRawTextSize(TypedValue.applyDimension(i, f, resources.getDisplayMetrics()), z);
    }

    private void setRawTextSize(float f, boolean z) {
        if (f != this.mTextPaint.getTextSize()) {
            this.mTextPaint.setTextSize(f);
            if (!z || this.mLayout == null) {
                return;
            }
            this.mNeedsAutoSizeText = false;
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public int getTextSizeUnit() {
        return this.mTextSizeUnit;
    }

    public float getTextScaleX() {
        return this.mTextPaint.getTextScaleX();
    }

    @RemotableViewMethod
    public void setTextScaleX(float f) {
        if (f != this.mTextPaint.getTextScaleX()) {
            this.mUserSetTextScaleX = true;
            this.mTextPaint.setTextScaleX(f);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setTypeface(Typeface typeface) {
        if (this.mTextPaint.getTypeface() != typeface) {
            this.mTextPaint.setTypeface(typeface);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public Typeface getTypeface() {
        return this.mTextPaint.getTypeface();
    }

    public void setElegantTextHeight(boolean z) {
        if (z != this.mTextPaint.isElegantTextHeight()) {
            this.mTextPaint.setElegantTextHeight(z);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setFallbackLineSpacing(boolean z) {
        if (this.mUseFallbackLineSpacing != z) {
            this.mUseFallbackLineSpacing = z;
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public boolean isFallbackLineSpacing() {
        return this.mUseFallbackLineSpacing;
    }

    public boolean isElegantTextHeight() {
        return this.mTextPaint.isElegantTextHeight();
    }

    public float getLetterSpacing() {
        return this.mTextPaint.getLetterSpacing();
    }

    @RemotableViewMethod
    public void setLetterSpacing(float f) {
        if (f != this.mTextPaint.getLetterSpacing()) {
            this.mTextPaint.setLetterSpacing(f);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public String getFontFeatureSettings() {
        return this.mTextPaint.getFontFeatureSettings();
    }

    public String getFontVariationSettings() {
        return this.mTextPaint.getFontVariationSettings();
    }

    public void setBreakStrategy(int i) {
        this.mBreakStrategy = i;
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public int getBreakStrategy() {
        return this.mBreakStrategy;
    }

    public void setHyphenationFrequency(int i) {
        this.mHyphenationFrequency = i;
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public int getHyphenationFrequency() {
        return this.mHyphenationFrequency;
    }

    public PrecomputedText.Params getTextMetricsParams() {
        return new PrecomputedText.Params(new TextPaint(this.mTextPaint), getTextDirectionHeuristic(), this.mBreakStrategy, this.mHyphenationFrequency);
    }

    public void setTextMetricsParams(PrecomputedText.Params params) {
        this.mTextPaint.set(params.getTextPaint());
        this.mUserSetTextScaleX = true;
        this.mTextDir = params.getTextDirection();
        this.mBreakStrategy = params.getBreakStrategy();
        this.mHyphenationFrequency = params.getHyphenationFrequency();
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setJustificationMode(int i) {
        this.mJustificationMode = i;
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public int getJustificationMode() {
        return this.mJustificationMode;
    }

    @RemotableViewMethod
    public void setFontFeatureSettings(String str) {
        if (str != this.mTextPaint.getFontFeatureSettings()) {
            this.mTextPaint.setFontFeatureSettings(str);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public boolean setFontVariationSettings(String str) {
        String fontVariationSettings = this.mTextPaint.getFontVariationSettings();
        if (str == fontVariationSettings) {
            return true;
        }
        if (str != null && str.equals(fontVariationSettings)) {
            return true;
        }
        boolean fontVariationSettings2 = this.mTextPaint.setFontVariationSettings(str);
        if (fontVariationSettings2 && this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
        return fontVariationSettings2;
    }

    @RemotableViewMethod
    public void setTextColor(int i) {
        this.mTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    @RemotableViewMethod
    public void setTextColor(ColorStateList colorStateList) {
        Objects.requireNonNull(colorStateList);
        this.mTextColor = colorStateList;
        updateTextColors();
    }

    public final ColorStateList getTextColors() {
        return this.mTextColor;
    }

    public final int getCurrentTextColor() {
        return this.mCurTextColor;
    }

    @RemotableViewMethod
    public void setHighlightColor(int i) {
        if (this.mHighlightColor != i) {
            this.mHighlightColor = i;
            invalidate();
        }
    }

    public int getHighlightColor() {
        return this.mHighlightColor;
    }

    @RemotableViewMethod
    public final void setShowSoftInputOnFocus(boolean z) {
        createEditorIfNeeded();
        this.mEditor.mShowSoftInputOnFocus = z;
    }

    public final boolean getShowSoftInputOnFocus() {
        Editor editor = this.mEditor;
        return editor == null || editor.mShowSoftInputOnFocus;
    }

    public void setShadowLayer(float f, float f2, float f3, int i) {
        this.mTextPaint.setShadowLayer(f, f2, f3, i);
        this.mShadowRadius = f;
        this.mShadowDx = f2;
        this.mShadowDy = f3;
        this.mShadowColor = i;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.invalidateTextDisplayList();
            this.mEditor.invalidateHandlesAndActionMode();
        }
        invalidate();
    }

    public float getShadowRadius() {
        return this.mShadowRadius;
    }

    public float getShadowDx() {
        return this.mShadowDx;
    }

    public float getShadowDy() {
        return this.mShadowDy;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public TextPaint getPaint() {
        return this.mTextPaint;
    }

    @RemotableViewMethod
    public final void setAutoLinkMask(int i) {
        this.mAutoLinkMask = i;
    }

    @RemotableViewMethod
    public final void setLinksClickable(boolean z) {
        this.mLinksClickable = z;
    }

    public final boolean getLinksClickable() {
        return this.mLinksClickable;
    }

    public URLSpan[] getUrls() {
        CharSequence charSequence = this.mText;
        return charSequence instanceof Spanned ? (URLSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), URLSpan.class) : new URLSpan[0];
    }

    @RemotableViewMethod
    public final void setHintTextColor(int i) {
        this.mHintTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    public final void setHintTextColor(ColorStateList colorStateList) {
        this.mHintTextColor = colorStateList;
        updateTextColors();
    }

    public final ColorStateList getHintTextColors() {
        return this.mHintTextColor;
    }

    public final int getCurrentHintTextColor() {
        return this.mHintTextColor != null ? this.mCurHintTextColor : this.mCurTextColor;
    }

    @RemotableViewMethod
    public final void setLinkTextColor(int i) {
        this.mLinkTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    public final void setLinkTextColor(ColorStateList colorStateList) {
        this.mLinkTextColor = colorStateList;
        updateTextColors();
    }

    public final ColorStateList getLinkTextColors() {
        return this.mLinkTextColor;
    }

    public void setGravity(int i) {
        if ((i & 8388615) == 0) {
            i |= 8388611;
        }
        if ((i & 112) == 0) {
            i |= 48;
        }
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        boolean z = i2 != (8388615 & i3);
        if (i != i3) {
            invalidate();
        }
        this.mGravity = i;
        Layout layout = this.mLayout;
        if (layout == null || !z) {
            return;
        }
        int width = layout.getWidth();
        Layout layout2 = this.mHintLayout;
        int width2 = layout2 != null ? layout2.getWidth() : 0;
        BoringLayout.Metrics metrics = UNKNOWN_BORING;
        makeNewLayout(width, width2, metrics, metrics, ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), true);
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getPaintFlags() {
        return this.mTextPaint.getFlags();
    }

    @RemotableViewMethod
    public void setPaintFlags(int i) {
        if (this.mTextPaint.getFlags() != i) {
            this.mTextPaint.setFlags(i);
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setHorizontallyScrolling(boolean z) {
        if (this.mHorizontallyScrolling != z) {
            this.mHorizontallyScrolling = z;
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public final boolean isHorizontallyScrollable() {
        return this.mHorizontallyScrolling;
    }

    public boolean getHorizontallyScrolling() {
        return this.mHorizontallyScrolling;
    }

    @RemotableViewMethod
    public void setMinLines(int i) {
        this.mMinimum = i;
        this.mMinMode = 1;
        requestLayout();
        invalidate();
    }

    public int getMinLines() {
        if (this.mMinMode == 1) {
            return this.mMinimum;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMinHeight(int i) {
        this.mMinimum = i;
        this.mMinMode = 2;
        requestLayout();
        invalidate();
    }

    public int getMinHeight() {
        if (this.mMinMode == 2) {
            return this.mMinimum;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMaxLines(int i) {
        this.mMaximum = i;
        this.mMaxMode = 1;
        requestLayout();
        invalidate();
    }

    public int getMaxLines() {
        if (this.mMaxMode == 1) {
            return this.mMaximum;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMaxHeight(int i) {
        this.mMaximum = i;
        this.mMaxMode = 2;
        requestLayout();
        invalidate();
    }

    public int getMaxHeight() {
        if (this.mMaxMode == 2) {
            return this.mMaximum;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setLines(int i) {
        this.mMinimum = i;
        this.mMaximum = i;
        this.mMinMode = 1;
        this.mMaxMode = 1;
        requestLayout();
        invalidate();
    }

    @RemotableViewMethod
    public void setHeight(int i) {
        this.mMinimum = i;
        this.mMaximum = i;
        this.mMinMode = 2;
        this.mMaxMode = 2;
        requestLayout();
        invalidate();
    }

    @RemotableViewMethod
    public void setMinEms(int i) {
        this.mMinWidth = i;
        this.mMinWidthMode = 1;
        requestLayout();
        invalidate();
    }

    public int getMinEms() {
        if (this.mMinWidthMode == 1) {
            return this.mMinWidth;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMinWidth(int i) {
        this.mMinWidth = i;
        this.mMinWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public int getMinWidth() {
        if (this.mMinWidthMode == 2) {
            return this.mMinWidth;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMaxEms(int i) {
        this.mMaxWidth = i;
        this.mMaxWidthMode = 1;
        requestLayout();
        invalidate();
    }

    public int getMaxEms() {
        if (this.mMaxWidthMode == 1) {
            return this.mMaxWidth;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        this.mMaxWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public int getMaxWidth() {
        if (this.mMaxWidthMode == 2) {
            return this.mMaxWidth;
        }
        return -1;
    }

    @RemotableViewMethod
    public void setEms(int i) {
        this.mMinWidth = i;
        this.mMaxWidth = i;
        this.mMinWidthMode = 1;
        this.mMaxWidthMode = 1;
        requestLayout();
        invalidate();
    }

    @RemotableViewMethod
    public void setWidth(int i) {
        this.mMinWidth = i;
        this.mMaxWidth = i;
        this.mMinWidthMode = 2;
        this.mMaxWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public void setLineSpacing(float f, float f2) {
        if (this.mSpacingAdd == f && this.mSpacingMult == f2) {
            return;
        }
        this.mSpacingAdd = f;
        this.mSpacingMult = f2;
        if (this.mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public float getLineSpacingMultiplier() {
        return this.mSpacingMult;
    }

    public float getLineSpacingExtra() {
        return this.mSpacingAdd;
    }

    public void setLineHeight(int i) {
        Preconditions.checkArgumentNonnegative(i);
        if (i != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i - r0, 1.0f);
        }
    }

    public final void append(CharSequence charSequence) {
        append(charSequence, 0, charSequence.length());
    }

    public void append(CharSequence charSequence, int i, int i2) {
        CharSequence charSequence2 = this.mText;
        if (!(charSequence2 instanceof Editable)) {
            setText(charSequence2, BufferType.EDITABLE);
        }
        ((Editable) this.mText).append(charSequence, i, i2);
        int i3 = this.mAutoLinkMask;
        if (i3 == 0 || !Linkify.addLinks(this.mSpannable, i3) || !this.mLinksClickable || textCanBeSelected()) {
            return;
        }
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateTextColors() {
        /*
            r6 = this;
            int[] r0 = r6.getDrawableState()
            android.content.res.ColorStateList r1 = r6.mTextColor
            r2 = 0
            int r1 = r1.getColorForState(r0, r2)
            int r3 = r6.mCurTextColor
            r4 = 1
            if (r1 == r3) goto L14
            r6.mCurTextColor = r1
            r1 = r4
            goto L15
        L14:
            r1 = r2
        L15:
            android.content.res.ColorStateList r3 = r6.mLinkTextColor
            if (r3 == 0) goto L28
            int r3 = r3.getColorForState(r0, r2)
            android.text.TextPaint r5 = r6.mTextPaint
            int r5 = r5.linkColor
            if (r3 == r5) goto L28
            android.text.TextPaint r1 = r6.mTextPaint
            r1.linkColor = r3
            r1 = r4
        L28:
            android.content.res.ColorStateList r3 = r6.mHintTextColor
            if (r3 == 0) goto L3f
            int r0 = r3.getColorForState(r0, r2)
            int r2 = r6.mCurHintTextColor
            if (r0 == r2) goto L3f
            r6.mCurHintTextColor = r0
            java.lang.CharSequence r0 = r6.mText
            int r0 = r0.length()
            if (r0 != 0) goto L3f
            goto L40
        L3f:
            r4 = r1
        L40:
            if (r4 == 0) goto L4c
            android.widget.Editor r0 = r6.mEditor
            if (r0 == 0) goto L49
            r0.invalidateTextDisplayList()
        L49:
            r6.invalidate()
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.updateTextColors():void");
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        super.drawableStateChanged();
        ColorStateList colorStateList3 = this.mTextColor;
        if ((colorStateList3 != null && colorStateList3.isStateful()) || (((colorStateList = this.mHintTextColor) != null && colorStateList.isStateful()) || ((colorStateList2 = this.mLinkTextColor) != null && colorStateList2.isStateful()))) {
            updateTextColors();
        }
        if (this.mDrawables != null) {
            int[] drawableState = getDrawableState();
            for (Drawable drawable : this.mDrawables.mShowing) {
                if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
                    invalidateDrawable(drawable);
                }
            }
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            for (Drawable drawable : drawables.mShowing) {
                if (drawable != null) {
                    drawable.setHotspot(f, f2);
                }
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        int selectionEnd;
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        boolean freezesText = getFreezesText();
        int selectionStart = -1;
        boolean z = false;
        if (this.mText != null) {
            selectionStart = getSelectionStart();
            selectionEnd = getSelectionEnd();
            if (selectionStart >= 0 || selectionEnd >= 0) {
                z = true;
            }
        } else {
            selectionEnd = -1;
        }
        if (!freezesText && !z) {
            return parcelableOnSaveInstanceState;
        }
        SavedState savedState = new SavedState(parcelableOnSaveInstanceState);
        if (freezesText) {
            CharSequence charSequence = this.mText;
            if (charSequence instanceof Spanned) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mText);
                if (this.mEditor != null) {
                    removeMisspelledSpans(spannableStringBuilder);
                    spannableStringBuilder.removeSpan(this.mEditor.mSuggestionRangeSpan);
                }
                savedState.text = spannableStringBuilder;
            } else {
                savedState.text = charSequence.toString();
            }
        }
        if (z) {
            savedState.selStart = selectionStart;
            savedState.selEnd = selectionEnd;
        }
        if (isFocused() && selectionStart >= 0 && selectionEnd >= 0) {
            savedState.frozenWithFocus = true;
        }
        savedState.error = getError();
        Editor editor = this.mEditor;
        if (editor != null) {
            savedState.editorState = editor.saveInstanceState();
        }
        return savedState;
    }

    void removeMisspelledSpans(Spannable spannable) {
        SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spannable.getSpans(0, spannable.length(), SuggestionSpan.class);
        for (int i = 0; i < suggestionSpanArr.length; i++) {
            int flags = suggestionSpanArr[i].getFlags();
            if ((flags & 1) != 0 && (flags & 2) != 0) {
                spannable.removeSpan(suggestionSpanArr[i]);
            }
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.text != null) {
            setText(savedState.text);
        }
        if (savedState.selStart >= 0 && savedState.selEnd >= 0 && this.mSpannable != null) {
            int length = this.mText.length();
            if (savedState.selStart > length || savedState.selEnd > length) {
                Log.e(LOG_TAG, "Saved cursor position " + savedState.selStart + "/" + savedState.selEnd + " out of range for " + (savedState.text != null ? "(restored) " : "") + "text " + ((Object) this.mText));
            } else {
                Selection.setSelection(this.mSpannable, savedState.selStart, savedState.selEnd);
                if (savedState.frozenWithFocus) {
                    createEditorIfNeeded();
                    this.mEditor.mFrozenWithFocus = true;
                }
            }
        }
        if (savedState.error != null) {
            final CharSequence charSequence = savedState.error;
            post(new Runnable() { // from class: android.widget.TextView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TextView.this.mEditor == null || !TextView.this.mEditor.mErrorWasChanged) {
                        TextView.this.setError(charSequence);
                    }
                }
            });
        }
        if (savedState.editorState != null) {
            createEditorIfNeeded();
            this.mEditor.restoreInstanceState(savedState.editorState);
        }
    }

    @RemotableViewMethod
    public void setFreezesText(boolean z) {
        this.mFreezesText = z;
    }

    public boolean getFreezesText() {
        return this.mFreezesText;
    }

    public final void setEditableFactory(Editable.Factory factory) {
        this.mEditableFactory = factory;
        setText(this.mText);
    }

    public final void setSpannableFactory(Spannable.Factory factory) {
        this.mSpannableFactory = factory;
        setText(this.mText);
    }

    @RemotableViewMethod
    public final void setText(CharSequence charSequence) {
        setText(charSequence, this.mBufferType);
    }

    @RemotableViewMethod
    public final void setTextKeepState(CharSequence charSequence) {
        setTextKeepState(charSequence, this.mBufferType);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        setText(charSequence, bufferType, true, 0);
        CharWrapper charWrapper = this.mCharWrapper;
        if (charWrapper != null) {
            charWrapper.mChars = null;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void setText(CharSequence charSequence, BufferType bufferType, boolean z, int i) {
        int length;
        CharSequence charSequenceNewSpannable;
        Spannable spannableNewSpannable;
        BufferType bufferType2 = bufferType;
        this.mTextSetFromXmlOrResourceId = false;
        CharSequence charSequence2 = charSequence == null ? "" : charSequence;
        CharSequence charSequenceRemoveSuggestionSpans = charSequence2;
        if (!isSuggestionsEnabled()) {
            charSequenceRemoveSuggestionSpans = removeSuggestionSpans(charSequence2);
        }
        if (!this.mUserSetTextScaleX) {
            this.mTextPaint.setTextScaleX(1.0f);
        }
        if ((charSequenceRemoveSuggestionSpans instanceof Spanned) && ((Spanned) charSequenceRemoveSuggestionSpans).getSpanStart(TextUtils.TruncateAt.MARQUEE) >= 0) {
            if (ViewConfiguration.get(this.mContext).isFadingMarqueeEnabled()) {
                setHorizontalFadingEdgeEnabled(true);
                this.mMarqueeFadeMode = 0;
            } else {
                setHorizontalFadingEdgeEnabled(false);
                this.mMarqueeFadeMode = 1;
            }
            setEllipsize(TextUtils.TruncateAt.MARQUEE);
        }
        int length2 = this.mFilters.length;
        int i2 = 0;
        CharSequence charSequence3 = charSequenceRemoveSuggestionSpans;
        while (i2 < length2) {
            CharSequence charSequenceFilter = this.mFilters[i2].filter(charSequence3, 0, charSequence3.length(), EMPTY_SPANNED, 0, 0);
            if (charSequenceFilter != null) {
                charSequence3 = charSequenceFilter;
            }
            i2++;
            charSequence3 = charSequence3;
        }
        if (z) {
            CharSequence charSequence4 = this.mText;
            if (charSequence4 != null) {
                length = charSequence4.length();
                sendBeforeTextChanged(this.mText, 0, length, charSequence3.length());
            } else {
                sendBeforeTextChanged("", 0, 0, charSequence3.length());
                length = i;
            }
        } else {
            length = i;
        }
        ArrayList<TextWatcher> arrayList = this.mListeners;
        boolean z2 = (arrayList == null || arrayList.size() == 0) ? false : true;
        PrecomputedText precomputedText = charSequence3 instanceof PrecomputedText ? (PrecomputedText) charSequence3 : null;
        if (bufferType2 == BufferType.EDITABLE || getKeyListener() != null || z2) {
            createEditorIfNeeded();
            this.mEditor.forgetUndoRedo();
            Editable editableNewEditable = this.mEditableFactory.newEditable(charSequence3);
            setFilters(editableNewEditable, this.mFilters);
            InputMethodManager inputMethodManager = getInputMethodManager();
            charSequenceNewSpannable = editableNewEditable;
            if (inputMethodManager != null) {
                inputMethodManager.restartInput(this);
                charSequenceNewSpannable = editableNewEditable;
            }
        } else if (precomputedText != null) {
            if (this.mTextDir == null) {
                this.mTextDir = getTextDirectionHeuristic();
            }
            int iCheckResultUsable = precomputedText.getParams().checkResultUsable(getPaint(), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            charSequenceNewSpannable = charSequence3;
            if (iCheckResultUsable == 0) {
                throw new IllegalArgumentException("PrecomputedText's Parameters don't match the parameters of this TextView.Consider using setTextMetricsParams(precomputedText.getParams()) to override the settings of this TextView: PrecomputedText: " + precomputedText.getParams() + "TextView: " + getTextMetricsParams());
            }
            if (iCheckResultUsable == 1) {
                PrecomputedText.create(precomputedText, getTextMetricsParams());
                charSequenceNewSpannable = charSequence3;
            }
        } else if (bufferType2 == BufferType.SPANNABLE || this.mMovement != null) {
            charSequenceNewSpannable = this.mSpannableFactory.newSpannable(charSequence3);
        } else {
            boolean z3 = charSequence3 instanceof CharWrapper;
            charSequenceNewSpannable = charSequence3;
            if (!z3) {
                charSequenceNewSpannable = TextUtils.stringOrSpannedString(charSequence3);
            }
        }
        CharSequence charSequence5 = charSequenceNewSpannable;
        if (this.mAutoLinkMask != 0) {
            if (bufferType2 == BufferType.EDITABLE || (charSequenceNewSpannable instanceof Spannable)) {
                spannableNewSpannable = (Spannable) charSequenceNewSpannable;
            } else {
                spannableNewSpannable = this.mSpannableFactory.newSpannable(charSequenceNewSpannable);
            }
            charSequence5 = charSequenceNewSpannable;
            if (Linkify.addLinks(spannableNewSpannable, this.mAutoLinkMask)) {
                bufferType2 = bufferType2 == BufferType.EDITABLE ? BufferType.EDITABLE : BufferType.SPANNABLE;
                setTextInternal(spannableNewSpannable);
                if (this.mLinksClickable && !textCanBeSelected()) {
                    setMovementMethod(LinkMovementMethod.getInstance());
                }
                charSequence5 = spannableNewSpannable;
            }
        }
        this.mBufferType = bufferType2;
        setTextInternal(charSequence5);
        TransformationMethod transformationMethod = this.mTransformation;
        if (transformationMethod == null) {
            this.mTransformed = charSequence5;
        } else {
            this.mTransformed = transformationMethod.getTransformation(charSequence5, this);
        }
        if (this.mTransformed == null) {
            this.mTransformed = "";
        }
        int length3 = charSequence5.length();
        if ((charSequence5 instanceof Spannable) && !this.mAllowTransformationLengthChange) {
            Spannable spannable = (Spannable) charSequence5;
            for (ChangeWatcher changeWatcher : (ChangeWatcher[]) spannable.getSpans(0, spannable.length(), ChangeWatcher.class)) {
                spannable.removeSpan(changeWatcher);
            }
            if (this.mChangeWatcher == null) {
                this.mChangeWatcher = new ChangeWatcher();
            }
            spannable.setSpan(this.mChangeWatcher, 0, length3, 6553618);
            Editor editor = this.mEditor;
            if (editor != null) {
                editor.addSpanWatchers(spannable);
            }
            TransformationMethod transformationMethod2 = this.mTransformation;
            if (transformationMethod2 != null) {
                spannable.setSpan(transformationMethod2, 0, length3, 18);
            }
            MovementMethod movementMethod = this.mMovement;
            if (movementMethod != null) {
                movementMethod.initialize(this, spannable);
                Editor editor2 = this.mEditor;
                if (editor2 != null) {
                    editor2.mSelectionMoved = false;
                }
            }
        }
        if (this.mLayout != null) {
            checkForRelayout();
        }
        sendOnTextChanged(charSequence5, 0, length, length3);
        onTextChanged(charSequence5, 0, length, length3);
        notifyViewAccessibilityStateChangedIfNeeded(2);
        if (z2) {
            sendAfterTextChanged((Editable) charSequence5);
        } else {
            notifyListeningManagersAfterTextChanged();
        }
        Editor editor3 = this.mEditor;
        if (editor3 != null) {
            editor3.prepareCursorControllers();
        }
    }

    public final void setText(char[] cArr, int i, int i2) {
        int length;
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException(i + ", " + i2);
        }
        CharSequence charSequence = this.mText;
        if (charSequence != null) {
            length = charSequence.length();
            sendBeforeTextChanged(this.mText, 0, length, i2);
        } else {
            sendBeforeTextChanged("", 0, 0, i2);
            length = 0;
        }
        CharWrapper charWrapper = this.mCharWrapper;
        if (charWrapper == null) {
            this.mCharWrapper = new CharWrapper(cArr, i, i2);
        } else {
            charWrapper.set(cArr, i, i2);
        }
        setText(this.mCharWrapper, this.mBufferType, false, length);
    }

    public final void setTextKeepState(CharSequence charSequence, BufferType bufferType) {
        Spannable spannable;
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        int length = charSequence.length();
        setText(charSequence, bufferType);
        if ((selectionStart >= 0 || selectionEnd >= 0) && (spannable = this.mSpannable) != null) {
            Selection.setSelection(spannable, Math.max(0, Math.min(selectionStart, length)), Math.max(0, Math.min(selectionEnd, length)));
        }
    }

    @RemotableViewMethod
    public final void setText(int i) {
        setText(getContext().getResources().getText(i));
        this.mTextSetFromXmlOrResourceId = true;
        this.mTextId = i;
    }

    public final void setText(int i, BufferType bufferType) {
        setText(getContext().getResources().getText(i), bufferType);
        this.mTextSetFromXmlOrResourceId = true;
        this.mTextId = i;
    }

    @RemotableViewMethod
    public final void setHint(CharSequence charSequence) {
        setHintInternal(charSequence);
        if (this.mEditor == null || !isInputMethodTarget()) {
            return;
        }
        this.mEditor.reportExtractedText();
    }

    private void setHintInternal(CharSequence charSequence) {
        this.mHint = TextUtils.stringOrSpannedString(charSequence);
        if (this.mLayout != null) {
            checkForRelayout();
        }
        if (this.mText.length() == 0) {
            invalidate();
        }
        if (this.mEditor == null || this.mText.length() != 0 || this.mHint == null) {
            return;
        }
        this.mEditor.invalidateTextDisplayList();
    }

    @RemotableViewMethod
    public final void setHint(int i) {
        this.mHintId = i;
        setHint(getContext().getResources().getText(i));
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getHint() {
        return this.mHint;
    }

    public boolean isSingleLine() {
        return this.mSingleLine;
    }

    CharSequence removeSuggestionSpans(CharSequence charSequence) {
        Spannable spannableNewSpannable;
        if (!(charSequence instanceof Spanned)) {
            return charSequence;
        }
        if (charSequence instanceof Spannable) {
            spannableNewSpannable = (Spannable) charSequence;
        } else {
            spannableNewSpannable = this.mSpannableFactory.newSpannable(charSequence);
        }
        SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) spannableNewSpannable.getSpans(0, charSequence.length(), SuggestionSpan.class);
        if (suggestionSpanArr.length == 0) {
            return charSequence;
        }
        for (SuggestionSpan suggestionSpan : suggestionSpanArr) {
            spannableNewSpannable.removeSpan(suggestionSpan);
        }
        return spannableNewSpannable;
    }

    public void setInputType(int i) {
        boolean zIsPasswordInputType = isPasswordInputType(getInputType());
        boolean zIsVisiblePasswordInputType = isVisiblePasswordInputType(getInputType());
        setInputType(i, false);
        boolean zIsPasswordInputType2 = isPasswordInputType(i);
        boolean zIsVisiblePasswordInputType2 = isVisiblePasswordInputType(i);
        if (zIsPasswordInputType2) {
            setTransformationMethod(PasswordTransformationMethod.getInstance());
            setTypefaceFromAttrs(null, null, 3, 0, -1);
        } else if (zIsVisiblePasswordInputType2) {
            z = this.mTransformation == PasswordTransformationMethod.getInstance();
            setTypefaceFromAttrs(null, null, 3, 0, -1);
        } else if (zIsPasswordInputType || zIsVisiblePasswordInputType) {
            setTypefaceFromAttrs(null, null, -1, 0, -1);
            if (this.mTransformation == PasswordTransformationMethod.getInstance()) {
                z = true;
            }
        }
        boolean z = !isMultilineInputType(i);
        if (this.mSingleLine != z || z) {
            applySingleLine(z, !zIsPasswordInputType2, true, true);
        }
        if (!isSuggestionsEnabled()) {
            setTextInternal(removeSuggestionSpans(this.mText));
        }
        InputMethodManager inputMethodManager = getInputMethodManager();
        if (inputMethodManager != null) {
            inputMethodManager.restartInput(this);
        }
    }

    boolean hasPasswordTransformationMethod() {
        return this.mTransformation instanceof PasswordTransformationMethod;
    }

    public boolean isAnyPasswordInputType() {
        int inputType = getInputType();
        return isPasswordInputType(inputType) || isVisiblePasswordInputType(inputType);
    }

    public void setRawInputType(int i) {
        if (i == 0 && this.mEditor == null) {
            return;
        }
        createEditorIfNeeded();
        this.mEditor.mInputType = i;
    }

    private Locale getCustomLocaleForKeyListenerOrNull() {
        LocaleList imeHintLocales;
        if (this.mUseInternationalizedInput && (imeHintLocales = getImeHintLocales()) != null) {
            return imeHintLocales.get(0);
        }
        return null;
    }

    private void setInputType(int i, boolean z) {
        KeyListener textKeyListener;
        TextKeyListener.Capitalize capitalize;
        int i2 = i & 15;
        if (i2 == 1) {
            boolean z2 = (32768 & i) != 0;
            if ((i & 4096) != 0) {
                capitalize = TextKeyListener.Capitalize.CHARACTERS;
            } else if ((i & 8192) != 0) {
                capitalize = TextKeyListener.Capitalize.WORDS;
            } else if ((i & 16384) != 0) {
                capitalize = TextKeyListener.Capitalize.SENTENCES;
            } else {
                capitalize = TextKeyListener.Capitalize.NONE;
            }
            textKeyListener = TextKeyListener.getInstance(z2, capitalize);
        } else if (i2 == 2) {
            Locale customLocaleForKeyListenerOrNull = getCustomLocaleForKeyListenerOrNull();
            DigitsKeyListener digitsKeyListener = DigitsKeyListener.getInstance(customLocaleForKeyListenerOrNull, (i & 4096) != 0, (i & 8192) != 0);
            if (customLocaleForKeyListenerOrNull != null) {
                int inputType = digitsKeyListener.getInputType();
                if ((inputType & 15) != 2) {
                    if ((i & 16) != 0) {
                        inputType |= 128;
                    }
                    i = inputType;
                }
            }
            textKeyListener = digitsKeyListener;
        } else if (i2 == 4) {
            Locale customLocaleForKeyListenerOrNull2 = getCustomLocaleForKeyListenerOrNull();
            int i3 = i & 4080;
            if (i3 == 16) {
                textKeyListener = DateKeyListener.getInstance(customLocaleForKeyListenerOrNull2);
            } else if (i3 == 32) {
                textKeyListener = TimeKeyListener.getInstance(customLocaleForKeyListenerOrNull2);
            } else {
                textKeyListener = DateTimeKeyListener.getInstance(customLocaleForKeyListenerOrNull2);
            }
            if (this.mUseInternationalizedInput) {
                i = textKeyListener.getInputType();
            }
        } else if (i2 == 3) {
            textKeyListener = DialerKeyListener.getInstance();
        } else {
            textKeyListener = TextKeyListener.getInstance();
        }
        setRawInputType(i);
        this.mListenerChanged = false;
        if (z) {
            createEditorIfNeeded();
            this.mEditor.mKeyListener = textKeyListener;
        } else {
            setKeyListenerOnly(textKeyListener);
        }
    }

    public int getInputType() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return 0;
        }
        return editor.mInputType;
    }

    public void setImeOptions(int i) {
        createEditorIfNeeded();
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.imeOptions = i;
    }

    public int getImeOptions() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mInputContentType == null) {
            return 0;
        }
        return this.mEditor.mInputContentType.imeOptions;
    }

    public void setImeActionLabel(CharSequence charSequence, int i) {
        createEditorIfNeeded();
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.imeActionLabel = charSequence;
        this.mEditor.mInputContentType.imeActionId = i;
    }

    public CharSequence getImeActionLabel() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mInputContentType == null) {
            return null;
        }
        return this.mEditor.mInputContentType.imeActionLabel;
    }

    public int getImeActionId() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mInputContentType == null) {
            return 0;
        }
        return this.mEditor.mInputContentType.imeActionId;
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        createEditorIfNeeded();
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.onEditorActionListener = onEditorActionListener;
    }

    public void onEditorAction(int i) {
        Editor editor = this.mEditor;
        Editor.InputContentType inputContentType = editor == null ? null : editor.mInputContentType;
        if (inputContentType != null) {
            if (inputContentType.onEditorActionListener != null && inputContentType.onEditorActionListener.onEditorAction(this, i, null)) {
                return;
            }
            if (i == 5) {
                View viewFocusSearch = focusSearch(2);
                if (viewFocusSearch != null && !viewFocusSearch.requestFocus(2)) {
                    throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                }
                return;
            }
            if (i == 7) {
                View viewFocusSearch2 = focusSearch(1);
                if (viewFocusSearch2 != null && !viewFocusSearch2.requestFocus(1)) {
                    throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                }
                return;
            }
            if (i == 6) {
                InputMethodManager inputMethodManager = getInputMethodManager();
                if (inputMethodManager == null || !inputMethodManager.isActive(this)) {
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                return;
            }
        }
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl != null) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            viewRootImpl.dispatchKeyFromIme(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, 66, 0, 0, -1, 0, 22));
            viewRootImpl.dispatchKeyFromIme(new KeyEvent(SystemClock.uptimeMillis(), jUptimeMillis, 1, 66, 0, 0, -1, 0, 22));
        }
    }

    public void setPrivateImeOptions(String str) {
        createEditorIfNeeded();
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.privateImeOptions = str;
    }

    public String getPrivateImeOptions() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mInputContentType == null) {
            return null;
        }
        return this.mEditor.mInputContentType.privateImeOptions;
    }

    public void setInputExtras(int i) throws XmlPullParserException, IOException {
        createEditorIfNeeded();
        XmlResourceParser xml = getResources().getXml(i);
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.extras = new Bundle();
        getResources().parseBundleExtras(xml, this.mEditor.mInputContentType.extras);
    }

    public Bundle getInputExtras(boolean z) {
        if (this.mEditor == null && !z) {
            return null;
        }
        createEditorIfNeeded();
        if (this.mEditor.mInputContentType == null) {
            if (!z) {
                return null;
            }
            this.mEditor.createInputContentTypeIfNeeded();
        }
        if (this.mEditor.mInputContentType.extras == null) {
            if (!z) {
                return null;
            }
            this.mEditor.mInputContentType.extras = new Bundle();
        }
        return this.mEditor.mInputContentType.extras;
    }

    public void setImeHintLocales(LocaleList localeList) {
        createEditorIfNeeded();
        this.mEditor.createInputContentTypeIfNeeded();
        this.mEditor.mInputContentType.imeHintLocales = localeList;
        if (this.mUseInternationalizedInput) {
            changeListenerLocaleTo(localeList == null ? null : localeList.get(0));
        }
    }

    public LocaleList getImeHintLocales() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mInputContentType == null) {
            return null;
        }
        return this.mEditor.mInputContentType.imeHintLocales;
    }

    public CharSequence getError() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return null;
        }
        return editor.mError;
    }

    @RemotableViewMethod
    public void setError(CharSequence charSequence) {
        if (charSequence == null) {
            setError(null, null);
            return;
        }
        Drawable drawable = getContext().getDrawable(com.android.internal.R.drawable.indicator_input_error);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        setError(charSequence, drawable);
    }

    public void setError(CharSequence charSequence, Drawable drawable) {
        createEditorIfNeeded();
        this.mEditor.setError(charSequence, drawable);
        notifyViewAccessibilityStateChangedIfNeeded(0);
    }

    @Override // android.view.View
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.setFrame();
        }
        restartMarqueeIfNeeded();
        return frame;
    }

    private void restartMarqueeIfNeeded() {
        if (this.mRestartMarquee && this.mEllipsize == TextUtils.TruncateAt.MARQUEE) {
            this.mRestartMarquee = false;
            startMarquee();
        }
    }

    public void setFilters(InputFilter[] inputFilterArr) {
        if (inputFilterArr == null) {
            throw new IllegalArgumentException();
        }
        this.mFilters = inputFilterArr;
        CharSequence charSequence = this.mText;
        if (charSequence instanceof Editable) {
            setFilters((Editable) charSequence, inputFilterArr);
        }
    }

    private void setFilters(Editable editable, InputFilter[] inputFilterArr) {
        Editor editor = this.mEditor;
        if (editor != null) {
            int i = 1;
            boolean z = editor.mUndoInputFilter != null;
            boolean z2 = this.mEditor.mKeyListener instanceof InputFilter;
            int i2 = z ? 1 : 0;
            if (z2) {
                i2++;
            }
            if (i2 > 0) {
                InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + i2];
                System.arraycopy(inputFilterArr, 0, inputFilterArr2, 0, inputFilterArr.length);
                if (z) {
                    inputFilterArr2[inputFilterArr.length] = this.mEditor.mUndoInputFilter;
                } else {
                    i = 0;
                }
                if (z2) {
                    inputFilterArr2[inputFilterArr.length + i] = (InputFilter) this.mEditor.mKeyListener;
                }
                editable.setFilters(inputFilterArr2);
                return;
            }
        }
        editable.setFilters(inputFilterArr);
    }

    public InputFilter[] getFilters() {
        return this.mFilters;
    }

    private int getBoxHeight(Layout layout) {
        int extendedPaddingTop;
        int extendedPaddingBottom;
        Insets opticalInsets = isLayoutModeOptical(this.mParent) ? getOpticalInsets() : Insets.NONE;
        if (layout == this.mHintLayout) {
            extendedPaddingTop = getCompoundPaddingTop();
            extendedPaddingBottom = getCompoundPaddingBottom();
        } else {
            extendedPaddingTop = getExtendedPaddingTop();
            extendedPaddingBottom = getExtendedPaddingBottom();
        }
        return (getMeasuredHeight() - (extendedPaddingTop + extendedPaddingBottom)) + opticalInsets.top + opticalInsets.bottom;
    }

    int getVerticalOffset(boolean z) {
        int boxHeight;
        int height;
        Layout layout;
        int i = this.mGravity & 112;
        Layout layout2 = this.mLayout;
        if (!z && this.mText.length() == 0 && (layout = this.mHintLayout) != null) {
            layout2 = layout;
        }
        if (i == 48 || (height = layout2.getHeight()) >= (boxHeight = getBoxHeight(layout2))) {
            return 0;
        }
        return i == 80 ? boxHeight - height : (boxHeight - height) >> 1;
    }

    private int getBottomVerticalOffset(boolean z) {
        int boxHeight;
        int height;
        Layout layout;
        int i = this.mGravity & 112;
        Layout layout2 = this.mLayout;
        if (!z && this.mText.length() == 0 && (layout = this.mHintLayout) != null) {
            layout2 = layout;
        }
        if (i == 80 || (height = layout2.getHeight()) >= (boxHeight = getBoxHeight(layout2))) {
            return 0;
        }
        return i == 48 ? boxHeight - height : (boxHeight - height) >> 1;
    }

    void invalidateCursorPath() {
        if (this.mHighlightPathBogus) {
            invalidateCursor();
            return;
        }
        int compoundPaddingLeft = getCompoundPaddingLeft();
        int extendedPaddingTop = getExtendedPaddingTop() + getVerticalOffset(true);
        if (this.mEditor.mDrawableForCursor == null) {
            RectF rectF = TEMP_RECTF;
            synchronized (rectF) {
                float fCeil = (float) Math.ceil(this.mTextPaint.getStrokeWidth());
                if (fCeil < 1.0f) {
                    fCeil = 1.0f;
                }
                float f = fCeil / 2.0f;
                this.mHighlightPath.computeBounds(rectF, false);
                float f2 = compoundPaddingLeft;
                float f3 = extendedPaddingTop;
                invalidate((int) Math.floor((rectF.left + f2) - f), (int) Math.floor((rectF.top + f3) - f), (int) Math.ceil(f2 + rectF.right + f), (int) Math.ceil(f3 + rectF.bottom + f));
            }
            return;
        }
        Rect bounds = this.mEditor.mDrawableForCursor.getBounds();
        invalidate(bounds.left + compoundPaddingLeft, bounds.top + extendedPaddingTop, bounds.right + compoundPaddingLeft, bounds.bottom + extendedPaddingTop);
    }

    void invalidateCursor() {
        int selectionEnd = getSelectionEnd();
        invalidateCursor(selectionEnd, selectionEnd, selectionEnd);
    }

    private void invalidateCursor(int i, int i2, int i3) {
        if (i >= 0 || i2 >= 0 || i3 >= 0) {
            invalidateRegion(Math.min(Math.min(i, i2), i3), Math.max(Math.max(i, i2), i3), true);
        }
    }

    void invalidateRegion(int i, int i2, boolean z) {
        int width;
        Editor editor;
        Layout layout = this.mLayout;
        if (layout == null) {
            invalidate();
            return;
        }
        int lineForOffset = layout.getLineForOffset(i);
        int lineTop = this.mLayout.getLineTop(lineForOffset);
        if (lineForOffset > 0) {
            lineTop -= this.mLayout.getLineDescent(lineForOffset - 1);
        }
        int lineForOffset2 = i == i2 ? lineForOffset : this.mLayout.getLineForOffset(i2);
        int lineBottom = this.mLayout.getLineBottom(lineForOffset2);
        if (z && (editor = this.mEditor) != null && editor.mDrawableForCursor != null) {
            Rect bounds = this.mEditor.mDrawableForCursor.getBounds();
            lineTop = Math.min(lineTop, bounds.top);
            lineBottom = Math.max(lineBottom, bounds.bottom);
        }
        int compoundPaddingLeft = getCompoundPaddingLeft();
        int extendedPaddingTop = getExtendedPaddingTop() + getVerticalOffset(true);
        if (lineForOffset == lineForOffset2 && !z) {
            int primaryHorizontal = (int) this.mLayout.getPrimaryHorizontal(i);
            width = ((int) (((double) this.mLayout.getPrimaryHorizontal(i2)) + 1.0d)) + compoundPaddingLeft;
            compoundPaddingLeft = primaryHorizontal + compoundPaddingLeft;
        } else {
            width = getWidth() - getCompoundPaddingRight();
        }
        invalidate(this.mScrollX + compoundPaddingLeft, lineTop + extendedPaddingTop, this.mScrollX + width, extendedPaddingTop + lineBottom);
    }

    private void registerForPreDraw() {
        if (this.mPreDrawRegistered) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(this);
        this.mPreDrawRegistered = true;
    }

    private void unregisterForPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        this.mPreDrawRegistered = false;
        this.mPreDrawListenerDetached = false;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (this.mLayout == null) {
            assumeLayout();
        }
        if (this.mMovement != null) {
            int selectionEnd = getSelectionEnd();
            Editor editor = this.mEditor;
            if (editor != null && editor.mSelectionModifierCursorController != null && this.mEditor.mSelectionModifierCursorController.isSelectionStartDragged()) {
                selectionEnd = getSelectionStart();
            }
            if (selectionEnd < 0 && (this.mGravity & 112) == 80) {
                selectionEnd = this.mText.length();
            }
            if (selectionEnd >= 0) {
                bringPointIntoView(selectionEnd);
            }
        } else {
            bringTextIntoView();
        }
        Editor editor2 = this.mEditor;
        if (editor2 != null && editor2.mCreatedWithASelection) {
            this.mEditor.refreshTextActionMode();
            this.mEditor.mCreatedWithASelection = false;
        }
        unregisterForPreDraw();
        return true;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onAttachedToWindow();
        }
        if (this.mPreDrawListenerDetached) {
            getViewTreeObserver().addOnPreDrawListener(this);
            this.mPreDrawListenerDetached = false;
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindowInternal() {
        if (this.mPreDrawRegistered) {
            getViewTreeObserver().removeOnPreDrawListener(this);
            this.mPreDrawListenerDetached = true;
        }
        resetResolvedDrawables();
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onDetachedFromWindow();
        }
        super.onDetachedFromWindowInternal();
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onScreenStateChanged(i);
        }
    }

    @Override // android.view.View
    protected boolean isPaddingOffsetRequired() {
        return (this.mShadowRadius == 0.0f && this.mDrawables == null) ? false : true;
    }

    @Override // android.view.View
    protected int getLeftPaddingOffset() {
        return (getCompoundPaddingLeft() - this.mPaddingLeft) + ((int) Math.min(0.0f, this.mShadowDx - this.mShadowRadius));
    }

    @Override // android.view.View
    protected int getTopPaddingOffset() {
        return (int) Math.min(0.0f, this.mShadowDy - this.mShadowRadius);
    }

    @Override // android.view.View
    protected int getBottomPaddingOffset() {
        return (int) Math.max(0.0f, this.mShadowDy + this.mShadowRadius);
    }

    @Override // android.view.View
    protected int getRightPaddingOffset() {
        return (-(getCompoundPaddingRight() - this.mPaddingRight)) + ((int) Math.max(0.0f, this.mShadowDx + this.mShadowRadius));
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        Drawables drawables;
        boolean zVerifyDrawable = super.verifyDrawable(drawable);
        if (!zVerifyDrawable && (drawables = this.mDrawables) != null) {
            for (Drawable drawable2 : drawables.mShowing) {
                if (drawable == drawable2) {
                    return true;
                }
            }
        }
        return zVerifyDrawable;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            for (Drawable drawable : drawables.mShowing) {
                if (drawable != null) {
                    drawable.jumpToCurrentState();
                }
            }
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        int i;
        int compoundPaddingTop;
        int i2;
        boolean z = false;
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int compoundPaddingRight = this.mScrollX;
            int i3 = this.mScrollY;
            Drawables drawables = this.mDrawables;
            if (drawables != null) {
                if (drawable == drawables.mShowing[0]) {
                    compoundPaddingTop = getCompoundPaddingTop();
                    int compoundPaddingBottom = ((this.mBottom - this.mTop) - getCompoundPaddingBottom()) - compoundPaddingTop;
                    compoundPaddingRight += this.mPaddingLeft;
                    i2 = (compoundPaddingBottom - drawables.mDrawableHeightLeft) / 2;
                } else if (drawable == drawables.mShowing[2]) {
                    compoundPaddingTop = getCompoundPaddingTop();
                    int compoundPaddingBottom2 = ((this.mBottom - this.mTop) - getCompoundPaddingBottom()) - compoundPaddingTop;
                    compoundPaddingRight += ((this.mRight - this.mLeft) - this.mPaddingRight) - drawables.mDrawableSizeRight;
                    i2 = (compoundPaddingBottom2 - drawables.mDrawableHeightRight) / 2;
                } else {
                    if (drawable == drawables.mShowing[1]) {
                        int compoundPaddingLeft = getCompoundPaddingLeft();
                        compoundPaddingRight += compoundPaddingLeft + (((((this.mRight - this.mLeft) - getCompoundPaddingRight()) - compoundPaddingLeft) - drawables.mDrawableWidthTop) / 2);
                        i = this.mPaddingTop;
                    } else if (drawable == drawables.mShowing[3]) {
                        int compoundPaddingLeft2 = getCompoundPaddingLeft();
                        compoundPaddingRight += compoundPaddingLeft2 + (((((this.mRight - this.mLeft) - getCompoundPaddingRight()) - compoundPaddingLeft2) - drawables.mDrawableWidthBottom) / 2);
                        i = ((this.mBottom - this.mTop) - this.mPaddingBottom) - drawables.mDrawableSizeBottom;
                    }
                    i3 += i;
                    z = true;
                }
                i = compoundPaddingTop + i2;
                i3 += i;
                z = true;
            }
            if (z) {
                invalidate(bounds.left + compoundPaddingRight, bounds.top + i3, bounds.right + compoundPaddingRight, bounds.bottom + i3);
            }
        }
        if (z) {
            return;
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return ((getBackground() == null || getBackground().getCurrent() == null) && this.mSpannable == null && !hasSelection() && !isHorizontalFadingEdgeEnabled() && this.mShadowColor == 0) ? false : true;
    }

    public boolean isTextSelectable() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return false;
        }
        return editor.mTextIsSelectable;
    }

    public void setTextIsSelectable(boolean z) {
        if (z || this.mEditor != null) {
            createEditorIfNeeded();
            if (this.mEditor.mTextIsSelectable == z) {
                return;
            }
            this.mEditor.mTextIsSelectable = z;
            setFocusableInTouchMode(z);
            setFocusable(16);
            setClickable(z);
            setLongClickable(z);
            setMovementMethod(z ? ArrowKeyMovementMethod.getInstance() : null);
            setText(this.mText, z ? BufferType.SPANNABLE : BufferType.NORMAL);
            this.mEditor.prepareCursorControllers();
        }
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState;
        if (this.mSingleLine) {
            iArrOnCreateDrawableState = super.onCreateDrawableState(i);
        } else {
            iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
            mergeDrawableStates(iArrOnCreateDrawableState, MULTILINE_STATE_SET);
        }
        if (isTextSelectable()) {
            int length = iArrOnCreateDrawableState.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (iArrOnCreateDrawableState[i2] == 16842919) {
                    int[] iArr = new int[length - 1];
                    System.arraycopy(iArrOnCreateDrawableState, 0, iArr, 0, i2);
                    System.arraycopy(iArrOnCreateDrawableState, i2 + 1, iArr, i2, (length - i2) - 1);
                    return iArr;
                }
            }
        }
        return iArrOnCreateDrawableState;
    }

    private Path getUpdatedHighlightPath() {
        Paint paint = this.mHighlightPaint;
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (this.mMovement != null && ((isFocused() || isPressed()) && selectionStart >= 0)) {
            if (selectionStart == selectionEnd) {
                Editor editor = this.mEditor;
                if (editor != null && editor.shouldRenderCursor()) {
                    if (this.mHighlightPathBogus) {
                        if (this.mHighlightPath == null) {
                            this.mHighlightPath = new Path();
                        }
                        this.mHighlightPath.reset();
                        this.mLayout.getCursorPath(selectionStart, this.mHighlightPath, this.mText);
                        this.mEditor.updateCursorPosition();
                        this.mHighlightPathBogus = false;
                    }
                    paint.setColor(this.mCurTextColor);
                    paint.setStyle(Paint.Style.STROKE);
                    return this.mHighlightPath;
                }
            } else {
                if (this.mHighlightPathBogus) {
                    if (this.mHighlightPath == null) {
                        this.mHighlightPath = new Path();
                    }
                    this.mHighlightPath.reset();
                    this.mLayout.getSelectionPath(selectionStart, selectionEnd, this.mHighlightPath);
                    this.mHighlightPathBogus = false;
                }
                paint.setColor(this.mHighlightColor);
                paint.setStyle(Paint.Style.FILL);
                return this.mHighlightPath;
            }
        }
        return null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int verticalOffset;
        int verticalOffset2;
        restartMarqueeIfNeeded();
        super.onDraw(canvas);
        int compoundPaddingLeft = getCompoundPaddingLeft();
        int compoundPaddingTop = getCompoundPaddingTop();
        int compoundPaddingRight = getCompoundPaddingRight();
        int compoundPaddingBottom = getCompoundPaddingBottom();
        int i = this.mScrollX;
        int i2 = this.mScrollY;
        int i3 = this.mRight;
        int i4 = this.mLeft;
        int i5 = this.mBottom;
        int i6 = this.mTop;
        boolean zIsLayoutRtl = isLayoutRtl();
        int horizontalOffsetForDrawables = getHorizontalOffsetForDrawables();
        int i7 = zIsLayoutRtl ? 0 : horizontalOffsetForDrawables;
        if (!zIsLayoutRtl) {
            horizontalOffsetForDrawables = 0;
        }
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            int i8 = ((i5 - i6) - compoundPaddingBottom) - compoundPaddingTop;
            int i9 = ((i3 - i4) - compoundPaddingRight) - compoundPaddingLeft;
            if (drawables.mShowing[0] != null) {
                canvas.save();
                canvas.translate(this.mPaddingLeft + i + i7, i2 + compoundPaddingTop + ((i8 - drawables.mDrawableHeightLeft) / 2));
                drawables.mShowing[0].draw(canvas);
                canvas.restore();
            }
            if (drawables.mShowing[2] != null) {
                canvas.save();
                canvas.translate(((((i + i3) - i4) - this.mPaddingRight) - drawables.mDrawableSizeRight) - horizontalOffsetForDrawables, i2 + compoundPaddingTop + ((i8 - drawables.mDrawableHeightRight) / 2));
                drawables.mShowing[2].draw(canvas);
                canvas.restore();
            }
            if (drawables.mShowing[1] != null) {
                canvas.save();
                canvas.translate(i + compoundPaddingLeft + ((i9 - drawables.mDrawableWidthTop) / 2), this.mPaddingTop + i2);
                drawables.mShowing[1].draw(canvas);
                canvas.restore();
            }
            if (drawables.mShowing[3] != null) {
                canvas.save();
                canvas.translate(i + compoundPaddingLeft + ((i9 - drawables.mDrawableWidthBottom) / 2), (((i2 + i5) - i6) - this.mPaddingBottom) - drawables.mDrawableSizeBottom);
                drawables.mShowing[3].draw(canvas);
                canvas.restore();
            }
        }
        int i10 = this.mCurTextColor;
        if (this.mLayout == null) {
            assumeLayout();
        }
        Layout layout = this.mLayout;
        if (this.mHint != null && this.mText.length() == 0) {
            if (this.mHintTextColor != null) {
                i10 = this.mCurHintTextColor;
            }
            layout = this.mHintLayout;
        }
        this.mTextPaint.setColor(i10);
        this.mTextPaint.drawableState = getDrawableState();
        canvas.save();
        int extendedPaddingTop = getExtendedPaddingTop();
        int extendedPaddingBottom = getExtendedPaddingBottom();
        int height = this.mLayout.getHeight() - (((this.mBottom - this.mTop) - compoundPaddingBottom) - compoundPaddingTop);
        float fMin = compoundPaddingLeft + i;
        float fMin2 = i2 == 0 ? 0.0f : extendedPaddingTop + i2;
        float compoundPaddingRight2 = ((i3 - i4) - getCompoundPaddingRight()) + i;
        int i11 = (i5 - i6) + i2;
        if (i2 == height) {
            extendedPaddingBottom = 0;
        }
        float fMax = i11 - extendedPaddingBottom;
        float f = this.mShadowRadius;
        if (f != 0.0f) {
            fMin += Math.min(0.0f, this.mShadowDx - f);
            compoundPaddingRight2 += Math.max(0.0f, this.mShadowDx + this.mShadowRadius);
            fMin2 += Math.min(0.0f, this.mShadowDy - this.mShadowRadius);
            fMax += Math.max(0.0f, this.mShadowDy + this.mShadowRadius);
        }
        canvas.clipRect(fMin, fMin2, compoundPaddingRight2, fMax);
        if ((this.mGravity & 112) != 48) {
            verticalOffset = getVerticalOffset(false);
            verticalOffset2 = getVerticalOffset(true);
        } else {
            verticalOffset = 0;
            verticalOffset2 = 0;
        }
        canvas.translate(compoundPaddingLeft, extendedPaddingTop + verticalOffset);
        int absoluteGravity = Gravity.getAbsoluteGravity(this.mGravity, getLayoutDirection());
        if (isMarqueeFadeEnabled()) {
            if (!this.mSingleLine && getLineCount() == 1 && canMarquee() && (absoluteGravity & 7) != 3) {
                canvas.translate(layout.getParagraphDirection(0) * (this.mLayout.getLineRight(0) - ((this.mRight - this.mLeft) - (getCompoundPaddingLeft() + getCompoundPaddingRight()))), 0.0f);
            }
            Marquee marquee = this.mMarquee;
            if (marquee != null && marquee.isRunning()) {
                canvas.translate(layout.getParagraphDirection(0) * (-this.mMarquee.getScroll()), 0.0f);
            }
        }
        int i12 = verticalOffset2 - verticalOffset;
        Path updatedHighlightPath = getUpdatedHighlightPath();
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onDraw(canvas, layout, updatedHighlightPath, this.mHighlightPaint, i12);
        } else {
            layout.draw(canvas, updatedHighlightPath, this.mHighlightPaint, i12);
        }
        Marquee marquee2 = this.mMarquee;
        if (marquee2 != null && marquee2.shouldDrawGhost()) {
            canvas.translate(layout.getParagraphDirection(0) * this.mMarquee.getGhostOffset(), 0.0f);
            layout.draw(canvas, updatedHighlightPath, this.mHighlightPaint, i12);
        }
        canvas.restore();
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.mLayout == null) {
            super.getFocusedRect(rect);
            return;
        }
        int selectionEnd = getSelectionEnd();
        if (selectionEnd < 0) {
            super.getFocusedRect(rect);
            return;
        }
        int selectionStart = getSelectionStart();
        if (selectionStart < 0 || selectionStart >= selectionEnd) {
            int lineForOffset = this.mLayout.getLineForOffset(selectionEnd);
            rect.top = this.mLayout.getLineTop(lineForOffset);
            rect.bottom = this.mLayout.getLineBottom(lineForOffset);
            rect.left = ((int) this.mLayout.getPrimaryHorizontal(selectionEnd)) - 2;
            rect.right = rect.left + 4;
        } else {
            int lineForOffset2 = this.mLayout.getLineForOffset(selectionStart);
            int lineForOffset3 = this.mLayout.getLineForOffset(selectionEnd);
            rect.top = this.mLayout.getLineTop(lineForOffset2);
            rect.bottom = this.mLayout.getLineBottom(lineForOffset3);
            if (lineForOffset2 == lineForOffset3) {
                rect.left = (int) this.mLayout.getPrimaryHorizontal(selectionStart);
                rect.right = (int) this.mLayout.getPrimaryHorizontal(selectionEnd);
            } else {
                if (this.mHighlightPathBogus) {
                    if (this.mHighlightPath == null) {
                        this.mHighlightPath = new Path();
                    }
                    this.mHighlightPath.reset();
                    this.mLayout.getSelectionPath(selectionStart, selectionEnd, this.mHighlightPath);
                    this.mHighlightPathBogus = false;
                }
                RectF rectF = TEMP_RECTF;
                synchronized (rectF) {
                    this.mHighlightPath.computeBounds(rectF, true);
                    rect.left = ((int) rectF.left) - 1;
                    rect.right = ((int) rectF.right) + 1;
                }
            }
        }
        int compoundPaddingLeft = getCompoundPaddingLeft();
        int extendedPaddingTop = getExtendedPaddingTop();
        if ((this.mGravity & 112) != 48) {
            extendedPaddingTop += getVerticalOffset(false);
        }
        rect.offset(compoundPaddingLeft, extendedPaddingTop);
        rect.bottom += getExtendedPaddingBottom();
    }

    public int getLineCount() {
        Layout layout = this.mLayout;
        if (layout != null) {
            return layout.getLineCount();
        }
        return 0;
    }

    public int getLineBounds(int i, Rect rect) {
        Layout layout = this.mLayout;
        if (layout == null) {
            if (rect != null) {
                rect.set(0, 0, 0, 0);
            }
            return 0;
        }
        int lineBounds = layout.getLineBounds(i, rect);
        int extendedPaddingTop = getExtendedPaddingTop();
        if ((this.mGravity & 112) != 48) {
            extendedPaddingTop += getVerticalOffset(true);
        }
        if (rect != null) {
            rect.offset(getCompoundPaddingLeft(), extendedPaddingTop);
        }
        return lineBounds + extendedPaddingTop;
    }

    @Override // android.view.View
    public int getBaseline() {
        if (this.mLayout == null) {
            return super.getBaseline();
        }
        return getBaselineOffset() + this.mLayout.getLineBaseline(0);
    }

    int getBaselineOffset() {
        int verticalOffset = (this.mGravity & 112) != 48 ? getVerticalOffset(true) : 0;
        if (isLayoutModeOptical(this.mParent)) {
            verticalOffset -= getOpticalInsets().top;
        }
        return getExtendedPaddingTop() + verticalOffset;
    }

    @Override // android.view.View
    protected int getFadeTop(boolean z) {
        if (this.mLayout == null) {
            return 0;
        }
        int verticalOffset = (this.mGravity & 112) != 48 ? getVerticalOffset(true) : 0;
        if (z) {
            verticalOffset += getTopPaddingOffset();
        }
        return getExtendedPaddingTop() + verticalOffset;
    }

    @Override // android.view.View
    protected int getFadeHeight(boolean z) {
        Layout layout = this.mLayout;
        if (layout != null) {
            return layout.getHeight();
        }
        return 0;
    }

    @Override // android.view.View
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (this.mSpannable != null && this.mLinksClickable) {
            int offsetForPosition = getOffsetForPosition(motionEvent.getX(i), motionEvent.getY(i));
            if (((ClickableSpan[]) this.mSpannable.getSpans(offsetForPosition, offsetForPosition, ClickableSpan.class)).length > 0) {
                return PointerIcon.getSystemIcon(this.mContext, 1002);
            }
        }
        if (isTextSelectable() || isTextEditable()) {
            return PointerIcon.getSystemIcon(this.mContext, 1008);
        }
        return super.onResolvePointerIcon(motionEvent, i);
    }

    @Override // android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4 && handleBackInTextActionModeIfNeeded(keyEvent)) {
            return true;
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public boolean handleBackInTextActionModeIfNeeded(KeyEvent keyEvent) {
        Editor editor = this.mEditor;
        if (editor != null && editor.getTextActionMode() != null) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    stopTextActionMode();
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (doKeyDown(i, keyEvent, null) == 0) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        KeyEvent keyEventChangeAction = KeyEvent.changeAction(keyEvent, 0);
        int iDoKeyDown = doKeyDown(i, keyEventChangeAction, keyEvent);
        if (iDoKeyDown == 0) {
            return super.onKeyMultiple(i, i2, keyEvent);
        }
        if (iDoKeyDown == -1) {
            return true;
        }
        int i3 = i2 - 1;
        KeyEvent keyEventChangeAction2 = KeyEvent.changeAction(keyEvent, 1);
        if (iDoKeyDown == 1) {
            this.mEditor.mKeyListener.onKeyUp(this, (Editable) this.mText, i, keyEventChangeAction2);
            while (true) {
                i3--;
                if (i3 <= 0) {
                    break;
                }
                this.mEditor.mKeyListener.onKeyDown(this, (Editable) this.mText, i, keyEventChangeAction);
                this.mEditor.mKeyListener.onKeyUp(this, (Editable) this.mText, i, keyEventChangeAction2);
            }
            hideErrorIfUnchanged();
        } else if (iDoKeyDown == 2) {
            this.mMovement.onKeyUp(this, this.mSpannable, i, keyEventChangeAction2);
            while (true) {
                i3--;
                if (i3 <= 0) {
                    break;
                }
                this.mMovement.onKeyDown(this, this.mSpannable, i, keyEventChangeAction);
                this.mMovement.onKeyUp(this, this.mSpannable, i, keyEventChangeAction2);
            }
        }
        return true;
    }

    private boolean shouldAdvanceFocusOnEnter() {
        int i;
        if (getKeyListener() == null) {
            return false;
        }
        if (this.mSingleLine) {
            return true;
        }
        Editor editor = this.mEditor;
        return editor != null && (editor.mInputType & 15) == 1 && ((i = this.mEditor.mInputType & 4080) == 32 || i == 48);
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int doKeyDown(int r8, android.view.KeyEvent r9, android.view.KeyEvent r10) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.doKeyDown(int, android.view.KeyEvent, android.view.KeyEvent):int");
    }

    public void resetErrorChangedFlag() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.mErrorWasChanged = false;
        }
    }

    public void hideErrorIfUnchanged() {
        Editor editor = this.mEditor;
        if (editor == null || editor.mError == null || this.mEditor.mErrorWasChanged) {
            return;
        }
        setError(null, null);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        InputMethodManager inputMethodManager;
        if (!isEnabled()) {
            return super.onKeyUp(i, keyEvent);
        }
        if (!KeyEvent.isModifierKey(i)) {
            this.mPreventDefaultMovement = false;
        }
        if (i == 23) {
            if (keyEvent.hasNoModifiers() && !hasOnClickListeners() && this.mMovement != null && (this.mText instanceof Editable) && this.mLayout != null && onCheckIsTextEditor()) {
                InputMethodManager inputMethodManager2 = getInputMethodManager();
                viewClicked(inputMethodManager2);
                if (inputMethodManager2 != null && getShowSoftInputOnFocus()) {
                    inputMethodManager2.showSoftInput(this, 0);
                }
            }
            return super.onKeyUp(i, keyEvent);
        }
        if ((i == 66 || i == 160) && keyEvent.hasNoModifiers()) {
            Editor editor = this.mEditor;
            if (editor != null && editor.mInputContentType != null && this.mEditor.mInputContentType.onEditorActionListener != null && this.mEditor.mInputContentType.enterDown) {
                this.mEditor.mInputContentType.enterDown = false;
                if (this.mEditor.mInputContentType.onEditorActionListener.onEditorAction(this, 0, keyEvent)) {
                    return true;
                }
            }
            if (((keyEvent.getFlags() & 16) != 0 || shouldAdvanceFocusOnEnter()) && !hasOnClickListeners()) {
                View viewFocusSearch = focusSearch(130);
                if (viewFocusSearch != null) {
                    if (!viewFocusSearch.requestFocus(130)) {
                        throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                    }
                    super.onKeyUp(i, keyEvent);
                    return true;
                }
                if ((keyEvent.getFlags() & 16) != 0 && (inputMethodManager = getInputMethodManager()) != null && inputMethodManager.isActive(this)) {
                    inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                }
            }
            return super.onKeyUp(i, keyEvent);
        }
        Editor editor2 = this.mEditor;
        if (editor2 != null && editor2.mKeyListener != null && this.mEditor.mKeyListener.onKeyUp(this, (Editable) this.mText, i, keyEvent)) {
            return true;
        }
        MovementMethod movementMethod = this.mMovement;
        if (movementMethod == null || this.mLayout == null || !movementMethod.onKeyUp(this, this.mSpannable, i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        Editor editor = this.mEditor;
        return (editor == null || editor.mInputType == 0) ? false : true;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (onCheckIsTextEditor() && isEnabled()) {
            this.mEditor.createInputMethodStateIfNeeded();
            editorInfo.inputType = getInputType();
            if (this.mEditor.mInputContentType != null) {
                editorInfo.imeOptions = this.mEditor.mInputContentType.imeOptions;
                editorInfo.privateImeOptions = this.mEditor.mInputContentType.privateImeOptions;
                editorInfo.actionLabel = this.mEditor.mInputContentType.imeActionLabel;
                editorInfo.actionId = this.mEditor.mInputContentType.imeActionId;
                editorInfo.extras = this.mEditor.mInputContentType.extras;
                editorInfo.hintLocales = this.mEditor.mInputContentType.imeHintLocales;
            } else {
                editorInfo.imeOptions = 0;
                editorInfo.hintLocales = null;
            }
            if (focusSearch(130) != null) {
                editorInfo.imeOptions |= 134217728;
            }
            if (focusSearch(33) != null) {
                editorInfo.imeOptions |= 67108864;
            }
            if ((editorInfo.imeOptions & 255) == 0) {
                if ((editorInfo.imeOptions & 134217728) != 0) {
                    editorInfo.imeOptions |= 5;
                } else {
                    editorInfo.imeOptions |= 6;
                }
                if (!shouldAdvanceFocusOnEnter()) {
                    editorInfo.imeOptions |= 1073741824;
                }
            }
            if (isMultilineInputType(editorInfo.inputType)) {
                editorInfo.imeOptions |= 1073741824;
            }
            editorInfo.hintText = this.mHint;
            editorInfo.targetInputMethodUser = this.mTextOperationUser;
            if (this.mText instanceof Editable) {
                EditableInputConnection editableInputConnection = new EditableInputConnection(this);
                editorInfo.initialSelStart = getSelectionStart();
                editorInfo.initialSelEnd = getSelectionEnd();
                editorInfo.initialCapsMode = editableInputConnection.getCursorCapsMode(getInputType());
                editorInfo.setInitialSurroundingText(this.mText);
                return editableInputConnection;
            }
        }
        return null;
    }

    public boolean extractText(ExtractedTextRequest extractedTextRequest, ExtractedText extractedText) {
        createEditorIfNeeded();
        return this.mEditor.extractText(extractedTextRequest, extractedText);
    }

    static void removeParcelableSpans(Spannable spannable, int i, int i2) {
        Object[] spans = spannable.getSpans(i, i2, ParcelableSpan.class);
        int length = spans.length;
        while (length > 0) {
            length--;
            spannable.removeSpan(spans[length]);
        }
    }

    public void setExtractedText(ExtractedText extractedText) {
        int i;
        Editable editableText = getEditableText();
        if (extractedText.text != null) {
            if (editableText == null) {
                setText(extractedText.text, BufferType.EDITABLE);
            } else {
                int length = editableText.length();
                if (extractedText.partialStartOffset >= 0) {
                    length = editableText.length();
                    int i2 = extractedText.partialStartOffset;
                    if (i2 > length) {
                        i2 = length;
                    }
                    int i3 = extractedText.partialEndOffset;
                    i = i2;
                    if (i3 <= length) {
                        length = i3;
                    }
                } else {
                    i = 0;
                }
                removeParcelableSpans(editableText, i, length);
                if (TextUtils.equals(editableText.subSequence(i, length), extractedText.text)) {
                    if (extractedText.text instanceof Spanned) {
                        TextUtils.copySpansFrom((Spanned) extractedText.text, 0, length - i, Object.class, editableText, i);
                    }
                } else {
                    editableText.replace(i, length, extractedText.text);
                }
            }
        }
        Spannable spannable = (Spannable) getText();
        int length2 = spannable.length();
        int i4 = extractedText.selectionStart;
        if (i4 < 0) {
            i4 = 0;
        } else if (i4 > length2) {
            i4 = length2;
        }
        int i5 = extractedText.selectionEnd;
        Selection.setSelection(spannable, i4, i5 >= 0 ? i5 > length2 ? length2 : i5 : 0);
        if ((extractedText.flags & 2) != 0) {
            MetaKeyKeyListener.startSelecting(this, spannable);
        } else {
            MetaKeyKeyListener.stopSelecting(this, spannable);
        }
        setHintInternal(extractedText.hint);
    }

    public void setExtracting(ExtractedTextRequest extractedTextRequest) {
        if (this.mEditor.mInputMethodState != null) {
            this.mEditor.mInputMethodState.mExtractedTextRequest = extractedTextRequest;
        }
        this.mEditor.hideCursorAndSpanControllers();
        stopTextActionMode();
        if (this.mEditor.mSelectionModifierCursorController != null) {
            this.mEditor.mSelectionModifierCursorController.resetTouchOffsets();
        }
    }

    public void onCommitCorrection(CorrectionInfo correctionInfo) {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onCommitCorrection(correctionInfo);
        }
    }

    public void beginBatchEdit() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.beginBatchEdit();
        }
    }

    public void endBatchEdit() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.endBatchEdit();
        }
    }

    public void nullLayouts() {
        Layout layout = this.mLayout;
        if ((layout instanceof BoringLayout) && this.mSavedLayout == null) {
            this.mSavedLayout = (BoringLayout) layout;
        }
        Layout layout2 = this.mHintLayout;
        if ((layout2 instanceof BoringLayout) && this.mSavedHintLayout == null) {
            this.mSavedHintLayout = (BoringLayout) layout2;
        }
        this.mHintLayout = null;
        this.mLayout = null;
        this.mSavedMarqueeModeLayout = null;
        this.mHintBoring = null;
        this.mBoring = null;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.prepareCursorControllers();
        }
    }

    private void assumeLayout() {
        int compoundPaddingLeft = ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        if (compoundPaddingLeft < 1) {
            compoundPaddingLeft = 0;
        }
        int i = compoundPaddingLeft;
        int i2 = this.mHorizontallyScrolling ? 1048576 : i;
        BoringLayout.Metrics metrics = UNKNOWN_BORING;
        makeNewLayout(i2, i, metrics, metrics, i, false);
    }

    private Layout.Alignment getLayoutAlignment() {
        switch (getTextAlignment()) {
            case 1:
                int i = this.mGravity & 8388615;
                if (i == 1) {
                    return Layout.Alignment.ALIGN_CENTER;
                }
                if (i == 3) {
                    return Layout.Alignment.ALIGN_LEFT;
                }
                if (i == 5) {
                    return Layout.Alignment.ALIGN_RIGHT;
                }
                if (i == 8388611) {
                    return Layout.Alignment.ALIGN_NORMAL;
                }
                if (i == 8388613) {
                    return Layout.Alignment.ALIGN_OPPOSITE;
                }
                return Layout.Alignment.ALIGN_NORMAL;
            case 2:
                return Layout.Alignment.ALIGN_NORMAL;
            case 3:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 4:
                return Layout.Alignment.ALIGN_CENTER;
            case 5:
                return getLayoutDirection() == 1 ? Layout.Alignment.ALIGN_RIGHT : Layout.Alignment.ALIGN_LEFT;
            case 6:
                return getLayoutDirection() == 1 ? Layout.Alignment.ALIGN_LEFT : Layout.Alignment.ALIGN_RIGHT;
            default:
                return Layout.Alignment.ALIGN_NORMAL;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x018d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void makeNewLayout(int r28, int r29, android.text.BoringLayout.Metrics r30, android.text.BoringLayout.Metrics r31, int r32, boolean r33) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.makeNewLayout(int, int, android.text.BoringLayout$Metrics, android.text.BoringLayout$Metrics, int, boolean):void");
    }

    public boolean useDynamicLayout() {
        return isTextSelectable() || (this.mSpannable != null && this.mPrecomputed == null);
    }

    protected Layout makeSingleLayout(int i, BoringLayout.Metrics metrics, int i2, Layout.Alignment alignment, boolean z, TextUtils.TruncateAt truncateAt, boolean z2) {
        BoringLayout.Metrics metrics2;
        BoringLayout boringLayout;
        BoringLayout boringLayoutMake;
        BoringLayout boringLayout2;
        Layout layoutMake = null;
        layoutMake = null;
        layoutMake = null;
        if (useDynamicLayout()) {
            layoutMake = DynamicLayout.Builder.obtain(this.mText, this.mTextPaint, i).setDisplayText(this.mTransformed).setAlignment(alignment).setTextDirection(this.mTextDir).setLineSpacing(this.mSpacingAdd, this.mSpacingMult).setIncludePad(this.mIncludePad).setUseLineSpacingFromFallbacks(this.mUseFallbackLineSpacing).setBreakStrategy(this.mBreakStrategy).setHyphenationFrequency(this.mHyphenationFrequency).setJustificationMode(this.mJustificationMode).setEllipsize(getKeyListener() == null ? truncateAt : null).setEllipsizedWidth(i2).build();
        } else {
            if (metrics == UNKNOWN_BORING) {
                BoringLayout.Metrics metricsIsBoring = BoringLayout.isBoring(this.mTransformed, this.mTextPaint, this.mTextDir, this.mBoring);
                if (metricsIsBoring != null) {
                    this.mBoring = metricsIsBoring;
                }
                metrics2 = metricsIsBoring;
            } else {
                metrics2 = metrics;
            }
            if (metrics2 != null) {
                if (metrics2.width <= i && (truncateAt == null || metrics2.width <= i2)) {
                    if (z2 && (boringLayout2 = this.mSavedLayout) != null) {
                        boringLayoutMake = boringLayout2.replaceOrMake(this.mTransformed, this.mTextPaint, i, alignment, this.mSpacingMult, this.mSpacingAdd, metrics2, this.mIncludePad);
                    } else {
                        boringLayoutMake = BoringLayout.make(this.mTransformed, this.mTextPaint, i, alignment, this.mSpacingMult, this.mSpacingAdd, metrics2, this.mIncludePad);
                    }
                    layoutMake = boringLayoutMake;
                    if (z2) {
                        this.mSavedLayout = (BoringLayout) layoutMake;
                    }
                } else if (z && metrics2.width <= i) {
                    if (z2 && (boringLayout = this.mSavedLayout) != null) {
                        layoutMake = boringLayout.replaceOrMake(this.mTransformed, this.mTextPaint, i, alignment, this.mSpacingMult, this.mSpacingAdd, metrics2, this.mIncludePad, truncateAt, i2);
                    } else {
                        layoutMake = BoringLayout.make(this.mTransformed, this.mTextPaint, i, alignment, this.mSpacingMult, this.mSpacingAdd, metrics2, this.mIncludePad, truncateAt, i2);
                    }
                }
            }
        }
        if (layoutMake != null) {
            return layoutMake;
        }
        CharSequence charSequence = this.mTransformed;
        StaticLayout.Builder maxLines = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.mTextPaint, i).setAlignment(alignment).setTextDirection(this.mTextDir).setLineSpacing(this.mSpacingAdd, this.mSpacingMult).setIncludePad(this.mIncludePad).setUseLineSpacingFromFallbacks(this.mUseFallbackLineSpacing).setBreakStrategy(this.mBreakStrategy).setHyphenationFrequency(this.mHyphenationFrequency).setJustificationMode(this.mJustificationMode).setMaxLines(this.mMaxMode == 1 ? this.mMaximum : Integer.MAX_VALUE);
        if (z) {
            maxLines.setEllipsize(truncateAt).setEllipsizedWidth(i2);
        }
        return maxLines.build();
    }

    private boolean compressText(float f) {
        if (!isHardwareAccelerated() && f > 0.0f && this.mLayout != null && getLineCount() == 1 && !this.mUserSetTextScaleX && this.mTextPaint.getTextScaleX() == 1.0f) {
            float lineWidth = ((this.mLayout.getLineWidth(0) + 1.0f) - f) / f;
            if (lineWidth > 0.0f && lineWidth <= 0.07f) {
                this.mTextPaint.setTextScaleX((1.0f - lineWidth) - 0.005f);
                post(new Runnable() { // from class: android.widget.TextView.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TextView.this.requestLayout();
                    }
                });
                return true;
            }
        }
        return false;
    }

    private static int desired(Layout layout) {
        int lineCount = layout.getLineCount();
        CharSequence text = layout.getText();
        for (int i = 0; i < lineCount - 1; i++) {
            if (text.charAt(layout.getLineEnd(i) - 1) != '\n') {
                return -1;
            }
        }
        float fMax = 0.0f;
        for (int i2 = 0; i2 < lineCount; i2++) {
            fMax = Math.max(fMax, layout.getLineWidth(i2));
        }
        return (int) Math.ceil(fMax);
    }

    public void setIncludeFontPadding(boolean z) {
        if (this.mIncludePad != z) {
            this.mIncludePad = z;
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public boolean getIncludeFontPadding() {
        return this.mIncludePad;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        BoringLayout.Metrics metricsIsBoring;
        boolean z;
        int i3;
        int iMax;
        int i4;
        int iMin;
        int iMax2;
        int iMin2;
        BoringLayout.Metrics metrics;
        BoringLayout.Metrics metrics2;
        BoringLayout.Metrics metricsIsBoring2;
        int i5;
        int i6;
        int i7;
        int i8;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        BoringLayout.Metrics metrics3 = UNKNOWN_BORING;
        if (this.mTextDir == null) {
            this.mTextDir = getTextDirectionHeuristic();
        }
        float f = mode == Integer.MIN_VALUE ? size : Float.MAX_VALUE;
        if (mode == 1073741824) {
            iMin2 = size;
            metrics = metrics3;
            metrics2 = metrics;
            i4 = 1;
            i3 = -1;
            z = false;
        } else {
            Layout layout = this.mLayout;
            int iDesired = (layout == null || this.mEllipsize != null) ? -1 : desired(layout);
            if (iDesired < 0) {
                metricsIsBoring = BoringLayout.isBoring(this.mTransformed, this.mTextPaint, this.mTextDir, this.mBoring);
                if (metricsIsBoring != null) {
                    this.mBoring = metricsIsBoring;
                }
                z = false;
            } else {
                metricsIsBoring = metrics3;
                z = true;
            }
            if (metricsIsBoring == null || metricsIsBoring == metrics3) {
                if (iDesired < 0) {
                    CharSequence charSequence = this.mTransformed;
                    iDesired = (int) Math.ceil(Layout.getDesiredWidthWithLimit(charSequence, 0, charSequence.length(), this.mTextPaint, this.mTextDir, f));
                }
                i3 = iDesired;
                iMax = i3;
            } else {
                iMax = metricsIsBoring.width;
                i3 = iDesired;
            }
            Drawables drawables = this.mDrawables;
            if (drawables != null) {
                iMax = Math.max(Math.max(iMax, drawables.mDrawableWidthTop), drawables.mDrawableWidthBottom);
            }
            int i9 = iMax;
            if (this.mHint != null) {
                Layout layout2 = this.mHintLayout;
                int iDesired2 = (layout2 == null || this.mEllipsize != null) ? -1 : desired(layout2);
                if (iDesired2 < 0) {
                    metricsIsBoring2 = BoringLayout.isBoring(this.mHint, this.mTextPaint, this.mTextDir, this.mHintBoring);
                    if (metricsIsBoring2 != null) {
                        this.mHintBoring = metricsIsBoring2;
                    }
                } else {
                    metricsIsBoring2 = metrics3;
                }
                if (metricsIsBoring2 == null || metricsIsBoring2 == metrics3) {
                    if (iDesired2 < 0) {
                        CharSequence charSequence2 = this.mHint;
                        i5 = i9;
                        iDesired2 = (int) Math.ceil(Layout.getDesiredWidthWithLimit(charSequence2, 0, charSequence2.length(), this.mTextPaint, this.mTextDir, f));
                    } else {
                        i5 = i9;
                    }
                    i9 = iDesired2;
                } else {
                    i5 = i9;
                    i9 = metricsIsBoring2.width;
                }
                if (i9 <= i5) {
                    i9 = i5;
                }
                metrics3 = metricsIsBoring2;
            }
            int compoundPaddingLeft = i9 + getCompoundPaddingLeft() + getCompoundPaddingRight();
            i4 = 1;
            if (this.mMaxWidthMode == 1) {
                iMin = Math.min(compoundPaddingLeft, this.mMaxWidth * getLineHeight());
            } else {
                iMin = Math.min(compoundPaddingLeft, this.mMaxWidth);
            }
            if (this.mMinWidthMode == 1) {
                iMax2 = Math.max(iMin, this.mMinWidth * getLineHeight());
            } else {
                iMax2 = Math.max(iMin, this.mMinWidth);
            }
            int iMax3 = Math.max(iMax2, getSuggestedMinimumWidth());
            iMin2 = mode == Integer.MIN_VALUE ? Math.min(size, iMax3) : iMax3;
            metrics = metricsIsBoring;
            metrics2 = metrics3;
        }
        int compoundPaddingLeft2 = (iMin2 - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        int i10 = this.mHorizontallyScrolling ? 1048576 : compoundPaddingLeft2;
        Layout layout3 = this.mHintLayout;
        int width = layout3 == null ? i10 : layout3.getWidth();
        Layout layout4 = this.mLayout;
        if (layout4 == null) {
            i7 = -1;
            i6 = i4;
            i8 = 1073741824;
            makeNewLayout(i10, i10, metrics, metrics2, (iMin2 - getCompoundPaddingLeft()) - getCompoundPaddingRight(), false);
        } else {
            i6 = i4;
            i7 = -1;
            i8 = 1073741824;
            int i11 = (layout4.getWidth() == i10 && width == i10 && this.mLayout.getEllipsizedWidth() == (iMin2 - getCompoundPaddingLeft()) - getCompoundPaddingRight()) ? 0 : i6;
            int i12 = (this.mHint != null || this.mEllipsize != null || i10 <= this.mLayout.getWidth() || (!(this.mLayout instanceof BoringLayout) && (!z || i3 < 0 || i3 > i10))) ? 0 : i6;
            int i13 = (this.mMaxMode == this.mOldMaxMode && this.mMaximum == this.mOldMaximum) ? 0 : i6;
            if (i11 != 0 || i13 != 0) {
                if (i13 == 0 && i12 != 0) {
                    this.mLayout.increaseWidthTo(i10);
                } else {
                    makeNewLayout(i10, i10, metrics, metrics2, (iMin2 - getCompoundPaddingLeft()) - getCompoundPaddingRight(), false);
                }
            }
        }
        if (mode2 == i8) {
            this.mDesiredHeightAtMeasure = i7;
        } else {
            int desiredHeight = getDesiredHeight();
            this.mDesiredHeightAtMeasure = desiredHeight;
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(desiredHeight, size2) : desiredHeight;
        }
        int compoundPaddingTop = (size2 - getCompoundPaddingTop()) - getCompoundPaddingBottom();
        if (this.mMaxMode == i6) {
            int lineCount = this.mLayout.getLineCount();
            int i14 = this.mMaximum;
            if (lineCount > i14) {
                compoundPaddingTop = Math.min(compoundPaddingTop, this.mLayout.getLineTop(i14));
            }
        }
        if (this.mMovement != null || this.mLayout.getWidth() > compoundPaddingLeft2 || this.mLayout.getHeight() > compoundPaddingTop) {
            registerForPreDraw();
        } else {
            scrollTo(0, 0);
        }
        setMeasuredDimension(iMin2, size2);
    }

    private void autoSizeText() {
        if (isAutoSizeEnabled()) {
            if (this.mNeedsAutoSizeText) {
                if (getMeasuredWidth() <= 0 || getMeasuredHeight() <= 0) {
                    return;
                }
                int measuredWidth = this.mHorizontallyScrolling ? 1048576 : (getMeasuredWidth() - getTotalPaddingLeft()) - getTotalPaddingRight();
                int measuredHeight = (getMeasuredHeight() - getExtendedPaddingBottom()) - getExtendedPaddingTop();
                if (measuredWidth <= 0 || measuredHeight <= 0) {
                    return;
                }
                RectF rectF = TEMP_RECTF;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = measuredHeight;
                    float fFindLargestTextSizeWhichFits = findLargestTextSizeWhichFits(rectF);
                    if (fFindLargestTextSizeWhichFits != getTextSize()) {
                        setTextSizeInternal(0, fFindLargestTextSizeWhichFits, false);
                        BoringLayout.Metrics metrics = UNKNOWN_BORING;
                        makeNewLayout(measuredWidth, 0, metrics, metrics, ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), false);
                    }
                }
            }
            this.mNeedsAutoSizeText = true;
        }
    }

    private int findLargestTextSizeWhichFits(RectF rectF) {
        int i;
        int length = this.mAutoSizeTextSizesInPx.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = 0;
        int i3 = 1;
        int i4 = length - 1;
        while (true) {
            int i5 = i3;
            int i6 = i2;
            i2 = i5;
            while (i2 <= i4) {
                i = (i2 + i4) / 2;
                if (suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[i], rectF)) {
                    break;
                }
                i6 = i - 1;
                i4 = i6;
            }
            return this.mAutoSizeTextSizesInPx[i6];
            i3 = i + 1;
        }
    }

    private boolean suggestedSizeFitsInSpace(int i, RectF rectF) {
        CharSequence text = this.mTransformed;
        if (text == null) {
            text = getText();
        }
        int maxLines = getMaxLines();
        TextPaint textPaint = this.mTempTextPaint;
        if (textPaint == null) {
            this.mTempTextPaint = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.mTempTextPaint.set(getPaint());
        this.mTempTextPaint.setTextSize(i);
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(text, 0, text.length(), this.mTempTextPaint, Math.round(rectF.right));
        builderObtain.setAlignment(getLayoutAlignment()).setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier()).setIncludePad(getIncludeFontPadding()).setUseLineSpacingFromFallbacks(this.mUseFallbackLineSpacing).setBreakStrategy(getBreakStrategy()).setHyphenationFrequency(getHyphenationFrequency()).setJustificationMode(getJustificationMode()).setMaxLines(this.mMaxMode == 1 ? this.mMaximum : Integer.MAX_VALUE).setTextDirection(getTextDirectionHeuristic());
        StaticLayout staticLayoutBuild = builderObtain.build();
        return (maxLines == -1 || staticLayoutBuild.getLineCount() <= maxLines) && ((float) staticLayoutBuild.getHeight()) <= rectF.bottom;
    }

    private int getDesiredHeight() {
        return Math.max(getDesiredHeight(this.mLayout, true), getDesiredHeight(this.mHintLayout, this.mEllipsize != null));
    }

    private int getDesiredHeight(Layout layout, boolean z) {
        int i;
        if (layout == null) {
            return 0;
        }
        int height = layout.getHeight(z);
        Drawables drawables = this.mDrawables;
        if (drawables != null) {
            height = Math.max(Math.max(height, drawables.mDrawableHeightLeft), drawables.mDrawableHeightRight);
        }
        int lineCount = layout.getLineCount();
        int compoundPaddingTop = getCompoundPaddingTop() + getCompoundPaddingBottom();
        int iMax = height + compoundPaddingTop;
        if (this.mMaxMode != 1) {
            iMax = Math.min(iMax, this.mMaximum);
        } else if (z && lineCount > (i = this.mMaximum) && ((layout instanceof DynamicLayout) || (layout instanceof BoringLayout))) {
            int lineTop = layout.getLineTop(i);
            if (drawables != null) {
                lineTop = Math.max(Math.max(lineTop, drawables.mDrawableHeightLeft), drawables.mDrawableHeightRight);
            }
            iMax = lineTop + compoundPaddingTop;
            lineCount = this.mMaximum;
        }
        if (this.mMinMode == 1) {
            if (lineCount < this.mMinimum) {
                iMax += getLineHeight() * (this.mMinimum - lineCount);
            }
        } else {
            iMax = Math.max(iMax, this.mMinimum);
        }
        return Math.max(iMax, getSuggestedMinimumHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0036 A[PHI: r2
  0x0036: PHI (r2v1 boolean) = (r2v0 boolean), (r2v2 boolean), (r2v2 boolean), (r2v2 boolean), (r2v2 boolean) binds: [B:3:0x0004, B:13:0x0027, B:15:0x002b, B:17:0x0033, B:10:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkForResize() {
        /*
            r4 = this;
            android.text.Layout r0 = r4.mLayout
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L36
            android.view.ViewGroup$LayoutParams r0 = r4.mLayoutParams
            int r0 = r0.width
            r3 = -2
            if (r0 != r3) goto L11
            r4.invalidate()
            r2 = r1
        L11:
            android.view.ViewGroup$LayoutParams r0 = r4.mLayoutParams
            int r0 = r0.height
            if (r0 != r3) goto L22
            int r0 = r4.getDesiredHeight()
            int r3 = r4.getHeight()
            if (r0 == r3) goto L36
            goto L37
        L22:
            android.view.ViewGroup$LayoutParams r0 = r4.mLayoutParams
            int r0 = r0.height
            r3 = -1
            if (r0 != r3) goto L36
            int r0 = r4.mDesiredHeightAtMeasure
            if (r0 < 0) goto L36
            int r0 = r4.getDesiredHeight()
            int r3 = r4.mDesiredHeightAtMeasure
            if (r0 == r3) goto L36
            goto L37
        L36:
            r1 = r2
        L37:
            if (r1 == 0) goto L3c
            r4.requestLayout()
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.checkForResize():void");
    }

    private void checkForRelayout() {
        Layout layout;
        if ((this.mLayoutParams.width != -2 || (this.mMaxWidthMode == this.mMinWidthMode && this.mMaxWidth == this.mMinWidth)) && ((this.mHint == null || this.mHintLayout != null) && ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight() > 0)) {
            int height = this.mLayout.getHeight();
            int width = this.mLayout.getWidth();
            Layout layout2 = this.mHintLayout;
            int width2 = layout2 == null ? 0 : layout2.getWidth();
            BoringLayout.Metrics metrics = UNKNOWN_BORING;
            makeNewLayout(width, width2, metrics, metrics, ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), false);
            if (this.mEllipsize != TextUtils.TruncateAt.MARQUEE) {
                if (this.mLayoutParams.height != -2 && this.mLayoutParams.height != -1) {
                    autoSizeText();
                    invalidate();
                    return;
                } else if (this.mLayout.getHeight() == height && ((layout = this.mHintLayout) == null || layout.getHeight() == height)) {
                    autoSizeText();
                    invalidate();
                    return;
                }
            }
            requestLayout();
            invalidate();
            return;
        }
        nullLayouts();
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = this.mDeferScroll;
        if (i5 >= 0) {
            this.mDeferScroll = -1;
            bringPointIntoView(Math.min(i5, this.mText.length()));
        }
        autoSizeText();
    }

    private boolean isShowingHint() {
        return TextUtils.isEmpty(this.mText) && !TextUtils.isEmpty(this.mHint);
    }

    private boolean bringTextIntoView() {
        int iFloor;
        int iCeil;
        Layout layout = isShowingHint() ? this.mHintLayout : this.mLayout;
        int lineCount = (this.mGravity & 112) == 80 ? layout.getLineCount() - 1 : 0;
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(lineCount);
        int paragraphDirection = layout.getParagraphDirection(lineCount);
        int compoundPaddingLeft = ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        int extendedPaddingTop = ((this.mBottom - this.mTop) - getExtendedPaddingTop()) - getExtendedPaddingBottom();
        int height = layout.getHeight();
        if (paragraphAlignment == Layout.Alignment.ALIGN_NORMAL) {
            paragraphAlignment = paragraphDirection == 1 ? Layout.Alignment.ALIGN_LEFT : Layout.Alignment.ALIGN_RIGHT;
        } else if (paragraphAlignment == Layout.Alignment.ALIGN_OPPOSITE) {
            paragraphAlignment = paragraphDirection == 1 ? Layout.Alignment.ALIGN_RIGHT : Layout.Alignment.ALIGN_LEFT;
        }
        if (paragraphAlignment == Layout.Alignment.ALIGN_CENTER) {
            iFloor = (int) Math.floor(layout.getLineLeft(lineCount));
            iCeil = (int) Math.ceil(layout.getLineRight(lineCount));
            if (iCeil - iFloor < compoundPaddingLeft) {
                iCeil = (iCeil + iFloor) / 2;
                compoundPaddingLeft /= 2;
            } else if (paragraphDirection < 0) {
            }
            iFloor = iCeil - compoundPaddingLeft;
        } else if (paragraphAlignment == Layout.Alignment.ALIGN_RIGHT) {
            iCeil = (int) Math.ceil(layout.getLineRight(lineCount));
            iFloor = iCeil - compoundPaddingLeft;
        } else {
            iFloor = (int) Math.floor(layout.getLineLeft(lineCount));
        }
        int i = (height >= extendedPaddingTop && (this.mGravity & 112) == 80) ? height - extendedPaddingTop : 0;
        if (iFloor == this.mScrollX && i == this.mScrollY) {
            return false;
        }
        scrollTo(iFloor, i);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean bringPointIntoView(int r17) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.bringPointIntoView(int):boolean");
    }

    /* JADX INFO: renamed from: android.widget.TextView$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            try {
                iArr[Layout.Alignment.ALIGN_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public boolean moveCursorToVisibleOffset() {
        int selectionStart;
        if (!(this.mText instanceof Spannable) || (selectionStart = getSelectionStart()) != getSelectionEnd()) {
            return false;
        }
        int lineForOffset = this.mLayout.getLineForOffset(selectionStart);
        int lineTop = this.mLayout.getLineTop(lineForOffset);
        int lineTop2 = this.mLayout.getLineTop(lineForOffset + 1);
        int extendedPaddingTop = ((this.mBottom - this.mTop) - getExtendedPaddingTop()) - getExtendedPaddingBottom();
        int i = lineTop2 - lineTop;
        int i2 = i / 2;
        int i3 = extendedPaddingTop / 4;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = this.mScrollY;
        int i5 = i4 + i2;
        if (lineTop < i5) {
            lineForOffset = this.mLayout.getLineForVertical(i5 + i);
        } else {
            int i6 = (extendedPaddingTop + i4) - i2;
            if (lineTop2 > i6) {
                lineForOffset = this.mLayout.getLineForVertical(i6 - i);
            }
        }
        int compoundPaddingLeft = ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        int offsetForHorizontal = this.mLayout.getOffsetForHorizontal(lineForOffset, this.mScrollX);
        int offsetForHorizontal2 = this.mLayout.getOffsetForHorizontal(lineForOffset, compoundPaddingLeft + r4);
        int i7 = offsetForHorizontal < offsetForHorizontal2 ? offsetForHorizontal : offsetForHorizontal2;
        if (offsetForHorizontal <= offsetForHorizontal2) {
            offsetForHorizontal = offsetForHorizontal2;
        }
        if (selectionStart >= i7) {
            i7 = selectionStart > offsetForHorizontal ? offsetForHorizontal : selectionStart;
        }
        if (i7 == selectionStart) {
            return false;
        }
        Selection.setSelection(this.mSpannable, i7);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mScroller;
        if (scroller == null || !scroller.computeScrollOffset()) {
            return;
        }
        this.mScrollX = this.mScroller.getCurrX();
        this.mScrollY = this.mScroller.getCurrY();
        invalidateParentCaches();
        postInvalidate();
    }

    private void getInterestingRect(Rect rect, int i) {
        convertFromViewportToContentCoordinates(rect);
        if (i == 0) {
            rect.top -= getExtendedPaddingTop();
        }
        if (i == this.mLayout.getLineCount() - 1) {
            rect.bottom += getExtendedPaddingBottom();
        }
    }

    private void convertFromViewportToContentCoordinates(Rect rect) {
        int iViewportToContentHorizontalOffset = viewportToContentHorizontalOffset();
        rect.left += iViewportToContentHorizontalOffset;
        rect.right += iViewportToContentHorizontalOffset;
        int iViewportToContentVerticalOffset = viewportToContentVerticalOffset();
        rect.top += iViewportToContentVerticalOffset;
        rect.bottom += iViewportToContentVerticalOffset;
    }

    int viewportToContentHorizontalOffset() {
        return getCompoundPaddingLeft() - this.mScrollX;
    }

    int viewportToContentVerticalOffset() {
        int extendedPaddingTop = getExtendedPaddingTop() - this.mScrollY;
        return (this.mGravity & 112) != 48 ? extendedPaddingTop + getVerticalOffset(false) : extendedPaddingTop;
    }

    @Override // android.view.View
    public void debug(int i) {
        String str;
        super.debug(i);
        String str2 = debugIndent(i) + "frame={" + this.mLeft + ", " + this.mTop + ", " + this.mRight + ", " + this.mBottom + "} scroll={" + this.mScrollX + ", " + this.mScrollY + "} ";
        if (this.mText != null) {
            str = str2 + "mText=\"" + ((Object) this.mText) + "\" ";
            if (this.mLayout != null) {
                str = str + "mLayout width=" + this.mLayout.getWidth() + " height=" + this.mLayout.getHeight();
            }
        } else {
            str = str2 + "mText=NULL";
        }
        Log.d("View", str);
    }

    @ViewDebug.ExportedProperty(category = "text")
    public int getSelectionStart() {
        return Selection.getSelectionStart(getText());
    }

    @ViewDebug.ExportedProperty(category = "text")
    public int getSelectionEnd() {
        return Selection.getSelectionEnd(getText());
    }

    public boolean hasSelection() {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        return selectionStart >= 0 && selectionEnd > 0 && selectionStart != selectionEnd;
    }

    String getSelectedText() {
        if (!hasSelection()) {
            return null;
        }
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        CharSequence charSequence = this.mText;
        return String.valueOf(selectionStart > selectionEnd ? charSequence.subSequence(selectionEnd, selectionStart) : charSequence.subSequence(selectionStart, selectionEnd));
    }

    public void setSingleLine() {
        setSingleLine(true);
    }

    public void setAllCaps(boolean z) {
        if (z) {
            setTransformationMethod(new AllCapsTransformationMethod(getContext()));
        } else {
            setTransformationMethod(null);
        }
    }

    public boolean isAllCaps() {
        TransformationMethod transformationMethod = getTransformationMethod();
        return transformationMethod != null && (transformationMethod instanceof AllCapsTransformationMethod);
    }

    @RemotableViewMethod
    public void setSingleLine(boolean z) {
        setInputTypeSingleLine(z);
        applySingleLine(z, true, true, true);
    }

    private void setInputTypeSingleLine(boolean z) {
        Editor editor = this.mEditor;
        if (editor == null || (editor.mInputType & 15) != 1) {
            return;
        }
        if (z) {
            this.mEditor.mInputType &= -131073;
        } else {
            this.mEditor.mInputType |= 131072;
        }
    }

    private void applySingleLine(boolean z, boolean z2, boolean z3, boolean z4) {
        this.mSingleLine = z;
        if (z) {
            setLines(1);
            setHorizontallyScrolling(true);
            if (z2) {
                setTransformationMethod(SingleLineTransformationMethod.getInstance());
            }
            if (z4 && this.mBufferType == BufferType.EDITABLE) {
                InputFilter[] filters = getFilters();
                for (InputFilter inputFilter : getFilters()) {
                    if (inputFilter instanceof InputFilter.LengthFilter) {
                        return;
                    }
                }
                if (this.mSingleLineLengthFilter == null) {
                    this.mSingleLineLengthFilter = new InputFilter.LengthFilter(5000);
                }
                InputFilter[] inputFilterArr = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                inputFilterArr[filters.length] = this.mSingleLineLengthFilter;
                setFilters(inputFilterArr);
                setText(getText());
                return;
            }
            return;
        }
        if (z3) {
            setMaxLines(Integer.MAX_VALUE);
        }
        setHorizontallyScrolling(false);
        if (z2) {
            setTransformationMethod(null);
        }
        if (z4 && this.mBufferType == BufferType.EDITABLE) {
            InputFilter[] filters2 = getFilters();
            if (filters2.length == 0 || this.mSingleLineLengthFilter == null) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= filters2.length) {
                    i = -1;
                    break;
                } else if (filters2[i] == this.mSingleLineLengthFilter) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            if (filters2.length == 1) {
                setFilters(NO_FILTERS);
                return;
            }
            InputFilter[] inputFilterArr2 = new InputFilter[filters2.length - 1];
            System.arraycopy(filters2, 0, inputFilterArr2, 0, i);
            System.arraycopy(filters2, i + 1, inputFilterArr2, i, (filters2.length - i) - 1);
            setFilters(inputFilterArr2);
            this.mSingleLineLengthFilter = null;
        }
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.mEllipsize != truncateAt) {
            this.mEllipsize = truncateAt;
            if (this.mLayout != null) {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setMarqueeRepeatLimit(int i) {
        this.mMarqueeRepeatLimit = i;
    }

    public int getMarqueeRepeatLimit() {
        return this.mMarqueeRepeatLimit;
    }

    @ViewDebug.ExportedProperty
    public TextUtils.TruncateAt getEllipsize() {
        return this.mEllipsize;
    }

    @RemotableViewMethod
    public void setSelectAllOnFocus(boolean z) {
        createEditorIfNeeded();
        this.mEditor.mSelectAllOnFocus = z;
        if (z) {
            CharSequence charSequence = this.mText;
            if (charSequence instanceof Spannable) {
                return;
            }
            setText(charSequence, BufferType.SPANNABLE);
        }
    }

    @RemotableViewMethod
    public void setCursorVisible(boolean z) {
        if (z && this.mEditor == null) {
            return;
        }
        createEditorIfNeeded();
        if (this.mEditor.mCursorVisible != z) {
            this.mEditor.mCursorVisible = z;
            invalidate();
            this.mEditor.makeBlink();
            this.mEditor.prepareCursorControllers();
        }
    }

    public boolean isCursorVisible() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return true;
        }
        return editor.mCursorVisible;
    }

    private boolean canMarquee() {
        Layout layout;
        int compoundPaddingLeft = ((this.mRight - this.mLeft) - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        if (compoundPaddingLeft <= 0) {
            return false;
        }
        float f = compoundPaddingLeft;
        return this.mLayout.getLineWidth(0) > f || !(this.mMarqueeFadeMode == 0 || (layout = this.mSavedMarqueeModeLayout) == null || layout.getLineWidth(0) <= f);
    }

    private void startMarquee() {
        if (getKeyListener() == null && !compressText((getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight())) {
            Marquee marquee = this.mMarquee;
            if (marquee == null || marquee.isStopped()) {
                if ((isFocused() || isSelected()) && getLineCount() == 1 && canMarquee()) {
                    if (this.mMarqueeFadeMode == 1) {
                        this.mMarqueeFadeMode = 2;
                        Layout layout = this.mLayout;
                        this.mLayout = this.mSavedMarqueeModeLayout;
                        this.mSavedMarqueeModeLayout = layout;
                        setHorizontalFadingEdgeEnabled(true);
                        requestLayout();
                        invalidate();
                    }
                    if (this.mMarquee == null) {
                        this.mMarquee = new Marquee(this);
                    }
                    this.mMarquee.start(this.mMarqueeRepeatLimit);
                }
            }
        }
    }

    private void stopMarquee() {
        Marquee marquee = this.mMarquee;
        if (marquee != null && !marquee.isStopped()) {
            this.mMarquee.stop();
        }
        if (this.mMarqueeFadeMode == 2) {
            this.mMarqueeFadeMode = 1;
            Layout layout = this.mSavedMarqueeModeLayout;
            this.mSavedMarqueeModeLayout = this.mLayout;
            this.mLayout = layout;
            setHorizontalFadingEdgeEnabled(false);
            requestLayout();
            invalidate();
        }
    }

    private void startStopMarquee(boolean z) {
        if (this.mEllipsize == TextUtils.TruncateAt.MARQUEE) {
            if (z) {
                startMarquee();
            } else {
                stopMarquee();
            }
        }
    }

    protected void onSelectionChanged(int i, int i2) {
        sendAccessibilityEvent(8192);
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        int iIndexOf;
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList == null || (iIndexOf = arrayList.indexOf(textWatcher)) < 0) {
            return;
        }
        this.mListeners.remove(iIndexOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBeforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.get(i4).beforeTextChanged(charSequence, i, i2, i3);
            }
        }
        int i5 = i2 + i;
        removeIntersectingNonAdjacentSpans(i, i5, SpellCheckSpan.class);
        removeIntersectingNonAdjacentSpans(i, i5, SuggestionSpan.class);
    }

    private <T> void removeIntersectingNonAdjacentSpans(int i, int i2, Class<T> cls) {
        CharSequence charSequence = this.mText;
        if (charSequence instanceof Editable) {
            Editable editable = (Editable) charSequence;
            Object[] spans = editable.getSpans(i, i2, cls);
            int length = spans.length;
            for (int i3 = 0; i3 < length; i3++) {
                int spanStart = editable.getSpanStart(spans[i3]);
                if (editable.getSpanEnd(spans[i3]) == i || spanStart == i2) {
                    return;
                }
                editable.removeSpan(spans[i3]);
            }
        }
    }

    void removeAdjacentSuggestionSpans(int i) {
        CharSequence charSequence = this.mText;
        if (charSequence instanceof Editable) {
            Editable editable = (Editable) charSequence;
            SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) editable.getSpans(i, i, SuggestionSpan.class);
            int length = suggestionSpanArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                int spanStart = editable.getSpanStart(suggestionSpanArr[i2]);
                int spanEnd = editable.getSpanEnd(suggestionSpanArr[i2]);
                if ((spanEnd == i || spanStart == i) && SpellChecker.haveWordBoundariesChanged(editable, i, i, spanStart, spanEnd)) {
                    editable.removeSpan(suggestionSpanArr[i2]);
                }
            }
        }
    }

    void sendOnTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.get(i4).onTextChanged(charSequence, i, i2, i3);
            }
        }
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.sendOnTextChanged(i, i2, i3);
        }
    }

    void sendAfterTextChanged(Editable editable) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList.get(i).afterTextChanged(editable);
            }
        }
        notifyListeningManagersAfterTextChanged();
        hideErrorIfUnchanged();
    }

    private void notifyListeningManagersAfterTextChanged() {
        ContentCaptureManager contentCaptureManager;
        ContentCaptureSession contentCaptureSession;
        AutofillManager autofillManager;
        if (isAutofillable() && (autofillManager = (AutofillManager) this.mContext.getSystemService(AutofillManager.class)) != null) {
            if (Helper.sVerbose) {
                Log.v(LOG_TAG, "notifyAutoFillManagerAfterTextChanged");
            }
            autofillManager.notifyValueChanged(this);
        }
        if (isLaidOut() && isImportantForContentCapture() && getNotifiedContentCaptureAppeared() && (contentCaptureManager = (ContentCaptureManager) this.mContext.getSystemService(ContentCaptureManager.class)) != null && contentCaptureManager.isContentCaptureEnabled() && (contentCaptureSession = getContentCaptureSession()) != null) {
            contentCaptureSession.notifyViewTextChanged(getAutofillId(), getText());
        }
    }

    private boolean isAutofillable() {
        return getAutofillType() != 0;
    }

    void updateAfterEdit() {
        invalidate();
        int selectionStart = getSelectionStart();
        if (selectionStart >= 0 || (this.mGravity & 112) == 80) {
            registerForPreDraw();
        }
        checkForResize();
        if (selectionStart >= 0) {
            this.mHighlightPathBogus = true;
            Editor editor = this.mEditor;
            if (editor != null) {
                editor.makeBlink();
            }
            bringPointIntoView(selectionStart);
        }
    }

    void handleTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        sLastCutCopyOrTextChangedTime = 0L;
        Editor editor = this.mEditor;
        Editor.InputMethodState inputMethodState = editor == null ? null : editor.mInputMethodState;
        if (inputMethodState == null || inputMethodState.mBatchEditNesting == 0) {
            updateAfterEdit();
        }
        if (inputMethodState != null) {
            inputMethodState.mContentChanged = true;
            if (inputMethodState.mChangedStart < 0) {
                inputMethodState.mChangedStart = i;
                inputMethodState.mChangedEnd = i + i2;
            } else {
                inputMethodState.mChangedStart = Math.min(inputMethodState.mChangedStart, i);
                inputMethodState.mChangedEnd = Math.max(inputMethodState.mChangedEnd, (i + i2) - inputMethodState.mChangedDelta);
            }
            inputMethodState.mChangedDelta += i3 - i2;
        }
        resetErrorChangedFlag();
        sendOnTextChanged(charSequence, i, i2, i3);
        onTextChanged(charSequence, i, i2, i3);
    }

    void spanChange(Spanned spanned, Object obj, int i, int i2, int i3, int i4) {
        int selectionEnd;
        boolean z;
        Editor editor = this.mEditor;
        Editor.InputMethodState inputMethodState = editor == null ? null : editor.mInputMethodState;
        int selectionStart = -1;
        if (obj == Selection.SELECTION_END) {
            if (i >= 0 || i2 >= 0) {
                invalidateCursor(Selection.getSelectionStart(spanned), i, i2);
                checkForResize();
                registerForPreDraw();
                Editor editor2 = this.mEditor;
                if (editor2 != null) {
                    editor2.makeBlink();
                }
            }
            selectionEnd = i2;
            z = true;
        } else {
            selectionEnd = -1;
            z = false;
        }
        if (obj == Selection.SELECTION_START) {
            if (i >= 0 || i2 >= 0) {
                invalidateCursor(Selection.getSelectionEnd(spanned), i, i2);
            }
            selectionStart = i2;
            z = true;
        }
        if (z) {
            this.mHighlightPathBogus = true;
            if (this.mEditor != null && !isFocused()) {
                this.mEditor.mSelectionMoved = true;
            }
            if ((spanned.getSpanFlags(obj) & 512) == 0) {
                if (selectionStart < 0) {
                    selectionStart = Selection.getSelectionStart(spanned);
                }
                if (selectionEnd < 0) {
                    selectionEnd = Selection.getSelectionEnd(spanned);
                }
                Editor editor3 = this.mEditor;
                if (editor3 != null) {
                    editor3.refreshTextActionMode();
                    if (!hasSelection() && this.mEditor.getTextActionMode() == null && hasTransientState()) {
                        setHasTransientState(false);
                    }
                }
                onSelectionChanged(selectionStart, selectionEnd);
            }
        }
        if ((obj instanceof UpdateAppearance) || (obj instanceof ParagraphStyle) || (obj instanceof CharacterStyle)) {
            if (inputMethodState == null || inputMethodState.mBatchEditNesting == 0) {
                invalidate();
                this.mHighlightPathBogus = true;
                checkForResize();
            } else {
                inputMethodState.mContentChanged = true;
            }
            Editor editor4 = this.mEditor;
            if (editor4 != null) {
                if (i >= 0) {
                    editor4.invalidateTextDisplayList(this.mLayout, i, i3);
                }
                if (i2 >= 0) {
                    this.mEditor.invalidateTextDisplayList(this.mLayout, i2, i4);
                }
                this.mEditor.invalidateHandlesAndActionMode();
            }
        }
        if (MetaKeyKeyListener.isMetaTracker(spanned, obj)) {
            this.mHighlightPathBogus = true;
            if (inputMethodState != null && MetaKeyKeyListener.isSelectingMetaTracker(spanned, obj)) {
                inputMethodState.mSelectionModeChanged = true;
            }
            if (Selection.getSelectionStart(spanned) >= 0) {
                if (inputMethodState == null || inputMethodState.mBatchEditNesting == 0) {
                    invalidateCursor();
                } else {
                    inputMethodState.mCursorChanged = true;
                }
            }
        }
        if ((obj instanceof ParcelableSpan) && inputMethodState != null && inputMethodState.mExtractedTextRequest != null) {
            if (inputMethodState.mBatchEditNesting != 0) {
                if (i >= 0) {
                    if (inputMethodState.mChangedStart > i) {
                        inputMethodState.mChangedStart = i;
                    }
                    if (inputMethodState.mChangedStart > i3) {
                        inputMethodState.mChangedStart = i3;
                    }
                }
                if (i2 >= 0) {
                    if (inputMethodState.mChangedStart > i2) {
                        inputMethodState.mChangedStart = i2;
                    }
                    if (inputMethodState.mChangedStart > i4) {
                        inputMethodState.mChangedStart = i4;
                    }
                }
            } else {
                inputMethodState.mContentChanged = true;
            }
        }
        Editor editor5 = this.mEditor;
        if (editor5 == null || editor5.mSpellChecker == null || i2 >= 0 || !(obj instanceof SpellCheckSpan)) {
            return;
        }
        this.mEditor.mSpellChecker.onSpellCheckSpanRemoved((SpellCheckSpan) obj);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        Spannable spannable;
        if (isTemporarilyDetached()) {
            super.onFocusChanged(z, i, rect);
            return;
        }
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onFocusChanged(z, i);
        }
        if (z && (spannable = this.mSpannable) != null) {
            MetaKeyKeyListener.resetMetaState(spannable);
        }
        startStopMarquee(z);
        TransformationMethod transformationMethod = this.mTransformation;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(this, this.mText, z, i, rect);
        }
        super.onFocusChanged(z, i, rect);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onWindowFocusChanged(z);
        }
        startStopMarquee(z);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Editor editor = this.mEditor;
        if (editor == null || i == 0) {
            return;
        }
        editor.hideCursorAndSpanControllers();
        stopTextActionMode();
    }

    public void clearComposingText() {
        if (this.mText instanceof Spannable) {
            BaseInputConnection.removeComposingSpans(this.mSpannable);
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        boolean zIsSelected = isSelected();
        super.setSelected(z);
        if (z == zIsSelected || this.mEllipsize != TextUtils.TruncateAt.MARQUEE) {
            return;
        }
        if (z) {
            startMarquee();
        } else {
            stopMarquee();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean isFromPrimePointer(android.view.MotionEvent r6, boolean r7) {
        /*
            r5 = this;
            int r0 = r5.mPrimePointerId
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L10
            int r0 = r6.getPointerId(r2)
            r5.mPrimePointerId = r0
            r5.mIsPrimePointerFromHandleView = r7
            goto L1c
        L10:
            int r4 = r6.getPointerId(r2)
            if (r0 == r4) goto L1c
            boolean r0 = r5.mIsPrimePointerFromHandleView
            if (r0 == 0) goto L1d
            if (r7 == 0) goto L1d
        L1c:
            r2 = r3
        L1d:
            int r7 = r6.getActionMasked()
            if (r7 == r3) goto L2a
            int r6 = r6.getActionMasked()
            r7 = 3
            if (r6 != r7) goto L2c
        L2a:
            r5.mPrimePointerId = r1
        L2c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.TextView.isFromPrimePointer(android.view.MotionEvent, boolean):boolean");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Editor editor;
        int actionMasked = motionEvent.getActionMasked();
        if (this.mEditor != null) {
            if (!isFromPrimePointer(motionEvent, false)) {
                return true;
            }
            this.mEditor.onTouchEvent(motionEvent);
            if (this.mEditor.mInsertionPointCursorController != null && this.mEditor.mInsertionPointCursorController.isCursorBeingModified()) {
                return true;
            }
            if (this.mEditor.mSelectionModifierCursorController != null && this.mEditor.mSelectionModifierCursorController.isDragAcceleratorActive()) {
                return true;
            }
        }
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        Editor editor2 = this.mEditor;
        if (editor2 != null && editor2.mDiscardNextActionUp && actionMasked == 1) {
            this.mEditor.mDiscardNextActionUp = false;
            if (this.mEditor.mIsInsertionActionModeStartPending) {
                this.mEditor.startInsertionActionMode();
                this.mEditor.mIsInsertionActionModeStartPending = false;
            }
            return zOnTouchEvent;
        }
        boolean z = actionMasked == 1 && ((editor = this.mEditor) == null || !editor.mIgnoreActionUpEvent) && isFocused();
        if ((this.mMovement != null || onCheckIsTextEditor()) && isEnabled() && (this.mText instanceof Spannable) && this.mLayout != null) {
            MovementMethod movementMethod = this.mMovement;
            boolean zOnTouchEvent2 = movementMethod != null ? movementMethod.onTouchEvent(this, this.mSpannable, motionEvent) | false : false;
            boolean zIsTextSelectable = isTextSelectable();
            if (z && this.mLinksClickable && this.mAutoLinkMask != 0 && zIsTextSelectable) {
                ClickableSpan[] clickableSpanArr = (ClickableSpan[]) this.mSpannable.getSpans(getSelectionStart(), getSelectionEnd(), ClickableSpan.class);
                if (clickableSpanArr.length > 0) {
                    clickableSpanArr[0].onClick(this);
                    zOnTouchEvent2 = true;
                }
            }
            if (z && (isTextEditable() || zIsTextSelectable)) {
                InputMethodManager inputMethodManager = getInputMethodManager();
                viewClicked(inputMethodManager);
                if (isTextEditable() && this.mEditor.mShowSoftInputOnFocus && inputMethodManager != null) {
                    inputMethodManager.showSoftInput(this, 0);
                }
                this.mEditor.onTouchUpEvent(motionEvent);
                zOnTouchEvent2 = true;
            }
            if (zOnTouchEvent2) {
                return true;
            }
        }
        return zOnTouchEvent;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        MovementMethod movementMethod = this.mMovement;
        if (movementMethod != null && (this.mText instanceof Spannable) && this.mLayout != null) {
            try {
                if (movementMethod.onGenericMotionEvent(this, this.mSpannable, motionEvent)) {
                    return true;
                }
            } catch (AbstractMethodError unused) {
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onCreateContextMenu(ContextMenu contextMenu) {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onCreateContextMenu(contextMenu);
        }
    }

    @Override // android.view.View
    public boolean showContextMenu() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.setContextMenuAnchor(Float.NaN, Float.NaN);
        }
        return super.showContextMenu();
    }

    @Override // android.view.View
    public boolean showContextMenu(float f, float f2) {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.setContextMenuAnchor(f, f2);
        }
        return super.showContextMenu(f, f2);
    }

    boolean isTextEditable() {
        return (this.mText instanceof Editable) && onCheckIsTextEditor() && isEnabled();
    }

    public boolean didTouchFocusSelect() {
        Editor editor = this.mEditor;
        return editor != null && editor.mTouchFocusSelected;
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.mIgnoreActionUpEvent = true;
        }
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        Spannable spannable;
        MovementMethod movementMethod = this.mMovement;
        if (movementMethod == null || (spannable = this.mSpannable) == null || this.mLayout == null || !movementMethod.onTrackballEvent(this, spannable, motionEvent)) {
            return super.onTrackballEvent(motionEvent);
        }
        return true;
    }

    public void setScroller(Scroller scroller) {
        this.mScroller = scroller;
    }

    @Override // android.view.View
    protected float getLeftFadingEdgeStrength() {
        Marquee marquee;
        if (isMarqueeFadeEnabled() && (marquee = this.mMarquee) != null && !marquee.isStopped()) {
            Marquee marquee2 = this.mMarquee;
            if (marquee2.shouldDrawLeftFade()) {
                return getHorizontalFadingEdgeStrength(marquee2.getScroll(), 0.0f);
            }
            return 0.0f;
        }
        if (getLineCount() == 1) {
            float lineLeft = getLayout().getLineLeft(0);
            if (lineLeft > this.mScrollX) {
                return 0.0f;
            }
            return getHorizontalFadingEdgeStrength(this.mScrollX, lineLeft);
        }
        return super.getLeftFadingEdgeStrength();
    }

    @Override // android.view.View
    protected float getRightFadingEdgeStrength() {
        Marquee marquee;
        if (isMarqueeFadeEnabled() && (marquee = this.mMarquee) != null && !marquee.isStopped()) {
            Marquee marquee2 = this.mMarquee;
            return getHorizontalFadingEdgeStrength(marquee2.getMaxFadeScroll(), marquee2.getScroll());
        }
        if (getLineCount() == 1) {
            float width = this.mScrollX + ((getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight());
            float lineRight = getLayout().getLineRight(0);
            if (lineRight < width) {
                return 0.0f;
            }
            return getHorizontalFadingEdgeStrength(width, lineRight);
        }
        return super.getRightFadingEdgeStrength();
    }

    private float getHorizontalFadingEdgeStrength(float f, float f2) {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (horizontalFadingEdgeLength == 0) {
            return 0.0f;
        }
        float fAbs = Math.abs(f - f2);
        float f3 = horizontalFadingEdgeLength;
        if (fAbs > f3) {
            return 1.0f;
        }
        return fAbs / f3;
    }

    private boolean isMarqueeFadeEnabled() {
        return this.mEllipsize == TextUtils.TruncateAt.MARQUEE && this.mMarqueeFadeMode != 1;
    }

    @Override // android.view.View
    protected int computeHorizontalScrollRange() {
        Layout layout = this.mLayout;
        if (layout != null) {
            return (this.mSingleLine && (this.mGravity & 7) == 3) ? (int) layout.getLineWidth(0) : layout.getWidth();
        }
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        Layout layout = this.mLayout;
        if (layout != null) {
            return layout.getHeight();
        }
        return super.computeVerticalScrollRange();
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        return (getHeight() - getCompoundPaddingTop()) - getCompoundPaddingBottom();
    }

    @Override // android.view.View
    public void findViewsWithText(ArrayList<View> arrayList, CharSequence charSequence, int i) {
        super.findViewsWithText(arrayList, charSequence, i);
        if (arrayList.contains(this) || (i & 1) == 0 || TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(this.mText)) {
            return;
        }
        if (this.mText.toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
            arrayList.add(this);
        }
    }

    public static ColorStateList getTextColors(Context context, TypedArray typedArray) {
        int resourceId;
        Objects.requireNonNull(typedArray);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.styleable.TextView);
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(5);
        if (colorStateList == null && (resourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0)) != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R.styleable.TextAppearance);
            colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(3);
            typedArrayObtainStyledAttributes2.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static int getTextColor(Context context, TypedArray typedArray, int i) {
        ColorStateList textColors = getTextColors(context, typedArray);
        return textColors == null ? i : textColors.getDefaultColor();
    }

    @Override // android.view.View
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (keyEvent.hasModifiers(4096)) {
            if (i != 29) {
                if (i != 31) {
                    if (i != 50) {
                        if (i != 52) {
                            if (i == 54 && canUndo()) {
                                return onTextContextMenuItem(16908338);
                            }
                        } else if (canCut()) {
                            return onTextContextMenuItem(16908320);
                        }
                    } else if (canPaste()) {
                        return onTextContextMenuItem(16908322);
                    }
                } else if (canCopy()) {
                    return onTextContextMenuItem(16908321);
                }
            } else if (canSelectText()) {
                return onTextContextMenuItem(16908319);
            }
        } else if (keyEvent.hasModifiers(4097)) {
            if (i != 50) {
                if (i == 54 && canRedo()) {
                    return onTextContextMenuItem(16908339);
                }
            } else if (canPaste()) {
                return onTextContextMenuItem(16908337);
            }
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    boolean canSelectText() {
        Editor editor;
        return (this.mText.length() == 0 || (editor = this.mEditor) == null || !editor.hasSelectionController()) ? false : true;
    }

    boolean textCanBeSelected() {
        MovementMethod movementMethod = this.mMovement;
        if (movementMethod == null || !movementMethod.canSelectArbitrarily()) {
            return false;
        }
        return isTextEditable() || (isTextSelectable() && (this.mText instanceof Spannable) && isEnabled());
    }

    private Locale getTextServicesLocale(boolean z) {
        updateTextServicesLocaleAsync();
        return (this.mCurrentSpellCheckerLocaleCache != null || z) ? this.mCurrentSpellCheckerLocaleCache : Locale.getDefault();
    }

    public final void setTextOperationUser(UserHandle userHandle) {
        if (Objects.equals(this.mTextOperationUser, userHandle)) {
            return;
        }
        if (userHandle != null && !Process.myUserHandle().equals(userHandle) && getContext().checkSelfPermission(Manifest.permission.INTERACT_ACROSS_USERS_FULL) != 0) {
            throw new SecurityException("INTERACT_ACROSS_USERS_FULL is required. userId=" + userHandle.getIdentifier() + " callingUserId" + UserHandle.myUserId());
        }
        this.mTextOperationUser = userHandle;
        this.mCurrentSpellCheckerLocaleCache = null;
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onTextOperationUserChanged();
        }
    }

    final TextServicesManager getTextServicesManagerForUser() {
        return (TextServicesManager) getServiceManagerForUser("android", TextServicesManager.class);
    }

    final ClipboardManager getClipboardManagerForUser() {
        return (ClipboardManager) getServiceManagerForUser(getContext().getPackageName(), ClipboardManager.class);
    }

    final TextClassificationManager getTextClassificationManagerForUser() {
        return (TextClassificationManager) getServiceManagerForUser(getContext().getPackageName(), TextClassificationManager.class);
    }

    final <T> T getServiceManagerForUser(String str, Class<T> cls) {
        if (this.mTextOperationUser == null) {
            return (T) getContext().getSystemService(cls);
        }
        try {
            return (T) getContext().createPackageContextAsUser(str, 0, this.mTextOperationUser).getSystemService(cls);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    void startActivityAsTextOperationUserIfNecessary(Intent intent) {
        if (this.mTextOperationUser != null) {
            getContext().startActivityAsUser(intent, this.mTextOperationUser);
        } else {
            getContext().startActivity(intent);
        }
    }

    public Locale getTextServicesLocale() {
        return getTextServicesLocale(false);
    }

    private boolean isAutoSizeEnabled() {
        return supportsAutoSizeText() && this.mAutoSizeTextType != 0;
    }

    public Locale getSpellCheckerLocale() {
        return getTextServicesLocale(true);
    }

    private void updateTextServicesLocaleAsync() {
        AsyncTask.execute(new Runnable() { // from class: android.widget.TextView.3
            @Override // java.lang.Runnable
            public void run() {
                TextView.this.updateTextServicesLocaleLocked();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextServicesLocaleLocked() {
        TextServicesManager textServicesManagerForUser = getTextServicesManagerForUser();
        if (textServicesManagerForUser == null) {
            return;
        }
        SpellCheckerSubtype currentSpellCheckerSubtype = textServicesManagerForUser.getCurrentSpellCheckerSubtype(true);
        this.mCurrentSpellCheckerLocaleCache = currentSpellCheckerSubtype != null ? currentSpellCheckerSubtype.getLocaleObject() : null;
    }

    void onLocaleChanged() {
        this.mEditor.onLocaleChanged();
    }

    public WordIterator getWordIterator() {
        Editor editor = this.mEditor;
        if (editor != null) {
            return editor.getWordIterator();
        }
        return null;
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEventInternal(accessibilityEvent);
        CharSequence textForAccessibility = getTextForAccessibility();
        if (TextUtils.isEmpty(textForAccessibility)) {
            return;
        }
        accessibilityEvent.getText().add(textForAccessibility);
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return TextView.class.getName();
    }

    @Override // android.view.View
    protected void onProvideStructure(ViewStructure viewStructure, int i, int i2) {
        int lineAtCoordinateUnclamped;
        int lineAtCoordinateUnclamped2;
        super.onProvideStructure(viewStructure, i, i2);
        boolean z = hasPasswordTransformationMethod() || isPasswordInputType(getInputType());
        if (i == 1 || i == 2) {
            if (i == 1) {
                viewStructure.setDataIsSensitive(!this.mTextSetFromXmlOrResourceId);
            }
            if (this.mTextId != 0) {
                try {
                    viewStructure.setTextIdEntry(getResources().getResourceEntryName(this.mTextId));
                } catch (Resources.NotFoundException e) {
                    if (Helper.sVerbose) {
                        Log.v(LOG_TAG, "onProvideAutofillStructure(): cannot set name for text id " + this.mTextId + ": " + e.getMessage());
                    }
                }
            }
        }
        if (!z || i == 1 || i == 2) {
            if (this.mLayout == null) {
                if (i == 2) {
                    Log.w(LOG_TAG, "onProvideContentCaptureStructure(): calling assumeLayout()");
                }
                assumeLayout();
            }
            Layout layout = this.mLayout;
            int lineCount = layout.getLineCount();
            if (lineCount <= 1) {
                CharSequence text = getText();
                if (i == 1) {
                    viewStructure.setText(text);
                } else {
                    viewStructure.setText(text, getSelectionStart(), getSelectionEnd());
                }
            } else {
                int[] iArr = new int[2];
                getLocationInWindow(iArr);
                int i3 = iArr[1];
                ViewParent parent = getParent();
                View view = this;
                while (parent instanceof View) {
                    view = (View) parent;
                    parent = view.getParent();
                }
                int height = view.getHeight();
                if (i3 >= 0) {
                    lineAtCoordinateUnclamped2 = getLineAtCoordinateUnclamped(0.0f);
                    lineAtCoordinateUnclamped = getLineAtCoordinateUnclamped(height - 1);
                } else {
                    int lineAtCoordinateUnclamped3 = getLineAtCoordinateUnclamped(-i3);
                    lineAtCoordinateUnclamped = getLineAtCoordinateUnclamped((height - 1) - i3);
                    lineAtCoordinateUnclamped2 = lineAtCoordinateUnclamped3;
                }
                int i4 = lineAtCoordinateUnclamped - lineAtCoordinateUnclamped2;
                int i5 = i4 / 2;
                int i6 = lineAtCoordinateUnclamped2 - i5;
                if (i6 < 0) {
                    i6 = 0;
                }
                int i7 = i5 + lineAtCoordinateUnclamped;
                if (i7 >= lineCount) {
                    i7 = lineCount - 1;
                }
                int lineStart = layout.getLineStart(i6);
                int lineEnd = layout.getLineEnd(i7);
                int selectionStart = getSelectionStart();
                int selectionEnd = getSelectionEnd();
                if (selectionStart < selectionEnd) {
                    if (selectionStart < lineStart) {
                        lineStart = selectionStart;
                    }
                    if (selectionEnd > lineEnd) {
                        lineEnd = selectionEnd;
                    }
                }
                CharSequence text2 = getText();
                if (lineStart > 0 || lineEnd < text2.length()) {
                    text2 = text2.subSequence(lineStart, lineEnd);
                }
                if (i == 1) {
                    viewStructure.setText(text2);
                } else {
                    viewStructure.setText(text2, selectionStart - lineStart, selectionEnd - lineStart);
                    int i8 = i4 + 1;
                    int[] iArr2 = new int[i8];
                    int[] iArr3 = new int[i8];
                    int baselineOffset = getBaselineOffset();
                    for (int i9 = lineAtCoordinateUnclamped2; i9 <= lineAtCoordinateUnclamped; i9++) {
                        int i10 = i9 - lineAtCoordinateUnclamped2;
                        iArr2[i10] = layout.getLineStart(i9);
                        iArr3[i10] = layout.getLineBaseline(i9) + baselineOffset;
                    }
                    viewStructure.setTextLines(iArr2, iArr3);
                }
            }
            if (i == 0 || i == 2) {
                int typefaceStyle = getTypefaceStyle();
                int i11 = (typefaceStyle & 1) != 0 ? 1 : 0;
                if ((typefaceStyle & 2) != 0) {
                    i11 |= 2;
                }
                int flags = this.mTextPaint.getFlags();
                if ((flags & 32) != 0) {
                    i11 |= 1;
                }
                if ((flags & 8) != 0) {
                    i11 |= 4;
                }
                if ((flags & 16) != 0) {
                    i11 |= 8;
                }
                viewStructure.setTextStyle(getTextSize(), getCurrentTextColor(), 1, i11);
            }
            if (i == 1 || i == 2) {
                viewStructure.setMinTextEms(getMinEms());
                viewStructure.setMaxTextEms(getMaxEms());
                int max = -1;
                InputFilter[] filters = getFilters();
                int length = filters.length;
                int i12 = 0;
                while (true) {
                    if (i12 >= length) {
                        break;
                    }
                    InputFilter inputFilter = filters[i12];
                    if (inputFilter instanceof InputFilter.LengthFilter) {
                        max = ((InputFilter.LengthFilter) inputFilter).getMax();
                        break;
                    }
                    i12++;
                }
                viewStructure.setMaxTextLength(max);
            }
        }
        if (this.mHintId != 0) {
            try {
                viewStructure.setHintIdEntry(getResources().getResourceEntryName(this.mHintId));
            } catch (Resources.NotFoundException e2) {
                if (Helper.sVerbose) {
                    Log.v(LOG_TAG, "onProvideAutofillStructure(): cannot set name for hint id " + this.mHintId + ": " + e2.getMessage());
                }
            }
        }
        viewStructure.setHint(getHint());
        viewStructure.setInputType(getInputType());
    }

    boolean canRequestAutofill() {
        AutofillManager autofillManager;
        if (isAutofillable() && (autofillManager = (AutofillManager) this.mContext.getSystemService(AutofillManager.class)) != null) {
            return autofillManager.isEnabled();
        }
        return false;
    }

    private void requestAutofill() {
        AutofillManager autofillManager = (AutofillManager) this.mContext.getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.requestAutofill(this);
        }
    }

    @Override // android.view.View
    public void autofill(AutofillValue autofillValue) {
        if (!autofillValue.isText() || !isTextEditable()) {
            Log.w(LOG_TAG, autofillValue + " could not be autofilled into " + this);
            return;
        }
        setText(autofillValue.getTextValue(), this.mBufferType, true, 0);
        CharSequence text = getText();
        if (text instanceof Spannable) {
            Selection.setSelection((Spannable) text, text.length());
        }
    }

    @Override // android.view.View
    public int getAutofillType() {
        return isTextEditable() ? 1 : 0;
    }

    @Override // android.view.View
    public AutofillValue getAutofillValue() {
        if (isTextEditable()) {
            return AutofillValue.forText(TextUtils.trimToParcelableSize(getText()));
        }
        return null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEventInternal(accessibilityEvent);
        accessibilityEvent.setPassword(hasPasswordTransformationMethod());
        if (accessibilityEvent.getEventType() == 8192) {
            accessibilityEvent.setFromIndex(Selection.getSelectionStart(this.mText));
            accessibilityEvent.setToIndex(Selection.getSelectionEnd(this.mText));
            accessibilityEvent.setItemCount(this.mText.length());
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        accessibilityNodeInfo.setPassword(hasPasswordTransformationMethod());
        accessibilityNodeInfo.setText(getTextForAccessibility());
        accessibilityNodeInfo.setHintText(this.mHint);
        accessibilityNodeInfo.setShowingHintText(isShowingHint());
        if (this.mBufferType == BufferType.EDITABLE) {
            accessibilityNodeInfo.setEditable(true);
            if (isEnabled()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_TEXT);
            }
        }
        Editor editor = this.mEditor;
        if (editor != null) {
            accessibilityNodeInfo.setInputType(editor.mInputType);
            if (this.mEditor.mError != null) {
                accessibilityNodeInfo.setContentInvalid(true);
                accessibilityNodeInfo.setError(this.mEditor.mError);
            }
            if (isTextEditable() && isFocused()) {
                CharSequence string = this.mContext.getResources().getString(com.android.internal.R.string.keyboardview_keycode_enter);
                if (getImeActionLabel() != null) {
                    string = getImeActionLabel();
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16908372, string));
            }
        }
        if (!TextUtils.isEmpty(this.mText)) {
            accessibilityNodeInfo.addAction(256);
            accessibilityNodeInfo.addAction(512);
            accessibilityNodeInfo.setMovementGranularities(31);
            accessibilityNodeInfo.addAction(131072);
            accessibilityNodeInfo.setAvailableExtraData(Arrays.asList(AccessibilityNodeInfo.EXTRA_DATA_RENDERING_INFO_KEY, AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY));
        } else {
            accessibilityNodeInfo.setAvailableExtraData(Arrays.asList(AccessibilityNodeInfo.EXTRA_DATA_RENDERING_INFO_KEY));
        }
        if (isFocused()) {
            if (canCopy()) {
                accessibilityNodeInfo.addAction(16384);
            }
            if (canPaste()) {
                accessibilityNodeInfo.addAction(32768);
            }
            if (canCut()) {
                accessibilityNodeInfo.addAction(65536);
            }
            if (canShare()) {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(268435456, getResources().getString(com.android.internal.R.string.share)));
            }
            if (canProcessText()) {
                this.mEditor.mProcessTextIntentActionsHandler.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            }
        }
        int length = this.mFilters.length;
        for (int i = 0; i < length; i++) {
            InputFilter inputFilter = this.mFilters[i];
            if (inputFilter instanceof InputFilter.LengthFilter) {
                accessibilityNodeInfo.setMaxTextLength(((InputFilter.LengthFilter) inputFilter).getMax());
            }
        }
        if (!isSingleLine()) {
            accessibilityNodeInfo.setMultiLine(true);
        }
        if ((accessibilityNodeInfo.isClickable() || accessibilityNodeInfo.isLongClickable()) && (this.mMovement instanceof LinkMovementMethod)) {
            if (!hasOnClickListeners()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            }
            if (hasOnLongClickListeners()) {
                return;
            }
            accessibilityNodeInfo.setLongClickable(false);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
        }
    }

    @Override // android.view.View
    public void addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
        RectF characterBounds;
        if (bundle != null && str.equals(AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY)) {
            int i = bundle.getInt(AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX, -1);
            int i2 = bundle.getInt(AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH, -1);
            if (i2 <= 0 || i < 0 || i >= this.mText.length()) {
                Log.e(LOG_TAG, "Invalid arguments for accessibility character locations");
                return;
            }
            RectF[] rectFArr = new RectF[i2];
            CursorAnchorInfo.Builder builder = new CursorAnchorInfo.Builder();
            populateCharacterBounds(builder, i, i + i2, viewportToContentHorizontalOffset(), viewportToContentVerticalOffset());
            CursorAnchorInfo cursorAnchorInfoBuild = builder.setMatrix(null).build();
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = i + i3;
                if ((cursorAnchorInfoBuild.getCharacterBoundsFlags(i4) & 1) == 1 && (characterBounds = cursorAnchorInfoBuild.getCharacterBounds(i4)) != null) {
                    mapRectFromViewToScreenCoords(characterBounds, true);
                    rectFArr[i3] = characterBounds;
                }
            }
            accessibilityNodeInfo.getExtras().putParcelableArray(str, rectFArr);
            return;
        }
        if (str.equals(AccessibilityNodeInfo.EXTRA_DATA_RENDERING_INFO_KEY)) {
            AccessibilityNodeInfo.ExtraRenderingInfo extraRenderingInfoObtain = AccessibilityNodeInfo.ExtraRenderingInfo.obtain();
            extraRenderingInfoObtain.setLayoutSize(getLayoutParams().width, getLayoutParams().height);
            extraRenderingInfoObtain.setTextSizeInPx(getTextSize());
            extraRenderingInfoObtain.setTextSizeUnit(getTextSizeUnit());
            accessibilityNodeInfo.setExtraRenderingInfo(extraRenderingInfoObtain);
        }
    }

    public void populateCharacterBounds(CursorAnchorInfo.Builder builder, int i, int i2, float f, float f2) {
        float f3;
        float f4;
        int i3;
        int i4 = i;
        int lineForOffset = this.mLayout.getLineForOffset(i4);
        int lineForOffset2 = this.mLayout.getLineForOffset(i2 - 1);
        while (lineForOffset <= lineForOffset2) {
            int lineStart = this.mLayout.getLineStart(lineForOffset);
            int lineEnd = this.mLayout.getLineEnd(lineForOffset);
            int iMax = Math.max(lineStart, i4);
            int iMin = Math.min(lineEnd, i2);
            boolean z = this.mLayout.getParagraphDirection(lineForOffset) == 1;
            float[] fArr = new float[iMin - iMax];
            this.mLayout.getPaint().getTextWidths(this.mTransformed, iMax, iMin, fArr);
            float lineTop = this.mLayout.getLineTop(lineForOffset);
            float lineBottom = this.mLayout.getLineBottom(lineForOffset);
            for (int i5 = iMax; i5 < iMin; i5++) {
                float f5 = fArr[i5 - iMax];
                boolean zIsRtlCharAt = this.mLayout.isRtlCharAt(i5);
                float primaryHorizontal = this.mLayout.getPrimaryHorizontal(i5);
                float secondaryHorizontal = this.mLayout.getSecondaryHorizontal(i5);
                if (!z) {
                    if (zIsRtlCharAt) {
                        secondaryHorizontal = primaryHorizontal - f5;
                    } else {
                        primaryHorizontal = secondaryHorizontal + f5;
                    }
                    f3 = primaryHorizontal;
                    primaryHorizontal = secondaryHorizontal;
                } else if (zIsRtlCharAt) {
                    primaryHorizontal = secondaryHorizontal - f5;
                    f3 = secondaryHorizontal;
                } else {
                    f3 = f5 + primaryHorizontal;
                }
                float f6 = primaryHorizontal + f;
                float f7 = f3 + f;
                float f8 = lineTop + f2;
                float f9 = lineBottom + f2;
                boolean zIsPositionVisible = isPositionVisible(f6, f8);
                boolean zIsPositionVisible2 = isPositionVisible(f7, f9);
                int i6 = (zIsPositionVisible || zIsPositionVisible2) ? 1 : 0;
                if (!zIsPositionVisible || !zIsPositionVisible2) {
                    i6 |= 2;
                }
                if (zIsRtlCharAt) {
                    f4 = f8;
                    i3 = i6 | 4;
                } else {
                    f4 = f8;
                    i3 = i6;
                }
                builder.addCharacterBounds(i5, f6, f4, f7, f9, i3);
            }
            lineForOffset++;
            i4 = i;
        }
    }

    public boolean isPositionVisible(float f, float f2) {
        float[] fArr = TEMP_POSITION;
        synchronized (fArr) {
            fArr[0] = f;
            fArr[1] = f2;
            View view = this;
            while (view != null) {
                if (view != this) {
                    fArr[0] = fArr[0] - view.getScrollX();
                    fArr[1] = fArr[1] - view.getScrollY();
                }
                if (fArr[0] >= 0.0f && fArr[1] >= 0.0f && fArr[0] <= view.getWidth() && fArr[1] <= view.getHeight()) {
                    if (!view.getMatrix().isIdentity()) {
                        view.getMatrix().mapPoints(fArr);
                    }
                    fArr[0] = fArr[0] + view.getLeft();
                    fArr[1] = fArr[1] + view.getTop();
                    Object parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                }
                return false;
            }
            return true;
        }
    }

    @Override // android.view.View
    public boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        int length;
        Editor editor = this.mEditor;
        if (editor != null && editor.mProcessTextIntentActionsHandler.performAccessibilityAction(i)) {
            return true;
        }
        switch (i) {
            case 16:
                return performAccessibilityActionClick(bundle);
            case 32:
                if (!isLongClickable()) {
                    return false;
                }
                if (isEnabled() && this.mBufferType == BufferType.EDITABLE) {
                    this.mEditor.mIsBeingLongClickedByAccessibility = true;
                    try {
                        return performLongClick();
                    } finally {
                        this.mEditor.mIsBeingLongClickedByAccessibility = false;
                    }
                }
                return performLongClick();
            case 256:
            case 512:
                ensureIterableTextForAccessibilitySelectable();
                return super.performAccessibilityActionInternal(i, bundle);
            case 16384:
                return isFocused() && canCopy() && onTextContextMenuItem(16908321);
            case 32768:
                return isFocused() && canPaste() && onTextContextMenuItem(16908322);
            case 65536:
                return isFocused() && canCut() && onTextContextMenuItem(16908320);
            case 131072:
                ensureIterableTextForAccessibilitySelectable();
                CharSequence iterableTextForAccessibility = getIterableTextForAccessibility();
                if (iterableTextForAccessibility == null) {
                    return false;
                }
                int i2 = bundle != null ? bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT", -1) : -1;
                int i3 = bundle != null ? bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT", -1) : -1;
                if (getSelectionStart() != i2 || getSelectionEnd() != i3) {
                    if (i2 == i3 && i3 == -1) {
                        Selection.removeSelection((Spannable) iterableTextForAccessibility);
                        return true;
                    }
                    if (i2 >= 0 && i2 <= i3 && i3 <= iterableTextForAccessibility.length()) {
                        Selection.setSelection((Spannable) iterableTextForAccessibility, i2, i3);
                        Editor editor2 = this.mEditor;
                        if (editor2 != null) {
                            editor2.startSelectionActionModeAsync(false);
                        }
                        return true;
                    }
                }
                return false;
            case 2097152:
                if (!isEnabled() || this.mBufferType != BufferType.EDITABLE) {
                    return false;
                }
                setText(bundle != null ? bundle.getCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE") : null);
                CharSequence charSequence = this.mText;
                if (charSequence != null && (length = charSequence.length()) > 0) {
                    Selection.setSelection(this.mSpannable, length);
                }
                return true;
            case 16908372:
                if (isFocused() && isTextEditable()) {
                    onEditorAction(getImeActionId());
                }
                return true;
            case 268435456:
                return isFocused() && canShare() && onTextContextMenuItem(16908341);
            default:
                return super.performAccessibilityActionInternal(i, bundle);
        }
    }

    private boolean performAccessibilityActionClick(Bundle bundle) {
        boolean z;
        if (!isEnabled()) {
            return false;
        }
        if (isClickable() || isLongClickable()) {
            if (isFocusable() && !isFocused()) {
                requestFocus();
            }
            performClick();
            z = true;
        } else {
            z = false;
        }
        if ((this.mMovement == null && !onCheckIsTextEditor()) || !hasSpannableText() || this.mLayout == null) {
            return z;
        }
        if ((!isTextEditable() && !isTextSelectable()) || !isFocused()) {
            return z;
        }
        InputMethodManager inputMethodManager = getInputMethodManager();
        viewClicked(inputMethodManager);
        return (isTextSelectable() || !this.mEditor.mShowSoftInputOnFocus || inputMethodManager == null) ? z : z | inputMethodManager.showSoftInput(this, 0);
    }

    private boolean hasSpannableText() {
        CharSequence charSequence = this.mText;
        return charSequence != null && (charSequence instanceof Spannable);
    }

    @Override // android.view.View
    public void sendAccessibilityEventInternal(int i) {
        Editor editor;
        if (i == 32768 && (editor = this.mEditor) != null) {
            editor.mProcessTextIntentActionsHandler.initializeAccessibilityActions();
        }
        super.sendAccessibilityEventInternal(i);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    private CharSequence getTextForAccessibility() {
        if (TextUtils.isEmpty(this.mText)) {
            return this.mHint;
        }
        return TextUtils.trimToParcelableSize(this.mTransformed);
    }

    void sendAccessibilityEventTypeViewTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16);
        accessibilityEventObtain.setFromIndex(i);
        accessibilityEventObtain.setRemovedCount(i2);
        accessibilityEventObtain.setAddedCount(i3);
        accessibilityEventObtain.setBeforeText(charSequence);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
    }

    public boolean isInputMethodTarget() {
        InputMethodManager inputMethodManager = getInputMethodManager();
        return inputMethodManager != null && inputMethodManager.isActive(this);
    }

    public boolean onTextContextMenuItem(int i) {
        int iMax;
        int length = this.mText.length();
        if (isFocused()) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
            length = Math.max(0, Math.max(selectionStart, selectionEnd));
        } else {
            iMax = 0;
        }
        if (i != 16908355) {
            switch (i) {
                case 16908319:
                    boolean zHasSelection = hasSelection();
                    selectAllText();
                    Editor editor = this.mEditor;
                    if (editor != null && zHasSelection) {
                        editor.invalidateActionModeAsync();
                    }
                    return true;
                case 16908320:
                    if (setPrimaryClip(ClipData.newPlainText(null, getTransformedText(iMax, length)))) {
                        deleteText_internal(iMax, length);
                    } else {
                        Toast.makeText(getContext(), com.android.internal.R.string.failed_to_copy_to_clipboard, 0).show();
                    }
                    return true;
                case 16908321:
                    int selectionStart2 = getSelectionStart();
                    int selectionEnd2 = getSelectionEnd();
                    if (setPrimaryClip(ClipData.newPlainText(null, getTransformedText(Math.max(0, Math.min(selectionStart2, selectionEnd2)), Math.max(0, Math.max(selectionStart2, selectionEnd2)))))) {
                        stopTextActionMode();
                    } else {
                        Toast.makeText(getContext(), com.android.internal.R.string.failed_to_copy_to_clipboard, 0).show();
                    }
                    return true;
                case 16908322:
                    paste(iMax, length, true);
                    return true;
                default:
                    switch (i) {
                        case 16908337:
                            paste(iMax, length, false);
                            return true;
                        case 16908338:
                            Editor editor2 = this.mEditor;
                            if (editor2 != null) {
                                editor2.undo();
                            }
                            return true;
                        case 16908339:
                            Editor editor3 = this.mEditor;
                            if (editor3 != null) {
                                editor3.redo();
                            }
                            return true;
                        case 16908340:
                            Editor editor4 = this.mEditor;
                            if (editor4 != null) {
                                editor4.replace();
                            }
                            return true;
                        case 16908341:
                            shareSelectedText();
                            return true;
                        default:
                            return false;
                    }
            }
        }
        requestAutofill();
        stopTextActionMode();
        return true;
    }

    CharSequence getTransformedText(int i, int i2) {
        return removeSuggestionSpans(this.mTransformed.subSequence(i, i2));
    }

    @Override // android.view.View
    public boolean performLongClick() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.mIsBeingLongClicked = true;
        }
        boolean zPerformLongClick = super.performLongClick();
        boolean z = zPerformLongClick;
        Editor editor2 = this.mEditor;
        if (editor2 != null) {
            zPerformLongClick |= editor2.performLongClick(zPerformLongClick);
            this.mEditor.mIsBeingLongClicked = false;
        }
        if (zPerformLongClick) {
            if (!z) {
                performHapticFeedback(0);
            }
            Editor editor3 = this.mEditor;
            if (editor3 != null) {
                editor3.mDiscardNextActionUp = true;
            }
        } else {
            MetricsLogger.action(this.mContext, 629, 0);
        }
        return zPerformLongClick;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.onScrollChanged();
        }
    }

    public boolean isSuggestionsEnabled() {
        Editor editor = this.mEditor;
        if (editor == null || (editor.mInputType & 15) != 1 || (this.mEditor.mInputType & 524288) > 0) {
            return false;
        }
        int i = this.mEditor.mInputType & 4080;
        return i == 0 || i == 48 || i == 80 || i == 64 || i == 160;
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        createEditorIfNeeded();
        this.mEditor.mCustomSelectionActionModeCallback = callback;
    }

    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return null;
        }
        return editor.mCustomSelectionActionModeCallback;
    }

    public void setCustomInsertionActionModeCallback(ActionMode.Callback callback) {
        createEditorIfNeeded();
        this.mEditor.mCustomInsertionActionModeCallback = callback;
    }

    public ActionMode.Callback getCustomInsertionActionModeCallback() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return null;
        }
        return editor.mCustomInsertionActionModeCallback;
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        this.mTextClassifier = textClassifier;
    }

    public TextClassifier getTextClassifier() {
        TextClassifier textClassifier = this.mTextClassifier;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManagerForUser = getTextClassificationManagerForUser();
        if (textClassificationManagerForUser != null) {
            return textClassificationManagerForUser.getTextClassifier();
        }
        return TextClassifier.NO_OP;
    }

    TextClassifier getTextClassificationSession() {
        String str;
        TextClassifier textClassifier = this.mTextClassificationSession;
        if (textClassifier == null || textClassifier.isDestroyed()) {
            TextClassificationManager textClassificationManagerForUser = getTextClassificationManagerForUser();
            if (textClassificationManagerForUser != null) {
                if (isTextEditable()) {
                    str = TextClassifier.WIDGET_TYPE_EDITTEXT;
                } else {
                    str = isTextSelectable() ? TextClassifier.WIDGET_TYPE_TEXTVIEW : TextClassifier.WIDGET_TYPE_UNSELECTABLE_TEXTVIEW;
                }
                TextClassificationContext textClassificationContextBuild = new TextClassificationContext.Builder(this.mContext.getPackageName(), str).build();
                this.mTextClassificationContext = textClassificationContextBuild;
                TextClassifier textClassifier2 = this.mTextClassifier;
                if (textClassifier2 != null) {
                    this.mTextClassificationSession = textClassificationManagerForUser.createTextClassificationSession(textClassificationContextBuild, textClassifier2);
                } else {
                    this.mTextClassificationSession = textClassificationManagerForUser.createTextClassificationSession(textClassificationContextBuild);
                }
            } else {
                this.mTextClassificationSession = TextClassifier.NO_OP;
            }
        }
        return this.mTextClassificationSession;
    }

    TextClassificationContext getTextClassificationContext() {
        return this.mTextClassificationContext;
    }

    boolean usesNoOpTextClassifier() {
        return getTextClassifier() == TextClassifier.NO_OP;
    }

    public boolean requestActionMode(TextLinks.TextLinkSpan textLinkSpan) {
        Preconditions.checkNotNull(textLinkSpan);
        CharSequence charSequence = this.mText;
        if (!(charSequence instanceof Spanned)) {
            return false;
        }
        int spanStart = ((Spanned) charSequence).getSpanStart(textLinkSpan);
        int spanEnd = ((Spanned) this.mText).getSpanEnd(textLinkSpan);
        if (spanStart < 0 || spanEnd > this.mText.length() || spanStart >= spanEnd) {
            return false;
        }
        createEditorIfNeeded();
        this.mEditor.startLinkActionModeAsync(spanStart, spanEnd);
        return true;
    }

    public boolean handleClick(TextLinks.TextLinkSpan textLinkSpan) {
        Preconditions.checkNotNull(textLinkSpan);
        CharSequence charSequence = this.mText;
        if (!(charSequence instanceof Spanned)) {
            return false;
        }
        Spanned spanned = (Spanned) charSequence;
        int spanStart = spanned.getSpanStart(textLinkSpan);
        int spanEnd = spanned.getSpanEnd(textLinkSpan);
        if (spanStart < 0 || spanEnd > this.mText.length() || spanStart >= spanEnd) {
            return false;
        }
        final TextClassification.Request requestBuild = new TextClassification.Request.Builder(this.mText, spanStart, spanEnd).setDefaultLocales(getTextLocales()).build();
        Supplier supplier = new Supplier() { // from class: android.widget.-$$Lambda$TextView$FnkUK4_wC-w1fIa8uQ3c1X8KIlk
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$handleClick$0$TextView(requestBuild);
            }
        };
        CompletableFuture.supplyAsync(supplier).completeOnTimeout(null, 1L, TimeUnit.SECONDS).thenAccept((Consumer) new Consumer() { // from class: android.widget.-$$Lambda$TextView$wr-XdR8IpC5iz6i6H2MCbXWFhUg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                TextView.lambda$handleClick$1((TextClassification) obj);
            }
        });
        return true;
    }

    public /* synthetic */ TextClassification lambda$handleClick$0$TextView(TextClassification.Request request) {
        return getTextClassificationSession().classifyText(request);
    }

    static /* synthetic */ void lambda$handleClick$1(TextClassification textClassification) {
        if (textClassification != null) {
            if (!textClassification.getActions().isEmpty()) {
                try {
                    textClassification.getActions().get(0).getActionIntent().send();
                    return;
                } catch (PendingIntent.CanceledException e) {
                    Log.e(LOG_TAG, "Error sending PendingIntent", e);
                    return;
                }
            }
            Log.d(LOG_TAG, "No link action to perform");
            return;
        }
        Log.d(LOG_TAG, "Timeout while classifying text");
    }

    protected void stopTextActionMode() {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.lambda$startActionModeInternal$0$Editor();
        }
    }

    public void hideFloatingToolbar(int i) {
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.hideFloatingToolbar(i);
        }
    }

    boolean canUndo() {
        Editor editor = this.mEditor;
        return editor != null && editor.canUndo();
    }

    boolean canRedo() {
        Editor editor = this.mEditor;
        return editor != null && editor.canRedo();
    }

    boolean canCut() {
        Editor editor;
        return !hasPasswordTransformationMethod() && this.mText.length() > 0 && hasSelection() && (this.mText instanceof Editable) && (editor = this.mEditor) != null && editor.mKeyListener != null;
    }

    boolean canCopy() {
        return !hasPasswordTransformationMethod() && this.mText.length() > 0 && hasSelection() && this.mEditor != null;
    }

    boolean canShare() {
        if (getContext().canStartActivityForResult() && isDeviceProvisioned()) {
            return canCopy();
        }
        return false;
    }

    boolean isDeviceProvisioned() {
        if (this.mDeviceProvisionedState == 0) {
            this.mDeviceProvisionedState = Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) != 0 ? 2 : 1;
        }
        return this.mDeviceProvisionedState == 2;
    }

    boolean canPaste() {
        Editor editor;
        return (this.mText instanceof Editable) && (editor = this.mEditor) != null && editor.mKeyListener != null && getSelectionStart() >= 0 && getSelectionEnd() >= 0 && getClipboardManagerForUser().hasPrimaryClip();
    }

    boolean canPasteAsPlainText() {
        if (!canPaste()) {
            return false;
        }
        ClipData primaryClip = getClipboardManagerForUser().getPrimaryClip();
        ClipDescription description = primaryClip.getDescription();
        boolean zHasMimeType = description.hasMimeType("text/plain");
        CharSequence text = primaryClip.getItemAt(0).getText();
        if (zHasMimeType && (text instanceof Spanned) && TextUtils.hasStyleSpan((Spanned) text)) {
            return true;
        }
        return description.hasMimeType("text/html");
    }

    boolean canProcessText() {
        if (getId() == -1) {
            return false;
        }
        return canShare();
    }

    boolean canSelectAllText() {
        return (!canSelectText() || hasPasswordTransformationMethod() || (getSelectionStart() == 0 && getSelectionEnd() == this.mText.length())) ? false : true;
    }

    boolean selectAllText() {
        if (this.mEditor != null) {
            hideFloatingToolbar(500);
        }
        int length = this.mText.length();
        Selection.setSelection(this.mSpannable, 0, length);
        return length > 0;
    }

    void replaceSelectionWithText(CharSequence charSequence) {
        ((Editable) this.mText).replace(getSelectionStart(), getSelectionEnd(), charSequence);
    }

    private void paste(int i, int i2, boolean z) {
        CharSequence charSequenceCoerceToText;
        ClipData primaryClip = getClipboardManagerForUser().getPrimaryClip();
        if (primaryClip != null) {
            boolean z2 = false;
            for (int i3 = 0; i3 < primaryClip.getItemCount(); i3++) {
                if (z) {
                    charSequenceCoerceToText = primaryClip.getItemAt(i3).coerceToStyledText(getContext());
                } else {
                    charSequenceCoerceToText = primaryClip.getItemAt(i3).coerceToText(getContext());
                    if (charSequenceCoerceToText instanceof Spanned) {
                        charSequenceCoerceToText = charSequenceCoerceToText.toString();
                    }
                }
                if (charSequenceCoerceToText != null) {
                    if (!z2) {
                        Selection.setSelection(this.mSpannable, i2);
                        ((Editable) this.mText).replace(i, i2, charSequenceCoerceToText);
                        z2 = true;
                    } else {
                        ((Editable) this.mText).insert(getSelectionEnd(), "\n");
                        ((Editable) this.mText).insert(getSelectionEnd(), charSequenceCoerceToText);
                    }
                }
            }
            sLastCutCopyOrTextChangedTime = 0L;
        }
    }

    private void shareSelectedText() {
        String selectedText = getSelectedText();
        if (selectedText == null || selectedText.isEmpty()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.removeExtra(Intent.EXTRA_TEXT);
        intent.putExtra(Intent.EXTRA_TEXT, (String) TextUtils.trimToParcelableSize(selectedText));
        getContext().startActivity(Intent.createChooser(intent, null));
        Selection.setSelection(this.mSpannable, getSelectionEnd());
    }

    private boolean setPrimaryClip(ClipData clipData) {
        try {
            getClipboardManagerForUser().setPrimaryClip(clipData);
            sLastCutCopyOrTextChangedTime = SystemClock.uptimeMillis();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getOffsetForPosition(float f, float f2) {
        if (getLayout() == null) {
            return -1;
        }
        return getOffsetAtCoordinate(getLineAtCoordinate(f2), f);
    }

    float convertToLocalHorizontalCoordinate(float f) {
        return Math.min((getWidth() - getTotalPaddingRight()) - 1, Math.max(0.0f, f - getTotalPaddingLeft())) + getScrollX();
    }

    int getLineAtCoordinate(float f) {
        return getLayout().getLineForVertical((int) (Math.min((getHeight() - getTotalPaddingBottom()) - 1, Math.max(0.0f, f - getTotalPaddingTop())) + getScrollY()));
    }

    int getLineAtCoordinateUnclamped(float f) {
        return getLayout().getLineForVertical((int) ((f - getTotalPaddingTop()) + getScrollY()));
    }

    int getOffsetAtCoordinate(int i, float f) {
        return getLayout().getOffsetForHorizontal(i, convertToLocalHorizontalCoordinate(f));
    }

    @Override // android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action == 1) {
            Editor editor = this.mEditor;
            return editor != null && editor.hasInsertionController();
        }
        if (action == 2) {
            if (this.mText instanceof Spannable) {
                Selection.setSelection(this.mSpannable, getOffsetForPosition(dragEvent.getX(), dragEvent.getY()));
            }
            return true;
        }
        if (action != 3) {
            if (action != 5) {
                return true;
            }
            requestFocus();
            return true;
        }
        Editor editor2 = this.mEditor;
        if (editor2 != null) {
            editor2.onDrop(dragEvent);
        }
        return true;
    }

    boolean isInBatchEditMode() {
        Editor editor = this.mEditor;
        if (editor == null) {
            return false;
        }
        Editor.InputMethodState inputMethodState = editor.mInputMethodState;
        if (inputMethodState != null) {
            return inputMethodState.mBatchEditNesting > 0;
        }
        return this.mEditor.mInBatchEditControllers;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        TextDirectionHeuristic textDirectionHeuristic = getTextDirectionHeuristic();
        if (this.mTextDir != textDirectionHeuristic) {
            this.mTextDir = textDirectionHeuristic;
            if (this.mLayout != null) {
                checkForRelayout();
            }
        }
    }

    public TextDirectionHeuristic getTextDirectionHeuristic() {
        if (hasPasswordTransformationMethod()) {
            return TextDirectionHeuristics.LTR;
        }
        Editor editor = this.mEditor;
        if (editor != null && (editor.mInputType & 15) == 3) {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if (directionality == 1 || directionality == 2) {
                return TextDirectionHeuristics.RTL;
            }
            return TextDirectionHeuristics.LTR;
        }
        boolean z = getLayoutDirection() == 1;
        switch (getTextDirection()) {
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                if (!z) {
                }
                break;
        }
        return TextDirectionHeuristics.LTR;
    }

    @Override // android.view.View
    public void onResolveDrawables(int i) {
        if (this.mLastLayoutDirection == i) {
            return;
        }
        this.mLastLayoutDirection = i;
        Drawables drawables = this.mDrawables;
        if (drawables == null || !drawables.resolveWithLayoutDirection(i)) {
            return;
        }
        prepareDrawableForDisplay(this.mDrawables.mShowing[0]);
        prepareDrawableForDisplay(this.mDrawables.mShowing[2]);
        applyCompoundDrawableTint();
    }

    private void prepareDrawableForDisplay(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setLayoutDirection(getLayoutDirection());
        if (drawable.isStateful()) {
            drawable.setState(getDrawableState());
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    protected void resetResolvedDrawables() {
        super.resetResolvedDrawables();
        this.mLastLayoutDirection = -1;
    }

    protected void viewClicked(InputMethodManager inputMethodManager) {
        if (inputMethodManager != null) {
            inputMethodManager.viewClicked(this);
        }
    }

    protected void deleteText_internal(int i, int i2) {
        ((Editable) this.mText).delete(i, i2);
    }

    protected void replaceText_internal(int i, int i2, CharSequence charSequence) {
        ((Editable) this.mText).replace(i, i2, charSequence);
    }

    protected void setSpan_internal(Object obj, int i, int i2, int i3) {
        ((Editable) this.mText).setSpan(obj, i, i2, i3);
    }

    protected void setCursorPosition_internal(int i, int i2) {
        Selection.setSelection((Editable) this.mText, i, i2);
    }

    private void createEditorIfNeeded() {
        if (this.mEditor == null) {
            this.mEditor = new Editor(this);
        }
    }

    @Override // android.view.View
    public CharSequence getIterableTextForAccessibility() {
        return this.mText;
    }

    private void ensureIterableTextForAccessibilitySelectable() {
        CharSequence charSequence = this.mText;
        if (charSequence instanceof Spannable) {
            return;
        }
        setText(charSequence, BufferType.SPANNABLE);
    }

    @Override // android.view.View
    public AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(int i) {
        if (i == 4) {
            Spannable spannable = (Spannable) getIterableTextForAccessibility();
            if (!TextUtils.isEmpty(spannable) && getLayout() != null) {
                AccessibilityIterators.LineTextSegmentIterator lineTextSegmentIterator = AccessibilityIterators.LineTextSegmentIterator.getInstance();
                lineTextSegmentIterator.initialize(spannable, getLayout());
                return lineTextSegmentIterator;
            }
        } else if (i == 16 && !TextUtils.isEmpty((Spannable) getIterableTextForAccessibility()) && getLayout() != null) {
            AccessibilityIterators.PageTextSegmentIterator pageTextSegmentIterator = AccessibilityIterators.PageTextSegmentIterator.getInstance();
            pageTextSegmentIterator.initialize(this);
            return pageTextSegmentIterator;
        }
        return super.getIteratorForGranularity(i);
    }

    @Override // android.view.View
    public int getAccessibilitySelectionStart() {
        return getSelectionStart();
    }

    @Override // android.view.View
    public int getAccessibilitySelectionEnd() {
        return getSelectionEnd();
    }

    @Override // android.view.View
    public void setAccessibilitySelection(int i, int i2) {
        if (getAccessibilitySelectionStart() == i && getAccessibilitySelectionEnd() == i2) {
            return;
        }
        CharSequence iterableTextForAccessibility = getIterableTextForAccessibility();
        if (Math.min(i, i2) >= 0 && Math.max(i, i2) <= iterableTextForAccessibility.length()) {
            Selection.setSelection((Spannable) iterableTextForAccessibility, i, i2);
        } else {
            Selection.removeSelection((Spannable) iterableTextForAccessibility);
        }
        Editor editor = this.mEditor;
        if (editor != null) {
            editor.hideCursorAndSpanControllers();
            this.mEditor.lambda$startActionModeInternal$0$Editor();
        }
    }

    @Override // android.view.View
    protected void encodeProperties(ViewHierarchyEncoder viewHierarchyEncoder) {
        super.encodeProperties(viewHierarchyEncoder);
        TextUtils.TruncateAt ellipsize = getEllipsize();
        viewHierarchyEncoder.addProperty("text:ellipsize", ellipsize == null ? null : ellipsize.name());
        viewHierarchyEncoder.addProperty("text:textSize", getTextSize());
        viewHierarchyEncoder.addProperty("text:scaledTextSize", getScaledTextSize());
        viewHierarchyEncoder.addProperty("text:typefaceStyle", getTypefaceStyle());
        viewHierarchyEncoder.addProperty("text:selectionStart", getSelectionStart());
        viewHierarchyEncoder.addProperty("text:selectionEnd", getSelectionEnd());
        viewHierarchyEncoder.addProperty("text:curTextColor", this.mCurTextColor);
        CharSequence charSequence = this.mText;
        viewHierarchyEncoder.addUserProperty("text:text", charSequence != null ? charSequence.toString() : null);
        viewHierarchyEncoder.addProperty("text:gravity", this.mGravity);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.widget.TextView.SavedState.1
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
        ParcelableParcel editorState;
        CharSequence error;
        boolean frozenWithFocus;
        int selEnd;
        int selStart;
        CharSequence text;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.selStart = -1;
            this.selEnd = -1;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.selStart);
            parcel.writeInt(this.selEnd);
            parcel.writeInt(this.frozenWithFocus ? 1 : 0);
            TextUtils.writeToParcel(this.text, parcel, i);
            if (this.error == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                TextUtils.writeToParcel(this.error, parcel, i);
            }
            if (this.editorState == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                this.editorState.writeToParcel(parcel, i);
            }
        }

        public String toString() {
            String str = "TextView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " start=" + this.selStart + " end=" + this.selEnd;
            if (this.text != null) {
                str = str + " text=" + ((Object) this.text);
            }
            return str + "}";
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.selStart = -1;
            this.selEnd = -1;
            this.selStart = parcel.readInt();
            this.selEnd = parcel.readInt();
            this.frozenWithFocus = parcel.readInt() != 0;
            this.text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if (parcel.readInt() != 0) {
                this.error = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            }
            if (parcel.readInt() != 0) {
                this.editorState = ParcelableParcel.CREATOR.createFromParcel(parcel);
            }
        }
    }

    private static class CharWrapper implements CharSequence, GetChars, GraphicsOperations {
        private char[] mChars;
        private int mLength;
        private int mStart;

        public CharWrapper(char[] cArr, int i, int i2) {
            this.mChars = cArr;
            this.mStart = i;
            this.mLength = i2;
        }

        void set(char[] cArr, int i, int i2) {
            this.mChars = cArr;
            this.mStart = i;
            this.mLength = i2;
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.mLength;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            return this.mChars[i + this.mStart];
        }

        @Override // java.lang.CharSequence
        public String toString() {
            return new String(this.mChars, this.mStart, this.mLength);
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            int i3;
            if (i < 0 || i2 < 0 || i > (i3 = this.mLength) || i2 > i3) {
                throw new IndexOutOfBoundsException(i + ", " + i2);
            }
            return new String(this.mChars, this.mStart + i, i2 - i);
        }

        @Override // android.text.GetChars
        public void getChars(int i, int i2, char[] cArr, int i3) {
            int i4;
            if (i < 0 || i2 < 0 || i > (i4 = this.mLength) || i2 > i4) {
                throw new IndexOutOfBoundsException(i + ", " + i2);
            }
            System.arraycopy(this.mChars, this.mStart + i, cArr, i3, i2 - i);
        }

        @Override // android.text.GraphicsOperations
        public void drawText(BaseCanvas baseCanvas, int i, int i2, float f, float f2, Paint paint) {
            baseCanvas.drawText(this.mChars, i + this.mStart, i2 - i, f, f2, paint);
        }

        @Override // android.text.GraphicsOperations
        public void drawTextRun(BaseCanvas baseCanvas, int i, int i2, int i3, int i4, float f, float f2, boolean z, Paint paint) {
            char[] cArr = this.mChars;
            int i5 = this.mStart;
            baseCanvas.drawTextRun(cArr, i + i5, i2 - i, i3 + i5, i4 - i3, f, f2, z, paint);
        }

        @Override // android.text.GraphicsOperations
        public float measureText(int i, int i2, Paint paint) {
            return paint.measureText(this.mChars, this.mStart + i, i2 - i);
        }

        @Override // android.text.GraphicsOperations
        public int getTextWidths(int i, int i2, float[] fArr, Paint paint) {
            return paint.getTextWidths(this.mChars, this.mStart + i, i2 - i, fArr);
        }

        @Override // android.text.GraphicsOperations
        public float getTextRunAdvances(int i, int i2, int i3, int i4, boolean z, float[] fArr, int i5, Paint paint) {
            char[] cArr = this.mChars;
            int i6 = this.mStart;
            return paint.getTextRunAdvances(cArr, i + i6, i2 - i, i3 + i6, i4 - i3, z, fArr, i5);
        }

        @Override // android.text.GraphicsOperations
        public int getTextRunCursor(int i, int i2, boolean z, int i3, int i4, Paint paint) {
            char[] cArr = this.mChars;
            int i5 = this.mStart;
            return paint.getTextRunCursor(cArr, i + i5, i2 - i, z, i3 + i5, i4);
        }
    }

    private static final class Marquee {
        private static final int MARQUEE_DELAY = 1200;
        private static final float MARQUEE_DELTA_MAX = 0.07f;
        private static final int MARQUEE_DP_PER_SECOND = 30;
        private static final byte MARQUEE_RUNNING = 2;
        private static final byte MARQUEE_STARTING = 1;
        private static final byte MARQUEE_STOPPED = 0;
        private float mFadeStop;
        private float mGhostOffset;
        private float mGhostStart;
        private long mLastAnimationMs;
        private float mMaxFadeScroll;
        private float mMaxScroll;
        private final float mPixelsPerMs;
        private int mRepeatLimit;
        private float mScroll;
        private final WeakReference<TextView> mView;
        private byte mStatus = 0;
        private Choreographer.FrameCallback mTickCallback = new Choreographer.FrameCallback() { // from class: android.widget.TextView.Marquee.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                Marquee.this.tick();
            }
        };
        private Choreographer.FrameCallback mStartCallback = new Choreographer.FrameCallback() { // from class: android.widget.TextView.Marquee.2
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                Marquee.this.mStatus = (byte) 2;
                Marquee marquee = Marquee.this;
                marquee.mLastAnimationMs = marquee.mChoreographer.getFrameTime();
                Marquee.this.tick();
            }
        };
        private Choreographer.FrameCallback mRestartCallback = new Choreographer.FrameCallback() { // from class: android.widget.TextView.Marquee.3
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                if (Marquee.this.mStatus == 2) {
                    if (Marquee.this.mRepeatLimit >= 0) {
                        Marquee.access$910(Marquee.this);
                    }
                    Marquee marquee = Marquee.this;
                    marquee.start(marquee.mRepeatLimit);
                }
            }
        };
        private final Choreographer mChoreographer = Choreographer.getInstance();

        static /* synthetic */ int access$910(Marquee marquee) {
            int i = marquee.mRepeatLimit;
            marquee.mRepeatLimit = i - 1;
            return i;
        }

        Marquee(TextView textView) {
            this.mPixelsPerMs = (textView.getContext().getResources().getDisplayMetrics().density * 30.0f) / 1000.0f;
            this.mView = new WeakReference<>(textView);
        }

        void tick() {
            if (this.mStatus != 2) {
                return;
            }
            this.mChoreographer.removeFrameCallback(this.mTickCallback);
            TextView textView = this.mView.get();
            if (textView != null) {
                if (textView.isFocused() || textView.isSelected()) {
                    long frameTime = this.mChoreographer.getFrameTime();
                    long j = frameTime - this.mLastAnimationMs;
                    this.mLastAnimationMs = frameTime;
                    float f = this.mScroll + (j * this.mPixelsPerMs);
                    this.mScroll = f;
                    float f2 = this.mMaxScroll;
                    if (f > f2) {
                        this.mScroll = f2;
                        this.mChoreographer.postFrameCallbackDelayed(this.mRestartCallback, 1200L);
                    } else {
                        this.mChoreographer.postFrameCallback(this.mTickCallback);
                    }
                    textView.invalidate();
                }
            }
        }

        void stop() {
            this.mStatus = (byte) 0;
            this.mChoreographer.removeFrameCallback(this.mStartCallback);
            this.mChoreographer.removeFrameCallback(this.mRestartCallback);
            this.mChoreographer.removeFrameCallback(this.mTickCallback);
            resetScroll();
        }

        private void resetScroll() {
            this.mScroll = 0.0f;
            TextView textView = this.mView.get();
            if (textView != null) {
                textView.invalidate();
            }
        }

        void start(int i) {
            if (i == 0) {
                stop();
                return;
            }
            this.mRepeatLimit = i;
            TextView textView = this.mView.get();
            if (textView == null || textView.mLayout == null) {
                return;
            }
            this.mStatus = (byte) 1;
            this.mScroll = 0.0f;
            int width = (textView.getWidth() - textView.getCompoundPaddingLeft()) - textView.getCompoundPaddingRight();
            float lineWidth = textView.mLayout.getLineWidth(0);
            float f = width;
            float f2 = f / 3.0f;
            float f3 = (lineWidth - f) + f2;
            this.mGhostStart = f3;
            this.mMaxScroll = f3 + f;
            this.mGhostOffset = f2 + lineWidth;
            this.mFadeStop = (f / 6.0f) + lineWidth;
            this.mMaxFadeScroll = f3 + lineWidth + lineWidth;
            textView.invalidate();
            this.mChoreographer.postFrameCallback(this.mStartCallback);
        }

        float getGhostOffset() {
            return this.mGhostOffset;
        }

        float getScroll() {
            return this.mScroll;
        }

        float getMaxFadeScroll() {
            return this.mMaxFadeScroll;
        }

        boolean shouldDrawLeftFade() {
            return this.mScroll <= this.mFadeStop;
        }

        boolean shouldDrawGhost() {
            return this.mStatus == 2 && this.mScroll > this.mGhostStart;
        }

        boolean isRunning() {
            return this.mStatus == 2;
        }

        boolean isStopped() {
            return this.mStatus == 0;
        }
    }

    private class ChangeWatcher implements TextWatcher, SpanWatcher {
        private CharSequence mBeforeText;

        private ChangeWatcher() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (AccessibilityManager.getInstance(TextView.this.mContext).isEnabled() && TextView.this.mTransformed != null) {
                this.mBeforeText = TextView.this.mTransformed.toString();
            }
            TextView.this.sendBeforeTextChanged(charSequence, i, i2, i3);
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            TextView.this.handleTextChanged(charSequence, i, i2, i3);
            if (AccessibilityManager.getInstance(TextView.this.mContext).isEnabled()) {
                if (TextView.this.isFocused() || (TextView.this.isSelected() && TextView.this.isShown())) {
                    TextView.this.sendAccessibilityEventTypeViewTextChanged(this.mBeforeText, i, i2, i3);
                    this.mBeforeText = null;
                }
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            TextView.this.sendAfterTextChanged(editable);
            if (MetaKeyKeyListener.getMetaState(editable, 2048) != 0) {
                MetaKeyKeyListener.stopSelecting(TextView.this, editable);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable, Object obj, int i, int i2, int i3, int i4) {
            TextView.this.spanChange(spannable, obj, i, i3, i2, i4);
        }

        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable, Object obj, int i, int i2) {
            TextView.this.spanChange(spannable, obj, -1, i, -1, i2);
        }

        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable, Object obj, int i, int i2) {
            TextView.this.spanChange(spannable, obj, i, -1, i2, -1);
        }
    }

    private static void logCursor(String str, String str2, Object... objArr) {
        if (str2 == null) {
            Log.d(LOG_TAG, str);
        } else {
            Log.d(LOG_TAG, str + ": " + String.format(str2, objArr));
        }
    }
}
