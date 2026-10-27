package com.amazon.music.curate.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.music.curate.model.LibraryContextMapping;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryContextMappingDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H'J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u0006H'J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H'J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/curate/data/database/LibraryContextMappingDao;", "", "deleteAll", "", "deleteByEntityId", ContextMappingConstants.ENTITY_ID, "", "deleteByPageContext", ContextMappingConstants.PAGE_CONTEXT, "getMappingsByPageContext", "", "Lcom/amazon/music/curate/model/LibraryContextMapping;", "getMappingsByWidgetContext", ContextMappingConstants.WIDGET_CONTEXT, "insertMappings", "mappings", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LibraryContextMappingDao {
    @Query("DELETE FROM LibraryContextMapping")
    void deleteAll();

    @Query("DELETE FROM LibraryContextMapping WHERE entity_id = (:entityId)")
    void deleteByEntityId(String entityId);

    @Query("DELETE FROM LibraryContextMapping WHERE page_context = (:pageContext)")
    void deleteByPageContext(String pageContext);

    @Query("SELECT * FROM LibraryContextMapping WHERE page_context = (:pageContext) ORDER BY id ASC")
    List<LibraryContextMapping> getMappingsByPageContext(String pageContext);

    @Query("SELECT * FROM LibraryContextMapping WHERE page_context = (:pageContext) AND widget_context = (:widgetContext) ORDER BY id ASC")
    List<LibraryContextMapping> getMappingsByWidgetContext(String pageContext, String widgetContext);

    @Insert(onConflict = 1)
    void insertMappings(List<? extends LibraryContextMapping> mappings);
}
