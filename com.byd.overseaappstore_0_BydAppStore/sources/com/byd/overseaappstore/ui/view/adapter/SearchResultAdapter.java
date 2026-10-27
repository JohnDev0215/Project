package com.byd.overseaappstore.ui.view.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.collection.ArraySet;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.ItemSearchResultBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.SearchResultAdapter;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import com.byd.overseaappstore.ui.view.base.ContentViewHolder;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ro;
import spi.to;
import spi.uo;
import spi.vd;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/SearchResultAdapter;", "Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "Lcom/byd/overseaappstore/databinding/ItemSearchResultBinding;", "Landroidx/collection/ArraySet;", "", "picErrorCollect", "<init>", "(Landroidx/collection/ArraySet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchResultAdapter extends BaseRvAdapter<Software, ItemSearchResultBinding> {

    @NotNull
    public final ArraySet<String> c;

    @Nullable
    public Function1<? super Software, Unit> d;

    @NotNull
    public String e;

    public SearchResultAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchResultAdapter(@NotNull ArraySet<String> picErrorCollect) {
        super(new uo(picErrorCollect));
        Intrinsics.checkNotNullParameter(picErrorCollect, "picErrorCollect");
        this.c = picErrorCollect;
        this.e = "";
    }

    public /* synthetic */ SearchResultAdapter(ArraySet arraySet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArraySet() : arraySet);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final ViewBinding b(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSearchResultBinding itemSearchResultBindingA = ItemSearchResultBinding.a(LayoutInflater.from(context), parent);
        Intrinsics.checkNotNullExpressionValue(itemSearchResultBindingA, "inflate(LayoutInflater.f…(context), parent, false)");
        return itemSearchResultBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void c(ContentViewHolder contentViewHolder, Software software, int i) {
        final Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        SkinAppCompatTextView skinAppCompatTextView = ((ItemSearchResultBinding) contentViewHolder.a).d;
        String name = itemData.getName();
        String str = this.e;
        Context context = ((ItemSearchResultBinding) contentViewHolder.a).a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "contentViewHolder.binding.root.context");
        skinAppCompatTextView.setText(f(name, str, context));
        ((ItemSearchResultBinding) contentViewHolder.a).e.setText(itemData.getSizeTip());
        ((ItemSearchResultBinding) contentViewHolder.a).f.setText(itemData.getClassifyName());
        this.c.remove(itemData.getPackageName());
        vd vdVar = vd.a;
        Context context2 = ((ItemSearchResultBinding) contentViewHolder.a).a.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "contentViewHolder.binding.root.context");
        String icon = itemData.getIcon();
        ImageView imageView = ((ItemSearchResultBinding) contentViewHolder.a).c;
        Intrinsics.checkNotNullExpressionValue(imageView, "contentViewHolder.binding.ivIcon");
        vdVar.b(context2, icon, imageView, SearchResultAdapter.class, new to(this, itemData));
        ((ItemSearchResultBinding) contentViewHolder.a).b.setOnClickListener(new View.OnClickListener() { // from class: spi.so
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchResultAdapter this$0 = this.a;
                Software itemData2 = itemData;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                Function1<? super Software, Unit> function1 = this$0.d;
                if (function1 != null) {
                    function1.invoke(itemData2);
                }
            }
        });
        DownloadProgressButtonView downloadProgressButtonView = ((ItemSearchResultBinding) contentViewHolder.a).b;
        Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.dpbOperate");
        downloadProgressButtonView.d(itemData.getState(), itemData.getPercentCount(), true);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseRvAdapter
    public final void e(ContentViewHolder contentViewHolder, Software software, int i, List payloads) {
        Software itemData = software;
        Intrinsics.checkNotNullParameter(contentViewHolder, "contentViewHolder");
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        int i2 = 0;
        Object obj = payloads.get(0);
        if (obj instanceof Software) {
            Software software2 = (Software) obj;
            ((ItemSearchResultBinding) contentViewHolder.a).f.setText(software2.getClassifyName());
            DownloadProgressButtonView downloadProgressButtonView = ((ItemSearchResultBinding) contentViewHolder.a).b;
            Intrinsics.checkNotNullExpressionValue(downloadProgressButtonView, "contentViewHolder.binding.dpbOperate");
            int state = software2.getState();
            int percentCount = software2.getPercentCount();
            int i3 = DownloadProgressButtonView.d;
            downloadProgressButtonView.d(state, percentCount, true);
            SkinAppCompatTextView skinAppCompatTextView = ((ItemSearchResultBinding) contentViewHolder.a).d;
            String name = itemData.getName();
            String str = this.e;
            Context context = ((ItemSearchResultBinding) contentViewHolder.a).a.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "contentViewHolder.binding.root.context");
            skinAppCompatTextView.setText(f(name, str, context));
            ((ItemSearchResultBinding) contentViewHolder.a).b.setOnClickListener(new ro(this, itemData, i2));
        }
    }

    public final SpannableString f(String str, String str2, Context context) {
        int iIndexOf;
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (i < str.length() && (iIndexOf = StringsKt__StringsKt.indexOf(str, str2, i, true)) >= 0) {
            int length = str2.length() + iIndexOf;
            vm vmVar = vm.a;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            spannableString.setSpan(new ForegroundColorSpan(vmVar.b(applicationContext, vmVar.a(context, R.color.search_result_highlight_color))), iIndexOf, length, 17);
            i = length;
        }
        return spannableString;
    }
}
