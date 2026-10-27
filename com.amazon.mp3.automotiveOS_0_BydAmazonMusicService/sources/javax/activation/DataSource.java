package javax.activation;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes7.dex */
public interface DataSource {
    String getContentType();

    InputStream getInputStream() throws IOException;
}
