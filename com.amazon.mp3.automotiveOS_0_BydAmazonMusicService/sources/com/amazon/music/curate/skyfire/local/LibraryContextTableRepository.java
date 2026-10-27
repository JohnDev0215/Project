package com.amazon.music.curate.skyfire.local;

import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.data.database.LibraryContextMappingDao;
import com.amazon.music.curate.model.LibraryContextMapping;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryContextTableRepository {
    private static final String TAG = "LibraryContextTableRepository";
    private static final Logger logger = LoggerFactory.getLogger(LibraryContextTableRepository.class.getSimpleName());

    public static void saveMappingsToDB(final AppDatabase appDatabase, final String mappingsString) {
        final ArrayList arrayList = new ArrayList();
        final String string = null;
        if (StringUtils.isNotBlank(mappingsString)) {
            try {
                JSONObject jSONObject = new JSONObject(mappingsString);
                string = jSONObject.getString(ContextMappingConstants.PAGE_CONTEXT);
                String string2 = jSONObject.getString(ContextMappingConstants.INTERFACE);
                if (ContextMappingConstants.LIBRARY_PAGE_CONTEXT_VALUE.equalsIgnoreCase(string) && ContextMappingConstants.LIBRARY_SYNC_SET_WIDGET_SYNC_ELEMENTS_METHOD.equalsIgnoreCase(string2)) {
                    arrayList.addAll(convertMappingsFromJson(string, jSONObject));
                }
            } catch (Exception e) {
                logger.error("failed to update context mapping table", (Throwable) e);
            }
        }
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.local.LibraryContextTableRepository.1
            @Override // java.lang.Runnable
            public void run() {
                if (StringUtils.isNotBlank(string)) {
                    LibraryContextMappingDao libraryContextMappingDao = appDatabase.libraryContextMappingDao();
                    libraryContextMappingDao.deleteByPageContext(string);
                    libraryContextMappingDao.insertMappings(arrayList);
                    LibraryContextTableRepository.logger.debug("Saved context mapping to DB");
                }
            }
        });
    }

    private static List<LibraryContextMapping> convertMappingsFromJson(final String pageContext, final JSONObject methodJSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = methodJSONObject.getJSONArray(ContextMappingConstants.ELEMENTS);
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string = jSONObject.getString(ContextMappingConstants.WIDGET_CONTEXT);
                    JSONArray jSONArray2 = jSONObject.getJSONArray(ContextMappingConstants.ITEMS);
                    if (jSONArray2 != null) {
                        int i2 = 0;
                        while (i2 < jSONArray2.length()) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                            int i3 = i2;
                            LibraryContextMapping libraryContextMapping = new LibraryContextMapping(0, pageContext, string, jsonOptString(jSONObject2, ContextMappingConstants.WIDGET_ITEM_CONTEXT, ""), jsonOptString(jSONObject2, ContextMappingConstants.ENTITY_ID_TYPE, ""), jsonOptString(jSONObject2, ContextMappingConstants.ENTITY_ID, ""), jsonOptString(jSONObject2, ContextMappingConstants.LABEL, ""), jsonOptString(jSONObject2, ContextMappingConstants.PRIMARY_TEXT, ""), jsonOptString(jSONObject2, ContextMappingConstants.SECONDARY_TEXT, ""), jsonOptString(jSONObject2, ContextMappingConstants.TERTIARY_TEXT, ""), jsonOptString(jSONObject2, ContextMappingConstants.IMAGE, ""), convertBadgesToString(jSONObject2.optJSONArray(ContextMappingConstants.CONTENT_ENCODING)), jsonOptString(jSONObject2, ContextMappingConstants.CONTENT_SRC, ""), jsonOptString(jSONObject2, "contentInfoInstId", ""), jsonOptString(jSONObject2, ContextMappingConstants.PLAY_MODE, ""));
                            logger.debug("Converted to context map: {}", libraryContextMapping.toString());
                            arrayList.add(libraryContextMapping);
                            i2 = i3 + 1;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            logger.error("failed to get widgets", (Throwable) e);
        }
        return arrayList;
    }

    private static String jsonOptString(final JSONObject jsonObject, final String key, final String fallback) {
        return (jsonObject == null || !jsonObject.isNull(key)) ? jsonObject.optString(key, fallback) : fallback;
    }

    private static String convertBadgesToString(final JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        int length = jsonArray.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            try {
                sb.append(jsonArray.get(i).toString());
                if (i < length - 1) {
                    sb.append(",");
                }
            } catch (JSONException e) {
                logger.error("failed to parse array", (Throwable) e);
            }
        }
        return sb.toString();
    }
}
