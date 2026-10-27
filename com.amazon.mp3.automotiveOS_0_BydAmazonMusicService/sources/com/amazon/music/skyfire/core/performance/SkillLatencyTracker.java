package com.amazon.music.skyfire.core.performance;

/* JADX INFO: loaded from: classes4.dex */
public interface SkillLatencyTracker {
    void complete(String str);

    void process(String str);

    void rendering(String str);

    void request(String str);

    void response(String str);
}
