package com.byd.vrassistant.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.byd.vrassistant.ContextHolder;

/* JADX INFO: loaded from: classes2.dex */
public class ViewModelHelper {
    public static <T extends ViewModel> T getAppViewModel(Class<T> cls) {
        return (T) new ViewModelProvider((ViewModelStoreOwner) ContextHolder.getApplication()).get(cls);
    }
}
