package com.amazon.music.views.library.recyclerview;

import android.view.View;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CompositeClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R!\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/CompositeClickListener;", "Landroid/view/View$OnClickListener;", "Landroid/view/View;", "view", "", "onClick", "", "listOfClickListeners", "Ljava/util/List;", "getListOfClickListeners", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CompositeClickListener implements View.OnClickListener {
    private final List<View.OnClickListener> listOfClickListeners;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeClickListener(List<? extends View.OnClickListener> list) {
        this.listOfClickListeners = list;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        List<View.OnClickListener> list = this.listOfClickListeners;
        if (list == null) {
            return;
        }
        for (View.OnClickListener onClickListener : list) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
}
