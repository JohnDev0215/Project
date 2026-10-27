package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.radio.DABViewModel;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentDabBinding extends ViewDataBinding {
    public final Button btnTuneDab;
    public final Button btnUpdateList;
    public final BydScrollerSupportedFrameLayout channelListContainer;
    public final BydScrollerSupportedFrameLayout collectListContainer;
    public final TextView dabDls;
    public final TextView dabEnsemble;
    public final ShapeableImageView dabPic;
    public final TextView dabPty;
    public final TextView dabService;
    public final Button epg;
    public final ConstraintLayout groupDabList;
    public final ConstraintLayout groupPlayingDabCard;
    public final ImageView ivNoData;

    @Bindable
    protected ObservableBoolean mIsCollectShow;

    @Bindable
    protected DABViewModel mViewModel;
    public final RadioButton rbChannel;
    public final RadioButton rbCollect;
    public final RadioGroup rbgProgramList;
    public final ConstraintLayout rootContainer;
    public final RecyclerView rvChannelList;
    public final RecyclerView rvCollectList;
    public final Button testbtn;
    public final ImageButton topSearch;
    public final ImageButton topSetting;

    public abstract void setIsCollectShow(ObservableBoolean observableBoolean);

    public abstract void setViewModel(DABViewModel dABViewModel);

    protected FragmentDabBinding(Object obj, View view, int i, Button button, Button button2, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout2, TextView textView, TextView textView2, ShapeableImageView shapeableImageView, TextView textView3, TextView textView4, Button button3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup, ConstraintLayout constraintLayout3, RecyclerView recyclerView, RecyclerView recyclerView2, Button button4, ImageButton imageButton, ImageButton imageButton2) {
        super(obj, view, i);
        this.btnTuneDab = button;
        this.btnUpdateList = button2;
        this.channelListContainer = bydScrollerSupportedFrameLayout;
        this.collectListContainer = bydScrollerSupportedFrameLayout2;
        this.dabDls = textView;
        this.dabEnsemble = textView2;
        this.dabPic = shapeableImageView;
        this.dabPty = textView3;
        this.dabService = textView4;
        this.epg = button3;
        this.groupDabList = constraintLayout;
        this.groupPlayingDabCard = constraintLayout2;
        this.ivNoData = imageView;
        this.rbChannel = radioButton;
        this.rbCollect = radioButton2;
        this.rbgProgramList = radioGroup;
        this.rootContainer = constraintLayout3;
        this.rvChannelList = recyclerView;
        this.rvCollectList = recyclerView2;
        this.testbtn = button4;
        this.topSearch = imageButton;
        this.topSetting = imageButton2;
    }

    public ObservableBoolean getIsCollectShow() {
        return this.mIsCollectShow;
    }

    public DABViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentDabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentDabBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_dab, viewGroup, z, obj);
    }

    public static FragmentDabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentDabBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_dab, null, false, obj);
    }

    public static FragmentDabBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabBinding bind(View view, Object obj) {
        return (FragmentDabBinding) bind(obj, view, R.layout.fragment_dab);
    }
}
