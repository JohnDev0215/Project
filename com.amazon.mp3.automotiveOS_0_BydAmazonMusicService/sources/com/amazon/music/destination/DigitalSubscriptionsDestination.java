package com.amazon.music.destination;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DigitalSubscriptionsDestination extends AbstractWebDestination {
    private final List<String> segments;

    public DigitalSubscriptionsDestination(List<String> list, Map<String, String> map, String str, String str2) {
        super(map, str, str2);
        this.segments = list;
    }

    public List<String> getSegments() {
        return this.segments;
    }
}
