package com.google.firebase.perf.plugin.instrumentation;

import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class ClassLoaderBasedInstrumentationConfig extends InstrumentationConfigBase {
    private final ClassLoader classLoader;
    private final Set<String> missingClasses;

    public ClassLoaderBasedInstrumentationConfig(ClassLoader classLoader, List<AnnotatedMethodInstrumentationConfig> list, List<NetworkObjectInstrumentationConfig> list2) {
        super(list, list2);
        this.missingClasses = new HashSet();
        this.classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.InstrumentationConfig
    @Nullable
    public NetworkObjectInstrumentationFactory getNetworkObjectInstrumentationFactory(String str, String str2, String str3) {
        Type objectType = Type.getObjectType(str);
        if (objectType.getSort() != 10) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(objectType.getClassName(), false, this.classLoader);
            for (NetworkObjectInstrumentationConfig networkObjectInstrumentationConfig : this.networkObjectConfigs) {
                String className = Type.getObjectType(networkObjectInstrumentationConfig.getClassName()).getClassName();
                if (!this.missingClasses.contains(className)) {
                    try {
                        boolean zIsAssignableFrom = Class.forName(className, false, this.classLoader).isAssignableFrom(cls);
                        boolean zEquals = networkObjectInstrumentationConfig.getMethodName().equals(str2);
                        boolean zEquals2 = networkObjectInstrumentationConfig.getMethodDesc().equals(str3);
                        if (zIsAssignableFrom && zEquals && zEquals2) {
                            return networkObjectInstrumentationConfig.getFactory();
                        }
                    } catch (Exception | LinkageError unused) {
                        this.missingClasses.add(className);
                    }
                }
            }
        } catch (Exception | LinkageError unused2) {
        }
        return null;
    }
}
