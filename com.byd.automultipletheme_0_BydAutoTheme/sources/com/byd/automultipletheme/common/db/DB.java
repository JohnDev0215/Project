package com.byd.automultipletheme.common.db;

import kotlin.Metadata;

/* JADX INFO: compiled from: DB.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b#\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/byd/automultipletheme/common/db/DB;", "", "()V", "AUTHORITY", "", "CAROUSEL_RES_KEY_ID", "CAROUSEL_RES_PATH0", "CAROUSEL_RES_PATH1", "CAROUSEL_RES_PATH2", "CAROUSEL_RES_PATH3", "CAROUSEL_RES_TABLE_NAME", "CAROUSEL_RES_URI", "CAROUSEL_RES_URI_CODE", "", "CAROUSEL_RES_URI_PATH", "CAROUSEL_SAVE_ID", "CAROUSEL_SAVE_METHOD", "CAROUSEL_SAVE_RES_ID", "CAROUSEL_SAVE_RES_PATH", "CAROUSEL_SAVE_TABLE_NAME", "CAROUSEL_SAVE_URI", "CAROUSEL_SAVE_URI_CODE", "CAROUSEL_SAVE_URI_PATH", "CAROUSEL_TIME_KEY_ID", "CAROUSEL_TIME_TABLE_NAME", "CAROUSEL_TIME_TIME", "CAROUSEL_TIME_URI", "CAROUSEL_TIME_URI_CODE", "CAROUSEL_TIME_URI_PATH", "DB_NAME", "DB_VERSION", "WALLPAPER_EXT_APP_CODE", "WALLPAPER_EXT_APP_VERSION", "WALLPAPER_EXT_KEY_ID", "WALLPAPER_EXT_PATH", "WALLPAPER_EXT_RES_ID", "WALLPAPER_EXT_TABLE_NAME", "WALLPAPER_EXT_TYPE", "WALLPAPER_EXT_URI", "WALLPAPER_EXT_URI_CODE", "WALLPAPER_EXT_URI_PATH", "WALLPAPER_RESULT", "WALLPAPER_RESULT_KEY_ID", "WALLPAPER_RESULT_RES_ID", "WALLPAPER_RESULT_TABLE_NAME", "WALLPAPER_RESULT_URI", "WALLPAPER_RESULT_URI_CODE", "WALLPAPER_RESULT_URI_PATH", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DB {
    public static final String AUTHORITY = "com.byd.automultipletheme.ContentProvider";
    public static final String CAROUSEL_RES_KEY_ID = "_id";
    public static final String CAROUSEL_RES_PATH0 = "path0";
    public static final String CAROUSEL_RES_PATH1 = "path1";
    public static final String CAROUSEL_RES_PATH2 = "path2";
    public static final String CAROUSEL_RES_PATH3 = "path3";
    public static final String CAROUSEL_RES_TABLE_NAME = "carousel_res";
    public static final String CAROUSEL_RES_URI = "content://com.byd.automultipletheme.ContentProvider/carousel_res";
    public static final int CAROUSEL_RES_URI_CODE = 2;
    public static final String CAROUSEL_RES_URI_PATH = "carousel_res";
    public static final String CAROUSEL_SAVE_ID = "_id";
    public static final String CAROUSEL_SAVE_METHOD = "carousel_save_path_method";
    public static final String CAROUSEL_SAVE_RES_ID = "res_id";
    public static final String CAROUSEL_SAVE_RES_PATH = "res_path";
    public static final String CAROUSEL_SAVE_TABLE_NAME = "carousel_save_path";
    public static final String CAROUSEL_SAVE_URI = "content://com.byd.automultipletheme.ContentProvider/carousel_save_path";
    public static final int CAROUSEL_SAVE_URI_CODE = 3;
    public static final String CAROUSEL_SAVE_URI_PATH = "carousel_save_path";
    public static final String CAROUSEL_TIME_KEY_ID = "_id";
    public static final String CAROUSEL_TIME_TABLE_NAME = "carousel_time";
    public static final String CAROUSEL_TIME_TIME = "time";
    public static final String CAROUSEL_TIME_URI = "content://com.byd.automultipletheme.ContentProvider/carousel_time";
    public static final int CAROUSEL_TIME_URI_CODE = 1;
    public static final String CAROUSEL_TIME_URI_PATH = "carousel_time";
    public static final String DB_NAME = "theme.db";
    public static final int DB_VERSION = 1;
    public static final DB INSTANCE = new DB();
    public static final String WALLPAPER_EXT_APP_CODE = "app_version_code";
    public static final String WALLPAPER_EXT_APP_VERSION = "app_version_name";
    public static final String WALLPAPER_EXT_KEY_ID = "_id";
    public static final String WALLPAPER_EXT_PATH = "wallpaper_path";
    public static final String WALLPAPER_EXT_RES_ID = "res_id";
    public static final String WALLPAPER_EXT_TABLE_NAME = "external";
    public static final String WALLPAPER_EXT_TYPE = "wallpaper_type";
    public static final String WALLPAPER_EXT_URI = "content://com.byd.automultipletheme.ContentProvider/external";
    public static final int WALLPAPER_EXT_URI_CODE = 0;
    public static final String WALLPAPER_EXT_URI_PATH = "external";
    public static final String WALLPAPER_RESULT = "result";
    public static final String WALLPAPER_RESULT_KEY_ID = "_id";
    public static final String WALLPAPER_RESULT_RES_ID = "res_id";
    public static final String WALLPAPER_RESULT_TABLE_NAME = "wallpaper_result";
    public static final String WALLPAPER_RESULT_URI = "content://com.byd.automultipletheme.ContentProvider/wallpaper_result";
    public static final int WALLPAPER_RESULT_URI_CODE = 4;
    public static final String WALLPAPER_RESULT_URI_PATH = "wallpaper_result";

    private DB() {
    }
}
