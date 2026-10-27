package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowArtistsSkillContext extends LocalSkillContext {
    public ShowArtistsSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowArtistsSkill.class.getCanonicalName();
    }
}
