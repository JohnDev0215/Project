package com.amazon.music.find.skyfire.common;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: Refreshable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/find/skyfire/common/Refreshable;", "", "refresh", "", "uri", "", "queryParams", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface Refreshable {
    void refresh(String uri, Map<String, String> queryParams);
}
