package java9.util.stream;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSupport$$Lambda$1 implements Runnable {
    private final BaseStream arg$1;

    private StreamSupport$$Lambda$1(BaseStream baseStream) {
        this.arg$1 = baseStream;
    }

    public static Runnable lambdaFactory$(BaseStream baseStream) {
        return new StreamSupport$$Lambda$1(baseStream);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.arg$1.close();
    }
}
