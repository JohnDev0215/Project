package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/* JADX INFO: loaded from: classes6.dex */
public class WebSocketHandshake {
    private static final String ACCEPT_SALT = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final String EMPTY = "";
    private static final String HTTP_HEADER_CONNECTION = "connection";
    private static final String HTTP_HEADER_CONNECTION_VALUE = "upgrade";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_ACCEPT = "sec-websocket-accept";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_PROTOCOL = "sec-websocket-protocol";
    private static final String HTTP_HEADER_UPGRADE = "upgrade";
    private static final String HTTP_HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String SHA1_PROTOCOL = "SHA1";
    Properties customWebSocketHeaders;
    String host;
    InputStream input;
    OutputStream output;
    int port;
    String uri;

    public WebSocketHandshake(InputStream inputStream, OutputStream outputStream, String str, String str2, int i, Properties properties) {
        this.input = inputStream;
        this.output = outputStream;
        this.uri = str;
        this.host = str2;
        this.port = i;
        this.customWebSocketHeaders = properties;
    }

    public void execute() throws IOException {
        byte[] bArr = new byte[16];
        System.arraycopy(UUID.randomUUID().toString().getBytes(), 0, bArr, 0, 16);
        String strEncodeBytes = Base64.encodeBytes(bArr);
        sendHandshakeRequest(strEncodeBytes);
        receiveHandshakeResponse(strEncodeBytes);
    }

    private void sendHandshakeRequest(String str) throws IOException {
        try {
            String rawPath = "/mqtt";
            URI uri = new URI(this.uri);
            if (uri.getRawPath() != null && !uri.getRawPath().isEmpty()) {
                rawPath = uri.getRawPath();
                if (uri.getRawQuery() != null && !uri.getRawQuery().isEmpty()) {
                    rawPath = String.valueOf(rawPath) + "?" + uri.getRawQuery();
                }
            }
            PrintWriter printWriter = new PrintWriter(this.output);
            printWriter.print("GET " + rawPath + " HTTP/1.1" + LINE_SEPARATOR);
            if (this.port != 80) {
                printWriter.print("Host: " + this.host + ":" + this.port + LINE_SEPARATOR);
            } else {
                printWriter.print("Host: " + this.host + LINE_SEPARATOR);
            }
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print("Sec-WebSocket-Key: " + str + LINE_SEPARATOR);
            printWriter.print("Sec-WebSocket-Protocol: mqtt\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            Properties properties = this.customWebSocketHeaders;
            if (properties != null) {
                for (String str2 : properties.keySet()) {
                    printWriter.print(String.valueOf(str2) + ": " + this.customWebSocketHeaders.getProperty(str2) + LINE_SEPARATOR);
                }
            }
            String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                printWriter.print("Authorization: Basic " + Base64.encode(userInfo) + LINE_SEPARATOR);
            }
            printWriter.print(LINE_SEPARATOR);
            printWriter.flush();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void receiveHandshakeResponse(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        ArrayList<String> arrayList = new ArrayList<>();
        String line = bufferedReader.readLine();
        if (line == null) {
            throw new IOException("WebSocket Response header: Invalid response from Server, It may not support WebSockets.");
        }
        while (!line.equals("")) {
            arrayList.add(line);
            line = bufferedReader.readLine();
        }
        Map<String, String> headers = getHeaders(arrayList);
        String str2 = headers.get(HTTP_HEADER_CONNECTION);
        if (str2 == null || str2.equalsIgnoreCase("upgrade")) {
            throw new IOException("WebSocket Response header: Incorrect connection header");
        }
        String str3 = headers.get("upgrade");
        if (str3 == null || !str3.toLowerCase().contains(HTTP_HEADER_UPGRADE_WEBSOCKET)) {
            throw new IOException("WebSocket Response header: Incorrect upgrade.");
        }
        if (headers.get(HTTP_HEADER_SEC_WEBSOCKET_PROTOCOL) == null) {
            throw new IOException("WebSocket Response header: empty sec-websocket-protocol");
        }
        if (!headers.containsKey(HTTP_HEADER_SEC_WEBSOCKET_ACCEPT)) {
            throw new IOException("WebSocket Response header: Missing Sec-WebSocket-Accept");
        }
        try {
            verifyWebSocketKey(str, headers.get(HTTP_HEADER_SEC_WEBSOCKET_ACCEPT));
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage());
        } catch (HandshakeFailedException unused) {
            throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
        }
    }

    private Map<String, String> getHeaders(ArrayList<String> arrayList) {
        HashMap map = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            String[] strArrSplit = arrayList.get(i).split(":");
            map.put(strArrSplit[0].toLowerCase(), strArrSplit[1]);
        }
        return map;
    }

    private void verifyWebSocketKey(String str, String str2) throws HandshakeFailedException, NoSuchAlgorithmException {
        if (!Base64.encodeBytes(sha1(String.valueOf(str) + ACCEPT_SALT)).trim().equals(str2.trim())) {
            throw new HandshakeFailedException();
        }
    }

    private byte[] sha1(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(SHA1_PROTOCOL).digest(str.getBytes());
    }
}
