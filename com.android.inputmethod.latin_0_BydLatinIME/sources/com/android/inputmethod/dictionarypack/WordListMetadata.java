package com.android.inputmethod.dictionarypack;

import android.content.ContentValues;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WordListMetadata {
    public final String mChecksum;
    public final String mDescription;
    public final long mFileSize;
    public final int mFlags;
    public final int mFormatVersion;
    public final String mId;
    public final long mLastUpdate;
    public final String mLocalFilename;
    public final String mLocale;
    public final String mRawChecksum;
    public final String mRemoteFilename;
    public int mRetryCount;
    public final int mType;
    public final int mVersion;

    public WordListMetadata(final String id, final int type, final String description, final long lastUpdate, final long fileSize, final String rawChecksum, final String checksum, final int retryCount, final String localFilename, final String remoteFilename, final int version, final int formatVersion, final int flags, final String locale) {
        this.mId = id;
        this.mType = type;
        this.mDescription = description;
        this.mLastUpdate = lastUpdate;
        this.mFileSize = fileSize;
        this.mRawChecksum = rawChecksum;
        this.mChecksum = checksum;
        this.mRetryCount = retryCount;
        this.mLocalFilename = localFilename;
        this.mRemoteFilename = remoteFilename;
        this.mVersion = version;
        this.mFormatVersion = formatVersion;
        this.mFlags = flags;
        this.mLocale = locale;
    }

    public static WordListMetadata createFromContentValues(@Nonnull final ContentValues values) {
        String asString = values.getAsString(MetadataDbHelper.WORDLISTID_COLUMN);
        Integer asInteger = values.getAsInteger(MetadataDbHelper.TYPE_COLUMN);
        String asString2 = values.getAsString("description");
        Long asLong = values.getAsLong("date");
        Long asLong2 = values.getAsLong(MetadataDbHelper.FILESIZE_COLUMN);
        String asString3 = values.getAsString(MetadataDbHelper.RAW_CHECKSUM_COLUMN);
        String asString4 = values.getAsString(MetadataDbHelper.CHECKSUM_COLUMN);
        int iIntValue = values.getAsInteger(MetadataDbHelper.RETRY_COUNT_COLUMN).intValue();
        String asString5 = values.getAsString(MetadataDbHelper.LOCAL_FILENAME_COLUMN);
        String asString6 = values.getAsString(MetadataDbHelper.REMOTE_FILENAME_COLUMN);
        Integer asInteger2 = values.getAsInteger("version");
        Integer asInteger3 = values.getAsInteger(MetadataDbHelper.FORMATVERSION_COLUMN);
        Integer asInteger4 = values.getAsInteger(MetadataDbHelper.FLAGS_COLUMN);
        String asString7 = values.getAsString("locale");
        if (asString == null || asInteger == null || asString2 == null || asLong == null || asLong2 == null || asString4 == null || asString5 == null || asString6 == null || asInteger2 == null || asInteger3 == null || asInteger4 == null || asString7 == null) {
            throw new IllegalArgumentException();
        }
        return new WordListMetadata(asString, asInteger.intValue(), asString2, asLong.longValue(), asLong2.longValue(), asString3, asString4, iIntValue, asString5, asString6, asInteger2.intValue(), asInteger3.intValue(), asInteger4.intValue(), asString7);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(WordListMetadata.class.getSimpleName());
        sb.append(" : ").append(this.mId);
        sb.append("\nType : ").append(this.mType);
        sb.append("\nDescription : ").append(this.mDescription);
        sb.append("\nLastUpdate : ").append(this.mLastUpdate);
        sb.append("\nFileSize : ").append(this.mFileSize);
        sb.append("\nRawChecksum : ").append(this.mRawChecksum);
        sb.append("\nChecksum : ").append(this.mChecksum);
        sb.append("\nRetryCount: ").append(this.mRetryCount);
        sb.append("\nLocalFilename : ").append(this.mLocalFilename);
        sb.append("\nRemoteFilename : ").append(this.mRemoteFilename);
        sb.append("\nVersion : ").append(this.mVersion);
        sb.append("\nFormatVersion : ").append(this.mFormatVersion);
        sb.append("\nFlags : ").append(this.mFlags);
        sb.append("\nLocale : ").append(this.mLocale);
        return sb.toString();
    }
}
