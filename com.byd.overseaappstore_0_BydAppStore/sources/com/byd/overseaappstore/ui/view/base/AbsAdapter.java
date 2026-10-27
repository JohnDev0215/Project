package com.byd.overseaappstore.ui.view.base;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.byd.overseaappstore.ui.view.base.AbsHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/AbsAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "H", "Lcom/byd/overseaappstore/ui/view/base/AbsHolder;", "Landroidx/recyclerview/widget/ListAdapter;", "differ", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbsAdapter<T, H extends AbsHolder> extends ListAdapter<T, H> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbsAdapter(@NotNull DiffUtil.ItemCallback<T> differ) {
        super(differ);
        Intrinsics.checkNotNullParameter(differ, "differ");
    }
}
