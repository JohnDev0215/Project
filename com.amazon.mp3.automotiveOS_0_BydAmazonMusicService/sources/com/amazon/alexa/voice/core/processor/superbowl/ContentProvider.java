package com.amazon.alexa.voice.core.processor.superbowl;

import android.util.ArrayMap;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentProvider {
    private final ArrayMap<String, Content> map = new ArrayMap<>();

    public void add(Content content) {
        Preconditions.notNull(content, "content == null");
        this.map.put(content.id(), content);
    }

    public Content get(String str) {
        Preconditions.notNull(str, "id == null");
        return this.map.get(str);
    }
}
