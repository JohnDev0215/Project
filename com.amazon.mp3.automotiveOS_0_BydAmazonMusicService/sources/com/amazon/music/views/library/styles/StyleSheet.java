package com.amazon.music.views.library.styles;

import android.content.Context;
import android.content.res.Resources;
import android.util.Range;
import android.view.WindowManager;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.DividerStyleKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ImageSizeKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.PopularityWidgetStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.music.views.library.styles.keys.TextSizeKeys;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.DividerStyle;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.styles.LinkStyle;
import com.amazon.ui.foundations.styles.PopularityWidgetStyle;
import com.amazon.ui.foundations.utils.ColorParser;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.amazon.ui.foundations.utils.SpacerParser;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.amazon.ui.foundations.views.Divider;
import com.amazon.ui.foundations.views.Link;
import com.amazon.ui.foundations.views.PopularityWidget$StyleBuilder;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import rx.Completable;
import rx.subjects.ReplaySubject;

/* JADX INFO: compiled from: StyleSheet.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 Õ\u00022\u00020\u0001:\u0002Õ\u0002B\u0013\u0012\b\u0010·\u0001\u001a\u00030¶\u0001¢\u0006\u0006\bÓ\u0002\u0010Ô\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\u000b\u001a\u00020\nH\u0004J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0004J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0004J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0004J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0004J \u0010\u001a\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u000eH\u0004J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u000eH\u0004J\u0018\u0010#\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!H\u0004J\u0018\u0010(\u001a\u00020\n2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&H\u0004J\u001c\u0010)\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010%\u001a\u0004\u0018\u00010$H\u0004J\u0018\u0010.\u001a\u00020\n2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,H\u0004J\u0018\u00103\u001a\u00020\n2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u000201H\u0004J\u0018\u00107\u001a\u00020\n2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020!H\u0004J\u0018\u0010;\u001a\u00020\n2\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020&H\u0004J\u001c\u0010<\u001a\u00020\n2\b\u00105\u001a\u0004\u0018\u0001042\b\u00109\u001a\u0004\u0018\u000108H\u0004J\u0018\u0010A\u001a\u00020\n2\u0006\u0010>\u001a\u00020=2\u0006\u0010@\u001a\u00020?H\u0004J\u0018\u0010E\u001a\u00020\n2\u0006\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020\u000eH\u0004J\u001e\u0010J\u001a\u00020\n2\u0006\u0010G\u001a\u00020F2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00130HH\u0004J\u0018\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020\u000eH\u0004J\u0018\u0010O\u001a\u00020\n2\u0006\u0010G\u001a\u00020F2\u0006\u0010L\u001a\u00020KH\u0004J\u0018\u0010T\u001a\u00020\n2\u0006\u0010Q\u001a\u00020P2\u0006\u0010S\u001a\u00020RH\u0004J\u0018\u0010W\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010V\u001a\u00020UH\u0004J\u0018\u0010Y\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u0013H\u0004J\u0018\u0010Z\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u0013H\u0004J\u0017\u0010[\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b[\u0010\\J\u0017\u0010]\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b]\u0010\\J \u0010_\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010^\u001a\u00020\u0006H\u0004J\u001e\u0010a\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00130HH\u0004J\u0018\u0010e\u001a\u00020\n2\u0006\u0010c\u001a\u00020b2\u0006\u0010d\u001a\u00020!H\u0004J\u0018\u0010i\u001a\u00020\n2\u0006\u0010g\u001a\u00020f2\u0006\u0010h\u001a\u00020&H\u0004J\u001c\u0010j\u001a\u00020\n2\b\u0010c\u001a\u0004\u0018\u00010b2\b\u0010g\u001a\u0004\u0018\u00010fH\u0004J\u0018\u0010o\u001a\u00020\n2\u0006\u0010l\u001a\u00020k2\u0006\u0010n\u001a\u00020mH\u0004J\u0010\u0010p\u001a\u00020\n2\u0006\u0010l\u001a\u00020kH\u0004J\u0018\u0010u\u001a\u00020\n2\u0006\u0010r\u001a\u00020q2\u0006\u0010t\u001a\u00020sH\u0004J\u0010\u0010v\u001a\u00020\n2\u0006\u0010r\u001a\u00020qH\u0004J\u0018\u0010z\u001a\u00020\n2\u0006\u0010x\u001a\u00020w2\u0006\u0010y\u001a\u00020!H\u0004J\u0018\u0010~\u001a\u00020\n2\u0006\u0010|\u001a\u00020{2\u0006\u0010}\u001a\u00020&H\u0004J\u001c\u0010\u007f\u001a\u00020\n2\b\u0010x\u001a\u0004\u0018\u00010w2\b\u0010|\u001a\u0004\u0018\u00010{H\u0004J\u001d\u0010\u0084\u0001\u001a\u00020\n2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u0001H\u0004J\u0013\u0010\u0085\u0001\u001a\u00020\n2\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001H\u0004J\u001a\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u001b\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000e2\u0007\u0010\u0088\u0001\u001a\u00020\u0016¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u001a\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\"\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016¢\u0006\u0006\b\u0086\u0001\u0010\u008d\u0001J\"\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\f¢\u0006\u0006\b\u0086\u0001\u0010\u008e\u0001J,\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u00112\u0007\u0010\u008f\u0001\u001a\u00020\u00112\u0007\u0010\u0090\u0001\u001a\u00020\f¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J-\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010\u0093\u0001\u001a\u00020\u00132\u0007\u0010\u0094\u0001\u001a\u00020\u00132\u0007\u0010\u0095\u0001\u001a\u00020\u000e¢\u0006\u0006\b\u0091\u0001\u0010\u0096\u0001J\u0019\u0010\u0097\u0001\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0004J\u001a\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001a\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0006\b\u009a\u0001\u0010\u0099\u0001J\u0011\u0010\u009b\u0001\u001a\u0004\u0018\u00010!2\u0006\u0010 \u001a\u00020\u001fJ\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u00010&2\u0006\u0010%\u001a\u00020$J\u001e\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009d\u00012\b\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010%\u001a\u0004\u0018\u00010$J\u0011\u0010\u009f\u0001\u001a\u0004\u0018\u00010,2\u0006\u0010+\u001a\u00020*J\u0011\u0010 \u0001\u001a\u0004\u0018\u0001012\u0006\u00100\u001a\u00020/J \u0010¤\u0001\u001a\t\u0012\u0004\u0012\u00020\u00130£\u00012\u0007\u0010¡\u0001\u001a\u00020/2\u0007\u0010¢\u0001\u001a\u00020/J\u0011\u0010¥\u0001\u001a\u0004\u0018\u00010!2\u0006\u00105\u001a\u000204J\u0011\u0010¦\u0001\u001a\u0004\u0018\u00010&2\u0006\u00109\u001a\u000208J\u001e\u0010§\u0001\u001a\u0005\u0018\u00010\u009d\u00012\b\u00105\u001a\u0004\u0018\u0001042\b\u00109\u001a\u0004\u0018\u000108J\u0011\u0010¨\u0001\u001a\u0004\u0018\u00010?2\u0006\u0010>\u001a\u00020=J\u001a\u0010©\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010C\u001a\u00020B¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u0015\u0010\u00ad\u0001\u001a\u0005\u0018\u00010¬\u00012\u0007\u0010«\u0001\u001a\u00020\u0013H&J\u0017\u0010®\u0001\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010H2\u0006\u0010G\u001a\u00020FJ\u0012\u0010¯\u0001\u001a\u00020\u00132\u0007\u0010«\u0001\u001a\u00020\u0013H&J\u001a\u0010°\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010L\u001a\u00020\u0016¢\u0006\u0006\b°\u0001\u0010±\u0001J\u001a\u0010°\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010L\u001a\u00020K¢\u0006\u0006\b°\u0001\u0010²\u0001J\u0011\u0010³\u0001\u001a\u0004\u0018\u00010\u00162\u0006\u0010G\u001a\u00020FJ\u0011\u0010´\u0001\u001a\u0004\u0018\u00010R2\u0006\u0010Q\u001a\u00020PJ\u0011\u0010µ\u0001\u001a\u0004\u0018\u00010U2\u0006\u0010\u0005\u001a\u00020\u0004J\u0013\u0010µ\u0001\u001a\u0004\u0018\u00010U2\b\u0010·\u0001\u001a\u00030¶\u0001J\u000b\u0010µ\u0001\u001a\u0004\u0018\u00010UH&J\u0019\u0010¸\u0001\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010H2\u0006\u0010\u0005\u001a\u00020\u0004H\u0004J\u0011\u0010¹\u0001\u001a\u0004\u0018\u00010!2\u0006\u0010c\u001a\u00020bJ\u0011\u0010º\u0001\u001a\u0004\u0018\u00010&2\u0006\u0010g\u001a\u00020fJ\u001e\u0010»\u0001\u001a\u0005\u0018\u00010\u009d\u00012\b\u0010c\u001a\u0004\u0018\u00010b2\b\u0010g\u001a\u0004\u0018\u00010fJ\u0011\u0010¼\u0001\u001a\u0004\u0018\u00010m2\u0006\u0010l\u001a\u00020kJ\u0011\u0010½\u0001\u001a\u0004\u0018\u00010s2\u0006\u0010r\u001a\u00020qJ\u0012\u0010¿\u0001\u001a\u0005\u0018\u00010¾\u00012\u0006\u0010r\u001a\u00020qJ\u0011\u0010À\u0001\u001a\u0004\u0018\u00010!2\u0006\u0010x\u001a\u00020wJ\u0011\u0010Á\u0001\u001a\u0004\u0018\u00010&2\u0006\u0010|\u001a\u00020{J\u001e\u0010Â\u0001\u001a\u0005\u0018\u00010\u009d\u00012\b\u0010x\u001a\u0004\u0018\u00010w2\b\u0010|\u001a\u0004\u0018\u00010{J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010·\u0001\u001a\u00030¶\u0001J\u0014\u0010Ã\u0001\u001a\u0005\u0018\u00010\u0082\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001J\u0007\u0010Ä\u0001\u001a\u00020\u0013J\u0007\u0010Å\u0001\u001a\u00020\u0013J\u0007\u0010Æ\u0001\u001a\u00020\u0013J!\u0010É\u0001\u001a\u0004\u0018\u00010\u00162\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00162\t\u0010È\u0001\u001a\u0004\u0018\u00010\u0016H\u0004J\u0007\u0010Ê\u0001\u001a\u00020\u0004J\u0011\u0010Ê\u0001\u001a\u00020\u00042\b\u0010·\u0001\u001a\u00030¶\u0001J\u0007\u0010Ë\u0001\u001a\u00020\u0004J\u0011\u0010Ë\u0001\u001a\u00020\u00042\b\u0010·\u0001\u001a\u00030¶\u0001J\u0007\u0010Ì\u0001\u001a\u00020\u0004J\u0007\u0010Í\u0001\u001a\u00020\u0004J\u0012\u0010Ï\u0001\u001a\u00030Î\u00012\b\u0010·\u0001\u001a\u00030¶\u0001J\u0012\u0010Ð\u0001\u001a\u00020\u00042\u0007\u0010«\u0001\u001a\u00020\u0013H&J\u0011\u0010Ñ\u0001\u001a\u00020\u00132\b\u0010·\u0001\u001a\u00030¶\u0001R,\u0010Ô\u0001\u001a\u00030Ò\u00012\b\u0010Ó\u0001\u001a\u00030Ò\u00018\u0006@BX\u0086\u000e¢\u0006\u0010\n\u0006\bÔ\u0001\u0010Õ\u0001\u001a\u0006\bÔ\u0001\u0010Ö\u0001R\"\u0010Ù\u0001\u001a\r Ø\u0001*\u0005\u0018\u00010×\u00010×\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÙ\u0001\u0010Ú\u0001R\u0018\u0010Ü\u0001\u001a\u00030Û\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÜ\u0001\u0010Ý\u0001R(\u0010ä\u0001\u001a\n\u0012\u0005\u0012\u00030ß\u00010Þ\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bà\u0001\u0010á\u0001\u001a\u0006\bâ\u0001\u0010ã\u0001R-\u0010é\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bæ\u0001\u0010á\u0001\u001a\u0006\bç\u0001\u0010è\u0001R-\u0010ì\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000e0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bê\u0001\u0010á\u0001\u001a\u0006\bë\u0001\u0010è\u0001R-\u0010ï\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bí\u0001\u0010á\u0001\u001a\u0006\bî\u0001\u0010è\u0001R-\u0010ò\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020!0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bð\u0001\u0010á\u0001\u001a\u0006\bñ\u0001\u0010è\u0001R-\u0010õ\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bó\u0001\u0010á\u0001\u001a\u0006\bô\u0001\u0010è\u0001R.\u0010ø\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u009d\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bö\u0001\u0010á\u0001\u001a\u0006\b÷\u0001\u0010è\u0001R-\u0010û\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020,0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bù\u0001\u0010á\u0001\u001a\u0006\bú\u0001\u0010è\u0001R-\u0010þ\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bü\u0001\u0010á\u0001\u001a\u0006\bý\u0001\u0010è\u0001R-\u0010\u0081\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020!0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÿ\u0001\u0010á\u0001\u001a\u0006\b\u0080\u0002\u0010è\u0001R-\u0010\u0084\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0082\u0002\u0010á\u0001\u001a\u0006\b\u0083\u0002\u0010è\u0001R.\u0010\u0087\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u009d\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0085\u0002\u0010á\u0001\u001a\u0006\b\u0086\u0002\u0010è\u0001R-\u0010\u008a\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020?0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0088\u0002\u0010á\u0001\u001a\u0006\b\u0089\u0002\u0010è\u0001R-\u0010\u008d\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000e0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u008b\u0002\u0010á\u0001\u001a\u0006\b\u008c\u0002\u0010è\u0001R-\u0010\u0090\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u008e\u0002\u0010á\u0001\u001a\u0006\b\u008f\u0002\u0010è\u0001R-\u0010\u0093\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0091\u0002\u0010á\u0001\u001a\u0006\b\u0092\u0002\u0010è\u0001R3\u0010\u0096\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130H0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0094\u0002\u0010á\u0001\u001a\u0006\b\u0095\u0002\u0010è\u0001R-\u0010\u0099\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020R0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0097\u0002\u0010á\u0001\u001a\u0006\b\u0098\u0002\u0010è\u0001R.\u0010\u009d\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u009a\u00020å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u009b\u0002\u0010á\u0001\u001a\u0006\b\u009c\u0002\u0010è\u0001R-\u0010 \u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u009e\u0002\u0010á\u0001\u001a\u0006\b\u009f\u0002\u0010è\u0001R-\u0010£\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b¡\u0002\u0010á\u0001\u001a\u0006\b¢\u0002\u0010è\u0001R-\u0010¦\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020U0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b¤\u0002\u0010á\u0001\u001a\u0006\b¥\u0002\u0010è\u0001R-\u0010©\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b§\u0002\u0010á\u0001\u001a\u0006\b¨\u0002\u0010è\u0001R3\u0010¬\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130H0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bª\u0002\u0010á\u0001\u001a\u0006\b«\u0002\u0010è\u0001R-\u0010¯\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020!0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u00ad\u0002\u0010á\u0001\u001a\u0006\b®\u0002\u0010è\u0001R-\u0010²\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b°\u0002\u0010á\u0001\u001a\u0006\b±\u0002\u0010è\u0001R.\u0010µ\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u009d\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b³\u0002\u0010á\u0001\u001a\u0006\b´\u0002\u0010è\u0001R-\u0010¸\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020m0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b¶\u0002\u0010á\u0001\u001a\u0006\b·\u0002\u0010è\u0001R.\u0010¼\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030¹\u00020å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bº\u0002\u0010á\u0001\u001a\u0006\b»\u0002\u0010è\u0001R-\u0010¿\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020s0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b½\u0002\u0010á\u0001\u001a\u0006\b¾\u0002\u0010è\u0001R.\u0010Â\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030¾\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÀ\u0002\u0010á\u0001\u001a\u0006\bÁ\u0002\u0010è\u0001R-\u0010Å\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020!0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÃ\u0002\u0010á\u0001\u001a\u0006\bÄ\u0002\u0010è\u0001R-\u0010È\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0002\u0010á\u0001\u001a\u0006\bÇ\u0002\u0010è\u0001R.\u0010Ë\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u009d\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÉ\u0002\u0010á\u0001\u001a\u0006\bÊ\u0002\u0010è\u0001R.\u0010Î\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u0082\u00010å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÌ\u0002\u0010á\u0001\u001a\u0006\bÍ\u0002\u0010è\u0001R.\u0010Ò\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030Ï\u00020å\u00018DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\bÐ\u0002\u0010á\u0001\u001a\u0006\bÑ\u0002\u0010è\u0001¨\u0006Ö\u0002"}, d2 = {"Lcom/amazon/music/views/library/styles/StyleSheet;", "", "Lcom/amazon/music/views/library/styles/keys/GridStyleKey;", "gridStyleKey", "Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;", "screenSizeKey", "Lcom/amazon/ui/foundations/styles/GridStyle;", "getGridStyle", "Lrx/Completable;", "initCompletable", "", "onInitCompleted", "Lcom/amazon/music/views/library/styles/keys/AlphaKey;", "alphaKey", "", "alphaPercentValue", "addAlphaPercent", "Lcom/amazon/music/views/library/styles/keys/ColorKey;", "colorKey", "", "colorValue", "addColor", "", "colorWithAlphaKey", "color", "alpha", "addColorWithAlpha", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "spacerKey", "pixelValue", "addSpacerInPixels", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconSizeKey", "Lcom/amazon/ui/foundations/styles/ButtonSize;", "iconSize", "addIconSize", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "iconStyleKey", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "iconStyle", "addIconStyle", "addBaseIconBuilder", "Lcom/amazon/music/views/library/styles/keys/FontSizeKey;", "fontSizeKey", "Lcom/amazon/ui/foundations/styles/FontSize;", "fontSizeValue", "addFontSize", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "fontStyleKey", "Lcom/amazon/ui/foundations/styles/FontStyle;", "fontStyleValue", "addFontStyle", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "buttonSizeValue", "addButtonSize", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "buttonStyleKey", "buttonStyleValue", "addButtonStyle", "addBaseButtonBuilder", "Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;", "gradientStyleKey", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "gradientStyle", "addGradientStyle", "Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;", "blurStyleKey", "blurStyleValue", "addBlurStyle", "Lcom/amazon/music/views/library/styles/keys/ImageSizeKey;", "imageSizeKey", "Landroid/util/Range;", "imageSizeRange", "addImageSizeRange", "Lcom/amazon/music/views/library/styles/keys/CornerSizeKey;", "cornerSizeKey", "cornerSize", "addCornerSize", "addImageCornerKey", "Lcom/amazon/music/views/library/styles/keys/ImageStyleKey;", "imageStyleKey", "Lcom/amazon/ui/foundations/styles/ImageStyle;", "imageStyle", "addImageStyle", "Lcom/amazon/ui/foundations/styles/GridSize;", "gridSize", "addGridSize", "topMargin", "addFeaturedPlayTopMargin", "addFeaturedPlayLeftAlignedTopMargin", "getFeaturedPlayTopMargin", "(Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;)Ljava/lang/Integer;", "getFeaturedPlayLeftAlignedTopMargin", "gridStyle", "addGridStyle", "screenSizeRange", "addScreenSizeRange", "Lcom/amazon/music/views/library/styles/keys/TagSizeKey;", "tagSizeKey", "tagSizeValue", "addTagSize", "Lcom/amazon/music/views/library/styles/keys/TagStyleKey;", "tagStyleKey", "tagStyleValue", "addTagStyle", "addTagBuilder", "Lcom/amazon/music/views/library/styles/keys/PopularityWidgetStyleKey;", "popularityWidgetStyleKey", "Lcom/amazon/ui/foundations/styles/PopularityWidgetStyle;", "popularityWidgetStyle", "addPopularityWidgetStyle", "addPopularityWidgetBuilder", "Lcom/amazon/music/views/library/styles/keys/DividerStyleKey;", "dividerStyleKey", "Lcom/amazon/ui/foundations/styles/DividerStyle;", "dividerStyle", "addDividerStyle", "addDividerBuilder", "Lcom/amazon/music/views/library/styles/keys/IndicatorSizeKey;", "indicatorSizeKey", "indicatorSize", "addIndicatorSize", "Lcom/amazon/music/views/library/styles/keys/IndicatorStyleKey;", "indicatorStyleKey", "indicatorStyle", "addIndicatorStyle", "addIndicatorBuilder", "Lcom/amazon/music/views/library/styles/LinkStyleKey;", "linkStyleKey", "Lcom/amazon/ui/foundations/styles/LinkStyle;", "linkStyle", "addLinkStyle", "addLinkBuilder", "getColor", "(Lcom/amazon/music/views/library/styles/keys/ColorKey;)Ljava/lang/Integer;", Splash.PARAMS_LOCALE, "getTextSize", "(Ljava/lang/String;)Ljava/lang/Float;", "getAlpha", "(Lcom/amazon/music/views/library/styles/keys/AlphaKey;)Ljava/lang/Float;", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Integer;", "(Lcom/amazon/music/views/library/styles/keys/ColorKey;Lcom/amazon/music/views/library/styles/keys/AlphaKey;)Ljava/lang/Integer;", "overlayColorKey", "overlayAlphaKey", "getCombinationColor", "(Lcom/amazon/music/views/library/styles/keys/ColorKey;Lcom/amazon/music/views/library/styles/keys/ColorKey;Lcom/amazon/music/views/library/styles/keys/AlphaKey;)Ljava/lang/Integer;", "colorInt", "overlayColorInt", "overlayAlphaPercent", "(IIF)Ljava/lang/Integer;", "getColorKeyWithAlpha", "getSpacerInDp", "(Lcom/amazon/music/views/library/styles/keys/SpacerKey;)Ljava/lang/Integer;", "getSpacerInPixels", "getIconSize", "getIconStyle", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getIconBuilder", "getFontSize", "getFontStyle", "minFontStyleKey", "maxFontStyleKey", "", "getTextSizeListInRange", "getButtonSize", "getButtonStyle", "getButtonBuilder", "getGradientStyle", "getBlurStyle", "(Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;)Ljava/lang/Float;", "size", "Lcom/amazon/ui/foundations/styles/ImageSize;", "getImageSize", "getImageSizeRange", "getImageCornerSize", "getCornerSize", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Lcom/amazon/music/views/library/styles/keys/CornerSizeKey;)Ljava/lang/Integer;", "getImageCornerKey", "getImageStyle", "getGridSize", "Landroid/content/Context;", "context", "getScreenSizeRange", "getTagSize", "getTagStyle", "getTagBuilder", "getPopularityWidgetStyle", "getDividerStyle", "Lcom/amazon/ui/foundations/views/Divider$StyleBuilder;", "getDividerBuilder", "getIndicatorSize", "getIndicatorStyle", "getIndicatorBuilder", "getLinkStyle", "getMediumListItemHeight", "getVisualRowItemHeight", "getLargeListItemHeight", "sizeKey", "styleKey", "getStyleBuilderKey", "getWidthScreenSizeKey", "getHeightScreenSizeKey", "getFullWidthScreenSizeKey", "getFullHeightScreenSizeKey", "Lcom/amazon/music/views/library/styles/keys/LandscapeType;", "getLandscapeType", "getScreenSizeKey", "horizontalInsetWidth", "", "<set-?>", "isInitCompleted", "Z", "()Z", "Landroid/content/res/Resources;", "kotlin.jvm.PlatformType", "resources", "Landroid/content/res/Resources;", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "Lrx/subjects/ReplaySubject;", "Ljava/lang/Void;", "initializedSubject$delegate", "Lkotlin/Lazy;", "getInitializedSubject", "()Lrx/subjects/ReplaySubject;", "initializedSubject", "Ljava/util/HashMap;", "colorMap$delegate", "getColorMap", "()Ljava/util/HashMap;", "colorMap", "alphaMap$delegate", "getAlphaMap", "alphaMap", "spacerMap$delegate", "getSpacerMap", "spacerMap", "iconSizeMap$delegate", "getIconSizeMap", "iconSizeMap", "iconStyleMap$delegate", "getIconStyleMap", "iconStyleMap", "iconBuilderMap$delegate", "getIconBuilderMap", "iconBuilderMap", "fontSizeMap$delegate", "getFontSizeMap", "fontSizeMap", "fontStyleMap$delegate", "getFontStyleMap", "fontStyleMap", "buttonSizeMap$delegate", "getButtonSizeMap", "buttonSizeMap", "buttonStyleMap$delegate", "getButtonStyleMap", "buttonStyleMap", "buttonBuilderMap$delegate", "getButtonBuilderMap", "buttonBuilderMap", "gradientStyleMap$delegate", "getGradientStyleMap", "gradientStyleMap", "blurStyleMap$delegate", "getBlurStyleMap", "blurStyleMap", "cornerSizeMap$delegate", "getCornerSizeMap", "cornerSizeMap", "imageCornerMap$delegate", "getImageCornerMap", "imageCornerMap", "imageSizeMap$delegate", "getImageSizeMap", "imageSizeMap", "imageStyleMap$delegate", "getImageStyleMap", "imageStyleMap", "Lcom/amazon/ui/foundations/views/BaseImage$StyleBuilder;", "imageBuilderMap$delegate", "getImageBuilderMap", "imageBuilderMap", "topMarginFeaturedPlay$delegate", "getTopMarginFeaturedPlay", "topMarginFeaturedPlay", "topMarginLeftAlignedFeaturedPlay$delegate", "getTopMarginLeftAlignedFeaturedPlay", "topMarginLeftAlignedFeaturedPlay", "gridSizeMap$delegate", "getGridSizeMap", "gridSizeMap", "gridStyleMap$delegate", "getGridStyleMap", "gridStyleMap", "screenSizeMap$delegate", "getScreenSizeMap", "screenSizeMap", "tagSizeMap$delegate", "getTagSizeMap", "tagSizeMap", "tagStyleMap$delegate", "getTagStyleMap", "tagStyleMap", "tagBuilderMap$delegate", "getTagBuilderMap", "tagBuilderMap", "popularityWidgetStyleMap$delegate", "getPopularityWidgetStyleMap", "popularityWidgetStyleMap", "Lcom/amazon/ui/foundations/views/PopularityWidget$StyleBuilder;", "popularityWidgetBuilderMap$delegate", "getPopularityWidgetBuilderMap", "popularityWidgetBuilderMap", "dividerStyleMap$delegate", "getDividerStyleMap", "dividerStyleMap", "dividerBuilderMap$delegate", "getDividerBuilderMap", "dividerBuilderMap", "indicatorSizeMap$delegate", "getIndicatorSizeMap", "indicatorSizeMap", "indicatorStyleMap$delegate", "getIndicatorStyleMap", "indicatorStyleMap", "indicatorBuilderMap$delegate", "getIndicatorBuilderMap", "indicatorBuilderMap", "linkStyleMap$delegate", "getLinkStyleMap", "linkStyleMap", "Lcom/amazon/ui/foundations/views/Link$LinkStringBuilder;", "linkBuilderMap$delegate", "getLinkBuilderMap", "linkBuilderMap", "<init>", "(Landroid/content/Context;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class StyleSheet {

    /* JADX INFO: renamed from: alphaMap$delegate, reason: from kotlin metadata */
    private final Lazy alphaMap;

    /* JADX INFO: renamed from: blurStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy blurStyleMap;

    /* JADX INFO: renamed from: buttonBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy buttonBuilderMap;

    /* JADX INFO: renamed from: buttonSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy buttonSizeMap;

    /* JADX INFO: renamed from: buttonStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy buttonStyleMap;

    /* JADX INFO: renamed from: colorMap$delegate, reason: from kotlin metadata */
    private final Lazy colorMap;

    /* JADX INFO: renamed from: cornerSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy cornerSizeMap;

    /* JADX INFO: renamed from: dividerBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy dividerBuilderMap;

    /* JADX INFO: renamed from: dividerStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy dividerStyleMap;

    /* JADX INFO: renamed from: fontSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy fontSizeMap;

    /* JADX INFO: renamed from: fontStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy fontStyleMap;

    /* JADX INFO: renamed from: gradientStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy gradientStyleMap;

    /* JADX INFO: renamed from: gridSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy gridSizeMap;

    /* JADX INFO: renamed from: gridStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy gridStyleMap;

    /* JADX INFO: renamed from: iconBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy iconBuilderMap;

    /* JADX INFO: renamed from: iconSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy iconSizeMap;

    /* JADX INFO: renamed from: iconStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy iconStyleMap;

    /* JADX INFO: renamed from: imageBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy imageBuilderMap;

    /* JADX INFO: renamed from: imageCornerMap$delegate, reason: from kotlin metadata */
    private final Lazy imageCornerMap;

    /* JADX INFO: renamed from: imageSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy imageSizeMap;

    /* JADX INFO: renamed from: imageStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy imageStyleMap;

    /* JADX INFO: renamed from: indicatorBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy indicatorBuilderMap;

    /* JADX INFO: renamed from: indicatorSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy indicatorSizeMap;

    /* JADX INFO: renamed from: indicatorStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy indicatorStyleMap;

    /* JADX INFO: renamed from: initializedSubject$delegate, reason: from kotlin metadata */
    private final Lazy initializedSubject;
    private boolean isInitCompleted;

    /* JADX INFO: renamed from: linkBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy linkBuilderMap;

    /* JADX INFO: renamed from: linkStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy linkStyleMap;

    /* JADX INFO: renamed from: popularityWidgetBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy popularityWidgetBuilderMap;

    /* JADX INFO: renamed from: popularityWidgetStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy popularityWidgetStyleMap;
    private final Resources resources;

    /* JADX INFO: renamed from: screenSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy screenSizeMap;

    /* JADX INFO: renamed from: spacerMap$delegate, reason: from kotlin metadata */
    private final Lazy spacerMap;

    /* JADX INFO: renamed from: tagBuilderMap$delegate, reason: from kotlin metadata */
    private final Lazy tagBuilderMap;

    /* JADX INFO: renamed from: tagSizeMap$delegate, reason: from kotlin metadata */
    private final Lazy tagSizeMap;

    /* JADX INFO: renamed from: tagStyleMap$delegate, reason: from kotlin metadata */
    private final Lazy tagStyleMap;

    /* JADX INFO: renamed from: topMarginFeaturedPlay$delegate, reason: from kotlin metadata */
    private final Lazy topMarginFeaturedPlay;

    /* JADX INFO: renamed from: topMarginLeftAlignedFeaturedPlay$delegate, reason: from kotlin metadata */
    private final Lazy topMarginLeftAlignedFeaturedPlay;
    private final WindowManager windowManager;

    public abstract GridSize getGridSize();

    public abstract int getImageCornerSize(int size);

    public abstract ImageSize getImageSize(int size);

    public abstract ScreenSizeKey getScreenSizeKey(int size);

    public StyleSheet(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.resources = context.getResources();
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.windowManager = (WindowManager) systemService;
        this.initializedSubject = LazyKt__LazyJVMKt.lazy(new Function0<ReplaySubject<Void>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$initializedSubject$2
            @Override // kotlin.jvm.functions.Function0
            public final ReplaySubject<Void> invoke() {
                return ReplaySubject.create();
            }
        });
        this.colorMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Integer>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$colorMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Integer> invoke() {
                return new HashMap<>();
            }
        });
        this.alphaMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Float>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$alphaMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Float> invoke() {
                return new HashMap<>();
            }
        });
        this.spacerMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Integer>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$spacerMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Integer> invoke() {
                return new HashMap<>();
            }
        });
        this.iconSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$iconSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonSize> invoke() {
                return new HashMap<>();
            }
        });
        this.iconStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$iconStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.iconBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, BaseButton.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$iconBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, BaseButton.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.fontSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, FontSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$fontSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, FontSize> invoke() {
                return new HashMap<>();
            }
        });
        this.fontStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, FontStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$fontStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, FontStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.buttonSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$buttonSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonSize> invoke() {
                return new HashMap<>();
            }
        });
        this.buttonStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$buttonStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.buttonBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, BaseButton.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$buttonBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, BaseButton.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.gradientStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, GradientStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$gradientStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, GradientStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.blurStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Float>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$blurStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Float> invoke() {
                return new HashMap<>();
            }
        });
        this.cornerSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Integer>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$cornerSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Integer> invoke() {
                return new HashMap<>();
            }
        });
        this.imageCornerMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, String>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$imageCornerMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, String> invoke() {
                return new HashMap<>();
            }
        });
        this.imageSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Range<Integer>>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$imageSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Range<Integer>> invoke() {
                return new HashMap<>();
            }
        });
        this.imageStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ImageStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$imageStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ImageStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.imageBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, BaseImage.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$imageBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, BaseImage.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.topMarginFeaturedPlay = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Integer>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$topMarginFeaturedPlay$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Integer> invoke() {
                return new HashMap<>();
            }
        });
        this.topMarginLeftAlignedFeaturedPlay = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Integer>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$topMarginLeftAlignedFeaturedPlay$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Integer> invoke() {
                return new HashMap<>();
            }
        });
        this.gridSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, GridSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$gridSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, GridSize> invoke() {
                return new HashMap<>();
            }
        });
        this.gridStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, GridStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$gridStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, GridStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.screenSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Range<Integer>>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$screenSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Range<Integer>> invoke() {
                return new HashMap<>();
            }
        });
        this.tagSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$tagSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonSize> invoke() {
                return new HashMap<>();
            }
        });
        this.tagStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$tagStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.tagBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, BaseButton.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$tagBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, BaseButton.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.popularityWidgetStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, PopularityWidgetStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$popularityWidgetStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, PopularityWidgetStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.popularityWidgetBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, PopularityWidget$StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$popularityWidgetBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, PopularityWidget$StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.dividerStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, DividerStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$dividerStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, DividerStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.dividerBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Divider.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$dividerBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Divider.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.indicatorSizeMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonSize>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$indicatorSizeMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonSize> invoke() {
                return new HashMap<>();
            }
        });
        this.indicatorStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, ButtonStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$indicatorStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, ButtonStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.indicatorBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, BaseButton.StyleBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$indicatorBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, BaseButton.StyleBuilder> invoke() {
                return new HashMap<>();
            }
        });
        this.linkStyleMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, LinkStyle>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$linkStyleMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, LinkStyle> invoke() {
                return new HashMap<>();
            }
        });
        this.linkBuilderMap = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Link.LinkStringBuilder>>() { // from class: com.amazon.music.views.library.styles.StyleSheet$linkBuilderMap$2
            @Override // kotlin.jvm.functions.Function0
            public final HashMap<String, Link.LinkStringBuilder> invoke() {
                return new HashMap<>();
            }
        });
    }

    /* JADX INFO: renamed from: isInitCompleted, reason: from getter */
    public final boolean getIsInitCompleted() {
        return this.isInitCompleted;
    }

    private final ReplaySubject<Void> getInitializedSubject() {
        Object value = this.initializedSubject.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-initializedSubject>(...)");
        return (ReplaySubject) value;
    }

    protected final HashMap<String, Integer> getColorMap() {
        return (HashMap) this.colorMap.getValue();
    }

    protected final HashMap<String, Float> getAlphaMap() {
        return (HashMap) this.alphaMap.getValue();
    }

    protected final HashMap<String, Integer> getSpacerMap() {
        return (HashMap) this.spacerMap.getValue();
    }

    protected final HashMap<String, ButtonSize> getIconSizeMap() {
        return (HashMap) this.iconSizeMap.getValue();
    }

    protected final HashMap<String, ButtonStyle> getIconStyleMap() {
        return (HashMap) this.iconStyleMap.getValue();
    }

    protected final HashMap<String, BaseButton.StyleBuilder> getIconBuilderMap() {
        return (HashMap) this.iconBuilderMap.getValue();
    }

    protected final HashMap<String, FontSize> getFontSizeMap() {
        return (HashMap) this.fontSizeMap.getValue();
    }

    protected final HashMap<String, FontStyle> getFontStyleMap() {
        return (HashMap) this.fontStyleMap.getValue();
    }

    protected final HashMap<String, ButtonSize> getButtonSizeMap() {
        return (HashMap) this.buttonSizeMap.getValue();
    }

    protected final HashMap<String, ButtonStyle> getButtonStyleMap() {
        return (HashMap) this.buttonStyleMap.getValue();
    }

    protected final HashMap<String, BaseButton.StyleBuilder> getButtonBuilderMap() {
        return (HashMap) this.buttonBuilderMap.getValue();
    }

    protected final HashMap<String, GradientStyle> getGradientStyleMap() {
        return (HashMap) this.gradientStyleMap.getValue();
    }

    protected final HashMap<String, Float> getBlurStyleMap() {
        return (HashMap) this.blurStyleMap.getValue();
    }

    protected final HashMap<String, Integer> getCornerSizeMap() {
        return (HashMap) this.cornerSizeMap.getValue();
    }

    protected final HashMap<String, String> getImageCornerMap() {
        return (HashMap) this.imageCornerMap.getValue();
    }

    protected final HashMap<String, Range<Integer>> getImageSizeMap() {
        return (HashMap) this.imageSizeMap.getValue();
    }

    protected final HashMap<String, ImageStyle> getImageStyleMap() {
        return (HashMap) this.imageStyleMap.getValue();
    }

    protected final HashMap<String, Integer> getTopMarginFeaturedPlay() {
        return (HashMap) this.topMarginFeaturedPlay.getValue();
    }

    protected final HashMap<String, Integer> getTopMarginLeftAlignedFeaturedPlay() {
        return (HashMap) this.topMarginLeftAlignedFeaturedPlay.getValue();
    }

    protected final HashMap<String, GridSize> getGridSizeMap() {
        return (HashMap) this.gridSizeMap.getValue();
    }

    protected final HashMap<String, GridStyle> getGridStyleMap() {
        return (HashMap) this.gridStyleMap.getValue();
    }

    protected final HashMap<String, Range<Integer>> getScreenSizeMap() {
        return (HashMap) this.screenSizeMap.getValue();
    }

    protected final HashMap<String, ButtonSize> getTagSizeMap() {
        return (HashMap) this.tagSizeMap.getValue();
    }

    protected final HashMap<String, ButtonStyle> getTagStyleMap() {
        return (HashMap) this.tagStyleMap.getValue();
    }

    protected final HashMap<String, BaseButton.StyleBuilder> getTagBuilderMap() {
        return (HashMap) this.tagBuilderMap.getValue();
    }

    protected final HashMap<String, PopularityWidgetStyle> getPopularityWidgetStyleMap() {
        return (HashMap) this.popularityWidgetStyleMap.getValue();
    }

    protected final HashMap<String, PopularityWidget$StyleBuilder> getPopularityWidgetBuilderMap() {
        return (HashMap) this.popularityWidgetBuilderMap.getValue();
    }

    protected final HashMap<String, DividerStyle> getDividerStyleMap() {
        return (HashMap) this.dividerStyleMap.getValue();
    }

    protected final HashMap<String, Divider.StyleBuilder> getDividerBuilderMap() {
        return (HashMap) this.dividerBuilderMap.getValue();
    }

    protected final HashMap<String, ButtonSize> getIndicatorSizeMap() {
        return (HashMap) this.indicatorSizeMap.getValue();
    }

    protected final HashMap<String, ButtonStyle> getIndicatorStyleMap() {
        return (HashMap) this.indicatorStyleMap.getValue();
    }

    protected final HashMap<String, BaseButton.StyleBuilder> getIndicatorBuilderMap() {
        return (HashMap) this.indicatorBuilderMap.getValue();
    }

    protected final HashMap<String, LinkStyle> getLinkStyleMap() {
        return (HashMap) this.linkStyleMap.getValue();
    }

    protected final HashMap<String, Link.LinkStringBuilder> getLinkBuilderMap() {
        return (HashMap) this.linkBuilderMap.getValue();
    }

    public final Completable initCompletable() {
        Completable completable = getInitializedSubject().toCompletable();
        Intrinsics.checkNotNullExpressionValue(completable, "initializedSubject.toCompletable()");
        return completable;
    }

    protected final void onInitCompleted() {
        getInitializedSubject().onCompleted();
        this.isInitCompleted = true;
    }

    protected final void addAlphaPercent(AlphaKey alphaKey, float alphaPercentValue) {
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        if (!ColorParser.INSTANCE.isValidAlphaPercent(alphaPercentValue)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        getAlphaMap().put(alphaKey.getKey(), Float.valueOf(alphaPercentValue));
    }

    protected final void addColor(ColorKey colorKey, int colorValue) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        getColorMap().put(colorKey.getKey(), Integer.valueOf(colorValue));
    }

    protected final void addColor(String colorKey, String alphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        Integer num = getColorMap().get(colorKey);
        Float f = getAlphaMap().get(alphaKey);
        if (num == null || f == null) {
            return;
        }
        addColorWithAlpha(getColorKeyWithAlpha(colorKey, alphaKey), num.intValue(), f.floatValue());
    }

    protected final void addColor(ColorKey colorKey, AlphaKey alphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        addColor(colorKey.getKey(), alphaKey.getKey());
    }

    protected final void addColorWithAlpha(String colorWithAlphaKey, int color, float alpha) {
        Intrinsics.checkNotNullParameter(colorWithAlphaKey, "colorWithAlphaKey");
        getColorMap().put(colorWithAlphaKey, Integer.valueOf(ColorParser.INSTANCE.parseColor(color, alpha)));
    }

    protected final void addSpacerInPixels(SpacerKey spacerKey, float pixelValue) {
        Intrinsics.checkNotNullParameter(spacerKey, "spacerKey");
        HashMap<String, Integer> spacerMap = getSpacerMap();
        String key = spacerKey.getKey();
        SpacerParser spacerParser = SpacerParser.INSTANCE;
        Resources resources = this.resources;
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        spacerMap.put(key, Integer.valueOf(spacerParser.pixelsToDp(resources, (int) pixelValue)));
    }

    protected final void addIconSize(IconSizeKey iconSizeKey, ButtonSize iconSize) {
        Intrinsics.checkNotNullParameter(iconSizeKey, "iconSizeKey");
        Intrinsics.checkNotNullParameter(iconSize, "iconSize");
        getIconSizeMap().put(iconSizeKey.getKey(), iconSize);
    }

    protected final void addIconStyle(IconStyleKey iconStyleKey, ButtonStyle iconStyle) {
        Intrinsics.checkNotNullParameter(iconStyleKey, "iconStyleKey");
        Intrinsics.checkNotNullParameter(iconStyle, "iconStyle");
        getIconStyleMap().put(iconStyleKey.getKey(), iconStyle);
    }

    protected final void addBaseIconBuilder(IconSizeKey iconSizeKey, IconStyleKey iconStyleKey) {
        ButtonStyle iconStyle;
        ButtonSize iconSize;
        String styleBuilderKey = getStyleBuilderKey(iconSizeKey == null ? null : iconSizeKey.getKey(), iconStyleKey != null ? iconStyleKey.getKey() : null);
        if (styleBuilderKey == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilder = getIconBuilderMap().get(styleBuilderKey);
        if (styleBuilder == null) {
            styleBuilder = new BaseButton.StyleBuilder();
        }
        if (iconSizeKey != null && (iconSize = getIconSize(iconSizeKey)) != null) {
            styleBuilder.withSize(iconSize);
        }
        if (iconStyleKey != null && (iconStyle = getIconStyle(iconStyleKey)) != null) {
            styleBuilder.withStyle(iconStyle);
        }
        getIconBuilderMap().put(styleBuilderKey, styleBuilder);
    }

    protected final void addFontSize(FontSizeKey fontSizeKey, FontSize fontSizeValue) {
        Intrinsics.checkNotNullParameter(fontSizeKey, "fontSizeKey");
        Intrinsics.checkNotNullParameter(fontSizeValue, "fontSizeValue");
        getFontSizeMap().put(fontSizeKey.getKey(), fontSizeValue);
    }

    protected final void addFontStyle(FontStyleKey fontStyleKey, FontStyle fontStyleValue) {
        Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
        Intrinsics.checkNotNullParameter(fontStyleValue, "fontStyleValue");
        getFontStyleMap().put(fontStyleKey.getKey(), fontStyleValue);
    }

    protected final void addButtonSize(ButtonSizeKey buttonSizeKey, ButtonSize buttonSizeValue) {
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        Intrinsics.checkNotNullParameter(buttonSizeValue, "buttonSizeValue");
        getButtonSizeMap().put(buttonSizeKey.getKey(), buttonSizeValue);
    }

    protected final void addButtonStyle(ButtonStyleKey buttonStyleKey, ButtonStyle buttonStyleValue) {
        Intrinsics.checkNotNullParameter(buttonStyleKey, "buttonStyleKey");
        Intrinsics.checkNotNullParameter(buttonStyleValue, "buttonStyleValue");
        getButtonStyleMap().put(buttonStyleKey.getKey(), buttonStyleValue);
    }

    protected final void addBaseButtonBuilder(ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey) {
        ButtonStyle buttonStyle;
        ButtonSize buttonSize;
        String styleBuilderKey = getStyleBuilderKey(buttonSizeKey == null ? null : buttonSizeKey.getKey(), buttonStyleKey != null ? buttonStyleKey.getKey() : null);
        if (styleBuilderKey == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilder = getButtonBuilderMap().get(styleBuilderKey);
        if (styleBuilder == null) {
            styleBuilder = new BaseButton.StyleBuilder();
        }
        if (buttonSizeKey != null && (buttonSize = getButtonSize(buttonSizeKey)) != null) {
            styleBuilder.withSize(buttonSize);
        }
        if (buttonStyleKey != null && (buttonStyle = getButtonStyle(buttonStyleKey)) != null) {
            styleBuilder.withStyle(buttonStyle);
        }
        getButtonBuilderMap().put(styleBuilderKey, styleBuilder);
    }

    protected final void addGradientStyle(GradientStyleKey gradientStyleKey, GradientStyle gradientStyle) {
        Intrinsics.checkNotNullParameter(gradientStyleKey, "gradientStyleKey");
        Intrinsics.checkNotNullParameter(gradientStyle, "gradientStyle");
        getGradientStyleMap().put(gradientStyleKey.getKey(), gradientStyle);
    }

    protected final void addBlurStyle(BlurStyleKey blurStyleKey, float blurStyleValue) {
        Intrinsics.checkNotNullParameter(blurStyleKey, "blurStyleKey");
        getBlurStyleMap().put(blurStyleKey.getKey(), Float.valueOf(blurStyleValue));
    }

    protected final void addImageSizeRange(ImageSizeKey imageSizeKey, Range<Integer> imageSizeRange) {
        Intrinsics.checkNotNullParameter(imageSizeKey, "imageSizeKey");
        Intrinsics.checkNotNullParameter(imageSizeRange, "imageSizeRange");
        getImageSizeMap().put(imageSizeKey.getKey(), imageSizeRange);
    }

    protected final void addCornerSize(CornerSizeKey cornerSizeKey, float cornerSize) {
        Intrinsics.checkNotNullParameter(cornerSizeKey, "cornerSizeKey");
        getCornerSizeMap().put(cornerSizeKey.getKey(), Integer.valueOf((int) cornerSize));
    }

    protected final void addImageCornerKey(ImageSizeKey imageSizeKey, CornerSizeKey cornerSizeKey) {
        Intrinsics.checkNotNullParameter(imageSizeKey, "imageSizeKey");
        Intrinsics.checkNotNullParameter(cornerSizeKey, "cornerSizeKey");
        getImageCornerMap().put(imageSizeKey.getKey(), cornerSizeKey.getKey());
    }

    protected final void addImageStyle(ImageStyleKey imageStyleKey, ImageStyle imageStyle) {
        Intrinsics.checkNotNullParameter(imageStyleKey, "imageStyleKey");
        Intrinsics.checkNotNullParameter(imageStyle, "imageStyle");
        getImageStyleMap().put(imageStyleKey.getKey(), imageStyle);
    }

    protected final void addGridSize(ScreenSizeKey screenSizeKey, GridSize gridSize) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        Intrinsics.checkNotNullParameter(gridSize, "gridSize");
        getGridSizeMap().put(screenSizeKey.getKey(), gridSize);
    }

    protected final void addFeaturedPlayTopMargin(ScreenSizeKey screenSizeKey, int topMargin) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        getTopMarginFeaturedPlay().put(screenSizeKey.getKey(), Integer.valueOf(topMargin));
    }

    protected final void addFeaturedPlayLeftAlignedTopMargin(ScreenSizeKey screenSizeKey, int topMargin) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        getTopMarginLeftAlignedFeaturedPlay().put(screenSizeKey.getKey(), Integer.valueOf(topMargin));
    }

    public final Integer getFeaturedPlayTopMargin(ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        return getTopMarginFeaturedPlay().get(screenSizeKey.getKey());
    }

    public final Integer getFeaturedPlayLeftAlignedTopMargin(ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        return getTopMarginLeftAlignedFeaturedPlay().get(screenSizeKey.getKey());
    }

    protected final void addGridStyle(ScreenSizeKey screenSizeKey, GridStyleKey gridStyleKey, GridStyle gridStyle) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        Intrinsics.checkNotNullParameter(gridStyleKey, "gridStyleKey");
        Intrinsics.checkNotNullParameter(gridStyle, "gridStyle");
        String styleBuilderKey = getStyleBuilderKey(screenSizeKey.getKey(), gridStyleKey.getKey());
        if (styleBuilderKey == null) {
            return;
        }
        getGridStyleMap().put(styleBuilderKey, gridStyle);
    }

    protected final void addScreenSizeRange(ScreenSizeKey screenSizeKey, Range<Integer> screenSizeRange) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        Intrinsics.checkNotNullParameter(screenSizeRange, "screenSizeRange");
        getScreenSizeMap().put(screenSizeKey.getKey(), screenSizeRange);
    }

    protected final void addTagSize(TagSizeKey tagSizeKey, ButtonSize tagSizeValue) {
        Intrinsics.checkNotNullParameter(tagSizeKey, "tagSizeKey");
        Intrinsics.checkNotNullParameter(tagSizeValue, "tagSizeValue");
        getTagSizeMap().put(tagSizeKey.getKey(), tagSizeValue);
    }

    protected final void addTagStyle(TagStyleKey tagStyleKey, ButtonStyle tagStyleValue) {
        Intrinsics.checkNotNullParameter(tagStyleKey, "tagStyleKey");
        Intrinsics.checkNotNullParameter(tagStyleValue, "tagStyleValue");
        getTagStyleMap().put(tagStyleKey.getKey(), tagStyleValue);
    }

    protected final void addTagBuilder(TagSizeKey tagSizeKey, TagStyleKey tagStyleKey) {
        ButtonStyle tagStyle;
        ButtonSize tagSize;
        String styleBuilderKey = getStyleBuilderKey(tagSizeKey == null ? null : tagSizeKey.getKey(), tagStyleKey != null ? tagStyleKey.getKey() : null);
        if (styleBuilderKey == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilder = getTagBuilderMap().get(styleBuilderKey);
        if (styleBuilder == null) {
            styleBuilder = new BaseButton.StyleBuilder();
        }
        if (tagSizeKey != null && (tagSize = getTagSize(tagSizeKey)) != null) {
            styleBuilder.withSize(tagSize);
        }
        if (tagStyleKey != null && (tagStyle = getTagStyle(tagStyleKey)) != null) {
            styleBuilder.withStyle(tagStyle);
        }
        getTagBuilderMap().put(styleBuilderKey, styleBuilder);
    }

    protected final void addPopularityWidgetStyle(PopularityWidgetStyleKey popularityWidgetStyleKey, PopularityWidgetStyle popularityWidgetStyle) {
        Intrinsics.checkNotNullParameter(popularityWidgetStyleKey, "popularityWidgetStyleKey");
        Intrinsics.checkNotNullParameter(popularityWidgetStyle, "popularityWidgetStyle");
        getPopularityWidgetStyleMap().put(popularityWidgetStyleKey.getKey(), popularityWidgetStyle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final void addPopularityWidgetBuilder(PopularityWidgetStyleKey popularityWidgetStyleKey) {
        Intrinsics.checkNotNullParameter(popularityWidgetStyleKey, "popularityWidgetStyleKey");
        PopularityWidget$StyleBuilder popularityWidget$StyleBuilder = getPopularityWidgetBuilderMap().get(popularityWidgetStyleKey.getKey());
        PopularityWidget$StyleBuilder popularityWidget$StyleBuilder2 = popularityWidget$StyleBuilder;
        if (popularityWidget$StyleBuilder == null) {
            popularityWidget$StyleBuilder2 = new Object() { // from class: com.amazon.ui.foundations.views.PopularityWidget$StyleBuilder
                private PopularityWidgetStyle popularityWidgetStyle;

                public PopularityWidget$StyleBuilder withStyle(PopularityWidgetStyle style) {
                    Intrinsics.checkNotNullParameter(style, "style");
                    this.popularityWidgetStyle = style;
                    return this;
                }
            };
        }
        PopularityWidgetStyle popularityWidgetStyle = getPopularityWidgetStyle(popularityWidgetStyleKey);
        if (popularityWidgetStyle != null) {
            popularityWidget$StyleBuilder2.withStyle(popularityWidgetStyle);
        }
        getPopularityWidgetBuilderMap().put(popularityWidgetStyleKey.getKey(), popularityWidget$StyleBuilder2);
    }

    protected final void addDividerStyle(DividerStyleKey dividerStyleKey, DividerStyle dividerStyle) {
        Intrinsics.checkNotNullParameter(dividerStyleKey, "dividerStyleKey");
        Intrinsics.checkNotNullParameter(dividerStyle, "dividerStyle");
        getDividerStyleMap().put(dividerStyleKey.getKey(), dividerStyle);
    }

    protected final void addDividerBuilder(DividerStyleKey dividerStyleKey) {
        Intrinsics.checkNotNullParameter(dividerStyleKey, "dividerStyleKey");
        Divider.StyleBuilder styleBuilder = getDividerBuilderMap().get(dividerStyleKey.getKey());
        if (styleBuilder == null) {
            styleBuilder = new Divider.StyleBuilder();
        }
        DividerStyle dividerStyle = getDividerStyle(dividerStyleKey);
        if (dividerStyle != null) {
            styleBuilder.withStyle(dividerStyle);
        }
        getDividerBuilderMap().put(dividerStyleKey.getKey(), styleBuilder);
    }

    protected final void addIndicatorSize(IndicatorSizeKey indicatorSizeKey, ButtonSize indicatorSize) {
        Intrinsics.checkNotNullParameter(indicatorSizeKey, "indicatorSizeKey");
        Intrinsics.checkNotNullParameter(indicatorSize, "indicatorSize");
        getIndicatorSizeMap().put(indicatorSizeKey.getKey(), indicatorSize);
    }

    protected final void addIndicatorStyle(IndicatorStyleKey indicatorStyleKey, ButtonStyle indicatorStyle) {
        Intrinsics.checkNotNullParameter(indicatorStyleKey, "indicatorStyleKey");
        Intrinsics.checkNotNullParameter(indicatorStyle, "indicatorStyle");
        getIndicatorStyleMap().put(indicatorStyleKey.getKey(), indicatorStyle);
    }

    protected final void addIndicatorBuilder(IndicatorSizeKey indicatorSizeKey, IndicatorStyleKey indicatorStyleKey) {
        ButtonStyle indicatorStyle;
        ButtonSize indicatorSize;
        String styleBuilderKey = getStyleBuilderKey(indicatorSizeKey == null ? null : indicatorSizeKey.getKey(), indicatorStyleKey != null ? indicatorStyleKey.getKey() : null);
        if (styleBuilderKey == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilder = getIndicatorBuilderMap().get(styleBuilderKey);
        if (styleBuilder == null) {
            styleBuilder = new BaseButton.StyleBuilder();
        }
        if (indicatorSizeKey != null && (indicatorSize = getIndicatorSize(indicatorSizeKey)) != null) {
            styleBuilder.withSize(indicatorSize);
        }
        if (indicatorStyleKey != null && (indicatorStyle = getIndicatorStyle(indicatorStyleKey)) != null) {
            styleBuilder.withStyle(indicatorStyle);
        }
        getIndicatorBuilderMap().put(styleBuilderKey, styleBuilder);
    }

    protected final void addLinkStyle(LinkStyleKey linkStyleKey, LinkStyle linkStyle) {
        Intrinsics.checkNotNullParameter(linkStyleKey, "linkStyleKey");
        Intrinsics.checkNotNullParameter(linkStyle, "linkStyle");
        getLinkStyleMap().put(linkStyleKey.getKey(), linkStyle);
    }

    protected final void addLinkBuilder(LinkStyleKey linkStyleKey) {
        Intrinsics.checkNotNullParameter(linkStyleKey, "linkStyleKey");
        Link.LinkStringBuilder linkStringBuilder = getLinkBuilderMap().get(linkStyleKey.getKey());
        if (linkStringBuilder == null) {
            linkStringBuilder = new Link.LinkStringBuilder();
        }
        LinkStyle linkStyle = getLinkStyle(linkStyleKey);
        if (linkStyle != null) {
            linkStringBuilder.withLinkStyle(linkStyle);
        }
        getLinkBuilderMap().put(linkStyleKey.getKey(), linkStringBuilder);
    }

    public final Integer getColor(ColorKey colorKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        return getColorMap().get(colorKey.getKey());
    }

    public final Float getTextSize(String locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        if (Intrinsics.areEqual(locale, "ml_IN")) {
            return Float.valueOf(TextSizeKeys.MALAYALAM_TEXT_SIZE.getKey());
        }
        return Float.valueOf(TextSizeKeys.DEFAULT_TEXT_SIZE.getKey());
    }

    public final Float getAlpha(AlphaKey alphaKey) {
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        return getAlphaMap().get(alphaKey.getKey());
    }

    public final Integer getColor(String colorKey, String alphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        String colorKeyWithAlpha = getColorKeyWithAlpha(colorKey, alphaKey);
        if (getColorMap().containsKey(colorKeyWithAlpha)) {
            return getColorMap().get(colorKeyWithAlpha);
        }
        Integer num = getColorMap().get(colorKey);
        Float f = getAlphaMap().get(alphaKey);
        if (num == null || f == null) {
            return num;
        }
        addColorWithAlpha(colorKeyWithAlpha, num.intValue(), f.floatValue());
        return getColorMap().get(colorKeyWithAlpha);
    }

    public final Integer getColor(ColorKey colorKey, AlphaKey alphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        return getColor(colorKey.getKey(), alphaKey.getKey());
    }

    public final Integer getCombinationColor(ColorKey colorKey, ColorKey overlayColorKey, AlphaKey overlayAlphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(overlayColorKey, "overlayColorKey");
        Intrinsics.checkNotNullParameter(overlayAlphaKey, "overlayAlphaKey");
        Integer color = getColor(colorKey);
        Integer color2 = getColor(overlayColorKey);
        Float alpha = getAlpha(overlayAlphaKey);
        if (!(color != null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(color2 != null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(alpha != null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return Integer.valueOf(ColorParser.INSTANCE.parseCombinationColor(color.intValue(), 1.0f, color2.intValue(), alpha.floatValue()));
    }

    public final Integer getCombinationColor(int colorInt, int overlayColorInt, float overlayAlphaPercent) {
        return Integer.valueOf(ColorParser.INSTANCE.parseCombinationColor(colorInt, 1.0f, overlayColorInt, overlayAlphaPercent));
    }

    protected final String getColorKeyWithAlpha(String colorKey, String alphaKey) {
        Intrinsics.checkNotNullParameter(colorKey, "colorKey");
        Intrinsics.checkNotNullParameter(alphaKey, "alphaKey");
        if (StringsKt__StringsKt.contains$default((CharSequence) colorKey, (CharSequence) alphaKey, false, 2, (Object) null)) {
            return colorKey;
        }
        String str = colorKey + "/" + alphaKey;
        Intrinsics.checkNotNullExpressionValue(str, "sb.toString()");
        return str;
    }

    public final Integer getSpacerInDp(SpacerKey spacerKey) {
        Intrinsics.checkNotNullParameter(spacerKey, "spacerKey");
        return getSpacerMap().get(spacerKey.getKey());
    }

    public final Integer getSpacerInPixels(SpacerKey spacerKey) {
        Intrinsics.checkNotNullParameter(spacerKey, "spacerKey");
        Integer num = getSpacerMap().get(spacerKey.getKey());
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        SpacerParser spacerParser = SpacerParser.INSTANCE;
        Resources resources = this.resources;
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        return Integer.valueOf(spacerParser.dpToPixels(resources, iIntValue));
    }

    public final ButtonSize getIconSize(IconSizeKey iconSizeKey) {
        Intrinsics.checkNotNullParameter(iconSizeKey, "iconSizeKey");
        return getIconSizeMap().get(iconSizeKey.getKey());
    }

    public final ButtonStyle getIconStyle(IconStyleKey iconStyleKey) {
        Intrinsics.checkNotNullParameter(iconStyleKey, "iconStyleKey");
        return getIconStyleMap().get(iconStyleKey.getKey());
    }

    public final BaseButton.StyleBuilder getIconBuilder(IconSizeKey iconSizeKey, IconStyleKey iconStyleKey) {
        BaseButton.StyleBuilder styleBuilder;
        String styleBuilderKey = getStyleBuilderKey(iconSizeKey == null ? null : iconSizeKey.getKey(), iconStyleKey == null ? null : iconStyleKey.getKey());
        if (styleBuilderKey == null || (styleBuilder = getIconBuilderMap().get(styleBuilderKey)) == null) {
            return null;
        }
        return styleBuilder.copy();
    }

    public final FontSize getFontSize(FontSizeKey fontSizeKey) {
        Intrinsics.checkNotNullParameter(fontSizeKey, "fontSizeKey");
        return getFontSizeMap().get(fontSizeKey.getKey());
    }

    public final FontStyle getFontStyle(FontStyleKey fontStyleKey) {
        Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
        return getFontStyleMap().get(fontStyleKey.getKey());
    }

    public final List<Integer> getTextSizeListInRange(FontStyleKey minFontStyleKey, FontStyleKey maxFontStyleKey) {
        FontSize fontSize;
        Intrinsics.checkNotNullParameter(minFontStyleKey, "minFontStyleKey");
        Intrinsics.checkNotNullParameter(maxFontStyleKey, "maxFontStyleKey");
        EnumSet<FontStyleKey> fontStyleSet = EnumSet.range(maxFontStyleKey, minFontStyleKey);
        Intrinsics.checkNotNullExpressionValue(fontStyleSet, "fontStyleSet");
        ArrayList arrayList = new ArrayList();
        for (FontStyleKey it : fontStyleSet) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            FontStyle fontStyle = getFontStyle(it);
            Integer numValueOf = null;
            if (fontStyle != null && (fontSize = fontStyle.getFontSize()) != null) {
                numValueOf = Integer.valueOf(fontSize.getTextSize());
            }
            if (numValueOf != null) {
                arrayList.add(numValueOf);
            }
        }
        return arrayList;
    }

    public final ButtonSize getButtonSize(ButtonSizeKey buttonSizeKey) {
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        return getButtonSizeMap().get(buttonSizeKey.getKey());
    }

    public final ButtonStyle getButtonStyle(ButtonStyleKey buttonStyleKey) {
        Intrinsics.checkNotNullParameter(buttonStyleKey, "buttonStyleKey");
        return getButtonStyleMap().get(buttonStyleKey.getKey());
    }

    public final BaseButton.StyleBuilder getButtonBuilder(ButtonSizeKey buttonSizeKey, ButtonStyleKey buttonStyleKey) {
        BaseButton.StyleBuilder styleBuilder;
        String styleBuilderKey = getStyleBuilderKey(buttonSizeKey == null ? null : buttonSizeKey.getKey(), buttonStyleKey == null ? null : buttonStyleKey.getKey());
        if (styleBuilderKey == null || (styleBuilder = getButtonBuilderMap().get(styleBuilderKey)) == null) {
            return null;
        }
        return styleBuilder.copy();
    }

    public final GradientStyle getGradientStyle(GradientStyleKey gradientStyleKey) {
        Intrinsics.checkNotNullParameter(gradientStyleKey, "gradientStyleKey");
        return getGradientStyleMap().get(gradientStyleKey.getKey());
    }

    public final Float getBlurStyle(BlurStyleKey blurStyleKey) {
        Intrinsics.checkNotNullParameter(blurStyleKey, "blurStyleKey");
        return getBlurStyleMap().get(blurStyleKey.getKey());
    }

    public final Range<Integer> getImageSizeRange(ImageSizeKey imageSizeKey) {
        Intrinsics.checkNotNullParameter(imageSizeKey, "imageSizeKey");
        return getImageSizeMap().get(imageSizeKey.getKey());
    }

    public final Integer getCornerSize(String cornerSizeKey) {
        Intrinsics.checkNotNullParameter(cornerSizeKey, "cornerSizeKey");
        return getCornerSizeMap().get(cornerSizeKey);
    }

    public final Integer getCornerSize(CornerSizeKey cornerSizeKey) {
        Intrinsics.checkNotNullParameter(cornerSizeKey, "cornerSizeKey");
        return getCornerSizeMap().get(cornerSizeKey.getKey());
    }

    public final String getImageCornerKey(ImageSizeKey imageSizeKey) {
        Intrinsics.checkNotNullParameter(imageSizeKey, "imageSizeKey");
        return getImageCornerMap().get(imageSizeKey.getKey());
    }

    public final ImageStyle getImageStyle(ImageStyleKey imageStyleKey) {
        Intrinsics.checkNotNullParameter(imageStyleKey, "imageStyleKey");
        return getImageStyleMap().get(imageStyleKey.getKey());
    }

    public final GridSize getGridSize(ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        return getGridSizeMap().get(screenSizeKey.getKey());
    }

    public final GridSize getGridSize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getGridSize(getWidthScreenSizeKey(context));
    }

    protected final Range<Integer> getScreenSizeRange(ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        return getScreenSizeMap().get(screenSizeKey.getKey());
    }

    public final ButtonSize getTagSize(TagSizeKey tagSizeKey) {
        Intrinsics.checkNotNullParameter(tagSizeKey, "tagSizeKey");
        return getTagSizeMap().get(tagSizeKey.getKey());
    }

    public final ButtonStyle getTagStyle(TagStyleKey tagStyleKey) {
        Intrinsics.checkNotNullParameter(tagStyleKey, "tagStyleKey");
        return getTagStyleMap().get(tagStyleKey.getKey());
    }

    public final BaseButton.StyleBuilder getTagBuilder(TagSizeKey tagSizeKey, TagStyleKey tagStyleKey) {
        BaseButton.StyleBuilder styleBuilder;
        String styleBuilderKey = getStyleBuilderKey(tagSizeKey == null ? null : tagSizeKey.getKey(), tagStyleKey == null ? null : tagStyleKey.getKey());
        if (styleBuilderKey == null || (styleBuilder = getTagBuilderMap().get(styleBuilderKey)) == null) {
            return null;
        }
        return styleBuilder.copy();
    }

    public final PopularityWidgetStyle getPopularityWidgetStyle(PopularityWidgetStyleKey popularityWidgetStyleKey) {
        Intrinsics.checkNotNullParameter(popularityWidgetStyleKey, "popularityWidgetStyleKey");
        return getPopularityWidgetStyleMap().get(popularityWidgetStyleKey.getKey());
    }

    public final DividerStyle getDividerStyle(DividerStyleKey dividerStyleKey) {
        Intrinsics.checkNotNullParameter(dividerStyleKey, "dividerStyleKey");
        return getDividerStyleMap().get(dividerStyleKey.getKey());
    }

    public final Divider.StyleBuilder getDividerBuilder(DividerStyleKey dividerStyleKey) {
        Intrinsics.checkNotNullParameter(dividerStyleKey, "dividerStyleKey");
        Divider.StyleBuilder styleBuilder = getDividerBuilderMap().get(dividerStyleKey.getKey());
        if (styleBuilder == null) {
            return null;
        }
        return styleBuilder.copy();
    }

    public final ButtonSize getIndicatorSize(IndicatorSizeKey indicatorSizeKey) {
        Intrinsics.checkNotNullParameter(indicatorSizeKey, "indicatorSizeKey");
        return getIndicatorSizeMap().get(indicatorSizeKey.getKey());
    }

    public final ButtonStyle getIndicatorStyle(IndicatorStyleKey indicatorStyleKey) {
        Intrinsics.checkNotNullParameter(indicatorStyleKey, "indicatorStyleKey");
        return getIndicatorStyleMap().get(indicatorStyleKey.getKey());
    }

    public final BaseButton.StyleBuilder getIndicatorBuilder(IndicatorSizeKey indicatorSizeKey, IndicatorStyleKey indicatorStyleKey) {
        BaseButton.StyleBuilder styleBuilder;
        String styleBuilderKey = getStyleBuilderKey(indicatorSizeKey == null ? null : indicatorSizeKey.getKey(), indicatorStyleKey == null ? null : indicatorStyleKey.getKey());
        if (styleBuilderKey == null || (styleBuilder = getIndicatorBuilderMap().get(styleBuilderKey)) == null) {
            return null;
        }
        return styleBuilder.copy();
    }

    public final GridStyle getGridStyle(GridStyleKey gridStyleKey) {
        Intrinsics.checkNotNullParameter(gridStyleKey, "gridStyleKey");
        return getGridStyle(gridStyleKey, getWidthScreenSizeKey());
    }

    public final GridStyle getGridStyle(GridStyleKey gridStyleKey, Context context) {
        Intrinsics.checkNotNullParameter(gridStyleKey, "gridStyleKey");
        Intrinsics.checkNotNullParameter(context, "context");
        return getGridStyle(gridStyleKey, getWidthScreenSizeKey(context));
    }

    private final GridStyle getGridStyle(GridStyleKey gridStyleKey, ScreenSizeKey screenSizeKey) {
        String styleBuilderKey = getStyleBuilderKey(screenSizeKey.getKey(), gridStyleKey.getKey());
        if (styleBuilderKey != null) {
            return getGridStyleMap().get(styleBuilderKey);
        }
        return null;
    }

    public final LinkStyle getLinkStyle(LinkStyleKey linkStyleKey) {
        Intrinsics.checkNotNullParameter(linkStyleKey, "linkStyleKey");
        return getLinkStyleMap().get(linkStyleKey.getKey());
    }

    public final int getMediumListItemHeight() {
        return (int) this.resources.getDimension(R$dimen.spacer_48);
    }

    public final int getVisualRowItemHeight() {
        return (int) this.resources.getDimension(R$dimen.visual_row_item_height);
    }

    public final int getLargeListItemHeight() {
        return (int) this.resources.getDimension(R$dimen.spacer_64);
    }

    protected final String getStyleBuilderKey(String sizeKey, String styleKey) {
        StringBuilder sb = new StringBuilder();
        if (sizeKey != null) {
            sb.append(sizeKey);
        }
        if (styleKey != null) {
            sb.append(styleKey);
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public final ScreenSizeKey getWidthScreenSizeKey() {
        return getScreenSizeKey(ScreenUtil.INSTANCE.getScreenWidthPixels(this.windowManager));
    }

    public final ScreenSizeKey getWidthScreenSizeKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        return getScreenSizeKey(ScreenUtil.INSTANCE.getScreenWidthPixels((WindowManager) systemService));
    }

    public final ScreenSizeKey getHeightScreenSizeKey() {
        return getScreenSizeKey(ScreenUtil.INSTANCE.getScreenHeightPixels(this.windowManager));
    }

    public final ScreenSizeKey getHeightScreenSizeKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        return getScreenSizeKey(ScreenUtil.INSTANCE.getScreenHeightPixels((WindowManager) systemService));
    }

    public final ScreenSizeKey getFullWidthScreenSizeKey() {
        return getScreenSizeKey(ScreenUtil.INSTANCE.getFullScreenWidthPixels(this.windowManager));
    }

    public final ScreenSizeKey getFullHeightScreenSizeKey() {
        return getScreenSizeKey(ScreenUtil.INSTANCE.getFullScreenHeightPixels(this.windowManager));
    }

    public final LandscapeType getLandscapeType(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ScreenUtil.INSTANCE.isPortrait(context) ? LandscapeType.NOT_LANDSCAPE : getHeightScreenSizeKey(context).compareTo(ScreenSizeKey.LARGE) < 0 ? LandscapeType.MOBILE_LANDSCAPE : LandscapeType.TABLET_LANDSCAPE;
    }

    public final int horizontalInsetWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        GridSize gridSize = getGridSize(context);
        if (gridSize == null) {
            return 0;
        }
        return gridSize.getColumnSpace() + gridSize.getMarginSpace();
    }
}
