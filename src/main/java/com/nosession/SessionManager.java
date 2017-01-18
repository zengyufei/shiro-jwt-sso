package com.nosession;

import java.io.IOException;  
import org.apache.catalina.Lifecycle;  
import org.apache.catalina.LifecycleException;  
import org.apache.catalina.LifecycleListener;  
import org.apache.catalina.LifecycleState;  
import org.apache.catalina.Session;  
import org.apache.catalina.session.ManagerBase;

public class SessionManager extends ManagerBase implements Lifecycle {  

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        super.addLifecycleListener(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {  
        return super.findLifecycleListeners();
    }  
  
    @Override  
    public void removeLifecycleListener(LifecycleListener listener) {
        super.removeLifecycleListener(listener);
    }  
  
    @Override  
    protected synchronized void startInternal() throws LifecycleException {  
        super.startInternal();  
    }
  
    @Override  
    protected synchronized void stopInternal() throws LifecycleException {  
        setState(LifecycleState.STOPPING);  
    }  
  
    @Override  
    public int getRejectedSessions() {  
        return 0;  
    }  
  
    public void setRejectedSessions1(int i) {  
    }  
  
    @Override  
    public void load() throws ClassNotFoundException, IOException {  
    }  
  
    public void setRejectedSessions(int arg0) {  
  
    }  
  
    @Override  
    public void unload() throws IOException {  
    }  
  
    @Override  
    public Session createSession(String sessionId) {  
        // TODO 关键位置  
        return null;  
    }  
  
    public Session createSession() {  
        // TODO 关键位置  
        return null;  
    }  
  
    @Override  
    public Session createEmptySession() {  
        // TODO 关键位置  
        return null;  
    }  
  
    @Override  
    public void add(Session session) {  
  
    }  
  
    @Override  
    public Session findSession(String id) {  
        return null;  
    }  
  
    @Override  
    public void remove(Session session) {  
        remove(session, false);  
    }  
  
    @Override  
    public void remove(Session session, boolean update) {  
  
    }  
  
    @Override  
    public void processExpires() {  
    }  
}  
