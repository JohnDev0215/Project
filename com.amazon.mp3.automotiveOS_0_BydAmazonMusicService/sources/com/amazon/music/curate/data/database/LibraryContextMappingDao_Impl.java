package com.amazon.music.curate.data.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.model.LibraryContextMapping;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class LibraryContextMappingDao_Impl implements LibraryContextMappingDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<LibraryContextMapping> __insertionAdapterOfLibraryContextMapping;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByEntityId;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByPageContext;

    public LibraryContextMappingDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfLibraryContextMapping = new EntityInsertionAdapter<LibraryContextMapping>(__db) { // from class: com.amazon.music.curate.data.database.LibraryContextMappingDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `LibraryContextMapping` (`id`,`page_context`,`widget_context`,`widget_item_context`,`entity_id_type`,`entity_id`,`label`,`primary_text`,`secondary_text`,`tertiary_text`,`image`,`content_encoding`,`content_src`,`content_info_inst_id`,`play_mode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, LibraryContextMapping value) {
                stmt.bindLong(1, value.getId());
                if (value.getPageContext() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPageContext());
                }
                if (value.getWidgetContext() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getWidgetContext());
                }
                if (value.getWidgetItemContext() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getWidgetItemContext());
                }
                if (value.getEntityIdType() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getEntityIdType());
                }
                if (value.getEntityId() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getEntityId());
                }
                if (value.getLabel() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getLabel());
                }
                if (value.getPrimaryText() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getPrimaryText());
                }
                if (value.getSecondaryText() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getSecondaryText());
                }
                if (value.getTertiaryText() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getTertiaryText());
                }
                if (value.getImage() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getImage());
                }
                if (value.getContentEncoding() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getContentEncoding());
                }
                if (value.getContentSrc() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getContentSrc());
                }
                if (value.getContentInfoInstId() == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.getContentInfoInstId());
                }
                if (value.getPlayMode() == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindString(15, value.getPlayMode());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.LibraryContextMappingDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LibraryContextMapping";
            }
        };
        this.__preparedStmtOfDeleteByPageContext = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.LibraryContextMappingDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LibraryContextMapping WHERE page_context = (?)";
            }
        };
        this.__preparedStmtOfDeleteByEntityId = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.LibraryContextMappingDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LibraryContextMapping WHERE entity_id = (?)";
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public void insertMappings(final List<? extends LibraryContextMapping> mappings) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLibraryContextMapping.insert(mappings);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public void deleteByPageContext(final String pageContext) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByPageContext.acquire();
        if (pageContext == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, pageContext);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByPageContext.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public void deleteByEntityId(final String entityId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByEntityId.acquire();
        if (entityId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, entityId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByEntityId.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public List<LibraryContextMapping> getMappingsByPageContext(final String pageContext) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        String string;
        int i;
        String string2;
        int i2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM LibraryContextMapping WHERE page_context = (?) ORDER BY id ASC", 1);
        if (pageContext == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, pageContext);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "page_context");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "widget_context");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "widget_item_context");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.LABEL);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "primary_text");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "secondary_text");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tertiary_text");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_encoding");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_src");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_info_inst_id");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "play_mode");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i4 = cursorQuery.getInt(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string4 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string5 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string6 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string7 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                String string8 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                String string9 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                String string10 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                String string11 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                String string12 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                String string13 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                if (cursorQuery.isNull(columnIndexOrThrow13)) {
                    i = i3;
                    string = null;
                } else {
                    string = cursorQuery.getString(columnIndexOrThrow13);
                    i = i3;
                }
                String string14 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                int i5 = columnIndexOrThrow15;
                int i6 = columnIndexOrThrow;
                if (cursorQuery.isNull(i5)) {
                    i2 = i5;
                    string2 = null;
                } else {
                    string2 = cursorQuery.getString(i5);
                    i2 = i5;
                }
                arrayList.add(new LibraryContextMapping(i4, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string, string14, string2));
                columnIndexOrThrow = i6;
                columnIndexOrThrow15 = i2;
                i3 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.music.curate.data.database.LibraryContextMappingDao
    public List<LibraryContextMapping> getMappingsByWidgetContext(final String pageContext, final String widgetContext) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        String string;
        int i;
        String string2;
        int i2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM LibraryContextMapping WHERE page_context = (?) AND widget_context = (?) ORDER BY id ASC", 2);
        if (pageContext == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, pageContext);
        }
        if (widgetContext == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, widgetContext);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "page_context");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "widget_context");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "widget_item_context");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.LABEL);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "primary_text");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "secondary_text");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tertiary_text");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_encoding");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_src");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_info_inst_id");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "play_mode");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i4 = cursorQuery.getInt(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string4 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string5 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string6 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string7 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                String string8 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                String string9 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                String string10 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                String string11 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                String string12 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                String string13 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                if (cursorQuery.isNull(columnIndexOrThrow13)) {
                    i = i3;
                    string = null;
                } else {
                    string = cursorQuery.getString(columnIndexOrThrow13);
                    i = i3;
                }
                String string14 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                int i5 = columnIndexOrThrow;
                int i6 = columnIndexOrThrow15;
                if (cursorQuery.isNull(i6)) {
                    i2 = i6;
                    string2 = null;
                } else {
                    string2 = cursorQuery.getString(i6);
                    i2 = i6;
                }
                arrayList.add(new LibraryContextMapping(i4, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string, string14, string2));
                columnIndexOrThrow = i5;
                columnIndexOrThrow15 = i2;
                i3 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
