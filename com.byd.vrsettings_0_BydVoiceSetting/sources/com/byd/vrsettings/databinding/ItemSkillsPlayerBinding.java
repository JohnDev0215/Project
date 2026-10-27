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
public final class ItemSkillsPlayerBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvMusic;
    public final CustomSkillsPopupView cspvRadio;
    public final ConstraintLayout customPlayer;
    public final View playerBottom;
    private final CustomScrollView rootView;
    public final CustomScrollView svPlayer;

    private ItemSkillsPlayerBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, ConstraintLayout constraintLayout, View view, CustomScrollView customScrollView2) {
        this.rootView = customScrollView;
        this.cspvMusic = customSkillsPopupView;
        this.cspvRadio = customSkillsPopupView2;
        this.customPlayer = constraintLayout;
        this.playerBottom = view;
        this.svPlayer = customScrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_player, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsPlayerBinding bind(View view) {
        int i = R.id.cspv_music;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_music);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_radio;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_radio);
            if (customSkillsPopupView2 != null) {
                i = R.id.custom_player;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.custom_player);
                if (constraintLayout != null) {
                    i = R.id.player_bottom;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.player_bottom);
                    if (viewFindChildViewById != null) {
                        CustomScrollView customScrollView = (CustomScrollView) view;
                        return new ItemSkillsPlayerBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, constraintLayout, viewFindChildViewById, customScrollView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
