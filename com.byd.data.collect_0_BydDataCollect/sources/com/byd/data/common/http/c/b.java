package com.byd.data.common.http.c;

import com.byd.data.common.entity.bms.QueryResult;
import com.byd.data.common.entity.bms.UploadResult;
import f.a0;
import f.f0;
import h.a0.l;
import h.a0.o;
import h.a0.q;
import h.d;
import java.util.List;

/* JADX INFO: compiled from: BmsApiServer.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends a {
    @l
    @o("bms/uploadFile")
    d<UploadResult> a(@q List<a0.b> list, @q("test") f0 f0Var);

    @o("bms/switch")
    d<QueryResult> b();
}
