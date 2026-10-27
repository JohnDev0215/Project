package com.byd.automultipletheme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.AutoBaseHolder;
import com.byd.automultipletheme.databinding.AdapterCarouselItemBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CarouselAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rJ\u0016\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/byd/automultipletheme/adapter/CarouselAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "()V", "isEdit", "", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "", "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", IntentString.DATA_POSITION, "item", "removeItem", "itemCount", "setEditStatus", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAdapter extends AutoBaseAdapter<CarouselPicture> {
    private boolean isEdit;

    public CarouselAdapter() {
        addChildClickViewIds(R.id.item_delete);
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterCarouselItemBinding adapterCarouselItemBindingInflate = AdapterCarouselItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterCarouselItemBindingInflate, "inflate(inflater, container, false)");
        return adapterCarouselItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, final int position, CarouselPicture item) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdapterCarouselItemBinding adapterCarouselItemBindingBind = AdapterCarouselItemBinding.bind(holder.itemView);
        Intrinsics.checkNotNullExpressionValue(adapterCarouselItemBindingBind, "bind(holder.itemView)");
        if (ScreenUtils.isLandscape()) {
            dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_190);
        } else {
            dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_107);
        }
        if (ScreenUtils.isLandscape()) {
            dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_107);
        } else {
            dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_190);
        }
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            adapterCarouselItemBindingBind.itemImage.setImageResource(R.mipmap.ic_delete_denza);
        }
        if (this.isEdit) {
            adapterCarouselItemBindingBind.itemDelete.setVisibility(0);
        } else {
            adapterCarouselItemBindingBind.itemDelete.setVisibility(8);
        }
        if (StringsKt.equals$default(item != null ? item.getLandLight() : null, "add", false, 2, null)) {
            adapterCarouselItemBindingBind.itemDelete.setVisibility(8);
            if (AutoUtils.INSTANCE.getModelType() == 3) {
                adapterCarouselItemBindingBind.itemImage.setImageResource(R.mipmap.ic_add_denza);
            } else {
                adapterCarouselItemBindingBind.itemImage.setImageResource(R.mipmap.ic_add);
            }
        } else if (ScreenUtils.isLandscape()) {
            Glide.with(adapterCarouselItemBindingBind.itemImage).load(item != null ? item.getLandLight() : null).apply((BaseRequestOptions<?>) getOptions()).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(adapterCarouselItemBindingBind.itemImage);
        } else {
            Glide.with(adapterCarouselItemBindingBind.itemImage).load(item != null ? item.getPortLight() : null).apply((BaseRequestOptions<?>) getOptions()).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(adapterCarouselItemBindingBind.itemImage);
        }
        adapterCarouselItemBindingBind.itemDelete.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.adapter.CarouselAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselAdapter.m48onBindData$lambda0(this.f$0, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindData$lambda-0, reason: not valid java name */
    public static final void m48onBindData$lambda0(CarouselAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function3<AutoBaseAdapter<CarouselPicture>, View, Integer, Unit> onItemChildClick = this$0.getOnItemChildClick();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        onItemChildClick.invoke(this$0, view, Integer.valueOf(i));
    }

    public final void setEditStatus(boolean isEdit, int itemCount) {
        this.isEdit = isEdit;
        if (getMList() != null) {
            notifyItemRangeChanged(0, itemCount);
        }
    }

    public final void removeItem(int position, int itemCount) {
        List<CarouselPicture> mList = getMList();
        if (mList != null) {
            mList.remove(position);
            notifyItemRangeChanged(position, itemCount);
        }
    }
}
