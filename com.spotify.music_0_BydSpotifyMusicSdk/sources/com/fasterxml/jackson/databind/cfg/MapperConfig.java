package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonFormat$Value;
import com.fasterxml.jackson.annotation.JsonInclude$Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import p.qw;

/* JADX INFO: loaded from: classes.dex */
public abstract class MapperConfig<T extends MapperConfig<T>> implements Serializable {
    private static final long serialVersionUID = 2;

    static {
        JsonInclude$Value jsonInclude$Value = JsonInclude$Value.c;
        JsonFormat$Value jsonFormat$Value = JsonFormat$Value.e;
    }

    public static int a(Class cls) {
        int iB = 0;
        for (Object obj : (Enum[]) cls.getEnumConstants()) {
            qw qwVar = (qw) obj;
            if (qwVar.a()) {
                iB |= qwVar.b();
            }
        }
        return iB;
    }
}
