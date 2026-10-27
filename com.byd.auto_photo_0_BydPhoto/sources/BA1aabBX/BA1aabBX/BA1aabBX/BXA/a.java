package BA1aabBX.BA1aabBX.BA1aabBX.BXA;

import android.acquisitionservice.BYDAcquisitionClient;
import com.byd.dilink50.acquisitionservice.DiLinkAcquisitionClient;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a f4a = new a();

    private /* synthetic */ a() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new DiLinkAcquisitionClient((BYDAcquisitionClient) obj);
    }
}
