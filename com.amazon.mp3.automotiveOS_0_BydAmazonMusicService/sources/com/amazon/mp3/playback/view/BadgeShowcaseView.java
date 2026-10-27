package com.amazon.mp3.playback.view;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BadgeShowcaseView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/playback/view/BadgeShowcaseView;", "", "()V", "isShown", "", "show", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "viewTarget", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BadgeShowcaseView {
    public static final BadgeShowcaseView INSTANCE = new BadgeShowcaseView();
    private static boolean isShown;

    private BadgeShowcaseView() {
    }

    @JvmStatic
    public static final void show(@NonNull final FragmentActivity activity, @NonNull View viewTarget) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (isShown) {
            return;
        }
        isShown = true;
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.showcase_button, (ViewGroup) null);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.Button");
        }
        Button button = (Button) viewInflate;
        button.setAllCaps(true);
        final ShowcaseView showcaseViewBuild = new ShowcaseView.Builder(activity).setTarget(new ViewTarget(viewTarget)).setContentTitle(activity.getResources().getText(R.string.showcaseview_audioquality_title)).setContentText(activity.getResources().getText(R.string.showcaseview_audioquality_description)).replaceEndButton(button).hideOnTouchOutside().setStyle(R.style.CastingShowcaseTheme).build();
        showcaseViewBuild.forceTextPosition(1);
        int dimension = (int) activity.getResources().getDimension(R.dimen.button_margin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        layoutParams.setMargins(dimension, dimension, dimension, dimension);
        button.setLayoutParams(layoutParams);
        showcaseViewBuild.overrideButtonClick(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.BadgeShowcaseView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BadgeShowcaseView.m1514show$lambda0(showcaseViewBuild, view);
            }
        });
        showcaseViewBuild.setOnShowcaseEventListener(new OnShowcaseEventListener() { // from class: com.amazon.mp3.playback.view.BadgeShowcaseView.show.2
            @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
            public void onShowcaseViewDidHide(ShowcaseView p0) {
            }

            @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
            public void onShowcaseViewShow(ShowcaseView p0) {
            }

            @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
            public void onShowcaseViewTouchBlocked(MotionEvent p0) {
            }

            @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
            public void onShowcaseViewHide(ShowcaseView p0) {
                KatanaNPSBadgeProvider.setShowcaseAudioQualityBadgeStatus(activity, true);
            }
        });
        activity.getLifecycle().addObserver(new LifecycleObserver() { // from class: com.amazon.mp3.playback.view.BadgeShowcaseView.show.3
            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public final void reset() {
                if (BadgeShowcaseView.isShown) {
                    showcaseViewBuild.hide();
                }
                BadgeShowcaseView.isShown = false;
                activity.getLifecycle().removeObserver(this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-0, reason: not valid java name */
    public static final void m1514show$lambda0(ShowcaseView showcaseView, View view) {
        isShown = false;
        showcaseView.hide();
    }
}
