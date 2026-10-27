package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.bugsnag.android.repackaged.dslplatform.json.JsonReader;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StringConverter {
    public static final JsonReader.ReadObject<String> READER = new JsonReader.ReadObject<String>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.StringConverter.1
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public String read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return jsonReader.readString();
        }
    };
    public static final JsonWriter.WriteObject<String> WRITER = new JsonWriter.WriteObject<String>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.StringConverter.2
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable String str) {
            StringConverter.serializeNullable(str, jsonWriter);
        }
    };
    public static final JsonWriter.WriteObject<CharSequence> WRITER_CHARS = new JsonWriter.WriteObject<CharSequence>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.StringConverter.3
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable CharSequence charSequence) {
            if (charSequence == null) {
                jsonWriter.writeNull();
            } else {
                jsonWriter.writeString(charSequence);
            }
        }
    };
    public static final JsonReader.ReadObject<StringBuilder> READER_BUILDER = new JsonReader.ReadObject<StringBuilder>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.StringConverter.4
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public StringBuilder read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return jsonReader.appendString(new StringBuilder());
        }
    };
    public static final JsonReader.ReadObject<StringBuffer> READER_BUFFER = new JsonReader.ReadObject<StringBuffer>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.StringConverter.5
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public StringBuffer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return jsonReader.appendString(new StringBuffer());
        }
    };

    public static void serializeShort(String str, JsonWriter jsonWriter) {
        jsonWriter.writeString(str);
    }

    public static void serializeNullable(@Nullable String str, JsonWriter jsonWriter) {
        if (str == null) {
            jsonWriter.writeNull();
        } else {
            jsonWriter.writeString(str);
        }
    }

    public static void serialize(String str, JsonWriter jsonWriter) {
        jsonWriter.writeString(str);
    }
}
