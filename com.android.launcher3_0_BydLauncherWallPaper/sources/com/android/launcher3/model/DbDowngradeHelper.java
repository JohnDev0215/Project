package com.android.launcher3.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.launcher3.R;
import com.android.launcher3.provider.LauncherDbUtils;
import com.android.launcher3.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class DbDowngradeHelper {
    private static final String KEY_DOWNGRADE_TO = "downgrade_to_";
    private static final String KEY_VERSION = "version";
    private static final String TAG = "DbDowngradeHelper";
    private final SparseArray<String[]> mStatements = new SparseArray<>();
    public final int version;

    private DbDowngradeHelper(int version) {
        this.version = version;
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ArrayList arrayList = new ArrayList();
        for (int i = oldVersion - 1; i >= newVersion; i--) {
            String[] strArr = this.mStatements.get(i);
            if (strArr == null) {
                throw new SQLiteException("Downgrade path not supported to version " + i);
            }
            Collections.addAll(arrayList, strArr);
        }
        LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(db);
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                db.execSQL((String) it.next());
            }
            sQLiteTransaction.commit();
            sQLiteTransaction.close();
        } catch (Throwable th) {
            try {
                sQLiteTransaction.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static DbDowngradeHelper parse(File file) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(new String(IOUtils.toByteArray(file)));
        DbDowngradeHelper dbDowngradeHelper = new DbDowngradeHelper(jSONObject.getInt("version"));
        for (int i = dbDowngradeHelper.version - 1; i > 0; i--) {
            if (jSONObject.has(KEY_DOWNGRADE_TO + i)) {
                JSONArray jSONArray = jSONObject.getJSONArray(KEY_DOWNGRADE_TO + i);
                int length = jSONArray.length();
                String[] strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    strArr[i2] = jSONArray.getString(i2);
                }
                dbDowngradeHelper.mStatements.put(i, strArr);
            }
        }
        return dbDowngradeHelper;
    }

    public static void updateSchemaFile(File schemaFile, int expectedVersion, Context context) {
        try {
            if (parse(schemaFile).version >= expectedVersion) {
                return;
            }
        } catch (Exception unused) {
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(schemaFile);
            try {
                InputStream inputStreamOpenRawResource = context.getResources().openRawResource(R.raw.downgrade_schema);
                try {
                    IOUtils.copy(inputStreamOpenRawResource, fileOutputStream);
                    if (inputStreamOpenRawResource != null) {
                        inputStreamOpenRawResource.close();
                    }
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "Error writing schema file", e);
        }
    }
}
