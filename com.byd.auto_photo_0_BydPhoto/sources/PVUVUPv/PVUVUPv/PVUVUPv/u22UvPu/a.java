package PVUVUPv.PVUVUPv.PVUVUPv.u22UvPu;

import android.acquisitionservice.BYDAcquisitionClient;
import com.byd.dilink_rse.acquisitionservice.DiLinkAcquisitionClient;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a f84a = new a();

    private /* synthetic */ a() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new DiLinkAcquisitionClient((BYDAcquisitionClient) obj);
    }
}
