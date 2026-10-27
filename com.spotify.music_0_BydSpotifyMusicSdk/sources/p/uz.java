package p;

import com.spotify.player.esperanto.proto.EsContextPlayerError$ContextPlayerError;
import com.spotify.player.esperanto.proto.EsContextPlayerState$ContextPlayerState;
import com.spotify.player.esperanto.proto.EsQueue$Queue;
import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import java.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public final class uz implements io.reactivex.rxjava3.functions.o {
    public static final uz e = new uz(0);
    public static final uz k = new uz(1);
    public static final uz l = new uz(2);
    public static final uz m = new uz(3);
    public static final uz n = new uz(4);
    public static final uz o = new uz(5);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final uz f197p = new uz(7);
    public static final uz q = new uz(8);
    public static final uz r = new uz(9);
    public static final uz s = new uz(10);
    public static final uz t = new uz(11);
    public static final uz u = new uz(12);
    public static final uz v = new uz(13);
    public static final uz w = new uz(15);
    public static final uz x = new uz(16);
    public static final uz y = new uz(17);
    public final /* synthetic */ int c;

    public /* synthetic */ uz(int i) {
        this.c = i;
    }

    public final EsResponseWithReasons$ResponseWithReasons a(byte[] bArr) {
        switch (this.c) {
            case 0:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e2) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e2);
                }
            case 1:
            case 2:
            case 3:
            default:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e3) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e3);
                }
            case 4:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e4) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e4);
                }
            case 5:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e5) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e5);
                }
            case 6:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e6) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e6);
                }
            case 7:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e7) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e7);
                }
            case 8:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e8) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e8);
                }
            case 9:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e9) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e9);
                }
            case 10:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e10) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e10);
                }
            case 11:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e11) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e11);
                }
            case 12:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e12) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e12);
                }
            case 13:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e13) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e13);
                }
            case 14:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e14) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e14);
                }
            case 15:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e15) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e15);
                }
            case 16:
                v03.j(bArr, "data");
                try {
                    return EsResponseWithReasons$ResponseWithReasons.g(bArr);
                } catch (Exception e16) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsResponseWithReasons.ResponseWithReasons: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e16);
                }
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return a((byte[]) obj);
            case 1:
                byte[] bArr = (byte[]) obj;
                v03.j(bArr, "data");
                try {
                    return EsContextPlayerError$ContextPlayerError.g(bArr);
                } catch (Exception e2) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsContextPlayerError.ContextPlayerError: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e2);
                }
            case 2:
                byte[] bArr2 = (byte[]) obj;
                v03.j(bArr2, "data");
                try {
                    return EsQueue$Queue.m(bArr2);
                } catch (Exception e3) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsQueue.Queue: '", Base64.getEncoder().encodeToString(bArr2), "' (Base64)"), e3);
                }
            case 3:
                byte[] bArr3 = (byte[]) obj;
                v03.j(bArr3, "data");
                try {
                    return EsContextPlayerState$ContextPlayerState.P(bArr3);
                } catch (Exception e4) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.player.esperanto.proto.EsContextPlayerState.ContextPlayerState: '", Base64.getEncoder().encodeToString(bArr3), "' (Base64)"), e4);
                }
            case 4:
                return a((byte[]) obj);
            case 5:
                return a((byte[]) obj);
            case 6:
                return a((byte[]) obj);
            case 7:
                return a((byte[]) obj);
            case 8:
                return a((byte[]) obj);
            case 9:
                return a((byte[]) obj);
            case 10:
                return a((byte[]) obj);
            case 11:
                return a((byte[]) obj);
            case 12:
                return a((byte[]) obj);
            case 13:
                return a((byte[]) obj);
            case 14:
                return a((byte[]) obj);
            case 15:
                return a((byte[]) obj);
            case 16:
                return a((byte[]) obj);
            default:
                return a((byte[]) obj);
        }
    }
}
