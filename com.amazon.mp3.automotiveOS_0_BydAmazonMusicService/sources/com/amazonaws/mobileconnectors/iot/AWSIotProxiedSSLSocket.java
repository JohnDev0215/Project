package com.amazonaws.mobileconnectors.iot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes5.dex */
class AWSIotProxiedSSLSocket extends SSLSocket {
    private final Socket proxySocket;
    private SSLSocket socket;
    private final SSLSocketFactory socketFactory;

    public AWSIotProxiedSSLSocket(SSLSocketFactory sSLSocketFactory, Socket socket) {
        this.socketFactory = sSLSocketFactory;
        this.proxySocket = socket;
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) throws IOException {
        connect(socketAddress, 0);
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        this.proxySocket.connect(socketAddress, i);
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        this.socket = (SSLSocket) this.socketFactory.createSocket(this.proxySocket, inetSocketAddress.getHostName(), inetSocketAddress.getPort(), true);
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedCipherSuites() {
        return this.socket.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledCipherSuites() {
        return this.socket.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledCipherSuites(String[] strArr) {
        this.socket.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedProtocols() {
        return this.socket.getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledProtocols() {
        return this.socket.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledProtocols(String[] strArr) {
        this.socket.setEnabledProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return this.socket.getSession();
    }

    @Override // javax.net.ssl.SSLSocket
    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.socket.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.socket.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public void startHandshake() throws IOException {
        this.socket.startHandshake();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setUseClientMode(boolean z) {
        this.socket.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getUseClientMode() {
        return this.socket.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setNeedClientAuth(boolean z) {
        this.socket.setNeedClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getNeedClientAuth() {
        return this.socket.getNeedClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setWantClientAuth(boolean z) {
        this.socket.setWantClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getWantClientAuth() {
        return this.socket.getWantClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnableSessionCreation(boolean z) {
        this.socket.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getEnableSessionCreation() {
        return this.socket.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLParameters getSSLParameters() {
        return this.socket.getSSLParameters();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setSSLParameters(SSLParameters sSLParameters) {
        this.socket.setSSLParameters(sSLParameters);
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.socket.getInetAddress();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.socket.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.socket.getPort();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.socket.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.socket.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.socket.getLocalSocketAddress();
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        return this.socket.getChannel();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) throws SocketException {
        this.socket.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() throws SocketException {
        return this.socket.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i) throws SocketException {
        this.socket.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public int getSoLinger() throws SocketException {
        return this.socket.getSoLinger();
    }

    @Override // java.net.Socket
    public void sendUrgentData(int i) throws IOException {
        this.socket.sendUrgentData(i);
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean z) throws SocketException {
        this.socket.setOOBInline(z);
    }

    @Override // java.net.Socket
    public boolean getOOBInline() throws SocketException {
        return this.socket.getOOBInline();
    }

    @Override // java.net.Socket
    public synchronized int getSoTimeout() throws SocketException {
        return this.socket.getSoTimeout();
    }

    @Override // java.net.Socket
    public synchronized void setSendBufferSize(int i) throws SocketException {
        this.socket.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public synchronized int getSendBufferSize() throws SocketException {
        return this.socket.getSendBufferSize();
    }

    @Override // java.net.Socket
    public synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.socket.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public synchronized int getReceiveBufferSize() throws SocketException {
        return this.socket.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) throws SocketException {
        this.socket.setKeepAlive(z);
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() throws SocketException {
        return this.socket.getKeepAlive();
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i) throws SocketException {
        this.socket.setTrafficClass(i);
    }

    @Override // java.net.Socket
    public int getTrafficClass() throws SocketException {
        return this.socket.getTrafficClass();
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) throws SocketException {
        this.socket.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() throws SocketException {
        return this.socket.getReuseAddress();
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.socket.close();
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        this.socket.shutdownInput();
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        this.socket.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public String toString() {
        return this.socket.toString();
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.socket.isBound();
    }

    @Override // java.net.Socket
    public boolean isClosed() {
        return this.socket.isClosed();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.socket.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.socket.isOutputShutdown();
    }

    @Override // java.net.Socket
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.socket.setPerformancePreferences(i, i2, i3);
    }
}
