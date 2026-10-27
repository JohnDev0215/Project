package com.amazon.music.find.data.database;

import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SearchFollowSource;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibrarySearchDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JC\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u0010JA\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u0017J?\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/data/database/LibrarySearchDao;", "", SearchIntents.EXTRA_QUERY, "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "searchTerm", "", "isOffline", "", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "pageSize", "", "searchFollowSource", "Lcom/amazon/music/find/model/search/SearchFollowSource;", "(Ljava/lang/String;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Lcom/amazon/music/search/SpellCorrections;Ljava/lang/Integer;Lcom/amazon/music/find/model/search/SearchFollowSource;)Lcom/amazon/music/find/model/search/GenericSearchResponse;", "requestState", "", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "trackIds", "", "trackAsins", "(Ljava/util/List;Ljava/util/List;ZLjava/lang/Integer;)Ljava/util/List;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "(Ljava/lang/String;ZLcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/Integer;Lcom/amazon/music/find/model/search/SearchFollowSource;)Lcom/amazon/music/find/model/search/SeeMoreResponse;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LibrarySearchDao {
    GenericSearchResponse query(String searchTerm, boolean isOffline, SearchFilterSelections searchFilterSelections, SpellCorrections spellCorrections, Integer pageSize, SearchFollowSource searchFollowSource);

    List<ContentOwnershipItem> requestState(List<Long> trackIds, List<String> trackAsins, boolean isOffline, Integer pageSize);

    SeeMoreResponse seeMore(String searchTerm, boolean isOffline, EntityType entityType, SearchFilterSelections searchFilterSelections, Integer pageSize, SearchFollowSource searchFollowSource);

    /* JADX INFO: compiled from: LibrarySearchDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ GenericSearchResponse query$default(LibrarySearchDao librarySearchDao, String str, boolean z, SearchFilterSelections searchFilterSelections, SpellCorrections spellCorrections, Integer num, SearchFollowSource searchFollowSource, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
            }
            if ((i & 8) != 0) {
                spellCorrections = null;
            }
            return librarySearchDao.query(str, z, searchFilterSelections, spellCorrections, num, searchFollowSource);
        }
    }
}
