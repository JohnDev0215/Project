package com.amazon.music.views.library.binders;

import android.view.View;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseStateChangeBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005J%\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u00012\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "V", "Landroid/view/View;", "M", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "handleStateChange", "", "view", "model", "payload", "", "(Landroid/view/View;Lcom/amazon/music/views/library/models/base/BaseViewModel;Ljava/lang/Object;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseStateChangeBinder<V extends View, M extends BaseViewModel> extends UniversalBinder<V, M> {
    void handleStateChange(V view, M model, Object payload);
}
