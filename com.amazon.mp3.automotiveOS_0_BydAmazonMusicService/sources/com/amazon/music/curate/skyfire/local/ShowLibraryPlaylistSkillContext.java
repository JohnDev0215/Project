package com.amazon.music.curate.skyfire.local;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowLibraryPlaylistSkillContext extends LocalSkillContext {
    public ShowLibraryPlaylistSkillContext(final Map<String, String> entity) {
        super(entity);
    }

    @Override // com.amazon.music.curate.skyfire.local.LocalSkillContext
    public String destination() {
        return ShowLibraryPlaylistSkill.class.getCanonicalName();
    }
}
