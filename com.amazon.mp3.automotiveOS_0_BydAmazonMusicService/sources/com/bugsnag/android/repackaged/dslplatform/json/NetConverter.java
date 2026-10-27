package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.bugsnag.android.repackaged.dslplatform.json.JsonReader;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NetConverter {
    static final JsonReader.ReadObject<URI> UriReader = new JsonReader.ReadObject<URI>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NetConverter.1
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public URI read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return NetConverter.deserializeUri(jsonReader);
        }
    };
    static final JsonWriter.WriteObject<URI> UriWriter = new JsonWriter.WriteObject<URI>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NetConverter.2
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable URI uri) {
            NetConverter.serializeNullable(uri, jsonWriter);
        }
    };
    static final JsonReader.ReadObject<InetAddress> AddressReader = new JsonReader.ReadObject<InetAddress>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NetConverter.3
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public InetAddress read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return NetConverter.deserializeIp(jsonReader);
        }
    };
    static final JsonWriter.WriteObject<InetAddress> AddressWriter = new JsonWriter.WriteObject<InetAddress>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NetConverter.4
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable InetAddress inetAddress) {
            NetConverter.serializeNullable(inetAddress, jsonWriter);
        }
    };

    public static void serializeNullable(@Nullable URI uri, JsonWriter jsonWriter) {
        if (uri == null) {
            jsonWriter.writeNull();
        } else {
            serialize(uri, jsonWriter);
        }
    }

    public static void serialize(URI uri, JsonWriter jsonWriter) {
        StringConverter.serializeShort(uri.toString(), jsonWriter);
    }

    public static URI deserializeUri(JsonReader jsonReader) throws IOException {
        return URI.create(jsonReader.readString());
    }

    public static void serializeNullable(@Nullable InetAddress inetAddress, JsonWriter jsonWriter) {
        if (inetAddress == null) {
            jsonWriter.writeNull();
        } else {
            serialize(inetAddress, jsonWriter);
        }
    }

    public static void serialize(InetAddress inetAddress, JsonWriter jsonWriter) {
        jsonWriter.writeByte((byte) 34);
        jsonWriter.writeAscii(inetAddress.getHostAddress());
        jsonWriter.writeByte((byte) 34);
    }

    public static InetAddress deserializeIp(JsonReader jsonReader) throws IOException {
        return InetAddress.getByName(jsonReader.readSimpleString());
    }
}
