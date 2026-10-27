package android.hardware.bydauto.yun;

/* JADX INFO: loaded from: classes.dex */
public class ActivateInfo {
    public NationalRegion activateCountry;
    public Region activateRegion;
    public ActiveState activateState;
    public long activateTime;
    public String appPkgName;
    public byte autoVinResult;
    public long licenseTime;
    public ProviderName supplierName;
    public ProviderType supplierType;

    public enum ActiveState {
        ATCIVE_INVALID(0),
        ATCIVE_SUCCESS(1),
        ATCIVE_FAILED(2);

        private final int mVal;

        ActiveState(int i2) {
            this.mVal = i2;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public enum NationalRegion {
        INVALID(0),
        CN(1),
        SG(2),
        HK(3),
        MO(4),
        NP(5),
        AU(6),
        TH(7),
        JP(8),
        LK(9),
        PK(10),
        IN(11),
        MY(12),
        ID(13),
        MM(14),
        LA(15),
        KH(16),
        PH(17),
        PF(18),
        NC(19),
        NZ(20),
        VNM(21),
        CL(22),
        UY(23),
        CO(24),
        DO(25),
        CR(26),
        PE(27),
        US(28),
        EC(29),
        PY(30),
        BR(31),
        BOL(32),
        BS(33),
        PA(34),
        CW(35),
        MX(36),
        CA(37),
        GT(38),
        JM(39),
        SA(40),
        AE(41),
        EG(42),
        GH(43),
        QA(44),
        MA(45),
        BH(46),
        OM(47),
        KW(48),
        NO(49),
        DK(50),
        NL(51),
        GB(52),
        PT(53),
        BE(54),
        SE(55),
        CH(56),
        AT(57),
        IT(58),
        ES(59),
        FI(60),
        IE(61),
        GR(62),
        DE(63),
        FR(64),
        BG(65),
        CY(66),
        HR(67),
        CZ(68),
        EE(69),
        HU(70),
        LV(71),
        LT(72),
        LU(73),
        MT(74),
        PL(75),
        RO(76),
        SK(77),
        SI(78),
        IS(79),
        LI(80),
        TR(81),
        UA(82),
        IL(83),
        ME(84),
        RS(85),
        BA(86),
        MK(97),
        AL(88),
        AM(89),
        AZ(90),
        BY(91),
        MD(92),
        KZ(93),
        KG(94),
        TJ(95),
        UZ(96),
        MC(97),
        RU(98),
        VA(99);

        private final int mVal;

        NationalRegion(int i2) {
            this.mVal = i2;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public enum ProviderName {
        Invalid(0),
        TeleNav(1),
        Cerence(2),
        Spotify(3),
        NeuSoft(4);

        private final int mVal;

        ProviderName(int i2) {
            this.mVal = i2;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public enum ProviderType {
        INVALID(0),
        MAP(1),
        VR(2),
        MUSIC(3);

        private final int mVal;

        ProviderType(int i2) {
            this.mVal = i2;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public enum Region {
        INVALID_REGION(0),
        Europe(1),
        America(2),
        AsiaPacific(3),
        MiddleEastAfrica(4);

        private final int mVal;

        Region(int i2) {
            this.mVal = i2;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public ActivateInfo(ProviderType providerType, byte b2, ProviderName providerName, ActiveState activeState, long j, long j2, NationalRegion nationalRegion, Region region, String str) {
        this.supplierType = providerType;
        this.autoVinResult = b2;
        this.supplierName = providerName;
        this.activateState = activeState;
        this.activateTime = j;
        this.licenseTime = j2;
        this.activateCountry = nationalRegion;
        this.activateRegion = region;
        this.appPkgName = str;
    }

    public NationalRegion getActivateCountry() {
        return this.activateCountry;
    }

    public Region getActivateRegion() {
        return this.activateRegion;
    }

    public ActiveState getActivateState() {
        return this.activateState;
    }

    public long getActivateTime() {
        return this.activateTime;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public byte getAutoVinResult() {
        return this.autoVinResult;
    }

    public long getLicenseTime() {
        return this.licenseTime;
    }

    public ProviderName getSupplierName() {
        return this.supplierName;
    }

    public ProviderType getSupplierType() {
        return this.supplierType;
    }

    public void setActivateCountry(NationalRegion nationalRegion) {
        this.activateCountry = nationalRegion;
    }

    public void setActivateRegion(Region region) {
        this.activateRegion = region;
    }

    public void setActivateState(ActiveState activeState) {
        this.activateState = activeState;
    }

    public void setActivateTime(long j) {
        this.activateTime = j;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public void setAutoVinResult(byte b2) {
        this.autoVinResult = b2;
    }

    public void setLicenseTime(long j) {
        this.licenseTime = j;
    }

    public void setSupplierName(ProviderName providerName) {
        this.supplierName = providerName;
    }

    public void setSupplierType(ProviderType providerType) {
        this.supplierType = providerType;
    }
}
