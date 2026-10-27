package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.radio.AMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.RulerView;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentAmBinding extends ViewDataBinding {
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
    protected AMViewModel mViewModel;
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

    public abstract void setViewModel(AMViewModel aMViewModel);

    protected FragmentAmBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, ImageButton imageButton5, ImageButton imageButton6, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout2, CollectLottieView collectLottieView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, TextView textView2, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup, ConstraintLayout constraintLayout3, RulerView rulerView, RecyclerView recyclerView, RecyclerView recyclerView2, ImageView imageView2, ImageButton imageButton7) {
        super(obj, view, i);
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
        this.programFreqUnit = textView;
        this.programFrequency = textView2;
        this.rbChannel = radioButton;
        this.rbCollect = radioButton2;
        this.rbgProgramList = radioGroup;
        this.rootContainer = constraintLayout3;
        this.rulerView = rulerView;
        this.rvChannelList = recyclerView;
        this.rvCollectList = recyclerView2;
        this.topMirror = imageView2;
        this.topSearch = imageButton7;
    }

    public AMViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentAmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentAmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_am, viewGroup, z, obj);
    }

    public static FragmentAmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAmBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentAmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_am, null, false, obj);
    }

    public static FragmentAmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAmBinding bind(View view, Object obj) {
        return (FragmentAmBinding) bind(obj, view, R.layout.fragment_am);
    }
}
