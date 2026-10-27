package com.permissionx.guolindev.request;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ChainTask {
    void finish();

    ExplainScope getExplainScope();

    ForwardScope getForwardScope();

    void request();

    void requestAgain(List<String> list);
}
