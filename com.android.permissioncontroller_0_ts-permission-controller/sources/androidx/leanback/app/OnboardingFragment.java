package androidx.leanback.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Property;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.leanback.R;
import androidx.leanback.widget.PagingIndicator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class OnboardingFragment extends Fragment {
    private static final boolean DEBUG = false;
    private static final long DESCRIPTION_START_DELAY_MS = 33;
    private static final long HEADER_ANIMATION_DURATION_MS = 417;
    private static final long HEADER_APPEAR_DELAY_MS = 500;
    private static final TimeInterpolator HEADER_APPEAR_INTERPOLATOR = new DecelerateInterpolator();
    private static final TimeInterpolator HEADER_DISAPPEAR_INTERPOLATOR = new AccelerateInterpolator();
    private static final String KEY_CURRENT_PAGE_INDEX = "leanback.onboarding.current_page_index";
    private static final String KEY_ENTER_ANIMATION_FINISHED = "leanback.onboarding.enter_animation_finished";
    private static final String KEY_LOGO_ANIMATION_FINISHED = "leanback.onboarding.logo_animation_finished";
    private static final long LOGO_SPLASH_PAUSE_DURATION_MS = 1333;
    private static final int SLIDE_DISTANCE = 60;
    private static final String TAG = "OnboardingF";
    private static int sSlideDistance;
    private AnimatorSet mAnimator;
    private boolean mArrowBackgroundColorSet;
    private boolean mArrowColorSet;
    int mCurrentPageIndex;
    TextView mDescriptionView;
    private boolean mDescriptionViewTextColorSet;
    private boolean mDotBackgroundColorSet;
    boolean mEnterAnimationFinished;
    private int mIconResourceId;
    boolean mIsLtr;
    boolean mLogoAnimationFinished;
    private int mLogoResourceId;
    private ImageView mLogoView;
    private ImageView mMainIconView;
    PagingIndicator mPageIndicator;
    View mStartButton;
    private CharSequence mStartButtonText;
    private boolean mStartButtonTextSet;
    private ContextThemeWrapper mThemeWrapper;
    TextView mTitleView;
    private boolean mTitleViewTextColorSet;
    private int mTitleViewTextColor = 0;
    private int mDescriptionViewTextColor = 0;
    private int mDotBackgroundColor = 0;
    private int mArrowColor = 0;
    private int mArrowBackgroundColor = 0;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: androidx.leanback.app.OnboardingFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!OnboardingFragment.this.mLogoAnimationFinished) {
                return;
            }
            if (OnboardingFragment.this.mCurrentPageIndex == OnboardingFragment.this.getPageCount() - 1) {
                OnboardingFragment.this.onFinishFragment();
            } else {
                OnboardingFragment.this.moveToNextPage();
            }
        }
    };
    private final View.OnKeyListener mOnKeyListener = new View.OnKeyListener() { // from class: androidx.leanback.app.OnboardingFragment.2
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (!OnboardingFragment.this.mLogoAnimationFinished) {
                return keyCode != 4;
            }
            if (event.getAction() == 0) {
                return false;
            }
            if (keyCode == 4) {
                if (OnboardingFragment.this.mCurrentPageIndex == 0) {
                    return false;
                }
                OnboardingFragment.this.moveToPreviousPage();
                return true;
            }
            if (keyCode == 21) {
                if (OnboardingFragment.this.mIsLtr) {
                    OnboardingFragment.this.moveToPreviousPage();
                } else {
                    OnboardingFragment.this.moveToNextPage();
                }
                return true;
            }
            if (keyCode != 22) {
                return false;
            }
            if (OnboardingFragment.this.mIsLtr) {
                OnboardingFragment.this.moveToNextPage();
            } else {
                OnboardingFragment.this.moveToPreviousPage();
            }
            return true;
        }
    };

    protected abstract int getPageCount();

    protected abstract CharSequence getPageDescription(int i);

    protected abstract CharSequence getPageTitle(int i);

    protected abstract View onCreateBackgroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract View onCreateForegroundView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected void moveToPreviousPage() {
        int i;
        if (this.mLogoAnimationFinished && (i = this.mCurrentPageIndex) > 0) {
            int i2 = i - 1;
            this.mCurrentPageIndex = i2;
            onPageChangedInternal(i2 + 1);
        }
    }

    protected void moveToNextPage() {
        if (this.mLogoAnimationFinished && this.mCurrentPageIndex < getPageCount() - 1) {
            int i = this.mCurrentPageIndex + 1;
            this.mCurrentPageIndex = i;
            onPageChangedInternal(i - 1);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resolveTheme();
        LayoutInflater localInflater = getThemeInflater(inflater);
        ViewGroup view = (ViewGroup) localInflater.inflate(R.layout.lb_onboarding_fragment, container, false);
        this.mIsLtr = getResources().getConfiguration().getLayoutDirection() == 0;
        PagingIndicator pagingIndicator = (PagingIndicator) view.findViewById(R.id.page_indicator);
        this.mPageIndicator = pagingIndicator;
        pagingIndicator.setOnClickListener(this.mOnClickListener);
        this.mPageIndicator.setOnKeyListener(this.mOnKeyListener);
        View viewFindViewById = view.findViewById(R.id.button_start);
        this.mStartButton = viewFindViewById;
        viewFindViewById.setOnClickListener(this.mOnClickListener);
        this.mStartButton.setOnKeyListener(this.mOnKeyListener);
        this.mMainIconView = (ImageView) view.findViewById(R.id.main_icon);
        this.mLogoView = (ImageView) view.findViewById(R.id.logo);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mDescriptionView = (TextView) view.findViewById(R.id.description);
        if (this.mTitleViewTextColorSet) {
            this.mTitleView.setTextColor(this.mTitleViewTextColor);
        }
        if (this.mDescriptionViewTextColorSet) {
            this.mDescriptionView.setTextColor(this.mDescriptionViewTextColor);
        }
        if (this.mDotBackgroundColorSet) {
            this.mPageIndicator.setDotBackgroundColor(this.mDotBackgroundColor);
        }
        if (this.mArrowColorSet) {
            this.mPageIndicator.setArrowColor(this.mArrowColor);
        }
        if (this.mArrowBackgroundColorSet) {
            this.mPageIndicator.setDotBackgroundColor(this.mArrowBackgroundColor);
        }
        if (this.mStartButtonTextSet) {
            ((Button) this.mStartButton).setText(this.mStartButtonText);
        }
        Context context = FragmentUtil.getContext(this);
        if (sSlideDistance == 0) {
            sSlideDistance = (int) (context.getResources().getDisplayMetrics().scaledDensity * 60.0f);
        }
        view.requestFocus();
        return view;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            this.mCurrentPageIndex = 0;
            this.mLogoAnimationFinished = false;
            this.mEnterAnimationFinished = false;
            this.mPageIndicator.onPageSelected(0, false);
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.leanback.app.OnboardingFragment.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    OnboardingFragment.this.getView().getViewTreeObserver().removeOnPreDrawListener(this);
                    if (!OnboardingFragment.this.startLogoAnimation()) {
                        OnboardingFragment.this.mLogoAnimationFinished = true;
                        OnboardingFragment.this.onLogoAnimationFinished();
                    }
                    return true;
                }
            });
            return;
        }
        this.mCurrentPageIndex = savedInstanceState.getInt(KEY_CURRENT_PAGE_INDEX);
        this.mLogoAnimationFinished = savedInstanceState.getBoolean(KEY_LOGO_ANIMATION_FINISHED);
        this.mEnterAnimationFinished = savedInstanceState.getBoolean(KEY_ENTER_ANIMATION_FINISHED);
        if (!this.mLogoAnimationFinished) {
            if (!startLogoAnimation()) {
                this.mLogoAnimationFinished = true;
                onLogoAnimationFinished();
                return;
            }
            return;
        }
        onLogoAnimationFinished();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PAGE_INDEX, this.mCurrentPageIndex);
        outState.putBoolean(KEY_LOGO_ANIMATION_FINISHED, this.mLogoAnimationFinished);
        outState.putBoolean(KEY_ENTER_ANIMATION_FINISHED, this.mEnterAnimationFinished);
    }

    public void setTitleViewTextColor(int color) {
        this.mTitleViewTextColor = color;
        this.mTitleViewTextColorSet = true;
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    public final int getTitleViewTextColor() {
        return this.mTitleViewTextColor;
    }

    public void setDescriptionViewTextColor(int color) {
        this.mDescriptionViewTextColor = color;
        this.mDescriptionViewTextColorSet = true;
        TextView textView = this.mDescriptionView;
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    public final int getDescriptionViewTextColor() {
        return this.mDescriptionViewTextColor;
    }

    public void setDotBackgroundColor(int color) {
        this.mDotBackgroundColor = color;
        this.mDotBackgroundColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setDotBackgroundColor(color);
        }
    }

    public final int getDotBackgroundColor() {
        return this.mDotBackgroundColor;
    }

    public void setArrowColor(int color) {
        this.mArrowColor = color;
        this.mArrowColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setArrowColor(color);
        }
    }

    public final int getArrowColor() {
        return this.mArrowColor;
    }

    public void setArrowBackgroundColor(int color) {
        this.mArrowBackgroundColor = color;
        this.mArrowBackgroundColorSet = true;
        PagingIndicator pagingIndicator = this.mPageIndicator;
        if (pagingIndicator != null) {
            pagingIndicator.setArrowBackgroundColor(color);
        }
    }

    public final int getArrowBackgroundColor() {
        return this.mArrowBackgroundColor;
    }

    public final CharSequence getStartButtonText() {
        return this.mStartButtonText;
    }

    public void setStartButtonText(CharSequence text) {
        this.mStartButtonText = text;
        this.mStartButtonTextSet = true;
        View view = this.mStartButton;
        if (view != null) {
            ((Button) view).setText(text);
        }
    }

    public int onProvideTheme() {
        return -1;
    }

    private void resolveTheme() {
        Context context = FragmentUtil.getContext(this);
        int theme = onProvideTheme();
        if (theme == -1) {
            int resId = R.attr.onboardingTheme;
            TypedValue typedValue = new TypedValue();
            boolean found = context.getTheme().resolveAttribute(resId, typedValue, true);
            if (found) {
                this.mThemeWrapper = new ContextThemeWrapper(context, typedValue.resourceId);
                return;
            }
            return;
        }
        this.mThemeWrapper = new ContextThemeWrapper(context, theme);
    }

    private LayoutInflater getThemeInflater(LayoutInflater inflater) {
        ContextThemeWrapper contextThemeWrapper = this.mThemeWrapper;
        return contextThemeWrapper == null ? inflater : inflater.cloneInContext(contextThemeWrapper);
    }

    public final void setLogoResourceId(int id) {
        this.mLogoResourceId = id;
    }

    public final int getLogoResourceId() {
        return this.mLogoResourceId;
    }

    protected Animator onCreateLogoAnimation() {
        return null;
    }

    boolean startLogoAnimation() {
        Animator animator;
        final Context context = FragmentUtil.getContext(this);
        if (context == null) {
            return false;
        }
        if (this.mLogoResourceId != 0) {
            this.mLogoView.setVisibility(0);
            this.mLogoView.setImageResource(this.mLogoResourceId);
            Animator inAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_enter);
            Animator outAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_logo_exit);
            outAnimator.setStartDelay(LOGO_SPLASH_PAUSE_DURATION_MS);
            AnimatorSet logoAnimator = new AnimatorSet();
            logoAnimator.playSequentially(inAnimator, outAnimator);
            logoAnimator.setTarget(this.mLogoView);
            animator = logoAnimator;
        } else {
            animator = onCreateLogoAnimation();
        }
        if (animator == null) {
            return false;
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.leanback.app.OnboardingFragment.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (context != null) {
                    OnboardingFragment.this.mLogoAnimationFinished = true;
                    OnboardingFragment.this.onLogoAnimationFinished();
                }
            }
        });
        animator.start();
        return true;
    }

    protected Animator onCreateEnterAnimation() {
        return null;
    }

    void hideLogoView() {
        this.mLogoView.setVisibility(8);
        int i = this.mIconResourceId;
        if (i != 0) {
            this.mMainIconView.setImageResource(i);
            this.mMainIconView.setVisibility(0);
        }
        View container = getView();
        LayoutInflater inflater = getThemeInflater(LayoutInflater.from(FragmentUtil.getContext(this)));
        ViewGroup backgroundContainer = (ViewGroup) container.findViewById(R.id.background_container);
        View background = onCreateBackgroundView(inflater, backgroundContainer);
        if (background != null) {
            backgroundContainer.setVisibility(0);
            backgroundContainer.addView(background);
        }
        ViewGroup contentContainer = (ViewGroup) container.findViewById(R.id.content_container);
        View content = onCreateContentView(inflater, contentContainer);
        if (content != null) {
            contentContainer.setVisibility(0);
            contentContainer.addView(content);
        }
        ViewGroup foregroundContainer = (ViewGroup) container.findViewById(R.id.foreground_container);
        View foreground = onCreateForegroundView(inflater, foregroundContainer);
        if (foreground != null) {
            foregroundContainer.setVisibility(0);
            foregroundContainer.addView(foreground);
        }
        container.findViewById(R.id.page_container).setVisibility(0);
        container.findViewById(R.id.content_container).setVisibility(0);
        if (getPageCount() > 1) {
            this.mPageIndicator.setPageCount(getPageCount());
            this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, false);
        }
        if (this.mCurrentPageIndex == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
        } else {
            this.mPageIndicator.setVisibility(0);
        }
        this.mTitleView.setText(getPageTitle(this.mCurrentPageIndex));
        this.mDescriptionView.setText(getPageDescription(this.mCurrentPageIndex));
    }

    protected void onLogoAnimationFinished() {
        startEnterAnimation(false);
    }

    protected final void startEnterAnimation(boolean force) {
        Context context = FragmentUtil.getContext(this);
        if (context == null) {
            return;
        }
        hideLogoView();
        if (this.mEnterAnimationFinished && !force) {
            return;
        }
        List<Animator> animators = new ArrayList<>();
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_enter);
        animator.setTarget(getPageCount() <= 1 ? this.mStartButton : this.mPageIndicator);
        animators.add(animator);
        Animator animator2 = onCreateTitleAnimator();
        if (animator2 != null) {
            animator2.setTarget(this.mTitleView);
            animators.add(animator2);
        }
        Animator animator3 = onCreateDescriptionAnimator();
        if (animator3 != null) {
            animator3.setTarget(this.mDescriptionView);
            animators.add(animator3);
        }
        Animator customAnimator = onCreateEnterAnimation();
        if (customAnimator != null) {
            animators.add(customAnimator);
        }
        if (animators.isEmpty()) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimator = animatorSet;
        animatorSet.playTogether(animators);
        this.mAnimator.start();
        this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: androidx.leanback.app.OnboardingFragment.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                OnboardingFragment.this.mEnterAnimationFinished = true;
            }
        });
        getView().requestFocus();
    }

    protected Animator onCreateDescriptionAnimator() {
        return AnimatorInflater.loadAnimator(FragmentUtil.getContext(this), R.animator.lb_onboarding_description_enter);
    }

    protected Animator onCreateTitleAnimator() {
        return AnimatorInflater.loadAnimator(FragmentUtil.getContext(this), R.animator.lb_onboarding_title_enter);
    }

    protected final boolean isLogoAnimationFinished() {
        return this.mLogoAnimationFinished;
    }

    protected final int getCurrentPageIndex() {
        return this.mCurrentPageIndex;
    }

    protected void onFinishFragment() {
    }

    private void onPageChangedInternal(int previousPage) {
        Animator fadeAnimator;
        AnimatorSet animatorSet = this.mAnimator;
        if (animatorSet != null) {
            animatorSet.end();
        }
        this.mPageIndicator.onPageSelected(this.mCurrentPageIndex, true);
        List<Animator> animators = new ArrayList<>();
        if (previousPage < getCurrentPageIndex()) {
            animators.add(createAnimator(this.mTitleView, false, GravityCompat.START, 0L));
            Animator animatorCreateAnimator = createAnimator(this.mDescriptionView, false, GravityCompat.START, DESCRIPTION_START_DELAY_MS);
            fadeAnimator = animatorCreateAnimator;
            animators.add(animatorCreateAnimator);
            animators.add(createAnimator(this.mTitleView, true, GravityCompat.END, HEADER_APPEAR_DELAY_MS));
            animators.add(createAnimator(this.mDescriptionView, true, GravityCompat.END, 533L));
        } else {
            animators.add(createAnimator(this.mTitleView, false, GravityCompat.END, 0L));
            Animator animatorCreateAnimator2 = createAnimator(this.mDescriptionView, false, GravityCompat.END, DESCRIPTION_START_DELAY_MS);
            fadeAnimator = animatorCreateAnimator2;
            animators.add(animatorCreateAnimator2);
            animators.add(createAnimator(this.mTitleView, true, GravityCompat.START, HEADER_APPEAR_DELAY_MS));
            animators.add(createAnimator(this.mDescriptionView, true, GravityCompat.START, 533L));
        }
        final int currentPageIndex = getCurrentPageIndex();
        fadeAnimator.addListener(new AnimatorListenerAdapter() { // from class: androidx.leanback.app.OnboardingFragment.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                OnboardingFragment.this.mTitleView.setText(OnboardingFragment.this.getPageTitle(currentPageIndex));
                OnboardingFragment.this.mDescriptionView.setText(OnboardingFragment.this.getPageDescription(currentPageIndex));
            }
        });
        Context context = FragmentUtil.getContext(this);
        if (getCurrentPageIndex() == getPageCount() - 1) {
            this.mStartButton.setVisibility(0);
            Animator navigatorFadeOutAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_out);
            navigatorFadeOutAnimator.setTarget(this.mPageIndicator);
            navigatorFadeOutAnimator.addListener(new AnimatorListenerAdapter() { // from class: androidx.leanback.app.OnboardingFragment.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    OnboardingFragment.this.mPageIndicator.setVisibility(8);
                }
            });
            animators.add(navigatorFadeOutAnimator);
            Animator buttonFadeInAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_in);
            buttonFadeInAnimator.setTarget(this.mStartButton);
            animators.add(buttonFadeInAnimator);
        } else if (previousPage == getPageCount() - 1) {
            this.mPageIndicator.setVisibility(0);
            Animator navigatorFadeInAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_page_indicator_fade_in);
            navigatorFadeInAnimator.setTarget(this.mPageIndicator);
            animators.add(navigatorFadeInAnimator);
            Animator buttonFadeOutAnimator = AnimatorInflater.loadAnimator(context, R.animator.lb_onboarding_start_button_fade_out);
            buttonFadeOutAnimator.setTarget(this.mStartButton);
            buttonFadeOutAnimator.addListener(new AnimatorListenerAdapter() { // from class: androidx.leanback.app.OnboardingFragment.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    OnboardingFragment.this.mStartButton.setVisibility(8);
                }
            });
            animators.add(buttonFadeOutAnimator);
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mAnimator = animatorSet2;
        animatorSet2.playTogether(animators);
        this.mAnimator.start();
        onPageChanged(this.mCurrentPageIndex, previousPage);
    }

    protected void onPageChanged(int newPage, int previousPage) {
    }

    private Animator createAnimator(View view, boolean fadeIn, int slideDirection, long startDelay) {
        Animator fadeAnimator;
        Animator slideAnimator;
        boolean isLtr = getView().getLayoutDirection() == 0;
        boolean slideRight = (isLtr && slideDirection == 8388613) || (!isLtr && slideDirection == 8388611) || slideDirection == 5;
        if (fadeIn) {
            fadeAnimator = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
            Property property = View.TRANSLATION_X;
            float[] fArr = new float[2];
            int i = sSlideDistance;
            if (!slideRight) {
                i = -i;
            }
            fArr[0] = i;
            fArr[1] = 0.0f;
            slideAnimator = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
            fadeAnimator.setInterpolator(HEADER_APPEAR_INTERPOLATOR);
            slideAnimator.setInterpolator(HEADER_APPEAR_INTERPOLATOR);
        } else {
            fadeAnimator = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
            Property property2 = View.TRANSLATION_X;
            float[] fArr2 = new float[2];
            fArr2[0] = 0.0f;
            int i2 = sSlideDistance;
            if (!slideRight) {
                i2 = -i2;
            }
            fArr2[1] = i2;
            slideAnimator = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
            fadeAnimator.setInterpolator(HEADER_DISAPPEAR_INTERPOLATOR);
            slideAnimator.setInterpolator(HEADER_DISAPPEAR_INTERPOLATOR);
        }
        fadeAnimator.setDuration(HEADER_ANIMATION_DURATION_MS);
        fadeAnimator.setTarget(view);
        slideAnimator.setDuration(HEADER_ANIMATION_DURATION_MS);
        slideAnimator.setTarget(view);
        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(fadeAnimator, slideAnimator);
        if (startDelay > 0) {
            animator.setStartDelay(startDelay);
        }
        return animator;
    }

    public final void setIconResouceId(int resourceId) {
        this.mIconResourceId = resourceId;
        ImageView imageView = this.mMainIconView;
        if (imageView != null) {
            imageView.setImageResource(resourceId);
            this.mMainIconView.setVisibility(0);
        }
    }

    public final int getIconResourceId() {
        return this.mIconResourceId;
    }
}
