package com.ming.entity;

import java.io.Serializable;
import java.util.Date;

public class CollectEntity extends UserEntity implements Serializable {
    private Date lasttime;
    private Date starttime;
    private boolean status;
    private String sessionid;

    public CollectEntity() {

    }
    public CollectEntity(UserEntity attribute) {
        super(attribute);
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return "CollectEntity{" +
                "lasttime=" + lasttime +
                ", starttime=" + starttime +
                ", status=" + status +
                ", sessionid='" + sessionid + '\'' +
                '}';
    }


}
