package mNTT77n7n.mNTT77n7n.mNTT77n7n.MTmnNTT.nmMNN77m;

import android.hardware.watermark.WatermarkRender;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkWatermarkRender {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkRender f3136a;

    public a(WatermarkRender watermarkRender) {
        if (watermarkRender == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("watermarkRender is null");
        }
        this.f3136a = watermarkRender;
    }

    public WatermarkRender a() {
        return this.f3136a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addFont(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f3136a.addFont(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3136a.addNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup) {
        if (iDiLinkWatermarkNodeGroup instanceof b) {
            return this.f3136a.addNodeGroup(((b) iDiLinkWatermarkNodeGroup).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean lock() {
        return this.f3136a.lock();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f3136a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean renderForDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f3136a.renderForDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean rmNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3136a.rmNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean rmNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup) {
        if (iDiLinkWatermarkNodeGroup instanceof b) {
            return this.f3136a.rmNodeGroup(((b) iDiLinkWatermarkNodeGroup).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean setNodeFont(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode, @NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f3136a.setNodeFont(((c) iDiLinkWatermarkNode).a(), str, i, str2, str3);
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public void unlock() {
        this.f3136a.unlock();
    }
}
