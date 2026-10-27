package dagger.spi.model;

import com.google.common.base.CaseFormat;

/* JADX INFO: loaded from: classes7.dex */
public enum RequestKind {
    INSTANCE,
    PROVIDER,
    LAZY,
    PROVIDER_OF_LAZY,
    MEMBERS_INJECTION,
    PRODUCER,
    PRODUCED,
    FUTURE;

    /* JADX INFO: renamed from: dagger.spi.model.RequestKind$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dagger$spi$model$RequestKind;

        static {
            int[] iArr = new int[RequestKind.values().length];
            $SwitchMap$dagger$spi$model$RequestKind = iArr;
            try {
                iArr[RequestKind.INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.PROVIDER_OF_LAZY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.MEMBERS_INJECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$dagger$spi$model$RequestKind[RequestKind.FUTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String format(Key key) {
        int i = AnonymousClass1.$SwitchMap$dagger$spi$model$RequestKind[ordinal()];
        if (i == 1) {
            return key.toString();
        }
        if (i == 2) {
            return String.format("Provider<Lazy<%s>>", key);
        }
        if (i != 3) {
            return i != 4 ? String.format("%s<%s>", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name()), key) : String.format("ListenableFuture<%s>", key);
        }
        return String.format("injectMembers(%s)", key);
    }
}
