package org.apache.commons.net.pop3;

import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.utils.AESUtils;
import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ListIterator;
import java.util.StringTokenizer;
import org.apache.commons.net.io.DotTerminatedMessageReader;

/* JADX INFO: loaded from: classes.dex */
public class POP3Client extends POP3 {
    private static POP3MessageInfo parseStatus(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        if (!tokenizer.hasMoreElements()) {
            return null;
        }
        try {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (!tokenizer.hasMoreElements()) {
                return null;
            }
            int size = Integer.parseInt(tokenizer.nextToken());
            return new POP3MessageInfo(num, size);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static POP3MessageInfo parseUID(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        if (!tokenizer.hasMoreElements()) {
            return null;
        }
        try {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (!tokenizer.hasMoreElements()) {
                return null;
            }
            String line2 = tokenizer.nextToken();
            return new POP3MessageInfo(num, line2);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean capa() throws IOException {
        if (sendCommand(12) == 0) {
            getAdditionalReply();
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) throws IOException {
        if (getState() != 0 || sendCommand(0, username) != 0 || sendCommand(1, password) != 0) {
            return false;
        }
        setState(1);
        return true;
    }

    public boolean login(String username, String timestamp, String secret) throws NoSuchAlgorithmException, IOException {
        if (getState() != 0) {
            return false;
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest((timestamp + secret).getBytes(getCharset()));
        StringBuilder digestBuffer = new StringBuilder(128);
        for (byte b : digest) {
            int digit = b & ReservationCharging.DEFAULT_ELECTROVALENCE;
            if (digit <= 15) {
                digestBuffer.append("0");
            }
            digestBuffer.append(Integer.toHexString(digit));
        }
        StringBuilder buffer = new StringBuilder(AESUtils.ECB_256_LENGTH);
        buffer.append(username);
        buffer.append(' ');
        buffer.append(digestBuffer.toString());
        if (sendCommand(9, buffer.toString()) != 0) {
            return false;
        }
        setState(1);
        return true;
    }

    public boolean logout() throws IOException {
        if (getState() == 1) {
            setState(2);
        }
        sendCommand(2);
        return this.replyCode == 0;
    }

    public boolean noop() throws IOException {
        return getState() == 1 && sendCommand(7) == 0;
    }

    public boolean deleteMessage(int messageId) throws IOException {
        return getState() == 1 && sendCommand(6, Integer.toString(messageId)) == 0;
    }

    public boolean reset() throws IOException {
        return getState() == 1 && sendCommand(8) == 0;
    }

    public POP3MessageInfo status() throws IOException {
        if (getState() == 1 && sendCommand(3) == 0) {
            return parseStatus(this.lastReplyLine.substring(3));
        }
        return null;
    }

    public POP3MessageInfo listMessage(int messageId) throws IOException {
        if (getState() == 1 && sendCommand(4, Integer.toString(messageId)) == 0) {
            return parseStatus(this.lastReplyLine.substring(3));
        }
        return null;
    }

    public POP3MessageInfo[] listMessages() throws IOException {
        if (getState() != 1 || sendCommand(4) != 0) {
            return null;
        }
        getAdditionalReply();
        POP3MessageInfo[] messages = new POP3MessageInfo[this.replyLines.size() - 2];
        ListIterator<String> en = this.replyLines.listIterator(1);
        for (int line = 0; line < messages.length; line++) {
            messages[line] = parseStatus(en.next());
        }
        return messages;
    }

    public POP3MessageInfo listUniqueIdentifier(int messageId) throws IOException {
        if (getState() == 1 && sendCommand(11, Integer.toString(messageId)) == 0) {
            return parseUID(this.lastReplyLine.substring(3));
        }
        return null;
    }

    public POP3MessageInfo[] listUniqueIdentifiers() throws IOException {
        if (getState() != 1 || sendCommand(11) != 0) {
            return null;
        }
        getAdditionalReply();
        POP3MessageInfo[] messages = new POP3MessageInfo[this.replyLines.size() - 2];
        ListIterator<String> en = this.replyLines.listIterator(1);
        for (int line = 0; line < messages.length; line++) {
            messages[line] = parseUID(en.next());
        }
        return messages;
    }

    public Reader retrieveMessage(int messageId) throws IOException {
        if (getState() == 1 && sendCommand(5, Integer.toString(messageId)) == 0) {
            return new DotTerminatedMessageReader(this.reader);
        }
        return null;
    }

    public Reader retrieveMessageTop(int messageId, int numLines) throws IOException {
        if (numLines < 0 || getState() != 1) {
            return null;
        }
        if (sendCommand(10, Integer.toString(messageId) + " " + Integer.toString(numLines)) != 0) {
            return null;
        }
        return new DotTerminatedMessageReader(this.reader);
    }
}
