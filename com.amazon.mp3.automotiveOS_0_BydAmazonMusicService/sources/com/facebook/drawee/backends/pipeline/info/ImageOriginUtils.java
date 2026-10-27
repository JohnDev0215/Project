package com.facebook.drawee.backends.pipeline.info;

import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.ImagesContract;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes5.dex */
public class ImageOriginUtils {
    public static String toString(int imageOrigin) {
        switch (imageOrigin) {
            case 2:
                return "network";
            case 3:
                return "disk";
            case 4:
                return "memory_encoded";
            case 5:
                return "memory_bitmap";
            case 6:
                return "memory_bitmap_shortcut";
            case 7:
                return ImagesContract.LOCAL;
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int mapProducerNameToImageOrigin(final String producerName) {
        producerName.hashCode();
        byte b = -1;
        switch (producerName.hashCode()) {
            case -1917159454:
                if (producerName.equals("QualifiedResourceFetchProducer")) {
                    b = 0;
                }
                break;
            case -1914072202:
                if (producerName.equals("BitmapMemoryCacheGetProducer")) {
                    b = 1;
                }
                break;
            case -1683996557:
                if (producerName.equals("LocalResourceFetchProducer")) {
                    b = 2;
                }
                break;
            case -1579985851:
                if (producerName.equals("LocalFileFetchProducer")) {
                    b = 3;
                }
                break;
            case -1307634203:
                if (producerName.equals("EncodedMemoryCacheProducer")) {
                    b = 4;
                }
                break;
            case -1224383234:
                if (producerName.equals("NetworkFetchProducer")) {
                    b = 5;
                }
                break;
            case 473552259:
                if (producerName.equals("VideoThumbnailProducer")) {
                    b = 6;
                }
                break;
            case 656304759:
                if (producerName.equals("DiskCacheProducer")) {
                    b = 7;
                }
                break;
            case 957714404:
                if (producerName.equals("BitmapMemoryCacheProducer")) {
                    b = 8;
                }
                break;
            case 1019542023:
                if (producerName.equals("LocalAssetFetchProducer")) {
                    b = 9;
                }
                break;
            case 1023071510:
                if (producerName.equals("PostprocessedBitmapMemoryCacheProducer")) {
                    b = 10;
                }
                break;
            case 1721672898:
                if (producerName.equals("DataFetchProducer")) {
                    b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                }
                break;
            case 1793127518:
                if (producerName.equals("LocalContentUriThumbnailFetchProducer")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                }
                break;
            case 2109593398:
                if (producerName.equals("PartialDiskCacheProducer")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                }
                break;
            case 2113652014:
                if (producerName.equals("LocalContentUriFetchProducer")) {
                    b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                }
                break;
        }
        switch (b) {
            case 0:
            case 2:
            case 3:
            case 6:
            case 9:
            case 11:
            case 12:
            case 14:
                return 7;
            case 1:
            case 8:
            case 10:
                return 5;
            case 4:
                return 4;
            case 5:
                return 2;
            case 7:
            case 13:
                return 3;
            default:
                return 1;
        }
    }

    private ImageOriginUtils() {
    }
}
