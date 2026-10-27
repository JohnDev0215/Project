package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import java.io.FileInputStream;
import java.io.IOException;
import spi.dm;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a.e {
    public final /* synthetic */ ParcelFileDescriptorRewinder a;
    public final /* synthetic */ m0 b;

    public b(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, m0 m0Var) {
        this.a = parcelFileDescriptorRewinder;
        this.b = m0Var;
    }

    @Override // com.bumptech.glide.load.a.e
    public final ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws Throwable {
        dm dmVar;
        try {
            dmVar = new dm(new FileInputStream(this.a.c().getFileDescriptor()), this.b);
        } catch (Throwable th) {
            th = th;
            dmVar = null;
        }
        try {
            ImageHeaderParser.ImageType imageTypeD = imageHeaderParser.d(dmVar);
            try {
                dmVar.close();
            } catch (IOException unused) {
            }
            this.a.c();
            return imageTypeD;
        } catch (Throwable th2) {
            th = th2;
            if (dmVar != null) {
                try {
                    dmVar.close();
                } catch (IOException unused2) {
                }
            }
            this.a.c();
            throw th;
        }
    }
}
