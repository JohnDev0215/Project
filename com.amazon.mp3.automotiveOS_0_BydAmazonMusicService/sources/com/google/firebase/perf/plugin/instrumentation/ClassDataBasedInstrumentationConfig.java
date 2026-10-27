package com.google.firebase.perf.plugin.instrumentation;

import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationFactory;
import java.util.List;
import javax.annotation.Nullable;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class ClassDataBasedInstrumentationConfig extends InstrumentationConfigBase {
    private final ClassContext classContext;

    public ClassDataBasedInstrumentationConfig(ClassContext classContext, List<AnnotatedMethodInstrumentationConfig> list, List<NetworkObjectInstrumentationConfig> list2) {
        super(list, list2);
        this.classContext = classContext;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.InstrumentationConfig
    @Nullable
    public NetworkObjectInstrumentationFactory getNetworkObjectInstrumentationFactory(String str, String str2, String str3) {
        ClassData classDataLoadClassData;
        if (Type.getObjectType(str).getSort() != 10 || (classDataLoadClassData = this.classContext.loadClassData(str.replace(JsonPointer.SEPARATOR, '.'))) == null) {
            return null;
        }
        for (NetworkObjectInstrumentationConfig networkObjectInstrumentationConfig : this.networkObjectConfigs) {
            String className = Type.getObjectType(networkObjectInstrumentationConfig.getClassName()).getClassName();
            boolean z = classDataLoadClassData.getClassName().equals(className) || classDataLoadClassData.getInterfaces().contains(className) || classDataLoadClassData.getSuperClasses().contains(className);
            boolean zEquals = networkObjectInstrumentationConfig.getMethodName().equals(str2);
            boolean zEquals2 = networkObjectInstrumentationConfig.getMethodDesc().equals(str3);
            if (z && zEquals && zEquals2) {
                return networkObjectInstrumentationConfig.getFactory();
            }
        }
        return null;
    }
}
