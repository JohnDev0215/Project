package spi;

import android.content.ContentResolver;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bt {
    public static final xb f = new xb();
    public final xb a;
    public final at b;
    public final m0 c;
    public final ContentResolver d;
    public final List<ImageHeaderParser> e;

    public bt(List<ImageHeaderParser> list, at atVar, m0 m0Var, ContentResolver contentResolver) {
        this(list, f, atVar, m0Var, contentResolver);
    }

    public bt(List<ImageHeaderParser> list, xb xbVar, at atVar, m0 m0Var, ContentResolver contentResolver) {
        this.a = xbVar;
        this.b = atVar;
        this.c = m0Var;
        this.d = contentResolver;
        this.e = list;
    }
}
