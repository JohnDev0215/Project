package com.bydux.api.common.webs.okhttp.constant;

import androidx.webkit.internal.AssetHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MediaType {
    public static final okhttp3.MediaType TEXT = okhttp3.MediaType.parse(AssetHelper.DEFAULT_MIME_TYPE);
    public static final okhttp3.MediaType JSON = okhttp3.MediaType.parse("application/json;charset=utf-8");
    public static final okhttp3.MediaType FORM = okhttp3.MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    public static final okhttp3.MediaType STREAM = okhttp3.MediaType.parse("application/octet-stream");
}
