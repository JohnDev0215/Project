package com.amazon.avod.content;

import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.media.playback.ContentType;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface HeuristicsProvider {
    Heuristics get(ContentType contentType, Map<String, String> map);
}
