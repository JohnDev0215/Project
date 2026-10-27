package com.byd.vrassistant.view;

import android.content.res.Resources;
import android.widget.ImageView;
import com.byd.vrassistant.bean.WaveStateWithArea;

/* JADX INFO: loaded from: classes2.dex */
public class AnimStateManger {
    private static final String TAG = "NEU_AnimStateManger";
    WaveStateWithArea currentWaveState;
    ImageView ivTaiji;
    ImageView ivWave;
    Resources resources;

    public AnimStateManger(ImageView imageView, ImageView imageView2, Resources resources) {
        this.ivTaiji = imageView;
        this.ivWave = imageView2;
        this.resources = resources;
    }
}
