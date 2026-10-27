package net.bytebuddy.jar.asm.commons;

import java.util.Collections;
import java.util.Map;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleRemapper extends Remapper {
    private final Map<String, String> mapping;

    public SimpleRemapper(Map<String, String> map) {
        this.mapping = map;
    }

    public SimpleRemapper(String str, String str2) {
        this.mapping = Collections.singletonMap(str, str2);
    }

    @Override // net.bytebuddy.jar.asm.commons.Remapper
    public String mapMethodName(String str, String str2, String str3) {
        String map = map(str + TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH + str2 + str3);
        return map == null ? str2 : map;
    }

    @Override // net.bytebuddy.jar.asm.commons.Remapper
    public String mapInvokeDynamicMethodName(String str, String str2) {
        String map = map("." + str + str2);
        return map == null ? str : map;
    }

    @Override // net.bytebuddy.jar.asm.commons.Remapper
    public String mapFieldName(String str, String str2, String str3) {
        String map = map(str + TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH + str2);
        return map == null ? str2 : map;
    }

    @Override // net.bytebuddy.jar.asm.commons.Remapper
    public String map(String str) {
        return this.mapping.get(str);
    }
}
