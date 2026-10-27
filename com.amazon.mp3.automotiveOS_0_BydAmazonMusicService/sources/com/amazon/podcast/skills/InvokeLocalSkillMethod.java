package com.amazon.podcast.skills;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;

/* JADX INFO: loaded from: classes5.dex */
public final class InvokeLocalSkillMethod extends Method {
    private final LocalSkillTarget target;

    public InvokeLocalSkillMethod(final LocalSkillTarget target, final Queue queue) {
        this.target = target;
        setQueue(queue);
    }

    public LocalSkillTarget getTarget() {
        return this.target;
    }
}
