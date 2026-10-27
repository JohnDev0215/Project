package mNTT77n7n.mNTT77n7n.mNTT77n7n.MTmnNTT.nmMNN77m;

import android.hardware.watermark.WatermarkNode;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class c implements IDiLinkWatermarkNode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkNode f3138a;

    public c(WatermarkNode watermarkNode) {
        if (watermarkNode == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("WatermarkNode is null");
        }
        this.f3138a = watermarkNode;
    }

    public WatermarkNode a() {
        return this.f3138a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean disable() {
        return this.f3138a.disable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean enable() {
        return this.f3138a.enable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public int getKey() {
        return this.f3138a.getKey();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean isEnable() {
        return this.f3138a.isEnable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public void release() {
        this.f3138a.release();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f3138a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean renderDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f3138a.renderDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setFontPath(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f3138a.setFontPath(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setImgArea(int i, int i2, int i3, int i4) {
        return this.f3138a.setImgArea(i, i2, i3, i4);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setImgPath(int i, @NonNull String str) {
        return this.f3138a.setImgPath(i, str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setText(@NonNull String str) {
        return this.f3138a.setText(str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setTextArea(int i, int i2, int i3, int i4) {
        return this.f3138a.setTextArea(i, i2, i3, i4);
    }
}
