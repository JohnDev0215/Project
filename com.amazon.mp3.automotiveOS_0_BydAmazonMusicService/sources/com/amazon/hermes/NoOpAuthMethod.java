package com.amazon.hermes;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class NoOpAuthMethod implements AuthMethod {
    @Override // com.amazon.hermes.AuthMethod
    public Map<String, String> getHeaders() {
        return new HashMap();
    }
}
