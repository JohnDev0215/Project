package com.byd.logtools.auth.e;

import com.byd.logtools.auth.data.QrCodePostData;
import com.byd.logtools.auth.data.QrCodeResponse;
import retrofit2.d;
import retrofit2.y.o;

/* JADX INFO: compiled from: RetrofitService.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    @o("authentication/verify")
    d<QrCodeResponse> a(@retrofit2.y.a QrCodePostData qrCodePostData);
}
