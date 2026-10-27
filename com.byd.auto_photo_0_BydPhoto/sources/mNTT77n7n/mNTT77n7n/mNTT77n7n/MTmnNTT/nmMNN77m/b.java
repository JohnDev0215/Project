package mNTT77n7n.mNTT77n7n.mNTT77n7n.MTmnNTT.nmMNN77m;

import android.hardware.watermark.WatermarkNodeGroup;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class b implements IDiLinkWatermarkNodeGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkNodeGroup f3137a;

    public b(WatermarkNodeGroup watermarkNodeGroup) {
        if (watermarkNodeGroup == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("watermarkNodeGroup is null");
        }
        this.f3137a = watermarkNodeGroup;
    }

    public WatermarkNodeGroup a() {
        return this.f3137a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean addFont(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f3137a.addFont(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean addNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3137a.addNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean disable() {
        return this.f3137a.disable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean enable() {
        return this.f3137a.enable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public int getKey() {
        return this.f3137a.getKey();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean isEnable() {
        return this.f3137a.isEnable();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean lock() {
        return this.f3137a.lock();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f3137a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean renderForDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f3137a.renderForDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean rmNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3137a.rmNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public void setHalfSplitWatermark(int i, int i2) {
        this.f3137a.setHalfSplitWatermark(i, i2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setNodeFont(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode, @NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3137a.setNodeFont(((c) iDiLinkWatermarkNode).a(), str, i, str2, str3);
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setPanoFrameNumber(int i) {
        return this.f3137a.setPanoFrameNumber(i);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public boolean setPosition(int i, int i2, int i3, int i4) {
        return this.f3137a.setPostion(i, i2, i3, i4);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup
    public void unlock() {
        this.f3137a.unlock();
    }
}
