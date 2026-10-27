package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeFragmentPagerAdapter;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingPresetFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeOnBoardingFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeOnBoardingFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "adapter", "Lcom/amazon/mp3/auto/carmode/CarModeFragmentPagerAdapter;", "nextButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "pagerTabLayout", "Lcom/google/android/material/tabs/TabLayout;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "createFragmentList", "", "Landroidx/fragment/app/Fragment;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "registerButtonClickListeners", "registerViewPagerChangeListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeOnBoardingFragment extends CarModeBaseFragment {
    private CarModeFragmentPagerAdapter adapter;
    private BaseButton nextButton;
    private TabLayout pagerTabLayout;
    private ViewPager viewPager;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_onboarding, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…arding, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_view_pager);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.car_mode_view_pager)");
        this.viewPager = (ViewPager) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_pager_tabs);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.car_mode_pager_tabs)");
        this.pagerTabLayout = (TabLayout) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_onboarding_next);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.car_mode_onboarding_next)");
        this.nextButton = (BaseButton) viewFindViewById3;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment.onViewCreated.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("carModeUserExit", true);
                    CarModeNavigation navigationListener = CarModeOnBoardingFragment.this.getNavigationListener();
                    if (navigationListener == null) {
                        return;
                    }
                    navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
                }
            });
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeOnBoardingFragment.m292onViewCreated$lambda0(this.f$0, (StyleSheet) obj);
            }
        }));
        BaseButton baseButton = this.nextButton;
        TabLayout tabLayout = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            baseButton = null;
        }
        String string = getString(R.string.dmusic_car_mode_onboarding_next_button);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…e_onboarding_next_button)");
        baseButton.setText(string);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        this.adapter = new CarModeFragmentPagerAdapter(childFragmentManager, createFragmentList());
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        }
        CarModeFragmentPagerAdapter carModeFragmentPagerAdapter = this.adapter;
        if (carModeFragmentPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carModeFragmentPagerAdapter = null;
        }
        viewPager.setAdapter(carModeFragmentPagerAdapter);
        registerViewPagerChangeListener();
        TabLayout tabLayout2 = this.pagerTabLayout;
        if (tabLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pagerTabLayout");
            tabLayout2 = null;
        }
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        tabLayout2.setupWithViewPager(viewPager2);
        TabLayout tabLayout3 = this.pagerTabLayout;
        if (tabLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pagerTabLayout");
        } else {
            tabLayout = tabLayout3;
        }
        ArrayList<View> touchables = tabLayout.getTouchables();
        Intrinsics.checkNotNullExpressionValue(touchables, "pagerTabLayout.touchables");
        Iterator<T> it = touchables.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setEnabled(false);
        }
        registerButtonClickListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-0, reason: not valid java name */
    public static final void m292onViewCreated$lambda0(CarModeOnBoardingFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.EPIC, ButtonStyleKey.BASE_GLASS);
        if (buttonBuilder == null) {
            return;
        }
        BaseButton baseButton = this$0.nextButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            baseButton = null;
        }
        buttonBuilder.applyStyle(baseButton);
    }

    private final List<Fragment> createFragmentList() {
        CarModeBaseFragment[] carModeBaseFragmentArr = new CarModeBaseFragment[3];
        CarModeOnBoardingPresetFragment.Companion companion = CarModeOnBoardingPresetFragment.INSTANCE;
        carModeBaseFragmentArr[0] = companion.newInstance(R.string.dmusic_car_mode_onboarding_tap_to_play, R.string.dmusic_car_mode_onboarding_presets_overview_description, AmazonApplication.getCapabilities().isCarModeV2Enabled() ? R.drawable.car_mode_v2_tap_to_play : R.drawable.car_mode_tap_to_play, R.drawable.car_mode_gradient_red_purple);
        carModeBaseFragmentArr[1] = companion.newInstance(R.string.dmusic_car_mode_onboarding_add_a_preset, R.string.dmusic_car_mode_onboarding_presets_customize_description, R.drawable.car_mode_add_a_preset, R.drawable.car_mode_gradient_purple_blue);
        carModeBaseFragmentArr[2] = new CarModeOnBoardingAlexaFragment();
        return CollectionsKt__CollectionsKt.listOf((Object[]) carModeBaseFragmentArr);
    }

    private final void registerViewPagerChangeListener() {
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment.registerViewPagerChangeListener.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                CarModeFragmentPagerAdapter carModeFragmentPagerAdapter = CarModeOnBoardingFragment.this.adapter;
                BaseButton baseButton = null;
                if (carModeFragmentPagerAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carModeFragmentPagerAdapter = null;
                }
                if (position == CollectionsKt__CollectionsKt.getLastIndex(carModeFragmentPagerAdapter.getFragmentList())) {
                    BaseButton baseButton2 = CarModeOnBoardingFragment.this.nextButton;
                    if (baseButton2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                    } else {
                        baseButton = baseButton2;
                    }
                    String string = CarModeOnBoardingFragment.this.getString(R.string.dmusic_car_mode_onboarding_done_button);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…e_onboarding_done_button)");
                    baseButton.setText(string);
                    return;
                }
                BaseButton baseButton3 = CarModeOnBoardingFragment.this.nextButton;
                if (baseButton3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                } else {
                    baseButton = baseButton3;
                }
                String string2 = CarModeOnBoardingFragment.this.getString(R.string.dmusic_car_mode_onboarding_next_button);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…e_onboarding_next_button)");
                baseButton.setText(string2);
            }
        });
    }

    private final void registerButtonClickListeners() {
        BaseButton baseButton = this.nextButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeOnBoardingFragment.m293registerButtonClickListeners$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerButtonClickListeners$lambda-3, reason: not valid java name */
    public static final void m293registerButtonClickListeners$lambda3(CarModeOnBoardingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewPager viewPager = this$0.viewPager;
        ViewPager viewPager2 = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager = null;
        }
        int currentItem = viewPager.getCurrentItem();
        CarModeFragmentPagerAdapter carModeFragmentPagerAdapter = this$0.adapter;
        if (carModeFragmentPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carModeFragmentPagerAdapter = null;
        }
        if (currentItem == CollectionsKt__CollectionsKt.getLastIndex(carModeFragmentPagerAdapter.getFragmentList())) {
            AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_ONBOARDING_COMPLETE, PageType.CAR_MODE_ONBOARDING, ExperienceMode.CAR_MODE, null, 8, null);
            CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
            Context contextRequireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            carModeUtility.setOnBoardingPreference(contextRequireContext, false);
            Bundle bundle = new Bundle();
            bundle.putBoolean("carModeLaunch", true);
            CarModeNavigation navigationListener = this$0.getNavigationListener();
            if (navigationListener == null) {
                return;
            }
            navigationListener.navigateTo(CarModeAction.SHOW_HOME_PAGE, bundle);
            return;
        }
        ViewPager viewPager3 = this$0.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager3;
        }
        viewPager2.setCurrentItem(currentItem + 1, true);
    }
}
