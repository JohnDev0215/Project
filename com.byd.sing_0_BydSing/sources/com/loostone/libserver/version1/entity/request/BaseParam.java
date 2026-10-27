package com.loostone.libserver.version1.entity.request;

import com.alibaba.fastjson.annotation.JSONField;

/* JADX INFO: loaded from: classes.dex */
public class BaseParam extends BaseRequest {
    private String _time;
    private Integer offset;
    private Integer pageSize;
    private String sign;
    private String token;
    private String tokenId;
    private String userId;

    public Integer getOffset() {
        return this.offset;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getSign() {
        return this.sign;
    }

    public String getToken() {
        return this.token;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getUserId() {
        return this.userId;
    }

    @Override // com.loostone.libserver.version1.entity.request.BaseRequest
    @JSONField(name = "_time")
    public String get_time() {
        return this._time;
    }

    public void setOffset(Integer num) {
        this.offset = num;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    @Override // com.loostone.libserver.version1.entity.request.BaseRequest
    public void set_time(String str) {
        this._time = str;
    }
}
