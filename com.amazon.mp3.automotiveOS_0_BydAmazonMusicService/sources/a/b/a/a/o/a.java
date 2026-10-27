package a.b.a.a.o;

import android.media.MediaCodecInfo;
import com.netinsight.sye.syeClient.video.SyeVideoCodecSupport;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0017a f89a = new C0017a();

    /* JADX INFO: renamed from: a.b.a.a.o.a$a, reason: collision with other inner class name */
    public static final class C0017a {

        /* JADX INFO: renamed from: a.b.a.a.o.a$a$a, reason: collision with other inner class name */
        public static final class C0018a<T> implements Comparator<T> {
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((SyeVideoCodecSupport) t2).getProfile()), Integer.valueOf(((SyeVideoCodecSupport) t).getProfile()));
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.o.a$a$b */
        public static final class b<T> implements Comparator<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Comparator f90a;

            public b(Comparator comparator) {
                this.f90a = comparator;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = this.f90a.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((SyeVideoCodecSupport) t2).getLevel()), Integer.valueOf(((SyeVideoCodecSupport) t).getLevel()));
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.o.a$a$c */
        public static final class c extends Lambda implements Function1<MediaCodecInfo.CodecProfileLevel, SyeVideoCodecSupport> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f91a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str) {
                super(1);
                this.f91a = str;
            }

            /* JADX WARN: Removed duplicated region for block: B:125:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x00a1  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.netinsight.sye.syeClient.video.SyeVideoCodecSupport invoke(android.media.MediaCodecInfo.CodecProfileLevel r12) {
                /*
                    Method dump skipped, instruction units count: 550
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.a.C0017a.c.invoke(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.o.a$a$d */
        public static final class d extends Lambda implements Function1<SyeVideoCodecSupport, Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final d f92a = new d();

            public d() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(SyeVideoCodecSupport syeVideoCodecSupport) {
                SyeVideoCodecSupport syeVideoCodecSupport2 = syeVideoCodecSupport;
                return Boolean.valueOf((syeVideoCodecSupport2.getProfile() == 0 || syeVideoCodecSupport2.getLevel() == 0) ? false : true);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0045  */
        @androidx.annotation.VisibleForTesting(otherwise = 2)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.netinsight.sye.syeClient.video.SyeVideoCodecSupport a(java.lang.String r4, java.util.List<android.media.MediaCodecInfo.CodecProfileLevel> r5, a.b.a.a.o.c.b r6) {
            /*
                r3 = this;
                kotlin.sequences.Sequence r5 = kotlin.collections.CollectionsKt.asSequence(r5)
                a.b.a.a.o.a$a$c r0 = new a.b.a.a.o.a$a$c
                r0.<init>(r4)
                kotlin.sequences.Sequence r5 = kotlin.sequences.SequencesKt.map(r5, r0)
                a.b.a.a.o.a$a$d r0 = a.b.a.a.o.a.C0017a.d.f92a
                kotlin.sequences.Sequence r5 = kotlin.sequences.SequencesKt.filter(r5, r0)
                java.util.List r5 = kotlin.sequences.SequencesKt.toList(r5)
                boolean r0 = r5.isEmpty()
                r1 = 0
                if (r0 == 0) goto L50
                com.netinsight.sye.syeClient.video.SyeVideoCodecSupport r5 = new com.netinsight.sye.syeClient.video.SyeVideoCodecSupport
                int r0 = r4.hashCode()
                r2 = -1662541442(0xffffffff9ce7a17e, float:-1.5328042E-21)
                if (r0 == r2) goto L3a
                r2 = 1331836730(0x4f62373a, float:3.7952701E9)
                if (r0 == r2) goto L2f
                goto L45
            L2f:
                java.lang.String r0 = "video/avc"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L45
                a.b.a.a.k.e.c r4 = a.b.a.a.k.e.c.AVC
                goto L47
            L3a:
                java.lang.String r0 = "video/hevc"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L45
                a.b.a.a.k.e.c r4 = a.b.a.a.k.e.c.HEVC
                goto L47
            L45:
                a.b.a.a.k.e.c r4 = a.b.a.a.k.e.c.Undefined
            L47:
                int r4 = r4.f
                r5.<init>(r4, r1, r1)
                java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            L50:
                a.b.a.a.o.a$a$a r4 = new a.b.a.a.o.a$a$a
                r4.<init>()
                a.b.a.a.o.a$a$b r0 = new a.b.a.a.o.a$a$b
                r0.<init>(r4)
                java.util.List r4 = kotlin.collections.CollectionsKt.sortedWith(r5, r0)
                r5 = 1
                java.util.List r4 = kotlin.collections.CollectionsKt.take(r4, r5)
                java.util.List r4 = kotlin.collections.CollectionsKt.toList(r4)
                java.lang.Object r4 = r4.get(r1)
                com.netinsight.sye.syeClient.video.SyeVideoCodecSupport r4 = (com.netinsight.sye.syeClient.video.SyeVideoCodecSupport) r4
                a.b.a.a.k.e.d r5 = r6.a()
                int r5 = r5.W
                r4.setLevel(r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.a.C0017a.a(java.lang.String, java.util.List, a.b.a.a.o.c.b):com.netinsight.sye.syeClient.video.SyeVideoCodecSupport");
        }
    }
}
