package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public class LocalResourceFetchProducer extends LocalFetchProducer {
    private final Resources mResources;

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected String getProducerName() {
        return "LocalResourceFetchProducer";
    }

    public LocalResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Resources resources) {
        super(executor, pooledByteBufferFactory);
        this.mResources = resources;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        return getEncodedImage(this.mResources.openRawResource(getResourceId(imageRequest)), getLength(imageRequest));
    }

    private int getLength(ImageRequest imageRequest) {
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = null;
        try {
            assetFileDescriptorOpenRawResourceFd = this.mResources.openRawResourceFd(getResourceId(imageRequest));
            int length = (int) assetFileDescriptorOpenRawResourceFd.getLength();
            try {
                assetFileDescriptorOpenRawResourceFd.close();
            } catch (IOException unused) {
            }
            return length;
        } catch (Resources.NotFoundException unused2) {
            if (assetFileDescriptorOpenRawResourceFd != null) {
                try {
                    assetFileDescriptorOpenRawResourceFd.close();
                } catch (IOException unused3) {
                }
            }
            return -1;
        } catch (Throwable th) {
            if (assetFileDescriptorOpenRawResourceFd != null) {
                try {
                    assetFileDescriptorOpenRawResourceFd.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static int getResourceId(ImageRequest imageRequest) {
        return Integer.parseInt(imageRequest.getSourceUri().getPath().substring(1));
    }
}
