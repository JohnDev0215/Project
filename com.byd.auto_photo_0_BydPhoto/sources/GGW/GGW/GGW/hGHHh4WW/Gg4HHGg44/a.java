package GGW.GGW.GGW.hGHHh4WW.Gg4HHGg44;

import android.hardware.watermark.WatermarkNodeGroup;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkWatermarkNodeGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkNodeGroup f58a;

    public a(WatermarkNodeGroup watermarkNodeGroup) {
        if (watermarkNodeGroup == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("watermarkNodeGroup is null");
        }
        this.f58a = watermarkNodeGroup;
    }

    public WatermarkNodeGroup a() {
        return this.f58a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean addFont(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f58a.addFont(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean addNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof b) {
            return this.f58a.addNode(((b) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean disable() {
        return this.f58a.disable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean enable() {
        return this.f58a.enable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public int getKey() {
        return this.f58a.getKey();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean isEnable() {
        return this.f58a.isEnable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean lock() {
        return this.f58a.lock();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f58a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean renderForDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f58a.renderForDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean rmNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof b) {
            return this.f58a.rmNode(((b) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public void setHalfSplitWatermark(int i, int i2) {
        this.f58a.setHalfSplitWatermark(i, i2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setNodeFont(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode, @NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        if (iDiLinkWatermarkNode instanceof b) {
            return this.f58a.setNodeFont(((b) iDiLinkWatermarkNode).a(), str, i, str2, str3);
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setPanoFrameNumber(int i) {
        return this.f58a.setPanoFrameNumber(i);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setPosition(int i, int i2, int i3, int i4) {
        return this.f58a.setPostion(i, i2, i3, i4);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public void unlock() {
        this.f58a.unlock();
    }
}
