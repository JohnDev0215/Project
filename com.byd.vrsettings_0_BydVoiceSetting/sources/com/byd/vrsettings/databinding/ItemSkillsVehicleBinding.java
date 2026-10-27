package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSkillsPopupView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemSkillsVehicleBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvAc;
    public final CustomSkillsPopupView cspvAirPur;
    public final CustomSkillsPopupView cspvAmbientLights;
    public final CustomSkillsPopupView cspvReadLight;
    public final CustomSkillsPopupView cspvSeat;
    public final CustomSkillsPopupView cspvSunshadeControl;
    public final CustomSkillsPopupView cspvWindowsSunroof;
    public final ConstraintLayout customVehicle;
    private final CustomScrollView rootView;
    public final CustomScrollView svVehicle;
    public final View vehicleBottom;

    private ItemSkillsVehicleBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, CustomSkillsPopupView customSkillsPopupView3, CustomSkillsPopupView customSkillsPopupView4, CustomSkillsPopupView customSkillsPopupView5, CustomSkillsPopupView customSkillsPopupView6, CustomSkillsPopupView customSkillsPopupView7, ConstraintLayout constraintLayout, CustomScrollView customScrollView2, View view) {
        this.rootView = customScrollView;
        this.cspvAc = customSkillsPopupView;
        this.cspvAirPur = customSkillsPopupView2;
        this.cspvAmbientLights = customSkillsPopupView3;
        this.cspvReadLight = customSkillsPopupView4;
        this.cspvSeat = customSkillsPopupView5;
        this.cspvSunshadeControl = customSkillsPopupView6;
        this.cspvWindowsSunroof = customSkillsPopupView7;
        this.customVehicle = constraintLayout;
        this.svVehicle = customScrollView2;
        this.vehicleBottom = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsVehicleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsVehicleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_vehicle, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsVehicleBinding bind(View view) {
        int i = R.id.cspv_ac;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_ac);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_air_pur;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_air_pur);
            if (customSkillsPopupView2 != null) {
                i = R.id.cspv_ambient_lights;
                CustomSkillsPopupView customSkillsPopupView3 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_ambient_lights);
                if (customSkillsPopupView3 != null) {
                    i = R.id.cspv_read_light;
                    CustomSkillsPopupView customSkillsPopupView4 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_read_light);
                    if (customSkillsPopupView4 != null) {
                        i = R.id.cspv_seat;
                        CustomSkillsPopupView customSkillsPopupView5 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_seat);
                        if (customSkillsPopupView5 != null) {
                            i = R.id.cspv_sunshade_control;
                            CustomSkillsPopupView customSkillsPopupView6 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_sunshade_control);
                            if (customSkillsPopupView6 != null) {
                                i = R.id.cspv_windows_sunroof;
                                CustomSkillsPopupView customSkillsPopupView7 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_windows_sunroof);
                                if (customSkillsPopupView7 != null) {
                                    i = R.id.custom_vehicle;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.custom_vehicle);
                                    if (constraintLayout != null) {
                                        CustomScrollView customScrollView = (CustomScrollView) view;
                                        i = R.id.vehicle_bottom;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.vehicle_bottom);
                                        if (viewFindChildViewById != null) {
                                            return new ItemSkillsVehicleBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, customSkillsPopupView3, customSkillsPopupView4, customSkillsPopupView5, customSkillsPopupView6, customSkillsPopupView7, constraintLayout, customScrollView, viewFindChildViewById);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
