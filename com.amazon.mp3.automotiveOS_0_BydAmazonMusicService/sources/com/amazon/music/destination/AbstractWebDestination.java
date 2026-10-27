package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractWebDestination extends Destination {
    private final Map<String, String> queryParameters;

    public AbstractWebDestination(Map<String, String> map, String str, String str2) {
        super(str, str2);
        this.queryParameters = map;
    }

    public Map<String, String> getQueryParameters() {
        return this.queryParameters;
    }
}
