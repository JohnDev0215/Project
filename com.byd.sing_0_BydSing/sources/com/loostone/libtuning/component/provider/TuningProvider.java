package com.loostone.libtuning.component.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.loostone.libtuning.component.provider.util.AppInfoProvider;
import com.loostone.libtuning.component.provider.util.AudioEffectProvider;
import com.loostone.libtuning.component.provider.util.MicInfoProvider;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class TuningProvider extends ContentProvider {
    public static final int APP_INFO_ID = 3;
    public static final String AUTHORITY = "com.loostone.tuning";
    public static final Companion Companion = new Companion(null);
    public static final int EFFECT_ID = 1;
    public static final String KEY = "key";
    public static final int MIC_INFO_ID = 2;
    private static final String TAG = "TuningProvider";
    public static final String TYPE = "type";
    public static final String URI_APP_INFO = "content://com.loostone.tuning/appInfo";
    public static final String URI_AUDIO_EFFECT = "content://com.loostone.tuning/effect";
    public static final String URI_MIC_INFO = "content://com.loostone.tuning/micInfo";
    public static final String VALUE = "value";
    private final UriMatcher sUriMatcher;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public TuningProvider() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(AUTHORITY, "effect", 1);
        uriMatcher.addURI(AUTHORITY, "micInfo", 2);
        uriMatcher.addURI(AUTHORITY, "appInfo", 3);
        this.sUriMatcher = uriMatcher;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        h.d(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        h.d(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        h.d(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        h.d(uri, "uri");
        if (strArr != null) {
            if (!(strArr.length == 0)) {
                try {
                    int i = Integer.parseInt(strArr[0]);
                    int iMatch = this.sUriMatcher.match(uri);
                    if (iMatch == 1) {
                        return AudioEffectProvider.INSTANCE.getValue(i);
                    }
                    if (iMatch == 2) {
                        return MicInfoProvider.INSTANCE.getValue(i);
                    }
                    if (iMatch != 3) {
                        return null;
                    }
                    return AppInfoProvider.INSTANCE.getValue(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Integer asInteger;
        int iIntValue;
        h.d(uri, "uri");
        if (contentValues == null) {
            return -2;
        }
        try {
            asInteger = contentValues.getAsInteger(KEY);
            iIntValue = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (asInteger == null) {
            Integer asInteger2 = contentValues.getAsInteger(TYPE);
            if (asInteger2 == null) {
                return -1;
            }
            int iIntValue2 = asInteger2.intValue();
            Integer asInteger3 = contentValues.getAsInteger(VALUE);
            if (asInteger3 != null) {
                iIntValue = asInteger3.intValue();
            }
            if (this.sUriMatcher.match(uri) == 1) {
                return AudioEffectProvider.INSTANCE.oldSetGetValue(iIntValue2, iIntValue);
            }
            return -1;
        }
        int iIntValue3 = asInteger.intValue();
        Integer asInteger4 = contentValues.getAsInteger(VALUE);
        if (asInteger4 != null) {
            iIntValue = asInteger4.intValue();
        }
        int iMatch = this.sUriMatcher.match(uri);
        if (iMatch == 1) {
            return AudioEffectProvider.INSTANCE.setValue(iIntValue3, iIntValue);
        }
        if (iMatch != 2) {
            return -1;
        }
        return MicInfoProvider.INSTANCE.setValue(iIntValue3, iIntValue);
    }
}
