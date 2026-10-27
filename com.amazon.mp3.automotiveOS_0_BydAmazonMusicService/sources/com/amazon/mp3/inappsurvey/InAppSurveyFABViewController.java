package com.amazon.mp3.inappsurvey;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InAppSurveyFABViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u001c\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/inappsurvey/InAppSurveyFABViewController;", "", "", "onInAppSurveyFABClicked", "", "shouldShowInAppSurveyFAB", "Landroid/view/View;", "inAppSurveyFABContainer", "Landroid/view/View;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "inAppSurveyFAB", "<init>", "(Landroid/view/View;Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class InAppSurveyFABViewController {
    private final String TAG;
    private final Context context;
    private final View inAppSurveyFAB;
    private View inAppSurveyFABContainer;

    public InAppSurveyFABViewController(View view, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.inAppSurveyFABContainer = view;
        this.context = context;
        String simpleName = InAppSurveyFABViewController.class.getSimpleName();
        this.TAG = simpleName;
        View view2 = this.inAppSurveyFABContainer;
        Unit unit = null;
        View viewFindViewById = view2 == null ? null : view2.findViewById(R.id.home_ic_in_app_survey_fab);
        this.inAppSurveyFAB = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.inappsurvey.InAppSurveyFABViewController$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    InAppSurveyFABViewController.m1205lambda1$lambda0(this.f$0, view3);
                }
            });
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(simpleName, "InAppSurvey floating action button is null");
        }
        if (shouldShowInAppSurveyFAB()) {
            View view3 = this.inAppSurveyFABContainer;
            if (view3 == null) {
                return;
            }
            view3.setVisibility(0);
            return;
        }
        View view4 = this.inAppSurveyFABContainer;
        if (view4 == null) {
            return;
        }
        view4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m1205lambda1$lambda0(InAppSurveyFABViewController this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onInAppSurveyFABClicked();
    }

    public final boolean shouldShowInAppSurveyFAB() {
        InterfaceProvider interfaceProvider = Providers.INSTANCE.get(FeatureGateProvider.class);
        FeatureGateProvider featureGateProvider = interfaceProvider instanceof FeatureGateProvider ? (FeatureGateProvider) interfaceProvider : null;
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.in_app_survey);
    }

    private final void onInAppSurveyFABClicked() {
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        if (companion.getShared().shouldShowActivity("Home", "SurveyButton")) {
            companion.getShared().openActivityForScreenComponent(this.context, "Home", "SurveyButton", null);
        }
    }
}
