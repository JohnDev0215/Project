package com.byd.automultipletheme.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.AutoBaseHolder;
import com.byd.automultipletheme.databinding.AdapterThemeItemBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: compiled from: WallpaperAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0012"}, d2 = {"Lcom/byd/automultipletheme/adapter/WallpaperAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "()V", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "", "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", IntentString.DATA_POSITION, "item", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperAdapter extends AutoBaseAdapter<AutoWallpaper> {
    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterThemeItemBinding adapterThemeItemBindingInflate = AdapterThemeItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterThemeItemBindingInflate, "inflate(inflater, container, false)");
        return adapterThemeItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, final int position, AutoWallpaper item) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdapterThemeItemBinding adapterThemeItemBindingBind = AdapterThemeItemBinding.bind(holder.itemView);
        Intrinsics.checkNotNullExpressionValue(adapterThemeItemBindingBind, "bind(holder.itemView)");
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            adapterThemeItemBindingBind.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_adapter_item_bg_denza));
            adapterThemeItemBindingBind.itemName.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.ItemNametextColor_denza));
        }
        if (item != null) {
            switch (item.getRsId()) {
                case Const.APP_WALLPAPER_DEFAULT_ID /* 110000010 */:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_default_theme));
                    break;
                case 110000011:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_time_wallpaper));
                    break;
                case 110000012:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_wallpaper_NO2));
                    break;
                case 110000013:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_wallpaper_NO3));
                    break;
                case 110000014:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_wallpaper_NO4));
                    break;
                case 110000015:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_wallpaper_NO5));
                    break;
                default:
                    adapterThemeItemBindingBind.itemName.setText(String.valueOf(item.getRsId()));
                    break;
            }
            holder.itemView.setSelected(false);
            BuildersKt__Builders_commonKt.launch$default(this, null, null, new WallpaperAdapter$onBindData$1$1(item, adapterThemeItemBindingBind, holder, null), 3, null);
            String string = item.getImgUrl().getPort().get(0);
            int rsType = item.getRsType();
            if (rsType != 1) {
                if (rsType == 15) {
                    if (ScreenUtils.isLandscape()) {
                        string = Uri.parse("android.resource://com.byd.automultipletheme/raw/g" + item.getRsId()).toString();
                    } else {
                        string = Uri.parse("android.resource://com.byd.automultipletheme/raw/gp" + item.getRsId()).toString();
                    }
                } else if (ScreenUtils.isLandscape()) {
                    string = item.getImgUrl().getLand().get(0);
                }
            } else if (ScreenUtils.isLandscape()) {
                string = Uri.parse("android.resource://com.byd.automultipletheme/raw/g" + item.getRsId()).toString();
            } else {
                string = Uri.parse("android.resource://com.byd.automultipletheme/raw/gp" + item.getRsId()).toString();
            }
            if (ScreenUtils.isLandscape()) {
                dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_448);
            } else {
                dimensionPixelSize = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_276);
            }
            if (ScreenUtils.isLandscape()) {
                dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_252);
            } else {
                dimensionPixelSize2 = holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_490);
            }
            if (item.getRsType() == 1) {
                Glide.with(adapterThemeItemBindingBind.itemImage).load(Integer.valueOf(ScreenUtils.isLandscape() ? R.raw.g110000010 : R.raw.gp110000010)).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).optionalTransform(getRoundedCorners()).optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(getRoundedCorners())).into(adapterThemeItemBindingBind.itemImage);
            } else if (item.getRsType() == 15) {
                Glide.with(adapterThemeItemBindingBind.itemImage).load(Integer.valueOf(ScreenUtils.isLandscape() ? R.raw.g110000011 : R.raw.gp110000011)).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).optionalTransform(getRoundedCorners()).optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(getRoundedCorners())).into(adapterThemeItemBindingBind.itemImage);
            } else {
                Glide.with(adapterThemeItemBindingBind.itemImage).load(string).apply((BaseRequestOptions<?>) getOptions()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).override(dimensionPixelSize, dimensionPixelSize2).diskCacheStrategy(DiskCacheStrategy.ALL).into(adapterThemeItemBindingBind.itemImage);
            }
            adapterThemeItemBindingBind.itemAction.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.adapter.WallpaperAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WallpaperAdapter.m50onBindData$lambda1$lambda0(this.f$0, position, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindData$lambda-1$lambda-0, reason: not valid java name */
    public static final void m50onBindData$lambda1$lambda0(WallpaperAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function3<AutoBaseAdapter<AutoWallpaper>, View, Integer, Unit> onItemChildClick = this$0.getOnItemChildClick();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        onItemChildClick.invoke(this$0, view, Integer.valueOf(i));
    }
}
