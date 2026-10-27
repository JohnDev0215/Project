package spi;

import android.view.View;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class bb implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ FeatureCategoryThemeAdapter b;
    public final /* synthetic */ FeatureThemTopicGroup c;
    public final /* synthetic */ int d;

    public /* synthetic */ bb(FeatureCategoryThemeAdapter featureCategoryThemeAdapter, FeatureThemTopicGroup featureThemTopicGroup, int i, int i2) {
        this.a = i2;
        this.b = featureCategoryThemeAdapter;
        this.c = featureThemTopicGroup;
        this.d = i;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                FeatureCategoryThemeAdapter this$0 = this.b;
                FeatureThemTopicGroup itemData = this.c;
                int i = this.d;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData, "$itemData");
                Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                if (function3 != null) {
                    function3.invoke(itemData, Integer.valueOf(view.getId()), Integer.valueOf(i));
                }
                break;
            default:
                FeatureCategoryThemeAdapter this$02 = this.b;
                FeatureThemTopicGroup itemData2 = this.c;
                int i2 = this.d;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                Intrinsics.checkNotNullParameter(itemData2, "$itemData");
                Function3 function32 = (Function3) this$02.b.get(Integer.valueOf(view.getId()));
                if (function32 != null) {
                    function32.invoke(itemData2, Integer.valueOf(view.getId()), Integer.valueOf(i2));
                }
                break;
        }
    }
}
