package DDC.DDC.DDC.DdCcD2c.YY2Y;

import android.hardware.watermark.WatermarkRender;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkWatermarkRender {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WatermarkRender f22a;

    public a(WatermarkRender watermarkRender) {
        if (watermarkRender == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("watermarkRender is null");
        }
        this.f22a = watermarkRender;
    }

    public WatermarkRender a() {
        return this.f22a;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addFont(@NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        return this.f22a.addFont(str, i, str2, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f22a.addNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean addNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup) {
        if (iDiLinkWatermarkNodeGroup instanceof b) {
            return this.f22a.addNodeGroup(((b) iDiLinkWatermarkNodeGroup).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean lock() {
        return this.f22a.lock();
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j) {
        return this.f22a.render(bArr, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean renderForDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        return this.f22a.renderForDirect(byteBuffer, i, i2, i3, j);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean rmNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f22a.rmNode(((c) iDiLinkWatermarkNode).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean rmNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup) {
        if (iDiLinkWatermarkNodeGroup instanceof b) {
            return this.f22a.rmNodeGroup(((b) iDiLinkWatermarkNodeGroup).a());
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public boolean setNodeFont(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode, @NonNull String str, int i, @NonNull String str2, @NonNull String str3) {
        if (iDiLinkWatermarkNode instanceof c) {
            return this.f22a.setNodeFont(((c) iDiLinkWatermarkNode).a(), str, i, str2, str3);
        }
        return false;
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender
    public void unlock() {
        this.f22a.unlock();
    }
}
