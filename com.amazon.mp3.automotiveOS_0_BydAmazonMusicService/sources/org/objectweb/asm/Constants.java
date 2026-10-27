package org.objectweb.asm;

import com.fasterxml.jackson.core.JsonPointer;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes6.dex */
final class Constants {
    private Constants() {
    }

    static void checkAsmExperimental(Object obj) {
        Class<?> cls = obj.getClass();
        String strReplace = cls.getName().replace('.', JsonPointer.SEPARATOR);
        if (isWhitelisted(strReplace)) {
            return;
        }
        checkIsPreview(cls.getClassLoader().getResourceAsStream(strReplace + ".class"));
    }

    static boolean isWhitelisted(String str) {
        if (!str.startsWith("org/objectweb/asm/")) {
            return false;
        }
        if (!str.contains("Test$")) {
            if (!Pattern.matches("org/objectweb/asm/util/Trace(Annotation|Class|Field|Method|Module|RecordComponent|Signature)Visitor(\\$.*)?", str)) {
                if (!Pattern.matches("org/objectweb/asm/util/Check(Annotation|Class|Field|Method|Module|RecordComponent|Signature)Adapter(\\$.*)?", str)) {
                    return false;
                }
            }
        }
        return true;
    }

    static void checkIsPreview(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalStateException("Bytecode not available, can't check class version");
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            try {
                dataInputStream.readInt();
                if (dataInputStream.readUnsignedShort() != 65535) {
                    throw new IllegalStateException("ASM9_EXPERIMENTAL can only be used by classes compiled with --enable-preview");
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException("I/O error, can't check class version", e);
        }
    }
}
