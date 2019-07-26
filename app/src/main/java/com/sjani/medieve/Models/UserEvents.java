
package com.sjani.medieve.Models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserEvents implements Serializable
{

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    private final static long serialVersionUID = 654146577661272365L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).append("events", events).toString();
    }

}
