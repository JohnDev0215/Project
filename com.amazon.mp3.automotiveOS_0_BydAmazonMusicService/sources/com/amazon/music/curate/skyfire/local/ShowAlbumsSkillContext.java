package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowAlbumsSkillContext extends LocalSkillContext {
    public ShowAlbumsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowAlbumsSkill.class.getCanonicalName();
    }
}
