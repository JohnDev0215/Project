package android.net.shared;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final class ParcelableUtil {
    public static <ParcelableType, BaseType> ParcelableType[] toParcelableArray(Collection<BaseType> collection, Function<BaseType, ParcelableType> function, Class<ParcelableType> cls) {
        ParcelableType[] parcelabletypeArr = (ParcelableType[]) ((Object[]) Array.newInstance((Class<?>) cls, collection.size()));
        int i = 0;
        Iterator<BaseType> it = collection.iterator();
        while (it.hasNext()) {
            parcelabletypeArr[i] = function.apply(it.next());
            i++;
        }
        return parcelabletypeArr;
    }

    public static <ParcelableType, BaseType> ArrayList<BaseType> fromParcelableArray(ParcelableType[] parceled, Function<ParcelableType, BaseType> conv) {
        ArrayList<BaseType> out = new ArrayList<>(parceled.length);
        for (ParcelableType t : parceled) {
            out.add(conv.apply(t));
        }
        return out;
    }
}
