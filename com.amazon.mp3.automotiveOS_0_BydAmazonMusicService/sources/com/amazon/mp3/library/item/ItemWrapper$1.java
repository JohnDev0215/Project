package com.amazon.mp3.library.item;

/* JADX INFO: loaded from: classes3.dex */
/* synthetic */ class ItemWrapper$1 {
    static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType;
    static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType;

    static {
        int[] iArr = new int[ItemWrapper$IdType.values().length];
        $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType = iArr;
        try {
            iArr[ItemWrapper$IdType.URI.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType[ItemWrapper$IdType.CID.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType[ItemWrapper$IdType.LCID.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType[ItemWrapper$IdType.MERGED_ID.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$IdType[ItemWrapper$IdType.ASIN.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        int[] iArr2 = new int[ItemWrapper$ItemType.values().length];
        $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType = iArr2;
        try {
            iArr2[ItemWrapper$ItemType.ALBUM.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.ARTIST.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.UDO_PLAYLIST.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.SMART_PLAYLIST.ordinal()] = 4;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.PRIME_PLAYLIST.ordinal()] = 5;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.SONG.ordinal()] = 6;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            $SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ItemWrapper$ItemType.GENRE.ordinal()] = 7;
        } catch (NoSuchFieldError unused12) {
        }
    }
}
