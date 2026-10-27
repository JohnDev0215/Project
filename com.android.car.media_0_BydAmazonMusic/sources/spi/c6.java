package spi;

import android.text.TextUtils;
import androidx.core.util.Pair;
import com.android.car.media.common.MediaItemMetadata;
import java.util.function.BiFunction;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c6 implements BiFunction {
    public static final /* synthetic */ c6 b = new c6(0);
    public static final /* synthetic */ c6 c = new c6(1);
    public final /* synthetic */ int a;

    public /* synthetic */ c6(int i) {
        this.a = i;
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                return new Pair(obj, obj2);
            default:
                MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                return Boolean.valueOf((mediaItemMetadata == null || TextUtils.isEmpty(mediaItemMetadata.e) || !((u8) obj2).d()) ? false : true);
        }
    }
}
