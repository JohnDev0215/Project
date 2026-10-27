package kotlinx.coroutines.channels;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.google.android.gms.cast.MediaError;
import com.visualon.OSMPUtils.voOSType;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: compiled from: Deprecated.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aJ\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\nH\u0001¢\u0006\u0002\u0010\u000b\u001a!\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a1\u0010\u0010\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u0006\u0012\u0002\b\u00030\nH\u0001\u001a!\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0007\u001aZ\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a0\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010!\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a+\u0010$\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001aT\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ai\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u0010 \u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001aT\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0001\u001aA\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u00101\u001a!\u00102\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u00103\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a`\u00104\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172(\u00106\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\n0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u00107\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a!\u0010:\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a)\u0010;\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a#\u0010<\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aZ\u0010=\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010>\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u00106\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0001ø\u0001\u0000¢\u0006\u0002\u0010(\u001au\u0010?\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001729\u00106\u001a5\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001a`\u0010@\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172$\u00106\u001a \b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a?\u0010A\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a?\u0010F\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a!\u0010G\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a$\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0007\u001a!\u0010I\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u0010J\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a0\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a9\u0010M\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u00101\u001a;\u0010N\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010O\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0P\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aU\u0010O\u001a\u0002HS\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q\"\u0018\b\u0002\u0010S*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0015\u0012\u0006\b\u0000\u0012\u0002HQ0T*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\n2\u0006\u0010.\u001a\u0002HSH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a'\u0010V\u001a\b\u0012\u0004\u0012\u0002H\u000e0W\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u000e0Y\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010Z\u001a\b\u0012\u0004\u0012\u0002H\u000e0[\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a.\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0]0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a?\u0010^\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H50R0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\nH\u0087\u0004\u001az\u0010^\u001a\b\u0012\u0004\u0012\u0002HQ0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105\"\u0004\b\u0002\u0010Q*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001726\u00106\u001a2\u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011H5¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(a\u0012\u0004\u0012\u0002HQ0\u0019H\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, d2 = {"consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "any", "", "E", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumes", "count", "", "distinct", "distinctBy", "K", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "predicate", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", PageUriUtils.FILTER_QUERY_PARAM, "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", PlatformWeblabs.C, "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "first", "firstOrNull", "flatMap", "R", "transform", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", "none", "requireNoNulls", "single", "singleOrNull", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", "V", "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toMutableSet", "", "toSet", "", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, k = 5, mv = {1, 6, 0}, xi = 48, xs = "kotlinx/coroutines/channels/ChannelsKt")
final /* synthetic */ class ChannelsKt__DeprecatedKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1, reason: invalid class name */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {404}, m = "any", n = {"$this$consume$iv"}, s = {"L$0"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.any(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "count", n = {"count", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class C05531<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05531(Continuation<? super C05531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.count(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {38}, m = "elementAt", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    static final class C05581<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05581(Continuation<? super C05581> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {53}, m = "elementAtOrNull", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    static final class C05591<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05591(Continuation<? super C05591> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class C05641<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05641(Continuation<? super C05641> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (Collection) null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3, reason: invalid class name */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 242}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass3<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (SendChannel) null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {65}, m = "first", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    static final class C05651<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05651(Continuation<? super C05651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.first(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {75}, m = "firstOrNull", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    static final class C05661<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05661(Continuation<? super C05661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {487}, m = "indexOf", n = {"element", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class C05681<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C05681(Continuation<? super C05681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {97, 100}, m = "last", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class C05691<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05691(Continuation<? super C05691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.last(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 0}, l = {487}, m = "lastIndexOf", n = {"element", "lastIndex", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C05701<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C05701(Continuation<? super C05701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {123, GeneratorAdapter.AND}, m = "lastOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class C05711<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05711(Continuation<? super C05711> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.DASH_MANIFEST_NO_MIMETYPE}, m = "maxWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "max"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    static final class C05741<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C05741(Continuation<? super C05741> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {434, 436}, m = "minWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    static final class C05751<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C05751(Continuation<? super C05751> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.minWith(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {447}, m = "none", n = {"$this$consume$iv"}, s = {"L$0"})
    static final class C05761<E> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C05761(Continuation<? super C05761> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.none(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {136, voOSType.VOOSMP_PID_ANALYTICS_EXPORT}, m = "single", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C05781<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05781(Continuation<? super C05781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.single(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {149, 152}, m = "singleOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C05791<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C05791(Continuation<? super C05791> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 278}, m = "toChannel", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C05821<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05821(Continuation<? super C05821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toChannel(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toCollection", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class C05831<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05831(Continuation<? super C05831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toCollection(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2, reason: invalid class name */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toMap", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass2<K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toMap(null, null, this);
        }
    }

    @PublishedApi
    public static final Function1<Throwable, Unit> consumesAll(final ReceiveChannel<?>... receiveChannelArr) {
        return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.consumesAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws Throwable {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) throws Throwable {
                Throwable th2 = null;
                for (ReceiveChannel<?> receiveChannel : receiveChannelArr) {
                    try {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                    } catch (Throwable th3) {
                        if (th2 == null) {
                            th2 = th3;
                        } else {
                            ExceptionsKt__ExceptionsKt.addSuppressed(th2, th3);
                        }
                    }
                }
                if (th2 != null) {
                    throw th2;
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006c A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #2 {all -> 0x0039, blocks: (B:12:0x0035, B:27:0x0064, B:29:0x006c, B:35:0x007b, B:36:0x0092), top: B:48:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007b A[Catch: all -> 0x0039, TRY_ENTER, TryCatch #2 {all -> 0x0039, blocks: (B:12:0x0035, B:27:0x0064, B:29:0x006c, B:35:0x007b, B:36:0x0092), top: B:48:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x005f -> B:26:0x0063). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object elementAt(kotlinx.coroutines.channels.ReceiveChannel r10, int r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05581
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 46
            java.lang.String r4 = "ReceiveChannel doesn't contain element at index "
            r5 = 1
            if (r2 == 0) goto L44
            if (r2 != r5) goto L3c
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L39
            goto L63
        L39:
            r10 = move-exception
            goto Laf
        L3c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L44:
            kotlin.ResultKt.throwOnFailure(r12)
            if (r11 < 0) goto L97
            r12 = 0
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch: java.lang.Throwable -> L93
        L4e:
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L93
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L93
            r0.I$0 = r11     // Catch: java.lang.Throwable -> L93
            r0.I$1 = r12     // Catch: java.lang.Throwable -> L93
            r0.label = r5     // Catch: java.lang.Throwable -> L93
            java.lang.Object r6 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L93
            if (r6 != r1) goto L5f
            return r1
        L5f:
            r9 = r6
            r6 = r10
            r10 = r12
            r12 = r9
        L63:
            r7 = 0
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> L39
            boolean r12 = r12.booleanValue()     // Catch: java.lang.Throwable -> L39
            if (r12 == 0) goto L7b
            java.lang.Object r12 = r2.next()     // Catch: java.lang.Throwable -> L39
            int r8 = r10 + 1
            if (r11 != r10) goto L78
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r7)
            return r12
        L78:
            r10 = r6
            r12 = r8
            goto L4e
        L7b:
            java.lang.IndexOutOfBoundsException r10 = new java.lang.IndexOutOfBoundsException     // Catch: java.lang.Throwable -> L39
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r12.<init>()     // Catch: java.lang.Throwable -> L39
            r12.append(r4)     // Catch: java.lang.Throwable -> L39
            r12.append(r11)     // Catch: java.lang.Throwable -> L39
            r12.append(r3)     // Catch: java.lang.Throwable -> L39
            java.lang.String r11 = r12.toString()     // Catch: java.lang.Throwable -> L39
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L39
            throw r10     // Catch: java.lang.Throwable -> L39
        L93:
            r11 = move-exception
            r6 = r10
            r10 = r11
            goto Laf
        L97:
            java.lang.IndexOutOfBoundsException r12 = new java.lang.IndexOutOfBoundsException     // Catch: java.lang.Throwable -> L93
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93
            r0.<init>()     // Catch: java.lang.Throwable -> L93
            r0.append(r4)     // Catch: java.lang.Throwable -> L93
            r0.append(r11)     // Catch: java.lang.Throwable -> L93
            r0.append(r3)     // Catch: java.lang.Throwable -> L93
            java.lang.String r11 = r0.toString()     // Catch: java.lang.Throwable -> L93
            r12.<init>(r11)     // Catch: java.lang.Throwable -> L93
            throw r12     // Catch: java.lang.Throwable -> L93
        Laf:
            throw r10     // Catch: java.lang.Throwable -> Lb0
        Lb0:
            r11 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #2 {all -> 0x007d, blocks: (B:27:0x0063, B:29:0x006b, B:24:0x0052, B:23:0x004e), top: B:47:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0060 -> B:27:0x0063). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel r8, int r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05591
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05591) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3a
            r7 = r10
            r10 = r8
            r8 = r5
            r5 = r7
            goto L63
        L3a:
            r8 = move-exception
            goto L80
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L44:
            kotlin.ResultKt.throwOnFailure(r10)
            if (r9 >= 0) goto L4d
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r4
        L4d:
            r10 = 0
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch: java.lang.Throwable -> L7d
        L52:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L7d
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L7d
            r0.I$0 = r9     // Catch: java.lang.Throwable -> L7d
            r0.I$1 = r10     // Catch: java.lang.Throwable -> L7d
            r0.label = r3     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r5 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L7d
            if (r5 != r1) goto L63
            return r1
        L63:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L7d
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L7d
            if (r5 == 0) goto L79
            java.lang.Object r5 = r2.next()     // Catch: java.lang.Throwable -> L7d
            int r6 = r10 + 1
            if (r9 != r10) goto L77
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r5
        L77:
            r10 = r6
            goto L52
        L79:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r4)
            return r4
        L7d:
            r9 = move-exception
            r5 = r8
            r8 = r9
        L80:
            throw r8     // Catch: java.lang.Throwable -> L81
        L81:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c A[Catch: all -> 0x0031, TRY_LEAVE, TryCatch #1 {all -> 0x0031, blocks: (B:12:0x002d, B:24:0x0054, B:26:0x005c, B:29:0x0064, B:30:0x006b), top: B:39:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0064 A[Catch: all -> 0x0031, TRY_ENTER, TryCatch #1 {all -> 0x0031, blocks: (B:12:0x002d, B:24:0x0054, B:26:0x005c, B:29:0x0064, B:30:0x006b), top: B:39:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object first(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05651
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05651) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L31
            goto L53
        L31:
            r5 = move-exception
            goto L6f
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch: java.lang.Throwable -> L6c
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L6c
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L6c
            r0.label = r3     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r0 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L6c
            if (r0 != r1) goto L4f
            return r1
        L4f:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L53:
            r1 = 0
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L31
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L31
            if (r6 == 0) goto L64
            java.lang.Object r5 = r5.next()     // Catch: java.lang.Throwable -> L31
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r5
        L64:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L31
            java.lang.String r6 = "ReceiveChannel is empty."
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L31
            throw r5     // Catch: java.lang.Throwable -> L31
        L6c:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L6f:
            throw r5     // Catch: java.lang.Throwable -> L70
        L70:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object firstOrNull(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05661
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05661) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L31
            goto L53
        L31:
            r5 = move-exception
            goto L6b
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch: java.lang.Throwable -> L68
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L68
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L68
            r0.label = r3     // Catch: java.lang.Throwable -> L68
            java.lang.Object r0 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L68
            if (r0 != r1) goto L4f
            return r1
        L4f:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L53:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L31
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L31
            r1 = 0
            if (r6 != 0) goto L60
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r1
        L60:
            java.lang.Object r5 = r5.next()     // Catch: java.lang.Throwable -> L31
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r5
        L68:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L6b:
            throw r5     // Catch: java.lang.Throwable -> L6c
        L6c:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070 A[Catch: all -> 0x0037, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0067, B:27:0x0070, B:29:0x007a, B:32:0x0084, B:21:0x0053, B:33:0x008b), top: B:44:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008b A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0067, B:27:0x0070, B:29:0x007a, B:32:0x0084, B:21:0x0053, B:33:0x008b), top: B:44:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0064 -> B:25:0x0067). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object indexOf(kotlinx.coroutines.channels.ReceiveChannel r7, java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05681
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05681) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L37
            goto L67
        L37:
            r7 = move-exception
            goto L9a
        L3a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
            r9.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch: java.lang.Throwable -> L96
            r6 = r8
            r8 = r7
            r7 = r2
            r2 = r9
            r9 = r6
        L53:
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L37
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L37
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L37
            r0.L$3 = r7     // Catch: java.lang.Throwable -> L37
            r0.label = r3     // Catch: java.lang.Throwable -> L37
            java.lang.Object r4 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L37
            if (r4 != r1) goto L64
            return r1
        L64:
            r6 = r4
            r4 = r9
            r9 = r6
        L67:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L37
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L37
            if (r9 == 0) goto L8b
            java.lang.Object r9 = r7.next()     // Catch: java.lang.Throwable -> L37
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r9)     // Catch: java.lang.Throwable -> L37
            if (r9 == 0) goto L84
            int r7 = r2.element     // Catch: java.lang.Throwable -> L37
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch: java.lang.Throwable -> L37
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r7
        L84:
            int r9 = r2.element     // Catch: java.lang.Throwable -> L37
            int r9 = r9 + r3
            r2.element = r9     // Catch: java.lang.Throwable -> L37
            r9 = r4
            goto L53
        L8b:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L37
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            r7 = -1
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        L96:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L9a:
            throw r7     // Catch: java.lang.Throwable -> L9b
        L9b:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0093 A[Catch: all -> 0x0036, TRY_LEAVE, TryCatch #1 {all -> 0x0036, blocks: (B:13:0x0032, B:38:0x008b, B:40:0x0093), top: B:54:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0086 -> B:37:0x008a). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object last(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05691
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05691) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L50
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L36
            goto L8a
        L36:
            r7 = move-exception
            r2 = r4
            goto La8
        L3a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L42:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L4e
            goto L68
        L4e:
            r7 = move-exception
            goto La8
        L50:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch: java.lang.Throwable -> La5
            r0.L$0 = r7     // Catch: java.lang.Throwable -> La5
            r0.L$1 = r8     // Catch: java.lang.Throwable -> La5
            r0.label = r4     // Catch: java.lang.Throwable -> La5
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> La5
            if (r2 != r1) goto L64
            return r1
        L64:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L68:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L4e
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L4e
            if (r8 == 0) goto L9d
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L4e
            r6 = r2
            r2 = r7
            r7 = r6
        L77:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> La5
            r0.L$1 = r2     // Catch: java.lang.Throwable -> La5
            r0.L$2 = r8     // Catch: java.lang.Throwable -> La5
            r0.label = r3     // Catch: java.lang.Throwable -> La5
            java.lang.Object r4 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> La5
            if (r4 != r1) goto L86
            return r1
        L86:
            r6 = r4
            r4 = r7
            r7 = r8
            r8 = r6
        L8a:
            r5 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L36
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L36
            if (r8 == 0) goto L99
            java.lang.Object r8 = r2.next()     // Catch: java.lang.Throwable -> L36
            r7 = r4
            goto L77
        L99:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            return r7
        L9d:
            java.util.NoSuchElementException r7 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L4e
            java.lang.String r8 = "ReceiveChannel is empty."
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L4e
            throw r7     // Catch: java.lang.Throwable -> L4e
        La5:
            r8 = move-exception
            r2 = r7
            r7 = r8
        La8:
            throw r7     // Catch: java.lang.Throwable -> La9
        La9:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007e A[Catch: all -> 0x003b, TryCatch #1 {all -> 0x003b, blocks: (B:12:0x0037, B:25:0x0075, B:27:0x007e, B:29:0x0088, B:30:0x008c, B:21:0x005f, B:31:0x0093), top: B:42:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0093 A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #1 {all -> 0x003b, blocks: (B:12:0x0037, B:25:0x0075, B:27:0x007e, B:29:0x0088, B:30:0x008c, B:21:0x005f, B:31:0x0093), top: B:42:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0072 -> B:25:0x0075). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel r8, java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05701
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r8 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            java.lang.Object r5 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3b
            goto L75
        L3b:
            r8 = move-exception
            goto La3
        L3e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L46:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.jvm.internal.Ref$IntRef r10 = new kotlin.jvm.internal.Ref$IntRef
            r10.<init>()
            r2 = -1
            r10.element = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            kotlinx.coroutines.channels.ChannelIterator r4 = r8.iterator()     // Catch: java.lang.Throwable -> L9f
            r7 = r9
            r9 = r8
            r8 = r4
            r4 = r10
            r10 = r7
        L5f:
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L3b
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L3b
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L3b
            r0.L$3 = r9     // Catch: java.lang.Throwable -> L3b
            r0.L$4 = r8     // Catch: java.lang.Throwable -> L3b
            r0.label = r3     // Catch: java.lang.Throwable -> L3b
            java.lang.Object r5 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L3b
            if (r5 != r1) goto L72
            return r1
        L72:
            r7 = r5
            r5 = r10
            r10 = r7
        L75:
            r6 = 0
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L3b
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L3b
            if (r10 == 0) goto L93
            java.lang.Object r10 = r8.next()     // Catch: java.lang.Throwable -> L3b
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r10)     // Catch: java.lang.Throwable -> L3b
            if (r10 == 0) goto L8c
            int r10 = r2.element     // Catch: java.lang.Throwable -> L3b
            r4.element = r10     // Catch: java.lang.Throwable -> L3b
        L8c:
            int r10 = r2.element     // Catch: java.lang.Throwable -> L3b
            int r10 = r10 + r3
            r2.element = r10     // Catch: java.lang.Throwable -> L3b
            r10 = r5
            goto L5f
        L93:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L3b
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r6)
            int r8 = r4.element
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            return r8
        L9f:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        La3:
            throw r8     // Catch: java.lang.Throwable -> La4
        La4:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097 A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #2 {all -> 0x0037, blocks: (B:13:0x0033, B:39:0x008f, B:41:0x0097), top: B:55:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x008b -> B:39:0x008f). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object lastOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05711
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05711) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L51
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L37
            goto L8f
        L37:
            r7 = move-exception
            r2 = r4
            goto La4
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L4f
            goto L69
        L4f:
            r7 = move-exception
            goto La4
        L51:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch: java.lang.Throwable -> La1
            r0.L$0 = r7     // Catch: java.lang.Throwable -> La1
            r0.L$1 = r8     // Catch: java.lang.Throwable -> La1
            r0.label = r4     // Catch: java.lang.Throwable -> La1
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> La1
            if (r2 != r1) goto L65
            return r1
        L65:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L69:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L4f
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L4f
            if (r8 != 0) goto L75
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            return r5
        L75:
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L4f
            r6 = r2
            r2 = r7
            r7 = r6
        L7c:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> La1
            r0.L$1 = r2     // Catch: java.lang.Throwable -> La1
            r0.L$2 = r8     // Catch: java.lang.Throwable -> La1
            r0.label = r3     // Catch: java.lang.Throwable -> La1
            java.lang.Object r4 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> La1
            if (r4 != r1) goto L8b
            return r1
        L8b:
            r6 = r4
            r4 = r7
            r7 = r8
            r8 = r6
        L8f:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L37
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L37
            if (r8 == 0) goto L9d
            java.lang.Object r8 = r2.next()     // Catch: java.lang.Throwable -> L37
            r7 = r4
            goto L7c
        L9d:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            return r7
        La1:
            r8 = move-exception
            r2 = r7
            r7 = r8
        La4:
            throw r7     // Catch: java.lang.Throwable -> La5
        La5:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006c A[Catch: all -> 0x004a, TRY_LEAVE, TryCatch #2 {all -> 0x004a, blocks: (B:20:0x0046, B:29:0x0064, B:31:0x006c, B:42:0x0096, B:43:0x009d), top: B:54:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0096 A[Catch: all -> 0x004a, TRY_ENTER, TryCatch #2 {all -> 0x004a, blocks: (B:20:0x0046, B:29:0x0064, B:31:0x006c, B:42:0x0096, B:43:0x009d), top: B:54:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object single(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05781
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05781) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r6 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L32
            goto L81
        L32:
            r6 = move-exception
            r2 = r0
            goto La1
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3e:
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L4a
            goto L64
        L4a:
            r6 = move-exception
            goto La1
        L4c:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch: java.lang.Throwable -> L9e
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L9e
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9e
            r0.label = r4     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r2 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L9e
            if (r2 != r1) goto L60
            return r1
        L60:
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
        L64:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L4a
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L4a
            if (r7 == 0) goto L96
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> L4a
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L4a
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L4a
            r0.label = r3     // Catch: java.lang.Throwable -> L4a
            java.lang.Object r6 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L4a
            if (r6 != r1) goto L7d
            return r1
        L7d:
            r0 = r2
            r5 = r7
            r7 = r6
            r6 = r5
        L81:
            r1 = 0
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L32
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L32
            if (r7 != 0) goto L8e
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r1)
            return r6
        L8e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L32
            java.lang.String r7 = "ReceiveChannel has more than one element."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L32
            throw r6     // Catch: java.lang.Throwable -> L32
        L96:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L4a
            java.lang.String r7 = "ReceiveChannel is empty."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L4a
            throw r6     // Catch: java.lang.Throwable -> L4a
        L9e:
            r7 = move-exception
            r2 = r6
            r6 = r7
        La1:
            throw r6     // Catch: java.lang.Throwable -> La2
        La2:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object singleOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05791
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05791) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L4d
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r7 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L33
            goto L86
        L33:
            r7 = move-exception
            r2 = r0
            goto L99
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L4b
            goto L65
        L4b:
            r7 = move-exception
            goto L99
        L4d:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch: java.lang.Throwable -> L96
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L96
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L96
            r0.label = r4     // Catch: java.lang.Throwable -> L96
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L96
            if (r2 != r1) goto L61
            return r1
        L61:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L65:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L4b
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L4b
            if (r8 != 0) goto L71
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            return r5
        L71:
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L4b
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L4b
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L4b
            r0.label = r3     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r7 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L4b
            if (r7 != r1) goto L82
            return r1
        L82:
            r0 = r2
            r6 = r8
            r8 = r7
            r7 = r6
        L86:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L33
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r8 == 0) goto L92
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            return r5
        L92:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r5)
            return r7
        L96:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L99:
            throw r7     // Catch: java.lang.Throwable -> L9a
        L9a:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", f = "Deprecated.kt", i = {0, 0, 1, 2}, l = {164, 169, 170}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "$this$produce"}, s = {"L$0", "I$0", "L$0", "L$0"})
    static final class C05561 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $n;
        final /* synthetic */ ReceiveChannel $this_drop;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05561(int i, ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_drop = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05561 c05561 = new C05561(this.$n, this.$this_drop, continuation);
            c05561.L$0 = obj;
            return c05561;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05561) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0076 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ab A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00ca  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0077 -> B:23:0x007e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00c8 -> B:32:0x009f). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 237
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05561.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return drop(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel drop(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05561(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return dropWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2, 3, 4}, l = {181, 182, 183, 187, 188}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
    static final class C05571 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $predicate;
        final /* synthetic */ ReceiveChannel $this_dropWhile;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05571(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$this_dropWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05571 c05571 = new C05571(this.$this_dropWhile, this.$predicate, continuation);
            c05571.L$0 = obj;
            return c05571;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05571) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x008d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00ce  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00e5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00e6  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00f1  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0104  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b1 -> B:27:0x00b5). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0102 -> B:35:0x00d9). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 263
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05571.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel dropWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05571(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {198, 199, 199}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    static final class C05601<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final /* synthetic */ ReceiveChannel<E> $this_filter;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05601(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C05601> continuation) {
            super(2, continuation);
            this.$this_filter = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05601 c05601 = new C05601(this.$this_filter, this.$predicate, continuation);
            c05601.L$0 = obj;
            return c05601;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C05601) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0057, code lost:
        
            r11 = r0;
            r0 = r1;
            r1 = r6;
            r6 = r8;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0065 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00a9  */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v15 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v7 */
        /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v4 */
        /* JADX WARN: Type inference failed for: r7v5 */
        /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference failed for: r9v0 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L48
                if (r1 == r5) goto L38
                if (r1 == r4) goto L26
                if (r1 != r3) goto L1e
                java.lang.Object r1 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r6 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r11)
                goto L56
            L1e:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L26:
                java.lang.Object r1 = r10.L$2
                java.lang.Object r6 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r11)
                r8 = r7
                r7 = r1
                r1 = r0
                r0 = r10
                goto L8d
            L38:
                java.lang.Object r1 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r6 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r11)
                r7 = r6
                r6 = r1
                r1 = r0
                r0 = r10
                goto L6c
            L48:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
                kotlinx.coroutines.channels.ReceiveChannel<E> r1 = r10.$this_filter
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r6 = r11
            L56:
                r11 = r10
            L57:
                r11.L$0 = r6
                r11.L$1 = r1
                r11.L$2 = r2
                r11.label = r5
                java.lang.Object r7 = r1.hasNext(r11)
                if (r7 != r0) goto L66
                return r0
            L66:
                r9 = r0
                r0 = r11
                r11 = r7
                r7 = r6
                r6 = r1
                r1 = r9
            L6c:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto La9
                java.lang.Object r11 = r6.next()
                kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r8 = r0.$predicate
                r0.L$0 = r7
                r0.L$1 = r6
                r0.L$2 = r11
                r0.label = r4
                java.lang.Object r8 = r8.mo4invoke(r11, r0)
                if (r8 != r1) goto L89
                return r1
            L89:
                r9 = r7
                r7 = r11
                r11 = r8
                r8 = r9
            L8d:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto La4
                r0.L$0 = r8
                r0.L$1 = r6
                r0.L$2 = r2
                r0.label = r3
                java.lang.Object r11 = r8.send(r7, r0)
                if (r11 != r1) goto La4
                return r1
            La4:
                r11 = r0
                r0 = r1
                r1 = r6
                r6 = r8
                goto L57
            La9:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05601.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @PublishedApi
    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05601(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterIndexed(receiveChannel, coroutineContext, function3);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {211, 212, 212}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "e", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
    static final class C05611 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function3 $predicate;
        final /* synthetic */ ReceiveChannel $this_filterIndexed;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05611(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.$this_filterIndexed = receiveChannel;
            this.$predicate = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05611 c05611 = new C05611(this.$this_filterIndexed, this.$predicate, continuation);
            c05611.L$0 = obj;
            return c05611;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05611) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0062, code lost:
        
            r13 = r0;
            r0 = r1;
            r7 = r8;
            r1 = r10;
         */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0072 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0082  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00ab  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00bc  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L52
                if (r1 == r5) goto L3f
                if (r1 == r4) goto L28
                if (r1 != r3) goto L20
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r13)
                goto L61
            L20:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L28:
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$2
                java.lang.Object r7 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
                java.lang.Object r8 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                kotlin.ResultKt.throwOnFailure(r13)
                r10 = r1
                r1 = r0
                r0 = r12
                r11 = r7
                r7 = r6
            L3c:
                r6 = r11
                goto La3
            L3f:
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r13)
                r8 = r7
                r7 = r6
                r6 = r1
                r1 = r0
                r0 = r12
                goto L7a
            L52:
                kotlin.ResultKt.throwOnFailure(r13)
                java.lang.Object r13 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
                r1 = 0
                kotlinx.coroutines.channels.ReceiveChannel r6 = r12.$this_filterIndexed
                kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
                r7 = r13
            L61:
                r13 = r12
            L62:
                r13.L$0 = r7
                r13.L$1 = r6
                r13.L$2 = r2
                r13.I$0 = r1
                r13.label = r5
                java.lang.Object r8 = r6.hasNext(r13)
                if (r8 != r0) goto L73
                return r0
            L73:
                r11 = r0
                r0 = r13
                r13 = r8
                r8 = r7
                r7 = r6
                r6 = r1
                r1 = r11
            L7a:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lc1
                java.lang.Object r13 = r7.next()
                kotlin.jvm.functions.Function3 r9 = r0.$predicate
                int r10 = r6 + 1
                java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
                r0.L$0 = r8
                r0.L$1 = r7
                r0.L$2 = r13
                r0.I$0 = r10
                r0.label = r4
                java.lang.Object r6 = r9.invoke(r6, r13, r0)
                if (r6 != r1) goto L9f
                return r1
            L9f:
                r11 = r7
                r7 = r13
                r13 = r6
                goto L3c
            La3:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lbc
                r0.L$0 = r8
                r0.L$1 = r6
                r0.L$2 = r2
                r0.I$0 = r10
                r0.label = r3
                java.lang.Object r13 = r8.send(r7, r0)
                if (r13 != r1) goto Lbc
                return r1
            Lbc:
                r13 = r0
                r0 = r1
                r7 = r8
                r1 = r10
                goto L62
            Lc1:
                kotlin.Unit r13 = kotlin.Unit.INSTANCE
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05611.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterIndexed(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05611(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterNot(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u008a@"}, d2 = {"E", "it", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1", f = "Deprecated.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    static final class C05621 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $predicate;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05621(Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05621 c05621 = new C05621(this.$predicate, continuation);
            c05621.L$0 = obj;
            return c05621;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(Object obj, Continuation continuation) {
            return ((C05621) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Function2 function2 = this.$predicate;
                this.label = 1;
                obj = function2.mo4invoke(obj2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(!((Boolean) obj).booleanValue());
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterNot(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filter(receiveChannel, coroutineContext, new C05621(function2, null));
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00018\u0000H\u008a@"}, d2 = {"", "E", "it", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C05631<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C05631(Continuation<? super C05631> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05631 c05631 = new C05631(continuation);
            c05631.L$0 = obj;
            return c05631;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(E e, Continuation<? super Boolean> continuation) {
            return ((C05631) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.L$0 != null);
        }
    }

    @PublishedApi
    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        return filter$default(receiveChannel, null, new C05631(null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: all -> 0x0035, TryCatch #0 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:29:0x006b, B:21:0x004a, B:31:0x0070), top: B:40:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:29:0x006b, B:21:0x004a, B:31:0x0070), top: B:40:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0059 -> B:25:0x005c). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r6, java.util.Collection r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05641
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05641) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L35
            goto L5c
        L35:
            r6 = move-exception
            goto L7a
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L76
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L4a:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L35
            r0.label = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L59
            return r1
        L59:
            r5 = r2
            r2 = r8
            r8 = r5
        L5c:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L35
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L70
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L6e
            r2.add(r8)     // Catch: java.lang.Throwable -> L35
        L6e:
            r8 = r2
            goto L4a
        L70:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L35
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L76:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L7a:
            throw r6     // Catch: java.lang.Throwable -> L7b
        L7b:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0062, code lost:
    
        r9 = r0;
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007e A[Catch: all -> 0x009d, TryCatch #2 {all -> 0x009d, blocks: (B:25:0x0062, B:29:0x0075, B:31:0x007e, B:33:0x0084, B:37:0x0097, B:24:0x005e), top: B:49:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097 A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #2 {all -> 0x009d, blocks: (B:25:0x0062, B:29:0x0075, B:31:0x007e, B:33:0x0084, B:37:0x0097, B:24:0x005e), top: B:49:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r7, kotlinx.coroutines.channels.SendChannel r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5b
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L59
            r9 = r7
            r7 = r8
            r8 = r2
            goto L62
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L59
            r6 = r0
            r0 = r7
            r7 = r8
            r8 = r2
        L56:
            r2 = r1
            r1 = r6
            goto L75
        L59:
            r7 = move-exception
            goto La1
        L5b:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ChannelIterator r9 = r7.iterator()     // Catch: java.lang.Throwable -> L9d
        L62:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L9d
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9d
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L9d
            r0.label = r4     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r2 = r9.hasNext(r0)     // Catch: java.lang.Throwable -> L9d
            if (r2 != r1) goto L71
            return r1
        L71:
            r6 = r0
            r0 = r9
            r9 = r2
            goto L56
        L75:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L9d
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L9d
            if (r9 == 0) goto L97
            java.lang.Object r9 = r0.next()     // Catch: java.lang.Throwable -> L9d
            if (r9 == 0) goto L93
            r1.L$0 = r8     // Catch: java.lang.Throwable -> L9d
            r1.L$1 = r7     // Catch: java.lang.Throwable -> L9d
            r1.L$2 = r0     // Catch: java.lang.Throwable -> L9d
            r1.label = r3     // Catch: java.lang.Throwable -> L9d
            java.lang.Object r9 = r8.send(r9, r1)     // Catch: java.lang.Throwable -> L9d
            if (r9 != r2) goto L93
            return r2
        L93:
            r9 = r0
            r0 = r1
            r1 = r2
            goto L62
        L97:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L9d
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r5)
            return r8
        L9d:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        La1:
            throw r7     // Catch: java.lang.Throwable -> La2
        La2:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {254, 255}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining"}, s = {"L$0", "I$0", "L$0", "I$0"})
    static final class C05801 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $n;
        final /* synthetic */ ReceiveChannel $this_take;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05801(int i, ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_take = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05801 c05801 = new C05801(this.$n, this.$this_take, continuation);
            c05801.L$0 = obj;
            return c05801;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05801) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0080 -> B:29:0x0082). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L36
                if (r1 == r3) goto L27
                if (r1 != r2) goto L1f
                int r1 = r8.I$0
                java.lang.Object r4 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r5
                r5 = r8
                goto L82
            L1f:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L27:
                int r1 = r8.I$0
                java.lang.Object r4 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r9)
                r6 = r8
                goto L65
            L36:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
                int r1 = r8.$n
                if (r1 != 0) goto L44
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L44:
                if (r1 < 0) goto L48
                r4 = r3
                goto L49
            L48:
                r4 = 0
            L49:
                if (r4 == 0) goto L8c
                kotlinx.coroutines.channels.ReceiveChannel r4 = r8.$this_take
                kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
                r5 = r8
            L52:
                r5.L$0 = r9
                r5.L$1 = r4
                r5.I$0 = r1
                r5.label = r3
                java.lang.Object r6 = r4.hasNext(r5)
                if (r6 != r0) goto L61
                return r0
            L61:
                r7 = r5
                r5 = r9
                r9 = r6
                r6 = r7
            L65:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto L89
                java.lang.Object r9 = r4.next()
                r6.L$0 = r5
                r6.L$1 = r4
                r6.I$0 = r1
                r6.label = r2
                java.lang.Object r9 = r5.send(r9, r6)
                if (r9 != r0) goto L80
                return r0
            L80:
                r9 = r5
                r5 = r6
            L82:
                int r1 = r1 + (-1)
                if (r1 != 0) goto L52
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L89:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L8c:
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>()
                java.lang.String r0 = "Requested element count "
                r9.append(r0)
                r9.append(r1)
                java.lang.String r0 = " is less than zero."
                r9.append(r0)
                java.lang.String r9 = r9.toString()
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r9 = r9.toString()
                r0.<init>(r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05801.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return take(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel take(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05801(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return takeWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_POSTPROCESSING, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DUAL_DECODING_MIXING}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    static final class C05811 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $predicate;
        final /* synthetic */ ReceiveChannel $this_takeWhile;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05811(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$this_takeWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05811 c05811 = new C05811(this.$this_takeWhile, this.$predicate, continuation);
            c05811.L$0 = obj;
            return c05811;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05811) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0063 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00ac  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00a7 -> B:14:0x0057). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L48
                if (r1 == r4) goto L38
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                goto L56
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L25:
                java.lang.Object r1 = r9.L$2
                java.lang.Object r5 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r10)
                r7 = r6
                r6 = r5
                r5 = r1
                r1 = r0
                r0 = r9
                goto L8c
            L38:
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                r6 = r5
                r5 = r1
                r1 = r0
                r0 = r9
                goto L6a
            L48:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
                kotlinx.coroutines.channels.ReceiveChannel r1 = r9.$this_takeWhile
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r5 = r10
            L56:
                r10 = r9
            L57:
                r10.L$0 = r5
                r10.L$1 = r1
                r10.label = r4
                java.lang.Object r6 = r1.hasNext(r10)
                if (r6 != r0) goto L64
                return r0
            L64:
                r8 = r0
                r0 = r10
                r10 = r6
                r6 = r5
                r5 = r1
                r1 = r8
            L6a:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto Lac
                java.lang.Object r10 = r5.next()
                kotlin.jvm.functions.Function2 r7 = r0.$predicate
                r0.L$0 = r6
                r0.L$1 = r5
                r0.L$2 = r10
                r0.label = r3
                java.lang.Object r7 = r7.mo4invoke(r10, r0)
                if (r7 != r1) goto L87
                return r1
            L87:
                r8 = r5
                r5 = r10
                r10 = r7
                r7 = r6
                r6 = r8
            L8c:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 != 0) goto L97
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L97:
                r0.L$0 = r7
                r0.L$1 = r6
                r10 = 0
                r0.L$2 = r10
                r0.label = r2
                java.lang.Object r10 = r7.send(r5, r0)
                if (r10 != r1) goto La7
                return r1
            La7:
                r10 = r0
                r0 = r1
                r1 = r6
                r5 = r7
                goto L57
            Lac:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05811.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel takeWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05811(receiveChannel, function2, null), 6, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079 A[Catch: all -> 0x0053, TryCatch #1 {all -> 0x0053, blocks: (B:13:0x0034, B:29:0x0071, B:31:0x0079, B:34:0x008c, B:18:0x004f), top: B:45:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008c A[Catch: all -> 0x0053, TRY_LEAVE, TryCatch #1 {all -> 0x0053, blocks: (B:13:0x0034, B:29:0x0071, B:31:0x0079, B:34:0x008c, B:18:0x004f), top: B:45:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r8v0, types: [C extends kotlinx.coroutines.channels.SendChannel<? super E>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object, kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0089 -> B:14:0x0037). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r7, C r8, kotlin.coroutines.Continuation<? super C> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05821
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L55
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L53
        L37:
            r9 = r7
            r7 = r8
            r8 = r2
            goto L5c
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L53
            goto L70
        L53:
            r7 = move-exception
            goto L96
        L55:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ChannelIterator r9 = r7.iterator()     // Catch: java.lang.Throwable -> L92
        L5c:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L92
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L92
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L92
            r0.label = r4     // Catch: java.lang.Throwable -> L92
            java.lang.Object r2 = r9.hasNext(r0)     // Catch: java.lang.Throwable -> L92
            if (r2 != r1) goto L6b
            return r1
        L6b:
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r2
            r2 = r6
        L70:
            r5 = 0
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L53
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L53
            if (r9 == 0) goto L8c
            java.lang.Object r9 = r7.next()     // Catch: java.lang.Throwable -> L53
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L53
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L53
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L53
            r0.label = r3     // Catch: java.lang.Throwable -> L53
            java.lang.Object r9 = r2.send(r9, r0)     // Catch: java.lang.Throwable -> L53
            if (r9 != r1) goto L37
            return r1
        L8c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L53
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r5)
            return r2
        L92:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L96:
            throw r7     // Catch: java.lang.Throwable -> L97
        L97:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:21:0x004a, B:28:0x006e), top: B:39:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006e A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:21:0x004a, B:28:0x006e), top: B:39:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0059 -> B:25:0x005c). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r6, C r7, kotlin.coroutines.Continuation<? super C> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05831
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L35
            goto L5c
        L35:
            r6 = move-exception
            goto L78
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L74
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L4a:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L35
            r0.label = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L59
            return r1
        L59:
            r5 = r2
            r2 = r8
            r8 = r5
        L5c:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L35
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L6e
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L35
            r2.add(r8)     // Catch: java.lang.Throwable -> L35
            r8 = r2
            goto L4a
        L6e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L35
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L74:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L78:
            throw r6     // Catch: java.lang.Throwable -> L79
        L79:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:21:0x004a, B:28:0x0078), top: B:39:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0078 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x005c, B:27:0x0065, B:21:0x004a, B:28:0x0078), top: B:39:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0059 -> B:25:0x005c). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r6, M r7, kotlin.coroutines.Continuation<? super M> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Map r2 = (java.util.Map) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L35
            goto L5c
        L35:
            r6 = move-exception
            goto L82
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L7e
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L4a:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L35
            r0.label = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L59
            return r1
        L59:
            r5 = r2
            r2 = r8
            r8 = r5
        L5c:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L35
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L78
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L35
            kotlin.Pair r8 = (kotlin.Pair) r8     // Catch: java.lang.Throwable -> L35
            java.lang.Object r4 = r8.getFirst()     // Catch: java.lang.Throwable -> L35
            java.lang.Object r8 = r8.getSecond()     // Catch: java.lang.Throwable -> L35
            r2.put(r4, r8)     // Catch: java.lang.Throwable -> L35
            r8 = r2
            goto L4a
        L78:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L35
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r4)
            return r2
        L7e:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L82:
            throw r6     // Catch: java.lang.Throwable -> L83
        L83:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return flatMap(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@"}, d2 = {"E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1", f = "Deprecated.kt", i = {0, 1, 2}, l = {MediaError.DetailedErrorCode.DASH_NETWORK, MediaError.DetailedErrorCode.DASH_NO_INIT, MediaError.DetailedErrorCode.DASH_NO_INIT}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    static final class C05671 extends SuspendLambda implements Function2 {
        final /* synthetic */ ReceiveChannel $this_flatMap;
        final /* synthetic */ Function2 $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05671(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$this_flatMap = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05671 c05671 = new C05671(this.$this_flatMap, this.$transform, continuation);
            c05671.L$0 = obj;
            return c05671;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05671) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0060 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0090 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0091 -> B:14:0x0054). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L45
                if (r1 == r4) goto L35
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                goto L53
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L25:
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                r6 = r5
                r5 = r1
                r1 = r0
                r0 = r9
                goto L82
            L35:
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                r6 = r5
                r5 = r1
                r1 = r0
                r0 = r9
                goto L67
            L45:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
                kotlinx.coroutines.channels.ReceiveChannel r1 = r9.$this_flatMap
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r5 = r10
            L53:
                r10 = r9
            L54:
                r10.L$0 = r5
                r10.L$1 = r1
                r10.label = r4
                java.lang.Object r6 = r1.hasNext(r10)
                if (r6 != r0) goto L61
                return r0
            L61:
                r8 = r0
                r0 = r10
                r10 = r6
                r6 = r5
                r5 = r1
                r1 = r8
            L67:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto L96
                java.lang.Object r10 = r5.next()
                kotlin.jvm.functions.Function2 r7 = r0.$transform
                r0.L$0 = r6
                r0.L$1 = r5
                r0.label = r3
                java.lang.Object r10 = r7.mo4invoke(r10, r0)
                if (r10 != r1) goto L82
                return r1
            L82:
                kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
                r0.L$0 = r6
                r0.L$1 = r5
                r0.label = r2
                java.lang.Object r10 = kotlinx.coroutines.channels.ChannelsKt.toChannel(r10, r6, r0)
                if (r10 != r1) goto L91
                return r1
            L91:
                r10 = r0
                r0 = r1
                r1 = r5
                r5 = r6
                goto L54
            L96:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05671.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel flatMap(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05671(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@"}, d2 = {"E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {487, 333, 333}, m = "invokeSuspend", n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
    static final class C05721<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_map;
        final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05721(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C05721> continuation) {
            super(2, continuation);
            this.$this_map = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05721 c05721 = new C05721(this.$this_map, this.$transform, continuation);
            c05721.L$0 = obj;
            return c05721;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C05721) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0093 A[Catch: all -> 0x00d1, TRY_LEAVE, TryCatch #0 {all -> 0x00d1, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:44:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00c2 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00c9 A[Catch: all -> 0x00d1, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00d1, blocks: (B:8:0x0022, B:22:0x0076, B:26:0x008b, B:28:0x0093, B:36:0x00c9, B:18:0x005e, B:21:0x006e), top: B:44:0x000a }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00c3 -> B:22:0x0076). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 216
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05721.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @PublishedApi
    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05721(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@"}, d2 = {"E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {344, 345, 345}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    static final class C05731<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<E> $this_mapIndexed;
        final /* synthetic */ Function3<Integer, E, Continuation<? super R>, Object> $transform;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05731(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super C05731> continuation) {
            super(2, continuation);
            this.$this_mapIndexed = receiveChannel;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05731 c05731 = new C05731(this.$this_mapIndexed, this.$transform, continuation);
            c05731.L$0 = obj;
            return c05731;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C05731) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0067 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00a7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ac  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a8 -> B:14:0x0059). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L4a
                if (r1 == r4) goto L3b
                if (r1 == r3) goto L28
                if (r1 != r2) goto L20
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r12)
                r12 = r6
                goto L58
            L20:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L28:
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$2
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                java.lang.Object r6 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r12)
                r8 = r11
                goto L96
            L3b:
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r12)
                r7 = r11
                goto L6c
            L4a:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
                r1 = 0
                kotlinx.coroutines.channels.ReceiveChannel<E> r5 = r11.$this_mapIndexed
                kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            L58:
                r6 = r11
            L59:
                r6.L$0 = r12
                r6.L$1 = r5
                r6.I$0 = r1
                r6.label = r4
                java.lang.Object r7 = r5.hasNext(r6)
                if (r7 != r0) goto L68
                return r0
            L68:
                r10 = r6
                r6 = r12
                r12 = r7
                r7 = r10
            L6c:
                java.lang.Boolean r12 = (java.lang.Boolean) r12
                boolean r12 = r12.booleanValue()
                if (r12 == 0) goto Lac
                java.lang.Object r12 = r5.next()
                kotlin.jvm.functions.Function3<java.lang.Integer, E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r8 = r7.$transform
                int r9 = r1 + 1
                java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
                r7.L$0 = r6
                r7.L$1 = r5
                r7.L$2 = r6
                r7.I$0 = r9
                r7.label = r3
                java.lang.Object r12 = r8.invoke(r1, r12, r7)
                if (r12 != r0) goto L91
                return r0
            L91:
                r8 = r7
                r1 = r9
                r7 = r6
                r6 = r5
                r5 = r7
            L96:
                r8.L$0 = r7
                r8.L$1 = r6
                r9 = 0
                r8.L$2 = r9
                r8.I$0 = r1
                r8.label = r2
                java.lang.Object r12 = r5.send(r12, r8)
                if (r12 != r0) goto La8
                return r0
            La8:
                r5 = r6
                r12 = r7
                r6 = r8
                goto L59
            Lac:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05731.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @PublishedApi
    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05731(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u008a@"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {370, 371}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0"})
    static final class C05841 extends SuspendLambda implements Function2 {
        final /* synthetic */ ReceiveChannel $this_withIndex;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05841(ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$this_withIndex = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05841 c05841 = new C05841(this.$this_withIndex, continuation);
            c05841.L$0 = obj;
            return c05841;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((C05841) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0051 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0078 -> B:12:0x0043). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L34
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                int r1 = r10.I$0
                java.lang.Object r4 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r11)
                r11 = r5
                goto L42
            L1d:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L25:
                int r1 = r10.I$0
                java.lang.Object r4 = r10.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r11)
                r6 = r10
                goto L56
            L34:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
                r1 = 0
                kotlinx.coroutines.channels.ReceiveChannel r4 = r10.$this_withIndex
                kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
            L42:
                r5 = r10
            L43:
                r5.L$0 = r11
                r5.L$1 = r4
                r5.I$0 = r1
                r5.label = r3
                java.lang.Object r6 = r4.hasNext(r5)
                if (r6 != r0) goto L52
                return r0
            L52:
                r9 = r5
                r5 = r11
                r11 = r6
                r6 = r9
            L56:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto L7c
                java.lang.Object r11 = r4.next()
                kotlin.collections.IndexedValue r7 = new kotlin.collections.IndexedValue
                int r8 = r1 + 1
                r7.<init>(r1, r11)
                r6.L$0 = r5
                r6.L$1 = r4
                r6.I$0 = r8
                r6.label = r2
                java.lang.Object r11 = r5.send(r7, r6)
                if (r11 != r0) goto L78
                return r0
            L78:
                r11 = r5
                r5 = r6
                r1 = r8
                goto L43
            L7c:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05841.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return withIndex(receiveChannel, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel withIndex(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05841(receiveChannel, null), 6, null);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u008a@"}, d2 = {"E", "it", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C05541 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        C05541(Continuation<? super C05541> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05541 c05541 = new C05541(continuation);
            c05541.L$0 = obj;
            return c05541;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(Object obj, Continuation continuation) {
            return ((C05541) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.L$0;
        }
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@"}, d2 = {"E", "K", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {387, 388, 390}, m = "invokeSuspend", n = {"$this$produce", "keys", "$this$produce", "keys", "e", "$this$produce", "keys", "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
    static final class C05551<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<E, Continuation<? super K>, Object> $selector;
        final /* synthetic */ ReceiveChannel<E> $this_distinctBy;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05551(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super C05551> continuation) {
            super(2, continuation);
            this.$this_distinctBy = receiveChannel;
            this.$selector = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05551 c05551 = new C05551(this.$this_distinctBy, this.$selector, continuation);
            c05551.L$0 = obj;
            return c05551;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((C05551) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0082 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0092  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00d2  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d8  */
        /* JADX WARN: Type inference failed for: r10v2 */
        /* JADX WARN: Type inference failed for: r5v2 */
        /* JADX WARN: Type inference failed for: r5v4 */
        /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v9 */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v13 */
        /* JADX WARN: Type inference failed for: r6v19 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v4 */
        /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Type inference failed for: r6v9, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object, java.util.HashSet] */
        /* JADX WARN: Type inference failed for: r7v1 */
        /* JADX WARN: Type inference failed for: r7v11 */
        /* JADX WARN: Type inference failed for: r7v14 */
        /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v3 */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r7v7 */
        /* JADX WARN: Type inference failed for: r7v8 */
        /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c5 -> B:29:0x00cb). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d2 -> B:13:0x0071). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 219
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05551.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @PublishedApi
    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new C05551(receiveChannel, function2, null), 6, null);
    }

    @PublishedApi
    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object any(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L4c
            goto L47
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch: java.lang.Throwable -> L4c
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L4c
            r0.label = r3     // Catch: java.lang.Throwable -> L4c
            java.lang.Object r5 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L4c
            if (r5 != r1) goto L47
            return r1
        L47:
            r0 = 0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            return r5
        L4c:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L4e
        L4e:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0069 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:26:0x0061, B:28:0x0069, B:29:0x0073), top: B:42:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:26:0x0061, B:28:0x0069, B:29:0x0073), top: B:42:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005d -> B:25:0x0060). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object count(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05531
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L35
            goto L60
        L35:
            r7 = move-exception
            goto L85
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$IntRef r8 = new kotlin.jvm.internal.Ref$IntRef
            r8.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch: java.lang.Throwable -> L82
            r4 = r8
            r8 = r7
            r7 = r2
        L4e:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L7f
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L7f
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L7f
            r0.label = r3     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r2 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L7f
            if (r2 != r1) goto L5d
            return r1
        L5d:
            r6 = r2
            r2 = r8
            r8 = r6
        L60:
            r5 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L35
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L73
            r7.next()     // Catch: java.lang.Throwable -> L35
            int r8 = r4.element     // Catch: java.lang.Throwable -> L35
            int r8 = r8 + r3
            r4.element = r8     // Catch: java.lang.Throwable -> L35
            r8 = r2
            goto L4e
        L73:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L35
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r5)
            int r7 = r4.element
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        L7f:
            r7 = move-exception
            r2 = r8
            goto L85
        L82:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L85:
            throw r7     // Catch: java.lang.Throwable -> L86
        L86:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #3 {all -> 0x00bf, blocks: (B:40:0x00a4, B:42:0x00ac, B:36:0x008f, B:26:0x0063), top: B:61:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a0 -> B:15:0x003d). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object maxWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #3 {all -> 0x00bf, blocks: (B:40:0x00a4, B:42:0x00ac, B:36:0x008f, B:26:0x0063), top: B:61:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a0 -> B:15:0x003d). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object minWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ java.lang.Object none(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05761
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05761) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L5a
            goto L47
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch: java.lang.Throwable -> L5a
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L5a
            r0.label = r3     // Catch: java.lang.Throwable -> L5a
            java.lang.Object r5 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L5a
            if (r5 != r1) goto L47
            return r1
        L47:
            r0 = 0
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L5a
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L5a
            if (r5 != 0) goto L51
            goto L52
        L51:
            r3 = 0
        L52:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch: java.lang.Throwable -> L5a
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            return r5
        L5a:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L5c
        L5c:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00018\u0000H\u008a@"}, d2 = {"", "E", "it", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C05771 extends SuspendLambda implements Function2 {
        final /* synthetic */ ReceiveChannel $this_requireNoNulls;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05771(ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$this_requireNoNulls = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05771 c05771 = new C05771(this.$this_requireNoNulls, continuation);
            c05771.L$0 = obj;
            return c05771;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(Object obj, Continuation continuation) {
            return ((C05771) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            if (obj2 != null) {
                return obj2;
            }
            throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + '.');
        }
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [V] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Deprecated.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00020\u0003H\u008a@"}, d2 = {"E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {487, 469, 471}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
    static final class C05862<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReceiveChannel<R> $other;
        final /* synthetic */ ReceiveChannel<E> $this_zip;
        final /* synthetic */ Function2<E, R, V> $transform;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05862(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super C05862> continuation) {
            super(2, continuation);
            this.$other = receiveChannel;
            this.$this_zip = receiveChannel2;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05862 c05862 = new C05862(this.$other, this.$this_zip, this.$transform, continuation);
            c05862.L$0 = obj;
            return c05862;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
            return ((C05862) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:56:0x0093, code lost:
        
            r14 = r0;
            r0 = r1;
            r1 = r6;
            r6 = r8;
            r7 = r9;
            r8 = r10;
            r9 = r11;
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b9 A[Catch: all -> 0x0056, TRY_LEAVE, TryCatch #3 {all -> 0x0056, blocks: (B:26:0x00b1, B:28:0x00b9, B:40:0x0109, B:13:0x004a), top: B:54:0x004a }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00e1 A[Catch: all -> 0x0106, TRY_LEAVE, TryCatch #2 {all -> 0x0106, blocks: (B:32:0x00d9, B:34:0x00e1), top: B:52:0x00d9 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00fe  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0109 A[Catch: all -> 0x0056, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0056, blocks: (B:26:0x00b1, B:28:0x00b9, B:40:0x0109, B:13:0x004a), top: B:54:0x004a }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C05862.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @PublishedApi
    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext coroutineContext, Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new C05862(receiveChannel2, receiveChannel, function2, null), 6, null);
    }

    @PublishedApi
    public static final Function1<Throwable, Unit> consumes(final ReceiveChannel<?> receiveChannel) {
        return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.consumes.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                ChannelsKt.cancelConsumed(receiveChannel, th);
            }
        };
    }
}
