package com.byd.data.common.io.bms;

import com.byd.data.common.entity.bms.UploadResult;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.io.BaseCloudOperate;
import com.byd.data.common.server.e.a;
import com.byd.data.common.sqlite.CloudFileTable;
import com.byd.data.common.sqlite.o;
import f.a0;
import f.z;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BmsCloudWrite extends BaseCloudOperate<UploadResult> {
    public BmsCloudWrite(a aVar) {
        super(aVar);
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    public String e(byte b2) {
        StringBuilder sb = new StringBuilder();
        sb.append(com.byd.data.common.g.a.i().h());
        sb.append("app_bms");
        sb.append(File.separator);
        sb.append(m(b2) ? "period" : "monomer");
        return sb.toString();
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected z g() {
        return a0.f5000e;
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected String h() {
        return "file";
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected CloudFileTable i() {
        return o.f().c();
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected byte j() {
        return (byte) 2;
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected void o(List<a0.b> list, com.byd.data.common.http.e.a aVar) {
        super.o(list, aVar);
        ApiRetrofit.h().f().a(list, aVar).C(this);
    }
}
