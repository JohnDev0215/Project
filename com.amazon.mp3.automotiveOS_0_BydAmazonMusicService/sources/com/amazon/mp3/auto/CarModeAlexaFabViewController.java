package com.amazon.mp3.auto;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.voice.AlexaIngressHelper;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.voice.AlexaEntryPoint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeAlexaFabViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\rH\u0002R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "", "context", "Landroid/content/Context;", "alexaFabButton", "Landroid/view/View;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroid/content/Context;Landroid/view/View;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "TAG", "", "kotlin.jvm.PlatformType", "destroy", "", "hideAlexaFab", "onAlexaFabClicked", "setAlexaFabButtonVisibility", "visibility", "", "showAlexaFab", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeAlexaFabViewController {
    private final String TAG;
    private View alexaFabButton;
    private final Context context;
    private PageType pageType;

    public CarModeAlexaFabViewController(Context context, View view, PageType pageType) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.alexaFabButton = view;
        this.pageType = pageType;
        String simpleName = CarModeAlexaFabViewController.class.getSimpleName();
        this.TAG = simpleName;
        View view2 = this.alexaFabButton;
        if (view2 == null) {
            unit = null;
        } else {
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.CarModeAlexaFabViewController$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    CarModeAlexaFabViewController.m252lambda1$lambda0(this.f$0, view3);
                }
            });
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(simpleName, "alexaFabButton is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m252lambda1$lambda0(CarModeAlexaFabViewController this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onAlexaFabClicked();
    }

    public final void destroy() {
        View view = this.alexaFabButton;
        if (view != null) {
            view.setOnClickListener(null);
        }
        this.alexaFabButton = null;
        this.pageType = null;
    }

    private final void hideAlexaFab() {
        Unit unit;
        View view = this.alexaFabButton;
        if (view == null) {
            unit = null;
        } else {
            view.setVisibility(8);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(this.TAG, "alexaFabButton is null");
        }
    }

    private final void showAlexaFab() {
        Unit unit;
        View view = this.alexaFabButton;
        if (view == null) {
            unit = null;
        } else {
            view.setVisibility(0);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(this.TAG, "alexaFabButton is null");
        }
    }

    public final void setAlexaFabButtonVisibility(boolean visibility) {
        Unit unit;
        if (this.alexaFabButton == null) {
            unit = null;
        } else {
            Log.debug(this.TAG, Intrinsics.stringPlus("Orientation : isPortrait : ", Boolean.valueOf(ScreenUtil.isPortrait())));
            if (visibility) {
                showAlexaFab();
                Log.debug(this.TAG, "Alexa Floating action button shown");
                return;
            } else {
                hideAlexaFab();
                Log.debug(this.TAG, "Alexa Floating action button hidden");
                unit = Unit.INSTANCE;
            }
        }
        if (unit == null) {
            Log.debug(this.TAG, "alexaFabButton is null");
        }
    }

    private final void onAlexaFabClicked() {
        PageType pageType = this.pageType;
        if (pageType != null) {
            AutoUiEventUtil.sendUiClickEvent$default(ActionType.GO_ALEXA, pageType, ExperienceMode.CAR_MODE, null, 8, null);
        }
        new AlexaIngressHelper(this.context).handleAlexaIngress(AlexaEntryPoint.BOTTOM_BAR);
    }
}
