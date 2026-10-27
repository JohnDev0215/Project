package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.view.MarqueeTxtView;

/* JADX INFO: loaded from: classes2.dex */
public final class ItemVoiceBarPhoneBinding implements ViewBinding {
    public final ConstraintLayout clItem;
    private final ConstraintLayout rootView;
    public final TextView tvBottom;
    public final TextView tvEnd;
    public final TextView tvNum;
    public final MarqueeTxtView tvTop;
    public final View vNumBg;

    private ItemVoiceBarPhoneBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, MarqueeTxtView marqueeTxtView, View view) {
        this.rootView = constraintLayout;
        this.clItem = constraintLayout2;
        this.tvBottom = textView;
        this.tvEnd = textView2;
        this.tvNum = textView3;
        this.tvTop = marqueeTxtView;
        this.vNumBg = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemVoiceBarPhoneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoiceBarPhoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_voice_bar_phone, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemVoiceBarPhoneBinding bind(View view) {
        View viewFindChildViewById;
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.tv_bottom;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.tv_end;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.tv_num;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView3 != null) {
                    i = R.id.tv_top;
                    MarqueeTxtView marqueeTxtView = (MarqueeTxtView) ViewBindings.findChildViewById(view, i);
                    if (marqueeTxtView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_num_bg))) != null) {
                        return new ItemVoiceBarPhoneBinding(constraintLayout, constraintLayout, textView, textView2, textView3, marqueeTxtView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
