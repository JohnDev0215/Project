package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import spi.dm;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: com.bumptech.glide.load.a$a, reason: collision with other inner class name */
    public class C0015a implements e {
        public final /* synthetic */ InputStream a;

        public C0015a(InputStream inputStream) {
            this.a = inputStream;
        }

        @Override // com.bumptech.glide.load.a.e
        public final ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.d(this.a);
            } finally {
                this.a.reset();
            }
        }
    }

    public class b implements e {
        public final /* synthetic */ ByteBuffer a;

        public b(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
        }

        @Override // com.bumptech.glide.load.a.e
        public final ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            return imageHeaderParser.a(this.a);
        }
    }

    public class c implements d {
        public final /* synthetic */ InputStream a;
        public final /* synthetic */ m0 b;

        public c(InputStream inputStream, m0 m0Var) {
            this.a = inputStream;
            this.b = m0Var;
        }

        @Override // com.bumptech.glide.load.a.d
        public final int a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.b(this.a, this.b);
            } finally {
                this.a.reset();
            }
        }
    }

    public interface d {
        int a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    public interface e {
        ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    private a() {
    }

    public static int a(@NonNull List<ImageHeaderParser> list, @Nullable InputStream inputStream, @NonNull m0 m0Var) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new dm(inputStream, m0Var);
        }
        inputStream.mark(5242880);
        return b(list, new c(inputStream, m0Var));
    }

    public static int b(@NonNull List<ImageHeaderParser> list, d dVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int iA = dVar.a(list.get(i));
            if (iA != -1) {
                return iA;
            }
        }
        return -1;
    }

    @NonNull
    public static ImageHeaderParser.ImageType c(@NonNull List<ImageHeaderParser> list, @Nullable InputStream inputStream, @NonNull m0 m0Var) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new dm(inputStream, m0Var);
        }
        inputStream.mark(5242880);
        return e(list, new C0015a(inputStream));
    }

    @NonNull
    public static ImageHeaderParser.ImageType d(@NonNull List<ImageHeaderParser> list, @Nullable ByteBuffer byteBuffer) throws IOException {
        return byteBuffer == null ? ImageHeaderParser.ImageType.UNKNOWN : e(list, new b(byteBuffer));
    }

    @NonNull
    public static ImageHeaderParser.ImageType e(@NonNull List<ImageHeaderParser> list, e eVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType imageTypeA = eVar.a(list.get(i));
            if (imageTypeA != ImageHeaderParser.ImageType.UNKNOWN) {
                return imageTypeA;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }
}
