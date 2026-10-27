package com.amazon.mp3.prime.upsell.nightwing;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.upsell.nightwing.viewmodel.AdsPreferenceSettingsViewModel;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdsPreferenceSettingsActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/prime/upsell/nightwing/AdsPreferenceSettingsActivity;", "Lcom/amazon/mp3/activity/BaseActivity;", "()V", "ADS_PREFS_UPDATE_FAILED", "", "viewModel", "Lcom/amazon/mp3/prime/upsell/nightwing/viewmodel/AdsPreferenceSettingsViewModel;", "initToolBar", "", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "sendUICLickEvent", "isTargetedAdsTurnedOn", "", "setUpToggleButton", "rootView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AdsPreferenceSettingsActivity extends BaseActivity {
    private final String ADS_PREFS_UPDATE_FAILED = "Update ads preferences failed";
    private AdsPreferenceSettingsViewModel viewModel;

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_ads_preference_view);
        View rootView = findViewById(R.id.ads_preference_settings);
        View viewFindViewById = findViewById(R.id.toolbar);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.appcompat.widget.Toolbar");
        }
        initToolBar((Toolbar) viewFindViewById);
        this.viewModel = (AdsPreferenceSettingsViewModel) ViewModelProviders.of(this).get(AdsPreferenceSettingsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
        setUpToggleButton(rootView);
    }

    private final void setUpToggleButton(View rootView) {
        final ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.ads_preference_toggle_button);
        final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.loading_spinner);
        AdsPreferenceSettingsViewModel adsPreferenceSettingsViewModel = this.viewModel;
        Intrinsics.checkNotNull(adsPreferenceSettingsViewModel);
        imageButton.setSelected(adsPreferenceSettingsViewModel.ifTargetedAdsEnabled());
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.upsell.nightwing.AdsPreferenceSettingsActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdsPreferenceSettingsActivity.m1592setUpToggleButton$lambda0(imageButton, progressBar, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setUpToggleButton$lambda-0, reason: not valid java name */
    public static final void m1592setUpToggleButton$lambda0(final ImageButton imageButton, final ProgressBar progressBar, final AdsPreferenceSettingsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        imageButton.setVisibility(8);
        progressBar.setVisibility(0);
        AdsPreferenceSettingsViewModel adsPreferenceSettingsViewModel = this$0.viewModel;
        Intrinsics.checkNotNull(adsPreferenceSettingsViewModel);
        adsPreferenceSettingsViewModel.toggleAdsPrefSettings(new Function0<Unit>() { // from class: com.amazon.mp3.prime.upsell.nightwing.AdsPreferenceSettingsActivity$setUpToggleButton$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                progressBar.setVisibility(8);
                imageButton.setVisibility(0);
                imageButton.setSelected(!r0.isSelected());
                this$0.sendUICLickEvent(imageButton.isSelected());
            }
        }, new Function0<Unit>() { // from class: com.amazon.mp3.prime.upsell.nightwing.AdsPreferenceSettingsActivity$setUpToggleButton$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AdsPreferenceSettingsActivity adsPreferenceSettingsActivity = this.this$0;
                BauhausToastUtils.showDefaultToast(adsPreferenceSettingsActivity, adsPreferenceSettingsActivity.ADS_PREFS_UPDATE_FAILED, 0);
                progressBar.setVisibility(8);
                imageButton.setVisibility(0);
                ImageButton imageButton2 = imageButton;
                AdsPreferenceSettingsViewModel adsPreferenceSettingsViewModel2 = this.this$0.viewModel;
                Intrinsics.checkNotNull(adsPreferenceSettingsViewModel2);
                imageButton2.setSelected(adsPreferenceSettingsViewModel2.ifTargetedAdsEnabled());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUICLickEvent(boolean isTargetedAdsTurnedOn) {
        ActionType actionType = ActionType.TOGGLE_BEHAVIORAL_ADS_ON;
        if (!isTargetedAdsTurnedOn) {
            actionType = ActionType.TOGGLE_BEHAVIORAL_ADS_OFF;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(PageType.BEHAVIORAL_ADS_SETTINGS).withInteractionType(InteractionType.TAP).build());
    }

    private final void initToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_goback);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.dmusic_setting_summary_ad_prefs_title);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.upsell.nightwing.AdsPreferenceSettingsActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdsPreferenceSettingsActivity.m1591initToolBar$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initToolBar$lambda-1, reason: not valid java name */
    public static final void m1591initToolBar$lambda1(AdsPreferenceSettingsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }
}
