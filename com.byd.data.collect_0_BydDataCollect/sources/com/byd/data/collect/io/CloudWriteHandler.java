package com.byd.data.collect.io;

import android.text.TextUtils;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.f;
import com.byd.data.common.i.h;
import com.byd.data.common.io.BaseCloudOperate;
import f.a0;
import f.h0;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes.dex */
public class CloudWriteHandler extends BaseCloudOperate<h0> {

    /* JADX INFO: renamed from: g */
    private TreeMap<String, BufferInfo> f3152g;

    public CloudWriteHandler(com.byd.data.common.server.e.a aVar) {
        super(aVar);
        this.f3152g = new TreeMap<>();
    }

    private File s(String str, byte b2, String str2, long j) {
        return new File(str, h.a(j, m(b2) ? "yyyyMMddHHmmss" : "yyyyMMddHHmmssSSS") + "_" + str2 + ".data");
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected void b(String str) {
        if ((this.f3152g != null) && (true ^ TextUtils.isEmpty(str))) {
            this.f3152g.remove(str);
        }
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected String e(byte b2) {
        StringBuilder sb = new StringBuilder();
        sb.append(com.byd.data.common.g.a.i().h());
        sb.append(m(b2) ? com.byd.data.common.d.a.f3302a : com.byd.data.common.d.a.f3303b);
        return sb.toString();
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected File l(String str, byte b2, String str2, long j) {
        if (this.f3152g == null || TextUtils.isEmpty(str2)) {
            return s(str, b2, str2, j);
        }
        BufferInfo bufferInfo = this.f3152g.get(str2);
        if (bufferInfo == null) {
            return s(str, b2, str2, j);
        }
        File fileB = bufferInfo.b();
        if (!fileB.exists()) {
            return s(str, b2, str2, j);
        }
        BufferedSink bufferedSink = bufferInfo.f3150a;
        this.f3436e = bufferedSink;
        if (bufferedSink == null) {
            f.k(this.f3433b, "map bufferedSink is null =" + str2);
        }
        return fileB;
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected boolean n(boolean z, String str, byte b2, File file) {
        boolean zN = super.n(z, str, b2, file);
        BufferedSink bufferedSink = this.f3436e;
        if ((bufferedSink != null) & zN) {
            this.f3152g.put(str, new BufferInfo(bufferedSink, file));
        }
        return zN;
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    protected void o(List<a0.b> list, com.byd.data.common.http.e.a aVar) {
        super.o(list, aVar);
        ApiRetrofit.h().d().e(list, aVar).C(this);
    }

    @Override // com.byd.data.common.io.BaseCloudOperate
    public void q() {
        BufferInfo value;
        BufferedSink bufferedSinkA;
        TreeMap<String, BufferInfo> treeMap = this.f3152g;
        if (treeMap == null || treeMap.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, BufferInfo>> it = this.f3152g.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, BufferInfo> next = it.next();
            it.remove();
            if (next != null && (value = next.getValue()) != null && (bufferedSinkA = value.a()) != null) {
                try {
                    bufferedSinkA.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.f3152g = null;
        f.g(this.f3433b, "stopUpload flush all sink end");
    }

    public void t() {
        f.g(this.f3433b, "temporaryStopUpload hasUpMsg");
    }
}
