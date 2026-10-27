package BA1aabBX.BA1aabBX.BA1aabBX.B1abXa1b.BXA;

import android.hardware.watermark.WatermarkNode;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class c implements IDiLinkWatermarkNode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkNode f2a;

    public c(WatermarkNode watermarkNode) {
        if (watermarkNode == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("WatermarkNode is null");
        }
        this.f2a = watermarkNode;
    }

    public WatermarkNode a() {
        return this.f2a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean disable() {
        return this.f2a.disable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean enable() {
        return this.f2a.enable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public int getKey() {
        return this.f2a.getKey();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean isEnable() {
        return this.f2a.isEnable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public void release() {
        this.f2a.release();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f2a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean renderDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f2a.renderDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setFontPath(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f2a.setFontPath(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setImgArea(int i, int i2, int i3, int i4) {
        return this.f2a.setImgArea(i, i2, i3, i4);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setImgPath(int i, @NonNull String str) {
        return this.f2a.setImgPath(i, str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setText(@NonNull String str) {
        return this.f2a.setText(str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode
    public boolean setTextArea(int i, int i2, int i3, int i4) {
        return this.f2a.setTextArea(i, i2, i3, i4);
    }
}
