package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public class LocalAssetFetchProducer extends LocalFetchProducer {
    private final AssetManager mAssetManager;

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected String getProducerName() {
        return "LocalAssetFetchProducer";
    }

    public LocalAssetFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, AssetManager assetManager) {
        super(executor, pooledByteBufferFactory);
        this.mAssetManager = assetManager;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        return getEncodedImage(this.mAssetManager.open(getAssetName(imageRequest), 2), getLength(imageRequest));
    }

    private int getLength(ImageRequest imageRequest) {
        AssetFileDescriptor assetFileDescriptorOpenFd = null;
        try {
            assetFileDescriptorOpenFd = this.mAssetManager.openFd(getAssetName(imageRequest));
            int length = (int) assetFileDescriptorOpenFd.getLength();
            try {
                assetFileDescriptorOpenFd.close();
            } catch (IOException unused) {
            }
            return length;
        } catch (IOException unused2) {
            if (assetFileDescriptorOpenFd != null) {
                try {
                    assetFileDescriptorOpenFd.close();
                } catch (IOException unused3) {
                }
            }
            return -1;
        } catch (Throwable th) {
            if (assetFileDescriptorOpenFd != null) {
                try {
                    assetFileDescriptorOpenFd.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static String getAssetName(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().getPath().substring(1);
    }
}
