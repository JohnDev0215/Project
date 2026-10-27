package com.byd.overseaappstore.module.ability.data.req;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/SoftwareDetailReq;", "", "software", "", "language", "", "(IS)V", "getLanguage", "()S", "getSoftware", "()I", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareDetailReq {
    private final short language;
    private final int software;

    public SoftwareDetailReq(int i, short s) {
        this.software = i;
        this.language = s;
    }

    public final short getLanguage() {
        return this.language;
    }

    public final int getSoftware() {
        return this.software;
    }
}
