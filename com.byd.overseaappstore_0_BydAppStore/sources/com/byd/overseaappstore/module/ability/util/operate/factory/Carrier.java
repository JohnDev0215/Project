package com.byd.overseaappstore.module.ability.util.operate.factory;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParamWrap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B/\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/operate/factory/Carrier;", "", "optParam", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "optParamList", "", "optParamWrap", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;Ljava/util/List;Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;)V", "getOptParam", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "setOptParam", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;)V", "getOptParamList", "()Ljava/util/List;", "setOptParamList", "(Ljava/util/List;)V", "getOptParamWrap", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;", "setOptParamWrap", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class Carrier {

    @Nullable
    private SoftwareOptParam optParam;

    @Nullable
    private List<SoftwareOptParam> optParamList;

    @Nullable
    private SoftwareOptParamWrap optParamWrap;

    public Carrier() {
        this(null, null, null, 7, null);
    }

    public Carrier(@Nullable SoftwareOptParam softwareOptParam, @Nullable List<SoftwareOptParam> list, @Nullable SoftwareOptParamWrap softwareOptParamWrap) {
        this.optParam = softwareOptParam;
        this.optParamList = list;
        this.optParamWrap = softwareOptParamWrap;
    }

    public /* synthetic */ Carrier(SoftwareOptParam softwareOptParam, List list, SoftwareOptParamWrap softwareOptParamWrap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : softwareOptParam, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : softwareOptParamWrap);
    }

    @Nullable
    public final SoftwareOptParam getOptParam() {
        return this.optParam;
    }

    @Nullable
    public final List<SoftwareOptParam> getOptParamList() {
        return this.optParamList;
    }

    @Nullable
    public final SoftwareOptParamWrap getOptParamWrap() {
        return this.optParamWrap;
    }

    public final void setOptParam(@Nullable SoftwareOptParam softwareOptParam) {
        this.optParam = softwareOptParam;
    }

    public final void setOptParamList(@Nullable List<SoftwareOptParam> list) {
        this.optParamList = list;
    }

    public final void setOptParamWrap(@Nullable SoftwareOptParamWrap softwareOptParamWrap) {
        this.optParamWrap = softwareOptParamWrap;
    }
}
