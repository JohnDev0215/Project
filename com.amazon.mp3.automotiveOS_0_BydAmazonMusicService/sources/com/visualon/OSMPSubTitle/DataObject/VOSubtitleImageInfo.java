package com.visualon.OSMPSubTitle.DataObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.visualon.OSMPUtils.voOSType;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleImageInfo {
    private static final String TAG = "voSubtitleImageInfo";
    public VOSubtitleImageInfoData imageData = null;
    public VOSubtitleImageInfoDescriptor imageInfoDescriptor = null;
    private Bitmap bitmap = null;

    public Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        if (this.imageData.getPicData() == null) {
            return null;
        }
        voOSType.VOOSMP_IMAGE_TYPE imageType = this.imageData.getImageType();
        voOSType.VOOSMP_IMAGE_TYPE voosmp_image_type = voOSType.VOOSMP_IMAGE_TYPE.VOOSMP_IMAGE_RGBA32;
        if (imageType == voosmp_image_type || this.imageData.getImageType() == voOSType.VOOSMP_IMAGE_TYPE.VOOSMP_IMAGE_ARGB32) {
            try {
                this.bitmap = Bitmap.createBitmap(this.imageData.getWidth(), this.imageData.getHeight(), Bitmap.Config.ARGB_8888);
                byte[] picData = this.imageData.getPicData();
                if (this.imageData.getImageType() == voosmp_image_type) {
                    byte[] bArr = new byte[this.imageData.getPicData().length];
                    for (int i = 0; i < this.imageData.getPicData().length; i += 4) {
                        int i2 = i + 3;
                        if (picData[i2] == 0) {
                            bArr[i] = 0;
                            bArr[i + 1] = 0;
                            bArr[i + 2] = 0;
                        } else {
                            bArr[i] = picData[i];
                            int i3 = i + 1;
                            bArr[i3] = picData[i3];
                            int i4 = i + 2;
                            bArr[i4] = picData[i4];
                            bArr[i2] = picData[i2];
                        }
                    }
                    picData = bArr;
                }
                this.bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(picData));
            } catch (Exception unused) {
                Log.e(TAG, "subtitle image create bitmap failed!");
            }
        } else {
            byte[] bArr2 = this.imageData.picData;
            this.bitmap = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
        }
        return this.bitmap;
    }
}
