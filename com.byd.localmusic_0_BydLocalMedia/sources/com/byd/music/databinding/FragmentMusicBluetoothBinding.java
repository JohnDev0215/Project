package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentMusicBluetoothBinding extends ViewDataBinding {
    public final ConstraintLayout androidauto;
    public final ImageView autoImgTow;
    public final TextView autoTextPhone;
    public final ImageView btBluetooth;
    public final ImageButton btRadioPlay;
    public final Button btRadioPlayNext;
    public final Button btRadioPlayPre;
    public final ConstraintLayout clContent;
    public final ConstraintLayout clControl;
    public final ConstraintLayout clNoConnect;
    public final ConstraintLayout clProgress;
    public final ImageView imageView2;
    public final ShapeableImageView ivIconAblum;
    public final ShapeableImageView ivIconAlbumPort;
    public final ImageView ivNoConnect;
    public final ImageView ivVoice;
    public final LinearLayout llBlueContent;
    public final LinearLayout llBluetooth;

    @Bindable
    protected BluetoothViewModel mViewModel;
    public final SeekBar seekBar;
    public final TextView textView;
    public final TextView tvMusicName;
    public final TextView tvNoConnect;
    public final TextView tvRadioCurrentTime;
    public final TextView tvRadioTotalTime;
    public final TextView tvSinger;
    public final TextView tvToConnect;

    public abstract void setViewModel(BluetoothViewModel bluetoothViewModel);

    protected FragmentMusicBluetoothBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, ImageView imageView2, ImageButton imageButton, Button button, Button button2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ImageView imageView3, ShapeableImageView shapeableImageView, ShapeableImageView shapeableImageView2, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, LinearLayout linearLayout2, SeekBar seekBar, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.androidauto = constraintLayout;
        this.autoImgTow = imageView;
        this.autoTextPhone = textView;
        this.btBluetooth = imageView2;
        this.btRadioPlay = imageButton;
        this.btRadioPlayNext = button;
        this.btRadioPlayPre = button2;
        this.clContent = constraintLayout2;
        this.clControl = constraintLayout3;
        this.clNoConnect = constraintLayout4;
        this.clProgress = constraintLayout5;
        this.imageView2 = imageView3;
        this.ivIconAblum = shapeableImageView;
        this.ivIconAlbumPort = shapeableImageView2;
        this.ivNoConnect = imageView4;
        this.ivVoice = imageView5;
        this.llBlueContent = linearLayout;
        this.llBluetooth = linearLayout2;
        this.seekBar = seekBar;
        this.textView = textView2;
        this.tvMusicName = textView3;
        this.tvNoConnect = textView4;
        this.tvRadioCurrentTime = textView5;
        this.tvRadioTotalTime = textView6;
        this.tvSinger = textView7;
        this.tvToConnect = textView8;
    }

    public BluetoothViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentMusicBluetoothBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMusicBluetoothBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentMusicBluetoothBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_music_bluetooth, viewGroup, z, obj);
    }

    public static FragmentMusicBluetoothBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMusicBluetoothBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentMusicBluetoothBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_music_bluetooth, null, false, obj);
    }

    public static FragmentMusicBluetoothBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMusicBluetoothBinding bind(View view, Object obj) {
        return (FragmentMusicBluetoothBinding) bind(obj, view, R.layout.fragment_music_bluetooth);
    }
}
