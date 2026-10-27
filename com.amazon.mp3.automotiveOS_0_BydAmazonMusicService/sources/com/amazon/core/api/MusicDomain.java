package com.amazon.core.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'US' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class MusicDomain {
    private static final /* synthetic */ MusicDomain[] $VALUES;
    public static final MusicDomain AE;
    public static final MusicDomain AE_GAMMA;
    public static final MusicDomain AE_RETAIL;
    public static final MusicDomain AE_RETAIL_GAMMA;
    public static final MusicDomain AU;
    public static final MusicDomain AU_GAMMA;
    public static final MusicDomain BR;
    public static final MusicDomain BR_GAMMA;
    public static final MusicDomain BR_RETAIL;
    public static final MusicDomain BR_RETAIL_GAMMA;
    public static final MusicDomain CA;
    public static final MusicDomain CA_GAMMA;
    public static final MusicDomain DE;
    public static final MusicDomain DE_GAMMA;
    public static final MusicDomain DE_INTEG;
    public static final MusicDomain DE_RETAIL;
    public static final MusicDomain DE_RETAIL_GAMMA;
    public static final MusicDomain DE_RETAIL_SMILE_PROD;
    public static final MusicDomain EG;
    public static final MusicDomain EG_GAMMA;
    public static final MusicDomain EG_RETAIL;
    public static final MusicDomain EG_RETAIL_GAMMA;
    public static final MusicDomain ES;
    public static final MusicDomain ES_GAMMA;
    public static final MusicDomain ES_INTEG;
    public static final MusicDomain ES_RETAIL;
    public static final MusicDomain ES_RETAIL_GAMMA;
    public static final MusicDomain FR;
    public static final MusicDomain FR_GAMMA;
    public static final MusicDomain FR_INTEG;
    public static final MusicDomain FR_RETAIL;
    public static final MusicDomain FR_RETAIL_GAMMA;
    public static final MusicDomain HTML5_DEV;
    public static final MusicDomain HTML5_INTEG;
    public static final MusicDomain HTML5_INTEG_ANACONDA;
    public static final MusicDomain HTML5_INTEG_GODZILLA;
    public static final MusicDomain HTML5_INTEG_KINGKONG;
    public static final MusicDomain HTML5_INTEG_PARTNERS;
    public static final MusicDomain HTML5_LOCAL;
    public static final MusicDomain HTML5_PROD;
    public static final MusicDomain HTML5_QA;
    public static final MusicDomain IN;
    public static final MusicDomain IN_GAMMA;
    public static final MusicDomain IN_RETAIL;
    public static final MusicDomain IN_RETAIL_GAMMA;
    public static final MusicDomain IT;
    public static final MusicDomain IT_GAMMA;
    public static final MusicDomain IT_INTEG;
    public static final MusicDomain IT_RETAIL;
    public static final MusicDomain IT_RETAIL_GAMMA;
    public static final MusicDomain JP;
    public static final MusicDomain JP_GAMMA;
    public static final MusicDomain JP_INTEG;
    public static final MusicDomain JP_RETAIL;
    public static final MusicDomain JP_RETAIL_GAMMA;
    public static final MusicDomain LOCAL;
    private static final Map<String, MusicDomain> LOOKUP;
    public static final MusicDomain MX;
    public static final MusicDomain MX_GAMMA;
    public static final MusicDomain SA;
    public static final MusicDomain SA_GAMMA;
    public static final MusicDomain SA_RETAIL;
    public static final MusicDomain SA_RETAIL_GAMMA;
    public static final MusicDomain UK;
    public static final MusicDomain UK_GAMMA;
    public static final MusicDomain UK_INTEG;
    public static final MusicDomain UK_RETAIL;
    public static final MusicDomain UK_RETAIL_GAMMA;
    public static final MusicDomain UK_RETAIL_SMILE_PROD;
    public static final MusicDomain US;
    public static final MusicDomain US_GAMMA;
    public static final MusicDomain US_INTEG;
    public static final MusicDomain US_RETAIL;
    public static final MusicDomain US_RETAIL_GAMMA;
    public static final MusicDomain US_RETAIL_INTEG;
    public static final MusicDomain US_RETAIL_SMILE_PROD;
    private AmazonDomain amazonDomain;
    private String value;

    public static MusicDomain valueOf(String str) {
        return (MusicDomain) Enum.valueOf(MusicDomain.class, str);
    }

    public static MusicDomain[] values() {
        return (MusicDomain[]) $VALUES.clone();
    }

    static {
        AmazonDomain amazonDomain = AmazonDomain.US;
        MusicDomain musicDomain = new MusicDomain("US", 0, "music.amazon.com", amazonDomain);
        US = musicDomain;
        AmazonDomain amazonDomain2 = AmazonDomain.CA;
        MusicDomain musicDomain2 = new MusicDomain("CA", 1, "music.amazon.ca", amazonDomain2);
        CA = musicDomain2;
        AmazonDomain amazonDomain3 = AmazonDomain.MX;
        MusicDomain musicDomain3 = new MusicDomain("MX", 2, "music.amazon.com.mx", amazonDomain3);
        MX = musicDomain3;
        AmazonDomain amazonDomain4 = AmazonDomain.BR;
        MusicDomain musicDomain4 = new MusicDomain("BR", 3, "music.amazon.com.br", amazonDomain4);
        BR = musicDomain4;
        AmazonDomain amazonDomain5 = AmazonDomain.UK;
        MusicDomain musicDomain5 = new MusicDomain("UK", 4, "music.amazon.co.uk", amazonDomain5);
        UK = musicDomain5;
        AmazonDomain amazonDomain6 = AmazonDomain.DE;
        MusicDomain musicDomain6 = new MusicDomain("DE", 5, "music.amazon.de", amazonDomain6);
        DE = musicDomain6;
        AmazonDomain amazonDomain7 = AmazonDomain.FR;
        MusicDomain musicDomain7 = new MusicDomain("FR", 6, "music.amazon.fr", amazonDomain7);
        FR = musicDomain7;
        AmazonDomain amazonDomain8 = AmazonDomain.IT;
        MusicDomain musicDomain8 = new MusicDomain("IT", 7, "music.amazon.it", amazonDomain8);
        IT = musicDomain8;
        AmazonDomain amazonDomain9 = AmazonDomain.ES;
        MusicDomain musicDomain9 = new MusicDomain("ES", 8, "music.amazon.es", amazonDomain9);
        ES = musicDomain9;
        AmazonDomain amazonDomain10 = AmazonDomain.IN;
        MusicDomain musicDomain10 = new MusicDomain("IN", 9, "music.amazon.in", amazonDomain10);
        IN = musicDomain10;
        AmazonDomain amazonDomain11 = AmazonDomain.JP;
        MusicDomain musicDomain11 = new MusicDomain("JP", 10, "music.amazon.co.jp", amazonDomain11);
        JP = musicDomain11;
        AmazonDomain amazonDomain12 = AmazonDomain.AU;
        MusicDomain musicDomain12 = new MusicDomain("AU", 11, "music.amazon.com.au", amazonDomain12);
        AU = musicDomain12;
        AmazonDomain amazonDomain13 = AmazonDomain.AE;
        MusicDomain musicDomain13 = new MusicDomain("AE", 12, "music.amazon.ae", amazonDomain13);
        AE = musicDomain13;
        AmazonDomain amazonDomain14 = AmazonDomain.SA;
        MusicDomain musicDomain14 = new MusicDomain("SA", 13, "music.amazon.sa", amazonDomain14);
        SA = musicDomain14;
        AmazonDomain amazonDomain15 = AmazonDomain.EG;
        MusicDomain musicDomain15 = new MusicDomain("EG", 14, "music.amazon.eg", amazonDomain15);
        EG = musicDomain15;
        MusicDomain musicDomain16 = new MusicDomain("US_GAMMA", 15, "music-gamma.amazon.com", amazonDomain);
        US_GAMMA = musicDomain16;
        MusicDomain musicDomain17 = new MusicDomain("CA_GAMMA", 16, "music-ca-iad.iad.proxy.amazon.com", amazonDomain2);
        CA_GAMMA = musicDomain17;
        MusicDomain musicDomain18 = new MusicDomain("MX_GAMMA", 17, "music-mx-iad.iad.proxy.amazon.com", amazonDomain3);
        MX_GAMMA = musicDomain18;
        MusicDomain musicDomain19 = new MusicDomain("BR_GAMMA", 18, "music-br-preprod-iad.iad.proxy.amazon.com", amazonDomain4);
        BR_GAMMA = musicDomain19;
        MusicDomain musicDomain20 = new MusicDomain("UK_GAMMA", 19, "music-uk-dub.dub.proxy.amazon.com", amazonDomain5);
        UK_GAMMA = musicDomain20;
        MusicDomain musicDomain21 = new MusicDomain("DE_GAMMA", 20, "music-de-dub.dub.proxy.amazon.com", amazonDomain6);
        DE_GAMMA = musicDomain21;
        MusicDomain musicDomain22 = new MusicDomain("FR_GAMMA", 21, "music-fr-dub.dub.proxy.amazon.com", amazonDomain7);
        FR_GAMMA = musicDomain22;
        MusicDomain musicDomain23 = new MusicDomain("IT_GAMMA", 22, "music-it-dub.dub.proxy.amazon.com", amazonDomain8);
        IT_GAMMA = musicDomain23;
        MusicDomain musicDomain24 = new MusicDomain("ES_GAMMA", 23, "music-es-dub.dub.proxy.amazon.com", amazonDomain9);
        ES_GAMMA = musicDomain24;
        MusicDomain musicDomain25 = new MusicDomain("IN_GAMMA", 24, "music-in-dub.dub.proxy.amazon.com", amazonDomain10);
        IN_GAMMA = musicDomain25;
        MusicDomain musicDomain26 = new MusicDomain("AE_GAMMA", 25, "music-ae-preprod.dub.xcorp.amazon.com", amazonDomain13);
        AE_GAMMA = musicDomain26;
        MusicDomain musicDomain27 = new MusicDomain("SA_GAMMA", 26, "music-sa-preprod.dub.amazon.com", amazonDomain14);
        SA_GAMMA = musicDomain27;
        MusicDomain musicDomain28 = new MusicDomain("EG_GAMMA", 27, "music-eg-preprod.dub.amazon.com", amazonDomain15);
        EG_GAMMA = musicDomain28;
        MusicDomain musicDomain29 = new MusicDomain("JP_GAMMA", 28, "music-jp-pdx.pdx.proxy.amazon.com", amazonDomain11);
        JP_GAMMA = musicDomain29;
        MusicDomain musicDomain30 = new MusicDomain("AU_GAMMA", 29, "music-au-pdx.pdx.proxy.amazon.com", amazonDomain12);
        AU_GAMMA = musicDomain30;
        MusicDomain musicDomain31 = new MusicDomain("US_INTEG", 30, "music-integ-iad.iad.proxy.amazon.com", amazonDomain);
        US_INTEG = musicDomain31;
        MusicDomain musicDomain32 = new MusicDomain("UK_INTEG", 31, "music-uk.integ.amazon.com", amazonDomain5);
        UK_INTEG = musicDomain32;
        MusicDomain musicDomain33 = new MusicDomain("DE_INTEG", 32, "music-de.integ.amazon.com", amazonDomain6);
        DE_INTEG = musicDomain33;
        MusicDomain musicDomain34 = new MusicDomain("FR_INTEG", 33, "music-fr.integ.amazon.com", amazonDomain7);
        FR_INTEG = musicDomain34;
        MusicDomain musicDomain35 = new MusicDomain("IT_INTEG", 34, "music-it.integ.amazon.com", amazonDomain8);
        IT_INTEG = musicDomain35;
        MusicDomain musicDomain36 = new MusicDomain("ES_INTEG", 35, "music-es.integ.amazon.com", amazonDomain9);
        ES_INTEG = musicDomain36;
        MusicDomain musicDomain37 = new MusicDomain("JP_INTEG", 36, "music-jp.integ.amazon.com", amazonDomain11);
        JP_INTEG = musicDomain37;
        MusicDomain musicDomain38 = new MusicDomain("LOCAL", 37, "mp3localhost.amazon.com", amazonDomain);
        LOCAL = musicDomain38;
        MusicDomain musicDomain39 = new MusicDomain("HTML5_LOCAL", 38, "local.html5tv.music.amazon.dev", amazonDomain);
        HTML5_LOCAL = musicDomain39;
        MusicDomain musicDomain40 = new MusicDomain("HTML5_INTEG", 39, "html5-tv-app.s3.amazonaws.com", amazonDomain);
        HTML5_INTEG = musicDomain40;
        MusicDomain musicDomain41 = new MusicDomain("HTML5_INTEG_ANACONDA", 40, "html5-tv-app-anaconda.s3.amazonaws.com", amazonDomain);
        HTML5_INTEG_ANACONDA = musicDomain41;
        MusicDomain musicDomain42 = new MusicDomain("HTML5_INTEG_GODZILLA", 41, "html5-tv-app-godzilla.s3.amazonaws.com", amazonDomain);
        HTML5_INTEG_GODZILLA = musicDomain42;
        MusicDomain musicDomain43 = new MusicDomain("HTML5_INTEG_KINGKONG", 42, "html5-tv-app-kingkong.s3.amazonaws.com", amazonDomain);
        HTML5_INTEG_KINGKONG = musicDomain43;
        MusicDomain musicDomain44 = new MusicDomain("HTML5_INTEG_PARTNERS", 43, "html5-tv-app-partners.s3.amazonaws.com", amazonDomain);
        HTML5_INTEG_PARTNERS = musicDomain44;
        MusicDomain musicDomain45 = new MusicDomain("HTML5_DEV", 44, "dev.html5tv.music.amazon.dev", amazonDomain);
        HTML5_DEV = musicDomain45;
        MusicDomain musicDomain46 = new MusicDomain("HTML5_QA", 45, "qa.html5tv.music.amazon.dev", amazonDomain);
        HTML5_QA = musicDomain46;
        MusicDomain musicDomain47 = new MusicDomain("HTML5_PROD", 46, "html5tv.music.amazon.dev", amazonDomain);
        HTML5_PROD = musicDomain47;
        MusicDomain musicDomain48 = new MusicDomain("US_RETAIL", 47, "www.amazon.com", amazonDomain);
        US_RETAIL = musicDomain48;
        MusicDomain musicDomain49 = new MusicDomain("UK_RETAIL", 48, "www.amazon.co.uk", amazonDomain5);
        UK_RETAIL = musicDomain49;
        MusicDomain musicDomain50 = new MusicDomain("DE_RETAIL", 49, "www.amazon.de", amazonDomain6);
        DE_RETAIL = musicDomain50;
        MusicDomain musicDomain51 = new MusicDomain("FR_RETAIL", 50, "www.amazon.fr", amazonDomain7);
        FR_RETAIL = musicDomain51;
        MusicDomain musicDomain52 = new MusicDomain("IT_RETAIL", 51, "www.amazon.it", amazonDomain8);
        IT_RETAIL = musicDomain52;
        MusicDomain musicDomain53 = new MusicDomain("ES_RETAIL", 52, "www.amazon.es", amazonDomain9);
        ES_RETAIL = musicDomain53;
        MusicDomain musicDomain54 = new MusicDomain("JP_RETAIL", 53, "www.amazon.co.jp", amazonDomain11);
        JP_RETAIL = musicDomain54;
        MusicDomain musicDomain55 = new MusicDomain("BR_RETAIL", 54, "www.amazon.com.br", amazonDomain4);
        BR_RETAIL = musicDomain55;
        MusicDomain musicDomain56 = new MusicDomain("IN_RETAIL", 55, "www.amazon.in", amazonDomain10);
        IN_RETAIL = musicDomain56;
        MusicDomain musicDomain57 = new MusicDomain("AE_RETAIL", 56, "www.amazon.ae", amazonDomain13);
        AE_RETAIL = musicDomain57;
        MusicDomain musicDomain58 = new MusicDomain("SA_RETAIL", 57, "www.amazon.sa", amazonDomain14);
        SA_RETAIL = musicDomain58;
        MusicDomain musicDomain59 = new MusicDomain("EG_RETAIL", 58, "www.amazon.eg", amazonDomain15);
        EG_RETAIL = musicDomain59;
        MusicDomain musicDomain60 = new MusicDomain("US_RETAIL_GAMMA", 59, "pre-prod.amazon.com", amazonDomain);
        US_RETAIL_GAMMA = musicDomain60;
        MusicDomain musicDomain61 = new MusicDomain("UK_RETAIL_GAMMA", 60, "uk-pre-prod.amazon.com", amazonDomain5);
        UK_RETAIL_GAMMA = musicDomain61;
        MusicDomain musicDomain62 = new MusicDomain("DE_RETAIL_GAMMA", 61, "de-pre-prod.amazon.com", amazonDomain6);
        DE_RETAIL_GAMMA = musicDomain62;
        MusicDomain musicDomain63 = new MusicDomain("FR_RETAIL_GAMMA", 62, "fr-pre-prod.amazon.com", amazonDomain7);
        FR_RETAIL_GAMMA = musicDomain63;
        MusicDomain musicDomain64 = new MusicDomain("IT_RETAIL_GAMMA", 63, "it-pre-prod.amazon.com", amazonDomain8);
        IT_RETAIL_GAMMA = musicDomain64;
        MusicDomain musicDomain65 = new MusicDomain("ES_RETAIL_GAMMA", 64, "es-pre-prod.amazon.com", amazonDomain9);
        ES_RETAIL_GAMMA = musicDomain65;
        MusicDomain musicDomain66 = new MusicDomain("JP_RETAIL_GAMMA", 65, "jp-pre-prod.amazon.com", amazonDomain11);
        JP_RETAIL_GAMMA = musicDomain66;
        MusicDomain musicDomain67 = new MusicDomain("BR_RETAIL_GAMMA", 66, "br-pre-prod.amazon.com", amazonDomain4);
        BR_RETAIL_GAMMA = musicDomain67;
        MusicDomain musicDomain68 = new MusicDomain("IN_RETAIL_GAMMA", 67, "in-pre-prod.amazon.com", amazonDomain10);
        IN_RETAIL_GAMMA = musicDomain68;
        MusicDomain musicDomain69 = new MusicDomain("AE_RETAIL_GAMMA", 68, "ae-pre-prod.amazon.com", amazonDomain13);
        AE_RETAIL_GAMMA = musicDomain69;
        MusicDomain musicDomain70 = new MusicDomain("SA_RETAIL_GAMMA", 69, "sa-pre-prod.amazon.com", amazonDomain14);
        SA_RETAIL_GAMMA = musicDomain70;
        MusicDomain musicDomain71 = new MusicDomain("EG_RETAIL_GAMMA", 70, "eg-pre-prod.amazon.com", amazonDomain15);
        EG_RETAIL_GAMMA = musicDomain71;
        MusicDomain musicDomain72 = new MusicDomain("US_RETAIL_INTEG", 71, "development.amazon.com", amazonDomain);
        US_RETAIL_INTEG = musicDomain72;
        MusicDomain musicDomain73 = new MusicDomain("US_RETAIL_SMILE_PROD", 72, "smile.amazon.com", amazonDomain);
        US_RETAIL_SMILE_PROD = musicDomain73;
        MusicDomain musicDomain74 = new MusicDomain("UK_RETAIL_SMILE_PROD", 73, "smile.amazon.co.uk", amazonDomain5);
        UK_RETAIL_SMILE_PROD = musicDomain74;
        MusicDomain musicDomain75 = new MusicDomain("DE_RETAIL_SMILE_PROD", 74, "smile.amazon.de", amazonDomain6);
        DE_RETAIL_SMILE_PROD = musicDomain75;
        $VALUES = new MusicDomain[]{musicDomain, musicDomain2, musicDomain3, musicDomain4, musicDomain5, musicDomain6, musicDomain7, musicDomain8, musicDomain9, musicDomain10, musicDomain11, musicDomain12, musicDomain13, musicDomain14, musicDomain15, musicDomain16, musicDomain17, musicDomain18, musicDomain19, musicDomain20, musicDomain21, musicDomain22, musicDomain23, musicDomain24, musicDomain25, musicDomain26, musicDomain27, musicDomain28, musicDomain29, musicDomain30, musicDomain31, musicDomain32, musicDomain33, musicDomain34, musicDomain35, musicDomain36, musicDomain37, musicDomain38, musicDomain39, musicDomain40, musicDomain41, musicDomain42, musicDomain43, musicDomain44, musicDomain45, musicDomain46, musicDomain47, musicDomain48, musicDomain49, musicDomain50, musicDomain51, musicDomain52, musicDomain53, musicDomain54, musicDomain55, musicDomain56, musicDomain57, musicDomain58, musicDomain59, musicDomain60, musicDomain61, musicDomain62, musicDomain63, musicDomain64, musicDomain65, musicDomain66, musicDomain67, musicDomain68, musicDomain69, musicDomain70, musicDomain71, musicDomain72, musicDomain73, musicDomain74, musicDomain75};
        LOOKUP = new HashMap(values().length);
        for (MusicDomain musicDomain76 : values()) {
            LOOKUP.put(musicDomain76.getValue(), musicDomain76);
        }
    }

    public String getValue() {
        return this.value;
    }

    public AmazonDomain getAmazonDomain() {
        return this.amazonDomain;
    }

    private MusicDomain(String str, int i, String str2, AmazonDomain amazonDomain) {
        this.value = str2;
        this.amazonDomain = amazonDomain;
    }

    public static final MusicDomain lookupByMusicDomainValue(String str) {
        MusicDomain musicDomain = LOOKUP.get(str);
        return musicDomain == null ? US : musicDomain;
    }

    public static List<String> getValues() {
        ArrayList arrayList = new ArrayList();
        for (MusicDomain musicDomain : values()) {
            arrayList.add(musicDomain.value);
        }
        return arrayList;
    }
}
