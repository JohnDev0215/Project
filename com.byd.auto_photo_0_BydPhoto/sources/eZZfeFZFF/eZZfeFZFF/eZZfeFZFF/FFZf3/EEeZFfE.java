package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FFZf3;

import android.content.Context;
import android.os.BydPerfManager;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPrefManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkPrefManager.class, singleton = true)
public class EEeZFfE implements IDiLinkPrefManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPerfManager f3038a;

    public EEeZFfE(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1, true)) {
            this.f3038a = (BydPerfManager) context.getSystemService("BYDMgmt");
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventEnd(@Nullable String str) {
        this.f3038a.noteEventEnd(str);
    }

    @Override // com.byd.dilink.os.IDiLinkPrefManager
    public void noteEventStart(@Nullable String str) {
        this.f3038a.noteEventStart(str);
    }
}
