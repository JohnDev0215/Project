package f2;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ImageHeaderParserRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f6339a = new ArrayList();

    public synchronized void a(ImageHeaderParser imageHeaderParser) {
        this.f6339a.add(imageHeaderParser);
    }

    public synchronized List<ImageHeaderParser> b() {
        return this.f6339a;
    }
}
