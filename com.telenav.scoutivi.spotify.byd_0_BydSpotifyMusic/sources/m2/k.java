package m2;

import android.graphics.Matrix;
import android.util.Log;
import android.util.Xml;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import m2.b;
import m2.e;
import m2.g;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SVGParser.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8361d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m2.g f8358a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g.j0 f8359b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f8360c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f8362e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private h f8363f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private StringBuilder f8364g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f8365h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private StringBuilder f8366i = null;

    /* JADX INFO: compiled from: SVGParser.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8367a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f8368b;

        static {
            int[] iArr = new int[g.values().length];
            f8368b = iArr;
            try {
                iArr[g.x.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8368b[g.y.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8368b[g.width.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8368b[g.height.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8368b[g.version.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8368b[g.href.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8368b[g.preserveAspectRatio.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8368b[g.d.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8368b[g.pathLength.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8368b[g.rx.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8368b[g.ry.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f8368b[g.cx.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f8368b[g.cy.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f8368b[g.r.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f8368b[g.x1.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f8368b[g.y1.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f8368b[g.x2.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f8368b[g.y2.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f8368b[g.dx.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f8368b[g.dy.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f8368b[g.requiredFeatures.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f8368b[g.requiredExtensions.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f8368b[g.systemLanguage.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f8368b[g.requiredFormats.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f8368b[g.requiredFonts.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f8368b[g.refX.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f8368b[g.refY.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f8368b[g.markerWidth.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f8368b[g.markerHeight.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f8368b[g.markerUnits.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f8368b[g.orient.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f8368b[g.gradientUnits.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                f8368b[g.gradientTransform.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f8368b[g.spreadMethod.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                f8368b[g.fx.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                f8368b[g.fy.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                f8368b[g.offset.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                f8368b[g.clipPathUnits.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                f8368b[g.startOffset.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                f8368b[g.patternUnits.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                f8368b[g.patternContentUnits.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                f8368b[g.patternTransform.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                f8368b[g.maskUnits.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                f8368b[g.maskContentUnits.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                f8368b[g.style.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                f8368b[g.CLASS.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                f8368b[g.fill.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                f8368b[g.fill_rule.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                f8368b[g.fill_opacity.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                f8368b[g.stroke.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                f8368b[g.stroke_opacity.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                f8368b[g.stroke_width.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                f8368b[g.stroke_linecap.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                f8368b[g.stroke_linejoin.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                f8368b[g.stroke_miterlimit.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                f8368b[g.stroke_dasharray.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                f8368b[g.stroke_dashoffset.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                f8368b[g.opacity.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                f8368b[g.color.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                f8368b[g.font.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                f8368b[g.font_family.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                f8368b[g.font_size.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                f8368b[g.font_weight.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                f8368b[g.font_style.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                f8368b[g.text_decoration.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                f8368b[g.direction.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                f8368b[g.text_anchor.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                f8368b[g.overflow.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                f8368b[g.marker.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                f8368b[g.marker_start.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                f8368b[g.marker_mid.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                f8368b[g.marker_end.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                f8368b[g.display.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                f8368b[g.visibility.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                f8368b[g.stop_color.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                f8368b[g.stop_opacity.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                f8368b[g.clip.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                f8368b[g.clip_path.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                f8368b[g.clip_rule.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                f8368b[g.mask.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                f8368b[g.solid_color.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                f8368b[g.solid_opacity.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                f8368b[g.viewport_fill.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                f8368b[g.viewport_fill_opacity.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                f8368b[g.vector_effect.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                f8368b[g.image_rendering.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                f8368b[g.viewBox.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                f8368b[g.type.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                f8368b[g.media.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            int[] iArr2 = new int[h.values().length];
            f8367a = iArr2;
            try {
                iArr2[h.svg.ordinal()] = 1;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                f8367a[h.g.ordinal()] = 2;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                f8367a[h.a.ordinal()] = 3;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                f8367a[h.defs.ordinal()] = 4;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                f8367a[h.use.ordinal()] = 5;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                f8367a[h.path.ordinal()] = 6;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                f8367a[h.rect.ordinal()] = 7;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                f8367a[h.circle.ordinal()] = 8;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                f8367a[h.ellipse.ordinal()] = 9;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                f8367a[h.line.ordinal()] = 10;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                f8367a[h.polyline.ordinal()] = 11;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                f8367a[h.polygon.ordinal()] = 12;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                f8367a[h.text.ordinal()] = 13;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                f8367a[h.tspan.ordinal()] = 14;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                f8367a[h.tref.ordinal()] = 15;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                f8367a[h.SWITCH.ordinal()] = 16;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                f8367a[h.symbol.ordinal()] = 17;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                f8367a[h.marker.ordinal()] = 18;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                f8367a[h.linearGradient.ordinal()] = 19;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                f8367a[h.radialGradient.ordinal()] = 20;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                f8367a[h.stop.ordinal()] = 21;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                f8367a[h.title.ordinal()] = 22;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                f8367a[h.desc.ordinal()] = 23;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                f8367a[h.clipPath.ordinal()] = 24;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                f8367a[h.textPath.ordinal()] = 25;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                f8367a[h.pattern.ordinal()] = 26;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                f8367a[h.image.ordinal()] = 27;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                f8367a[h.view.ordinal()] = 28;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                f8367a[h.mask.ordinal()] = 29;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                f8367a[h.style.ordinal()] = 30;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                f8367a[h.solidColor.ordinal()] = 31;
            } catch (NoSuchFieldError unused120) {
            }
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Map<String, e.a> f8369a;

        static {
            HashMap map = new HashMap(10);
            f8369a = map;
            map.put("none", e.a.none);
            map.put("xMinYMin", e.a.xMinYMin);
            map.put("xMidYMin", e.a.xMidYMin);
            map.put("xMaxYMin", e.a.xMaxYMin);
            map.put("xMinYMid", e.a.xMinYMid);
            map.put("xMidYMid", e.a.xMidYMid);
            map.put("xMaxYMid", e.a.xMaxYMid);
            map.put("xMinYMax", e.a.xMinYMax);
            map.put("xMidYMax", e.a.xMidYMax);
            map.put("xMaxYMax", e.a.xMaxYMax);
        }

        static e.a a(String str) {
            return f8369a.get(str);
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Map<String, Integer> f8370a;

        static {
            HashMap map = new HashMap(47);
            f8370a = map;
            map.put("aliceblue", -984833);
            map.put("antiquewhite", -332841);
            map.put("aqua", -16711681);
            map.put("aquamarine", -8388652);
            map.put("azure", -983041);
            map.put("beige", -657956);
            map.put("bisque", -6972);
            map.put("black", -16777216);
            map.put("blanchedalmond", -5171);
            map.put("blue", -16776961);
            map.put("blueviolet", -7722014);
            map.put("brown", -5952982);
            map.put("burlywood", -2180985);
            map.put("cadetblue", -10510688);
            map.put("chartreuse", -8388864);
            map.put("chocolate", -2987746);
            map.put("coral", -32944);
            map.put("cornflowerblue", -10185235);
            map.put("cornsilk", -1828);
            map.put("crimson", -2354116);
            map.put("cyan", -16711681);
            map.put("darkblue", -16777077);
            map.put("darkcyan", -16741493);
            map.put("darkgoldenrod", -4684277);
            map.put("darkgray", -5658199);
            map.put("darkgreen", -16751616);
            map.put("darkgrey", -5658199);
            map.put("darkkhaki", -4343957);
            map.put("darkmagenta", -7667573);
            map.put("darkolivegreen", -11179217);
            map.put("darkorange", -29696);
            map.put("darkorchid", -6737204);
            map.put("darkred", -7667712);
            map.put("darksalmon", -1468806);
            map.put("darkseagreen", -7357297);
            map.put("darkslateblue", -12042869);
            map.put("darkslategray", -13676721);
            map.put("darkslategrey", -13676721);
            map.put("darkturquoise", -16724271);
            map.put("darkviolet", -7077677);
            map.put("deeppink", -60269);
            map.put("deepskyblue", -16728065);
            map.put("dimgray", -9868951);
            map.put("dimgrey", -9868951);
            map.put("dodgerblue", -14774017);
            map.put("firebrick", -5103070);
            map.put("floralwhite", -1296);
            map.put("forestgreen", -14513374);
            map.put("fuchsia", -65281);
            map.put("gainsboro", -2302756);
            map.put("ghostwhite", -460545);
            map.put("gold", -10496);
            map.put("goldenrod", -2448096);
            map.put("gray", -8355712);
            map.put("green", -16744448);
            map.put("greenyellow", -5374161);
            map.put("grey", -8355712);
            map.put("honeydew", -983056);
            map.put("hotpink", -38476);
            map.put("indianred", -3318692);
            map.put("indigo", -11861886);
            map.put("ivory", -16);
            map.put("khaki", -989556);
            map.put("lavender", -1644806);
            map.put("lavenderblush", -3851);
            map.put("lawngreen", -8586240);
            map.put("lemonchiffon", -1331);
            map.put("lightblue", -5383962);
            map.put("lightcoral", -1015680);
            map.put("lightcyan", -2031617);
            map.put("lightgoldenrodyellow", -329006);
            map.put("lightgray", -2894893);
            map.put("lightgreen", -7278960);
            map.put("lightgrey", -2894893);
            map.put("lightpink", -18751);
            map.put("lightsalmon", -24454);
            map.put("lightseagreen", -14634326);
            map.put("lightskyblue", -7876870);
            map.put("lightslategray", -8943463);
            map.put("lightslategrey", -8943463);
            map.put("lightsteelblue", -5192482);
            map.put("lightyellow", -32);
            map.put("lime", -16711936);
            map.put("limegreen", -13447886);
            map.put("linen", -331546);
            map.put("magenta", -65281);
            map.put("maroon", -8388608);
            map.put("mediumaquamarine", -10039894);
            map.put("mediumblue", -16777011);
            map.put("mediumorchid", -4565549);
            map.put("mediumpurple", -7114533);
            map.put("mediumseagreen", -12799119);
            map.put("mediumslateblue", -8689426);
            map.put("mediumspringgreen", -16713062);
            map.put("mediumturquoise", -12004916);
            map.put("mediumvioletred", -3730043);
            map.put("midnightblue", -15132304);
            map.put("mintcream", -655366);
            map.put("mistyrose", -6943);
            map.put("moccasin", -6987);
            map.put("navajowhite", -8531);
            map.put("navy", -16777088);
            map.put("oldlace", -133658);
            map.put("olive", -8355840);
            map.put("olivedrab", -9728477);
            map.put("orange", -23296);
            map.put("orangered", -47872);
            map.put("orchid", -2461482);
            map.put("palegoldenrod", -1120086);
            map.put("palegreen", -6751336);
            map.put("paleturquoise", -5247250);
            map.put("palevioletred", -2396013);
            map.put("papayawhip", -4139);
            map.put("peachpuff", -9543);
            map.put("peru", -3308225);
            map.put("pink", -16181);
            map.put("plum", -2252579);
            map.put("powderblue", -5185306);
            map.put("purple", -8388480);
            map.put("rebeccapurple", -10079335);
            map.put("red", -65536);
            map.put("rosybrown", -4419697);
            map.put("royalblue", -12490271);
            map.put("saddlebrown", -7650029);
            map.put("salmon", -360334);
            map.put("sandybrown", -744352);
            map.put("seagreen", -13726889);
            map.put("seashell", -2578);
            map.put("sienna", -6270419);
            map.put("silver", -4144960);
            map.put("skyblue", -7876885);
            map.put("slateblue", -9807155);
            map.put("slategray", -9404272);
            map.put("slategrey", -9404272);
            map.put("snow", -1286);
            map.put("springgreen", -16711809);
            map.put("steelblue", -12156236);
            map.put("tan", -2968436);
            map.put("teal", -16744320);
            map.put("thistle", -2572328);
            map.put("tomato", -40121);
            map.put("turquoise", -12525360);
            map.put("violet", -1146130);
            map.put("wheat", -663885);
            map.put("white", -1);
            map.put("whitesmoke", -657931);
            map.put("yellow", -256);
            map.put("yellowgreen", -6632142);
            map.put("transparent", 0);
        }

        static Integer a(String str) {
            return f8370a.get(str);
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Map<String, g.p> f8371a;

        static {
            HashMap map = new HashMap(9);
            f8371a = map;
            g.d1 d1Var = g.d1.pt;
            map.put("xx-small", new g.p(0.694f, d1Var));
            map.put("x-small", new g.p(0.833f, d1Var));
            map.put("small", new g.p(10.0f, d1Var));
            map.put("medium", new g.p(12.0f, d1Var));
            map.put("large", new g.p(14.4f, d1Var));
            map.put("x-large", new g.p(17.3f, d1Var));
            map.put("xx-large", new g.p(20.7f, d1Var));
            g.d1 d1Var2 = g.d1.percent;
            map.put("smaller", new g.p(83.33f, d1Var2));
            map.put("larger", new g.p(120.0f, d1Var2));
        }

        static g.p a(String str) {
            return f8371a.get(str);
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Map<String, Integer> f8372a;

        static {
            HashMap map = new HashMap(13);
            f8372a = map;
            map.put("normal", 400);
            map.put("bold", 700);
            map.put("bolder", 1);
            map.put("lighter", -1);
            map.put("100", 100);
            map.put("200", 200);
            map.put("300", 300);
            map.put("400", 400);
            map.put("500", 500);
            map.put("600", 600);
            map.put("700", 700);
            map.put("800", 800);
            map.put("900", 900);
        }

        static Integer a(String str) {
            return f8372a.get(str);
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private class f extends DefaultHandler2 {
        private f() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i7, int i8) {
            k.this.c1(new String(cArr, i7, i8));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() {
            k.this.o();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            k.this.p(str, str2, str3);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void processingInstruction(String str, String str2) {
            k.this.r(str, k.this.x0(new i(str2)));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() {
            k.this.W0();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws m2.j {
            k.this.X0(str, str2, str3, attributes);
        }

        /* synthetic */ f(k kVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private enum g {
        CLASS,
        clip,
        clip_path,
        clipPathUnits,
        clip_rule,
        color,
        cx,
        cy,
        direction,
        dx,
        dy,
        fx,
        fy,
        d,
        display,
        fill,
        fill_rule,
        fill_opacity,
        font,
        font_family,
        font_size,
        font_weight,
        font_style,
        gradientTransform,
        gradientUnits,
        height,
        href,
        image_rendering,
        marker,
        marker_start,
        marker_mid,
        marker_end,
        markerHeight,
        markerUnits,
        markerWidth,
        mask,
        maskContentUnits,
        maskUnits,
        media,
        offset,
        opacity,
        orient,
        overflow,
        pathLength,
        patternContentUnits,
        patternTransform,
        patternUnits,
        points,
        preserveAspectRatio,
        r,
        refX,
        refY,
        requiredFeatures,
        requiredExtensions,
        requiredFormats,
        requiredFonts,
        rx,
        ry,
        solid_color,
        solid_opacity,
        spreadMethod,
        startOffset,
        stop_color,
        stop_opacity,
        stroke,
        stroke_dasharray,
        stroke_dashoffset,
        stroke_linecap,
        stroke_linejoin,
        stroke_miterlimit,
        stroke_opacity,
        stroke_width,
        style,
        systemLanguage,
        text_anchor,
        text_decoration,
        transform,
        type,
        vector_effect,
        version,
        viewBox,
        width,
        x,
        y,
        x1,
        y1,
        x2,
        y2,
        viewport_fill,
        viewport_fill_opacity,
        visibility,
        UNSUPPORTED;


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final Map<String, g> f8375e = new HashMap();

        static {
            for (g gVar : values()) {
                if (gVar == CLASS) {
                    f8375e.put("class", gVar);
                } else {
                    if (gVar != UNSUPPORTED) {
                        f8375e.put(gVar.name().replace('_', '-'), gVar);
                    }
                }
            }
        }

        public static g fromString(String str) {
            g gVar = f8375e.get(str);
            return gVar != null ? gVar : UNSUPPORTED;
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private enum h {
        svg,
        a,
        circle,
        clipPath,
        defs,
        desc,
        ellipse,
        g,
        image,
        line,
        linearGradient,
        marker,
        mask,
        path,
        pattern,
        polygon,
        polyline,
        radialGradient,
        rect,
        solidColor,
        stop,
        style,
        SWITCH,
        symbol,
        text,
        textPath,
        title,
        tref,
        tspan,
        use,
        view,
        UNSUPPORTED;


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final Map<String, h> f8385e = new HashMap();

        static {
            for (h hVar : values()) {
                if (hVar == SWITCH) {
                    f8385e.put("switch", hVar);
                } else if (hVar != UNSUPPORTED) {
                    f8385e.put(hVar.name(), hVar);
                }
            }
        }

        public static h fromString(String str) {
            h hVar = f8385e.get(str);
            return hVar != null ? hVar : UNSUPPORTED;
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f8388a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f8390c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f8389b = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private m2.d f8391d = new m2.d();

        i(String str) {
            this.f8390c = 0;
            String strTrim = str.trim();
            this.f8388a = strTrim;
            this.f8390c = strTrim.length();
        }

        void A() {
            while (true) {
                int i7 = this.f8389b;
                if (i7 >= this.f8390c || !k(this.f8388a.charAt(i7))) {
                    return;
                } else {
                    this.f8389b++;
                }
            }
        }

        int a() {
            int i7 = this.f8389b;
            int i8 = this.f8390c;
            if (i7 == i8) {
                return -1;
            }
            int i9 = i7 + 1;
            this.f8389b = i9;
            if (i9 < i8) {
                return this.f8388a.charAt(i9);
            }
            return -1;
        }

        String b() {
            int i7 = this.f8389b;
            while (!h() && !k(this.f8388a.charAt(this.f8389b))) {
                this.f8389b++;
            }
            String strSubstring = this.f8388a.substring(i7, this.f8389b);
            this.f8389b = i7;
            return strSubstring;
        }

        Boolean c(Object obj) {
            if (obj == null) {
                return null;
            }
            z();
            return m();
        }

        float d(float f7) {
            if (Float.isNaN(f7)) {
                return Float.NaN;
            }
            z();
            return n();
        }

        float e(Boolean bool) {
            if (bool == null) {
                return Float.NaN;
            }
            z();
            return n();
        }

        boolean f(char c7) {
            int i7 = this.f8389b;
            boolean z6 = i7 < this.f8390c && this.f8388a.charAt(i7) == c7;
            if (z6) {
                this.f8389b++;
            }
            return z6;
        }

        boolean g(String str) {
            int length = str.length();
            int i7 = this.f8389b;
            boolean z6 = i7 <= this.f8390c - length && this.f8388a.substring(i7, i7 + length).equals(str);
            if (z6) {
                this.f8389b += length;
            }
            return z6;
        }

        boolean h() {
            return this.f8389b == this.f8390c;
        }

        boolean i() {
            int i7 = this.f8389b;
            if (i7 == this.f8390c) {
                return false;
            }
            char cCharAt = this.f8388a.charAt(i7);
            return (cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z');
        }

        boolean j(int i7) {
            return i7 == 10 || i7 == 13;
        }

        boolean k(int i7) {
            return i7 == 32 || i7 == 10 || i7 == 13 || i7 == 9;
        }

        Integer l() {
            int i7 = this.f8389b;
            if (i7 == this.f8390c) {
                return null;
            }
            String str = this.f8388a;
            this.f8389b = i7 + 1;
            return Integer.valueOf(str.charAt(i7));
        }

        Boolean m() {
            int i7 = this.f8389b;
            if (i7 == this.f8390c) {
                return null;
            }
            char cCharAt = this.f8388a.charAt(i7);
            if (cCharAt != '0' && cCharAt != '1') {
                return null;
            }
            this.f8389b++;
            return Boolean.valueOf(cCharAt == '1');
        }

        float n() {
            float fB = this.f8391d.b(this.f8388a, this.f8389b, this.f8390c);
            if (!Float.isNaN(fB)) {
                this.f8389b = this.f8391d.a();
            }
            return fB;
        }

        String o() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            int iCharAt = this.f8388a.charAt(i7);
            while (true) {
                if ((iCharAt < 97 || iCharAt > 122) && (iCharAt < 65 || iCharAt > 90)) {
                    break;
                }
                iCharAt = a();
            }
            int i8 = this.f8389b;
            while (k(iCharAt)) {
                iCharAt = a();
            }
            if (iCharAt == 40) {
                this.f8389b++;
                return this.f8388a.substring(i7, i8);
            }
            this.f8389b = i7;
            return null;
        }

        g.p p() {
            float fN = n();
            if (Float.isNaN(fN)) {
                return null;
            }
            g.d1 d1VarV = v();
            return d1VarV == null ? new g.p(fN, g.d1.px) : new g.p(fN, d1VarV);
        }

        String q() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            char cCharAt = this.f8388a.charAt(i7);
            if (cCharAt != '\'' && cCharAt != '\"') {
                return null;
            }
            int iA = a();
            while (iA != -1 && iA != cCharAt) {
                iA = a();
            }
            if (iA == -1) {
                this.f8389b = i7;
                return null;
            }
            int i8 = this.f8389b + 1;
            this.f8389b = i8;
            return this.f8388a.substring(i7 + 1, i8 - 1);
        }

        String r() {
            return t(' ', false);
        }

        String s(char c7) {
            return t(c7, false);
        }

        String t(char c7, boolean z6) {
            if (h()) {
                return null;
            }
            char cCharAt = this.f8388a.charAt(this.f8389b);
            if ((!z6 && k(cCharAt)) || cCharAt == c7) {
                return null;
            }
            int i7 = this.f8389b;
            int iA = a();
            while (iA != -1 && iA != c7 && (z6 || !k(iA))) {
                iA = a();
            }
            return this.f8388a.substring(i7, this.f8389b);
        }

        String u(char c7) {
            return t(c7, true);
        }

        g.d1 v() {
            if (h()) {
                return null;
            }
            if (this.f8388a.charAt(this.f8389b) == '%') {
                this.f8389b++;
                return g.d1.percent;
            }
            int i7 = this.f8389b;
            if (i7 > this.f8390c - 2) {
                return null;
            }
            try {
                g.d1 d1VarValueOf = g.d1.valueOf(this.f8388a.substring(i7, i7 + 2).toLowerCase(Locale.US));
                this.f8389b += 2;
                return d1VarValueOf;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        String w() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            char cCharAt = this.f8388a.charAt(i7);
            if ((cCharAt < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z')) {
                this.f8389b = i7;
                return null;
            }
            int iA = a();
            while (true) {
                if ((iA < 65 || iA > 90) && (iA < 97 || iA > 122)) {
                    break;
                }
                iA = a();
            }
            return this.f8388a.substring(i7, this.f8389b);
        }

        float x() {
            z();
            float fB = this.f8391d.b(this.f8388a, this.f8389b, this.f8390c);
            if (!Float.isNaN(fB)) {
                this.f8389b = this.f8391d.a();
            }
            return fB;
        }

        String y() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            this.f8389b = this.f8390c;
            return this.f8388a.substring(i7);
        }

        boolean z() {
            A();
            int i7 = this.f8389b;
            if (i7 == this.f8390c || this.f8388a.charAt(i7) != ',') {
                return false;
            }
            this.f8389b++;
            A();
            return true;
        }
    }

    /* JADX INFO: compiled from: SVGParser.java */
    private class j implements Attributes {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private XmlPullParser f8392a;

        public j(XmlPullParser xmlPullParser) {
            this.f8392a = xmlPullParser;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str, String str2) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getLength() {
            return this.f8392a.getAttributeCount();
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int i7) {
            return this.f8392a.getAttributeName(i7);
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int i7) {
            String attributeName = this.f8392a.getAttributeName(i7);
            if (this.f8392a.getAttributePrefix(i7) == null) {
                return attributeName;
            }
            return this.f8392a.getAttributePrefix(i7) + ':' + attributeName;
        }

        @Override // org.xml.sax.Attributes
        public String getType(int i7) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str, String str2) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int i7) {
            return this.f8392a.getAttributeNamespace(i7);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(int i7) {
            return this.f8392a.getAttributeValue(i7);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str, String str2) {
            return null;
        }
    }

    k() {
    }

    private void A(g.d dVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 12:
                    dVar.f8162o = o0(strTrim);
                    break;
                case 13:
                    dVar.f8163p = o0(strTrim);
                    break;
                case 14:
                    g.p pVarO0 = o0(strTrim);
                    dVar.f8164q = pVarO0;
                    if (pVarO0.g()) {
                        throw new m2.j("Invalid <circle> element. r cannot be negative");
                    }
                    break;
                    break;
            }
        }
    }

    private static Set<String> A0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            hashSet.add(iVar.r());
            iVar.A();
        }
        return hashSet;
    }

    private void B(g.e eVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            if (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()] == 38) {
                if ("objectBoundingBox".equals(strTrim)) {
                    eVar.f8167p = Boolean.FALSE;
                } else {
                    if (!"userSpaceOnUse".equals(strTrim)) {
                        throw new m2.j("Invalid value for attribute clipPathUnits");
                    }
                    eVar.f8167p = Boolean.TRUE;
                }
            }
        }
    }

    private static g.p[] B0(String str) {
        g.p pVarP;
        i iVar = new i(str);
        iVar.A();
        if (iVar.h() || (pVarP = iVar.p()) == null || pVarP.g()) {
            return null;
        }
        float fA = pVarP.a();
        ArrayList arrayList = new ArrayList();
        arrayList.add(pVarP);
        while (!iVar.h()) {
            iVar.z();
            g.p pVarP2 = iVar.p();
            if (pVarP2 == null || pVarP2.g()) {
                return null;
            }
            arrayList.add(pVarP2);
            fA += pVarP2.a();
        }
        if (fA == 0.0f) {
            return null;
        }
        return (g.p[]) arrayList.toArray(new g.p[arrayList.size()]);
    }

    private void C(g.g0 g0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 21:
                    g0Var.k(z0(strTrim));
                    break;
                case 22:
                    g0Var.g(strTrim);
                    break;
                case 23:
                    g0Var.e(F0(strTrim));
                    break;
                case 24:
                    g0Var.b(A0(strTrim));
                    break;
                case 25:
                    List<String> listI0 = i0(strTrim);
                    g0Var.d(listI0 != null ? new HashSet(listI0) : new HashSet(0));
                    break;
            }
        }
    }

    private static g.e0.c C0(String str) {
        if ("butt".equals(str)) {
            return g.e0.c.Butt;
        }
        if ("round".equals(str)) {
            return g.e0.c.Round;
        }
        if ("square".equals(str)) {
            return g.e0.c.Square;
        }
        return null;
    }

    private void D(g.l0 l0Var, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String qName = attributes.getQName(i7);
            if (qName.equals("id") || qName.equals("xml:id")) {
                l0Var.f8235c = attributes.getValue(i7).trim();
                return;
            }
            if (qName.equals("xml:space")) {
                String strTrim = attributes.getValue(i7).trim();
                if ("default".equals(strTrim)) {
                    l0Var.f8236d = Boolean.FALSE;
                    return;
                } else {
                    if ("preserve".equals(strTrim)) {
                        l0Var.f8236d = Boolean.TRUE;
                        return;
                    }
                    throw new m2.j("Invalid value for \"xml:space\" attribute: " + strTrim);
                }
            }
        }
    }

    private static g.e0.d D0(String str) {
        if ("miter".equals(str)) {
            return g.e0.d.Miter;
        }
        if ("round".equals(str)) {
            return g.e0.d.Round;
        }
        if ("bevel".equals(str)) {
            return g.e0.d.Bevel;
        }
        return null;
    }

    private void E(g.i iVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 10:
                    g.p pVarO0 = o0(strTrim);
                    iVar.f8220q = pVarO0;
                    if (pVarO0.g()) {
                        throw new m2.j("Invalid <ellipse> element. rx cannot be negative");
                    }
                    break;
                    break;
                case 11:
                    g.p pVarO02 = o0(strTrim);
                    iVar.f8221r = pVarO02;
                    if (pVarO02.g()) {
                        throw new m2.j("Invalid <ellipse> element. ry cannot be negative");
                    }
                    break;
                    break;
                case 12:
                    iVar.f8218o = o0(strTrim);
                    break;
                case 13:
                    iVar.f8219p = o0(strTrim);
                    break;
            }
        }
    }

    private static void E0(g.l0 l0Var, String str) {
        i iVar = new i(str.replaceAll("/\\*.*?\\*/", ""));
        while (true) {
            String strS = iVar.s(':');
            iVar.A();
            if (!iVar.f(':')) {
                return;
            }
            iVar.A();
            String strU = iVar.u(';');
            if (strU == null) {
                return;
            }
            iVar.A();
            if (iVar.h() || iVar.f(';')) {
                if (l0Var.f8238f == null) {
                    l0Var.f8238f = new g.e0();
                }
                S0(l0Var.f8238f, strS, strU);
                iVar.A();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x008c, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void F(m2.g.j r5, org.xml.sax.Attributes r6) throws m2.j {
        /*
            r4 = this;
            r0 = 0
        L1:
            int r1 = r6.getLength()
            if (r0 >= r1) goto L90
            java.lang.String r1 = r6.getValue(r0)
            java.lang.String r1 = r1.trim()
            int[] r2 = m2.k.a.f8368b
            java.lang.String r3 = r6.getLocalName(r0)
            m2.k$g r3 = m2.k.g.fromString(r3)
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 6
            if (r2 == r3) goto L72
            switch(r2) {
                case 32: goto L50;
                case 33: goto L49;
                case 34: goto L26;
                default: goto L25;
            }
        L25:
            goto L8c
        L26:
            m2.g$k r2 = m2.g.k.valueOf(r1)     // Catch: java.lang.IllegalArgumentException -> L2d
            r5.f8230k = r2     // Catch: java.lang.IllegalArgumentException -> L2d
            goto L8c
        L2d:
            m2.j r5 = new m2.j
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "Invalid spreadMethod attribute. \""
            r6.append(r0)
            r6.append(r1)
            java.lang.String r0 = "\" is not a valid value."
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L49:
            android.graphics.Matrix r1 = r4.J0(r1)
            r5.f8229j = r1
            goto L8c
        L50:
            java.lang.String r2 = "objectBoundingBox"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L5d
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            r5.f8228i = r1
            goto L8c
        L5d:
            java.lang.String r2 = "userSpaceOnUse"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L6a
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r5.f8228i = r1
            goto L8c
        L6a:
            m2.j r5 = new m2.j
            java.lang.String r6 = "Invalid value for attribute gradientUnits"
            r5.<init>(r6)
            throw r5
        L72:
            java.lang.String r2 = r6.getURI(r0)
            java.lang.String r3 = ""
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L8a
            java.lang.String r2 = r6.getURI(r0)
            java.lang.String r3 = "http://www.w3.org/1999/xlink"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L8c
        L8a:
            r5.f8231l = r1
        L8c:
            int r0 = r0 + 1
            goto L1
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.k.F(m2.g$j, org.xml.sax.Attributes):void");
    }

    private static Set<String> F0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            String strR = iVar.r();
            int iIndexOf = strR.indexOf(45);
            if (iIndexOf != -1) {
                strR = strR.substring(0, iIndexOf);
            }
            hashSet.add(new Locale(strR, "", "").getLanguage());
            iVar.A();
        }
        return hashSet;
    }

    private void G(g.o oVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                oVar.f8248q = o0(strTrim);
            } else if (i8 == 2) {
                oVar.f8249r = o0(strTrim);
            } else if (i8 == 3) {
                g.p pVarO0 = o0(strTrim);
                oVar.f8250s = pVarO0;
                if (pVarO0.g()) {
                    throw new m2.j("Invalid <use> element. width cannot be negative");
                }
            } else if (i8 == 4) {
                g.p pVarO02 = o0(strTrim);
                oVar.f8251t = pVarO02;
                if (pVarO02.g()) {
                    throw new m2.j("Invalid <use> element. height cannot be negative");
                }
            } else if (i8 != 6) {
                if (i8 == 7) {
                    w0(oVar, strTrim);
                }
            } else if ("".equals(attributes.getURI(i7)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i7))) {
                oVar.f8247p = strTrim;
            }
        }
    }

    private static g.e0.f G0(String str) {
        str.hashCode();
        switch (str) {
            case "middle":
                return g.e0.f.Middle;
            case "end":
                return g.e0.f.End;
            case "start":
                return g.e0.f.Start;
            default:
                return null;
        }
    }

    private void H(g.q qVar, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 15:
                    qVar.f8256o = o0(strTrim);
                    break;
                case 16:
                    qVar.f8257p = o0(strTrim);
                    break;
                case 17:
                    qVar.f8258q = o0(strTrim);
                    break;
                case 18:
                    qVar.f8259r = o0(strTrim);
                    break;
            }
        }
    }

    private static g.e0.EnumC0124g H0(String str) {
        str.hashCode();
        switch (str) {
            case "line-through":
                return g.e0.EnumC0124g.LineThrough;
            case "underline":
                return g.e0.EnumC0124g.Underline;
            case "none":
                return g.e0.EnumC0124g.None;
            case "blink":
                return g.e0.EnumC0124g.Blink;
            case "overline":
                return g.e0.EnumC0124g.Overline;
            default:
                return null;
        }
    }

    private void I(g.m0 m0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 15:
                    m0Var.f8241m = o0(strTrim);
                    break;
                case 16:
                    m0Var.f8242n = o0(strTrim);
                    break;
                case 17:
                    m0Var.f8243o = o0(strTrim);
                    break;
                case 18:
                    m0Var.f8244p = o0(strTrim);
                    break;
            }
        }
    }

    private static g.e0.h I0(String str) {
        str.hashCode();
        if (str.equals("ltr")) {
            return g.e0.h.LTR;
        }
        if (str.equals("rtl")) {
            return g.e0.h.RTL;
        }
        return null;
    }

    private void J(g.r rVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            switch (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()]) {
                case 26:
                    rVar.f8266r = o0(strTrim);
                    break;
                case 27:
                    rVar.f8267s = o0(strTrim);
                    break;
                case 28:
                    g.p pVarO0 = o0(strTrim);
                    rVar.f8268t = pVarO0;
                    if (pVarO0.g()) {
                        throw new m2.j("Invalid <marker> element. markerWidth cannot be negative");
                    }
                    break;
                    break;
                case 29:
                    g.p pVarO02 = o0(strTrim);
                    rVar.f8269u = pVarO02;
                    if (pVarO02.g()) {
                        throw new m2.j("Invalid <marker> element. markerHeight cannot be negative");
                    }
                    break;
                    break;
                case 30:
                    if (!"strokeWidth".equals(strTrim)) {
                        if (!"userSpaceOnUse".equals(strTrim)) {
                            throw new m2.j("Invalid value for attribute markerUnits");
                        }
                        rVar.f8265q = true;
                    } else {
                        rVar.f8265q = false;
                    }
                    break;
                case 31:
                    if ("auto".equals(strTrim)) {
                        rVar.f8270v = Float.valueOf(Float.NaN);
                    } else {
                        rVar.f8270v = Float.valueOf(f0(strTrim));
                    }
                    break;
            }
        }
    }

    private Matrix J0(String str) throws m2.j {
        Matrix matrix = new Matrix();
        i iVar = new i(str);
        iVar.A();
        while (!iVar.h()) {
            String strO = iVar.o();
            if (strO == null) {
                throw new m2.j("Bad transform function encountered in transform list: " + str);
            }
            switch (strO) {
                case "matrix":
                    iVar.A();
                    float fN = iVar.n();
                    iVar.z();
                    float fN2 = iVar.n();
                    iVar.z();
                    float fN3 = iVar.n();
                    iVar.z();
                    float fN4 = iVar.n();
                    iVar.z();
                    float fN5 = iVar.n();
                    iVar.z();
                    float fN6 = iVar.n();
                    iVar.A();
                    if (Float.isNaN(fN6) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    Matrix matrix2 = new Matrix();
                    matrix2.setValues(new float[]{fN, fN3, fN5, fN2, fN4, fN6, 0.0f, 0.0f, 1.0f});
                    matrix.preConcat(matrix2);
                    break;
                    break;
                case "rotate":
                    iVar.A();
                    float fN7 = iVar.n();
                    float fX = iVar.x();
                    float fX2 = iVar.x();
                    iVar.A();
                    if (Float.isNaN(fN7) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    if (Float.isNaN(fX)) {
                        matrix.preRotate(fN7);
                    } else {
                        if (Float.isNaN(fX2)) {
                            throw new m2.j("Invalid transform list: " + str);
                        }
                        matrix.preRotate(fN7, fX, fX2);
                    }
                    break;
                    break;
                case "scale":
                    iVar.A();
                    float fN8 = iVar.n();
                    float fX3 = iVar.x();
                    iVar.A();
                    if (Float.isNaN(fN8) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    if (!Float.isNaN(fX3)) {
                        matrix.preScale(fN8, fX3);
                    } else {
                        matrix.preScale(fN8, fN8);
                    }
                    break;
                    break;
                case "skewX":
                    iVar.A();
                    float fN9 = iVar.n();
                    iVar.A();
                    if (Float.isNaN(fN9) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    matrix.preSkew((float) Math.tan(Math.toRadians(fN9)), 0.0f);
                    break;
                    break;
                case "skewY":
                    iVar.A();
                    float fN10 = iVar.n();
                    iVar.A();
                    if (Float.isNaN(fN10) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    matrix.preSkew(0.0f, (float) Math.tan(Math.toRadians(fN10)));
                    break;
                    break;
                case "translate":
                    iVar.A();
                    float fN11 = iVar.n();
                    float fX4 = iVar.x();
                    iVar.A();
                    if (Float.isNaN(fN11) || !iVar.f(')')) {
                        throw new m2.j("Invalid transform list: " + str);
                    }
                    if (!Float.isNaN(fX4)) {
                        matrix.preTranslate(fN11, fX4);
                    } else {
                        matrix.preTranslate(fN11, 0.0f);
                    }
                    break;
                    break;
                default:
                    throw new m2.j("Invalid transform list fn: " + strO + ")");
            }
            if (iVar.h()) {
                return matrix;
            }
            iVar.z();
        }
        return matrix;
    }

    private void K(g.s sVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                sVar.f8274q = o0(strTrim);
            } else if (i8 == 2) {
                sVar.f8275r = o0(strTrim);
            } else if (i8 == 3) {
                g.p pVarO0 = o0(strTrim);
                sVar.f8276s = pVarO0;
                if (pVarO0.g()) {
                    throw new m2.j("Invalid <mask> element. width cannot be negative");
                }
            } else if (i8 == 4) {
                g.p pVarO02 = o0(strTrim);
                sVar.f8277t = pVarO02;
                if (pVarO02.g()) {
                    throw new m2.j("Invalid <mask> element. height cannot be negative");
                }
            } else if (i8 != 43) {
                if (i8 != 44) {
                    continue;
                } else if ("objectBoundingBox".equals(strTrim)) {
                    sVar.f8273p = Boolean.FALSE;
                } else {
                    if (!"userSpaceOnUse".equals(strTrim)) {
                        throw new m2.j("Invalid value for attribute maskContentUnits");
                    }
                    sVar.f8273p = Boolean.TRUE;
                }
            } else if ("objectBoundingBox".equals(strTrim)) {
                sVar.f8272o = Boolean.FALSE;
            } else {
                if (!"userSpaceOnUse".equals(strTrim)) {
                    throw new m2.j("Invalid value for attribute maskUnits");
                }
                sVar.f8272o = Boolean.TRUE;
            }
        }
    }

    private void K0(InputStream inputStream) throws m2.j {
        Log.d("SVGParser", "Falling back to SAX parser");
        try {
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            XMLReader xMLReader = sAXParserFactoryNewInstance.newSAXParser().getXMLReader();
            f fVar = new f(this, null);
            xMLReader.setContentHandler(fVar);
            xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", fVar);
            xMLReader.parse(new InputSource(inputStream));
        } catch (IOException e7) {
            throw new m2.j("Stream error", e7);
        } catch (ParserConfigurationException e8) {
            throw new m2.j("XML parser problem", e8);
        } catch (SAXException e9) {
            throw new m2.j("SVG parse error", e9);
        }
    }

    private void L(g.v vVar, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 8) {
                vVar.f8282o = u0(strTrim);
            } else if (i8 != 9) {
                continue;
            } else {
                Float fValueOf = Float.valueOf(f0(strTrim));
                vVar.f8283p = fValueOf;
                if (fValueOf.floatValue() < 0.0f) {
                    throw new m2.j("Invalid <path> element. pathLength cannot be negative");
                }
            }
        }
    }

    private void L0(InputStream inputStream, boolean z6) throws m2.j {
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                j jVar = new j(xmlPullParserNewPullParser);
                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                xmlPullParserNewPullParser.setInput(inputStream, null);
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.nextToken()) {
                    if (eventType == 0) {
                        W0();
                    } else if (eventType == 8) {
                        Log.d("SVGParser", "PROC INSTR: " + xmlPullParserNewPullParser.getText());
                        i iVar = new i(xmlPullParserNewPullParser.getText());
                        r(iVar.r(), x0(iVar));
                    } else if (eventType == 10) {
                        if (z6 && this.f8358a.m() == null && xmlPullParserNewPullParser.getText().contains("<!ENTITY ")) {
                            try {
                                Log.d("SVGParser", "Switching to SAX parser to process entities");
                                inputStream.reset();
                                K0(inputStream);
                                return;
                            } catch (IOException unused) {
                                Log.w("SVGParser", "Detected internal entity definitions, but could not parse them.");
                                return;
                            }
                        }
                    } else if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (xmlPullParserNewPullParser.getPrefix() != null) {
                            name = xmlPullParserNewPullParser.getPrefix() + ':' + name;
                        }
                        X0(xmlPullParserNewPullParser.getNamespace(), xmlPullParserNewPullParser.getName(), name, jVar);
                    } else if (eventType == 3) {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (xmlPullParserNewPullParser.getPrefix() != null) {
                            name2 = xmlPullParserNewPullParser.getPrefix() + ':' + name2;
                        }
                        p(xmlPullParserNewPullParser.getNamespace(), xmlPullParserNewPullParser.getName(), name2);
                    } else if (eventType == 4) {
                        int[] iArr = new int[2];
                        e1(xmlPullParserNewPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                    } else if (eventType == 5) {
                        c1(xmlPullParserNewPullParser.getText());
                    }
                }
                o();
            } catch (XmlPullParserException e7) {
                throw new m2.j("XML parser problem", e7);
            }
        } catch (IOException e8) {
            throw new m2.j("Stream error", e8);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00cf, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void M(m2.g.y r6, org.xml.sax.Attributes r7) throws m2.j {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.k.M(m2.g$y, org.xml.sax.Attributes):void");
    }

    private static g.e0.i M0(String str) {
        str.hashCode();
        if (str.equals("none")) {
            return g.e0.i.None;
        }
        if (str.equals("non-scaling-stroke")) {
            return g.e0.i.NonScalingStroke;
        }
        return null;
    }

    private void N(g.z zVar, Attributes attributes, String str) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            if (g.fromString(attributes.getLocalName(i7)) == g.points) {
                i iVar = new i(attributes.getValue(i7));
                ArrayList arrayList = new ArrayList();
                iVar.A();
                while (!iVar.h()) {
                    float fN = iVar.n();
                    if (Float.isNaN(fN)) {
                        throw new m2.j("Invalid <" + str + "> points attribute. Non-coordinate content found in list.");
                    }
                    iVar.z();
                    float fN2 = iVar.n();
                    if (Float.isNaN(fN2)) {
                        throw new m2.j("Invalid <" + str + "> points attribute. There should be an even number of coordinates.");
                    }
                    iVar.z();
                    arrayList.add(Float.valueOf(fN));
                    arrayList.add(Float.valueOf(fN2));
                }
                zVar.f8298o = new float[arrayList.size()];
                Iterator it = arrayList.iterator();
                int i8 = 0;
                while (it.hasNext()) {
                    zVar.f8298o[i8] = ((Float) it.next()).floatValue();
                    i8++;
                }
            }
        }
    }

    private static g.b N0(String str) throws m2.j {
        i iVar = new i(str);
        iVar.A();
        float fN = iVar.n();
        iVar.z();
        float fN2 = iVar.n();
        iVar.z();
        float fN3 = iVar.n();
        iVar.z();
        float fN4 = iVar.n();
        if (Float.isNaN(fN) || Float.isNaN(fN2) || Float.isNaN(fN3) || Float.isNaN(fN4)) {
            throw new m2.j("Invalid viewBox definition - should have four numbers");
        }
        if (fN3 < 0.0f) {
            throw new m2.j("Invalid viewBox. width cannot be negative");
        }
        if (fN4 >= 0.0f) {
            return new g.b(fN, fN2, fN3, fN4);
        }
        throw new m2.j("Invalid viewBox. height cannot be negative");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void O(m2.g.q0 r5, org.xml.sax.Attributes r6) throws m2.j {
        /*
            r4 = this;
            r0 = 0
        L1:
            int r1 = r6.getLength()
            if (r0 >= r1) goto L5e
            java.lang.String r1 = r6.getValue(r0)
            java.lang.String r1 = r1.trim()
            int[] r2 = m2.k.a.f8368b
            java.lang.String r3 = r6.getLocalName(r0)
            m2.k$g r3 = m2.k.g.fromString(r3)
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 35
            if (r2 == r3) goto L55
            r3 = 36
            if (r2 == r3) goto L4e
            switch(r2) {
                case 12: goto L47;
                case 13: goto L40;
                case 14: goto L2b;
                default: goto L2a;
            }
        L2a:
            goto L5b
        L2b:
            m2.g$p r1 = o0(r1)
            r5.f8262o = r1
            boolean r1 = r1.g()
            if (r1 != 0) goto L38
            goto L5b
        L38:
            m2.j r5 = new m2.j
            java.lang.String r6 = "Invalid <radialGradient> element. r cannot be negative"
            r5.<init>(r6)
            throw r5
        L40:
            m2.g$p r1 = o0(r1)
            r5.f8261n = r1
            goto L5b
        L47:
            m2.g$p r1 = o0(r1)
            r5.f8260m = r1
            goto L5b
        L4e:
            m2.g$p r1 = o0(r1)
            r5.f8264q = r1
            goto L5b
        L55:
            m2.g$p r1 = o0(r1)
            r5.f8263p = r1
        L5b:
            int r0 = r0 + 1
            goto L1
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.k.O(m2.g$q0, org.xml.sax.Attributes):void");
    }

    private void O0(Attributes attributes) throws m2.j {
        l("<path>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.v vVar = new g.v();
        vVar.f8245a = this.f8358a;
        vVar.f8246b = this.f8359b;
        D(vVar, attributes);
        S(vVar, attributes);
        W(vVar, attributes);
        C(vVar, attributes);
        L(vVar, attributes);
        this.f8359b.i(vVar);
    }

    private void P(g.b0 b0Var, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                b0Var.f8150o = o0(strTrim);
            } else if (i8 == 2) {
                b0Var.f8151p = o0(strTrim);
            } else if (i8 == 3) {
                g.p pVarO0 = o0(strTrim);
                b0Var.f8152q = pVarO0;
                if (pVarO0.g()) {
                    throw new m2.j("Invalid <rect> element. width cannot be negative");
                }
            } else if (i8 == 4) {
                g.p pVarO02 = o0(strTrim);
                b0Var.f8153r = pVarO02;
                if (pVarO02.g()) {
                    throw new m2.j("Invalid <rect> element. height cannot be negative");
                }
            } else if (i8 == 10) {
                g.p pVarO03 = o0(strTrim);
                b0Var.f8154s = pVarO03;
                if (pVarO03.g()) {
                    throw new m2.j("Invalid <rect> element. rx cannot be negative");
                }
            } else if (i8 != 11) {
                continue;
            } else {
                g.p pVarO04 = o0(strTrim);
                b0Var.f8155t = pVarO04;
                if (pVarO04.g()) {
                    throw new m2.j("Invalid <rect> element. ry cannot be negative");
                }
            }
        }
    }

    private void P0(Attributes attributes) throws m2.j {
        l("<pattern>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.y yVar = new g.y();
        yVar.f8245a = this.f8358a;
        yVar.f8246b = this.f8359b;
        D(yVar, attributes);
        S(yVar, attributes);
        C(yVar, attributes);
        Y(yVar, attributes);
        M(yVar, attributes);
        this.f8359b.i(yVar);
        this.f8359b = yVar;
    }

    private void Q(g.f0 f0Var, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                f0Var.f8206q = o0(strTrim);
            } else if (i8 == 2) {
                f0Var.f8207r = o0(strTrim);
            } else if (i8 == 3) {
                g.p pVarO0 = o0(strTrim);
                f0Var.f8208s = pVarO0;
                if (pVarO0.g()) {
                    throw new m2.j("Invalid <svg> element. width cannot be negative");
                }
            } else if (i8 == 4) {
                g.p pVarO02 = o0(strTrim);
                f0Var.f8209t = pVarO02;
                if (pVarO02.g()) {
                    throw new m2.j("Invalid <svg> element. height cannot be negative");
                }
            } else if (i8 == 5) {
                f0Var.f8210u = strTrim;
            }
        }
    }

    private void Q0(Attributes attributes) throws m2.j {
        l("<polygon>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.z a0Var = new g.a0();
        a0Var.f8245a = this.f8358a;
        a0Var.f8246b = this.f8359b;
        D(a0Var, attributes);
        S(a0Var, attributes);
        W(a0Var, attributes);
        C(a0Var, attributes);
        N(a0Var, attributes, "polygon");
        this.f8359b.i(a0Var);
    }

    private void R(g.d0 d0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            if (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()] == 37) {
                d0Var.f8165h = n0(strTrim);
            }
        }
    }

    private void R0(Attributes attributes) throws m2.j {
        l("<polyline>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.z zVar = new g.z();
        zVar.f8245a = this.f8358a;
        zVar.f8246b = this.f8359b;
        D(zVar, attributes);
        S(zVar, attributes);
        W(zVar, attributes);
        C(zVar, attributes);
        N(zVar, attributes, "polyline");
        this.f8359b.i(zVar);
    }

    private void S(g.l0 l0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            if (strTrim.length() != 0) {
                int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
                if (i8 == 45) {
                    E0(l0Var, strTrim);
                } else if (i8 != 46) {
                    if (l0Var.f8237e == null) {
                        l0Var.f8237e = new g.e0();
                    }
                    S0(l0Var.f8237e, attributes.getLocalName(i7), attributes.getValue(i7).trim());
                } else {
                    l0Var.f8239g = m2.b.f(strTrim);
                }
            }
        }
    }

    static void S0(g.e0 e0Var, String str, String str2) {
        if (str2.length() == 0 || str2.equals("inherit")) {
            return;
        }
        try {
            switch (a.f8368b[g.fromString(str).ordinal()]) {
                case 47:
                    g.o0 o0VarT0 = t0(str2);
                    e0Var.f8169g = o0VarT0;
                    if (o0VarT0 != null) {
                        e0Var.f8168e |= 1;
                        return;
                    }
                    return;
                case 48:
                    g.e0.a aVarE0 = e0(str2);
                    e0Var.f8170h = aVarE0;
                    if (aVarE0 != null) {
                        e0Var.f8168e |= 2;
                        return;
                    }
                    return;
                case 49:
                    Float fR0 = r0(str2);
                    e0Var.f8171i = fR0;
                    if (fR0 != null) {
                        e0Var.f8168e |= 4;
                        return;
                    }
                    return;
                case 50:
                    g.o0 o0VarT02 = t0(str2);
                    e0Var.f8172j = o0VarT02;
                    if (o0VarT02 != null) {
                        e0Var.f8168e |= 8;
                        return;
                    }
                    return;
                case 51:
                    Float fR02 = r0(str2);
                    e0Var.f8173k = fR02;
                    if (fR02 != null) {
                        e0Var.f8168e |= 16;
                        return;
                    }
                    return;
                case 52:
                    e0Var.f8174l = o0(str2);
                    e0Var.f8168e |= 32;
                    break;
                case 53:
                    g.e0.c cVarC0 = C0(str2);
                    e0Var.f8175m = cVarC0;
                    if (cVarC0 != null) {
                        e0Var.f8168e |= 64;
                        return;
                    }
                    return;
                case 54:
                    g.e0.d dVarD0 = D0(str2);
                    e0Var.f8176n = dVarD0;
                    if (dVarD0 != null) {
                        e0Var.f8168e |= 128;
                        return;
                    }
                    return;
                case 55:
                    e0Var.f8177o = Float.valueOf(f0(str2));
                    e0Var.f8168e |= 256;
                    break;
                case 56:
                    if ("none".equals(str2)) {
                        e0Var.f8178p = null;
                        e0Var.f8168e |= 512;
                        return;
                    }
                    g.p[] pVarArrB0 = B0(str2);
                    e0Var.f8178p = pVarArrB0;
                    if (pVarArrB0 != null) {
                        e0Var.f8168e |= 512;
                        return;
                    }
                    return;
                case 57:
                    e0Var.f8179q = o0(str2);
                    e0Var.f8168e |= 1024;
                    break;
                case 58:
                    e0Var.f8180r = r0(str2);
                    e0Var.f8168e |= 2048;
                    return;
                case 59:
                    e0Var.f8181s = b0(str2);
                    e0Var.f8168e |= 4096;
                    break;
                case 60:
                    h0(e0Var, str2);
                    return;
                case 61:
                    List<String> listI0 = i0(str2);
                    e0Var.f8182t = listI0;
                    if (listI0 != null) {
                        e0Var.f8168e |= 8192;
                        return;
                    }
                    return;
                case 62:
                    g.p pVarJ0 = j0(str2);
                    e0Var.f8183u = pVarJ0;
                    if (pVarJ0 != null) {
                        e0Var.f8168e |= 16384;
                        return;
                    }
                    return;
                case 63:
                    Integer numL0 = l0(str2);
                    e0Var.f8184v = numL0;
                    if (numL0 != null) {
                        e0Var.f8168e |= 32768;
                        return;
                    }
                    return;
                case 64:
                    g.e0.b bVarK0 = k0(str2);
                    e0Var.f8185w = bVarK0;
                    if (bVarK0 != null) {
                        e0Var.f8168e |= 65536;
                        return;
                    }
                    return;
                case 65:
                    g.e0.EnumC0124g enumC0124gH0 = H0(str2);
                    e0Var.f8186x = enumC0124gH0;
                    if (enumC0124gH0 != null) {
                        e0Var.f8168e |= 131072;
                        return;
                    }
                    return;
                case 66:
                    g.e0.h hVarI0 = I0(str2);
                    e0Var.f8187y = hVarI0;
                    if (hVarI0 != null) {
                        e0Var.f8168e |= 68719476736L;
                        return;
                    }
                    return;
                case 67:
                    g.e0.f fVarG0 = G0(str2);
                    e0Var.f8188z = fVarG0;
                    if (fVarG0 != null) {
                        e0Var.f8168e |= 262144;
                        return;
                    }
                    return;
                case 68:
                    Boolean boolS0 = s0(str2);
                    e0Var.A = boolS0;
                    if (boolS0 != null) {
                        e0Var.f8168e |= 524288;
                        return;
                    }
                    return;
                case 69:
                    String strM0 = m0(str2, str);
                    e0Var.C = strM0;
                    e0Var.D = strM0;
                    e0Var.E = strM0;
                    e0Var.f8168e |= 14680064;
                    return;
                case 70:
                    e0Var.C = m0(str2, str);
                    e0Var.f8168e |= 2097152;
                    return;
                case 71:
                    e0Var.D = m0(str2, str);
                    e0Var.f8168e |= 4194304;
                    return;
                case 72:
                    e0Var.E = m0(str2, str);
                    e0Var.f8168e |= 8388608;
                    return;
                case 73:
                    if (str2.indexOf(124) < 0) {
                        if ("|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".contains('|' + str2 + '|')) {
                            e0Var.F = Boolean.valueOf(!str2.equals("none"));
                            e0Var.f8168e |= 16777216;
                            return;
                        }
                        return;
                    }
                    return;
                case 74:
                    if (str2.indexOf(124) < 0) {
                        if ("|visible|hidden|collapse|".contains('|' + str2 + '|')) {
                            e0Var.G = Boolean.valueOf(str2.equals("visible"));
                            e0Var.f8168e |= 33554432;
                            return;
                        }
                        return;
                    }
                    return;
                case 75:
                    if (!str2.equals("currentColor")) {
                        try {
                            e0Var.H = b0(str2);
                        } catch (m2.j e7) {
                            Log.w("SVGParser", e7.getMessage());
                            return;
                        }
                        break;
                    } else {
                        e0Var.H = g.C0125g.a();
                    }
                    e0Var.f8168e |= 67108864;
                    return;
                case 76:
                    e0Var.I = r0(str2);
                    e0Var.f8168e |= 134217728;
                    return;
                case 77:
                    g.c cVarA0 = a0(str2);
                    e0Var.B = cVarA0;
                    if (cVarA0 != null) {
                        e0Var.f8168e |= 1048576;
                        return;
                    }
                    return;
                case 78:
                    e0Var.J = m0(str2, str);
                    e0Var.f8168e |= 268435456;
                    return;
                case 79:
                    e0Var.K = e0(str2);
                    e0Var.f8168e |= 536870912;
                    return;
                case 80:
                    e0Var.L = m0(str2, str);
                    e0Var.f8168e |= 1073741824;
                    return;
                case 81:
                    if (!str2.equals("currentColor")) {
                        try {
                            e0Var.M = b0(str2);
                        } catch (m2.j e8) {
                            Log.w("SVGParser", e8.getMessage());
                            return;
                        }
                        break;
                    } else {
                        e0Var.M = g.C0125g.a();
                    }
                    e0Var.f8168e |= 2147483648L;
                    return;
                case 82:
                    e0Var.N = r0(str2);
                    e0Var.f8168e |= 4294967296L;
                    return;
                case 83:
                    if (!str2.equals("currentColor")) {
                        try {
                            e0Var.O = b0(str2);
                        } catch (m2.j e9) {
                            Log.w("SVGParser", e9.getMessage());
                            return;
                        }
                        break;
                    } else {
                        e0Var.O = g.C0125g.a();
                    }
                    e0Var.f8168e |= 8589934592L;
                    return;
                case 84:
                    e0Var.P = r0(str2);
                    e0Var.f8168e |= 17179869184L;
                    return;
                case 85:
                    g.e0.i iVarM0 = M0(str2);
                    e0Var.Q = iVarM0;
                    if (iVarM0 != null) {
                        e0Var.f8168e |= 34359738368L;
                        return;
                    }
                    return;
                case 86:
                    g.e0.e eVarY0 = y0(str2);
                    e0Var.R = eVarY0;
                    if (eVarY0 != null) {
                        e0Var.f8168e |= 137438953472L;
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (m2.j unused) {
        }
    }

    private void T(g.u0 u0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            if (a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()] == 6 && ("".equals(attributes.getURI(i7)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i7)))) {
                u0Var.f8280o = strTrim;
            }
        }
    }

    private void T0(Attributes attributes) throws m2.j {
        l("<radialGradient>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.q0 q0Var = new g.q0();
        q0Var.f8245a = this.f8358a;
        q0Var.f8246b = this.f8359b;
        D(q0Var, attributes);
        S(q0Var, attributes);
        F(q0Var, attributes);
        O(q0Var, attributes);
        this.f8359b.i(q0Var);
        this.f8359b = q0Var;
    }

    private void U(g.z0 z0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 != 6) {
                if (i8 == 39) {
                    z0Var.f8300p = o0(strTrim);
                }
            } else if ("".equals(attributes.getURI(i7)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i7))) {
                z0Var.f8299o = strTrim;
            }
        }
    }

    private void U0(Attributes attributes) throws m2.j {
        l("<rect>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.b0 b0Var = new g.b0();
        b0Var.f8245a = this.f8358a;
        b0Var.f8246b = this.f8359b;
        D(b0Var, attributes);
        S(b0Var, attributes);
        W(b0Var, attributes);
        C(b0Var, attributes);
        P(b0Var, attributes);
        this.f8359b.i(b0Var);
    }

    private void V(g.a1 a1Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                a1Var.f8142o = p0(strTrim);
            } else if (i8 == 2) {
                a1Var.f8143p = p0(strTrim);
            } else if (i8 == 19) {
                a1Var.f8144q = p0(strTrim);
            } else if (i8 == 20) {
                a1Var.f8145r = p0(strTrim);
            }
        }
    }

    private void V0(Attributes attributes) throws m2.j {
        l("<solidColor>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.c0 c0Var = new g.c0();
        c0Var.f8245a = this.f8358a;
        c0Var.f8246b = this.f8359b;
        D(c0Var, attributes);
        S(c0Var, attributes);
        this.f8359b.i(c0Var);
        this.f8359b = c0Var;
    }

    private void W(g.n nVar, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            if (g.fromString(attributes.getLocalName(i7)) == g.transform) {
                nVar.l(J0(attributes.getValue(i7)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        this.f8358a = new m2.g();
    }

    private void X(g.e1 e1Var, Attributes attributes) throws m2.j {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 1) {
                e1Var.f8199q = o0(strTrim);
            } else if (i8 == 2) {
                e1Var.f8200r = o0(strTrim);
            } else if (i8 == 3) {
                g.p pVarO0 = o0(strTrim);
                e1Var.f8201s = pVarO0;
                if (pVarO0.g()) {
                    throw new m2.j("Invalid <use> element. width cannot be negative");
                }
            } else if (i8 == 4) {
                g.p pVarO02 = o0(strTrim);
                e1Var.f8202t = pVarO02;
                if (pVarO02.g()) {
                    throw new m2.j("Invalid <use> element. height cannot be negative");
                }
            } else if (i8 == 6 && ("".equals(attributes.getURI(i7)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i7)))) {
                e1Var.f8198p = strTrim;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0(String str, String str2, String str3, Attributes attributes) throws m2.j {
        if (this.f8360c) {
            this.f8361d++;
        }
        if ("http://www.w3.org/2000/svg".equals(str) || "".equals(str)) {
            if (str2.length() <= 0) {
                str2 = str3;
            }
            h hVarFromString = h.fromString(str2);
            switch (a.f8367a[hVarFromString.ordinal()]) {
                case 1:
                    a1(attributes);
                    break;
                case 2:
                case 3:
                    q(attributes);
                    break;
                case 4:
                    m(attributes);
                    break;
                case 5:
                    i1(attributes);
                    break;
                case 6:
                    O0(attributes);
                    break;
                case 7:
                    U0(attributes);
                    break;
                case 8:
                    i(attributes);
                    break;
                case 9:
                    n(attributes);
                    break;
                case 10:
                    v(attributes);
                    break;
                case 11:
                    R0(attributes);
                    break;
                case 12:
                    Q0(attributes);
                    break;
                case 13:
                    d1(attributes);
                    break;
                case 14:
                    h1(attributes);
                    break;
                case 15:
                    g1(attributes);
                    break;
                case 16:
                    k1(attributes);
                    break;
                case 17:
                    b1(attributes);
                    break;
                case 18:
                    x(attributes);
                    break;
                case 19:
                    w(attributes);
                    break;
                case 20:
                    T0(attributes);
                    break;
                case 21:
                    Y0(attributes);
                    break;
                case 22:
                case 23:
                    this.f8362e = true;
                    this.f8363f = hVarFromString;
                    break;
                case 24:
                    k(attributes);
                    break;
                case 25:
                    f1(attributes);
                    break;
                case 26:
                    P0(attributes);
                    break;
                case 27:
                    u(attributes);
                    break;
                case 28:
                    j1(attributes);
                    break;
                case 29:
                    y(attributes);
                    break;
                case 30:
                    Z0(attributes);
                    break;
                case 31:
                    V0(attributes);
                    break;
                default:
                    this.f8360c = true;
                    this.f8361d = 1;
                    break;
            }
        }
    }

    private void Y(g.r0 r0Var, Attributes attributes) {
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 7) {
                w0(r0Var, strTrim);
            } else if (i8 == 87) {
                r0Var.f8271p = N0(strTrim);
            }
        }
    }

    private void Y0(Attributes attributes) throws m2.j {
        l("<stop>", new Object[0]);
        g.j0 j0Var = this.f8359b;
        if (j0Var == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        if (!(j0Var instanceof g.j)) {
            throw new m2.j("Invalid document. <stop> elements are only valid inside <linearGradient> or <radialGradient> elements.");
        }
        g.d0 d0Var = new g.d0();
        d0Var.f8245a = this.f8358a;
        d0Var.f8246b = this.f8359b;
        D(d0Var, attributes);
        S(d0Var, attributes);
        R(d0Var, attributes);
        this.f8359b.i(d0Var);
        this.f8359b = d0Var;
    }

    private void Z(String str) {
        this.f8358a.a(new m2.b(b.f.screen, b.u.Document).d(str));
    }

    private void Z0(Attributes attributes) throws m2.j {
        l("<style>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        String str = "all";
        boolean zEquals = true;
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            String strTrim = attributes.getValue(i7).trim();
            int i8 = a.f8368b[g.fromString(attributes.getLocalName(i7)).ordinal()];
            if (i8 == 88) {
                zEquals = strTrim.equals("text/css");
            } else if (i8 == 89) {
                str = strTrim;
            }
        }
        if (zEquals && m2.b.b(str, b.f.screen)) {
            this.f8365h = true;
        } else {
            this.f8360c = true;
            this.f8361d = 1;
        }
    }

    private static g.c a0(String str) {
        if ("auto".equals(str) || !str.startsWith("rect(")) {
            return null;
        }
        i iVar = new i(str.substring(5));
        iVar.A();
        g.p pVarQ0 = q0(iVar);
        iVar.z();
        g.p pVarQ02 = q0(iVar);
        iVar.z();
        g.p pVarQ03 = q0(iVar);
        iVar.z();
        g.p pVarQ04 = q0(iVar);
        iVar.A();
        if (iVar.f(')') || iVar.h()) {
            return new g.c(pVarQ0, pVarQ02, pVarQ03, pVarQ04);
        }
        return null;
    }

    private void a1(Attributes attributes) throws m2.j {
        l("<svg>", new Object[0]);
        g.f0 f0Var = new g.f0();
        f0Var.f8245a = this.f8358a;
        f0Var.f8246b = this.f8359b;
        D(f0Var, attributes);
        S(f0Var, attributes);
        C(f0Var, attributes);
        Y(f0Var, attributes);
        Q(f0Var, attributes);
        g.j0 j0Var = this.f8359b;
        if (j0Var == null) {
            this.f8358a.w(f0Var);
        } else {
            j0Var.i(f0Var);
        }
        this.f8359b = f0Var;
    }

    private static g.f b0(String str) throws m2.j {
        if (str.charAt(0) == '#') {
            m2.c cVarB = m2.c.b(str, 1, str.length());
            if (cVarB == null) {
                throw new m2.j("Bad hex colour value: " + str);
            }
            int iA = cVarB.a();
            if (iA == 4) {
                int iD = cVarB.d();
                int i7 = iD & 3840;
                int i8 = iD & 240;
                int i9 = iD & 15;
                return new g.f(i9 | (i7 << 8) | (-16777216) | (i7 << 12) | (i8 << 8) | (i8 << 4) | (i9 << 4));
            }
            if (iA == 5) {
                int iD2 = cVarB.d();
                int i10 = 61440 & iD2;
                int i11 = iD2 & 3840;
                int i12 = iD2 & 240;
                int i13 = iD2 & 15;
                return new g.f((i13 << 24) | (i13 << 28) | (i10 << 8) | (i10 << 4) | (i11 << 4) | i11 | i12 | (i12 >> 4));
            }
            if (iA == 7) {
                return new g.f(cVarB.d() | (-16777216));
            }
            if (iA == 9) {
                return new g.f((cVarB.d() >>> 8) | (cVarB.d() << 24));
            }
            throw new m2.j("Bad hex colour value: " + str);
        }
        String lowerCase = str.toLowerCase(Locale.US);
        boolean zStartsWith = lowerCase.startsWith("rgba(");
        if (!zStartsWith && !lowerCase.startsWith("rgb(")) {
            boolean zStartsWith2 = lowerCase.startsWith("hsla(");
            if (!zStartsWith2 && !lowerCase.startsWith("hsl(")) {
                return c0(lowerCase);
            }
            i iVar = new i(str.substring(zStartsWith2 ? 5 : 4));
            iVar.A();
            float fN = iVar.n();
            float fD = iVar.d(fN);
            if (!Float.isNaN(fD)) {
                iVar.f('%');
            }
            float fD2 = iVar.d(fD);
            if (!Float.isNaN(fD2)) {
                iVar.f('%');
            }
            if (!zStartsWith2) {
                iVar.A();
                if (!Float.isNaN(fD2) && iVar.f(')')) {
                    return new g.f(s(fN, fD, fD2) | (-16777216));
                }
                throw new m2.j("Bad hsl() colour value: " + str);
            }
            float fD3 = iVar.d(fD2);
            iVar.A();
            if (!Float.isNaN(fD3) && iVar.f(')')) {
                return new g.f((j(fD3 * 256.0f) << 24) | s(fN, fD, fD2));
            }
            throw new m2.j("Bad hsla() colour value: " + str);
        }
        i iVar2 = new i(str.substring(zStartsWith ? 5 : 4));
        iVar2.A();
        float fN2 = iVar2.n();
        if (!Float.isNaN(fN2) && iVar2.f('%')) {
            fN2 = (fN2 * 256.0f) / 100.0f;
        }
        float fD4 = iVar2.d(fN2);
        if (!Float.isNaN(fD4) && iVar2.f('%')) {
            fD4 = (fD4 * 256.0f) / 100.0f;
        }
        float fD5 = iVar2.d(fD4);
        if (!Float.isNaN(fD5) && iVar2.f('%')) {
            fD5 = (fD5 * 256.0f) / 100.0f;
        }
        if (!zStartsWith) {
            iVar2.A();
            if (!Float.isNaN(fD5) && iVar2.f(')')) {
                return new g.f((j(fN2) << 16) | (-16777216) | (j(fD4) << 8) | j(fD5));
            }
            throw new m2.j("Bad rgb() colour value: " + str);
        }
        float fD6 = iVar2.d(fD5);
        iVar2.A();
        if (!Float.isNaN(fD6) && iVar2.f(')')) {
            return new g.f((j(fD6 * 256.0f) << 24) | (j(fN2) << 16) | (j(fD4) << 8) | j(fD5));
        }
        throw new m2.j("Bad rgba() colour value: " + str);
    }

    private void b1(Attributes attributes) throws m2.j {
        l("<symbol>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.r0 t0Var = new g.t0();
        t0Var.f8245a = this.f8358a;
        t0Var.f8246b = this.f8359b;
        D(t0Var, attributes);
        S(t0Var, attributes);
        C(t0Var, attributes);
        Y(t0Var, attributes);
        this.f8359b.i(t0Var);
        this.f8359b = t0Var;
    }

    private static g.f c0(String str) throws m2.j {
        Integer numA = c.a(str);
        if (numA != null) {
            return new g.f(numA.intValue());
        }
        throw new m2.j("Invalid colour keyword: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c1(String str) {
        if (this.f8360c) {
            return;
        }
        if (this.f8362e) {
            if (this.f8364g == null) {
                this.f8364g = new StringBuilder(str.length());
            }
            this.f8364g.append(str);
        } else if (this.f8365h) {
            if (this.f8366i == null) {
                this.f8366i = new StringBuilder(str.length());
            }
            this.f8366i.append(str);
        } else if (this.f8359b instanceof g.y0) {
            h(str);
        }
    }

    private static g.o0 d0(String str) {
        str.hashCode();
        if (str.equals("none")) {
            return g.f.f8204h;
        }
        if (str.equals("currentColor")) {
            return g.C0125g.a();
        }
        try {
            return b0(str);
        } catch (m2.j unused) {
            return null;
        }
    }

    private void d1(Attributes attributes) throws m2.j {
        l("<text>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.w0 w0Var = new g.w0();
        w0Var.f8245a = this.f8358a;
        w0Var.f8246b = this.f8359b;
        D(w0Var, attributes);
        S(w0Var, attributes);
        W(w0Var, attributes);
        C(w0Var, attributes);
        V(w0Var, attributes);
        this.f8359b.i(w0Var);
        this.f8359b = w0Var;
    }

    private static g.e0.a e0(String str) {
        if ("nonzero".equals(str)) {
            return g.e0.a.NonZero;
        }
        if ("evenodd".equals(str)) {
            return g.e0.a.EvenOdd;
        }
        return null;
    }

    private void e1(char[] cArr, int i7, int i8) {
        if (this.f8360c) {
            return;
        }
        if (this.f8362e) {
            if (this.f8364g == null) {
                this.f8364g = new StringBuilder(i8);
            }
            this.f8364g.append(cArr, i7, i8);
        } else if (this.f8365h) {
            if (this.f8366i == null) {
                this.f8366i = new StringBuilder(i8);
            }
            this.f8366i.append(cArr, i7, i8);
        } else if (this.f8359b instanceof g.y0) {
            h(new String(cArr, i7, i8));
        }
    }

    private static float f0(String str) throws m2.j {
        int length = str.length();
        if (length != 0) {
            return g0(str, 0, length);
        }
        throw new m2.j("Invalid float value (empty string)");
    }

    private void f1(Attributes attributes) throws m2.j {
        l("<textPath>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.z0 z0Var = new g.z0();
        z0Var.f8245a = this.f8358a;
        z0Var.f8246b = this.f8359b;
        D(z0Var, attributes);
        S(z0Var, attributes);
        C(z0Var, attributes);
        U(z0Var, attributes);
        this.f8359b.i(z0Var);
        this.f8359b = z0Var;
        g.j0 j0Var = z0Var.f8246b;
        if (j0Var instanceof g.b1) {
            z0Var.p((g.b1) j0Var);
        } else {
            z0Var.p(((g.x0) j0Var).h());
        }
    }

    private static float g0(String str, int i7, int i8) throws m2.j {
        float fB = new m2.d().b(str, i7, i8);
        if (!Float.isNaN(fB)) {
            return fB;
        }
        throw new m2.j("Invalid float value: " + str);
    }

    private void g1(Attributes attributes) throws m2.j {
        l("<tref>", new Object[0]);
        g.j0 j0Var = this.f8359b;
        if (j0Var == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        if (!(j0Var instanceof g.y0)) {
            throw new m2.j("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
        }
        g.u0 u0Var = new g.u0();
        u0Var.f8245a = this.f8358a;
        u0Var.f8246b = this.f8359b;
        D(u0Var, attributes);
        S(u0Var, attributes);
        C(u0Var, attributes);
        T(u0Var, attributes);
        this.f8359b.i(u0Var);
        g.j0 j0Var2 = u0Var.f8246b;
        if (j0Var2 instanceof g.b1) {
            u0Var.p((g.b1) j0Var2);
        } else {
            u0Var.p(((g.x0) j0Var2).h());
        }
    }

    private void h(String str) {
        g.h0 h0Var = (g.h0) this.f8359b;
        int size = h0Var.f8212i.size();
        g.n0 n0Var = size == 0 ? null : h0Var.f8212i.get(size - 1);
        if (!(n0Var instanceof g.c1)) {
            this.f8359b.i(new g.c1(str));
            return;
        }
        StringBuilder sb = new StringBuilder();
        g.c1 c1Var = (g.c1) n0Var;
        sb.append(c1Var.f8160c);
        sb.append(str);
        c1Var.f8160c = sb.toString();
    }

    private static void h0(g.e0 e0Var, String str) {
        String strS;
        if ("|caption|icon|menu|message-box|small-caption|status-bar|".contains('|' + str + '|')) {
            i iVar = new i(str);
            Integer numA = null;
            g.e0.b bVarK0 = null;
            String str2 = null;
            while (true) {
                strS = iVar.s('/');
                iVar.A();
                if (strS != null) {
                    if (numA != null && bVarK0 != null) {
                        break;
                    }
                    if (!strS.equals("normal") && (numA != null || (numA = e.a(strS)) == null)) {
                        if (bVarK0 != null || (bVarK0 = k0(strS)) == null) {
                            if (str2 != null || !strS.equals("small-caps")) {
                                break;
                            } else {
                                str2 = strS;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            g.p pVarJ0 = j0(strS);
            if (iVar.f('/')) {
                iVar.A();
                String strR = iVar.r();
                if (strR != null) {
                    try {
                        o0(strR);
                    } catch (m2.j unused) {
                        return;
                    }
                }
                iVar.A();
            }
            e0Var.f8182t = i0(iVar.y());
            e0Var.f8183u = pVarJ0;
            e0Var.f8184v = Integer.valueOf(numA == null ? 400 : numA.intValue());
            if (bVarK0 == null) {
                bVarK0 = g.e0.b.Normal;
            }
            e0Var.f8185w = bVarK0;
            e0Var.f8168e |= 122880;
        }
    }

    private void h1(Attributes attributes) throws m2.j {
        l("<tspan>", new Object[0]);
        g.j0 j0Var = this.f8359b;
        if (j0Var == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        if (!(j0Var instanceof g.y0)) {
            throw new m2.j("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
        }
        g.v0 v0Var = new g.v0();
        v0Var.f8245a = this.f8358a;
        v0Var.f8246b = this.f8359b;
        D(v0Var, attributes);
        S(v0Var, attributes);
        C(v0Var, attributes);
        V(v0Var, attributes);
        this.f8359b.i(v0Var);
        this.f8359b = v0Var;
        g.j0 j0Var2 = v0Var.f8246b;
        if (j0Var2 instanceof g.b1) {
            v0Var.p((g.b1) j0Var2);
        } else {
            v0Var.p(((g.x0) j0Var2).h());
        }
    }

    private void i(Attributes attributes) throws m2.j {
        l("<circle>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.d dVar = new g.d();
        dVar.f8245a = this.f8358a;
        dVar.f8246b = this.f8359b;
        D(dVar, attributes);
        S(dVar, attributes);
        W(dVar, attributes);
        C(dVar, attributes);
        A(dVar, attributes);
        this.f8359b.i(dVar);
    }

    private static List<String> i0(String str) {
        i iVar = new i(str);
        ArrayList arrayList = null;
        do {
            String strQ = iVar.q();
            if (strQ == null) {
                strQ = iVar.u(',');
            }
            if (strQ == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(strQ);
            iVar.z();
        } while (!iVar.h());
        return arrayList;
    }

    private void i1(Attributes attributes) throws m2.j {
        l("<use>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.e1 e1Var = new g.e1();
        e1Var.f8245a = this.f8358a;
        e1Var.f8246b = this.f8359b;
        D(e1Var, attributes);
        S(e1Var, attributes);
        W(e1Var, attributes);
        C(e1Var, attributes);
        X(e1Var, attributes);
        this.f8359b.i(e1Var);
        this.f8359b = e1Var;
    }

    private static int j(float f7) {
        if (f7 < 0.0f) {
            return 0;
        }
        if (f7 > 255.0f) {
            return 255;
        }
        return Math.round(f7);
    }

    private static g.p j0(String str) {
        try {
            g.p pVarA = d.a(str);
            return pVarA == null ? o0(str) : pVarA;
        } catch (m2.j unused) {
            return null;
        }
    }

    private void j1(Attributes attributes) throws m2.j {
        l("<view>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.r0 f1Var = new g.f1();
        f1Var.f8245a = this.f8358a;
        f1Var.f8246b = this.f8359b;
        D(f1Var, attributes);
        C(f1Var, attributes);
        Y(f1Var, attributes);
        this.f8359b.i(f1Var);
        this.f8359b = f1Var;
    }

    private void k(Attributes attributes) throws m2.j {
        l("<clipPath>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.e eVar = new g.e();
        eVar.f8245a = this.f8358a;
        eVar.f8246b = this.f8359b;
        D(eVar, attributes);
        S(eVar, attributes);
        W(eVar, attributes);
        C(eVar, attributes);
        B(eVar, attributes);
        this.f8359b.i(eVar);
        this.f8359b = eVar;
    }

    private static g.e0.b k0(String str) {
        str.hashCode();
        switch (str) {
            case "oblique":
                return g.e0.b.Oblique;
            case "italic":
                return g.e0.b.Italic;
            case "normal":
                return g.e0.b.Normal;
            default:
                return null;
        }
    }

    private void k1(Attributes attributes) throws m2.j {
        l("<switch>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.s0 s0Var = new g.s0();
        s0Var.f8245a = this.f8358a;
        s0Var.f8246b = this.f8359b;
        D(s0Var, attributes);
        S(s0Var, attributes);
        W(s0Var, attributes);
        C(s0Var, attributes);
        this.f8359b.i(s0Var);
        this.f8359b = s0Var;
    }

    private void l(String str, Object... objArr) {
    }

    private static Integer l0(String str) {
        return e.a(str);
    }

    private void m(Attributes attributes) throws m2.j {
        l("<defs>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.h hVar = new g.h();
        hVar.f8245a = this.f8358a;
        hVar.f8246b = this.f8359b;
        D(hVar, attributes);
        S(hVar, attributes);
        W(hVar, attributes);
        this.f8359b.i(hVar);
        this.f8359b = hVar;
    }

    private static String m0(String str, String str2) {
        if (!str.equals("none") && str.startsWith("url(")) {
            return str.endsWith(")") ? str.substring(4, str.length() - 1).trim() : str.substring(4).trim();
        }
        return null;
    }

    private void n(Attributes attributes) throws m2.j {
        l("<ellipse>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.i iVar = new g.i();
        iVar.f8245a = this.f8358a;
        iVar.f8246b = this.f8359b;
        D(iVar, attributes);
        S(iVar, attributes);
        W(iVar, attributes);
        C(iVar, attributes);
        E(iVar, attributes);
        this.f8359b.i(iVar);
    }

    private Float n0(String str) throws m2.j {
        if (str.length() == 0) {
            throw new m2.j("Invalid offset value in <stop> (empty string)");
        }
        int length = str.length();
        boolean z6 = true;
        if (str.charAt(str.length() - 1) == '%') {
            length--;
        } else {
            z6 = false;
        }
        try {
            float fG0 = g0(str, 0, length);
            float f7 = 100.0f;
            if (z6) {
                fG0 /= 100.0f;
            }
            if (fG0 < 0.0f) {
                f7 = 0.0f;
            } else if (fG0 <= 100.0f) {
                f7 = fG0;
            }
            return Float.valueOf(f7);
        } catch (NumberFormatException e7) {
            throw new m2.j("Invalid offset value in <stop>: " + str, e7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
    }

    static g.p o0(String str) throws m2.j {
        if (str.length() == 0) {
            throw new m2.j("Invalid length value (empty string)");
        }
        int length = str.length();
        g.d1 d1VarValueOf = g.d1.px;
        char cCharAt = str.charAt(length - 1);
        if (cCharAt == '%') {
            length--;
            d1VarValueOf = g.d1.percent;
        } else if (length > 2 && Character.isLetter(cCharAt) && Character.isLetter(str.charAt(length - 2))) {
            length -= 2;
            try {
                d1VarValueOf = g.d1.valueOf(str.substring(length).toLowerCase(Locale.US));
            } catch (IllegalArgumentException unused) {
                throw new m2.j("Invalid length unit specifier: " + str);
            }
        }
        try {
            return new g.p(g0(str, 0, length), d1VarValueOf);
        } catch (NumberFormatException e7) {
            throw new m2.j("Invalid length value: " + str, e7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(String str, String str2, String str3) {
        if (this.f8360c) {
            int i7 = this.f8361d - 1;
            this.f8361d = i7;
            if (i7 == 0) {
                this.f8360c = false;
                return;
            }
        }
        if ("http://www.w3.org/2000/svg".equals(str) || "".equals(str)) {
            if (str2.length() <= 0) {
                str2 = str3;
            }
            int i8 = a.f8367a[h.fromString(str2).ordinal()];
            if (i8 != 1 && i8 != 2 && i8 != 4 && i8 != 5 && i8 != 13 && i8 != 14) {
                switch (i8) {
                    case 22:
                    case 23:
                        this.f8362e = false;
                        StringBuilder sb = this.f8364g;
                        if (sb != null) {
                            h hVar = this.f8363f;
                            if (hVar == h.title) {
                                this.f8358a.x(sb.toString());
                            } else if (hVar == h.desc) {
                                this.f8358a.s(sb.toString());
                            }
                            this.f8364g.setLength(0);
                        }
                        break;
                    case 30:
                        StringBuilder sb2 = this.f8366i;
                        if (sb2 != null) {
                            this.f8365h = false;
                            Z(sb2.toString());
                            this.f8366i.setLength(0);
                        }
                        break;
                }
                return;
            }
            this.f8359b = ((g.n0) this.f8359b).f8246b;
        }
    }

    private static List<g.p> p0(String str) throws m2.j {
        if (str.length() == 0) {
            throw new m2.j("Invalid length list (empty string)");
        }
        ArrayList arrayList = new ArrayList(1);
        i iVar = new i(str);
        iVar.A();
        while (!iVar.h()) {
            float fN = iVar.n();
            if (Float.isNaN(fN)) {
                throw new m2.j("Invalid length list value: " + iVar.b());
            }
            g.d1 d1VarV = iVar.v();
            if (d1VarV == null) {
                d1VarV = g.d1.px;
            }
            arrayList.add(new g.p(fN, d1VarV));
            iVar.z();
        }
        return arrayList;
    }

    private void q(Attributes attributes) throws m2.j {
        l("<g>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.m mVar = new g.m();
        mVar.f8245a = this.f8358a;
        mVar.f8246b = this.f8359b;
        D(mVar, attributes);
        S(mVar, attributes);
        W(mVar, attributes);
        C(mVar, attributes);
        this.f8359b.i(mVar);
        this.f8359b = mVar;
    }

    private static g.p q0(i iVar) {
        return iVar.g("auto") ? new g.p(0.0f) : iVar.p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(String str, Map<String, String> map) {
        if (str.equals("xml-stylesheet")) {
            m2.g.k();
        }
    }

    private static Float r0(String str) {
        try {
            float fF0 = f0(str);
            if (fF0 < 0.0f) {
                fF0 = 0.0f;
            } else if (fF0 > 1.0f) {
                fF0 = 1.0f;
            }
            return Float.valueOf(fF0);
        } catch (m2.j unused) {
            return null;
        }
    }

    private static int s(float f7, float f8, float f9) {
        float f10 = f7 % 360.0f;
        if (f7 < 0.0f) {
            f10 += 360.0f;
        }
        float f11 = f10 / 60.0f;
        float f12 = f8 / 100.0f;
        float f13 = f9 / 100.0f;
        if (f12 < 0.0f) {
            f12 = 0.0f;
        } else if (f12 > 1.0f) {
            f12 = 1.0f;
        }
        float f14 = f13 >= 0.0f ? f13 > 1.0f ? 1.0f : f13 : 0.0f;
        float f15 = f14 <= 0.5f ? (f12 + 1.0f) * f14 : (f14 + f12) - (f12 * f14);
        float f16 = (f14 * 2.0f) - f15;
        return j(t(f16, f15, f11 - 2.0f) * 256.0f) | (j(t(f16, f15, f11 + 2.0f) * 256.0f) << 16) | (j(t(f16, f15, f11) * 256.0f) << 8);
    }

    private static Boolean s0(String str) {
        str.hashCode();
        switch (str) {
            case "hidden":
            case "scroll":
                return Boolean.FALSE;
            case "auto":
            case "visible":
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    private static float t(float f7, float f8, float f9) {
        float f10;
        if (f9 < 0.0f) {
            f9 += 6.0f;
        }
        if (f9 >= 6.0f) {
            f9 -= 6.0f;
        }
        if (f9 < 1.0f) {
            f10 = (f8 - f7) * f9;
        } else {
            if (f9 < 3.0f) {
                return f8;
            }
            if (f9 >= 4.0f) {
                return f7;
            }
            f10 = (f8 - f7) * (4.0f - f9);
        }
        return f10 + f7;
    }

    private static g.o0 t0(String str) {
        if (!str.startsWith("url(")) {
            return d0(str);
        }
        int iIndexOf = str.indexOf(")");
        if (iIndexOf == -1) {
            return new g.u(str.substring(4).trim(), null);
        }
        String strTrim = str.substring(4, iIndexOf).trim();
        String strTrim2 = str.substring(iIndexOf + 1).trim();
        return new g.u(strTrim, strTrim2.length() > 0 ? d0(strTrim2) : null);
    }

    private void u(Attributes attributes) throws m2.j {
        l("<image>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.o oVar = new g.o();
        oVar.f8245a = this.f8358a;
        oVar.f8246b = this.f8359b;
        D(oVar, attributes);
        S(oVar, attributes);
        W(oVar, attributes);
        C(oVar, attributes);
        G(oVar, attributes);
        this.f8359b.i(oVar);
        this.f8359b = oVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x028e, code lost:
    
        android.util.Log.e("SVGParser", "Bad path coords for " + ((char) r12) + " path segment");
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x027b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x027c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static m2.g.w u0(java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 760
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.k.u0(java.lang.String):m2.g$w");
    }

    private void v(Attributes attributes) throws m2.j {
        l("<line>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.q qVar = new g.q();
        qVar.f8245a = this.f8358a;
        qVar.f8246b = this.f8359b;
        D(qVar, attributes);
        S(qVar, attributes);
        W(qVar, attributes);
        C(qVar, attributes);
        H(qVar, attributes);
        this.f8359b.i(qVar);
    }

    static m2.e v0(String str) throws m2.j {
        i iVar = new i(str);
        iVar.A();
        String strR = iVar.r();
        if ("defer".equals(strR)) {
            iVar.A();
            strR = iVar.r();
        }
        e.a aVarA = b.a(strR);
        e.b bVar = null;
        iVar.A();
        if (!iVar.h()) {
            String strR2 = iVar.r();
            strR2.hashCode();
            if (strR2.equals("meet")) {
                bVar = e.b.meet;
            } else {
                if (!strR2.equals("slice")) {
                    throw new m2.j("Invalid preserveAspectRatio definition: " + str);
                }
                bVar = e.b.slice;
            }
        }
        return new m2.e(aVarA, bVar);
    }

    private void w(Attributes attributes) throws m2.j {
        l("<linearGradient>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.m0 m0Var = new g.m0();
        m0Var.f8245a = this.f8358a;
        m0Var.f8246b = this.f8359b;
        D(m0Var, attributes);
        S(m0Var, attributes);
        F(m0Var, attributes);
        I(m0Var, attributes);
        this.f8359b.i(m0Var);
        this.f8359b = m0Var;
    }

    private static void w0(g.p0 p0Var, String str) {
        p0Var.f8255o = v0(str);
    }

    private void x(Attributes attributes) throws m2.j {
        l("<marker>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.r rVar = new g.r();
        rVar.f8245a = this.f8358a;
        rVar.f8246b = this.f8359b;
        D(rVar, attributes);
        S(rVar, attributes);
        C(rVar, attributes);
        Y(rVar, attributes);
        J(rVar, attributes);
        this.f8359b.i(rVar);
        this.f8359b = rVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> x0(i iVar) {
        HashMap map = new HashMap();
        iVar.A();
        String strS = iVar.s('=');
        while (strS != null) {
            iVar.f('=');
            map.put(strS, iVar.q());
            iVar.A();
            strS = iVar.s('=');
        }
        return map;
    }

    private void y(Attributes attributes) throws m2.j {
        l("<mask>", new Object[0]);
        if (this.f8359b == null) {
            throw new m2.j("Invalid document. Root element must be <svg>");
        }
        g.s sVar = new g.s();
        sVar.f8245a = this.f8358a;
        sVar.f8246b = this.f8359b;
        D(sVar, attributes);
        S(sVar, attributes);
        C(sVar, attributes);
        K(sVar, attributes);
        this.f8359b.i(sVar);
        this.f8359b = sVar;
    }

    private static g.e0.e y0(String str) {
        str.hashCode();
        switch (str) {
            case "optimizeQuality":
                return g.e0.e.optimizeQuality;
            case "auto":
                return g.e0.e.auto;
            case "optimizeSpeed":
                return g.e0.e.optimizeSpeed;
            default:
                return null;
        }
    }

    private static Set<String> z0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            String strR = iVar.r();
            if (strR.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                hashSet.add(strR.substring(35));
            } else {
                hashSet.add("UNSUPPORTED");
            }
            iVar.A();
        }
        return hashSet;
    }

    m2.g z(InputStream inputStream, boolean z6) {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        try {
            inputStream.mark(3);
            int i7 = inputStream.read() + (inputStream.read() << 8);
            inputStream.reset();
            if (i7 == 35615) {
                inputStream = new BufferedInputStream(new GZIPInputStream(inputStream));
            }
        } catch (IOException unused) {
        }
        try {
            inputStream.mark(4096);
            L0(inputStream, z6);
            return this.f8358a;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused2) {
                Log.e("SVGParser", "Exception thrown closing input stream");
            }
        }
    }
}
