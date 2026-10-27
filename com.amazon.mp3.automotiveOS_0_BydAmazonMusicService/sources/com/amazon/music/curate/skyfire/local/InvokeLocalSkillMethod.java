package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;

/* JADX INFO: loaded from: classes4.dex */
public final class InvokeLocalSkillMethod extends Method {
    private Queue queue;
    private final LocalSkillContext skillContext;

    public InvokeLocalSkillMethod(final LocalSkillContext skillContext, final Queue queue) {
        this.skillContext = skillContext;
        this.queue = queue;
    }

    public LocalSkillContext getSkillContext() {
        return this.skillContext;
    }

    @Override // CoreInterface.v1_0.Method
    public Queue queue() {
        return this.queue;
    }
}
