package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LocalSkill {
    private LocalSkillContext localSkillContext;

    abstract List<Method> invoke(final LocalSkillContext localSkillContext);

    public void setLocalContext(final LocalSkillContext localSkillContext) {
        this.localSkillContext = localSkillContext;
    }

    public LocalSkillContext getLocalContext() {
        return this.localSkillContext;
    }
}
