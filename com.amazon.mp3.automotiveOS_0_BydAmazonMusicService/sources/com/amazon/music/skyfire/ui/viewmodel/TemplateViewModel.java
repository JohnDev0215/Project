package com.amazon.music.skyfire.ui.viewmodel;

import CoreInterface.v1_0.Template;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: TemplateViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/viewmodel/TemplateViewModel;", "T", "LCoreInterface/v1_0/Template;", "Landroidx/lifecycle/ViewModel;", "()V", "template", "Landroidx/lifecycle/MutableLiveData;", "getTemplate", "()Landroidx/lifecycle/MutableLiveData;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TemplateViewModel<T extends Template> extends ViewModel {
    private final MutableLiveData<T> template = new MutableLiveData<>();

    public final MutableLiveData<T> getTemplate() {
        return this.template;
    }
}
