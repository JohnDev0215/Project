package com.neusoft.na.common;

import android.app.Activity;
import android.view.Surface;
import com.neusoft.na.ocservice.OneCoreService;
import kotlin.Metadata;

/* JADX INFO: compiled from: IArComponent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\f"}, d2 = {"Lcom/neusoft/na/common/IArComponent;", "", "initArManager", "", "activity", "Landroid/app/Activity;", "initVisionManager", "setSurface", OneCoreService.PAYLOAD_SURFACE, "Landroid/view/Surface;", "startAr", "stopAr", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IArComponent {
    void initArManager(Activity activity);

    void initVisionManager(Activity activity);

    void setSurface(Surface surface);

    void startAr();

    void stopAr();
}
