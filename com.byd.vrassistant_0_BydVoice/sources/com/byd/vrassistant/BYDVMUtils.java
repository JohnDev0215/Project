package com.byd.vrassistant;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* JADX INFO: loaded from: classes.dex */
public class BYDVMUtils {
    private static BYDVMUtils bydvmUtils;

    public static BYDVMUtils getInstance() {
        if (bydvmUtils == null) {
            bydvmUtils = new BYDVMUtils();
        }
        return bydvmUtils;
    }

    public BYDViewModel getBYDVMViewModel() {
        return (BYDViewModel) getApplicationViewModel(BYDViewModel.class);
    }

    public static <T extends ViewModel> T getApplicationViewModel(Class<T> cls) {
        return (T) new ViewModelProvider(BydVoiceApplication.getContext()).get(cls);
    }
}
