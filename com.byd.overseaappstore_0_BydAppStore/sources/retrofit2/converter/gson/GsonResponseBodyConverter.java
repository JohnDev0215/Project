package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import retrofit2.Converter;
import spi.mn;

/* JADX INFO: loaded from: classes2.dex */
final class GsonResponseBodyConverter<T> implements Converter<mn, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    public T convert(mn mnVar) throws IOException {
        JsonReader jsonReaderNewJsonReader = this.gson.newJsonReader(mnVar.charStream());
        try {
            T t = this.adapter.read(jsonReaderNewJsonReader);
            if (jsonReaderNewJsonReader.peek() == JsonToken.END_DOCUMENT) {
                return t;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            mnVar.close();
        }
    }
}
