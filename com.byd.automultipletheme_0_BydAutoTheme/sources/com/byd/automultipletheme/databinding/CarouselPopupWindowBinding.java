package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class CarouselPopupWindowBinding implements ViewBinding {
    public final ConstraintLayout layoutDay;
    public final ImageView layoutDaySelectIcon;
    public final TextView layoutDaySelectTxt;
    public final ConstraintLayout layoutHour;
    public final ImageView layoutHourSelectIcon;
    public final TextView layoutHourSelectTxt;
    public final ConstraintLayout layoutMin;
    public final ImageView layoutMinSelectIcon;
    public final TextView layoutMinSelectTxt;
    private final LinearLayout rootView;

    private CarouselPopupWindowBinding(LinearLayout linearLayout, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, ConstraintLayout constraintLayout2, ImageView imageView2, TextView textView2, ConstraintLayout constraintLayout3, ImageView imageView3, TextView textView3) {
        this.rootView = linearLayout;
        this.layoutDay = constraintLayout;
        this.layoutDaySelectIcon = imageView;
        this.layoutDaySelectTxt = textView;
        this.layoutHour = constraintLayout2;
        this.layoutHourSelectIcon = imageView2;
        this.layoutHourSelectTxt = textView2;
        this.layoutMin = constraintLayout3;
        this.layoutMinSelectIcon = imageView3;
        this.layoutMinSelectTxt = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CarouselPopupWindowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CarouselPopupWindowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.carousel_popup_window, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CarouselPopupWindowBinding bind(View view) {
        int i = R.id.layout_day;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_day);
        if (constraintLayout != null) {
            i = R.id.layout_day_select_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.layout_day_select_icon);
            if (imageView != null) {
                i = R.id.layout_day_select_txt;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.layout_day_select_txt);
                if (textView != null) {
                    i = R.id.layout_hour;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_hour);
                    if (constraintLayout2 != null) {
                        i = R.id.layout_hour_select_icon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.layout_hour_select_icon);
                        if (imageView2 != null) {
                            i = R.id.layout_hour_select_txt;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.layout_hour_select_txt);
                            if (textView2 != null) {
                                i = R.id.layout_min;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_min);
                                if (constraintLayout3 != null) {
                                    i = R.id.layout_min_select_icon;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.layout_min_select_icon);
                                    if (imageView3 != null) {
                                        i = R.id.layout_min_select_txt;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.layout_min_select_txt);
                                        if (textView3 != null) {
                                            return new CarouselPopupWindowBinding((LinearLayout) view, constraintLayout, imageView, textView, constraintLayout2, imageView2, textView2, constraintLayout3, imageView3, textView3);
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
