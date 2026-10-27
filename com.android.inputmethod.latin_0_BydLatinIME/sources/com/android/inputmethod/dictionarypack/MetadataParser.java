package com.android.inputmethod.dictionarypack;

import android.text.TextUtils;
import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataParser {
    private static final String CHECKSUM_FIELD_NAME = "checksum";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    private static final String FILESIZE_FIELD_NAME = "filesize";
    private static final String FORMATVERSION_FIELD_NAME = "formatversion";
    private static final String ID_FIELD_NAME = "id";
    private static final String LOCALE_FIELD_NAME = "locale";
    private static final String RAW_CHECKSUM_FIELD_NAME = "rawChecksum";
    private static final String REMOTE_FILENAME_FIELD_NAME = "url";
    private static final String UPDATE_FIELD_NAME = "update";
    private static final String VERSION_FIELD_NAME = "version";

    private static WordListMetadata parseOneWordList(final JsonReader reader) throws IOException, BadFormatException {
        TreeMap treeMap = new TreeMap();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (!TextUtils.isEmpty(strNextName)) {
                treeMap.put(strNextName, reader.nextString());
            }
        }
        reader.endObject();
        if (TextUtils.isEmpty((CharSequence) treeMap.get("id")) || TextUtils.isEmpty((CharSequence) treeMap.get("locale")) || TextUtils.isEmpty((CharSequence) treeMap.get("description")) || TextUtils.isEmpty((CharSequence) treeMap.get(UPDATE_FIELD_NAME)) || TextUtils.isEmpty((CharSequence) treeMap.get("filesize")) || TextUtils.isEmpty((CharSequence) treeMap.get("checksum")) || TextUtils.isEmpty((CharSequence) treeMap.get("url")) || TextUtils.isEmpty((CharSequence) treeMap.get("version")) || TextUtils.isEmpty((CharSequence) treeMap.get("formatversion"))) {
            throw new BadFormatException(treeMap.toString());
        }
        return new WordListMetadata((String) treeMap.get("id"), 2, (String) treeMap.get("description"), Long.parseLong((String) treeMap.get(UPDATE_FIELD_NAME)), Long.parseLong((String) treeMap.get("filesize")), (String) treeMap.get("rawChecksum"), (String) treeMap.get("checksum"), 2, null, (String) treeMap.get("url"), Integer.parseInt((String) treeMap.get("version")), Integer.parseInt((String) treeMap.get("formatversion")), 0, (String) treeMap.get("locale"));
    }

    public static List<WordListMetadata> parseMetadata(final InputStreamReader input) throws IOException, BadFormatException {
        JsonReader jsonReader = new JsonReader(input);
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            WordListMetadata oneWordList = parseOneWordList(jsonReader);
            if (!TextUtils.isEmpty(oneWordList.mLocale)) {
                arrayList.add(oneWordList);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
