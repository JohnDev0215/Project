package com.bumptech.glide.load;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import java.io.FileInputStream;
import java.io.IOException;
import spi.dm;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class d implements a.d {
    public final /* synthetic */ ParcelFileDescriptorRewinder a;
    public final /* synthetic */ m0 b;

    public d(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, m0 m0Var) {
        this.a = parcelFileDescriptorRewinder;
        this.b = m0Var;
    }

    @Override // com.bumptech.glide.load.a.d
    public final int a(ImageHeaderParser imageHeaderParser) throws Throwable {
        dm dmVar;
        dm dmVar2 = null;
        try {
            dmVar = new dm(new FileInputStream(this.a.c().getFileDescriptor()), this.b);
        } catch (Throwable th) {
            th = th;
        }
        try {
            int iB = imageHeaderParser.b(dmVar, this.b);
            try {
                dmVar.close();
            } catch (IOException unused) {
            }
            this.a.c();
            return iB;
        } catch (Throwable th2) {
            th = th2;
            dmVar2 = dmVar;
            if (dmVar2 != null) {
                try {
                    dmVar2.close();
                } catch (IOException unused2) {
                }
            }
            this.a.c();
            throw th;
        }
    }
}
