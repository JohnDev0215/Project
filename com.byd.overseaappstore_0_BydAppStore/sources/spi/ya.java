package spi;

import android.view.View;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryHotAppAdapter;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import com.byd.overseaappstore.ui.view.adapter.UpdateAdapter;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ya implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ BaseRvAdapter c;
    public final /* synthetic */ Object d;

    public /* synthetic */ ya(BaseRvAdapter baseRvAdapter, Object obj, int i, int i2) {
        this.a = i2;
        this.c = baseRvAdapter;
        this.d = obj;
        this.b = i;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                FeatureCategoryHotAppAdapter this$0 = (FeatureCategoryHotAppAdapter) this.c;
                Software itemData = (Software) this.d;
                int i = this.b;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData, "$itemData");
                Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                if (function3 != null) {
                    function3.invoke(itemData, Integer.valueOf(view.getId()), Integer.valueOf(i));
                }
                break;
            case 1:
                FeatureCategoryThemeAdapter this$02 = (FeatureCategoryThemeAdapter) this.c;
                FeatureThemTopicGroup itemData2 = (FeatureThemTopicGroup) this.d;
                int i2 = this.b;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                Function3 function32 = (Function3) this$02.b.get(Integer.valueOf(view.getId()));
                if (function32 != null) {
                    function32.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(i2));
                }
                break;
            default:
                UpdateAdapter this$03 = (UpdateAdapter) this.c;
                Software itemData3 = (Software) this.d;
                int i3 = this.b;
                Intrinsics.checkNotNullParameter(this$03, "this$0");
                Intrinsics.checkNotNullParameter(itemData3, "$itemData");
                Function3 function33 = (Function3) this$03.b.get(Integer.valueOf(view.getId()));
                if (function33 != null) {
                    function33.invoke(itemData3, Integer.valueOf(view.getId()), Integer.valueOf(i3));
                }
                break;
        }
    }
}
