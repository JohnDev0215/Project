package x;

/* JADX INFO: loaded from: classes.dex */
public interface F0 {
    boolean a();

    long b(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3);

    AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3);

    AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3);

    default AbstractC2652s t(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return c(b(abstractC2652s, abstractC2652s2, abstractC2652s3), abstractC2652s, abstractC2652s2, abstractC2652s3);
    }
}
