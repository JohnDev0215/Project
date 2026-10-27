package com.byd.automultipletheme.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
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
import com.byd.automultipletheme.databinding.AdapterWallpaperDetailItemBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallpaperDetailAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0012"}, d2 = {"Lcom/byd/automultipletheme/adapter/WallpaperDetailAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "", "()V", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "", "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", IntentString.DATA_POSITION, "item", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperDetailAdapter extends AutoBaseAdapter<String> {
    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterWallpaperDetailItemBinding adapterWallpaperDetailItemBindingInflate = AdapterWallpaperDetailItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterWallpaperDetailItemBindingInflate, "inflate(inflater, container, false)");
        return adapterWallpaperDetailItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, int position, String item) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdapterWallpaperDetailItemBinding adapterWallpaperDetailItemBindingBind = AdapterWallpaperDetailItemBinding.bind(holder.itemView);
        Intrinsics.checkNotNullExpressionValue(adapterWallpaperDetailItemBindingBind, "bind(holder.itemView)");
        if (ScreenUtils.isLandscape()) {
            dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_746);
        } else {
            dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_540);
        }
        if (ScreenUtils.isLandscape()) {
            dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_420);
        } else {
            dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_960);
        }
        String item2 = getItem(position);
        boolean z = false;
        if (item2 != null && item2.equals("WALLPAPER_TYPE_DAY_NIGHT")) {
            z = true;
        }
        if (z) {
            Glide.with(adapterWallpaperDetailItemBindingBind.itemImage).load(Integer.valueOf(ScreenUtils.isLandscape() ? R.raw.gd110000011 : R.raw.gdp110000011)).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).optionalTransform(getRoundedCorners()).optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(getRoundedCorners())).into(adapterWallpaperDetailItemBindingBind.itemImage);
        } else {
            Glide.with(adapterWallpaperDetailItemBindingBind.itemImage).load(item).apply((BaseRequestOptions<?>) getOptions()).centerInside().override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(adapterWallpaperDetailItemBindingBind.itemImage);
        }
    }
}
