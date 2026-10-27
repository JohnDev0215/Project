package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SushiFormatter {
    public static final Charset CHARSET = Charset.forName(ParserUtil.UTF_8);

    public String getFileSuffix() {
        return "]}";
    }

    public long getMaxFileSizeBytes() {
        return 1048576L;
    }

    public int getMaxRecordCount() {
        return 500;
    }

    public String getFilePrefix() {
        return String.format("{\"%s\":[", "events");
    }

    public JSONArray parseSushiEntries(String str) throws JSONException {
        return new JSONObject(str).getJSONArray("events");
    }
}
