package com.amazon.music.find.api.util;

import com.amazon.music.find.converter.model.MusicSearchTrack;
import com.amazon.music.search.EntityRelation;
import com.amazon.music.search.EntityRelationRole;
import com.amazon.music.search.PlaymodeEligibility;
import com.amazon.music.search.RelatedPlaylist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: RelatedEntityUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ \u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bJ0\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/api/util/RelatedEntityUtil;", "", "()V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "getRelatedPlaylist", "Lcom/amazon/music/search/RelatedPlaylist;", "track", "Lcom/amazon/music/find/converter/model/MusicSearchTrack;", "relatedPlaylistList", "", "entityRelationRole", "Lcom/amazon/music/search/EntityRelationRole;", "relatedPlaylists", "getTopRelatedPlaylists", "maxSize", "", "isRelatedPlaylistFeaturedAndOnDemand", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RelatedEntityUtil {
    public static final RelatedEntityUtil INSTANCE = new RelatedEntityUtil();
    private static final Logger logger = LoggerFactory.getLogger(RelatedEntityUtil.class.getName());

    private RelatedEntityUtil() {
    }

    public final RelatedPlaylist getRelatedPlaylist(MusicSearchTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        return getRelatedPlaylist(track.getRelatedPlaylists());
    }

    public final RelatedPlaylist getRelatedPlaylist(List<RelatedPlaylist> relatedPlaylists) {
        if (relatedPlaylists != null) {
            try {
                RelatedEntityUtil relatedEntityUtil = INSTANCE;
                RelatedPlaylist relatedPlaylist = relatedEntityUtil.getRelatedPlaylist(relatedPlaylists, EntityRelationRole.FEATURED_PLAYLIST);
                if (relatedPlaylist != null) {
                    return relatedPlaylist;
                }
                RelatedPlaylist relatedPlaylist2 = relatedEntityUtil.getRelatedPlaylist(relatedPlaylists, EntityRelationRole.RELATED_PLAYLIST);
                if (relatedPlaylist2 != null) {
                    return relatedPlaylist2;
                }
            } catch (Exception e) {
                logger.warn(Intrinsics.stringPlus("Something went wrong while parsing Tenzing RelatedPlaylist response: ", e.getMessage()));
                return null;
            }
        }
        return null;
    }

    static /* synthetic */ List getTopRelatedPlaylists$default(RelatedEntityUtil relatedEntityUtil, List list, EntityRelationRole entityRelationRole, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 1;
        }
        return relatedEntityUtil.getTopRelatedPlaylists(list, entityRelationRole, i);
    }

    public final boolean isRelatedPlaylistFeaturedAndOnDemand(List<RelatedPlaylist> relatedPlaylistList) {
        EntityRelation entityRelation;
        RelatedPlaylist relatedPlaylist = getRelatedPlaylist(relatedPlaylistList);
        EntityRelationRole role = null;
        if (relatedPlaylist != null && (entityRelation = relatedPlaylist.getEntityRelation()) != null) {
            role = entityRelation.getRole();
        }
        if (role != EntityRelationRole.FEATURED_PLAYLIST) {
            return false;
        }
        PlaymodeEligibility playmodeEligibility = relatedPlaylist.getPlaymodeEligibility();
        return playmodeEligibility == null ? false : Intrinsics.areEqual(playmodeEligibility.isOnDemandPlayable(), Boolean.TRUE);
    }

    private final RelatedPlaylist getRelatedPlaylist(List<RelatedPlaylist> relatedPlaylistList, EntityRelationRole entityRelationRole) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = relatedPlaylistList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            EntityRelation entityRelation = ((RelatedPlaylist) next).getEntityRelation();
            if ((entityRelation != null ? entityRelation.getRole() : null) == entityRelationRole) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            List topRelatedPlaylists$default = getTopRelatedPlaylists$default(this, arrayList, entityRelationRole, 0, 4, null);
            RelatedPlaylist relatedPlaylist = topRelatedPlaylists$default == null ? null : (RelatedPlaylist) CollectionsKt___CollectionsKt.firstOrNull(topRelatedPlaylists$default);
            if (relatedPlaylist != null) {
                PlaymodeEligibility playmodeEligibility = relatedPlaylist.getPlaymodeEligibility();
                if (playmodeEligibility != null ? Intrinsics.areEqual(playmodeEligibility.isOnDemandPlayable(), Boolean.TRUE) : false) {
                    return relatedPlaylist;
                }
                throw new Exception("Expected related playlist to be on demand playable - " + relatedPlaylist + '.');
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<com.amazon.music.search.RelatedPlaylist> getTopRelatedPlaylists(java.util.List<com.amazon.music.search.RelatedPlaylist> r6, com.amazon.music.search.EntityRelationRole r7, int r8) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r6 = r6.iterator()
        L9:
            boolean r1 = r6.hasNext()
            r2 = 0
            if (r1 == 0) goto L3b
            java.lang.Object r1 = r6.next()
            r3 = r1
            com.amazon.music.search.RelatedPlaylist r3 = (com.amazon.music.search.RelatedPlaylist) r3
            com.amazon.music.search.EntityRelation r4 = r3.getEntityRelation()
            if (r4 != 0) goto L1f
            r4 = r2
            goto L23
        L1f:
            com.amazon.music.search.EntityRelationRole r4 = r4.getRole()
        L23:
            if (r4 != r7) goto L34
            com.amazon.music.search.EntityRelation r3 = r3.getEntityRelation()
            if (r3 != 0) goto L2c
            goto L30
        L2c:
            java.lang.Long r2 = r3.getSequenceNumber()
        L30:
            if (r2 == 0) goto L34
            r2 = 1
            goto L35
        L34:
            r2 = 0
        L35:
            if (r2 == 0) goto L9
            r0.add(r1)
            goto L9
        L3b:
            com.amazon.music.find.api.util.RelatedEntityUtil$getTopRelatedPlaylists$$inlined$sortedBy$1 r6 = new com.amazon.music.find.api.util.RelatedEntityUtil$getTopRelatedPlaylists$$inlined$sortedBy$1
            r6.<init>()
            java.util.List r6 = kotlin.collections.CollectionsKt.sortedWith(r0, r6)
            if (r6 != 0) goto L47
            goto L4b
        L47:
            java.util.List r2 = kotlin.collections.CollectionsKt.take(r6, r8)
        L4b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.api.util.RelatedEntityUtil.getTopRelatedPlaylists(java.util.List, com.amazon.music.search.EntityRelationRole, int):java.util.List");
    }
}
