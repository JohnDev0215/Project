package com.byd.vrassistant.base;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.lifecycle.Observer;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DirectionAwareImageView extends ImageViewLifecycle {
    private static final String TAG = "NEU_DirectionAwareImageView";
    CommonViewModel commonViewModel;

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initView() {
    }

    public DirectionAwareImageView(Context context) {
        super(context);
        init();
    }

    public DirectionAwareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public DirectionAwareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initViewModel() {
        CommonViewModel commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.commonViewModel = commonViewModel;
        commonViewModel.getLocaleLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.base.DirectionAwareImageView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m128xba09239d((Locale) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initViewModel$0$com-byd-vrassistant-base-DirectionAwareImageView, reason: not valid java name */
    /* synthetic */ void m128xba09239d(Locale locale) {
        updateMirroring();
    }

    private void init() {
        updateMirroring();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "onConfigurationChanged: " + configuration);
        updateMirroring();
    }

    private void updateMirroring() {
        setScaleX(TextUtils.getLayoutDirectionFromLocale(ContextHolder.getCurrentLocale()) == 1 ? -1.0f : 1.0f);
    }
}
