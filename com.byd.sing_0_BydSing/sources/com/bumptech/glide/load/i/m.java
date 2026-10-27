package com.bumptech.glide.load.i;

import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.bumptech.glide.load.i.e;
import java.io.IOException;

/* JADX INFO: compiled from: ParcelFileDescriptorRewinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class m implements e<ParcelFileDescriptor> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2939a;

    /* JADX INFO: compiled from: ParcelFileDescriptorRewinder.java */
    public static final class a implements e.a<ParcelFileDescriptor> {
        @Override // com.bumptech.glide.load.i.e.a
        public Class<ParcelFileDescriptor> a() {
            return ParcelFileDescriptor.class;
        }

        @Override // com.bumptech.glide.load.i.e.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e<ParcelFileDescriptor> b(ParcelFileDescriptor parcelFileDescriptor) {
            return new m(parcelFileDescriptor);
        }
    }

    /* JADX INFO: compiled from: ParcelFileDescriptorRewinder.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ParcelFileDescriptor f2940a;

        b(ParcelFileDescriptor parcelFileDescriptor) {
            this.f2940a = parcelFileDescriptor;
        }

        ParcelFileDescriptor a() throws IOException {
            try {
                Os.lseek(this.f2940a.getFileDescriptor(), 0L, OsConstants.SEEK_SET);
                return this.f2940a;
            } catch (ErrnoException e) {
                throw new IOException(e);
            }
        }
    }

    public m(ParcelFileDescriptor parcelFileDescriptor) {
        this.f2939a = new b(parcelFileDescriptor);
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 21;
    }

    @Override // com.bumptech.glide.load.i.e
    public void b() {
    }

    @Override // com.bumptech.glide.load.i.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public ParcelFileDescriptor a() throws IOException {
        return this.f2939a.a();
    }
}
