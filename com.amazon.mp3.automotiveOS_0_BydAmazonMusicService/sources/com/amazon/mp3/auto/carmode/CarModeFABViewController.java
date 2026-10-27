package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeFABViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0002R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0016\u0010\r\u001a\u0004\u0018\u00010\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeFABViewController;", "", "", "showCarModeFab", "onCarModeFabClicked", "", "shouldShowCarModeFAB", "hideCarModeFab", "setCarModeFabButtonVisibility", "Landroid/view/View;", "carModeFabButtonContainer", "Landroid/view/View;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "carModeFabButton", "<init>", "(Landroid/view/View;Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeFABViewController {
    private final String TAG;
    private final View carModeFabButton;
    private View carModeFabButtonContainer;
    private final Context context;

    public CarModeFABViewController(View view, Context context) {
        this.carModeFabButtonContainer = view;
        this.context = context;
        String simpleName = CarModeFABViewController.class.getSimpleName();
        this.TAG = simpleName;
        View view2 = this.carModeFabButtonContainer;
        Unit unit = null;
        View viewFindViewById = view2 == null ? null : view2.findViewById(R.id.home_ic_car_mode_fab);
        this.carModeFabButton = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.CarModeFABViewController$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    CarModeFABViewController.m256lambda1$lambda0(this.f$0, view3);
                }
            });
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(simpleName, "CarModeFabButton is null");
        }
        if (shouldShowCarModeFAB()) {
            View view3 = this.carModeFabButtonContainer;
            if (view3 == null) {
                return;
            }
            view3.setVisibility(0);
            return;
        }
        View view4 = this.carModeFabButtonContainer;
        if (view4 == null) {
            return;
        }
        view4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m256lambda1$lambda0(CarModeFABViewController this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onCarModeFabClicked();
    }

    public final boolean shouldShowCarModeFAB() {
        if (!CarModeUtility.isCarModeEnabled()) {
            return false;
        }
        String showCarModeFABPreference = SettingsUtil.getShowCarModeFABPreference(this.context);
        if (Intrinsics.areEqual(showCarModeFABPreference, CarModeShowFABPreferences.ALWAYS.getEntryValue())) {
            return true;
        }
        if (Intrinsics.areEqual(showCarModeFABPreference, CarModeShowFABPreferences.IN_CAR.getEntryValue())) {
            return CarModeUtility.INSTANCE.isCurrentlyInCar();
        }
        Intrinsics.areEqual(showCarModeFABPreference, CarModeShowFABPreferences.NEVER.getEntryValue());
        return false;
    }

    public final void hideCarModeFab() {
        Unit unit;
        View view = this.carModeFabButtonContainer;
        if (view == null) {
            unit = null;
        } else {
            view.setVisibility(8);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(this.TAG, "CarModeFabButton is null");
        }
    }

    private final void showCarModeFab() {
        Unit unit;
        View view = this.carModeFabButtonContainer;
        if (view == null) {
            unit = null;
        } else {
            view.setVisibility(0);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(this.TAG, "CarModeFabButton is null");
        }
    }

    public final void setCarModeFabButtonVisibility() {
        Unit unit;
        if (this.carModeFabButtonContainer == null) {
            unit = null;
        } else if (shouldShowCarModeFAB()) {
            showCarModeFab();
            Log.debug(this.TAG, "Car Mode Floating action button shown");
            return;
        } else {
            hideCarModeFab();
            Log.debug(this.TAG, "Car Mode Floating action button hidden");
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.debug(this.TAG, "CarModeFabButton is null");
        }
    }

    private final void onCarModeFabClicked() {
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_FAB_OPEN, PageType.BROWSE_HOME);
        Context context = this.context;
        if (context == null) {
            return;
        }
        CarModeUtility.INSTANCE.navigateToBrushFragment(context, true, false);
    }
}
