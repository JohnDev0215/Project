package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import retrofit2.Converter;
import spi.g2;
import spi.h2;
import spi.mi;
import spi.mm;

/* JADX INFO: loaded from: classes2.dex */
final class GsonRequestBodyConverter<T> implements Converter<T, mm> {
    private static final mi MEDIA_TYPE = mi.f.a("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    public GsonRequestBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    public mm convert(T t) throws IOException {
        g2 g2Var = new g2();
        JsonWriter jsonWriterNewJsonWriter = this.gson.newJsonWriter(new OutputStreamWriter(new h2(g2Var), UTF_8));
        this.adapter.write(jsonWriterNewJsonWriter, t);
        jsonWriterNewJsonWriter.close();
        return mm.create(MEDIA_TYPE, g2Var.q());
    }
}
