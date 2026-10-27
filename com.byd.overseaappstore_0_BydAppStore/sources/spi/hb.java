package spi;

import android.view.View;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.FeatureCenterAdapter;
import com.byd.overseaappstore.ui.view.adapter.TopicDetailAdapter;
import com.byd.overseaappstore.ui.view.base.BaseRvAdapter;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hb implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Software b;
    public final /* synthetic */ int c;
    public final /* synthetic */ BaseRvAdapter d;

    public /* synthetic */ hb(BaseRvAdapter baseRvAdapter, Software software, int i, int i2) {
        this.a = i2;
        this.d = baseRvAdapter;
        this.b = software;
        this.c = i;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, kotlin.jvm.functions.Function3<Data, java.lang.Integer, java.lang.Integer, kotlin.Unit>>] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case 0:
                FeatureCenterAdapter this$0 = (FeatureCenterAdapter) this.d;
                Software itemData = this.b;
                int i = this.c;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(itemData, "$itemData");
                Function3 function3 = (Function3) this$0.b.get(Integer.valueOf(view.getId()));
                if (function3 != null) {
                    function3.invoke(itemData, Integer.valueOf(view.getId()), Integer.valueOf(i));
                }
                break;
            default:
                TopicDetailAdapter this$02 = (TopicDetailAdapter) this.d;
                Software itemData2 = this.b;
                int i2 = this.c;
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
