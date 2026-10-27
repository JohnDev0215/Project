package com.byd.automultipletheme.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.AutoBaseHolder;
import com.byd.automultipletheme.databinding.AdapterPreviewImageItemBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreviewImageAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/byd/automultipletheme/adapter/PreviewImageAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "", "dataType", "", "(I)V", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", IntentString.DATA_POSITION, "item", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PreviewImageAdapter extends AutoBaseAdapter<String> {
    private final int dataType;

    public PreviewImageAdapter(int i) {
        this.dataType = i;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterPreviewImageItemBinding adapterPreviewImageItemBindingInflate = AdapterPreviewImageItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterPreviewImageItemBindingInflate, "inflate(inflater, container, false)");
        return adapterPreviewImageItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, int position, String item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdapterPreviewImageItemBinding adapterPreviewImageItemBindingBind = AdapterPreviewImageItemBinding.bind(holder.itemView);
        Intrinsics.checkNotNullExpressionValue(adapterPreviewImageItemBindingBind, "bind(holder.itemView)");
        if (this.dataType == 2) {
            String item2 = getItem(position);
            boolean z = false;
            if (item2 != null && item2.equals("WALLPAPER_TYPE_DAY_NIGHT")) {
                z = true;
            }
            if (z) {
                adapterPreviewImageItemBindingBind.itemImage.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(adapterPreviewImageItemBindingBind.itemImage).load(Integer.valueOf(ScreenUtils.isLandscape() ? R.raw.gd110000011 : R.raw.gdp110000011)).optionalTransform(getRoundedCorners()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(getRoundedCorners())).into(adapterPreviewImageItemBindingBind.itemImage);
                return;
            } else {
                Glide.with(adapterPreviewImageItemBindingBind.itemImage).load(item).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).apply((BaseRequestOptions<?>) getOptions()).diskCacheStrategy(DiskCacheStrategy.ALL).into(adapterPreviewImageItemBindingBind.itemImage);
                return;
            }
        }
        Glide.with(adapterPreviewImageItemBindingBind.itemImage).load(item).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.DATA).into(adapterPreviewImageItemBindingBind.itemImage);
    }
}
