package org.apache.commons.net.telnet;

/* JADX INFO: loaded from: classes3.dex */
public class TerminalTypeOptionHandler extends TelnetOptionHandler {
    protected static final int TERMINAL_TYPE = 24;
    protected static final int TERMINAL_TYPE_IS = 0;
    protected static final int TERMINAL_TYPE_SEND = 1;
    private final String termType;

    public TerminalTypeOptionHandler(String termtype, boolean initlocal, boolean initremote, boolean acceptlocal, boolean acceptremote) {
        super(24, initlocal, initremote, acceptlocal, acceptremote);
        this.termType = termtype;
    }

    public TerminalTypeOptionHandler(String termtype) {
        super(24, false, false, false, false);
        this.termType = termtype;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] answerSubnegotiation(int[] suboptionData, int suboptionLength) {
        String str;
        if (suboptionData != null && suboptionLength > 1 && (str = this.termType) != null && suboptionData[0] == 24 && suboptionData[1] == 1) {
            int[] response = new int[str.length() + 2];
            response[0] = 24;
            response[1] = 0;
            for (int ii = 0; ii < this.termType.length(); ii++) {
                response[ii + 2] = this.termType.charAt(ii);
            }
            return response;
        }
        return null;
    }
}
