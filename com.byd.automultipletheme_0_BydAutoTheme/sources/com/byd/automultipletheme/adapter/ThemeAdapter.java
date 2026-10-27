package com.byd.automultipletheme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoTheme;
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

/* JADX INFO: compiled from: ThemeAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0012"}, d2 = {"Lcom/byd/automultipletheme/adapter/ThemeAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "Lcom/byd/automultipletheme/bean/AutoTheme;", "()V", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "", "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", IntentString.DATA_POSITION, "item", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThemeAdapter extends AutoBaseAdapter<AutoTheme> {
    public ThemeAdapter() {
        addChildClickViewIds(R.id.item_action);
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterThemeItemBinding adapterThemeItemBindingInflate = AdapterThemeItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterThemeItemBindingInflate, "inflate(inflater, container, false)");
        return adapterThemeItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, final int position, AutoTheme item) {
        String str;
        int dimensionPixelSize;
        int dimensionPixelSize2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (item != null) {
            AdapterThemeItemBinding adapterThemeItemBindingBind = AdapterThemeItemBinding.bind(holder.itemView);
            Intrinsics.checkNotNullExpressionValue(adapterThemeItemBindingBind, "bind(holder.itemView)");
            if (AutoUtils.INSTANCE.getModelType() == 3) {
                adapterThemeItemBindingBind.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_adapter_item_bg_denza));
                adapterThemeItemBindingBind.itemName.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.ItemNametextColor_denza));
            }
            switch (item.getRsId()) {
                case Const.APP_THEME_DEFAULT_ID /* 100000010 */:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_default_theme));
                    break;
                case 100000011:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_theme_NO1));
                    break;
                case 100000012:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_theme_NO2));
                    break;
                case 100000013:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_theme_NO3));
                    break;
                case 100000014:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_theme_NO4));
                    break;
                case 100000015:
                    adapterThemeItemBindingBind.itemName.setText(Utils.getApp().getResources().getString(R.string.str_theme_NO5));
                    break;
                default:
                    adapterThemeItemBindingBind.itemName.setText(String.valueOf(item.getRsId()));
                    break;
            }
            holder.itemView.setSelected(false);
            BuildersKt__Builders_commonKt.launch$default(this, null, null, new ThemeAdapter$onBindData$1$1(item, adapterThemeItemBindingBind, holder, null), 3, null);
            int modelType = AutoUtils.INSTANCE.getModelType();
            if (modelType != 2) {
                if (modelType == 3) {
                    if (ScreenUtils.isLandscape()) {
                        str = item.getImgUrl().getDenza().getLand().get(0);
                    } else {
                        str = item.getImgUrl().getDenza().getPort().get(0);
                    }
                } else if (ScreenUtils.isLandscape()) {
                    str = item.getImgUrl().getDynasty().getLand().get(0);
                } else {
                    str = item.getImgUrl().getDynasty().getPort().get(0);
                }
            } else if (ScreenUtils.isLandscape()) {
                str = item.getImgUrl().getOcean().getLand().get(0);
            } else {
                str = item.getImgUrl().getOcean().getPort().get(0);
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
            Glide.with(adapterThemeItemBindingBind.itemImage).load(str).apply((BaseRequestOptions<?>) getOptions()).override(dimensionPixelSize, dimensionPixelSize2).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.DATA).into(adapterThemeItemBindingBind.itemImage);
            adapterThemeItemBindingBind.itemAction.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.adapter.ThemeAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ThemeAdapter.m49onBindData$lambda1$lambda0(this.f$0, position, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindData$lambda-1$lambda-0, reason: not valid java name */
    public static final void m49onBindData$lambda1$lambda0(ThemeAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function3<AutoBaseAdapter<AutoTheme>, View, Integer, Unit> onItemChildClick = this$0.getOnItemChildClick();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        onItemChildClick.invoke(this$0, view, Integer.valueOf(i));
    }
}
