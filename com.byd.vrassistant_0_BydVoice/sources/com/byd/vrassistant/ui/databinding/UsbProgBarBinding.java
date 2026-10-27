package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public final class UsbProgBarBinding implements ViewBinding {
    public final Button btCancel;
    public final Button btStart;
    public final ConstraintLayout clProg;
    public final Group groupStart;
    public final View pbBg;
    public final ProgressBar pbCopy;
    private final ConstraintLayout rootView;
    public final TextView txState;
    public final TextView txState2;
    public final TextView txTitle;

    private UsbProgBarBinding(ConstraintLayout constraintLayout, Button button, Button button2, ConstraintLayout constraintLayout2, Group group, View view, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btCancel = button;
        this.btStart = button2;
        this.clProg = constraintLayout2;
        this.groupStart = group;
        this.pbBg = view;
        this.pbCopy = progressBar;
        this.txState = textView;
        this.txState2 = textView2;
        this.txTitle = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static UsbProgBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static UsbProgBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.usb_prog_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static UsbProgBarBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.bt_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.bt_start;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.group_start;
                Group group = (Group) ViewBindings.findChildViewById(view, i);
                if (group != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.pb_bg))) != null) {
                    i = R.id.pb_copy;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                    if (progressBar != null) {
                        i = R.id.tx_state;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.tx_state2;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.tx_title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    return new UsbProgBarBinding(constraintLayout, button, button2, constraintLayout, group, viewFindChildViewById, progressBar, textView, textView2, textView3);
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
