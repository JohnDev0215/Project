package com.android.inputmethod.keyboard.emoji;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyboardActionListener;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.keyboard.emoji.EmojiCategory;
import com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.keyboard.internal.KeyVisualAttributes;
import com.android.inputmethod.keyboard.internal.KeyboardIconsSet;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.byd.ime.ResModifier;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class EmojiPalettesView extends LinearLayout implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener, View.OnTouchListener, EmojiPageKeyboardView.OnKeyEventListener {
    private TextView mAlphabetKeyLeft;
    private TextView mAlphabetKeyRight;
    private final int mCategoryIndicatorBackgroundResId;
    private final int mCategoryIndicatorDrawableResId;
    private final boolean mCategoryIndicatorEnabled;
    private final int mCategoryPageIndicatorBackground;
    private final int mCategoryPageIndicatorColor;
    private int mCurrentPagerPosition;
    private ImageButton mDeleteKey;
    private final DeleteKeyOnTouchListener mDeleteKeyOnTouchListener;
    private final EmojiCategory mEmojiCategory;
    private EmojiCategoryPageIndicatorView mEmojiCategoryPageIndicatorView;
    private final EmojiLayoutParams mEmojiLayoutParams;
    private ViewPager mEmojiPager;
    private EmojiPalettesAdapter mEmojiPalettesAdapter;
    private final int mFunctionalKeyBackgroundId;
    private KeyboardActionListener mKeyboardActionListener;
    private View mSpacebar;
    private final int mSpacebarBackgroundId;
    private View mSpacebarIcon;
    private TabHost mTabHost;
    private boolean onTabChangedWhenInflate;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(final int state) {
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public EmojiPalettesView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.emojiPalettesViewStyle);
    }

    public EmojiPalettesView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentPagerPosition = 0;
        this.mKeyboardActionListener = KeyboardActionListener.EMPTY_LISTENER;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.KeyboardView, defStyle, R.style.KeyboardView);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.mFunctionalKeyBackgroundId = typedArrayObtainStyledAttributes.getResourceId(0, resourceId);
        this.mSpacebarBackgroundId = typedArrayObtainStyledAttributes.getResourceId(7, resourceId);
        typedArrayObtainStyledAttributes.recycle();
        KeyboardLayoutSet.Builder builder = new KeyboardLayoutSet.Builder(context, null);
        Resources resources = context.getResources();
        EmojiLayoutParams emojiLayoutParams = new EmojiLayoutParams(resources);
        this.mEmojiLayoutParams = emojiLayoutParams;
        builder.setSubtype(RichInputMethodSubtype.getEmojiSubtype());
        builder.setKeyboardGeometry(ResourceUtils.getDefaultKeyboardWidth(resources), emojiLayoutParams.mEmojiKeyboardHeight);
        KeyboardLayoutSet keyboardLayoutSetBuild = builder.build();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attrs, R.styleable.EmojiPalettesView, defStyle, R.style.EmojiPalettesView);
        this.mEmojiCategory = new EmojiCategory(PreferenceManager.getDefaultSharedPreferences(context), resources, keyboardLayoutSetBuild, typedArrayObtainStyledAttributes2);
        this.mCategoryIndicatorEnabled = typedArrayObtainStyledAttributes2.getBoolean(2, false);
        this.mCategoryIndicatorDrawableResId = typedArrayObtainStyledAttributes2.getResourceId(1, 0);
        this.mCategoryIndicatorBackgroundResId = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
        this.mCategoryPageIndicatorColor = typedArrayObtainStyledAttributes2.getColor(4, 0);
        this.mCategoryPageIndicatorBackground = typedArrayObtainStyledAttributes2.getColor(3, 0);
        typedArrayObtainStyledAttributes2.recycle();
        this.mDeleteKeyOnTouchListener = new DeleteKeyOnTouchListener();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Resources resources = getContext().getResources();
        setMeasuredDimension(ResourceUtils.getDefaultKeyboardWidth(resources) + getPaddingLeft() + getPaddingRight(), ResourceUtils.getDefaultKeyboardHeight(resources) + resources.getDimensionPixelSize(R.dimen.config_suggestions_strip_height) + getPaddingTop() + getPaddingBottom());
    }

    private void addTab(final TabHost host, final int categoryId) {
        TabHost.TabSpec tabSpecNewTabSpec = host.newTabSpec(EmojiCategory.getCategoryName(categoryId, 0));
        tabSpecNewTabSpec.setContent(R.id.emoji_keyboard_dummy);
        ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.emoji_keyboard_tab_icon, (ViewGroup) null);
        imageView.setBackgroundResource(ResModifier.getEmojiTabBackground(getContext()));
        imageView.setImageResource(this.mEmojiCategory.getCategoryTabIcon(categoryId));
        imageView.setContentDescription(this.mEmojiCategory.getAccessibilityDescription(categoryId));
        tabSpecNewTabSpec.setIndicator(imageView);
        host.addTab(tabSpecNewTabSpec);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        this.onTabChangedWhenInflate = true;
        TabHost tabHost = (TabHost) findViewById(R.id.emoji_category_tabhost);
        this.mTabHost = tabHost;
        tabHost.setup();
        Iterator<EmojiCategory.CategoryProperties> it = this.mEmojiCategory.getShownCategories().iterator();
        while (it.hasNext()) {
            addTab(this.mTabHost, it.next().mCategoryId);
        }
        this.mTabHost.setOnTabChangedListener(this);
        TabWidget tabWidget = this.mTabHost.getTabWidget();
        tabWidget.setStripEnabled(this.mCategoryIndicatorEnabled);
        if (this.mCategoryIndicatorEnabled) {
            tabWidget.setBackgroundResource(this.mCategoryIndicatorDrawableResId);
            tabWidget.setLeftStripDrawable(this.mCategoryIndicatorBackgroundResId);
            tabWidget.setRightStripDrawable(this.mCategoryIndicatorBackgroundResId);
        }
        this.mEmojiPalettesAdapter = new EmojiPalettesAdapter(this.mEmojiCategory, this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.emoji_keyboard_pager);
        this.mEmojiPager = viewPager;
        viewPager.setAdapter(this.mEmojiPalettesAdapter);
        this.mEmojiPager.setOnPageChangeListener(this);
        this.mEmojiPager.setOffscreenPageLimit(0);
        this.mEmojiPager.setPersistentDrawingCache(0);
        this.mEmojiLayoutParams.setPagerProperties(this.mEmojiPager);
        EmojiCategoryPageIndicatorView emojiCategoryPageIndicatorView = (EmojiCategoryPageIndicatorView) findViewById(R.id.emoji_category_page_id_view);
        this.mEmojiCategoryPageIndicatorView = emojiCategoryPageIndicatorView;
        emojiCategoryPageIndicatorView.setColors(this.mCategoryPageIndicatorColor, this.mCategoryPageIndicatorBackground);
        this.mEmojiLayoutParams.setCategoryPageIdViewProperties(this.mEmojiCategoryPageIndicatorView);
        setCurrentCategoryId(this.mEmojiCategory.getCurrentCategoryId(), true);
        this.mEmojiLayoutParams.setActionBarProperties((LinearLayout) findViewById(R.id.emoji_action_bar));
        ImageButton imageButton = (ImageButton) findViewById(R.id.emoji_keyboard_delete);
        this.mDeleteKey = imageButton;
        imageButton.setBackgroundResource(this.mFunctionalKeyBackgroundId);
        this.mDeleteKey.setTag(-5);
        this.mDeleteKey.setOnTouchListener(this.mDeleteKeyOnTouchListener);
        findViewById(R.id.delete_parent).setBackgroundColor(this.mCategoryPageIndicatorBackground);
        TextView textView = (TextView) findViewById(R.id.emoji_keyboard_alphabet_left);
        this.mAlphabetKeyLeft = textView;
        textView.setBackgroundResource(this.mFunctionalKeyBackgroundId);
        this.mAlphabetKeyLeft.setTag(-14);
        this.mAlphabetKeyLeft.setOnTouchListener(this);
        this.mAlphabetKeyLeft.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(R.id.emoji_keyboard_alphabet_right);
        this.mAlphabetKeyRight = textView2;
        textView2.setBackgroundResource(this.mFunctionalKeyBackgroundId);
        this.mAlphabetKeyRight.setTag(-14);
        this.mAlphabetKeyRight.setOnTouchListener(this);
        this.mAlphabetKeyRight.setOnClickListener(this);
        View viewFindViewById = findViewById(R.id.emoji_keyboard_space);
        this.mSpacebar = viewFindViewById;
        viewFindViewById.setBackgroundResource(this.mSpacebarBackgroundId);
        this.mSpacebar.setTag(32);
        this.mSpacebar.setOnTouchListener(this);
        this.mSpacebar.setOnClickListener(this);
        this.mEmojiLayoutParams.setKeyProperties(this.mSpacebar);
        this.mSpacebarIcon = findViewById(R.id.emoji_keyboard_space_icon);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(final String tabId) {
        if (!this.onTabChangedWhenInflate) {
            AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(-15, this);
        }
        this.onTabChangedWhenInflate = false;
        setCurrentCategoryId(this.mEmojiCategory.getCategoryId(tabId), false);
        updateEmojiCategoryPageIdView();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(final int position) {
        Pair<Integer, Integer> categoryIdAndPageIdFromPagePosition = this.mEmojiCategory.getCategoryIdAndPageIdFromPagePosition(position);
        setCurrentCategoryId(((Integer) categoryIdAndPageIdFromPagePosition.first).intValue(), false);
        this.mEmojiCategory.setCurrentCategoryPageId(((Integer) categoryIdAndPageIdFromPagePosition.second).intValue());
        updateEmojiCategoryPageIdView();
        this.mCurrentPagerPosition = position;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        this.mEmojiPalettesAdapter.onPageScrolled();
        Pair<Integer, Integer> categoryIdAndPageIdFromPagePosition = this.mEmojiCategory.getCategoryIdAndPageIdFromPagePosition(position);
        int iIntValue = ((Integer) categoryIdAndPageIdFromPagePosition.first).intValue();
        int categoryPageSize = this.mEmojiCategory.getCategoryPageSize(iIntValue);
        int currentCategoryId = this.mEmojiCategory.getCurrentCategoryId();
        int currentCategoryPageId = this.mEmojiCategory.getCurrentCategoryPageId();
        int currentCategoryPageSize = this.mEmojiCategory.getCurrentCategoryPageSize();
        if (iIntValue == currentCategoryId) {
            this.mEmojiCategoryPageIndicatorView.setCategoryPageId(categoryPageSize, ((Integer) categoryIdAndPageIdFromPagePosition.second).intValue(), positionOffset);
        } else if (iIntValue > currentCategoryId) {
            this.mEmojiCategoryPageIndicatorView.setCategoryPageId(currentCategoryPageSize, currentCategoryPageId, positionOffset);
        } else if (iIntValue < currentCategoryId) {
            this.mEmojiCategoryPageIndicatorView.setCategoryPageId(currentCategoryPageSize, currentCategoryPageId, positionOffset - 1.0f);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getActionMasked() != 0) {
            return false;
        }
        Object tag = v.getTag();
        if (!(tag instanceof Integer)) {
            return false;
        }
        this.mKeyboardActionListener.onPressKey(((Integer) tag).intValue(), 0, true);
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof Integer) {
            int iIntValue = ((Integer) tag).intValue();
            this.mKeyboardActionListener.onCodeInput(iIntValue, -1, -1, false);
            this.mKeyboardActionListener.onReleaseKey(iIntValue, false);
        }
    }

    @Override // com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.OnKeyEventListener
    public void onPressKey(final Key key) {
        this.mKeyboardActionListener.onPressKey(key.getCode(), 0, true);
    }

    @Override // com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.OnKeyEventListener
    public void onReleaseKey(final Key key) {
        this.mEmojiPalettesAdapter.addRecentKey(key);
        this.mEmojiCategory.saveLastTypedCategoryPage();
        int code = key.getCode();
        if (code == -4) {
            this.mKeyboardActionListener.onTextInput(key.getOutputText());
        } else {
            this.mKeyboardActionListener.onCodeInput(code, -1, -1, false);
        }
        this.mKeyboardActionListener.onReleaseKey(code, false);
    }

    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        if (enabled) {
            setLayerType(2, null);
        }
    }

    private static void setupAlphabetKey(final TextView alphabetKey, final String label, final KeyDrawParams params) {
        alphabetKey.setText(label);
        alphabetKey.setTextColor(params.mFunctionalTextColor);
        alphabetKey.setTextSize(0, params.mLabelSize);
        alphabetKey.setTypeface(params.mTypeface);
    }

    public void startEmojiPalettes(final String switchToAlphaLabel, final KeyVisualAttributes keyVisualAttr, final KeyboardIconsSet iconSet) {
        int iconResourceId = iconSet.getIconResourceId(KeyboardIconsSet.NAME_DELETE_KEY);
        if (iconResourceId != 0) {
            this.mDeleteKey.setImageResource(iconResourceId);
        }
        int iconResourceId2 = iconSet.getIconResourceId(KeyboardIconsSet.NAME_SPACE_KEY);
        if (iconResourceId2 != 0) {
            this.mSpacebarIcon.setBackgroundResource(iconResourceId2);
        }
        KeyDrawParams keyDrawParams = new KeyDrawParams();
        keyDrawParams.updateParams(this.mEmojiLayoutParams.getActionBarHeight(), keyVisualAttr);
        setupAlphabetKey(this.mAlphabetKeyLeft, switchToAlphaLabel, keyDrawParams);
        setupAlphabetKey(this.mAlphabetKeyRight, switchToAlphaLabel, keyDrawParams);
        this.mEmojiPager.setAdapter(this.mEmojiPalettesAdapter);
        this.mEmojiPager.setCurrentItem(this.mCurrentPagerPosition);
    }

    public void stopEmojiPalettes() {
        this.mEmojiPalettesAdapter.releaseCurrentKey(true);
        this.mEmojiPalettesAdapter.flushPendingRecentKeys();
        this.mEmojiPager.setAdapter(null);
    }

    public void setKeyboardActionListener(final KeyboardActionListener listener) {
        this.mKeyboardActionListener = listener;
        this.mDeleteKeyOnTouchListener.setKeyboardActionListener(listener);
    }

    private void updateEmojiCategoryPageIdView() {
        EmojiCategoryPageIndicatorView emojiCategoryPageIndicatorView = this.mEmojiCategoryPageIndicatorView;
        if (emojiCategoryPageIndicatorView == null) {
            return;
        }
        emojiCategoryPageIndicatorView.setCategoryPageId(this.mEmojiCategory.getCurrentCategoryPageSize(), this.mEmojiCategory.getCurrentCategoryPageId(), 0.0f);
    }

    private void setCurrentCategoryId(final int categoryId, final boolean force) {
        int currentCategoryId = this.mEmojiCategory.getCurrentCategoryId();
        if (currentCategoryId != categoryId || force) {
            if (currentCategoryId == 0) {
                this.mEmojiPalettesAdapter.flushPendingRecentKeys();
            }
            this.mEmojiCategory.setCurrentCategoryId(categoryId);
            int tabIdFromCategoryId = this.mEmojiCategory.getTabIdFromCategoryId(categoryId);
            int pageIdFromCategoryId = this.mEmojiCategory.getPageIdFromCategoryId(categoryId);
            if (force || ((Integer) this.mEmojiCategory.getCategoryIdAndPageIdFromPagePosition(this.mEmojiPager.getCurrentItem()).first).intValue() != categoryId) {
                this.mEmojiPager.setCurrentItem(pageIdFromCategoryId, false);
            }
            if (force || this.mTabHost.getCurrentTab() != tabIdFromCategoryId) {
                this.mTabHost.setCurrentTab(tabIdFromCategoryId);
            }
        }
    }

    private static class DeleteKeyOnTouchListener implements View.OnTouchListener {
        private KeyboardActionListener mKeyboardActionListener;

        private DeleteKeyOnTouchListener() {
            this.mKeyboardActionListener = KeyboardActionListener.EMPTY_LISTENER;
        }

        public void setKeyboardActionListener(final KeyboardActionListener listener) {
            this.mKeyboardActionListener = listener;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(final View v, final MotionEvent event) {
            int actionMasked = event.getActionMasked();
            if (actionMasked == 0) {
                onTouchDown(v);
                return true;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float x = event.getX();
                    float y = event.getY();
                    if (x < 0.0f || v.getWidth() < x || y < 0.0f || v.getHeight() < y) {
                        onTouchCanceled(v);
                    }
                    return true;
                }
                if (actionMasked != 3) {
                    return false;
                }
            }
            onTouchUp(v);
            return true;
        }

        private void onTouchDown(final View v) {
            this.mKeyboardActionListener.onPressKey(-5, 0, true);
            v.setPressed(true);
        }

        private void onTouchUp(final View v) {
            this.mKeyboardActionListener.onCodeInput(-5, -1, -1, false);
            this.mKeyboardActionListener.onReleaseKey(-5, false);
            v.setPressed(false);
        }

        private void onTouchCanceled(final View v) {
            v.setBackgroundColor(0);
        }
    }
}
