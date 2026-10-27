package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.radio.FMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.RulerView;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentFmBinding extends ViewDataBinding {
    public final LinearLayout RDSContainer;
    public final TextView RDSFreq;
    public final TextView RDSPS;
    public final TextView RDSPTY;
    public final TextView RDSRT;
    public final ImageButton btnCollect;
    public final ImageButton btnNext;
    public final ImageButton btnPlayOrPause;
    public final ImageButton btnPre;
    public final ImageButton btnSearchNext;
    public final ImageButton btnSearchPre;
    public final BydScrollerSupportedFrameLayout channelListContainer;
    public final BydScrollerSupportedFrameLayout collectListContainer;
    public final CollectLottieView collectLottie;
    public final ConstraintLayout groupPlayingCard;
    public final ConstraintLayout groupProgramList;
    public final ImageView ivNoData;

    @Bindable
    protected FMViewModel mViewModel;
    public final NestedScrollView nestedScrollView;
    public final TextView programFreqUnit;
    public final TextView programFrequency;
    public final RadioButton rbChannel;
    public final RadioButton rbCollect;
    public final RadioGroup rbgProgramList;
    public final ConstraintLayout rootContainer;
    public final RulerView rulerView;
    public final RecyclerView rvChannelList;
    public final RecyclerView rvCollectList;
    public final ImageView topMirror;
    public final ImageButton topSearch;
    public final ImageButton topSetting;

    public abstract void setViewModel(FMViewModel fMViewModel);

    protected FragmentFmBinding(Object obj, View view, int i, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageButton imageButton5, ImageButton imageButton6, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout2, CollectLottieView collectLottieView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, NestedScrollView nestedScrollView, TextView textView5, TextView textView6, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup, ConstraintLayout constraintLayout3, RulerView rulerView, RecyclerView recyclerView, RecyclerView recyclerView2, ImageView imageView2, ImageButton imageButton7, ImageButton imageButton8) {
        super(obj, view, i);
        this.RDSContainer = linearLayout;
        this.RDSFreq = textView;
        this.RDSPS = textView2;
        this.RDSPTY = textView3;
        this.RDSRT = textView4;
        this.btnCollect = imageButton;
        this.btnNext = imageButton2;
        this.btnPlayOrPause = imageButton3;
        this.btnPre = imageButton4;
        this.btnSearchNext = imageButton5;
        this.btnSearchPre = imageButton6;
        this.channelListContainer = bydScrollerSupportedFrameLayout;
        this.collectListContainer = bydScrollerSupportedFrameLayout2;
        this.collectLottie = collectLottieView;
        this.groupPlayingCard = constraintLayout;
        this.groupProgramList = constraintLayout2;
        this.ivNoData = imageView;
        this.nestedScrollView = nestedScrollView;
        this.programFreqUnit = textView5;
        this.programFrequency = textView6;
        this.rbChannel = radioButton;
        this.rbCollect = radioButton2;
        this.rbgProgramList = radioGroup;
        this.rootContainer = constraintLayout3;
        this.rulerView = rulerView;
        this.rvChannelList = recyclerView;
        this.rvCollectList = recyclerView2;
        this.topMirror = imageView2;
        this.topSearch = imageButton7;
        this.topSetting = imageButton8;
    }

    public FMViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentFmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentFmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fm, viewGroup, z, obj);
    }

    public static FragmentFmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFmBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentFmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fm, null, false, obj);
    }

    public static FragmentFmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFmBinding bind(View view, Object obj) {
        return (FragmentFmBinding) bind(obj, view, R.layout.fragment_fm);
    }
}
