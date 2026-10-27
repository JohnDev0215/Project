package com.byd.automultipletheme.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.AutoBaseHolder;
import com.byd.automultipletheme.common.base.GlideRoundTransform;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.AdapterCarouselChildItemBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselAddChildAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u001e\u0010\u0013\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00150\u0014J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010\u001e\u001a\u00020\u001a2\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tJ \u0010 \u001a\u00020\u001a2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0007j\b\u0012\u0004\u0012\u00020\u0002`\tH\u0016J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/byd/automultipletheme/adapter/CarouselAddChildAdapter;", "Lcom/byd/automultipletheme/common/adapter/AutoBaseAdapter;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", IntentString.DATA_TYPE, "", "(I)V", "arrayList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sparseArray", "Landroid/util/SparseBooleanArray;", "getDataBinding", "Landroidx/viewbinding/ViewBinding;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "viewType", "getSelectList", "Lkotlin/Pair;", "", "isSelect", "", IntentString.DATA_POSITION, "onBindData", "", "holder", "Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", "item", "setCountNum", "countList", "setData", IntentString.DATA_LIST, "setSelect", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAddChildAdapter extends AutoBaseAdapter<CarouselPicture> {
    private int type;
    private SparseBooleanArray sparseArray = new SparseBooleanArray();
    private ArrayList<String> arrayList = new ArrayList<>();

    public CarouselAddChildAdapter(int i) {
        this.type = i;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public ViewBinding getDataBinding(LayoutInflater inflater, ViewGroup container, int viewType) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(container, "container");
        AdapterCarouselChildItemBinding adapterCarouselChildItemBindingInflate = AdapterCarouselChildItemBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(adapterCarouselChildItemBindingInflate, "inflate(inflater, container, false)");
        return adapterCarouselChildItemBindingInflate;
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void onBindData(AutoBaseHolder holder, int position, CarouselPicture item) {
        String portLight;
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdapterCarouselChildItemBinding adapterCarouselChildItemBindingBind = AdapterCarouselChildItemBinding.bind(holder.itemView);
        Intrinsics.checkNotNullExpressionValue(adapterCarouselChildItemBindingBind, "bind(holder.itemView)");
        if (item != null) {
            if (ScreenUtils.isLandscape()) {
                if (AutoUtils.INSTANCE.isDark()) {
                    portLight = item.getLandNight();
                } else {
                    portLight = item.getLandLight();
                }
            } else if (AutoUtils.INSTANCE.isDark()) {
                portLight = item.getPortNight();
            } else {
                portLight = item.getPortLight();
            }
            boolean z = this.sparseArray.get(position);
            String fileName = FileUtils.getFileName(item.getLandLight());
            LogUtils.INSTANCE.i("pathname_a:", fileName);
            if (z) {
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    adapterCarouselChildItemBindingBind.itemSelected.setImageResource(R.mipmap.ic_carousel_add_selected_denza);
                } else {
                    adapterCarouselChildItemBindingBind.itemSelected.setImageResource(R.mipmap.ic_carousel_add_selected);
                }
                int iIndexOf = this.arrayList.indexOf(fileName) + 1;
                adapterCarouselChildItemBindingBind.itemCount.setVisibility(0);
                adapterCarouselChildItemBindingBind.itemCount.setText(String.valueOf(iIndexOf));
            } else {
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    adapterCarouselChildItemBindingBind.itemSelected.setImageResource(R.mipmap.ic_carousel_add_unselected_denza);
                } else {
                    adapterCarouselChildItemBindingBind.itemSelected.setImageResource(R.mipmap.ic_carousel_add_unselected);
                }
                adapterCarouselChildItemBindingBind.itemCount.setVisibility(4);
            }
            Glide.with(adapterCarouselChildItemBindingBind.itemImage).load(portLight).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).override(holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_109), holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.dp_109)).transform(new CenterCrop(), new GlideRoundTransform(10)).into(adapterCarouselChildItemBindingBind.itemImage);
        }
    }

    @Override // com.byd.automultipletheme.common.adapter.AutoBaseAdapter
    public void setData(ArrayList<CarouselPicture> list) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList<CarouselPicture> carouselRes = DBUtils.INSTANCE.getCarouselRes();
        ArrayList<CarouselPicture> arrayList = list;
        int i = 0;
        if (this.type == 1) {
            HashMap map = new HashMap();
            Iterator<CarouselPicture> it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap map2 = map;
                map2.put(AssetsUtils.INSTANCE.getPathName(it.next().getPortLight()), Integer.valueOf(i));
                i++;
            }
            Iterator<CarouselPicture> it2 = carouselRes.iterator();
            while (it2.hasNext()) {
                String pathName = AssetsUtils.INSTANCE.getPathName(it2.next().getPortLight());
                if (map.get(pathName) != null) {
                    SparseBooleanArray sparseBooleanArray = this.sparseArray;
                    Object obj = map.get(pathName);
                    Intrinsics.checkNotNull(obj);
                    sparseBooleanArray.put(((Number) obj).intValue(), true);
                }
            }
        } else {
            HashMap map3 = new HashMap();
            Iterator<CarouselPicture> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                HashMap map4 = map3;
                map4.put(AssetsUtils.INSTANCE.getPathName(it3.next().getPortLight()), Integer.valueOf(i));
                i++;
            }
            Iterator<CarouselPicture> it4 = carouselRes.iterator();
            while (it4.hasNext()) {
                String pathName2 = AssetsUtils.INSTANCE.getPathName(it4.next().getPortLight());
                if (map3.get(pathName2) != null) {
                    SparseBooleanArray sparseBooleanArray2 = this.sparseArray;
                    Object obj2 = map3.get(pathName2);
                    Intrinsics.checkNotNull(obj2);
                    sparseBooleanArray2.put(((Number) obj2).intValue(), true);
                }
            }
        }
        super.setData(list);
    }

    public final void setCountNum(ArrayList<String> countList) {
        if (countList != null) {
            this.arrayList = countList;
        }
        notifyItemRangeChanged(0, getItemCount());
        LogUtils.INSTANCE.i("list_setCountNum", this.arrayList);
    }

    public final boolean setSelect(int position) {
        boolean z = this.sparseArray.get(position);
        if (z) {
            this.sparseArray.put(position, false);
        } else {
            this.sparseArray.put(position, true);
        }
        notifyItemChanged(position);
        return !z;
    }

    public final boolean isSelect(int position) {
        return this.sparseArray.get(position);
    }

    public final Pair<List<CarouselPicture>, List<CarouselPicture>> getSelectList() {
        Pair<List<CarouselPicture>, List<CarouselPicture>> pair = new Pair<>(new ArrayList(), new ArrayList());
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (this.sparseArray.get(i)) {
                List<CarouselPicture> first = pair.getFirst();
                CarouselPicture item = getItem(i);
                Intrinsics.checkNotNull(item);
                first.add(item);
            } else {
                List<CarouselPicture> second = pair.getSecond();
                CarouselPicture item2 = getItem(i);
                Intrinsics.checkNotNull(item2);
                second.add(item2);
            }
        }
        return pair;
    }
}
