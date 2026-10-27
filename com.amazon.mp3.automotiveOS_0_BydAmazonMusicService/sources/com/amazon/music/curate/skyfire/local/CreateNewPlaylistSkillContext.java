package com.amazon.music.curate.skyfire.local;

/* JADX INFO: loaded from: classes4.dex */
public final class CreateNewPlaylistSkillContext extends LocalSkillContext {
    public CreateNewPlaylistSkillContext() {
        super(null);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return CreateNewPlaylistSkill.class.getCanonicalName();
    }
}
