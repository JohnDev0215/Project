package com.byd.data.common.entity.bms;

import com.byd.data.common.c.a;
import com.byd.data.common.entiry.cloud.config.BaseConfigResult;
import com.byd.data.common.i.f;

/* JADX INFO: loaded from: classes.dex */
public class QueryDataBean extends BaseConfigResult<a<QueryDataBean>> {
    private final String TAG = "QueryDataBean";
    public String message;
    public byte uploadCycle;

    public QueryDataBean(QueryResult queryResult) {
        this.countryCode = queryResult.countryCode;
        this.mccMnc = queryResult.mccmnc;
        this.flagType = queryResult.switchStatus;
        this.uploadCycle = queryResult.uploadCycle;
        this.message = queryResult.message;
        queryResult.setUpdateIndex((byte) 1);
        queryResult.rebacekResult = queryResult.code;
        this.uuid = Integer.toString(queryResult.hashCode());
        f.g("QueryDataBean", "result =" + queryResult.code + ", flag =" + ((int) this.flagType));
    }

    @Override // com.byd.data.common.entiry.cloud.config.BaseConfigResult
    public void e() {
    }

    @Override // com.byd.data.common.entiry.cloud.config.BaseConfigResult
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void a(a<QueryDataBean> aVar) {
        if (this.flagType < 1) {
            return;
        }
        f.g("QueryDataBean", "initial cloud data is finish =" + ((int) this.flagType));
    }
}
