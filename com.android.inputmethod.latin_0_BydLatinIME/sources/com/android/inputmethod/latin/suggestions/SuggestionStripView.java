package com.android.inputmethod.latin.suggestions;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.keyboard.MoreKeysPanel;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.suggestions.MoreSuggestions;
import com.android.inputmethod.latin.suggestions.MoreSuggestionsView;
import com.android.inputmethod.latin.utils.ImportantNoticeUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SuggestionStripView extends RelativeLayout implements View.OnClickListener, View.OnLongClickListener {
    static final boolean DBG = false;
    private static final float DEBUG_INFO_TEXT_SIZE_IN_DIP = 6.0f;
    private final ArrayList<TextView> mDebugInfoViews;
    private final ArrayList<View> mDividerViews;
    private final ImageButton mHideIMEKeyRight;
    private final View mImportantNoticeStrip;
    private boolean mIsDispatchingHoverEventToMoreSuggestions;
    private int mLastX;
    private int mLastY;
    private final SuggestionStripLayoutHelper mLayoutHelper;
    Listener mListener;
    MainKeyboardView mMainKeyboardView;
    private final MoreSuggestions.Builder mMoreSuggestionsBuilder;
    private final View mMoreSuggestionsContainer;
    private final MoreKeysPanel.Controller mMoreSuggestionsController;
    private final MoreSuggestionsView.MoreSuggestionsListener mMoreSuggestionsListener;
    private final int mMoreSuggestionsModalTolerance;
    private final GestureDetector mMoreSuggestionsSlidingDetector;
    private final GestureDetector.OnGestureListener mMoreSuggestionsSlidingListener;
    private final MoreSuggestionsView mMoreSuggestionsView;
    private boolean mNeedsToTransformTouchEventToHoverEvent;
    private int mOriginX;
    private int mOriginY;
    private int mStartIndexOfMoreSuggestions;
    private final StripVisibilityGroup mStripVisibilityGroup;
    private SuggestedWords mSuggestedWords;
    private final ViewGroup mSuggestionsStrip;
    private final ImageButton mVoiceKey;
    private final ArrayList<TextView> mWordViews;

    public interface Listener {
        void onCodeInput(int primaryCode, int x, int y, boolean isKeyRepeat);

        void onHideIME();

        void pickSuggestionManually(SuggestedWords.SuggestedWordInfo word);

        void showImportantNoticeContents();
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent event) {
        return true;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    private static class StripVisibilityGroup {
        private final View mImportantNoticeStrip;
        private final View mSuggestionStripView;
        private final View mSuggestionsStrip;

        public StripVisibilityGroup(final View suggestionStripView, final ViewGroup suggestionsStrip, final View importantNoticeStrip) {
            this.mSuggestionStripView = suggestionStripView;
            this.mSuggestionsStrip = suggestionsStrip;
            this.mImportantNoticeStrip = importantNoticeStrip;
            showSuggestionsStrip();
        }

        public void setLayoutDirection(boolean z) {
            ViewCompat.setLayoutDirection(this.mSuggestionStripView, z ? 1 : 0);
            ViewCompat.setLayoutDirection(this.mSuggestionsStrip, z ? 1 : 0);
            ViewCompat.setLayoutDirection(this.mImportantNoticeStrip, z ? 1 : 0);
        }

        public void showSuggestionsStrip() {
            this.mSuggestionsStrip.setVisibility(0);
            this.mImportantNoticeStrip.setVisibility(4);
        }

        public void showImportantNoticeStrip() {
            this.mSuggestionsStrip.setVisibility(4);
            this.mImportantNoticeStrip.setVisibility(0);
        }

        public boolean isShowingImportantNoticeStrip() {
            return this.mImportantNoticeStrip.getVisibility() == 0;
        }
    }

    public SuggestionStripView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionStripViewStyle);
    }

    public SuggestionStripView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mWordViews = new ArrayList<>();
        this.mDebugInfoViews = new ArrayList<>();
        this.mDividerViews = new ArrayList<>();
        this.mSuggestedWords = SuggestedWords.getEmptyInstance();
        this.mMoreSuggestionsListener = new MoreSuggestionsView.MoreSuggestionsListener() { // from class: com.android.inputmethod.latin.suggestions.SuggestionStripView.1
            @Override // com.android.inputmethod.latin.suggestions.MoreSuggestionsView.MoreSuggestionsListener
            public void onSuggestionSelected(final SuggestedWords.SuggestedWordInfo wordInfo) {
                SuggestionStripView.this.mListener.pickSuggestionManually(wordInfo);
                SuggestionStripView.this.dismissMoreSuggestionsPanel();
            }

            @Override // com.android.inputmethod.keyboard.KeyboardActionListener.Adapter, com.android.inputmethod.keyboard.KeyboardActionListener
            public void onCancelInput() {
                SuggestionStripView.this.dismissMoreSuggestionsPanel();
            }
        };
        this.mMoreSuggestionsController = new MoreKeysPanel.Controller() { // from class: com.android.inputmethod.latin.suggestions.SuggestionStripView.2
            @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
            public void onDismissMoreKeysPanel() {
                SuggestionStripView.this.mMainKeyboardView.onDismissMoreKeysPanel();
            }

            @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
            public void onShowMoreKeysPanel(final MoreKeysPanel panel) {
                SuggestionStripView.this.mMainKeyboardView.onShowMoreKeysPanel(panel);
            }

            @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
            public void onCancelMoreKeysPanel() {
                SuggestionStripView.this.dismissMoreSuggestionsPanel();
            }
        };
        this.mMoreSuggestionsSlidingListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.android.inputmethod.latin.suggestions.SuggestionStripView.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent down, MotionEvent me, float deltaX, float deltaY) {
                float y = me.getY() - down.getY();
                if (deltaY <= 0.0f || y >= 0.0f) {
                    return false;
                }
                return SuggestionStripView.this.showMoreSuggestions();
            }
        };
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        layoutInflaterFrom.inflate(R.layout.suggestions_strip, this);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.suggestions_strip);
        this.mSuggestionsStrip = viewGroup;
        this.mVoiceKey = (ImageButton) findViewById(R.id.suggestions_strip_voice_key);
        this.mHideIMEKeyRight = (ImageButton) findViewById(R.id.hide_ime_key_right);
        View viewFindViewById = findViewById(R.id.important_notice_strip);
        this.mImportantNoticeStrip = viewFindViewById;
        this.mStripVisibilityGroup = new StripVisibilityGroup(this, viewGroup, viewFindViewById);
        for (int i = 0; i < 18; i++) {
            TextView textView = new TextView(context, null, R.attr.suggestionWordStyle);
            textView.setContentDescription(getResources().getString(R.string.spoken_empty_suggestion));
            textView.setOnClickListener(this);
            textView.setOnLongClickListener(this);
            this.mWordViews.add(textView);
            this.mDividerViews.add(layoutInflaterFrom.inflate(R.layout.suggestion_divider, (ViewGroup) null));
            TextView textView2 = new TextView(context, null, R.attr.suggestionWordStyle);
            textView2.setTextColor(-1);
            textView2.setTextSize(1, DEBUG_INFO_TEXT_SIZE_IN_DIP);
            this.mDebugInfoViews.add(textView2);
        }
        this.mLayoutHelper = new SuggestionStripLayoutHelper(context, attrs, defStyle, this.mWordViews, this.mDividerViews, this.mDebugInfoViews);
        View viewInflate = layoutInflaterFrom.inflate(R.layout.more_suggestions, (ViewGroup) null);
        this.mMoreSuggestionsContainer = viewInflate;
        MoreSuggestionsView moreSuggestionsView = (MoreSuggestionsView) viewInflate.findViewById(R.id.more_suggestions_view);
        this.mMoreSuggestionsView = moreSuggestionsView;
        this.mMoreSuggestionsBuilder = new MoreSuggestions.Builder(context, moreSuggestionsView);
        this.mMoreSuggestionsModalTolerance = context.getResources().getDimensionPixelOffset(R.dimen.config_more_suggestions_modal_tolerance);
        this.mMoreSuggestionsSlidingDetector = new GestureDetector(context, this.mMoreSuggestionsSlidingListener);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Keyboard, defStyle, R.style.SuggestionStripView);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(16);
        typedArrayObtainStyledAttributes.recycle();
        this.mVoiceKey.setImageDrawable(drawable);
        this.mVoiceKey.setOnClickListener(this);
        this.mVoiceKey.setVisibility(8);
        this.mHideIMEKeyRight.setImageDrawable(getResources().getDrawable(R.drawable.hide_ime, null));
        this.mHideIMEKeyRight.setOnClickListener(this);
        this.mHideIMEKeyRight.setVisibility(0);
    }

    public void setListener(final Listener listener, final View inputView) {
        this.mListener = listener;
        this.mMainKeyboardView = (MainKeyboardView) inputView.findViewById(R.id.keyboard_view);
    }

    public void updateVisibility(final boolean shouldBeVisible, final boolean isFullscreenMode) {
        setVisibility(shouldBeVisible ? 0 : isFullscreenMode ? 8 : 4);
        this.mVoiceKey.setVisibility(Settings.getInstance().getCurrent().mShowsVoiceInputKey ? 0 : 4);
        this.mVoiceKey.setVisibility(8);
    }

    public void setSuggestions(final SuggestedWords suggestedWords, final boolean isRtlLanguage) {
        clear();
        this.mStripVisibilityGroup.setLayoutDirection(isRtlLanguage);
        this.mSuggestedWords = suggestedWords;
        this.mStartIndexOfMoreSuggestions = this.mLayoutHelper.layoutAndReturnStartIndexOfMoreSuggestions(getContext(), this.mSuggestedWords, this.mSuggestionsStrip, this);
        this.mStripVisibilityGroup.showSuggestionsStrip();
    }

    public void setMoreSuggestionsHeight(final int remainingHeight) {
        this.mLayoutHelper.setMoreSuggestionsHeight(remainingHeight);
    }

    public boolean maybeShowImportantNoticeTitle() {
        if (!ImportantNoticeUtils.shouldShowImportantNotice(getContext(), Settings.getInstance().getCurrent()) || getWidth() <= 0) {
            return false;
        }
        String suggestContactsNoticeTitle = ImportantNoticeUtils.getSuggestContactsNoticeTitle(getContext());
        if (TextUtils.isEmpty(suggestContactsNoticeTitle)) {
            return false;
        }
        if (isShowingMoreSuggestionPanel()) {
            dismissMoreSuggestionsPanel();
        }
        this.mLayoutHelper.layoutImportantNotice(this.mImportantNoticeStrip, suggestContactsNoticeTitle);
        this.mStripVisibilityGroup.showImportantNoticeStrip();
        this.mImportantNoticeStrip.setOnClickListener(this);
        return true;
    }

    public void clear() {
        this.mSuggestionsStrip.removeAllViews();
        removeAllDebugInfoViews();
        this.mStripVisibilityGroup.showSuggestionsStrip();
        dismissMoreSuggestionsPanel();
    }

    private void removeAllDebugInfoViews() {
        for (TextView textView : this.mDebugInfoViews) {
            ViewParent parent = textView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(textView);
            }
        }
    }

    public boolean isShowingMoreSuggestionPanel() {
        return this.mMoreSuggestionsView.isShowingInParent();
    }

    public void dismissMoreSuggestionsPanel() {
        this.mMoreSuggestionsView.dismissMoreKeysPanel();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(final View view) {
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(-1, this);
        return showMoreSuggestions();
    }

    boolean showMoreSuggestions() {
        Keyboard keyboard = this.mMainKeyboardView.getKeyboard();
        if (keyboard == null) {
            return false;
        }
        SuggestionStripLayoutHelper suggestionStripLayoutHelper = this.mLayoutHelper;
        if (this.mSuggestedWords.size() <= this.mStartIndexOfMoreSuggestions) {
            return false;
        }
        int width = getWidth();
        View view = this.mMoreSuggestionsContainer;
        int paddingLeft = (width - view.getPaddingLeft()) - view.getPaddingRight();
        MoreSuggestions.Builder builder = this.mMoreSuggestionsBuilder;
        builder.layout(this.mSuggestedWords, this.mStartIndexOfMoreSuggestions, paddingLeft, (int) (paddingLeft * suggestionStripLayoutHelper.mMinMoreSuggestionsWidth), suggestionStripLayoutHelper.getMaxMoreSuggestionsRow(), keyboard);
        this.mMoreSuggestionsView.setKeyboard(builder.build());
        view.measure(-2, -2);
        this.mMoreSuggestionsView.showMoreKeysPanel(this, this.mMoreSuggestionsController, width / 2, -suggestionStripLayoutHelper.mMoreSuggestionsBottomGap, this.mMoreSuggestionsListener);
        this.mOriginX = this.mLastX;
        this.mOriginY = this.mLastY;
        for (int i = 0; i < this.mStartIndexOfMoreSuggestions; i++) {
            this.mWordViews.get(i).setPressed(false);
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(final MotionEvent me) {
        if (this.mStripVisibilityGroup.isShowingImportantNoticeStrip()) {
            return false;
        }
        if (!this.mMoreSuggestionsView.isShowingInParent()) {
            this.mLastX = (int) me.getX();
            this.mLastY = (int) me.getY();
            return this.mMoreSuggestionsSlidingDetector.onTouchEvent(me);
        }
        if (this.mMoreSuggestionsView.isInModalMode()) {
            return false;
        }
        int action = me.getAction();
        int actionIndex = me.getActionIndex();
        int x = (int) me.getX(actionIndex);
        int y = (int) me.getY(actionIndex);
        int iAbs = Math.abs(x - this.mOriginX);
        int i = this.mMoreSuggestionsModalTolerance;
        if (iAbs >= i || this.mOriginY - y >= i) {
            this.mNeedsToTransformTouchEventToHoverEvent = AccessibilityUtils.getInstance().isTouchExplorationEnabled();
            this.mIsDispatchingHoverEventToMoreSuggestions = false;
            return true;
        }
        if (action == 1 || action == 6) {
            this.mMoreSuggestionsView.setModalMode();
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(final MotionEvent me) {
        int i;
        if (!this.mMoreSuggestionsView.isShowingInParent()) {
            return true;
        }
        int actionIndex = me.getActionIndex();
        int iTranslateX = this.mMoreSuggestionsView.translateX((int) me.getX(actionIndex));
        int iTranslateY = this.mMoreSuggestionsView.translateY((int) me.getY(actionIndex));
        me.setLocation(iTranslateX, iTranslateY);
        if (!this.mNeedsToTransformTouchEventToHoverEvent) {
            this.mMoreSuggestionsView.onTouchEvent(me);
            return true;
        }
        boolean z = iTranslateX >= 0 && iTranslateX < this.mMoreSuggestionsView.getWidth() && iTranslateY >= 0 && iTranslateY < this.mMoreSuggestionsView.getHeight();
        if (!z && !this.mIsDispatchingHoverEventToMoreSuggestions) {
            return true;
        }
        if (z && !this.mIsDispatchingHoverEventToMoreSuggestions) {
            this.mIsDispatchingHoverEventToMoreSuggestions = true;
            i = 9;
        } else if (me.getActionMasked() == 1) {
            this.mIsDispatchingHoverEventToMoreSuggestions = false;
            this.mNeedsToTransformTouchEventToHoverEvent = false;
            i = 10;
        } else {
            i = 7;
        }
        me.setAction(i);
        this.mMoreSuggestionsView.onHoverEvent(me);
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
        int iIntValue;
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(-15, this);
        if (view == this.mImportantNoticeStrip) {
            this.mListener.showImportantNoticeContents();
            return;
        }
        if (view == this.mVoiceKey) {
            this.mListener.onCodeInput(-7, -2, -2, false);
            return;
        }
        if (view == this.mHideIMEKeyRight) {
            this.mListener.onHideIME();
            return;
        }
        Object tag = view.getTag();
        if (!(tag instanceof Integer) || (iIntValue = ((Integer) tag).intValue()) >= this.mSuggestedWords.size()) {
            return;
        }
        this.mListener.pickSuggestionManually(this.mSuggestedWords.getInfo(iIntValue));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissMoreSuggestionsPanel();
    }

    @Override // android.view.View
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        if (oldw > 0 || w <= 0) {
            return;
        }
        maybeShowImportantNoticeTitle();
    }
}
