package com.amazon.avod.media.playback.support;

import kotlin.Metadata;

/* JADX INFO: compiled from: RendererSchemeOverrides.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/amazon/avod/media/playback/support/RendererSchemeOverrides;", "", "forceMediaCodecMediaDrm", "", "getForceMediaCodecMediaDrm", "()Z", "forceMediaCodecSwPlayReady", "getForceMediaCodecSwPlayReady", "forceWidevine", "getForceWidevine", "NoRendererSchemeOverrides", "RendererSchemeOverridesData", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RendererSchemeOverrides {

    /* JADX INFO: renamed from: NoRendererSchemeOverrides, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    boolean getForceMediaCodecMediaDrm();

    boolean getForceMediaCodecSwPlayReady();

    boolean getForceWidevine();

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.support.RendererSchemeOverrides$NoRendererSchemeOverrides, reason: from kotlin metadata */
    /* JADX INFO: compiled from: RendererSchemeOverrides.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/avod/media/playback/support/RendererSchemeOverrides$NoRendererSchemeOverrides;", "Lcom/amazon/avod/media/playback/support/RendererSchemeOverrides;", "()V", "forceMediaCodecMediaDrm", "", "getForceMediaCodecMediaDrm", "()Z", "forceMediaCodecSwPlayReady", "getForceMediaCodecSwPlayReady", "forceWidevine", "getForceWidevine", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements RendererSchemeOverrides {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceMediaCodecMediaDrm() {
            return false;
        }

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceMediaCodecSwPlayReady() {
            return false;
        }

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceWidevine() {
            return false;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: RendererSchemeOverrides.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/avod/media/playback/support/RendererSchemeOverrides$RendererSchemeOverridesData;", "Lcom/amazon/avod/media/playback/support/RendererSchemeOverrides;", "forceMediaCodecSwPlayReady", "", "forceMediaCodecMediaDrm", "forceWidevine", "(ZZZ)V", "getForceMediaCodecMediaDrm", "()Z", "getForceMediaCodecSwPlayReady", "getForceWidevine", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class RendererSchemeOverridesData implements RendererSchemeOverrides {
        private final boolean forceMediaCodecMediaDrm;
        private final boolean forceMediaCodecSwPlayReady;
        private final boolean forceWidevine;

        public static /* synthetic */ RendererSchemeOverridesData copy$default(RendererSchemeOverridesData rendererSchemeOverridesData, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = rendererSchemeOverridesData.getForceMediaCodecSwPlayReady();
            }
            if ((i & 2) != 0) {
                z2 = rendererSchemeOverridesData.getForceMediaCodecMediaDrm();
            }
            if ((i & 4) != 0) {
                z3 = rendererSchemeOverridesData.getForceWidevine();
            }
            return rendererSchemeOverridesData.copy(z, z2, z3);
        }

        public final boolean component1() {
            return getForceMediaCodecSwPlayReady();
        }

        public final boolean component2() {
            return getForceMediaCodecMediaDrm();
        }

        public final boolean component3() {
            return getForceWidevine();
        }

        public final RendererSchemeOverridesData copy(boolean forceMediaCodecSwPlayReady, boolean forceMediaCodecMediaDrm, boolean forceWidevine) {
            return new RendererSchemeOverridesData(forceMediaCodecSwPlayReady, forceMediaCodecMediaDrm, forceWidevine);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RendererSchemeOverridesData)) {
                return false;
            }
            RendererSchemeOverridesData rendererSchemeOverridesData = (RendererSchemeOverridesData) other;
            return getForceMediaCodecSwPlayReady() == rendererSchemeOverridesData.getForceMediaCodecSwPlayReady() && getForceMediaCodecMediaDrm() == rendererSchemeOverridesData.getForceMediaCodecMediaDrm() && getForceWidevine() == rendererSchemeOverridesData.getForceWidevine();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public int hashCode() {
            boolean forceMediaCodecSwPlayReady = getForceMediaCodecSwPlayReady();
            ?? r0 = forceMediaCodecSwPlayReady;
            if (forceMediaCodecSwPlayReady) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean forceMediaCodecMediaDrm = getForceMediaCodecMediaDrm();
            ?? r2 = forceMediaCodecMediaDrm;
            if (forceMediaCodecMediaDrm) {
                r2 = 1;
            }
            int i2 = (i + r2) * 31;
            boolean forceWidevine = getForceWidevine();
            return i2 + (forceWidevine ? 1 : forceWidevine);
        }

        public String toString() {
            return "RendererSchemeOverridesData(forceMediaCodecSwPlayReady=" + getForceMediaCodecSwPlayReady() + ", forceMediaCodecMediaDrm=" + getForceMediaCodecMediaDrm() + ", forceWidevine=" + getForceWidevine() + ')';
        }

        public RendererSchemeOverridesData(boolean z, boolean z2, boolean z3) {
            this.forceMediaCodecSwPlayReady = z;
            this.forceMediaCodecMediaDrm = z2;
            this.forceWidevine = z3;
        }

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceMediaCodecSwPlayReady() {
            return this.forceMediaCodecSwPlayReady;
        }

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceMediaCodecMediaDrm() {
            return this.forceMediaCodecMediaDrm;
        }

        @Override // com.amazon.avod.media.playback.support.RendererSchemeOverrides
        public boolean getForceWidevine() {
            return this.forceWidevine;
        }
    }
}
