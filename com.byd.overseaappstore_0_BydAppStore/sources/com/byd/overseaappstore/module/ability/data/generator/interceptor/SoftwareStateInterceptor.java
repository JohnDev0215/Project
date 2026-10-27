package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import androidx.exifinterface.media.ExifInterface;
import com.byd.car.VehicleBrand;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0017\u0010\u000b\u001a\u00028\u00012\b\b\u0002\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eJ+\u0010\u000f\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00022\b\b\u0002\u0010\f\u001a\u00020\rH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R.\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", ExifInterface.GPS_DIRECTION_TRUE, "K", VehicleBrand.VEHICLE_BRAND_F, "", "()V", "next", "getNext", "()Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", "setNext", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;)V", "generateDefaultRetData", "treeLogicRet", "", "(B)Ljava/lang/Object;", "handle", "t", "f", "(Ljava/lang/Object;Ljava/lang/Object;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SoftwareStateInterceptor<T, K, F> {

    @Nullable
    private SoftwareStateInterceptor<T, K, F> next;

    public static /* synthetic */ Object generateDefaultRetData$default(SoftwareStateInterceptor softwareStateInterceptor, byte b, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: generateDefaultRetData");
        }
        if ((i & 1) != 0) {
            b = 0;
        }
        return softwareStateInterceptor.generateDefaultRetData(b);
    }

    public static /* synthetic */ Object handle$default(SoftwareStateInterceptor softwareStateInterceptor, Object obj, Object obj2, byte b, Continuation continuation, int i, Object obj3) {
        if (obj3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handle");
        }
        if ((i & 4) != 0) {
            b = 0;
        }
        return softwareStateInterceptor.handle(obj, obj2, b, continuation);
    }

    public abstract K generateDefaultRetData(byte treeLogicRet);

    @Nullable
    public final SoftwareStateInterceptor<T, K, F> getNext() {
        return this.next;
    }

    @Nullable
    public abstract Object handle(T t, F f, byte b, @NotNull Continuation<? super K> continuation);

    public final void setNext(@Nullable SoftwareStateInterceptor<T, K, F> softwareStateInterceptor) {
        this.next = softwareStateInterceptor;
    }
}
