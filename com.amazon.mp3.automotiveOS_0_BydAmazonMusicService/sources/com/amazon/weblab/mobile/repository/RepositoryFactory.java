package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.cache.ICachePolicy;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class RepositoryFactory {
    private static Set<String> sFolder = new HashSet();

    public static IRepository createRepository(RepositoryType repositoryType, String str, String str2, ICachePolicy iCachePolicy) {
        if (sFolder.contains(str)) {
            throw new RuntimeException(String.format("The %s it is in use by another repository", str));
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$weblab$mobile$repository$RepositoryType[repositoryType.ordinal()];
        if (i == 1) {
            sFolder.add(str);
            return new Repository(iCachePolicy, new FileStorage(str, str2), str2);
        }
        if (i == 2) {
            sFolder.add(str);
            Repository repository = new Repository(iCachePolicy, new LazyStorage(str, str2), str2);
            repository.setFailsafeRepository(new Repository(iCachePolicy, new FileStorage(str, str2), str2));
            return repository;
        }
        if (i == 3) {
            sFolder.add(str);
            Repository repository2 = new Repository(iCachePolicy, new FlatBufferStorage(str, str2), str2);
            repository2.setFailsafeRepository(new Repository(iCachePolicy, new FlatBufferStorage(str, str2), str2));
            return repository2;
        }
        throw new RuntimeException(String.format("The %s is not supported", repositoryType.toString()));
    }

    /* JADX INFO: renamed from: com.amazon.weblab.mobile.repository.RepositoryFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$weblab$mobile$repository$RepositoryType;

        static {
            int[] iArr = new int[RepositoryType.values().length];
            $SwitchMap$com$amazon$weblab$mobile$repository$RepositoryType = iArr;
            try {
                iArr[RepositoryType.FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$repository$RepositoryType[RepositoryType.LAZY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$repository$RepositoryType[RepositoryType.FLATBUFFERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
