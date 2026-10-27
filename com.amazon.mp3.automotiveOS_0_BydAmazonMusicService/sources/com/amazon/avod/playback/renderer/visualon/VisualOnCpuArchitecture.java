package com.amazon.avod.playback.renderer.visualon;

import android.os.Build;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum VisualOnCpuArchitecture {
    X86,
    ARM,
    MIPS,
    UNKNOWN;

    public static final VisualOnCpuArchitecture INSTANCE = getCpuArchitecture();

    private static VisualOnCpuArchitecture getCpuArchitecture() {
        return getCpuArchitectureFromString(Build.CPU_ABI);
    }

    static VisualOnCpuArchitecture getCpuArchitectureFromString(@Nonnull String str) {
        Preconditions.checkNotNull(str);
        String upperCase = str.trim().toUpperCase(Locale.US);
        VisualOnCpuArchitecture visualOnCpuArchitecture = ARM;
        if (upperCase.startsWith(visualOnCpuArchitecture.name())) {
            return visualOnCpuArchitecture;
        }
        VisualOnCpuArchitecture visualOnCpuArchitecture2 = X86;
        if (upperCase.startsWith(visualOnCpuArchitecture2.name())) {
            return visualOnCpuArchitecture2;
        }
        VisualOnCpuArchitecture visualOnCpuArchitecture3 = MIPS;
        return upperCase.startsWith(visualOnCpuArchitecture3.name()) ? visualOnCpuArchitecture3 : UNKNOWN;
    }
}
