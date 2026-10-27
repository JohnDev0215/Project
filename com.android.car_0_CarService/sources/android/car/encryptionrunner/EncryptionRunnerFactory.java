package android.car.encryptionrunner;

/* JADX INFO: loaded from: classes.dex */
public class EncryptionRunnerFactory {

    public @interface EncryptionRunnerType {
        public static final int OOB_UKEY2 = 1;
        public static final int UKEY2 = 0;
    }

    private EncryptionRunnerFactory() {
    }

    public static EncryptionRunner newRunner(int type) {
        if (type == 0) {
            return new Ukey2EncryptionRunner();
        }
        if (type == 1) {
            return new OobUkey2EncryptionRunner();
        }
        throw new IllegalArgumentException("Unknown EncryptionRunnerType: " + type);
    }

    @Deprecated
    public static EncryptionRunner newRunner() {
        return newRunner(0);
    }

    public static EncryptionRunner newDummyRunner() {
        return new DummyEncryptionRunner();
    }
}
