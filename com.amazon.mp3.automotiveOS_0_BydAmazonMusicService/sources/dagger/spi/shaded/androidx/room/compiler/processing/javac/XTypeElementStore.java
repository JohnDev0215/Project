package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XTypeElementStore.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004Be\u0012#\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e\u0012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000e\u0012!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\b\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00028\u00012\u0006\u0010\n\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\u0002¢\u0006\u0004\b\u000b\u0010\rR1\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R/\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00028\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0012R&\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00170\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/XTypeElementStore;", "BackingType", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "T", "", "", "qName", "element", "cache", "(Ljava/lang/String;Landroidx/room/compiler/processing/XTypeElement;)Landroidx/room/compiler/processing/XTypeElement;", "backingType", "get", "(Ljava/lang/Object;)Landroidx/room/compiler/processing/XTypeElement;", "(Ljava/lang/String;)Landroidx/room/compiler/processing/XTypeElement;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "findElement", "Lkotlin/jvm/functions/Function1;", "getQName", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "wrap", "", "Ljava/lang/ref/WeakReference;", "typeCache", "Ljava/util/Map;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class XTypeElementStore<BackingType, T extends XTypeElement> {
    private final Function1<String, BackingType> findElement;
    private final Function1<BackingType, String> getQName;
    private final Map<String, WeakReference<T>> typeCache;
    private final Function1<BackingType, T> wrap;

    /* JADX WARN: Multi-variable type inference failed */
    public XTypeElementStore(Function1<? super String, ? extends BackingType> findElement, Function1<? super BackingType, String> getQName, Function1<? super BackingType, ? extends T> wrap) {
        Intrinsics.checkNotNullParameter(findElement, "findElement");
        Intrinsics.checkNotNullParameter(getQName, "getQName");
        Intrinsics.checkNotNullParameter(wrap, "wrap");
        this.findElement = findElement;
        this.getQName = getQName;
        this.wrap = wrap;
        this.typeCache = new LinkedHashMap();
    }

    public final T get(BackingType backingtype) {
        T t;
        String strInvoke = this.getQName.invoke(backingtype);
        if (strInvoke == null) {
            return this.wrap.invoke(backingtype);
        }
        WeakReference<T> weakReference = this.typeCache.get(strInvoke);
        return (weakReference == null || (t = weakReference.get()) == null) ? (T) cache(strInvoke, this.wrap.invoke(backingtype)) : t;
    }

    public final T get(String qName) {
        T tInvoke;
        T t;
        Intrinsics.checkNotNullParameter(qName, "qName");
        WeakReference<T> weakReference = this.typeCache.get(qName);
        if (weakReference != null && (t = weakReference.get()) != null) {
            return t;
        }
        BackingType backingtypeInvoke = this.findElement.invoke(qName);
        if (backingtypeInvoke == null || (tInvoke = this.wrap.invoke(backingtypeInvoke)) == null) {
            return null;
        }
        return (T) cache(qName, tInvoke);
    }

    private final T cache(String str, T t) {
        this.typeCache.put(str, new WeakReference<>(t));
        return t;
    }
}
