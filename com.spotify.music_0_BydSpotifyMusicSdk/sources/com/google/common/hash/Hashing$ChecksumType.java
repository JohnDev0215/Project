package com.google.common.hash;

import java.util.zip.Adler32;
import java.util.zip.CRC32;
import p.c41;
import p.p11;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
abstract class Hashing$ChecksumType implements c41 {
    public static final AnonymousClass1 c;
    public static final /* synthetic */ Hashing$ChecksumType[] e;
    public final p11 hashFunction;

    /* JADX INFO: renamed from: com.google.common.hash.Hashing$ChecksumType$1, reason: invalid class name */
    public enum AnonymousClass1 extends Hashing$ChecksumType {
        @Override // p.fh2
        public final Object get() {
            return new CRC32();
        }
    }

    /* JADX INFO: renamed from: com.google.common.hash.Hashing$ChecksumType$2, reason: invalid class name */
    public enum AnonymousClass2 extends Hashing$ChecksumType {
        @Override // p.fh2
        public final Object get() {
            return new Adler32();
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1("CRC_32", 0, "Hashing.crc32()");
        c = anonymousClass1;
        e = new Hashing$ChecksumType[]{anonymousClass1, new AnonymousClass2("ADLER_32", 1, "Hashing.adler32()")};
    }

    public Hashing$ChecksumType(String str, int i, String str2) {
        this.hashFunction = new ChecksumHashFunction(this, str2);
    }

    public static Hashing$ChecksumType valueOf(String str) {
        return (Hashing$ChecksumType) Enum.valueOf(Hashing$ChecksumType.class, str);
    }

    public static Hashing$ChecksumType[] values() {
        return (Hashing$ChecksumType[]) e.clone();
    }
}
