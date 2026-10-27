package com.amazon.avod.media.framework.error;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorEnumUtils {

    public interface ErrorEnum {
        String getDescription();

        int getErrorCode();
    }

    /* JADX WARN: Incorrect types in method signature: <E:Ljava/lang/Enum<TE;>;:Lcom/amazon/avod/media/framework/error/ErrorEnumUtils$ErrorEnum;>([TE;)Ljava/util/Map<Ljava/lang/Integer;TE;>; */
    /* JADX WARN: Multi-variable type inference failed */
    public static Map createErrorMap(@Nonnull Enum[] enumArr) {
        Preconditions.checkNotNull(enumArr);
        HashMap map = new HashMap();
        for (Object[] objArr : enumArr) {
            map.put(Integer.valueOf(((ErrorEnum) objArr).getErrorCode()), objArr);
        }
        return map;
    }

    /* JADX WARN: Incorrect return type in method signature: <E:Ljava/lang/Enum<TE;>;:Lcom/amazon/avod/media/framework/error/ErrorEnumUtils$ErrorEnum;>(ILjava/util/Map<Ljava/lang/Integer;TE;>;)TE; */
    public static Enum fromErrorCode(int i, @Nonnull Map map) {
        Preconditions.checkNotNull(map);
        if (map.containsKey(Integer.valueOf(i))) {
            return (Enum) map.get(Integer.valueOf(i));
        }
        DLog.devf("Error code %d is not a known error code", Integer.valueOf(i));
        return null;
    }

    public static <E extends Enum<E> & ErrorEnum> String mapErrorCodeToDescription(int i, @Nonnull Map<Integer, E> map) {
        Preconditions.checkNotNull(map);
        return containsErrorCode(i, map) ? toString(fromErrorCode(i, map)) : String.format(Locale.US, "Unsupported error: 0x%x", Integer.valueOf(i));
    }

    public static <E extends Enum<E> & ErrorEnum> boolean containsErrorCode(int i, @Nonnull Map<Integer, E> map) {
        Preconditions.checkNotNull(map);
        return map.containsKey(Integer.valueOf(i));
    }

    /* JADX WARN: Incorrect types in method signature: <E:Ljava/lang/Enum<TE;>;:Lcom/amazon/avod/media/framework/error/ErrorEnumUtils$ErrorEnum;>(TE;)Ljava/lang/String; */
    /* JADX WARN: Multi-variable type inference failed */
    @SuppressFBWarnings(justification = "It is a bug from findbugs", value = {"BC_UNCONFIRMED_CAST"})
    public static String toString(@Nonnull Enum r4) {
        Preconditions.checkNotNull(r4);
        return String.format(Locale.US, "Error 0x%x: %s: %s", Integer.valueOf(((ErrorEnum) r4).getErrorCode()), r4.name(), ((ErrorEnum) r4).getDescription());
    }
}
