package android.mtp;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.mtp.MtpStorageManager;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
class MtpPropertyGroup {
    private static final String PATH_WHERE = "_data=?";
    private static final String TAG = "MtpPropertyGroup";
    private String[] mColumns;
    private final Property[] mProperties;

    private native String format_date_time(long j);

    private class Property {
        int code;
        int column;
        int type;

        Property(int i, int i2, int i3) {
            this.code = i;
            this.type = i2;
            this.column = i3;
        }
    }

    public MtpPropertyGroup(int[] iArr) {
        int length = iArr.length;
        ArrayList<String> arrayList = new ArrayList<>(length);
        arrayList.add("_id");
        this.mProperties = new Property[length];
        for (int i = 0; i < length; i++) {
            this.mProperties[i] = createProperty(iArr[i], arrayList);
        }
        int size = arrayList.size();
        this.mColumns = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            this.mColumns[i2] = arrayList.get(i2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Property createProperty(int i, ArrayList<String> arrayList) {
        int i2 = 4;
        String str = null;
        switch (i) {
            case MtpConstants.PROPERTY_STORAGE_ID /* 56321 */:
            case MtpConstants.PROPERTY_PARENT_OBJECT /* 56331 */:
            case MtpConstants.PROPERTY_SAMPLE_RATE /* 56979 */:
            case MtpConstants.PROPERTY_AUDIO_WAVE_CODEC /* 56985 */:
            case MtpConstants.PROPERTY_AUDIO_BITRATE /* 56986 */:
                i2 = 6;
                break;
            case MtpConstants.PROPERTY_OBJECT_FORMAT /* 56322 */:
            case MtpConstants.PROPERTY_PROTECTION_STATUS /* 56323 */:
            case MtpConstants.PROPERTY_BITRATE_TYPE /* 56978 */:
            case MtpConstants.PROPERTY_NUMBER_OF_CHANNELS /* 56980 */:
                break;
            case MtpConstants.PROPERTY_OBJECT_SIZE /* 56324 */:
                i2 = 8;
                break;
            case MtpConstants.PROPERTY_OBJECT_FILE_NAME /* 56327 */:
            case MtpConstants.PROPERTY_DATE_MODIFIED /* 56329 */:
            case MtpConstants.PROPERTY_NAME /* 56388 */:
            case MtpConstants.PROPERTY_DATE_ADDED /* 56398 */:
            case MtpConstants.PROPERTY_DISPLAY_NAME /* 56544 */:
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_PERSISTENT_UID /* 56385 */:
                i2 = 10;
                break;
            case MtpConstants.PROPERTY_ARTIST /* 56390 */:
                str = "artist";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_DESCRIPTION /* 56392 */:
                str = "description";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_DURATION /* 56457 */:
                str = "duration";
                i2 = 6;
                break;
            case MtpConstants.PROPERTY_TRACK /* 56459 */:
                str = "track";
                break;
            case MtpConstants.PROPERTY_GENRE /* 56460 */:
                str = "genre";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_COMPOSER /* 56470 */:
                str = "composer";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_ORIGINAL_RELEASE_DATE /* 56473 */:
                str = "year";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_ALBUM_NAME /* 56474 */:
                str = "album";
                i2 = 65535;
                break;
            case MtpConstants.PROPERTY_ALBUM_ARTIST /* 56475 */:
                str = "album_artist";
                i2 = 65535;
                break;
            default:
                i2 = 0;
                Log.e(TAG, "unsupported property " + i);
                break;
        }
        if (str != null) {
            arrayList.add(str);
            return new Property(i, i2, arrayList.size() - 1);
        }
        return new Property(i, i2, -1);
    }

    public int getPropertyList(ContentProviderClient contentProviderClient, String str, MtpStorageManager.MtpObject mtpObject, MtpPropertyList mtpPropertyList) {
        Cursor cursor;
        int id = mtpObject.getId();
        String string = mtpObject.getPath().toString();
        Property[] propertyArr = this.mProperties;
        int length = propertyArr.length;
        int i = 0;
        Cursor cursorQuery = null;
        while (i < length) {
            Property property = propertyArr[i];
            if (property.column != -1 && cursorQuery == null) {
                try {
                    try {
                    } catch (IllegalArgumentException unused) {
                        return MtpConstants.RESPONSE_INVALID_OBJECT_PROP_CODE;
                    }
                } catch (RemoteException unused2) {
                }
                try {
                    cursorQuery = contentProviderClient.query(MtpDatabase.getObjectPropertiesUri(mtpObject.getFormat(), str), this.mColumns, PATH_WHERE, new String[]{string}, null, null);
                    if (cursorQuery != null && !cursorQuery.moveToNext()) {
                        cursorQuery.close();
                        cursorQuery = null;
                    }
                } catch (RemoteException unused3) {
                    Log.e(TAG, "Mediaprovider lookup failed");
                }
            }
            Cursor cursor2 = cursorQuery;
            switch (property.code) {
                case MtpConstants.PROPERTY_STORAGE_ID /* 56321 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, mtpObject.getStorageId());
                    break;
                case MtpConstants.PROPERTY_OBJECT_FORMAT /* 56322 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, mtpObject.getFormat());
                    break;
                case MtpConstants.PROPERTY_PROTECTION_STATUS /* 56323 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, 0L);
                    break;
                case MtpConstants.PROPERTY_OBJECT_SIZE /* 56324 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, mtpObject.getSize());
                    break;
                case MtpConstants.PROPERTY_OBJECT_FILE_NAME /* 56327 */:
                case MtpConstants.PROPERTY_NAME /* 56388 */:
                case MtpConstants.PROPERTY_DISPLAY_NAME /* 56544 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, mtpObject.getName());
                    break;
                case MtpConstants.PROPERTY_DATE_MODIFIED /* 56329 */:
                case MtpConstants.PROPERTY_DATE_ADDED /* 56398 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, format_date_time(mtpObject.getModifiedTime()));
                    break;
                case MtpConstants.PROPERTY_PARENT_OBJECT /* 56331 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, mtpObject.getParent().isRoot() ? 0L : mtpObject.getParent().getId());
                    break;
                case MtpConstants.PROPERTY_PERSISTENT_UID /* 56385 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, property.type, mtpObject.getModifiedTime() + ((long) (mtpObject.getPath().toString().hashCode() << 32)));
                    break;
                case MtpConstants.PROPERTY_TRACK /* 56459 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, 4, (cursor != null ? cursor.getInt(property.column) : 0) % 1000);
                    break;
                case MtpConstants.PROPERTY_ORIGINAL_RELEASE_DATE /* 56473 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, Integer.toString(cursor != null ? cursor.getInt(property.column) : 0) + "0101T000000");
                    break;
                case MtpConstants.PROPERTY_BITRATE_TYPE /* 56978 */:
                case MtpConstants.PROPERTY_NUMBER_OF_CHANNELS /* 56980 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, 4, 0L);
                    break;
                case MtpConstants.PROPERTY_SAMPLE_RATE /* 56979 */:
                case MtpConstants.PROPERTY_AUDIO_WAVE_CODEC /* 56985 */:
                case MtpConstants.PROPERTY_AUDIO_BITRATE /* 56986 */:
                    cursor = cursor2;
                    mtpPropertyList.append(id, property.code, 6, 0L);
                    break;
                default:
                    cursor = cursor2;
                    int i2 = property.type;
                    if (i2 == 0) {
                        mtpPropertyList.append(id, property.code, property.type, 0L);
                    } else if (i2 == 65535) {
                        mtpPropertyList.append(id, property.code, cursor != null ? cursor.getString(property.column) : "");
                    } else {
                        mtpPropertyList.append(id, property.code, property.type, cursor != null ? cursor.getLong(property.column) : 0L);
                    }
                    break;
            }
            i++;
            cursorQuery = cursor;
        }
        if (cursorQuery == null) {
            return 8193;
        }
        cursorQuery.close();
        return 8193;
    }
}
