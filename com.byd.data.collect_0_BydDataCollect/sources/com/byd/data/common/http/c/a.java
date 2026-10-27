package com.byd.data.common.http.c;

import com.byd.data.common.entiry.cloud.CloudConfig;
import e.a.e;
import f.a0;
import f.h0;
import h.a0.l;
import h.a0.o;
import h.a0.q;
import h.d;
import java.util.List;

/* JADX INFO: compiled from: ApiServer.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    @o("cancollectpost/canCollectConfig/getLastCanCollectConfig/")
    d<CloudConfig> c();

    @l
    @o("cancollectpost/canCollectFileUpload/upload")
    e<com.byd.data.common.b.b> d(@q List<a0.b> list);

    @l
    @o("cancollectpost/canCollectFileUpload/upload")
    d<h0> e(@q List<a0.b> list, @q("test") com.byd.data.common.http.e.a aVar);
}
