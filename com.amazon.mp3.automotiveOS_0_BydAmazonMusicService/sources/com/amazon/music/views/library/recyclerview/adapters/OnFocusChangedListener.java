package com.amazon.music.views.library.recyclerview.adapters;

import android.view.View;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnFocusChangedListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/OnFocusChangedListener;", "", "onFocusChanged", "", "view", "Landroid/view/View;", "model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "hasFocus", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OnFocusChangedListener {
    void onFocusChanged(View view, BaseViewModel model, boolean hasFocus);
}
