package com.ts.car.input;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyCodeCombine {
    public final List<Integer> combineCode = new ArrayList();
    public final Long combineTimeout;
    public final int transferCode;

    public KeyCodeCombine(int transferCode, Long combineTimeout) {
        this.transferCode = transferCode;
        this.combineTimeout = combineTimeout;
    }

    public KeyCodeCombine addCombineCode(int code) {
        this.combineCode.add(Integer.valueOf(code));
        return this;
    }

    public KeyCodeCombine addCombineCodeList(List<Integer> list) {
        this.combineCode.addAll(list);
        return this;
    }

    public List<Integer> getCombineKeyList() {
        return this.combineCode;
    }

    public int getTransferCode() {
        return this.transferCode;
    }

    public Long getCombineTimeout() {
        return this.combineTimeout;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("combineCode={%s}", StringUtils.join(this.combineCode)));
        sb.append(",");
        sb.append(String.format("transferCode=%s", Integer.valueOf(this.transferCode)));
        sb.append(",");
        sb.append(String.format("combineTimeout=%s", this.combineTimeout));
        return sb.toString();
    }
}
