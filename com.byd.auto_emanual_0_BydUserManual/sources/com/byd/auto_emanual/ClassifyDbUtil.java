package com.byd.auto_emanual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.fragment.PageData;
import com.byd.auto_emanual.fragment.ZipInfo;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.CloseUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.Utils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ClassifyDbUtil {
    private static String CLASSIFY_TABLE_NAME = "classify";
    private static final String DB_NAME = "classify_cache.db";
    private static final String TAG = "ClassifyDbUtil";
    private static volatile ClassifyDbUtil instance;
    private Context context;
    private SQLiteDatabase db;
    private int dbVersion = 2;
    private MHelper mHelper;

    private ClassifyDbUtil(Context c) {
        this.context = c.getApplicationContext();
        MHelper mHelper = new MHelper(this.context);
        this.mHelper = mHelper;
        this.db = mHelper.getWritableDatabase();
    }

    public static ClassifyDbUtil getInstance(Context context) {
        synchronized (ClassifyDbUtil.class) {
            if (instance == null) {
                instance = new ClassifyDbUtil(context);
            }
        }
        return instance;
    }

    private class MHelper extends SQLiteOpenHelper {
        public MHelper(Context context) {
            super(context, ClassifyDbUtil.DB_NAME, (SQLiteDatabase.CursorFactory) null, ClassifyDbUtil.this.dbVersion);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TableClassify.TABLE_CREATOR);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 2) {
                db.execSQL("ALTER TABLE " + ClassifyDbUtil.CLASSIFY_TABLE_NAME + " ADD COLUMN " + TableClassify.COLUMN_MULTI_RESOURCE_LANGUAGE + " TEXT");
                db.execSQL("UPDATE " + ClassifyDbUtil.CLASSIFY_TABLE_NAME + " SET " + TableClassify.COLUMN_MULTI_RESOURCE_LANGUAGE + " = ''");
                MLog.d(ClassifyDbUtil.TAG, "onUpgrade " + oldVersion + " " + newVersion);
            }
        }
    }

    public PageData queryByResourceLanguage(String page, String resourceLanguage, String multiResourceLanguage) {
        String[] strArr;
        boolean z;
        String str = null;
        boolean z2 = true;
        if (TextUtils.isEmpty(resourceLanguage)) {
            strArr = null;
            z = false;
        } else {
            z = true;
            str = "vin = ? and page = ? and resource_language = ? ";
            strArr = new String[]{AutoInfoUtil.getAutoVIN(this.context), page, resourceLanguage};
        }
        if (TextUtils.isEmpty(multiResourceLanguage)) {
            z2 = false;
        } else {
            strArr = new String[]{AutoInfoUtil.getAutoVIN(this.context), page, multiResourceLanguage};
            str = "vin = ? and page = ? and multi_resource_language = ? ";
        }
        if (z && z2) {
            MLog.e(TAG, "queryByResourceLanguage error. both single and multi.");
        }
        if (!z && !z2) {
            MLog.e(TAG, "queryByResourceLanguage error. not single or multi.");
        }
        return queryPageData(page, str, strArr);
    }

    public PageData queryByPage(String page) {
        saveOldVin(page);
        return queryPageData(page, "vin = ? and page = ? and language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, Utils.getCurrentLanguage()});
    }

    private PageData queryPageData(String page, String selection, String[] selectionArgs) {
        ArrayList arrayList;
        int i;
        List list;
        String str = page;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList2 = new ArrayList();
        PageData pageData = new PageData(str, arrayList2);
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, null, selection, selectionArgs, TableClassify.COLUMN_HEADER, null, "_id_id", null);
            if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                arrayList = arrayList2;
            } else {
                int columnIndex = cursorQuery.getColumnIndex(TableClassify.COLUMN_CLASSIFY);
                int columnIndex2 = cursorQuery.getColumnIndex(TableClassify.COLUMN_HEADER);
                int columnIndex3 = cursorQuery.getColumnIndex(TableClassify.COLUMN_COVER_PATH);
                int columnIndex4 = cursorQuery.getColumnIndex(TableClassify.COLUMN_FOOTER);
                int columnIndex5 = cursorQuery.getColumnIndex(TableClassify.COLUMN_PATH);
                int columnIndex6 = cursorQuery.getColumnIndex("type");
                int columnIndex7 = cursorQuery.getColumnIndex("managerId");
                int columnIndex8 = cursorQuery.getColumnIndex(TableClassify.COLUMN_NAME_INDEX);
                int columnIndex9 = cursorQuery.getColumnIndex(TableClassify.COLUMN_COUNTRY);
                int columnIndex10 = cursorQuery.getColumnIndex("language");
                int columnIndex11 = cursorQuery.getColumnIndex(TableClassify.COLUMN_RESOURCE_LANGUAGE);
                arrayList = arrayList2;
                int columnIndex12 = cursorQuery.getColumnIndex(TableClassify.COLUMN_MULTI_RESOURCE_LANGUAGE);
                LinkedHashMap linkedHashMap2 = linkedHashMap;
                int columnIndex13 = cursorQuery.getColumnIndex(TableClassify.COLUMN_SERVER_FILE_PATH);
                while (cursorQuery.moveToNext()) {
                    int i2 = columnIndex;
                    if (Page.MANUAL.equals(str)) {
                        ZipInfo zipInfo = new ZipInfo();
                        zipInfo.setZipUploadTime(cursorQuery.getString(columnIndex2));
                        zipInfo.setZipUrl(cursorQuery.getString(columnIndex3));
                        zipInfo.setZipSize(cursorQuery.getString(columnIndex4));
                        zipInfo.setTargetFile(cursorQuery.getString(columnIndex5));
                        zipInfo.setResourceLanguage(cursorQuery.getString(columnIndex11));
                        zipInfo.setMultiResourceLanguage(cursorQuery.getString(columnIndex12));
                        zipInfo.setServerFilepath(cursorQuery.getString(columnIndex13));
                        pageData.setZipInfo(zipInfo);
                        str = page;
                        columnIndex = i2;
                    } else {
                        ContentItem contentItem = new ContentItem();
                        contentItem.setHeader(cursorQuery.getString(columnIndex2));
                        contentItem.setCoverPath(cursorQuery.getString(columnIndex3));
                        contentItem.setFooter(cursorQuery.getString(columnIndex4));
                        contentItem.setPath(cursorQuery.getString(columnIndex5));
                        int i3 = columnIndex12;
                        contentItem.setContentItemType((ContentItem.ContentItemType) Enum.valueOf(ContentItem.ContentItemType.class, cursorQuery.getString(columnIndex6)));
                        contentItem.setManagerId(cursorQuery.getString(columnIndex7));
                        contentItem.setNameIndex(cursorQuery.getString(columnIndex8));
                        contentItem.setCountry(cursorQuery.getString(columnIndex9));
                        contentItem.setLanguage(cursorQuery.getString(columnIndex10));
                        contentItem.setResourceLanguage(cursorQuery.getString(columnIndex11));
                        contentItem.setServerFilepath(cursorQuery.getString(columnIndex13));
                        String string = cursorQuery.getString(i2);
                        int i4 = columnIndex13;
                        LinkedHashMap linkedHashMap3 = linkedHashMap2;
                        if (linkedHashMap3.containsKey(string)) {
                            list = (List) linkedHashMap3.get(string);
                            i = i2;
                        } else {
                            i = i2;
                            ArrayList arrayList3 = new ArrayList();
                            linkedHashMap3.put(string, arrayList3);
                            list = arrayList3;
                        }
                        list.add(contentItem);
                        str = page;
                        columnIndex12 = i3;
                        columnIndex = i;
                        linkedHashMap2 = linkedHashMap3;
                        columnIndex13 = i4;
                    }
                }
                linkedHashMap = linkedHashMap2;
            }
            CloseUtil.close(cursorQuery, TAG);
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ClassifyData classifyData = new ClassifyData();
                classifyData.setClassify((String) entry.getKey());
                classifyData.setContentItems((List) entry.getValue());
                classifyData.setModelType((entry.getValue() == null || ((List) entry.getValue()).get(0) == null) ? "" : ((ContentItem) ((List) entry.getValue()).get(0)).getReserve2());
                arrayList.add(classifyData);
            }
            pageData.setPage(page);
            return pageData;
        } catch (Throwable th) {
            CloseUtil.close(cursorQuery, TAG);
            throw th;
        }
    }

    public int querySizeByClassify(String classify) {
        Cursor cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, null, "vin = ? and classify = ? and language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), classify, Utils.getCurrentLanguage()}, null, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        int count = cursorQuery.getCount();
        cursorQuery.close();
        return count;
    }

    private void saveOldVin(String page) {
        String string;
        String autoVIN = AutoInfoUtil.getAutoVIN(this.context);
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, null, "page = ?", new String[]{page}, null, null, null, null);
            if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                string = "";
            } else {
                cursorQuery.moveToNext();
                string = cursorQuery.getString(cursorQuery.getColumnIndex(TableClassify.COLUMN_VIN));
            }
            CloseUtil.close(cursorQuery, TAG);
            if (string.isEmpty() || autoVIN.equals(string)) {
                return;
            }
            AutoInfoUtil.setOldVIN(string);
        } catch (Throwable th) {
            CloseUtil.close(cursorQuery, TAG);
            throw th;
        }
    }

    public int querySizeByResourceLanguage(String page, String resourceLanguage, String multiResourceLanguage) {
        int iQuerySize = !TextUtils.isEmpty(resourceLanguage) ? querySize("vin = ? and page = ? and resource_language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, resourceLanguage}) + 0 : 0;
        return !TextUtils.isEmpty(multiResourceLanguage) ? iQuerySize + querySize("vin = ? and page = ? and multi_resource_language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, multiResourceLanguage}) : iQuerySize;
    }

    public int querySizeByPage(String page) {
        return querySize("vin = ? and page = ? and language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, Utils.getCurrentLanguage()});
    }

    private int querySize(String selection, String[] selectionArgs) {
        Cursor cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        int count = cursorQuery.getCount();
        cursorQuery.close();
        return count;
    }

    public void deleteByResourceLanguage(String page, String resourceLanguage, String multiResourceLanguage) {
        if (!TextUtils.isEmpty(resourceLanguage)) {
            deleteByPage(page, "vin = ? and page = ? and resource_language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, resourceLanguage});
        }
        if (TextUtils.isEmpty(multiResourceLanguage)) {
            deleteByPage(page, "vin = ? and page = ? and multi_resource_language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, multiResourceLanguage});
        }
    }

    public void deleteByPage(String page) {
        deleteByPage(page, "vin = ? and page = ? and language = ? ", new String[]{AutoInfoUtil.getAutoVIN(this.context), page, Utils.getCurrentLanguage()});
    }

    private void deleteByPage(String page, String whereClause, String[] whereArgs) {
        this.db.delete(TableClassify.TABLE_NAME, whereClause, whereArgs);
        if (querySizeByClassify(page) == 0) {
            updateDbSequence();
        }
    }

    private void updateDbSequence() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("seq", (Integer) 0);
        this.db.update("sqlite_sequence", contentValues, "name=?", new String[]{CLASSIFY_TABLE_NAME});
    }

    public String queryHeaderByVoiceId(String id) {
        try {
            Cursor cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, new String[]{TableClassify.COLUMN_HEADER}, "vin = ? and language like ? escape '/' and type != 'ZIP' ", new String[]{AutoInfoUtil.getAutoVIN(this.context), id}, TableClassify.COLUMN_HEADER, null, null, null);
            String string = "";
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex(TableClassify.COLUMN_HEADER);
                while (cursorQuery.moveToNext()) {
                    string = cursorQuery.getString(columnIndex);
                    if (!TextUtils.isEmpty(string)) {
                        break;
                    }
                }
            }
            CloseUtil.close(cursorQuery, TAG);
            return string;
        } catch (Throwable th) {
            CloseUtil.close(null, TAG);
            throw th;
        }
    }

    public List<ClassifyData> queryByKey(String key) {
        ArrayList arrayList = new ArrayList();
        ClassifyData classifyData = new ClassifyData();
        classifyData.setClassify(key);
        ArrayList arrayList2 = new ArrayList();
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.db.query(true, TableClassify.TABLE_NAME, null, "vin = ? and header like ? escape '/' and type != 'ZIP' ", new String[]{AutoInfoUtil.getAutoVIN(this.context), "%" + key + "%"}, TableClassify.COLUMN_HEADER, null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex(TableClassify.COLUMN_HEADER);
                int columnIndex2 = cursorQuery.getColumnIndex(TableClassify.COLUMN_COVER_PATH);
                int columnIndex3 = cursorQuery.getColumnIndex(TableClassify.COLUMN_FOOTER);
                int columnIndex4 = cursorQuery.getColumnIndex(TableClassify.COLUMN_PATH);
                int columnIndex5 = cursorQuery.getColumnIndex("type");
                int columnIndex6 = cursorQuery.getColumnIndex("managerId");
                int columnIndex7 = cursorQuery.getColumnIndex(TableClassify.COLUMN_NAME_INDEX);
                int columnIndex8 = cursorQuery.getColumnIndex(TableClassify.COLUMN_COUNTRY);
                int columnIndex9 = cursorQuery.getColumnIndex("language");
                int columnIndex10 = cursorQuery.getColumnIndex(TableClassify.COLUMN_SERVER_FILE_PATH);
                while (cursorQuery.moveToNext()) {
                    ContentItem contentItem = new ContentItem();
                    contentItem.setHeader(cursorQuery.getString(columnIndex));
                    contentItem.setCoverPath(cursorQuery.getString(columnIndex2));
                    contentItem.setFooter(cursorQuery.getString(columnIndex3));
                    contentItem.setPath(cursorQuery.getString(columnIndex4));
                    contentItem.setContentItemType((ContentItem.ContentItemType) Enum.valueOf(ContentItem.ContentItemType.class, cursorQuery.getString(columnIndex5)));
                    contentItem.setManagerId(cursorQuery.getString(columnIndex6));
                    contentItem.setNameIndex(cursorQuery.getString(columnIndex7));
                    contentItem.setCountry(cursorQuery.getString(columnIndex8));
                    contentItem.setLanguage(cursorQuery.getString(columnIndex9));
                    contentItem.setServerFilepath(cursorQuery.getString(columnIndex10));
                    arrayList2.add(contentItem);
                    columnIndex = columnIndex;
                }
                classifyData.setContentItems(arrayList2);
                arrayList.add(classifyData);
            }
            return arrayList;
        } finally {
            CloseUtil.close(cursorQuery, TAG);
        }
    }

    public void insert(ContentValues values) {
        this.db.insert(TableClassify.TABLE_NAME, null, values);
    }

    public int clear() {
        return this.db.delete(TableClassify.TABLE_NAME, null, null);
    }

    public static class TableClassify {
        public static final String COLUMN_CLASSIFY = "classify";
        public static final String COLUMN_COUNTRY = "country_code";
        public static final String COLUMN_COVER_PATH = "coverPath";
        public static final String COLUMN_FOOTER = "footer";
        public static final String COLUMN_HEADER = "header";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_INDEX = "_id";
        public static final String COLUMN_LANGUAGE = "language";
        public static final String COLUMN_MANAGER_ID = "managerId";
        public static final String COLUMN_MULTI_RESOURCE_LANGUAGE = "multi_resource_language";
        public static final String COLUMN_NAME_INDEX = "name_index";
        public static final String COLUMN_PAGE = "page";
        public static final String COLUMN_PATH = "path";
        public static final String COLUMN_RESERVE_1 = "reserve_one";
        public static final String COLUMN_RESERVE_2 = "reserve_two";
        public static final String COLUMN_RESERVE_3 = "reserve_three";
        public static final String COLUMN_RESOURCE_LANGUAGE = "resource_language";
        public static final String COLUMN_SERVER_FILE_PATH = "server_file_path";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_VIN = "vin";
        public static final String TABLE_CREATOR;
        public static final String TABLE_NAME;

        static {
            String str = ClassifyDbUtil.CLASSIFY_TABLE_NAME;
            TABLE_NAME = str;
            TABLE_CREATOR = " create table " + str + "(  " + COLUMN_INDEX + "_id integer primary key autoincrement, " + COLUMN_VIN + " text not null, id text not null, " + COLUMN_PAGE + " text not null, " + COLUMN_CLASSIFY + " text not null, " + COLUMN_HEADER + " text, " + COLUMN_COVER_PATH + " text, " + COLUMN_FOOTER + " text, " + COLUMN_PATH + " text, type text, managerId text, " + COLUMN_NAME_INDEX + " text, " + COLUMN_COUNTRY + " text, language text, " + COLUMN_RESOURCE_LANGUAGE + " text, " + COLUMN_MULTI_RESOURCE_LANGUAGE + " text, " + COLUMN_SERVER_FILE_PATH + " text, " + COLUMN_RESERVE_1 + " text, " + COLUMN_RESERVE_2 + " text, " + COLUMN_RESERVE_3 + " text)";
        }
    }
}
